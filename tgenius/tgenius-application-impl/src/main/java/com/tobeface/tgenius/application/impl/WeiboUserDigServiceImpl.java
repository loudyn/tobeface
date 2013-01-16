package com.tobeface.tgenius.application.impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tobeface.tgenius.application.WeiboApiService;
import com.tobeface.tgenius.application.WeiboUserDigService;
import com.tobeface.tgenius.application.WeiboUserService;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;

/**
 * 
 * @author loudyn
 * 
 */
@Service
public class WeiboUserDigServiceImpl implements WeiboUserDigService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier(PlatformRoutingWeiboApiService.BEAN_NAME)
	private WeiboApiService weiboApiService;

	@Autowired
	private WeiboUserService weiboUserService;

	@Async
	@Override
	public void findUserByTalking(List<WeiboAppKey> appKeys, WeiboTalking talking) {
		Iterator<WeiboUser> users = weiboApiService.findUserByTalking(appKeys, talking);
		while (users.hasNext()) {
			persistUser(users.next());
		}
	}

	private void persistUser(WeiboUser entity) {
		try {

			if (weiboUserService.existsByPlatformAndName(entity.getPlatform(), entity.getName())) {
				weiboUserService.update(entity);
				return;
			}

			weiboUserService.save(entity);
		} catch (Exception ignore) {
			logger.error("persist user occur error.", ignore);
		}
	}

	@Async
	@Override
	public void findUserByKeyword(List<WeiboAppKey> appKeys, String keyword) {
		Iterator<WeiboUser> users = weiboApiService.findUserByKeyword(appKeys, keyword);
		while (users.hasNext()) {
			persistUser(users.next());
		}
	}

	@Async
	@Override
	public void findUserByTags(List<WeiboAppKey> appKeys, String tags) {
		Iterator<WeiboUser> users = weiboApiService.findUserByTags(appKeys, tags);
		while (users.hasNext()) {
			persistUser(users.next());
		}
	}

}
