<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程有效期管理</title> 
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
                    <span class="t">课程有效期设置</span>
                </div>
            </div>
           
	       <div class="classTra" style="margin-top: 15px;margin-right: 60px;">
			说明：课程有效期设置包括三个级别，开启不同的级别有不同的限制，如果开启多个级别则以低级别的配置为准。
			比如，同时开启公司级别和课程级别，那么以课程级别为准。0表示无限次数。
		   </div>
			   <div class="c-main" style="width:95%;margin: 20px 0px 10px 50px;">
				<div class="s-title">
					<h2 class="h6" style="font-size: 14px;">公司级别设置<span class="kg kgCom" level="com"></span></h2>
				</div><hr style="width: 93%;margin-right: 100px;"/>
				<div class="s-content comContent none ml58">
						
				</div>
				
				<div class="s-title">
					<h2 class="h6" style="font-size: 14px;">学科级别设置<span class="kg kgItem" level="item"></span></h2>
				</div><hr style="width: 93%;margin-right: 100px;"/>
				<div class="c-content ml58 none itemContent">
						<ul id="subInfo">
							
						</ul>
					<p class="c" style="margin-left: -16px;">
							<a href="javascript:;" class="btn btn-mini btn-success" id="itemSave">保存</a>
					</p>
				</div>

				<div class="s-title">
					<h2 class="h6" style="font-size: 14px;">课程级别设置<span class="kg kgCla" level="cla"></span></h2>
				</div><hr style="width: 93%;margin-right: 100px;"/>
				<div class="s-content ml58">
						<p class="c">
						开启配置后，在创建课程的时候可以为每个课程指定有效期和观看次数。
						</p>
				</div>
			</div>
			  
           <div class="clear"></div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
		<div class="loading lp-units-loading" style="display: none">
			<p>
				<i></i>加载中,请稍后...
			</p>
		</div>
		<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
		<!--  ajax加载中div结束 -->
		<div>
			<input type="hidden" id="comLS" value="${comLS}" /> 
			<input type="hidden" id="itemLS" value="${itemLS}" />
			<input type="hidden" id="claLS" value="${claLS}" />
		</div>
		<script type="text/javascript">
			$(function() {
				$selectSubMenu('org_service');
				$selectSubMenus("course_validate");
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
			});
		</script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
 <script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
 <script type="text/javascript" src="<%=rootPath%>/javascripts/class/classSet.js"></script>
</body>

</html>