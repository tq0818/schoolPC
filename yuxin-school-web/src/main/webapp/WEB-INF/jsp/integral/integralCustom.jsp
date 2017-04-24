<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>积分消费</title>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/integral/points.css"/>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <%@include file="/WEB-INF/jsp/menu/menu_integral.jsp" %>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">积分消费</span>
                </div>
            </div>
            <div class="point-get">
                <div class="set-system point-get-content" data-id="${cic.id }">
                    <div>
                        <p>说明：用户可根据需要开启或关闭学员使用积分的场景</p>
                        <div class="point-get-itm">
                            <div>
                                <i>课程</i>
                                <c:choose>
	                            	<c:when test="${cic.classCost == 1}">
	                            		<span class="l-btn-status  open classCost"><i class="iconfont">&#xe603</i> <em>已开启</em></span>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<span class="l-btn-status  close classCost"><i class="iconfont">&#xe604</i> <em>已禁用</em></span>
	                            	</c:otherwise>
	                            </c:choose>
                            </div>
                            <div class="g-money" <c:if test="${cic.classCost != 1}">style="display:none;"</c:if>>
	                            <p>说明：开启后，学员可根据机构设定的比例，使用积分兑换金钱，购买课程</p>
	                            <div >
	                                <i>积分抵现:</i>
	                                <input type="text" data-type="classCostMaxScale" value="<c:if test="${empty cic.classCostMaxScale}">20</c:if><c:if test="${!empty cic.classCostMaxScale}">${cic.classCostMaxScale}</c:if>"/>
	                                <i>%</i>
	                                <span>（使用积分抵消金钱，最多可抵消课程全部金额的百分之<em><c:if test="${empty cic.classCostMaxScale}">20</c:if><c:if test="${!empty cic.classCostMaxScale}">${cic.classCostMaxScale}</c:if></em>%）</span>
	                            </div>
                            </div>
                        </div>
                        <div class="point-get-itm" style="display:none;">
                            <div>
                                <i>课程包</i>
                                <c:choose>
	                            	<c:when test="${cic.classPackageCost == 1}">
	                            		<span class="l-btn-status  open"><i class="iconfont">&#xe603</i> <em>已开启</em></span>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<span class="l-btn-status  close"><i class="iconfont">&#xe604</i> <em>已禁用</em></span>
	                            	</c:otherwise>
	                            </c:choose>
                            </div>
                            <div  class="g-money" <c:if test="${cic.classCost != 1}">style="display:none;"</c:if>>
	                            <p>说明：开启后，学员可根据机构设定的比例，使用积分兑换金钱，购买课程包</p>
	                            <div>
	                                <i>积分抵现:</i>
	                                <input type="text" data-type="classPackageCostMaxScale" value="<c:if test="${empty cic.classPackageCostMaxScale}">20</c:if><c:if test="${!empty cic.classPackageCostMaxScale}">${cic.classPackageCostMaxScale}</c:if>"/>
	                                <i>%</i>
	                                <span>（使用积分抵消金钱，最多可抵消课程全部金额的百分之<em><c:if test="${empty cic.classPackageCostMaxScale}">20</c:if><c:if test="${!empty cic.classPackageCostMaxScale}">${cic.classPackageCostMaxScale}</c:if></em>%）</span>
	                            </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
$(function(){
	$selectSubMenu('org_service');
	$chooseMenu('integralToBuy');
});
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/integral/integralCustom.js"></script>
</body>
</html>