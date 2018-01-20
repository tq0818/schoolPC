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
						<span class="fr"><a href="javascript:;" class="btn btn-primary exportExcleSchool">导出数据</a></span>
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
							<th width="5%">报名状态</th>
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
    //    左侧active切换
    $selectSubMenus('studentList');
	
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
		 var role=$("#role").val();
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