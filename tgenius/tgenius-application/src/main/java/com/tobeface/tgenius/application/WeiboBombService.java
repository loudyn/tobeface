package com.tobeface.tgenius.application;

import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboBombService {

	/**
	 * 
	 * @param letter
	 */
	void fire(WeiboLetter letter);
	
	/**
	 * 
	 * @param mention
	 */
	void fire(WeiboMention mention);
}
