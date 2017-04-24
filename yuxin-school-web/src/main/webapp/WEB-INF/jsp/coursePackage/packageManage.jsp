<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程管理</title> 
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/class-manage.css">
    <style>
        #sortable { list-style-type: none; margin: 0 0 0 10px; padding: 0; width: 97%; }
        /*#sortable li { margin: 0 5px 5px 5px; padding: 5px; font-size: 1.2em; height: 1.5em; }*/
        /*html>body #sortable li { height: 1.5em; line-height: 1.2em; }*/
        .ui-state-highlight {
            border-style:dashed;
            background: transparent;
        }
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
        <div class="u-wrap classes">
            <div class="mainbackground nopadding">
                <div class="heading">
                    <h2 class="h5 class-manage-head">课程管理</h2>
                    <i class="iconfont ask" style="float:right;cursor: pointer;color:gray;line-height: 24px;margin-left: 5px;" title="没有添加阶段时课程包下可以直接添加课程；添加阶段后课程只可以在阶段中添加。">&#xe60f;</i>
                    <span class="head-btn choice-class-btn">添加课程</span>
                    <span class="head-btn add-grade-btn">添加阶段</span>
                    <em>原价<span id="totalCoursePrice">0.00</span>元</em>
                    <em>共<span id="totalCourseNum">0</span>个课程</em>
                    <span class="line"></span>
                </div>
                <div style="padding-bottom: 30px;">
                    <div id="sortable">
                        
                       
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
            <span id="addMarktitle">添加阶段</span>
            <em class="iconfont pop-close-btn">&#xe610;</em>
        </div>
        <form id="stageForm" method="post">
        <input type="hidden" name="id" value="" id="stage_id"/>
        <input type="hidden" name="sort" id="stage_sort" value=""/>
        <input type="hidden" name="classPackageId" value="${classPackage.id }" id="classPackageId"/>
        <input type="hidden" name="protocolId" value="${classPackage.protocolId }" id="protocolId"/>
        <input type="hidden" name="publishStatus" value="NOT_ALLOW_SALE" id="publishStatus"/>
        <input type="hidden" name="updateFlag" id="updateFlag" value=""/>
        <div class="select-content">
            <div style="margin-top: 20px;">
                <div class="add-grade-content">
                    <p>
                        <span>名称</span>
                        <input type="text" name="title" id="stage_title" placeholder="最多输入16个汉字" style="width: 400px"/>
                    </p>
                    <p>
                        <span>阶段售卖</span>
                        <span class="tit-font" style="margin:0 0 0 -20px;">
                            <em class="iconfont normal close">&#xe604;</em>
                            <i id="addStatus30" class="i close">不允许</i>
                        </span>
                    </p>
                    <p class="setstagePrice" style="display: none;">
                        <span>设置阶段价格</span>
<!--                         <em style="margin-right: 10px;"></em> -->
                        <input class="prices" type="text" id="stage_price" name="realPrice" style="width: 80px"/>
                    </p>
                    <p>
                        <span>阶段描述</span>
                        <textarea name="description" maxlength="120" id="stage_description" cols="30" rows="10" style="width: 400px;height: 100px;resize: none" placeholder="此处输入描述内容"></textarea>
                    </p>
                    <div>
                        <span class="yes-btn manageStage">确认</span>
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
            <span class="yes-btn add_stage_course">确认</span>
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
            <span class="yes-btn del_course_yes">确认</span>
            <span class="cancel-btn">取消</span>
        </div>
    </div>
</div>
<div style="display: none;" id="chooseCourseIds">

</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript" src="<%=rootPath%>/javascripts/class/cousePackage/packageManage.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/class-manage.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
</body>
</html>