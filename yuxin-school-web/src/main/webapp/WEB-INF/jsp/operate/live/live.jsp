<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
<title>上直播</title>
    <%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma","no-cache");
	response.setDateHeader("Expires",0);
	%>
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css">

    <script type="text/javascript" src="<%=rootPath %>/javascripts/operate.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/operate/live/live.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/crypto.js"></script>

	<style type="text/css">
		.add-classes{display:none;position:fixed;top:50%;left:50%;width:760px;height:535px;margin-left:-400px;margin-top:-280px;padding:10px 20px;background-color:#fafafa;z-index:99}
		.add-classes .close{position:absolute;top:5px;right:0;font-size:16px;font-size:1.6rem;cursor:pointer}
		.add-classes-bg{display:none;position:fixed;top:0;right:0;bottom:0;left:0;background-color:rgba(0,0,0,0.4);z-index:98}	
		.course-table{
			width: 100%;
			margin-top: 25px;
		}
		.course-table li{
			padding: 8px 0;
		    float: left;
		    text-align: center;
		    font-size: 14px;
		    width: 378px;
		    background: #eee;
		    border-right: solid 1px #dfe1e6;
		    cursor: pointer;
		}
		.course-table li:hover{
			background: #00abed;
    		color: #fff;
		}
		.course-table li.courseactive{
			background: #00abed;
    		color: #fff;
		}
		.del-nuanchang{
			border-right: none;
		}
	</style>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_teach.jsp"%>
<div class="u-wrap operate">
	<div class="operate_live_heading" style="display:none">
	</div>
	<div class="mainbackground" style="min-height: 520px;">
        <div class="heading">
            <h2 class="h5">上直播</h2>
			<div class="search" style="top:-2px;">
                 	<input type="text" id="findNoLessonName"
                    		placeholder="按班号或课次搜索" onkeyup="javascript:listener();"/>
                    <input type="button" value="搜索" class="btn btn-default btn-sm" onclick="javascript:search();"/>
			</div>
            <span class="line"></span>
        </div>
        <div class="select-option clear">
            <ul>
                <li>
                    <span class="s-title">日期</span>
                    <span class="s-list">
                        <a class="btn btn-sm btn-default btn-day" href="javascript:;" data-time="0">全部</a>
                        <a class="btn btn-sm btn-default btn-day active" href="javascript:;" data-time="1">今天</a>
                        <a class="btn btn-sm btn-default btn-day" href="javascript:;" data-time="2">明天</a>
                        <a class="btn btn-sm btn-default btn-day" href="javascript:;" data-time="3">昨天</a>
                    </span>
                </li>
                <li>
                    <span class="s-title">状态</span>
                    <span class="s-list">
                        <a class="btn btn-sm btn-default btn-ing active" href="javascript:;" data-time="0">全部</a>
                        <a class="btn btn-sm btn-default btn-ing" href="javascript:;" data-time="4">进行中</a>
                        <a class="btn btn-sm btn-default btn-ing" href="javascript:;" data-time="5">未开始</a>
                        <a class="btn btn-sm btn-default btn-ing" href="javascript:;" data-time="6">已结束</a>
                    </span>
                </li>
            </ul>
        </div>
        <div class="operate_list" style="margin-top:20px">
		 </div>
		<div class="pages">
			<ul class="pagination">
				
			</ul>
		</div>
    </div>
</div>

<div class="add-classes">
	 <div class="main-content nospace">
		<div class="marketing" style="text-align: center;">
			<p class="c">
				<span class="t-title">搜索标题</span> 
				<span class="t-content">
					<input type="text" id="findClassLessonName" style="border:#5980FF 1px solid;"/>
				</span>
				<span class="btn-section">
	        		<a href="javascript:;" class="btn btn-primary  btn-seach">查询</a>
	        	</span>
			</p>
    		<ul class="course-table">
    			<li class="courseactive" data-types="1">添加插播件</li>
    			<li data-types="2">管理插播件</li>
    			<%-- 
    			<li class="del-nuanchang" data-types="3">管理暖场件</li>
    			--%>
    		</ul>
		</div>
    <div class="place-list" style="margin-top:61px;padding:0 0 10px 0;">
        
    </div>
    <div class="pages">
        <ul class="paginationss">
	
		</ul>
    </div>
    <div class="btns text-center" style="position: absolute;left: 40%;top: 92%">
        <p class="text-center">
            <a href="javascript:;" class="btn btn-primary btn-ok" disabled="disabled">确定</a>
            <a href="javascript:;" class="btn btn-primary btn-del" disabled="disabled" style="display:none;">删除</a>
            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
        </p>
        <input type="hidden" id="lessonids"/>
        <input type="hidden" id="recordids"/>
    </div>
	</div>
</div>
<div class="add-classes-bg"></div>
<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
</body>
</html>