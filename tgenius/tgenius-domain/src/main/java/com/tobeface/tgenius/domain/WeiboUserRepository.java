package com.tobeface.tgenius.domain;

import com.tobeface.modules.domain.Page;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboUserRepository {

	/**
	 * 
	 * @param page
	 * @return
	 */
	Page<WeiboUser> queryPage(Page<WeiboUser> page);

	/**
	 * 
	 * @param name
	 * @return
	 */
	WeiboUser queryUniqueByName(String name);

	/**
	 * 
	 * @param name
	 * @return
	 */
	boolean existsByName(String name);

	/**
	 * 
	 * @param entity
	 */
	void save(WeiboUser entity);

	/**
	 * 
	 * @param which
	 */
	void deleteByName(String which);

	/**
	 * 
	 * @param entity
	 */
	void update(WeiboUser entity);

}
