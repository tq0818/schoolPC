<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>转人</title>
<%@include file="/decorators/import.jsp"%>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
  	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jquery-validation/jquery.validate.js"></script>
    <%-- <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script> --%>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/student/common.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/student/transaction/changeStudent.js"></script>

</head>
<body>
	<input id="payMasterId" value="${payMaster.id }" type="hidden"/>
	<input id="mobile" value="${student.mobile }" type="hidden"/>
	<input id="username" value="${student.username }" type="hidden"/>
	<input type="hidden" value="${lable }" id="lableTypes"/>
<c:choose>
	<c:when test="${lable=='simpleType' }">
		<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
	<jsp:include page="top.jsp"></jsp:include>
	<div class="u-wrap student">
		<div class="mainbackground">
			<div class="heading">
				<h2 class="h5">转人</h2>
				<div class="user-infos">
					<span>${student.name }</span> <span>${student.mobile }</span> <span>${payMaster.classTypeName }</span>
				</div>
				<span class="line"></span>
			</div>
			<div class="main-content" >
				<div class="m-title">
					<h2 class="h6">订单信息</h2>
				</div>
				<ul class="list-infos clear">
					<li>
						<p class='c'>
							<span class="c-title">学习课程</span> <span class="c-content">${payMaster.classTypeName }</span>
						</p>
					</li>
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
			<div id="message"></div>
			<form action="" method="post" id="newStu" onkeydown="if(event.keyCode==13)return false;">
			<input type="hidden" id="studentId" name="studentId"/>
			<input type="hidden" id="stuId" name="stuId" value="${student.id}"/>
			<input type="hidden" id="stuName" name="stuName" value="${student.name}"/>
			<input type="hidden" id="stuIdentityId" name="stuIdentityId" value="${student.identityId}"/>
			<input type="hidden" id="mid" name="mid" value="${payMaster.id}"/>
			<input type="hidden" id="changeType" name="changeType" value="ORDER_OPERTE_CHANGE_STU">
			<div class="main-content">
				<div class="m-title">
					<h2 class="h6">转人原因</h2>
				</div>
				<ul class="list-infos clear">
					<li>
						<p class='c'>
							<span class="c-title">原因分类</span> <span class="c-content">
								<select class="form-control" id="reason_type" name="reasonType"></select>
							</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">具体原因</span> <span class="c-content">
								<select class="form-control" id="reason_detail" name="reasonCode">
								</select>
							</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">责任部门</span> <span class="c-content">
								<select class="form-control" id="reason_depart" name="dutySectionCode" >
								</select>
							</span>
						</p>
					</li>
					<li class="full clear ">
						<p class='c'>
							<span class="c-title c-l-title">备注</span> <span class="c-content"> <textarea
									name="remark"  class="text" placeholder="输入备注信息"></textarea>
							</span>
						</p>
					</li>
				</ul>
			</div>
			<div class="main-content">
				<div class="m-title">
					<h2 class="h6">转赠学员信息</h2>
				</div>
				<ul class="list-infos clear">
					<li class="long">
						<p class='c'>
							<span class="c-title">转入学员的<c:if test="${crc.mobileFlag==1 && crc.usernameFlag!=1}">手机号</c:if><c:if test="${crc.mobileFlag!=1 && crc.usernameFlag==1}">用户名</c:if><c:if test="${crc.mobileFlag==1 && crc.usernameFlag==1}">手机号或用户名</c:if></span>
							<span class="c-content">
								<input type="text" id="mb" class="input-ctrol"> <input
								type="button" class="btn btn-default btn-sm" onclick="query();"
								value="查询">
								 <span class="tips" id="tips" style="display: none">该学员不存在，请在下方编辑</span>
							</span>
						</p>
					</li>
				</ul>
			</div>
			<div class="main-content">
				<div id="oldStudent"></div>
			</div>
			</form>
		</div>
	</div>
<input type="hidden" id="crc-mobileFlag" value="${crc.mobileFlag}">
<input type="hidden" id="crc-usernameFlag" value="${crc.usernameFlag}">		
<input type="hidden" id="checkIsOk" value=""/>
</body>
</html>