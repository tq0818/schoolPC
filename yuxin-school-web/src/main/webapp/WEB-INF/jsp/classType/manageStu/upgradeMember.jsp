<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css" />
<link href="<%=rootPath  %>/plugins/select2/select2_metro.css" rel="stylesheet" type="text/css"  />
<title>升级会员</title>
<style type="text/css">
.ico{font-size:5px;color:red;} 
</style>
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
<!-- usersFrontId前台用户的编号 -->
<input type="hidden" value="${usersFrontId}" id="usersFrontId"/>
<input type="hidden"  id="needPay1Hidden"/>
<input type="hidden"  id="originPriceHidden"/>
<input type="hidden" value="${classType.id }" id="stuClassTypeId"/>
<input type="hidden" value="${lable }" id="lable"/>
<div id="attributes"><input type="hidden" id="student" value='${student}'/><input type="hidden" id="_mobile" value='${student.mobile}'/></div>
		<div class="u-wrap student clear over contentWindow">
		<div class="body clear curr" style="width:400%; left: -100%">
		<div class="mainbackground fl step0" style="width:25%;">
		<form id="studentForm" action="">
				</form>
			</div>

	<div class="mainbackground fl step2" style="width:25%;margin-bottom:40px;">
		<div class="heading">
			<h2 class="h5 openWay">会员升级</h2>
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
						<span class="c-title">会员等级</span>
						<span class="c-content">
							<select name="" class="selectVips" id="selectVips"  >
									<option value='-2'>请选择</option>
							</select>
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title becomeMemberData" data-becomemember="${companyMemberConfig.becomeMember}">会员有效期</span>
						<span class="c-content">
						<select name="" class="selectVipDetail" id="selectVipDetail"  >
							<option value='-1'>请选择</option>
						</select>
						</span>
					</p>
				</li>				
			</ul>
		</div>
		<div class="main-content payInfo">
			<div class="m-title">
				<h2 class="h6">缴费</h2>
				<span class="more">
						<input type="hidden" id="needPay1" value="0" />
                       <b style="color:red;" class="needPay1Title">欠缴：</b>
                       <b class="needPay1" style="color:red;">0</b>
                        <b style="color:red;" class="leaveMoneyTitle">剩余：</b>
                       <b class="leaveMoney" style="color:red;">0</b>
               </span>
			</div>
			<div class="pay-list clear">
				<div class="pay-order">
					<span><input type="radio" name="payType" value="ALL" checked /> 全款</span> 
				</div>
				<div class="pay-list-left">
					<p>本期</p>
				</div>
				<div class="pay-list-right">
					<p class="c">
						<span class="p-title">POS：</span> <span class="p-content">￥
							<input id="pos" class="money" type="text" placeholder="输入金额" />
						</span>
					</p>
					<p class="c">
						<span class="p-title">现金：</span> <span class="p-content">￥
							<input id="cash" class="money" type="text" placeholder="输入金额" />
						</span>
					</p>
					<p class="c">
						<span class="p-title">支票：</span> <span class="p-content">￥
							<input id="cheque" class="money" type="text" placeholder="输入金额" />
						</span>
					</p>
					<p class="c">
						<span class="p-title">转账：</span> <span class="p-content">￥
							<input id="remit" class="money" type="text" placeholder="输入金额" />
						</span>
					</p>
				</div>
			</div>
		</div>
		
		<div class="main-content">
				<div class="m-content">
				<p class="c text-center">
					<span class="c-title">&nbsp;</span> <span class="c-content">
						<a href="javascript:;" class="btn btn-sm btn-default cancle">取消</a> <a
						href="javascript:;" class="btn btn-sm btn-primary save">确定</a>
					</span>
				</p>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student/upgradeMember.js"></script>
<script>
$(document).ready(function(){
	$selectMenu("company_member_vip");
	$("#dateList").on("click","a.btn",function(){
		var status=$(this).hasClass("btn-primary");
		if(!status){
			$(this).addClass("btn-primary").siblings("a").removeClass("btn-primary");
		}
		$("#timeMark").val($(this).attr("mark"));
	});
	
})
</script>
</body>
</html>

