<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="left-side">
	<!-- <div class="left-side-title subentry" data-url="/company/companyService">返回</div> -->
	<div class="left-side-title">
		<em>学员心声</em>
		<span class="iconfont return-pic">&#xe650;</span>
	</div>
	
	<ul>
		<li class="subentry zst" data-url="/studentStar/showImg">展示图</li>
	</ul>
</div>
<script type="text/javascript">
$(function(){
	$(".subentry").click(function(){
		$(this).addClass("active").siblings(".subentry").removeClass("active");
		var url = $(this).attr("data-url");
		location.href=rootPath+url;
	});
	$(".return-pic").click(function(){
		var url = $(this).attr("data-url");
		location.href=rootPath+"/company/companyService";
	});
})
</script>