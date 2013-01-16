package com.tobeface.tgenius.application.impl.qweibo.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author loudyn
 * 
 */
public class QWeiboUserCompany {

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

}
