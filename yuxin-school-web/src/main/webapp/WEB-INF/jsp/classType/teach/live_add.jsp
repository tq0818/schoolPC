<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>新增排课</title>
    <%@include file="/decorators/import.jsp"%>
   <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <style type="text/css">
    	.classes .mainbackground .c-main .add-classes p.h{
    		margin-bottom:22px;
    		color:red;
    	}
    	.classes .mainbackground .c-main .add-classes p span.c-title{
    		float:left;
    	}
    </style>
    <script type="text/javascript">
	    function limite(){
	    	var moduleDesc = $("#moduleDesc").val();
	        if(moduleDesc.length>254){
	        	$('<div class="c-fa">'+ "最大长度256!" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
	        	$("#moduleDesc").val(moduleDesc.substr(0,254));
	        }
	        	
	    }
    </script>
    
</head>
<body>

<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap classes head" style="display:none">
    <div class="nopadding">
        <div class="steps">
            <div class="line"></div>
            <ul class="clear">
                <li class="step3 s1 hover">
                    <i>01</i>
                    <em>基本信息</em>
                </li>
                <li class="step3 s2">
                    <i>02</i>
                    <em>添加视频</em>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
		<div class="heading">
            <h2 class="h5">新增课程单元</h2>
            <span class="line"></span>
        </div>
        <div class="m-search clear module">
            <span class="fl">
               <a href="javascript:void(0);" id="TEACH_METHOD_LIVE" fType="moduleType" fValue="TEACH_METHOD_LIVE" class="bt1 btn btn-mini btn-default ">直播</a>
                <a href="javascript:void(0);" id="TEACH_METHOD_FACE" fType="moduleType" fValue="TEACH_METHOD_FACE" class="bt1 btn btn-mini btn-default">面授</a>
                <a href="javascript:void(0);" id="TEACH_METHOD_VIDEO" fType="moduleType" fValue="TEACH_METHOD_VIDEO" class="bt1 btn btn-mini btn-default">录播</a>
<!--                <a href="javascript:void(0);" id="TEACH_METHOD_REMOTE" fType="moduleType" fValue="TEACH_METHOD_REMOTE" class="bt2 btn btn-mini btn-default">远程</a>  --> 
            </span>
        </div>
        <form action="<%=rootPath %>/classModule/toMain" method="post" id="addForm">
        <input type="hidden" name="id" id="moduleId" value="${classModule.id }"/>
        <div class="c-main">
            <div class="c-content add-classes">
                <p class="h">
                    <span class="c-title">学科</span>
                    <span class="c-content">
                        <select name="itemOneId" id="one" class="item">
                        	<c:forEach items="${oneItems}" var="oneItem">
                           	 	<option value="${oneItem.id }"
                           	 	 <c:if test="${itemOneId==oneItem.id }">selected</c:if>
                           	 	>${oneItem.itemName }</option>
                        	</c:forEach>
                        </select>
                    </span>
                </p>
                <p class="h">
                    <span class="c-title">学科小类</span>
                    <span class="c-content">
                        <select name="itemSecondId" id="two" class="item">
                        <c:forEach items="${twoItems }" var="twoItem">
                            <option value="${twoItem.id }" <c:if test="${itemTwoId==twoItem.id }">selected</c:if> >${twoItem.itemName }</option>
                        </c:forEach>
                        </select>
                    </span>
                   
                </p>
                <p class="h">
                    <span class="c-title">模块名称</span>
                    <span class="c-content"><input class="moduleName" type="text" name="name" value="${classModule.name }" id="name" >
                    </span>
                </p>
                <p class="h">
                    <span class="c-title">授课方式</span>
                    <span class="c-content">
                    <input type="hidden" id="method1" name="teachMethod"  value="${teachMethod}" readonly>
                    <input type="text" id="method" class="readonly" value="${wx:dictCode2Name(teachMethod )}" readonly>
                  </span>
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
                        	<option value="${type.itemCode }" id="${type.itemCode }">${type.itemValue }</option>
                        </c:forEach>
                        </select>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">描述</span>
                    <span class="c-content">
                        <textarea name="moduleDesc" id="moduleDesc" onkeydown="limite();">${classModule.moduleDesc }</textarea>
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
<input id="teachMethod" value="${teachMethod }" type="hidden"/>
<input id="moduleType" value="${moduleType }" type="hidden"/>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jquery-validation/jquery.validate.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/class/live_add.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/teach/liveAdd.js"></script>
</body>
</html>