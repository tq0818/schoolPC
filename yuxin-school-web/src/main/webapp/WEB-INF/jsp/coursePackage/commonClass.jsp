<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/plus/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/common/utils.js"></script>

<div class="left-side">
        <div class="left-sides-title courseCancle">
            <em>返回</em>
        </div>
        <div class="left-sides-title pop">
            <em class="iconfont">&#xe68b;</em>
        </div>
         <ul class="menuList_choose">
         <c:choose>
         	<c:when test="${empty classPackage }">
         	 	<li class="subentry" mark="/classPackage/manageBaseInfo/add" ids="${classPackage.id }" code="baseCode"><span class="iconfont" title="基本信息">&#xe682;</span><em>基本信息</em></li>
         	</c:when>
         	<c:otherwise>
         		 <li class="subentry" mark="/classPackage/manageBaseInfo/edit?id=${classPackage.id }" ids="${classPackage.id }" code="baseCode"><span class="iconfont" title="基本信息">&#xe682;</span><em>基本信息</em></li>
         	</c:otherwise>
         </c:choose>
            <li class="subentry" mark="/classPackage/manageClassPackage/${classPackage.id }/edit" ids="${classPackage.id }" code="manageCode"><span class="iconfont" title="课程管理">&#xe685;</span><em>课程管理</em></li>
            <li class="subentry bl" mark="/classPackage/setDetail/${classPackage.id }/edit" ids="${classPackage.id }" code="detailCode"><span class="iconfont" title="课程包详情">&#xe684;</span><em>课程包详情</em></li>
            <li class="lines"></li>
            <li class="subentry" mark="/classPackage/studentList/${classPackage.id }" ids="${classPackage.id }" code="studentsCode"><span class="iconfont" title="学员管理">&#xe680;</span><em>学员管理</em></li>
            <li class="subentry" mark="/classPackage/orderManage/${classPackage.id }/edit" ids="${classPackage.id }" code="ordersCode"><span class="iconfont" style="font-size: 18px;" title="订单管理">&#xe6a4;</span><em>订单管理</em></li>
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
		var ids=$(this).attr("ids");
		if(ids && ids!=""){
			window.location.href=rootPath+mark;
		}else{
			$.msg("您还没有完善基本信息");
		}
		
	});
	//点击返回
	$(".courseCancle").on('click',function(){
		window.location.href=rootPath+"/classPackage/list";
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