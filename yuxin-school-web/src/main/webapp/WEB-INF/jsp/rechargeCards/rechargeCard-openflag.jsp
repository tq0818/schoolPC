<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>促销</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/system.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/footer.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage-screen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/coupon.css"/>
    <style type="text/css">
   	 .classify{
   	 	cursor:pointer
   	 }
   	 .save-useway{
   		 width:58px;
   		 text-align: center;
   	 }
   	 .classify-box .rule-wrap-new{
	    box-sizing: border-box;
	    -moz-box-sizing: border-box;
	    -webkit-box-sizing: border-box;
	    color: #999;
	    left: 210px;
	    width: 300px;
	   	padding-top: 2px;
   	    margin-left: 620px;
	}
	.classify-box .rule-wrap-new p{
	    color: #999;
	    margin-top: 0;
	    font-size: 12px;
	    line-height: 18px;
	}
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
<jsp:include page="/WEB-INF/jsp/menu/menu_newpromotion.jsp"></jsp:include>
	 <div class="screen-right">
        <div class="screen-right-cont">
            <div class="screen-right-title">
                <h3 style="border-left-color: #fa6900;">
                    <i style="color:#000;"> 充值卡</i>
                    <c:if test="${rechargeCardService == 0}">
	                    <em class="iconfont normal close">&#xe604;</em>
	                    <span class="i close">已禁用</span>
                    </c:if>
                    <c:if test="${rechargeCardService == 1}">
                        <em class="iconfont normal open">&#xe603;</em>
	                    <span class="i open">已启用</span>
                    </c:if>
                </h3>
            </div>
            <p class="prompt-font" style="line-height: 52px;font-size: 14px;padding-left: 20px;">说明：开启该功能后，您可以创建充值卡，发放给学员。学员可向账号中充值，用余额购买网校的产品。</p>
        </div>
    </div>
</div>
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript">
$selectSubMenu('org_service');
$selectSubMenus('recharge-card');
</script>
<script src="<%=rootPath%>/javascripts/rechargeCard/rechargeCard-openflag.js"></script>
</body>
</html>