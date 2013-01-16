package com.tobeface.tgenius.infrastructure.wapi.selector;

import java.util.List;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboApiRequestSeletors {

	/**
	 * 
	 * @param requests
	 * @return
	 */
	public static WeiboApiRequestSeletor newRandom(List<WeiboApiRequest> requests) {
		return new RandomWeiboApiRequestSelector(requests);
	}

	/**
	 * 
	 * @param requests
	 * @return
	 */
	public static WeiboApiRequestSeletor newRoundRobin(List<WeiboApiRequest> requests) {
		return new RoundRobinWeiboApiRequestSeletor(requests);
	}

	private WeiboApiRequestSeletors() {}

}
