<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>试题解析</title>
<%@include file="/common/import_tiku.jsp" %>
<style type="text/css">
	.que_left{cursor: pointer;}
	.que_l .active { background: #45abdb; color: #fff }
	li.active { background: #45abdb; color: #fff }
	.queContainer>iframe{
		display:none !important;
	}
	
</style>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/resolve/resolve.js"></script>
</head>
<body class="background-gray">
<input type="hidden" value="${exerId }" id="exerId"/>
<input type="hidden" value="${exer.categoryId }" id="cateId">
<input type="hidden" value="${userId }" id="userId">
<input type="hidden" value="${exer.subjectId }" id="subId">
	<div class="queContainer">
		
		<div class="que_container w1200">
			<div class="que_title clearfix">
				<span class="que_t1 fl que_btn que_t1_fast">试题解析</span>
				<c:if test="${paper != null }">
					<span class="que_t5 fl">${paper.paperName }</span>
				</c:if>
				<c:if test="${batch != null }">
					<span class="que_t5 fl">${batch.batchName }</span>
				</c:if>
				<%-- <c:if test="${exer.exerciseType == 'EXERCISE_TYPE_EXAM' }">
					<a href="<%=rootPath %>/usersCenter?url=myExam" class="que_back que_btn fr" style="width:186px;color: #fff;">返回个人中心-我的考试</a>
				</c:if>
				<c:if test="${exer.exerciseType != 'EXERCISE_TYPE_EXAM' }">
					<a href="javascript:;" class="que_back que_btn fr" style="color: #fff;">返回做题</a>
				</c:if> --%>
			</div>
			<div class="que_main clearfix">
			<c:if test="${exer != null && exer.totalTopic != 0 }">
				<div class="que_l fl">
					<div class="que_answer_infor">
						<div class="que_answer_hd">共<span>${exer.totalTopic }</span>题，错误<span>${exer.errorTopic == null ? 0 : exer.errorTopic}</span>题</div>
						<div class="que_answer_icon clearfix">
							<span><i class="que_noanswer"></i>未答</span>
							<span><i class="que_yesanswer"></i>正确</span>
							<span><i class="que_erroranswer"></i>错误</span>
						</div>
					</div>
					<div class="que_quenumber que_zuo">
						<c:forEach var="m" items="${map }">
							<div class="que_quenumber_cell">
								<c:if test="${m.key == 'TOPIC_TYPE_RADIO' }"><div class="que_quenumber_title" data-type="${m.key }">单选题（${fn:length(map[m.key]) }题）</div></c:if>
								<c:if test="${m.key == 'TOPIC_TYPE_MULTIPLE' }"><div class="que_quenumber_title" data-type="${m.key }">多选题（${fn:length(map[m.key]) }题）</div></c:if>
								<c:if test="${m.key == 'TOPIC_TYPE_UNDEFINED' }"><div class="que_quenumber_title" data-type="${m.key }">不定项选择题（${fn:length(map[m.key]) }题）</div></c:if>
								<c:if test="${m.key == 'TOPIC_TYPE_TRUE_FALSE' }"><div class="que_quenumber_title" data-type="${m.key }">判断题（${fn:length(map[m.key]) }题）</div></c:if>
								<c:if test="${m.key == 'TOPIC_TYPE_FILLING' }"><div class="que_quenumber_title" data-type="${m.key }">填空题（${fn:length(map[m.key]) }题）</div></c:if>
								<c:if test="${m.key == 'TOPIC_TYPE_ANSWER' }"><div class="que_quenumber_title" data-type="${m.key }">简答题（${fn:length(map[m.key]) }题）</div></c:if>
								<c:if test="${m.key == 'TOPIC_TYPE_CASE' }"><div class="que_quenumber_title" data-type="${m.key }">材料题（<span>${fn:length(map[m.key]) }</span>题）</div></c:if>
								<div class="que_quenumber_main">
									<ul class="clearfix">
									<c:forEach var="t" items="${map[m.key] }" varStatus="status">
									<c:if test="${t.topicType == 'TOPIC_TYPE_CASE' }">
										<c:forEach var="z" items="${t.topicList }">
											<c:if test="${z.correctFlag == 1 }">
												<li class="que_yes que_left" data-id="${z.id }" page="" data-type="${m.key }">${status.index + 1 }<i class="que_markanswer"></i></li>
											</c:if>
											<c:if test="${z.correctFlag == 0 }">
												<li class="que_no que_left" data-id="${z.id }" page="" data-type="${m.key }">${status.index + 1 }<i class="que_markanswer"></i></li>
											</c:if>
											<c:if test="${z.correctFlag == null }">
												<li class="que_default que_left" data-id="${z.id }" page="" data-type="${m.key }">${status.index + 1 }<i class="que_markanswer"></i></li>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${t.topicType != 'TOPIC_TYPE_CASE' }">
										<c:if test="${t.correctFlag == 1 }">
											<li class="que_yes que_left" data-id="${t.id }" page="" data-type="${m.key }">${status.index + 1 }<i class="que_markanswer"></i></li>
										</c:if>
										<c:if test="${t.correctFlag == 0 }">
											<li class="que_no que_left" data-id="${t.id }" page="" data-type="${m.key }">${status.index + 1 }<i class="que_markanswer"></i></li>
										</c:if>
										<c:if test="${t.correctFlag == null }">
											<li class="que_default que_left" data-id="${t.id }" page="" data-type="${m.key }">${status.index + 1 }<i class="que_markanswer"></i></li>
										</c:if>
									</c:if>
									</c:forEach>
									</ul>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="que_r fl detail">
					
				</div>
				</c:if>
				<c:if test="${exer == null || exer.totalTopic == 0 }">
				<link href="<%=rootPath%>/stylesheets/class.css" rel="stylesheet" type="text/css"/>
				<div class="wrap public-content" style="height: 400px;width:100%;">
				    <div class="contents empty"	style="height: 280px;">
				        <div class="icon"></div>
				        <p>
				        	还没有解析呢，快去做题吧
				       </p>
				    </div>
				</div>
				</c:if>
			</div>
		</div>
	</div>

	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
    <script type="text/javascript">
	$(document).ready(function(){
		$selectMenu('course_class_type');
	});
</script>
<!--  ajax加载中div结束 -->
</body>
</html>