<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>评论</title>
   	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/teacher.css">
    <style type="text/css">
        .iconfont{
            font-size: 13px;
            cursor: auto;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_operate.jsp"%>
<script type="text/javascript">
	$selectSubMenu('teacher_person_comment');
</script>
	 <div class="Y_wrap">
        <div class="Y_background Y_backcomment">
            <div class="Y_head">
                <h2 class="h5">评论</h2>
                <span class="line"></span>
            </div>
            <ul class="comment_all">
            </ul>
            
            <!-- 分页插件开始 -->
            <div class="pages pagination"></div>
            <div class="mark mark1">
                <div class="alertbox">
                    <p style="text-align: center"><i class="iconfont">&#xe653;</i>您是否确定删除该条动态及评论</p>
                    <div class="button-box Y_clear">
                        <button class="yes">是</button>
                        <button class="no">否</button>
                    </div>
                </div>
            </div>
            <div class="mark mark2">
                <div class="alertbox">
                    <p style="text-align: center"><i class="iconfont">&#xe653;</i>您是否确定删除该条评论</p>
                    <input type="hidden" class="deleteId" id=""/>
                    <div class="button-box Y_clear">
                        <button class="yes">是</button>
                        <button class="no">否</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

     
	    <!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
		<p><i></i>加载中,请稍后...</p>
	</div>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/operate/comment/comment.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
    
</body>
</html>