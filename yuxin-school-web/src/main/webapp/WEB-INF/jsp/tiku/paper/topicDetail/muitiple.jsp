<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
        <div class="qution-content" style="margin-top:50px">
            <div class="qution-control">
                <span class="qution-title">题目</span>
                <span class="qution-input">
                    <textarea style="width:600px;height:100px" disabled="disabled">${topicVo.topicName }</textarea>
                </span>
            </div>
			<c:forEach var="o" items="${topicVo.option }" varStatus="status">
	            <div class="qution-control option-div">
	                <span class="qution-title">选项${o.optionNo }</span>
	                <span class="qution-input quesOption">
	                    <input type="text" value="${o.optionName }" disabled="disabled">
	                </span>
	            </div>
			</c:forEach>
            <div class="qution-control">
                <span class="qution-title">正确答案</span>
                <span class="qution-input">
                   ${topicVo.answer }
                </span>
            </div>
        </div>