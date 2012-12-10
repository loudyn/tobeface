package com.tobeface.tgenius.domain.task;

/**
 * 
 * @author loudyn
 *
 */
public final class WeiboTasks {

	/**
	 * 
	 * @param tags
	 * @return
	 */
	public static WeiboTask newDigByTags(String tags) {
		return new WeiboTask(String.format("通过标签查找用户任务[标签：%s]", tags));
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public static WeiboTask newDigByKeyword(String keyword) {
		return new WeiboTask(String.format("通过关键字查找用户任务[关键字：%s]", keyword));
	}

	/**
	 * 
	 * @param names
	 * @return
	 */
	public static WeiboTask newLetterByNames(String[] names) {
		return new WeiboTask("通过用户名批量发送私信任务");
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public static WeiboTask newLetterByTalkabout(String keyword) {
		return new WeiboTask(String.format("通过关键字批量发送私信[关键字：%s]", keyword));
	}

	/**
	 * 
	 * @return
	 */
	public static WeiboTask newMentionByRelay() {
		return new WeiboTask("随机转播热门微博@用户任务");
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public static WeiboTask newMentionByTalkabout(String keyword) {
		return new WeiboTask(String.format("通过关键字随机@用户任务[关键字：%s]", keyword));
	}

	private WeiboTasks() {}
}
