package com.tobeface.tgenius.application;

import java.util.List;

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
	 * @param object
	 * @return
	 */
	List<WeiboUser> query(Object object);

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
	void update(WeiboUser entity);

	/**
	 * 
	 * @param entity
	 */
	void save(WeiboUser entity);
}
