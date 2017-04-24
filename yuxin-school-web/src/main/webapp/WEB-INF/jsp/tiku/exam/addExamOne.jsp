<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <%@include file="/decorators/import.jsp" %>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/classedit.css"/>

	<script src="<%=rootPath %>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath %>/javascripts/tiku/exam/classedit.js"></script>
	<script src="<%=rootPath%>/javascripts/datetime/WdatePicker.js"></script>
	<script src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/exam/addExamOne.js"></script>
	<style type="text/css">
		.exam-info-box p span {
		    float: left;
		    width: 90px;
		    height: 25px;
		    line-height: 25px;
		    margin-right: 10px;
		    font-size: 14px;
		    color: #666;
		}
	</style>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_tiku.jsp"%>
<div class="u-wrap company clear">
        <div class="mainbackground nopadding paddno">
            <div class="heading-box">
                <div class="heading">
                    <h2 class="h5">考试信息</h2>
                    <span class="line"></span>
                </div>
            </div>
            <input type="hidden" value="${exam.id }" id="examId"/>
	            <input type="hidden" value="${exam.itemOneId }" id="itemOneId"/>
	            <input type="hidden" value="${exam.itemSecondId }" id="itemSecondId"/>
	            <input type="hidden" value="${exam.classTypeId }" id="classTypeId"/>
            <div class="class-steps clear">
                <div class="class-step active">01 考试信息<div class="blue-horn"></div></div>
                <div class="class-step">02 考试内容</div>
            </div>
            <div class="exam-info-box">
                <p class="clear exam-scope">
                    <span>考试范围：</span>
                    <c:if test="${empty exam.examRange || exam.examRange == 1}">
	                    <em class="oneItem active" data-examRange="1" style="margin-left: 0px;">学科</em>
	                    <em class="twoItem" data-examRange="2">学科小类</em>
	                    <em class="course" data-examRange="3">课程</em>
                    </c:if>
                    <c:if test="${exam.examRange == 2}">
	                    <em class="oneItem" data-examRange="1" style="margin-left: 0px;">学科</em>
	                    <em class="twoItem active" data-examRange="2">学科小类</em>
	                    <em class="course" data-examRange="3">课程</em>
                    </c:if>
                    <c:if test="${exam.examRange == 3}">
	                    <em class="oneItem" data-examRange="1" style="margin-left: 0px;">学科</em>
	                    <em class="twoItem" data-examRange="2">学科小类</em>
	                    <em class="course active" data-examRange="3">课程</em>
                    </c:if>
                </p>
                <p class="clear">
                    <span></span>
                    <select id="oneItem" style="width: 200px;">
                    	<option value="">----</option>
                    </select>
                    <select id="twoItem" style="width: 200px;display:none;">
                    	<option value="">----</option>
                    </select>
                    <select id="course" style="width: 200px;display:none;">
                    	<option value="">----</option>
                    </select>
                </p>
                <p class="clear">
                    <span>考试名称：</span>
                    <input id="examName" type="text" placeholder="15个字以内" style="width: 440px;" maxlength="15" value="${exam.examName }"/>
                </p>
                <p class="clear">
                    <span>考试说明：</span>
                    <textarea id="introduction" placeholder="120个字以内" style="width: 440px;height: 150px;" maxlength="120">${exam.introduction }</textarea>
                </p>
                <p class="clear">
                	<input type="hidden" value="${imgpath }" id="imgUrl"/>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;封面：</span>
                    <span class="pic-box">
                    	<c:if test="${!empty exam.cover }">
                    		<img src="${imgpath }/${exam.cover}"/>
                    	</c:if>
                    	<c:if test="${empty exam.cover }">
                    		<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image"/>
                    	</c:if>
                    </span>
                </p>
                <p class="clear up-btn-box">
                    <span></span>
                    <span class="up-btn" id="coverUrl" data-cover="${exam.cover}">${!empty exam.cover ? '更改封面' : '上传封面'}</span>
                    <input type="file" style="display: block;opacity:0;z-index:97" id="coverImg" name="imgFile" class="up-input" accept="image/*" onchange="javascript:coverImgChange();">
                    <span id="coverHint"></span>
                </p>
                <p class="clear">
                    <span>考试方式：</span>
                    <input type="radio" id="free" name="examMode" checked="checked" value="1"/><label for="free">自由考试</label>
                    <%-- <c:if test="${empty exam.examMode || exam.examMode == 1}">
                    	<input type="radio" id="free" name="examMode" checked="checked" value="1"/><label for="free">自由考试</label>
                    	<input type="radio" id="together" name="examMode" value="2"/><label for="together">集中考试</label>
                    
                    </c:if>
                    <c:if test="${exam.examMode == 2}">
                    	<input type="radio" id="free" name="examMode" value="1"/><label for="free">自由考试</label>
                    	<input type="radio" id="together" name="examMode" checked="checked" value="2"/><label for="together">集中考试</label>
                    </c:if> --%>
                </p>
                <%-- <p class="clear jz" style="display:none;">
                    <span></span>
                    <input class="Wdate" style="width: 124px;" placeholder="开始时间" readonly="readonly" type="text" value="<fmt:formatDate value="${exam.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="startTime"/>
                    <i class="calendar"></i>
                    <em>设置考试开始时间</em>
                </p>
                <p class="clear jz" style="display:none;">
                    <span></span>
                    <input class="Wdate" style="width: 124px;" placeholder="结束时间" readonly="readonly" type="text" value="<fmt:formatDate value="${exam.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="endTime"/>
                    <i class="calendar"></i>
                    <em>设置考试结束时间</em>
                </p> --%>
                <p class="clear">
                    <span>试题解析：</span>
                    <c:if test="${empty exam.scanAnalysis || exam.scanAnalysis == 'NOT_SACN_ANALYSIS'}">
                    	<input type="radio" name="scanAnalysis" checked="checked" value="NOT_SACN_ANALYSIS"/><label for="free">交卷后不允许查看解析</label>
                    	<input type="radio" name="scanAnalysis" value="ALLOW_SACN_ANALYSIS"/><label for="together">交卷后允许查看解析</label>
                    </c:if>
                    <c:if test="${exam.scanAnalysis == 'ALLOW_SACN_ANALYSIS'}">
                    	<input type="radio" name="scanAnalysis" value="NOT_SACN_ANALYSIS"/><label for="free">交卷后不允许查看解析</label>
                    	<input type="radio" name="scanAnalysis" checked="checked" value="ALLOW_SACN_ANALYSIS"/><label for="together">交卷后允许查看解析</label>
                    </c:if>
                </p>
                <p class="clear">
                    <span>考试资格：</span>
                    <c:if test="${empty exam.allowUserExam || exam.allowUserExam == 'FREE_EXAM'}">
                    	<input type="radio" name="allowUserExam" checked="checked" value="FREE_EXAM"/><label for="together">自由申请考试</label>
                    	<input type="radio" name="allowUserExam" value="STUDY_ALL_LESSION"/><label for="free">全部课程学完才可申请</label>
                    </c:if>
                    <c:if test="${exam.allowUserExam == 'STUDY_ALL_LESSION'}">
                    	<input type="radio" name="allowUserExam" value="FREE_EXAM"/><label for="together">自由申请考试</label>
                    	<input type="radio" name="allowUserExam" checked="checked" value="STUDY_ALL_LESSION"/><label for="free">全部课程学完才可申请</label>
                    </c:if>
                </p>
                <p class="clear zy">
                    <span>考试次数：</span>
                    <input type="text" value="${exam.examCount != null ? exam.examCount : 0}" id="examCount" style="margin-right: 10px" onkeydown="onlyNum();"/>次
                    <span style="color:red;width: 130px;float: none;padding-left: 20px;">* 0 代表不限制次数</span>
                </p>
                <input type="hidden" value="${count }" id="etcount"/>
                <p class="clear">
                    <span>通过分数：</span>
                    <input type="text" value="${exam.passScore != null ? exam.passScore : 60 }" id="passScore" style="margin-right: 10px" onkeydown="onlyNum();"/>分
                </p>
                <p class="clear">
                    <span>通过提示：</span>
                    <textarea style="width:440px;height:150px;" id="successWord">${exam.successWord }</textarea>
                </p>
                <p class="clear">
                    <span>未通过提示：</span>
                    <textarea style="width:440px;height:150px;" id="failWord">${exam.failWord }</textarea>
                </p>
            </div>
            <div class="add-exam-box clear">
                <div class="btn btn-primary saveNext" data-status="${types }">保存，去添加考试内容</div>
                <div class="btn btn-cancel cancel">取消</div>
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