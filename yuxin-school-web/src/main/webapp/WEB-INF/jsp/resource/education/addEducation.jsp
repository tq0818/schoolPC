<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
    <title>添加教务</title>
    <%@include file="/decorators/import.jsp"%>
    <link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>/stylesheets/resource.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/resource/education/addEducation.js"></script>
</head>
<body>
<!-- header start -->
<!-- header end -->
<!-- 二级导航 -->

<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<div class="u-wrap resource">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">编辑</h2>
            
            <span class="line"></span>
        </div>
        <form id="editInfo">
        <div class="mark-more">
        	
        	<c:if test="${empty teacher.id}">
        	<div class="main-content">
                <div class="m-title">
                    <h2 class="h6">账户信息</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">登陆账号<em>*</em></span>
                            <span class="c-content">
                                <input type="text" name="userName" id="userName">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">密码<em>*</em></span>
                            <span class="c-content">
                                <input type="password" name="pwd" id="pwd">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">确认密码<em>*</em></span>
                            <span class="c-content">
                                <input type="password" name="pwdNext" id="pwdNext">
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
            <input type="hidden" name="teaOrAdu" value="adu"/>
        	</c:if>
        	
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">基本信息</h2>
                </div>
                
                <ul class="list-infos clear">
                
                    <li class="long">
                        <p class='c'>
                            <span class="c-title">教务人员类型 <em>*</em></span>
                            <span class="c-content">
				               	<c:if test="${empty teacher}">
				               	<a href="javascript:;" class="btn btn-mini btn-default btn-success tType manager" typeStatus="PERSON_MANAGER">班主任</a>
				                <a href="javascript:;" class="btn btn-mini btn-default tType waiter" typeStatus="PERSON_WAITER">跟班生</a>
				                <a href="javascript:;" class="btn btn-mini btn-default tType assistant" typeStatus="PERSON_ASSISTANT">助教</a>
				               	</c:if>
				               	<c:if test="${!empty teacher}">
				               	<a href="javascript:;" class="${teacher.teacherType == 'PERSON_MANAGER' ? 'btn btn-mini btn-success tType' : 'btn btn-mini btn-default tType'}" typeStatus="PERSON_MANAGER">班主任</a>
				                <a href="javascript:;" class="${teacher.teacherType == 'PERSON_WAITER' ? 'btn btn-mini btn-success tType' : 'btn btn-mini btn-default tType'}" typeStatus="PERSON_WAITER">跟班生</a>
				                <a href="javascript:;" class="${teacher.teacherType == 'PERSON_ASSISTANT' ? 'btn btn-mini btn-success tType' : 'btn btn-mini btn-default tType'}" typeStatus="PERSON_ASSISTANT">助教</a>
				               	</c:if>
                                <input type="hidden" name="teacherType" value="${type}" id="teaType"/>
                                <input type="hidden" value="${type}" id="agoTeaType"/>
                            </span>
                        </p>
                    </li>
                    <li>
	                    <c:if test="${!empty teacher}">
	                    <input type="hidden" id="teacherId" name="id" value="${teacher.id}"/>
	                    </c:if>
                    	
                        <p class='c'>
                            <span class="c-title">姓名<em>*</em></span>
                            <span class="c-content"><input type="text" name="name" id="tName" value="${teacher.name}"></span>
                            <input type="hidden" id="agoTName" value="${teacher.name}"/>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">性别</span>
                            <span class="c-content">
                            <c:if test="${empty teacher}">
                            <input type="radio" name="sex" value="MALE" checked> 男
                            <input type="radio" name="sex" value="FEMALE"> 女
                            </c:if>
                            <c:if test="${!empty teacher}">
                            <input type="radio" name="sex" value="MALE" ${teacher.sex == 'MALE' ? 'checked' : '' }> 男
                            <input type="radio" name="sex" value="FEMALE" ${teacher.sex == 'FEMALE' ? 'checked' : '' }> 女
                            </c:if> 
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">出生日期</span>
                            <span class="c-content">
                            
                                <input type="text" readonly="readonly" name="birthday" value="<fmt:formatDate value="${teacher.birthday}"/>" id="datetimepicker">
                            </span>
                        </p>
                    </li>                    
                    <li>
                        <p class='c'>
                            <span class="c-title">家庭住址</span>
                            <span class="c-content">
                                <input type="text" name="homeAddress" value="${teacher.homeAddress}">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">现居住址</span>
                            <span class="c-content">
                                <input type="text" name="address" value="${teacher.address}">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">最高学历</span>
                            <span class="c-content">
                                <select name="educationCode">
                                   	<option value="UNDER_JUNIOR" ${teacher.educationCode == 'UNDER_JUNIOR' ? 'selected' : ''}>大专以下</option>
                                   	<option value="JUNIOR" ${teacher.educationCode == 'JUNIOR' ? 'selected' : ''}>大专</option>
                                   	<option value="BECHELOR" ${teacher.educationCode == 'BECHELOR' ? 'selected' : ''}>本科</option>
                                    <option value="POSTGRADUATE" ${teacher.educationCode == 'POSTGRADUATE' ? 'selected' : ''}>研究生</option>
                                    <option value="DOCTOR" ${teacher.educationCode == 'DOCTOR' ? 'selected' : ''}>博士生及以上</option>
                                </select>
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">身份证号码</span>
                            <span class="c-content">
                                <input type="text" name="idNumber" value="${teacher.idNumber}">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">个人简介</span>
                            <span class="c-content">
                                <textarea rows="3" cols="30" name="resume">${teacher.resume}</textarea>
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">联系信息</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">手机号<em>*</em></span>
                            <span class="c-content">
                                <input type="text" name="mobile" id="mobile" value="${teacher.mobile}">
                                <input type="hidden" id="agoTMobile" value="${teacher.mobile}"/>
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">家庭电话</span>
                            <span class="c-content">
                                <input type="text" name="homePhone" value="${teacher.homePhone}">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">办公电话</span>
                            <span class="c-content">
                                <input type="text" name="workPhone" value="${teacher.workPhone}">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急联系人</span>
                            <span class="c-content">
                                <input type="text" name="emergencyContactName" value="${teacher.emergencyContactName}">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急人电话</span>
                            <span class="c-content">
                                <input type="text" name="emergencyContactPhone" value="${teacher.emergencyContactPhone}">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">邮箱</span>
                            <span class="c-content">
                                <input type="text" name="email" value="${teacher.email}">
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
            <div class="m-bo text-center">
                <a href="<%=rootPath%>/sysConfigTeacher/toEducationInfo" class="btn btn-sm btn-default">取消</a>
                <a href="javascript:void(0)" class="btn btn-sm btn-primary">保存</a>
            </div>
        </div>
        </form>
        <input type="hidden" value="${teaStatus}" id="teaStatus"/>
        <!-- ajax加载中div开始 -->
		<div class="loading lp-units-loading" style="display:none">
		       <p><i></i>加载中,请稍后...</p>
		</div>
	    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
		<!--  ajax加载中div结束 -->
    </div>
</div>
<script>
$(function(){
	$selectSubMenu('resource_manager');
});
</script>
<!-- footer start -->
<!-- footer end -->
</body>
</html>