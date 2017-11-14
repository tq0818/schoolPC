<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 二级导航 -->
 <div class="left-side">
    	<div class="left-side-title">
        	<em>查询统计</em>
        	<!-- <span class="iconfont return-pic hcancle">&#xe650;</span> -->
        </div>
        <ul id="course_manage" class="system_managelist tj_managelist">
        	<shiro:hasAnyRoles name="文轩教育,教科院,区县负责人,学校负责人">
            <li class="subentry active" code="studentList" mark="/query/statistics/studentList"><p class="managelist-parent">学员</p></li>
            <li class="subentry" code="orgStuList" mark="/query/statistics/queryOrg"><p class="managelist-parent">学校</p></li>
            <li class="subentry"  code="watchInfoList" mark="/query/statistics/watchInfoList">
                <p class="managelist-parent">直播统计</p>
                <%--<b class="arrow-bottom"><i class="bottom-arrow1"></i><i class="bottom-arrow2"></i></b>
                <ul class="managelist-child">
                    <li class="item-child" code="watchInfoList" >— 直播课程并发</li>
                </ul>--%>
            </li>
           <%-- <li class="subentry" code="teacherVideoList" mark="/query/statistics/teacherVideoList"><p class="managelist-parent">教师授课详情</p></li>
            <li class="subentry" code="userVideoList" mark="/query/statistics/userVideoList"><p class="managelist-parent">用户点播统计</p></li>--%>
            <li class="subentry" code="videoList" mark="/query/statistics/videoCourseIndex">
                <p class="managelist-parent">点播统计</p>
                <b class="arrow-bottom"><i class="bottom-arrow1"></i><i class="bottom-arrow2"></i></b>
                <ul class="managelist-child">
                    <li class="item-child" code="teacherVideoList" mark="/query/statistics/teacherVideoList">教师授课详情</li>
                    <li class="item-child" code="userVideoList" mark="/query/statistics/userVideoList">用户点播统计</li>
                </ul>
                </li>
            </shiro:hasAnyRoles>
       </ul>
    </div>
<script>
$(document).ready(function(){
	 //点击左侧菜单
	 $("#course_manage").on('click','li',function(e){
         e.stopPropagation();
         var url=$(this).attr("mark");
       /*  if(url){*/
             window.location.href=rootPath+url;
     /*    }else{
             $selectThirdMenu($(this).attr("code"));
         }*/

	 });
	 //返回
	 $(".hcancle").on('click',function(){
		 window.location.href=rootPath+"/company/companyService";
	 });

    $selectSubMenu('statistics_all_detail');
});
</script>