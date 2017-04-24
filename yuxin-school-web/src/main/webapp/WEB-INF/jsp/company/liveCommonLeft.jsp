<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="left-side">
	<!-- <div class="left-side-title subentry" data-url="/company/companyService">返回</div> -->
	<div class="left-side-title">
		<em>直播</em>
		<span class="iconfont return-pic">&#xe650;</span>
	</div>
	
	<ul>
		<li class="subentry zbTail" data-url="/companyMemberService/toLiveStatistics">直播详情</li>
	</ul>
</div>
<script type="text/javascript">
$(function(){
	$(".subentry").click(function(){
		$(this).addClass("active").siblings(".subentry").removeClass("active");
		var url = $(this).attr("data-url");
		window.location.href=rootPath+url;
	});
	$(".return-pic").click(function(){
		window.location.href=rootPath+"/company/companyService";
	});
})
</script>