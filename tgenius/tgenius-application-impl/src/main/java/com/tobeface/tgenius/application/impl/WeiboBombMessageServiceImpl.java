package com.tobeface.tgenius.application.impl;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tobeface.modules.lang.Each;
import com.tobeface.modules.lang.Lang;
import com.tobeface.tgenius.application.WeiboBombMessageService;
import com.tobeface.tgenius.application.WeiboTaskService;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboAppKeysRepository;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;
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
public class WeiboBombMessageServiceImpl implements WeiboBombMessageService {

	private ExecutorService executor = Executors.newFixedThreadPool(50);

	@Autowired
	private WeiboAppKeysRepository appKeysRepository;
	@Autowired
	private WeiboApiService weiboApiService;
	@Autowired
	private WeiboTaskService weiboTaskService;

	public void publishLetterByNames(final String[] names, final WeiboLetter letter) {

		final WeiboTask task = WeiboTasks.newLetterByNames(names);
		weiboTaskService.save(task.queue());

		executor.submit(new Runnable() {

			@Override
			public void run() {

				final AtomicInteger success = new AtomicInteger();
				final AtomicInteger fail = new AtomicInteger();
				try {

					weiboTaskService.update(task.running());

					final WeiboAppKeys appKeys = appKeysRepository.findAnyAvaliable();
					Lang.each(names, new Each<String>() {

						public void invoke(int index, String which) {
							try {

								weiboApiService.sendLetter(appKeys, which, letter);
								success.getAndIncrement();
							} catch (Exception e) {
								fail.getAndIncrement();
							}
						}
					});

					task.statictisFinish(success.intValue(), fail.intValue());
				} catch (Exception e) {
					task.statictisFinish(e, success.intValue(), fail.intValue());
				}

				weiboTaskService.update(task);
			}
		});
	}

	@Override
	public void publishLetterByTalkabout(final WeiboTalking talking, final WeiboLetter letter) {

		final WeiboTask task = WeiboTasks.newLetterByTalkabout(talking.getKeyword());
		weiboTaskService.save(task.queue());

		executor.submit(new Runnable() {

			@Override
			public void run() {

				final AtomicInteger success = new AtomicInteger();
				final AtomicInteger failure = new AtomicInteger();

				try {

					weiboTaskService.update(task.running());

					final WeiboAppKeys appKeys = appKeysRepository.findAnyAvaliable();
					Iterator<String> names = weiboApiService.findWhoTalkabout(appKeys, talking);
					Lang.each(names, new Each<String>() {

						public void invoke(int index, String which) {
							try {

								weiboApiService.sendLetter(appKeys, which, letter);
								success.incrementAndGet();
							} catch (Exception e) {
								failure.getAndIncrement();
							}
						}
					});

					task.statictisFinish(success.intValue(), failure.intValue());
				} catch (Exception e) {
					task.statictisFinish(e, success.intValue(), failure.intValue());
				}

				weiboTaskService.update(task);
			}
		});
	}

	@Override
	public void publishMentionByRelay(final WeiboMention mention) {

		final WeiboTask task = WeiboTasks.newMentionByRelay();
		weiboTaskService.save(task.queue());

		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {

					weiboTaskService.update(task.running());
					final WeiboAppKeys appKeys = appKeysRepository.findAnyAvaliable();
					weiboApiService.sendMentionByRelay(appKeys, mention);
					task.finish();
				} catch (Exception e) {
					task.finish(e);
				}

				weiboTaskService.update(task);
			}
		});
	}

	@Override
	public void publishMetionByTalkabout(final WeiboTalking talking, final WeiboMention mention) {

		final WeiboTask task = WeiboTasks.newMentionByTalkabout(talking.getKeyword());
		weiboTaskService.save(task.queue());

		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {

					weiboTaskService.update(task.running());
					final WeiboAppKeys appKeys = appKeysRepository.findAnyAvaliable();
					weiboApiService.sendMentionByTalking(appKeys, talking, mention);
					task.finish();
				} catch (Exception e) {
					task.finish(e);
				}

				weiboTaskService.update(task);
			}
		});
	}
}
