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
            <li class="subentry active" mark="/companyMarketSet/CompanyMarket_sq">百度商桥</li>
            <li class="subentry" mark="/companyMarketSet/CompanyMarket_kf">客服电话</li>
            <li class="subentry" mark="/sysConfigPage/toConfigPage">营销工具条设置</li>
        </ul>
    </div>
    <div class="right-side">
        <div class="title-box">
            <div class="tit-font" mark="bdsq">
                <span class="t">百度商桥设置</span>
               <c:choose>
                	<c:when test="${!empty comMark }">
	                	<c:choose>
	                		<c:when test="${comMark.bdsqFlag==1 }">
                				<em class="iconfont normal open">&#xe603;</em>
                				<span class="i open">已启用</span>
                			</c:when>
                			<c:otherwise>
                				<em class="iconfont normal close">&#xe604;</em>
                				<span class="i close">已禁用</span>
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
            <div class="wx-name">JS参数</div>
            <input type="text" id="bdsqJsUrl" placeholder="请在这里输入百度商桥JS参数" value="${comMark.bdsqJsUrl}"/>
        </div>
        <div class="wx-box">
            <div class="wx-name">自动弹出咨询邀请</div>
            <select id="bdsqType" value= "${comMark.bdsqType}">
            		<c:if test="${comMark.bdsqType==1 }">
                        <option value="1">自动弹出提示</option>
        				<option value="0">不自动弹出</option>
        			</c:if>
                    <c:otherwise>
        				<option value="0">不自动弹出</option>
                        <option value="1">自动弹出提示</option>
        			</c:otherwise>
            </select>
        </div>
        <div class="clear"></div>
        <div class="wx-sive-btn savebtn"><input type="button" marks="bdsq" class="btn btn-success" value="保存 "/></div>
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