<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<div>
	<div class="main-content">
		<div class="m-title">
			<h2 class="h6">基本信息</h2>
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
			<li>
				<p class='c'>
					<span class="c-title">证件类型</span> <span class="c-content">${wx:dictCode2Name(student.identityTypeCode )}</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">证件号码</span> <span class="c-content">${student.identityId }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">出生日期</span> <span class="c-content">
					<fmt:formatDate value="${student.birthday }" timeStyle="yyyy-MM-dd"/></span>
					
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">年龄</span> <span class="c-content">${student.age }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">户口所在地</span> <span class="c-content">${student.hkAddress }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">最高学历</span> <span class="c-content">${wx:dictCode2Name(student.educationCode)}</span>
				</p>
			</li>
			<c:if test="${sgOpen==1 }">
               <c:if test="${!empty G1Name }">
               <li>
                   <p class='c'>
                       <span class="c-title">一级分组</span>
                       <span class="c-content cc">${G1Name}</span>
                   </p>
               </li>
               </c:if>
             
               <c:if test="${!empty G2Name }">
               <li>
                   <p class='c'>
                       <span class="c-title">二级分组</span>
                       <span class="c-content cc">${G2Name}</span>
                   </p>
               </li>
               </c:if>
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
					<span class="c-title">用户名</span> <span class="c-content"><em>${student.username }</em></span>
				</p>
			</li>
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
					<span class="c-title">紧急电话</span> <span class="c-content">${student.emergencyContact }</span>
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
		<div class="m-bo text-center">
			<a href="javascript:;" class="btn btn-big btn-default" onclick="history.back()">取消</a> 
			<a	href="javascript:;" class="btn btn-big btn-primary" onclick="save(${student.id});">保存</a>
		</div>
	</div>
</div>
<script>
$(function(){
	$("#tips").hide();
})
</script>