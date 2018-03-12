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
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/classList.css">
	<style type="text/css">
		.pages li.disabled{padding:0px;}
		.changePw,.updateStudentPopup{left: 60%;margin-left: -450px;}
		.tips{
			color: red;
		}
		.xingMark{background: url("../../../images/xing.png") no-repeat left 3px ;
			padding-left: 7px;}
		.tableSecond th{
			overflow: hidden;
			text-overflow:ellipsis;
			white-space: nowrap;
			max-width: 80px;
		}
		.tableFirst,.tableSecond{
			width:49%;
			float:left;
		}
		table{display: table;}
		.classNo{
			display: none;width: 49%; text-align: center;border: 1px solid #666;
			margin: 20px 0 10px;border: 1px solid #ddd;
			height: 374px;
		}
		.table{margin: 0 !important;}
		.tableFirst{height: 374px;border: 1px solid #ddd;width: 49.5%;display: inline-block;margin-top: 20px;}
	</style>
</head>
<body>
<input type="hidden" id="schoolId" value='${schoolId}'/>
		<input type="hidden" id="schoolName" value='${schoolName}'/>
		<input type="hidden" id="isAdmin" value='${isAdmin}'/>
		<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>	
		<input type="hidden" id="sourceFromStatic" value='1'/>	
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_org.jsp"></jsp:include>
	<div class="u-wrap query overflow">
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query_org.jsp"></jsp:include>
		<div class="right-side set-system">
			<div class="mainbackground nopadding">
				<div class="heading">
					<c:if test="${role==1}">
						<h2 class="h5 active">学员列表</h2>
						<i class="markTitle"></i>
						<h2 class="h5 studentListTitle " >班级学生列表</h2>
					</c:if>
					<c:if test="${role==2}">
						<h2 class="h5 active">用户列表</h2>
						<i class="markTitle"></i>
						<h2 class="h5 studentListTitle " >班级学生列表</h2>
					</c:if>
					<c:if test="${role!=1 and role != 2}">
						<h2 class="h5 active">${school.itemValue}学员列表</h2>
						<i class="markTitle"></i>
						<h2 class="h5 studentListTitle " >班级学生列表</h2>
					</c:if>
					<%--<h2 class="h5 active">${school.itemValue}学员列表</h2>
					<i class="markTitle"></i>
					<h2 class="h5 studentListTitle " >班级学生列表</h2>--%>
					<span class="line"></span>
				</div>
				<form method="post" id="searchForm" class="studentListContent">
					<div>
						<%--<input type="text" id="stuMobile" name="mobile" placeholder="手机号" maxlength="11"/>--%>
						<%--<input type="text" id="stuusername" name="username" placeholder="用户名"/>--%>
						<input type="hidden" id="eduSchool" name="eduSchool" value='${school.itemCode}'/>
						<c:if test="${role == '2'}">
							<input type="hidden" id="hiddenEduStep" name="hiddenEduStep" value='${eduStep}'/>
							<input type="hidden" id="hiddenEduYear" name="hiddenEduYear" value='${eduYear}'/>
							<input type="hidden" id="hiddenEduClass" name="hiddenEduClass" value='${eduClass}'/>
						</c:if>
						<input type="hidden" id="role" name="role" value='${role}'/>
						<input type="text" id="stuName" name="name" placeholder="姓名"/>
						<input type="hidden" id="sfzh" name="identityId" placeholder="证件号码"/>
						<input type="hidden" id="isStu" name="isStu" value="1"/>
                        <span style="margin-left:30px">当前学段</span>
                       		<c:if test="${role != '2' && role != '3'}">
                       		 	<select id="eduStep" name="eduStep" style="width:150px;">
		                           <option value="">请选择学段</option>
		                           <c:forEach items="${stepList}" var="step">
		                               <option value="${step.itemCode}">${step.itemValue}</option>
		                           </c:forEach>
		                        </select>
                          </c:if> 
                       		<c:if test="${role == '3' }">
                       			<select id="eduStep" name="eduStep" style="width:150px;" onchange="changeLevel(this);">
		                           	<option value="">请选择学段</option>
			                            <c:forEach items="${eduStep}" var="step">
			                               <option value="${step.eduStep}">${step.eduStepName}</option>
			                           </c:forEach>
	                         	</select>  
                          </c:if> 
                          <c:if test="${role == '2' }">
	                          <select id="eduStep" name="eduStep" style="width:150px;">
	                          	<option value="">请选择学段</option>
	                          	<option value="${eduStep}">${eduStepName}</option>
	                          </select>
                          </c:if>
	                        <c:if test="${role!='2' && role!='3' }">
	                        	<select id="eduYear" name="eduYear" style="width:150px;">
	                           		<option value="">请选择入学年份</option>
	                           	</select>
	                        </c:if>
	                         <c:if test="${role=='3' }">
		                         <select id="eduYear" name="eduYear" style="width:150px;" onchange="changeGrade(this);">
			                         	<option value="">请选择入学年份</option>
			                           <c:forEach items="${eduYear}" var="year">
			                               <option value="${year.eduYear}" class="${year.eduStep}" style="display:none;">${year.eduYear}年</option>
			                           </c:forEach> 
		                          </select>
	                        </c:if> 
	                        <c:if test="${role=='2'}">
	                        	<select id="eduYear" name="eduYear" style="width:150px;">
	                        		<option value="">请选择入学年份</option>
	                           		<%-- <option value="${eduYear}">${eduYear}年</option> --%>
	                           	</select>
	                      </c:if>
                        <select id="eduClass" name="eduClass" style="width:150px;">
                        <c:if test="${role!='2' && role!='3' }">
                        	<option value="">请选择班级</option>
                        </c:if>
                        <c:if test="${role=='2'}">
                        	<option value="">请选择班级</option>
                        	<%-- <option value="${eduClass}">${eduClass}班</option> --%>
                        </c:if>
                        <c:if test="${role=='3' }">
                         	<option value="">请选择班级</option>
                           	<c:forEach items="${eduClass}" var="classes">
                               <option value="${classes.eduClass}" class="${classes.eduYear}" style="display:none;">${classes.eduClass}班</option>
                           	</c:forEach> 
	                     </c:if>  
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
						<span class="fr">
							<a href="javascript:;" class="btn btn-primary addStudent" id="addStudentNew">添加用户</a>
							<a href="javascript:;" class="btn btn-primary importexcle" >导入用户</a>
							<a href="javascript:;" class="btn btn-primary exportExcleSchool">导出数据</a></span>
					</div>
				</form>
				<div class="user-list studentListContent">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
							<th width="5%">用户名</th>
							<th width="5%">姓名</th>
							<th width="3%">身份</th>
							<!-- <th width="4%">区域</th> -->
							<!-- <th width="9%">学校</th> -->
							<th width="7%">所在班级</th>
							<c:if test="${userorg_roleopenflag==1 }">
								<shiro:hasAnyRoles name="机构管理员,代理机构">
									<th width="10%">所属代理机构</th>
								</shiro:hasAnyRoles>
							</c:if>
							<th width="5%">创建时间</th>
							<%--<th width="6%">前台登录账号</th>--%>
							<%--<th width="6%">前台账号状态</th>--%>
							<%--<th width="5%">报名状态</th>--%>
							<th width="11%">操作</th>
						</tr>
						<c:choose>
							<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">
								<tr><td colspan="13">暂无数据</td></tr>
							</c:when>
							<c:otherwise>
								<tr><td colspan="12">暂无数据</td></tr>
							</c:otherwise>
						</c:choose>


					</table>
					<div class="pages pagination"></div>
				</div>
				<div class="classListContent">
					<form>
						<ul>
							<li>
								<label for="" class="xingMark">学段</label>
								<select name="" id="eduStep2">
									<c:forEach items="${stepList}" var="step">
										<option value="${step.itemCode}">${step.itemValue}</option>
									</c:forEach>
								</select>
							</li>
							<li>
								<label for="">入学年份</label>
								<select name="" id="eduYear2">
								</select>
							</li>
							<li>
								<label for="">班级</label>
								<select name="" id="eduClass2">
									<option value=''>请选择班级</option>
								</select>
							</li>
							<li>
								<label for="" class="xingMark">学科</label>
								<select name="" id="subject">
									<c:forEach items="${subjectItem}" var="subject">
										<option value="${subject.itemCode}">${subject.itemName}</option>
									</c:forEach>
								</select>
							</li>
							<li>
								<label for="" class="xingMark">课程形式</label>
								<select name="" id="liveFlag">
									<option value="1">直播</option>
									<option value="0">点播</option>
								</select>
							</li>
							<li>
								<a href="##" class="btn btn-mb btn-primary" style="margin-right: 10px;" onclick="findClassStu(0)">查询</a>
								<a href="##" class="btn btn-mb btn-primary">导出</a>
							</li>
						</ul>
					</form>
					<%--<div class="user-list">--%>
					<div class='tipsWord' style="width: 100%;height: 50px;line-height: 110px;text-align: right;">
						<span class="xingMark" style="font-size: 12px;margin-right: 30px;">
							实际观课效果：√表示观课时间超过了70%，✘表示观课时长未超过70%
						</span>
					</div>
					<div style="width:100%;">
						<div class="studentContent" style="width:100%;">
							<div class="tableFirst">
								<table class="table table-center" style="float: right;">
									<tr data-buy="true">
										<th width="8%">姓名</th>
										<th width="7%">班级</th>
										<th width="7%">观课总节数</th>
										<th width="11%">观课总时长（分钟）</th>
									</tr>
									<tbody id="stuListTbody"></tbody>
								</table>
							</div>
							<table  class="table table-center tableSecond">
									<tr id="className" data-buy="true">

									</tr>
									<tbody id="classListTbody"></tbody>
								</table>

							<div class="leftIcon changeIcon" style="display:none;">
								<i id="leftIconBtn" class="icon iconfont ">&#xe650;</i>
							</div>
							<div class="rightIcon changeIcon">
								<i id="rightIconBtn" class="icon iconfont ">&#xe651;</i>
							</div>
							<div class="classNo">
								<img src="<%=rootPath%>/images/classNew.jpg" alt="" style="margin-top: 150px;">
							</div>
						</div>
						<div class="studentNo" style="display: none;width: 100%; text-align: center;">
							<img src="<%=rootPath%>/images/studentNew.jpg" alt="" style="margin-top: 150px;">
						</div>
						<div id="paginationStuList" class="pages pagination"></div>
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
			<div class="popupwin addStudentPopup" style="width: 900px; height: auto; top: 2%; left: 60%; display: none;" data-pupwin="modal">
				<form id="addStudentForm" novalidate="novalidate">
					<div class="popupwin-title">
						<h2 class="h5">添加用户</h2>
						<i class="close iconfont canclekuang"></i>
					</div>
					<div class="main form-horizontal" id="lsOne">
						<div class="form-body">
							<div class="form-group" style="margin-bottom: 0;">

								<label class="col-md-2 control-label">手机号<i class="iconfont ico" style="color: red;"></i></label>
								<div class="col-md-2">
									<input class="form-control" id="sMobile" name="sMobile" type="text" maxlength="11" aria-required="true">
									<span class="tips" style="color: red; display: none;"></span>
								</div>


								<label class="col-md-2 control-label" style="display:none">用户名<i class="iconfont ico" style="color: red;"></i></label>
								<div class="col-md-2" style="display:none">
									<input type="text" id="suserName" name="suserName" maxlength="30" class="form-control" placeholder="">
									<span class="help-block" style="color:red;"></span>
								</div>

								<label class="col-md-2 control-label">姓名<i class="iconfont ico" style="color: red;"></i></label>
								<div class="col-md-2">
									<input type="text" id="sName" name="sName" maxlength="15" class="form-control" placeholder="" aria-required="true">
									<span class="help-block" style="color:red;"></span>
								</div>
							</div>
							<div class="form-group" style="display: none;">
								<label class="col-md-2 control-label" style="display:none">性别</label>
								<div class="col-md-2" style="margin-top: 7px;display:none;">
									<input type="radio" id="insertman" class="sSex" name="sSex" value="MALE">男
									<input type="radio" id="insertwoman" class="sSex" name="sSex" value="FEMALE">女
								</div>
							</div>

							<div class="form-group" id="add_div_school">
								<label class="col-md-2 control-label">所在区域<i class="iconfont ico" style="color: red;"></i></label>

								<div class="col-md-2">
									<select name="eduArea" id="addEduArea">
										<c:forEach items="${areas}" var="area" >
											<option value="${area.itemCode}" data-id="${area.id}" selected="selected">${area.itemValue}</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-md-2 control-label">所在学校<i class="iconfont ico" style="color: red;"></i></label>

								<div class="col-md-2">
									<select name="eduSchool" id="addEduSchools" data-id="${schoolName.itemCode}">
										<option value="${schoolName.itemCode}" selected="selected">${schoolName.itemValue}</option>
									</select>
									<span class="tips" style="color: red; display: none;"></span>
								</div>
							</div>
							<div class="form-group" id="add_div_class">
								<label class="col-md-2 control-label">所在班级<i class="iconfont ico" style="color: red;"></i></label>
								<div class="col-md-2" style="width: 700px;">
									<select name="eduStep" id="addEduStep">
										<option value="">请选择当前学段</option>
										<c:forEach items="${steps}" var="step">
											<option value="${step.itemCode}">${step.itemValue}</option>
										</c:forEach>
									</select>
									<select name="eduYear" id="addEduYear" style="float: left">
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
							<div class="form-group" style="display:none">
								<label class="col-md-2 control-label">出生日期</label>
								<div class="col-md-2">
									<input class="form-control date-picker " id="sBirth" name="sBirth" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
								<input class="form-control" id="sAge" name="sAge" type="hidden">
								<label class="col-md-2 control-label">户口所在地</label>
								<div class="col-md-2">
									<input class="form-control" id="sRegist" name="sRegist" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
							</div>
							<div class="form-group" style="display:none">
								<label class="col-md-2 control-label">最高学历</label>
								<div class="col-md-2">
									<select name="sEducation" id="sEducation">
										<option value="">请选择</option>
										<option value="UNDER_JUNIOR">大专以下</option><option value="JUNIOR">大专</option><option value="BECHELOR">本科</option><option value="POSTGRADUATE">研究生</option><option value="DOCTOR">博士生及以上</option></select>
								</div>
								<label class="col-md-2 control-label">家庭电话号</label>
								<div class="col-md-2">
									<input class="form-control" id="sTel" name="sTel" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
							</div>

							<div class="form-group" style="display:none">
								<label class="col-md-2 control-label">办公电话</label>
								<div class="col-md-2">
									<input class="form-control" id="sOfficeTel" name="sOfficeTel" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
								<label class="col-md-2 control-label">紧急联系人<i class="iconfont ico" style="color: red;"></i></label>
								<div class="col-md-2">
									<input class="form-control" id="sEmergencyContact" name="sEmergencyContact" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
							</div>
							<div class="form-group" style="display:none">
								<label class="col-md-2 control-label">紧急联系人电话<i class="iconfont ico" style="color: red;"></i></label>
								<div class="col-md-2">
									<input class="form-control" id="sEmergencyPhone" name="sEmergencyPhone" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
								<label class="col-md-2 control-label">邮箱</label>
								<div class="col-md-2">
									<input class="form-control" id="sEmail" name="sEmail" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
							</div>
							<div class="form-group" style="display:none">
								<label class="col-md-2 control-label">QQ号</label>
								<div class="col-md-2">
									<input class="form-control" id="sQQ" name="sQQ" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
								<label class="col-md-2 control-label">微信</label>
								<div class="col-md-2">
									<input class="form-control" id="sWebChat" name="sWebChat" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
							</div>
							<div class="form-group" style="display:none">
								<label class="col-md-2 control-label">地址</label>
								<div class="col-md-3">
					<span class="selectplace clear" id="sAddress">
							<select id="prov"><option value="">请选择</option><option value="北京">北京</option><option value="天津">天津</option><option value="河北">河北</option><option value="山西">山西</option><option value="内蒙古">内蒙古</option><option value="辽宁">辽宁</option><option value="吉林">吉林</option><option value="黑龙江">黑龙江</option><option value="上海">上海</option><option value="江苏">江苏</option><option value="浙江">浙江</option><option value="安徽">安徽</option><option value="福建">福建</option><option value="江西">江西</option><option value="山东">山东</option><option value="河南">河南</option><option value="湖北">湖北</option><option value="湖南">湖南</option><option value="广东">广东</option><option value="广西">广西</option><option value="海南">海南</option><option value="重庆">重庆</option><option value="四川">四川</option><option value="贵州">贵州</option><option value="云南">云南</option><option value="西藏">西藏</option><option value="陕西">陕西</option><option value="甘肃">甘肃</option><option value="青海">青海</option><option value="宁夏">宁夏</option><option value="新疆">新疆</option><option value="香港">香港</option><option value="澳门">澳门</option><option value="台湾">台湾</option><option value="全国">全国</option><option value="国外">国外</option></select>
					    	<select id="city" style="display: none;"></select>
					        <select id="dist" style="display: none;"></select>
						</span>
								</div>

								<div class="col-md-4">
									<input class="form-control" id="sAddressDetail" name="sAddressDetail" type="text">
									<span class="tips" style="color: red; display: none;"></span>
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
									<input class="form-control" id="remark_name" name="remark_name" type="text">
									<span class="tips" style="color: red; display: none;"></span>
								</div>
							</div>

							<div class="form-group" style="text-align: center;">
								<div class="col-md-3" style="width: 100%;padding: 10px 0 0;">
									<input type="button" class="m-btn-red addStudentOk" value="确&nbsp;&nbsp;定">
									<a class="m-btn-default canclekuang" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div><div class="popupwin-bg colsekuang" style="display: none;"></div>
		</div>
		<!-- popupwin 界面结束 -->

		<!-- popupwin 编辑学生界面 开始    -->
		<div class="popupwin-box updateStudentPopup1 clear" style="display: none;">
			<div class="popupwin updateStudentPopup" style="width:900px; height: auto;top:20%;left:60%;" data-pupwin="modal">
				<form id="updateStudentForm">
					<div class="popupwin-title">
						<h2 class="h5">修改用户</h2>
						<i class="close iconfont canclekuang"></i>
					</div>
					<div class="main form-horizontal" id="lsOne">
						<div class="form-body">
							<div class="form-group" style="margin-bottom: 0;">
								<%--<c:if test="${registConfig.mobileFlag==1 }">--%>
									<label class="col-md-2 control-label">手机号<i class="iconfont ico">&#xe605;</i></label>
									<div class="col-md-2">
										<input class="form-control" id="uMobile" name="uMobile" type="text" disabled />
										<span class="tips" style="color:red;"></span>
									</div>
								<%--</c:if>--%>
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
										<c:forEach items="${areas}" var="area" >
											<option value="${area.itemCode}" data-id="${area.id}" selected="selected">${area.itemValue}</option>
										</c:forEach>
									</select>
								</div>
								<label class="col-md-2 control-label">所在学校<i class="iconfont ico">&#xe605;</i></label>
								<div class="col-md-2">
									<select name="eduSchool" id="editEduSchool" data-id="${schoolName.itemCode}">
										<option value="${schoolName.itemCode}" selected="selected">${schoolName.itemValue}</option>
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
			<div class="popupwin changePw" style="width:900px;height: auto;top:20%;" data-pupwin="modal">
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

	</div>
<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/class_student.js"></script>
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
	//$selectSubMenu('statistics_org_detail');
    //    左侧active切换
   // $selectSubMenus('studentList');
	
	function changeGrade (obj){
    	var gradeCode=$(obj).val();
    	$("#eduClass").find("option").each(function(){
    		var optionClass=$(this).attr("class");
    		$(this).attr('selected',false);
    		if(optionClass==''||optionClass==undefined)
    			return;
    		if(gradeCode==optionClass){
    			$(this).attr('style','display:block');
    		}else{
    			$(this).attr('style','display:none');
    		}
    	});
    }
	
	function changeLevel(obj){
    	var levelCode=$(obj).val();
    	$("#eduClass").find("option").each(function(){
    		var optionClass=$(this).attr("class");
    		$(this).attr('selected',false);
    		if(optionClass==''||optionClass==undefined)
    			return;
    	});
    	$("#eduYear").find("option").each(function(){
    		var optionClass=$(this).attr("class");
    		$(this).attr('selected',false);
    		if(optionClass==''||optionClass==undefined)
    			return;
    		if(levelCode==optionClass){
    			$(this).attr('style','display:block');
    		}else{
    			$(this).attr('style','display:none');
    		}
    	});
    }
	
	$(document).ready(function(){
        var currdate = new Date();
        var year = currdate.getFullYear();
        var yearBody = "";
        for(i = 0;i < 12;i++){
            var li ="<option value='"+(year - i)+"'>"+(year - i)+"年</option>";
            yearBody += li;
        }
        $("#eduYear2").append(yearBody);
        var classesBody = "";
        for(i = 1;i <=30; i++){
            var li ="<option value='"+i+"'>"+i+"班</option>";
            classesBody += li;
        }
        $("#eduClass2").append(classesBody);


		 var role='${role}';
		 if(role=='2'){
			 var yearBody ="<option value='"+$("#hiddenEduYear").val()+"'>"+$("#hiddenEduYear").val()+"年</option>";
			 var classesBody ="<option value='"+$("#hiddenEduClass").val()+"'>"+$("#hiddenEduClass").val()+"班</option>";
			 $("#eduYear").append(yearBody);
			 $("#eduClass").append(classesBody);
		 }else if(role=='3'){
			 return;
		 }else{
			 var currdate = new Date();
			 var year = currdate.getFullYear();
			 var yearBody = "";
	         for(i = 0;i < 12;i++){
	           var li ="<option value='"+(year - i)+"'>"+(year - i)+"年</option>";
	           yearBody += li;
	         }
         $("#eduYear").append(yearBody);
         var classesBody = "";
         for(i = 1;i <=30; i++){
           var li ="<option value='"+i+"'>"+i+"班</option>";
           classesBody += li;
         }
         $("#eduClass").append(classesBody);
		 }
	});
</script>
<script>
//	添加用户弹窗
	$('#addStudentNew').click(function () {
		$('.addStudentPopup1').show();
		$('.addStudentPopup').show();
    });
//  修改信息弹窗
	$('table').on('click','.changeInfo',function () {
		$('.updateStudentPopup1').show();
		$('.updateStudentPopup').show();
	});

//  修改密码
$('table').on('click','.changePassword',function () {
    $('.changePw1').show();
    $('.changePw').show();
});


//    tab切换
$('.heading h2').click(function(){
    $(this).addClass('active');
    $(this).siblings('h2').removeClass('active');
    if(!$(this).index()){
        //点击的是学员列表
        $('.studentListContent').show();
        $('.classListContent').hide();
    }else {
        //点击的是班级学生列表
        $('.studentListContent').hide();
        $('.classListContent').show();
        //根据列表的高度设置切换按钮的高度
        var tableHeight = ($('.tableFirst').height()-35)+'px';
        $('.changeIcon').css('height',tableHeight).css('line-height',tableHeight).css('margin-top','75px');
//		$('.classNo').css('height',($('.tableFirst').height()-2)+'px');
    }
});


/* $('.leftIcon').click(function(){
    $('.tableSecond').html("");
    $('.tableSecond').append(contentHtml);
});

//点击下一页
$('.rightIcon').click(function(){
    $('.tableSecond').html("");
    $('.tableSecond').append(contentHtml);
}); */
</script>
</body>
</html>
