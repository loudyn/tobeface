package com.tobeface.tgenius.domain.task;

/**
 * 
 * @author loudyn
 * 
 */
final class WeiboTaskHandleEvents {

	/**
	 * 
	 * @return
	 */
	static WeiboTaskHandleEvent newQueuedHandleEvent(WeiboTask task) {
		return new WeiboTaskHandleEvent(task, "已进入任务处理队列，等待执行中", System.currentTimeMillis());
	}

	/**
	 * 
	 * @param task
	 * @return
	 */
	static WeiboTaskHandleEvent newRunningHandleEvent(WeiboTask task) {
		return new WeiboTaskHandleEvent(task, "从任务处理队列中出队，开始执行", System.currentTimeMillis());
	}

	/**
	 * 
	 * @param task
	 * @return
	 */
	static WeiboTaskHandleEvent newFinishedHandleEvent(WeiboTask task) {
		return new WeiboTaskHandleEvent(task, "任务已正常结束", System.currentTimeMillis());
	}

	/**
	 * 
	 * @param task
	 * @param success
	 * @param fail
	 * @return
	 */
	static WeiboTaskHandleEvent newStatictisFinishedHandleEvent(WeiboTask task, int success, int fail) {
		return new WeiboTaskHandleEvent(
										task, 
										String.format("任务已正常结束,成功:%s，失败：%s", success, fail), 
										System.currentTimeMillis()
									);
	}

	/**
	 * 
	 * @param task
	 * @param e
	 * @return
	 */
	static WeiboTaskHandleEvent newFinishedHandleEvent(WeiboTask task, Exception e) {
		return new WeiboTaskHandleEvent(
										task, 
										String.format("任务非正常状态下结束,错误信息:%s", e.getMessage()), 
										System.currentTimeMillis()
									);
	}

	/**
	 * 
	 * @param task
	 * @param e
	 * @return
	 */
	static WeiboTaskHandleEvent newStatisticFinishedHandleEvent(WeiboTask task, Exception e, int success, int fail) {
		return new WeiboTaskHandleEvent(
										task, 
										String.format("任务非正常状态下结束,错误信息:%s,已成功处理:%s，失败处理：%s", e.getMessage()),
										System.currentTimeMillis()
									);
	}
	
	private WeiboTaskHandleEvents() {}

}
