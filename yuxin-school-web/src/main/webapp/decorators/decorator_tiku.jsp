<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<% String rootPath=request.getContextPath(); %>
<!DOCTYPE html >
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<%=rootPath %>/images/favicon.ico" type="image/x-icon" /> 
    <title><decorator:title /></title>
	<!-- 日期控件样式 -->
	<!-- 日期控件样式结束 -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tiku/tiku_fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tiku/tiku_manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tiku/tiku.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/sidebar/sidebar.css" />
	<script type="text/javascript">var rootPath='<%=rootPath%>'</script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/sidebar.js"></script>
	<decorator:head />
  </head>
  
  <body> 
	<jsp:include page="/header.jsp"></jsp:include>
	<!---------- 页面主体内容 开始 ------------>
	 <decorator:body />
	<!---------- 页面主体内容 结束 ------------>
	<!---------- 页脚 开始 ------------>
	<jsp:include page="/footer.jsp"></jsp:include>
	<!---------- 页教 结束 ------------>
	
	<!-- 以下是javascript引用，写在这里是为了加快页面加载速度，尽量不要写到head里 -->
  	<!----------日期控件 开始--------------->
   <!----------日期控件 结束--------------->
    <!----------- 分页 开始------------->
    <!----------- 分页 结束------------->
    <!-- 通用工具 -->
    <!-- 通用工具 -->
    <script type="text/javascript">
	    window["contextPath"] = "${pageContext.request.contextPath}";
	    window["sessionId"] = "${pageContext.session.id}";
	    window["sessionName"] = "JSESSIONID";
	    var realName = "${sessionScope.loginUser.realName}";
	    var companyId = "${sessionScope.loginUser.companyId}";
	    var companyName = "${sessionScope.company.companyName}";
	    var email = "${sessionScope.loginUser.email}";
	    var mobile = "${sessionScope.loginUser.mobile}";
    </script>
  </body> 
</html>