package com.tobeface.modules.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Objects;
import com.tobeface.modules.lang.Preconditions;

/**
 * 
 * @author loudyn
 * 
 * @param <ID>
 * @param <T>
 */
public abstract class CrudControllerSupport<ID, T> extends ControllerSupport {
	/**
	 * 
	 * @param one
	 * @param another
	 */
	protected void checkIdNotModified(ID one, ID another) {
		Preconditions.isTrue(Objects.equal(one, another));
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	public abstract String create(Model model);

	/**
	 * 
	 * @param entity
	 * @param result
	 * @param attributes
	 * @return
	 */
	public abstract String create(T entity, BindingResult result, RedirectAttributes attributes);

	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public abstract String edit(ID id, Model model);

	/**
	 * 
	 * @param id
	 * @param request
	 * @param attributes
	 * @return
	 */
	public abstract String edit(ID id, HttpServletRequest request, RedirectAttributes attributes);

	/**
	 * 
	 * @param id
	 * @param attributes
	 * @return
	 */
	public abstract String delete(ID id, RedirectAttributes attributes);

	/**
	 * 
	 * @param request
	 * @param attributes
	 * @return
	 */
	public abstract String delete(HttpServletRequest request, RedirectAttributes attributes);
}
