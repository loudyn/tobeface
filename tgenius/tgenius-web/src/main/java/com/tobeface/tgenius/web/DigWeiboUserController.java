package com.tobeface.tgenius.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@RequestMapping(value = "/by-location/", method = RequestMethod.POST)
	public String digByLocation(String keyword) {
		digWeiboUserService.digByKeyword(keyword);
		return null;
	}

	/**
	 * 
	 * @param tags
	 * @return
	 */
	@RequestMapping(value = "/by-tags/", method = RequestMethod.POST)
	public String digByTags(String tags) {
		digWeiboUserService.digByTags(tags);
		return null;
	}
}
