<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
	<title id="showTemplate"></title>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript">var rootPath='<%=rootPath%>'</script>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/custom/fatstyle.css" />
	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/custom/head.css"/>
	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/custom/register.css"/>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/footer.css"/>
      <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/custom/about.css" />
       <style type="text/css">
      	.background-gray div.class-menu {
			    margin: 5px auto 5px;
			    padding: 10px 0;
			    background-color: #fff;
			    min-height: 500px;
			}
      </style>
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		//var data=JSON.parse($.cookie("templeteContent"));
    		var data=JSON.parse(localStorage.getItem("templeteContent"));
    		var content=decodeURI(data.content?data.content:"");
    		var isContainHeadFoot=data.includeHeadFoot;
    		var isCustomWidht=data.screenFlag;
     	 	$("#showTemplateContent").html(content);
     	 	$("#showTemplate").text(data.title?data.title:"");
     	 	if(isContainHeadFoot==0){
     	 		$("#headContent").css("display","none");
     	 		$("#footContent").css("display","none");
     	 	}else{
     	 		$("#headContent").css("display","block");
     	 		$("#footContent").css("display","block");
     	 	}
     	 	if(isCustomWidht && isCustomWidht==1){
     	 		$("#showTemplateContent").css("width","100%");
     	 	}
    	});
    </script>
</head>
<body class="background-gray">
<div id="headContent">
	<jsp:include page="/header-relative.jsp"></jsp:include>
</div>
<div style="min-height: 600px;">
	<div id="showTemplateContent" class="wrap class-menu">
	
	</div>
</div>
<div class="c-footer c-footer-4" id="footContent">
    <div class="footer-content clear content-4">
    		<div style="color:white;font-size:14px;width: 100%;text-align: center;">这是页尾</div>
    </div>
</div>
</body>
</html>