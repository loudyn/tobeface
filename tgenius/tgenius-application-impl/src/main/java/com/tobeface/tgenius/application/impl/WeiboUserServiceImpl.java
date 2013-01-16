package com.tobeface.tgenius.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.application.WeiboUserService;
import com.tobeface.tgenius.domain.WeiboPlatform;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.WeiboUserRepository;

/**
 * 
 * @author loudyn
 * 
 */
@Service
@Transactional
public class WeiboUserServiceImpl implements WeiboUserService {

	@Autowired
	private WeiboUserRepository weiboUserRepository;

	@Override
	public Page<WeiboUser> queryPage(Page<WeiboUser> page) {
		return weiboUserRepository.queryPage(page);
	}

	@Override
	public List<WeiboUser> query(Object object) {
		return weiboUserRepository.query(object);
	}
	
	@Override
	public void deleteByPlatformAndName(WeiboPlatform platform, String name) {
		weiboUserRepository.deleteByPlatformAndName(platform, name);
	}

	@Override
	public boolean existsByPlatformAndName(WeiboPlatform platform, String name) {
		return weiboUserRepository.existsByPlatformAndName(platform, name);
	}

	@Override
	public void update(WeiboUser entity) {
		weiboUserRepository.update(entity);
	}

	@Override
	public void save(WeiboUser entity) {
		weiboUserRepository.save(entity);
	}
}
