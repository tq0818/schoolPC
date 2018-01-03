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
<title>首页页面设置</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/system.css" />

<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/web-index.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
<script type="text/javascript">
	$(function(){
		$selectSubMenus('system_index');
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap set-system company overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_systemconfig.jsp"></jsp:include>
	<div class="right-side">
		<div class="mainbackground space" style="background:#fff;">
		<input type="hidden" id="schoolId" value='${schoolId}'/>
		<input type="hidden" id="schoolName" value='${schoolName}'/>
		<input type="hidden" id="isAdmin" value='${isAdmin}'/>
		<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>
			<div class="title single">
				<h2 class="h6 fl" style="font-size: 14px;">选择分校</h2>
				<div class="sc-type">
				</div>
			</div>

			<div class="sysmodules">
				<div class="heading">
					<h2 class="h5" style="font-size: 14px;">系统模板</h2>
					<span class="line"></span>
				</div>
				<div class="themes-list">
					<ul>
						
					</ul>
				</div>
			</div>
			<%--<div class="mymodules">
				<div class="heading">
					<h2 class="h5" style="font-size: 14px;">我的模板</h2>
					<div class="rb">
						<a href="javascript:void(0);" class="btn btn-mini btn-primary addModule">
						<em class="iconfont">&#xe606;</em> 添加模板</a>
					</div>
					<span class="line"></span>
				</div>
				<div class="themes-list">
					<ul>
					</ul>
				</div>
			</div>--%>
		</div>
		</div>
	</div>
<script type="text/javascript" src="<%=rootPath%>/javascripts/sys-body.js" ></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/jquery.scrollTo-2.1.0/jquery.scrollTo.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/system/sysBody.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
</body>
</html>