<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp"%>

    <title>补费</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('student_urge_fee');
    		var mobile=$("#mobile").val();
    		var id=$("#pid").val();
    		var stuId=$("#stuId").val();
    		var schoolId=$("#schoolId").val();
    		$.ajax({
    			url:"<%=rootPath%>/student/baseMessage",
    			type:"post",
    			data:{
    				"mobile":mobile,
    			},
    			success:function(data){
    				$("#baseMessage").html(data);
    			}
    		})
    		$.ajax({
    					url:"<%=rootPath%>/student/classTypeMessage",
    					type:"post",
    					data:{
    						"mobile":mobile,
    						"id":id,
    						"stuId":stuId,
    						"schoolId":schoolId,
    						
    					},
    					success:function(data){
    						$("#classTypeMessage").html(data);
    					}
    		})
    		$.ajax({
    					url:"<%=rootPath%>/student/feeMessage",
    					type:"post",
    					data:{
    						"mobile":mobile
    					},
    					success:function(data){
    						$("#feeMessage").html(data);
    					}
    		})
    		
    		
    		// 更多按钮
 /*    		$(document).on('click.link.more','.m1 .m',function(){
    			var mm = $(this).html();
    			var $this=$(this).parents(".mark-more");
    			if("更多"==mm){
    				$this.find(".m").html("收起");
    				$this.find("#more-tel").hide();
    				$this.find(".not").show();
    				return false;
    			}else{
    				$this.find(".m").html("更多");
   					$this.find(".not").hide();
   					$this.find("#more-tel").show();
    				return false;
    			}
    		})
    		    		// 更多按钮
    		$(document).on('click.link.more','.m2 .m',function(){
    			var mm = $(this).html();
    			var $this=$(this).parents(".mark-more");
    			if("更多"==mm){
    				$(this).html("收起");
    				$this.find(".not").show();
    				return false;
    			}else{
    				$(this).html("更多");
    					$this.find(".not").hide();
    				return false;
    			}
    		}) */
    	
    	})
    	
    </script>
    <script type="text/javascript">
    	function pay(id){
    		//alert(id);
    		$("#id").val(id);
    		$("#payForm")[0].submit();
    	}
    	function toSearch(){
    		$(".tips").fadeOut();
    		var mobile=$("#phone").val();
    		var id=$("#pid").val();
    		var stuId=$("#stuId").val();
    		var schoolId=$("#schoolId").val();
    		$.ajax({
    			url:"<%=rootPath%>/student/search",
    			type:"post",
    			data:{
    				"mobile":mobile,
    			},
    			success:function(data){
    				if(data==-1){
    					$(".tips").fadeIn();
    					return;
    				}
    				
    				if(data!=null&&data!=""){
    					$.ajax({
    		    			url:"<%=rootPath%>/student/baseMessage",
    		    			type:"post",
    		    			data:{
    		    				"mobile":mobile
    		    			},
    		    			success:function(data){
    		    				$("#baseMessage").html(data);
    		    			}
    		    		})
    		    		$.ajax({
    		    					url:"<%=rootPath%>/student/classTypeMessage",
    		    					type:"post",
    		    					data:{
    		    						"mobile":mobile,
    		    					},
    		    					success:function(data){
    		    						$("#classTypeMessage").html(data);
    		    					}
    		    		})
    		    		$.ajax({
    		    					url:"<%=rootPath%>/student/feeMessage",
    		    					type:"post",
    		    					data:{
    		    						"mobile":mobile,
    		    					},
    		    					success:function(data){
    		    						$("#feeMessage").html(data);
    		    					}
    		    		})
    				}else{
    					$(".tips").fadeIn();
    					//alert("没有相关信息!");
    				}
    			}
    		})
    		
    	}

		function getElementsByClassName(className, tagName) {
			var ele = [], all = document
					.getElementsByTagName(tagName || "*");
			for (var i = 0; i < all.length; i++) {
				if (all[i].className.indexOf(className) != -1) {
					ele[ele.length] = all[i];
				}
			}
			return ele;
		}
</script>
    
</head>
<body>
<form action="<%=request.getContextPath()%>/student/pay" method="post" id="payForm">
	<input type="hidden" id="id" name="id"/>
	<input type="hidden" id="mobile" name="mobile" value="${mobile }"/>

	<input type="hidden" id="pid" name="pid" value="${payMaster.id }"/>
	<input type="hidden" id="schoolId" name="schoolId" value="${payMaster.schoolId }"/>
	<input type="hidden" id="examTermName" name="examTermName" value="${payMaster.examTermName }"/>
	<input type="hidden"  id="stuId" name="stuId" value="${student.id }"/>
	<input type="hidden"  id="totalAmount" name="totalAmount" value="${payMaster.totalAmount }"/>
</form>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">补费</h2>
            <div class="search">
           		 <i class="tips">没有查到相关信息!</i>
                <input type="text" id="phone" name="mobile" class="input-ctrl">
                <input type="button" class="btn btn-sm" onclick="toSearch();" value="搜索">
            </div>
            <span class="line"></span>
        </div>
        <div id="baseMessage"></div>
        <div id="classTypeMessage"></div>
        <div id="feeMessage"></div>
    </div>
    </div>
</body>
</html>