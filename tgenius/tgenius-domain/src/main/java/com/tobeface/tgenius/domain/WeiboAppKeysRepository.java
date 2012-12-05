package com.tobeface.tgenius.domain;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboAppKeysRepository {

	/**
	 * 
	 * @return
	 */
	WeiboAppKeys findAnyAvaliable();

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
}
