<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
    <script src="<%=rootPath %>/javascripts/itemTree/script/ztree/jquery.ztree.all.min.js"></script>
    <script src="<%=rootPath %>/javascripts/itemTree/script/metadata.js"></script>
    <link rel="stylesheet" href="<%=rootPath %>/javascripts/itemTree/script/ztree/metroStyle.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/itemTree/css/metadata.css">
    <title>元数据管理</title>
</head>

<body>
  <%--  <div class="tab-sub">
        <span class="tab-type active" href=".tab1">元数据管理</span>
        <span class="tab-type" href=".tab2">目录树管理</span>
    </div>--%>
    <div class="cntr tab1 tab-info">
        <div class="top"><input type="button" value="发布生效" id="publishRelation"></div>
        <div class="content">
            <div class="tree_cntr">
                <input id="addCatg" type="button" value="添加分类">
                <div id="ztree" class="ztree"></div>
            </div>
            <div class="tree_setting">
                <div class="tree-listtype" id="bigType">
                    <label class="tit-type fl">分类：</label>
                    <div class="tit-list fl">
                        <c:forEach items="${typeList}" var="type">
                            <label><input type="checkbox" value="${type.itemCode}" name="bigtype">${type.itemName}</label>
                        </c:forEach>
                    </div>
                </div>
                <div id="courses" class="tree-listtype">
                    <label class="tit-type fl">学科：</label>
                    <div class="tit-list fl">
                        <c:forEach items="${subjectList}" var="subject">
                            <label><input type="checkbox" value="${subject.itemCode}" name="course">${subject.itemName}</label>
                        </c:forEach>
                    </div>
                </div>
                <div id="periods" class="tree-listtype">
                    <label class="tit-type fl">学段：</label>
                    <div class="tit-list fl">
                        <c:forEach items="${gradeList}" var="grade">
                            <label><input type="checkbox" value="${grade.itemCode}" name="period">${grade.itemName}</label>
                        </c:forEach>
                    </div>
                </div>
                <div id="topic" class="tree-listtype">
                    <label class="tit-type fl">知识点：</label>
                    <input name="itemCode" type="text">
                </div>
                <div class="btn-list" style="display: none;">
                    <button class="btn btn-default"  id="savabtn">保存</button>
                </div>
            </div>
        </div>
    </div>
  <!-- ajax加载中div开始 -->
  <div class="loading lp-units-loading" style="display:none">
      <p><i></i>加载中,请稍后...</p>
  </div>
  <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
  <!--  ajax加载中div结束 -->
</body>

</html>