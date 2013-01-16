package com.tobeface.tgenius.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.application.WeiboApiService;
import com.tobeface.tgenius.application.WeiboAppKeyService;
import com.tobeface.tgenius.application.WeiboBombService;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;

/**
 * 
 * @author loudyn
 * 
 */
@Service
public class WeiboBombServiceImpl implements WeiboBombService {

	@Autowired
	@Qualifier(PlatformRoutingWeiboApiService.BEAN_NAME)
	private WeiboApiService weiboApiService;

	@Autowired
	private WeiboAppKeyService appKeysService;

	@Override
	public void fire(WeiboLetter letter) {
		Preconditions.notNull(letter);
		List<WeiboAppKey> appKeys = appKeysService.queryByPlatformAndEnablePrivateLetter(letter.getPlatform());
		weiboApiService.send(appKeys, letter);
	}

	@Override
	public void fire(WeiboMention mention) {
		Preconditions.notNull(mention);
		List<WeiboAppKey> appKeys = appKeysService.queryByPlatform(mention.getPlatform());
		weiboApiService.send(appKeys, mention);
	}
}
