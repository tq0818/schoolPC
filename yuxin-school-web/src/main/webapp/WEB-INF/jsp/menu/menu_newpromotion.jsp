<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
 <style>
	 .system_managelist .subentry span,
        .system_managelist .subentry em {
            vertical-align: middle;
        }
        
        .system_managelist .subentry em {
            display: inline-block;
            background: url("/images/new-icon.png") left top no-repeat;
            width: 24px;
            height: 11px;
            margin-left: 10px;
        }
 </style>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="left-side">
        <div class="left-side-title">
            <i class="iconfont return-pic hcancle">&#xe650;</i>
            <span>促销</span>
        </div>
        <ul class="system_managelist syslist-fen">
            <li class="subentry" code="promotion" mark="/company/setCouponService">优惠码管理</li>
            <li class="subentry" code="invite" mark="/companyInvitConfig/toCompanyInviteCofig">学员邀请码</li>
        	<li class="subentry" code="teacher" mark="/companyInvitConfigTeacher/toConfigTeacherInvitePage"><span>教师邀请码</span><em></em></li>
            <li class="subentry" code="org" mark="/companyInvitConfigOrg/toConfigOrgInvitePage">代理机构邀请码<em></em></li>
            <li class="subentry" code="recharge-card" id="recharge-card" style="display: none;" mark="/companyRechargePatch/gotoRechargeCardOpenFlagPage">充值卡<em></em></li>
        </ul>
    </div> 
    
    <script>
$(document).ready(function(){
	
	$('#kaitongfuwu').addClass('active');
	 //点击左侧菜单
	 $(".syslist-fen").on('click','li',function(){
		 var url=$(this).attr("mark");
		 window.location.href=rootPath+url;
	 });
	 //返回
	 $(".hcancle").on('click',function(){
		 window.location.href=rootPath+"/company/companyService";
	 });
	 //$selectSubMenus('invite');
	 $.getFunctionSet("RECHARGECARD_SERVICE",function(jsonData){
		if(jsonData.status == 1){
			$('#recharge-card').show();
		}
	 })
});
function $selectSubMenus(code) {
	$('.left-side').find(".syslist-fen").find("li").each(function() {
		if ($(this).attr("code") == code) {
			$(this).addClass("active").siblings("li").removeClass("active");
		}
	})
 }
</script>