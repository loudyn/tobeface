package com.tobeface.tgenius.application;

import java.util.List;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.domain.WeiboPlatform;
import com.tobeface.tgenius.domain.WeiboUser;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboUserService {

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
	 * @param platform
	 * @param name
	 */
	void deleteByPlatformAndName(WeiboPlatform platform, String name);

	/**
	 * 
	 * @param name
	 * @return
	 */
	boolean existsByPlatformAndName(WeiboPlatform platform, String name);

	/**
	 * 
	 * @param entity
	 */
	void update(WeiboUser entity);

	/**
	 * 
	 * @param entity
	 */
	void save(WeiboUser entity);
}
