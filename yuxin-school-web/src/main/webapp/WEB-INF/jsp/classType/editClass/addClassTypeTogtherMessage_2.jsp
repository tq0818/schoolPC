<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-选择课程单元</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
     <style type="text/css">
    	.classes .mainbackground .c-main .add-classes p.h{
    		margin-bottom: 15px;
    	}
    </style>

    
</head>
<body>
<form id="Forms" method="post">
	<input type="hidden" name="id" value="${ct.id }"/>
	<input type="hidden" name="type" value="update"/>
	<input type="hidden" name="lable" value="${lable }"/>
<!-- 	<input type="hidden" name="itemOneId" > -->
<!-- 	<input type="hidden" name="itemSecondId" /> -->
</form>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/classType/commonTitle.jsp" %>
<div class="u-wrap company overflow">
	 <%@include file="/WEB-INF/jsp/classType/commonClass.jsp" %>
    <div class="right-side">
       <div class="u-wrap classes">
    <div class="mainbackground nopadding">
        
        <form method="post" id="subMessageForm">
        	<input type="hidden" name="itemOneId" id="itemOneIds" value="${ct.itemOneId }"/>
        	<input type="hidden" name="itemSecondId" id="iTwoId" value="${ct.itemSecondId }"/>
        	<input type="hidden" id="moduleId" name="moduleId"/>
        	<input type="hidden" name="id" value="${ct.id }"/>
        	<input type="hidden" name="lable" value="${lable }"/>
        	<input type="hidden" name="type2" id="type2" value="${type2 }"/>
        	<input type="hidden" name="mark" id="markType"/>
        	<input type="hidden" name="faceFlag" id="faceId"/>
        	<input type="hidden" name="liveFlag" id="liveId"/>
        	<input type="hidden" name="videoFlag" id="videoId"/>
        </form>
        <div class="c-main c-max">
            <div class="box-config">
                <div class="tabs" id="tabsList">
                	<a href="javascript:Form.queryModuleByClassType();" ids="TEACH_METHOD_LIVE" class="btn btn-mini btn-default btn-success">直播</a>
                	<a href="javascript:Form.queryModuleByClassType();" ids="TEACH_METHOD_FACE" class="btn btn-mini btn-default">面授</a>
                	<span><select id="itemSecsList"></select></span>
                </div>
                <div class="box-select">
                    <span class="curr">
                        <i onclick="Form.moveRight()" style="cursor: pointer;" class="iconfont">&#xe61e;</i>
                        <i onclick="Form.moveLeft()" style="cursor: pointer;" class="iconfont">&#xe61f;</i>
                    </span>
                    <div class='firstLi marks' style="width: 40%;" title="增加直播课程单元"><span class='btns'><i class='iconfont icons'>&#xe61c;</i></span></div>
                    <div class="left">
                        <ul id="ulOne">
                        
                        </ul>
                    </div>
                    <div class="right">
                        <ul id="ulTwo" class="ulTwo">
                        <c:forEach items="${classModuleList }" var="module">
                            <li id="li${module.id }" ids="${module.id }" types="${module.teachMethod }" title="${module.name }">
                                <c:choose>
						          	<c:when test="${fn:length(module.name)>10}">
						          		<span class="p1">${fn:substring(module.name, 0, 10)}...</span>
						          	</c:when>
						          	<c:otherwise>
						          		<span class="p1">${module.name }</span>
						          	</c:otherwise>
						         </c:choose>
                                <span class="p2">${wx:dictCode2Name(module.teachMethod) }</span>
                                <span class="p3">${module.totalClassHour==null?0:module.totalClassHour }课时</span>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="c-content">
                <p class="c text-center">
                    <a href="javascript:Form.ClassTypeOnsal('save')" class="btn btn-primary">保存</a>
                    <a href="<%=rootPath %>/classType/showClassTypePage" class="btn btn-default">取消</a>
                </p>
            </div>
        </div>
    </div>
</div>
    </div>
</div>

<!-- 添加模块弹窗 -->
<div class="upload-layer none" id="chooseDiv">
<input type="hidden" id="teachMethod" name="teachMethod" value="TEACH_METHOD_REMOTE"/>
<div class="u-wrap classes">
   	 <div class="mainbackground nopadding" style="height:400px">
        <div class="heading">
            <h2 class="h5" id="tits">新增直播课程单元</h2>
            <span class="line"></span>
        </div>
        <div class="c-main">
            <div class="c-content add-classes">
                 <p class='h'>
                     <span class="c-title">学科</span>
                     <span class="c-content">
                     	<span ids="${ct.itemOneId }">${ct.itemOneName }</span>
<%--                          <input type="text" name="itemOneId" value="${ct.itemOneName }" ids="${ct.itemOneId }" readonly="readonly" readonly/> --%>
                     </span>
                 </p>
                 <p class='h'>
                     <span class="c-title">学科小类</span>
                     <span class="c-content">
                      <span ids="" id="twoItemsCon"></span>
<!--                         	<select name="itemSecondId" id="twoItems"></select> -->
                     </span>
                 </p>
                  <p class='h'>
                      <span class="c-title">课程单元名称</span>
                      <span class="c-content">
                          <input type="text" id="mouName" value=""/>
                      </span>
                      <em style="color:red;">*</em>
                       <span class="note1" style="display: none;color: red;font-size: 12px;margin-left: 124px;">请输入课程单元名称</span>
                  </p>
                  <p class='h'>
                      <span class="c-title">授课方式</span>
                      <span class="c-content" id="tMs">直播</span>
                  </p>
                  <p class='h'>
                      <span class="c-title">总课时</span>
                      <span class="c-content">
                          <input type="text" id="totalClassHour" value=""/>
                      </span>
                      <em style="color:red;">*</em>
                       <span class="note2" style="display: none;color: red;font-size: 12px;margin-left: 124px;">请输入总课时</span>
                  </p>
                  <p class="c">
	                  <span class="c-title">课程单元描述</span>
	                  <span class="c-content" >
	                      <textarea name="moduleDesc"  id="moduleDesc" style="width:435px;height:113px"></textarea>
	                  </span>
             	  </p>
            </div>
        </div>
        
    </div>
</div>
        <div class="m-bo text-center">
                <a href="javascript:void(0);" id="giveUp" class="btn btn-big btn-default">取消</a>
                <a href="javascript:Form.addModules();"  class="btn btn-big btn-primary">保存</a>
         </div>

</div>
<div class="add-layer-bg none" id="stopDiv"></div>
<form method="post" id="editFroms">
	<input type="hidden" name="itemOneId" value="${ct.itemOneId }"/>
	<input type="hidden" name="itemSecondId" id="ejItemId"/>
	<input type="hidden" name="name" id="mNames"/>
	<input type="hidden" name="totalClassHour" id="totalHours"/>
	<input type="hidden" name="teachMethod" id="methodTeach"/>
	<input type="hidden" name="moduleDesc" id="moduleMark"/>
</form>
 <script type="text/javascript">
 	$(function(){
 		$("#tabsList").on('click',"a.btn",function(){
 			var $this=$(this);
 			var mark=$this.attr("ids");
 			if(mark=="TEACH_METHOD_FACE"){
 				$("#tits").html("新增面授课程单元");
 				$("#tMs").html("面授");
 			}else{
 				$("#tits").html("新增直播课程单元");
 				$("#tMs").html("直播");
 			}
 		});
 		$chooseMenu("moduleCode");
 	});
    </script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/editClass/addClassPackage.js"></script>
</body>
</html>