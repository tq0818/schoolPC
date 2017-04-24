<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${pointList}" var="po">
	<li style="position: relative;" pointId="${po.id}" class="poOrder">
		<span class="info" style="display: inline-block;width: 170px;height: 32px;">
		<a href="javascript:void(0)" style="line-height: 2.5;display: inline-block;max-width: 220px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">${po.pointName}</a>
		</span>
		
		<span class="pointM none">
		<a href="javascript:void(0)" class="btn btn-sm iconfont" style="padding: 7px 7px;float: right;" pointId="${po.id}" onclick="javascript:Forms.delExamPointByChecked(this)">&#xe626;</a>
		<a href="javascript:void(0)" class="btn btn-sm iconfont" style="padding: 7px 7px;float: right;" ht="编辑">&#xe625;</a>
		</span>
		
		<span style="" class="none edit"><br/>
		<input type="text" value="${po.pointName}" maxlength="50" class="newPointName" style="width: 153px;"/>
		<a href="javascript:void(0)" class="btn btn-sm btn-success" pointId="${po.id}">保存</a>
		<a href="javascript:void(0)" class="btn btn-sm btn-default">取消</a>
		</span>
	</li>
</c:forEach>