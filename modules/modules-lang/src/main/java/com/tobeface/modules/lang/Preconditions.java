package com.tobeface.modules.lang;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author loudyn
 * 
 */
public final class Preconditions {

	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}

	public static void isTrue(boolean expression, String message) {
		isTrue(expression, new IllegalArgumentException(message));
	}

	public static void isTrue(boolean expression, RuntimeException throwIfAssertFail) {
		if (!expression) {
			throw throwIfAssertFail;
		}
	}

	public static <T> T isNull(T object) {
		return isNull(object, "[Assertion failed] - the object argument must be null");
	}

	public static <T> T isNull(T object, String message) {
		return isNull(object, new IllegalArgumentException(message));
	}

	public static <T> T isNull(T object, RuntimeException throwIfAssertFail) {
		if (object != null) {
			throw throwIfAssertFail;
		}

		return object;
	}

	public static <T> T notNull(T object) {
		return notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}

	public static <T> T notNull(T object, String message) {
		return notNull(object, new IllegalArgumentException(message));
	}

	public static <T> T notNull(T object, RuntimeException throwIfAssertFail) {
		if (object == null) {
			throw throwIfAssertFail;
		}

		return object;
	}

	public static String hasLength(String text) {
		return hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
	}

	public static String hasLength(String text, String message) {
		return hasLength(text, new IllegalArgumentException(message));
	}

	public static String hasLength(String text, RuntimeException throwIfAssertFail) {
		if (!isStringHasLength(text)) {
			throw throwIfAssertFail;
		}

		return text;
	}

	private static boolean isStringHasLength(String text) {
		return null != text && text.length() > 0;
	}

	public static String hasText(String text) {
		return hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	public static String hasText(String text, String message) {
		return hasText(text, new IllegalArgumentException(message));
	}

	public static String hasText(String text, RuntimeException throwIfAssertFail) {
		if (!isStringHasText(text)) {
			throw throwIfAssertFail;
		}

		return text;
	}

	private static boolean isStringHasText(String text) {
		if (!isStringHasLength(text)) {
			return false;
		}

		int strLen = text.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(text.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static String doesNotContain(String textToSearch, String substring) {
		return doesNotContain(
								textToSearch, 
								substring, 
								"[Assertion failed] - this String argument must not contain the substring [" + substring + "]"
						);
	}

	public static String doesNotContain(String textToSearch, String substring, String message) {
		return doesNotContain(textToSearch, substring, new IllegalArgumentException(message));
	}

	public static String doesNotContain(String textToSearch, String substring, RuntimeException throwIfAssertFail) {

		if (isStringHasLength(textToSearch) && isStringHasLength(substring) && textToSearch.indexOf(substring) != -1) {
			throw throwIfAssertFail;
		}
		
		return textToSearch;
	}

	public static Object[] notEmpty(Object[] array) {
		return notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	public static Object[] notEmpty(Object[] array, String message) {
		return notEmpty(array, new IllegalArgumentException(message));
	}

	public static Object[] notEmpty(Object[] array, RuntimeException throwIfAssertFail) {

		if (isEmptyArray(array)) {
			throw throwIfAssertFail;
		}
		
		return array;
	}

	private static boolean isEmptyArray(Object[] array) {
		return null == array || array.length <= 0;
	}

	public static Object[] notNullElements(Object[] array) {
		return notNullElements(array, "[Assertion failed] - this array must not contain any null elements");
	}

	public static Object[] notNullElements(Object[] array, String message) {
		return notNullElements(array, new IllegalArgumentException(message));
	}

	public static Object[] notNullElements(Object[] array, RuntimeException throwIfAssertFail) {
		if (null == array) {
			throw throwIfAssertFail;
		}

		for (Object ele : array) {
			if (null == ele) {
				throw throwIfAssertFail;
			}
		}
		
		return array;
	}

	public static Collection<?> notEmpty(Collection<?> collection) {
		return notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}
	
	public static Collection<?> notEmpty(Collection<?> collection, String message) {
		return notEmpty(collection, new IllegalArgumentException(message));
	}

	public static Collection<?> notEmpty(Collection<?> collection, RuntimeException throwIfAssertFail) {
		if (isEmptyCollection(collection)) {
			throw throwIfAssertFail;
		}
		
		return collection;
	}

	private static boolean isEmptyCollection(Collection<?> collection) {
		return null == collection || collection.isEmpty();
	}
	
	public static Map<?, ?> notEmpty(Map<?, ?> map) {
		return notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}

	public static Map<?, ?> notEmpty(Map<?, ?> map, String message) {
		return notEmpty(map, new IllegalArgumentException(message));
	}
	
	public static Map<?, ?> notEmpty(Map<?, ?> map, RuntimeException throwIfAssertFail) {
		if (isEmptyMap(map)) {
			throw throwIfAssertFail;
		}
		
		return map;
	}

	private static boolean isEmptyMap(Map<?, ?> map) {
		return null == map || map.isEmpty();
	}

	public static Object isInstanceOf(Class<?> type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(
												message + 
												"Object of class [" + 
												(obj != null ? obj.getClass().getName() : "null") + 
												"] must be an instance of " + 
												type
											);
		}
		
		return obj;
	}

	public static Object isInstanceOf(Class<?> clazz, Object obj) {
		return isInstanceOf(clazz, obj, "");
	}

	public static Object isInstanceOf(Class<?> type, Object obj, RuntimeException throwIfAssertFail) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw throwIfAssertFail;
		}
		
		return obj;
	}

	public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
		}
	}

	public static void isAssignable(Class<?> superType, Class<?> subType) {
		isAssignable(superType, subType, "");
	}
	
	private Preconditions() {}
}
