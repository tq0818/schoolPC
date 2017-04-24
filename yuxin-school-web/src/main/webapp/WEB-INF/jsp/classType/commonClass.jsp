<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/plus/jquery.cookie.js"></script>

<div class="left-side">
        <div class="left-sides-title courseCancle">
            <em>返回</em>
        </div>
        <div class="left-sides-title pop">
            <em class="iconfont">&#xe68b;</em>
        </div>
         <ul class="menuList_choose">
         	<c:if test="${lable=='live'|| lable=='face' }">
	            <li class="subentry" code="baseCode" mark="/editClass/editClassBaseInfo/${ct.id }/${lable }"><span class="iconfont" title="基本信息">&#xe682;</span><em>基本信息</em></li>
	            <li class="subentry" code="moduleCode" mark="/editClass/editClassModule/${ct.id }/${lable }"><span class="iconfont ap" style="font-size: 20px;" title="课程内容">&#xe68a;</span><em>课程内容</em></li>
	            <li class="subentry" code="listCode" mark="/editClass/editCourseList/${ct.id }/${lable }"><span class="iconfont" title="排课表">&#xe683;</span><em>排课表</em></li>
	            <li class="subentry bl" code="detailCode" mark="/editClass/editClassVideoInfo/${ct.id }/${lable }"><span class="iconfont" title="课程详情">&#xe684;</span><em>课程详情</em></li>
	            <li class="lines"></li>
	            <li class="subentry t1" code="studentsCode" mark="/classStu/studentList/${ct.id }/${lable}"><span class="iconfont" title="学员管理">&#xe680;</span><em>学员管理</em></li>
	            <li class="subentry" code="classResource" mark="/classModuleLesson/classesResource/${ct.id }/${lable}"><span class="iconfont" title="课程资料">&#xe688;</span><em>课程资料</em></li>
	             <c:if test="${ct.liveFlag==1}">
	            	  <li class="subentry t1" code="statisticsCode" mark="/companyLiveStaticDetail/companyLiveStaticDetailList/${ct.id}/${lable}"><span class="iconfont" title="直播上课统计">&#xe6b1;</span><em>直播上课统计</em></li>
	            </c:if>
         	</c:if>
         	<c:if test="${lable=='video' }">
	            <li class="subentry" code="baseCode" mark="/editClass/editClassBaseInfo/${ct.id }/${lable}"><span class="iconfont" title="基本信息">&#xe682;</span><em>基本信息</em></li>
	            <li class="subentry" code="videoCode" mark="/editClass/editClassVideoInfo/${ct.id }/${lable}"><span class="iconfont" title="视频课">&#xe685;</span><em>视频课</em></li>
	            <li class="subentry bl" code="detailCode" mark="/editClass/editCourseDetail/${ct.id }/${lable}"><span class="iconfont" title="课程详情">&#xe684;</span><em>课程详情</em></li>
	            <li class="lines"></li>
	             <li class="subentry t1" code="studentsCode" mark="/classStu/studentList/${ct.id }/${lable}"><span class="iconfont" title="学员管理">&#xe680;</span><em>学员管理</em></li>
	              <li class="subentry" code="classResource" mark="/classModuleLesson/classesResource/${ct.id }/${lable}"><span class="iconfont" title="课程资料">&#xe688;</span><em>课程资料</em></li>
         	</c:if>
         	<c:if test="${lable=='togther' }">
         		<li class="subentry" code="baseCode" mark="/editClass/editClassBaseInfo/${ct.id }/togther"><span class="iconfont" title="基本信息">&#xe682;</span><em>基本信息</em></li>
	            <li class="subentry" code="videoCode" mark="/editClass/editClassVideoInfo/${ct.id }/togther"><span class="iconfont" title="视频课">&#xe685;</span><em>视频课</em></li>
	            <li class="subentry" code="moduleLive" mark="/editClass/editClassModuleLive/${ct.id }/togther"><span class="iconfont ap" style="font-size: 20px;" title="直播课">&#xe69f;</span><em>直播课</em></li>
	            <li class="subentry" code="moduleFace" mark="/editClass/editClassModuleFace/${ct.id }/togther"><span class="iconfont ap" style="font-size: 20px;" title="面授课">&#xe6a0;</span><em>面授课</em></li>
	            <c:if test="${ct.liveFlag==1 || ct.faceFlag==1 }">
	            	<li class="subentry" code="listCode" mark="/editClass/editCourseList/${ct.id }/togther"><span class="iconfont" title="排课表">&#xe683;</span><em>排课表</em></li>
	            </c:if>
	            <li class="subentry bl" code="detailCode" mark="/editClass/editCourseDetail/${ct.id }/togther"><span class="iconfont" title="课程详情">&#xe684;</span><em>课程详情</em></li>
	            <li class="lines"></li>
	             <li class="subentry t1" code="studentsCode" mark="/classStu/studentList/${ct.id }/togther"><span class="iconfont" title="学员管理">&#xe680;</span><em>学员管理</em></li>
	              <li class="subentry" code="classResource" mark="/classModuleLesson/classesResource/${ct.id }/togther"><span class="iconfont" title="课程资料">&#xe688;</span><em>课程资料</em></li>
	            <c:if test="${ct.liveFlag==1}">
	            	  <li class="subentry t1" code="statisticsCode" mark="/companyLiveStaticDetail/companyLiveStaticDetailList/${ct.id}/${lable}"><span class="iconfont" title="直播上课统计">&#xe6b1;</span><em>直播上课统计</em></li>
	            </c:if>
         	</c:if>
         	<c:if test="${lable=='remote' || lable=='other' }">
         		<li class="subentry" code="baseCode" mark="/editClass/editClassBaseInfo/${ct.id }/remote"><span class="iconfont" title="基本信息">&#xe682;</span><em>基本信息</em></li>
            	<li class="subentry bl" code="detailCode" mark="/editClass/editClassModule/${ct.id }/remote"><span class="iconfont" title="课程详情">&#xe684;</span><em>课程详情</em></li>
            	<li class="lines"></li>
            	 <li class="subentry t1" code="studentsCode" mark="/classStu/studentList/${ct.id }/remote"><span class="iconfont" title="学员管理">&#xe680;</span><em>学员管理</em></li>
            	 <li class="subentry" code="classResource" mark="/classModuleLesson/classesResource/${ct.id }/remote"><span class="iconfont" title="课程资料">&#xe688;</span><em>课程资料</em></li>
         	</c:if>
<!--             <li class="subentry"><span class="iconfont">&#xe687;</span><em>老师管理</em></li> -->
<!--             <li class="subentry"><span class="iconfont">&#xe689;</span><em>作业批改</em></li> -->
<!--             <li class="subentry"><span class="iconfont ap" style="font-size: 22px;">&#xe686;</span><em>安排考试</em></li> -->
        </ul>
</div>
<input type="hidden" id="courseUseMethod" value="simple"/>
<script type="text/javascript">
 $(document).ready(function(){
     if($.cookie("beOff")==1){
         $(".left-side .pop").parent(".left-side").css({
             width : "3%",
             transition : "width 0s"
         });
         $(".left-side .pop").parent(".left-side").find("li.subentry span").css({
             left : "32%"
         });
         //$(this).parent(".left-side").find("li.subentry .ap").addClass("mg3");
         $(".left-side .pop").parent(".left-side").find("li.subentry em").hide();

         $(".left-side .pop").parents(".u-wrap").find(".right-side").css({
             width : "96%",
             transition : "width 0s"
         });
         $(".left-side .pop").removeClass("pop").addClass("push");
     }
	//点击菜单列表
	$(".menuList_choose").on('click','li.subentry',function(){
		var mark=$(this).attr("mark");
		window.location.href=rootPath+mark;
	});
	//点击返回
	$(".courseCancle").on('click',function(){
// 		if($("#courseUseMethod").val()=="simple"){
// 			window.location.href=rootPath+"/simpleClasses/showClassTypePage";
// 		}else{
			window.location.href=rootPath+"/classType/showClassTypePage";
		//}
	});
	//缩放
	 $(".left-side").on("click",".pop",function(){
         $(this).parent(".left-side").css({
             width : "3%",
             transition : "width 0.3s"
         });
         $(this).parent(".left-side").find("li.subentry span").css({
             left : "32%"
         });
         //$(this).parent(".left-side").find("li.subentry .ap").addClass("mg3");
         $(this).parent(".left-side").find("li.subentry em").hide();

         $(this).parents(".u-wrap").find(".right-side").css({
             width : "96%",
             transition : "width 0.3s"
         });
         $(this).removeClass("pop").addClass("push");
         $.cookie("beOff","1",{path:'/'});
     });
     $(".left-side").on("click",".push",function(){
         $(this).parents(".u-wrap").find(".right-side").css({
             width : "81%",
             transition : "width 0.3s"
         });
         $(this).parent(".left-side").css({
             width : "18%",
             transition : "width 0.3s"
         });
         $(this).parent(".left-side").find("li.subentry span").css({
             left : "64px",
             transition : "left 0.3s"
         });
         $(this).parent(".left-side").find("li.subentry em").show();
         $(this).removeClass("push").addClass("pop");
         $.cookie("beOff","0",{path:'/'});
     });
 });
 function $chooseMenu(code) {
	 $(".menuList_choose").find("li.subentry").each(function(){
		 if ($(this).attr("code") == code) {
				$(this).addClass("active").siblings("li").removeClass("active");
		 }
	 });
 }
</script>