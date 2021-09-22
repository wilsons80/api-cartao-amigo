package br.com.cartaoamigo.infra.util;


import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NumeroUtil {
   
   public static String formataMoeda(Object v){
      if(v!=null){
         DecimalFormat df = new DecimalFormat("#,##0.00");
         return df.format(v);
      }
      return null;
   }
   
   public static String formataDoubleComDuasCasasDecimais(double valor) {
		DecimalFormat df = new DecimalFormat("#####0.00");
		return df.format(valor).replaceAll(",", ".");
   }
   
   public static String formataNumeroDecimal(Object v, int casas){
      if(v!=null){
         StringBuilder mascara = new StringBuilder("#,##0.");
         for(int i = 0; i<casas; i++){
            mascara = mascara.append("0");
         }
         DecimalFormat df = new DecimalFormat(mascara.toString());
         return df.format(v);
      }
      return null;
   }

   /**
    * Preenche com zeros a esquerda
    * @param numero
    * @param numZeros 
    * @return numero com zeros a esquerda
    */
   public static String zeroEsquerda(Integer numero, int numZeros){
      if (numero == null)
         numero = 0;
      return zeroEsquerda(numero.longValue(), numZeros);
   }
   
   /**
    * Preenche com zeros a esquerda
    * @param numero
    * @param numZeros 
    * @return numero com zeros a esquerda
    */
   public static String zeroEsquerda(Long numero, int numZeros){
      if (numero == null)
         numero = 0l;
      StringBuilder num = (new StringBuilder("%0")).append(numZeros).append("d");
      return String.format(num.toString(), numero);
   }
   
   
   
   /**
    * @author wilson.souza
    * Obtém apenas os valores numericos do parâmetro informado.
    *
    * @param valor
    * @return int
    */
   public static int extrairNumerosMatches(String valor) {
      int resultado = 0;
      String line = valor;
      String pattern = "(\\d+)";

      try {
         Pattern r = Pattern.compile(pattern);
         Matcher m = r.matcher(line);
         if (m.find()) {
            resultado = Integer.parseInt(m.group(0));
         }
      } catch (Exception e) {
         resultado = 0;
      }

      return resultado;
   }
   
   
   /**
    * @author wilson.souza
    * Valida se o par�metro informado � num�rico.
    *
    * @param valor
    * @return
    */
   public static boolean isNumero(String valor) {
      try {
         if (valor == null || valor.trim().isEmpty()) {
            return false;
         }
         Long.parseLong(valor);
         return true;
      } catch (Exception e) {
         return false;
      }
   }
   
   
   /**
    * @author wilson.souza
    * Valida se o par�metro informado � num�rico.
    *
    * @param valor
    * @return
    */
   public static boolean isDouble(String valor) {
      try {
         if (valor == null || valor.trim().isEmpty()) {
            return false;
         }
         Double.parseDouble(valor);
         return true;
      } catch (Exception e) {
         return false;
      }
   }
   
   /**
    * @author wilson.souza
    * 1 - Valor a arredondar. 
    * 2 - Quantidade de casas depois da v�rgula. 
    * 3 - Arredondar para cima ou para baixo? Para cima = 0 (ceil) Para baixo = 1 ou qualquer outro inteiro (floor)
    * 
    * @param valor
    * @param casas
    * @param ceilOrFloor
    * @return double
    */
   public double arredondar(double valor, int casas, int ceilOrFloor) {
      double arredondado = valor;
      arredondado *= (Math.pow(10, casas));
      if (ceilOrFloor == 0) {
         arredondado = Math.ceil(arredondado);
      } else {
         arredondado = Math.floor(arredondado);
      }
      arredondado /= (Math.pow(10, casas));
      return arredondado;
   }
   
 
  /**
    * @author wilson.souza
    * Retorna apenas os valores numericos do parâmetro informado. 
    * Ex: 56.567.98-86/23 retorna 56567988623
    *
    * @param valor
    * @return String
    */
   public static String somenteNumeros(String valor) {
       return valor.replaceAll("[^0-9]","");
   }
    
}