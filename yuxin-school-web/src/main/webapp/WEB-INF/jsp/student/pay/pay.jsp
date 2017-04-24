<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>补费</title>
    <%@include file="/decorators/import.jsp"%>
    <link type="text/css" rel="stylesheet"  href="<%=request.getContextPath()%>/stylesheets/student.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/pay/pay.js"> </script>
    <script type="text/javascript">
    $selectSubMenu('student_urge_fee');
    </script>
    
</head>
<body>
<input type="hidden" id="id" value="${id }"/>
<form action="<%=request.getContextPath() %>/student/toMessage" method="post" id="topayForm">
<input type="hidden" id="mobile" name="mobile" value="${mobile }"/>
</form>
<form action="<%=request.getContextPath()%>/student/pay" method="post" id="payForm">
	<input type="hidden" id="idd" name="id"/>
	<input type="hidden" id="mob" name="mobile" value="${mobile }"/>
	<input type="hidden" id="pid" name="pid" value="${payMaster.id }"/>
	<input type="hidden" id="schoolId" name="schoolId" value="${payMaster.schoolId }"/>
	<input type="hidden" id="examTermName" name="examTermName" value="${payMaster.examTermName }"/>
	<input type="hidden" id="stuId" name="stuId" value="${payMaster.stuId }"/>
	<input type="hidden" id="totalAmount" name="totalAmount" value="${payMaster.totalAmount }"/>
</form>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">补费</h2>
            <div class="search">
                 <i class="tips">没有查到相关信息!</i>
                <input type="text" id="phone" name="mobile" class="input-ctrl">
                <input type="button" class="btn btn-sm" onclick="toSearch();" value="搜索">
            </div>
            <span class="line"></span>
        </div>
		<div id="message">
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">应缴费用</h2>
            </div>
            <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">订单总价</span>
                        <span class="c-content">${total}</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">已缴</span>
                        <span class="c-content">${payed}</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                    	
                        <span class="c-title">分期日期</span>
                        <span class="c-content"><fmt:formatDate value="${feeStage.stageDate }" timeStyle="yyyy-MM-dd"/></span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">本期应缴</span>
                        <span class="c-content" id="canPay">${feeStage.stageFee }</span>
                    </p>
                </li>
            </ul>
        </div>
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">缴费</h2>
            </div>
            <div class="pay-list clear">
                <div class="pay-list-left">
                    <p>本期</p>
                </div>
                <form action="<%=request.getContextPath() %>/student/toPay"   id="toPay">
                <input type="hidden" id="payMasterId" name="payMasterId" value="${payMaster.id }"/>
                <div class="pay-list-right">
                    <p class="c">
                        <span class="p-title">POS：</span>
                        <span class="p-content">￥ <input type="text" class="prices" id="posReal" name="posReal"   placeholder="输入金额"></span>
                    </p>
                    <p class="c">
                        <span class="p-title">现金：</span>
                        <span class="p-content">￥ <input type="text" class="prices" id="cashReal"  name="cashReal" placeholder="输入金额"></span>
                    </p>
                    <p class="c">
                        <span class="p-title">支票：</span>
                        <span class="p-content">￥ <input type="text" class="prices" id="checkReal"  name="checkReal" placeholder="输入金额"></span>
                    </p>
                    <p class="c">
                        <span class="p-title">转账：</span>
                        <span class="p-content">￥ <input type="text" class="prices" id="remitReal"  name="remitReal" placeholder="输入金额"></span>
                    </p>
                    <p class="c">
                        <span class="p-title">&nbsp;</span>
                        <span class="p-content">
                            <a href="javascript:;" class="btn btn-sm btn-primary" onclick="payed();">确定</a>
                            <a href="javascript:;" onclick="history.go(-1);" class="btn btn-sm btn-default">返回</a>
                        </span>
                    </p>
                </div>
                </form>
            </div>
        </div>
    </div>
        <div id="classTypeMessage"></div>
        <div id="feeMessage"></div>
    </div>
</div>
</body>
</html>