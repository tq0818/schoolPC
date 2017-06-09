<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 二级导航 -->
 <div class="left-side">
    	<div class="left-side-title">
        	<em>查询统计</em>
        	<!-- <span class="iconfont return-pic hcancle">&#xe650;</span> -->
        </div>
        <ul id="course_manage" class="system_managelist">
        	<shiro:hasAnyRoles name="教科院,区县负责人,直属校负责人">
            <li class="subentry active" code="studentList" mark="/query/areastatistics/studentList">学员</li>
            <li class="subentry" code="orgStuList" mark="/query/areastatistics/queryOrg">学校</li>
      		</shiro:hasAnyRoles>
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

    $selectSubMenu('statistics_area_detail');
});
</script>