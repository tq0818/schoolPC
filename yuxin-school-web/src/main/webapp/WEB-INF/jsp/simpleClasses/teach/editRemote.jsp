<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>远程模块</title>
    <%@include file="/decorators/import.jsp"%>
   <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
     <style type="text/css">
   		.classes .mainbackground .c-main .add-classes p.h{
    		margin-bottom:22px;
    		float:left;
    		color:red;
    	}
    	.classes .mainbackground .c-main .add-classes p span.c-title{
    		float:left;
    	}
   </style>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/class/teach/editRemote.js"></script>
    
</head>
<body>

<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<form action="<%=rootPath %>/classModule/toMain" method="post" id="editForm">
<input type="hidden" id="moduleId" name="id" value="${remote.id }"/>
<input type="hidden" id="teachMethod" name="teachMethod" value="TEACH_METHOD_REMOTE"/>
<div class="u-wrap student">
   	 <div class="mainbackground nopadding" style="height:400px">
        <div class="heading">
            <h2 class="h5">新增课程单元</h2>
            <span class="line"></span>
        </div>
        <div class="m-search clear">
            <span class="fl">
               <a href="javascript:void(0);" id="TEACH_METHOD_LIVE" fType="moduleType" fValue="TEACH_METHOD_LIVE" class="bt1 btn btn-mini btn-default ">直播</a>
                <a href="javascript:void(0);" id="TEACH_METHOD_FACE" fType="moduleType" fValue="TEACH_METHOD_FACE" class="bt1 btn btn-mini btn-default">面授</a>
                <a href="javascript:void(0);" id="TEACH_METHOD_VIDEO" fType="moduleType" fValue="TEACH_METHOD_VIDEO" class="bt1 btn btn-mini btn-default">录播</a>
                <a href="javascript:void(0);" id="TEACH_METHOD_REMOTE" fType="moduleType" fValue="TEACH_METHOD_REMOTE" class="bt2 btn btn-mini btn-default">远程</a>
            </span>
        </div>
        <div class="main-content">
            <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">学科</span>
                        <span class="c-content">
                            <select name="itemOneId" id="one" >
                            <c:forEach items="${oneItems }" var="oneItem">
                            	<option value="${oneItem.id }"
                            	<c:if test="${itemOneId==oneItem.id }">selected</c:if>
                            	>${oneItem.itemName }</option>
                            </c:forEach>
                            </select>
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">学科小类</span>
                        <span class="c-content">
                           	<select name="itemSecondId" id="two">
                            <c:forEach items="${twoItems }" var="twoItem">
                            	<option value="${twoItem.id }"
                            	 <c:if test="${itemTwoId==twoItem.id }">selected</c:if>
                            	>${twoItem.itemName }</option>
                            </c:forEach>
                            </select>
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">合作院校</span>
                        <span class="c-content">
                            <input type="text" id="schoolName" name="name" onkeyup="value=value.replace(/\s/g,'')" value="${remote.name }"/>
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">专业</span>
                        <span class="c-content">
                            	<input type="text" id="major"  name="major" onkeyup="value=value.replace(/\s/g,'')" value="${remote.major }"/>
                        </span>
                    </p>
                </li>
                
                <li>
                    <p class="c">
                    <span class="c-title">描述</span>
                    <span class="c-content" >
                        <textarea  id="" style="width:546px;height:113px">${remote.remoteDesc }</textarea>
                    </span>
               		 </p>
                </li>
            </ul>
        </div>
        
        
        
        
    </div>
</div>
        <div class="m-bo text-center">
                <a href="javascript:void(0);"  onclick="history.go(-1)" class="btn btn-big btn-default">取消</a>
                <a href="javascript:void(0);" id="save"  onclick="toSave()"  class="btn btn-big btn-primary">保存</a>
         </div>
</form>
<input id="teachMethod" value="${teachMethod }" type="hidden"/>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/class/editRemoteValidate.js"></script>
</body>

</html>