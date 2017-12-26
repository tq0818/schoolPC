<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>${type=='save'?'新增用户':'编辑用户' }</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
     <script type="text/javascript" src="<%=rootPath %>/javascripts/my.jquery.validate.js"></script>
    
     <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('sys_user_auth');
    	});
    </script>
	<style>
		.editToB select{margin: 5px 0}
		.editDelete{color: red !important;}
		.Confirm_Close{display:none;}
	</style>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include> 
<div class="u-wrap admin overflow">
     <jsp:include page="/WEB-INF/jsp/menu/menu_berkeleyLeft.jsp"></jsp:include>
    <div class="right-side">
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">${type=='save'?'新增用户':'编辑用户' }</h2>
            <span class="line"></span>
        </div>
        <div class="users-infos">
        <form id="saveUserForm" method="post"  onsubmit="return Form.editUserMsg();">            
            <div class="sm-heading">
                <h2 class="h6">基本信息</h2>
            </div>
            	<input id="uId" type="hidden" name="id" value="${user.id }"/>
  				<input type="hidden" name="type" id="type" value="${type }"/>
  				<input type="hidden" name="teachersId" id="teachersId"/>
  				<input type="hidden" name="rolesId" id="rolsesId"/>
  				<input type="hidden" name="usernames" id="usernames"/>
  				<input type="hidden" name="companyId" id="companyId" value="${companyId}"/>
  				<input type="hidden" name="tId" id="tsId"/>
  				<input type="hidden" name="isGrant" id="isGrant"/>
  				<input type="hidden" name="proxyOrgId" id="proxyOrgId" value="${user.proxyOrgId }"/>
  				<input type="hidden" value="${user.userType }" id="userType_status"/>
	            <div class="users-infos-list">
	                <p class="c">
	                    <span class="c-title">登录账号&nbsp;<c:if test="${type=='save' }"><i style="color: red;" class="iconfont ico">&#xe605;</i></c:if></span>
	                    <span class="c-content" id="ucontent">
	                    	<c:if test="${type=='save' }">
	                    	  <input id="userName" name="username" type="text" placeholder="输入账号或手机号搜索"/>
	                    	</c:if>
	                    	<c:if test="${type!='save' }">
	                    	  <input id="nameMark" disabled="disabled" name="usernames" type="text" value="${user.username }" disabled/>
	                    	</c:if>
	                    </span>
	                </p>
	                 <p class="c">
	                    <span class="c-title">性别&nbsp;&nbsp;</span>
	                    <span class="c-content">
	                    	<c:if test="${user.sex==null }">
	                    		  <input name="sex" style="height: 15px;" checked="checked" type="radio" value="MALE">男
		                     	  <input name="sex" style="height: 15px;" type="radio" value="FEMALE">女
	                    	</c:if>
	                    	<c:if test="${user.sex!=null }">
	                    		<c:if test="${user.sex=='MALE' }">
	                    			 <input name="sex" style="height: 15px;" checked="checked" type="radio" value="MALE">男
		                             <input name="sex" style="height: 15px;" type="radio" value="FEMALE">女
	                    		</c:if>
	                    		 <c:if test="${user.sex!='MALE' }">
	                    			 <input name="sex" style="height: 15px;"  type="radio" value="MALE">男
		                             <input name="sex" style="height: 15px;" checked="checked" type="radio" value="FEMALE">女
	                    		</c:if>
	                    	</c:if>
		                   
	                    </span>
	                </p>
	                <p class="c">
	                    <span class="c-title">密码&nbsp;<c:if test="${type=='save' }"><i style="color: red;" class="iconfont ico">&#xe605;</i></c:if></span>
	                    <span class="c-content" id="pcontent">
	                    	<input id="password1" name="password1" type="password" style="display:none;"/>
	                    	<input id="password" name="password" type="password"/>
	                    </span>
	                </p>
	                <p class="c">
	                    <span class="c-title">确认密码&nbsp;<c:if test="${type=='save' }"><i style="color: red;" class="iconfont ico">&#xe605;</i></c:if></span>
	                    <span class="c-content"><input id="confirmPassword" name="confirmPassword" value="" type="password"></span>
	                </p>
	                <p class="c">
	                    <span class="c-title">姓名&nbsp;<i style="color: red;" class="iconfont ico">&#xe605;</i></span>
	                    <span class="c-content"><input id="realName" name="realName" type="text" value="${user.realName }"></span>
	                </p>
	                <p class="c">
	                    <span class="c-title">邮箱&nbsp;<i style="color: red;" class="iconfont ico">&#xe605;</i></span>
	                    <span class="c-content"><input id="email" name="email" type="text" value="${user.email }"></span>
	                </p>
	                  <p class="c">
	                    <span class="c-title">手机号&nbsp;<i style="color: red;" class="iconfont ico">&#xe605;</i></span>
	                    <span class="c-content"><input id="mobile" name="mobile" type="text" value="${user.mobile }"></span>
	                </p>
	            </div>
            <div class="sm-heading">
                <h2 class="h6">角色选择</h2>
            </div>
            <div class="people-list">
            	<c:forEach items="${authRoleList }" var="role">
            		<c:if test="${role.roleName ne '班主任' and role.roleName ne '任课老师' and role.roleName ne '区县负责人' and role.roleName ne '学校负责人'}">
	            		<c:choose>
	            			<c:when test="${role.roleName == '代理机构' }">
	            				<a href="javascript:;" ids="${role.roleUid }" class="btn btn-mini btn-default" id="org-manage" style="display: none;">${role.roleName }</a>
	            			</c:when>
	            			<c:otherwise>
	            				<a href="javascript:;" ids="${role.roleUid }" class="btn btn-mini btn-default">${role.roleName }</a>
	            			</c:otherwise>
	            		</c:choose>
            		</c:if>
            		<c:if test="${role.roleName=='区县负责人'}">
            			<a href="javascript:;" ids="${role.roleUid }" class="btn btn-mini btn-default areamaster">区县负责人</a>
            		</c:if>
            		<c:if test="${role.roleName=='学校负责人'}">
            			<a href="javascript:;" ids="${role.roleUid }" class="btn btn-mini btn-default schoolmaster">学校负责人</a>
            		</c:if>
            		<c:if test="${role.roleName=='班主任'}">
            			<a href="javascript:;" ids="${role.roleUid }" class="btn btn-mini btn-default headmaster">班主任</a>
            		</c:if>
            		<c:if test="${role.roleName=='任课老师'}">
            			<a href="javascript:;" ids="${role.roleUid }" class="btn btn-mini btn-default classTeacher">任课老师</a>
            		</c:if>
            	</c:forEach>
            </div>
            <div id="org-manage-list" style="display: none;">
             <div class="sm-heading">
                <h2 class="h6">关联代理机构</h2>
            </div>
            	
            <div class="org-list" >
            	<em style="color: #999;">说明：请在此处关联代理机构，学员在第一次购买课程时可选择被绑定代理机构，此管理员登录后台后也只可查看与此代理机构相关的学员数据。</em></br>
            	<div style="padding-top: 20px;">
            	选择代理机构： 
            	<c:forEach items="${proxyOrgs }" var="org">
            		<a href="javascript:;" ids="${org.id }" class="btn btn-mini btn-default orgs" style="margin: 2px;">${org.name }</a>
            	</c:forEach>
            	</div>
            </div>
            </div>
            <div class="pri-list">
                <ul class="clear" id="menuList">
                <c:forEach items="${privilegeList }" var="category">
                		<li style="display: none;">
	                        <p class="c-title" marks="${category.id }">
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
                <li class="editToB areamasterToB"></li>
                <li class="editToB schoolmasterToB"></li>
                <li class="editToB classTeacherToB"></li>
                <li class="editToB headmasterToB"></li>
                </ul>
            </div>
            <div id="contactTeacher" style="display: none;">
	            <div class="sm-heading">
	            		<h2 class="h6">绑定老师<span style="font-size: 10px;color:gray;">&nbsp;&nbsp;请为此账户绑定一个老师，安排给绑定老师的直播课，需要用此账号登陆上课。还没有老师，<a href="<%=rootPath %>/sysConfigTeacher/toTeacherIndex" style="color:blue;" target="_blank">点我新增老师</a></span></h2>
	             </div>
	            <div class="teacher-type">
	            	<input type="text" id="teacherName" placeholder="输入教师名称"/>
	                <input type="button" onclick="Form.searchTeacher()" class="btn btn-default btn-sm" value="搜索">
	            </div>
	             <div class="teacher-list">
	             	<c:forEach items="${teacherList }" var="t">
	             		<a href="javascript:;" style="width:300px;"><input type="checkbox" name="tlist" checked="checked" value="${t.id }"/><span>${t.teacherName } ${wx:dictCode2Name(t.sex)} ${t.mobile }</span></a>
	             	</c:forEach>
	             	<div id="tList"></div>
	            </div>
            </div>
            <div class="t-btns">
                <p class="text-center">
                    <input type="submit" class="btn btn-primary" value="保存"></input>
                    <a href="javascript:history.go(-1);" class="btn btn-default">取消</a>
                </p>
            </div>
             </form>
        </div>
    </div>
</div>
</div>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/branchschool/editSystemAuth.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>


<script type="text/javascript">
    <%--任课老师点击删除，删除该班级--%>
	 $('.editToB').on('click','.editDelete',function(){
	    $(this).parent('.c').remove();
	});
	//	点击任课老师，出来任课老师权限
	$('.headmaster').click(function(){
	    if($(this).hasClass('btn-success')){
            $('.areamasterToB').empty();
        }
	});
    $('.classTeacher').click(function(){
        if($(this).hasClass('btn-success')){
            $('.headmasterToB').empty();
        }
    });
</script>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript">
    $(function() {
        $selectSubMenu('course_class_type');
        $selectSubMenus('permissionManagement');
    });
    $('.areamaster').click(function(){
        if($(this).hasClass('btn-success')){
            $('.areamasterToB').empty();
        }
    });
    $('.schoolmaster').click(function(){
	    if($(this).hasClass('btn-success')){
            $('.schoolmasterToB').empty();
        }
	});
</script>
</body>
</html>
