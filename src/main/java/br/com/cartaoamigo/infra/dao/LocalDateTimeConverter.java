package br.com.cartaoamigo.infra.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @deprecated <strong>TODO:</strong> utilizar dependência hibernate-java8 <br>
 *             Não foi possível usar no wildfly pois ele desconsidera o hibernate e hibernate-java8 empacotado pela aplicação.
 *
 *             <pre>
 * {@code
 * <dependency>
 *     <groupId>org.hibernate</groupId>
 *     <artifactId>hibernate-java8</artifactId>
 *     <version>hibernate.version</version>
 * </dependency>
 * }
 *             </pre>
 */
@Deprecated
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		return (attribute == null ? null : Timestamp.valueOf(attribute));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		return (dbData == null ? null : dbData.toLocalDateTime());
	}

}
