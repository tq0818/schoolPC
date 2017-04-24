<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>教师邀请码设置</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath %>/stylesheets/manage.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath %>/stylesheets/system.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/minitip.css">
<link rel="stylesheet" type="text/css"
	href="<%=rootPath %>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/sidebar/sidebar.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classSet/classSet.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/footSet.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/distrib.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/resource.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/utils.css">

<style>
.iconfontH {
    color: red;
}

.tipsH {
    color: red;
}

.iii {
    position: absolute;
    top: 23px;
    right: -8px;
    width: 0;
    height: 0;
    border: 5px solid transparent;
    border-top-color: #ddd;
}

.cc {
    height: 62px;
    padding: 0 1%;
    line-height: 1.5;
}

.alert {
    padding: 15px;
    border: 1px solid transparent;
    margin: 0 auto;
}

.alert-warning {
    color: #c09853;
    background-color: #fcf8e3;
    border-color: #fbeed5
}

.alert-dismissable .cloze {
    position: relative;
    right: 50px;
    color: inherit;
    font-size: 1.6rem;
    cursor: pointer;
}

.cloze {
    float: right;
    font-size: 21px;
    font-weight: bold;
    line-height: 1;
    color: #000;
    text-shadow: 0 1px 0 #fff;
    opacity: .2;
    filter: alpha(opacity=20);
    display: inline-block;
    margin-top: 0px;
    margin-right: 0px;
    width: 9px;
    height: 9px;
    background-repeat: no-repeat !important;
}

.noalert {
    float: right;
    font-size: 12px;
    font-weight: bold;
    line-height: 1;
    color: #000;
    text-shadow: 0 1px 0 #fff;
    opacity: .2;
    filter: alpha(opacity=20);
    display: inline-block;
    margin: 1px -15px 0 15px;
    width: 80px;
    height: 9px;
    background-repeat: no-repeat !important;
    cursor: pointer;
}

.cloze:hover,
.cloze:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
    opacity: .5;
    filter: alpha(opacity=50)
}

button.cloze {
    padding: 0;
    cursor: pointer;
    background: transparent;
    border: 0;
    -webkit-appearance: none
}
</style>
<script src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
<script>
  	var rootPath = '<%=rootPath%>';
  	var teacherConfig;
  	
  	$(function(){
  		
  		$.ajax({
			 type:"POST",
			 url :rootPath + "/companyInvitConfigTeacher/ajaxCheck",
			 success:function(data){
				 if(data&&data.openFlag){
					 teacherConfig = data;
					 $('#1thbtn').removeClass('close').addClass('open').html('&#xe603;').next('span').eq(0).removeClass('close').addClass('open').html('&nbsp;&nbsp;已启用');
					 $('.footerSetBox-fen').show();
				 }
				 else{
					 $('.footerSetBox-fen').hide();
				  	 $('.stubox').hide();
				     $('.inviteList').find('.ticheng').attr("disabled", "true");
				 }
				 if(data&&data.stuRewardsFlag){
					 $('#2secbtn').removeClass('close').addClass('open').html('&#xe603;').next('span').eq(0).removeClass('close').addClass('open').html('&nbsp;&nbsp;已启用');
					 $('.stubox').show();
				 }else{
					 $('.stubox').hide();
				 }
				 if(data&&data.invitRgstAward){
					$('#invitRgstAward').val(data.invitRgstAward);
				 }
				 if(data&&data.invitRgstAwardPeriod){
					 $('#invitRgstAwardPeriod').val(data.invitRgstAwardPeriod);
				 }
				 if(data&&data.invitCastAwardFlag){
					 $('#tichenglist').find('.chet').eq(0).prop('checked','checked').siblings('.numberday').removeAttr('disabled').val(data.invitCastAward).parent().siblings().find('.numberday').attr('disabled','disabled');
				 }else if(data && data.invitCastAwardFlag==0){
					 $('#tichenglist').find('.chet').eq(1).prop('checked','checked').siblings('.numberday').removeAttr('disabled').val(data.invitCastAward).parent().siblings().find('.numberday').attr('disabled','disabled');
				 }
				 if(data&&data.castTypeCourse){
					 $('#castTypeCourse').attr('checked','checked');
				 }
				 if(data&&data.castTypePackage){
					 $('#castTypePackage').attr('checked','checked');
				 }
				 if(data&&data.castTypeMember){
					 $('#castTypeMember').attr('checked','checked');
				 }
				 if(data&&data.castTypeIntegral){
					 $('#castTypeIntegral').attr('checked','checked');
				 }
			 }
			
		});
  	})
</script>
</head>


<body >
	<jsp:include page="/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<form id='companyinvitconfigForm' action="">
		<input type="hidden" value="${companyId }" name="companyId" />
	</form>
	
	<!-- 二级导航 -->
	<div class="u-wrap company overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_newpromotion.jsp"></jsp:include>
	<script>
	$(function(){
		$selectSubMenus('teacher');
	})
	</script>
	 <div class="right-side wxRight-side">
            <div>
                <div class="title-box titlefen-box">
                    <div class="tit-font ">
                        <span class="t ts">教师邀请码</span>
                        <span class="kg kgCla kg-fen" level="cla">
                        <i class="iconfont close" id="1thbtn"></i><span class="i fs12">&nbsp;&nbsp;已禁用</span>
                        </span>
                    </div>
                </div>
                <div class="footerSetBox footerSetBox-fen">
                    <h4>说明：开启该功能后，可以为教师发放二维邀请码，教师通过邀请码邀请学生成功后，可获得相应的奖励。</h4>
                    <div class="contentList">
                        <div class="title-box titlefen-box titleC-box">
                            <div class="tit-font titfen-font">
                                <span class="t ts">被邀请学生奖励设置</span>
                                <span class="kg kgCla kg-fen" level="cla">
                        	<i class="iconfont close" id="2secbtn"></i><span class="i fs12">&nbsp;&nbsp;已禁用</span>
                             </span>
                            </div>
                        </div>
                        <div class="inviteList-box stubox">
                            <div class="inviteList">
                                <span>被邀请人注册成功后获得：</span>
                                <span class="coupon">代金券</span>
                                <input type="text" class="numberday"  id="invitRgstAward" maxlength="11"/>
                                <span class="yuan">元</span>
                            </div>
                            <div class="inviteList">
                                <span class="cashday">代金券使用有效天数：</span>
                                <input type="text" class="numberday" id="invitRgstAwardPeriod" maxlength="11"/>
                                <span class="yuan">天</span>
                                <i>说明：获取代金券后 ** 天内使用</i>
                            </div>
                        </div>
                    </div>
                    <!-- ****************** -->
                    <div class="contentList">
                        <div class="title-box titlefen-box titleC-box">
                            <div class="tit-font titfen-font">
                                <span class="t ts">教师奖励规则设置</span>
                            </div>
                        </div>
                        <h4 class="explain">说明：提成按实际支付的金额计算。</h4>
                        <div class="inviteList-box " id="tichenglist">
                            <div class="inviteList inviteList-uncheck">
                                <input class="inpche chet" type="checkbox" mark="first"/>
                                <span>被邀请人首次消费老师可获得 :</span>
                                <span class="coupon">提成</span>
                                <input type="text" class="numberday ticheng" maxlength="11"/>
                                <span class="yuan">%</span>
                            </div>
                            <div class="inviteList inviteList-uncheck">
                                <input class="inpche chet" type="checkbox"  mark="every"/>
                                <span>被邀请人每次消费老师可获得 :</span>
                                <span class="coupon">提成</span>
                                <input type="text" class="numberday ticheng" maxlength="11"/>
                                <span class="yuan">%</span>
                            </div>
                        </div>
                    </div>
                    <!-- ************ -->
                    <div class="contentList">
                        <div class="title-box titlefen-box titleC-box">
                            <div class="tit-font titfen-font">
                                <span class="t ts">奖励的消费类型设置</span>
                            </div>
                        </div>
                        <ul class="classficat">
                            <li class="ficat">
                                <input class="inpche" type="checkbox" id="castTypeCourse"/><span>购买课程</span>
                            </li>
                            <li class="ficat">
                                <input class="inpche" type="checkbox" id="castTypePackage"/><span>购买课程包</span>
                            </li>
                            <li class="ficat">
                                <input class="inpche" type="checkbox" id="castTypeMember"/><span>购买会员</span>
                            </li>
                            <li class="ficat">
                                <input class="inpche" type="checkbox" id="castTypeIntegral"/><span>购买积分</span>
                            </li>
                        </ul>
                        <p class="butfen">
                            <a href="javascript:;" class="btn btn-default">取消</a>
                            <a href="javascript:;" class="btn btn-success">保存</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        </div>
	<script src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
	<script src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script src="<%=rootPath%>/javascripts/company/inviteTeacher.js"></script>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>

</html>
