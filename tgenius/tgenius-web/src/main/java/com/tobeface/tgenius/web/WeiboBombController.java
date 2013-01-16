package com.tobeface.tgenius.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tobeface.modules.lang.Strings;
import com.tobeface.modules.web.controller.ControllerSupport;
import com.tobeface.tgenius.application.WeiboBombService;
import com.tobeface.tgenius.domain.WeiboLetter;
import com.tobeface.tgenius.domain.WeiboMention;

/**
 * 
 * @author loudyn
 * 
 */
@Controller
@RequestMapping("/weibo-bomb")
public class WeiboBombController extends ControllerSupport {

	@Autowired
	private WeiboBombService bombService;

	/**
	 * 
	 * @param letter
	 * @return
	 */
	@RequestMapping(value = "/letter/", method = RequestMethod.POST)
	@ResponseBody
	public CreateBombMessageResult fire(@RequestParam("names") String namesString, WeiboLetter letter) {

		try {

			String[] names = Strings.split(namesString, ",|;");
			letter.setUsers(names);
			bombService.fire(letter);
			return CreateBombMessageResult.create().success();
		} catch (Exception e) {
			return CreateBombMessageResult.create().error().message(e.getMessage());
		}
	}

	@RequestMapping(value = "/mention/", method = RequestMethod.POST)
	@ResponseBody
	public CreateBombMessageResult fire(@RequestParam("names") String namesString, WeiboMention mention) {
		try {

			String[] names = Strings.split(namesString, ",|;");
			mention.setNames(Arrays.asList(names));
			bombService.fire(mention);
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
