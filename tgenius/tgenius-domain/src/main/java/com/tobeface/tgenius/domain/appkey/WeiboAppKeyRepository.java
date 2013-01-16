package com.tobeface.tgenius.domain.appkey;

import java.util.List;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.domain.WeiboPlatform;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboAppKeyRepository {

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
	 * @return
	 */
	List<WeiboAppKey> queryAll();

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
