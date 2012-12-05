package com.tobeface.tgenius.infrastructure.wapi;

import java.util.Iterator;

import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.WeiboUser;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboApiService {

	/**
	 * 
	 * @param appKeys
	 * @param which
	 * @param letter
	 */
	void sendLetter(WeiboAppKeys appKeys, String which, WeiboLetter letter);

	/**
	 * 
	 * @param appKeys
	 * @param mention
	 */
	void sendMentionByRelay(WeiboAppKeys appKeys, WeiboMention mention);

	/**
	 * 
	 * @param appKeys
	 * @param talking
	 * @param mention 
	 */
	void sendMentionByTalking(WeiboAppKeys appKeys, WeiboTalking talking, WeiboMention mention);

	/**
	 * 
	 * @param appKeys
	 * @param talking
	 * @return
	 */
	Iterator<String> findWhoTalkabout(WeiboAppKeys appKeys, WeiboTalking talking);

	/**
	 * 
	 * @param appKeys
	 * @param tags
	 * @return
	 */
	Iterator<WeiboUser> findWhoTagLikes(WeiboAppKeys appKeys, String tags);

	/**
	 * 
	 * @param appKeys
	 * @param keyword
	 * @return
	 */
	Iterator<WeiboUser> findWhoKeywordLikes(WeiboAppKeys appKeys, String keyword);

}
