<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>题库设置</title>
<%@include file="/decorators/import.jsp" %>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/test-sub.css"/>
	<script src="<%=rootPath %>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath %>/javascripts/test-sub.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
<%@include file="/WEB-INF/jsp/tiku/set/tikuSetLeft.jsp"%>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">选题范围</span>
                </div>
            </div>
            <div>
                <p class="prompt-font">说明：章节做题将根据您所选择的选题范围抽取全部试题。快速做题不包含材料题/简答题以及试卷中的所有试题</p>
                <div class="test-padding">
                    <p>
                        <span>
                        	<c:if test="${TOPIC_OF_ALL == 'isok' }">
                            	<input type="radio" name="testchoose" value="0" data-code="TOPIC_OF_ALL" checked="checked"/>
                        	</c:if>
                        	<c:if test="${TOPIC_OF_ALL != 'isok' }">
                            	<input type="radio" name="testchoose" value="0" data-code="TOPIC_OF_ALL"/>
                        	</c:if>
                        </span>
                        <span>题库中所有试题</span>
                    </p>
                    <p>
                        <span>
                        
                        	<c:if test="${TOPIC_OF_ALL == 'isok' }">
                            	<input type="radio" name="testchoose" value="1" data-code="TOPIC_OF_ALL"/>
                        	</c:if>
                        	<c:if test="${TOPIC_OF_ALL != 'isok' }">
                            	<input type="radio" name="testchoose" value="1" data-code="TOPIC_OF_ALL" checked="checked"/>
                        	</c:if>
                        </span>
                        <span>下列范围内的试题</span>
                    </p>
                    <div class="test-in-padd">
                        <p>
                            <span>
                                <input type="checkbox" disabled="disabled" checked="checked"/>
                            </span>
                            <span>所有试题中的试题</span>
                        </p>
                        <p>
                            <span>
                            <c:if test="${empty TOPIC_OF_PAST }">
                            	<input type="checkbox" class="notpaper" value="1" data-code="TOPIC_OF_PAST"/>
                            </c:if>
                            <c:if test="${!empty TOPIC_OF_PAST }">
                            	<input type="checkbox" class="notpaper" value="0" data-code="TOPIC_OF_PAST" checked/>
                            </c:if>
                            </span>
                            <span>“试卷-真题”中的试题</span>
                        </p>
                        <p>
                            <span>
                            <c:if test="${empty TOPIC_OF_MODEL }">
                            	<input type="checkbox" class="notpaper" value="1" data-code="TOPIC_OF_MODEL"/>
                            </c:if>
                            <c:if test="${!empty TOPIC_OF_MODEL }">
                            	<input type="checkbox" class="notpaper" value="0" data-code="TOPIC_OF_MODEL" checked/>
                            </c:if>
                            </span>
                            <span>“试卷-模拟题”中的试题</span>
                        </p>
                        <p>
                            <span>
                            <c:if test="${empty TOPIC_OF_AFTER_CLASS }">
                            	<input type="checkbox" class="notpaper" value="1" data-code="TOPIC_OF_AFTER_CLASS"/>
                            </c:if>
                            <c:if test="${!empty TOPIC_OF_AFTER_CLASS }">
                            	<input type="checkbox" class="notpaper" value="0" data-code="TOPIC_OF_AFTER_CLASS" checked/>
                            </c:if>
                            </span>
                            <span>“试卷-课后练习”中的试题</span>
                        </p>
                        <c:if test="${!empty scs && scs.delFlag == 1 }">
                        <p>
                            <span>
                            <c:if test="${empty TOPIC_OF_EXAM }">
                            	<input type="checkbox" class="notpaper" value="1" data-code="TOPIC_OF_EXAM"/>
                            </c:if>
                            <c:if test="${!empty TOPIC_OF_EXAM }">
                            	<input type="checkbox" class="notpaper" value="0" data-code="TOPIC_OF_EXAM" checked/>
                            </c:if>
                            </span>
                            <span>“试卷-考试”中的试题</span>
                        </p>
                        </c:if>
                    </div>
                </div>
                <div class="clear"></div>
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
<script type="text/javascript">
$(function(){
	$selectSubMenu('org_service');
	$(".subentry:eq(2)").addClass("active");
});
</script>
</body>
</html>