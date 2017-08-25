<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="zh-cn">
<head>
<title>题库</title>
<style type="text/css">
.register {
	position: fixed;
	left: 50%;
	top: 50%;
	width: 400px;
	height: 400px;
	margin-left: -200px;
	margin-top: -200px;
	padding-bottom: 15px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
	z-index: 999
}
.none{
display: none;
}
.register .reg-close {
  position: absolute;
  top: 12px;
  right: 12px;
  width: 12px;
  height: 12px;
  background-repeat: no-repeat;
  background-position: 0 0;
  cursor: pointer;
}
.register .reg-title {
  padding: 15px 30px;
  border-bottom: 1px solid #e5e5e5;
}
.register .reg-form {
  padding: 0 60px;
}
.register .reg-bottom {
  padding: 2px 52px;
  border-top: 1px solid #e5e5e5;
}
.mark-bg {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: #fff;
  background-color: rgba(255,255,255,0.6);
  opacity: .6 \9;
  filter: alpha(opacity = 60);
}
a:link {
 font-size: 12px;
 text-decoration: none;
}
a:visited {
 font-size: 12px;
 text-decoration: none;
}
a:hover {
 font-size: 12px;
 text-decoration: none;
}
.liColor{
	background-color: #D6D6D6;
}
.tiku .classes-type span {
	width:100%;
}
</style>
<script type="text/javascript"
	src="<%=rootPath%>/plugins/dragsort/jquery-list-dragsort.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/common/message.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/tiku/chapter/chapter.js"></script>
</head>
<body>
	<!-- header start -->
	</header>
	<!-- header end -->
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/tiku/tikuHeader.jsp"></jsp:include>
<div class="u-wrap tiku">
<div class="mainbackground nopadding">
<div class="classes-type">
	<p class="c">
		<span class="t-content" id="subList">
			<c:forEach items="${subList}" var="sub" varStatus="index">
				<c:if test="${index.index == 0 }">
					<a href="javascript:void(0)" class="btn btn-sm btn-default btn-success subject" subId="${sub.id}">${sub.subjectName}</a>
				</c:if>
				<c:if test="${index.index != 0 }">
					<a href="javascript:void(0)" class="btn btn-sm btn-default subject"	subId="${sub.id}">${sub.subjectName}</a>
				</c:if>
			</c:forEach>
		</span>
	</p>
</div>
</div>
</div>
	<div class="u-wrap tiku">
		<div class="mainbackground nopadding ">
			<div class="parts">
				<div class="p-1">
					<div class="heading">
						<h2 class="h5">章</h2>
						<span class="line"></span>
					</div>
					<div class="list">
						<ul>
							<ul id="chapterInfo">
							
							</ul>
							<li class="addCBtn">
								<a href="javascript:void(0)" class="btn btn-sm btn-success">添加一章</a>
							</li>
							<li class="addCConter none">
								<input type="text" id="addChapterName" maxlength="22" style="width: 153px;"/>
								<a href="javascript:void(0)" class="btn btn-sm btn-success">保存</a>
								<a href="javascript:void(0)" class="btn btn-sm btn-default">取消</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="p-2">
					<div class="heading">
						<h2 class="h5">节</h2>
						<span class="line"></span>
					</div>
					<div class="list">
						<ul>
							<ul id="sectionInfo">
							
							</ul>
							<li class="addSBtn">
								<a href="javascript:void(0)" class="btn btn-sm btn-success">添加一节</a>
							</li>
							<li class="addSConter none">
								<input type="text" id="addSectionName" maxlength="22" style="width: 153px;"/>
								<a href="javascript:void(0)" class="btn btn-sm btn-success">保存</a>
								<a href="javascript:void(0)" class="btn btn-sm btn-default">取消</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="p-3">
					<div class="heading">
						<h2 class="h5">考点</h2>
						<span class="line"></span>
					</div>
					<div class="list">
						<ul>
							<ul id="pointInfo">
							
							</ul>
							<li class="addPBtn">
								<a href="javascript:void(0)"  class="btn btn-sm btn-success">添加考点</a>
							</li>
							<li class="addPConter none">
								<input type="text" id="addPointName" maxlength="50" style="width: 153px;"/>
								<a href="javascript:void(0)" class="btn btn-sm btn-success">保存</a>
								<a href="javascript:void(0)" class="btn btn-sm btn-default">取消</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div>
		<input type="hidden" value="${tikuId}" id="tikuId"/>
	</div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<!-- footer start -->

	<!-- footer end -->
	<script type="text/javascript">
	$(function(){
		$(".tiHeader .navspace li>a:eq(2)").addClass("active");
		$selectMenu('tiku_header');
		$selectSubMenu('tiku_exampoint');
	});
</script>
</body>
</html>