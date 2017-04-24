<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<input type="hidden" id="student" value="${student }"/>
<div class="main-content">
		<div class="m-title">
			<h2 class="h6">基本信息</h2>
			<span class="more"> <a href="javascript:;" class="m" id="moreBase">更多</a>
			</span>
		</div>
		<ul class="list-infos clear">
			<li>
				<p class='c'>
					<span class="c-title">姓名</span> <span class="c-content">${student.name }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">性别</span> <span class="c-content">${wx:dictCode2Name(student.sex)}</span>
				</p>
			</li>
			<li class="none not">
				<p class='c'>
					<span class="c-title">出生日期</span> <span class="c-content"><fmt:formatDate
							value="${student.birthday }" timeStyle="yyyy-MM-dd" /></span>
				</p>
			</li>
			<li id="more-tel">
				<p class='c'>
					<span class="c-title" >手机号</span> <span class="c-content"><em>${student.mobile }</em></span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">年龄</span> <span class="c-content">${student.age }</span>
				</p>
			</li>
			<li class="none not">
				<p class='c'>
					<span class="c-title">户口所在地</span> <span class="c-content">${student.hkAddress }</span>
				</p>
			</li>
			<li class="none not">
				<p class='c'>
					<span class="c-title">最高学历</span> <span class="c-content">${wx:dictCode2Name(student.educationCode)}</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">证件类型</span> <span class="c-content">${wx:dictCode2Name(student.identityTypeCode)}</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">证件号码</span> <span class="c-content">${student.identityId }</span>
				</p>
			</li>
		</ul>
	</div>
	<div class="main-content none not">
		<div class="m-title">
			<h2 class="h6">联系信息</h2>
		</div>
		<ul class="list-infos clear">
			<li>
				<p class='c'>
					<span class="c-title">手机号</span> <span class="c-content"><em>${student.mobile }</em></span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">家庭电话</span> <span class="c-content">${student.homePhone }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">办公电话</span> <span class="c-content">${student.officePhone }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">紧急联系人</span> <span class="c-content">${student.emergencyContact }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">紧急人电话</span> <span class="c-content">${student.emergencyPhone }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">邮箱</span> <span class="c-content">${student.email }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">QQ号</span> <span class="c-content">${student.qq }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">微信</span> <span class="c-content">${student.weixinId }</span>
				</p>
			</li>
		</ul>
	</div>

<script>
	$(function(){
		$("#moreBase").click(function(){
			var mm = $(this).html();
	    	if("更多"==mm){
	    		
	    		$(this).html("收起");
	    		
	    		$("#more-tel").hide();
	    		$(".not").show();
	    	}
	    	if("收起"==mm){
	    		
	    		$(this).html("更多");
	    		$(".not").hide();
	    		$("#more-tel").show();
	    	}
		})
	})
</script>