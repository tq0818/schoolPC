<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
<title>考试统计</title>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classes.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classedit.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/newresource.css" />
<script type="text/javascript"> var rootPath = "<%=rootPath%>"</script>
</head>
<style>
	.L-exam-line div{text-align:center; line-height:84px; margin-top:0;}
	.L-exam-line .td1{width:25%;}
	.L-exam-line .td2{width:32%;}
	.L-exam-line .td2{width:10%;}
	.L-exam-line .td3 p{_line-height:40px; margin-top:14px;}
	.L-exam-line .td6 a{line-height:84px; display:inline-block; width:136px; height:26px; background:#07bbee; color:#fff;}

</style>

<body>
	<jsp:include page="/WEB-INF/jsp/tiku/tikuHeader.jsp"></jsp:include>
	<div class="u-wrap company clear">
		<div class="mainbackground nopadding">
			<div class="heading">
				<h2 class="h5">考试统计</h2>
				<span class="line"></span>
				<div class="new-set" onclick="location.href='<%=rootPath%>/tikuPaper/toTikuPaper/${tikuId}'">返回</div>
			</div>
			<div class="r-list L-r-list">
				<div class="sc-select searchBook clear">
					<dl class="bookTitle">
	                    <dt>试卷名称：<span>${statistics.paperName}</span></dt>
	                    <dd>试卷作答人数：<span>${statistics.total}人</span></dd>
	                    <dd>最高分：<span>${statistics.max}分</span></dd>
	                    <dd>最低分：<em>${statistics.min}分</em></dd>
	                    <dd>平均分：<em>${statistics.avg}分</em></dd>
	                </dl>
				</div>
			<div>
				<div class="tab-info">
					<span class="tab-type active" href = ".teacherContent">概括</span>
					<span class="tab-type" href=".teacherContent2">详情</span>
					<div class="tab-search teacherContent">
						<select name="eduArea" id="eduArea">
							<option value="">请选择区域</option>
							<c:forEach items="${areas}" var="area" >
								<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
							</c:forEach>
						</select>

						<select name="eduSchool" id="eduSchool" data-id="${student.eduSchool}">
							<option value="">请选择学校</option>
						</select>
						<select id="eduYear" name="eduYear" style="width:150px;">
							<option value="">请选择入学年份</option>
							<c:forEach items="${years}" var="year">
								<option value="${year}">${year}</option>
							</c:forEach>
						</select>
						<select name="eduClass" id="eduClass">
							<option value="">请选择班级</option>
							<c:forEach begin="1" end="30" varStatus="index">
								<option value="${index.index}">${index.index}班</option>
							</c:forEach>
						</select>
						<div class="bookBut">
							<a href="javascript:;" class="btn btn-mini btn-primary searchContents">搜索</a>
							<a id="exportExcle" href="javascript:void(0);" class="btn btn-mini btn-primary">导出数据</a>
						</div>
					</div>
					</div>
				</div>
				<div class="content-show">
				<div class="r-list L-r-list-table teacherContent" id="teacherContent" style="min-height: 350px;position: relative;">
					<table id="tableList" class="table table-hover table-center table-list L-table">
						<colgroup>
						  <col width="10%">
						  <col width="10%">
						  <col width="10%">
						  <col width="30%">
						  <col width="15%">
						  <col width="10%">
						  <col width="15%">
						</colgroup>
						<tr class="top-tr">
							<td>用户名称</td>
							<td>学员名称</td>
							<td>区域</td>
							<td>学校</td>
							<td>班级</td>
							<td>当前试卷分数</td>
							<td>考试时间</td>
						</tr>
						<tr><td colspan="5">暂无数据</td></tr>
					</table>
					<div class="pages pagination"></div>
				</div>
				<div class="teacherContent2" style="display: none;">
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
						             <div class="ques">
							               (${topicStatus.index+1})&nbsp;&nbsp;&nbsp;${topic.topicName }
							            </div>
						               <c:forEach var="childTopic" items="${topic.topicList}" varStatus="childTopicStatus">
						                   <li>
							                <div class="ques-ans">
							                <div class="ques">
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
										<div class="ques-ans  paperstatistics-list">
											<div class="ques" topicId="${topic.id}">
												<span class="topic-tit">(${topicStatus.index+1})&nbsp;&nbsp;&nbsp;${topic.topicName }</span>
												<div class="topic-count fr">
													<select id="topic_${topic.id}">
														<option>统计</option>
													</select>
												</div>
												<i class="solid-title"></i>
											</div>
											<c:if test="${topic.optionList==null || topic.optionList.size()==0 }">
												<div class="answer">正确答案：${topic.answer}</div>
											</c:if>
											<div class="answer-list">
												<c:forEach var="option" items="${topic.optionList }">
													<c:set var="optionNo" value="${option.optionNo}"/>
													<c:choose>
														<c:when test="${fn:indexOf(topic.answer,option.optionNo)!=-1}">
															<p class="choice-list">
																<span class="choice fl" style="color:red" title="option.optionName">${option.optionNo }  ${option.optionName}</span><span class="choice-person fl">选此答题人数：${option.topicOptionAnswerNum}</span>
															</p>
														</c:when>
														<c:otherwise>
													<p class="choice-list">
															<span class="choice fl">${option.optionNo }  ${option.optionName}</span><span class="choice-person fl">选此答题人数：${option.topicOptionAnswerNum}</span>
													</p>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</div>

										</div>

									</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
				</div>

			</div>
		</div>
	</div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath%>/javascripts/classedit.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/laydate/laydate.js"></script>
	<script src="<%=rootPath%>/javascripts/tiku/paper/paperStatisticsIndex.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<input type="hidden" value="${examId }" id="examId" />
	<script type="text/javascript">
	$(function(){
		$(".tiHeader .navspace li>a:eq(1)").addClass("active");
		$selectMenu('tiku_header');
	})
	</script>
	
	<input type="hidden" id ="paperId" value="${paperId}">
	<input type="hidden" value="${tikuId}" id="tikuId"/>
	<form id="exportForm"  method="post">
		<input type="hidden" name="page" id="page"/>
		<input type="hidden" name="tikuExamId" id="tikuExamId"/>
		<input type="hidden" name="startTime" id="startTime" value="2000-1-1"/>
		<input type="hidden" name="endTime" id="endTime"  value="2099-1-1"/>
		<input type="hidden" name="userMobile" id="userMobile"/>
		<input type="hidden" name="exerciseId" id="tikuPaperId"/>
		<input type="hidden" name="status" id="status"/>
	</form>
</body>
</html>