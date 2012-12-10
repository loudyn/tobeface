<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<%@include file="/WEB-INF/commons/common-header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form action="${ctx }/weibo-user/dump-xls/" method="post">
 <!--content start-->
<div class="content">
<div class="table">
<div class="contentNav"><h1>Weibo用户导出</h1></div>
 <div class="tips"><img src="${ctx}/resources/img/tips.gif" align="left" />&nbsp;所有带有<span class="red" style="color: red;" >*</span>为必填项</div>
<div class="info border">

<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tbody>
<tr>
<td width="150" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>是否认证用户：</td>
<td colspan="2">
	<select name="vip" >
		<option label="全部" value=""/>
		<option label="是" value="1"/>
		<option label="否" value="0"/>
	</select>
</td>
</tr>
<tr>
<td width="150" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>性别：</td>
<td colspan="2">
	<select name="sex" >
		<option label="全部" value=""/>
		<option label="男" value="1"/>
		<option label="女" value="2"/>
		<option label="用户未填写" value="0"/>
	</select>
</td>
</tr>
<tr>
<td width="240" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>用户所在地：</td>
<td colspan="2">
	<input name="loc" class="input5 fontMar" type="text" value=""/>&nbsp;（如：中国 广东 广州），中间有空格
</td>
</tr>
<tr>
<td width="150" align="right" nowrap="nowrap"><span class="red" style="color: red;" >*</span>粉丝数目：</td>
<td colspan="2">
	<select name="fans" >
		<option label="全部" value=""/>
		<option label="100以上" value="100"/>
		<option label="500以上" value="500"/>
		<option label="1000以上" value="1000"/>
	</select>
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
</form>
</body>
</html>
