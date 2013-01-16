package com.tobeface.tgenius.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.ValueObj;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboUserLocation implements ValueObj<WeiboUserLocation> {

	private long longitude = -1;
	private long latitude = -1;
	private int radius;

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isAvaliable() {
		return (getLongitude() > 0) && (getLatitude() > 0);
	}

	@Override
	public boolean sameValueAs(WeiboUserLocation other) {
		return new EqualsBuilder().append(getLongitude(), other.getLongitude())
									.append(getLatitude(), other.getLatitude())
									.append(getRadius(), other.getRadius()).isEquals();
	}

}
