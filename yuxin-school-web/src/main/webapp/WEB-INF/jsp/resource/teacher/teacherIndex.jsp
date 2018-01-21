<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师管理</title>
<style type="text/css">
.register {
	position: fixed;
	left: 50%;
	top: 50%;
	width: 400px;
	height: 400px;
	margin-left: -200px;
	margin-top: -200px;
	padding-bottom: 15px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
	z-index: 999
}
.none{
display: none;
}
.register .reg-close {
  position: absolute;
  top: 12px;
  right: 12px;
  width: 12px;
  height: 12px;
  background-image: url('../images/index-icons.png');
  background-repeat: no-repeat;
  background-position: 0 0;
  cursor: pointer;
}
.register .reg-title {
  padding: 15px 30px;
  border-bottom: 1px solid #e5e5e5;
}
.register .reg-form {
  padding: 0 60px;
}
.register .reg-bottom {
  padding: 2px 52px;
  border-top: 1px solid #e5e5e5;
}
.mark-bg {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: #fff;
  background-color: rgba(255,255,255,0.6);
  opacity: .6 \9;
  filter: alpha(opacity = 60);
}
</style>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/resource.css"/>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/resource/teacher/teacher.js"></script>
</head>
<body>
	<!-- 二级导航开始 -->
	<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>	
	<!-- 二级导航结束 -->
	
	<!--  内容开始 -->
		<div class="u-wrap resource">
		    <div class="mainbackground nopadding">
		        <div class="heading">
		            <h2 class="h5">教师</h2>
		            <div class="search" style="right: 106px;top: 	-2px;">
		            	 <input type="text" id="teacherName" placeholder="请输入教师名称"/>
		           		 <input type="button" value="搜索" id="nameSearch"/>
		            </div>
		           
		            <div class="search">
		                <a href="<%= rootPath %>/sysConfigTeacher/manage/0" class="btn btn-mini btn-primary"><em class="iconfont">&#xe606;</em> 添加教师</a>
		            </div>
		            <span class="line"></span>
		        </div>        
		        <div class="r-list">
		            <div class="sc-select">
		                <span class="sc-select-title">学科</span>
		                <a href="javascript:;"  itemId="0" class="btn btn-mini btn-default"> 全部</a>
		                <c:forEach items="${items }" var="item" varStatus="index">
								<a href="javascript:;"  itemId="${item.id }" class="btn btn-mini btn-default"> ${item.itemName }</a>
		                </c:forEach>
		            </div>
		            <input type="hidden" id="itemId" value="0"/>
		            <div class="r-list" id="teacherContent" style="min-height: 350px;position: relative;">
		                
		            </div>
		        </div>
		        <div class="pages">
						<ul class="pagination">
		
						</ul>
				</div>
		    </div>
		</div>
	<!--  内容结束 -->
	
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<!--  ajax加载中div结束 -->
<script>
	$(function(){
		$selectSubMenu('resource_teacher');
	});
</script>
</body>
</html>