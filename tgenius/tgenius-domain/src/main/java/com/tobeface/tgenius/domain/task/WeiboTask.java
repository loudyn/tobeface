package com.tobeface.tgenius.domain.task;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.tobeface.modules.domain.AbstractDomainObj;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboTask extends AbstractDomainObj<Long, WeiboTask> {

	private String name;
	private long createTime;

	private List<WeiboTaskHandleEvent> handleEvents = new LinkedList<WeiboTaskHandleEvent>();
	private Queue<WeiboTaskHandleEvent> uncommitHandleEvents = new ConcurrentLinkedQueue<WeiboTaskHandleEvent>();

	WeiboTask() {}

	public WeiboTask(String name) {
		this(name, System.currentTimeMillis());
	}

	public WeiboTask(String name, long createTime) {
		this.name = name;
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public long getCreateTime() {
		return createTime;
	}

	protected void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public List<WeiboTaskHandleEvent> getHandleEvents() {
		return handleEvents;
	}

	public WeiboTaskHandleEvent getLastHandleEvent() {
		if (null == getHandleEvents()) {
			return null;
		}

		if (getHandleEvents().isEmpty()) {
			return null;
		}

		int lastIndex = getHandleEvents().size() - 1;
		return getHandleEvents().get(lastIndex);
	}

	public void setHandleEvents(List<WeiboTaskHandleEvent> handleEvents) {
		this.handleEvents = handleEvents;
	}

	public Queue<WeiboTaskHandleEvent> getUncommitHandleEvents() {
		return uncommitHandleEvents;
	}

	public WeiboTask queue() {
		applyUncommitEvents(WeiboTaskHandleEvents.newQueuedHandleEvent(this));
		return this;
	}

	private void applyUncommitEvents(WeiboTaskHandleEvent handleEvent) {
		uncommitHandleEvents.add(handleEvent);
	}

	public WeiboTask running() {
		applyUncommitEvents(WeiboTaskHandleEvents.newRunningHandleEvent(this));
		return this;
	}

	public WeiboTask finish() {
		applyUncommitEvents(WeiboTaskHandleEvents.newFinishedHandleEvent(this));
		return this;
	}

	public WeiboTask finish(Exception e) {
		applyUncommitEvents(WeiboTaskHandleEvents.newFinishedHandleEvent(this, e));
		return this;
	}

	public WeiboTask statictisFinish(int success, int fail) {
		applyUncommitEvents(WeiboTaskHandleEvents.newStatictisFinishedHandleEvent(this, success, fail));
		return this;
	}

	public WeiboTask statictisFinish(Exception e, int success, int fail) {
		applyUncommitEvents(WeiboTaskHandleEvents.newStatisticFinishedHandleEvent(this, e, success, fail));
		return this;
	}

	/**
	 * 
	 */
	public void commitHandleEvents() {
		for (;;) {
			WeiboTaskHandleEvent entity = uncommitHandleEvents.poll();
			if (null == entity) {
				break;
			}

			handleEvents.add(entity);
		}
	}

	public boolean hasUncommitHandleEvents() {
		return !uncommitHandleEvents.isEmpty();
	}

	private static final long serialVersionUID = 1L;
}
