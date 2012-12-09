<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<%@include file="/WEB-INF/commons/common-header.jsp" %>
<%
	long current = System.currentTimeMillis();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${ctx }/resources/js/jquery-timer.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		var current = <%= current %>;
		$("#current").everyTime("1s", "updateTimer", function(){
			current += 1000;
			var date = new Date();
			date.setTime(current);
			$("#current").empty().append("当前时间：" + tgenius.date2string(date,"yyyy-MM-dd hh:mm:ss"));
		});
	});
</script>
</head>
<body>
<div class="nrgt">
<div class="chsm"><span>软件信息</span></div>
<ul class="inx_con">
<p id="current">正在获取当前时间,请稍后...</p>
<li>操作系统：${props['os.name'] }</li>
<li>操作系统版本：${props['os.version'] }</li>
<li>JAVA运行时环境版本：${props['java.version'] }</li>
<li>后台管理系统：t-genius 0.0.1</li>
<li>技术支持：<a href="http://tobeface/" target="_blank">tobeface.com</a></li>
<li>推荐浏览器：Google Chrome<a href="http://www.google.cn/chrome/intl/zh-CN/landing_chrome.html?hl=zh-CN" target="_blank">&nbsp;&nbsp;点此下载</a></li>
</ul>
</div>
</body>
</html>
