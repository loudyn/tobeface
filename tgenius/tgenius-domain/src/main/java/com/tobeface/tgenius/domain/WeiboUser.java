package com.tobeface.tgenius.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.DomainObject;
import com.tobeface.modules.helper.JsonHelper;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboUser implements DomainObject<WeiboUser>, Serializable {

	private static final long serialVersionUID = 1L;

	private List<WeiboUserCompany> companies = new ArrayList<WeiboUserCompany>();
	private List<WeiboUserEducation> educations = new ArrayList<WeiboUserEducation>();

	private String name;
	private String nickname;
	private String location;
	private String homepage;
	private String intro;

	private String statecode;
	private String citycode;
	private String regioncode;

	private int birthYear;
	private int fansCount;
	private boolean isVip;
	private boolean isEnterprise;
	private boolean isRealname;

	public List<WeiboUserCompany> getCompanies() {
		return companies;
	}

	public String getCompaniesAsString() {
		return JsonHelper.toJsonString(getCompanies());
	}

	public void setCompanies(List<WeiboUserCompany> companies) {
		this.companies = companies;
	}

	public List<WeiboUserEducation> getEducations() {
		return educations;
	}

	public String getEducationsAsString() {
		return JsonHelper.toJsonString(getEducations());
	}

	public void setEducations(List<WeiboUserEducation> educations) {
		this.educations = educations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	public boolean isVip() {
		return isVip;
	}

	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}

	public boolean isEnterprise() {
		return isEnterprise;
	}

	public void setEnterprise(boolean isEnterprise) {
		this.isEnterprise = isEnterprise;
	}

	public boolean isRealname() {
		return isRealname;
	}

	public void setRealname(boolean isRealname) {
		this.isRealname = isRealname;
	}
	
	@Override
	public boolean sameIdentityAs(WeiboUser other) {
		return new EqualsBuilder().append(getName(), other.getName()).isEquals();
	}

}
