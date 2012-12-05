package com.tobeface.modules.table;

import java.io.File;

/**
 * 
 * @author loudyn
 * 
 */
public final class Tables {

	/**
	 * 
	 * @return
	 */
	public static Table newCsv(File workspace) {
		return new CsvTable(workspace);
	}

	/**
	 * 
	 * @param template
	 * @param conf
	 * @return
	 */
	public static Table newXls(File workspace, File template) {
		return new XlsTable(workspace, template);
	}

	/**
	 * 
	 * @param template
	 * @param conf
	 * @return
	 */
	public static Table newXls(File workspace, File template, File conf) {
		return new XlsTable(workspace, template, conf);
	}

	/**
	 * 
	 * @param splitter
	 * @return
	 */
	public static Table newText(File workspace, String splitter) {
		return new TextTable(workspace, splitter);
	}

	private Tables() {}
}
