package com.tobeface.tgenius.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tobeface.modules.domain.Page;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.application.WeiboAppKeyService;
import com.tobeface.tgenius.domain.WeiboPlatform;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;
import com.tobeface.tgenius.domain.appkey.WeiboAppKeyRepository;

/**
 * 
 * @author loudyn
 * 
 */
@Service
@Transactional
public class WeiboAppKeysServiceImpl implements WeiboAppKeyService {

	@Autowired
	private WeiboAppKeyRepository appKeyRepository;

	public WeiboAppKey get(String id) {
		return appKeyRepository.get(id);
	}

	public void save(WeiboAppKey entity) {
		appKeyRepository.save(entity);
	}

	public void update(WeiboAppKey entity) {
		appKeyRepository.update(entity);
	}

	public void delete(String id) {
		appKeyRepository.delete(id);
	}

	@Override
	public List<WeiboAppKey> queryByPlatform(WeiboPlatform platform) {
		Preconditions.notNull(platform);
		return appKeyRepository.queryByPlatform(platform);
	}

	@Override
	public List<WeiboAppKey> queryByPlatformAndEnablePrivateLetter(WeiboPlatform platform) {
		Preconditions.notNull(platform);
		return appKeyRepository.queryByPlatformAndEnablePrivateLetter(platform);
	}

	@Override
	public Page<WeiboAppKey> queryPage(Page<WeiboAppKey> page) {
		return appKeyRepository.queryPage(page);
	}
}
