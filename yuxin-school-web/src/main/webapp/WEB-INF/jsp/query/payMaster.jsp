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
		<input type="hidden" id="isProxyOrg" value='${isProxyOrg}'/>	
		<input type="hidden" id="userorg_roleopenflag" value='${userorg_roleopenflag}'/>	
<div class="u-wrap query overflow">
 	<jsp:include page="/WEB-INF/jsp/menu/menu_query.jsp"></jsp:include>
    <div class="right-side">    
    <div class="mainbackground nopadding " style="padding-left:15px;">
        <div class="main-content nospace">
            <div class="classes-type">
               <p class="c">
					<span class="t-title">分校</span> 
					<span class="t-content" id="schoolList"> </span>
			   </p>
			   <c:if test="${userorg_roleopenflag==1 && isAdmin == 'true' }">
	      	   <p class="" style="position: relative;padding-left: 72px;">
					<span class="t-title">代理机构</span> 
					<span class="t-content">
						<select id="proxyOrgs" style="width: 170px;padding-top: 0px;"> </select>
					</span>
			   </p>
			   </c:if>
                <p class="c">
                    <span class="t-title">科目</span>
                    <span class="t-content" id="oneList"></span>
                </p>
                <p class="c">
                    <span class="t-title">科目小类</span>
                    <span class="t-content" id="secondList">
                    </span>
                </p>
                <p class="c">
                    <span class="t-title">课程</span>
                    <span class="t-content">
                    	<select id="classtypeList" style="width: 170px;padding-top: 0px;">
                    	
                    	</select>
                    </span>
                </p>
                <p class="c">
                    <span class="t-title">订单</span>
                    <span class="t-content" id="orderList">
                    </span>
                </p>
                <p class="c">
                    <span class="t-title">日期</span>
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
						<input type="button" class="btn btn-sm btn-primary btn-searh" id="searchContent" value="搜索">
                    </span>
                </p>
            </div>
        </div>
    </div>
    
    <div class="mainbackground" style="padding:50px 15px 10px;">
         <div class="title">
				<a href="javascript:;" class="btn btn-sm btn-success btn-detail">明细</a> <a
					href="javascript:;" class="btn btn-sm btn-default btn-chart">趋势图</a>
	     </div>
        <div class="c-list">
            <div class="tabs">
                <a href="javascript:;" class="btn btn-sm btn-default">报名人次：<b>0</b>人次</a>
                <a href="javascript:;" class="btn btn-sm btn-default">合计应缴：￥<b>0</b></a>
            </div>
            <div class="panel">
					<div class="chars none" style="width:1060px;height:500px;"></div>
					<div class="tables">
						<table class="table table-center">
								<tr>
									<c:choose>
										<c:when test="${userorg_roleopenflag ==1 && (isAdmin == true || isProxyOrg == true)}">
											<th width="12%" >所属分校-代理机构</th>
										</c:when>
										<c:otherwise>
											<th width="12%" >所属分校</th>
										</c:otherwise>
									</c:choose>
			                        <th width="12%">姓名</th>
			                        <th width="12%">用户名</th>
			                        <th width="12%">订单时间</th>
			                        <th width="20%">课程</th>
			                        <th width="10%">课程定价</th>
			                        <th width="10%">售价</th>
			                        <th width="12%">订单来源</th>
			                    </tr>
								<tr><td colspan="8">暂无数据</td></tr>
						</table>
						<div class="pages pagination">
						</div>
					</div>
				</div>
        </div>
    </div>
    
    </div>
</div>

	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/query/payMaster.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
</body>
</html>