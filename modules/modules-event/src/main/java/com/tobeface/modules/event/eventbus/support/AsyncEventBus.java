package com.tobeface.modules.event.eventbus.support;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

import com.tobeface.modules.event.Event;
import com.tobeface.modules.event.EventListener;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.modules.lang.annotation.Beta;

/**
 * 
 * @author loudyn
 * 
 */
@Beta
public class AsyncEventBus extends SyncEventBus {

	private final Executor executor;
	private final ConcurrentLinkedQueue<EventWithListener> eventsToDispatch = new ConcurrentLinkedQueue<EventWithListener>();

	/**
	 * 
	 * @param executor
	 */
	public AsyncEventBus(Executor executor) {
		Preconditions.notNull(executor);
		this.executor = executor;
	}

	@Override
	protected void enqueueEvent(Event event, EventListener listener) {
		eventsToDispatch.offer(new EventWithListener(event, listener));
	}

	@Override
	protected void dispatchQueuedEvents() {
		for (;;) {
			EventWithListener eventWithListener = eventsToDispatch.poll();
			if (null == eventWithListener) {
				break;
			}

			asyncDispatch(eventWithListener.event, eventWithListener.listener);
		}
	}

	private void asyncDispatch(final Event event, final EventListener listener) {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				listener.onEvent(event);
			}
		});
	}

}