<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		var pid = $("#paperIds").val();
		if(typeof(pid) != "undefined" && pid != null && pid != ""){
			$.ajax({
				url : rootPath + "/question/selScore",
				type:"post",
				data:{"paperId":pid,"topicId":$("#topicId").val()},
				dataType:"json",
				success:function(data){
					for(var i = 0 ; i <= 50 ; i ++){
						$("#score").append("<option value='" + i + "'>" + i + "</option>");
					}
					$("#score").val(parseInt(data.score));
				}
			});
		}
	});
</script>
</head>
<body>
<c:if test="${paperId == null }">
	<c:if test="${tikuSet.topicAuditYes == 1 && tikuSet.topicAuditNo == 0}">
		<c:if test="${btn == 'create' }">
	    <div class="qution-control">
	        <p class="text-center">
	            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="save">保存</a>
            	<!-- 材料题子题，只有保存以及取消 -->
	            <c:choose>
	            	<c:when test="${parentId != null && parentId != 0 }">
	            		<a href="javascript:;" class="btn btn-default btn-childCancel">取消</a>
	            	</c:when>
	            	<c:otherwise>
	            		<c:if test="${types != 'TOPIC_TYPE_CASE' }">
		            		<a href="javascript:;" class="btn btn-primary btn-button" data-commit="commit">提交审核</a>
				            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="commitAndAdd">提交审核并继续</a>
	            		</c:if>
			            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
	            	</c:otherwise>
	            </c:choose>
	        </p>
	    </div>
		</c:if>
		<c:if test="${btn == 'edit' }">
	    <div class="qution-control">
	        <p class="text-center">
	        	<!-- 如果是材料题编辑状态不需要保存，取消按钮 -->
		        <c:if test="${types != 'TOPIC_TYPE_CASE' }">
		            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="save">保存</a>
		            <c:choose>
		            	<c:when test="${parentId != null && parentId != 0}">
		            		<a href="javascript:;" class="btn btn-default btn-childCancel">取消</a>
		            	</c:when>
		            	<c:otherwise>
		            		<a href="javascript:;" class="btn btn-primary btn-button" data-commit="commit">提交审核</a>
			            	<a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
		            	</c:otherwise>
		            </c:choose>
	             </c:if>
	        </p>
	    </div>
		</c:if>
		<c:if test="${btn == 'audite' }">
	    <div class="qution-control">
	        <p class="text-center">
	            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="okAndContinue">审核通过继续审核</a>
	            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="noAndContinue">审核不通过继续审核</a>
	            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
	        </p>
	    </div>
		</c:if>
	</c:if>
	<c:if test="${tikuSet.topicAuditYes == 0 && tikuSet.topicAuditNo == 1}">
		<c:if test="${btn == 'create' }">
	    <div class="qution-control">
	        <p class="text-center">
	            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="save">保存</a>
	            <c:if test="${!(parentId != null && parentId > 0) && types != 'TOPIC_TYPE_CASE' }">
		            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="saveAndPublish">发布</a>
		            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="publishAndContinue">发布并继续</a>
	            </c:if>
	            <c:choose>
	            	<c:when test="${parentId != null && parentId != 0 }">
	            		<a href="javascript:;" class="btn btn-default btn-childCancel">取消</a>
	            	</c:when>
	            	<c:otherwise>
			            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
	            	</c:otherwise>
            	</c:choose>
	        </p>
	    </div>
		</c:if>
		<c:if test="${btn == 'edit' }">
	    <div class="qution-control">
	        <p class="text-center">
	        <c:if test="${types != 'TOPIC_TYPE_CASE' }">
	            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="save">保存</a>
	            <c:if test="${!(parentId != null && parentId > 0) && types != 'TOPIC_TYPE_CASE' }">
	            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="saveAndPublish">保存并发布</a>
	            </c:if>
	            <c:choose>
	            	<c:when test="${parentId != null && parentId != 0 }">
	            		<a href="javascript:;" class="btn btn-default btn-childCancel">取消</a>
	            	</c:when>
	            	<c:otherwise>
			            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
	            	</c:otherwise>
            	</c:choose>
	        </c:if>
	        </p>
	    </div>
		</c:if>
	</c:if>
</c:if>
	
<c:if test="${paperId != null }">
	<c:if test="${types == 'TOPIC_TYPE_ANSWER' || parentId > 0}">
	<input type="hidden" value="${paperId }" id="paperIds"/>
		<div class="qution-control">
	        <span class="qution-title">分数</span>
	        <span class="qution-input">
	        	<select id="score">
	        		
	        	</select>
	        </span>
	    </div>
	</c:if>
	<c:if test="${btn == 'create' }">
	    <div class="qution-control">
	        <p class="text-center">
	            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="commit">保存</a>
	            <a href="javascript:;" class="btn btn-default btn-papeCancel">取消</a>
	        </p>
	    </div>
	</c:if>
	<c:if test="${btn == 'edit' }">
	    <div class="qution-control">
	        <p class="text-center">
	            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="commit">保存</a>
	            <a href="javascript:;" class="btn btn-default btn-papeCancel">取消</a>
	        </p>
	    </div>
	</c:if>
	<c:if test="${btn == 'audite' }">
    <div class="qution-control">
        <p class="text-center">
            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="auditok">审核通过</a>
            <a href="javascript:;" class="btn btn-primary btn-button" data-commit="auditno">审核不通过</a>
            <a href="javascript:;" class="btn btn-default btn-papeCancel">取消</a>
        </p>
    </div>
	</c:if>
</c:if>
</body>
</html>