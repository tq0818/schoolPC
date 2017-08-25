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
    
        .ques_type {
		    background: #9bbbdb;
		    padding: 3px;
		    color: #fff;
		    display: inline-block;
		    margin-top: 15px;
		}
		
		
        .ques_list .ques,.ques_list .answer-list{margin:15px 0 ;}
        .ques_list .answer-list{}
        .ques_list .answer{color:red;float:right;width: 15%;}
        .ques_list .choice{margin-right:20px}
        .btn-info{float:right;margin:10px 0;width: 25%;}
        .ques-child{     margin-left: 30px;  }
.ques-childlist{    clear: both;
    margin-left: 30px;;}
.ques-childlist .answer p,.ques-infocon p,.answer-list.answer-tkt p{display: inline-block;}

.ques-ans{overflow: hidden}
.ques-infocon{
    float: left;}
.answer-list.answer-tkt{
    float: right;
    width: 15%;
}
 .answer-list.answer-tkt .answer{    float: initial;width: auto;}
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
             	
             </p>
            </div>
        </div>
       </form>
         <div class="heading">
             <h2 class="h5">试卷题目</h2>
             <span class="line"></span>
        </div>
        <c:forEach var = "tm" items="${topicMap}" varStatus="tmStatus">
            <div>
               <c:if test="${tm.key == 'TOPIC_TYPE_RADIO' }">
            		<div class="ques_type">单选题</div>
        		</c:if>
        		<c:if test="${tm.key == 'TOPIC_TYPE_MULTIPLE' }">
            		<div class="ques_type">多选题</div>
        		</c:if>
        		<c:if test="${tm.key == 'TOPIC_TYPE_TRUE_FALSE' }">
            		<div class="ques_type">判断题</div>
        		</c:if>
        		<c:if test="${tm.key == 'TOPIC_TYPE_ANSWER' }">
            		<div class="ques_type">简答题</div>
        		</c:if>
        		<c:if test="${tm.key == 'TOPIC_TYPE_UNDEFINED' }">
            		<div class="ques_type">不定项</div>
        		</c:if>
        		<c:if test="${tm.key == 'TOPIC_TYPE_FILLING' }">
            		<div class="ques_type">填空题</div>
        		</c:if>
        		<c:if test="${tm.key == 'TOPIC_TYPE_CASE' }">
            		<div class="ques_type">材料题</div>
        		</c:if>
        		
           <ul class="ques_list">
           <c:forEach var="topic" items="${tm.value}" varStatus="topicStatus">
            <c:if test="${tm.key eq 'TOPIC_TYPE_CASE' }">
             <div class="ques ques-infocon">
	               (${topicStatus.index+1})&nbsp;&nbsp;&nbsp;${topic.topicName }
	            </div>
               <c:forEach var="childTopic" items="${topic.topicList}" varStatus="childTopicStatus">
                   <li>
	                <div class="ques-childlist">
                        <div class="ques ques-child">
                            (${childTopicStatus.index+1})&nbsp;&nbsp;&nbsp;${childTopic.topicName }
                        </div>
                        <div class="answer-list">
                            <c:forEach var="childOption" items="${childTopic.optionList }">
                             <span class="choice">${childOption.optionNo }  ${childOption.optionName}</span>
                            </c:forEach>
                            <div class="answer">正确答案：${childTopic.answer}</div>
                       </div>
	                </div>
	               
	            </li>
               </c:forEach>
            </c:if>
            <c:if test="${tm.key ne 'TOPIC_TYPE_CASE' }">
	            <li>
	                <div class="ques-ans">
                        <c:choose>
                        <c:when test="${tm.key == 'TOPIC_TYPE_FILLING' || tm.key == 'TOPIC_TYPE_ANSWER'  }">
                            <div class="ques ques-infocon">
                        </c:when>
                       <c:otherwise>
                            <div class="ques">
                         </c:otherwise>
                    </c:choose>

	                	(${topicStatus.index+1})&nbsp;&nbsp;&nbsp;${topic.topicName }
	                </div>
                        <c:choose>
                            <c:when test="${tm.key == 'TOPIC_TYPE_FILLING' || tm.key == 'TOPIC_TYPE_ANSWER' }">
                                <div class="answer-list answer-tkt">
                            </c:when>
                            <c:otherwise>
                                <div class="answer-list">
                            </c:otherwise>
                        </c:choose>
	                	<c:forEach var="option" items="${topic.optionList }">
	                     <span class="choice">${option.optionNo }  ${option.optionName}</span>
	                 	</c:forEach>
	                    <div class="answer">正确答案：${topic.answer}</div>
	                </div>
	                </div>
	               
	            </li>
            </c:if>
            
           </c:forEach>
        </ul>
            </div>
        </c:forEach>
    </div>
    <div class="btn-info fr">
           <a href="javascript:;" onclick="paperCommit('${paper.id}','publish')" class="btn btn-primary ">审核通过</a>
           <a href="javascript:;" onclick="paperCommit('${paper.id}','no')" class="btn btn-primary ">审核不通过</a>
           <a href="<%=rootPath %>/tikuPaper/toTikuPaper/${tikuId}" class="btn btn-default">取消</a>
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
	
	function paperCommit(paperId,commit){
		$.ajax({
			url : "<%=rootPath %>/tikuPaper/commitAudite",
			type:"post",
			data:{"paperId":paperId,"commit":commit},
			dataType:"JSON",
			success:function(data){
				$.msg("试卷操作成功",1000,function(){
					window.location.href="<%=rootPath%>/tikuPaper/toTikuPaper/${tikuId}";
				});
				
			}
		});
	}
</script>
</body>
</html>