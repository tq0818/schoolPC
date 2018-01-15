<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe61d;</i>资源</a>
        <ul class="nav nav-left navspace">
             <shiro:hasPermission name="resource_item">  
            <li code="resource_item"><a href="<%=request.getContextPath() %>/sysConfigItem/project">结构管理</a></li>
            </shiro:hasPermission>
             <shiro:hasPermission name="resource_tree">
            <li code="resource_tree"><a href="<%=request.getContextPath() %>/sysKnowledgeTree/knowledgeTreeIndex">知识树管理</a></li>
            </shiro:hasPermission>
             <shiro:hasPermission name="resource_teacher">
            <li code="resource_teacher"><a href="<%=request.getContextPath()%>/sysConfigTeacher/toTeacherIndex">老师</a></li>
            </shiro:hasPermission>
             <shiro:hasPermission name="resource_manager">  
            <li code="resource_manager"><a href="<%=request.getContextPath()%>/sysConfigTeacher/toEducationInfo">教务</a></li>
            </shiro:hasPermission>
            <c:if test="${isAreaSchool1 eq 0}">
                 <shiro:hasPermission name="resource_campus">
                <li code="resource_campus"><a href="<%=request.getContextPath()%>/sysConfigCampus/toCampusInfo">校区</a></li>
                </shiro:hasPermission>
                 <shiro:hasPermission name="resource_classroom">
                <li code="resource_classroom"><a href="<%=request.getContextPath() %>/sysConfigClassroom/classroom">教室</a></li>
                </shiro:hasPermission>
            </c:if>
            <shiro:hasPermission name="resource_video">  
            <li code="resource_video"><a href="<%=request.getContextPath() %>/video/toVideo">资源库</a></li>
            </shiro:hasPermission>
            <%--<c:if test="${isAreaSchool1 eq 0}">--%>
            <%--<li id="resourceProtocol" code="resource_protocol" ><a href="<%=request.getContextPath() %>/courseProtocolConfig/toProtocolList" id="resource_protocol">课程协议</a></li>--%>
        	<%--<shiro:hasPermission name="resource_org">--%>
            <%--<li code="resource_org" id="resourceOrg"><a href="<%=request.getContextPath() %>/companyConfigProxyOrg/viewPrxoyList">代理机构</a></li>--%>
            <%--</shiro:hasPermission>--%>
            <%--</c:if>--%>
        </ul>
    </div>
</div>
<script>
	$(function(){
		$selectMenu('resource_head');
		var courseStatus = "";
		var packageStatus = "";
		$.ajax({
			 url:rootPath+"/companyFunctionSet/queryCompanyProtocolExist",
			 type:"post",
			 async: false,
			 data:{"functionCode":"CLASS_POTOCOL_SET"},
			 dataType:"json",
			 success:function(jsonData){
				 if(jsonData && jsonData == "success"){
					 courseStatus = "success";
				 }
			 }
		 })
		 
		 $.ajax({
			 url:rootPath+"/companyFunctionSet/queryCompanyProtocolExist",
			 type:"post",
			 async: false,
			 data:{"functionCode":"CLASSPACKAGE_POTOCOL_SET"},
			 dataType:"json",
			 success:function(jsonData){
				 if(jsonData && jsonData == "success"){
					 packageStatus = "success";
				 }
			 }
		 })
		 
		 if(packageStatus == "success" || courseStatus == "success"){
			 $('#resourceProtocol').show();
		 }else{
			 $('#resourceProtocol').hide();
		 }
		
		$.ajax({
			 url:rootPath+"/companyInvitConfigOrg/checkOpenProxy",
			 type:"post",
			 async: false,
			 dataType:"json",
			 success:function(jsonData){
				 if(jsonData && jsonData == "success"){
					 $('#resourceOrg').show();
				 }else{
					 $('#resourceOrg').hide();
				 }
			 }
		 })
	});
	
	
</script>