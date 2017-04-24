<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<c:forEach var="two" items="${twoProList }">
	<span class="r-subs-title">
        <em>${two.itemName }</em>
        <a href="javascript:;" class="btn btn-mini btn-link btn-edit-two">编辑</a>
        <a href="javascript:;" class="btn btn-mini btn-link btn-switch btn-two" style="text-decoration: none">
        	<c:if test="${two.status == 0 }"><i class="iconfont normal closed">&#xe604;</i>&nbsp;&nbsp;已停用</c:if>
        	<c:if test="${two.status == 1 }"><i class="iconfont normal open">&#xe603;</i>&nbsp;&nbsp;已启用</c:if>
        </a>
        <input type="hidden" class="pid" value="${two.parentId }"/>
        <input type="hidden" class="twoId" value="${two.id }"/>
        <input type="hidden" class="twoStatus" value="${two.status }"/>
    </span>
</c:forEach>
<span class="add-subs">
    <input type="text" class="twoName" value="" maxlength="20">
    <input type="button" class="btn btn-sm btn-default" name="twoAdd" value="保存" data-pid="${oneItemId }">
    <input type="button" class="btn btn-sm btn-default" name="twoCancel" value="取消">
</span>
<script type="text/javascript" src="<%=rootPath%>/javascripts/system/twoPro.js"></script>