package com.tobeface.modules.lang;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author loudyn
 * 
 */
public final class Stupids {

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static boolean isCareless(Class<?> clazz) {
		return isCareless(Files.asPath(clazz.getName() + ".class"));
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isCareless(String path) {

		try {

			Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
			if (null == urls) {
				return false;
			}

			Set<String> files = new HashSet<String>();
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				String file = url.getFile();
				files.add(file);
			}

			return files.size() > 1;
		} catch (IOException e) {
			throw Lang.uncheck(e);
		}
	}

	private Stupids() {}
}
