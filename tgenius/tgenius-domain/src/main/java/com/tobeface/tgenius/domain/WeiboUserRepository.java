package com.tobeface.tgenius.domain;

import java.util.List;

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
	 * @param object
	 * @return
	 */
	List<WeiboUser> query(Object object);

	/**
	 * 
	 * @param entity
	 */
	void save(WeiboUser entity);

	/**
	 * 
	 * @param entity
	 */
	void update(WeiboUser entity);

	/**
	 * 
	 * @param platform
	 * @param name
	 * @return
	 */
	boolean existsByPlatformAndName(WeiboPlatform platform, String name);

	/**
	 * 
	 * @param platform
	 * @param name
	 */
	void deleteByPlatformAndName(WeiboPlatform platform, String name);
}
