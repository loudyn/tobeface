package com.tobeface.modules.table;

import java.util.List;

/**
 * 
 * @author loudyn
 * 
 */
public interface Table {

	/**
	 * 
	 * @param file
	 * @param objects
	 */
	<T> void insert(List<T> objects);

	/**
	 * 
	 * @return
	 */
	<T> List<T> select(Class<T> clazz);
}
