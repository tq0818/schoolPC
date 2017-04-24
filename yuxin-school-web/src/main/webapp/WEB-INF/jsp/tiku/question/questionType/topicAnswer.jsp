<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
    <div class="u-wrap tiku">
        <input type="hidden" value="${topicVo.id }" id="topicId" />
        <div class="mainbackground nopadding">
            <div class="heading paperhead">
                <h2 class="h5">简答题</h2>
                <span class="line"></span>
                <div class="h-type">
                                                         科目 <span class="c">${sub.subjectName }</span>
                </div>
            </div>
            <div class="qution-content">
                <div class="qution-control">
                    <span class="qution-title">题目</span>
                    <span class="qution-input">
                        <textarea id="quesContent">${topicVo.topicName }</textarea>
                        <i class="iconfont i-edit">&#xe628;</i>
                        <i class="iconfont ask" title="使用富文本编辑器时，编辑完成会自动生成html代码">&#xe60f;</i>
                    </span>
                </div>
                <div class="qution-control">
                    <span class="qution-title">答案</span>
                    <div style="margin-left:100px">
                        <textarea rows="" cols="" id="answerContent">${topicVo.answer }</textarea>
                    </div>
                </div>
                <jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicAnalytical.jsp" />
                <jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicSubmit.jsp" />
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var answer = CKEDITOR.replace('answerContent');
        answer.config.width = "820px";
        answer.config.toolbar = [
            ['mode', 'document', 'doctools'],
            ['Source', ],
            ['basicstyles', 'cleanup'],
            ['Bold', 'Italic', 'Underline', 'Strike'],
            ['NumberedList', 'BulletedList','JustifyLeft', 'JustifyCenter', 'JustifyRight','JustifyBlock'],
            ['Link', 'Unlink'],
            ['Image', 'Table'],
            ['Maximize']
        ];
        answer.config.baseFloatZIndex = 10100;
        answer.config.customConfig = 'config.js';
    </script>
</body>

</html>