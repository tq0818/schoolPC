<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${type=='save'?'新增用户':'编辑用户' }</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>

    <script type="text/javascript">
        $(function(){
            $selectSubMenu('sys_user_auth');
        });
    </script>

    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/system/order.js"></script>--%>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/berkeley.js"></script>--%>
    <style type="text/css">
        .head-div {
            position: relative;
            margin-top: 15px;
            padding: 3px 8px;
        }

        .font-size {
            font-size: 14px;
            margin-left: 10px;
            margin-right: 11px;
        }
    </style>
    <%--tob--%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
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
                    <div class="sm-heading">
                        <h2 class="h6">基本信息</h2>
                    </div>
                    <form id="saveUserForm" method="post">
                        <input id="uId" type="hidden" name="id" value="${user.id }"/>
                        <input type="hidden" name="type" id="type" value="${type }"/>
                        <input type="hidden" name="teachersId" id="teachersId"/>
                        <input type="hidden" name="rolesId" id="rolsesId"/>
                        <input type="hidden" name="usernames" id="usernames"/>
                        <input type="hidden" name="tId" id="tsId"/>
                        <input type="hidden" name="proxyOrgId" id="proxyOrgId" value="${user.proxyOrgId }"/>
                        <input type="hidden" value="${user.userType }" id="userType_status"/>
                        <div class="users-infos-list">
                            <p class="c" id="pcOne">
                                <span class="c-title">所属分校</span>
                                <span class="c-content">
	                    	<select name="schoolId" id="schoolId">
				       			<c:forEach items="${schoolList }" var="school">
                                    <c:if test="${school.id==schoolId }">
                                        <option value="${school.id }" selected="selected">${school.schoolName }</option>
                                    </c:if>
                                    <c:if test="${school.id!=schoolId }">
                                        <option value="${school.id }">${school.schoolName }</option>
                                    </c:if>
                                </c:forEach>
				       			<c:if test="${empty schoolList }">
                                    <option value="${school1.id }" selected="selected">${school1.schoolName }</option>
                                </c:if>
				       		</select>
	                    </span>
                            </p>
                            <p class="c">
                                <span class="c-title">登录账号&nbsp;<c:if test="${type=='save' }"><i style="color: red;" class="iconfont ico">&#xe605;</i></c:if></span>
                                <span class="c-content">
	                    	<c:if test="${type=='save' }">
                                <input id="userName" name="username" type="text">
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
                                <span class="c-content"><input id="password" name="password" type="password"></span>
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
                    </form>
                    <div class="sm-heading">
                        <h2 class="h6">角色选择</h2>
                    </div>
                    <div class="people-list">
                        <c:forEach items="${authRoleList }" var="role">
                            <c:choose>
                                <c:when test="${role.roleName == '代理机构' }">
                                    <a href="javascript:;" ids="${role.roleUid }" class="btn btn-mini btn-default" id="org-manage" style="display: none;">${role.roleName }</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:;" ids="${role.roleUid }" class="btn btn-mini btn-default">${role.roleName }</a>
                                </c:otherwise>
                            </c:choose>
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
                        <ul class="clear">
                            <c:forEach items="${privilegeList }" var="category">
                                <li style="display: none;">
                                    <p class="c-title" marks="${category.id }">
                                        <!-- 	                            <i class="iconfont">&#xe60a;</i> -->
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
                            <a href="javascript:Form.editUserMsg();" class="btn btn-primary">保存</a>
                            <a href="javascript:history.go(-1);" class="btn btn-default">取消</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
        <%--<script type="text/javascript" src="<%=rootPath %>/javascripts/system/editSystemAuth.js"></script>--%>
        <script type="text/javascript" src="<%=rootPath %>/javascripts/system/addUsers.js"></script>
        <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    </div>
</div>
<input type="hidden" value="5" id="pageSize">


<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript">
    $(function() {
        $selectSubMenu('financial');
        $selectSubMenus('operate_fee_confirm');
    });
</script>
</body>
</html>