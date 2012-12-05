package com.tobeface.modules.lang;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author loudyn
 * 
 */
public final class IOs {

	/**
	 * 
	 * @param out
	 * @param bytes
	 */
	public static void write(OutputStream out, byte[] bytes) {
		try {
			
			out.write(bytes);
			out.flush();
		} catch (IOException e) {
			throw Lang.uncheck(e);
		}
	}

	/**
	 * 
	 * @param out
	 * @param content
	 * @param encoding
	 */
	public static void write(OutputStream out, String content, String encoding) {
		BufferedOutputStream bos = null;

		try {

			bos = new BufferedOutputStream(out);
			bos.write(content.getBytes(encoding));
			bos.flush();
			IOs.write(out, content.getBytes(encoding));
		} catch (Exception e) {
			throw Lang.uncheck(e);
		} finally {
			IOs.freeQuietly(bos);
		}

	}

	/**
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void piping(InputStream in, OutputStream out) throws IOException {
		piping(in, out, new byte[4 * 1024]);
	}

	/**
	 * @param in
	 * @param out
	 * @param buf
	 * @throws IOException
	 */
	public static void piping(InputStream in, OutputStream out, byte[] buf) throws IOException {
		int bytesRead = 0;

		while ((bytesRead = in.read(buf)) != -1) {
			out.write(buf, 0, bytesRead);
			out.flush();
		}
	}

	/**
	 * @param closeables
	 * @throws IOException
	 */
	public static void free(Closeable... closeables) throws IOException {

		List<Throwable> throwables = new LinkedList<Throwable>();
		for (Closeable closeable : closeables) {

			try {

				if (null != closeable) {
					closeable.close();
				}
			} catch (IOException e) {
				throwables.add(e);
			}
		}

		if (!throwables.isEmpty()) {
			throw new IOException(Lang.comboThrow(throwables));
		}
	}

	/**
	 * @param closeables
	 */
	public static void freeQuietly(Closeable... closeables) {
		try {

			free(closeables);
		} catch (Exception ingore) {}
	}

	private IOs() {}
}
