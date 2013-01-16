<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<%@include file="/WEB-INF/commons/common-header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${ctx }/resources/js/ueditor/themes/default/ueditor.css"/>
</head>
<body>
<form:form method="post" modelAttribute="weiboAppKey" id="form">
<input type="hidden" name="_method" value="${_method }" />
 <!--content start-->
<div class="content">
<div class="table">
<div class="contentNav"><h1>AppKeys管理</h1></div>
 <div class="tips"><img src="${ctx}/resources/img/tips.gif" align="left" />&nbsp;所有带有<span class="red" style="color: red;" >*</span>为必填项</div>
<div class="info border">
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tbody>
<tr>
<td width="240" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>应用App平台：</td>
<td colspan="2">
	<form:select path="platform">
		<form:option value="QWEIBO">腾讯微博</form:option>
		<form:option value="SINA">新浪微博</form:option>
	</form:select>
</td>
</tr>
<tr>
<td width="240" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>应用App Key：</td>
<td colspan="2">
	<form:input path="apiKey" cssClass="input5 fontMar"/>
</td>
</tr>
<tr>
<td width="240" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>应用App Secret：</td>
<td colspan="2">
	<form:input path="apiSecret" cssClass="input5 fontMar"/>
</td>
</tr>
<tr>
<td width="240" align="right" nowrap="nowrap">Access Token：</td>
<td colspan="2">
	<form:input path="accessToken" cssClass="input5 fontMar"/>&nbsp;<a href="${weiboAppKeys.authorizationUrl }" target="_blank">点此获取AccessToken</a>
</td>
</tr>
<tr>
<td width="240" align="right" nowrap="nowrap">能否发送私信：</td>
<td colspan="2">
	<form:select path="enablePrivateLetter">
		<form:option value="1">是</form:option>
		<form:option value="0">否</form:option>
	</form:select>
</td>
</tr>
<tr>
<td width="240" align="right" nowrap="nowrap">Api请求附加参数（json格式）：</td>
<td colspan="2">
	<form:textarea path="otherParams" cssClass="input5 fontMar" style="margin-top: 2px; margin-bottom: 2px; height: 80px; margin-left: 2px; margin-right: 2px; width: 318px;"  />
</td>
</tr>
<tr>
<td width="240" align="right" nowrap="nowrap">应用地址：</td>
<td colspan="2">
	<form:textarea path="callbackUrl" cssClass="input5 fontMar" style="margin-top: 2px; margin-bottom: 2px; height: 80px; margin-left: 2px; margin-right: 2px; width: 318px;"  />
</td>
</tr>
<tr>
<td width="240" align="right" nowrap="nowrap">授权AccessToken地址：</td>
<td colspan="2">
	<form:textarea path="authorizationUrl" cssClass="input5 fontMar" style="margin-top: 2px; margin-bottom: 2px; height: 80px; margin-left: 2px; margin-right: 2px; width: 318px;"  />
</td>
</tr>
<tr>
<td width="240" align="right" nowrap="nowrap">刷新AccessToken地址：</td>
<td colspan="2">
	<form:textarea path="refreshAccessTokenUrl" cssClass="input5 fontMar" style="margin-top: 2px; margin-bottom: 2px; height: 80px; margin-left: 2px; margin-right: 2px; width: 318px;"  />
</td>
</tr>
</tbody>
</table>
</div>
<div class="contactBbutton">
<input id="ok" type="submit" value="提交" class="button1" />&nbsp;
<input id=“back” type="button" value="返回" class="button1" onclick="javascript:history.go(-1);"/>
</div>
</div></div>
</form:form>
</body>
</html>
