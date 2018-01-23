<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>导入学员</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
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
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<input type="hidden" id="markUrl" value=""/>
<form action="<%=rootPath %>/otherSchool/queryData" id="DataForm" method="post">
	<input type="hidden" value="${classType.id }" id="stuClassTypeId" name="id"/>
	<input type="hidden" value="${lable }" id="lableTypes" name="lable"/>
	<input type="hidden" id="stuMobiles" value="" name="stuMobiles"/>
</form>

<jsp:include page="/WEB-INF/jsp/classType/otherSchool/commonTitle.jsp"></jsp:include>
<div class="u-wrap company overflow">
	<jsp:include page="/WEB-INF/jsp/classType/otherSchool/commonClass.jsp"></jsp:include>
	<div class="right-side" style="background-color: white;">

		    <div class="mainbackground nopadding" style="min-height: 546px;">
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
							 3、如果导入的用户不存在则会自动生成网校前台登录账号。<br/>
							 4、模板文件中的密码列，如果填写的话按照模板中填写的密码生成账号，如果不填写则为手机号后六位。<br/>
							 5、当手机号为空时，密码默认为6个1。<br/>
							 6、如果导入的用户存在，会提示更新用户数据，但是用户之前的密码不会更改。<br/>
							 <%--7、选择导入文件后先校验数据，再进行数据导入，导入成功后可以选择是否为这批用户报名课程。--%>
			          	</span>
			          	<c:if test="${sgOpen==1 }">
				          	<div style="margin-top: 20px;">
				          	分组： <select id="studentG1" name="studentGroup1" onchange="javaScript:selectGroup2(this,'');">
						          </select>
						          <select id="studentG2" name="studentGroup2">
						          </select>
						    </div>
					    </c:if>
		          	</div>
		          	<div>
		          		<div class="center-content">
          					<span>请选择要导入的文件</span>
		          			<span style="position: relative;">
		          			<input style="position: relative;z-index: 1;width: 64px;height: 30px;opacity: 0;filter: alpha(opacity = 0);color: pointer;" type="file" id="imgData" onchange="changeval()" name="imgData"/>
		          			<a href="javascript:;" style="position: absolute;top: -7px;left: 0;width: 56px;height: 13px;" class="btn btn-default btn-up" id="chossewenjian" style="display: block;">选择文件</a>
		          			<input id="fileNames" style="border: 0px;height: 12px;margin-left: 30px;" type="text"/></span>
		          			<span><a href="<%=rootPath %>/student/download" style="color:blue;">点击此处下载模板文件</a></span>
		          		</div>
		          		<div>
		          			<span>
		          				<a href="javascript:;" class="btn btn-primary student-validate">校验数据</a>
		          			</span>
		          		</div>
		          	</div>
		          	<div>
		          		<div id="returnInfo" style="margin: 10px 0px 10px 0px;">
		          			
		          		</div>
		          		<div>
		          			<span>
		          				<a href="javascript:;" class="btn btn-primary chooseFile none">重新选择</a>
		          				<a href="javascript:;" mark="update" class="btn btn-primary allupdate student-import none">全部更新并导入</a>
		          				<a href="javascript:;" mark="insert" class="btn btn-primary newinsert student-import none">只导入新增学员</a>
		          				<a href="javascript:;" mark="insert" class="btn btn-primary chooseInsert student-import none">确认导入</a>
		          			</span>
		          		</div>
		          	</div>
		        </div>
		    </div>

    </div>
</div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading check" style="display:none">
	<p><i></i>数据校验中,请稍后...</p>
</div>
<div class="loading lp-units-loading insert" style="display:none">
	<p><i></i>数据导入中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript">
	$(document).ready(function(){
		$chooseMenu("studentsCode");
		$("#chossewenjian").on('click',function(){
			$("#imgData").trigger('click');
		});
	});
	function changeval(){
		$("#fileNames").val($("#imgData").val());
	}
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/otherSchool/StudentManage.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/studentImport.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/selectStudentGroup.js"></script>
</body>
</html>