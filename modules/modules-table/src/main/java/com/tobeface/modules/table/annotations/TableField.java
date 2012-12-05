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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableField {

	/**
	 * 
	 * @return
	 */
	public String columnName() default "A";

	/**
	 * 
	 * @return
	 */
	public int columnIndex() default 0;
}
