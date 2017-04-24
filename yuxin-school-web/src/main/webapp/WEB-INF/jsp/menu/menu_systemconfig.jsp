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
        	<em>网校设置</em>
        	<span class="iconfont return-pic hcancle">&#xe650;</span>
        </div>
	    <ul id="course_manage" class="system_managelist">
        <c:choose>
        	<c:when test="${sessionScope.company.memberLevel == 12 or sessionScope.company.memberLevel == 13 }">
        		<c:if test="${empty sessionScope.SYSTEM_HEAD or sessionScope.SYSTEM_HEAD == 1 }">
	            	<li class="subentry" code="system_head" mark="/sysPageHeadFoot/showHead"><span>页头设置<%-- <i class="icon-red"></i> --%></span><%-- <em></em> --%></li>
        		</c:if>
	<!--             <li class="subentry" code="system_foot" mark="/sysPageHeadFoot/toConfigFooter">页尾设置</li> -->
				<c:if test="${empty sessionScope.SYSTEM_FOOT or sessionScope.SYSTEM_FOOT == 1 }">
	            	<li class="subentry" code="system_foot" mark="/companyHeadFootConfig/showFootTemplete"><span>页尾设置</span></li>
				</c:if>
				<c:if test="${empty sessionScope.SYSTEM_INDEX or sessionScope.SYSTEM_INDEX == 1 }">
	            	<li class="subentry" code="system_index" mark="/sysBody/show"><span>首页模板<i class="icon-red"></i></span></li>
				</c:if>
				<c:if test="${empty sessionScope.SYSTEM_CYCLE or sessionScope.SYSTEM_CYCLE == 1 }">
	            	<li class="subentry" code="system_cycle" mark="/sysCyclePic/showPic"><span>首页轮播图</span></li>
				</c:if>
				<c:if test="${empty sessionScope.SYSTEM_SEO or sessionScope.SYSTEM_SEO == 1 }">
	            	<li class="subentry" code="system_seo" mark="/sysConfigIco/showIco"><span>SEO配置</span></li>
				</c:if>
				<c:if test="${empty sessionScope.SYSTEM_ICO or sessionScope.SYSTEM_ICO == 1 }">
	            	<li class="subentry" code="system_ico" mark="/sysConfigIco/showLogo"><span>浏览器图标</span></li>
				</c:if>
				<c:if test="${empty sessionScope.SYSTEM_CODE or sessionScope.SYSTEM_CODE == 1 }">
	            	<li class="subentry" code="system_code" mark="/sysConfigIco/showCode"><span>统计分析代码</span></li>
				</c:if>
				<c:if test="${empty sessionScope.SYSTEM_CUSTOM_PAGE or sessionScope.SYSTEM_CUSTOM_PAGE == 1 }">
	            	<li class="subentry" code="system_custom_page" mark="/companyConfigCustompage/customPage"><span>自定义页面管理</span></li>
				</c:if>
	<!--            <li class="subentry" code="system_stu_log" mark="/companyFunctionSet/stuLogin"><span>学员登录设置</span></li> -->
	<!--            <li class="subentry" code="system_stu_register" mark="/companyFunctionSet/stuRegister"><span>学员注册设置</span></li> -->
	<!--           	<li class="subentry" code="system_stu_login" mark="/sysConfigStuLogin/setPage">学员登录设置</li> -->
	<!--            <li class="subentry" code="student_group" mark="/studentGroup/setStudentGroup"><span>学员分组</span></li>  -->
				<c:if test="${empty sessionScope.COMPANY_SUBJECT_CUSTOMNAME or sessionScope.SYSTEM_SUBJECT_CUSTOMNAME == 1 }">
	            	<li class="subentry" code="company_subject_customname" mark="/company/updateSubjectName"><span>自定义学科</span></li> 
				</c:if>
        	</c:when>
        	<c:otherwise>
            	<li class="subentry" code="system_head" mark="/sysPageHeadFoot/showHead"><span>页头设置<%-- <i class="icon-red"></i> --%></span><%-- <em></em> --%></li>
<!--            <li class="subentry" code="system_foot" mark="/sysPageHeadFoot/toConfigFooter">页尾设置</li> -->
            	<li class="subentry" code="system_foot" mark="/companyHeadFootConfig/showFootTemplete"><span>页尾设置</span></li>
            	<li class="subentry" code="system_index" mark="/sysBody/show"><span>首页模板<i class="icon-red"></i></span></li>
            	<li class="subentry" code="system_cycle" mark="/sysCyclePic/showPic"><span>首页轮播图</span></li>
            	<li class="subentry" code="system_seo" mark="/sysConfigIco/showIco"><span>SEO配置</span></li>
            	<li class="subentry" code="system_ico" mark="/sysConfigIco/showLogo"><span>浏览器图标</span></li>
            	<li class="subentry" code="system_code" mark="/sysConfigIco/showCode"><span>统计分析代码</span></li>
            	<li class="subentry" code="system_custom_page" mark="/companyConfigCustompage/customPage"><span>自定义页面管理</span></li>
<!--            <li class="subentry" code="system_stu_log" mark="/companyFunctionSet/stuLogin"><span>学员登录设置</span></li> -->
<!--            <li class="subentry" code="system_stu_register" mark="/companyFunctionSet/stuRegister"><span>学员注册设置</span></li> -->
<!--           	<li class="subentry" code="system_stu_login" mark="/sysConfigStuLogin/setPage">学员登录设置</li> -->
<!--            <li class="subentry" code="student_group" mark="/studentGroup/setStudentGroup"><span>学员分组</span></li>  -->
	            <li class="subentry" code="company_subject_customname" mark="/company/updateSubjectName"><span>自定义学科</span></li> 
        	</c:otherwise>
        </c:choose>
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
