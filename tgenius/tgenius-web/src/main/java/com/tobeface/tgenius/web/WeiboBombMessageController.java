package com.tobeface.tgenius.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tobeface.modules.lang.Strings;
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
@RequestMapping("/weibo-bomb-msg")
public class WeiboBombMessageController extends ControllerSupport {

	@Autowired
	private WeiboBombMessageService bombMessageService;

	/**
	 * 
	 * @param letter
	 * @return
	 */
	@RequestMapping(value = "/letter-by-names/", method = RequestMethod.POST)
	@ResponseBody
	public CreateBombMessageResult publishLetterByNames(@RequestParam("names") String namesString, WeiboLetter letter) {

		try {

			bombMessageService.publishLetterByNames(Strings.split(namesString, ";"), letter);
			return CreateBombMessageResult.create().success();
		} catch (Exception e) {
			return CreateBombMessageResult.create().error().message(e.getMessage());
		}
	}

	/**
	 * 
	 * @param talking
	 * @param letter
	 * @return
	 */
	@RequestMapping(value = "/letter-by-talkabout/", method = RequestMethod.POST)
	@ResponseBody
	public CreateBombMessageResult publishLetterByTalkabout(WeiboTalking talking, WeiboLetter letter) {
		try {

			bombMessageService.publishLetterByTalkabout(talking, letter);
			return CreateBombMessageResult.create().success();
		} catch (Exception e) {
			return CreateBombMessageResult.create().error().message(e.getMessage());
		}
	}

	@RequestMapping(value = "/mention-by-relay/", method = RequestMethod.POST)
	@ResponseBody
	public CreateBombMessageResult publishMentionByRelay(WeiboMention mention) {
		try {

			bombMessageService.publishMentionByRelay(mention);
			return CreateBombMessageResult.create().success();
		} catch (Exception e) {
			return CreateBombMessageResult.create().error().message(e.getMessage());
		}
	}

	@RequestMapping(value = "/mention-by-talkabout/", method = RequestMethod.POST)
	@ResponseBody
	public CreateBombMessageResult publishMentionByTalkAbout(WeiboTalking talking, WeiboMention mention) {
		try {

			bombMessageService.publishMetionByTalkabout(talking, mention);
			return CreateBombMessageResult.create().success();
		} catch (Exception e) {
			return CreateBombMessageResult.create().error().message(e.getMessage());
		}
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	static class CreateBombMessageResult {
		String result;
		String message;

		public String getResult() {
			return result;
		}

		public CreateBombMessageResult success() {
			return result("success");
		}

		public CreateBombMessageResult error() {
			return result("error");
		}

		public CreateBombMessageResult result(String result) {
			this.result = result;
			return this;
		}

		public String getMessage() {
			return message;
		}

		public CreateBombMessageResult message(String message) {
			this.message = message;
			return this;
		}

		public static CreateBombMessageResult create() {
			return new CreateBombMessageResult();
		}

	}

}
