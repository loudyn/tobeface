package com.tobeface.tgenius.application;

import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboBombMessageService {

	/**
	 * 
	 * @param names
	 * @param letter
	 */
	void publishLetterByNames(String[] names, WeiboLetter letter);

	/**
	 * 
	 * @param talking
	 * @param letter
	 */
	void publishLetterByTalkabout(WeiboTalking talking, WeiboLetter letter);

	/**
	 * 
	 * @param mention
	 */
	void publishMentionByRelay(WeiboMention mention);

	/**
	 * 
	 * @param talking
	 * @param mention
	 */
	void publishMetionByTalkabout(WeiboTalking talking, WeiboMention mention);
}
