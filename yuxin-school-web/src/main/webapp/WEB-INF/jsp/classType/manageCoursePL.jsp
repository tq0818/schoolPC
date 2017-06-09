<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程评论</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <style type="text/css">
    	.pay-box-parent {
		    padding: 0px 50px 70px;
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
                    <span class="t">课程评论管理</span>
                </div>
            </div>
            
            <div class="pay-box-parent">
		         <div style="margin-top:15px;">
	           		<span style="font-size: 14px;color:#666;">开放课程评论功能</span><span style="margin-left: 36px;font-size: 14px;" id="videofunction" ids="" mark="COURSE_COMMENT" con="开放课程评论功能"><i class="iconfont close font_big">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span>
	            </div><hr/>
	            <div style="margin: 10px 0px 40px 10px;">
	           	   <span style="font-size: 12px;color:#999;margin-left:-10px;">说明:开启课程评论功能后，学员在观看课程的时候可以对课程进行评论，也可以查看到其他人对课程的评论。</span><br/>
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
    		$selectSubMenus('course_pl');
    	})
    </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/class/signUpNotice.js"></script>
</body>

</html>