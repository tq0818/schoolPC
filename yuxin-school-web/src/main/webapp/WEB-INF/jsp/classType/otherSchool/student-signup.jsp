<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css" />
<link href="<%=rootPath  %>/plugins/select2/select2_metro.css" rel="stylesheet" type="text/css"  />
<title>线下报名</title>
<style type="text/css">
.ico{font-size:5px;color:red;} 
</style>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap student">
	<div class="nopadding">
		<div class="steps">
			<div class="line"></div>
			<ul class="clear">
				<li class="step3 s1 hover"><i>1</i> <em>学员信息</em></li>
				<li class="step3 s2"><i>2</i> <em>选择收费</em></li>
			</ul>
		</div>
	</div>
</div>
<input type="hidden" value="${classType.id }" id="stuClassTypeId"/>
<input type="hidden" value="${lable }" id="lable"/>
<div id="attributes"><input type="hidden" id="student" value='${student}'/><input type="hidden" id="_mobile" value='${student.mobile}'/><input type="hidden" id="_userName" value='${student.username}'/></div>
		<div class="u-wrap student clear over contentWindow">
		<div class="body clear curr" style="width:400%; left: -100%">
		<div class="mainbackground fl step0" style="width:25%;">
		<form id="studentForm" action="">
				<div class="heading">
					<h2 class="h5">学员</h2>
				<!-- 	<div class="search">
						<span class="tip" style="color:red;font-size:1.2rem;padding-right:15px;"></span><input type="text" class="input-ctrl" placeholder="手机号"> <input type="button" class="btn btn-sm" value="搜索">
					</div> -->
					<span class="line"></span>
				</div>
				<div class="mark-more">
					<div class="main-content">
						<div class="m-title">
							<h2 class="h6">基本信息</h2>
							<span class="more"> <a href="javascript:;" class="e saveStudent">保存</a>
							</span>
						</div>
						<ul class="list-infos clear">
						    <c:if test="${registConfig.mobileFlag==1 }">
							    <li>
									<p class='c'>
										<span class="c-title">手机号<i class="iconfont ico">&#xe605;</i></span> <span class="c-content">
											<input type="text" id="stuMobiles" class="mobile" maxlength="11" name="mobile" value=""/>
										</span>
									</p>
								</li>
							</c:if>
							<c:if test="${registConfig.usernameFlag==1 }">
							 <li>
								<p class='c'>
									<span class="c-title">用户名<i class="iconfont ico">&#xe605;</i></span> <span class="c-content">
										<input type="text" id="stuUserNames" class="username" name="username" value=""/>
									</span>
								</p>
							</li>
							</c:if>
							<li>
								<p class='c'>
									<span class="c-title">姓名<i class="iconfont ico">&#xe605;</i></span> <span class="c-content"><input class="name" name="name" type="text" value=""></span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">性别</span> <span class="c-content">
										<input type="radio" class="sex" name="sex" value="MALE" checked="checked"> 男 <input type="radio" class="sex" name="sex" value="FEMALE"> 女
									</span>
								</p>
							</li>
								<li>
								<p class='c'>
									<span class="c-title">证件类型</span> <span class="c-content">
										<select name="identityTypeCode" class="identityTypeCode">
											<option value="1">身份证</option>
											<option value="2">护照</option>
											<option value="3">其他</option>
									</select>
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">证件号码</span> <span class="c-content">
										<input class="identityId" name="identityId" type="text" value="">
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">出生日期</span> <span class="c-content">
										<input type="text" class="birthday date-picker" onchange="countAge(this)" name="birthday" value="" >
									</span>
								</p>
							</li>
							<li style="display: none;">
								<p class='c'>
									<span class="c-title">年龄</span> <span class="c-content">
										<input type="text" id="stu_Age" class="age" name="age" value="">
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">户口所在地</span> <span class="c-content">
										<input type="text" class="hkAddress" name="hkAddress" value="">
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">最高学历</span> <span class="c-content">
										<select class="educationCode" name="educationCode" >
										</select>
									</span>
								</p>
							</li>
							<c:if test="${sgOpen==1 }">
								<li>
									<p class='c'>
										<span class="c-title">分组</span> <span class="c-content">
											<select id="studentG1" onchange="javaScript:selectGroup2(this,'');"></select>
											<select  id="studentG2"></select>
										</span>
									</p>
								</li>
							</c:if>
						</ul>
					</div>
					<div class="main-content">
						<div class="m-title">
							<h2 class="h6">联系信息</h2>
						</div>
						<ul class="list-infos clear">
							<li>
								<p class='c'>
									<span class="c-title">家庭电话</span> <span class="c-content">
										<input type="text" class="homePhone" name="homePhone" value="">
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">办公电话</span> <span class="c-content">
										<input type="text" class="officePhone" name="officePhone" value="">
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">紧急联系人</span> <span class="c-content">
										<input type="text" class="emergencyContact" name="emergencyContact" value="">
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">紧急联系电话</span> <span class="c-content">
										<input type="text" class="emergencyPhone" name="emergencyPhone" value="">
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">邮箱</span> <span class="c-content">
										<input type="text" class="email" name="email" value="">
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">QQ号</span> <span class="c-content">
										<input type="text" class="qq" name="qq" value="">
									</span>
								</p>
							</li>
							<li>
								<p class='c'>
									<span class="c-title">微信</span> <span class="c-content">
										<input type="text" class="weixinId" name="weixinId" value="">
									</span>
								</p>
							</li>
						</ul>
					</div>
					<div class="m-bo text-center">
						<a href="javascript:;" class="btn btn-sm btn-default cancle">取消</a> <a
							href="javascript:void(0);" id="saveStuInfo" class="btn btn-sm btn-primary nextstep">下一步</a>
					</div>
				</div>
				</form>
			</div>
			<div class="mainbackground fl step1" style="width:25%;">
				<div class="heading">
					<h2 class="h5">报名</h2>
					<span class="line"></span>
				</div>
				<div class="main-content">
					<div class="m-title">
						<h2 class="h6">基本信息</h2>
						<span class="more">
                        <a href="javascript:void(0)" class="edit" id="edit_base">编辑</a>
                    </span>
					</div>
					<ul class="list-infos clear">
						<li>
							<p class='c'>
								<span class="c-title">用户名</span> <span class="c-content username">${student.username}</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">姓名</span> <span class="c-content name">${student.name}</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">性别</span> <span class="c-content sex" >${student.sex}</span>
							</p>
						</li>
							<li>
							<p class='c'>
								<span class="c-title">证件类型</span> <span class="c-content identityTypeCode" >${student.identityTypeCode }</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">证件号码</span> <span class="c-content identityId">${student.identityId}</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">出生日期</span> <span class="c-content birthday" ><fmt:formatDate value="${student.birthday}" pattern="yyyy年MM月dd日"/></span>
							</p>
						</li>
						<li style="display: none;">
							<p class='c'>
								<span class="c-title">年龄</span> <span class="c-content age">${student.age}</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">户口所在地</span> <span class="c-content hkAddress" >${student.hkAddress}</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">最高学历</span> <span class="c-content educationCode" >${student.educationCode}</span>
							</p>
						</li>
						<c:if test="${sgOpen==1 }">
	                   <%-- <c:if test="${!empty G1Name }"> --%> 
	                    <li>
	                        <p class='c'>
	                            <span class="c-title">一级分组</span>
	                            <span class="c-content cc G1Name">${G1Name}</span>
	                        </p>
	                    </li>
	                   <%-- </c:if> --%>
                   
	                   <%-- <c:if test="${!empty G2Name }"> --%> 
	                    <li>
	                        <p class='c'>
	                            <span class="c-title">二级分组</span>
	                            <span class="c-content cc G2Name">${G2Name}</span>
	                        </p>
	                    </li>
	                   <%-- </c:if> --%> 
	                 </c:if>
					</ul>
				</div>
				<div class="main-content">
					<div class="m-title">
						<h2 class="h6">联系信息</h2>
					</div>
					<ul class="list-infos clear">
						<li>
							<p class='c'>
								<span class="c-title">手机号</span> <span class="c-content"><em class="mobile">${student.mobile }</em></span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">家庭电话</span> <span class="c-content homePhone">${student.homePhone }</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">办公电话</span> <span class="c-content officePhone" >${student.officePhone }</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">紧急联系人</span> <span class="c-content emergencyContact" >${student.emergencyContact}</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">紧急联系电话</span> <span class="c-content emergencyPhone">${student.emergencyPhone}</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">邮箱</span> <span class="c-content email">${student.email }</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">QQ号</span> <span class="c-content qq">${student.qq }</span>
							</p>
						</li>
						<li>
							<p class='c'>
								<span class="c-title">微信</span> <span class="c-content weixinId" >${student.weixinId }</span>
							</p>
						</li>
					</ul>
				</div>
				<div class="m-bo text-center">
					<a href="javascript:;" class="btn btn-sm btn-default cancle">取消</a> <a
						href="javascript:;" class="btn btn-sm btn-primary nextstep">下一步</a>
				</div>
			</div>

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
							<option value="${classType.id }" realPrice="${classType.realPrice }">${classType.name }</option>
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
				<div class="pay-order">
					<span><input type="radio" name="payType" value="ALL" checked> 全款</span> 
					 <shiro:hasPermission name="student_add_fee">  
					<span><input type="radio" name="payType" value="STAGE"> 分期 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="stageNum none"> 共分 <input maxlength="2" id="val_total_stage" class="sm" type="text" value="">期</span></span>
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
		<div class="main-content">
				<div class="m-content">
				<p class="c text-center">
					<span class="c-title">&nbsp;</span> <span class="c-content">
						<a href="javascript:;" class="btn btn-sm btn-default prevstep">上一步</a> 
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
<script type="text/javascript" src="<%=rootPath%>/javascripts/studentComm.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/class/otherSchool/signUp.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/selectStudentGroup.js"></script>
</body>
</html>

