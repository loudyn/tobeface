<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<%@include file="/WEB-INF/commons/common-header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
	var urlMapping = {
						"letterByNames" : "${ctx}/weibo-bomb-msg/letter-by-names/",
						"letterByTalkabout" : "${ctx}/weibo-bomb-msg/letter-by-talkabout/",
						"mentionByRelay" : "${ctx}/weibo-bomb-msg/mention-by-relay/",
						"mentionByTalkabout" : "${ctx}/weibo-bomb-msg/mention-by-talkabout/"
					};
	$(function(){
		$("#type").change(function(){
			var $this = $(this);
			var value = $this.val();
			if(value === "mentionByRelay"){
				$("#nameOrKeywordTr").hide();
			} else{
				$("#nameOrKeywordTr").show();
				if(value.indexOf("Talkabout") !== -1){
					$("#nameOrKeywordTd").empty().append("<span class=\"red\" style=\"color: red;\" >*</span>关键词：");
				}else{
					$("#nameOrKeywordTd").empty().append("<span class=\"red\" style=\"color: red;\" >*</span>用户名(以;分隔)：");
				}
			}
			
		});
		$("#commit").click(function(){
			var type = $("#type").val();
			var data = {"content" : $("#content").val()};
			data[type.indexOf("Talkabout") !== -1 ? "keyword" : "names"] = $("#nameOrKeyword").val();
			$.ajax({
				url : urlMapping[type],
				type : "POST",
				dataType : "json",
				data : data,
				success : function(data){
					if(data.result && data.result.indexOf("success") !== -1){
						$("#message").empty().append(tgenius.date2string(new Date(),"yyyy-MM-dd hh:mm:ss") + " 请求已进入处理队列，稍后请查看处理日志");
						return;
					}
					
					$("#message").empty().append("请求发送失败，请核对数据后重试");
				},
				error : function(data){
					$("#message").empty().append("网络异常，请稍后重试");
				}
			});
		});
	});
</script>
</head>
<body>
 <!--content start-->
<div class="content">
<div class="table">
<div class="contentNav"><h1>Weibo消息发送</h1></div>
<div class="tips"><img src="${ctx}/resources/img/tips.gif" align="left" />&nbsp;所有带有<span class="red" style="color: red;" >*</span>为必填项</div>
<p id="message"></p>
<div class="info border">
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tbody>
<tr>
<td width="150" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>发送消息类型：</td>
<td colspan="2">
	<select id="type" name="type" >
		<option label="私信(通过用户名)" value="letterByNames"/>
		<option label="私信(通过用户发表的微博)" value="letterByTalkabout"/>
		<option label="转播(随机转播热门微博)" value="mentionByRelay"/>
		<option label="转播(随机@)" value="mentionByTalkabout"/>
	</select>
</td>
</tr>
<tr id="nameOrKeywordTr">
<td id="nameOrKeywordTd" width="240" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>用户名(以;分隔)：</td>
<td colspan="2">
	<input id="nameOrKeyword" name="nameOrKeyword" class="input5 fontMar" type="text" value=""/>
</td>
</tr>
<tr>
<td width="150" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>消息内容：</td>
<td colspan="2">
	<textarea rows="5" cols="80" id="content"></textarea>
</td>
</tr>
</tbody>
</table>
</div>
<div class="contactBbutton">
<input id="commit" type="button" value="提交" class="button1" />&nbsp;
<input id=“back” type="button" value="返回" class="button1" onclick="javascript:history.go(-1);"/>
</div>
</div></div>
</body>
</html>
