package com.tobeface.tgenius.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.application.WeiboAppKeysService;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboAppKeysRepository;

/**
 * 
 * @author loudyn
 * 
 */
@Service
@Transactional
public class WeiboAppKeysServiceImpl implements WeiboAppKeysService {

	@Autowired
	private WeiboAppKeysRepository appKeysRepository;

	public WeiboAppKeys get(String id) {
		return appKeysRepository.get(id);
	}

	public void save(WeiboAppKeys entity) {
		appKeysRepository.save(entity);
	}

	public void update(WeiboAppKeys entity) {
		appKeysRepository.update(entity);
	}
	
	public void delete(String id) {
		appKeysRepository.delete(id);
	}

	@Override
	public Page<WeiboAppKeys> queryPage(Page<WeiboAppKeys> page) {
		return appKeysRepository.queryPage(page);
	}

}
