package com.tobeface.tgenius.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import com.tobeface.modules.domain.DomainObject;
import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.modules.table.annotations.TableField;

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
	@TableField(columnIndex = 0)
	private String name;
	@JsonProperty("nick")
	@TableField(columnIndex = 1)
	private String nickname;
	@JsonProperty("location")
	@TableField(columnIndex = 2)
	private String location;
	@JsonProperty("homepage")
	@TableField(columnIndex = 3)
	private String homepage;
	@JsonProperty("introduction")
	@TableField(columnIndex = 4)
	private String intro;

	@JsonProperty("industry_code")
	private String careercode;
	@TableField(columnIndex = 5)
	private String careername;

	@JsonProperty("verifyinfo")
	@TableField(columnIndex = 6)
	private String verifyInfo;

	@JsonProperty("sex")
	@TableField(columnIndex = 7)
	private int sex;
	@JsonProperty("birth_year")
	@TableField(columnIndex = 8)
	private int birthYear;
	@JsonProperty("fansnum")
	@TableField(columnIndex = 9)
	private int fansCount;
	@JsonProperty("isvip")
	@TableField(columnIndex = 10)
	private boolean isVip;
	@JsonProperty("isent")
	@TableField(columnIndex = 11)
	private boolean isEnterprise;
	@JsonProperty("isrealname")
	@TableField(columnIndex = 12)
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
