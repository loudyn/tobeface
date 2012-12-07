package com.tobeface.tgenius.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.tobeface.modules.domain.DomainObject;
import com.tobeface.modules.helper.JsonHelper;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboUser implements DomainObject<WeiboUser>, Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("comp")
	private List<WeiboUserCompany> companies = new ArrayList<WeiboUserCompany>();
	@JsonProperty("edu")
	private List<WeiboUserEducation> educations = new ArrayList<WeiboUserEducation>();

	@JsonProperty("name")
	private String name;
	@JsonProperty("nick")
	private String nickname;
	@JsonProperty("location")
	private String location;
	@JsonProperty("homepage")
	private String homepage;
	@JsonProperty("introduction")
	private String intro;

	@JsonProperty("industry_code")
	private String careercode;
	private String careername;

	@JsonProperty("verifyinfo")
	private String verifyInfo;

	@JsonProperty("sex")
	private int sex;
	@JsonProperty("birth_year")
	private int birthYear;
	@JsonProperty("fansnum")
	private int fansCount;
	@JsonProperty("isvip")
	private boolean isVip;
	@JsonProperty("isent")
	private boolean isEnterprise;
	@JsonProperty("isrealname")
	private boolean isRealname;

	public List<WeiboUserCompany> getCompanies() {
		return companies;
	}

	@JsonIgnore
	public String getCompaniesAsString() {
		return JsonHelper.toJsonString(getCompanies());
	}

	public void setCompanies(List<WeiboUserCompany> companies) {
		this.companies = companies;
	}

	public List<WeiboUserEducation> getEducations() {
		return educations;
	}

	@JsonIgnore
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

	public String getCareercode() {
		return careercode;
	}

	public void setCareercode(String careercode) {
		this.careercode = careercode;
	}

	public String getCareername() {
		return careername;
	}

	public void setCareername(String careername) {
		this.careername = careername;
	}

	public String getVerifyInfo() {
		return verifyInfo;
	}

	public void setVerifyInfo(String verifyInfo) {
		this.verifyInfo = verifyInfo;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
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
