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
    <title>试卷作业</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/utils.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/classWork.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/homeWork.css">
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/tools.js"></script>
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
<body class="q-box">
<jsp:include page="/WEB-INF/jsp/menu/menu_teach.jsp"></jsp:include>
<div class="u-wrap operate">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">批改作业</h2>
            <a href="javascript:;" class="returnBut">返回</a>
            <span class="line"></span>
        </div>
       <div class="workListSearch workStatus">
           <span>${hw.courseName }</span>
           <span>${hw.lessonName }</span>
           <span>课次作业状态：
               <c:choose>
		           <c:when test="${empty hsc}">
		               <em>待提交</em>
			       </c:when>
			       <c:when test="${hsc.status==1}">
			           <em>待批改</em>
			       </c:when>
			       <c:when test="${hsc.status==3}">
			           <em>已批改</em>
			       </c:when>
		           <c:otherwise></c:otherwise>
	           </c:choose>
           </span>
       </div>
        <div class="workStantus clear">
            <h2 class="titleWork">${paper.paperName }</h2>
            <div class="JobDescript clear">
                <h3>作业描述：</h3>
                <div class="descriptWork">${hw.desciption}</div>
                <p class="consultWork">
                    <span>
                        <em>作业创建时间:</em>
                        <em><fmt:formatDate value="${hw.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/></em>
                    </span>
                    <!--<button>查阅试卷</button>-->
                </p>
            </div>
            <c:if test="${!empty hsc}">
            	<div class="JobDescript clear studentWork noBorder">
            		<c:choose>
            			<c:when test="${!empty frontVo.name }">
            				<h3>${frontVo.name }同学的作业：</h3>
            			</c:when>
            			<c:when test="${!empty frontVo.username }">
            				<h3>${frontVo.username }同学的作业：</h3>
            			</c:when>
            			<c:when test="${!empty frontVo.mobile }">
            				<h3>${frontVo.mobile }同学的作业：</h3>
            			</c:when>
            		</c:choose>
	                
	                <p class="consultWork">
	                    <span>
	                        <em>完成作业时间:</em>
	                        <em><fmt:formatDate value="${hsc.completeTime}" pattern="yyyy/MM/dd HH:mm:ss"/></em>
	                    </span>
	                    <c:if test="${hsc.status==1}">
	                    	<button class="checkHomework" hscId="${hsc.id }" hwId="${hw.id }">批改试卷</button>
	                    </c:if>
	                </p>
	            </div>
            </c:if>
            <c:if test="${hsc.status==3}">
	            <div class="JobDescript clear">
	                <h3>已批改的试卷：</h3>
	                <div class="descriptWork">${htr.content }</div>
	                <p class="consultWork">
	                    <span>
	                        <em>批改时间:</em>
	                        <em><fmt:formatDate value="${htr.readTime}" pattern="yyyy/MM/dd HH:mm:ss"/></em>
	                    </span>
	                    <button class="checkHomework" hscId="${hsc.id }" hwId="${hw.id }">查看试卷</button>
	                </p>
	            </div>
            </c:if>
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
<script type="text/javascript">
	var rootPath = "<%=rootPath %>";
	$selectSubMenu('home_work');
	$(".checkHomework").on("click",function(e){
		window.location.href=rootPath+"/homeworkTeacherRead/checkHomeWork?studentCompleteId="+$(this).attr("hscId")+"&hwId="+$(this).attr("hwId");
	});
	$(".returnBut").on("click",function(e){
		window.location.href = rootPath + '/homeworkStudentComplete/gotoHomeworkStudentCompletePage/' + $.getUrlParam('urlSuffix') + '?param=' + $.getUrlParam('param');
	});
</script>
</body>
</html>