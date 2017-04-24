<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程模板配置</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/classSet/classSet.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_newsystem.jsp"></jsp:include>
    <div class="right-side wxRight-side">
        <div class="u-wrap set-system">
            <div class="mainbackground space">
                <input type="hidden" id="schoolId">
                <div class="title single">
                    <h2 class="h6 fl">课程模板配置</h2>
                </div>
                <div class="sysmodules">
                    <div class="themes-list" data-id="${cd.id}">
                        <ul>
                            <li class="module sysmodule" id="1">
                                <div class="picture">
                                	<div class="curr <c:if test="${cd.templateId != 1}">none</c:if>">当前使用</div>
                                    <img src="<%=rootPath %>/images/img-course2.png">
                                </div>
                                <div class="themes-title">
                                    <h2 class="h6">模板名称：经典</h2>
                                    <a href="<%=rootPath %>/sysBody/courseDetailExample/2" target="_blank">查看示例</a>
                                </div>
                                <div class="themes-content">
                                    <!--公开课模板1-->
                                </div>
                                <div class="themes-btns">
                                    <a class="left use" href="javascript:;" data-num="1">应用</a>
                                </div>
                            </li>
                            <li class="module sysmodule" id="2">
                                <div class="picture">
                                	<div class="curr <c:if test="${cd.templateId != 2}">none</c:if>">当前使用</div>
                                    <img src="<%=rootPath %>/images/img-course1.png">
                                </div>
                                <div class="themes-title">
                                    <h2 class="h6">模板名称：简约</h2>
                                    <a href="<%=rootPath %>/sysBody/courseDetailExample/1" target="_blank">查看示例</a>
                                </div>
                                <div class="themes-content">
                                    <!--公开课模板2-->
                                </div>
                                <div class="themes-btns">
                                    <a class="left use" href="javascript:;" data-num="2">应用</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	$(function() {
		$selectSubMenu('org_service');
		$selectSubMenus("course_detail");
		$('.themes-btns .use').on('click',function(){
			var dataInfo = $('.themes-list').data(),
				_this = this;
			dataInfo.templateId = $(this).data('num');
			$.ajax({
				 url:rootPath+"/classManage/courseDetailSetConfig",
				 type:"post",
				 data:dataInfo,
				 dataType:"json",
				 success:function(jsonData){
					 $('.curr').addClass('none');
					 $(_this).parents('.sysmodule').find('.curr').removeClass('none');
					$.msg('应用成功');
				 }
			 })
		});
	});
	
	
</script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>

</html>