package com.tobeface.tgenius.application.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.application.WeiboApiService;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboPlatform;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;

/**
 * 
 * @author loudyn
 * 
 */
public class PlatformRoutingWeiboApiService implements WeiboApiService {

	public static final String BEAN_NAME = "platformRoutingWeiboApiService";
	private Map<WeiboPlatform, WeiboApiService> weiboApiServices;

	/**
	 * 
	 * @param weiboApiServices
	 */
	public PlatformRoutingWeiboApiService(Map<WeiboPlatform, WeiboApiService> weiboApiServices) {
		Preconditions.notEmpty(weiboApiServices);
		this.weiboApiServices = weiboApiServices;
	}

	@Override
	public Iterator<WeiboUser> findUserByTalking(List<WeiboAppKey> appKeys, WeiboTalking talking) {
		WeiboApiService platformWeiboApiService = determinePlatformWeiboApiService(talking.getPlatform());
		return platformWeiboApiService.findUserByTalking(appKeys, talking);
	}

	private WeiboApiService determinePlatformWeiboApiService(WeiboPlatform platform) {
		Preconditions.notNull(platform);
		WeiboApiService result = weiboApiServices.get(platform);
		if (null == result) {
			throw new NotFoundPlatformRoutingException(platform);
		}
		return result;
	}

	@Override
	public Iterator<WeiboUser> findUserByKeyword(List<WeiboAppKey> appKeys, String keyword) {
		WeiboApiService platformWeiboApiService = determinePlatformWeiboApiService(getPlatform(appKeys));
		return platformWeiboApiService.findUserByKeyword(appKeys, keyword);
	}

	private WeiboPlatform getPlatform(List<WeiboAppKey> appKeys) {
		Preconditions.notEmpty(appKeys);
		return appKeys.get(0).getPlatform();
	}

	@Override
	public Iterator<WeiboUser> findUserByTags(List<WeiboAppKey> appKeys, String tags) {
		WeiboApiService platformWeiboApiService = determinePlatformWeiboApiService(getPlatform(appKeys));
		return platformWeiboApiService.findUserByTags(appKeys, tags);
	}

	@Override
	public void send(List<WeiboAppKey> appKeys, WeiboLetter letter) {
		WeiboApiService platformWeiboApiService = determinePlatformWeiboApiService(letter.getPlatform());
		platformWeiboApiService.send(appKeys, letter);
	}

	@Override
	public void send(List<WeiboAppKey> appKeys, WeiboMention mention) {
		WeiboApiService platformWeiboApiService = determinePlatformWeiboApiService(mention.getPlatform());
		platformWeiboApiService.send(appKeys, mention);
	}

	@SuppressWarnings("serial")
	public class NotFoundPlatformRoutingException extends RuntimeException {

		public NotFoundPlatformRoutingException(WeiboPlatform platform) {
			super("Not found routing for platform[" + platform + "]");
		}
	}

	// need for cglib
	protected PlatformRoutingWeiboApiService() {}
}
