<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>角色管理</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
    <style type="text/css">
    	.btn.btn-mini, input.btn-mini{
    		margin: 3px;
    	}
    </style>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    
     <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('system_company_authRole');
    		$("#companyId_mark").select2();
    	});
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_config.jsp"></jsp:include>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">角色管理</h2>
            <span class="line"></span>
        </div>
        <div class="users-infos"> 
        	 <div class="sm-heading">
                <h2 class="h6">角色列表</h2>
            </div>
            <div>
            	<span style="margin-left: 14px;">公司ID&nbsp;&nbsp;
            	<select id="companyId_mark" style="min-width: 100px;">
            		<option value="0" selected="selected">0</option>
            		<c:forEach items="${companyIds }" var="com">
            			<option value="${com.id }">${com.id }</option>
            		</c:forEach>
            	</select>
            	</span>
            </div>
            <div class="people-list">
<%--             	<c:forEach items="${authRoleList }" var="role"> --%>
<%--             		<a href="javascript:;" ids="${role.roleUid }" class="btn btn-mini btn-default">&nbsp;&nbsp;${role.roleName } --%>
<%--             		<em class="iconfont del_authRole" ids="${role.id }" style="font-size: 12px;">&#xe610;</em></a> --%>
<%--             	</c:forEach> --%>
            </div>
            <span class="c-title" style="font-size: 14px;margin-left: 15px;">角色名称</span>
            <span class="c-content" style="margin-left: 10px;"><input id="roleName" type="text" value=""></span>
<!--              <span class="c-title" style="font-size: 14px;margin-left: 15px;">公司ID</span> -->
<!--             <span class="c-content" style="margin-left: 10px;"><input id="companyAuthId" type="text" value=""></span> -->
             <span class="c-title" style="font-size: 14px;margin-left: 15px;">是否为管理员</span>
            <span class="c-content" style="margin-left: 10px;" id="manageRoleFlag"><input type="radio" value="1" name="roleFlag"/>是&nbsp;&nbsp;<input checked="checked" value="0" type="radio" name="roleFlag"/>否</span>
            <div class="sm-heading">
                <h2 class="h6">选择权限菜单</h2>
            </div>
            <div class="pri-list">
                <ul class="clear">
                <c:forEach items="${privilegeList }" var="category">
                		<li>
	                        <p class="c-title" marks="${category.id }">
	                            <i class="iconfont">&#xe609;</i>
	                            <span>${category.categoryName }</span>
	                        </p>
	                        <c:forEach items="${category.arr }" var="childCategory">
		                        <p class="c"  marks="${childCategory.id }">
		                            <i class="iconfont">&#xe609;</i>
		                            <span>${childCategory.description }</span>
		                        </p>
	                        </c:forEach>
                       </li>
                </c:forEach>
                </ul>
            </div>
            <div class="t-btns">
                <p class="text-center">
                    <a href="javascript:Form.addRolePrivliage(this);" class="btn btn-primary">保存</a>
                    <a href="javascript:Form.giveUp();" class="btn btn-default">取消</a>
                </p>
            </div>
        </div>
    </div>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/system/companyRole.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
  <script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>
</body>
</html>