package com.tobeface.modules.table;

import java.util.concurrent.ConcurrentHashMap;

import com.tobeface.modules.lang.Ghost;
import com.tobeface.modules.table.annotation.TableValueConverter;
import com.tobeface.modules.table.annotation.TableValueConverters;

/**
 * 
 * @author loudyn
 * 
 */
final class TableValues {

	private static final ConcurrentHashMap<Class<?>, Object> instanceCache = new ConcurrentHashMap<Class<?>, Object>();

	/**
	 * 
	 * @param converters
	 * @param value
	 * @return
	 */
	static Object downstreamConvert(TableValueConverters converters, Object value) {

		if (null == converters || converters.downstream().length == 0) {
			return value;
		}

		Object result = value;
		for (TableValueConverter converter : converters.downstream()) {
			result = convert(converter, result);
		}

		return result;
	}

	private static Object convert(TableValueConverter converter, Object value) {
		Ghost<?> ghost = Ghost.me(converter.type());
		Object converterInstance = instanceCache.get(converter.type());
		if (null == converterInstance) {
			converterInstance = ghost.born();
			instanceCache.putIfAbsent(converter.type(), converterInstance);
		}

		return ghost.invoke(converterInstance, converter.method(), value);
	}

	/**
	 * 
	 * @param converters
	 * @param value
	 * @return
	 */
	static Object upstreamConvert(TableValueConverters converters, Object value) {

		if (null == converters || converters.upstream().length == 0) {
			return value;
		}

		Object result = value;
		for (TableValueConverter converter : converters.upstream()) {
			result = convert(converter, result);
		}

		return result;
	}

	private TableValues() {}
}
