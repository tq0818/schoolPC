<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
<title>考试列表</title>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classes.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classedit.css" />
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
  background-image: url('../images/index-icons.png');
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
</style>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_tiku.jsp"%>
	<div class="u-wrap company clear">
		<div class="mainbackground nopadding paddno">
			<div class="heading-box">
				<div class="heading">
					<h2 class="h5">考试</h2>
					<span class="line"></span>
					<div class="new-set add-new">
						<em class="iconfont">&#xe61c;</em><span onclick="location.href='<%=rootPath%>/tikuExam/addExam'">添加考试</span>
					</div>
				</div>
			</div>
			<div class="exam-box examnew-mg20" id="info">
				
			</div>
			<div class="pages">
				<ul class="pagination">
				
				</ul>
			</div>
		</div>
	</div>

	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath%>/javascripts/classedit.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
	<script src="<%=rootPath%>/javascripts/tiku/exam/exam.js"></script>
	<script type="text/javascript">
	$(function(){
		$("body").find(".exam").addClass("active");
	})
	</script>
</body>
</html>