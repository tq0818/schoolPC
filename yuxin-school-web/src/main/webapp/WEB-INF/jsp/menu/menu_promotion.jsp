<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="screen-left">
        <div class="screen-left-title">
            <i class="iconfont hcancle">&#xe650;</i>
            <span>促销</span>
        </div>
        <ul class="screen-left-list">
            <li class="active" code="promotion" mark="/company/setCouponService">优惠码管理</li>
            <li class="" code="invite" mark="/companyInvitConfig/toCompanyInviteCofig">邀请码设置</li>
        </ul>
    </div> 
    
    <script>
$(document).ready(function(){
	 //点击左侧菜单
	 $(".screen-left-list").on('click','li',function(){
		 var url=$(this).attr("mark");
		 window.location.href=rootPath+url;
	 });
	 //返回
	 $(".hcancle").on('promotion',function(){
		 window.location.href=rootPath+"/company/companyService";
	 });
	 $selectSubMenus('promotion');
});
function $selectSubMenus(code) {
	$(".manage-screen").find('.screen-left').find(".screen-left-list").find("li").each(function() {
		if ($(this).attr("code") == code) {
			$(this).addClass("active").siblings("li").removeClass("active");
		}
	})
 }
</script>