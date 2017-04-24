<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css" />
<link href="<%=rootPath  %>/plugins/select2/select2_metro.css" rel="stylesheet" type="text/css"  />
<title>批量报名</title>
<style type="text/css">
.ico{font-size:5px;color:red;} 
</style>
</head>
<body>
<input type="hidden" value="${classpackage.id }" id="packageId"/>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap student">
	<div class="nopadding">
	</div>
</div>
<div id="attributes"><input type="hidden" id="student" value='${student}'/><input type="hidden" id="_mobile" value=''/></div>
		<div class="u-wrap student clear over contentWindow">
		<div class="body clear curr" style="width:400%; left: -100%">
	<div class="mainbackground fl step2" style="width:25%;">
		<div class="heading">
			<h2 class="h5">选择课程</h2>
			<div class="user-infos">
				<span class="user_name"></span> <span class="user_mobile"></span>
			</div>
			<span class="line"></span>
		</div>
		<div class="main-content payMasterInfo">
			<div class="m-title">
				<h2 class="h6">订单信息</h2>
			</div>
			<ul class="list-infos clear">
				<li>
					<p class='c'>
						<span class="c-title">分类</span> <span class="c-content">
							<span>${classpackage.categoryName }</span>
							<select name="" class="category" disabled="disabled" style="display: none;">
							<option value="${classpackage.categoryCode }">${classpackage.categoryName }</option>
						</select>
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">课程包</span> <span class="c-content">
							<span>${classpackage.name }</span>
							<select name="" class="package" disabled="disabled" style="display: none;">
							<option value="${classpackage.id }">${classpackage.name }</option>
						</select>
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">阶段</span> <span class="c-content">
							<select name="" class="packageStage" >
								<option>全部</option>
							</select>
						</span>
					</p>
				</li>
			</ul>
		</div>
				<div class="main-content">
			<div class="m-title">
				<h2 class="h6">应缴费用</h2>
			</div>
			<ul class="list-infos clear">
				<li>
					<p class='c'>
						<span class="c-title">培训费用</span> <span class="c-content trainingFee">${classpackage.realPrice }</span>
					</p>
				</li>
				<li class="agentFeeInput none">
					<p class='c'>
						<span class="c-title">代报考</span> <span class="c-content"> <input type="text" class="agentFee" value="">
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">订单总价</span> <span class="c-content totalAmount">${classpackage.realPrice }</span>
					</p>
				</li>
			</ul>
		</div>
		<div class="main-content payInfo">
			<div class="m-title">
				<h2 class="h6">缴费</h2>
				<span class="more">
						
                       <b style="color:red;">欠缴</b>
                       <b class="needPay" style="color:red;"></b>
               </span>
			</div>
			<div class="pay-list clear">
				<div class="pay-list-left">
					<p>本期</p>
				</div>
				<div class="pay-list-right">
					<p class="c">
						<span class="p-title">POS：</span> <span class="p-content">￥
							<input id="pos" class="money" type="text" placeholder="输入金额">
						</span>
					</p>
					<p class="c">
						<span class="p-title">现金：</span> <span class="p-content">￥
							<input id="cash" class="money" type="text" placeholder="输入金额">
						</span>
					</p>
					<p class="c">
						<span class="p-title">支票：</span> <span class="p-content">￥
							<input id="cheque" class="money" type="text" placeholder="输入金额">
						</span>
					</p>
					<p class="c">
						<span class="p-title">转账：</span> <span class="p-content">￥
							<input id="remit" class="money" type="text" placeholder="输入金额">
						</span>
					</p>
				</div>
			</div>
			<!-- 这里是分期显示的地方 -->
			<div class="stageInfo"></div>
		</div>
		<div class="main-content">
				<div class="m-content">
				<p class="c text-center">
					<span class="c-title">&nbsp;</span> <span class="c-content">
						<a href="javascript:;" class="btn btn-sm btn-default cancle">取消</a> <a
						href="javascript:;" class="btn btn-sm btn-primary nextstep">报名</a>
					</span>
				</p>
			</div>
		</div>
	</div>

</div>
</div>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/class/cousePackage/signUpMany.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/select2/select2.min.js"></script>
</body>
</html>

