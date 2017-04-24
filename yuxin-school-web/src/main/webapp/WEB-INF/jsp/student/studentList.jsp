<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>学员列表</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
     <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    
    <style type="text/css">
   	.tips{
   		color:red;
   	}
    </style>
</head>

<body>
<input type="hidden" value="${isDelete }" id="isDelete"/>
<input type="hidden" value="${registConfig.mobileFlag }" id="mobileSet"/>
<input type="hidden" value="${registConfig.usernameFlag }" id="userNameSet"/>
<input type="hidden" value="${address }" id="addreSet"/>
<input type="hidden" value="${proxyOrgRole }" id="proxyOrgRole"/>
<input type="hidden" value="${userorg_roleopenflag }" id="userorg_roleopenflag"/>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading"> 
            <h2 class="h5">学员列表</h2>
            <span class="line"></span>
        </div>
        <form method="post" id="searchForm">
        <div>
        	<input type="text" id="stuMobile" name="mobile" placeholder="手机号" maxlength="11"/>
        	<input type="text" id="stuusername" name="username" placeholder="用户名"/>
        	<input type="text" id="stuName" name="name" placeholder="姓名"/>
        	<input type="text" id="sfzh" name="identityId" placeholder="证件号码"/>
        	<select id="registStatus" name="status">
        		<option value="">前台账号状态</option>
        		<option value="1">启用</option>
        		<option value="0">禁用</option>
        	</select>
        	<select id="registMethods" name="registType">
        		<option value="">前台登录账号</option>
        		<option value="1">已开通</option>
        		<option value="0">未开通</option>
        	</select>
        	<select id="payStatus" name="paymaterCount">
        		<option value="">报名状态</option>
        		<option value="1">已报名</option>
        		<option value="0">未报名</option>
        	</select>
        	<c:if test="${sgOpen==1 }">
	        	<select id="studentG1" name="groupOneId" onchange="javaScript:selectGroup2(this,'');" >
	        	</select>
	        	<select id="studentG2" name="groupTwoId">
	        	</select>
        	</c:if>
        	
        </div>
        <div style="margin-top: 10px;">
        	<span>创建时间</span>
        	<span><input type="text" name="startTime" class="date-picker from"/><em>到</em><input type="text" name="endTime" class="date-picker to"/></span>
        	<c:if test="${address==1}">
        	<span style="padding:0 15px;" id="caddress">
	        	<select id="prov" name="province"></select> 
		    	<select id="city" name="city"></select>
		        <select id="dist" name="county"></select>
			</span>
			</c:if>
			<c:if test="${userorg_roleopenflag==1 }">
			<shiro:hasAnyRoles name="机构管理员,代理机构">
			<input type="text" id="proxyOrgName" name="proxyOrgName" placeholder="代理机构名称"/>
			</shiro:hasAnyRoles>
			</c:if>
        	<span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
        </div>
        <div style="margin-top: 10px;text-align:right;padding:0 10px;">
        	<span><a href="javascript:;" class="btn btn-primary addStudent">添加用户</a></span>
        	<span><a href="javascript:;" class="btn btn-primary importexcle" target="_blank">导入用户</a></span>
        	<span><a href="javascript:;" class="btn btn-primary exportexcle">导出用户</a></span>
        	<span><a href="javascript:;" class="btn btn-primary signUpMany">批量报名</a></span>
        	<c:if test="${showFlag=='show' }">
        		<span><a href="javascript:;" class="btn btn-primary exportStudentDatas">导出学员报名数据</a></span>
        	</c:if>
        </div>
        </form>
        <div class="user-list">
          	<table class="table table-center" id="tableList">
				<tr data-buy="true">
					<th width="3%"><input type="checkbox" class="checkboxAll"></th>
					<th width="8%">手机号</th>
					<th width="8%">用户名</th>
					<th width="8%">姓名</th>
					<th width="10%">证件号码</th>
					<c:if test="${userorg_roleopenflag==1 }">
					<shiro:hasAnyRoles name="机构管理员,代理机构">
					<th width="10%">所属代理机构</th>
					</shiro:hasAnyRoles>
					</c:if>
					<th width="8%">创建时间</th>
					<th width="6%">前台登录账号</th>
					<th width="6%">前台账号状态</th>
					<th width="5%">报名状态</th>
					<th width="10%">操作</th>
				</tr>
				<c:choose>
					<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">
						<tr><td colspan="11">暂无数据</td></tr>
					</c:when>
					<c:otherwise>
						<tr><td colspan="10">暂无数据</td></tr>
					</c:otherwise>
				</c:choose>
				
				
		</table>
			<div class="pages pagination"></div>
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
	<div class="popupwin-box addStudentPopup1 clear" style="display: none;">
	<div class="popupwin addStudentPopup" style="width:1000px; height:auto;top:10px" data-pupwin="modal">
	<form id="addStudentForm">
		<div class="popupwin-title">  
			<h2 class="h5">添加用户</h2>
			<i class="close iconfont canclekuang"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
				    <c:if test="${registConfig.mobileFlag==1 }">
						<label class="col-md-2 control-label">手机号<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input class="form-control" id="sMobile" name="sMobile" type="text" maxlength="11"/>
							<span class="tips" style="color:red;"></span>
						</div>
					</c:if>
					<c:if test="${registConfig.usernameFlag==1 }">
						<label class="col-md-2 control-label">用户名<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input type="text" id="suserName" name="suserName" maxlength="30" class="form-control" placeholder="">
							<span class="help-block" style="color:red;"></span>
						</div>
					</c:if>
				 </div>
					<div class="form-group">
					<label class="col-md-2 control-label">姓名<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input type="text" id="sName" name="sName" maxlength="15" class="form-control" placeholder="">
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
					<input class="form-control" id="sAge" name="sAge" type="hidden" />
					<label class="col-md-2 control-label">户口所在地</label>
					<div class="col-md-2">
						<input class="form-control" id="sRegist" name="sRegist" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">最高学历</label>
					<div class="col-md-2">
						<select name="sEducation" id="sEducation">
							<option value="">请选择</option>
						</select>
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
					<label class="col-md-2 control-label">地址</label>
					<div class="col-md-3">
					<span class="selectplace clear" id="sAddress">
							<select id="prov" ></select> 
					    	<select id="city" ></select>
					        <select id="dist" ></select>
						</span> 
						</div>
						
					<div class="col-md-4">
						<input class="form-control" id="sAddressDetail" name="sAddressDetail" type="text"/>
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group" style="display: none;">
					<label class="col-md-2 control-label">是否生成前台登录账号</label>
					<div class="col-md-2">
						<input type="radio" name="sUserFront" value="0">否
						<input type="radio" name="sUserFront" value="1" checked="checked">是
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">备注</label>
					<div class="col-md-2">
						<input class="form-control" id="remark_name" name="remark_name" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<c:if test="${sgOpen==1 }">
					<div class="form-group">
						<label class="col-md-2 control-label">分组</label>
						<div class="col-md-2" style="width: 350px;">
							<select id="studentG1_add" name="studentGroup1_add" onchange="javaScript:selectGroup2(this,'_add');">
				        	</select>
				        	<select id="studentG2_add" name="studentGroup2_add" style="width: 110px;" >
				        	</select>
						</div>
					</div>
				</c:if>
				<div class="form-group" style="text-align: center;">
                        <div class="col-md-3" style="width: 100%;padding: 10px 0 0;">
                            <input type="button" class="m-btn-red addStudentOk" value="确&nbsp;&nbsp;定"/>
                            <a class="m-btn-default canclekuang" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
                        </div>
                 </div>
			</div>
		</div>
		</form>
	</div>
	</div>

<!-- popupwin 界面结束 -->
<!-- popupwin 编辑学生界面 开始    -->
<div class="popupwin-box updateStudentPopup1 clear" style="display: none;">
	<div class="popupwin updateStudentPopup" style="width:1000px; height: auto;top:10px" data-pupwin="modal">
	<form id="updateStudentForm">
		<div class="popupwin-title">
			<h2 class="h5">修改用户</h2>
			<i class="close iconfont canclekuang"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
				    <c:if test="${registConfig.mobileFlag==1 }">
						<label class="col-md-2 control-label">手机号<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input class="form-control" id="uMobile" name="uMobile" type="text" disabled />
							<span class="tips" style="color:red;"></span>
						</div>
					</c:if>
					<c:if test="${registConfig.usernameFlag==1 }">
						<label class="col-md-2 control-label">用户名<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input type="text" id="uuserName" name="uuserName" maxlength="30" class="form-control" disabled />
							<span class="help-block" style="color:red;"></span>
						</div>
					</c:if>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">姓名<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input type="hidden" id="uId" value="">
							<input type="text" id="uName" name="uName" maxlength="15" class="form-control" placeholder="" >
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
					<input class="form-control" id="uAge" name="uAge" type="hidden" />
					<label class="col-md-2 control-label">户口所在地</label>
					<div class="col-md-2">
						<input class="form-control" id="uRegist" name="uRegist" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">最高学历</label>
					<div class="col-md-2">
						<select name="uEducation" id="uEducation">
							<option value="">请选择</option>
						</select>
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
				<div class="form-group">
					<label class="col-md-2 control-label">地址</label>
					<div class="col-md-3">
					<span class="selectplace clear" id="uAddress">
							<select id="prov" ></select> 
					    	<select id="city" ></select>
					        <select id="dist" ></select>
						</span> 
						</div>
						
					<div class="col-md-4">
						<input class="form-control" id="uAddressDetail" name="uAddressDetail" type="text"/>
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group isUserFront" style="display: none;">
					<label class="col-md-2 control-label">是否生成前台登录账号</label>
					<div class="col-md-2">
						<input type="radio" name="uUserFront" value="0">否
						<input type="radio" name="uUserFront" value="1" checked="checked">是
					</div>
				</div>
					<div class="form-group">
					<label class="col-md-2 control-label">备注</label>
					<div class="col-md-2">
						<input class="form-control remark_names" id="remark_name"  name="remark_name" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<c:if test="${sgOpen==1 }">
					<div class="form-group">
						<label class="col-md-2 control-label">分组</label>
						<div class="col-md-2" style="width: 350px;">
							<select id="studentG1_edit" name="studentGroup1_edit" onchange="javaScript:selectGroup2(this,'_edit');">
				        	</select>
				        	<select id="studentG2_edit" name="studentGroup2_edit" style="width: 110px;">
				        	</select>
						</div>
					</div>
				</c:if>
				<div class="form-group" style="text-align: center;">
                        <div class="col-md-3" style="width: 100%;padding: 10px 0 0;">
                            <a class="m-btn-red" href="javascript:update.updateStudent();">确&nbsp;&nbsp;定</a>
                            <a class="m-btn-default canclekuang" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
                        </div>
                 </div>
			</div>
		</div>
		</form>
	</div>
	</div>
<!-- popupwin 编辑学生界面结束 -->
<!-- popupwin 修改密码界面 开始-->
<div class="popupwin-box changePw1 clear" style="display: none;">
	<div class="popupwin changePw" style="width:1000px;height: auto;top:10%" data-pupwin="modal">
	<form id="changePw">
		<div class="popupwin-title">
			<h2 class="h5">修改密码</h2>
			<i class="close iconfont canclekuang"></i>
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
				<div class="form-group" style="text-align: center;">
                        <div class="col-md-3" style="width: 100%;padding: 10px 0 0;">
                            <a class="m-btn-red" href="javascript:changePw.submit();">确&nbsp;&nbsp;定</a>
                            <a class="m-btn-default canclekuang" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
                        </div>
                 </div>
			</div>
		</div>
		</form>
	</div>
</div>	
<form action="" method="post" style="display:none" id="exprotForm">
<input type="text" name="stuId">
</form>	
<!-- popupwin 修改密码界面结束 -->
<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/studentlist.js"></script>
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
<script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/selectStudentGroup.js"></script>
</body>
</html>