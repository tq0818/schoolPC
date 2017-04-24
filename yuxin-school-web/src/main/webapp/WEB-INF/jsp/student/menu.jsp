<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<script type="text/javascript">
<!--
	function $select(index){
		$(".smbar").find(".nav").find("li").find("a").removeClass("active");
		$(".smbar").find(".nav").find("li").eq(index).find("a").addClass("active");
	}
//-->
$(function(){
	selectMenu(0);
})
</script>
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe613;</i>学员</a>
        <ul class="nav nav-left navspace">
            <li><a href="<%=rootPath %>/student" class="active">线下报名</a></li>
            <li><a href="<%=rootPath %>/student/toStuSearch">异动</a></li>
            <li><a href="<%=rootPath %>/student/toSearch">补费</a></li>
            <li><a href="<%=rootPath %>/studentAgentMaterial/stuMaterial">报考材料</a></li>            
            <li><a href="<%=rootPath %>/fee/page/urgeFee">催缴</a></li>            
            <li><a href="<%=rootPath %>/exam/registerPage">代报考</a></li>
            <li><a href="javascript:void(0);">远程结费</a></li>
            <li><a href="<%=rootPath %>/payOrder/index" >线上订单排课</a></li>
            <li><a href="javascript:void(0);" >学员通知</a></li>
        </ul>
    </div>
</div>