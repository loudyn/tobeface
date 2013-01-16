package com.tobeface.tgenius.infrastructure.wapi.selector;

import java.util.List;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;

/**
 * 
 * @author loudyn
 * 
 */
public abstract class AbstractWeiboApiRequestSelector implements WeiboApiRequestSeletor {

	private final List<WeiboApiRequest> requests;

	/**
	 * 
	 * @param requests
	 */
	protected AbstractWeiboApiRequestSelector(List<WeiboApiRequest> requests) {
		Preconditions.notEmpty(requests);
		this.requests = requests;
	}

	@Override
	public final WeiboApiRequest select() {

		if (isSingleWeiboApiRequest(requests)) {
			return getSingleWeiboApiRequest(requests);
		}

		return doSelect(requests);
	}

	private boolean isSingleWeiboApiRequest(List<WeiboApiRequest> requests) {
		return requests.size() == 1;
	}

	private WeiboApiRequest getSingleWeiboApiRequest(List<WeiboApiRequest> requests) {
		return requests.get(0);
	}

	protected abstract WeiboApiRequest doSelect(List<WeiboApiRequest> requests);
}
