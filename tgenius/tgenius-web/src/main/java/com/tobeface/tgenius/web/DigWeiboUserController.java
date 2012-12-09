package com.tobeface.tgenius.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tobeface.modules.web.controller.ControllerSupport;
import com.tobeface.tgenius.application.DigWeiboUserService;

/**
 * 
 * @author loudyn
 * 
 */
@Controller
@RequestMapping("/dig-weibo-user")
public class DigWeiboUserController extends ControllerSupport {

	@Autowired
	private DigWeiboUserService digWeiboUserService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/by-keyword/", method = RequestMethod.POST)
	@ResponseBody
	public DigWeiboUserResult digByKeyword(@RequestParam("keyword") String keyword) {
		try {

			digWeiboUserService.digByKeyword(keyword);
			return DigWeiboUserResult.create().success();
		} catch (Exception e) {
			return DigWeiboUserResult.create().error().message(e.getMessage());
		}
	}

	/**
	 * 
	 * @param tags
	 * @return
	 */
	@RequestMapping(value = "/by-tags/", method = RequestMethod.POST)
	@ResponseBody
	public DigWeiboUserResult digByTags(@RequestParam("tags") String tags) {
		try {

			digWeiboUserService.digByTags(tags);
			return DigWeiboUserResult.create().success();
		} catch (Exception e) {
			return DigWeiboUserResult.create().error().message(e.getMessage());
		}
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	static class DigWeiboUserResult {
		String result;
		String message;

		public String getResult() {
			return result;
		}

		public DigWeiboUserResult success() {
			return result("success");
		}

		public DigWeiboUserResult error() {
			return result("error");
		}

		public DigWeiboUserResult result(String result) {
			this.result = result;
			return this;
		}

		public String getMessage() {
			return message;
		}

		public DigWeiboUserResult message(String message) {
			this.message = message;
			return this;
		}

		public static DigWeiboUserResult create() {
			return new DigWeiboUserResult();
		}

	}

}
