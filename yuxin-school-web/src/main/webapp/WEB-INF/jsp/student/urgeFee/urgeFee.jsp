<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>催缴</title>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <link href="<%=rootPath  %>/plugins/select2/select2_metro.css" rel="stylesheet" type="text/css"  />
<style type="text/css">
	.select2-choice {width:200px;}
</style>
</head>
<body>
	<input type="hidden" name="itemOneId" id="itemOneId" value="${one }" />
	<input type="hidden" name="itemSecondId" id="itemSecondId" />
	<input type="hidden" name="classTypeId" id="classTypeId" />
	<input type="hidden" name="examTermId" id="examTermId" />
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>

	<div class="u-wrap student">
		<div class="mainbackground">
			<div class="heading">
				<h2 class="h5">催缴</h2>
				<!-- <div class="search">
					<i class="tips" id="tip">没有查到相关信息!</i> <input type="text"
						id="mobile" class="input-ctrl"> <input type="button"
						class="btn btn-sm" onclick="toSearch();" value="搜索">
				</div> -->
				<span class="line"></span>
			</div>
			<div class="select-option select-inline clear">
				<ul id="qwe">
					<li><span class="s-title">学科</span> <span class="s-list" id="itemOne"> 
						<!-- <a href="javascript:;" name="itemOne" fType="itemOne"
										fValue="" class="btn btn-mini btn-default ">全部</a> -->
						<select>
							<option fType="itemOne" fValue="">全部</option>
							<c:forEach items="${itemOnes }" var="itemOne"
								varStatus="status">
									<option  name="itemOne" fType="itemOne" fValue="${itemOne.id }">${itemOne.itemName }</option>
							</c:forEach>
						</select>
					</span></li>
					<li><span class="s-title">学科小类</span> <span class="s-list" id="itemTwo"> 
						<select>
							<option fType="itemTwo" fValue="">全部</option>
							<c:forEach items="${itemTwos }" var="itemTwo"
								varStatus="status">
									<option  name="itemTwo" fType="itemTwo" fValue="${itemTwo.id }">${itemTwo.itemName }</option>
							</c:forEach>
						</select>
					</span></li>
					<li><span class="s-title">课程</span> <span class="s-list" id="classType"> 
						<select>
							<option fType="classType" fValue="">全部</option>
							<c:forEach items="${classTypeList }" var="classType">
								<option fType="classType" fValue="${classType.id }">${classType.name }</option>
							</c:forEach>
						</select>
					</span></li>
					<li><span class="s-title">考试时间</span> <span class="s-list" id="term"> 
						<select>
							<option fType="term" fValue="">全部</option>
							<c:forEach items="${terms }" var="term" varStatus="status">
								<option fType="term" fValue="${term.id }" >${term.termName }</option>
							</c:forEach>
						</select>
					</span></li>
				</ul>
			</div>
			<div class="student-list">
				<div class="list-title">
					<h2 class="h6">
						<i class="iconfont">&#xe614;</i>催缴学员列表
					</h2>
				</div>
				<div class="list-content" id="message"></div>
			</div>
		</div>
	</div>
	<div class="pages" id="firstItemPage">
	</div>
	<script type="text/javascript" src="<%=rootPath%>/plugins/select2/select2.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/select2/select2_locale_zh-CN.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/student/urgeFee/urgeFee.js"></script>
</body>
</html>