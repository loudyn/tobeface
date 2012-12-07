package com.tobeface.tgenius.application;

import com.tobeface.modules.domain.Page;
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
	 * @param name
	 * @return
	 */
	WeiboUser queryUniqueByName(String name);

	/**
	 * 
	 * @param which
	 */
	void deleteByName(String which);
}
