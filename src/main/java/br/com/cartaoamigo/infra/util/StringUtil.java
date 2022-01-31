/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cartaoamigo.infra.util;

import java.text.Normalizer;
import java.util.stream.Stream;

/**
 *
 * @author fernando.colombo
 */
public class StringUtil {

	public static boolean temNumero(String valor) {
		String[] nomes = valor.split(" ");
		return Stream.of(nomes).filter(nome -> NumeroUtil.isNumero(nome)).findAny().isPresent();	
	}
	
	
   /**
    * Preenche com Brancos a Diteira
    *
    * @param texto
    * @param numero de Brancos
    * @return texto com brancos a direita
    */
   public static String espacoDireita(String txt, int numEspacos) {
      if (txt == null) {
         txt = " ";
      }
      if (numEspacos > txt.length()) {
         numEspacos = numEspacos - txt.length();
         StringBuilder num = (new StringBuilder(txt)).append("%").append(numEspacos).append("s");
         return String.format(num.toString(), " ");
      } else {
         return txt.substring(0, numEspacos);
      }
   }

   /**
    * Preenche com Brancos a Esquerda
    *
    * @param texto
    * @param numero de Brancos
    * @return texto com brancos a direita
    */
   public static String espacoEsquerda(String txt, int numEspacos) {
      if (txt == null) {
         txt = " ";
      }
      if (numEspacos > txt.length()) {
         numEspacos = numEspacos - txt.length();
         StringBuilder num = (new StringBuilder("%")).append(numEspacos).append("s").append(txt);
         return String.format(num.toString(), " ");
      } else {
         return txt.substring(0, numEspacos);
      }
   }
   
   public static String mascaraCPF(String cpf) {
      if ((cpf != null) && (cpf.length() == 11)) {
         return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
      }

      return cpf;
   }

   public static String mascaraCNPJ(String cnpj) {
      if ((cnpj != null) && (cnpj.length() == 14)) {
         return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8,12)+"-"+cnpj.substring(12);
         //vr.substr(0, 2) + '.' + vr.substr(2, 3) + '.' + vr.substr(6, 3) + '/' + vr.substr(9, 4) + '-' + vr.substr(13, 2);");
      }

      return cnpj;
   }
   
   /**
    * @author wilson.souza
    * Acrescenta caracteres a esquerda
    * @param valueToPad
    * @param filler
    * @param size
    * @return
    */
   public static String lpad(String valueToPad, String filler, int size) {
      while (valueToPad.length() < size) {
         valueToPad = filler + valueToPad;
      }
      return valueToPad;
   }

   
   /**
    * @author wilson.souza
    * Acrescenta caracteres a direita
    * @param valueToPad
    * @param filler
    * @param size
    * @return
    */
   public static String rpad(String valueToPad, String filler, int size) {
      while (valueToPad.length() < size) {
         valueToPad = valueToPad + filler;
      }
      return valueToPad;
   }
   
   
   /**
    * @author wilson.souza
    * Retira os acentos e caracteres especiais.
    *
    * @param string
    * @return string sem acentos
    */
   public static String removerCaractereEspecial(String str) {
      return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
   }
   
   /**
    * @author wilson.souza
    * Verifica se o parâmetro está vazio.
    * @param valor
    * @return
    */
   public static boolean isVazio(String valor) {
      if (valor == null || valor.equals("")) {
         return true;
      }
      return false;
   }
   
   /**
    * Retorna a string com a primeira letra em maiuscula.
    * As preposições entres as palavras ficarão em minuscula.
    * 
    * @author wilson.souza
    * @param valor
    * @return String
    */
   public static String initCap(String valor){
      if(valor == null || valor.equals(""))
         return valor;
      
      valor = valor.toLowerCase();
      
      //Garante que a primeira letra estará em maiuscúla...
      valor = valor.substring(0, 1).toUpperCase().concat(valor.substring(1));
      
      String[] args = valor.split(" ");
      
      StringBuilder sb = new StringBuilder();
      String aux = "";
      for (String v : args) {
         if(v.length() > 2){
            aux = String.valueOf(v.charAt(0)).toUpperCase();
            aux = aux.concat(v.substring(1));
         }else{
            aux = v;
         }
         sb.append(aux + " ");
      }
      
      return sb.toString();
   }
   
   
   	/**
   	 * Converte a string informada em um array de byte.
   	 * Ideal para salvar textos longos em campos do tipo BLOB do banco de dados. 
   	 * 
   	 * @param texto
   	 * @return
   	 */
	public static byte[] stringToByteArray(String texto) {
		if(texto == null) return new byte[0];
		byte[] out = new byte[texto.length()];
		for (int i = 0; i < texto.length(); i++) {
			out[i] = (byte) texto.charAt(i);
		}

		return out;
	}

   	/**
   	 * Converte o array de bytes informado em um String.
   	 * Ideal para recuperar textos longos salvos no banco de dados do tipo BLOB. 
   	 * 
   	 * @param array
   	 * @return
   	 */
	public static String byteArrayToString(byte[] array) {
		if(array == null) return "";
		char[] cbuf = new char[array.length];
		for (int i = 0; i < array.length; i++) {
			cbuf[i] = (char) array[i];
		}
		String s = new String(cbuf);

		return s;
	}	
   
	

}
