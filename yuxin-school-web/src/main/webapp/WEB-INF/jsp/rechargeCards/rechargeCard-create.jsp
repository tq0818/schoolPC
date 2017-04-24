<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp" %>
    <title>促销</title> 
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/system.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/classedit.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/coupon.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/conpon-alert.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage-screen.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fonts/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
    <style type="text/css">
    li.subentry span {left:82px;}
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap company overflow coupon-page manage-screen">
<jsp:include page="/WEB-INF/jsp/menu/menu_coupon.jsp"></jsp:include>
    <div class="screen-right">
        <div class="screen-right-cont counp-right-cont">
            <div class="screen-right-title">
                <h3> <i> 创建充值卡</i></h3>
            </div>
            <div class="coupon-out-type coupon-online  coupon-online-card">
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont"></i>
                        储值金额
                    </div>
                    <div class="fl">
                        <input type="text" value=""  id="rechargeCard-price" > 元
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont"></i>
                        数量
                    </div>
                    <div class="fl">
                        <input type="text" value=""  id="rechargeCard-num" > 个
                        <p class="l-note">一次最多只能生成100个充值卡</p>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont"></i>
                        有效期限
                    </div>
                    <div class="fl">
                        <input type="text" readonly="readonly" value="" name="startTime" id="rechargeCard-from" class="date-picker from">
                        <em>——</em>
                        <input type="text" readonly="readonly" value="" name="endTime" id="rechargeCard-to" class="date-picker to">

                        <p class="l-note">开始时间不早于当天，结束时间不能早于或等于开始时间</p>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
                        <i class="iconfont"></i>
                        充值卡前缀
                    </div>
                    <div class="fl">
                        <input type="text" value="" class="tikeckts_fornt" id="rechargeCard-prefix" maxlength="4">

                        <p class="l-note">只能是英文字母A-Z，限4个字母，不区分大小写，不可与其他优惠码前缀重复</p>
                    </div>
                </div>
                <div class="coupon-block clear">
                    <div class="t-label fl">
<!--                         <i class="iconfont"></i> -->
                        关联代理机构
                    </div>
                    <div class="fl p-relative relative-coupon">
                   		 <select id="rechargeCard-proxyOrgs"></select>
                    </div>
                </div>

                <div class="bnts-wrap">
                    <button class="manage-btn manage-btn-cancel">取消</button>
                    <button class="manage-btn manage-btn-success save" >保存</button>
                    <button class="manage-btn manage-btn-success export" >保存并导出</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="layer-tips-bg"></div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none;">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none;"></div>


<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/rechargeCard/rechargeCard-create.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>

</body>

</html>