<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!-- 二级导航 -->
 <div class="left-side">
    	<div class="left-side-title">
        	<em>APP</em>
        	<span class="iconfont return-pic hcancle">&#xe650;</span>
        </div>
        <ul id="app_manage" class="app_managelist">
            <li class="subentry" code="app_info" mark="/companyAppBarConfig/configBaseInfo">基本设置</li>
<!--             <li class="subentry" code="app_content" mark="/companyAppBarConfig/openPage">导航设置</li> -->
            <li class="subentry" code="app_cycle" mark="/sysCyclePic/showAppBannerPic">轮播图设置</li>
       </ul>
    </div>
<script>
$(document).ready(function(){
	 //点击左侧菜单
	 $("#app_manage").on('click','li',function(){
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
	$(".overflow").find(".app_managelist").find("li").each(function() {
		if ($(this).attr("code") == code) {
			$(this).addClass("active").siblings("li").removeClass("active");
		}
	})
 }
</script>