<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<c:forEach var="t" items="${topics }" varStatus="status">
	<div class="qution-content clickEditTopicChildQuestion">
        <div class="qution-status left-more">
        	<%-- <c:if test="${t.status == 'PAPER_STATUS_EDIT' }"><span class="status s1">正在编辑</span></c:if>
			<c:if test="${t.status == 'PAPER_STATUS_WAIT_AUDIT' }"><span class="status s2">等待审核</span></c:if>
			<c:if test="${t.status == 'PAPER_STATUS_PUBLISH' }"><span class="status s3">发布成功</span></c:if>
			<c:if test="${t.status == 'PAPER_STATUS_AUDIT_FAIL' }"><span class="status s4">审核不通过</span></c:if> --%>
			${status.index+1}.
        </div>
        <div class="qution-titles" style="width:89%;word-wrap: break-word; word-break: break all;">
        	<%-- c:if test="${fn:length(t.topicName) > 85 }">
        		${fn:substring(t.topicName,0,85) }......
        	</c:if>
        	<c:if test="${fn:length(t.topicName) <= 85 }">
        		${t.topicName }
        	</c:if> --%>
        	${t.topicName }
        </div>
        <div class="qution-icons">
            <a href="javascript:;" class="btn iconfont" data-btn="del" data-id="${t.id }" data-type="${t.topicType }">&#xe626;</a>
            <a href="javascript:;" class="btn iconfont" data-btn="edit" data-id="${t.id }" data-type="${t.topicType }">&#xe625;</a>
            <%-- <a href="javascript:;" class="btn iconfont" data-btn="audite" data-id="${t.id }" data-type="${t.topicType }">&#xe62c;</a> --%>
        </div>
    </div>
    <div  class="caseChildTopicEdit"></div>
</c:forEach>
<script type="text/javascript" src="<%=request.getContextPath()%>/javascripts/tiku/question/editCaseChilds.js"></script>
