package br.com.cartaoamigo.infra.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.TypeResolver;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

/**
 * type usado para mapear enums nas entidades utilizando hibernate <br>
 * <br>
 * exemplo:
 *
 * <pre>
 * &#64;Column(name = "idTipoEvento", nullable = false)
 *     &#64;Type(type = "br.mp.mpdft.infra.dao.GenericEnumUserType", parameters = {
 *     &#64;Parameter(name = "enumClass", value = "br.mp.mpdft.neosispro.entity.Evento"),
 *     &#64;Parameter(name = "keyName", value = "codigo")
 * })
 * </pre>
 *
 * @param enumClass
 *            nome completo do enum <br>
 *            <br>
 * @param keyName
 *            nome do atributo do enum que representa o valor da coluna no enum (default <strong>id</strong>)
 */
@SuppressWarnings("rawtypes")
public class GenericEnumUserType implements UserType, ParameterizedType {

	private Class<? extends Enum> enumClass;
	private Class<?> identifierType;
	private Method identifierMethod;
	private static final String defaultKeyName = "id";
	private AbstractSingleColumnStandardBasicType type;
	private int[] sqlTypes;

	@Override
	public void setParameterValues(Properties parameters) {
		String enumClassName = parameters.getProperty("enumClass");
		try {
			enumClass = Class.forName(enumClassName).asSubclass(Enum.class);
		} catch (ClassNotFoundException exception) {
			throw new HibernateException("Enum class not found", exception);
		}

		String keyName = parameters.getProperty("keyName", defaultKeyName);

		try {
			identifierMethod = enumClass.getMethod(getKeyMethodName(keyName), new Class[0]);
			identifierType = identifierMethod.getReturnType();
		} catch (Exception exception) {
			throw new HibernateException("Failed to optain identifier method", exception);
		}

		TypeResolver tr = new TypeResolver();
		type = (AbstractSingleColumnStandardBasicType) tr.basic(identifierType.getName());
		if (type == null) {
			throw new HibernateException("Unsupported identifier type " + identifierType.getName());
		}
		sqlTypes = new int[] { type.sqlType() };
	}

	private String getKeyMethodName(String keyName) {
		final StringBuilder keyMethodName = new StringBuilder("get");
		keyMethodName.append(keyName.substring(0, 1).toUpperCase());
		keyMethodName.append(keyName.substring(1));

		return keyMethodName.toString();
	}

	@Override
	public Class returnedClass() {
		return enumClass;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		Object identifier = type.get(rs, names[0], session);
		if (identifier == null) {
			return null;
		}
		try {
			final Method values = enumClass.getMethod("values", new Class[] {});
			final Enum<?>[] enums = (Enum[]) values.invoke(null, new Object[] {});
			for (Enum<?> enum1 : enums) {
				final Object enumValue = identifierMethod.invoke(enum1, new Object[] {});
				if (identifier.equals(enumValue)) {
					return enum1;
				}
			}
			return null;
		} catch (Exception exception) {
			throw new HibernateException("Exception while invoking valueOfMethod of enumeration class: ", exception);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {//public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		try {
			Object identifier = value != null ? identifierMethod.invoke(value, new Object[0]) : null;
			st.setObject(index, identifier);
		} catch (Exception exception) {
			throw new HibernateException("Exception while invoking identifierMethod of enumeration class: ", exception);
		}
	}

	@Override
	public int[] sqlTypes() {
		return sqlTypes;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}


}
