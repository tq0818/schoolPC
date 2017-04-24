<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/plus/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/common/utils.js"></script>

<div class="left-side">
    <div class="left-side-title">
        <em>积分</em>
        <span class="iconfont return-pic goback">&#xe650;</span>
    </div>
    <ul class="menuList_choose">
        <li class="subentry" mark="/companyIntegralConfig/openIntegralSet" code="integralSet">积分设置</li>
        <li class="subentry" mark="/companyIntegralConfig/openGetIntegral" code="getIntegral">获取积分</li>
        <li class="subentry" mark="/companyIntegralConfig/openCustomIntegral" code="integralToBuy">积分消费</li>
    </ul>
</div>
<script type="text/javascript">
 function $chooseMenu(code) {
	 $(".menuList_choose").find("li.subentry").each(function(){
		 if ($(this).attr("code") == code) {
				$(this).addClass("active").siblings("li").removeClass("active");
		 }
	 });
 }
$(function(){
	//点击返回
	$(".goback").on('click',function(){
		 window.location.href = rootPath + "/company/companyService";
	});
	//li点击事件跳转页面
	$(".menuList_choose").on('click','li',function(){
		var url = $(this).attr('mark');
		window.location.href = rootPath + url;
	})
});
</script>