package com.tobeface.tgenius.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tobeface.modules.domain.Page;
import com.tobeface.modules.lang.Each;
import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.web.controller.CrudControllerSupport;
import com.tobeface.tgenius.application.WeiboAppKeysService;
import com.tobeface.tgenius.domain.WeiboAppKeys;

/**
 * 
 * @author loudyn
 * 
 */
@Controller
@RequestMapping("/weibo-app-keys")
public class WeiboAppKeysController extends CrudControllerSupport<String, WeiboAppKeys> {

	private static final String REDIRECT_LIST = "redirect:/weibo-app-keys/list/";

	@Autowired
	private WeiboAppKeysService appKeysService;

	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(Page<WeiboAppKeys> page, Model model) {
		page = appKeysService.queryPage(page);
		model.addAttribute(page);
		return listView();
	}

	@Override
	@RequestMapping(value = "/create/", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute(new WeiboAppKeys()).addAttribute("_method", "POST");
		return formView();
	}

	@Override
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
	public String create(WeiboAppKeys entity, BindingResult result) {
		if (result.hasErrors()) {

		}
		appKeysService.save(entity);
		return REDIRECT_LIST;
	}

	@Override
	@RequestMapping(value = "/{id}/edit/", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model) {
		WeiboAppKeys entity = appKeysService.get(id);
		model.addAttribute(entity).addAttribute("_method", "PUT");
		return formView();
	}

	@Override
	@RequestMapping(value = "/{apiKey}/edit/", method = RequestMethod.PUT)
	public String edit(@PathVariable("apiKey") String apiKey, HttpServletRequest request) {
		try {

			WeiboAppKeys entity = appKeysService.get(apiKey);
			bind(request, entity);
			checkIdNotModified(apiKey, entity.getApiKey());
			appKeysService.update(entity);
		} catch (Exception e) {
			getLogger().error("Update WeiboAppKeys occur error", e);
		}

		return REDIRECT_LIST;
	}

	@Override
	@RequestMapping(value = "/{id}/delete/", method = RequestMethod.DELETE)
	public String delete(String id) {
		appKeysService.delete(id);
		return REDIRECT_LIST;
	}

	@Override
	@RequestMapping(value = "/delete/", method = RequestMethod.DELETE)
	public String delete(HttpServletRequest request) {
		Lang.each(Lang.nullSafe(request.getParameterValues("items"), new String[] {}), new Each<String>() {

			@Override
			public void invoke(int index, String which) {
				delete(which);
			}
		});
		return REDIRECT_LIST;
	}

	@Override
	protected String getViewPackage() {
		return "weibo-app-keys";
	}

}
