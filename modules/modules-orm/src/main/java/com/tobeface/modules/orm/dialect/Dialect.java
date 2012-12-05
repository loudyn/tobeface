package com.tobeface.modules.orm.dialect;

/**
 * 
 * @author loudyn
 *
 */
public interface Dialect {

	/**
	 * 
	 * @param sql
	 * @param offset
	 * @param offsetSize
	 * @return
	 */
	String getLimitString(String sql, int offset, int offsetSize);
}
