<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<%=rootPath %>/images/favicon.ico" type="image/x-icon" />
    <title>附件作业</title>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/utils.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/classWork.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/homeWork.css">
	<style type="text/css">
		.studentWork {
		   padding: 20px 0;
		   border-top: 0px solid #e0e0e0;
		   border-bottom: 1px solid #e0e0e0;
		   margin: 10px 0;
		}
		.JobDescript {
		   padding: 5px 0;
		   border-bottom: 1px solid #e0e0e0;
		}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_teach.jsp"></jsp:include>
	<div class="u-wrap operate">
	    <div class="mainbackground">
	        <div class="heading">
	            <h2 class="h5">批阅作业</h2>
	            <a href="javascript:;" class="returnBut readattachmentjob_goback">返回</a>
	            <span class="line"></span>
	        </div>
	       <div class="workListSearch workStatus">
	           <span>${homework.courseName }</span>
	           <span>${homework.lessonName }</span>
	           <span>课次作业状态：${statusHtml }</span>
	       </div>
	        <div class="workStantus clear">
	            <div class="JobDescript clear">
	                <h3>作业描述：</h3>
	                <div class="descriptWork">${homework.desciption }</div>
	                <p class="consultWork">
	                    <span>
	                        <em>作业创建时间:</em>
	                        <em><fmt:formatDate value="${homework.createTime }" type="both"/></em>
	                    </span>
	                    <c:if test="${!empty homework.resourceId  }">
	                  	 	<button class="readattachmentjob_download" data-ids="${homework.resourceId }">下载附件</button>
	                    </c:if>
	                </p>
	            </div>
	            <div class="homework_contents">
	            	
	            </div>
	            <div class="MarkingWork none" >
	                <div class="alertDialog markeWork">
	                    <h3 class="diaLogTil clear marking">
	                        <div class="fl">
	                         	   批阅作业
	                        </div>
	                        <div class="fl rightCon rightConBut">
                            <h4 class="clear stat">
                                <a href="javascript:void(0);"  class="but-py st">批阅</a>
                                <a href="javascript:void(0);"  class="but-hf st">回复</a>
                            </h4>
                            <p class="express">说明:批阅后学员将不可再做作业，回复操作后需要学员再次提交作业</p>
                        </div>
	                    </h3>
	                    <div class="diaLogTil clear mTop20">
	                        <div class="fl">
	                         	   作业评语 :
	                        </div>
	                        <div class="fl rightCon workRightCon">
	                            <textarea class="userTextCon" id="t_content" maxlength="4000"></textarea>
	                        </div>
	                    </div>
	                    <div class="diaLogTil clear mTop20" >
	                        <div class="fl workEnc">
	                         	   作业附件 :
	                        </div>
	                        <div class="fl rightCon uploadWork homework_resource" id="homework_resource">
	                            <p class="show">
					                <span>
					                    <i class="iconfont iYun">&#xe6e1;</i>
					                    <a href="javascript:;" class="fileBut" >
					                     	   上传附件
											<input type="file" id="doctype" style="border:none;" name="doc"  onchange="javascript:docChange();">
					                    </a>
					                </span>
					                <span id="homework_result"></span>
	                            </p>
	                        </div>
	                    </div>
	                    <div class="diaLogTil clear mTop20">
	                        <div class="fl workEnc">
	                          	  附件名称 :
	                        </div>
	                        <div class="fl rightCon">
	                            <span class="EncName" id="fileName"></span>
	                            <span class="homework_resource_name" ></span>
	                        </div>
	                    </div>
	                    <div class="diaLogTil clear mTop20 scores">
	                        <div class="fl workEnc workScore">
	                          	  作业评分 :
	                        </div>
	                        <div class="fl rightCon">
	                            <input type="text" placeholder="输入评分" value="" class="inScore"  id="t_score" maxlength="5">
	                        </div>
	                    </div>
	                </div>
	                <div class="incoreBut">
	                	<button class="readattachmentjob_save">保存</button>
	            	</div>
	            </div>
	            
	        </div>
	    </div>
	</div>
	<!--选择试卷-->
	
	<!--背影遮罩-->
	<div class="add-classes-bg hide" style="_display: block; width: 100%; height:100%; background: rgba(0,0,0,.2); position: fixed;left:0; top:0;"></div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
	    <p><i></i>加载中,请稍后...</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<!--  ajax加载中div结束 -->
	<input type="hidden" id="hiddenId" data-resourceid="${resourceId}" data-moduleid="${moduleId}" data-homeworkid="${homework.id}" data-stuid="${stuId}" data-stuname="${stuName}"  data-status="${status}" data-companyid="${companyId }" data-answerkey="${hscId }" data-htrid="${htrId }" data-htrresourceid="${htrResourceId }" data-htrresourcename="${htrResourceName }">
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/plupload.full.min.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/moxie.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.2.1/jquery.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/homework/attachmentStudentHomework.js"></script>
</body>
</html>