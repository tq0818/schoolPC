<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp"%>
<title>课程设置</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/classes.css">
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/classSet/classSet.css">
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/common/message.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/class/classSet.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<div class="u-wrap classes">
		<div class="mainbackground nopadding">
			<div class="heading">
				<h2 class="h5">课程有效期设置</h2>
				<span class="line"></span>
			</div>
			<div class="classTra">
				说明：课程有效期设置包括三个级别，开启不同的级别有不同的限制，如果开启多个级别则以低级别的配置为准。
				比如，同时开启公司级别和课程级别，那么以课程级别为准。0表示无限次数。
			</div>
			<div class="c-main" style="width:95%;">
				<div class="s-title">
					<h2 class="h6">公司级别设置<span class="kg kgCom" level="com"></span></h2>
				</div>
				<div class="s-content comContent none ml58">
						
				</div>
				
				<div class="s-title">
					<h2 class="h6">学科级别设置<span class="kg kgItem" level="item"></span></h2>
				</div>
				<div class="c-content ml58 none itemContent">
						<ul id="subInfo">
							
						</ul>
					<p class="c" style="margin-left: -16px;">
							<a href="javascript:;" class="btn btn-mini btn-success" id="itemSave">保存</a>
					</p>
				</div>

				<div class="s-title">
					<h2 class="h6">课程级别设置<span class="kg kgCla" level="cla"></span></h2>
				</div>
				<div class="s-content ml58">
					<p class="c d">
					说明：开启配置后,在创建课程的时候可以为每个课程指定有效 期和观看次数。
					</p>
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
		<div>
			<input type="hidden" id="comLS" value="${comLS}" /> 
			<input type="hidden" id="itemLS" value="${itemLS}" />
			<input type="hidden" id="claLS" value="${claLS}" />
		</div>
		<script type="text/javascript">
			$(function() {
				if($(".tiHeader .navspace li").length == 1){
					$(".tiHeader .navspace li>a:eq(0)").addClass("active");
				}else{
					$(".tiHeader .navspace li>a:eq(3)").addClass("active");
				}
				$selectMenu('course_validity_set');
			});
		</script>
</body>
</html>