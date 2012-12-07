package com.tobeface.tgenius.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import com.tobeface.modules.domain.ValueObject;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboUserCompany implements ValueObject<WeiboUserCompany> {
	
	@JsonProperty("begin_year")
	private int beginYear;
	@JsonProperty("end_year")
	private int endYear;
	@JsonProperty("company_name")
	private String name;
	@JsonProperty("department_name")
	private String departmentName;

	public int getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(int beginYear) {
		this.beginYear = beginYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public boolean sameValueAs(WeiboUserCompany other) {
		return new EqualsBuilder().append(getBeginYear(), other.getBeginYear())
									.append(getEndYear(), other.getEndYear())
									.append(getName(), other.getName())
									.append(getDepartmentName(), other.getDepartmentName())
									.isEquals();
	}

}
