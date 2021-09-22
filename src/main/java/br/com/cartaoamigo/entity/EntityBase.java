package br.com.cartaoamigo.entity;

import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

public class EntityBase {

	public EntityBase() {
	}

	@SuppressWarnings("rawtypes")
	public static void beanPropertiesToUpperCase(Object object) {

		BeanUtilsBean beansUtilsBean = BeanUtilsBean.getInstance();

		//Return the entire set of properties for which the specified bean provides a read method
		Map mapProperties;
		try {
			mapProperties = beansUtilsBean.describe(object);

			//Set of properties of the bean
			Set properties = mapProperties.keySet();

			//bucle obver the properties
			for (Object property : properties) {
				//if the property is a String we convert tu uppercase
				if (PropertyUtils.getSimpleProperty(object, property.toString()) instanceof String) {
					String value = (String) mapProperties.get(property);
					if (!StringUtils.isEmpty(value)) {
						beansUtilsBean.setProperty(object, property.toString(), value.toUpperCase());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}