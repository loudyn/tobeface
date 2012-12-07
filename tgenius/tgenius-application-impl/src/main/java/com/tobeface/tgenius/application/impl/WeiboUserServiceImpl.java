package com.tobeface.tgenius.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.application.WeiboUserService;
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
	public WeiboUser queryUniqueByName(String name) {
		return weiboUserRepository.queryUniqueByName(name);
	}

	@Override
	public void deleteByName(String name) {
		weiboUserRepository.deleteByName(name);
	}
}
