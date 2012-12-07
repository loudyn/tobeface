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
<h1><strong>微博消息管理</strong></h1>
<shiro:hasPermission name="weibo-bomb-msg:create">
<li><a href="${ctx}/weibo-bomb-msg//letter-by-names/" target="right"><span class="allIco ico15"></span>发送私信（列表）</a></li>
<li><a href="${ctx}/weibo-bomb-msg//letter-by-names-file/" target="right"><span class="allIco ico15"></span>发送私信（文件）</a></li>
<li><a href="${ctx}/weibo-bomb-msg//letter-by-talkabout/" target="right"><span class="allIco ico15"></span>发送私信（讨论）</a></li>
<li><a href="${ctx}/weibo-bomb-msg/mention-by-relay/" target="right"><span class="allIco ico15"></span>转播微博（）</a></li>
<li><a href="${ctx}/weibo-bomb-msg/mention-by-talkabout/" target="right"><span class="allIco ico15"></span>转播微博</a></li>
</shiro:hasPermission>
</ul>
</div>
</body>
</html>
