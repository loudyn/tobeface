package com.tobeface.tgenius.domain.task;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboTaskHandleEvent {

	private WeiboTask task;
	private String message;
	private long createTime;

	WeiboTaskHandleEvent() {}

	public WeiboTaskHandleEvent(WeiboTask task, String message, long createTime) {
		this.task = task;
		this.message = message;
		this.createTime = createTime;
	}

	public WeiboTask getTask() {
		return task;
	}

	protected void setTask(WeiboTask task) {
		this.task = task;
	}

	public String getMessage() {
		return message;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

	public long getCreateTime() {
		return createTime;
	}

	protected void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

}
