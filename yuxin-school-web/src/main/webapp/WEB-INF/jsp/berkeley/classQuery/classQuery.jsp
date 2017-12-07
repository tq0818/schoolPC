<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>分校课程查询</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
   	<script type="text/javascript" src="<%=rootPath%>/javascripts/branchschool/classQuery.js"></script>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/system/order.js"></script>--%>
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
            <h2 class="h5">分校课程查询</h2>
            <span class="line"></span>
        </div>
        <div style="margin-top: 10px;">
        	<input type="hidden" id="companyId" value="${companyId }">
			<input type="hidden" id="rowCount"/>
			<input type="hidden" id="pageNo"/>
			<input type="hidden" id="pageSize" value="5"/>
            <input type="text" id="name" name="name" placeholder="课程名称/科目/老师"/>
            <select name="lable" id="lable">
                <option value="">请选择课程类型</option>
                <option value="live">直播</option>
                <option value="video">录播</option>
                <option value="face">面授</option>
            </select>
            <span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
        </div>
        <div class="user-list">
            <table class="table table-center" id="tableList">
            	<thead>
	                <tr data-buy="true">
	                    <th width="3%">序号</th>
	                    <th width="16%">课程名称</th>
	                    <th width="10%">学科</th>
	                    <th width="10%">授课老师</th>
	                    <th width="8%">课程类型</th>
	                    <th width="8%">报名人数 <i id="order_signup" class="icon iconfont unsort order_sorting">&#xe612;</i></th>
	                    <th width="8%">购买人数 <i id="order_buy" class="icon iconfont unsort order_sorting">&#xe612;</i></th>
	                    <th width="10%">课程详情</th>
	                </tr>
                </thead>
                <tbody class="tbodyList">
                	
                </tbody>
            </table>
            <div class="pages pagination">

            </div>
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
<script>
    //    点击更多显示菜单
    $('.more').mouseenter(function(){
        $(this).siblings('ul').show();
    });
    $('.more').mouseleave(function(){
        $(this).siblings('ul').hide();
    });
    $('.box').mouseenter(function(){
        $(this).show();
    });
    $('.box').mouseleave(function(){
        $(this).hide();
    });
</script>
<script>
    //    左侧active切换
    $selectSubMenus('classQueryGetClassList');
</script>

</body>
</html>