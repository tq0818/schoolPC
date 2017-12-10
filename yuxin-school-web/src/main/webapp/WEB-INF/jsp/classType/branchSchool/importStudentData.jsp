<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String rootPath=request.getContextPath(); %>

<!doctype html>
<html lang="zh-cn">
<head>
    <title>学员列表</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
      <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/bootstrap/css/bootstrap.min.css"/>
    
    <style type="text/css">
		th{
			text-align: center;
		}
    </style>
    <script type="text/javascript">
    	var rootPath='<%=rootPath %>';
    </script>
</head>

<body>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
     <div class="heading" style="padding: 0px 0px;">
            <h2 class="h5">导入学员数据信息</h2>
            <span class="line"></span>
     </div>
     		<input type="hidden" value="${classType.id }" id="stuClassTypeId"/>
			<input type="hidden" value="${lable }" id="lableTypes"/>
			<input type="hidden" value="${stuMobiles }" id="stuMobiles"/>
			<input type="hidden" value="${registConfig.mobileFlag }" id="mobileSet"/>
			<input type="hidden" value="${registConfig.usernameFlag }" id="userNameSet"/>
	        <div class="user-list">
<!-- 	        	<div style="margin-top: 10px;padding-bottom: 10px;text-align:right;"> -->
<!-- 		        	<span><a href="javascript:;" class="btn btn-primary signUpMany">批量报名</a></span> -->
<!-- 		        </div> -->
		     <div style="height:400px;overflow: auto;">
          	<table class="table table-center" style="text-align: center; font-size: 14px;margin-bottom: 20px;width: 100%;border-collapse: collapse;height: 80px;border: 1px solid #eee;" id="tableList">
				<tr>
					<th width="5%" style="display: none;"></th>
					<th width="10%">手机号</th>
					<th width="8%">姓名</th>
					<th width="12%">证件号码</th>
					<th width="10%">创建时间</th>
					<th width="10%">前台登录账号</th>
					<th width="10%">前台账号状态</th>
					<th width="5%">报名状态</th>
				</tr>
				<tr><td colspan="7">暂无数据</td></tr>
		</table>
		</div>
		<div style="text-align: center;">
			<a href="javascript:;" class="btn btn-primary signUpMany">批量报名</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=rootPath %>/branchSchool/importStudents/${classType.id }/${lable }" class="btn btn-primary">继续导入</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=rootPath %>/branchSchool/studentList/${classType.id }/${lable }" class="btn btn-primary">返回</a></div>
        </div>
    </div>
</div>


<!-- 添加模块弹窗 -->
<div class="upload-layer" style="display: none;width: 550px;height: 300px;top: 70%;left: 70%;">
   <div class="popupwin-title">
		<h2 class="h5"></h2>
		<i class="close iconfont close_choose" style="margin: -20px 5px 0px 0px;"></i>
	</div>
   <div class="main">
   	  <ul class="tabsn c2">
   	      <li mark="all" style="margin-left: 0px;" class="b1 signStudents">全部报名</li>
		  <li mark="part" style="margin-left: 0px;list-style: none;" class="b1 signStudents">报名新增学员</li>
	  </ul>
   </div>
</div>
<div class="add-layer-bg" style="display: none;"></div>

<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/branchschool/importlist.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>