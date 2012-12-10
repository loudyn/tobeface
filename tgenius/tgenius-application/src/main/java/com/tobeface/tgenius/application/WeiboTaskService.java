package com.tobeface.tgenius.application;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.domain.task.WeiboTask;

/**
 * 
 * @author loudyn
 *
 */
public interface WeiboTaskService {

	/**
	 * 
	 * @param task
	 */
	void save(WeiboTask task);

	/**
	 * 
	 * @param task
	 */
	void update(WeiboTask task);

	/**
	 * 
	 * @param page
	 * @return
	 */
	Page<WeiboTask> queryPage(Page<WeiboTask> page);
}
