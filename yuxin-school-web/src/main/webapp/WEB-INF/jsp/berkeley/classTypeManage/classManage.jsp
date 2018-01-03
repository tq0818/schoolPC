<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>课程管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/classes.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/branchschool/classManage.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/berkeley.js"></script>--%>
    <style type="text/css">
        .head-div {
            position: relative;
            margin-top: 15px;
            padding: 3px 8px;
        }
        .font-size {
            font-size: 14px;
            margin-left: 10px;
            margin-right: 11px;
        }
    </style>
    <%--tob--%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include>
<div class="u-wrap admin overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_berkeleyLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="heading">
            <h2 class="h5">分校直播课程管理</h2>
            <span class="line"></span>
        </div>
        <div style="margin-top: 10px;">
        	<input type="hidden" id="companyId" value="${companyId }">
        	<input type="hidden" id="schoolId" value="${school.id }">
			<input type="hidden" id="rowCount"/>
			<input type="hidden" id="pageNo"/>
			<input type="hidden" id="pageSize" value="5"/>
            <input type="text" id="name" name="name" placeholder="课程名称"/>
            <select name="livestatus" id="livestatus">
                <option value="">请选择直播课状态</option>
                <option value="nostart">未开始</option>
                <option value="start">进行中</option>
                <option value="over">已结束</option>
            </select>
         <!--    <select name="" id="">
                <option value="">请选择权限状态</option>
                <option value="">已开通</option>
                <option value="">已关闭</option>
            </select> -->
            <span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
            <button class="btn btn-primary addCourse">添加课程</button>
        </div>
        <div class="user-list">
            <table class="table table-center" id="tableList">
                <tr data-buy="true">
                    <th width="5%">序号</th>
                    <th width="10%">课程名称</th>
                    <%--<th width="10%">学科</th>--%>
                    <th width="10%">所属学校</th>
                    <th width="10%">直播课状态</th>
                   <!--  <th width="10%">权限</th> -->
                    <th width="10%">操作</th>
                </tr>
                <tbody class="tbodyList">
                	
                </tbody>
            </table>
            <div class="pages pagination">

            </div>
        </div>
    </div>
</div>

<%--弹窗begin--%>
<div class="popupOpacity"></div>

<%--课程表弹窗--%>
<div class="popupContainerCourse">
    <h5>课程表</h5>
    <div class="popupCourseContent">
            <ul class="sortable base-sort item-panel courseliList ui-sortable">
                
            </ul>
    </div>
    <button class="btn btn-primary closeCourse">关闭</button>
</div>

<%--添加课程弹窗--%>
<div class="popupAddCourse">
    <h5>添加课程</h5>
    <button class="btn btn-primary closePopupAddCourse">关闭</button>
    <div class="classManagementInput">
        <div>
            <label for="" class="classManagementInputFirst">课程名称:</label>
            <input type="text" placeholder="请输入课程名" id="searchClassName">
        </div>
        <div>
            <label for="">课程所属学校:</label>
            <input type="text" placeholder="请输入课程所属学校" id="searchSchoolName">
        </div>
        <button class="btn btn-primary searchClass">搜索</button>
    </div>
    <div class="user-list">
            <table class="table table-center">
                <tr data-buy="true">
                    <th width="4%">序号</th>
                    <th width="15%">课程名称</th>
                    <th width="10%">课程</th>
                    <th width="15%">所属学校</th>
                    <th width="6%">课程详情</th>
                    <th width="6%">操作</th>
                </tr>
               <tbody class="searchList"> 
               </tbody>
            </table>
            <div class="pages classpagination">

            </div>
        </div>
</div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->
<!-- <script type="text/javascript">
    $("#startTime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
    $("#endTime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
</script> -->
<script>
    // 左侧active切换
    $selectSubMenus('classManagerGetClassList');
</script>
</body>
</html>