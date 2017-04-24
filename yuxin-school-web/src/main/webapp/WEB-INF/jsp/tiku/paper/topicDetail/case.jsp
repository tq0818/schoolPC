<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
        <div class="qution-content" style="margin-top:50px">
            <div class="qution-control">
                <span class="qution-title">案例</span>
                <span class="qution-input">
                    <textarea style="width:600px;height:100px" disabled="disabled">${topicVo.topicName }</textarea>
                </span>
            </div>
			<c:forEach var="o" items="${childTopics }" varStatus="status" >
	            <div class="qution-control option-div">
	                <span class="qution-title">子题${status.index + 1}</span>
	                <span class="qution-input quesOption">
	                    <input type="text" value="${o.topicName }" disabled="disabled">
	                </span>
	            </div>
			</c:forEach>
        </div>