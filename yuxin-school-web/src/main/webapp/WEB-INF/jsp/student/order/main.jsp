<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>订单</title>
    <%@include file="/decorators/import.jsp"%>
  	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
  	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
  	<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/plus/jquery.pagination.js"></script>
   <script type="text/javascript" src="<%=rootPath %>/javascripts/student/order/main.js"> </script>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
<form id="detailForm" action="<%=rootPath%>/studentPayMaster/detail" method="post">
	<input type="hidden" name="id" id="pmId"/>
</form>
<div class="u-wrap student">
    <div class="mainbackground nopadding">
        <table class="table userinfos">
            <tr>
                <td style="width:65px">
                    <span class="c-title" >学科</span>
                </td>
                <td>
                    <span class="c-content" id="one">
                       <!--  <a href="javascript:;" fType="itemOne" value="" class="btn btn-mini btn-default btn-success">全部</a> -->
                        <c:forEach items="${itemOnes }" var="itemOne" varStatus="i">
                        <c:if test="${i.index==0 }">
                        	<a href="javascript:;" fType="itemOne" value="${itemOne.id }" class="btn btn-mini btn-default btn-success">${itemOne.itemName }</a>
                        </c:if>
                        <c:if test="${i.index>0 }">
                        	<a href="javascript:;" fType="itemOne"  value="${itemOne.id }" class="btn btn-mini btn-default">${itemOne.itemName }</a>
                        </c:if>
                        </c:forEach>
                    </span>
                </td>
                
            </tr>
            <tr>
            	<td>
                <span class="c-content">学科小类</span>
                </td>
                <td colspan="3">
                    <span class="c-content" id="two">
                        <a href="javascript:;" fType="itemSecond"  class="btn btn-mini btn-default btn-success">全部</a>
                        <c:forEach items="${itemSeconds }" var="itemSecond">
                        	<a href="javascript:;" fType="itemSecond" value="${itemSecond.id }"  class="btn btn-mini btn-default">${itemSecond.itemName }</a>
                        </c:forEach>
                    </span>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="c-content">课程</span>
                </td>
                <td colspan="3">
                    <span class="c-content" id="classType">
                        <a href="javascript:;" fType="classType" value=""  class="btn btn-mini btn-default btn-success">全部</a>
                        <c:forEach items="${classTypes }" var="classType">
                        	<a href="javascript:;" fType="classType" value="${classType.id }"  class="btn btn-mini btn-default">${classType.name }</a>
                        </c:forEach>
                       
                    </span>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="u-wrap student">
    <div class="mainbackground">
        <div class="order-list">
            <ul>
                <li>
                    <table class="table order-list-infos" id="message">
                        
                    </table>
                </li>
            </ul>
        </div>
    </div>
    <div class="pages" id="page"></div>
</div>
</body>
</html>