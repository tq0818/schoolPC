<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
<title>题库</title>
<style type="text/css">
.kg{
padding-bottom: 16px;
}
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
a:link {
 font-size: 12px;
 text-decoration: none;
}
a:visited {
 font-size: 12px;
 text-decoration: none;
}
a:hover {
 font-size: 12px;
 text-decoration: none;
}
.liColor{
	background-color: #D6D6D6;
}
</style>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/stylesheets/classes.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css" />
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/tikuIndex.js"></script>
</head>
<body>
	<!-- header start -->
<%@include file="/WEB-INF/jsp/menu/menu_tiku.jsp"%>
	<!-- header end -->
	<!-- 二级导航 -->
	<div class="u-wrap resource">
		<div class="mainbackground nopadding">
			<div class="heading">
	            <h2 class="h5">题库</h2>
	            <span class="line"></span>
	        </div>
	        <div class="main-content" style="overflow: hidden;">
	        	<div id="info">
	        	
	        	</div>
	        </div>
		</div>
	</div>
	<!-- 弹层 -->
	<div class="add-teacher-room edit-tiku" style="height: 500px; width: 600px; margin-left: -22%; overflow: auto;margin-top: -260px;">
		<div class="add-teacher-room-content tikuEdit" style="margin-left: 14%; overflow: auto;">
			<p class="kg">
				<span class="sc-c-title">题库名称</span> <span class="sc-c-content">
					<input id="tikuName" type="text" value="" maxlength="50" style="width: 176px;"/>
					<span style="color: red;">最多50字符</span>
				</span>
			</p>
			<p class="kg">
				<span class="sc-c-title">包含科目</span> <span class="sc-c-content">
					<ul>
						<ul id="subInfo" style="margin-left: 20%; margin-top: -9%;"></ul>
						<li class="addCBtn" style="margin-left: 20%">
						<a href="javascript:;" class="btn btn-sm btn-success" style="margin-left: -14px;">添加科目</a></li>
						<li class="addCConter none" style="margin-left: 20%">
                        <input type="text" id="addSubName" style="width: 178px;margin-left: -15px;" maxlength="20" placeholder="最多20个字符"/>
                            <a href="javascript:;" class="btn btn-sm btn-success">保存</a>
                            <a href="javascript:;" class="btn btn-sm btn-default">取消</a>
                        </li>
					</ul>
				</span>
			</p>
			<p class="kg" style="margin-top: 12px;">
				<span class="sc-c-title">绑定学科</span>
				<span class="sc-c-content">
					<span style="width: 70px;">学科</span> 
					<select id="itemOneId" onchange="javascript:Forms.queryItemSecond()" style="width: 120px;">
						<c:forEach items="${itemOneList}" var="itemOne">
							<option value="${itemOne.id}">${itemOne.itemName}</option>
						</c:forEach>
					</select>
				</span>
				<br>
				<span class="sc-c-content" style="position: relative; left: 84px;display: none">
					<span style="width: 70px;">学科小类</span>
					<select id="itemSecondId" style="width: 120px;">
					</select>
				</span>
			</p>
			<p class="kg">
				<span class="sc-c-title">题库描述</span> <span class="sc-c-content">
					<textarea rows="3" cols="40" id="tikuDesc" maxlength="160"></textarea>
				</span>
			</p>
			<p class="kg">
		        <span class="sc-c-title">学科图标</span>
		        <span class="sc-c-content itemiconone">
					<c:forEach var="i" items="${iconList }" varStatus="status">
					<c:if test="${status.index <= 3 }">
					<span class="item-icons" style="width:60px;height:30px;">
						<img src="http://${ImagePath }/${i.iconBackUrl }" width='20px' picId="${i.id}" title="${i.iconName }" style="padding:5px;">
					</span>
					</c:if>
					</c:forEach>
		        </span>
		        <span style="width:60px;height:60px;" id="jia">
					<img src="<%=rootPath%>/images/jia.jpg" width='30px' style="padding:10px;">
				</span>
				<a href="javascript:;" class="btn-nexticon"><span style="font-size:14px;">换一批</span></a>
				<input type="hidden" value="${nowpage }" id="iconpage"/>
				<input type="hidden" value="${pagecount }" id="pagecount"/>
				<input type="hidden" id="imgurl" value="${ImagePath }"/>
				<input type="hidden" id="picUrl"/>
				<input type="hidden" id="picId"/>
		    </p>
		    <p>
		    	<span class="sc-c-title"></span>
		        <span class="sc-c-content itemiconone">
		        <c:forEach var="i" items="${iconList }" varStatus="status">
				<c:if test="${status.index > 3 }">
				<span class="item-icons" style="width:60px;height:30px;">
					<img src="http://${ImagePath }/${i.iconBackUrl }" width='20px' picId="${i.id}" title="${i.iconName }" style="padding:5px;">
				</span>
				</c:if>
				</c:forEach>
		        </span>
		    </p>

			<div class="c text-center" style="margin-left: -16%; margin-top: 6%;">
				<a id="saveTikuBtn" href="javascript:;" class="btn btn-sm btn-default btn-primary">保存</a>
				<a href="javascript:;" class="btn btn-sm btn-default btn-cancel">取消</a>
			</div>
		</div>
	</div>
	<div class="add-teacher-room-bg"></div>
	<!-- footer start -->
	<input type="hidden" id="isTikuId" />
	<!-- footer end -->
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<script>
		$(function() {
			$("body").find(".tiku").addClass("active");
		})
	</script>
</body>
</html>