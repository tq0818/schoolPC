<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理评论</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/teacher.css">
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/operate/comment/manageComment.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
    <style type="text/css">
        .iconfont{
            font-size: 13px;
            cursor: auto;
        }

    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<script type="text/javascript">
	$selectSubMenu('teacher_person_comment');
</script>
	<div class="Y_wrap">
        <div class="Y_background org_back">
            <div class="Y_head Y_clear">
                <h2 class="h5">评论</h2>
                <div class="input-ground">
                    <span class="text">
                       <input type="text" placeholder="搜索老师" class="searchTeacher" />
                        <button type="button" class="btn btn-default btn-sm search-icon" >搜索</button>
                    </span>

                </div>
                <span class="line"></span>
            </div>
            <div class="list">
           
                <div class="teacher_list Y_clear Y_mt10">
                    <div class="left">
                        老师：
                    </div>
                    <div class="middle">
                        <ul class="teahcerName">
                        </ul>
                    </div>
                    <div class="right hideRight" style="display: none">
                        <span  hide-div="hideRight">收起<i class="iconfont">&#xe655;</i></span>
                    </div>
                    <div class="right showRight">
                        <span hide-div="showRight">更多<i class="iconfont">&#xe656;</i></span>
                    </div>
                </div>
            </div>
            <div class="border"></div>

            <input type="hidden" id="" class="clickTeacherId">
            <ul class="comment_all">
            <!--  
                <li class="Y_clear">
                    <div class="headpic">
                        <a href=""><img src="../../../temp/2.png" alt="" width="50" height="50"/></a>
                    </div>
                    <div class="Y_backcomment_content">
                        <div class="word Y_clear">
                            <span><a href="">Daisy：</a></span>
                            <span class="wordcontent">十年树木，百年树人”，完美年树人”，完年树人”，完的诠释了教育的本质价值。教育事业的兴旺，也正是国家兴旺的本源。国内领先的在线教育技术服务商268教育，跟随着时代的进程，很早便跻身于网...</span>
                        </div>
                        <p class="Y_time Y_mt10">
                            <span>2015-09-37</span>
                            <span>15：00</span>
                            <span><em>源自：</em>会计基础ps巴拉巴拉</span>
                            <span><a href=""><em>章节：</em>会计基础ps巴拉巴拉第一节</a></span>
                        </p>
                    </div>
                    <button class="delete delete2 active">
                        删除
                    </button>
                </li>
               --> 
            </ul>
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

</body>
</html>