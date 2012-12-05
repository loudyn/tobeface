package com.tobeface.tgenius.infrastructure.wapi;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboAddressService {

	/**
	 * 
	 * @param code
	 * @return
	 */
	String getStateNameByCode(String code);

	/**
	 * 
	 * @param code
	 * @return
	 */
	String getCityNameByCode(String code);

	/**
	 * 
	 * @param code
	 * @return
	 */
	String getRegionNameByCode(String code);
}
