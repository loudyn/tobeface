<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<%@include file="/WEB-INF/commons/common-header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div class="nrgt">

<form id="myForm" action="${ctx }/event-log/list/" method="get">
<div class="chsm"><span>Weibo任务管理</span></div>
<div class="czsrt">
<span>你可以：</span>
<span class="dist">名称：</span><input name="params[name]" value="${page.params.name}" type="text" /><input class="tjbtn" type="submit" value="提交" />
</div>
<p>${message.text }</p>
<div class="rcd_td">
    <table cellpadding="0" cellspacing="0" border="0" class="table">
  <tr>
    <th>任务名称</th>
    <th>任务提交时间</th>
    <th>最后状态</th>
  </tr>
  <c:choose>
  	<c:when test="${not empty page.result }">
  	<c:forEach items="${page.result }" var="weiboTask">
  	<tr>
    <td>${weiboTask.name}&nbsp;</td>
    <td class="date">${weiboTask.createTime}&nbsp;</td>
    <td>${weiboTask.lastHandleEvent.message}&nbsp;</td>
  	</tr>
  	</c:forEach>
  	</c:when>
  	<c:otherwise>
  	<tr><td colspan="3" align="center"><b>暂无内容</b></td></tr>
  	</c:otherwise>
  </c:choose>
</table>
 <!--page-->             
<div class="page"><jsp:include page="/WEB-INF/commons/page.jsp" /></div>    
</div>
</form>
</div>
</body>
</html>
