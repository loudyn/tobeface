package com.tobeface.tgenius.application.impl.qweibo.model;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.domain.WeiboPlatform;
import com.tobeface.tgenius.domain.WeiboUser;
import com.tobeface.tgenius.domain.WeiboUserCompany;
import com.tobeface.tgenius.domain.WeiboUserEducation;

/**
 * 
 * @author loudyn
 * 
 */
@SuppressWarnings("serial")
public class QWeiboUserAdapter extends WeiboUser {

	private final QWeiboUser qWeiboUser;

	public QWeiboUserAdapter(QWeiboUser qWeiboUser) {
		Preconditions.notNull(qWeiboUser);
		this.qWeiboUser = qWeiboUser;
	}

	@Override
	public WeiboPlatform getPlatform() {
		return WeiboPlatform.QWEIBO;
	}

	@Override
	public List<WeiboUserCompany> getCompanies() {
		List<QWeiboUserCompany> coms = Lang.nullSafe(qWeiboUser.getCompanies(), Collections.<QWeiboUserCompany> emptyList());
		return Lists.transform(coms, new Function<QWeiboUserCompany, WeiboUserCompany>() {

			@Override
			public WeiboUserCompany apply(QWeiboUserCompany input) {
				WeiboUserCompany com = new WeiboUserCompany();
				com.setBeginYear(input.getBeginYear());
				com.setDepartmentName(input.getDepartmentName());
				com.setEndYear(input.getEndYear());
				com.setName(input.getName());
				return com;
			}

		});
	}

	@Override
	public List<WeiboUserEducation> getEducations() {
		List<QWeiboUserEducation> edus = Lang.nullSafe(qWeiboUser.getEducations(), Collections.<QWeiboUserEducation> emptyList());
		return Lists.transform(edus, new Function<QWeiboUserEducation, WeiboUserEducation>() {

			@Override
			public WeiboUserEducation apply(QWeiboUserEducation input) {
				WeiboUserEducation edu = new WeiboUserEducation();
				edu.setLevel(input.getLevel());
				edu.setYear(input.getYear());
				return edu;
			}

		});
	}

	@Override
	public String getName() {
		return qWeiboUser.getName();
	}

	@Override
	public String getNickname() {
		return qWeiboUser.getNickname();
	}

	@Override
	public String getLocation() {
		return qWeiboUser.getLocation();
	}

	@Override
	public String getHomepage() {
		return qWeiboUser.getHomepage();
	}

	@Override
	public String getIntro() {
		return qWeiboUser.getIntro();
	}

	@Override
	public String getCareercode() {
		return qWeiboUser.getCareercode();
	}

	@Override
	public String getCareername() {
		return qWeiboUser.getCareername();
	}

	@Override
	public String getVerifyInfo() {
		return qWeiboUser.getVerifyInfo();
	}

	@Override
	public int getSex() {
		return qWeiboUser.getSex();
	}

	@Override
	public int getBirthYear() {
		return qWeiboUser.getBirthYear();
	}

	@Override
	public int getFansCount() {
		return qWeiboUser.getFansCount();
	}

	@Override
	public boolean isVip() {
		return qWeiboUser.isVip();
	}

	@Override
	public boolean isEnterprise() {
		return qWeiboUser.isEnterprise();
	}

	@Override
	public boolean isRealname() {
		return qWeiboUser.isRealname();
	}
}
