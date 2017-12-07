<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>资源管理</title>
    <link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet" type="text/css" />
    <link href="<%=rootPath%>/stylesheets/company.css" rel="stylesheet" type="text/css" />
    <link href="<%=rootPath%>/stylesheets/minitip.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/miniTip.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/laydate/laydate.js"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
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
        .u-wrap .mainbackground{padding: 0;}
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
    <div class="mainbackground u-content clear">
        <div class="full-wrap buy-box">
            <div class="buy-title">
                <div class="heading">
                    <i class="brand-text">存储详情</i>
                </div>
                <div class="title-infos">
                    <c:if test="${cms.videoFlow ==null || cms.videoFlow == 0}">
    						<span class="infos">
                                <em style="font-size: 14px;">
                                <fmt:formatNumber type="number" value="${cvf}" maxFractionDigits="3"/>/${cms.giveVideoFlow}</em>GB流量
                                <i class="iconfont ask" title="已使用：<fmt:formatNumber type="number" value="${cvf}" maxFractionDigits="3"/>GB&nbsp;/&nbsp;总量：${cms.giveVideoFlow}GB">&#xe60f;</i>
                            </span>
                    </c:if>
                    <c:if test="${cms.videoFlow !=null && cms.videoFlow != 0}">
    						<span class="infos"><em style="font-size: 14px;">
                                <fmt:formatNumber type="number" value="${cvf}" maxFractionDigits="3"/>/${cms.giveVideoFlow + cms.videoFlow}</em>GB流量
                                <i class="iconfont ask" title="已使用：<fmt:formatNumber type="number" value="${cvf}" maxFractionDigits="3"/>GB&nbsp;/&nbsp;总量：${cms.giveVideoFlow + cms.videoFlow}GB">&#xe60f;</i>
                            </span>
                    </c:if>
                    <c:if test="${cms.videoStorage ==null || cms.videoStorage == 0}">
							<span class="infos"><em style="font-size: 14px;">${cvs}/${cms.giveVideoStorage}</em>GB空间
                                <i class="iconfont ask" title="已使用：${cvs}GB&nbsp;/&nbsp;总量：${cms.giveVideoStorage}GB">&#xe60f;</i>
                            </span>
                    </c:if>
                    <c:if test="${cms.videoStorage !=null && cms.videoStorage != 0}">
							<span class="infos"><em style="font-size: 14px;">${cvs}/${cms.giveVideoStorage + cms.videoStorage}</em>GB空间
                                <i class="iconfont ask" title="已使用：${cvs}GB&nbsp;/&nbsp;总量：${cms.giveVideoStorage + cms.videoStorage}GB">&#xe60f;</i>
                            </span>
                    </c:if>
                    <c:if test="${cms.videoEndDate == null}">
    						<span class="infos">
                                <em style="font-size: 14px;"><fmt:formatDate value="${cms.giveVideoStorageDate}" /></em>空间有效期
                                <i class="iconfont ask" title="有效期至：<fmt:formatDate value="${cms.giveVideoStorageDate}"/>">&#xe60f;</i>
                            </span>
                    </c:if>
                    <c:if test="${cms.videoEndDate != null}">
    						<span class="infos"><em style="font-size: 14px;"><fmt:formatDate value="${cms.videoEndDate}" /></em>空间有效期
                                <i class="iconfont ask" title="有效期至：<fmt:formatDate value="${cms.videoEndDate}"/>">&#xe60f;</i>
                            </span>
                    </c:if>
                    <input type="hidden" value="<fmt:formatDate value="${cms.videoEndDate}"/>" id="videoEndDate" />
                    <input type="hidden" value="${cms.videoStorage}" id="haveSpace" />
                    <input type="hidden" value="${cms.videoFlow}" id="haveFlow" />
                </div>
            </div>
            <div class="tabs" style="padding-left: 0px;">
                <a href="javascript:;" class="btn btn-sm btn-default btn-success">统计概况</a>
                <a href="javascript:;" class="btn btn-sm btn-default" id="FDetail">流量统计详情</a>
            </div>
            <div class="tabs-content" >
                <div class="p-1">
                    <div id="pieDomSpace" style="height: 400px;width: 95%;"></div>
                    <div id="pieDomFlow" style="height: 400px;width: 95%;"></div>
                </div>
                <div class="p-1" id="pageTwo">
                    <div class="w">
                        <span class="class_number_name">起始时间：</span>
                        <input type="text" class="laydate-icon" readonly="readonly" id="start" style="width:200px">
                        <span>至 </span><input type="text" readonly="readonly" class="laydate-icon" id="end" style="width:200px">
                        <select id = "searchType">
                            <option >全部</option>
                            <option value="video">视频</option>
                            <option value="resource">资源</option>
                        </select>
                        <input type="button" value="搜索" class="btn btn-sm selectInfo">
                    </div>
                    <div id="tb" style="margin-top: 40px;">
                        <div id="lineDomFlowDetail" style="height: 400px; width: 95%;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<input type="hidden" id="haveSpace" value="${cms.videoStorage}" />
<input type="hidden" id="usedSpace" value="${css.videoStorage}" />
<input type="hidden" id="resourceUsedSpace" value="${css.resourceStorage}" />
<input type="hidden" id="videoUsedSpace" value="${css.videoStorage}" />
<input type="hidden" id="giveSpace" value="${cms.giveVideoStorage}" />
<input type="hidden" id="giveFlow" value="${cms.giveVideoFlow}" />
<input type="hidden" id="haveFlow" value="${cms.videoFlow}" />
<input type="hidden" id="videoUsedFlow" value="${css.videoFlow}" />
<input type="hidden" id="ResourceUsedFlow" value="${css.resourceFlow}" />
<!-- 购买主体结束 -->
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<!-- footer start -->
<!-- footer end -->

<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts-all.js"></script>
<%--<script type="text/javascript" src="<%=rootPath%>/javascripts/company/videoStatistics.js"></script>--%>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company/resourceManger.js"></script>
<script>
    //    左侧active切换
    $selectSubMenus('getVideoResourceAndMessageStatistics');
</script>
</body>
</html>