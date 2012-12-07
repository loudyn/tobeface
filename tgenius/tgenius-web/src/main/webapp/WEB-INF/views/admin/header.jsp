<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<%@include file="/WEB-INF/commons/common-header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
$(function(){
	var navs = $(".n_lft > li > a");
	if(navs && navs[0]){
		$(navs[0]).addClass("nav_cur");
	}
	
	var reset = function(){
		navs.each(function(){
			$(this).removeClass("nav_cur");
		});
	};
	
	navs.each(function(){
		var $this = $(this);
		$this.click(function(){
			reset();
			$this.addClass("nav_cur");
		});
	});
});
</script>
</head>
<body>
<div class="head">
<div class="div960">
<div class="wm"></div>
</div>
</div>

<div class="nav">
<div class="div960">
<ul class="ldst">
<shiro:authenticated>
<li><a target="right" href="${ctx }/admin/app-info/">软件信息</a></li>
<li><a target="left" href="${ctx }/admin/data/">数据管理</a></li>
<li><a target="right" href="${ctx }/admin/msg/">消息管理</a></li>
<li><a target="right" href="${ctx }/admin/dig-data/">数据挖掘</a></li>
</shiro:authenticated>
<li class="wel"><p>欢迎您：<span><shiro:principal/></span><a href="${ctx}/admin/logout/" target="_parent" >安全退出</a></p></li>
<li class="clear"></li>
</ul>
</div>
</div>


<div class="clear"></div>
</body>
</html>
