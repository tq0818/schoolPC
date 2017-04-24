<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>详情</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/student/full/transaction/detail.js">  </script>
    
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
<input type="hidden" id="id" value="${payMaster.id }"/>
<input type="hidden" id="stuId" value="${payMaster.stuId }"/>
<input type="hidden" id="mobile" value="${student.mobile }"/>
<%-- <div class="u-wrap student">
    <div class="mainbackground">
        <div class="main-content nospace">
            <div class="m-b-m">
                <div class="m-b-p clear">
                    <div class="m-b-p-left" >
                        <p class="h c">
                            <span class="p-title">姓名</span>
                            <span class="p-content">${student.name }</span>
                        </p>
                        <p class="h c">
                            <span class="p-title">学费</span>
                            <span class="p-content">${payMaster.totalAmount }</span>
                        </p>
                        <p class="h c">
                            <span class="p-title">报名时间</span>
                            <span class="p-content"><fmt:formatDate value="${payMaster.applyTime }" timeStyle="yyyy-MM-dd hh:mm:ss"/></span>
                        </p>
                        <p class="h c">
                            <span class="p-title">手机号码</span>
                            <span class="p-content">${student.mobile}</span>
                        </p>
                        <p class="h c">
                            <span class="p-title">已缴</span>
                            <span class="p-content">${payMaster.hasPay }</span>
                        </p>
                        <p class="h c">
                            <span class="p-title">来源</span>
                            <span class="p-content">${wx:dictCode2Name(payMaster.applyChannelCode)}</span>
                        </p>
                        <p class="h c">
                            <span class="p-title">班型</span>
                            <span class="p-content">${payMaster.classTypeName }</span>
                        </p>
                        <p class="h c important">
                            <span class="p-title">欠缴</span>
                            <span class="p-content">${payMaster.totalAmount-payMaster.hasPay }</span>
                        </p>
                    </div>
                    <div class="m-b-p-right">
                        <div class="btns">
                            <a href="javascript:;" class="m-btn">退费</a>
                            <a href="javascript:;" class="m-btn">转班</a>
                            <a href="javascript:;" class="m-btn m-mb">转人</a>
                            <a href="javascript:;" class="m-btn m-mb  m-active" >详情</a>
                            <a href="javascript:;" class="m-btn m-mb change">更换班号</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> --%>
<jsp:include page="top.jsp"></jsp:include>
<div class="u-wrap student">
	<div class="mainbackground">
	<div id="classtype"></div>
	<div id="feeMessage"></div>
	<shiro:hasPermission name="student_agent">
	<div id="material"></div>
	</shiro:hasPermission>
	</div>
	<div class="m-bo text-center noon">
			<a href="javascript:;" class="btn btn-big btn-primary"
				onclick="history.go(-1);">返回</a>
	</div>
</div>

</body>
</html>