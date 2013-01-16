package com.tobeface.modules.lang;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author loudyn
 * 
 */
public final class Patterns {

	private static final List<Character> QUOTES = Arrays.asList(new Character[] {
																					'+', '*', '{', '}',
																					'[', ']', '(', ')',
																					'|', '\\', '$', '^',
																					'?'
																				}
															);

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static boolean hasQuotes(String text) {
		if (Strings.isBlank(text)) {
			return false;
		}

		int length = text.length();
		for (int i = 0; i < length; i++) {
			if (QUOTES.contains(text.charAt(i))) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String noQuotes(String text) {
		if (Strings.isBlank(text)) {
			return text;
		}

		StringBuilder result = new StringBuilder();
		char[] chars = text.toCharArray();
		int length = chars.length;

		for (int i = 0; i < length; i++) {
			char c = chars[i];
			if (QUOTES.contains(c)) {
				continue;
			}

			result.append(c);
		}

		return result.toString();
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public static String safeQuotes(String text) {
		if (Strings.isBlank(text)) {
			return text;
		}

		StringBuilder result = new StringBuilder();
		char[] chars = text.toCharArray();
		int length = chars.length;

		for (int i = 0; i < length; i++) {
			char c = chars[i];
			if (QUOTES.contains(c)) {
				result.append("\\");
			}

			result.append(c);
		}

		return result.toString();
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String contains(String string) {
		return String.format(".*?%s.*?", safeQuotes(string));
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String startWith(String string) {
		return String.format("^%s", safeQuotes(string));
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String endWith(String string) {
		return String.format("%s$", safeQuotes(string));
	}

	/**
	 * 
	 * @return
	 */
	public static String digits() {
		return "[0-9]+";
	}

	/**
	 * 
	 * @return
	 */
	public static String letters() {
		return "[a-zA-Z]+";
	}

	/**
	 * 
	 * @return
	 */
	public static String digitsOrLetters() {
		return "[a-zA-Z0-9]+";
	}

	/**
	 * 
	 * @return
	 */
	public static String email() {
		return "[a-zA-z0-9\\-_]+@[a-zA-Z0-9]+\\.[a-zA-z]{2,3}";
	}

	private Patterns() {}
}
