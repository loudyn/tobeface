package com.tobeface.tgenius.service;


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
