<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
 <script type="text/javascript" src="<%=request.getContextPath()%>/javascripts/selectStudentGroup.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/student/blankStudent.js"></script>
 <!--   <form id="blankForm"> -->
	<input id="payId" name="payMasterId" type="hidden"/>
	<div class="main-content">
		<div class="m-title">
			<h2 class="h6">基本信息</h2>
		</div>
		<ul class="list-infos clear">
			<li>
				<p class='c'>
					<span class="c-title">姓名</span> <span class="c-content"><input
						type="text" name="name"></span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">性别</span> <span class="c-content"> <input
						type="radio" name="sex" value="MALE" checked> 男 <input
						type="radio" name="sex" value="FEMALE"> 女
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">出生日期</span> <span class="c-content"> <input
						type="text" class="date-picker" name="birthday">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">年龄</span> <span class="c-content"> <input
						type="text" name="age">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">户口所在地</span> <span class="c-content">
						<input type="text" name="hkAddress">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">最高学历</span> <span class="c-content"> <select
						name="educationCode">
							
							<option value="">-请-选-择-</option>
							<option value="UNDER_JUNIOR">大专以下</option>
							<option value="JUNIOR">大专</option>
							<option value="BECHELOR">本科</option>
							<option value="POSTGRADUATE">研究生</option>
							<option value="DOCTOR">博士生及以上</option>
					</select>
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">证件类型</span> <span class="c-content"> 
					<select name="identityTypeCode" class="identityTypeCode" >
                        <option value="ID_IDCARD">-请-选-择-</option>
                        <option value="ID_IDCARD">身份证</option>
                        <option value="ID_PASSPORT">护照</option>
                        <option value="ID_HMP">港澳通行证</option>
                        <option value="ID_TCP">台胞证</option>
                        <option value="ID_OFFICERS">军官证</option>
                        <option value="ID_SERGEANTS">士官证</option>
                        <option value="ID_UNCONFM_ID">其他</option>
                       </select>
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">证件号码</span> <span class="c-content"> <input
						type="text" name="identityId" class="identityId">
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
			<li id="username_li">
				<p class='c'>
					<span class="c-title">用户名</span> <span class="c-content"> 
						<input type="text" name="username" id="blankUsername">
					</span>
				</p>
			</li>
			<li id="mobile_li">
				<p class='c'>
					<span class="c-title">手机号</span> <span class="c-content"> 
					<input type="text" name="mobile" id="blankMobile">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">家庭电话</span> <span class="c-content"> <input
						type="text" name="homePhone">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">办公电话</span> <span class="c-content"> <input
						type="text" name="officePhone">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">紧急联系人</span> <span class="c-content">
						<input type="text" name="emergencyContact">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">紧急人电话</span> <span class="c-content">
						<input type="text" name="emergencyPhone">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">邮箱</span> <span class="c-content"> <input
						type="text" id="email" name="email">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">QQ号</span> <span class="c-content"> <input
						type="text" name="qq">
					</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">微信</span> <span class="c-content"> <input
						type="text" name="weixinId">
					</span>
				</p>
			</li>
		</ul>
		<div class="m-bo text-center">
			<a href="javascript:;" class="btn btn-big btn-default" onclick="history.go(-1)">取消</a> <a
				href="javascript:;" class="btn btn-big btn-primary"
				onclick="save('no');">保存</a>
		</div>
	</div>
	<!-- </form> -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	


<script>
$(function(){
	$("#tips").html('该学员不存在，请在下方编辑').show();
	var payMasterId=$("#payMasterId").val();
	$("#payId").val(payMasterId);
	
	autoValue();
	
	function autoValue(){
		var mobileOrUsername = $("#mb").val();
		if($.checkUsername(mobileOrUsername)){
			$('#username_li').find('input').val(mobileOrUsername).attr('readonly',true);
		}
		if($.checkMobile(mobileOrUsername)){
			$('#mobile_li').find('input').val(mobileOrUsername).attr('readonly',true);
		}
	}
})

</script>