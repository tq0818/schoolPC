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
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_org.jsp"></jsp:include>
	<div class="u-wrap query overflow">
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query_org.jsp"></jsp:include>
		<div class="right-side set-system">
			<div class="mainbackground nopadding">
				<div class="heading">
					<h2 class="h5">${school.itemValue}用户列表</h2>
					<span class="line"></span>
				</div>
				<form method="post" id="searchForm">
					<div>
						<input type="hidden" id="eduSchool" name="eduSchool" value='${school.itemCode}'/>
						<input type="hidden" id="role" name="role" value='${role}'/>
						<input type="text" id="stuName" name="name" placeholder="姓名"/>
						<input type="hidden" id="sfzh" name="identityId" placeholder="证件号码"/>
						<input type="hidden" id="isStu" name="isStu" value="1"/>
                        <select id="eduStep" name="eduStep" style="width:150px;">
                           <option value="">请选择身份</option>
                           <option value="0">学生</option>
                           <option value="1">普通用户</option>
                        </select>
                        <span>学段：</span>
						<select name="eduStep" id="eduStep">
							<c:if test="${role != '2' && role != '3'}">
	                           <option value="">请选择学段</option>
	                           <c:forEach items="${stepList}" var="step">
	                               <option value="${step.itemCode}">${step.itemValue}</option>
	                           </c:forEach> 
                          </c:if> 
                       		<c:if test="${role == '3' }">
	                           <option value="">请选择学段</option>
	                            <c:forEach items="${eduStep}" var="step">
	                               <option value="${step.eduStep}">${step.eduStepName}</option>
	                           </c:forEach>  
                          </c:if> 
                          <c:if test="${role == '2' }">
                          	<option value="${eduStep}">${eduStepName}</option>
                          </c:if>
						</select>

						<span>入学年份：</span>
						<select name="eduYear" id="eduYear">
							<c:if test="${role != '2' && role != '3'}">
								<option value="">请选择年份</option>
								<c:forEach items="${years}" var="item" >
									<option value="${item}" >${item}</option>
								</c:forEach>
							</c:if>
							<c:if test="${role=='2' }">
								<option value="${eduYear}">${eduYear}年</option>
							</c:if>	
							<c:if test="${role=='3' }">
	                         	<option value="">请选择入学年份</option>
	                           	<c:forEach items="${eduYear}" var="year">
	                               <option value="${year.eduYear}">${year.eduYear}年</option>
	                           	</c:forEach> 
	                        </c:if>  
						</select>
						<span>班级：</span>
						<select name="eduClass" id="eduClass">
							<c:if test="${role!='2' && role!='3' }">
								<option value="">请选择班级</option>
								<c:forEach begin="1" end="30" varStatus="index">
									<option value="${index.index}">${index.index}班</option>
								</c:forEach>
							</c:if>
						 	<c:if test="${role=='2' }">
	                         	<option value="${eduClass}">${eduClass}班</option>
              			 	</c:if>
							<c:if test="${role=='3' }">
                         	<option value="">请选择班级</option>
                           	<c:forEach items="${eduClass}" var="classes">
                               <option value="${classes.eduClass}">${classes.eduClass}班</option>
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
							<%--<th width="3%"><input type="checkbox" class="checkboxAll"></th>--%>
							<%--<th width="8%">手机号</th>--%>
							<%--<th width="8%">用户名</th>--%>
							<th width="8%">姓名</th>
							<th width="5%">身份</th>
							<th width="8%">手机号</th>
							<th width="8%">创建时间</th>
							<th width="6%">已报名课程数</th>
							<th width="6%">学习课程数</th>
							<th width="5%">已消费金额（元）</th>
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
 	var role=$("#role").val();
	if(role!='2' && role!='3'){
	 $(document).ready(function(){
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
	}); 
	}
</script>
</body>
</html>