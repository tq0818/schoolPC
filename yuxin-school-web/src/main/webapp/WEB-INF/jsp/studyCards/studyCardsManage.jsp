<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
	<title>学习卡</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/splitscreen.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/studyCard/stuCard.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/utils.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	 <div class="u-wrap company overflow">
        <jsp:include page="/WEB-INF/jsp/menu/menu_studycard.jsp"></jsp:include>
        <div class="right-side proxyManage">
            <div>
                <div class="title-box">
                    <div class="tit-font clear">
                        <span class="t">学习卡管理</span>
                        <a href="javascript:;" class="btn proxyBtn studyCard-create">创建学习卡</a>
                    </div>
                </div>
                <div class="proxyBox studyCard-list">
                	<form class="none studyCardLibsFrom"><input type="hidden" name="cardId" id="cardId" value=""></form>
                    <form class="proxySearch studyCardFrom">
                        <span>创建日期：</span>
                        <input type="text" name="startDate" id="startDate" class="setTime data-picker" value="" readonly="">
                        <b>至</b>
                        <input type="text" name="endDate" id="endDate" class="setTime data-picker" value="" readonly="">
                        <input type="text" name="name" id="name" class="proxyAgen" placeholder="学习卡名称">
                        <input type="text" name="proxyName" id="proxyName" class="proxyAgen proxyAgens" placeholder="代理机构名称">
                        <input class="studyCard-search" type="button" value="搜索">
                    </form>
                      <div class="export clear">
                          <input type="button" class="export studyCard-export" value="导出">
                      </div>
                    <table class="table table-center cashrecord-tab">
                        <colgroup>
                            <col width="20%" />
                            <col width="10%" />
                            <col width="10%" />
                            <col width="20%" />
                            <col width="10%" />
                            <col width="10%" />
                            <col width="10%" />
                            <col width="20%" />
                        </colgroup>
                        <tbody>
                            <tr class="tab-htr">
                                <th>学习卡名称_代理机构名称</th>
                                <th>学习卡数量</th>
                                <th>已领取数量</th>
                                <th>使用期限</th>
                                <th>学习卡单价</th>
                                <th>创建时间</th>
                                <th>创建人</th>
                                <th>操作</th>
                            </tr>
                            <tr><td colspan="8">暂无数据</td></tr>
                        </tbody>
                    </table>
                    <div class="pages pages-find pages-card">
                        <ul class="pagination">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="loading-bg layerTipsBg lp-units-loading-bg" style="display:none"></div>
    <div class="loading data lp-units-loading " style="z-index: 99; display: none;">
		<p><i></i>加载中,请稍后...</p>
	</div>
    <div class="loading create lp-units-loading " style="z-index: 99; display: none;">
		<p><i></i>正在准备学习卡库,请稍后...</p>
	</div>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-pagination/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/studyCards/studyCardsManage.js"></script>
	
</body>
</html>