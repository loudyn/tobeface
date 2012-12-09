package com.tobeface.tgenius.infrastructure.wapi;

import java.util.Iterator;
import java.util.Map;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.WeiboUser;

/**
 * 
 * @author loudyn
 * 
 */
public abstract class DelegateWeiboApiService implements WeiboApiService {

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	@SuppressWarnings("serial")
	class DelegateWeiboApiServiceNotFoundException extends RuntimeException {
		DelegateWeiboApiServiceNotFoundException(String delegateKey) {
			super("Can't find delegate WeiboApiService[" + delegateKey + "]");
		}
	}

	private final Map<String, WeiboApiService> delegates;

	/**
	 * 
	 * @param delegates
	 */
	public DelegateWeiboApiService(Map<String, WeiboApiService> delegates) {
		Preconditions.notNull(delegates);
		this.delegates = delegates;
	}

	protected Map<String, WeiboApiService> getDelegates() {
		return delegates;
	}

	@Override
	public void sendLetter(WeiboAppKeys appKeys, String which, WeiboLetter letter) {
		WeiboApiService delegate = detemineWeiboApiService(appKeys);
		delegate.sendLetter(appKeys, which, letter);
	}

	@Override
	public void sendMentionByRelay(WeiboAppKeys appKeys, WeiboMention mention) {
		WeiboApiService delegate = detemineWeiboApiService(appKeys);
		delegate.sendMentionByRelay(appKeys, mention);
	}

	@Override
	public void sendMentionByTalking(WeiboAppKeys appKeys, WeiboTalking talking, WeiboMention mention) {
		WeiboApiService delegate = detemineWeiboApiService(appKeys);
		delegate.sendMentionByTalking(appKeys, talking, mention);
	}

	@Override
	public Iterator<String> findWhoTalkabout(WeiboAppKeys appKeys, WeiboTalking talking) {
		WeiboApiService delegate = detemineWeiboApiService(appKeys);
		return delegate.findWhoTalkabout(appKeys, talking);
	}

	@Override
	public Iterator<WeiboUser> findWhoTagLikes(WeiboAppKeys appKeys, String tags) {
		WeiboApiService delegate = detemineWeiboApiService(appKeys);
		return delegate.findWhoTagLikes(appKeys, tags);
	}

	@Override
	public Iterator<WeiboUser> findWhoKeywordLikes(WeiboAppKeys appKeys, String keyword) {
		WeiboApiService delegate = detemineWeiboApiService(appKeys);
		return delegate.findWhoKeywordLikes(appKeys, keyword);
	}

	private final WeiboApiService detemineWeiboApiService(WeiboAppKeys appKeys) {
		String delegateKey = determineDelegateKey(appKeys);
		if (getDelegates().containsKey(delegateKey)) {
			return getDelegates().get(delegateKey);
		}

		throw new DelegateWeiboApiServiceNotFoundException(delegateKey);
	}

	/**
	 * 
	 * @param appKeys
	 * @return
	 */
	protected abstract String determineDelegateKey(WeiboAppKeys appKeys);
}
