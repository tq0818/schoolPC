<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp"%>
<meta charset="UTF-8" />
<title>直播上课统计</title>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/query.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classes.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classedit.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/live-class-count.css" />
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	   <script type="text/javascript">
    	$(function(){
    		$selectMenu("course_class_type");
    	});
    </script>
</head>
<body>
	<!-- 主菜单 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
	<!-- 标题 -->
	<%@include file="/WEB-INF/jsp/classType/commonTitle.jsp"%>
	<input type="hidden"   class="classTypeId"  value="${ct.id }">
	<input type="hidden"   class="studentName" />
	<input type="hidden"   class="mobile_num" />
	<div class="u-wrap company  query overflow">
		<!-- 左边的导航 -->
		<c:choose>
			<c:when test="${plus}"><%@include file="/WEB-INF/jsp/classType/commonClass.jsp"%></c:when>
			<c:otherwise><%@include file="/WEB-INF/jsp/simpleClasses/commonClass.jsp"%></c:otherwise>
		</c:choose>
		<div class="right-side">
			<div class="u-wrap classes live-class-count">
				<div class="mainbackground nopadding">
					<!-- <div class="title-tab">
						<span class="active" mark="join-num">参课人数查询</span> <span
							mark="student-info">学员上课详情</span>
					</div> -->
					<div class="count-content join-num" style="margin-top:10px;">
<!-- 					<form method="post" id="searchForm"> -->
						<div class="count-chose clear">
							<div class="fl">
							
								<input type="text" placeholder="学员手机号/用户名查询" id="mobile" name="mobile"/> 
								课次查询：<select id="classLesson" name="classLessionId">
											<option value="">全部</option>
											<c:forEach var="val" items="${list}">
												<option value="${val.id}">${val.lesson_name}</option>
											</c:forEach>
										</select>
								学习方式：<select id="watchType" name="watchType">
											<option value="">全部</option>
											<option value="0">看直播</option>
											<option value="1">看回放</option>	
										</select>
								<button class="btn btn-sm btn-primary btn-search" id="queryById">查询</button>
								
								<span>总计上课人数：<em class="peoples"></em>人
								</span>
							</div>
							<div class="fr">
								<button class="btn btn-sm btn-primary" id="export">导出列表</button>
							</div>
						</div>
<!-- 						</form> -->
						<div id="lession_list" class="tables">
							<table class="table table-center">
								<colgroup>
									<col width="14.29%">
									<col width="14.29%">
									<col width="14.29%">
									<col width="14.29%">
									<col width="14.29%">
									<col width="7.29%">
									<col width="21.29%">
								</colgroup>
								<tbody>
									<tr>
										<th>课次名称</th>
										<th>学习方式</th>
										<th>上课时间</th>
										<th>手机号</th>
										<th>用户名</th>
										<th>学员名称</th>
										<th>邮箱</th>
									</tr>
								</tbody>
							</table>
							<div class="pages pagination"></div>
						</div>
					</div>
					
						<div class="count-content student-info">
						
						<div class="count-chose clear">
							<div class="fl">
								<input type="text" placeholder="学员手机号/用户名查询" name="mobile" id="mobileDetail"/> <input type="text"
									placeholder="学员名称查询"  name="name" id="nameDetail"/>
								<button class="btn btn-sm btn-primary btn-search" id="searchs">查询</button>
								<span>合计上课次数 / 总课次：<em><span class="countClass">0</span>/<span id="stuBuyLessonCount" style="margin:0;">0</span></em></span>
							</div>
							<div class="fr">
								<button class="btn btn-sm btn-primary" id="exportData">导出列表</button>
							</div>
						</div>
						
						<div class="tables" id="tableMemberDetails">
							<table class="table table-center">
								<colgroup>
									<col width="18%">
									<col width="12.5%">
									<col width="12.5%">
									<col width="12.5%">
									<col width="12.5%">
									<col width="12.5%">
									<col width="12.5%">
									<col width="7%">
								</colgroup>
								<tbody>
									<tr>
										<th>课次名称</th>
										<th>上课时间</th>
										<th>手机号</th>
										<th>用户名</th>
										<th>昵称</th>
										<th>学员名称</th>
										<th>邮箱</th>
										<th>学习方式</th>
									</tr>
								</tbody>
							</table>
							<div class="pages  paginationdetail"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<!--背影遮罩-->
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript">
	$(document).ready(function(){
		$chooseMenu("statisticsCode");
	});
</script>
	<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath%>/javascripts/classedit.js"></script>
	<script src="<%=rootPath%>/javascripts/live-class-count.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</body>
</html>