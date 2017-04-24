<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>系统配置</title>
        <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap/css/markdown.min.css">
    <style type="text/css">
	    .iconfont.open {
		  color: #1b6fbd;
		}
    	.iconfont {
  		   font-size: 2.5rem;
		}
		.close {
		   float: none;
		   font-weight:normal;  
		}
		.i{
			font-size:14px;
		}
    </style>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>

<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">系统设置</h2>
            <span class="line"></span>
        </div>
        <div class="user-list" style="width:95%;margin-left: 30px;" >
           <div><span style="font-size: 16px;color:gray;">手工报名后给学员发送短信通知</span><span style="margin-left: 20px;font-size: 14px;" id="onefunction" ids="" mark="COMPANY_FUNCTION_APPLY" con="手工报名发短信配置"><i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span></div>
           <hr/>
           <div style="color:gray;margin: 20px 0px 40px 10px;">
           	   <span style="font-size: 14px;margin-left: 50px;"> 开启该功能后，在网校后台给学员手工报名时，报名成功后会给学员发送报名成功的通知，同时会把学员网校前台的登录账号和密码告知学员。</span>
           	    <div id="addmessage" style="margin-top: 10px;display: none;">
           	   		<span style="font-size: 14px;margin-left: 50px;">短信内容</span>
           	   		<span style="margin-left: 80px;display: inline-block;">
			              <textarea  id="messageContent" cols="78" rows="6">您已成功报名【coursename】课程，网校账户已经开通，账户名称是报名时所使用的手机号，初始密码是手机号后六位，请及时登录网校查看。</textarea>
           	   		</span>
           	   		<span>短信内容最好不要超过70个字符，超过后将按多条短信收费。</span>
           	   		<div style="margin-left:187px;margin-top:5px;"><input type="button" class="btn btn-success saveMessageContent" value="保存 "/></div>
           	   </div>
           </div>
           <div>
           		<span style="font-size: 16px;color:gray;">给新购买学员发送站内信通知</span><span style="margin-left: 36px;font-size: 14px;" id="twofunction" ids="" mark="COMPANY_FUNCTION_BUY" con="新学员购买发送站内信配置"><i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span>
           </div><hr/>
           <div style="color:gray;margin: 10px 0px 10px 10px;">
           	   <span style="font-size: 14px;margin-left: 50px;">开启该功能后，当有学员报名课程后，会收到系统发送的欢迎站内信。</span>
           	   <div id="addMessageModules" style="margin-top: 10px;display: none;">
           	   		<span style="font-size: 14px;margin-left: 50px;">私信内容</span>
           	   		<span style="margin-left: 80px;display: inline-block;">
			              <textarea  id="newsContents" data-provide="markdown" data-width="500"
			                name="newsContents" rows="6">亲爱的【username】，您好，感谢您购买我们的课程【coursename】，您可以登录个人中心-我的课程中进行学习，有问题及时和我们联系，祝您学习愉快。</textarea>
           	   		</span>
           	   		<span>【username】和【coursename】是固定内容，不能修改。</span> 
           	   		<div style="margin-left:187px;"><input type="button" class="btn btn-success saveContent" value="保存 "/></div>
           	   </div>
           </div>

           <div style="margin-top:30px;">
           		<span style="font-size: 16px;color:gray;">课程的多课程单元和多班号功能</span><span style="margin-left: 36px;font-size: 14px;" id="threefunction" ids="" mark="COMPANY_FUNCTION_COURSE" con="课程的多课程单元和多班号功能"><i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span>
           </div><hr/>
            <div style="color:gray;margin: 20px 0px 40px 10px;">
           	   <span style="font-size: 14px;margin-left: 50px;"> 开启该功能后，在创建课程的时候，可以创建多个课程单元，并且为每个课程单元创建多个招生的班号，这样可以让不同课程的同学在同一个班号中上课，节约上课成本。</span><br/>
           	   <span style="color:red;font-size: 14px;margin-left: 50px;">两种创建课程的方式之间频繁的进行切换可能会造成数据错误，请谨慎操作</span>
           </div>

           <div style="margin-top:30px;">
           		<span style="font-size: 16px;color:gray;">开放课程评论功能</span><span style="margin-left: 36px;font-size: 14px;" id="videofunction" ids="" mark="COURSE_COMMENT" con="视频课的评论功能"><i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span>
           </div><hr/>
            <div style="color:gray;margin: 20px 0px 40px 10px;">
           	   <span style="font-size: 14px;margin-left: 50px;"> 开启课程评论功能后，学员在观看课程的时候可以对课程进行评论，也可以查看到其他人对课程的评论。</span>
           </div>
           
<!--             <div style="margin-top:30px;"> -->
<!--            		<span style="font-size: 16px;color:gray;">开放名师功能</span><span style="margin-left: 36px;font-size: 14px;" id="teacherfunction" ids="" mark="COMPANY_FUNCTION_TEACHER" con="名师功能"><i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span></span> -->
<!--            </div><hr/> -->
<!--             <div style="color:gray;margin: 20px 0px 40px 10px;"> -->
<!--            	   <span style="font-size: 14px;margin-left: 50px;"> 开启名师功能后，学员可以查看机构中名师。</span> -->
<!--            </div> -->
        </div>
    </div>
</div>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/system/systemConfigSet.js"></script>
   <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
   <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
</body>
</html>