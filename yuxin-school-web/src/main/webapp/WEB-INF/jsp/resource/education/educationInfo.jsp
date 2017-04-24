<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>教务</title>
    <%@include file="/decorators/import.jsp"%>
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
    <link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>/stylesheets/resource.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/resource/education/educationInfo.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/resource.js"></script>
</head>
<body>
<!-- header start -->
<!-- header end -->
<!-- 二级导航 -->
<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<div class="u-wrap resource">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">教务</h2>
            <div class="search">
                <a href="javascript:void(0)" class="btn btn-mini btn-primary add"><em class="iconfont">&#xe606;</em> 添加教务</a>
            </div>
            <span class="line"></span>
        </div>        
        <div class="r-list">
            <div class="sc-select">
                <span class="sc-select-title">教务人员类型</span>
                <a href="javascript:;" class="btn btn-mini btn-default btn-success tType all" typeStatus="all">全部</a>
                <a href="javascript:;" class="btn btn-mini btn-default tType manager" typeStatus="PERSON_MANAGER">班主任</a>
                <a href="javascript:;" class="btn btn-mini btn-default tType waiter" typeStatus="PERSON_WAITER">跟班生</a>
                <a href="javascript:;" class="btn btn-mini btn-default tType assistant" typeStatus="PERSON_ASSISTANT">助教</a>
                <input type="hidden" id="teaType" value="all"/>
                <div class="search" style="position: absolute;margin-left: 79%;margin-top: -28px;">
	                <input type="text" class="input-ctrl" id="condition">
	                <input type="button" class="btn btn-sm" value="搜索" id="btnSearch">
           		</div>
            </div>
            <div class="r-list">
                <ul id="info" style="min-height: 350px;position: relative;">
                    
                </ul>
            </div>
            <!-- ajax加载中div开始 -->
			<div class="loading lp-units-loading" style="display:none">
		        <p><i></i>加载中,请稍后...</p>
		    </div>
		    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
			<!--  ajax加载中div结束 -->
        </div>
         <div class="pages">
				<ul class="pagination">

				</ul>
		</div>
    </div>
</div>
<!-- 删除教师 -->
<div class="add-subs-layer del-teacher" style="width:200px;height: 280px;">
	<div id="lessonInfo">
	</div>
    <p class="c text-center">
        <a href="javascript:;" class="btn btn-sm btn-primary updateTeacher">继续</a>
        <a href="javascript:;" class="btn btn-sm btn-default btn-cancel">返回</a>
    </p>
</div>
<div class="add-subs-layer-bg"></div>
<!-- footer start -->
<!-- footer end -->
<script>
	$(function(){
		$selectSubMenu('resource_manager');
	});
</script>
</body>
</html>


