<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>学员列表</title>
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
<form action="<%=rootPath %>/excelImportStudents/queryData" id="DataForm" method="post">
	<input type="hidden" id="stuMobiles" value="" name="stuMobiles"/>
	<input type="hidden" id="xuesheng" value="1" name="xuesheng"/>
</form>
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
					3、如果导入的用户不存在则会自动生成网校前台登录账号。<br/>
					4、导入用户密码默认为手机号后六位。<br/>
					5、导入的手机号应为11位的有效手机号，且不能是已存在的手机号。<br/>
					<!-- 6、如果导入的用户名存在，会提示用户名已存在。<br/> -->
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
          			<a href="javascript:;" style="position: absolute;top: -7px;left: 0;width: 56px;height: 13px;" class="btn btn-default btn-up" id="chossewenjian">选择文件</a>
          			<input id="fileNames" style="border: 0px;height: 12px;margin-left: 30px;" type="text"/></span>
          			<span><a href="<%=rootPath %>/student/download" style="color:blue;">点击此处下载模板文件</a></span>
          		</div>
          		<div>
          			<span>
          				<a href="javascript:;" class="btn btn-primary student-validate">校验数据</a>
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
          				<!-- <a href="javascript:;" mark="update" class="btn btn-primary allupdate student-import none">全部更新并导入</a>
          				<a href="javascript:;" mark="insert" class="btn btn-primary newinsert student-import none">只导入新增学员</a> -->
          				<a href="javascript:;" mark="insert" class="btn btn-primary chooseInsert student-import none">确认导入</a>
          			</span>
          		</div>
          	</div>
        </div>
        
        <!-- 学员列表 -->
         <div class="user-list none">
          	<table class="table table-center">
				<tr>
					<th width="10%">手机号</th>
					<th width="8%">姓名</th>
					<th width="12%">证件号码</th>
					<th width="10%">创建时间</th>
					<th width="10%">注册时间</th>
					<th width="5%">注册状态</th>
					<th width="10%">注册方式</th>
					<th width="5%">报名状态</th>
					<th width="20%">操作</th>
				</tr>
				<tr><td colspan="9">暂无数据</td></tr>
		</table>
			<div class="pages pagination"></div>
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
	function changeval(){
		$("#fileNames").val($("#imgData").val());
	}
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/importStudent.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/studentImport.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/selectStudentGroup.js"></script>
</body>
</html>