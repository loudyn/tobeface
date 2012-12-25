package com.tobeface.modules.event.eventbus.support;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import com.tobeface.modules.event.Event;
import com.tobeface.modules.event.EventListener;
import com.tobeface.modules.event.eventbus.EventBus;
import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.lang.Preconditions;

/**
 * 
 * @author loudyn
 * 
 */
public class JDKEventBus implements EventBus {
	private final List<EventListener> listeners = new CopyOnWriteArrayList<EventListener>();

	private final ThreadLocal<ConcurrentLinkedQueue<EventWithListener>> eventsToDispatch = new ThreadLocal<ConcurrentLinkedQueue<EventWithListener>>() {

		@Override
		protected ConcurrentLinkedQueue<EventWithListener> initialValue() {
			return new ConcurrentLinkedQueue<EventWithListener>();
		}

	};

	private final ThreadLocal<Boolean> isDispatching = new ThreadLocal<Boolean>() {

		@Override
		protected Boolean initialValue() {
			return false;
		}

	};

	@Override
	public void subscibe(EventListener listener) {
		Preconditions.notNull(listener);
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	@Override
	public void unsubscibe(EventListener listener) {
		Preconditions.notNull(listener);
		listeners.remove(listener);
	}

	@Override
	public void publish(Event event) {
		Preconditions.notNull(event);

		for (EventListener listener : listeners) {
			enqueueEvent(event, listener);
		}

		dispatchQueuedEvents();
	}

	protected void enqueueEvent(Event event, EventListener listener) {
		eventsToDispatch.get().offer(new EventWithListener(event, listener));
	}

	protected void dispatchQueuedEvents() {

		if (isDispatching.get()) {
			return;
		}

		isDispatching.set(true);

		try {

			List<Throwable> throwables = new LinkedList<Throwable>();
			for (;;) {
				EventWithListener eventWithListener = eventsToDispatch.get().poll();
				if (null == eventWithListener) {
					break;
				}

				dispatch(eventWithListener.event, eventWithListener.listener, throwables);
			}

			if (!throwables.isEmpty()) {
				throw Lang.comboThrow(throwables);
			}
		} finally {
			isDispatching.set(false);
		}
	}

	private void dispatch(Event event, EventListener listener, List<Throwable> throwables) {
		try {

			listener.onApplicationEvent(event);
		} catch (Exception e) {
			throwables.add(e);
		}
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	class EventWithListener {
		Event event;
		EventListener listener;

		EventWithListener(Event event, EventListener listener) {
			this.event = event;
			this.listener = listener;
		}
	}

}
