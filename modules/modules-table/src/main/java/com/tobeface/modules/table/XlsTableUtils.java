package com.tobeface.modules.table;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.modules.lang.Files;
import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.lang.Strings;
import com.tobeface.modules.table.XlsTable.XlsConf;

/**
 * 
 * @author loudyn
 * 
 */
final class XlsTableUtils {

	/**
	 * 
	 * @param template
	 * @return
	 */
	static File copyTemplate(File template) {
		try {

			File temp = File.createTempFile("xls-table", Files.suffix(template.getName()));
			Files.copy(template, temp);
			return temp;
		} catch (Exception e) {
			throw Lang.uncheck(e);
		}
	}

	/**
	 * 
	 * @param conf
	 * @return
	 */
	static XlsConf loadConf(File conf) {
		if (null == conf) {
			return new XlsConf();
		}

		String confString = Files.read(conf, "UTF-8");
		return JsonHelper.newfor(confString, XlsConf.class);
	}

	/**
	 * 
	 * @param conf
	 * @param wb
	 * @return
	 */
	static HSSFSheet loadSheet(XlsConf conf, HSSFWorkbook wb) {
		return wb.getSheetAt(conf.sheet);
	}

	/**
	 * 
	 * @param conf
	 * @param sheet
	 * @return
	 */
	static Map<String, Integer> loadSheetHeader(XlsConf conf, HSSFSheet sheet) {

		HSSFRow header = sheet.getRow(conf.headerRow);
		Map<String, Integer> headerMapping = new HashMap<String, Integer>();
		for (int i = header.getFirstCellNum(); i < header.getLastCellNum(); i++) {
			HSSFCell cell = header.getCell(i);
			String cellValue = cell.getStringCellValue();
			if (Strings.isBlank(cellValue)) {
				continue;
			}

			headerMapping.put(cellValue, i);
		}

		return headerMapping;
	}

	/**
	 * 
	 * @param columnName
	 * @param columnIndex
	 * @param headers
	 * @return
	 */
	static int selectColumnIndex(String columnName, int columnIndex, Map<String, Integer> headers) {
		if (Strings.isBlank(columnName)) {
			return columnIndex;
		}

		if (!headers.containsKey(columnName)) {
			throw new IllegalStateException("Can't mapping the column index, please check the template xls file and TableField");
		}
		return headers.get(columnName);
	}

	private XlsTableUtils() {}

}
