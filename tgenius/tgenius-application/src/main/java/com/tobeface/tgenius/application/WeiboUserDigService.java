package com.tobeface.tgenius.application;

import java.util.List;

import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboUserDigService {

	/**
	 * 
	 * @param appKeys
	 * @param talking
	 */
	void findUserByTalking(List<WeiboAppKey> appKeys, WeiboTalking talking);

	/**
	 * 
	 * @param appKeys
	 * @param keyword
	 */
	void findUserByKeyword(List<WeiboAppKey> appKeys, String keyword);

	/**
	 * 
	 * @param appKeys
	 * @param tags
	 */
	void findUserByTags(List<WeiboAppKey> appKeys, String tags);
}
