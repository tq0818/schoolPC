<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<%@include file="/decorators/import.jsp" %>
<html lang="zh-cn">
<head>
    <title>试卷信息</title> 
    <style type="text/css">
    	p.c span{
    		color:red;
    	}
		.steps ul li.step3 {width: 50%;text-align: center}
		.steps ul li.step3.s1 {left: 0;z-index: 9}
		.steps ul li.step3.s2 {left: 50%;z-index: 8}
		.steps ul li.step3.s2:after {background-image: none;background-color: #ddd}
		.steps ul li.step3.s2.active:after, .steps ul li.step3.s2.hover:after {background-image: none;background-color: #04a2ca}
		.steps ul li:after {content: "\20";position: absolute;top: 0;right: -30px;width: 30px;height: 30px;background-image: url(../../../../../images/step-after.png);background-repeat: no-repeat;background-position: 0 -64px;z-index: 9}
		.steps ul li.active:after {content: "\20";position: absolute;top: 0;right: -30px;idth: 30px;height: 30px;background-image: url(../../../../../images/step-after.png);background-repeat: no-repeat;background-position: 0 -32px;z-index: 9}
		.steps ul li.hover:after {content: "\20";position: absolute;top: 0;right: -30px;width: 30px;height: 30px;background-color: #ddd;background-image: url(../../../../../images/step-after.png);background-repeat: no-repeat;background-position: 0 0;z-index: 9}
    </style>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/tiku/jquery.cityselect.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/paper/tikuPaperInfo.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/tiku/tikuHeader.jsp"></jsp:include>
<input type="hidden" value="${btn}" id="btn"/>
<div class="u-wrap classes">
    <div class="nopadding">
        <div class="steps">
            <div class="line"></div>
            <ul class="clear">
                <li class="step3 s1 hover">
                    <i>01</i>
                    <em>试卷基本信息</em>
                </li>
                <li class="step3 s2">
                    <i>02</i>
                    <em>添加试题</em>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">试卷基本信息</h2>
            <span class="line"></span>
        </div>
        <form id="paperInfoFrom">
        <div class="c-main">
            <div class="c-content">
                <p class="c">
                    <span class="c-title">试卷名称</span>
                    <span class="c-content">
                       <input type="text" id="paperName" name="paperName" maxlength="99" value="${paper.paperName}" style="width: 500px;">
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">试卷类型</span>
                    <span class="c-content">
                    <c:if test="${exam != 'exam' }">
                        <c:if test="${empty paper.paperType || paper.paperType=='PAPER_TYPE_PAST'}">
                     		 <input type="radio" checked="checked" value="PAPER_TYPE_PAST" name="paperType" class="paperType"><span style="color: black;">真题</span>
                     		 <input type="radio" value="PAPER_TYPE_MODEL" name="paperType" class="paperType"><span style="color: black;">模拟题</span>
                     		 <input type="radio" value="PRACTICE_AFTER_CLASS" name="paperType" class="paperType"><span style="color: black;">课后练习</span>
                     		 <c:if test="${examOk == 'examOk'}">
                        	 	<input type="radio" value="PAPER_TYPE_EXAM" name="paperType" class="paperType"><span style="color: black;">题库考试试卷</span>
                        	 </c:if>
                        	 <input type="radio" value="PAPER_TYPE_HOMEWORK" name="paperType" class="paperType"><span style="color: black;">课后作业</span>
                     	</c:if>
                       <c:if test="${paper.paperType=='PAPER_TYPE_MODEL'}">
                       		<input type="radio" value="PAPER_TYPE_PAST" name="paperType" class="paperType"><span style="color: black;">真题</span>
                     		<input type="radio" checked="checked" value="PAPER_TYPE_MODEL" name="paperType" class="paperType"><span style="color: black;">模拟题</span>
                     		<input type="radio" value="PRACTICE_AFTER_CLASS" name="paperType" class="paperType"><span style="color: black;">课后练习</span>
                        	<c:if test="${examOk == 'examOk'}">
                        		<input type="radio" value="PAPER_TYPE_EXAM" name="paperType" class="paperType"><span style="color: black;">题库考试试卷</span>
                     		</c:if>
                     		<input type="radio" value="PAPER_TYPE_HOMEWORK" name="paperType" class="paperType"><span style="color: black;">课后作业</span>
                     	</c:if>
                        <c:if test="${paper.paperType=='PRACTICE_AFTER_CLASS'}">
                        	<input type="radio" value="PAPER_TYPE_PAST" name="paperType" class="paperType"><span style="color: black;">真题</span>
                     		<input type="radio" value="PAPER_TYPE_MODEL" name="paperType" class="paperType"><span style="color: black;">模拟题</span>
                     		<input type="radio" checked="checked" value="PRACTICE_AFTER_CLASS" name="paperType" class="paperType"><span style="color: black;">课后练习</span>
                        	<c:if test="${examOk == 'examOk'}">
                        		<input type="radio" value="PAPER_TYPE_EXAM" name="paperType" class="paperType"><span style="color: black;">题库考试试卷</span>
                        	</c:if>
                        	<input type="radio" value="PAPER_TYPE_HOMEWORK" name="paperType" class="paperType"><span style="color: black;">课后作业</span>
                        </c:if>
                        <c:if test="${paper.paperType=='PAPER_TYPE_HOMEWORK'}">
                        	<input type="radio" value="PAPER_TYPE_PAST" name="paperType" class="paperType"><span style="color: black;">真题</span>
                     		<input type="radio" value="PAPER_TYPE_MODEL" name="paperType" class="paperType"><span style="color: black;">模拟题</span>
                     		<input type="radio" value="PRACTICE_AFTER_CLASS" name="paperType" class="paperType"><span style="color: black;">课后练习</span>
                        	<c:if test="${examOk == 'examOk' }">
                        		<input type="radio" value="PAPER_TYPE_EXAM" name="paperType" class="paperType"><span style="color: black;">题库考试试卷</span>
                        	</c:if>
                        	<input type="radio" checked="checked" value="PAPER_TYPE_HOMEWORK" name="paperType" class="paperType"><span style="color: black;">课后作业</span>
                        </c:if>
                        <c:if test="${examOk == 'examOk'}">
	                        <c:if test="${paper.paperType=='PAPER_TYPE_EXAM'}">
	                        	<input type="radio"	value="PAPER_TYPE_PAST" name="paperType" class="paperType"><span style="color: black;">真题</span>
	                     		<input type="radio" value="PAPER_TYPE_MODEL" name="paperType" class="paperType"><span style="color: black;">模拟题</span>
	                     		<input type="radio" value="PRACTICE_AFTER_CLASS" name="paperType" class="paperType"><span style="color: black;">课后练习</span>
	                     		<input type="radio" checked="checked" value="PAPER_TYPE_EXAM" name="paperType" class="paperType"><span style="color: black;">题库考试试卷</span>
	                     		<input type="radio" value="PAPER_TYPE_HOMEWORK" name="paperType" class="paperType"><span style="color: black;">课后作业</span>
	                        </c:if>
                        </c:if>
                    </c:if>
                    <c:if test="${exam == 'exam' }">
                    	<input type="radio" checked="checked" value="PAPER_TYPE_EXAM" name="paperType" class="paperType"><span style="color: black;">题库考试试卷</span>
                    </c:if>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">答题时间</span>
                    <span class="c-content">
                        <input type="text" maxlength="6" id="examTime" name="examTime" placeholder="单位：分钟" value="${paper.examTime}" class="number"/>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">总分数</span>
                    <span class="c-content">
                        <input type="text" maxlength="6" id="totalScore" name="totalScore" placeholder="单位：分" value="${paper.totalScore}" class="number"/>
                    </span>
                </p>
                <%--<p class="c">--%>
                    <%--<span class="c-title">所属区域</span>--%>
                    <%--<span class="c-content" id="region">--%>
                   		<%--<select id="prov">--%>
                   		<%--</select> --%>
                    <%--</span>--%>
                <%--</p>--%>
                <p class="c">
                    <span class="c-title">名师</span>
                    <span class="c-content">
                        <select name="teacherId" id="teacherList" style="width:100px;">
                            <option value="">--选择教师--</option>
                           <c:forEach items="${teachers }" var="t">
                               <c:if test="${teacher.id==t.id }">
                                   <option value="${t.id }" selected="selected">${t.name }</option>
                               </c:if>
                               <c:if test="${teacher.id!=t.id }">
                                   <option value="${t.id }">${t.name }</option>
                               </c:if>
                           </c:forEach>
                        </select>
                    </span>
                </p>
                 <p class="c">
                    <span class="c-title">包含题型</span>
                    <span class="c-content" style="width: 52%;">
                    	<input type="checkbox" value="TOPIC_TYPE_RADIO" name="topicType" class="topicType"><span style="color: black;">单选题</span>
                    	<input type="checkbox" value="TOPIC_TYPE_MULTIPLE" name="topicType" class="topicType"><span style="color: black;">多选题</span>
                    	<input type="checkbox" value="TOPIC_TYPE_UNDEFINED" name="topicType" class="topicType"><span style="color: black;">不定项</span>
                    	<input type="checkbox" value="TOPIC_TYPE_TRUE_FALSE" name="topicType" class="topicType"><span style="color: black;">判断题</span>
                    	<input type="checkbox" value="TOPIC_TYPE_FILLING" name="topicType" class="topicType"><span style="color: black;">填空题</span>
                    	<input type="checkbox" value="TOPIC_TYPE_ANSWER" name="topicType" class="topicType"><span style="color: black;">简答题</span>
                    	<input type="checkbox" value="TOPIC_TYPE_CASE" name="topicType" class="topicType"><span style="color: black;">材料题</span>
                    </span>
                    <span id="error" style="display: none;">至少选中一种题型</span>
                </p>
              <p class="c text-center">
             	<a href="javascript:void(0)" class="btn btn-primary save">保存并退出</a>
                <a href="javascript:void(0)" class="btn btn-primary save">保存并继续</a>
           		<a href="<%=rootPath %>/tikuPaper/toTikuPaper/${tikuId}" class="btn btn-default">取消</a>
             </p>
            </div>
        </div>
       </form>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<div>
	<input type="hidden" id="paperId" value="${paper.id}"/>
	<input type="hidden" id="subjectId" value="${subjectId}"/>
	<input type="hidden" id="tikuId" value="${tikuId}"/>
	<input type="hidden" id="HRegion" value="${paper.region}"/>
	<input type="hidden" id="HTopicType" value="${paper.containTopicType}"/>
	
</div>
<script type="text/javascript">
	$(function(){
		$(".tiHeader .navspace li>a:eq(1)").addClass("active");
		$selectMenu('tiku_header');
	});
</script>
</body>
</html>