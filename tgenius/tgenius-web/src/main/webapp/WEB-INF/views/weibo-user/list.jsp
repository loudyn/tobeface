<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<%@include file="/WEB-INF/commons/common-header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
	$(function(){
				
		$("#del").click(function(){
			var items = tgenius.select();
			if(items && items.length <=0){
				alert("请先选择要删除的内容");
				return false;
			}
			
			if(confirm("你确定要删除这些内容吗?")){
				
				$('input[name="_method"]').remove();
				$("#myForm").attr("action", "${ctx}/weibo-user/delete/")
							.attr("method","post")
							.append('<input type="hidden" name="_method" value="DELETE" />')
							.submit();
				
				return false;
			}
		});
		
		$("#dump").click(function(){
			$.ajax({
				url : "${ctx}/weibo-user/dump-csv/",
				type : "POST"
			});
		});
});
</script>
</head>
<body>
<div class="nrgt">

<form id="myForm" action="${ctx }/weibo-user/list/" method="get">
<div class="chsm"><span>Weibo用户管理</span></div>
<div class="czsrt">
<span>你可以：</span>
<shiro:hasPermission name="weibo-user:delete">
<input class="tjbtn" id="del" type="button" value="删除" />
</shiro:hasPermission>
<span class="dist">名称：</span><input name="params[name]" value="${page.params.name}" type="text" /><input class="tjbtn" type="submit" value="提交" />
</div>
<p>${message.text }</p>
<div class="rcd_td">
    <table cellpadding="0" cellspacing="0" border="0" class="table">
  <tr>
  	<th style="border-left:0;" width="5%"><jsp:include page="/WEB-INF/commons/checkall.jsp"></jsp:include>&nbsp;</th>
    <th>账号名</th>
    <th>昵称</th>
    <th>性别</th>
    <th>用户类别</th>
    <th>所在地</th>
    <th>所属行业</th>
    <th>出生年份</th>
    <th>粉丝数</th>
    <th width="10%">操作</th>
  </tr>
  <c:choose>
  	<c:when test="${not empty page.result }">
  	<c:forEach items="${page.result }" var="weiboUser">
  	<tr>
    <td style="border-left:0;"><input type="checkbox" name="items" id="checkbox" value="${weiboUser.name}"/></td>
    <td>${weiboUser.name}&nbsp;</td>
    <td>${weiboUser.nickname}&nbsp;</td>
    <td>
    	<c:choose>
    		<c:when test="${weiboUser.sex == '1'}">[男]&nbsp;</c:when>
    		<c:when test="${weiboUser.sex == '2'}">[女]&nbsp;</c:when>
    		<c:otherwise>[用户未填写]&nbsp;</c:otherwise>
    	</c:choose>
    </td>
    <td>${weiboUser.vip == 'true' ? '[认证用户]&nbsp;' : '[非认证用户]&nbsp;' }&nbsp;</td>
    <td>${weiboUser.location}&nbsp;</td>
    <td>${weiboUser.careername}&nbsp;</td>
    <td>${weiboUser.birthYear }&nbsp;</td>
    <td>${weiboUser.fansCount }&nbsp;</td>
    <td>
    	<a href="${ctx }/weibo-user/${weiboUser.name}/view/" target="_blank" >查看完整信息</a>&nbsp;
    </td>
  	</tr>
  	</c:forEach>
  	</c:when>
  	<c:otherwise>
  	<tr><td colspan="10" align="center"><b>暂无内容</b></td></tr>
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
