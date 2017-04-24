<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>远程</title>
    <%@include file="/decorators/import.jsp"%>
   <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/class/live_add.js"></script>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>

<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">课程单元详情</h2>
            <span class="line"></span>
        </div>
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">模块信息</h2>
            </div>
            <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">学科</span>
                        <span class="c-content">
                            	${remote.itemOneName }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">学科小类</span>
                        <span class="c-content">
                           		${remote.itemSecondName }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">合作院校</span>
                        <span class="c-content">
                            	${remote.name }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">专业</span>
                        <span class="c-content">
                            	${remote.major }
                        </span>
                    </p>
                </li>
                
                <li>
                    <p class="c">
                    <span class="c-title">描述</span>
                    <span class="c-content">
                        ${remote.remoteDesc }
                    </span>
               		 </p>
                </li>
            </ul>
        </div>
        
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">使用此单元的课程</h2>
            </div>
            <ul class="list-infos clear">
                <c:forEach items="${list }" var="classType">
                <li>
                    <p class='c'>
                        <span class="c-title">${classType.name }</span>
                    </p>
                </li>
               </c:forEach>
               
            </ul>
        </div>
        
        
        
        <div class="m-bo text-center">
                <a href="<%=rootPath %>/classModule/toTeach"  class="btn btn-big btn-primary">返回</a>
         </div>
    </div>
</div>
<input id="teachMethod" value="${teachMethod }" type="hidden"/>

</body>
</html>