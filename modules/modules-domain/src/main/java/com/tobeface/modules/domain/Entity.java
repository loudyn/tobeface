package com.tobeface.modules.domain;
/**
 * 
 * @author loudyn
 *
 * @param <T>
 */
public interface Entity<T> {
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	boolean sameIdentityAs(T other);
}
