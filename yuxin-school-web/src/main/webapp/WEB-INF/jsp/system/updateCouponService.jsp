<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>优惠码管理</title> 
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

<div class="u-wrap  manage-screen clear">
<jsp:include page="/WEB-INF/jsp/menu/menu_newpromotion.jsp"></jsp:include>
<script>
	$(function(){
		debugger;
		$selectSubMenus('promotion');
	})
	</script>
    <div class="screen-right">
        <div class="screen-right-cont">
        	<input type="hidden" value="${cfs.status}" id="CfsStatus"/>
            <div class="screen-right-title">
                <h3 style="border-left-color: #fa6900;">
                    <i style="color:#000;"> 优惠码</i>
                    <c:if test="${cfs.status == 0}">
	                    <em class="iconfont normal close">&#xe604;</em>
	                    <span class="i close">已禁用</span>
                    </c:if>
                    <c:if test="${cfs.status == 1}">
                        <em class="iconfont normal open">&#xe603;</em>
	                    <span class="i open">已启用</span>
                    </c:if>
                </h3>
            </div>
            <p class="prompt-font" style="line-height: 52px;font-size: 14px;padding-left: 20px;">说明：开启该功能后，机构可以自行创建生成优惠码向用户、学员发放，获得优惠码后，可在消费时使用。</p>
            <input type="hidden" value="${ccc.useWay}" id="useWay"/>
            <div class="coupon-code">
                <div class="classify-box" >
                    <div class="classify" id="isNotDoubleUse">
                        <div>
                            <div class="classify-tit" id="isNotDoubleUse-tit">不可叠加使用</div>
                        </div>
                        <img src="<%=rootPath%>/images/choosed-1_03.png" alt="" id="isNotDoubleUse-img">
                    </div>
                    <div class="classify " id="isDoubleUse">
                        <div>
                            <div class="classify-tit" id="isDoubleUse-tit">可叠加使用</div>
<!--                             <p class="rule-bottom">叠加规则：当打折和抵现两种优惠方式同时使用时，先打折，再抵现。</p> -->
                        </div>
                        <img src="<%=rootPath%>/images/choosed-1_03.png" alt="" id="isDoubleUse-img">
                    </div>
                    <div class="rule-wrap-new">
                                <p>叠加规则：</p>
                                <p>1、同类型的优惠不可叠加使用。例如：学员拥有2张打折券，在购买课程时不能同时使用；</p>
                                <p>2、当满减、打折和抵现三种优惠方式同时使用时，先满减，再打折，再抵现。</p>
                   </div>
                </div>
                  <div style="margin-left:340px;margin-top:220px;">
			 	<a href="javascript:;" class="btn  btn-primary save-useway " style="display: none;">保存</a>
   				<a href="javascript:;" class="btn  btn-default esc-useway" style="display: none;">取消</a>
   			</div>
            </div>
          
        </div>
    </div>
</div>




<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script src="<%=rootPath%>/javascripts/common/common.js"></script>
<script src="<%=rootPath%>/javascripts/updateCouponService.js"></script>
<script src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script>
$(function(){
	$('#yaoqingshezhi').on('click',function(){
		$(this).siblings('li').removeClass('active');
		$(this).addClass('active');
		window.location.href=rootPath+"/companyInvitConfig/toCompanyInviteCofig";
	})
	$('#youhuiguanli').on('click',function(){
		$(this).siblings('li').removeClass('active');
		$(this).addClass('active');
		window.location.href=rootPath+"/company/setCouponService";
	})
})
</script>
</body>

</html>