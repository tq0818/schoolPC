<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${list}" var="cv">

	<c:if test="${cv.delFlag == '0' || empty cv.delFlag }">
	<div class="block active" data-type="start">
	</c:if>
	<c:if test="${cv.delFlag == '1' }">
	<div class="block" data-type="stop" style="background:#EEEEEE">
	</c:if>
		<div class="hidden">
			<div class="b-title">
				<div class="icon">
					<i class="iconfont">
						 <img src="http://${ImagePath }/${cv.iconUrl}" />
					</i>
				</div>
				<div class="tt">
					<div class="h3">
						<c:choose>
   					    <c:when test="${fn:length(cv.tikuName) > 15}">
   					    	<b fullName="${cv.tikuName}">${fn:substring(cv.tikuName,0,15)}...</b>
  					    </c:when>
					    <c:otherwise>
					    	<b fullName="${cv.tikuName}">${cv.tikuName}</b>
					    </c:otherwise>
						</c:choose>
						<span style="display: none;" class="manageGL">
						<i class="iconfont btn-edit-pro delInfo" tikuId="${cv.id}">&#xe626;</i>
						<i class="iconfont btn-edit-pro editInfo" tikuId="${cv.id}">&#xe625;</i>
						</span>
					</div>
					<div class="c">
						<c:if test="${fn:length(cv.tikuDesc) > 25 }">
							<span title="${cv.tikuDesc }">${fn:substring(cv.tikuDesc,0,25) }......</span>
						</c:if>
						<c:if test="${fn:length(cv.tikuDesc) <= 25 }">
							<span>${cv.tikuDesc}</span>
						</c:if>
					</div>
				</div>
				<div class="line"></div>
			</div>
			<div class="b-content">
				<ul>
					<li style="text-align: center;"><span class="r-subs-title">
							<em>共有${cv.subjectNo }个科目</em>
					</span></li>
					<li style="text-align: center;"><span class="r-subs-title">
							<em>共有${cv.paperNo }套试卷</em>
					</span></li>
					<li style="text-align: center;"><span class="r-subs-title">
							<em>共有${cv.topicNo }道试题</em>
					</span></li>
				</ul>
			</div>
			<div class="b-btns" style="text-align: center;">
				<c:if test="${cv.delFlag == '0' }">
					<i class="iconfont left btn-ok btn-switch jinYong" title="已启用，点击停用" tikuId="${cv.id}">&#xe636;</i>
				</c:if>
				<c:if test="${cv.delFlag == '1' }">
					<i class="iconfont left btn-ok btn-switch qiYong" style="color:red;" title="已停用，点击启用" tikuId="${cv.id}">&#xe635;</i>
				</c:if>
				<div class="btn-add glTiku" name="twoToCreate" style="cursor: pointer;" title="管理题库" cvid="${cv.id}">管理</div>
			</div>
		</div>
	</div>
</c:forEach>

<div class="block active">
	<div class="b-btns" style="margin-left: -100px; margin-bottom: 120px;"
		id="addTiku">
		<div class="btn-addss btn-pro" id="createPro" title="添加题库">
			<i class="iconfont" style="font-size: 80px">&#xe61c;</i>
		</div>
	</div>
</div>
