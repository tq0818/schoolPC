<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <%@include file="/decorators/import.jsp" %>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/question/chapterSectionPoint.js"></script>
</head>

<body>
    <div class="qution-control">
        <span class="qution-title">题目解析</span>
        <div style="margin-left:100px">
            <textarea rows="" cols="" id="newsContents">${topicVo.analyseWord}</textarea>
        </div>
    </div>
    <div class="qution-control">
        <span class="qution-title">章</span>
        <span class="qution-input">
    	<select id="chapter"></select>
    </span>
    </div>
    <div class="qution-control">
        <span class="qution-title">节</span>
        <span class="qution-input">
    	<select id="section"></select>
    </span>
    </div>
    <div class="qution-control">
        <span class="qution-title">考点</span>
        <span class="qution-input">
    	<select id="exampoint"></select>
    </span>
    </div>
    <div class="qution-control">
        <span class="qution-title">难度</span>
        <span class="qution-input">
        <label for=""><input type="radio" name="ndsd" value="DIFFICULT_TYPE_EASY" <c:if test="${topicVo.difficulty == 'DIFFICULT_TYPE_EASY' || topicVo == null }">checked="checked"</c:if> >简单</label>
        <label for=""><input type="radio" name="ndsd" value="DIFFICULT_TYPE_MIDDLE" <c:if test="${topicVo.difficulty == 'DIFFICULT_TYPE_MIDDLE' }">checked="checked"</c:if>>一般</label>
        <label for=""><input type="radio" name="ndsd" value="DIFFICULT_TYPE_DIFFICULT" <c:if test="${topicVo.difficulty == 'DIFFICULT_TYPE_DIFFICULT' }">checked="checked"</c:if> >困难</label>
    </span>
    </div>
    <script type="text/javascript" src="<%=rootPath%>/plugins/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        var editor = CKEDITOR.replace('newsContents');
        editor.config.width = "820px";
        editor.config.toolbar = [
            ['mode', 'document', 'doctools'],
            ['Source', ],
            ['basicstyles', 'cleanup'],
            ['Bold', 'Italic', 'Strike'],
            ['NumberedList', 'BulletedList', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
            ['Link', 'Unlink'],
            ['Image', 'Table'],
            ['Maximize']
        ];
        editor.config.baseFloatZIndex = 10100;
        editor.config.customConfig = 'config.js';
    </script>
</body>

</html>