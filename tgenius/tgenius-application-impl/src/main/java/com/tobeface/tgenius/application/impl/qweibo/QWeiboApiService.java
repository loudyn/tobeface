package com.tobeface.tgenius.application.impl.qweibo;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.tobeface.modules.lang.Strings;
import com.tobeface.tgenius.application.WeiboApiService;
import com.tobeface.tgenius.application.impl.qweibo.creator.QWeiboApiRequests;
import com.tobeface.tgenius.application.impl.qweibo.exception.QWeiboApiExceptionExplorer;
import com.tobeface.tgenius.application.impl.qweibo.model.QWeiboUser;
import com.tobeface.tgenius.application.impl.qweibo.model.QWeiboUserAdapter;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptionExplorer;
import com.tobeface.tgenius.infrastructure.wapi.selector.WeiboApiRequestSeletor;
import com.tobeface.tgenius.infrastructure.wapi.selector.WeiboApiRequestSeletors;
import com.tobeface.tgenius.infrastructure.wapi.strategy.WeiboApiRequestStrategies;
import com.tobeface.tgenius.infrastructure.wapi.support.BulkLazyWeiboApiRequestIterator;

/**
 * 
 * @author loudyn
 * 
 */
public class QWeiboApiService implements WeiboApiService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier(QWeiboApiExceptionExplorer.BEAN_NAME)
	private WeiboApiExceptionExplorer exceptionExplorer;

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<WeiboUser> findUserByTalking(final List<WeiboAppKey> appKeys, final WeiboTalking talking) {

		final AtomicInteger page = new AtomicInteger(1);
		final BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(), 10000);

		return new BulkLazyWeiboApiRequestIterator<WeiboUser>() {

			@Override
			protected List<WeiboUser> computeNextBulk() {
				int current = page.getAndIncrement();
				WeiboApiResponse resp = findByTalkingPage(appKeys, talking, current);
				if (isEmptyResponse(resp)) {
					return Collections.emptyList();
				}

				Map<String, String> nameNicks = (Map<String, String>) resp.getResult().on("data").on("user").get();
				return findUserByNames(appKeys, nameNicks.keySet(), filter);
			}
		};
	}

	private List<WeiboUser> findUserByNames(List<WeiboAppKey> appKeys, Collection<String> usernames, BloomFilter<CharSequence> filter) {

		List<WeiboUser> result = new LinkedList<WeiboUser>();
		for (String name : usernames) {

			if (filter.mightContain(name)) {
				continue;
			}

			WeiboApiResponse resp = findUserByName(appKeys, name);
			if (isEmptyResponse(resp)) {
				continue;
			}

			QWeiboUser qWeiboUser = resp.getResult().on("data").get(QWeiboUser.class);
			result.add(new QWeiboUserAdapter(qWeiboUser));
			filter.put(name);
		}

		return result;
	}

	private WeiboApiResponse findUserByName(List<WeiboAppKey> appKeys, final String name) {
		List<WeiboApiRequest> requests = Lists.transform(appKeys, new Function<WeiboAppKey, WeiboApiRequest>() {

			@Override
			public WeiboApiRequest apply(WeiboAppKey input) {
				return QWeiboApiRequests.newUserInfo(input, name);
			}

		});

		try {

			return performRequests(requests);
		} catch (Exception e) {
			logger.warn("find user by talking occur error[{}]", new Object[] { e.getMessage() });
			return null;
		}
	}

	private WeiboApiResponse performRequests(List<WeiboApiRequest> requests) throws Exception {

		WeiboApiRequestSeletor selector = WeiboApiRequestSeletors.newRandom(requests);
		int counter = requests.size();
		while (true) {
			try {

				counter--;
				return selector.select().execute(WeiboApiRequestStrategies.newFailFast(exceptionExplorer));
			} catch (Exception e) {
				if (counter <= 0) {
					throw e;
				}
			}
		}
	}

	private WeiboApiResponse findByTalkingPage(List<WeiboAppKey> appKeys, final WeiboTalking talking, final int page) {
		List<WeiboApiRequest> requests = Lists.transform(appKeys, new Function<WeiboAppKey, WeiboApiRequest>() {

			@Override
			public WeiboApiRequest apply(WeiboAppKey input) {
				return QWeiboApiRequests.newSearch(input, talking, page);
			}

		});

		try {

			return performRequests(requests);
		} catch (Exception e) {
			logger.warn("find user by talking occur error[{}]", new Object[] { e.getMessage() });
			return null;
		}
	}

	private boolean isEmptyResponse(WeiboApiResponse resp) {
		if (null == resp) {
			return true;
		}

		int resultCode = (Integer) resp.getResult().on("ret").get();
		return resultCode != 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<WeiboUser> findUserByKeyword(final List<WeiboAppKey> appKeys, final String keyword) {

		final AtomicInteger page = new AtomicInteger(1);
		final BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(), 10000);

		return new BulkLazyWeiboApiRequestIterator<WeiboUser>() {

			@Override
			protected List<WeiboUser> computeNextBulk() {
				int current = page.getAndIncrement();
				WeiboApiResponse resp = findByKeywordPage(appKeys, keyword, current);
				if (isEmptyResponse(resp)) {
					return Collections.emptyList();
				}

				List<Map<String, Object>> simpleUserInfos = (List<Map<String, Object>>) resp.getResult().on("data").on("info").get();
				return findUserByNames(appKeys, Lists.transform(simpleUserInfos, new Function<Map<String, Object>, String>() {

					@Override
					public String apply(Map<String, Object> input) {
						return (String) input.get("name");
					}
				}), filter);
			}

		};
	}

	private WeiboApiResponse findByKeywordPage(List<WeiboAppKey> appKeys, final String keyword, final int page) {
		List<WeiboApiRequest> requests = Lists.transform(appKeys, new Function<WeiboAppKey, WeiboApiRequest>() {

			@Override
			public WeiboApiRequest apply(WeiboAppKey input) {
				return QWeiboApiRequests.newSearchUser(input, keyword, page);
			}

		});

		try {
			return performRequests(requests);
		} catch (Exception e) {
			logger.warn("find user by keyword occur error[{}]", new Object[] { e.getMessage() });
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<WeiboUser> findUserByTags(final List<WeiboAppKey> appKeys, final String tags) {

		final AtomicInteger page = new AtomicInteger(1);
		final BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(), 10000);

		return new BulkLazyWeiboApiRequestIterator<WeiboUser>() {

			@Override
			protected List<WeiboUser> computeNextBulk() {
				int current = page.getAndIncrement();
				WeiboApiResponse resp = findByTagsPage(appKeys, tags, current);
				if (isEmptyResponse(resp)) {
					return Collections.emptyList();
				}

				List<Map<String, Object>> simpleUserInfos = (List<Map<String, Object>>) resp.getResult().on("data").on("info").get();
				return findUserByNames(appKeys, Lists.transform(simpleUserInfos, new Function<Map<String, Object>, String>() {

					@Override
					public String apply(Map<String, Object> input) {
						return (String) input.get("name");
					}
				}), filter);
			}

		};
	}

	private WeiboApiResponse findByTagsPage(List<WeiboAppKey> appKeys, final String tags, final int page) {
		List<WeiboApiRequest> requests = Lists.transform(appKeys, new Function<WeiboAppKey, WeiboApiRequest>() {

			@Override
			public WeiboApiRequest apply(WeiboAppKey input) {
				return QWeiboApiRequests.newSearchUserByTags(input, tags, page);
			}

		});

		try {

			return performRequests(requests);
		} catch (Exception e) {
			logger.warn("find user by tags occur error[{}]", new Object[] { e.getMessage() });
			return null;
		}
	}

	@Override
	public void send(final List<WeiboAppKey> appKeys, final WeiboLetter letter) {

		String content = letter.getContent();
		for (String username : letter.getUsers()) {
			addPrivateEach(appKeys, username, content);
		}
	}

	private void addPrivateEach(final List<WeiboAppKey> appKeys, final String username, final String content) {
		List<WeiboApiRequest> requests = Lists.transform(appKeys, new Function<WeiboAppKey, WeiboApiRequest>() {

			@Override
			public WeiboApiRequest apply(WeiboAppKey input) {
				return QWeiboApiRequests.newAddPrivate(input, username, content);
			}

		});

		try {

			performRequests(requests);
		} catch (Exception e) {
			logger.error("send letter fail. username[{}],error[{}]", new Object[] { username, e.getMessage() });
		}
	}

	@Override
	public void send(final List<WeiboAppKey> appKeys, final WeiboMention mention) {

		String content = mention.getContent();
		for (String username : mention.getNames()) {
			addTwitterEach(appKeys, username, content);
		}
	}

	private void addTwitterEach(final List<WeiboAppKey> appKeys, final String username, final String content) {
		List<WeiboApiRequest> requests = Lists.transform(appKeys, new Function<WeiboAppKey, WeiboApiRequest>() {

			@Override
			public WeiboApiRequest apply(WeiboAppKey input) {
				StringBuilder buf = new StringBuilder();
				if (!Strings.startsWith(username, "@")) {
					buf.append("@");
				}

				buf.append(username).append(" ").append(content);
				return QWeiboApiRequests.newAddTwitter(input, buf.toString());
			}

		});

		try {

			performRequests(requests);
		} catch (Exception e) {
			logger.error("send mention fail. username[{}],error[{}]", new Object[] { username, e.getMessage() });
		}
	}

}
