<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>问答设置</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <div class="left-side">
        <!-- <div class="left-side-title">返回</div> -->
        <div class="left-side-title">
        	<em>问答社区</em>
        	<span class="iconfont return-pic goBack">&#xe650;</span>
        </div>
        <ul>
            <li class="subentry active">问答分类</li>
        </ul>
    </div>
    <div class="right-side">
        <div class="title-box">
            <div class="tit-font">
                <span class="t">问答分类</span>
            </div>
        </div>
        <p class="prompt-font">说明：可通过问答分类设置对社区进行管理。课程中的问答权限需在 “<span class="blue-font kcwd">课程--课程问答</span>”中进行设置，下列分类只可选择一种</p>
        <div class="classify-box">
            <div class="classify">
                <div>
                    <div class="classify-tit">自定义分类</div>
                    <p>用户可以在问答社区中自定义问答分类</p>
                    <p>学员在提问后可选择问题分类</p>
                    <span class="instruc" onclick="javascript:window.open('<%=rootPath%>/Question/queZdyfl')">使用说明</span>
                    <span class="cl-choose zdy" data-status="0">选择</span>
                </div>
                <img src="<%=rootPath%>/images/choosed-1_03.png" alt=""/>
            </div>
            <div class="classify">
                <div>
                    <div class="classify-tit">课程分类</div>
                    <p>用户可在问答社区中使用课程分类即学科分类，并且可以对学科定义昵称</p>
                    <p>学员在提问后可选择学科作为问题标签</p>
                    <span class="instruc" onclick="javascript:window.open('<%=rootPath%>/Question/queKcfl')">使用说明</span>
                    <span class="cl-choose kc" data-status="1">选择</span>
                </div>
                <img src="<%=rootPath%>/images/choosed-1_03.png" alt=""/>
            </div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<input type="hidden" value="${isCorP}" id="isCorP"/>
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath%>/javascripts/queAns/questionClassfyset.js"></script>

<script type="text/javascript">
$(function(){
	$selectSubMenu('org_service');
	var isCorP = $("#isCorP").val();
	console.log(isCorP);
	if(isCorP == 0){
		$(".zdy").html("去设置");
        $(".zdy").parents(".classify").addClass("active");
        $(".zdy").parents(".classify").find("img").css("display","block");
        $(".zdy").parents(".classify").find("div,p").css({
            "color":"#0099ff"
        });
        $(".zdy").parents(".classify").css({
            "background-color":"#fff"
        });
        $(".zdy").parents(".classify").siblings().removeClass("active").find("img").css("display","none");
        $(".zdy").parents(".classify").siblings().find(".cl-choose").html("选择");
        $(".zdy").parents(".classify").siblings().find("div,p,span").css("color","#999");
	}else{
		$(".kc").html("去设置");
        $(".kc").parents(".classify").addClass("active");
        $(".kc").parents(".classify").find("img").css("display","block");
        $(".kc").parents(".classify").find("div,p").css({
            "color":"#0099ff"
        });
        $(".kc").parents(".classify").css({
            "background-color":"#fff"
        });
        $(".kc").parents(".classify").siblings().removeClass("active").find("img").css("display","none");
        $(".kc").parents(".classify").siblings().find(".cl-choose").html("选择");
        $(".kc").parents(".classify").siblings().find("div,p,span").css("color","#999");
	}
	$(".kcwd").click(function(){
		location.href=rootPath+"/Question/queAnsSet";
	});
	$(".goBack").click(function(){
		location.href=rootPath+"/company/companyService";
	});
	 $(".cl-choose").on("click",function(){
		 var html = $(this).html();
		 var status = $(this).attr("data-status");
		 if(html == '选择'){
			 changeStatus(status);
				$(this).html("去设置");
		        $(this).parents(".classify").addClass("active");
		        $(this).parents(".classify").find("img").css("display","block");
		        $(this).parents(".classify").find("div,p").css({
		            "color":"#0099ff"
		        });
		        $(this).parents(".classify").css({
		            "background-color":"#fff"
		        });
		        $(this).parents(".classify").siblings().removeClass("active").find("img").css("display","none");
		        $(this).parents(".classify").siblings().find(".cl-choose").html("选择");
		        $(this).parents(".classify").siblings().find("div,p,span").css("color","#999");
		 }else{
			 location.href=rootPath+"/Question/questionClassfy";
		 }
	 });
	function changeStatus(status){
		$.ajax({
			url : rootPath+"/Question/changeStatus",
			type : "post",
			data : {"status":status,"code":"QUESTION_CLASSIFY_TYPE"},
			beforeSend : function(XMLHttpRequest) {
				$(".loading").show();
				$(".loading-bg").show();
			},
			success : function(data){
				if(data=="success"){
					$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
					});
				}
			},
			complete : function(XMLHttpRequest, textStatus) {
				$(".loading").hide();
				$(".loading-bg").hide();
			}
		});
	}
})
</script>
</body>
</html>