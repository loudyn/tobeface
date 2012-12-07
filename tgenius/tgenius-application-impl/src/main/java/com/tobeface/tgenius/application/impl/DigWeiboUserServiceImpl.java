package com.tobeface.tgenius.application.impl;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
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
				saveOrUpdateWeiboUsers(it);
			}
		});
	}

	protected void saveOrUpdateWeiboUsers(Iterator<WeiboUser> it) {
		while (it.hasNext()) {

			try {

				WeiboUser entity = it.next();

				if (weiboUserRepository.existsByName(entity.getName())) {
					weiboUserRepository.update(entity);
					continue;
				}

				weiboUserRepository.save(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void digByTags(final String tags) {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				WeiboAppKeys appKeys = weiboAppKeysRepository.findAnyAvaliable();
				Iterator<WeiboUser> it = weiboApiService.findWhoTagLikes(appKeys, tags);
				saveOrUpdateWeiboUsers(it);
			}
		});
	}

}
