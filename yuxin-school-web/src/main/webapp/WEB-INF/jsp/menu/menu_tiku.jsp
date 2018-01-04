<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="full-wrap navbar smbar">
    <div class="header tiHeader">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe6c8;</i>题库管理</a>
        <ul class="nav nav-left navspace tikuhader">
            <li><a href="<%=request.getContextPath() %>/tikuCategory/toTiku" class="tiku">题库</a></li>
            <c:if test="${isAreaSchool1 eq 0}">
                <shiro:hasPermission name="tiku_exam">
                    <li><a href="<%=request.getContextPath() %>/tikuExam/toTikuExamIndex" class="exam">考试</a></li>
                </shiro:hasPermission>
            </c:if>
            <c:if test="${sessionScope.loginUser.companyId == 18093}">
      			<li><a href="<%=request.getContextPath() %>/sysStudentScore/competitionScore" class="competitionScore">竞赛成绩</a></li>
      		</c:if>
        </ul>
    </div>
</div>
<script type="text/javascript">
	$(function(){
		$selectMenu('tiku_header');		
	})
</script>