<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
    <title>外校直播课程管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/class/otherSchool/classTypeManage.js"></script>
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
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap admin overflow baseSchoolBg"  style="min-height: 580px;">
    <div class="heading baseSchoolHead">
        <h2 class="h5">课程列表</h2>
        <a href="##" class="btn btn-primary batches">批量下架</a>
        <a href="##"  class="btn btn-primary batchesShelves">批量上架</a>
        <span class="line"></span>
		<input type="hidden" id="rowCount"/>
		<input type="hidden" id="pageNo" value="1"/>
		<input type="hidden" id="pageSize" value="10"/>
    </div>
    <div style="margin-top: 10px;">
        <ul class="baseSchoolLiveLabel">
            <li><input type="text" id="name" name="name" placeholder="请输入课程名称"></li>
            <li>
                <select  name="firstItemCode" id="firstItemCode" class="firstItemCode">
                    <option value="" data-code="">分类</option>
                    <c:forEach items="${oneItems}" var="item">
                    <option value="${item.id}" data-code="${item.itemCode}">${item.itemName}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                <select name="secondItemCode" id="secondItemCode" class="secondItemCode">
                    <option value="" data-code="">学段</option>
                </select>
            </li>
            <li>
                <select name="thirdItemCode" id="thirdItemCode" class="thirdItemCode">
                    <option value="" data-code="">学科</option>
                </select>
            </li>
            <li>
                <select name="status" id="status" class="status">
                    <option value="">状态</option>
                    <option value="CLASS_UNPUBLISHED">未上架</option>
                    <option value="CLASS_ON_SALE">招生中</option>
                    <option value="CLASS_STOP_SALE">已下架</option>
                </select>
            </li>
        </ul>
        <span class="chooseData">添加时间 :</span>
        <span>
                <input type="text" name="startTime" class="date-picker from" id="startTime"/>
                <em>到</em>
                <input type="text" name="endTime" class="date-picker to" id="endTime"/>
        </span>
        <span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
    </div>
    <div class="user-list">
        <table class="table table-center" id="tableList">
            <tr data-buy="true">
                <th width="5%"><input type="checkbox" class="checkboxAll"></th>
                <th width="10%">课程名称</th>
                <th width="10%">所属学校</th>
                <th width="5%">分类</th>
                <th width="10%">学段</th>
                <th width="10%">学科</th>
                <th width="10%">状态</th>
                <th width="15%">添加时间</th>
                <th width="15%">操作</th>
            </tr>
            <tbody class="tbodyList">
            
            </tbody>
        </table>
        <div class="pages pagination">

        </div>
    </div>

</div>
<input type="hidden" value="5" id="pageSize">

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->

<%--调用日期插件--%>
<script type="text/javascript">
    $("#startTime").datetimepicker({format: 'yyyy-mm-dd hh:ii', language: 'zh-CN'});
    $("#endTime").datetimepicker({format: 'yyyy-mm-dd hh:ii', language: 'zh-CN'});
</script>
<script>
</script>

<script>
    //    点击更多显示菜单
    $('table').on('mouseenter','.more',function(){
        $(this).siblings('ul').show();
    });
    $('table').on('mouseleave','.more',function(){
        $(this).siblings('ul').hide();
    });
    $('table').on('mouseenter','.box',function(){
        $(this).show();
    });
    $('table').on('mouseleave','.box',function(){
        $(this).hide();
    });
    console.log(rootPath);
</script>
<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('course_class_type');
    });
</script>
</body>
</html>