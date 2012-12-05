package com.tobeface.tgenius.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tobeface.modules.domain.Page;
import com.tobeface.modules.web.controller.ControllerSupport;
import com.tobeface.tgenius.application.WeiboUserService;
import com.tobeface.tgenius.domain.WeiboUser;

/**
 * 
 * @author loudyn
 * 
 */
@Controller
@RequestMapping("/weibo-user")
public class WeiboUserController extends ControllerSupport {
	
	@Autowired
	private WeiboUserService weiboUserService;

	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(Page<WeiboUser> page) {
		page = weiboUserService.queryPage(page);
		return listView();
	}

	@Override
	protected String getViewPackage() {
		return "weibo-user";
	}
}
