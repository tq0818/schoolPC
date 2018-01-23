<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>查询统计</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
<style type="text/css">
		.pages li.disabled{padding:0px;}
   	.tips{
   		color:red;
   	}
	.u-wrap .mainbackground{height: 800px;}
	</style>
</head>
<body>
<input type="hidden" id="schoolId" value='${schoolId}'/>
<input type="hidden" id="schoolName" value='${schoolName}'/>
<input type="hidden" id="isAdmin" value='${isAdmin}'/>
<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics.jsp"></jsp:include>
	<div class="u-wrap query overflow">
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>
		<div class="right-side set-system">
			<div class="mainbackground nopadding">
				<div class="heading">
					<h2 class="h5">学员列表</h2>
					<span class="line"></span>
				</div>
				<form method="post" id="searchForm">
					<div>
						<input type="text" id="stuName" name="name" placeholder="姓名"/>
						<input type="hidden" id="sfzh" name="identityId" placeholder="证件号码"/>
						<input type="hidden" id="isStu" name="isStu" value="1"/>
						<input type="hidden" id="sourceFromStatic" value='1'/>		
						<select name="eduArea" id="eduArea">
							<option value="">请选择区域</option>
							<c:forEach items="${areas}" var="area" >
								<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
							</c:forEach>
						</select>

						<select name="eduSchool" id="eduSchool" data-id="${student.eduSchool}">
							<option value="">请选择学校</option>
						</select>
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
						<span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
						<span><a href="javascript:;" class="btn btn-primary exportexcle" target="_blank">导出数据</a></span>
					</div>

					<div style="margin-top: 10px;text-align:right;padding:0 10px;">
			        	<%--<span><a href="javascript:;" class="btn btn-primary addStudent">添加用户</a></span>--%>

			        </div>
				</form>
				<div class="user-list">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
							<%--<th width="3%"><input type="checkbox" class="checkboxAll"></th>--%>
							<%--<th width="8%">手机号</th>--%>
							<%--<th width="8%">用户名</th>--%>
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
							<%--<th width="10%">操作</th>--%>
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
			</div>
		</div>
		<!-- ajax加载中div开始 -->
		<div class="loading lp-units-loading" style="display:none">
			<p><i></i>加载中,请稍后...</p>
		</div>
		<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
		<!--  ajax加载中div结束 -->
	</div>
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
						<label class="col-md-2 control-label">手机号<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input class="form-control" id="sMobile" name="sMobile" type="text" maxlength="11"/>
							<span class="tips" style="color:red;"></span>
						</div>
						<label class="col-md-2 control-label">用户名<i class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-2">
							<input type="text" id="suserName" name="suserName" maxlength="30" class="form-control" placeholder="">
							<span class="help-block" style="color:red;"></span>
						</div>
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

				<div class="form-group" id="add_div_school">
					<label class="col-md-2 control-label">所在区域<i class="iconfont ico">&#xe605;</i></label>
					
						<div class="col-md-2">
							<select name="eduArea" id="addEduArea">
								<option value="">请选择所在区域</option>
								<c:forEach items="${areas}" var="area" >
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
								</c:forEach>
								<%-- <c:if test="${isArea ==0 }">
									<c:forEach items="${areas}" var="area" >
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
									</c:forEach>
								</c:if>
								<c:if test="${isArea ==1 }">
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
								</c:if>
								<c:if test="${isArea ==2 }">
										<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>
								</c:if> --%>
							</select>
						</div>
					<label class="col-md-2 control-label">所在学校<i class="iconfont ico">&#xe605;</i></label>
					<c:if test="${isArea !=2 }">
						<div class="col-md-2">
							<select name="eduSchool" id="addEduSchoolQuery" data-id="${student.eduSchool}">
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
						<select name="eduStep" id="addEduStepQuery" >
							<option value="">请选择当前学段</option>
							<c:forEach items="${steps}" var="step">
								<option value="${step.itemCode}">${step.itemValue}</option>
							</c:forEach>
						</select>

						<select name="eduYear" id="addEduYearQuery" style="float: left">
							<option value="">请选择入学年份</option>
							<c:forEach items="${years}" var="year">
								<option value="${year}">${year}</option>
							</c:forEach>
						</select>

						<select name="eduClass" id="addEduClass">
							<c:forEach begin="1" end="30" varStatus="index">
								<option value="${index.index}">${index.index}班</option>
							</c:forEach>
						</select>
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
					<label class="col-md-2 control-label">紧急联系人<i class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-2">
						<input class="form-control" id="sEmergencyContact" name="sEmergencyContact" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
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
<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/query_student.js"></script>
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
<script type="text/javascript">
	$selectSubMenu('statistics_org_detail');
    $selectThirdMenu('studentList');
</script>
</body>
</html>