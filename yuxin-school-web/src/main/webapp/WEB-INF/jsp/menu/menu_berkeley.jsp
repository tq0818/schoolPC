<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/decorators/import.jsp"%>
<script>
    $(function(){
        $selectMenu('berkeley');
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
        <c:choose>
            <c:when test="${sessionScope.company.memberLevel == 12 or sessionScope.company.memberLevel == 13}">
                <ul class="nav nav-left navspace">
                    <shiro:hasPermission name="course_class_type">
                        <li code="course_class_type"><a id="courseUrl">分校管理</a></li>
                    </shiro:hasPermission>
                    <!-- <shiro:hasPermission name="course_module_no">
	            <li code="course_module_no"><a href="<%=rootPath %>/classModuleLesson/classes" >排课</a></li>
	            </shiro:hasPermission> -->
                        <%--              <shiro:hasPermission name="class_resource">   --%>
                        <%--             <li code="class_resource"><a href="<%=rootPath %>/classModuleLesson/classesResource" >课程资料</a></li> --%>
                        <%--             </shiro:hasPermission> --%>
                    <c:if test="${empty sessionScope.SERVICE_OPENCLASS or sessionScope.SERVICE_OPENCLASS == 1 }">
                        <shiro:hasPermission name="open_class_set">
                            <li code="baseSchoolLive"><a href="<%=rootPath %>/baseSchool/baseSchoolLive" >基地校</a></li>
                        </shiro:hasPermission>
                    </c:if>
                    <c:if test="${empty sessionScope.SERVICE_CLASS_PACKAGE or sessionScope.SERVICE_CLASS_PACKAGE == 1 }">
                        <shiro:hasPermission name="class_package">
                            <li code="course_package"><a href="<%=rootPath %>/classPackage/list" ></a></li>
                        </shiro:hasPermission>
                    </c:if>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="nav nav-left navspace">
                    <shiro:hasPermission name="course_class_type">
                        <li code="course_class_type"><a id="courseUrl">分校管理</a></li>
                    </shiro:hasPermission>
                    <!-- <shiro:hasPermission name="course_module_no">
	            <li code="course_module_no"><a href="<%=rootPath %>/classModuleLesson/classes" >排课</a></li>
	            </shiro:hasPermission> -->
                        <%--              <shiro:hasPermission name="class_resource">   --%>
                        <%--             <li code="class_resource"><a href="<%=rootPath %>/classModuleLesson/classesResource" >课程资料</a></li> --%>
                        <%--             </shiro:hasPermission> --%>

                    <shiro:hasPermission name="open_class_set">
                        <li code="baseSchoolLive"><a href="<%=rootPath %>/baseSchool/baseSchoolLive" >基地校</a></li>
                    </shiro:hasPermission>
                    <%--<shiro:hasPermission name="class_package">--%>
                        <%--<li code="course_package"><a href="<%=rootPath %>/classPackage/list" >课程包</a></li>--%>
                    <%--</shiro:hasPermission>--%>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</div>