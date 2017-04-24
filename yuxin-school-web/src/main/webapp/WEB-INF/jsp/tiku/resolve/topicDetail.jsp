<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String rootPath=request.getContextPath();
%>
<div class="que_answer_infor">
	<c:if test="${topic.topicType == 'TOPIC_TYPE_RADIO' && topic.parentId == 0}">
		<div class="que_answer_hd"><span>单项选择题</span>
<%-- 			<c:if test="${score != null }">本题${score }分</c:if>
 --%>		</div>
		<div class="que_answer_bd"><%-- 如下${fn:length(topic.option) }个选项中选择一个正确答案， --%>
&nbsp;<%-- 			<c:if test="${count != null && score != null }">一共${count }题，每题${score }分。</c:if>
 --%>		</div>
	</c:if>
	<c:if test="${topic.topicType == 'TOPIC_TYPE_MULTIPLE' && topic.parentId == 0}">
		<div class="que_answer_hd"><span>多选题</span>
<%-- 			<c:if test="${score != null }">本题${score }分</c:if>
 --%>		</div>
		<div class="que_answer_bd">
&nbsp;<%-- 			<c:if test="${count != null && score != null }">一共${count }题，每题${score }分。</c:if>
 --%>		</div>
	</c:if>
	<c:if test="${topic.topicType == 'TOPIC_TYPE_UNDEFINED' && topic.parentId == 0 }">
		<div class="que_answer_hd"><span>不定项选择题</span>
<%-- 			<c:if test="${score != null }">本题${score }分</c:if>
 --%>		</div>
		<div class="que_answer_bd">
&nbsp;<%-- 			<c:if test="${count != null && score != null }">一共${count }题，每题${score }分。</c:if>
 --%>		</div>
	</c:if>
	<c:if test="${topic.topicType == 'TOPIC_TYPE_TRUE_FALSE' && topic.parentId == 0 }">
		<div class="que_answer_hd"><span>判断题</span>
<%-- 			<c:if test="${score != null }">本题${score }分</c:if>
 --%>		</div>
		<div class="que_answer_bd">
&nbsp;<%-- 			<c:if test="${count != null && score != null }">一共${count }题，每题${score }分。</c:if>
 --%>		</div>
	</c:if>
	<c:if test="${topic.topicType == 'TOPIC_TYPE_FILLING' && topic.parentId == 0 }">
		<div class="que_answer_hd"><span>填空题</span>
<%-- 			<c:if test="${score != null }">本题${score }分</c:if>
 --%>		</div>
		<div class="que_answer_bd">
&nbsp;<%-- 			<c:if test="${count != null && score != null }">一共${count }题，每题${score }分。</c:if>
 --%>		</div>
	</c:if>
	<c:if test="${topic.topicType == 'TOPIC_TYPE_ANSWER' && topic.parentId == 0 }">
		<div class="que_answer_hd"><span>简答题</span>
<%-- 			<c:if test="${score != null }">本题${score }分</c:if>
 --%>		</div>
		<div class="que_answer_bd">
&nbsp;<%-- 			<c:if test="${count != null && score != null }">一共${count }题，每题${score }分。</c:if>
 --%>		</div>
	</c:if>
	<c:if test="${topic.topicType == 'TOPIC_TYPE_CASE' || topic.parentId != 0 }">
		<div class="que_answer_hd"><span>材料题</span>
<%-- 			<c:if test="${score != null }">本题${score }分</c:if>
 --%>		</div>
		<div class="que_answer_bd"><%-- 如下${fn:length(topic.option) }个选项中选择一个正确答案， --%>
&nbsp;<%-- 			<c:if test="${count != null && score != null }">一共${count }题，每题${score }分。</c:if>
 --%>		</div>
	</c:if>
</div>
<div class="que_question_introduce">${parentTopic.topicName }</div>
<div class="que_question">
	<div class="que_question_number">第${page }题
		<span class="que_start_box">
			<span class="que_start"></span>
			<c:if test="${topic.difficulty == 'DIFFICULT_TYPE_MIDDLE' }">
				<span class="que_start"></span>
				<span class="que_start"></span>
			</c:if>
			<c:if test="${topic.difficulty == 'DIFFICULT_TYPE_DIFFICULT' }">
				<span class="que_start"></span>
				<span class="que_start"></span>
				<span class="que_start"></span>
				<span class="que_start"></span>
			</c:if>
		</span>
		<c:if test="${topic.topicType != 'TOPIC_TYPE_ANSWER' }">
			<c:if test="${answer.correctFlag == 1 }">
				<span class="que_result_true"></span>
			</c:if>
			<c:if test="${answer.correctFlag == 0 }">
				<span class="que_result_false"></span>
			</c:if>
		</c:if>
		<%-- <c:if test="${colId == null }">
			<a href="javascript:;" class="que_collection que_ok" data-id="${topic.id }">收藏</a>
		</c:if>
		<c:if test="${colId != null }">
			<a href="javascript:;" class="que_collection_cancel que_cancel" data-id="${colId }">取消收藏</a>
		</c:if> --%>
	</div>
	<c:if test="${parentTopic == null }">
	<div class="que_question_introduce">${topic.topicName }</div>
	<div class="que_question_option que_question_explain" style="min-height: 0px;">
		<c:forEach var="p" items="${topic.option }">
		<div class="que_option_cell">
			<span>${p.optionNo }&nbsp;&nbsp;${p.optionName }</span>
		</div>
		</c:forEach>
	</div>
	<div class="que_option_forme" style="min-height: 0px;">
		<div class="que_goodinfor">
			<div class="que_goodinfor_item">
				<span class="que_goodinfor_title">学员答案</span>
				<span class="clred">${answer.userAnswer }</span>
			</div>
			<div class="que_goodinfor_item">
				<span class="que_goodinfor_title">正确答案</span>
				<span class="clgreen">${topic.answer }</span>
			</div>
			<div class="que_goodinfor_item">
				<span class="que_goodinfor_title">解析</span>
				<span class="que_explain_true right_answer" style="padding-left: 15px;margin-top: 0px;">${topic.analyseWord }</span>
			</div>
			<!-- <div class="que_goodinfor_item">
				<span class="que_goodinfor_title">语音解析</span>
				<span class="que_voice"></span>
			</div> -->
		</div>
		<div class="que_option_ft que_explain_ft">
			<a href="javascript:;" class="que_btn queNextBtn fr">查看下一题</a>
		</div>
	</div>
	</c:if>
	<c:if test="${parentTopic != null }">
	<div class="que_question_introduce">${topic.topicName }</div>
	<div class="que_question_option que_question_explain" style="min-height: 0px;">
		<c:forEach var="p" items="${topic.option }">
		<div class="que_option_cell">
			<span>${p.optionNo }&nbsp;&nbsp;${p.optionName }</span>
		</div>
		</c:forEach>
	</div>
	<div class="que_option_forme" style="min-height: 0px;">
		<div class="que_goodinfor">
			<div class="que_goodinfor_item">
				<span class="que_goodinfor_title">您的答案</span>
				<span class="clred">${answer.userAnswer }</span>
			</div>
			<div class="que_goodinfor_item">
				<span class="que_goodinfor_title">正确答案</span>
				<span class="clgreen">${topic.answer }</span>
			</div>
			<div class="que_goodinfor_item">
				<span class="que_goodinfor_title">解析</span>
				<span class="que_explain_true right_answer" style="padding-left: 15px;margin-top: 0px;">${topic.analyseWord }</span>
			</div>
			<!-- <div class="que_goodinfor_item">
				<span class="que_goodinfor_title">语音解析</span>
				<span class="que_voice"></span>
			</div> -->
		</div>
		<div class="que_option_ft que_explain_ft">
			<a href="javascript:;" class="que_btn queNextBtn fr">查看下一题</a>
		</div>
	</div>
	</c:if>
</div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/tiku/collectToggle.js"></script>
<script type="text/javascript">
	$(function(){
		var answer = $(".right_answer").html();
		answer = answer.replace(/&amp;ldquo;/g,"“");
		answer = answer.replace(/&amp;rdquo;/g,"”");
		$(".right_answer").html(answer);
		$(".queNextBtn").click(function(){
			continueTopic();
		});
		
		for(var i = 0;i < $(".que_option_cell img").length; i++){
			if($(".que_option_cell").width() < $(".que_option_cell img:eq("+i+")")
					.width()){
				$(".que_option_cell img:eq("+i+")").css({width:'100%',heigth:'auto'});
			}
		}
	});
</script>
<style>
	.clgreen p{padding-left: 80px;}
	.clgreen p:first-child{margin-top: -22px}
	.clred p{padding-left: 80px;}
	.clred p:first-child{margin-top: -22px}
</style>