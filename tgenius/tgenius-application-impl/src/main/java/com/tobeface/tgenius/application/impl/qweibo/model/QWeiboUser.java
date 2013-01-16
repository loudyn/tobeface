package com.tobeface.tgenius.application.impl.qweibo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.modules.table.annotation.TableField;

/**
 * 
 * @author loudyn
 * 
 */
public class QWeiboUser implements Serializable {


	@JsonProperty("comp")
	private List<QWeiboUserCompany> companies = new ArrayList<QWeiboUserCompany>();
	@JsonProperty("edu")
	private List<QWeiboUserEducation> educations = new ArrayList<QWeiboUserEducation>();

	@JsonProperty("name")
	@TableField(columnIndex = 0, columnName = "用户名")
	private String name;
	@JsonProperty("nick")
	@TableField(columnIndex = 1, columnName = "昵称")
	private String nickname;
	@JsonProperty("location")
	@TableField(columnIndex = 2, columnName = "所在地")
	private String location;
	@JsonProperty("homepage")
	@TableField(columnIndex = 3, columnName = "个人主页")
	private String homepage;
	@JsonProperty("introduction")
	@TableField(columnIndex = 4, columnName = "个人简介")
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

	public List<QWeiboUserCompany> getCompanies() {
		return companies;
	}

	@JsonIgnore
	public String getCompaniesAsString() {
		return JsonHelper.toJsonString(getCompanies());
	}

	public void setCompanies(List<QWeiboUserCompany> companies) {
		this.companies = companies;
	}

	public List<QWeiboUserEducation> getEducations() {
		return educations;
	}

	@JsonIgnore
	public String getEducationsAsString() {
		return JsonHelper.toJsonString(getEducations());
	}

	public void setEducations(List<QWeiboUserEducation> educations) {
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

	private static final long serialVersionUID = 1L;
}
