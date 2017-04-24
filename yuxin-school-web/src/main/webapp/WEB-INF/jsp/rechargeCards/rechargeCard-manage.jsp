<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp" %>
    <title>促销</title> 
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/system.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/classedit.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/coupon.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/conpon-alert.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage-screen.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fonts/iconfont.css"/>
    <style type="text/css">
    li.subentry span {left:82px;}
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<input id="exportExcel_PatchId" value="0" type="hidden">
<div class="u-wrap company overflow coupon-page">
  
    <jsp:include page="/WEB-INF/jsp/menu/menu_coupon.jsp"></jsp:include>
    <div class="right-side">

        <div class="u-wrap classes set-system set-coupon">
            <div class="screen-right-cont set-system ">
                <div class="screen-right-title">
                    <h3>
                        <i> 充值卡管理</i>
                    </h3>
                    <div class="set-new manage-button couponCreate rechargeCard-create"> 创建充值卡<span class="manage-button-mask"></span></div>
                </div>
                <div class="search-cont search-coupon clear">
                    <h2 class="fl">创建日期:</h2>
                    <div class="fl inputBut">
                        <input type="button" value="今天" data-mark="today" class="rechargeCard-timemark">
                        <input type="button" value="昨天" data-mark="yesday" class="rechargeCard-timemark">
                        <input type="button" value="7天" data-mark="sevnday" class="rechargeCard-timemark">
                        <input type="button" value="当月" data-mark="thirty" class="rechargeCard-timemark">
                        <input type="button" value="三个月" data-mark="thirmonth" class="rechargeCard-timemark">
                        <input type="button" value="指定时间" class="button rechargeCard-timemark" data-mark="nos" class="rechargeCard-timemark">
                    </div>
                    <div class="fl fromToTime">
                        <span class="use-time c-use-time" style="display: none;">
                            <input type="text" id="rechargeCard-startTime" class="date-picker from" readonly="true">
                            <em>至</em>
                            <input type="text" id="rechargeCard-endTime" class="date-picker to" readonly="true">
                        </span>
                    </div>
                </div>
                <div class="search-cont search-coupon clear">
                    <h2 class="fl">有效期:</h2>
                    <div class="fl fromToTime">
                        <span class="use-time">
                            <input type="text" id="rechargeCard-startDate" class="date-picker from">
                            <em>至</em>
                            <input type="text" id="rechargeCard-endDate" class="date-picker to">
                        </span>
                    </div>
                </div>
                <div class="search-cont search-coupon clear">
                    <h2 class="fl">储值金额:</h2>
                    <div class="fl price">
                        <label for="">
                            <input type="text" name="" id="rechargeCard-minPrice">
                            <i>￥</i>
                        </label>
                        <em>—</em>
                        <label for="">
                            <input type="text" name="" id="rechargeCard-maxPrice">
                            <i>￥</i>
                        </label>
                    </div>
                    <input type="text" placeholder="代理机构名称" id="rechargeCard-orgName" class="fl prox">
                    <button class="btn btn-coupon-searh patchs-search fl">搜索</button>
                </div>
                <div class="user-list card-list">
                    <table class="table table-center couponsList">
                        <colgroup>
                            <col width="16%">
                            <col width="21%">
                            <col width="8%">
                            <col width="8%">
                            <col width="22%">
                            <col width="9%">
                            <col width="16%">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>创建日期</th>
                            <th>代理机构</th>
                            <th>储值金额</th>
                            <th>数量</th>
                            <th>有效期</th>
                            <th>已充值数量</th>
                            <th>操作</th>
                        </tr>
                        <tr><td colspan="7">暂无数据</td></tr>
                        </tbody>
                    </table>
                    <p class="money">
                        <span>已储值金额：<em id="price-num"></em></span>
                    </p>
                </div>
                <div class="pages pagination">
		            <ul class="pagination">
		            </ul>
		        </div>
            </div>
        </div>

    </div>
</div>
<div class="layer-tips coupon-code-detail couponCodeDetail couponDetail libsShow" style="margin-left: -470px; display: none; margin-top: -285px;">
    <div class="layer-tips-title">详情 <i class="close iconfont confirm_close libs-close"></i></div>
    <div class="search-cont search-coupon clear">
        <h2 class="fl">使用状态:</h2>
        <select name="" id="libs-status" class="fl">
            <option value="" selected="selected">请选择</option>
            <option value="0">未充值</option>
            <option value="1">已充值</option>
        </select>
        <input type="text" placeholder="使用者账号" id="libs-user" class="fl prox">
        <button class="btn btn-coupon-searh libs-search fl">搜索</button>
    </div>
	<form action="" id="export-form">
		<input type="hidden" value="" name="patchId" id="export-patchId"/>
	</form>
    <!--<h3>使用详情</h3>-->
    <div class="coupon-use-detail couponUseDetail libs-list">
        <table class="table table-center coupons_libs_order_List">
            <colgroup>
                <col width="25%">
                <col width="15%">
                <col width="30%">
                <col width="30%">
            </colgroup>
            <tbody>
            <tr>
                <th>充值码</th>
                <th>状态</th>
                <th>使用者账号</th>
                <th>充值时间</th>
            </tr>
			<tr><td colspan="4">没有查找到数据</td></tr>
            </tbody>
        </table>
        <div class="pages pagination_libs_order L-pages">
            <ul class="lib-pagination">
            </ul>
        </div>
    </div>
</div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none; z-index: 99;">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none;"></div>
<!--  ajax加载中div结束 -->
<div class="layer-tips-bg" style="display: none;"></div>

<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script src="<%=rootPath%>/javascripts/rechargeCard/rechargeCard-manage.js"></script>

</body>

</html>