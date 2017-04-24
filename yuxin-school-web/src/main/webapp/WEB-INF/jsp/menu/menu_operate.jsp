<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
        
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe6c5;</i>运营</a>
        <ul class="nav nav-left navspace">
            <%-- <shiro:hasPermission name="course_qa">  
            <li code="course_qa"><a href="<%=request.getContextPath() %>/Question/questionIndex">课程问答</a></li>
            </shiro:hasPermission> --%>
            <shiro:hasPermission name="community_qa">
            <li code="community_qa"><a href="<%=request.getContextPath() %>/Question/comQuestionIndex">问答</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="teacher_person_status">
            <li code="teacher_person_status"><a href="<%=request.getContextPath() %>/classModule/dynamics">老师动态</a></li>
            </shiro:hasPermission>
             <shiro:hasPermission name="teacher_person_comment">  
            <li code="teacher_person_comment"><a href="<%=request.getContextPath() %>/classModule/comment">课程评论</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="netschool_news">  
            <li code="netschool_news"><a href="<%=request.getContextPath() %>/sysConfigNews/showNews">新闻资讯</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="financial">
            <li code="financial"><a href="<%=request.getContextPath() %>/payOrder/toOrder">财  务</a></li>
            </shiro:hasPermission>
       
         	<shiro:hasAnyRoles name="机构管理员,分校管理员,运营,课程顾问,客服,直播老师,助教">
            <shiro:hasPermission name="query_statistics">
            <li code="query_statistics"><a href="<%=request.getContextPath() %>/query/page/student" id="chaxuntongji">查询统计</a></li>
            </shiro:hasPermission>
            </shiro:hasAnyRoles>
            <shiro:hasRole name="代理机构">
            <shiro:hasPermission name="query_statistics">
            <li code="query_statistics"><a href="<%=request.getContextPath() %>/query/page/payMaster" id="chaxuntongji">查询统计</a></li>
            </shiro:hasPermission>
            </shiro:hasRole>
            <shiro:hasPermission name="promotion_head">
			  <li code="sales"><a href="<%=request.getContextPath() %>/companyCouponsConfig/showCouponsList">促  销</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="member_class">
 			  <li code="member_class"><a href="<%=request.getContextPath() %>/classType/memberClass">会员课程</a></li>
			</shiro:hasPermission>
 			  <li code="certificate_list" id="certificate_list" style="display: none;"><a href="<%=request.getContextPath() %>/certificateConfig/toCertificateList">证书管理</a></li>
            
       <%--                  <li code="stu"><a href="<%=request.getContextPath() %>/query/page/student">学员</a></li>
            <li code="order"><a href="<%=request.getContextPath() %>/query/page/payMaster">订单</a></li>
            <li code="free"><a href="<%=request.getContextPath() %>/query/page/fee">费用</a></li> --%>
            <shiro:hasPermission name="studycard_head">
			  <li code="studycard_head"><a href="<%=request.getContextPath() %>/companyStudyCard/gotoStudyCardsManage">学习卡</a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="student_star">
 			  <li code="student_star"><a href="<%=request.getContextPath() %>/studentStar/studentStarIndex">学员心声</a></li>
			</shiro:hasPermission>
        </ul>
    </div>
</div>
<script>
	$(function(){
		$selectMenu('operating_head');
		
		$.ajax({
			url:rootPath+"/companyFunctionSet/queryCompanyCertificateExist",
			type:"post",
			data:{"functionCode":"certificate_manage","status":"1"},
			dataType:"json",
			success:function(data){
				if(data=="success"){
					$('#certificate_list').show();
				}else{
					$('#certificate_list').hide();
				}
			}
		})
	});
</script>