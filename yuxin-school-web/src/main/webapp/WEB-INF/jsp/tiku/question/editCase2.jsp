<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/decorators/import.jsp" %>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<div class="qution-content">
    <div class="qution-control">
        <table class="table table-border-title files-list">
            <tr>
                <td colspan="3">${topic.topicName }</td>
            </tr>
            <tr>
                 <td colspan="3" class="text-right">
                 	<%-- <c:if test="${topic.status != null && topic.status == 'PAPER_STATUS_EDIT' }">
	                    <a href="javascript:;" class="btn btn-mini btn-default audite btn-audite" data-commit="commit" data-qid="${topic.id }">提交审核</a>
                 	</c:if> --%>
                    <%--<a href="javascript:;" class="btn btn-mini btn-default edit editCase" data-qid="${topic.id }">编辑材料</a>
                    <a href="javascript:;" class="btn btn-mini btn-default delete" data-qid="${topic.id }">删除</a>--%>
                </td> 
            </tr>
        </table>
    </div>
    <c:if test="${btn != 'audite' }">
    <input type="hidden" value="${paperId }" id="paperId"/>
	    <div class="qution-control">
	        <p>
	        	<a href="javascript:;" class="btn btn-mini btn-success edit editCase" data-qid="${topic.id }">编辑材料</a>
	            <a href="javascript:;" class="btn btn-mini btn-success addChildQuestion" data-qid="${topic.id }">新增子题</a>
	            <c:if test="${paperId == null }">
	            	<c:if test="${tikuSet.topicAuditYes == 1 && tikuSet.topicAuditNo == 0}">
		            	<c:if test="${topic.status != null && topic.status == 'PAPER_STATUS_EDIT' }">
		          			<a href="javascript:;" class="btn btn-mini btn-primary audite btn-audite" data-commit="commit" data-qid="${topic.id }">提交审核</a>
		           		</c:if>
	           		</c:if>
	           		<c:if test="${tikuSet.topicAuditYes == 0 && tikuSet.topicAuditNo == 1}">
		            	<c:if test="${topic.status != null && topic.status == 'PAPER_STATUS_EDIT' }">
		          			<a href="javascript:;" class="btn btn-mini btn-primary audite btn-audite" data-commit="saveAndPublish" data-qid="${topic.id }">发布</a>
		           		</c:if>
	           		</c:if>
	            </c:if>
	            <c:if test="${paperId != null }">
	            	<c:if test="${topic.status != null && topic.status == 'PAPER_STATUS_EDIT' }">
	          			<a href="javascript:;" class="btn btn-mini btn-primary audite btn-audite" data-commit="commit" data-qid="${topic.id }">保存</a>
	           		</c:if>
	            	<a href="javascript:;" class="btn btn-mini btn-default btn-return">返回</a>
	            </c:if>
	            <c:if test="${paperId == null }">
	            	<a href="javascript:;" class="btn btn-mini btn-default btn-return">返回</a>
	            </c:if>
	        </p>
	    </div>
    </c:if>
</div>
        
</body>
<script type="text/javascript" src="/javascripts/tiku/question/editCase.js"></script>
</html>