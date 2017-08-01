<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="<%=rootPath %>/javascripts/itemTree/script/ztree/jquery.ztree.all.min.js"></script>
    <script src="<%=rootPath %>/javascripts/itemTree/script/metadata.js"></script>
    <link rel="stylesheet" href="<%=rootPath %>/javascripts/itemTree/script/ztree/metroStyle.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/itemTree/css/metadata.css">
    <title>元数据管理</title>
</head>

<body>
    <div class="cntr">

        <div class="top"><input type="button" value="发布生效"></div>

        <div class="content">

            <div class="tree_cntr">
                <input id="addCatg" type="button" value="添加类型">
                <div id="ztree" class="ztree"></div>
            </div>

            <div class="tree_setting">
                <div id="courses">
                    <label>学科：</label>
                    <c:forEach items="${subjectList}" var="subject">
                        <label>${subject.itemName}<input type="checkbox" value="" name="course"></label>
                    </c:forEach>
                    <%--<label>语文<input type="checkbox" value="yuwen" name="course"></label>--%>
                    <%--<label>数学<input type="checkbox" value="shuxue" name="course"></label>--%>
                    <%--<label>英语<input type="checkbox" value="yingyu" name="course"></label>--%>
                </div>
                <div id="periods">
                    <label>学段：</label>
                    <c:forEach items="${gradeList}" var="grade">
                        <label>${grade.itemName}<input type="checkbox" value="" name="period"></label>
                    </c:forEach>
                </div>
                <div id="topic">
                    <label>知识点：</label>
                    <input id="newTopicName" type="text">
                    <input id="addTopic" type="button" value="添加">
                </div>
            </div>
        </div>

    </div>
</body>

</html>