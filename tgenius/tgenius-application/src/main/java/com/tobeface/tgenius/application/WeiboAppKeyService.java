package com.tobeface.tgenius.application;

import java.util.List;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.domain.WeiboPlatform;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboAppKeyService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	WeiboAppKey get(String id);

	/**
	 * 
	 * @param entity
	 */
	void save(WeiboAppKey entity);

	/**
	 * 
	 * @param entity
	 */
	void update(WeiboAppKey entity);

	/**
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 
	 * @param page
	 * @return
	 */
	Page<WeiboAppKey> queryPage(Page<WeiboAppKey> page);

	/**
	 * 
	 * @param platform
	 * @return
	 */
	List<WeiboAppKey> queryByPlatform(WeiboPlatform platform);

	/**
	 * 
	 * @param platform
	 * @return
	 */
	List<WeiboAppKey> queryByPlatformAndEnablePrivateLetter(WeiboPlatform platform);
}
