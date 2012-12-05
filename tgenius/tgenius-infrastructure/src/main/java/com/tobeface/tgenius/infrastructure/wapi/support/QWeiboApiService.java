package com.tobeface.tgenius.infrastructure.wapi.support;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiService;

/**
 * 
 * @author loudyn
 * 
 */
@Component
public class QWeiboApiService implements WeiboApiService {

	@SuppressWarnings("unchecked")
	public void sendLetter(WeiboAppKeys appKeys, String which, WeiboLetter letter) {
		Map<String, Object> result = QWeiboApiRequests.newAddPrivate(appKeys, which, letter.getContent()).execute(Map.class);
		if (isNotOkResult(result)) {}
	}

	private boolean isNotOkResult(Map<String, Object> result) {
		int ret = (Integer) result.get("ret");
		return ret != 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendMentionByRelay(WeiboAppKeys appKeys, WeiboMention mention) {
		Map<String, Object> result = QWeiboApiRequests.newTrendsTwitter(appKeys).execute(Map.class);
		List<Map<String, Object>> infos = extractInfos(result);
		if (infos.isEmpty()) {

		}

		String relayId = (String) infos.get(0).get("id");
		WeiboApiRequest addRelay = QWeiboApiRequests.newAddRelay(appKeys, relayId, mention.getContent());
		Map<String, Object> addResult = addRelay.execute(Map.class);
		if (isNotOkResult(addResult)) {

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendMentionByTalking(WeiboAppKeys appKeys, WeiboTalking talking, WeiboMention mention) {
		Set<String> usernames = findWhoTalkaboutInternal(appKeys, talking, 1);
		for (String username : usernames) {
			mention.mention(username);
		}

		Map<String, Object> result = QWeiboApiRequests.newAddTwitter(appKeys, mention.getContent()).execute(Map.class);
		if (isNotOkResult(result)) {}

	}

	@Override
	public Iterator<WeiboUser> findWhoTagLikes(final WeiboAppKeys appKeys, final String tags) {
		final AtomicInteger page = new AtomicInteger(1);
		final Queue<WeiboUser> those = new LinkedList<WeiboUser>();
		final BloomFilter<CharSequence> bloom = BloomFilter.create(Funnels.stringFunnel(), 10000);
		return new Iterator<WeiboUser>() {

			@Override
			public boolean hasNext() {
				if (null != those.peek()) {
					return true;
				}

				computeNextTagLikes(appKeys, tags, those, page.getAndIncrement(), bloom);
				return null != those.peek();
			}

			@Override
			public WeiboUser next() {
				return those.poll();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		};
	}

	@SuppressWarnings("unchecked")
	protected void computeNextTagLikes(WeiboAppKeys appKeys, String tags, Queue<WeiboUser> those, int page, BloomFilter<CharSequence> bloom) {
		try {

			Map<String, Object> result = QWeiboApiRequests.newSearchUserByTags(appKeys, tags, page).execute(Map.class);
			List<Map<String, Object>> infos = extractInfos(result);
			for (Map<String, Object> info : infos) {
				String name = (String) info.get("name");
				if (bloom.mightContain(name)) {
					continue;
				}

				WeiboUser entity = findWeiboUserByName(appKeys, name);
				if (null != entity) {
					those.add(entity);
					bloom.put(name);
				}
			}
		} catch (Exception e) {

		}
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> extractInfos(Map<String, Object> result) {
		if (isNotOkResult(result)) {
			return Collections.emptyList();
		}

		Map<String, Object> data = (Map<String, Object>) result.get("data");
		return (List<Map<String, Object>>) data.get("infos");
	}

	@SuppressWarnings("unchecked")
	private WeiboUser findWeiboUserByName(WeiboAppKeys appKeys, String name) {
		Map<String, Object> result = QWeiboApiRequests.newOtherInfo(appKeys, name).execute(Map.class);
		if (isNotOkResult(result)) {
			return null;
		}

		Map<String, Object> infoData = (Map<String, Object>) result.get("data");
		return JsonHelper.newfor(infoData, WeiboUser.class);
	}

	@Override
	public Iterator<WeiboUser> findWhoKeywordLikes(final WeiboAppKeys appKeys, final String keyword) {
		final AtomicInteger page = new AtomicInteger(1);
		final Queue<WeiboUser> those = new LinkedList<WeiboUser>();
		final BloomFilter<CharSequence> bloom = BloomFilter.create(Funnels.stringFunnel(), 10000);
		return new Iterator<WeiboUser>() {

			@Override
			public boolean hasNext() {
				if (null != those.peek()) {
					return true;
				}

				computeNextKeywordLikes(appKeys, keyword, those, page.getAndIncrement(), bloom);
				return null != those.peek();
			}

			@Override
			public WeiboUser next() {
				return those.poll();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		};
	}

	@SuppressWarnings("unchecked")
	protected void computeNextKeywordLikes(WeiboAppKeys appKeys, String keyword, Queue<WeiboUser> those, int page, BloomFilter<CharSequence> bloom) {
		try {

			Map<String, Object> result = QWeiboApiRequests.newSearchUser(appKeys, keyword, page).execute(Map.class);
			List<Map<String, Object>> infos = extractInfos(result);

			for (Map<String, Object> info : infos) {
				String name = (String) info.get("name");
				if (bloom.mightContain(name)) {
					continue;
				}

				WeiboUser entity = findWeiboUserByName(appKeys, name);
				if (null != entity) {
					those.add(entity);
					bloom.put(name);
				}
			}
		} catch (Exception ignore) {

		}
	}

	public Iterator<String> findWhoTalkabout(final WeiboAppKeys appKeys, final WeiboTalking talking) {

		final AtomicInteger page = new AtomicInteger(1);
		final Queue<String> those = new LinkedList<String>();
		final BloomFilter<CharSequence> bloom = BloomFilter.create(Funnels.stringFunnel(), 1000);
		return new Iterator<String>() {

			@Override
			public boolean hasNext() {
				if (null != those.peek()) {
					return true;
				}

				computeNextTalkabout(those, appKeys, talking, page.getAndIncrement(), bloom);
				return null != those.peek();
			}

			@Override
			public String next() {
				return those.poll();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		};
	}

	protected void computeNextTalkabout(Queue<String> those, WeiboAppKeys appKeys, WeiboTalking talking, int page, BloomFilter<CharSequence> bloom) {

		try {
			Set<String> usernames = findWhoTalkaboutInternal(appKeys, talking, page);
			for (String username : usernames) {
				if (bloom.mightContain(username)) {
					continue;
				}
				those.add(username);
				bloom.put(username);
			}
		} catch (Exception e) {
			// log and ignore
		}
	}

	@SuppressWarnings("unchecked")
	private Set<String> findWhoTalkaboutInternal(WeiboAppKeys appKeys, WeiboTalking talking, int page) {

		Map<String, Object> result = QWeiboApiRequests.newSearchTwitter(appKeys, talking, page).execute(Map.class);
		if (isNotOkResult(result)) {
			// log and just return
			return Collections.emptySet();
		}

		Map<String, Object> data = (Map<String, Object>) result.get("data");
		Map<String, String> users = (Map<String, String>) data.get("user");
		return users.keySet();
	}

}
