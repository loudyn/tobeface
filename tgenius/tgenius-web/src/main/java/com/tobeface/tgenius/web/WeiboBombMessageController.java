package com.tobeface.tgenius.web;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.tobeface.modules.lang.Files;
import com.tobeface.modules.lang.Strings;
import com.tobeface.modules.lang.file.FileCommandInvoker;
import com.tobeface.modules.lang.file.WriteBytesToFileCommand;
import com.tobeface.modules.web.controller.ControllerSupport;
import com.tobeface.tgenius.application.WeiboBombMessageService;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;
import com.tobeface.tgenius.domain.WeiboTalking;

/**
 * 
 * @author loudyn
 * 
 */
@Controller
@RequestMapping("/weibo-bomb")
public class WeiboBombMessageController extends ControllerSupport {

	@Autowired
	private WeiboBombMessageService bombMessageService;

	/**
	 * 
	 * @param letter
	 * @return
	 */
	@RequestMapping(value = "/letter-by-names/", method = RequestMethod.POST)
	public String publishLetterByNames(String namesString, WeiboLetter letter) {
		bombMessageService.publishLetterByNames(Strings.split(namesString, ";"), letter);
		return null;
	}

	/**
	 * 
	 * @param file
	 * @param letter
	 * @return
	 */
	@RequestMapping(value = "/letter-by-names-file/", method = RequestMethod.POST)
	public String publishLetterByNamesFile(MultipartFile file, String fileEncoding, WeiboLetter letter) {

		try {

			File temp = File.createTempFile("letter-files", Files.suffix(file.getOriginalFilename()));
			new FileCommandInvoker().command(new WriteBytesToFileCommand(temp, file.getBytes())).invoke();
			List<String> names = com.google.common.io.Files.readLines(temp, Charset.forName(fileEncoding));
			bombMessageService.publishLetterByNames(names.toArray(new String[names.size()]), letter);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 
	 * @param talking
	 * @param letter
	 * @return
	 */
	@RequestMapping(value = "/letter-by-talkabout/", method = RequestMethod.POST)
	public String publishByTalkabout(WeiboTalking talking, WeiboLetter letter) {
		bombMessageService.publishLetterByTalkabout(talking, letter);
		return null;
	}

	@RequestMapping(value = "/mention-by-relay/", method = RequestMethod.POST)
	public String publishMentionByRelay(WeiboMention mention) {
		bombMessageService.publishMentionByRelay(mention);
		return null;
	}

	@RequestMapping(value = "/mention-by-talkabout/", method = RequestMethod.POST)
	public String publishMentionByTalkAbout(WeiboTalking talking, WeiboMention mention) {
		bombMessageService.publishMetionByTalkabout(talking, mention);
		return null;
	}

}
