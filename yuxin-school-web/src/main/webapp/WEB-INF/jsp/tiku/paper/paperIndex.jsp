<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="zh-cn">
<head>
    <title>题库</title>
    <style type="text/css">
.register {
	position: fixed;
	left: 50%;
	top: 50%;
	width: 400px;
	height: 400px;
	margin-left: -200px;
	margin-top: -200px;
	padding-bottom: 15px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
	z-index: 999
}
.none{
display: none;
}
.register .reg-close {
  position: absolute;
  top: 12px;
  right: 12px;
  width: 12px;
  height: 12px;
  background-repeat: no-repeat;
  background-position: 0 0;
  cursor: pointer;
}
.register .reg-title {
  padding: 15px 30px;
  border-bottom: 1px solid #e5e5e5;
}
.register .reg-form {
  padding: 0 60px;
}
.register .reg-bottom {
  padding: 2px 52px;
  border-top: 1px solid #e5e5e5;
}
.mark-bg {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: #fff;
  background-color: rgba(255,255,255,0.6);
  opacity: .6 \9;
  filter: alpha(opacity = 60);
}
a:link {
 font-size: 12px;
 text-decoration: none;
}
a:visited {
 font-size: 12px;
 text-decoration: none;
}
a:hover {
 font-size: 12px;
 text-decoration: none;
}
.liColor{
	background-color: #D6D6D6;
}
</style>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/paper/tikuPaper.js"></script>
</head>
<body>
<!-- header start -->
</header>
<!-- header end -->
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/tiku/tikuHeader.jsp"></jsp:include>
<div class="u-wrap tiku">
    <div class="mainbackground nopadding">
        <div class="classes-type">
            <p class="c title-p clear">
                <span class="t-title fl-left margin-t3">科目</span>
                <span class="t-content fl-left" id="subList">
                	<c:forEach items="${subList}" var="sub" varStatus="index">
                		<c:if test="${index.index == 0}">
                			<a href="javascript:;" class="btn btn-mini btn-default btn-success subject" subId="${sub.id}">${sub.subjectName}</a>
                		</c:if>
                		<c:if test="${index.index != 0}">
                			<a href="javascript:;" class="btn btn-mini btn-default subject" subId="${sub.id}">${sub.subjectName}</a>
                		</c:if>
                	</c:forEach>
                </span>
            </p>
            <p class="c">
                <span class="t-title">试卷类型</span>
                <span class="t-content">
                    <a href="javascript:;" class="btn btn-mini btn-default paperType btn-success" pt="">全部</a>
                    <a href="javascript:;" class="btn btn-mini btn-default paperType" pt="PAPER_TYPE_PAST">真题</a>
                    <a href="javascript:;" class="btn btn-mini btn-default paperType" pt="PAPER_TYPE_MODEL">模拟题</a>
                    <a href="javascript:;" class="btn btn-mini btn-default paperType" pt="PRACTICE_AFTER_CLASS">课后练习</a>
                    <c:if test="${!empty examOk }">
                    	<a href="javascript:;" class="btn btn-mini btn-default paperType" pt="PAPER_TYPE_EXAM">题库考试</a>
                    </c:if>
                    <a href="javascript:;" class="btn btn-mini btn-default paperType" pt="PAPER_TYPE_HOMEWORK">课后作业</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">试卷状态</span>
                <span class="t-content">
                    <a href="javascript:;" class="btn btn-mini btn-default paperStatus btn-success" ps="">全部</a>
                    <a href="javascript:;" class="btn btn-mini btn-default paperStatus" ps="PAPER_STATUS_EDIT">正在编辑</a>
                    <a href="javascript:;" class="btn btn-mini btn-default paperStatus" ps="PAPER_STATUS_WAIT_AUDIT">等待审核</a>
                    <a href="javascript:;" class="btn btn-mini btn-default paperStatus" ps="PAPER_STATUS_PUBLISH">审核通过</a>
                    <a href="javascript:;" class="btn btn-mini btn-default paperStatus" ps="PAPER_STATUS_AUDIT_FAIL">审核不通过</a>
                </span>
            </p>
			<div>
				<a href="javascript:;" id="newPaper" class="btn btn-mini btn-primary" style="position: absolute;top: 76%;right: -1%;"><em class="iconfont" style="top:0px;">&#xe606;</em> 添加试卷</a>
			</div>
            <div class="search">
                <input type="text" placeholder="试卷名称" id="pName">
                <input type="button" class="btn btn-primary" onclick="javascript:Forms.searchByName()" value="搜索">
            </div>
        </div>
    </div>
</div>

<div class="u-wrap tiku">
    <div class="mainbackground nopadding">
        <div class="table-list">
            <ul id="info">
                
            </ul>
        </div>
        <div class="pages">
			<ul class="pagination">
			</ul>
		</div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<div>
	<input type="hidden" value="${tikuId}" id="tikuId"/>
	<input type="hidden" id="subId"/>
	<input type="hidden" id="paperType"/>
	<input type="hidden" id="paperStatus"/>
	<input type="hidden" id="paperName"/>
</div>
<!-- footer start -->
<!-- footer end --> 
<script type="text/javascript">
	$(function(){
		$(".tiHeader .navspace li>a:eq(1)").addClass("active");
		$selectMenu('tiku_header');
        $selectSubMenu('tiku_paper');
	});
</script> 
</body>
</html>