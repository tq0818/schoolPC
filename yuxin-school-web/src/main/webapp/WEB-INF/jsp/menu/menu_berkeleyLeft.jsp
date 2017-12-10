<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!-- 二级导航 -->
<div class="left-side">
    <div class="left-side-title">
        <em>成都市三原外国语学校</em>
        <!-- <span class="iconfont return-pic hcancle">&#xe650;</span> -->
    </div>
    <ul id="course_manage" class="system_managelist">
        <li class="subentry" code="getClassInfo" mark="/classManager/getClassInfo/${companyId}">详情</li>
        <li class="subentry" code="berkeleyOrder" mark="/berkeley/berkeleyOrder/${companyId}">订单查询</li>
        <li class="subentry" code="permissionManagement" mark="/berkeley/permissionManagement/${companyId}">权限管理</li>
        <li class="subentry" code="classManagerGetClassList" mark="/classManager/getClassList/${companyId}">课程管理</li>
        <li class="subentry" code="getServiceManager" mark="/serviceManager/getServiceManager/${companyId}">服务管理</li>
        <li class="subentry" code="getFirstItems" mark="/teacherManger/getFirstItems/${companyId}">老师管理</li>
        <li class="subentry" code="classQueryGetClassList" mark="/classQuery/getClassList/${companyId}">分校课程查询</li>
        <li class="subentry" code="getVideoResourceAndMessageStatistics" mark="/computingResource/getVideoResourceAndMessageStatistics/${companyId}">计算资源查询</li>
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
//        $selectSubMenu("org_service");
        //        二级菜单加active
        $selectSubMenu('course_class_type');
    });
    function $selectSubMenus(code) {
        $(".overflow").find(".system_managelist").find("li").each(function() {
            if ($(this).attr("code") == code) {
                $(this).addClass("active").siblings("li").removeClass("active");
            }
        })
    }
</script>

