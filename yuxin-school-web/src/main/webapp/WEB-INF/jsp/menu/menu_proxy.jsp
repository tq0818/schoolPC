<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="left-side">
        <div class="left-side-title">
            <em>代理机构</em>
            <span class="iconfont return-pic hcancle"></span>
        </div>
        <ul id="course_manage" class="system_managelist">
            <li class="subentry active" code="proxy" mark="/companyConfigProxyOrg/viewPrxoyList">代理机构管理</li>
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
		 window.location.href=rootPath+"/companyConfigProxyOrg/viewPrxoyList";
	 });
});
function $selectSubMenus(code) {
	$('.left-side').find("#course_manage").find("li").each(function() {
		if ($(this).attr("code") == code) {
			$(this).addClass("active").siblings("li").removeClass("active");
		}
	})
 }
</script>