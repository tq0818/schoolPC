<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程章节</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <style type="text/css">
    	.pay-box-parent {
		    padding: 0px 50px 0px;
		    overflow: hidden;
		}
		.open{
    		color:#0099ff;
    	}
    	.font_big{
    		font-size:22px;
    	}
    	.txt_width{
    		padding:50px;
    	}
    	p.c {
		    margin-bottom: 15px;
		    clear: both;
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
                    <span class="t">课程章节设置</span>
                </div>
            </div>
            
            <div class="pay-box-parent">
		         <div style="margin-top:15px;">
	           		<span style="font-size: 14px;color:#666;">启用章节名称管理</span><span style="margin-left: 36px;font-size: 14px;" id="chapterLecfunction" ids="" mark="COMPANY_COURSE_SET" con="公司课程章节名称自定义"><i class="iconfont close font_big">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span>
	            </div>
	            <div style="margin: 10px 0px 10px 0px;">
	           	   <span style="font-size: 12px;color:#999999;">说明:启用后可自定义课程章节的字样。禁用后默认为章节。如：默认为第一<span style="color:red;">章</span>，如把章修改为<span style="color:red;">部分</span>，则显示为：第一<span style="color:red;">部分</span></span>
	            </div>
	            <hr/>
	            <p class="c">
	            	<span style="width: 100px;color:#666;">课程章名称:</span>
	            	<span>
		            	<span id="showchapterName" style="padding: 10px;">章</span>
		            	<input type="text" id="printchapterName" style="display: none;" value="章"/>
		            	<a href="javascript:void(0);" mark="chapter"  class="btn btn-primary savebtn_list savechapterBtn" style="padding: 5px 14px;" disabled="disabled">修改</a>
		            	<a href="javascript:void(0);"  class="btn btn-default cancle-chapterandlec" style="display: none;padding: 5px 14px;">取消</a>
	            	</span>
	            </p>
	            <p class="c">
	            	<span style="width: 100px;color:#666;">课程节名称:</span>
	            	<span>
		            	<span id="showlecName" style="padding: 10px;">节</span>
		            	<input type="text" id="printlecName" style="display: none;" value="节"/>
		            	<a href="javascript:void(0);" mark="lecture"  class="btn btn-primary savebtn_list savelectureBtn" style="padding: 5px 14px;" disabled="disabled">修改</a>
		            	<a href="javascript:void(0);"  class="btn btn-default cancle-chapterandlec" style="display: none;padding: 5px 14px;">取消</a>
	            	</span>
	            </p>
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
    		$selectSubMenus('course_chapterandlecture');
    	});
    </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/class/signUpNotice.js"></script>
</body>

</html>