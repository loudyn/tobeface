package com.tobeface.tgenius.application;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.domain.WeiboAppKeys;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboAppKeysService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	WeiboAppKeys get(String id);

	/**
	 * 
	 * @param entity
	 */
	void save(WeiboAppKeys entity);

	/**
	 * 
	 * @param entity
	 */
	void update(WeiboAppKeys entity);

	/**
	 * 
	 * @param id
	 */
	void delete(String id);

	Page<WeiboAppKeys> queryPage(Page<WeiboAppKeys> page);

}
