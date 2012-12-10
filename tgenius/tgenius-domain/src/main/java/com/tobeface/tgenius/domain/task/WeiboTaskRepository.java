package com.tobeface.tgenius.domain.task;

import com.tobeface.modules.domain.Page;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboTaskRepository {

	/**
	 * 
	 * @param entity
	 */
	void save(WeiboTask entity);

	/**
	 * 
	 * @param entity
	 */
	void update(WeiboTask entity);

	/**
	 * 
	 * @param page
	 * @return
	 */
	Page<WeiboTask> queryPage(Page<WeiboTask> page);
}
