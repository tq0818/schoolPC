<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/decorators/import.jsp"%>
<script>
    $(function(){
        $selectMenu('brach_school');
        $.ajax({
            url : rootPath + "/serviceGroup/couseMethod",
            type : "post",
            async:false,
            success : function(result){
                if(result==""||result.status==0){
                    $("#courseUrl").attr("href",rootPath+"/berkeley/berkeleyIndex");
                }else{
                    $("#courseUrl").attr("href",rootPath+"/berkeley/berkeleyIndex");
                }
            }
        });
    })
</script>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe67c;</i>分校</a>
                <ul class="nav nav-left navspace">
                    <shiro:hasPermission name="brach_school_manage">
                        <li code="course_class_type"><a id="courseUrl">分校管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="base_school">
                        <li code="baseSchoolLive"><a href="<%=rootPath %>/baseSchool/baseSchoolLive" >基地校</a></li>
                    </shiro:hasPermission>
                </ul>
    </div>
</div>