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
	<style type="text/css">
		.pages li.disabled{padding:0px;}
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
					<h2 class="h5">${school.itemValue}学员列表</h2>
					<span class="line"></span>
				</div>
				<form method="post" id="searchForm">
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
						<span>创建时121间</span>
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
							<c:if test="${isArea ne 1}">
							<a href="javascript:;" class="btn btn-primary addStudent">添加用户</a>
							<a href="javascript:;" class="btn btn-primary importexcle" >导入用户</a></c:if>
							<a href="javascript:;" class="btn btn-primary exportExcleSchool">导出数据</a></span>
					</div>
				</form>
				<div class="user-list">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
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
							<%--<th width="5%">报名状态</th>--%>
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
							<div class="form-group" >
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
										<c:if test="${classTeacher ne 1 }">
											<option value="">请选择当前学段</option>
											<c:forEach items="${steps}" var="step">
												<option value="${step.itemCode}">${step.itemValue}</option>
											</c:forEach>
										</c:if>
										<c:if test="${classTeacher eq 1 }">
											<option value="${step.itemCode}">${step.itemValue}</option>
										</c:if>
									</select>
									<select name="eduYear" id="addEduYear" style="float: left">
										<c:if test="${classTeacher ne 1 }">
											<option value="">请选择入学年份</option>
											<c:forEach items="${years}" var="year">
												<option value="${year}">${year}</option>
											</c:forEach>
										</c:if>
										<c:if test="${classTeacher eq 1}">
											<option value="${year}">${year}</option>
										</c:if>
									</select>
									<select name="eduClass" id="addEduClass">
										<c:if test="${classTeacher ne 1 }">
											<c:forEach begin="1" end="30" varStatus="index">
												<option value="${index.index}">${index.index}班</option>
											</c:forEach>
										</c:if>
										<c:if test="${classTeacher eq 1 }">
											<option value="${eduClassIndex}">${eduClassIndex}班</option>
										</c:if>
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
</body>
</html>