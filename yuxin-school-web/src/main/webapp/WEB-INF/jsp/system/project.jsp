<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>学科</title>
   <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css"/>
   <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css"/>
   <script type="text/javascript" src="<%=rootPath%>/javascripts/system/onePro.js"></script>
<style type="text/css">
.iconfont {
	position: relative;
	top: 2px;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	line-height: 1
}

.iconfont.open {
	color: #1b6fbd
}

.iconfont.closed {
	color: #666
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>

<div class="u-wrap resource">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">学科&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	<!-- <span class="btn btn-mini">说明：此处的学科设置是公司级别的设置，每个分校如需使用，需要到资源里的学科功能里启用</span> -->
            </h2>
            <div class="add-btns">
                <a id="createPro" href="javascript:;" class="btn btn-mini btn-primary btn-pro"><em class="iconfont" style="top:0px;">&#xe606;</em> 添加学科</a>
            </div>
            <span class="line"></span>
        </div>

        <div class="r-list">
            <ul class="clear">
            	<c:forEach var="one" items="${oneList }">
                <li>
                	<input type="hidden" class="oneId" value="${one.id }"/>
                	<input type="hidden" class="oneStatus" value="${one.status }"/>
                    <p class="r-title"><i class="iconfont"><img src="http://${ImagePath }/${one.itemPic }" style="background-color:blue;"/></i><em>${one.itemName }</em>
                    <a href="javascript:;" class="btn btn-mini btn-link btn-edit-pro" data-id="${one.id }">编辑</a>
                    <a name="oneStatus" href="javascript:;" class="btn btn-mini btn-link btn-switch" style="text-decoration: none">
                    	<c:if test="${one.status == 0 }"><i class="iconfont normal closed">&#xe604;</i>已停用</c:if>
                    	<c:if test="${one.status == 1 }"><i class="iconfont normal open">&#xe603;</i>已启用</c:if>
                    </a>
                    <span class="new-btn">
                        <a href="javascript:;" name="twoToCreate" class="btn btn-mini btn-primary btn-add-subs"><em class="iconfont" style="top:0px;">&#xe606;</em>新增学科小类</a>
                    </span>
                    </p>
                    <p class="r-subs">
                    </p>
                </li>
            	</c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>