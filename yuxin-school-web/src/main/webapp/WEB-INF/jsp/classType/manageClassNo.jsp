<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>多班号管理</title> 
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
                    <span class="t">多班号管理</span>
                </div>
            </div>
            
            <div class="pay-box-parent">
		         <div style="margin-top:15px;">
	           		<span style="font-size: 14px;color:#666;">课程的多课程单元和多班号功能</span><span style="margin-left: 36px;font-size: 14px;" id="threefunction" ids="" mark="COMPANY_FUNCTION_COURSE" con="课程的多课程单元和多班号功能"><i class="iconfont close font_big">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span>
	            </div><hr/>
	            <div style="margin: 10px 0px 40px 0px;">
	           	   <span style="font-size: 12px;color:#999;">说明:开启该功能后，在创建课程的时候，可以创建多个课程单元，并且为每个课程单元创建多个招生的班号，这样可以让不同课程的同学在同一个班号中上课，节约上课成本。
	           	   <span style="color:red;">两种创建课程的方式之间频繁的进行切换可能会造成数据错误，请谨慎操作</span></span><br/>
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
    		$selectSubMenus('class_nos');
    	});
    </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/class/signUpNotice.js"></script>
</body>
</html>