package com.tobeface.tgenius.application;

import java.util.Iterator;
import java.util.List;

import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboApiService {

	/**
	 * 
	 * @param appKeys
	 * @param talking
	 */
	Iterator<WeiboUser> findUserByTalking(List<WeiboAppKey> appKeys, WeiboTalking talking);

	/**
	 * 
	 * @param appKeys
	 * @param keyword
	 */
	Iterator<WeiboUser> findUserByKeyword(List<WeiboAppKey> appKeys, String keyword);

	/**
	 * 
	 * @param appKeys
	 * @param tags
	 */
	Iterator<WeiboUser> findUserByTags(List<WeiboAppKey> appKeys, String tags);

	/**
	 * 
	 * @param appKeys
	 * @param letter
	 */
	void send(List<WeiboAppKey> appKeys, WeiboLetter letter);

	/**
	 * 
	 * @param appKeys
	 * @param mention
	 */
	void send(List<WeiboAppKey> appKeys, WeiboMention mention);

}
