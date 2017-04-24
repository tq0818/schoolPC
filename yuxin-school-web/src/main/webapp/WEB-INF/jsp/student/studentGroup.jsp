<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp"%>
<title>学员分组</title>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/system.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/footer.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage-screen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/class-set.css">
<style type="text/css">
.add-first-btn{
	display:block;
	float: right;
    width: 104px;
    height: 27px;
    background-color: #237fd5;
    line-height: 27px;
    text-align: center;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    cursor: pointer;
    font-size: 12px;
    color: #fff;
   	margin-top: -20px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap company overflow">
		<jsp:include page="/WEB-INF/jsp/menu/menu_studentconfig.jsp"></jsp:include>
		<div class="screen-right">
			<div class="screen-right-cont">
				<div class="screen-right-title">
					<h3>
						<i> 学员分组</i> 
						<c:choose>
							<c:when test="${cfs.status==1 }">
								<span class="q-open"><em class="iconfont normal open"></em></span> <span class="i open ">已启用</span>
							</c:when>
							<c:otherwise>
								<span class="q-open"><em class="iconfont normal close"></em></span> <span class="i close ">已禁用</span>
							</c:otherwise>
						</c:choose>
					</h3>
				</div>
				<p class="prompt-font q-font">说明：开启该功能后可以对学员进行分组设置，便于查找、报名。</p>
				<div class="code-cont none" id="q-cont">
					<em class="add-first-btn" mark=""><i class="iconfont">&#xe61c;</i>添加分组</em>
					<div class="add-classify-content" id="studentGroupLists" style="padding-left: 50px;">
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="add-grade-pop-box">
		<div class="add-grade-pop">
			<div class="add-grade-title" id="group_type_name">添加一级分组</div>
			<div class="add-grade-content">
				<p>
					<span>分类名称：</span> 
					<input type="text" maxlength="10" id="new_group_name">
				</p>
				<p class="care">注：最多可输入10个字</p>
				<div class="addpop-btn-box">
					<span class="add-yes">确定</span>
					<span class="add-cancel">取消</span>
				</div>
			</div>
		</div>
	</div>
	
	
	<input type="hidden" id="isok" value=""/>
	<input type="hidden" id="group_xg" value=""/>
	<input type="hidden" id="group_pid" value=""/>
	<input type="hidden" id="group_xgpid" value=""/>
	<input type="hidden" id="groupStatus" value="${cfs.status}"/>
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script src="<%=rootPath%>/javascripts/student/studentGroup.js"></script>
</body>

</html>