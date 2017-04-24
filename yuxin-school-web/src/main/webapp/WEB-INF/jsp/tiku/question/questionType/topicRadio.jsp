<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<div class="u-wrap tiku">
	<input type="hidden" value="${topicVo.id }" id="topicId"/>
    <div class="mainbackground nopadding">
        <div class="heading paperhead">
            <h2 class="h5">单选题</h2>
            <span class="line"></span>
            <div class="h-type">
                科目 <span class="c">${sub.subjectName}</span>
            </div>
        </div>
        <div class="qution-content">
            <div class="qution-control">
                <span class="qution-title">题目</span>
                <span class="qution-input">
                    <textarea  name="" id="quesContent">${topicVo.topicName}</textarea>
                    <i class="iconfont i-edit" data-select="">&#xe628;</i>
                    <i class="iconfont ask" title="使用富文本编辑器可支持更多文本格式，比如字体、段落等设置">&#xe60f;</i>
                </span>
            </div>
            <c:if test="${topicVo == null }">
            <div class="qution-control option-div">
                <span class="qution-title">选项A</span>
                <span class="qution-input quesOption">
                    <input type="text" data-select="A">
                    <label class="q-i-i">
                        <input type="radio" name="answer" value="A"/> 设为正确答案
                    	<i class="iconfont i-del">&#xe626;</i>
                    	<i class="iconfont i-edit" data-select="A" style="font-size: 12px;">&#xe628;</i>
                    </label>
                </span>
            </div>
            <div class="qution-control option-div">
                <span class="qution-title">选项B</span>
                <span class="qution-input quesOption">
                    <input type="text" data-select="B">
                    <label class="q-i-i">
                        <input type="radio" name="answer" value="B"/> 设为正确答案
                    	<i class="iconfont i-del">&#xe626;</i>
                    	<i class="iconfont i-edit" data-select="B" style="font-size:12px;">&#xe628;</i>
                    </label>
                </span>
            </div>
            <div class="qution-control option-div">
                <span class="qution-title">选项C</span>
                <span class="qution-input quesOption">
                    <input type="text" data-select="C">
                    <label class="q-i-i">
                        <input type="radio" name="answer" value="C"/> 设为正确答案
                    	<i class="iconfont i-del">&#xe626;</i>
                    	<i class="iconfont i-edit" data-select="C" style="font-size:12px;">&#xe628;</i>
                    </label>
                </span>
            </div>
            <div class="qution-control option-div">
                <span class="qution-title">选项D</span>
                <span class="qution-input quesOption">
                    <input type="text" data-select="D">
                    <label class="q-i-i">
                        <input type="radio" name="answer" value="D"/> 设为正确答案
                    	<i class="iconfont i-del">&#xe626;</i>
                    	<i class="iconfont i-edit" data-select="D" style="font-size:12px;">&#xe628;</i>
                    </label>
                </span>
            </div>
            </c:if>
			<c:if test="${topicVo != null }">
				<c:forEach var="o" items="${topicVo.option }" varStatus="status">
					<c:if test="${o.correctFlag == '1' }">
			            <div class="qution-control option-div">
			                <span class="qution-title">选项${o.optionNo }</span>
			                <span class="qution-input quesOption">
			                    <input type="text" value="${o.optionName }" data-select="${o.optionNo }">
			                    <label class="q-i-i">
			                        <input type="radio" name="answer" value="${o.optionNo }" checked="checked" data-id="${o.id }"/> 设为正确答案
                    				<i class="iconfont i-del">&#xe649;</i>
                    				<i class="iconfont i-edit" data-select="${o.optionNo }" style="font-size:12px;">&#xe628;</i>
			                    </label>
			                </span>
			            </div>
					</c:if>
					<c:if test="${o.correctFlag == '0' }">
			            <div class="qution-control option-div">
			                <span class="qution-title">选项${o.optionNo }</span>
			                <span class="qution-input quesOption">
			                    <input type="text" value="${o.optionName }" data-select="${o.optionNo }">
			                    <label class="q-i-i">
			                        <input type="radio" name="answer" value="${o.optionNo }" data-id="${o.id }"/> 设为正确答案
                    				<i class="iconfont i-del">&#xe649;</i>
                    				<i class="iconfont i-edit" data-select="${o.optionNo }" style="font-size:12px;">&#xe628;</i>
			                    </label>
			                </span>
			            </div>
					</c:if>
				</c:forEach>
			</c:if>
            <div class="qution-control add-option-parent">
                <span class="qution-title">&nbsp;</span>
                <span class="qution-input add-option">
                    <a href="javascript:;" class="btn btn-primary">增加选项</a>
                </span>
            </div>
            <div class="qution-control">
                <span class="qution-title">正确答案</span>
                <span class="qution-input right-answer" style="font-size:20px;color:green;">
                    ${topicVo.answer }
                </span>
            </div>
            <jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicAnalytical.jsp"/>
            <jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicSubmit.jsp"/>
        </div>
    </div>
</div>
</body>
</html>