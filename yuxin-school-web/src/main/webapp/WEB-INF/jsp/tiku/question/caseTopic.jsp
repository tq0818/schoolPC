<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<input type="hidden" value="${topicPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${topicPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${topicPage.pageSize }" id="pageSize"/>
<ul>
	<c:forEach var="t" items="${topicPage.data }">
    <c:if test="${tikuSet.topicAuditYes == 1 && tikuSet.topicAuditNo == 0 }">
    <li>
		<table class="table table-border-title files-list">
          <tr>
              <td width="40%">
                  <span>编辑者
                  	<em>
						<span class="p">
			    			<c:if test="${t.updatorName == null }">${t.creatorName }</c:if>
			    			<c:if test="${t.updatorName != null }">${t.updatorName }</c:if>
		    			</span>
					</em>
				</span>
              </td>
              <td width="40%">
                  <span>审核者<em>
                  <c:if test="${t.status eq 'PAPER_STATUS_PUBLISH' or t.status eq 'PAPER_STATUS_AUDIT_FAIL'}">
                  ${t.auditorName }
                  </c:if>
                  <c:if test="${t.updatorName == null }">${t.creatorName }</c:if>
                  </em></span>
              </td>
              <td class="text-right">
                <c:if test="${t.status == 'PAPER_STATUS_EDIT' }"><span class="text-right">正在编辑</span></c:if>
    			<c:if test="${t.status == 'PAPER_STATUS_WAIT_AUDIT' }"><span class="text-right info-color">等待审核</span></c:if>
    			<c:if test="${t.status == 'PAPER_STATUS_PUBLISH' }"><span class="text-right success-color">发布成功</span></c:if>
    			<c:if test="${t.status == 'PAPER_STATUS_AUDIT_FAIL' }"><span class="text-right danger-color">审核不通过</span></c:if>
              </td>
          </tr>
          <tr>
              <td colspan="3">${t.topicName }</td>
          </tr>
          <tr>
              <td colspan="3" class="text-right">
                  <a href="javascript:;" class="btn btn-mini btn-topic btn-default" data-btn="delete" data-qid="${t.id }">删除</a>
                  <a href="javascript:;" class="btn btn-mini btn-topic btn-default" data-btn="edit" data-qid="${t.id }">编辑</a>
                  <c:if test="${t.status != null && t.status == 'PAPER_STATUS_WAIT_AUDIT' }">
                  	<a href="javascript:;" class="btn btn-mini btn-topic btn-default" data-btn="audite" data-qid="${t.id }">审核</a>
                  </c:if>
              </td>
          </tr>
        </table>
    </li>
    </c:if>
    <c:if test="${tikuSet.topicAuditYes == 0 && tikuSet.topicAuditNo == 1 }">
    <c:if test="${t.status == 'PAPER_STATUS_EDIT' || t.status == 'PAPER_STATUS_PUBLISH' }">
    <li>
        <table class="table table-border-title files-list">
          <tr>
              <td width="40%">
                  <span>编辑者
                  	<em>
						<span class="p">
			    			<c:if test="${t.updatorName == null }">${t.creatorName }</c:if>
			    			<c:if test="${t.updatorName != null }">${t.updatorName }</c:if>
		    			</span>
					</em>
				</span>
              </td>
              <td width="40%">
                  <span>审核者<em>
                  <c:if test="${t.status eq 'PAPER_STATUS_PUBLISH' or t.status eq 'PAPER_STATUS_AUDIT_FAIL'}">
                  ${t.auditorName }
                  </c:if>
                  </em></span>
              </td>
              <td class="text-right">
                  <span class="text-right info-color">
                  	<c:if test="${t.status == 'PAPER_STATUS_EDIT' }"><span class="status s1">正在编辑</span></c:if>
	    			<c:if test="${t.status == 'PAPER_STATUS_PUBLISH' }"><span class="status s3">发布成功</span></c:if>
                  </span>
              </td>
          </tr>
          <tr>
              <td colspan="3">${t.topicName }</td>
          </tr>
          <tr>
              <td colspan="3" class="text-right">
           		 <a href="javascript:;" class="btn btn-mini btn-default btn-topic" data-btn="delete" data-qid="${t.id }">删除</a>
                 <a href="javascript:;" class="btn btn-mini btn-default btn-topic" data-btn="edit" data-qid="${t.id }">编辑</a>
              </td>
          </tr>
        </table>
        
    </li>
    </c:if>
    </c:if>
   	</c:forEach>
</ul>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/question/topic.js"></script>