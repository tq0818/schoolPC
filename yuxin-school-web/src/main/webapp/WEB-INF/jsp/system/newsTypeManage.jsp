<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
<title>新闻类型</title>
<script type="text/javascript">
    	$(function(){
    		$selectSubMenu('netschool_news');
    	});
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">新闻类型</h2>
            <span class="line"></span>
            <span class="rb">
                <a href="javascript:void(0);" id="addNewsType" class="btn btn-mini btn-primary"><em class="iconfont">&#xe606;</em>添加新闻类型</a>
                &nbsp;&nbsp;<a href="<%=rootPath %>/sysConfigNews/showNews" class="btn btn-mini btn-primary"><em class="iconfont">&#xe61f;</em>&nbsp;&nbsp;返回</a>
            </span>
        </div>
        <p id="schoolListP">
        	<c:forEach items="${schoolList }" var="school" varStatus="status">
        		<c:if test="${school.id==schoolId }">
        			<input type="hidden" id="schoolId" value="${school.id }"/>
        			<a href="javascript:void(0);" class="btn btn-sm btn-default btn-success" mark="${school.id }">${school.schoolName }</a>
        		</c:if>
        		<c:if test="${school.id!=schoolId }">
        			<a href="javascript:void(0);" class="btn btn-sm btn-default" mark="${school.id }">${school.schoolName }</a>
        		</c:if>
            </c:forEach>
            <c:if test="${empty schoolList }">
            	 <input type="hidden" id="schoolId" value="${school1.id }"/>	
            	 <a href="javascript:void(0);" class="btn btn-sm btn-default btn-success" mark="${school1.id }">${school1.schoolName }</a>
            </c:if>
        </p>
        <div id="newsManagerList">
        	<div class="user-list">
			        <table class="table table-center">
			            
			        </table>
   			 </div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<!-- popupwin 添加新闻类型界面 开始-->
	<div class="popupwin addDiv" style="width: 500px;height: 281.25px" data-pupwin="modal">
	<form>
		<div class="popupwin-title">
			<h2 class="h5">添加新闻类型</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
						<span class="col-md-3 control-label">新闻类型名称<i class="iconfont ico" style="color:red;font-size:12px;">&#xe605;</i></span>
						<div class="col-md-6">
							<input type="text" id="addName" name="addName" class="form-control" placeholder="">
							<span class="help-block" style="color:red;"></span>
						</div>
				</div>
				<div class="form-group">
					<div class="col-md-6 col-md-offset-4">
						<a class="btn btn-primary" id="addBtn" href="javascript:void(0);">添&nbsp;&nbsp;加</a>
						<a class="btn btn-default btn-cancel" data-pupwin-btn="cancle" href="javascript:void(0);">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
<!-- popupwin 添加新闻类型界面结束 -->
<!-- popupwin 修改新闻类型界面 开始-->
	<div class="popupwin editDiv" style="width: 500px;height: 281.25px" data-pupwin="modal">
	<form>
		<div class="popupwin-title">
			<h2 class="h5">修改新闻类型</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
						<span class="col-md-3 control-label">新闻类型名称<i class="iconfont ico" style="color:red;font-size:12px;">&#xe605;</i></span>
						<div class="col-md-6">
							<input type="text" id="editName" data-id="" name="editName" class="form-control" placeholder="">
							<span class="help-block" style="color:red;"></span>
						</div>
				</div>
				<div class="form-group">
					<div class="col-md-6 col-md-offset-4">
						<a class="m-btn-red" id="editBtn" href="javascript:void(0);">修&nbsp;&nbsp;改</a>
						<a class="m-btn-default" data-pupwin-btn="cancle" href="javascript:void(0);">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
<!-- popupwin 修改新闻类型界面结束 -->
<script type="text/javascript" src="<%=rootPath %>/javascripts/system/newsTypeManage.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
</body>
</html>