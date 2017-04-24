<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<style type="text/css">
.zanwu{
	text-align: center;
    color: #666;
    font-size: 14px;
    position: absolute;
    left: 50%;
    top: 50%;
    z-index: 3;
    width: 100%;
    -webkit-transform: translate(-50%,-50%);
	}
</style>
<c:if test="${empty camList }">
	<div class="zanwu">
		暂无信息
	</div>
</c:if>
<c:if test="${!empty camList }">
<c:forEach items="${camList}" var="cam">
	<div class="sc-part">
		<div class="sc-part-left">
		
			<p class="xq">
				<span class="sc-p-title">校区名称</span> <span class="sc-p-content">${cam.campusName}</span>
			</p>
			<p class="bh">
				<span class="sc-p-title">编号</span> <span class="sc-p-content">${cam.campusNo}</span>
			</p>
			<c:if test="${cam.status == '1'}">
				<p class="zt">
					<span class="sc-p-title">状态</span> <span class="sc-p-content">已启用</span>
				</p>
			</c:if>
			<c:if test="${cam.status != '1'}">
				<p class="zt">
					<span class="sc-p-title">状态</span> <span class="sc-p-content" style="color: red;">已停用</span>
				</p>
			</c:if>
			<p class="zy">
				<span class="sc-p-title">租用教室</span> <span class="sc-p-content">${cam.classRoomNum}间</span>
			</p>
		</div>
		<div class="sc-part-right">
		<input type="hidden" value="${cam.id}" class="campusId"/>
				<a href="javascript:;" class="btn btn-mini btn-block btn-default btn-stop-sc">${cam.status == '1' ? '停用' : '启用'}</a> 
				<input type="hidden" value="${cam.id}" class="campusId"/>
				<a href="javascript:;" class="btn btn-mini btn-block btn-default editCampus">编辑</a>
				<input type="hidden" value="${cam.campusName}" class="campusName"/>
				<input type="hidden" value="${cam.campusNo}" class="campusNo"/>
		</div>
	</div>
</c:forEach>
</c:if>
