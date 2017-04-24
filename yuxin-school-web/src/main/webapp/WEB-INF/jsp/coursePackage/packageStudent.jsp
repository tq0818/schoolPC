<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程包-学员管理</title> 
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/class-manage.css">
    <style>
        #sortable { list-style-type: none; margin: 0 0 0 10px; padding: 0; width: 97%; }
        .ui-state-highlight { height: 1.5em; line-height: 1.2em; }
        .sortable-sec{
            margin-left: 25px;
        }
    </style>
    <script type="text/javascript">
    	$(document).ready(function(){
    		$selectMenu("course_package");
			$chooseMenu("manageCode");
    	});
    </script>
     
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/coursePackage/commonTitle.jsp" %>
<div class="u-wrap company overflow">
	<%@include file="/WEB-INF/jsp/coursePackage/commonClass.jsp" %>
    <div class="right-side">
    <input type="hidden" value="${classPackage.id }" id="classPackageId"/>
        <div class="u-wrap classes">
            <div class="mainbackground nopadding">
                <div class="heading">
                    <h2 class="h5 class-manage-head">课程管理</h2>
                    <span class="head-btn choice-class-btn">添加课程</span>
                    <span class="head-btn add-grade-btn">添加阶段</span>
                    <em>原价<span id="totalCoursePrice">0.00</span>元</em>
                    <em>共<span id="totalCourseNum">0</span>个课程</em>
                    <span class="line"></span>
                </div>
                <div style="padding-bottom: 30px;">
                    <div id="sortable">
                        <div class="ui-state-parent">
                            <div class="ui-state-default">
                                <span class="iconfont">&#xe630;</span>
                                <span style="width: 75px">第一阶段</span>
                                <span style="width: 150px">SpringMVC详解</span>
                                <span style="width: 50px;font-size: 12px;color: #FE5151;">999.9元</span>
                                <em class="iconfont open-btn">&#xe681;</em>
                                <em class="iconfont">&#xe626;</em>
                                <em class="iconfont">&#xe628;</em>
                                <em class="iconfont">&#xe61c;</em>
                            </div>
                            <div class="open-box">
                                <div class=" sec-title">Android作为目前最热门的技术之一，工作前景一片大好！不是不想学，只是不知从何学起？没关系！由小慕来带领大家学习从最基本的Java语言基础，到各种Android应用案例，为你的求职做足准备，助你成为一名合格的Android开发工程师！</div>
                                <div class="sortable-sec droptrue">
                                    <div class="ui-state-default course-manage">
                                        <img src="../images/6d818.jpg" alt="" width="19%" height="100px"/>
                                        <div class="course-manage-cont">
                                            <div class="iconfont">&#xe630;</div>
                                            <div class="course-manage-title" style="line-height: 30px">
                                                <span>人力资源三级保过班</span>
                                                <em>1680.00元</em>
                                                <i>已上架</i>
                                            </div>
                                            <div class="course-manage-bottom">
                                                <span class="play-status blue-bg">直播</span>
                                                <span class="remove-btn">移除课程</span>
                                            <span class="number-box">
                                                <i>999学员</i> | <i>999课时</i> | <i>有效期30天</i>
                                            </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="ui-state-default course-manage">
                                        <img src="../images/a044a.jpg" alt="" width="19%" height="100px"/>
                                        <div class="course-manage-cont">
                                            <div class="iconfont">&#xe630;</div>
                                            <div class="course-manage-title" style="line-height: 30px">
                                                <span>人力资源三级保过班</span>
                                                <em>1680.00元</em>
                                                <i>已上架</i>
                                            </div>
                                            <div class="course-manage-bottom">
                                                <span class="play-status yellow-bg">录播</span>
                                                <span class="remove-btn">移除课程</span>
                                            <span class="number-box">
                                                <i>999学员</i> | <i>999课时</i> | <i>有效期30天</i>
                                            </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="ui-state-parent">
                            <div class="ui-state-default">
                                <span class="iconfont">&#xe630;</span>
                                <span style="width: 75px">第一阶段</span>
                                <span style="width: 150px">CoreJava语法</span>
                                <span style="width: 50px;font-size: 12px;color: #FE5151;">999.9元</span>
                                <em class="iconfont open-btn">&#xe681;</em>
                                <em class="iconfont">&#xe626;</em>
                                <em class="iconfont">&#xe628;</em>
                                <em class="iconfont">&#xe61c;</em>
                            </div>
                            <div class="open-box">
                                <div class=" sec-title">Android作为目前最热门的技术之一，工作前景一片大好！不是不想学，只是不知从何学起？没关系！由小慕来带领大家学习从最基本的Java语言基础，到各种Android应用案例，为你的求职做足准备，助你成为一名合格的Android开发工程师！</div>
                                <div class="sortable-sec droptrue">
                                    <div class="ui-state-default course-manage">
                                        <img src="../images/a044a.jpg" alt="" width="19%" height="100px"/>
                                        <div class="course-manage-cont">
                                            <div class="iconfont">&#xe630;</div>
                                            <div class="course-manage-title" style="line-height: 30px">
                                                <span>人力资源三级保过班</span>
                                                <em>1680.00元</em>
                                                <i>已上架</i>
                                            </div>
                                            <div class="course-manage-bottom">
                                                <span class="play-status red-bg">混合</span>
                                                <span class="remove-btn">移除课程</span>
                                            <span class="number-box">
                                                <i>999学员</i> | <i>999课时</i> | <i>有效期30天</i>
                                            </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 添加阶段弹框 -->
<div class="pop-fixed add-grade-pop">
    <div class="select-board cum-detail">
        <div class="board-title">
            <span>添加阶段</span>
            <em class="iconfont pop-close-btn">&#xe610;</em>
        </div>
        <form id="stageForm" method="post">
        <div class="select-content">
            <div style="margin-top: 20px;">
                <div class="add-grade-content">
                    <p>
                        <span>名称</span>
                        <input type="text" placeholder="最多输入16个汉字" style="width: 400px"/>
                    </p>
                    <p>
                        <span>阶段售卖</span>
                        <span class="tit-font">
                            <em class="iconfont normal open"></em>
                            <i id="addStatus30" class="i open">已启用</i>
                        </span>
                    </p>
                    <p class="setstagePrice">
                        <span></span>
                        <em style="margin-right: 10px;">设置阶段价格</em>
                        <input type="text" style="width: 80px"/>
                    </p>
                    <p>
                        <span>描述</span>
                        <textarea name="" id="" cols="30" rows="10" style="width: 400px;height: 100px;resize: none" placeholder="此处输入对此课程分类的描述，会在网站课程包中目录中展示给学员"></textarea>
                    </p>
                    <div>
                        <span class="yes-btn">确认</span>
                        <span class="cancel-btn">取消</span>
                    </div>
                </div>
            </div>
        </div>
        </form>
        <div class="page-btn-box"></div>
    </div>
</div>

<!-- 添加课程弹框 -->
<div class="pop-fixed choice-class-pop">
    <div class="select-board cum-detail">
        <div class="board-title">
            <span>选择课程</span>
            <em class="iconfont pop-close-btn">&#xe610;</em>
        </div>
        <div class="select-content">
            <div>
                <div class="selects">
                    <select name="xk" id="itemOne" onchange="Page.queryItemSecond();" style="width: 90px;">
                        
                    </select>
                    <select name="km" id="itemTwo">
                       
                    </select>
                    <input type="text" placeholder="课程名称/老师" id="searchName" class="input-name" style="width: 100px"/>
                    <span id="searchCourse">查询</span>
                </div>
                <div id="classLists">
                	
                </div>
            </div>
        </div>
        <div class="page-btn-box" style="width: 100%;">
        	<div class="pages pagination">
                
			</div>
        </div>
        <div class="choice-class-box bottomButtom">
            <span class="yes-btn">确认</span>
            <span class="cancel-btn">取消</span>
        </div>
    </div>
</div>

<!-- 删除弹框 -->
<div class="pop-fixed delete-sure">
    <div class="select-board cum-detail">
        <div class="board-title">
            <span>删除确认</span>
            <em class="iconfont pop-close-btn">&#xe610;</em>
        </div>
        <div class="select-content">
            <div>
                <div class="no-content-prompt">删除后无法恢复，您确认要删除课程数据？</div>
            </div>
        </div>
        <div class="choice-class-box">
            <span class="yes-btn">确认</span>
            <span class="cancel-btn">取消</span>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=rootPath%>/javascripts/class/cousePackage/packageManage.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/classedit.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/class-manage.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</body>
</html>