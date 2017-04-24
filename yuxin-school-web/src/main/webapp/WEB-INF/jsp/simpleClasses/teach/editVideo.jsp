<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>编辑模块</title>
    <%@include file="/decorators/import.jsp"%>
   <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jquery-validation/jquery.validate.js"></script>
   <style type="text/css">
   		.classes .mainbackground .c-main .add-classes p.h{
    		margin-bottom:22px;
    		color:red;
    	}
    	.classes .mainbackground .c-main .add-classes p span.c-title{
    		float:left;
    	}
   </style>
    
</head>
<body>

<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>

<div class="u-wrap classes">
    <div class="mainbackground nopadding">
		<div class="heading">
            <h2 class="h5">新增课程单元</h2>
            <span class="line"></span>
        </div>
        
        <form action="<%=rootPath %>/classModule/toMain" method="post" id="addForm1">
        <input type="hidden" name="id" id="moduleId" value="${classModule.id }"/>
        <div class="c-main">
            <div class="c-content add-classes">
                <p class="h">
                    <span class="c-title">学科</span>
                    <span class="c-content">
                        <select name="itemOneId" id="one" disabled="disabled" >
                           	 	<option value="${one.id }" >${one.itemName }</option>
                           	 	 
                        </select>
                    </span>
                </p>
                <p class="h">
                    <span class="c-title">学科小类</span>
                    <span class="c-content">
                        <select name="itemSecondId" id="two" disabled="disabled">
                            <option value="${two.id }" >${two.itemName }</option>
                        </select>
                    </span>
                </p>
                <p class="h">
                    <span class="c-title">模块名称</span>
                    <span class="c-content"><input type="text" name="name" value="${classModule.name }" id="name" >
                    </span>
                </p>
                <p class="h">
                    <span class="c-title">授课方式</span>
                    <span class="c-content">
                    <input type="hidden" id="method1" name="teachMethod"  value="TEACH_METHOD_VIDEO" readonly>
                    <input type="text" id="method" class="readonly" value="视频模块"></span>
                </p>
                <p class="h">
                    <span class="c-title">总课时</span>
                    <span class="c-content"><input type="text" name="totalClassHour" value="${classModule.totalClassHour }"></span>
                </p>
                <p class="h">
                    <span class="c-title">课程单元类型</span>
                    <span class="c-content">
                        <select name="moduleType" id="">
                        <c:forEach items="${types }" var="type">
                        	<option value="${type.itemCode }"
                        	<c:if test="${moduleType==type.itemCode }">checked</c:if>
                        	>${type.itemValue }</option>
                        </c:forEach>
                        </select>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">描述</span>
                    <span class="c-content">
                        <textarea name="moduleDesc" id="">${classModule.moduleDesc }</textarea>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">&nbsp;</span>
                    <span class="c-content">
                        <a href="javascript:void(0);" onclick="history.go(-1)" class="btn btn-default">取消</a>
                        <a href="javascript:void(0);" onclick="save();" id="saveButton" class="btn btn-primary">保存</a>
                    </span>
                </p>
            </div>
        </div>
        </form>
    </div>
</div>
<input id="teachMethod" value="TEACH_METHOD_VIDEO" type="hidden"/>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/class/live_add2.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/teach/editVideo.js"></script>
</body>
</html>