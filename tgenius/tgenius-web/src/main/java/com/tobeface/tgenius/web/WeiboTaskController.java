package com.tobeface.tgenius.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tobeface.modules.domain.Page;
import com.tobeface.tgenius.application.WeiboTaskService;
import com.tobeface.tgenius.domain.task.WeiboTask;

/**
 * 
 * @author loudyn
 * 
 */
@Controller
@RequestMapping("/weibo-task")
public class WeiboTaskController {

	@Autowired
	private WeiboTaskService weiboTaskService;

	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(Page<WeiboTask> page, Model model) {
		page = weiboTaskService.queryPage(page);
		model.addAttribute(page);
		return "weibo-task/list";
	}
}
