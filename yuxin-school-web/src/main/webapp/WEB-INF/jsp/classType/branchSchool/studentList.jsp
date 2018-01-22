<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>学员列表</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
     <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    
    <style type="text/css">
   	.tips{
   		color:red;
   	}
    </style>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<input type="hidden" id="lableType" value="${lable }"/>
<input type="hidden" id="isDelete" value="${isDelete }"/>
<input type="hidden" value="${registConfig.mobileFlag }" id="mobileSet"/>
<input type="hidden" value="${registConfig.usernameFlag }" id="userNameSet"/>
<input type="hidden" id="companyId" value="${ct.companyId }"/>
<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonTitle.jsp"></jsp:include>
<div class="u-wrap company overflow">
	<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonClass.jsp"></jsp:include>
    <div class="right-side" style="background-color: white;">
   
		    <div class="mainbackground nopadding" style="min-height: 546px;">
		        <div class="heading clear" data-protocolconfig="${courseProtocolConfig}">
		            <h2 class="h5" style="float:left;">学员列表</h2>
		             <div style="position: absolute;right: 0;top: -8px;padding:0 10px;">
	             		<!-- <span><a href="javascript:;" class="btn btn-primary addStudent">添加学员</a></span>
	             		<span><a href="javascript:;" class="btn btn-primary importexcle">导入学员</a></span> -->
			        	<span><a href="javascript:;" class="btn btn-primary exportexcle">导出学员</a></span>
			        	<span><a href="javascript:;" class="btn btn-primary exportStudyPercent">导出学习进度</a></span>
			        </div>
		            <span class="line"></span>
		        </div>
		        <form method="post" id="searchForm">
		        <div>
		            <input type="hidden" id="classTypeId"  name="classTypeId" value="${ct.id }"/>
		        	<input type="text" id="stuMobile" name="mobile" placeholder="手机号" style="width:100px;"/>
		        	<input type="text" id="stuusername" name="username" placeholder="用户名"/>
		        	<input type="text" id="stuName" name="name" placeholder="姓名" style="width:100px;"/>
		        	<!-- <input type="text" id="sfzh" name="identityId" placeholder="证件号码"/> -->
		        	<select id="registStatus" name="status">
		        		<option value="">前台账号状态</option>
		        		<option value="1">启用</option>
		        		<option value="0">禁用</option>
		        	</select>
		        	<span style="margin-left:15px">创建时间</span>
		        	<span><input type="text" name="startTime" style="width: 80px;" class="date-picker from"/><em>到</em><input type="text" style="width: 80px;" name="endTime" class="date-picker to"/></span>
		        	<%-- <c:if test="${courseProtocolConfig}">
			        	<select id="protocolStatus" name="protocolStatus">
			        		<option value="">协议签署状态</option>
			        		<option value="1">已签署</option>
			        		<option value="2">未签署</option>
			        	</select>
			        	<input type="hidden" value="1" name="protocolConfig">
		        	</c:if> --%>
		        	<span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
<!-- 		        	<select id="registMethods" name="registType"> -->
<!-- 		        		<option value="">前台登录账号</option> -->
<!-- 		        		<option value="1">已开通</option> -->
<!-- 		        		<option value="0">未开通</option> -->
<!-- 		        	</select> -->
<!-- 		        	<select id="payStatus" name="paymaterCount"> -->
<!-- 		        		<option value="">报名状态</option> -->
<!-- 		        		<option value="1">已报名</option> -->
<!-- 		        		<option value="0">未报名</option> -->
<!-- 		        	</select> -->
		        </div>
		        </form>
		        <div class="user-list" style="margin-top: 20px;">
		          	<table class="table table-center" id="tableList">
						<tr>
<!-- 						<th width="5%"><input type="checkbox" class="checkboxAll"></th> -->
							<th width="10%">姓名</th>
							<th width="10%">手机号</th>
							<th width="10%">用户名</th>
							<th width="10%">邮箱</th>
							<th width="10%">报名时间</th>
							<th width="10%">最后登录时间</th>
							<th width="10%">账号状态</th>
							<th width="15%">操作</th>
						</tr>
						<tr><td colspan="8">暂无数据</td></tr>
				</table>
					<div class="pages pagination"></div>
		        </div>
		    </div>
	 
    </div>
</div>


<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<!-- popupwin 界面 开始-->
	<div class="popupwin addStudentPopup" style="width:1000px; height:auto;top:10px" data-pupwin="modal">
	<form id="addStudentForm">
		<div class="popupwin-title">
			<h2 class="h5">添加用户</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-2 control-label">姓名<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input type="text" id="sName" name="sName" class="form-control" placeholder="">
							<span class="help-block" style="color:red;"></span>
						</div>
					<label class="col-md-2 control-label">性别</label>	
						<div class="col-md-2">
							<input type="radio"  id="insertman" class="sSex" name="sSex" value="MALE" >男
							<input type="radio" id="insertwoman" class="sSex" name="sSex" value="FEMALE">女
						</div>
					</div>
				<div class="form-group">
					<label class="col-md-2 control-label">证件类型</label>
					<div class="col-md-2">
						<select id="sIdentity" name="sIdentity">
							<option value="">请选择</option>	
						</select>
					</div>
					<label class="col-md-2 control-label">证件号码</label>
					<div class="col-md-2">
						<input class="form-control" id="sIdentityNum" name="sIdentityNum" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>	
				<div class="form-group">
					<label class="col-md-2 control-label">出生日期</label>
					<div class="col-md-2">
						<input class="form-control date-picker " id="sBirth" name="sBirth" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">年龄</label>
					<div class="col-md-2">
						<input class="form-control" id="sAge" name="sAge" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">户口所在地</label>
					<div class="col-md-2">
						<input class="form-control" id="sRegist" name="sRegist" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">最高学历</label>
					<div class="col-md-2">
						<select name="sEducation" id="sEducation">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label">手机号<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<input class="form-control" id="sMobile" name="sMobile" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">家庭电话号</label>
					<div class="col-md-2">
						<input class="form-control" id="sTel" name="sTel" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">办公电话</label>
					<div class="col-md-2">
						<input class="form-control" id="sOfficeTel" name="sOfficeTel" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">紧急联系人</label>
					<div class="col-md-2">
						<input class="form-control" id="sEmergencyContact" name="sEmergencyContact" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">紧急联系人电话</label>
					<div class="col-md-2">
						<input class="form-control" id="sEmergencyPhone" name="sEmergencyPhone" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">邮箱</label>
					<div class="col-md-2">
						<input class="form-control" id="sEmail" name="sEmail" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">QQ号</label>
					<div class="col-md-2">
						<input class="form-control" id="sQQ" name="sQQ" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">微信</label>
					<div class="col-md-2">
						<input class="form-control" id="sWebChat" name="sWebChat" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">是否生成前台登录账号</label>
					<div class="col-md-2">
						<input type="radio" name="sUserFront" value="0" checked="checked">否
						<input type="radio" name="sUserFront" value="1">是
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-4">
						<a class="m-btn-red"  href="javascript:Form.addTeacher();">确&nbsp;&nbsp;定</a>
						<a class="m-btn-default" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	<!--  <div class="popupwin-bg"></div> -->
<!-- popupwin 界面结束 -->
<!-- popupwin 编辑学生界面 开始-->
	<div class="popupwin updateStudentPopup" style="width:1000px; height: auto;top:10px" data-pupwin="modal">
	<form id="updateStudentForm">
		<div class="popupwin-title">
			<h2 class="h5">修改用户</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-2 control-label">姓名<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input type="hidden" id="uId" value="">
							<input type="text" id="uName" name="uName" class="form-control" placeholder="" >
							<span class="help-block" style="color:red;"></span>
						</div>
					<label class="col-md-2 control-label">性别</label>	
						<div class="col-md-2">
							<input type="radio"  id="updateman" class="uSex" name="uSex" value="MALE">男
							<input type="radio" id="updatewoman" class="uSex" name="uSex" value="FEMALE">女
						</div>
					</div>
				<div class="form-group">
					<label class="col-md-2 control-label">证件类型</label>
					<div class="col-md-2">
						<select id="uIdentity" name="uIdentity">
							<option value="">请选择</option>	
						</select>
					</div>
					<label class="col-md-2 control-label">证件号码</label>
					<div class="col-md-2">
						<input class="form-control" id="uIdentityNum" name="uIdentityNum" type="text"/>
						<span class="tips" style="color:red;"></span>
					</div>
				</div>	
				<div class="form-group">
					<label class="col-md-2 control-label">出生日期</label>
					<div class="col-md-2">
						<input class="form-control date-picker " id="uBirth" name="uBirth" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">年龄</label>
					<div class="col-md-2">
						<input class="form-control" id="uAge" name="uAge" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">户口所在地</label>
					<div class="col-md-2">
						<input class="form-control" id="uRegist" name="uRegist" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">最高学历</label>
					<div class="col-md-2">
						<select name="uEducation" id="uEducation">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-md-2 control-label">手机号<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<input class="form-control" id="uMobile" name="uMobile" type="text" disabled />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">家庭电话号</label>
					<div class="col-md-2">
						<input class="form-control" id="uTel" name="uTel" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">办公电话</label>
					<div class="col-md-2">
						<input class="form-control" id="uOfficeTel" name="uOfficeTel" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">紧急联系人</label>
					<div class="col-md-2">
						<input class="form-control" id="uEmergencyContact" name="uEmergencyContact" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">紧急联系人电话</label>
					<div class="col-md-2">
						<input class="form-control" id="uEmergencyPhone" name="uEmergencyPhone" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">邮箱</label>
					<div class="col-md-2">
						<input class="form-control" id="uEmail" name="uEmail" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">QQ号</label>
					<div class="col-md-2">
						<input class="form-control" id="uQQ" name="uQQ" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">微信</label>
					<div class="col-md-2">
						<input class="form-control" id="uWebChat" name="uWebChat" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group isUserFront">
					<label class="col-md-2 control-label">是否生成前台登录账号</label>
					<div class="col-md-2">
						<input type="radio" name="uUserFront" value="0" checked="checked">否
						<input type="radio" name="uUserFront" value="1">是
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-4">
						<a class="m-btn-red"  href="javascript:update.updateStudent();">修&nbsp;&nbsp;改</a>
						<a class="m-btn-default" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
<!-- popupwin 编辑学生界面结束 -->
<!-- popupwin 修改密码界面 开始-->
	<div class="popupwin changePw" style="width:1000px;height: auto;top:10%" data-pupwin="modal">
	<form id="changePw">
		<div class="popupwin-title">
			<h2 class="h5">修改密码</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
					<input type="hidden" id="userId">
					<label class="col-md-3 control-label">输入新密码<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-3">
							<input type="password" id="nPassword" name="nPassword" class="form-control" placeholder="">
							<span class="help-block" style="color:red;"></span>
						</div>
					</div>
				<div class="form-group">
					<label class="col-md-3 control-label">重复输入新密码<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-3">
						<input class="form-control" id="rPassword" name="rPassword" type="password" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-4">
						<a class="m-btn-red"  href="javascript:changePw.submit();">修&nbsp;&nbsp;改</a>
						<a class="m-btn-default" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
<!-- popupwin 修改密码界面结束 -->

<script type="text/javascript">
$(document).ready(function(){
	$chooseMenu("studentsCode");
	$selectMenu("course_class_type");
});
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/branchschool/studentlist.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
</body>
</html>