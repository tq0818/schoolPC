<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>页尾配置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <style>
    .l-info{
     	padding-left: 50px;
	    font-size: 14px;
	    color: #999;
	    margin: 15px 0;
    }
   .l-login{
        padding-left: 50px;
	    font-size: 14px;
	    color: #333;
   }
    </style>	
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_systemconfig.jsp"></jsp:include>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">页尾配置</span>
                </div>
            </div>
            <div class="l-info">说明：开启后一个学员账号 ，可以在同一时间多处登录</div>
            <div class="l-login">
            	同一账号多处同时登录：
            	<c:if test="${flag==1}">
            		<i class="iconfont open">&#xe603;</i>
	            	<em>已启用</em>
            	</c:if>
            	<c:if test="${flag==0}">
            		<i class="iconfont close">&#xe604;</i>
	            	<em>已禁用</em>
            	</c:if>
            </div>
        </div>
    </div>
</div>
 <script type="text/javascript">
   	$(function(){
   		$selectSubMenus('system_stu_login');
   		$(".right-side").on('click','.iconfont',function(){
   			var _this = this,
   				status;
   			if($(_this).hasClass("open")){
   				status = 0;
   				$.ajax({ 
  	   	 		  type: "post", 
  	   	 		  url : rootPath+"/sysConfigStuLogin/setStuLogin", 
  	   	 		  data: {status:status},
  	   	 		  success: function(result){
  	   	 			  if(!result)
  	   	 				  return;
  	   	 			  $(_this).html('&#xe604;').removeClass('open').addClass('close').next().html('已禁用');
  	   	 		  }
  	   	 	  	});
   			}else{
   				status = 1;
   				$.ajax({ 
  	   	 		  type: "post", 
  	   	 		  url : rootPath+"/sysConfigStuLogin/setStuLogin", 
  	   	 		  data: {status:status},
  	   	 		  success: function(result){
	  	   	 		if(!result)
	   	 				  return;
	  	   	 	 	$(_this).html('&#xe603;').removeClass('close').addClass('open').next().html('已启用');
  	   	 		  }
   				});
   			}
   		});
   	});
 </script>
</body>
</html>