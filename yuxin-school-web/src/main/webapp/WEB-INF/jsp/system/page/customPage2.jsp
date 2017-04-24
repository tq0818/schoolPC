<%@page import="com.yuxin.wx.model.company.Company"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title id="showTemplate"></title>
    <script type="text/javascript">var rootPath='<%=rootPath%>'</script>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/custom/fatstyle.css" />
	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/custom/head.css"/>
	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/custom/register.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/custom/footer.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/custom/about.css" />
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		//var data=JSON.parse($.cookie("templeteContent"));
    		var data=JSON.parse(localStorage.getItem("templeteContent"));
    		var content=decodeURI(data.content?data.content:"");
    		var isContainHeadFoot=data.includeHeadFoot;
     	 	$("#showTemplateContent").html(content);
     	 	$("#showTemplate").text(data.title?data.title:"");
     	 	$(".secondTitle").text(data.title?data.title:"");
     	 	var groupList=data.groupList?data.groupList:"";
     	 	if(groupList && groupList!=""){
     	 		var groupList1=groupList.split(",");
     	 		for(var i=0;i<groupList1.length;i++){
     	 			if(!groupList1[i] || groupList1[i]=="undefined"){
     	 				groupList1[i]=data.title;
     	 			}
     	 			if(data.title==groupList1[i]){
     	 				$("#leftTitles").append('<li><a href="javascript:;" class="tits active">'+groupList1[i]+'</a></li>');
     	 			}else{
     	 				$("#leftTitles").append('<li><a href="javascript:;" class="tits">'+groupList1[i]+'</a></li>');
     	 			}
     	 		}
     	 	}
     	 	if(isContainHeadFoot==0){
     	 		$("#headContent").css("display","none");
     	 		$("#footContent").css("display","none");
     	 	}else{
     	 		$("#headContent").css("display","block");
     	 		$("#footContent").css("display","block");
     	 	}
    	});
    </script>
</head>
<body class="background-gray">
<!-- header start -->
<div id="headContent">
<jsp:include page="/header-relative.jsp"></jsp:include>
</div>
<!-- body start -->
<div class="wrap">
    <div class="place">
        <a href="javascript:void(0);">首页</a> > <span class="secondTitle"></span>
    </div>
    <div class="about">
        <div class="about-left" id="aboutLeft">  
            <ul id="leftTitles">

            </ul>
        </div>
        <div class="about-right linkus" style="min-height: 600px;">
           <div class="contents" style="min-height: 500px;">
	            <div class="title"><h1 class="h4 secondTitle"></h1></div>
	            <div class="content in">
	            	<p id="showTemplateContent"></p>
				</div>
		  </div>
        </div>
    </div>
</div>

<div class="c-footer c-footer-4" id="footContent">
    <div class="footer-content clear content-4">
    		<div style="color:white;font-size:14px;width: 100%;text-align: center;">这是页尾</div>
    </div>
</div>
</body>
</html>