package com.tobeface.tgenius.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.application.WeiboTaskService;
import com.tobeface.tgenius.domain.task.WeiboTask;
import com.tobeface.tgenius.domain.task.WeiboTaskRepository;

/**
 * 
 * @author loudyn
 * 
 */
@Service
@Transactional
public class WeiboTaskServiceImpl implements WeiboTaskService {

	@Autowired
	private WeiboTaskRepository weiboTaskRepository;

	@Override
	public void save(WeiboTask task) {
		weiboTaskRepository.save(task);
	}

	@Override
	public void update(WeiboTask task) {
		weiboTaskRepository.update(task);
	}

	@Override
	public Page<WeiboTask> queryPage(Page<WeiboTask> page) {
		return weiboTaskRepository.queryPage(page);
	}
}
