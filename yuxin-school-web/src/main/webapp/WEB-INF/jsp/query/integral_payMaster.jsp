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
<title>积分订单</title>
  <link rel="stylesheet" href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
  <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
  <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/footer.css"/>
  <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/points.css"/>
	<style type="text/css">
		.pages li.disabled{padding:0px;}
	</style>
</head>
<body>
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	<div class="u-wrap query overflow">
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_query.jsp"></jsp:include>
	<div class="right-side points-check-finance">
        <div class="mainbackground nopadding">
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">积分订单</span>
                </div>
            </div>
            <div >
                <div class="main-content nospace">
                    <div class="classes-type">
                        <p class="c">
                            <span class="t-title" style="position:relative;">日期</span>
                            <span class="t-content" id="dateList">
                                <a href="javascript:;" mark="today" class="btn btn-sm btn-default">今天</a>
                                <a href="javascript:;" mark="yesday" class="btn btn-sm btn-default">昨天</a>
                                <a href="javascript:;" mark="sevnday" class="btn btn-sm btn-default">7天</a>
                                <a href="javascript:;" mark="thirty" class="btn btn-sm btn-default">当月</a>
                                <a href="javascript:;" mark="thirmonth" class="btn btn-sm btn-default">三个月</a>
                                <a href="javascript:;" mark="nos" class="btn btn-sm btn-default">指定时间</a>
                                <em class="daterangs none" style="color: black;background-color: #f6f6f6;border: none;">从</em>
                                <input class="date-picker from daterangs none" type="text">
                                <em class="daterangs none" style="color: black;background-color: #f6f6f6;border: none;">到</em>
                                <input class="date-picker to daterangs none" type="text">
                                <input type="button" class="btn btn-sm btn-primary btn-searh search_integral_detail" value="搜索">
						    </span>
                        </p>
                    </div>
                </div>
                <div class="tables">
                    <table class="table table-center order_integralList">
                        <tbody><tr>
                            <th width="12%">手机号</th>
                            <th width="12%">用户名</th>
                            <th width="15%">订单时间</th>
                            <th width="10%">购买积分</th>
                            <th width="12%">兑换比例</th>
                            <th width="15%">支付方式</th>
                            <th width="12%">支付金额</th>
                            <th width="12%">学员名称</th>
                        </tr>
                        <tr><td colspan="8">暂无数据</td></tr>
                        </tbody></table>
                    <div class="pages pagination">
                    </div>
                </div>
            </div>
        </div>
    </div>
	</div>

	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/query/integral_payMaster.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
</body>
</html>