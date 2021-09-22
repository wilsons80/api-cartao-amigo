package br.com.cartaoamigo.infra.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class Java8DateUtil {

	public static String getLocalDateTimeFormater(LocalDateTime ldt) {
		if(ldt == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mi:ss");
		return formatter.format(ldt);
	}

	public static String getLocalDateFormater(LocalDate ld) {
		if(ld == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(ld);
	}
	
	public static Date getDate(LocalDateTime ldt) {
		if(ldt == null) return null;
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static LocalDateTime getLocalDateTime(Date ts) {
		Instant instant = Instant.ofEpochMilli(ts.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
	
	public static LocalDateTime getLocalDateTimeZeroTiming(Date ts) {
		return getLocalDate(ts).atStartOfDay();
	}
	
	public static LocalDateTime getLocalDateTimeLastTiming(Date ts) {
		LocalTime specificTime = LocalTime.of(23, 59, 59, 999999999);
		return LocalDateTime.of(getLocalDateTime(ts).toLocalDate(), specificTime);
	}
	
	public static LocalDateTime getLocalDateTimeLastSecond(Date ts) {
		LocalTime specificTime = LocalTime.of(23, 59, 59);
		return LocalDateTime.of(getLocalDateTime(ts).toLocalDate(), specificTime);
	}
	
	public static LocalDate getLocalDate(Date ts) {
		return ts.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static LocalDateTime parseLocalDateTime(String str){
		if(str == null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt;
		try {
			dt = sdf.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return getLocalDateTime(dt);
	}
	
	public static LocalDate parseLocalDate(String str){
		if(str == null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt;
		try {
			dt = sdf.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return getLocalDate(dt);
	}

	public static boolean isVigente(LocalDate inicio, LocalDate fim) {
		LocalDate hoje = LocalDate.now();

		if (hoje.equals(inicio)) {return true;}
		if (hoje.equals(fim)) {return true;}
		if (hoje.isBefore(inicio)) {return false;}
		if (Objects.isNull(fim)) {return true;}     
		if (hoje.isAfter(inicio) && hoje.isBefore(fim)) {return true;}
		
		return false;
	}

	public static boolean isVigente(LocalDate dataRefencia, LocalDate inicio, LocalDate fim) {
		if (dataRefencia.equals(inicio)) {return true;}
		if (dataRefencia.equals(fim)) {return true;}
		if (Objects.nonNull(inicio) && dataRefencia.isBefore(inicio)) {return false;}
		if (Objects.isNull(fim)) {return true;}     
		if (Objects.nonNull(inicio) && dataRefencia.isAfter(inicio) && dataRefencia.isBefore(fim)) {return true;}
		if (Objects.isNull(inicio) && Objects.nonNull(fim) && dataRefencia.isBefore(fim)) {return true;}
		
		return false;
	}
	
	private static boolean isVigenteDataFim(LocalDate dataRefencia, LocalDate inicio, LocalDate fim) {
		if (dataRefencia.equals(fim)) {return true;}
		if (Objects.isNull(fim)) {return false;} 
		if (fim.isBefore(dataRefencia)) {return true;}
		
		return false;
	}
	
	private static boolean isVigenteDataInicio(LocalDate dataRefencia, LocalDate inicio, LocalDate fim) {
		if (dataRefencia.equals(inicio)) {return true;}
		if (inicio.isAfter(dataRefencia)) {return true;}
		
		return false;
	}
	
	public static boolean entreLocalDateTime(LocalDateTime inicio, LocalDateTime fim, LocalDateTime dataPesquisaInicio, LocalDateTime dataPesquisaFim) {
		LocalDate p_dataPesqIni = Objects.nonNull(dataPesquisaInicio) ? dataPesquisaInicio.toLocalDate() : null;
		LocalDate p_dataPesqFim = Objects.nonNull(dataPesquisaFim) ? dataPesquisaFim.toLocalDate() : null;
		
		LocalDate p_dataInicio = Objects.nonNull(inicio) ? inicio.toLocalDate() : null;
		LocalDate p_dataFim    = Objects.nonNull(fim) ? fim.toLocalDate() : null;
		
		return entreDatas(p_dataInicio, p_dataFim, p_dataPesqIni, p_dataPesqFim);
	}
	
	public static boolean entreDatas(LocalDate inicio, LocalDate fim,LocalDate dataPesquisaInicio, LocalDate dataPesquisaFim) {

		if(Objects.nonNull(dataPesquisaInicio) && Objects.nonNull(dataPesquisaFim)) {
			return isVigenteDataInicio(dataPesquisaInicio, inicio, fim) && isVigenteDataFim(dataPesquisaFim, inicio, fim);
		}
		
		if(Objects.nonNull(dataPesquisaInicio)) {
			return isVigenteDataInicio(dataPesquisaInicio, inicio, fim);
		}
		
		if(Objects.nonNull(dataPesquisaFim)) {
			return isVigenteDataFim(dataPesquisaFim, inicio, fim);
		}
		
		return false;
	}
	
	public static Long horaStringToLong(String hora) {
		String teste = hora.replace(":","");
		return Long.valueOf(teste);
		
	}
	
}
