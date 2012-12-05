package com.tobeface.modules.lang;

/**
 * 
 * @author loudyn
 * 
 */
public interface Each<Which> {

	/**
	 * 
	 * @param which
	 */
	public void invoke(int index, Which which);
}
