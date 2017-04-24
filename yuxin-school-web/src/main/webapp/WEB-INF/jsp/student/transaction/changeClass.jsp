<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>转班</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css" />
    <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('student_manage');
    		selectType(1);
    	})
    </script>
    
</head>
<body>
<input type="hidden" value="${lable }" id="lableTypes"/>
<c:choose>
	<c:when test="${lable=='simpleType' }">
		<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
<jsp:include page="top.jsp"></jsp:include>
<%-- <div class="u-wrap student">
   <div class="mainbackground">
			<div class="main-content nospace" id="topMessage">
				<div class="m-b-m">
					<div class="m-b-p clear">
						<div class="m-b-p-left">
							<p class="h c">
								<span class="p-title">学科</span> <span class="p-content">${payMaster.itemOneName }</span>
							</p>
							<p class="h c">
								<span class="p-title">学费</span> <span class="p-content">${payMaster.totalAmount }</span>
							</p>
							<p class="h c">
								<span class="p-title">报名时间</span> <span class="p-content"><fmt:formatDate
										value="${payMaster.applyTime }"
										timeStyle="yyyy-MM-dd hh:mm:ss" /></span>
							</p>
							<p class="h c">
								<span class="p-title">学科小类</span> <span class="p-content">${payMaster.itemSecondName }</span>
							</p>
							<p class="h c">
								<span class="p-title">已缴</span> <span class="p-content">${payMaster.hasPay }</span>
							</p>
							<p class="h c">
								<span class="p-title">来源</span> <span class="p-content">${wx:dictCode2Name(payMaster.applyChannelCode)}</span>
							</p>
							<p class="h c">
								<span class="p-title">班型</span> <span class="p-content">${payMaster.classTypeName }</span>
							</p>
							<p class="h c important">
								<span class="p-title">欠缴</span> <span class="p-content">${payMaster.totalAmount-payMaster.hasPay }</span>
							</p>
						</div>
						<div class="m-b-p-right">
							<div class="btns">
								<a href="javascript:;" id="1" class="m-btn">退费</a> <a
									href="javascript:;" id="2" class="m-btn m-active">转班</a> <a
									href="javascript:;" id="3" class="m-btn m-mb">转人</a> <a
									href="javascript:;" id="4" class="m-btn m-mb">详情</a> <a
									href="javascript:;" id="5" class="m-btn m-mb change">更换班号</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</div> --%>
<div id="attributes">
<input type="hidden" id="student" value='${student}'/>
<input type="hidden" id="payMaster" value='${payMaster}'/>
<input type="hidden" id="payMasterId" value='${payMaster.id}'/>
</div>
		<div class="u-wrap student clear over contentWindow">
		<div class="body clear curr" style="width:200%; left: 0">

	<div class="mainbackground fl mystep1" style="width:50%;">
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
						<span class="c-title">学科</span> <span class="c-content">
							<select name="" class="itemOne">
							<option value="null">请选择</option>
						</select>
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">学科小类</span> <span class="c-content">
							<select name="" class="itemSecond">
							<option value="null">请选择</option>
						</select>
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">学习课程</span> <span class="c-content">
							<select name="" class="classType">
							<option value="null">请选择</option>
						</select>
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">总 课 时</span> <span class="c-content totalClassHour"></span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">授课类型</span> <span class="c-content resouceType"></span>
					</p>
				</li>

			<shiro:hasPermission name="student_agent">
				<li>
					<p class="c">
						<span class="c-title">是否代报考</span> <i class="iconfont open isAgent" style="font-size:20px;margin:0 10px;">&#xe642;</i><span>否</span>
					</p>
				</li>
			</shiro:hasPermission>

			</ul>
		</div>
		<div class="main-content materialsInfo none">
			<div class="m-title">
				<h2 class="h6">代报考</h2>
			</div>
			<div class="m-diy space">
				<p class="m-tools">
					<span>是否代报考 <i id="dbk" class="iconfont">&#xe609;</i></span> <span>资料是否齐全
						<i id="zlqq" class="iconfont">&#xe609;</i>
					</span>
				</p>
				<div class="m-content">
					<p class="c">
						<span class="c-title" style="width:100px;">学员计划考期</span> 
						<span class="c-content">
							<!-- <select name="" class="term">
							<option value="">请选择</option>
							</select> -->
							<select class="year">
								<option value="2015">2015</option>
								<option value="2016">2016</option>
								<option value="2017">2017</option>
								<option value="2018">2018</option>
								<option value="2019">2019</option>
								<option value="2020">2020</option>
								<option value="2021">2021</option>
								<option value="2022">2022</option>
								<option value="2023">2023</option>
								<option value="2024">2024</option>
								<option value="2025">2025</option>
							</select>
							<select class="month">
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
								<option value="04">04</option>
								<option value="05">05</option>
								<option value="06">06</option>
								<option value="07">07</option>
								<option value="08">08</option>
								<option value="09">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
							
						</span>
					</p>
				</div>
				<div class="m-content materials" >
					<div class="m-c-top clear">
						<p class="m-c-title">资料交接</p>
						<div class="m-c-c">
							<p class="m-c-c-t" id="materials">
								<span class="m-c-c-tt">已交资料</span> <span class="m-c-c-c">
									<em>一寸<i code="EM_INCH1" txt="一寸">0</i></em> <em>二寸<i code="EM_INCH2" txt="二寸">0</i></em> <em>身份证<i code="EM_IDCARD" txt="身份证">0</i></em> <em>学历证<i code="EM_EDUCATION" txt="学历证">0</i></em>
									<em >学位证<i code="EM_DEGREE" txt="学位证">0</i></em> <em>证明材料<i code="EM_PROVE" txt="证明材料">0</i></em> <em>其他<i id="EM_OTHER" txt="其他">0</i></em>
								</span>
							</p>
						<!-- 	<p class="m-c-c-t" id="unmaterials">
								<span class="m-c-c-tt">未交资料</span> <span class="m-c-c-c">
									<em>一寸<i code="EM_INCH1" txt="一寸">0</i></em> <em>二寸<i code="EM_INCH2" txt="二寸">0</i></em> <em>身份证<i code="EM_IDCARD" txt="身份证">0</i></em> <em>学历证<i code="EM_EDUCATION" txt="学历证">0</i></em>
									<em>学位证<i code="EM_DEGREE" txt="学位证">0</i></em> <em>证明材料<i code="EM_PROVE" txt="证明材料">0</i></em> <em>其他<i id="EM_OTHER" txt="其他">0</i></em>
								</span>
							</p> -->
						</div>
					</div>
					<div class="m-c-bottom clear">
						<p class="m-c-title">考务备注</p>
						<p class="m-c-c">
							<textarea name="" id="materialMark" placeholder="输入你的备注信息"></textarea>
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="heading">
			<h2 class="h5">缴费</h2>
			<div class="user-infos">
				<span class="user_name"></span> <span class="user_mobile"></span> <span class="user_classType"></span>
			</div>
			<span class="line"></span>
		</div>

		<div class="main-content">
			<div class="m-title">
				<h2 class="h6">新订单应缴费用</h2>
			</div>
			<ul class="list-infos clear">
				<li>
					<p class='c'>
						<span class="c-title">培训费用</span> <span class="c-content trainingFee"></span>
					</p>
				</li>
				<li>
					<p class='c none'>
						<span class="c-title">代报考</span> <span class="c-content"> <input
							type="text" class="agentFee" value="">
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">订单总价</span> <span class="c-content totalAmount"></span>
					</p>
				</li>
			</ul>
		</div>
		<div class="main-content hasPayed">
			<div class="m-title">
				<h2 class="h6">原订单已缴费用</h2>
			</div>
			<ul class="list-infos clear">
				<li>
					<p class='c'>
						<span class="c-title">原订单总价</span> <span class="c-content ototalAmount"></span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">原订单已缴</span> <span class="c-content opayed"> </span>
					</p>
				</li>
				<li class="full-long">
					<p class='c'>
						<span>POS:</span><span class="opos"></span>
						<span>现金:</span><span class="ocash"></span>
						<span>支票:</span><span class="ocheck"></span>
						<span>转账:</span><span class="orimit"></span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">已使用</span> <input type="text" class="c-content used" /> </span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">剩余</span> <span class="c-content last"> </span>
					</p>
				</li>
			</ul>
		</div>
		<div class="main-content payInfo">
			<div class="m-title">
				<h2 class="h6">补缴</h2>
				<span class="more">
                       <b style="color:red;">欠缴</b>
                       <b class="needPay" style="color:red;"></b>
               </span>
			</div>
			<div class="pay-list clear">
				<div class="pay-order">
					<span><input type="radio" name="payType" value="ALL" checked> 全款</span> 
					<shiro:hasPermission name="student_add_fee">
					<span><input type="radio" name="payType" value="STAGE"> 分期 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="stageNum none"> 共分 <input id="val_total_stage" class="sm" type="text" value="">期</span></span>
					</shiro:hasPermission>
				</div>
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
		<div class="main-content payBack none">
			<div class="m-title">
				<h2 class="h6">退费</h2>
				<span class="more">
                       <b style="color:red;">需退</b>
                       <b class="needBack" style="color:red;"></b>
               </span>
			</div>
			<div class="pay-list clear">
				<div class="pay-order">
					<p class="c">
						<span class="p-title">POS</span>
						<span class="p-content opos"></span>
						<span class="p-title">退费金额</span>
						<span class="p-content"><input class="bpos money" type="text" value="" placeholder="输入金额"/></span>
					</p>
					<p class="c">
						<span class="p-title">现金</span>
						<span class="p-content ocash"></span>
						<span class="p-title">退费金额</span>
						<span class="p-content"><input class="bcash money" type="text" value="" placeholder="输入金额"/></span>
					</p>
					<p class="c">
						<span class="p-title">支票</span>
						<span class="p-content ocheck"></span>
						<span class="p-title">退费金额</span>
						<span class="p-content"><input class="bcheck money" type="text" value="" placeholder="输入金额"/></span>
					</p>
					<p class="c">
						<span class="p-title">转账</span>
						<span class="p-content orimit"></span>
						<span class="p-title">退费金额</span>
						<span class="p-content"><input class="bremit money" type="text" value="" placeholder="输入金额"/></span>
					</p>
				</div>
			</div>
		</div>
		<div class="main-content">
			<div class="m-content">
				<p class="c text-center">
					<span class="c-title">&nbsp;</span> <span class="c-content">
						<!-- <a href="javascript:;" class="btn btn-sm btn-default prevstep">上一步</a>  -->
						<a href="javascript:;" class="btn btn-sm btn-default cancle">取消</a> <a
						href="javascript:;" class="btn btn-sm btn-primary nextstep">确定</a>
					</span>
				</p>
			</div>
		</div>
	</div>
</div>
</div>
<input type="hidden"  value="" id="checkIsOk"/>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student/changeClass.js"></script>
</body>
</html>