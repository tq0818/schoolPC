<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-开始招生</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    
    <script type="text/javascript">
    	$(function(){
    		$selectMenu("course_class_type");
    	});
    	//返回按钮
		function backReturn(){
			$("#Forms").attr("action",rootPath+"/classType/updatePackage").submit();
		}
    </script>
</head>
<body>
<form method="post" id="Forms">
	<input type="hidden" name="id" id="banxing" value="${ct.id }"/>
	<input type="hidden" name="type2" value="update"/>
	<input type="hidden" name="lable" id="labType" value="${lable }"/>
</form>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap classes">
    <div class="nopadding">
        <div class="steps">
            <div class="line"></div>
            <ul class="clear">
                 <li class="step s1 active">
                    <i>01</i>
                    <em>基本信息</em>
                </li>
                <li class="step s2 active">
                    <i>02</i>
                    <em>选择课程单元</em>
                </li>
                <li class="step s3 active">
                    <i>03</i>
                    <em>课程详情</em>
                </li>
                 <li class="step s4 hover">
                    <i>04</i>
                    <em>开始招生</em>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="u-wrap classes">
<form method="post" id="myForm">
	<input type="hidden" name="id" value="${ct.id }"/>
	<input type="hidden" name="lable" value="${lable }"/>
	<input type="hidden" name="publishStatus" value="CLASS_ON_SALE"/>
</form>
    <div class="mainbackground nopadding">
        <div class="heading">
            <span class="h5">${ct.name }</span>
            <span class="float-right">
	             <span>${ct.isSale==0?'未上架':'已上架' }</span>
	            <span>${ct.itemOneName }-${ct.itemSecondName }</span>
	            <span>定价:${ct.realPrice }</span>
            </span>
            <span class="line"></span>
        </div>
        <div class="c-main c-max">
        <div style="margin-bottom: 5px;color: gray;font-size: 14px;">说明：为此课程中每个单元指定默认报名班号，报名此课程时默认安排到此班号中</div>
            <div class="c-list">
            	<c:forEach items="${modulesVoList }" var="moduleList">
            	 <c:if test="${wx:dictCode2Name(moduleList.teachMethod)=='面授' }">
            			<p class="public clear">
		                    <span class="left clear" style="width:29%;">
		                        <span class="c-title">课程单元</span>
		                        <span class="c-content">${moduleList.name }</span>
		                        <span class="c-title" style="width:85px;">课程单元类型</span>
		                        <span class="c-content">${wx:dictCode2Name(moduleList.teachMethod) }</span>
		                        <span class="c-title">总课时</span>
                    			<span class="c-content">${moduleList.totalClassHour==null?0:moduleList.totalClassHour }课时</span>
		                    </span>
		                    <span class="right clear" style="width:62%;">
		                    	<c:if test="${empty moduleList.classModules }">
		                    		<span class="block">
			                            <span class="c-title"></span>
			                            <span class="c-content"></span>
			                        </span>
		                    	</c:if>
		                    	<c:forEach items="${moduleList.classModules }" var="modulesNo" varStatus="status">
			                    	<span class="block active">
			                            <span class="c-title" style="width:200px;">${status.count==1?'指定默认报名的班号':'&nbsp;' }&nbsp;
			                            	<c:if test="${status.count==1 }">
			                            		 <i class="iconfont ask" style="cursor: pointer;" title="为此课程中每个单元指定默认报名班号，报名此课程时默认安排到此班号中">&#xe60f;</i>
			                            	</c:if>
			                            </span>
			                            <span class="c-content">
			                            <input type="radio" name="clasNo${moduleList.id }" ids="${modulesNo.id }" mark="${moduleList.id }" value=""/>
			                            ${modulesNo.campusName }&nbsp;&nbsp;<span title="已排${modulesNo.lessonPlan==null?0:modulesNo.lessonPlan }次课/总共${modulesNo.lessonTotal==null?0:modulesNo.lessonTotal }次课">${modulesNo.lessonPlan==null?0:modulesNo.lessonPlan }/${modulesNo.lessonTotal==null?0:modulesNo.lessonTotal }</span>&nbsp;&nbsp;  <a href="javascript:;" class="updatebtn" ids="${modulesNo.id }" style="color:blue;">${modulesNo.name }</a>
			                             </span>
			                        </span>
		                    	</c:forEach>
		                    	<a href="javascript:;" style="color:blue;" ids="${moduleList.id }" class="addbtn">新增班号</a>
		                    </span>
		                </p>
            		</c:if>
            		<c:if test="${wx:dictCode2Name(moduleList.teachMethod)=='直播' }">
	            		<p class="public clear">
		                    <span class="left clear" style="width:29%;">
		                        <span class="c-title">课程单元</span>
		                        <span class="c-content">${moduleList.name }</span>
		                        <span class="c-title" style="width:85px;">课程单元类型</span>

		                        <span class="c-content">${wx:dictCode2Name(moduleList.teachMethod) }</span>
		                         <span class="c-title">总课时</span>
                    			<span class="c-content">${moduleList.totalClassHour==null?0:moduleList.totalClassHour }课时</span>
		                    </span>
		                    <span class="right clear" style="width:62%;">
		                    	<c:if test="${empty moduleList.classModules }">
		                    		<span class="block">
			                            <span class="c-title"></span>
			                             <span class="c-content"></span>
			                        </span>
		                    	</c:if>
		                    	<c:forEach items="${moduleList.classModules }" var="modulesNo" varStatus="status">
			                    	<span class="block active">
			                            <span class="c-title" style="width:200px;">${status.count==1?'指定默认报名的班号':'&nbsp;' }&nbsp;
			                            	<c:if test="${status.count==1 }">
			                            		 <i class="iconfont ask" style="cursor: pointer;" title="为此课程中每个单元指定默认报名班号，报名此课程时默认安排到此班号中">&#xe60f;</i>
			                            	</c:if>
			                            </span>
			                            <span class="c-content">
			                            <input type="radio" name="clasNo${moduleList.id }" ids="${modulesNo.id }" mark="${moduleList.id }" value=""/>
			                            &nbsp;&nbsp;<span title="已排${modulesNo.lessonPlan==null?0:modulesNo.lessonPlan }次课/总共${modulesNo.lessonTotal==null?0:modulesNo.lessonTotal }次课">${modulesNo.lessonPlan==null?0:modulesNo.lessonPlan }/${modulesNo.lessonTotal==null?0:modulesNo.lessonTotal }</span>&nbsp;&nbsp; <a href="javascript:;" class="updatebtn" ids="${modulesNo.id }" style="color:blue;">${modulesNo.name }</a>
			                             </span>
			                        </span>
		                    	</c:forEach>
		                    	<a href="javascript:;" style="color:blue;" ids="${moduleList.id }" class="addbtn">新增班号</a>
		                    </span>
		                </p>
            		</c:if>
            	</c:forEach>
            </div>
            <div class="c-content">
                <p class="c text-center">
                    <a href="javascript:backReturn();" class="btn btn-default">返回</a>
                    <a href="javascript:Form.classTypeOnsale();" class="btn btn-primary">开始招生</a>
                   <a href="<%=rootPath %>/classType/showClassTypePage" class="btn btn-default">取消</a>
                </p>
            </div>
        </div>
    </div>
</div>
<div class="layout-choose none">
	<p class="text">请您在打开的网页中进行排课，排课完成后点击下面的按钮刷新页面。</p>
	<p class="btns">
		<a type="button"  class="btn btn-warning btn-bigger yes" >课表已排</a>
		<a type="button" class="btn btn-gray btn-bigger no" >排课遇到问题</a>
	</p>
	<div class="main">
	
	</div>
</div>
<div class="layout-mb none"></div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/classTypeComplete.js"></script>
</body>
</html>