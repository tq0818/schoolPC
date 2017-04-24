<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/plus/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/common/utils.js"></script>
<div class="left-side">
    <div class="left-side-title">
        <em>会员</em>
        <span class="iconfont return-pic goback">&#xe650;</span>
    </div>
     <ul class="menuList_choose">
            <li class="subentry" mark="/companyMemberConfig/addUI" code="setMember" >设置会员</li>
            <li class="subentry" mark="/companyMemberLevelConfig/vipSet" code="setMemberLevel">设置会员等级</li>
        </ul>
</div>
<script type="text/javascript">
 function $chooseMenus(code) {
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