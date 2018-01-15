<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<input type="hidden" value="${topicPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${topicPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${topicPage.pageSize }" id="pageSize"/>
<ul>
	<c:forEach var="t" items="${topicPage.data }">
    <c:if test="${tikuSet.topicAuditYes == 1 && tikuSet.topicAuditNo == 0 }">
    <li>
        <table class="table table-border-title">
            <tr>
                <td rowspan="2" width="10%">
	    			<c:if test="${t.status == 'PAPER_STATUS_EDIT' }"><span class="status s1">正在编辑</span></c:if>
	    			<c:if test="${t.status == 'PAPER_STATUS_WAIT_AUDIT' }"><span class="status s2">等待审核</span></c:if>
	    			<c:if test="${t.status == 'PAPER_STATUS_PUBLISH' }">
	    				<span class="status s3">发布成功</span>
	    				<c:choose>
	    				<c:when test="${t.auditTime ne null}">
	    				<span class="status c9 mt4"><fmt:formatDate value="${t.auditTime}"/></span>
	    				</c:when>
	    				<c:otherwise>
	    				<span class="status c9 mt4"><fmt:formatDate value="${t.createTime}"/></span>
	    				</c:otherwise>
	    				</c:choose>
	    			</c:if>
	    			<c:if test="${t.status == 'PAPER_STATUS_AUDIT_FAIL' }"><span class="status s4">审核不通过</span></c:if>
                </td>
                <td rowspan="2" width="65%">
                    <span class="l-title">${t.topicName }</span>
                </td>
                <td width="10%">
                    <span class="c">编辑者</span>
                    <span class="p">
		    			<c:if test="${t.updatorName == null }">${t.creatorName }</c:if>
		    			<c:if test="${t.updatorName != null }">${t.updatorName }</c:if>
	    			</span>
                </td>
                <td width="15%" rowspan="2" class="btns">
                    <a href="javascript:;" class="btn btn-mini btn-primary btn-topic" data-btn="delete" data-qid="${t.id }">删除</a>
                    <a href="javascript:;" class="btn btn-mini btn-primary btn-topic" data-btn="edit" data-qid="${t.id }">编辑</a>
                    <c:if test="${t.status != 'PAPER_STATUS_WAIT_AUDIT' }">
                    	<a href="javascript:;" class="btn btn-mini btn-primary btn-b btn-topic" data-btn="audite" data-qid="${t.id }" disabled="disabled">审核</a>
                    </c:if>
                    <c:if test="${t.status == 'PAPER_STATUS_WAIT_AUDIT' }">
                    	<a href="javascript:;" class="btn btn-mini btn-primary btn-b btn-topic" data-btn="audite" data-qid="${t.id }">审核</a>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="c">审核者</span>
                    <c:if test="${t.updatorName == null }">${t.creatorName }</c:if>
			    	<c:if test="${t.updatorName != null }">${t.updatorName }</c:if>
                </td>
            </tr>
        </table>
    </li>
    </c:if>
    <c:if test="${tikuSet.topicAuditYes == 0 && tikuSet.topicAuditNo == 1 }">
    <c:if test="${t.status == 'PAPER_STATUS_EDIT' || t.status == 'PAPER_STATUS_PUBLISH' }">
    <li>
        <table class="table table-border-title">
            <tr>
                <td rowspan="2" width="10%">
	    			<c:if test="${t.status == 'PAPER_STATUS_EDIT' }"><span class="status s1">正在编辑</span></c:if>
	    			<c:if test="${t.status == 'PAPER_STATUS_PUBLISH' }"><span class="status s3">发布成功</span></c:if>
                </td>
                <td rowspan="2" width="75%">
                    <span class="l-title">${t.topicName }</span>
                </td>
                <td width="10%" rowspan="2">
                    <span class="c">编辑者</span>
                    <span class="p">
		    			<c:if test="${t.updatorName == null }">${t.creatorName }</c:if>
		    			<c:if test="${t.updatorName != null }">${t.updatorName }</c:if>
	    			</span>
                </td>
                <td width="5%" rowspan="2" class="btns">
                    <a href="javascript:;" class="btn btn-mini btn-primary btn-topic" data-btn="delete" data-qid="${t.id }">删除</a>
                    <a href="javascript:;" class="btn btn-mini btn-primary btn-topic" data-btn="edit" data-qid="${t.id }">编辑</a>
                </td>
            </tr>
        </table>
    </li>
    </c:if>
    </c:if>
   	</c:forEach>
</ul>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/question/topic.js"></script>