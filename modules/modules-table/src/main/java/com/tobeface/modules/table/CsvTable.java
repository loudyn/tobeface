package com.tobeface.modules.table;

import java.io.File;

/**
 * 
 * @author loudyn
 * 
 */
final class CsvTable extends TextTable {

	CsvTable(File workspace) {
		super(workspace, ",");
	}
}
