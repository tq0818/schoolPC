<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>课程基本信息</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/system/order.js"></script>--%>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/berkeley.js"></script>--%>
    <style type="text/css">
        .head-div {
            position: relative;
            margin-top: 15px;
            padding: 3px 8px;
        }

        .font-size {
            font-size: 14px;
            margin-left: 10px;
            margin-right: 11px;
        }

    </style>
    <%--tob--%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap admin overflow baseSchoolBg">

    <div class="heading baseSchoolHead">
        <h2 class="h5">课程基本信息</h2>
        <span class="line"></span>
    </div>
    <div class="user-list informationCourseContent">
        <div class="informationContentFirst">
        	<input type="hidden" id="classTypeId" value="${ct.id }"/>
        	<input type="hidden" id="lable" value="${lable}"/>
            <h4>基本信息</h4>
            <span class="line"></span>
            <ul>
                <li>
                    <label for="">分类</label>
                    <span>${ct.itemOneName }</span>
                </li>
                <li>
                    <label for="">学段</label>
                    <span>${ct.itemSecondName }</span>
                </li>
                <li>
                    <label for="">学科</label>
                    <span>${ct.itemThirdName }</span>
                </li>
                <li>
                    <label for="" class="knowledgePoint">知识点</label>
                    <span>${ct.itemFourthName }</span>
                </li>
                <li>
                    <label for="" class="courseName">课程名称</label>
                    <span>${ct.name }</span>
                </li>
                <li>
                    <label for="">定价</label>
                    <span>${ct.originalPrice }</span>
                </li>
                <li>
                    <label for="" class="concessionalRate">优惠价</label>
                    <span>${ct.realPrice }</span>
                </li>
                <li>
                    <label for="">类型</label>
                    <span>
                    	<c:choose>
                    		<c:when test="${'CLASS_TYPE_NOMAL' eq ct.typeCode }">常规班型</c:when>
                    		<c:when test="${'CLASS_TYPE_REMOTE' eq ct.typeCode }">其他</c:when>
                    	</c:choose>
                    </span>
                </li>
            </ul>
        </div>
        <div class="informationContentSecond">
            <h4>
            	<c:choose>
               		<c:when test="${'live' eq lable}">排课信息</c:when>
               		<c:when test="${'video' eq lable}">视频信息</c:when>
               		<c:otherwise>课程信息</c:otherwise>
               	</c:choose>
            </h4>
            <span class="line"></span>
	        <c:choose>
               		<c:when test="${'live' eq lable}">
               			<c:if test="${not empty liveModuleList }">
	               			<c:forEach items="${liveModuleList }" var='module' varStatus="m">
		               			<ul>
					                <li>
					                    <p>
					                        <label for="">第${m.index+1 }章</label><span style="margin-left: 10px;">${module.name }</span>
					                    </p>
					                     <c:if test="${not empty module.classmoudleNo and not empty module.classmoudleNo.classModuleLessons }">
					                    	<c:forEach items="${module.classmoudleNo.classModuleLessons }" var='lesson' varStatus="l">
							                    <ul>
							                        <li class="secondContent">
							                            <p>
							                                <label for="">第${l.index+1 }节</label><span style="margin-left: 10px;">${lesson.lessonName }</span>
							                            </p>
							                        </li>
							                    </ul>
						                    </c:forEach> 
					                    </c:if>
					                </li>
					            </ul>
	               			</c:forEach>
               			</c:if>	
               		</c:when>
               		<c:when test="${'video' eq lable}">
               			<c:if test="${not empty videoChapterList }">
	               			<c:forEach items="${videoChapterList }" var='chapter' varStatus="c">
	               					<ul>
						                <li>
						                    <p>
						                        <label for="">第${c.index+1 }章</label><span style="margin-left: 10px;">${chapter.chapterName }</span>
						                    </p>
						                    <c:if test="${not empty chapter.lectureAndTests }">
							                   <c:set var="num" value="0"></c:set>
						                    	<c:forEach items="${chapter.lectureAndTests }" var='lesson' >
							                    	<c:if test="${lesson.type eq '1' }">
							                    	 <c:set var="num" value="${num+1 }"></c:set>
								                    	<ul>
									                        <li class="secondContent">
									                            <p>
									                                <label for="">第${num}节</label><span style="margin-left: 10px;">${lesson.name }</span>
									                            </p>
									                        </li>
									                    </ul>
							                    	</c:if>
							                    </c:forEach>
						                    </c:if>
						                </li>
						            </ul>
	               			</c:forEach>
               			</c:if>	
               		</c:when>
            </c:choose>
            <a href="<%=rootPath %>/branchSchool/queryClassType" class="btn btn-default" style="margin-left: 131px;margin-top: 20px;">返回</a>
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

</body>
</html>