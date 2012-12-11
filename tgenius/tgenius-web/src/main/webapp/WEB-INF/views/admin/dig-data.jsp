<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<%@include file="/WEB-INF/commons/common-header.jsp" %>
<script src="${ctx }/resources/js/jquery-timer.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
	$(function(){
		$("#commit").click(function(){
			var $this = $(this);
			$this.attr("disabled", true).oneTime("2s","disable",function(){
				$this.attr("disabled",false);
			});
			var type = $("#type").val();
			var value = $("#content").val();
			var url = (type === "byKeyword") ? "${ctx}/dig-weibo-user/by-keyword/" : "${ctx}/dig-weibo-user/by-tags/";
			var data = (type === "byKeyword") ? {"keyword" : value} : {"tags" : value};
			$.ajax({
				url : url,
				type : "POST",
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
<div class="contentNav"><h1>Weibo用户挖掘</h1></div>
<div class="tips"><img src="${ctx}/resources/img/tips.gif" align="left" />&nbsp;所有带有<span class="red" style="color: red;" >*</span>为必填项</div>
<p id="message"></p>
<div class="info border">
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tbody>
<tr>
<td width="150" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>用户查找类型：</td>
<td colspan="2">
	<select id="type" name="type" >
		<option label="最近说过" value="byKeyword"/>
		<option label="标签" value="byTags"/>
	</select>
</td>
</tr>
<tr>
<td width="150" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>关键字或标签：</td>
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
