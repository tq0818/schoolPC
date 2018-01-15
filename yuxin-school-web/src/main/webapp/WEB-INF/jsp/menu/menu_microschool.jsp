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
        	<em>WAP管理</em>
        	<span class="iconfont return-pic hcancle">&#xe650;</span>
        </div>
	    <ul id="course_manage" class="system_managelist">
            	<%--<li class="subentry" code="microSchool_navigationConfig" mark="/microSchool/gotoNavigationConfig"><span>导航配置</li>
            	<li class="subentry" code="microSchool_footerConfig" mark="/microSchool/gotoFooterConfig"><span>页尾设置</li>--%>
            	<li class="subentry" code="microSchool_carouselFigureConfig" mark="/microSchool/gotoCarouselFigureConfig"><span>轮播图设置</li>
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
 
// /*新功能提示*/
// function newfn(newList){
// 	if(newList.length>0){
// 		$(".system_managelist .subentry").each(function(i, dom){
// 			if($.inArray($.trim($(this).find("span").html()), newList)!=-1){
// 				$(this).append('<em></em>');
// 			}
// 		})
// 	}
// }

// $(function(){
// 	var newList=new Array();
// 	newList=[];  //配置新功能列表
// 	newfn(newList);
// })
</script>
