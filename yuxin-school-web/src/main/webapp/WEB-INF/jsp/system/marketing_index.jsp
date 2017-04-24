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
            <li class="subentry active" mark="/companyMarketSet/showCompanyMarket">QQ号码</li>
            <li class="subentry" mark="/companyMarketSet/CompanyMarket_wx">微信</li>
            <li class="subentry" mark="/companyMarketSet/CompanyMarket_wb">新浪微博</li>
            <!-- <li class="subentry" mark="/companyMarketSet/CompanyMarket_sq">百度商桥</li> -->
            <li class="subentry" mark="/companyMarketSet/CompanyMarket_kf">客服电话</li>
            <li class="subentry" mark="/sysConfigPage/toConfigPage">营销工具条设置</li>
        </ul>
    </div>
    <div class="right-side">
        <div class="title-box">
            <div class="tit-font" mark="markqq">
                <span class="t">QQ号码</span>
                <c:choose>
                	<c:when test="${!empty comMark }">
	                	<c:choose>
	                		<c:when test="${comMark.qqFlag==1 }">
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
        <div class="personqq-box qbox">
            <div class="qq-box personqq qqtypes" mark="MARKET_QQ_PERSON">
                <span class="iconfont qq-pic">&#xe66e;</span>
                <p class="picbox-name">个人QQ</p>
                <div class="choose-btn">选择</div>
                <div class="choosed">
                    <img src="<%=rootPath %>/images/choosed-1_03.png" alt=""/>
                </div>
            </div>
            <div class="qq-input person-input person_qqval">
                <div>
                    <span>QQ号码：</span>
                    <div class="qqinput-box">
                        <input type="text" id="person_qqval" value="${comMark.qqNum }"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="marketqq-box qbox">
            <div class="qq-box marketqq qqtypes" mark="MARKET_QQ_MARKET">
                <span class="iconfont qq-pic">&#xe66d;</span>
                <p class="picbox-name">营销QQ</p>
                <div class="choose-btn">选择</div>
                <div class="choosed">
                    <img src="<%=rootPath %>/images/choosed-1_03.png" alt=""/>
                </div>
            </div>
            <div class="qq-input market-input">
                <div>
                    <span>QQ号码：</span>
                    <div class="qqinput-box">
                        <input type="text" id="private_qqval" value="${comMark.qqNum }"/>
                    </div>
                </div>
                <div>
                    <span>营销QQ-Key值：</span>
                    <div class="qqinput-box">
                        <input type="text" id="private_qqkey" value="${comMark.qqKey }"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div class="sive-btn savebtn"><input type="button" marks="qqmark" class="btn btn-success" value="保存 "></div>
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
    <script src="<%=request.getContextPath()%>/javascripts/company/commonHeader.js" id="seajsnode"></script>
</body>
</html>