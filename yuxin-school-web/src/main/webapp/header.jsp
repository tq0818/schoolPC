<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.shiro.session.Session"%>
<%@page import="org.apache.shiro.subject.Subject" %>
<%@page import="org.apache.shiro.SecurityUtils" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/decorators/import.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/resource.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/utils.css"/>
<style>
.iconfontH{color: red;}
.tipsH{color: red;}
.iii{
  position: absolute;
  top: 23px;
  right: -8px;
  width: 0;
  height: 0;
  border: 5px solid transparent;
  border-top-color: #ddd;
}
.cc{
  height: 62px;
  padding: 0 1%;
  line-height: 1.5;
}
.alert {
    padding: 15px;
    border: 1px solid transparent;
    margin:0 auto;
}

.alert-warning {
    color: #c09853;
    background-color: #fcf8e3;
    border-color: #fbeed5
}
.alert-dismissable .cloze {
    position: relative;
    right: 50px;
    color: inherit;
    font-size:1.6rem;
    cursor:pointer;
}
.cloze {
    float: right;
    font-size: 21px;
    font-weight: bold;
    line-height: 1;
    color: #000;
    text-shadow: 0 1px 0 #fff;
    opacity: .2;
    filter: alpha(opacity=20);
    display: inline-block;
	margin-top: 0px;
	margin-right: 0px;
	width: 9px;
	height: 9px;
	background-repeat: no-repeat !important;
}

.noalert {
    float: right;
    font-size: 12px;
    font-weight: bold;
    line-height: 1;
    color: #000;
    text-shadow: 0 1px 0 #fff;
    opacity: .2;
    filter: alpha(opacity=20);
    display: inline-block;
	margin: 1px -15px 0 15px;
	width: 80px;
	height: 9px;
	background-repeat: no-repeat !important;
	cursor:pointer;
}

.cloze:hover,.cloze:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
    opacity: .5;
    filter: alpha(opacity=50)
}
button.cloze {
    padding: 0;
    cursor: pointer;
    background: transparent;
    border: 0;
    -webkit-appearance: none
}
</style>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript">
	var rootPath = '<%=rootPath%>';
	$(document).ready(function(){
		$(".logout").on("click.btn.index",function(){
			location.href=rootPath+"/logout";
		});
		 //判断是否为管理员
        $.ajax({
			url : rootPath+"/authRole/authAdmin2",
			type : "post",
			success : function(data){
				if(data&&data=="1"){
					$(".dropdown").append('<li class="subli"><a href="javascript:void(0)" class="updatePwd">修改密码</a></li><li class="subli"><a href="javascript:void(0)" class="accInfo">账户信息</a></li>');
				}else if(data&&data=="2"){
					$(".dropdown").append('<li class="subli"><a href="javascript:void(0)" class="updatePwd">修改密码</a></li><li class="subli"><a href="javascript:void(0)" class="accInfo2">账户信息</a></li>');
				}
				else{
					$(".dropdown").append('<li class="subli"><a href="javascript:void(0)" class="updatePwd">修改密码</a></li>')
				}
			}
        });
		 $(".uInfo")
     	.on('mouseenter.index',function(){
     		$(".dropdown").show();
     		$(this).find('.dropdown').stop().animate({height:"74px"});
     	})
     	.on('mouseleave.index',function(e){
     		$(this).find('.dropdown').stop().animate({height:"0px"});
     		
     	});
		/* $(".nav-right").on("click",".subli",function(){
			$('.add-subs-layer-bg').fadeIn(200,function(){
	            $('.edit-pwd').fadeIn(200);
	        });
		}); */
		
		/* $(".cancle").click(function(){
			$('.edit-pwd').fadeOut(200,function(){
	            $('.add-subs-layer-bg').fadeOut(200);
	        });
		}); */
		$(".uInfo").on("click",".updatePwd",function(){
			location.href=rootPath+"/companyMemberService/toUpdatePwd";
		});
		$(".uInfo").on("click",".accInfo",function(){
			location.href=rootPath+"/users/accountInfo";
		});
		$(".uInfo").on("click",".accInfo2",function(){
			location.href=rootPath+"/users/teacherAccountInfo";
		});
		$(".save").click(function(){
			var oldPwd = $.trim($("#oldPwd").val());
			var newPwd = $.trim($("#newPwd").val());
			var nextPwd = $.trim($("#nextPwd").val());
			var userId = $("#userId").val();
			if(!oldPwd){
				$(".old").html("请输入旧密码");
				return;
			}else{
				$(".old").html("");
			}
			if(!newPwd){
				$(".new").html("请输入新密码");
				return;
			}else{
				$(".new").html("");
			}
			if(!nextPwd){
				$(".next").html("请输入重复密码");
				return;
			}else{
				$(".next").html("");
			}
			if(newPwd!=nextPwd){
				$(".next").html("两次密码不一致");
				return;
			}else{
				$(".next").html("");
			}
			if(newPwd.length<6){
				$(".new").html("密码长度不能小于6位");
				return;
			}else{
				$(".new").html("");
			}
			if(nextPwd.length<6){
				$(".next").html("密码长度不能小于6位");
				return;
			}else{
				$(".next").html("");
			}
			//将原密码加密
			$.ajax({
				url : rootPath+"/users/md5Pwd",
				data : {"pwd":oldPwd},
				type : "post",
				success : function(md5Pwd){
					//返回加密后的密码与原密码相比
					$.ajax({
						url : rootPath+"/users/checkPwd",
						data : {"password":md5Pwd},
						type : "post",
						success : function(data){
							if(data){
								$.ajax({
									url : rootPath+"/users/upCurrPwd",
									data : {"pwd":newPwd},
									type : "post",
									success : function(data){
										
												if(data == "success"){
													$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
														$(".old").html("");
														$("#oldPwd").val("");
														$("#newPwd").val("");
														$("#nextPwd").val("");
//														$('.edit-pwd').fadeOut(200,function(){
//												            $('.add-subs-layer-bg').fadeOut(200);
//												        });
													});
												}
									}
								});
								
								
							}else{
								$(".old").html("旧密码与原密码不符");
								return;
							}
						}
					});
				}
			});
			
		});
	})

	function getCookie(c_name) {
		if(window.localStorage){
			return localStorage.getItem(c_name);
		}
		if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(c_name + "=")
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1
				c_end = document.cookie.indexOf(";", c_start)
				if (c_end == -1)
					c_end = document.cookie.length
				return document.cookie.substring(c_start, c_end);
			}
		}
		return ""
	}

	//两个参数，一个是cookie的名子，一个是值
	function setCookie(name, value) {
		if(window.localStorage){
			localStorage.setItem(name,value);
		}else{
			var Days = 30; //此 cookie 将被保存 30 天
			var exp = new Date(); //new Date("December 31, 9998");
			exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
			document.cookie = name + "=" + value + ";expires="
			+ exp.toGMTString();
		}
		

	}

	function $selectMenu(code) {
		if ($(".header").find("ul").find("li")) {
			$(".header").find("ul").find("li").removeClass("active");
			$(".header").find("ul").find("li").each(function() {
				if ($(this).attr("code") == code) {
					$(this).find("a").addClass("active");
				}
			})
		} else {
			setTimeOut(function() {
				selectMenu(code);
			}, 1000)
		}
	}
	function $selectSubMenu(code) {
		$(".smbar").find(".nav").find("li").find("a").removeClass("active");
		$(".smbar").find(".nav").find("li").each(function() {
			if ($(this).attr("code") == code) {
				$(this).find("a").addClass("active");
			}
		})
	}
    function $selectThirdMenu(code) {
       // $(".system_managelist").find("li").removeClass("active");

        var $target = $(".system_managelist").find("li[code="+code+"]");
        $target.addClass("active").siblings().removeClass("active");
        if(!$target.hasClass("item-child")){
            $(".system_managelist").find("li .managelist-child").hide();
        }
        $target.find(".managelist-child").show();
     /*   $(".system_managelist").find("li").each(function() {
            if ($(this).attr("code") == code) {
                $(this).addClass("active");
                $(this).closest(".managelist-child").show();
            }
        })*/
    }
</script>
<!-- header start -->
<header class="full-wrap navbar minibar reversal">
    <div class="header">
        <a href="<%=rootPath %>/index" class="navbar-brand"></a>
        <ul class="nav nav-left navspace">
       		 <shiro:hasPermission name="student_head">  
            <li code="student_head"><a href="<%=rootPath %>/company/student/firstTransferStation">学员</a></li>
            </shiro:hasPermission>
             <shiro:hasPermission name="course_head">  
            <li code="course_head"><a href="<%=rootPath %>/company/course/firstTransferStation">课程</a></li>
            </shiro:hasPermission>
             <shiro:hasPermission name="teach_head">  
            <li code="teach_head"><a href="<%=rootPath %>/company/teaching/firstTransferStation">教学</a></li>
             </shiro:hasPermission>
            <shiro:hasPermission name="operating_head">  
            <li code="operating_head"><a href="<%=rootPath %>/company/operate/firstTransferStation">运营</a></li>
             </shiro:hasPermission>
            <shiro:hasPermission name="tiku_header">  
             <li code="tiku_header"><a href="<%=rootPath %>/company/tiku/firstTransferStation">题库</a></li>
             </shiro:hasPermission>
             
             <shiro:hasPermission name="resource_head">  
            <li code="resource_head"><a href="<%=rootPath %>/company/resource/firstTransferStation">资源</a></li>
             </shiro:hasPermission>

           <shiro:hasPermission name="system_head">
            <li code="system_head"><a href="<%=rootPath %>/company/system/firstTransferStation" >系统</a></li>
			</shiro:hasPermission>

            <shiro:hasPermission name="statistics_all">
                <li code="statistics_all"><a href="<%=rootPath %>/company/statistics/firstTransferStation" >总览</a></li>
            </shiro:hasPermission>

            <shiro:hasPermission name="statistics_area">
                <li code="statistics_area"><a href="<%=rootPath %>/company/areastatistics/firstTransferStation" >总览</a></li>
            </shiro:hasPermission>

            <shiro:hasPermission name="statistics_org">
                <li code="statistics_org"><a href="<%=rootPath %>/company/orgstatistics/firstTransferStation" >总览</a></li>
            </shiro:hasPermission>
        </ul>
        <ul class="nav nav-right">
            <c:choose>
            	<c:when test="${sessionScope.loginUser.realName != null && sessionScope.loginUser.realName != ''}">
            		<li class="uInfo"><a href="javascript:;" class="u" style="cursor: pointer;">${sessionScope.loginUser.schoolName}&nbsp;${sessionScope.loginUser.realName}</a><i class="iii">
            		</i>	<ul class="dropdown" style="display: none;overflow: hidden;"></ul></li>
            	</c:when>
            	<c:otherwise>
            		<li class="uInfo"><a href="javascript:;" class="u" style="cursor: pointer;">${sessionScope.loginUser.schoolName}&nbsp;${sessionScope.loginUser.username}</a><i class="iii">
            		</i>	<ul class="dropdown" style="display: none;overflow: hidden;"></ul></li>
            	</c:otherwise>
            </c:choose>
            <li><a href="javascript:;" class="logout">退出</a></li>
            <input type="hidden" id="userId" value="${sessionScope.loginUser.id}"/>
        </ul>
    </div>
    
	<div class="add-subs-layer-bg"></div>
</header>
<!-- header end -->
