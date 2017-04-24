<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html lang="zh-cn">
<head>
    <title>考期</title>
     <%@include file="/decorators/import.jsp"%>
    <link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>/stylesheets/resource.css" rel="stylesheet" type="text/css"/>
    <script src="<%=rootPath%>/javascripts/resource/term/examDate.js" ></script>
    <%-- <script src="<%=rootPath%>/javascripts/resource.js" ></script> --%>
</head>
<body>
<!-- header start -->
<!-- header end -->
<!-- 二级导航 -->
<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<div class="u-wrap resource">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">考期</h2>
            <span class="line"></span>
        </div>
        <input type="hidden" id="schoolName" value="${schoolName}"/>
		<input type="hidden" id="schoolId" value="${schoolId}"/>
        <div class="r-list">
            <ul class="clear">
            <c:forEach items="${companyItems}" var="item">
            	<li>
                    <p class="r-title"><i class="iconfont">&#xe61b;</i>${item.itemName}
                        <span class="new-btn">
                            <a href="javascript:;" class="btn btn-mini btn-primary btn-add-subs"><em class="iconfont">&#xe606;</em>新增考期</a>
                        </span>
                    </p>
                    <input type="hidden" value="${item.id}" class="itemOneId"/>
                	<p class="r-subs">
                	<c:forEach items="${sctMap}" var="sctMap">
                	
	                	<c:if test="${sctMap.key eq item.itemName}">
	                		<c:forEach items="${sctMap.value}" var="sctV">
		                        <span class="r-subs-title">
		                            <em>${sctV.termName}</em>
		                            <%-- <a href="javascript:void(0)" termId="${sctV.id}" class="btn btn-mini btn-link btn-switch btnDel">删除</a> --%>
		                        </span>
		                    </c:forEach>
		                    
                        </c:if>
                    </c:forEach>
                   			<span class="add-subs edit">
                            <select class="termYear">
                            	
                            </select>年
                            <select class="termMonth">
                           		
                            </select>月
                            <input type="button" class="btn btn-sm btn-default saveTerm" value="保存">
                            <input type="button" class="btn btn-sm btn-default btnCancle" value="取消">
                        </span>
                    </p>
                
                </li>
	                	
            </c:forEach>
           <%--  <c:forEach items="${sctMap}" var="sctMap">
            	<c:forEach items="${sctMap.key}" var="sctK">
            	<li>
                    <p class="r-title"><i class="iconfont">&#xe61b;</i>${sctK}
                        <span class="new-btn">
                            <a href="javascript:;" class="btn btn-mini btn-default btn-add-subs">新增考期</a>
                        </span>
                    </p>
                   
                	<p class="r-subs">
                	<c:forEach items="${sctMap.value}" var="sctV">
                        <span class="r-subs-title">
                            <em>${sctV.termName}</em>
                            <a href="<%=rootPath%>/sysConfigTerm/deleteById?termId=${sctV.id}" class="btn btn-mini btn-link btn-switch">删除</a>
                        </span>
                        <input type="hidden" value="${sctV.schoolId}" class="schoolId"/>
                        <input type="hidden" value="${sctV.itemOneId}" class="itemOneId"/>
                        <input type="hidden" value="${sctV.schoolName}" class="schoolName"/>
                    </c:forEach>
                        <span class="add-subs edit">
                            <select class="termYear">
                            	
                            </select>年
                            <select class="termMonth">
                           		
                            </select>月
                            <input type="button" class="btn btn-sm btn-default saveTerm" value="保存">
                            <input type="button" class="btn btn-sm btn-default btnCancle" value="取消">
                        </span>
                    </p>
                
                </li>
                
            	</c:forEach>
            </c:forEach> --%>
            </ul>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<!-- footer start -->
<script>
	$(function(){
		$selectSubMenu('resource_term');
	});
</script>
<!-- footer end -->
</body>
</html>