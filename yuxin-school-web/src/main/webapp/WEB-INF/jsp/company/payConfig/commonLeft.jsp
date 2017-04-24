<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="left-side">
	<!-- <div class="left-side-title subentry" data-url="/company/companyService">返回</div> -->
	<div class="left-side-title">
		<em>支付</em>
		<span class="iconfont return-pic">&#xe650;</span>
	</div>
	
	<ul>
		<li class="subentry zfb" data-url="/payConfig/showPayChoice">支付宝设置</li>
		<li class="subentry wx" data-url="/payConfig/showWxChoice">微信设置</li>
		<li class="subentry yhhk" data-url="/payConfig/showBankChoice">银行汇款</li>
		<li class="subentry sxy" data-url="/payConfig/showSXYChoice">网银支付</li>
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