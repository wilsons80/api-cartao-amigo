
package br.com.cartaoamigo.infra.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CurrencyWriter {

   private static final BigInteger THOUSAND = new BigInteger("1000");
   private static final BigInteger HUNDRED = new BigInteger("100");
   private static final String CENTO = "cento";
   private static final String CEM = "cem";
   /**
    * Nomes das grandezas numéricas no plural. O mapa a chave do mapa é o
    * expoente de dez do número e o valor é o seu nome no plural. Por exemplo:
    * para chave 3 (10^3) o valor é "mil", para a chave 6 (10^6) o valor é
    * "milhões", e assim por diante. Os nomes foram obtidos de um artigo
    * publicado na seção Dois Mais Dois na revista SuperInteressante nº. 15, de
    * dezembro de 1988 (Editora Abril, São Paulo/SP), disponível em
    * http://www.novomilenio.inf.br/idioma/19881200.htm.
    */
   private final Map<Integer, String> grandezasPlural = new HashMap<Integer, String>();
   private final Map<Integer, String> grandezasSingular = new HashMap<Integer, String>();

   /** Nomes dos números. */
   private final Map<Integer, String> nomes = new HashMap<Integer, String>();

   private static final String MOEDA_SINGULAR = "real";
   private static final String MOEDA_PLURAL = "reais";

   private static final String FRACAO_SINGULAR = "centavo";
   private static final String FRACAO_PLURAL = "centavos";

   private static final String PARTICULA_ADITIVA = "e";
   private static final String PARTICULA_DESCRITIVA = "de";

   /**
    * O conversor reconhece números até a ordem dos setilhões, portanto, o maior
    * valor suportado atualmente é o representado abaixo.
    */
   private static final BigDecimal MAX_SUPPORTED_VALUE = new BigDecimal(
         "999999999999999999999999999.99");

   private static CurrencyWriter currencyWriter;
   
   private CurrencyWriter() {
      preencherGrandezasPlural();
      preencherGrandezasSingular();
      preencherNomes();
   }
   
   public static String escreve(final BigDecimal amount){
      if(currencyWriter == null){
         currencyWriter = new CurrencyWriter();
      }
      return currencyWriter.write(amount);
   }
   
   public static String escreveCheque(final BigDecimal amount){
      StringBuilder linha = new StringBuilder();
      linha.append(CurrencyWriter.escreve(amount));
      int max = 154-linha.length();
      for(int i=0; i< max; i++){
         if(i%2==1)
            linha.append(" ");
         else
            linha.append("*");
      }
      return linha.toString().replaceAll("  ", "**").toUpperCase();
   }
   
   private String write(final BigDecimal amount) {
      if (null == amount) {
         throw new IllegalArgumentException();
      }

      /*
       * TODO substituir o método setScale, abaixo, pela versão cujo parâmetro
       * de arredondamento é um enum
       */
      BigDecimal value = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);

      if (value.compareTo(BigDecimal.ZERO) <= 0) {
         return "";
      }

      if (MAX_SUPPORTED_VALUE.compareTo(value) < 0) {
         throw new IllegalArgumentException("Valor acima do limite suportado.");
      }

      Stack<Integer> decomposed = decompose(value);

      /*
       * Se o número estiver, digamos, na casa dos milhões, a pilha deverá
       * conter 4 elementos sendo os dois últimos os das centenas e dos
       * centavos, respectivamente. Assim, o expoente de dez que representa a
       * grandeza no topo da pilha é o número de (elementos - 2) * 3
       */
      int expoente = 3 * (decomposed.size() - 2); 

      StringBuffer sb = new StringBuffer();
      int lastNonZeroExponent = -1;

      while (!decomposed.empty()) {
         int valor = decomposed.pop();

         if (valor > 0) {
            sb.append(" ").append(PARTICULA_ADITIVA).append(" ");
            sb.append(comporNomeGrupos(valor));
            String nomeGrandeza = obterNomeGrandeza(expoente, valor);
            if (nomeGrandeza.length() > 0) {
               sb.append(" ");
            }
            sb.append(nomeGrandeza);

            lastNonZeroExponent = expoente;
         }

         switch (expoente) { 
            case 0:
               BigInteger parteInteira = value.toBigInteger();

               if (BigInteger.ONE.equals(parteInteira)) {
                  sb.append(" ").append(MOEDA_SINGULAR);
               } else if (parteInteira.compareTo(BigInteger.ZERO) > 0) {
                  if (lastNonZeroExponent >= 6) {
                     sb.append(" ").append(PARTICULA_DESCRITIVA);
                  }
                  sb.append(" ").append(MOEDA_PLURAL);
               }
               break;

            case -3:
               if (1 == valor) {
                  sb.append(" ").append(FRACAO_SINGULAR);
               } else if (valor > 1) {
                  sb.append(" ").append(FRACAO_PLURAL);
               }
               break;
         }

         expoente -= 3;
      }

      return sb.substring(3);
   }

   private StringBuffer comporNomeGrupos(int valor) {
      StringBuffer nome = new StringBuffer();

      int centenas = valor - (valor % 100);
      int unidades = valor % 10;
      int dezenas = (valor - centenas) - unidades;
      int duasCasas = dezenas + unidades;

      if (centenas > 0) {
         nome.append(" ").append(PARTICULA_ADITIVA).append(" ");

         if (100 == centenas) {
            if (duasCasas > 0) {
               nome.append(CENTO);
            } else {
               nome.append(CEM);
            }
         } else {
            nome.append(nomes.get(centenas));
         }
      }

      if (duasCasas > 0) {
         nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
         if (duasCasas < 20) {
            nome.append(nomes.get(duasCasas));
         } else {
            if (dezenas > 0) {
               nome.append(nomes.get(dezenas));
            }

            if (unidades > 0) {
               nome.append(" ").append(PARTICULA_ADITIVA).append(" ");
               nome.append(nomes.get(unidades));
            }
         }
      }

      return nome.delete(0, 3);
   }

   private String obterNomeGrandeza(int exponent, int value) {
      if (exponent < 3) {
         return "";
      }

      if (1 == value) {
         return grandezasSingular.get(exponent);
      } else {
         return grandezasPlural.get(exponent);
      }
   }

   private Stack<Integer> decompose(BigDecimal value) {
      BigInteger intermediate = value.multiply(new BigDecimal(100))
            .toBigInteger();
      Stack<Integer> decomposed = new Stack<Integer>();

      BigInteger[] result = intermediate.divideAndRemainder(HUNDRED);
      intermediate = result[0];
      decomposed.add(result[1].intValue());

      while (intermediate.compareTo(BigInteger.ZERO) > 0) {
         result = intermediate.divideAndRemainder(THOUSAND);
         intermediate = result[0];
         decomposed.add(result[1].intValue());
      }

      /*
       * Se o valor for apenas em centavos, adicionar zero para a casa dos reais
       * inteiros
       */
      if (decomposed.size() == 1) {
         decomposed.add(0);
      }

      return decomposed;
   }

   private void preencherGrandezasPlural() {
      grandezasPlural.put(3, "mil");
      grandezasPlural.put(6, "milhoes");
      grandezasPlural.put(9, "bilhoes");
      grandezasPlural.put(12, "trilhoes");
      grandezasPlural.put(15, "quatrilhoes");
      grandezasPlural.put(18, "quintilhoes");
      grandezasPlural.put(21, "sextilhoes");
      grandezasPlural.put(24, "setilhoes");
   }

   private void preencherGrandezasSingular() {
      grandezasSingular.put(3, "mil");
      grandezasSingular.put(6, "milhao");
      grandezasSingular.put(9, "bilhao");
      grandezasSingular.put(12, "trilhao");
      grandezasSingular.put(15, "quatrilhao");
      grandezasSingular.put(18, "quintilhao");
      grandezasSingular.put(21, "sextilhao");
      grandezasSingular.put(24, "setilhao");
   }

   private void preencherNomes() {
      nomes.put(1, "um");
      nomes.put(2, "dois");
      nomes.put(3, "tres");
      nomes.put(4, "quatro");
      nomes.put(5, "cinco");
      nomes.put(6, "seis");
      nomes.put(7, "sete");
      nomes.put(8, "oito");
      nomes.put(9, "nove");
      nomes.put(10, "dez");
      nomes.put(11, "onze");
      nomes.put(12, "doze");
      nomes.put(13, "treze");
      nomes.put(14, "quatorze");
      nomes.put(15, "quinze");
      nomes.put(16, "dezesseis");
      nomes.put(17, "dezessete");
      nomes.put(18, "dezoito");
      nomes.put(19, "dezenove");
      nomes.put(20, "vinte");
      nomes.put(30, "trinta");
      nomes.put(40, "quarenta");
      nomes.put(50, "cinquenta");
      nomes.put(60, "sessenta");
      nomes.put(70, "setenta");
      nomes.put(80, "oitenta");
      nomes.put(90, "noventa");
      nomes.put(200, "duzentos");
      nomes.put(300, "trezentos");
      nomes.put(400, "quatrocentos");
      nomes.put(500, "quinhentos");
      nomes.put(600, "seiscentos");
      nomes.put(700, "setecentos");
      nomes.put(800, "oitocentos");
      nomes.put(900, "novecentos");
   }
}