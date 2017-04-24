<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>异动</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheets/student.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascripts/student.js"></script> 
    <script type="text/javascript">
    	$(function(){
    		$(".footer").addClass("footer-fixed"); 
    		$selectSubMenu('student_change') ;
    		$("#mobile").on("keypress",function(e){
    			if(e.keyCode==13){
    				$("#searchBtn").trigger("click");
    				return false;
    			}
    		})
    	})
    	function search1(){
    		var mobile = $("#mobile").val();
    		$.ajax({
    			url:"<%=request.getContextPath()%>/student/searchTransaction",
    			type:"post",
    			data:{
    				"mobile":mobile
    			},
    			success:function(data){
    				if("yes"==data){
    					$("form:first").submit();
    				}else{
    					$(".ps").show();	
    				}
    			}
    		})
    	}
    </script>
    
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
<form action="<%=request.getContextPath()%>/student/toTransaction" method="post">
<div class="u-wrap student main-fixed">
    <div class="mainbackground">
        <div class="center-search">
            <p class="c">
                <input type="text" class="input-control" value="" name="mobile" placeholder="输入学员手机号" id="mobile">
                <a href="javascript:void(0);" id="searchBtn" class="btn btn-sm btn-default" onclick="search1();">搜索</a>
            </p>
            <p class="c"><span class="ps" style="display:none">你查询的学员手机号不存在或者该学员没有可操作订单。</span></p>
            <p class="tip"><span>通过该功能可以为已报名的学员更改课程、更换班号、转人</span></p>
        </div>
    </div>
</div>
</form>
</body>
</html>