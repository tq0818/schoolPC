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
<title>会员订单</title>
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
                    <span class="t">会员订单</span>
                </div>
            </div>
            <div >
                <div class="main-content nospace">
                	<form id="searchForm" method="post">
		            <div style="margin-top: 15px;margin-left:50px;">
		                <select id="stuMemberLevel" name="memberId" style="width: 180px;overflow: hidden">
		                </select>
		                <input id="stuMobile" name="mobile" placeholder="手机号" maxlength="11" type="text">
		                <input id="stuName" name="stuName" placeholder="姓名" type="text">
		                <input id="username" name="username" placeholder="用户名" type="text">
		            </div>
                    <div class="classes-type">
                        <p class="c">
                            <span class="t-title" style="position:relative;">日期</span>
                            <span class="t-content" id="dateList">
                                <a href="javascript:;" mark="today" class="btn btn-sm btn-default btn-success">今天</a>
                                <a href="javascript:;" mark="yesday" class="btn btn-sm btn-default">昨天</a>
                                <a href="javascript:;" mark="sevnday" class="btn btn-sm btn-default">7天</a>
                                <a href="javascript:;" mark="thirty" class="btn btn-sm btn-default">当月</a>
                                <a href="javascript:;" mark="thirmonth" class="btn btn-sm btn-default">三个月</a>
                                <a href="javascript:;" mark="nos" class="btn btn-sm btn-default">指定时间</a>
                                <em class="daterangs none" style="color: black;background-color: #f6f6f6;border: none;">从</em>
                                <input class="date-picker from daterangs none" type="text" name="startTime">
                                <em class="daterangs none" style="color: black;background-color: #f6f6f6;border: none;">到</em>
                                <input class="date-picker to daterangs none" type="text" name="endTime">
                                <input type="button" class="btn btn-sm btn-primary btn-searh search_vip_detail" value="搜索">
                                <input type="button" class="btn btn-sm btn-primary export_vip_detail" value="导出">
						    </span>
                        </p>
                    </div>
                    </form>
                </div>
                <div class="tables">
                    <table class="table table-center order_vipList">
                        <tbody><tr>
                            <th width="10%">姓名</th>
                            <th width="10%">用户名</th>
                            <th width="10%">手机号</th>
                            <th width="10%">会员等级</th>
                            <th width="10%">有效期</th>
                            <th width="12%">订单日期</th>
                            <th width="10%">购买方式</th>
                            <th width="10%">金额</th>
                            <th width="10%">订单来源</th>
                        </tr>
                        <tr><td colspan="9">暂无数据</td></tr>
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
	<script type="text/javascript" src="<%=rootPath%>/javascripts/query/vip_payMaster.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
</body>
</html>