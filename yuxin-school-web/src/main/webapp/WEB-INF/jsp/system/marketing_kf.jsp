<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>营销设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
     <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <div class="left-side">
       <div class="left-side-title">
        	<em>营销</em>
        	<span class="iconfont return-pic hcancle">&#xe650;</span>
        </div>
       <ul id="markin_settings">
            <li class="subentry" mark="/companyMarketSet/showCompanyMarket">QQ号码</li>
            <li class="subentry" mark="/companyMarketSet/CompanyMarket_wx">微信</li>
            <li class="subentry" mark="/companyMarketSet/CompanyMarket_wb">新浪微博</li>
            <!-- <li class="subentry" mark="/companyMarketSet/CompanyMarket_sq">百度商桥</li> -->
            <li class="subentry active" mark="/companyMarketSet/CompanyMarket_kf">客服电话</li>
            <li class="subentry" mark="/sysConfigPage/toConfigPage">营销工具条设置</li>
        </ul>
    </div>
    <div class="right-side">
        <div class="title-box">
            <div class="tit-font" mark="services">
                <span class="t">客服电话设置</span>
               <c:choose>
                	<c:when test="${!empty comMark }">
	                	<c:choose>
	                		<c:when test="${comMark.serviceFlag==1 }">
                				<em class="iconfont normal open">&#xe603;</em>
                				<span id="addStatus30" class="i open">已启用</span>
                			</c:when>
                			<c:otherwise>
                				<em class="iconfont normal close">&#xe604;</em>
                				<span id="addStatus30" class="i close">已禁用</span>
                			</c:otherwise>
	                	</c:choose>
                	</c:when>
                	<c:otherwise>
                		<em class="iconfont normal close">&#xe604;</em>
                		<span id="addStatus30" class="i close">已禁用</span>
                	</c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="wx-box">
            <div class="wx-name">电话号码</div>
            <input type="text" id="comPhone" placeholder="请在这里输入电话号码" value="${comMark.servicePhone }"/>
        </div>
        <div class="wx-box">
            <div class="wx-name">服务时间</div>
            <input type="text" id="serviceTime" placeholder="上午8:00-下午18:00" value="${comMark.serviceTime }" maxlength="45"/>
        </div>
        <div class="clear"></div>
        <div class="savebtn"><input type="button" marks="kfmark" class="btn btn-success" style="margin-left: 100px; margin-top: 40px;" value="保存 "/></div>
    </div>
</div>
<input type="hidden" id="markId" value=""/>
<script type="text/javascript">
		$(function(){
			$selectMenu('org_service');
		})
	</script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/company/markting.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/splitmarket.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script src="<%=request.getContextPath()%>/javascripts/company/commonHeader.js"></script>
</body>
</html>