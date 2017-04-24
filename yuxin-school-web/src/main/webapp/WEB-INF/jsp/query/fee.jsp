<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>订单查询</title>
    <link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
     <link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css"/>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<input type="hidden" id="schoolId" value='${schoolId}'/>
		<input type="hidden" id="schoolName" value='${schoolName}'/>
		<input type="hidden" id="isAdmin" value='${isAdmin}'/>
		<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>	
<div class="u-wrap query overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_operaconfig.jsp"></jsp:include>
	<div class="right-side">
    <div class="mainbackground nopadding" style="padding-left:15px;">
        <div class="main-content nospace">
            <div class="classes-type">
                <p class="c">
                    <span class="t-title" style="width:30px">分校</span>
                    <span class="t-content" id="schoolList">
                    </span>
                </p>
                <p class="c">
                    <span class="t-title" style="width:30px">科目</span>
                    <span class="t-content" id="oneList">
                    </span>
                </p>
                <p class="c">
                    <span class="t-title" style="width:30px">类型</span>
                    <span class="t-content" id="orderList">
                    </span>
                </p>
                <p class="c">
                    <span class="t-title" style="width:30px">日期</span>
                    <span class="t-content" id="dateList">
	                <a href="javascript:;" mark="today" class="btn btn-sm btn-default" >今天</a>
	                <a href="javascript:;" mark="yesday" class="btn btn-sm btn-default" >昨天</a>
	                <a href="javascript:;" mark="sevnday" class="btn btn-sm btn-default" >7天</a>
	                <a href="javascript:;" mark="thirty" class="btn btn-sm btn-default" >当月</a>
	                <a href="javascript:;" mark="thirmonth" class="btn btn-sm btn-default" >三个月</a>
                	<a href="javascript:;" mark="nos" class="btn btn-sm btn-default" >指定时间</a>
                        <em class="daterangs none">从</em> 
						<input class="date-picker from daterangs none" type="text"> 
						<em class="daterangs none">到</em>
						<input class="date-picker to daterangs none" type="text"> 
                        <input type="button" id="searchContent" class="btn btn-sm btn-primary" value="搜索">
                    </span>
                </p>
            </div>
        </div>
    </div>
    <div class="mainbackground" style="padding: 50px 15px 10px;">
        <div class="title" style="width:96%;">
            <a href="javascript:;" class="btn btn-sm btn-success btn-detail">流水</a>
            <a href="javascript:;" class="btn btn-sm btn-default btn-chart">退费</a>
            <a href="javascript:;" class="btn btn-sm btn-primary btn-daoc" style="float:right;">导出</a>
        </div>
        <div class="c-list lsList">
            <div class="tabs lsts">
                <a href="javascript:;" class="btn btn-sm btn-default">线上￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">线下￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">支票:￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">转账:￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">现金:￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">POS:￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">合计流水：￥<b>0</b></a>
            </div>
            <div class="tables lsOne">
                <table class="table table-center">
                    <tr>
                        <th width="7%">所属分校</th>
                        <th width="8%">姓名</th>
                        <th width="15%">报名时间</th>
                        <th width="20%">科目</th>
                        <th width="15%">课程</th>
                        <th width="10%">缴费日期</th>
                        <th width="10%">类型</th>
                        <th width="10%">缴费方式</th>
                        <th width="10%">金额</th>
                    </tr>
                   <tr><td colspan="9">暂无数据</td></tr>
                </table>
            </div>
            <div class="pages pagination ls">
            </div>
        </div>
       
       <div class="c-list thList" style="display: none;">
            <div class="tabs thOne">
                <a href="javascript:;" class="btn btn-sm btn-default">支票:￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">转账:￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">现金:￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">POS:￥<b>0</b></a>
                <a href="javascript:;" class="btn btn-sm btn-default">合计退费：￥<b>0</b></a>
            </div>
            <div class="tables tfOne">
                <table class="table table-center">
                    <tr>
                        <th width="7%">所属分校</th>
                        <th width="8%">姓名</th>
                        <th width="15%">退费日期</th>
                        <th width="15%">科目</th>
                        <th width="12%">科目小类</th>
                        <th width="15%">课程</th>
                        <th width="10%">退费类型</th>
                        <th width="10%">退费方式</th>
                        <th width="13%">金额</th>
                    </tr>
                    <tr><td colspan="9">暂无数据</td></tr>
                </table>
            </div>
            <div class="pages pagination th">
            </div>
        </div>
       <!-- 结束 -->
    </div>
    </div>
</div>
<input type="hidden" value="${stydycardservice }" id="stydycardservice"/>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/query/fee.js"></script>
</body>
</html>