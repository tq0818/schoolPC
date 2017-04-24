<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>

<c:forEach items="${list }" var="classModule">
<c:if test="${classModule.teachMethod=='TEACH_METHOD_FACE' || classModule.teachMethod=='TEACH_METHOD_LIVE'}">
<p class="public clear face">
     <span class="left clear">
         <span class="c-title">课程单元</span>
         <span class="c-content">${classModule.name }</span>
         <span class="c-title">课程单元类型</span>
         <span class="c-content">${wx:dictCode2Name(classModule.teachMethod) }</span>
     </span>
     <span class="right clear">
         <span class="class-number">可选班号</span>
         <c:if test="${fn:length(classModule.classModuleNos) ==0}">
			<span class="block">  
			<span class="c-content"><em>此模块没有安排课程，请督促安排</em></span>
			</span>
		</c:if> 
		 <c:if test="${fn:length(classModule.classModuleNos) >0}">
         <c:forEach items="${classModule.classModuleNos }" var="classModuleNo">
	         <span class="block" teachmethod="${classModule.teachMethod }" moduleId="${classModule.id}" id="${classModuleNo.id}" campus="${classModuleNo.campusId}">
	             <span class="c-content"><b>${classModuleNo.name }</b></span>
	         </span>
         </c:forEach>
         </c:if>
     </span>
 </p>
 </c:if>
 <c:if test="${classModule.teachMethod=='TEACH_METHOD_VIDEO' }">
 <p class="long video" teachmethod="${classModule.teachMethod }" moduleId="${classModule.id}" id="${classModuleNo.id}" campus="${classModuleNo.campusId}">
     <span class="c-title">课程单元</span>
     <span class="c-content">${classModule.name }</span>
     <span class="c-title">课程单元类型</span>
     <span class="c-content">录播</span>
     <span class="c-title">总课时</span>
     <span class="c-content">${classModule.totalClassHour }课时</span>
 </p>
 </c:if>

 </c:forEach>
 
 
<script>
$(function(){
	$(".modules").find(".block").click(function(){
		$(this).parent().find(".active").removeClass("active");
		$(this).addClass("active");
	})
})
</script>              