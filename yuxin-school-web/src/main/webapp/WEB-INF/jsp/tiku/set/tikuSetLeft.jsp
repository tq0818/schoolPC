<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="left-side">
	<!-- <div class="left-side-title subentry" data-url="/company/companyService">返回</div> -->
	<div class="left-side-title">
		<em>题库设置</em>
		<span class="iconfont return-pic">&#xe650;</span>
	</div>
	
	<ul>
		<li class="subentry" data-url="/tikuSet/toSet/stuFree">做题权限</li>
		<li class="subentry" data-url="/tikuSet/toSet/topicFree">审核权限</li>
		<li class="subentry" data-url="/tikuSet/getTopic">选题范围</li>
	</ul>
</div>
<script type="text/javascript">
$(function(){
	$(".subentry").click(function(){
		$(this).addClass("active").siblings(".subentry").removeClass("active");
		var url = $(this).attr("data-url");
		window.location.href = rootPath + url;
	});
	$(".return-pic").click(function(){
		var url = $(this).attr("data-url");
		window.location.href = rootPath+"/company/companyService";
	});
})
</script>