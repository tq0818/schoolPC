<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
        <div class="qution-content" style="margin-top:50px">
            <div class="qution-control">
                <span class="qution-title">题目</span>
                <span class="qution-input">
                    <textarea style="width:600px;height:100px" id="quesContent">${topicVo.topicName }</textarea>
                </span>
            </div>
            <div class="qution-control">
                <span class="qution-title">答案</span>
                <span class="qution-input">
					<c:forEach var="o" items="${topicVo.option }" varStatus="status">
						<input type="radio" name="answer" value="${o.optionNo }" disabled="disabled">${o.optionNo }
					</c:forEach>
                </span>
            </div>
            <div class="qution-control">
                <span class="qution-title">正确答案</span>
                <span class="qution-input">
                	${topicVo.answer }
                </span>
            </div>
        </div>