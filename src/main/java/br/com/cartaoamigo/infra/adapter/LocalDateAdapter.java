package br.com.cartaoamigo.infra.adapter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<Long, LocalDate> {

	@Override
	public LocalDate unmarshal(Long epoch) throws Exception {
		Instant instant = Instant.ofEpochMilli(epoch);
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public Long marshal(LocalDate localDateTime) throws Exception {
		return localDateTime.atStartOfDay((ZoneOffset.systemDefault())).toInstant().toEpochMilli();
	}

}
