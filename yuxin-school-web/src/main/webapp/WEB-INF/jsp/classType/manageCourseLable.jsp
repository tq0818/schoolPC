<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程标签管理</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <style type="text/css">
    	.pay-box-parent {
		    padding: 0px 50px 10px;
		    overflow: hidden;
		}
		.open{
    		color:#0099ff;
    	}
    	.font_big{
    		font-size:22px;
    	}
    </style>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_newsystem.jsp"></jsp:include>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">课程标签管理</span>
                </div>
            </div>
            
            <div class="pay-box-parent">
		         <div style="margin-top:15px;">
	           		<span style="font-size: 14px;color:#666;">课程标签</span><span style="margin-left: 36px;font-size: 14px;" id="courseLablefunction" ids="" mark="COURSE_THIRD_CATEGORY" con="课程标签"><i class="iconfont close font_big">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span>
	            </div><hr/>
	            <div style="margin: 10px 0px 40px 0px;">
	           	   <span style="font-size: 12px;color:#999;">说明:开启该功能后，在创建课程的时候，可以为课程添加课程标签,网校前台可以根据课程标签查询课程。</span>
	            </div>
	       </div> 
	       <div class="pay-box-parent">
		         <div>
	           		<span style="font-size: 14px;color:#666;">课程二级标签</span><span style="margin-left: 36px;font-size: 14px;" id="secondLablefunction" ids="" mark="COURSE_SECOND_CATEGORY" con="课程二级标签"><i class="iconfont close font_big">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span>
	            </div><hr/>
	            <div style="margin: 10px 0px 40px 0px;">
	           	   <span style="font-size: 12px;color:#999;">说明:开启该功能后，在创建课程的时候，可以为课程添加课程二级标签,网校前台可以根据课程二级标签查询课程。</span>
	            </div>
	       </div>    
           <div class="clear"></div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('org_service');
    		$selectSubMenus('course_lable');
    	});
    </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/class/signUpNotice.js"></script>
</body>
</html>