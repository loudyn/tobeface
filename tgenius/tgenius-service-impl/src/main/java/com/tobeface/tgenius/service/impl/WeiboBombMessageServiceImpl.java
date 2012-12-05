package com.tobeface.tgenius.service.impl;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobeface.modules.lang.Each;
import com.tobeface.modules.lang.Lang;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboAppKeysRepository;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiService;
import com.tobeface.tgenius.service.WeiboBombMessageService;

/**
 * 
 * @author loudyn
 * 
 */
@Service
public class WeiboBombMessageServiceImpl implements WeiboBombMessageService {

	private ExecutorService executor = Executors.newFixedThreadPool(20);
	
	@Autowired
	private WeiboAppKeysRepository appKeysRepository;
	@Autowired
	private WeiboApiService weiboApiService;

	public void publishLetterByNames(final String[] names, final WeiboLetter letter) {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				final WeiboAppKeys appKeys = appKeysRepository.findAnyAvaliable();
				Lang.each(names, new Each<String>() {

					public void invoke(int index, String which) {
						weiboApiService.sendLetter(appKeys, which, letter);
					}
				});
			}
		});
	}

	@Override
	public void publishLetterByTalkabout(final WeiboTalking talking, final WeiboLetter letter) {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				final WeiboAppKeys appKeys = appKeysRepository.findAnyAvaliable();
				Iterator<String> names = weiboApiService.findWhoTalkabout(appKeys, talking);
				Lang.each(names, new Each<String>() {

					public void invoke(int index, String which) {
						weiboApiService.sendLetter(appKeys, which, letter);
					}
				});
			}
		});
	}

	@Override
	public void publishMentionByRelay(final WeiboMention mention) {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				final WeiboAppKeys appKeys = appKeysRepository.findAnyAvaliable();
				weiboApiService.sendMentionByRelay(appKeys, mention);
			}
		});
	}

	@Override
	public void publishMetionByTalkabout(final WeiboTalking talking, final WeiboMention mention) {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				final WeiboAppKeys appKeys = appKeysRepository.findAnyAvaliable();
				weiboApiService.sendMentionByTalking(appKeys, talking, mention);
			}
		});
	}
}
