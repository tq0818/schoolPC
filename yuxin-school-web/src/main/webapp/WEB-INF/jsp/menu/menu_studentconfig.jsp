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
        	<em>学员设置</em>
        	<span class="iconfont return-pic hcancle">&#xe650;</span>
        </div>
        <c:choose>
        	<c:when test="${sessionScope.company.memberLevel == 12 or sessionScope.company.memberLevel == 13}">
	        <ul id="course_manage" class="system_studentlist">
	       			<li class="subentry" code="system_stu_register" mark="/companyFunctionSet/stuRegister"><span>学员注册设置</span></li>
		        <c:if test="${empty sessionScope.SYSTEM_STU_LOG or sessionScope.SYSTEM_STU_LOG == 1 }">
	            	<li class="subentry" code="system_stu_log" mark="/companyFunctionSet/stuLogin"><span>学员登录设置</span></li>
        		</c:if>
	            
	            <c:if test="${empty sessionScope.STUDENT_GROUP or sessionScope.STUDENT_GROUP == 1 }">
	            	<li class="subentry" code="student_group" mark="/studentGroup/setStudentGroup"><span>学员分组</span></li> 
	            </c:if>
	           	<c:if test="${bind_status == '1' }">
	            	<li class="subentry" code="system_stu_bind" mark="/companyHardbindData/toList"><span>绑定学员mac</span></li> 
	      		</c:if>
			</ul>
        	</c:when>
        	<c:otherwise>
	        	<ul id="course_manage" class="system_studentlist">
		            <li class="subentry" code="system_stu_register" mark="/companyFunctionSet/stuRegister"><span>学员注册设置</span></li>
		            <li class="subentry" code="system_stu_log" mark="/companyFunctionSet/stuLogin"><span>学员登录设置</span></li>
		            <li class="subentry" code="student_group" mark="/studentGroup/setStudentGroup"><span>学员分组</span></li> 
		           	<c:if test="${bind_status == '1' }">
		            	<li class="subentry" code="system_stu_bind" mark="/companyHardbindData/toList"><span>绑定学员mac</span></li> 
		      		</c:if>
				</ul>
        	</c:otherwise>
        </c:choose>
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
	$(".system_studentlist").find("li").each(function() {
		if ($(this).attr("code") == code) {
			$(this).addClass("active").siblings("li").removeClass("active");
		}
	})
 }
/*新功能提示*/
function newfn(newList){
	if(newList.length>0){
		$(".system_studentlist .subentry").each(function(i, dom){
			if($.inArray($.trim($(this).find("span").html()), newList)!=-1){
				$(this).append('<em></em>');
			}
		})
	}
}

// $(function(){
// 	var newList=new Array();
// 	newList=[];  //配置新功能列表
// 	newfn(newList);
// })
</script>
