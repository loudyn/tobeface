package com.tobeface.modules.table.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author loudyn
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableValueConverter {
	
	/**
	 * 
	 * @return
	 */
	public Class<?> type();

	/**
	 * 
	 * @return
	 */
	public String method();
}
