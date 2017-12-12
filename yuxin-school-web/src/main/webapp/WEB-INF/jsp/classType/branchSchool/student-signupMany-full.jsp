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
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap student">
	<div class="nopadding">
	<!-- 
		<div class="steps">
			<div class="line"></div>
		
			<ul class="clear">
				<li class="step3 s1 active"><i>1</i> <em>选择学员</em></li>
				<li class="step3 s2 hover"><i>2</i> <em>选择收费</em></li>
			</ul>
				
		</div>
	 -->	
	</div>
</div>
<input type="hidden" value="${classType.id }" id="stuClassTypeId"/>
<input type="hidden" value="${lable }" id="lable"/>
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
						<span class="c-title">学科</span> <span class="c-content">
						<span>${classType.itemOneName }</span>
							<select name="" class="itemOne" disabled="disabled" style="display: none;">
							<option value="${classType.itemOneId }">${classType.itemOneName }</option>
						</select>
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">学科小类</span> <span class="c-content">
						<span>${classType.itemSecondName }</span>
							<select name="" class="itemSecond" disabled="disabled" style="display: none;">
							<option value="${classType.itemSecondId }">${classType.itemSecondName }</option>
						</select>
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">学习课程</span> <span class="c-content">
						<span>${classType.name }</span>
							<select name="" class="classType" style="font-size:1.4rem;display: none;" disabled="disabled">
							<option value="${classType.id }" realPrice="${classType.publicPrice }">${classType.name }</option>
						</select>
						</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">总 课 时</span> <span class="c-content totalClassHour">${classType.totalClass }</span>
					</p>
				</li>
				<li>
					<p class='c'>
						<span class="c-title">授课类型</span> <span class="c-content resouceType"></span>
					</p>
				</li>
		<!--  	<shiro:hasPermission name="student_agent">
				<li>
					<p class="c">
						<span class="c-title">是否代报考</span> <i class="iconfont open isAgent" style="font-size:20px;margin:0 10px;">&#xe642;</i><span>否</span>
					</p>
				</li>
			</shiro:hasPermission> -->
			</ul>
		</div>
		<div class="main-content classInfo">
			<div class="m-title">
				<h2 class="h6">课程</h2>
			</div>
			<ul class="list-infos clear">
				<li class="long clear none">
					<p class='c'>
						<span class="c-title" style="width:100px;">优先上课校区</span> <span class="c-content schools">
							<!-- <a href="javascript:;" class="btn btn-sm btn-success">各地分校</a> 
							<a href="javascript:;" class="btn btn-sm btn-default">各地分校</a> -->
						</span>
					</p>
				</li>
			</ul>
			<div class="c-list modules">
		
			</div>

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
							<!-- <p class="m-c-c-t" id="unmaterials">
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
		
		<div class="main-content">
			<div class="m-title">
				<h2 class="h6">应缴费用</h2>
			</div>
			<ul class="list-infos clear">
				<li>
					<p class='c'>
						<span class="c-title">培训费用</span> <span class="c-content trainingFee"></span>
					</p>
				</li>
				<li class="agentFeeInput none">
					<p class='c'>
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
		
		<div class="main-content payInfo">
			<div class="m-title">
				<h2 class="h6">缴费</h2>
				<span class="more">
						
                       <b style="color:red;">欠缴</b>
                       <b class="needPay" style="color:red;"></b>
               </span>
			</div>
			<div class="pay-list clear">
			<!-- 
				<div class="pay-order">
					<span><input type="radio" name="payType" value="ALL" checked> 全款</span> 
					 <shiro:hasPermission name="student_add_fee">  
					<span><input type="radio" name="payType" value="STAGE"> 分期 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="stageNum none"> 共分 <input id="val_total_stage" class="sm" type="text" value="">期</span></span>
					</shiro:hasPermission>
				</div>
			 -->	
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
			<div class="m-bo text-center">
				<p class="c">
					<span class="p-title">&nbsp;</span> <span class="p-content">
					<!-- 	<a href="javascript:;" class="btn btn-sm btn-default prevstep">上一步</a>  -->
						<a href="javascript:;" class="btn btn-sm btn-default cancle">返回</a>
						<a href="javascript:;" class="btn btn-sm btn-primary nextstep">确定</a> 
					</span>
				</p>
			</div>
		</div>
		<!-- 
		<div class="main-content">
				<div class="m-content">
				<p class="c text-center">
					<span class="c-title">&nbsp;</span> <span class="c-content">
						<a href="javascript:;" class="btn btn-sm btn-default prevstep">上一步</a> 
						<a href="javascript:;" class="btn btn-sm btn-default cancle">取消</a> <a
						href="javascript:;" class="btn btn-sm btn-primary nextstep">下一步</a>
					</span>
				</p>
			</div>
		</div>  -->
	</div>
	<!-- 
	<div class="mainbackground fl step3" style="width:25%;">
		<div class="heading">
			<h2 class="h5">缴费</h2>
			<div class="user-infos">
				<span class="user_name"></span> <span class="user_mobile"></span> <span class="user_classType"></span>
			</div>
			<span class="line"></span>
		</div>

		<div class="main-content">
			<div class="m-title">
				<h2 class="h6">应缴费用</h2>
			</div>
			<ul class="list-infos clear">
				<li>
					<p class='c'>
						<span class="c-title">培训费用</span> <span class="c-content trainingFee"></span>
					</p>
				</li>
				<li class="agentFeeInput none">
					<p class='c'>
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
		<div class="main-content payInfo">
			<div class="m-title">
				<h2 class="h6">缴费</h2>
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
			</div>  -->
			<!-- 这里是分期显示的地方 -->
			<!-- 
			<div class="stageInfo"></div>
			<div class="m-bo text-center">
				<p class="c">
					<span class="p-title">&nbsp;</span> <span class="p-content">
						<a href="javascript:;" class="btn btn-sm btn-default prevstep">上一步</a> 
						<a href="javascript:;" class="btn btn-sm btn-default cancle">返回</a>
						<a href="javascript:;" class="btn btn-sm btn-primary nextstep">确定</a> 
					</span>
				</p>
			</div>
		</div>
	</div>  -->
</div>
</div>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/class/branchschool/signUpMany-full.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/select2/select2.min.js"></script>
</body>
</html>

