package com.tobeface.tgenius.application.impl.qweibo.creator;

import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;

/**
 * 
 * @author loudyn
 * 
 */
public final class QWeiboApiRequests {

	/**
	 * 
	 * @param appKey
	 * @param which
	 * @param content
	 * @return
	 */
	public static WeiboApiRequest newAddPrivate(WeiboAppKey appKey, String which, String content) {
		return new AddPrivateQWeiboApiRequestCreator(appKey, which, content).create();
	}

	/**
	 * 
	 * @param appKey
	 * @param content
	 * @return
	 */
	public static WeiboApiRequest newAddTwitter(WeiboAppKey appKey, String content) {
		return new AddTwitterQWeiboApiRequestCreator(appKey, content).create();
	}

	/**
	 * 
	 * @param appKey
	 * @param talking
	 * @param page
	 * @return
	 */
	public static WeiboApiRequest newSearch(WeiboAppKey appKey, WeiboTalking talking, int page) {
		return new SearchQWeiboApiRequestCreator(appKey, talking, page).create();
	}

	/**
	 * 
	 * @param appKey
	 * @param keyword
	 * @param page
	 * @return
	 */
	public static WeiboApiRequest newSearchUser(WeiboAppKey appKey, String keyword, int page) {
		return new SearchUserQWeiboApiRequestCreator(appKey, keyword, page).create();
	}

	/**
	 * 
	 * @param appKey
	 * @param tags
	 * @param page
	 * @return
	 */
	public static WeiboApiRequest newSearchUserByTags(WeiboAppKey appKey, String tags, int page) {
		return new SearchUserByTagsQWeiboApiRequestCreator(appKey, tags, page).create();
	}

	/**
	 * 
	 * @param appKey
	 * @param name
	 * @return
	 */
	public static WeiboApiRequest newUserInfo(WeiboAppKey appKey, String name) {
		return new UserInfoQWeiboApiRequestCreator(appKey, name).create();
	}

	private QWeiboApiRequests() {}
}
