<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!-- 二级导航 -->
<div class="left-side">
    <div class="left-side-title">
        <i class="icon iconfont" style="float: left;color: #cdcccc;font-size: 20px;margin-left: 2px;" id="backUp">&#xe650;</i>
        <em title='${company.companyName }' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width: 200px;display : inline-block;'>${company.companyName }</em>
        <!-- <span class="iconfont return-pic hcancle">&#xe650;</span> -->
    </div>
    <ul id="course_manage" class="system_managelist">
        <li class="subentry" code="getClassInfo" mark="/classManager/getClassInfo/${companyId}">详情</li>
        <li class="subentry" code="berkeleyOrder" mark="/berkeley/berkeleyOrder/${companyId}">订单查询</li>
        <li class="subentry" code="permissionManagement" mark="/berkeley/permissionManagement/${companyId}">权限管理</li>
        <li class="subentry" code="classManagerGetClassList" mark="/classTypeManage/queryClassType/${companyId}">课程管理</li>
        <li class="subentry" code="getServiceManager" mark="/serviceManager/getServiceManager/${companyId}">服务管理</li>
        <li class="subentry" code="getFirstItems" mark="/teacherManger/getFirstItems/${companyId}">老师管理</li>
        <li class="subentry" code="classQueryGetClassList" mark="/classQuery/getClassList/${companyId}">分校课程查询</li>
        <li class="subentry" code="getVideoResourceAndMessageStatistics" mark="/computingResource/getVideoResourceAndMessageStatistics/${companyId}">计算资源查询</li>
        <li class="subentry"  code="watchInfoList" mark="/query/statistics/watchInfoListAdmin">
            <p class="managelist-parent">直播统计</p>
            <b class="arrow-bottom"><i class="bottom-arrow1"></i><i class="bottom-arrow2"></i></b>
            <ul class="managelist-child">
                <li class="item-child" code="watchInfoCurrentCoun" mark="/query/statistics/watchInfoCurrentCountAdmin" >— 直播课程并发</li>
            </ul>
        </li>
        <li class="subentry" code="videoList" mark="/query/statistics/videoCourseIndexAdmin">
            <p class="managelist-parent">点播统计</p>
            <b class="arrow-bottom"><i class="bottom-arrow1"></i><i class="bottom-arrow2"></i></b>
            <ul class="managelist-child">
                <li class="item-child" code="teacherVideoList" mark="/query/statistics/teacherVideoListAdmin">教师授课详情</li>
                <li class="item-child" code="userVideoList" mark="/query/statistics/userVideoListAdmin">用户点播统计</li>
            </ul>
        </li>
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
            /*window.history.go(-1);*/
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
    //点击返回，返回上一级
    $('#backUp').click(function () {
        window.history.go(-1);
    });
</script>

