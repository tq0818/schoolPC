<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html >
<html>
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
	<title>课后作业批改</title>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/utils.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css" />
	<%-- <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/classWork.css"> --%>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/homeWork.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/readHomework.css">
	
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/tools.js"></script>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/menu/menu_teach.jsp"></jsp:include>
<c:choose>
<c:when  test="${!empty msg }">
	<script type="text/javascript">
		$.msg("${msg }",function(){
			history.back();
		});
	</script>
</c:when>
<c:otherwise>
	<div class="u-wrap operate">
	    <div class="mainbackground">
	        <div class="heading">
	            <h2 class="h5">批改试卷</h2>
	            <a href="javascript:void(0);" class="returnButton returnBut ritPos" hwId="${hw.id }" hscId="${hwStudentComplete.id }">返回</a>
	           	<c:if test="${empty homeworkTeacherRead.score }">
		            <a href="javascript:void(0);" class="returnBut" id="complete">完成批改</a>
	            </c:if>
	            <span class="line"></span>
	        </div>
	       <div class="workListSearch workStatus">
	           <span>${hw.courseName }</span>
	           <span>${hw.lessonName }</span>
	           <span>总分数：<i>${paper.totalScore }</i>分</span>
	       </div>
	        <div class="workStantus">
	            <table class="testTable">
	                <colgroup>
	                    <col width="10%" align="right">
	                    <col width="18%">
	                    <col width="18%">
	                    <col width="18%">
	                    <col width="18%">
	                    <col width="18%">
	                </colgroup>
	                <thead>
	                    <tr>
	                        <th></th>
	                        <th>题数</th>
	                        <th>正确</th>
	                        <th>错误</th>
	                        <th>未作答</th>
	                        <th>分数</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:if test="${wrData.TOPIC_TYPE_RADIO.total != '0'}">
	                        <tr>
	                            <td><span class="testTitle">单选题</span></td>
	                            <td><span>${wrData.TOPIC_TYPE_RADIO.total}</span></td>
		                        <td><span class="colorBlue">${wrData.TOPIC_TYPE_RADIO.right}</span></td>
		                        <td><span class="colorRed">${wrData.TOPIC_TYPE_RADIO.wrong}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_RADIO.none}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_RADIO.score}</span></td>
	                            </td>
	                        </tr>
	                    </c:if>
	                    <c:if test="${wrData.TOPIC_TYPE_MULTIPLE.total != '0'}">
	                    	<tr>
	                            <td><span class="testTitle">多选题</span></td>
	                            <td><span>${wrData.TOPIC_TYPE_MULTIPLE.total}</span></td>
		                        <td><span class="colorBlue">${wrData.TOPIC_TYPE_MULTIPLE.right}</span></td>
		                        <td><span class="colorRed">${wrData.TOPIC_TYPE_MULTIPLE.wrong}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_MULTIPLE.none}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_MULTIPLE.score}</span></td>
	                            </td>
	                        </tr>
	                    </c:if>
	                    <c:if test="${wrData.TOPIC_TYPE_UNDEFINED.total != '0'}">
	                    	<tr>
	                            <td><span class="testTitle">不定项选择</span></td>
	                            <td><span>${wrData.TOPIC_TYPE_UNDEFINED.total}</span></td>
		                        <td><span class="colorBlue">${wrData.TOPIC_TYPE_UNDEFINED.right}</span></td>
		                        <td><span class="colorRed">${wrData.TOPIC_TYPE_UNDEFINED.wrong}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_UNDEFINED.none}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_UNDEFINED.score}</span></td>
	                            </td>
	                        </tr>
	                    </c:if>
	                    <c:if test="${wrData.TOPIC_TYPE_TRUE_FALSE.total != '0'}">
	                    	<tr>
	                            <td><span class="testTitle">判断题</span></td>
	                            <td><span>${wrData.TOPIC_TYPE_TRUE_FALSE.total}</span></td>
		                        <td><span class="colorBlue">${wrData.TOPIC_TYPE_TRUE_FALSE.right}</span></td>
		                        <td><span class="colorRed">${wrData.TOPIC_TYPE_TRUE_FALSE.wrong}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_TRUE_FALSE.none}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_TRUE_FALSE.score}</span></td>
	                            </td>
	                        </tr>
	                    </c:if>
	                    <c:if test="${wrData.TOPIC_TYPE_FILLING.total != '0'}">
	                    	<tr>
	                            <td><span class="testTitle">填空题</span></td>
	                            <td><span>${wrData.TOPIC_TYPE_FILLING.total}</span></td>
		                        <td><span class="colorBlue">${wrData.TOPIC_TYPE_FILLING.right}</span></td>
		                        <td><span class="colorRed">${wrData.TOPIC_TYPE_FILLING.wrong}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_FILLING.none}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_FILLING.score}</span></td>
	                            </td>
	                        </tr>
	                    </c:if>
	                    <c:if test="${wrData.TOPIC_TYPE_ANSWER.total != '0'}">
	                    	<tr>
	                            <td><span class="testTitle">简答题</span></td>
	                            <td><span>${wrData.TOPIC_TYPE_ANSWER.total}</span></td>
		                        <td><span class="colorBlue">${wrData.TOPIC_TYPE_ANSWER.right}</span></td>
		                        <td><span class="colorRed">${wrData.TOPIC_TYPE_ANSWER.wrong}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_ANSWER.none}</span></td>
		                        <td><span>${wrData.TOPIC_TYPE_ANSWER.score}</span></td>
	                            </td>
	                        </tr>
	                    </c:if>
	                </tbody>
	            </table>
	         </div>
	        <div class="paperStantus">
	            <ul>
	            	<c:if test="${wrData.TOPIC_TYPE_ANSWER.total != '0'}">
		                <li class="clear listTitleB">
		                    <h4 class="leftTitle">简答题</h4>
		                    <ul class="jiandaTopic rightCon">
		                    <c:forEach var="item" items="${wrData.TOPIC_TYPE_ANSWER.tidList}" varStatus="status">
		                    	<c:choose> 
									<c:when test="${!fn:contains(hwPaperDetailIdList,item)}">   
								    	<li tid="${item}"><span>${status.index+1}</span></li>
									</c:when> 
									<c:otherwise>   
										<li class="active" tid="${item}"><span>${status.index+1}</span></li>
									</c:otherwise> 
								</c:choose> 
							</c:forEach>  
		                    </ul>
		                </li>
	                </c:if>
	            </ul>
	        </div>
	        <div class="topicContent">
		        <div class="cailiaoContent paperContent">
		            <div class="paperConTitle">
		                <h5>材料内容</h5>
		                <span class="hr"></span>
		            </div>
		            <div class="topicTitle paperCon">
		                <p>材料题内容</p>
		            </div>
		        </div>
		        <div class="paperContent">
		            <div class="topicIndex paperConTitle">
		                <h5>第2题</h5>
		                <span class="hr"></span>
		            </div>
			        <dl class="answerDescript">
		                <dt>简答题题干</dt>
		                <dd class="clear">
		                    <h4 class="colorBlu">学员答案</h4>
		                    <div class="studentAnswer paperCon">
		                    </div>
		                </dd>
		                <dd class="clear">
		                    <h4 class="colorGre">正确答案</h4>
		                    <div class="rightAnswer paperCon">
		                    </div>
		                </dd>
		                <dd class="clear">
		                    <h4 class="colorGre">题目解析</h4>
		                    <div class="anlysis paperCon">
		                    </div>
		                </dd>
		            </dl>
	            </div>
	        </div>
	        <div class="paperContent">
	            <div class="paperConTitle">
	                <h5>教师批改</h5>
	                <span class="hr"></span>
	            </div>
	            <div class="paperCon">
	                <div class="ConPaper clear">
	                    <h5 class="leftPaperTi">分数 :</h5>
	                    <p class="rightPaperCon">
	                        <input type="number" id="score">
	                        <span class="inPrompt">请输入小于题目总分数的数值</span>
	                    </p>
	                </div>
	                <div class="ConPaper clear">
	                    <h5>评阅 :</h5>
	                    <p>
	                        <textarea id="content"></textarea>
	                    </p>
	                </div>
	            </div>
	        </div>
	        <div class="pagerButton">
	            <!-- <button class="btDef">取消</button> -->
	            <c:if test="${empty homeworkTeacherRead.score}">
	            	<button class="submitRead btSuc">保存</button>
	            </c:if>
	        </div>
	    </div>
	</div>
	<!--填写评语-->
	<div class="loading lp-units-loading" style="display:none">
	    <p><i></i>加载中,请稍后...</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<!--  ajax加载中div结束 -->
	
	<data id="data" style="display:none" tid="${homeworkTeacherRead.id}" sid="${hwStudentComplete.id}" homeworkId="${hwStudentComplete.homeworkId}" stuId="${hwStudentComplete.stuId}" exerciseId="${hwStudentComplete.exerciseId}"></data>
	
	<script type="text/javascript" src="<%=rootPath%>/javascripts/homework/readHomework.js"></script>
</c:otherwise>
</c:choose>

</body>
</html>