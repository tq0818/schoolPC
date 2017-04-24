<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>报名通知</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap/css/markdown.min.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <style type="text/css">
    	.open{
    		color:#0099ff;
    	}
    	.font_middle{
    		font-size:12px;
    	}
    	.font_big{
    		font-size:22px;
    	}
    	.bottom_postion{
    		margin: 5px 65px;
    	}
    	/* .dom_postion{
    		margin-left: 30px;
    	} */
		.pay-box-parent {
		    padding: 29px 50px 70px;
		    overflow: hidden;
		}
    	.div_father{
    		font-size: 14px;
    		color:#666;
    	}
    	.div_child{
    		color:#999;
    		margin: 20px 0px 40px 0px;
    	}
    </style>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_newsystem.jsp"></jsp:include>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">报名通知</span>
                </div>
            </div>
            
            <div class="pay-box-parent">
		                <div><span class="div_father">手工报名后给学员发送短信通知</span><span style="margin-left: 20px;font-size: 14px;" id="onefunction" ids="" mark="COMPANY_FUNCTION_APPLY" con="手工报名发短信配置"><i class="iconfont close font_big">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span></div>
		           <hr/>
		           <div class="div_child">
		           	   <span class="font_middle dom_postion"> 说明：开启该功能后，在网校后台给学员手工报名时，报名成功后会给学员发送报名成功的通知，同时会把学员网校前台的登录账号和密码告知学员。</span>
		           	    <div id="addmessage" style="margin-top: 10px;display: none;">
		           	   		<div class="font_middle dom_postion" style="float: left;">短信内容</div>
		           	   		<div>
			           	   		<div style="display:inline-block;margin-left: 15px;">
						              <textarea  id="messageContent" cols="78" rows="6">您已成功报名【coursename】课程，网校账户已经开通，账户名称是报名时所使用的手机号，初始密码是手机号后六位，请及时登录网校查看。</textarea>
			           	   		</div>
			           	   		<div style="margin-left:65px;">短信内容最好不要超过70个字符，超过后将按多条短信收费。<span style="color:red;">注:短信内容中添加【】符号，易造成短信内容发送不成功</span></div>
			           	    </div>
		           	   		<div class="bottom_postion"><input type="button" class="btn btn-success saveMessageContent" value="保存 "/></div>
		           	   </div>
		           </div>
		           <div>
		           		<span class="div_father">给新购买学员发送站内信通知</span><span style="margin-left: 36px;font-size: 14px;" id="twofunction" ids="" mark="COMPANY_FUNCTION_BUY" con="新学员购买发送站内信配置"><i class="iconfont close font_big">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span>
		           </div><hr/>
		           <div class="div_child">
		           	   <span class="font_middle dom_postion">说明：开启该功能后，当有学员报名课程后，会收到系统发送的欢迎站内信。</span>
		           	   <div id="addMessageModules" style="margin-top: 10px;display: none;">
		           	   		<div class="font_middle dom_postion" style="float: left;">私信内容</div>
		           	   		<div>
			           	   		<div style="margin-left: 15px;display: inline-block;">
						              <textarea  id="newsContents" data-provide="markdown" data-width="500"
						                name="newsContents" rows="6">亲爱的【username】，您好，感谢您购买我们的课程【coursename】，您可以登录个人中心-我的课程中进行学习，有问题及时和我们联系，祝您学习愉快。</textarea>
			           	   		</div>
			           	   		<div style="margin-left:60px;">【username】和【coursename】是固定内容，不能修改。</div> 
			           	    </div>
		           	   		<div class="bottom_postion"><input type="button" class="btn btn-success saveContent" value="保存 "/></div>
		           	   </div>
		           </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('org_service');
    		$selectSubMenus('signup_news');
    	})
    </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/class/signUpNotice.js"></script>
</body>

</html>