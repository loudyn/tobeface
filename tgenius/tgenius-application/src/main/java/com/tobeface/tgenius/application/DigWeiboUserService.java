package com.tobeface.tgenius.application;


/**
 * 
 * @author loudyn
 * 
 */
public interface DigWeiboUserService {

	/**
	 * 
	 * @param location
	 */
	void digByKeyword(String keyword);

	/**
	 * 
	 * @param tags
	 */
	void digByTags(String tags);
}
