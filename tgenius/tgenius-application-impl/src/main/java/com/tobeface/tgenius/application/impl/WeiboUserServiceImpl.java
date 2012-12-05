package com.tobeface.tgenius.application.impl;

import org.springframework.stereotype.Service;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.application.WeiboUserService;
import com.tobeface.tgenius.domain.WeiboUser;

@Service
public class WeiboUserServiceImpl implements WeiboUserService {

	@Override
	public Page<WeiboUser> queryPage(Page<WeiboUser> page) {
		return null;
	}
}
