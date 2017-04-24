<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>动态</title>
   <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
   <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/teacher.css">
   <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/lightbox.css">
    <style type="text/css">
        .iconfont{
            cursor: auto;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_operate.jsp"%>
<script type="text/javascript">
	$selectSubMenu('teacher_person_status');
</script>
<div class="Y_wrap">
	 <div class="Y_background org_back">
            <div class="Y_head Y_clear">
                <h2 class="h5">动态</h2>
                <div class="input-ground">
                    <span class="text">
                       <input type="text"  placeholder="搜索老师" class="searchTeacher"/>
                        <button type="button" class="btn btn-default btn-sm search-icon" >搜索</button>
                    </span>

                </div>
                <span class="line"></span>
            </div>
            <div class="list">
            <!--  
                <div class="subject_list Y_clear">
                    <div class="left">
                        学科：
                    </div>
                    <div class="middle">
                        <ul class="Y_clear">
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                            <li>会计</li>
                        </ul>
                    </div>
                    <div class="right">
                        <span>更多<i class="iconfont">&#xe656;</i></span>
                    </div>
                </div>
               --> 
                <div class="teacher_list Y_clear Y_mt10">
                    <div class="left">
                        老师：
                    </div>
                    <div class="middle">
                        <ul class="teahcerName clear">
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
            <div class="title">
                <span class="click searchType" style="cursor: pointer" searchType="1">最新</span>
                <span class=" searchType" style="cursor: pointer" searchType="2">热门</span>
            </div>
           
            <div class="dynamicsList">
			</div>
            	 <!-- 分页插件开始 -->
            <div class="pages pagination"></div>
           <div class="mark mark1">
				<input type="hidden" class="deleteId" id=""/>
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
					<input type="hidden" class="deleteReplayId" id=""/>
					<p style="text-align: center"><i class="iconfont">&#xe653;</i>您是否确定删除该条评论</p>
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
<script type="text/javascript" src="<%=rootPath %>/javascripts/operate/dynamics/manageDynamics.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/operate/dynamics/lightbox.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
</body>
</html>