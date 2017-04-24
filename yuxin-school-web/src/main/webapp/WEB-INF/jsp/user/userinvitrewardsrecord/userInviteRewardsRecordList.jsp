<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html >
<html>

<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>邀请记录</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/system.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/footer.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/manage-screen.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/code.css">
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/popupwin.css">
	<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/coupon.css">
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script>
  	var rootPath = '<%=rootPath%>';
  	$(function(){
	$('#chaxuntongji').addClass('active');
  	})
</script>
</head>


<body class="q_box">
	<jsp:include page="/header.jsp" />
	<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	 <form method="POST" id="exportForm2" >
    
        </form> 
         <div class="u-wrap company manage-screen clear coupon-page" id="zongleftdiv">
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_query.jsp"></jsp:include>
	 	<script>
  			$(function(){
  				$selectSubMenus('invite_config');
  			})
		</script>
		<div class="screen-right">
			<div class="screen-right-cont set-system">
				<div class="search-cont">
					<span class="timer">邀请时间</span> <span class="use-time"> <em
						class="all">全部</em> <em>今天</em> <em>7天</em> <em>当月</em>
					</span>

					<button class="manage-button fr">
						搜索 <span class="manage-button-mask"></span>
					</button>
					<input type="text" class="serch fr" placeholder="邀请人名称/手机号/邀请码"
						id="conentSearch" />
				</div>
				<div id="pageNo1"></div>
				<div class="text">
				
					<span>被邀请注册的总人数：${totalRegNumber}&nbsp;&nbsp;人</span> <span>被邀请消费的总人数：${totalConNumber}&nbsp;&nbsp;人</span>
					<span>发放代金券奖励总金额：<fmt:formatNumber type="number" value="${totalRewardsMoney }" pattern="0.00" maxFractionDigits="2"/>&nbsp;&nbsp;元</span> <span>发放积分奖励总数：${totalRewardsIntegral }&nbsp;&nbsp;积分</span>
					<span  class="a-derive  q-derive"
						style="font-size: 14px; border-radius: 2px;">导出</span>
				</div>
				<div class="user-list">
					<div class="ctr-hei">
						<table class="table table-center" id="tableList">
							<tr>
								<th width="15%">邀请人</th>
								<th width="20%">邀请码</th>
								<th width="13%">邀请注册人数</th>
								<th width="13%">邀请消费人数</th>
								<th width="17%">获得的代金券奖励金额</th>
								<th width="15%">获得的积分奖励</th>
								<th width="13%">操作</th>
							</tr>
						</table>
						<div class="pages pagination"></div>
					</div>
				</div>
			</div>
		</div>
		</div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	
	
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<div class="layer-tips coupon-code-detail">
		<div class="layer-tips-title">
			查看详情 <i class="close iconfont confirm_close">&#xe610;</i>
		</div>
		<h3 class="select">
			<select id="yaoqingsel">
				<option value="volvo">全部邀请</option>
				<option value="saab">直接邀请</option>
				<option value="opel">下级邀请</option>
			</select> <select id='typesel'>
				<option value="volvo">选择奖励行为</option>
				<option value="saab">邀请注册</option>
				<option value="opel">首次消费</option>
			</select>
		</h3>
		<div class="text-two">
			<span>奖励的代金券总金额：<i id="totalMoneyid"><fmt:formatNumber type="number" value="${rewardsCount.totalMoney}" pattern="0.00" maxFractionDigits="2"/>元</i></span> <span>奖励的积分总数：<i id="totalIntegralid">${rewardsCount.totalIntegral}&nbsp;&nbsp;</i></span>
		</div>

		<div class="coupon-use-detail">
					<div class="ctr-alert">
						<table class="table table-center">
							<tr>
								<th width="15%">邀请人账号</th>
								<th width="16%">被邀请人账号</th>
								<th width="16%">奖励行为</th>
								<th width="17%">奖励日期</th>
								<th width="22%">奖励的代金券金额</th>
								<th width="20%">奖励积分</th>
							</tr>
						</table>
						<div class="pages pagination1"></div>
					</div>
				</div>
	</div>
	<div class="layer-tips-bg"></div>
	<jsp:include page="/footer.jsp" />
	<input type="hidden" class="test12" value=""/>
	<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath%>/javascripts/classedit.js"></script>
	<script	src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
	<script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
	<script src="<%=rootPath%>/javascripts/coupon/alert-function.js"></script>
	<script src="<%=rootPath%>/javascripts/coupon/alert-function.js"></script>
	<script src="<%=rootPath%>/javascripts/code.js"></script>
	<script src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script src="<%=rootPath%>/javascripts/rewardsrecord.js"></script>
	<script src="<%=rootPath%>/javascripts/rewardsrecorddetail.js"></script>
	<script src="<%=rootPath%>/javascripts/newcouponmanage.js"></script>
	<script src="<%=rootPath%>/javascripts/common/utils.js"></script>
	
</body>

</html>
