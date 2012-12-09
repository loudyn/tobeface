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
import com.tobeface.modules.table.annotations.TableValueConverter;
import com.tobeface.modules.table.annotations.TableValueConverters;

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
	@TableField(columnIndex = 5, columnName = "所在行业")
	private String careername;

	@JsonProperty("verifyinfo")
	@TableField(columnIndex = 6, columnName = "认证信息")
	private String verifyInfo;

	@JsonProperty("sex")
	@TableField(columnIndex = 7, columnName = "性别")
	@TableValueConverters(downstream = { @TableValueConverter(type = SexConverter.class, method = "convert") })
	private int sex;
	@JsonProperty("birth_year")
	@TableValueConverters(downstream = { @TableValueConverter(type = BirthYearConverter.class, method = "convert") })
	@TableField(columnIndex = 8, columnName = "出生年份")
	private int birthYear;
	@JsonProperty("fansnum")
	@TableField(columnIndex = 9, columnName = "粉丝数目")
	private int fansCount;
	@JsonProperty("isvip")
	@TableField(columnIndex = 10, columnName = "是否认证用户")
	@TableValueConverters(downstream = { @TableValueConverter(type = BooleanConverter.class, method = "convert") })
	private boolean isVip;
	@JsonProperty("isent")
	@TableField(columnIndex = 11, columnName = "是否企业用户")
	@TableValueConverters(downstream = { @TableValueConverter(type = BooleanConverter.class, method = "convert") })
	private boolean isEnterprise;
	@JsonProperty("isrealname")
	@TableField(columnIndex = 12, columnName = "是否实名认证")
	@TableValueConverters(downstream = { @TableValueConverter(type = BooleanConverter.class, method = "convert") })
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

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	public static class BooleanConverter {
		/**
		 * 
		 * @param value
		 * @return
		 */
		public String convert(boolean value) {
			return value ? "是" : "否";
		}
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	public static class SexConverter {
		/**
		 * 
		 * @param value
		 * @return
		 */
		public String convert(int value) {
			return (value == 1) ? "男" : (value == 2) ? "女" : "未填写";
		}
	}

	public static class BirthYearConverter {
		/**
		 * 
		 * @param value
		 * @return
		 */
		public String convert(int value) {
			return (value == 0) ? "用户未填写" : String.valueOf(value);
		}
	}
}
