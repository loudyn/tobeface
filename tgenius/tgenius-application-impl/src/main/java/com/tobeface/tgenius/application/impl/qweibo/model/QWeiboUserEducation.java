package com.tobeface.tgenius.application.impl.qweibo.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author loudyn
 * 
 */
public class QWeiboUserEducation {

	@JsonProperty("level")
	private String level;
	@JsonProperty("year")
	private int year;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
