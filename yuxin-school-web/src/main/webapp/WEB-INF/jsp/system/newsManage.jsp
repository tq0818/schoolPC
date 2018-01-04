<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>新闻资讯</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    
    <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('netschool_news');
    	});
    </script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<form method="post" id="myForm">
	<input type="hidden" name="type" id="type"/>
	<input type="hidden" name="id" id="newsId"/>
	<input type="hidden" name="schoolId" id="sId"/>
</form>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding" style="padding: 15px;">
        <div class="heading">
            <h2 class="h5">新闻资讯</h2>
            <span class="line"></span>
            <span class="rb">
                <a href="javascript:;" onclick="editNews('1')" class="btn btn-mini btn-primary"><em class="iconfont">&#xe606;</em>添加新闻</a>
            	&nbsp;&nbsp;<a href="<%=rootPath %>/sysNewsType/newsTypePage" class="btn btn-mini btn-primary"><em class="iconfont">&#xe600;</em>&nbsp;&nbsp;设置新闻类型</a>
            </span>
        </div>
        <p id="schoolListP">
        	<c:forEach items="${schoolList }" var="school" varStatus="status">
        		<c:if test="${school.id==schoolId }">
        			<a href="javascript:void(0);" class="btn btn-sm btn-default btn-success" mark="${school.id }">${school.schoolName }</a>
        		</c:if>
        		<c:if test="${school.id!=schoolId }">
        			<a href="javascript:void(0);" class="btn btn-sm btn-default" mark="${school.id }">${school.schoolName }</a>
        		</c:if>
            </c:forEach>
            <c:if test="${empty schoolList }">
            	 <a href="javascript:void(0);" class="btn btn-sm btn-default btn-success" mark="${school1.id }">${school1.schoolName }</a>
            </c:if>
        </p>
        <p class="select-type">
        	<span class="c-title">类型</span>
            <span class="c-content" id="typeList">
            		
            	
            </span>
        </p>
        <p class="select-type">
       
          <%--     <span class="c-title">类型</span>
            <span class="c-content" id="typeList">
                <a href="javascript:queryPageByKeys(1,'','');" marks="SYS_NEWS_TYPE_1" class="btn btn-mini btn-default">新闻资讯</a>
                <a href="javascript:queryPageByKeys(1,'','');" marks="SYS_NEWS_TYPE_2" class="btn btn-mini btn-default">考试通知</a>
            </span>
           --%>
            <span class="c-title">状态</span>
            <span class="c-content" id="chooseStatus">
                <a href="javascript:queryPageByKeys(1,'','','');" marks="1" class="btn btn-mini btn-default">已发布</a>
                <a href="javascript:queryPageByKeys(1,'','','');" marks="0" class="btn btn-mini btn-default">未发布</a>
            </span>
            <span class="c-title">发布时间</span>
            <span class="c-content" id="publicTime">
                <a href="javascript:queryPageByKeys(1,'','','','');" class="btn btn-mini btn-default">全部</a>
                <a href="javascript:queryPageByKeys(1,'','','',7);" class="btn btn-mini btn-default">7天</a>
                <a href="javascript:queryPageByKeys(1,'','','',30);" class="btn btn-mini btn-default">当月</a>
                <a href="javascript:queryPageByKeys(1,'','','',90);" class="btn btn-mini btn-default">三个月</a>
                <a href="javascript:queryPageByKeys(1,'','','',180);" class="btn btn-mini btn-default">半年</a>
            </span>
            <span class="c-content">
                <input type="text" id="newTitle" value="" palceholder="输入标题">
                <input type="button" onclick="searh(1)" class="btn btn-sm" value="搜索">
            </span>
        </p>
        <div id="newsManagerList">
        
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript" src="<%=rootPath %>/javascripts/system/newsManage.js"></script>
</body>
</html>