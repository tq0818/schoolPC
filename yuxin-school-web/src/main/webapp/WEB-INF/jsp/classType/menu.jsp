<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<script>
$(function(){
	selectMenu(1);
})
function $select(index){
	$(".smbar").find(".nav").find("li").find("a").removeClass("active");
	$(".smbar").find(".nav").find("li").eq(index).find("a").addClass("active");
}
</script>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe61b;</i>课程</a>
        <ul class="nav nav-left navspace">
            <li><a href="<%=rootPath %>/classType/showClassTypePage">课程</a></li>
            <li><a href="<%=rootPath %>/classModule/toTeach" class="active">课程单元</a></li>
            <li><a href="<%=rootPath %>/classModuleLesson/classes" >排课</a></li>
        </ul>
    </div>
</div>