<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>课程单元</title>
    <%@include file="/decorators/import.jsp"%>
   <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
   <script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/class/teach/main.js">
    	
    	
    </script>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>

<div id="message">
<div class="u-wrap classes">
    <div class="mainbackground nopadding" id="item">
        <div class="classes-type">
            <p class="c">
                <span class="t-title">学科</span>
                <span class="t-content" id="one">
                	<c:forEach items="${oneItems }" var="oneItem" varStatus="status">
                		<c:if test="${status.index==0 }">
                		 <a href="javascript:;" fType="oneItem" fValue="${oneItem.id }" class="btn btn-mini btn-default">${oneItem.itemName }</a>
                		</c:if>
                		<c:if test="${status.index!=0 }">
                		 <a href="javascript:;" fType="oneItem" fValue="${oneItem.id }" class="btn btn-mini btn-default">${oneItem.itemName }</a>
                		</c:if>
                	</c:forEach>
                </span>
            </p>
            <p class="c">
                <span class="t-title">学科小类</span>
                <span class="t-content" id="two">
                    <c:forEach items="${twoItems }" var="twoItem" varStatus="status">
                		<c:if test="${status.index==0 }">
                		 <a href="javascript:;" fType="twoItem" fValue="${twoItem.id }" class="btn btn-mini btn-default">${twoItem.itemName }</a>
                		</c:if>
                		<c:if test="${status.index!=0 }">
                		 <a href="javascript:;" fType="twoItem" fValue="${twoItem.id }"  class="btn btn-mini btn-default">${twoItem.itemName }</a>
                		</c:if>
                	</c:forEach>
                </span>
            </p>
        </div>
    </div>
</div>
<input type="hidden" id="moduleType" value="${moduleType }"/>
<input type="hidden" id="item1" value="${one }"/>
<input type="hidden" id="item2" value="${two }"/>

<div id="main" class="u-wrap classes">

</div>

</div>
</body>
</html>