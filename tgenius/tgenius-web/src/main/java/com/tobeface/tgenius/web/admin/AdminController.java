package com.tobeface.tgenius.web.admin;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tobeface.modules.web.controller.ControllerSupport;

/**
 * 
 * @author loudyn
 * 
 */
@Controller
public class AdminController extends ControllerSupport {

	public static final int UNKNOWN_ACCOUT_ERROR_CODE = 1;
	public static final int LOCKED_ACCOUT_ERROR_CODE = 2;
	public static final int AUTHENTICATION_ERROR_CODE = 4;
	public static final int INVALID_CAPTCHA_ERROR_CODE = 8;
	public static final int OTHER_ERROR_CODE = 16;

	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public String login(HttpServletRequest req) {

		try {

			getLogger().warn("coming a new login request.");

			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()) {
				subject.logout();
			}

			AuthenticationToken token = createAuthenticationToken(req);
			subject.login(token);
			return "redirect:/admin/dashboard/";
		} catch (Exception e) {
			getLogger().warn("login fail.", e);
			return "redirect:/login/?code=" + translateLoginException(e);
		}

	}

	private AuthenticationToken createAuthenticationToken(HttpServletRequest req) {

		String username = WebUtils.getCleanParam(req, "username");
		String password = WebUtils.getCleanParam(req, "password");

		String rememberMeAsString = WebUtils.getCleanParam(req, "rememberMe");
		boolean rememberMe = false;
		if (null != rememberMeAsString) {
			rememberMe = Boolean.valueOf(rememberMeAsString);
		}
		String host = req.getRemoteHost();
		return new UsernamePasswordToken(username, password, rememberMe, host);
	}

	private int translateLoginException(Exception e) {

		if (e instanceof UnknownAccountException) {
			return UNKNOWN_ACCOUT_ERROR_CODE;
		}

		if (e instanceof LockedAccountException) {
			return LOCKED_ACCOUT_ERROR_CODE;
		}

		if (e instanceof AuthenticationException) {
			return AUTHENTICATION_ERROR_CODE;
		}

		return OTHER_ERROR_CODE;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/logout/", method = { GET, POST })
	public String logout() {

		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login/";
	}

	@RequestMapping(value = "/admin/header/", method = RequestMethod.GET)
	public String header() {
		return "admin/header";
	}

	@RequestMapping(value = "/admin/footer/", method = RequestMethod.GET)
	public String footer() {
		return "admin/footer";
	}

	@RequestMapping(value = "/admin/dashboard/", method = RequestMethod.GET)
	public String dashboard() {
		return "admin/dashboard";
	}

	@RequestMapping(value = "/admin/app-info/", method = RequestMethod.GET)
	public String appInfo(Model model) {
		Properties props = System.getProperties();
		model.addAttribute("props", props);
		return "admin/app-info";
	}

	@RequestMapping(value = "/admin/data/", method = RequestMethod.GET)
	public String data() {
		return "admin/data";
	}

	@RequestMapping(value = "/admin/dig-data/", method = RequestMethod.GET)
	public String digData() {
		return "admin/dig-data";
	}

	@RequestMapping(value = "/admin/msg/", method = RequestMethod.GET)
	public String msg() {
		return "admin/msg";
	}
}
