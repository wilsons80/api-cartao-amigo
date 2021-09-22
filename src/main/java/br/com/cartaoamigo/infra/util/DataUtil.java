/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cartaoamigo.infra.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtil {
   
   public static Date parseDate(String data){
      try {
         return parseDate(data, "dd/MM/yyyy");
      } catch (ParseException e) {
         return null;
      }
   }
   
   public static String formataCep(String cep){
      if(cep==null || cep.length()!=8)
         return cep;
      
      char[] c = cep.toCharArray();
      StringBuilder ret = new StringBuilder();
      ret.append(c[0]).append(c[1]).append('.').append(c[2]).append(c[3]).append(c[4]).append('-').append(c[5]).append(c[6]).append(c[7]);
      return ret.toString();
   }
   
   /**
    * Transforma string em data
    * @param data
    * @param patern (dd - dia, MM - mes, yyyy - ano 4, yy - ano 2)
    * @return data
    * @throws ParseException
    */
   public static Date parseDate(String data, String patern) throws ParseException {
      DateFormat df = new SimpleDateFormat(patern);
      if (data != null && !data.trim().equals(""))
         return df.parse(data);
      return null;
   }
   
   public static Date somaHoraData(Date data, Integer hora){
      if(data!=null){
         GregorianCalendar gc = new GregorianCalendar();
         gc.setTime(data);
         gc.add(Calendar.HOUR, hora);
         return gc.getTime();
      }
      return null;
   }
   
   public static Date adicionaMeses(Date data, int qtdMeses){
      if(data!=null){
         GregorianCalendar gc = new GregorianCalendar();
         gc.setTime(data);
         gc.add(Calendar.MONTH, qtdMeses);
         return gc.getTime();
      }
      return null;
   }
   
   public static Date adicionaDia(Date data, int qtdDias){
      if(data!=null){
         GregorianCalendar gc = new GregorianCalendar();
         gc.setTime(data);
         gc.add(Calendar.DAY_OF_MONTH, qtdDias);
         return gc.getTime();
      }
      return null;
   }
   
   public static Date adicionaDiaTiraFinalSemana(Date data, int qtdDias){
      if(data!=null){
         GregorianCalendar gc = new GregorianCalendar();
         gc.setTime(data);
         gc.add(Calendar.DAY_OF_MONTH, qtdDias);
         if(gc.get(Calendar.DAY_OF_WEEK)==7 || gc.get(Calendar.DAY_OF_WEEK)==1) 
            return adicionaDiaTiraFinalSemana(gc.getTime(), 1);
         return gc.getTime();
      }
      return null;
   }
   
   public static Date alterarDia(Date data, int dia){
      if(data!=null){
         GregorianCalendar gc = new GregorianCalendar();
         gc.setTime(data);
         int mesInicio = gc.get(Calendar.MONTH);
         gc.set(Calendar.DAY_OF_MONTH, dia);
         int mesFinal = gc.get(Calendar.MONTH);
         if(mesInicio!=mesFinal){
            data = adicionaMeses(gc.getTime(), -1);
            data = alterarDia(data, dia-1);
            return data;
         }
         return gc.getTime();
      }
      return null;
   }
   
   /***
    * Por convenção,  a data 01/01/1901 será considerada como NULA nas 
    * procedures. O formato a ser retornado é yyyy-MM-dd
    * @return 
    */
   public static Date getDataNulaPadrao(){
     return  new GregorianCalendar(1901,Calendar.JANUARY,1).getTime();
   }
   
   /**
    *  Formata a data para saida no formato "dd/MM/yyyy"
    * @param dt
    * @return
    */
   public static String formataData(Date dt){
      return formataData(dt, "dd/MM/yyyy");
   }
   
   
   public static String getAnoYY(){
	   return formataData(getHojeSemHora(), "yy");
   }
   
   public static String getAnoYYYY(){
	   return formataData(getHojeSemHora(), "yyyy");
   }

   
   /**
    *  Formata a data para saida no formato "dd/MM/yyyy"
    * @param dt
    * @return
    */
   public static String getHojeFormatado(){
      return formataData(getHojeSemHora(), "dd/MM/yyyy");
   }
   
   /**
    * Formata a data de acordo com o pattern informado<br/>
    * Pattern:<br>
    *  - G  designador era<br>
    *  - y  ano<br>
    *  - M  Mês no ano<br>
    *  - w  Semana do ano<br>
    *  - W  Semana no mês<br>
    *  - D  Dia no ano<br>
    *  - d  Dia no mês<br>
    *  - F  Dia da semana no mês<br>
    *  - E  Dia na semana<br>
    *  - a  Am / pm marcador<br>
    *  - H  Hora em dias (0-23)<br>
    *  - k  Hora no dia (24/01)<br>
    *  - K  Hora em am / pm (0-11)<br>
    *  - h  Hora em am / pm (1-12)<br>
    *  - m  Minuto em hora<br>
    *  - s  Segundo em minutos<br>
    *  - S  milisegundo<br>
    *  - z  fuso horário<br>
    *  - Z  fuso horário <br>
    * @param dt
    * @param pattern
    * @return
    */
   public static String formataData(Date dt, String pattern){
      DateFormat df = new SimpleDateFormat(pattern);
      if(dt!=null)
         return df.format(dt);
      else
         return null;
   }
   
   /**
    * Retorna a data por extenso
    * @param dt
    * @return data extenso
    */
   public static String dataExtenso(Date dt){
      DateFormat df = new SimpleDateFormat("dd - MMMMM - yyyy");
      if(dt!=null)
         return df.format(dt).replaceAll("-", "de");
      else
         return null;
   }
   
   public static Integer getAno(Date dt){
      Calendar cl = GregorianCalendar.getInstance();
      cl.setTime(dt);
      return cl.get(Calendar.YEAR);
   }
   
   public static Integer getMes(Date dt){
      Calendar cl = GregorianCalendar.getInstance();
      cl.setTime(dt);
      return cl.get(Calendar.MONTH)+1;
   }
   
   public static Integer getDia(Date dt){
      Calendar cl = GregorianCalendar.getInstance();
      cl.setTime(dt);
      return cl.get(Calendar.DAY_OF_MONTH);
   }
   
   /** 
    * Calcula a diferença de duas datas em dias 
    * <br> 
    * <b>Importante:</b> Quando realiza a diferença em dias 
    * entre duas datas, este método considera as horas restantes e as 
    * converte em fração de dias. 
    * @param dataInicial 
    * @param dataFinal 
    * @return quantidade de dias existentes entre a dataInicial e dataFinal. 
    */
   public static Integer diferencaEmDias(Date dataInicial, Date dataFinal) {
      GregorianCalendar data = new GregorianCalendar();
      data.setTime(dataInicial);
      int elapsed = 0;  
      GregorianCalendar g1 = new GregorianCalendar();
      g1.setTime(dataFinal);
      int controle = 0;
      GregorianCalendar gc1, gc2;  
      if (data.after(g1)) {  
          gc2 = (GregorianCalendar) data.clone();  
          gc1 = (GregorianCalendar) g1.clone();
          controle = -1;
      } else {  
          gc2 = (GregorianCalendar) g1.clone();  
          gc1 = (GregorianCalendar) data.clone();
          controle = 1;
      }  
      gc1.clear(Calendar.MILLISECOND);  
      gc1.clear(Calendar.SECOND);  
      gc1.clear(Calendar.MINUTE);  
      gc1.clear(Calendar.HOUR_OF_DAY);  
      gc2.clear(Calendar.MILLISECOND);  
      gc2.clear(Calendar.SECOND);  
      gc2.clear(Calendar.MINUTE);  
      gc2.clear(Calendar.HOUR_OF_DAY);  
      while (gc1.before(gc2)) {  
          gc1.add(Calendar.DATE, 1);  
          elapsed++;  
      }  
      return elapsed*controle; 
   }
      
   /** 
    * Calcula a diferença de duas datas em Meses 
    * <br> 
    * <b>Importante:</b> Quando realiza a diferença em meses 
    * entre duas datas, este método considera as horas restantes e as 
    * converte em fração de meses. 
    * @param dataInicial 
    * @param dataFinal 
    * @return quantidade de meses existentes entre a dataInicial e dataFinal. 
    */
   public static Integer diferencaEmMeses(Date dataInicial, Date dataFinal){
      dataInicial = alterarDia(dataInicial, 25);
      dataFinal = alterarDia(dataFinal, 25);
      
      GregorianCalendar maior = new GregorianCalendar();  
      maior.setTime(dataInicial);  
        
      GregorianCalendar menor = new GregorianCalendar();  
      menor.setTime(dataFinal);  
        
      GregorianCalendar dif = new GregorianCalendar();  
      dif.setTimeInMillis(maior.getTimeInMillis() - menor.getTimeInMillis()); 
      
      int meses = (maior.get(GregorianCalendar.YEAR)-menor.get(GregorianCalendar.YEAR))*12;
      meses += maior.get(GregorianCalendar.MONTH)-menor.get(GregorianCalendar.MONTH); 
      return meses;
   }
   
   public static Date getHoje(){
      return new Date(System.currentTimeMillis());
   }
   
   public static Date getHojeSemHora(){
      return parseDate(formataData(new Date(System.currentTimeMillis())));
   }
   
   
   /**
    * @author wilson.souza
    * Retorna a data no formato dd/MM/yyyy HH:mm:ss
    * @return String
    */
   public static String getData() {
      SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      String strDate = sm.format(new Date());

      return strDate;
   }
   
   /**
    * @author wilson.souza
    * Retona a data no formato yyyyMMdd_HHmmss
    * @return
    */
   public static String getDataRelatorio() {
      SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd_HHmmss");
      String strDate = sm.format(new Date());

      return strDate;
   }
   
   
}
