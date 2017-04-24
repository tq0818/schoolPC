<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/decorators/import.jsp"%>
<script type="text/javascript">
$(function(){
	$selectMenu('student_head');
})
</script>
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe61b;</i>学员</a>
        <ul class="nav nav-left navspace">
        	<shiro:hasPermission name="student_manage">
        	<li code="student_manage"><a href="<%=rootPath %>/student/studentList">学员管理</a></li>
        	</shiro:hasPermission>
<%--              <shiro:hasPermission name="student_apply">  
            <li code="student_apply"><a href="<%=rootPath %>/student">手工报名</a></li>
            </shiro:hasPermission> --%>
<%--              <shiro:hasPermission name="student_change">  
            <li code="student_change"><a href="<%=rootPath %>/student/toStuSearch">学员异动</a></li>
            </shiro:hasPermission> --%>
<%--              <shiro:hasPermission name="student_add_fee">  
            <li code="student_add_fee"><a href="<%=rootPath %>/student/toSearch">补费</a></li>
            </shiro:hasPermission> --%>
<%--              <shiro:hasPermission name="student_agent_material">  
            <li code="student_agent_material"><a href="<%=rootPath %>/studentAgentMaterial/stuMaterial">报考材料</a></li>     
            </shiro:hasPermission> --%>
             <shiro:hasPermission name="student_urge_fee">         
            <li code="student_urge_fee"><a href="<%=rootPath %>/fee/page/urgeFee">催缴</a></li>    
            </shiro:hasPermission>
             <shiro:hasPermission name="student_agent">          
            <li code="student_agent"><a href="<%=rootPath %>/exam/registerPage">代报考</a></li>
            </shiro:hasPermission>
<%--              <shiro:hasPermission name="netschool_remote">  
            <li code="netschool_remote"><a href="<%=rootPath %>/fee/stuLong">远程结费</a></li>
            </shiro:hasPermission> --%>
             <shiro:hasPermission name="student_message">  
            <li code="student_message"><a href="<%=rootPath %>/student/notice" >学员通知</a></li>
            </shiro:hasPermission>
<%--             <shiro:hasPermission name="student_order">  
            <li code="student_order"><a href="<%=rootPath %>/payOrder/toOrder" >转账确认</a></li>
            </shiro:hasPermission> --%>
          <shiro:hasPermission name="company_member_vip">
            <li code="company_member_vip"><a href="<%=rootPath %>/companyMemberConfig/companyMemberVip" >会员管理</a></li>
           </shiro:hasPermission> 
        </ul>
    </div>
</div>