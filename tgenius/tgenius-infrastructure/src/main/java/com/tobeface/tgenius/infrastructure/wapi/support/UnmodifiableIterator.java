package com.tobeface.tgenius.infrastructure.wapi.support;

import java.util.Iterator;

/**
 * 
 * @author loudyn
 *
 * @param <T>
 */
abstract class UnmodifiableIterator<T> implements Iterator<T> {

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
