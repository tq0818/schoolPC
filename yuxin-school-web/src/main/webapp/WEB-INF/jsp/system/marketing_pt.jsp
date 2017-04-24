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
            <li class="subentry" mark="/companyMarketSet/CompanyMarket_kf">客服电话</li>
            <li class="subentry active" mark="/sysConfigPage/toConfigPage">营销工具条设置</li>
        </ul>
    </div>
    <div class="right-side">
        <div class="title-box">
            <div class="tit-font">
                <span class="t">营销工具条设置</span>
            </div>
        </div>
        <p class="prompt-font">说明：营销工具条将在网校右侧显示。展现内容将根据 <span class="blue-font">系统-营销工具</span> 中的配置展示</p>
        <div class="classify-box">
            <div class="temp-boxs clear">
                <div>模板1</div>
                <div class="temp">
                    <div class="tool-choosed" <c:if test="${spr!=null && spr.link==1}" >style="display:block;"</c:if>>
                        <img class="choosed" src="<%=rootPath %>/images/choosed-1_03.png" alt="" >
                    </div>
                    <img class="tool_style" data-link="1" src="<%=rootPath %>/images/yx2_02.png" alt=""/>
                </div>
            </div>
            <div class="temp-boxs clear" >
                <div>模板2</div>
                <div class="temp">
                    <div class="tool-choosed" <c:if test="${spr!=null && spr.link==2}" >style="display:block;"</c:if>>
                        <img class="choosed" src="<%=rootPath %>/images/choosed-1_03.png" alt="">
                    </div>
                    <img class="tool_style" data-link="2" src="<%=rootPath %>/images/yx2_03.png" alt=""/>
                </div>
            </div>
            <div class="temp-boxs clear">
                <div>模板3</div>
                <div class="temp">
                    <div class="tool-choosed" <c:if test="${spr == null || spr.link==3}" >style="display:block;"</c:if>>
                        <img class="choosed" src="<%=rootPath %>/images/choosed-1_03.png" alt="">
                    </div>
                    <img class="tool_style" data-link="3"  src="<%=rootPath %>/images/yx2_09.png" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	$(function(){
		$selectMenu('org_service');
	})
</script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company/markting.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/system/marketing_pt.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/company/commonHeader.js" id="seajsnode"></script>
</body>
</html>