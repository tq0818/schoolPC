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
        	<shiro:hasAnyRoles name="教科院,区县负责人,学校负责人,文轩教育,班主任,任课老师">
            <li class="subentry active" code="studentList" mark="/query/orgstatistics/studentList">学员</li>
        	<c:if test="${isArea != '0'}">
        		<li class="subentry " code="userList" mark="/query/orgstatistics/userQue">用户统计</li>
        	</c:if>
            <li class="subentry" code="orgStuList" mark="/query/orgstatistics/queryOrg">学校</li>
            <li class="subentry"  code="watchInfoList" mark="/query/statistics/watchInfoList">直播统计</li>
            <li class="subentry" code="videoList" mark="/query/orgstatistics/videoCourseIndex">点播统计</li>
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

    $selectSubMenu('statistics_org_detail');
});
</script>