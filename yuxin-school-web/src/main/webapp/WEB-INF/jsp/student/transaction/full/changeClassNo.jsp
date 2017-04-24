<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>调整班号</title>
<%@include file="/decorators/import.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/manage.css">
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/student.css">
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/student.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/full/transaction/changeClassNo.js"></script>

</head>
<body>
	<input type="hidden" value="${lable }" id="lableTypes"/>
	<c:choose>
		<c:when test="${lable=='simpleType' }">
			<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>
	<input id="itemOneId" value="${payMaster.itemOneId }" type="hidden">
	<input id="classTypeId" value="${payMaster.commodityId }" type="hidden">
	<input id="payMasterId" value="${payMaster.id }" type="hidden">
	<input id="campusId" value="" type="hidden">
	<input id="changeMessage" value="" type="hidden">
	<jsp:include page="top.jsp"></jsp:include>
	<div class="u-wrap student">
		<div class="mainbackground">
			<div class="heading">
				<h2 class="h5">调整班号</h2>
				<div class="user-infos">
					<span>${student.name }</span> <span>${student.mobile }</span> <span>${payMaster.classTypeName }</span>
				</div>
				<span class="line"></span>
			</div>
			<div class="main-content">
				<div class="m-title">
					<h2 class="h6">订单信息</h2>
				</div>
				<ul class="list-infos clear">
					<li>
						<p class='c'>
							<span class="c-title">学习课程</span> <span class="c-content">${payMaster.classTypeName }</span>
						</p>
					</li>
					<shiro:hasPermission name="student_agent">
					<li>
						<p class='c'>
							<span class="c-title">代报考</span> <span class="c-content">
								<input type="checkbox" 
								<c:if test="${payMaster.isAgent==1 }">checked</c:if>
								 value="1" disabled="disabled"> 是否代报考 <input
								type="checkbox"
								<c:if test="${payMaster.isAgentMaterialComplete==1 }">checked</c:if>
								 value="2" disabled="disabled"> 资料是否齐全
							</span>
						</p>
					</li>
					</shiro:hasPermission>
					<li>
						<p class='c'>
							<span class="c-title">培训费用</span> <span class="c-content">${payMaster.trainingFee }</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">代报考费用</span> <span class="c-content">${payMaster.examAgentFee }</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">订单总价</span> <span class="c-content">${payMaster.totalAmount }</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">已交总额</span> <span class="c-content">${payMaster.hasPay }</span>
						</p>
					</li>
				</ul>
			</div>
			<div class="main-content classInfo">
				<div class="m-title">
					<h2 class="h6">课程</h2>
				</div>
				<ul class="list-infos clear">
					<li class="long clear none">
						<p class='c'>
							<span class="c-title" style="width:100px;">优先上课校区</span> <span class="c-content schools">
							</span>
						</p>
					</li>
					<shiro:hasPermission name="student_agent">
					<li class="long clear">
						<p class="c">
						<span class="c-title" style="width:100px;">学员计划考期</span> <span class="c-content">
							<!-- <select name="" class="term">
							<option value="">请选择</option>
							</select> -->
							<select class="year">
								<option value="2015">2015</option>
								<option value="2016">2016</option>
								<option value="2017">2017</option>
								<option value="2018">2018</option>
								<option value="2019">2019</option>
								<option value="2020">2020</option>
								<option value="2021">2021</option>
								<option value="2022">2022</option>
								<option value="2023">2023</option>
								<option value="2024">2024</option>
								<option value="2025">2025</option>
							</select>
							<select class="month">
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
								<option value="04">04</option>
								<option value="05">05</option>
								<option value="06">06</option>
								<option value="07">07</option>
								<option value="08">08</option>
								<option value="09">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
							
						</span>
					</p>
					</li>
					</shiro:hasPermission>
				</ul>
				<div class="c-list modules">
					
				</div>
				<div class="m-content">
					<p class="c text-center">
						<span class="c-title">&nbsp;</span> <span class="c-content">
							<a href="javascript:;" class="btn btn-sm btn-primary" onclick="toSubmit();">确定</a> <a
							href="javascript:;" class="btn btn-sm btn-default" onclick="history.go(-1)">取消</a>
						</span>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>