<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!-- 二级导航 -->
 <style>
	 .system_managelist .subentry span,.system_managelist .subentry em{
		 vertical-align:middle;
	 }
	 .system_managelist .subentry em{
		 display:inline-block;
		 background:url("<%=rootPath%>/images/new-icon.png") left top no-repeat;
		 width:24px;
		 height:11px;
		 margin-left:10px;
	 }
 </style>
 <div class="left-side">
    	<div class="left-side-title">
        	<em>公开课设置</em>
        	<span class="iconfont return-pic hcancle">&#xe650;</span>
        </div>
        <ul id="course_manage" class="system_managelist">
            <li class="subentry" code="system_opensetting" mark="/liveOpenCourse/showLiveOpenClassSetting"><span>公开课设置</span></li> 
            <li class="subentry" code="system_openindex" mark="/sysBody/openClassOption"><span>公开课模板配置</span></li> 
            <li class="subentry" code="system_opencycle" mark="/liveOpenCourse/showBannerPic"><span>公开课轮播图</span></li> 
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