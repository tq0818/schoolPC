<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>导入学员</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <style type="text/css">
    	.main-content{
    		margin: 20px 20px 0px 100px;
    	}
    	.top-content{
    		margin: 0px 0px 50px 0px;
    	}
    	.c-title{
    		font-size:14px;
    		margin-left: -50px;
    	}
    	.c-content{
    		font-size: 14px;
    	}
    	.center-content{
    		margin: 0px 0px 10px 0px;
    		font-size:14px;
    	}
    </style>
    
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<input type="hidden" id="markUrl" value=""/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">导入用户</h2>
            <span class="line"></span>
        </div>
        <div class="main-content">
        	<div class="top-content">
	          	<span class="c-title">规则说明：</span><br/>
	          	<span class="c-content">	
			      1、导入的文件是excel文件。<br/>
			      2、请下载示例模板，按照示例模板中的格式准备数据。<br/>
			      3、只能导入用户的基本信息，并生成用户前台登录账号，不能批量报名课程。<br/>
			      4、模板文件中的密码列，如果填写的话按照模板中填写的密码生成账号，如果不填写则为手机号后六位。
	          	</span>
          	</div>
          	<div>
          		<div class="center-content">
          			<span>请选择要导入的文件</span>
          			<span style="position: relative;">
          			<input style="position: relative;z-index: 1;width: 64px;height: 30px;opacity: 0;filter: alpha(opacity = 0);color: pointer;" type="file" id="imgData" onchange="changeval()" name="imgData"/>
          			<a href="javascript:;" style="position: absolute;top: -7px;left: 0;width: 56px;height: 13px;" class="btn btn-default btn-up" id="chossewenjian">选择文件</a>
          			<input id="fileNames" style="border: 0px;height: 12px;margin-left: 30px;" type="text"/></span>
          			<span><a href="<%=rootPath %>/student/download" style="color:blue;">点击此处下载模板文件</a></span>
          		</div>
          		<div class="center-content">
          			<span>请选择报名课程</span>
          			<span><select id="courseLists" style="width: 185px;">
          				<option>请选择</option>
          				<c:forEach items="${arr }" var="course">
          					<option value="${course.id }">${course.name }</option>
          				</c:forEach>
          			</select></span>
          		</div>
          		<div class="center-content">
          			<span>请选择报名金额</span>
          			<span><input type="text" id="signMoney" name="signMoney" value=""/></span>
          		</div>
          		<div>
          			<span>
          				<a href="javascript:;" class="btn btn-primary validateData">校验数据</a>
          				<a href="javascript:;" class="btn btn-primary backreturn">返      回</a>
          			</span>
          		</div>
          	</div>
          	<div>
          		<div id="returnInfo" style="margin: 10px 0px 10px 0px;">
          			
          		</div>
          		<div>
          			<span>
          				<a href="javascript:;" class="btn btn-primary chooseFile none">重新选择</a>
          				<a href="javascript:;" class="btn btn-primary insertData none">确认导入</a>
          			</span>
          		</div>
          	</div>
        </div>
    </div>
</div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>数据校验中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript">
	function changeval(){
		$("#fileNames").val($("#imgData").val());
	}
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/StudentManage.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>