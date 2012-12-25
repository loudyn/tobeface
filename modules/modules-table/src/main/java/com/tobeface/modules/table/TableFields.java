package com.tobeface.modules.table;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

import com.tobeface.modules.lang.Ghost;
import com.tobeface.modules.table.annotation.TableField;

/**
 * 
 * @author loudyn
 *
 */
final class TableFields {

	private static ConcurrentHashMap<Class<?>, Field[]> tableFieldsCache = new ConcurrentHashMap<Class<?>, Field[]>();

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Field[] tableFields(Object obj) {
		Class<?> objClazz = obj.getClass();
		if (tableFieldsCache.containsKey(objClazz)) {
			return tableFieldsCache.get(objClazz);
		}

		Ghost<?> ghost = Ghost.me(objClazz);
		Field[] result = ghost.annotationFields(TableField.class);
		tableFieldsCache.putIfAbsent(objClazz, result);
		return result;
	}

	private TableFields() {}
}
