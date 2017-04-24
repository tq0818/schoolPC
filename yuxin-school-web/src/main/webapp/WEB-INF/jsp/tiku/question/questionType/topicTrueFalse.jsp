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
            <h2 class="h5">判断题</h2>
            <span class="line"></span>
            <div class="h-type">
                科目 <span class="c">${sub.subjectName }</span>
            </div>
        </div>
        <div class="qution-content">
            <div class="qution-control">
                <span class="qution-title">题目</span>
                <span class="qution-input">
                    <textarea name="" id="quesContent">${topicVo.topicName }</textarea>
                    <i class="iconfont i-edit">&#xe628;</i>
                    <i class="iconfont ask" title="使用富文本编辑器时，编辑完成会自动生成html代码">&#xe60f;</i>
                </span>
            </div>
            <div class="qution-control">
                <span class="qution-title">答案</span>
                <span class="qution-input">
                <c:if test="${topicVo == null }">
                    <label class="q-i-i quesOption">
                        <input type="radio" name="answer" value="正确"> 正确
                    </label>
                    <label class="q-i-i quesOption">
                        <input type="radio" name="answer" value="错误" > 错误
                    </label>
					</c:if>
					<c:if test="${topicVo != null }">
						<c:forEach var="o" items="${topicVo.option }" varStatus="status">
							<label class="q-i-i quesOption">
							<c:if test="${o.correctFlag == 1 }">
								<input type="radio" name="answer" value="${o.optionNo }" checked="checked" data-id="${o.id }">${o.optionNo }
							</c:if>
							<c:if test="${o.correctFlag == 0 }">
								<input type="radio" name="answer" value="${o.optionNo }" data-id="${o.id }">${o.optionNo }
							</c:if>
							</label>
						</c:forEach>
					</c:if>
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