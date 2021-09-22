/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cartaoamigo.infra.util;

public class CPF_CNPJ_Util {
   
   private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
   private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

   private static int calcularDigito(String str, int[] peso) {
      int soma = 0;
      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
         digito = Integer.parseInt(str.substring(indice,indice+1));
         soma += digito*peso[peso.length-str.length()+indice];
      }
      soma = 11 - soma % 11;
      return soma > 9 ? 0 : soma;
   }
   
   /**
    * Valida o numero do CPF
    * @param CPF
    * @return true / false
    */
   public static boolean isCPF(String CPF) {
      if (CPF == null || CPF.isEmpty() 
              || CPF.equals("00000000000") || CPF.equals("11111111111")
              || CPF.equals("22222222222") || CPF.equals("33333333333")
              || CPF.equals("44444444444") || CPF.equals("55555555555")
              || CPF.equals("66666666666") || CPF.equals("77777777777")
              || CPF.equals("88888888888") || CPF.equals("99999999999")
              || (CPF.length() != 11)) {
         return false;
      }
      Integer digito1 = calcularDigito(CPF.substring(0,9), pesoCPF);
      Integer digito2 = calcularDigito(CPF.substring(0,9).concat(digito1.toString()), pesoCPF);
      return CPF.equals(CPF.substring(0,9).concat(digito1.toString()).concat(digito2.toString()));
   }

   /**
    * Valida o numero do CNPJ
    * @param CNPJ
    * @return true / false
    */
   public static boolean isCNPJ(String CNPJ) {
      if (CNPJ == null || CNPJ.isEmpty() 
              || CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
              || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
              || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
              || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
              || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
              || (CNPJ.length() != 14)) {
         return false;
      }
      
      Integer digito1 = calcularDigito(CNPJ.substring(0,12), pesoCNPJ);
      Integer digito2 = calcularDigito(CNPJ.substring(0,12).concat(digito1.toString()), pesoCNPJ);
      return CNPJ.equals(CNPJ.substring(0,12).concat(digito1.toString()).concat(digito2.toString()));
   }
   
   public static String mascaraCPF(String cpf) {
      if ((cpf != null) && (cpf.length() == 11)) {
         return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
      }

      return cpf;
   }

   public static String mascaraCNPJ(String cnpj) {
      if ((cnpj != null) && (cnpj.length() == 14)) {
         return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
      }
      return cnpj;
   }
   
}
