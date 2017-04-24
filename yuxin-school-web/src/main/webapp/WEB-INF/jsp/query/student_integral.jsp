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
<title>学员积分</title>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/classes.css"/>
    <link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
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
	<div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">学员积分统计</span>
                </div>
            </div>
            <div class="points-check">
            <form  id="integralForm">
                <div class="search-cnt">
                    <input type="text" placeholder="手机号" id="st_mobile" name="mobile"/>
                    <input type="text" placeholder="姓名" id="st_name" name="name"/>
                    <input type="text" placeholder="用户名" id="st_username" name="username"/>
                    <input type="button" class="btn btn-sm btn-primary btn-searh searchIntegralList" value="搜索">
                </div>
            </form>
                <div class="table-wrap">
                    <table class="table table-center table_integral">
                        <colgroup>
                            <col width="20%">
                            <col width="20%">
                            <col width="20%">
                            <col width="20%">
                            <col width="20%">
                        </colgroup>
                        <tr>
                            <th>手机号</th>
                            <th>姓名</th>
                            <th>用户名</th>
                            <th>当前积分</th>
                            <th>操作</th>
                        </tr>
                    </table>
                     <div class="pages pagination">
					</div>
                </div>
            </div>
        </div>
    </div>
	</div>
<!--弹出层-->
<div class="upload-layer check-student none" >
    <div class="upload-title">
        <h2 class="h5" id="stu_ingte_Name">学员名称</h2>
        <i class="close iconfont"></i>
    </div>
    <div class="points-check">
        <div class="search-cnt">
            <p class="c">
                <span class="t-title">日期</span>
                <span class="t-content" id="dateList">
                    <a href="javascript:;" mark="today" class="btn btn-sm btn-default">今天</a>
                    <a href="javascript:;" mark="yesday" class="btn btn-sm btn-default">昨天</a>
                    <a href="javascript:;" mark="sevnday" class="btn btn-sm btn-default">7天</a>
                    <a href="javascript:;" mark="thirty" class="btn btn-sm btn-default">当月</a>
                    <a href="javascript:;" mark="thirmonth" class="btn btn-sm btn-default">三个月</a>
                    <a href="javascript:;" mark="nos" class="btn btn-sm btn-default set-time">指定时间</a>
                    <em class="daterangs none" style="color: black;background-color: #f6f6f6;border: none;">从</em>
                    <input class="date-picker from daterangs none" type="text">
                    <em class="daterangs none" style="color: black;background-color: #f6f6f6;border: none;">到</em>
                    <input class="date-picker to daterangs none" type="text">
                    <input type="button" class="btn btn-sm btn-primary btn-searh searchIntegral_detail" value="搜索">
                </span>
            </p>
        </div>
        <div class="table-wrap">
            <table class="table table-center tabIntegralDetailList">
                <colgroup>
                    <col width="25%">
                    <col width="25%">
                    <col width="25%">
                    <col width="25%">
                </colgroup>
                <tr>
                    <th>时间</th>
                    <th>获取/消费</th>
                    <th>积分变化</th>
                    <th>当前积分</th>
                </tr>
               
            </table>
        </div>
    </div>
</div>
<div class="add-layer-bg none"></div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/query/student_integral.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
</body>
</html>