<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<%@include file="/WEB-INF/commons/common-header.jsp" %>
<%@include file="/WEB-INF/commons/no-cache.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div class="nlft">
<ul class="wcc">
<h1><strong>数据管理</strong></h1>
<shiro:hasPermission name="weibo-app-keys:list">
<li><a href="${ctx}//weibo-app-keys/list/" target="right"><span class="allIco ico15"></span>AppKeys管理</a></li>
<li><a href="${ctx}//weibo-app-keys/create/" target="right"><span class="allIco ico15"></span>创建AppKeys</a></li>
</shiro:hasPermission>
<shiro:hasPermission name="weibo-user:list">
<li><a href="${ctx}//weibo-user/list/" target="right"><span class="allIco ico15"></span>Weibo用户管理</a></li>
<li><a href="${ctx}//weibo-user/dump/" target="right"><span class="allIco ico15"></span>Weibo用户导出</a></li>
</shiro:hasPermission>
</ul>
</div>
</body>
</html>
