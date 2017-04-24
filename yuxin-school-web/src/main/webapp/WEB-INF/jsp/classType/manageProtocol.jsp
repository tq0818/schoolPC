<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程协议</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/classSet/classSet.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    
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
                    <span class="t">课程协议</span>
                </div>
            </div>
           
	       <div class="classTra" style="margin-top: 15px;margin-right: 60px;">
			说明：开启课程协议功能后，可在创建课程时进行配置，并且会在用户购买时进行提示查看课程购买协议。
		   </div>
			   <div class="c-main" style="width:95%;margin: 20px 0px 10px 50px;">
				<div class="s-title" style="padding-bottom:10px; padding-left:13px;">
					<h2 class="h6" style="font-size: 14px;">课程协议:<span class="kg kgCom" level="com"><i class="iconfont close" id="courseProtocolButton">&#xe604;</i><span class="i close" style="font-size:14px;" id="courseText">&nbsp;&nbsp;已禁用</span></span></h2>
				</div>
				<div class="s-title" >
					<h2 class="h6" style="font-size: 14px;">课程包协议:<span class="kg kgItem" level="item"><i class="iconfont close" id="packageProtocolButton">&#xe604;</i><span class="i close" style="font-size:14px;" id="packageText">&nbsp;&nbsp;已禁用</span></span></h2>
				</div>
			</div>
           <div class="clear"></div>
        </div>
    </div>
</div>
		<script type="text/javascript">
			$(function() {
				$selectSubMenu('org_service');
				$selectSubMenus("course_protocol_config");
				if($(".tiHeader .navspace li").length == 1){
					$(".tiHeader .navspace li>a:eq(0)").addClass("active");
				}else{
					$(".tiHeader .navspace li>a:eq(3)").addClass("active");
				}
				 //点击左侧菜单
				 $("#course_manage").on('click','li',function(){
					 var url=$(this).attr("mark");
					 window.location.href=rootPath+url;
				 });
				 //返回
				 $(".hcancle").on('click',function(){
					 window.location.href=rootPath+"/company/companyService";
				 });
				 
				 $.ajax({
					 url:rootPath+"/companyFunctionSet/queryCompanyProtocolExist",
					 type:"post",
					 data:{"functionCode":"CLASS_POTOCOL_SET"},
					 dataType:"json",
					 success:function(jsonData){
						 if(jsonData && jsonData == "success"){
							$('#courseProtocolButton').removeClass('close').addClass('open');
							$('#courseProtocolButton').html('&#xe603;'); 
							$('#courseText').removeClass('close').addClass('open');
							$('#courseText').html('&nbsp;&nbsp;已启用');
						 }
					 }
				 })
				 
				 $.ajax({
					 url:rootPath+"/companyFunctionSet/queryCompanyProtocolExist",
					 type:"post",
					 data:{"functionCode":"CLASSPACKAGE_POTOCOL_SET"},
					 dataType:"json",
					 success:function(jsonData){
						 if(jsonData && jsonData == "success"){
							 $('#packageProtocolButton').removeClass('close').addClass('open');
							$('#packageProtocolButton').html('&#xe603;'); 
							$('#packageText').removeClass('close').addClass('open');
							$('#packageText').html('&nbsp;&nbsp;已启用');
						 }
					 }
				 })
				 
				 $('#courseProtocolButton').on('click',function(){
					 if($(this).hasClass('open')){
						 $(this).removeClass('open').addClass('close');
						 $(this).html('&#xe604;');
						 $('#courseText').removeClass('open').addClass('close');
						 $('#courseText').html('&nbsp;&nbsp;已禁用');
						 $.ajax({
							 url:rootPath+"/companyFunctionSet/saveOrUpdataProtocol",
							 type:"post",
							 data:{"functionCode":"CLASS_POTOCOL_SET","status":"0"},
							 dataType:"json",
							 success:function(jsonData){
							 } 
						 })
					 }else{
						 $(this).removeClass('close').addClass('open');
						 $(this).html('&#xe603;');
						 $('#courseText').removeClass('close').addClass('open');
						 $('#courseText').html('&nbsp;&nbsp;已启用');
						 $.ajax({
							 url:rootPath+"/companyFunctionSet/saveOrUpdataProtocol",
							 type:"post",
							 data:{"functionCode":"CLASS_POTOCOL_SET","status":"1"},
							 dataType:"json",
							 success:function(jsonData){
							 } 
						 })
					 }
				 })
				 
				 $('#packageProtocolButton').on('click',function(){
					 if($(this).hasClass('open')){
						 $(this).removeClass('open').addClass('close');
						 $(this).html('&#xe604;');
						 $('#packageText').removeClass('open').addClass('close');
						 $('#packageText').html('&nbsp;&nbsp;已禁用');
						 $.ajax({
							 url:rootPath+"/companyFunctionSet/saveOrUpdataProtocol",
							 type:"post",
							 data:{"functionCode":"CLASSPACKAGE_POTOCOL_SET","status":"0"},
							 dataType:"json",
							 success:function(jsonData){
							 } 
						 })
					 }else{
						 $(this).removeClass('close').addClass('open');
						 $(this).html('&#xe603;');
						 $('#packageText').removeClass('close').addClass('open');
						 $('#packageText').html('&nbsp;&nbsp;已启用');
						 $.ajax({
							 url:rootPath+"/companyFunctionSet/saveOrUpdataProtocol",
							 type:"post",
							 data:{"functionCode":"CLASSPACKAGE_POTOCOL_SET","status":"1"},
							 dataType:"json",
							 success:function(jsonData){
							 } 
						 })
					 }
				 })
			});
			
			
		</script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
 <script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
 <%-- <script type="text/javascript" src="<%=rootPath%>/javascripts/class/classSet.js"></script> --%>
</body>

</html>