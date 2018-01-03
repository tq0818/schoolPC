<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!-- 二级导航 -->
 <div class="left-side">
    	<div class="left-side-title">
        	<em>查询统计</em>
        	<!-- <span class="iconfont return-pic hcancle">&#xe650;</span> -->
        </div>
        <ul id="course_manage" class="system_managelist syslist-fen">
            <li class="subentry active" code="course_validate" mark="/query/page/student">学员</li>
            <li class="subentry" code="signup_news" mark="/query/page/payMaster">订单</li>
            <li class="subentry integral_flaging"  code="student_integral" mark="/query/page/student_integral">学员积分</li>
            <li class="subentry integral_flaging"  code="integral_payMaster" mark="/query/page/integral_payMaster">积分订单</li>
            <li class="subentry member_flaging" style="display: none;" code="vip_payMaster" mark="/query/page/vip_payMaster">会员订单</li>
       		<li class="subentry member_flaging1" style="display: none;" code="invite_config" mark="/userInviteRewardsRecord/toRewardsRecordList">学员邀请记录</li>
       		<li class="subentry member_flaging1" style="display: none;" code="teacher_invite" mark="/teacherInviteRewardRecord/toTeacherInviteRecordList">教师邀请记录</li>
      		<li class="subentry member_flaging1" style="display: none;" code="org_invite" mark="/organInviteRewardRecord/toOrgInviteRecordList">代理机构邀请记录</li>
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
	 $.ajax({
			url : rootPath + "/company/queryServiceSet",
			type : "post",
			data : {"groupCode":"SERVICE_INTEGRAL"},
			success : function(result) {
				if(result=="show"){
					$(".integral_flaging").show();
				}
			}
	 });	
	 $.ajax({
			url : rootPath + "/company/queryServiceSet",
			type : "post",
			data : {"groupCode":"SERVICE_MEMBER"},
			success : function(result) {
				if(result=="show"){
					$(".member_flaging").show();
				}
			}
	 });
	 
	 $.ajax({
		 url : rootPath + "/companyInvitConfig/isOpenInvite",
		 type : "get",
		 success : function(data){
			 if("1"==data){
				 $('.member_flaging1').show();
			 }
		 }
	 });
});
function $selectSubMenus(code) {
	$(".u-wrap").find(".system_managelist").find("li").each(function() {
		if ($(this).attr("code") == code) {
			$(this).addClass("active").siblings("li").removeClass("active");
		}
	})
 }
</script>