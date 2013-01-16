package com.tobeface.tgenius.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tobeface.modules.domain.Page;
import com.tobeface.modules.helper.CodeHelper;
import com.tobeface.modules.lang.Each;
import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.lang.Strings;
import com.tobeface.modules.lang.file.WriteFileToCommand;
import com.tobeface.modules.table.Table;
import com.tobeface.modules.table.Tables;
import com.tobeface.modules.web.Webs;
import com.tobeface.modules.web.Webs.ContentType;
import com.tobeface.modules.web.controller.ControllerSupport;
import com.tobeface.tgenius.application.WeiboAppKeyService;
import com.tobeface.tgenius.application.WeiboUserDigService;
import com.tobeface.tgenius.application.WeiboUserService;
import com.tobeface.tgenius.domain.WeiboPlatform;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;

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

	@Autowired
	private WeiboUserDigService weiboUserDigService;

	@Autowired
	private WeiboAppKeyService weiboAppKeysService;

	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(Page<WeiboUser> page, Model model) {
		page = weiboUserService.queryPage(page);
		model.addAttribute(model);
		return listView();
	}

	@RequestMapping(value = "/dump/", method = RequestMethod.GET)
	public String dump() {
		return getViewPackage().concat("/dump");
	}

	@RequestMapping(value = "/dump-xls/", method = RequestMethod.POST)
	public void dumpXls(HttpServletResponse resp, @RequestParam Map<String, String> params) {
		try {

			Webs.prepareDownload(resp, "weibo-user.xls", ContentType.EXCEL);

			List<WeiboUser> entities = weiboUserService.query(params);
			File temp = File.createTempFile("dump", ".xls");
			String templatename = CodeHelper.urlDecode(
														getClass().getResource("/META-INF/xls/template.xls").getFile(),
														"UTF-8"
												);

			File template = new File(templatename);
			Table xls = Tables.newXls(temp, template);
			xls.insert(entities);

			new WriteFileToCommand(temp, resp.getOutputStream(), false).execute();
		} catch (IOException e) {
			getLogger().error(e.getMessage());
		}

	}

	@RequestMapping(value = "/delete/", method = RequestMethod.DELETE)
	public String delete(HttpServletRequest request) {
		Lang.each(Lang.nullSafe(request.getParameterValues("items"), new String[] {}), new Each<String>() {

			@Override
			public void invoke(int index, String which) {
				String[] platformAndName = Strings.split(which, ",");
				
				WeiboPlatform platform = WeiboPlatform.valueOf(platformAndName[0]);
				String name = platformAndName[1];
				weiboUserService.deleteByPlatformAndName(platform, name);
			}
		});

		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/dig/", method = RequestMethod.GET)
	public String dig() {
		return getViewPackage().concat("/dig");
	}

	@RequestMapping(value = "/dig-by-talking/", method = RequestMethod.POST)
	@ResponseBody
	public DigWeiboUserResult digByTalking(WeiboTalking talking) {
		try {

			List<WeiboAppKey> appKeys = weiboAppKeysService.queryByPlatform(talking.getPlatform());
			weiboUserDigService.findUserByTalking(appKeys, talking);
			return DigWeiboUserResult.create().success();
		} catch (Exception e) {
			return DigWeiboUserResult.create().error().message(e.getMessage());
		}
	}

	@RequestMapping(value = "/dig-by-keyword/", method = RequestMethod.POST)
	@ResponseBody
	public DigWeiboUserResult digByKeyword(@RequestParam("platform") String platform, @RequestParam("keyword") String keyword) {
		try {

			List<WeiboAppKey> appKeys = weiboAppKeysService.queryByPlatform(WeiboPlatform.valueOf(platform));
			weiboUserDigService.findUserByKeyword(appKeys, keyword);
			return DigWeiboUserResult.create().success();
		} catch (Exception e) {
			return DigWeiboUserResult.create().error().message(e.getMessage());
		}
	}

	@RequestMapping(value = "/dig-by-tags/", method = RequestMethod.POST)
	@ResponseBody
	public DigWeiboUserResult digByTags(@RequestParam("platform") String platform, @RequestParam("tags") String tags) {
		try {

			List<WeiboAppKey> appKeys = weiboAppKeysService.queryByPlatform(WeiboPlatform.valueOf(platform));
			weiboUserDigService.findUserByTags(appKeys, tags);
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

	@Override
	protected String getViewPackage() {
		return "weibo-user";
	}
}
