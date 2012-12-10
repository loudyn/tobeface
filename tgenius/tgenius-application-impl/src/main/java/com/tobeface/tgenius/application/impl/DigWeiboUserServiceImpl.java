package com.tobeface.tgenius.application.impl;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tobeface.tgenius.application.DigWeiboUserService;
import com.tobeface.tgenius.application.WeiboTaskService;
import com.tobeface.tgenius.application.WeiboUserService;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboAppKeysRepository;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.task.WeiboTask;
import com.tobeface.tgenius.domain.task.WeiboTasks;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiService;

/**
 * 
 * @author loudyn
 * 
 */
@Service
@Transactional
public class DigWeiboUserServiceImpl implements DigWeiboUserService {

	private ExecutorService executor = Executors.newFixedThreadPool(20);

	@Autowired
	private WeiboApiService weiboApiService;
	@Autowired
	private WeiboUserService weiboUserService;
	@Autowired
	private WeiboTaskService weiboTaskService;

	@Autowired
	private WeiboAppKeysRepository weiboAppKeysRepository;

	public void digByKeyword(final String keyword) {

		final WeiboTask task = WeiboTasks.newDigByKeyword(keyword);
		weiboTaskService.save(task.queue());
		executor.submit(new Runnable() {

			@Override
			public void run() {

				AtomicInteger success = new AtomicInteger();
				AtomicInteger failure = new AtomicInteger();
				try {

					weiboTaskService.update(task.running());
					WeiboAppKeys appKeys = weiboAppKeysRepository.findAnyAvaliable();
					Iterator<WeiboUser> it = weiboApiService.findWhoKeywordLikes(appKeys, keyword);
					saveOrUpdateWeiboUsers(it, success, failure);
					task.statictisFinish(success.intValue(), failure.intValue());
				} catch (Exception e) {
					task.statictisFinish(e, success.intValue(), failure.intValue());
				}

				weiboTaskService.update(task);
			} // end of run
		});
	}

	protected void saveOrUpdateWeiboUsers(Iterator<WeiboUser> it, AtomicInteger success, AtomicInteger failure) {
		while (it.hasNext()) {

			try {

				WeiboUser entity = it.next();

				if (weiboUserService.existsByName(entity.getName())) {
					weiboUserService.update(entity);
					continue;
				}

				weiboUserService.save(entity);
				success.getAndIncrement();
			} catch (Exception ignore) {
				failure.getAndIncrement();
			}
		}
	}

	public void digByTags(final String tags) {

		final WeiboTask task = WeiboTasks.newDigByTags(tags);
		weiboTaskService.save(task.queue());
		executor.submit(new Runnable() {

			@Override
			public void run() {

				AtomicInteger success = new AtomicInteger();
				AtomicInteger failure = new AtomicInteger();
				try {

					weiboTaskService.update(task.running());
					WeiboAppKeys appKeys = weiboAppKeysRepository.findAnyAvaliable();
					Iterator<WeiboUser> it = weiboApiService.findWhoTagLikes(appKeys, tags);
					saveOrUpdateWeiboUsers(it, success, failure);
					task.finish();
				} catch (Exception e) {
					task.finish(e);
				}

				weiboTaskService.update(task);
			}
		});
	}

}
