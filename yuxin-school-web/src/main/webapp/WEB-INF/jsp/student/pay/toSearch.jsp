<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>补费</title>
<%@include file="/decorators/import.jsp"%>
 <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
<script type="text/javascript">
	function search1(){
		$("#tip1").attr("style","display:none");	
		$("#tip2").attr("style","display:none");	
		var mobile = document.getElementById("mobile").value;
		$.ajax({
			url:rootPath+"/student/search",
			type:"post",
			data:{
				"mobile":mobile
			},
			success:function(data){
				//alert(data);
				/* if("no"==data){
					$("#tip").attr("style","display:block");
				}else{
					$("#toPayMessage")[0].submit();
				}  */
				if(data==-1){
					$("#tip1").attr("style","display:block");	
					return;
				}
				 if(data==null||data==""){
					$("#tip2").attr("style","display:block");
				}else{
					//alert(data);
					//var aa = jQuery.parseJSON(data);
					$("#studentPayMasterId").val(data);
					//alert($("#studentPayMasterId").val());
					$("#toPayMessage")[0].submit();
				}  
					
			},
			
		})
		
		
		
	}
	$(function(){
		$selectSubMenu('student_add_fee');
		$(".footer").addClass("footer-fixed"); 
		$("#mobile").on("keypress",function(e){
			if(e.keyCode==13){
				$("#searchBtn").trigger("click");
				return false;
			}
		})
	})
</script>

</head>
<body>
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
	<form action="<%=request.getContextPath() %>/student/toMessage" method="post" id="toPayMessage">
		<input type="hidden" id="studentPayMasterId" name="studentPayMasterId"/>
	
	<div class="u-wrap student main-fixed">
		<div class="mainbackground">
			<div class="center-search">
				<p class="c">
					<input type="text" class="input-control" value="" id="mobile" name="mobile"
						placeholder="输入学员手机号"> <a href="javascript:void(0);"
						class="btn btn-sm btn-default" id="searchBtn" onclick="search1();">搜索</a>
				</p>
				<p class="c" id="tip1" style="display:none">
					<span class="ps">你查询的学员手机号不存在。</span>
				</p>
				<p class="c" id="tip2" style="display:none">
					<span class="ps">该学员没有可补的费用</span>
				</p>

			</div>
			<p class="tip">
				<span>通过该功能可以给分期付款的学员进行补费</span>
			</p>
		</div>
	</div>
	</form>
</body>
</html>