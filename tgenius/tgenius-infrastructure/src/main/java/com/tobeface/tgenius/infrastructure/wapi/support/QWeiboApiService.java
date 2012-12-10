package com.tobeface.tgenius.infrastructure.wapi.support;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequestStrategy;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse.WeiboApiResponseResult;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptionExplorer;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiService;
import com.tobeface.tgenius.infrastructure.wapi.strategy.WeiboApiRequestStrategies;

/**
 * 
 * @author loudyn
 * 
 */
@Component
public class QWeiboApiService implements WeiboApiService {

	private Logger logger = LoggerFactory.getLogger(QWeiboApiService.class);
	
	@Autowired
	@Qualifier("qweibo-api-exception-explorer")
	private WeiboApiExceptionExplorer exceptionExplorer;

	protected Logger getLogger() {
		return logger;
	}

	protected WeiboApiExceptionExplorer getExceptionExplorer() {
		return exceptionExplorer;
	}

	@Override
	public void sendLetter(WeiboAppKeys appKeys, String which, WeiboLetter letter) {
		checkNotNull(appKeys, which, letter);

		WeiboApiRequest req = QWeiboApiRequests.newAddPrivate(appKeys, which, letter.getContent());
		req.execute(
						WeiboApiRequestStrategies.newFastFail(getExceptionExplorer())
		);
	}

	private void checkNotNull(Object... objs) {
		for (Object obj : objs) {
			Preconditions.notNull(obj);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendMentionByRelay(WeiboAppKeys appKeys, WeiboMention mention) {
		checkNotNull(appKeys, mention);

		WeiboApiRequestStrategy strategy = WeiboApiRequestStrategies.newFastFail(getExceptionExplorer());
		WeiboApiResponse resp = QWeiboApiRequests.newTrendsTwitter(appKeys).execute(strategy);

		WeiboApiResponseResult result = resp.getResult();
		List<Map<String, Object>> infos = (List<Map<String, Object>>) result.on("data").on("info").get();
		if (infos.isEmpty()) {
			logger.warn("found empty weibo users to mention.");
			return;
		}

		String relayId = (String) infos.get(0).get("id");
		Map<String, String> users = (Map<String, String>) result.on("data").on("user").get();
		for (String username : users.keySet()) {
			mention.mention(username);
		}

		WeiboApiRequest addRelay = QWeiboApiRequests.newAddRelay(appKeys, relayId, mention.getContent());
		addRelay.execute(strategy);
	}

	@Override
	public void sendMentionByTalking(WeiboAppKeys appKeys, WeiboTalking talking, WeiboMention mention) {
		checkNotNull(appKeys, talking, mention);

		// random on page 1~10
		Set<String> usernames = findWhoTalkaboutInternal(appKeys, talking, 1 + new Random().nextInt(10));
		if (usernames.isEmpty()) {
			logger.warn("found empty weibo users to mention.");
			return;
		}

		for (String username : usernames) {
			mention.mention(username);
		}

		QWeiboApiRequests.newAddTwitter(appKeys, mention.getContent()).execute(
				WeiboApiRequestStrategies.newFastFail(getExceptionExplorer())
		);
	}

	@Override
	public Iterator<WeiboUser> findWhoTagLikes(final WeiboAppKeys appKeys, final String tags) {
		checkNotNull(appKeys, tags);

		return new LazyWeiboApiRequestIterator<WeiboUser>(appKeys) {

			@Override
			protected void lazyRequest(WeiboAppKeys appKeys, Queue<WeiboUser> queue, BloomFilter<CharSequence> filter, int page) {
				computeNextTagLikes(appKeys, queue, filter, page, tags);
			}
		};
	}

	@SuppressWarnings("unchecked")
	protected void computeNextTagLikes(WeiboAppKeys appKeys, Queue<WeiboUser> result,
										BloomFilter<CharSequence> filter, int page,
										String tags) {

		try {

			WeiboApiResponse resp = QWeiboApiRequests.newSearchUserByTags(appKeys, tags, page).execute(
					WeiboApiRequestStrategies.newSleepAndRetry(getExceptionExplorer(), 3 * 60 * 1000)
			);

			List<Map<String, Object>> infos = (List<Map<String, Object>>) resp.getResult().on("data").on("info").get();
			for (Map<String, Object> info : infos) {
				String name = (String) info.get("name");
				name2WeiboUser(appKeys, name, filter, result);
			}
		} catch (Exception e) {
			getLogger().warn("compute next tag like fail,{}", e.getMessage());
		}
	}

	private void name2WeiboUser(WeiboAppKeys appKeys, String name, BloomFilter<CharSequence> filter, Queue<WeiboUser> result) {
		if (filter.mightContain(name)) {
			return;
		}

		try {

			WeiboUser entity = findWeiboUserByName(appKeys, name);
			if (null != entity) {
				result.add(entity);
				filter.put(name);
			}
		} catch (Exception ignore) {
		}
	}

	@SuppressWarnings("unchecked")
	private WeiboUser findWeiboUserByName(WeiboAppKeys appKeys, String name) {
		WeiboApiResponse resp = QWeiboApiRequests.newOtherInfo(appKeys, name).execute(
				WeiboApiRequestStrategies.newFastFail(getExceptionExplorer())
		);

		Map<String, Object> data = (Map<String, Object>) resp.getResult().on("data").get();
		return JsonHelper.newfor(data, WeiboUser.class);
	}

	@Override
	public Iterator<WeiboUser> findWhoKeywordLikes(final WeiboAppKeys appKeys, final String keyword) {
		checkNotNull(appKeys, keyword);

		return new LazyWeiboApiRequestIterator<WeiboUser>(appKeys) {

			@Override
			protected void lazyRequest(WeiboAppKeys appKeys, Queue<WeiboUser> result, BloomFilter<CharSequence> filter, int page) {
				computeNextKeywordLikes(appKeys, result, filter, page, keyword);
			}
		};
	}

	@SuppressWarnings("unchecked")
	protected void computeNextKeywordLikes(WeiboAppKeys appKeys, Queue<WeiboUser> result,
											BloomFilter<CharSequence> filter, int page,
											String keyword) {

		try {

			WeiboApiResponse resp = QWeiboApiRequests.newSearchUser(appKeys, keyword, page).execute(
					WeiboApiRequestStrategies.newSleepAndRetry(getExceptionExplorer(), 3 * 60 * 1000)
			);

			List<Map<String, Object>> infos = (List<Map<String, Object>>) resp.getResult().on("data").on("info").get();
			for (Map<String, Object> info : infos) {
				String name = (String) info.get("name");
				name2WeiboUser(appKeys, name, filter, result);
			}
		} catch (Exception ignore) {
			getLogger().warn("compute next keyword like fail,{}", ignore.getMessage());
		}
	}

	@Override
	public Iterator<String> findWhoTalkabout(final WeiboAppKeys appKeys, final WeiboTalking talking) {
		checkNotNull(appKeys, talking);
		return new LazyWeiboApiRequestIterator<String>(appKeys) {

			@Override
			protected void lazyRequest(WeiboAppKeys appKeys, Queue<String> result, BloomFilter<CharSequence> filter, int page) {
				computeNextTalkabout(appKeys, result, filter, page, talking);
			}
		};
	}

	protected void computeNextTalkabout(WeiboAppKeys appKeys, Queue<String> result,
										BloomFilter<CharSequence> filter, int page,
										WeiboTalking talking) {

		try {

			Set<String> usernames = findWhoTalkaboutInternal(appKeys, talking, page);
			for (String username : usernames) {
				if (filter.mightContain(username)) {
					continue;
				}
				result.add(username);
				filter.put(username);
			}
		} catch (Exception ignore) {
		}
	}

	@SuppressWarnings("unchecked")
	private Set<String> findWhoTalkaboutInternal(WeiboAppKeys appKeys, WeiboTalking talking, int page) {

		WeiboApiResponse resp = QWeiboApiRequests.newSearchTwitter(appKeys, talking, page).execute(
				WeiboApiRequestStrategies.newSleepAndRetry(getExceptionExplorer(), 3 * 60 * 1000)
		);

		Map<String, String> users = (Map<String, String>) resp.getResult().on("data").on("user").get();
		return users.keySet();
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	abstract class LazyWeiboApiRequestIterator<T> implements Iterator<T> {
		final WeiboAppKeys appKeys;

		Queue<T> result = new LinkedList<T>();
		BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(), 10000);
		AtomicInteger page = new AtomicInteger(1);

		LazyWeiboApiRequestIterator(WeiboAppKeys appKeys) {
			this.appKeys = appKeys;
		}

		@Override
		public final boolean hasNext() {
			if (null != result.peek()) {
				return true;
			}

			lazyRequest(appKeys, result, filter, page.getAndIncrement());
			return null != result.peek();
		}

		protected abstract void lazyRequest(WeiboAppKeys appKeys, Queue<T> result, BloomFilter<CharSequence> filter, int page);

		@Override
		public final T next() {
			return result.poll();
		}

		@Override
		public final void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
