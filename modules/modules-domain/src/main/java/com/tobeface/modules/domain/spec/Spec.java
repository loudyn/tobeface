package com.tobeface.modules.domain.spec;

/**
 * 
 * @author loudyn
 *
 * @param <T>
 */
public interface Spec<T> {
	/**
	 * 
	 * @param entity
	 * @return
	 */
	boolean isSatisfiedBy(T entity);

	/**
	 * 
	 * @param another
	 * @return
	 */
	Spec<T> and(Spec<T> another);

	/**
	 * 
	 * @param another
	 * @return
	 */
	Spec<T> or(Spec<T> another);

	/**
	 * 
	 * @param another
	 * @return
	 */
	Spec<T> not(Spec<T> another);
}
