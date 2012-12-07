package com.tobeface.tgenius.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tobeface.modules.domain.Page;
import com.tobeface.modules.lang.Each;
import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.lang.file.WriteFileToCommand;
import com.tobeface.modules.table.Table;
import com.tobeface.modules.table.Tables;
import com.tobeface.modules.web.Webs;
import com.tobeface.modules.web.Webs.ContentType;
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

	private static final String REDIRECT_LIST = "redirect:/weibo-user/list/";

	@Autowired
	private WeiboUserService weiboUserService;

	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(Page<WeiboUser> page, Model model) {
		page = weiboUserService.queryPage(page);
		model.addAttribute(model);
		return listView();
	}

	@RequestMapping(value = "/dump-csv/", method = RequestMethod.POST)
	public void dumpAsCsv(HttpServletResponse resp) {
		try {

			Webs.prepareDownload(resp, "weibo-user.csv", ContentType.EXCEL);

			List<WeiboUser> entities = weiboUserService.query(new Object());
			File temp = File.createTempFile("dump", ".csv");
			Table csv = Tables.newCsv(temp);
			csv.insert(entities);

			new WriteFileToCommand(temp, resp.getOutputStream(), false).execute();
		} catch (IOException e) {
			getLogger().error(e.getMessage());
		}

	}

	@RequestMapping(value = "/{name}/view/", method = RequestMethod.GET)
	public String view(@PathVariable("name") String name, Model model) {
		WeiboUser entity = weiboUserService.queryUniqueByName(name);
		model.addAttribute(entity);
		return getViewPackage().concat("/view");
	}

	@RequestMapping(value = "/delete/", method = RequestMethod.DELETE)
	public String delete(HttpServletRequest request) {
		Lang.each(Lang.nullSafe(request.getParameterValues("items"), new String[] {}), new Each<String>() {

			@Override
			public void invoke(int index, String which) {
				weiboUserService.deleteByName(which);
			}
		});
		return REDIRECT_LIST;
	}

	@Override
	protected String getViewPackage() {
		return "weibo-user";
	}
}
