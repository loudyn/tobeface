package com.tobeface.tgenius.infrastructure.wapi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.modules.lang.Preconditions;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboApiResponse {

	private final Map<String, Object> mapResult;

	@SuppressWarnings("unchecked")
	public WeiboApiResponse(String resp) {
		Preconditions.hasText(resp);
		this.mapResult = JsonHelper.fromJsonString(resp, Map.class);
	}

	public boolean isOK() {
		return ((Integer) mapResult.get("ret")) == 0;
	}

	public String getErrors() {
		return "errorcode[" + mapResult.get("errcode") + "]-errormsg[" + mapResult.get("msg") + "]";
	}

	public WeiboApiResponseResult getResult() {
		return new WeiboApiResponseResult(mapResult);
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	public static class WeiboApiResponseResult {
		Deque<String> actions = new ArrayDeque<String>();
		Map<String, Object> result;

		WeiboApiResponseResult(Map<String, Object> result) {
			this.result = result;
		}

		/**
		 * 
		 * @param action
		 * @return
		 */
		public WeiboApiResponseResult on(String action) {
			Preconditions.hasText(action);
			actions.offerLast(action);
			return this;
		}

		@SuppressWarnings("unchecked")
		public Object get() {
			Object objResult = result;
			for (;;) {

				String action = actions.pollFirst();
				if (null == action) {
					break;
				}

				Map<String, Object> mapResult = (Map<String, Object>) objResult;
				if (null == mapResult || !mapResult.containsKey(action)) {
					throw new UnsupportedOperationException("Bad action[" + action + "]");
				}

				objResult = mapResult.get(action);
			}

			return objResult;
		}

	}
}
