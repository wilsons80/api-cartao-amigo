package br.com.cartaoamigo.infra.adapter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<Long, LocalDateTime> {

	@Override
	public LocalDateTime unmarshal(Long epoch) throws Exception {
		Instant instant = Instant.ofEpochMilli(epoch);
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}

	@Override
	public Long marshal(LocalDateTime localDateTime) throws Exception {
		return localDateTime.atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
	}

}
