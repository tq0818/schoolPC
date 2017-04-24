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
        	<em>促 销</em>
        </div>
<%--         <c:if test="${cfs!=null && cfs.status==1 }"> --%>
	    <ul id="course_manage" class="system_managelist coupon_manage">
            	<li class="subentry" code="coupon_manage" mark="/companyCouponsConfig/showCouponsList"><span style="    font-size: 14px;">优惠码管理</span></li>
        </ul>
<%--         </c:if> --%>
        <ul id="course_manage" class="system_managelist rachargecard_manage" style="display: none;">
            	<li class="subentry" code="rachargecard_manage" mark="/companyRechargePatch/gotoRechargeCardManagePage"><span style="    font-size: 14px;">充值卡管理</span></li>
        </ul>
    </div>
<script>
$(document).ready(function(){
	 //点击左侧菜单
	 $(".system_managelist").on('click','li',function(){
		 var url=$(this).attr("mark");
		 window.location.href=rootPath+url;
	 });
// 	 $selectSubMenus("coupon_manage");
});
function $selectSubMenus(code) {
	
	$('.left-side').find(".system_managelist").find("li").each(function() {
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
     
<script>
	$(function(){
		$.getFunctionSet("COMPANY_COUPONS_SERVICE",function(jsonData){
			if(jsonData.status == 0){
				$('.coupon_manage').hide();
			}
		})
	    $.getFunctionSet("RECHARGECARD_SERVICE",function(jsonData){
			if(jsonData.status == 1){
				$.getFunctionSet("RECHARGECARD_OPENFLAG",function(jsonData){
					if(jsonData.status == 1){
						$('.rachargecard_manage').show();
					}
				})
			}
	 	})
		
	})
</script>