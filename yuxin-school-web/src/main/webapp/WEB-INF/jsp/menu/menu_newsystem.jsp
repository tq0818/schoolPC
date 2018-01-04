<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
 <style>
	 .course_managelist .subentry span,.system_managelist .subentry em{
		 vertical-align:middle;
	 }
	 .course_managelist .subentry em{
		 display:inline-block;
		 background:url("<%=rootPath%>/images/new-icon.png") left top no-repeat;
		 width:24px;
		 height:11px;
		 margin-left:10px;
	 }
 </style>
<!-- 二级导航 -->
<div class="left-side">
        <div class="left-side-title">
        	<em>课程配置</em>
        	<span class="iconfont return-pic hcancle">&#xe650;</span>
        </div>
        <c:choose>
        	<c:when test="${sessionScope.company.memberLevel == 12 or sessionScope.company.memberLevel == 13}">
		        <ul id="course_manage" class="course_managelist">
		        <%-- <c:if test="${empty sessionScope.COURSE_VALIDATE or sessionScope.COURSE_VALIDATE == 1 }">
		            <li class="subentry" code="course_validate" mark="/classManage/manageCourseValidaty">课程有效期</li>
		        </c:if> --%>
		        <c:if test="${empty sessionScope.SIGNUP_NEWS or sessionScope.SIGNUP_NEWS == 1 }">
		            <li class="subentry" code="signup_news" mark="/classManage/signup_news">报名通知</li>
		        </c:if>
		        <c:if test="${empty sessionScope.COURSE_PL or sessionScope.COURSE_PL == 1 }">
		            <li class="subentry" code="course_pl" mark="/classManage/manage_classpl">课程评论</li>
		        </c:if>
		       	<c:if test="${empty sessionScope.COURSE_WD or sessionScope.COURSE_WD == 1 }">
		            <li class="subentry" code="course_wd" mark="/Question/queAnsSet">课程问答</li>
		        </c:if>
		       <%-- <c:if test="${empty sessionScope.CLASS_NOS or sessionScope.CLASS_NOS == 1 }">
		            <li class="subentry" code="class_nos" mark="/classManage/manage_classno">多班号管理</li>
		        </c:if>--%>
		       <%-- <c:if test="${empty sessionScope.COURSE_LIST or sessionScope.COURSE_LIST == 1 }">
		            <li class="subentry" code="course_list" mark="/classManage/manage_lesson">课表查看权限</li>
		        </c:if>--%>
		        <c:if test="${empty sessionScope.COURSE_CHAPTERANDLECTURE or sessionScope.COURSE_CHAPTERANDLECTURE == 1 }">
		            <li class="subentry" code="course_chapterandlecture" mark="/classManage/manage_chapOrlecName">课程章节</li>
		        </c:if>
		        <%--<c:if test="${empty sessionScope.COURSE_VIDEO_AUTH or sessionScope.COURSE_VIDEO_AUTH == 1 }">
		            <li class="subentry" code="course_video_auth" mark="/classManage/manage_courseVideo">课程观看权限</li>
		        </c:if>--%>
		        <c:if test="${empty sessionScope.COURSE_BUY_AUTH or sessionScope.COURSE_BUY_AUTH == 1 }">
		            <li class="subentry" code="course_buy_auth" mark="/classManage/manage_courseBuy">课程购买限制</li>
		        </c:if>
		        <%--<c:if test="${empty sessionScope.COURSE_PROTOCOL_CONFIG or sessionScope.COURSE_PROTOCOL_CONFIG == 1 }">
		            <li class="subentry" code="course_protocol_config" mark="/classManage/manage_protocol">课程协议</li>
		        </c:if>--%>
		<!--             <li class="subentry" code="course_lable" mark="/classManage/manage_courseLable">课程标签管理</li> -->
				<li class="subentry" code="course_detail" mark="/classManage/courseDetailSet">课程模板配置</li>
		       </ul>
        	</c:when>
        	<c:otherwise>
		        <ul id="course_manage" class="course_managelist">
		            <li class="subentry" code="course_validate" mark="/classManage/manageCourseValidaty">课程有效期</li>
		            <li class="subentry" code="signup_news" mark="/classManage/signup_news">报名通知</li>
		            <li class="subentry" code="course_pl" mark="/classManage/manage_classpl">课程评论</li>
		            <c:if test="${sessionScope.isAreaSchool1 eq 0}">  
		            <li class="subentry" code="course_wd" mark="/Question/queAnsSet">课程问答</li>
		           	</c:if>
		           <%-- <li class="subentry" code="class_nos" mark="/classManage/manage_classno">多班号管理</li>--%>
		            <%--<li class="subentry" code="course_list" mark="/classManage/manage_lesson">课表查看权限</li>--%>
		            <c:if test="${sessionScope.isAreaSchool1 eq 0}"> 
		            <li class="subentry" code="course_chapterandlecture" mark="/classManage/manage_chapOrlecName">课程章节</li>
		            </c:if>
		            <%--<li class="subentry" code="course_video_auth" mark="/classManage/manage_courseVideo">课程观看权限</li>--%>
		            <li class="subentry" code="course_buy_auth" mark="/classManage/manage_courseBuy">课程购买限制</li>
		           <%-- <li class="subentry" code="course_protocol_config" mark="/classManage/manage_protocol">课程协议</li>--%>
		           <%-- <li class="subentry" code="course_homework_inform" mark="/classManage/manage_homework_inform" id="home_work">课后作业</li>--%>
		            <li class="subentry" code="course_detail" mark="/classManage/courseDetailSet">课程模板配置</li>
		       </ul>
        	</c:otherwise>
        </c:choose>
</div>
<script>
$(document).ready(function(){
	 //点击左侧菜单
	 $("#course_manage").on('click','li',function(){
		 var url=$(this).attr("mark");
		 if($(this).hasClass('to')) return;
		 $(this).addClass('to');
		 window.location.href=rootPath+url;
	 });
	 //返回
	 $(".hcancle").on('click',function(){
		 window.location.href=rootPath+"/company/companyService";
	 });
	 $selectSubMenu('org_service');
	 //课后作业是否开启
	 $.ajax({
		 url:rootPath + "/company/queryServiceOpenFlag",
		 data:{"serviceString":"SERVICE_HOMEWORK"},
		 type:"post",
		 dataType:"json",
		 success: function(jsonData){
			 if(jsonData.result == "success"){
				 if(jsonData.flag!=1){
					 $("#home_work").remove();
				 }
			 }
		 }
	 })
});
function $selectSubMenus(code) {
	$(".overflow").find(".course_managelist").find("li").each(function() {
		if ($(this).attr("code") == code) {
			$(this).addClass("active").siblings("li").removeClass("active");
		}
	})
 }

</script>