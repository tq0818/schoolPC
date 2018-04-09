<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
	.u-wrap .mainbackground{height: 800px;}
	.updateStudentPopup{top: 20% !important;}
	.h5{display: inline-block;margin-right: 10px;cursor: pointer;color: #cccccc;}
	h2.active{color: #6b6b6b;}
	.markTitle{height: 20px;width: 3px;display: inline-block;background: #fa6900;position: absolute;}
	.heading{border-left: none;position: relative;}
	.studentReview{margin-left: 10px !important;}
	.studentReviewContent{display: none;}
	.checkbox{margin: auto !important;}
    </style>
</head>

<body>
<input type="hidden" value="${isArea}" id="isArea"/>
<input type="hidden" value="${roleType}" id="roleType"/>
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
            <h2 class="h5 active">学员列表</h2>
			<i class="markTitle"></i>
			<shiro:hasAnyRoles name="机构管理员,班主任">
				<h2 class="h5  studentReview" >学员审核</h2>
				<span class="line"></span>
			</shiro:hasAnyRoles>
        </div>
        <form method="post" class="studentListAll" id="searchForm">
        <div>
        	<input type="text" id="stuMobile" name="mobile" placeholder="手机号" maxlength="11"/>
        	<input type="text" id="stuusername" name="username" placeholder="用户名"/>
        	<input type="text" id="stuName" name="name" placeholder="姓名"/>
        	<input type="hidden" id="sfzh" name="identityId" placeholder="证件号码"/>
        	<input type="hidden" id="isStu" name="isStu" value="1"/>
			<c:if test="${isArea eq 0 }">
				<select name="eduArea" id="eduArea">
					<option value="">请选择区域</option>
					<c:forEach items="${areas}" var="area" >
						<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
					</c:forEach>
				</select>
			</c:if>
			<c:if test="${isArea eq 0 }">
				<select name="eduSchool" id="eduSchool" data-id="${student.eduSchool}">
					<option value="">请选择学校</option>
				</select>
			</c:if>
			<c:if test="${isArea eq 1 }">
				<select name="eduSchool" id="eduSchool">
					<option value="">请选择学校</option>
					<c:forEach items="${schoolList}" var="schoolList" >
						<option value="${schoolList.itemCode}" data-id="${schoolList.id}" ${student.eduArea==schoolList.itemValue?"selected":""}>${schoolList.itemValue}</option>
					</c:forEach>
				</select>
			</c:if>
			<c:if test="${roleType eq 1}">
				<select name="EduSteps" id="EduSteps">
					<option value="">请选择学段</option>
					<option value="${materTeacher.eduStep}" >${materTeacher.eduStepName}</option>
				</select>
				<select name="EduYears" id="EduYears">
					<option value="">请选择年级</option>
					<option value="${materTeacher.eduYear}" >${materTeacher.eduYear}</option>
				</select>
				<select name="EduClasses" id="EduClasses">
					<option value="">请选择班级</option>
					<option value="${materTeacher.eduClass}">${materTeacher.eduClass}班</option>
				</select>
			</c:if>
			<c:if test="${roleType eq 2}">
				<select name="EduSteps" id="EduSteps" onchange="student.changeLevel(this);">
					<option value="">请选择学段</option>
					<c:forEach items="${eduStepGLY}" var="gly">
						<option value="${gly.eduStep}" >${gly.eduStepName}</option>
					</c:forEach>
				</select>
				<select name="EduYears" id="EduYears" onchange="student.changeGrade(this);">
					<option value="">请选择年级</option>
					<c:forEach items="${eduYearGLY}" var="gly">
						<option value="${gly.eduYear}" class="${gly.eduStep}" style="display:none;">${gly.eduYear}</option>
					</c:forEach>
				</select>
				<select name="EduClasses" id="EduClasses">
					<option value="">请选择班级</option>
					<c:forEach items="${eduClassGLY}" var="gly">
						<option value="${gly.eduClass}" class="${gly.eduYear}" style="display:none;">${gly.eduClass}班</option>
					</c:forEach>
				</select>
			</c:if>
			<c:if test="${roleType eq 3}">
				<select name="eduStep" id="EduSteps">
					<option value="">请选择学段</option>
					<c:forEach items="${eduStepGLY}" var="gly">
						<option value="${gly.eduStep}" >${gly.eduStepName}</option>
					</c:forEach>
				</select>
				<select name="eduYear" id="EduYears">
					<option value="">请选择年级</option>
					<c:forEach items="${eduYearGLY}" var="gly">
						<option value="${gly.eduYear}" >${gly.eduYear}</option>
					</c:forEach>
				</select>
				<select name="eduClass" id="EduClasses">
					<option value="">请选择班级</option>
					<c:forEach items="${eduClassGLY}" var="gly">
						<option value="${gly.eduClass}">${gly.eduClass}班</option>
					</c:forEach>
				</select>
			</c:if>
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
        	<!-- <select id="payStatus" name="paymaterCount">
        		<option value="">报名状态</option>
        		<option value="1">已报名</option>
        		<option value="0">未报名</option>
        	</select> -->
        	<c:if test="${sgOpen eq 1 }">
	        	<select id="studentG1" name="groupOneId" onchange="javaScript:selectGroup2(this,'');" >
	        	</select>
	        	<select id="studentG2" name="groupTwoId">
	        	</select>
        	</c:if>
        	
        </div>
        <div style="margin-top: 10px;">
        	<span>创建时间</span>
        	<span><input type="text" name="startTime" class="date-picker from"/><em>到</em><input type="text" name="endTime" class="date-picker to"/></span>
        	<c:if test="${address eq 1}">
        	<span style="padding:0 15px;" id="caddress">
	        	<select id="prov" name="province"></select> 
		    	<select id="city" name="city"></select>
		        <select id="dist" name="county"></select>
			</span>
			</c:if>
			<c:if test="${userorg_roleopenflag eq 1 }">
			<shiro:hasAnyRoles name="机构管理员,代理机构">
			<input type="text" id="proxyOrgName" name="proxyOrgName" placeholder="代理机构名称"/>
			</shiro:hasAnyRoles>
			</c:if>
        	<span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
        </div>
        <c:if test="${roleType ne 2}">
	        <div style="margin-top: 10px;text-align:right;padding:0 10px;">
	        	<span><a href="javascript:;" class="btn btn-primary addStudent">添加用户</a></span>
	        	<span><a href="javascript:;" class="btn btn-primary importexcle" target="_blank">导入用户</a></span>
	        	<span><a href="javascript:;" class="btn btn-primary exportexcle">导出用户</a></span>
	        	<!-- <span><a href="javascript:;" class="btn btn-primary signUpMany">批量报名</a></span> -->
	        	<c:if test="${showFlag=='show' }">
	        		<span><a href="javascript:;" class="btn btn-primary exportStudentDatas">导出学员报名数据</a></span>
	        	</c:if>
	        </div>
        </c:if>
        </form>
        <div class="user-list studentListAll ">
          	<table class="table table-center" id="tableList">
				<tr data-buy="true">
					<th width="3%"><input type="checkbox" class="checkboxAll"></th>
					<th width="8%">手机号</th>
					<th width="8%">用户名</th>
					<th width="8%">姓名</th>
					<th width="5%">身份</th>
					<th width="8%">区域</th>
					<th width="12%">学校</th>
					<th width="10%">所在班级</th>
					<c:if test="${userorg_roleopenflag==1 }">
					<shiro:hasAnyRoles name="机构管理员,代理机构">
					<th width="10%">所属代理机构</th>
					</shiro:hasAnyRoles>
					</c:if>
					<th width="8%">创建时间</th>
					<th width="6%">前台登录账号</th>
					<th width="6%">前台账号状态</th>
					<!-- <th width="5%">报名状态</th> -->
					<c:if test="${roleType ne 2}">
					<th width="10%">操作</th>
					</c:if>
				</tr>
				<c:choose>
					<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">
						<tr><td colspan="15">暂无数据</td></tr>
					</c:when>
					<c:otherwise>
						<tr><td colspan="14">暂无数据</td></tr>
					</c:otherwise>
				</c:choose>
				
				
		</table>
			<div class="pages pagination"></div>
        </div>
		<div class="studentReviewContent">
			<form  style="display: block;">
				<div>
					<input type="text"  id="mobile" placeholder="手机号">
					<input type="text"   id = "name" placeholder="姓名">
					<c:if test="${roleType eq 1}">
						<select name="EduSteps" id="eduStep3">
							<option value="">请选择学段</option>
							<option value="${materTeacher.eduStep}" >${materTeacher.eduStepName}</option>
						</select>
						<select name="EduYears" id="eduYear3">
							<option value="">请选择年级</option>
							<option value="${materTeacher.eduYear}" >${materTeacher.eduYear}</option>
						</select>
						<select name="EduClasses" id="eduClass3">
							<option value="">请选择班级</option>
							<option value="${materTeacher.eduClass}">${materTeacher.eduClass}班</option>
						</select>
					</c:if>
					<c:if test="${roleType eq 3}">
						<select name="eduStep" id="eduStep3">
							<option value="">请选择学段</option>
							<c:forEach items="${eduStepGLY}" var="gly">
								<option value="${gly.eduStep}" >${gly.eduStepName}</option>
							</c:forEach>
						</select>
						<select name="eduYear" id="eduYear3">
							<option value="">请选择年级</option>
							<c:forEach items="${eduYearGLY}" var="gly">
								<option value="${gly.eduYear}" >${gly.eduYear}</option>
							</c:forEach>
						</select>
						<select name="eduClass" id="eduClass3">
							<option value="">请选择班级</option>
							<c:forEach items="${eduClassGLY}" var="gly">
								<option value="${gly.eduClass}">${gly.eduClass}班</option>
							</c:forEach>
						</select>
					</c:if>

				</div>
				<div style="margin-top: 10px;">
					<span>创建时间</span>
					<span>
								<input type="text" name="startTime" class="date-picker from " id="starTime">
								<em>到</em>
								<input type="text" name="endTime" class="date-picker to" id="endTime">
							</span>
					<span><a href="javascript:;" class="btn btn-primary" id="searchAll" onclick="studentReview(0)">搜索</a></span>
					<span class="fr">
								<a href="javascript:;" class="btn btn-primary batchAudit" >批量审批</a>
							</span>
				</div>
			</form>
			<div  style="display: block;">
				<table class="table table-center" >
					<tr id="reviewTr">
						<th width="3%"><input type="checkbox" id="checkBoxList"></th>
						<th width="5%">手机号</th>
						<th width="5%">用户名</th>
						<th width="5%">姓名</th>
						<th width="5%">学段</th>
						<th width="7%">所在班级</th>
						<th width="7%">创建时间</th>
						<th width="11%">操作</th>
					</tr>
					<tbody id ="review">
					<c:choose>
						<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">
							<tr><td colspan="15">暂无数据</td></tr>
						</c:when>
						<c:otherwise>
							<tr><td colspan="14">暂无数据</td></tr>
						</c:otherwise>
					</c:choose>
					</tbody>
				</table>
				<div class="pages paginationNew" id = "studentReview">

				</div>
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
<div class="popupwin-box addStudentPopup1 clear" style="display: none;">
		<div class="popupwin addStudentPopup" style="width:900px; height:auto;top:10px;" data-pupwin="modal">
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
						<label class="col-md-2 control-label"  style="display:none">用户名<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2"  style="display:none">
							<input type="text" id="suserName" name="suserName" maxlength="30" class="form-control" placeholder="">
							<span class="help-block" style="color:red;"></span>
						</div>
					</c:if>
					<label class="col-md-2 control-label">姓名<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<input type="text" id="sName" name="sName" maxlength="15" class="form-control" placeholder="">
						<span class="help-block" style="color:red;"></span>
					</div>
				 </div>
				<div class="form-group" style="display: none;">
					<%--<label class="col-md-2 control-label">姓名<i class="iconfont ico">&#xe605;</i></label>--%>
						<%--<div class="col-md-2">--%>
							<%--<input type="text" id="sName" name="sName" maxlength="15" class="form-control" placeholder="">--%>
							<%--<span class="help-block" style="color:red;"></span>--%>
						<%--</div>--%>
					<label class="col-md-2 control-label" style="display:none">性别</label>	
						<div class="col-md-2" style="margin-top: 7px;display:none;">
							<input type="radio"  id="insertman" class="sSex" name="sSex" value="MALE" >男
							<input type="radio" id="insertwoman" class="sSex" name="sSex" value="FEMALE">女
						</div>
					</div>

				<div class="form-group" id="add_div_school">
					<label class="col-md-2 control-label">所在区域<i class="iconfont ico">&#xe605;</i></label>
					
						<div class="col-md-2">
							<select name="eduArea" id="addEduArea">
								<option value="">请选择所在区域</option>
								<c:if test="${isArea ==0 }">
									<c:forEach items="${areas}" var="area" >
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
									</c:forEach>
								</c:if>
								<c:if test="${isArea ==1 }">
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
								</c:if>
								<c:if test="${isArea ==2 }">
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
								</c:if>
							</select>
						</div>
					<label class="col-md-2 control-label">所在学校<i class="iconfont ico">&#xe605;</i></label>
					<c:if test="${isArea !=2}">
						<div class="col-md-2">
							<select name="eduSchool" id="addEduSchool" data-id="${student.eduSchool}">
								<option value="">请选择所在学校</option>
							</select>
							<span class="tips" style="color:red;"></span>
						</div>
					</c:if>	
					<c:if test="${isArea ==2 }">
						<select name="eduSchool" id="addEduSchools" data-id="${student.eduSchool}">
							<option value="">请选择所在学校</option>	
							<option value="${schoolName.itemCode}" >${schoolName.itemValue}</option>
						</select>
					</c:if>
				</div>
				<div class="form-group" id="add_div_class">
					<label class="col-md-2 control-label">所在班级<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2" style="width: 700px;">
						<select name="eduStep" id="addEduStep" >
							<%-- <c:if test="${classTeacher ne 1 }"> --%>
							<option value="">请选择当前学段</option>
							<c:forEach items="${steps}" var="step">
								<option value="${step.itemCode}">${step.itemValue}</option>
							</c:forEach>
							<%-- </c:if>
							<c:if test="${classTeacher eq 1 }">
								<option value="${step.itemCode}">${step.itemValue}</option>
							</c:if> --%>
						</select>
						<select name="eduYear" id="addEduYear" style="float: left">
						<%-- <c:if test="${classTeacher ne 1 }"> --%>
							<option value="">请选择入学年份</option>
							<c:forEach items="${years}" var="year">
								<option value="${year}">${year}</option>
							</c:forEach>
						<%-- </c:if>
						<c:if test="${classTeacher eq 1}">
							<option value="${year}">${year}</option>
						</c:if> --%>
						</select>
						<select name="eduClass" id="addEduClass">
						<%-- <c:if test="${classTeacher ne 1 }"> --%>
							<c:forEach begin="1" end="30" varStatus="index">
								<option value="${index.index}">${index.index}班</option>
							</c:forEach>
						<%-- </c:if>
						<c:if test="${classTeacher eq 1 }">
							<option value="${eduClassIndex}">${eduClassIndex}班</option>
						</c:if> --%>
						</select>
					</div>
				</div>
				<div class="form-group" style="display:none">
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
				<div class="form-group" style="display:none">
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
				
				<div class="form-group" style="display:none">
					<label class="col-md-2 control-label">办公电话</label>
					<div class="col-md-2">
						<input class="form-control" id="sOfficeTel" name="sOfficeTel" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">紧急联系人<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<input class="form-control" id="sEmergencyContact" name="sEmergencyContact" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group" style="display:none">
					<label class="col-md-2 control-label">紧急联系人电话<i class="iconfont ico">&#xe605;</i></label>
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
				<div class="form-group" style="display:none">
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
				<div class="form-group" style="display:none">
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
						<input type="radio" name="sUserFront" value="1" checked="checked">是
					</div>
				</div>
				<div class="form-group" style="display:none">
					<label class="col-md-2 control-label">备注</label>
					<div class="col-md-2">
						<input class="form-control" id="remark_name" name="remark_name" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<c:if test="${sgOpen==1 }">
					<div class="form-group" style="display:none">
						<label class="col-md-2 control-label">分组</label>
						<div class="col-md-2" style="width: 350px;">
							<select id="studentG1_add" name="studentGroup1_add" onchange="javaScript:selectGroup2(this,'_add');">
				        	</select>
				        	<select id="studentG2_add" name="studentGroup2_add" style="width: 110px;" >
				        	</select>
						</div>
					</div>
				</c:if>
				<div class="form-group" style="text-align: center;" style="display:none">
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
<div class="popupwin-box updateStudentPopup1 clear" style="display: none;margin-bottom: 0;">
	<div class="popupwin updateStudentPopup" style="width:900px; height: auto;top:20%;" data-pupwin="modal">
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
					<%--<c:if test="${registConfig.usernameFlag==1 }">--%>
						<%--<label class="col-md-2 control-label" style="display:none">用户名<i class="iconfont ico">&#xe605;</i></label>--%>
						<%--<div class="col-md-2">--%>
							<%--<input type="text" style="display:none" id="uuserName" name="uuserName" maxlength="30" class="form-control" disabled />--%>
							<%--<span class="help-block" style="color:red;" style="display:none"></span>--%>
						<%--</div>--%>
					<%--</c:if>--%>
					<label class="col-md-2 control-label">姓名<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<input type="hidden" id="uId" value="">
						<input type="text" id="uName" name="uName" maxlength="15" class="form-control" placeholder="" disabled>
						<span class="help-block" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group" style="display: none;">
					<%--<label class="col-md-2 control-label">姓名<i class="iconfont ico">&#xe605;</i></label>--%>
						<%--<div class="col-md-2">--%>
							<%--<input type="hidden" id="uId" value="">--%>
							<%--<input type="text" id="uName" name="uName" maxlength="15" class="form-control" placeholder="" disabled>--%>
							<%--<span class="help-block" style="color:red;"></span>--%>
						<%--</div>--%>
					<label class="col-md-2 control-label" style="display:none">性别</label>	
						<div class="col-md-2"  style="display:none">
							<input type="radio"  id="updateman" class="uSex" name="uSex" value="MALE">男
							<input type="radio" id="updatewoman" class="uSex" name="uSex" value="FEMALE">女
						</div>
					</div>

				<div class="form-group" style="display:none">
					<label class="col-md-2 control-label">个人身份<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<input type="radio" value="0" name="editeduIdentity" checked="checked" id="edit_eduIdentity_stu">学生
						<!-- <input type="radio" value="1" name="editeduIdentity" id="edit_eduIdentity_normal">普通用户 -->
					</div>
				</div>

				<div class="form-group" id="edit_div_school">
					<label class="col-md-2 control-label">所在区域<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<select name="eduArea" id="editEduArea">
							<option value="">请选择所在区域</option>
							<c:if test="${isArea ==0 }">
									<c:forEach items="${areas}" var="area" >
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
									</c:forEach>
								</c:if>
								<c:if test="${isArea ==1 }">
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
								</c:if>
								<c:if test="${isArea ==2 }">
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
							</c:if>
						</select>
					</div>
					<label class="col-md-2 control-label">所在学校<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<select name="eduSchool" id="editEduSchool" data-id="${student.eduSchool}">
							<option value="">请选择所在学校</option>
						</select>
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group" id="edit_div_class">
					<label class="col-md-2 control-label">所在班级<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2" style="width: 700px;">
						<select name="eduStep" id="editEduStep" >
							<option value="">请选择当前学段</option>
							<c:forEach items="${steps}" var="step">
								<option value="${step.itemCode}">${step.itemValue}</option>
							</c:forEach>
						</select>

						<select name="eduYear" id="editEduYear" style="float: left">
							<option value="">请选择入学年份</option>
							<c:forEach items="${years}" var="year">
								<option value="${year}">${year}</option>
							</c:forEach>
						</select>

						<select name="eduClass" id="editEduClass">
							<c:forEach begin="1" end="30" varStatus="index">
								<option value="${index.index}">${index.index}班</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="form-group" id="li_school" style="display:none">
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
				<div class="form-group" style="display:none">
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
			
				<div class="form-group" style="display:none">
					<label class="col-md-2 control-label">办公电话</label>
					<div class="col-md-2">
						<input class="form-control" id="uOfficeTel" name="uOfficeTel" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
					<label class="col-md-2 control-label">紧急联系人 <i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<input class="form-control" id="uEmergencyContact" name="uEmergencyContact" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group" style="display:none">
					<label class="col-md-2 control-label">紧急联系人电话 <i class="iconfont ico">&#xe605;</i></label>
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
				<div class="form-group" style="display:none">
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
				<div class="form-group" style="display:none">
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
						<input type="radio" name="uUserFront" value="1" checked="checked">是
					</div>
				</div>
				<div class="form-group" style="display:none">
					<label class="col-md-2 control-label">备注</label>
					<div class="col-md-2">
						<input class="form-control remark_names" id="remark_name"  name="remark_name" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<c:if test="${sgOpen==1 }">
					<div class="form-group" style="display:none">
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
	<div class="popupwin changePw" style="width:1000px;height: auto;top:10px" data-pupwin="modal">
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
<script type="text/javascript" src="<%=rootPath%>/javascripts/studentReviewSchool.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var currdate = new Date();
        var year = currdate.getFullYear();
        var yearBody = "";
        for(i = 0;i < 7;i++){
            var li ="<option value='"+(year - i)+"'>"+(year - i)+"年</option>";
            yearBody += li;
        }
//        $("#eduYear3").append(yearBody);
        var classesBody = "";
        for(i = 1;i <=30; i++){
            var li ="<option value='"+i+"'>"+i+"班</option>";
            classesBody += li;
        }
//        $("#eduClass3").append(classesBody);
    });
</script>

</body>
</html>