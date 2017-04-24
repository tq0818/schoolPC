<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<div id="m1" class="mark-more">
<input type="hidden" id="student" value="${student }"/>
<div class="main-content">
		<div class="m-title">
			<h2 class="h6">基本信息</h2>
			<span class="more"> <a href="javascript:;"  onclick="moreStudent(this)">更多</a>
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
			<li class="none n1">
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
			<li class="none n1">
				<p class='c'>
					<span class="c-title">户口所在地</span> <span class="c-content">${student.hkAddress }</span>
				</p>
			</li>
			<li class="none n1">
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
	<div class="main-content none n1">
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
</div>
<script type="text/javascript">

function moreStudent(ele){
	
	var mm = ele.innerHTML;
	var $this=document.getElementById('m1');
	if("更多"==mm){
		ele.innerHTML="收起";
		var eles=getElementsByClassName('n1');
		for(var i=0;i<eles.length;i++){
			eles[i].style.display="block";
		}
		return false;
	}else{
		ele.innerHTML="更多";
		var eles=getElementsByClassName('n1');
		for(var i=0;i<eles.length;i++){
			eles[i].style.display="none";
		}
		return false;
	}
	
}

</script>
