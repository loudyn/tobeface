package com.tobeface.tgenius.infrastructure.wapi.support;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.tobeface.modules.lang.annotation.NotThreadSafe;

/**
 * 
 * @author loudyn
 * 
 * @param <T>
 */
@NotThreadSafe
public abstract class BulkLazyWeiboApiRequestIterator<T> extends UnmodifiableIterator<T> {

	private final Queue<T> queue = new LinkedList<T>();

	@Override
	public boolean hasNext() {
		if (null != queue.peek()) {
			return true;
		}

		List<T> next = computeNextBulk();
		enqueue(next);
		return null != queue.peek();
	}

	private void enqueue(List<T> next) {
		for (T obj : next) {
			queue.offer(obj);
		}
	}

	protected abstract List<T> computeNextBulk();

	@Override
	public T next() {
		return queue.poll();
	}
}
