package com.tobeface.tgenius.application.impl;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobeface.tgenius.application.DigWeiboUserService;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboAppKeysRepository;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.WeiboUserRepository;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiService;

/**
 * 
 * @author loudyn
 * 
 */
@Service
public class DigWeiboUserServiceImpl implements DigWeiboUserService {

	private ExecutorService executor = Executors.newFixedThreadPool(20);
	@Autowired
	private WeiboApiService weiboApiService;

	@Autowired
	private WeiboAppKeysRepository weiboAppKeysRepository;
	@Autowired
	private WeiboUserRepository weiboUserRepository;

	public void digByKeyword(final String keyword) {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				WeiboAppKeys appKeys = weiboAppKeysRepository.findAnyAvaliable();
				Iterator<WeiboUser> it = weiboApiService.findWhoKeywordLikes(appKeys, keyword);
				while (it.hasNext()) {
					WeiboUser entity = it.next();
					weiboUserRepository.save(entity);
				}
			}
		});
	}

	public void digByTags(final String tags) {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				WeiboAppKeys appKeys = weiboAppKeysRepository.findAnyAvaliable();
				Iterator<WeiboUser> it = weiboApiService.findWhoTagLikes(appKeys, tags);
				while (it.hasNext()) {
					WeiboUser entity = it.next();
					weiboUserRepository.save(entity);
				}
			}
		});
	}

}
