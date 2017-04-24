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
		    <div class="qution-control">
		        <span class="qution-title">答案</span>
		        <div style="margin-left:100px">
		       		<textarea style="width:600px;height:150px" disabled="disabled">${topicVo.answer }</textarea>
		    	</div>
		    </div>
        </div>