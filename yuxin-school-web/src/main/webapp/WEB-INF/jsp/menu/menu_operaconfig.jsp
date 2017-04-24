<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!-- 二级导航 -->
 <div class="left-side">
    	<div class="left-side-title">
        	<em>财务</em>
        	<!-- <span class="iconfont return-pic hcancle">&#xe650;</span> -->
        </div>
        <ul id="course_manage" class="system_managelist">
            <li class="subentry" code="operate_fee_confirm" mark="/payOrder/toOrder">转账确认</li> 
            <li class="subentry" code="operate_fee" mark="/query/page/fee">费用</li> 
       </ul>
    </div>
<script>
$(document).ready(function(){
	 //点击左侧菜单
	 $("#course_manage").on('click','li',function(){
		 var url=$(this).attr("mark");
		 window.location.href=rootPath+url;
	 });
	 //返回
	 $(".hcancle").on('click',function(){
		 window.location.href=rootPath+"/company/companyService";
	 });
	 $selectSubMenu("org_service");
});
function $selectSubMenus(code) {
	$(".overflow").find(".system_managelist").find("li").each(function() {
		if ($(this).attr("code") == code) {
			$(this).addClass("active").siblings("li").removeClass("active");
		}
	})
 }
</script>