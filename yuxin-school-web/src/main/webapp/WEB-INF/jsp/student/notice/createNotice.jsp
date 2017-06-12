<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>通知内容</title>
    <%@include file="/decorators/import.jsp" %>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/select2.min.css" />
	<script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/select2.full.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/notice/createNotice.js"></script>
    <style type="text/css">
    	.font-style{
    		font-size:0;
    	}
    	.font-style a{
    		margin-right:4px;
    	}
    	.select2{
    		width:150px!important;
    		    height: 28px!important;
    		overflow:hidden;
    	}
    	.select2-selection{
    	 height: 28px!important;
    	 
    	}
    	.c-content select{
    		width:150px;
    		    height: 28px;
    		        padding: 0.15em 8px .35em;
    	}
    </style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp" />
	<input type="hidden" value="${count }" id="msgCount" />
	<div class="u-wrap student">
		<div class="mainbackground">
			<div class="heading">
				<h2 class="h5">新建学员通知</h2>
				<span class="line"></span>
			</div>
			<div class="main-content">
				<div class="notice-main">
					<p class="c con-tzbt">
						<span class="c-title">通知标题：</span> <span class="c-content long">
							<input type="text" id="title" maxlength="15">
						</span>
						<span style="color:red;">* 最多15个字</span>
					</p>
					<p class="c">
						<span class="c-title">通知方式：</span> <span class="c-content font-style">
							<a href="javascript:;"
							class="btn btn-mini btn-method btn-primary"
							data-type="STUDENT_MESSAGE_MOBILE">短信通知</a> <a
							href="javascript:;" class="btn btn-mini btn-method btn-default"
							data-type="STUDENT_MESSAGE_WEB">站内信通知</a><a
							href="javascript:;" class="btn btn-mini btn-method btn-default"
							data-type="STUDENT_MESSAGE_EMAIL">邮件通知</a>
						</span>
					</p>
					<p class="c">
						<span class="c-title">通知类型：</span> <span class="c-content font-style">
							<a href="javascript:;" class="btn btn-mini btn-type btn-primary"
							data-type="STUDENT_MESSAGE_CLASSTYPE">课程通知</a> 
							
	                        <c:if test="${classMoreStatus == 1 }">
								<a
								href="javascript:;" class="btn btn-mini btn-type btn-default"
								data-type="STUDENT_MESSAGE_MODULENO">班号通知</a>
	                        </c:if> 
							<a
							href="javascript:;" class="btn btn-mini btn-type btn-default"
							data-type="STUDENT_MESSAGE_SPECIAL">指定通知</a>
							<a
							href="javascript:;" class="btn btn-mini btn-type btn-default"
							data-type="STUDENT_MESSAGE_GROUP">学员分组通知</a>
						</span>
					</p>
					
					<!-- 课程或者班号 -->
					<p class="c sendStuMsg">
						<span class="c-title">学科：</span> <span class="c-content"> <select
							id="one">
								<c:forEach var="o" items="${oneItem }">
									<option value="${o.id }">${o.itemName }</option>
								</c:forEach>
						</select>
						</span> <span class="c-title">学科小类：</span> <span class="c-content">
							<select id="two">
						</select>
						</span>
					</p>
					<p class="c sendStuMsg">
						<span class="c-title" id="classTitle">课程：</span> <span
							class="c-content"> <select id="class"
							class="js-example-basic-single">
						</select>
						</span> <span class="c-title">学员数量：</span> <span class="c-content btn-view">
						</span>
					</p>
					<p class="c sendStuMsg">
						<span class="c-title">发送学员：</span> <span class="c-content"
							id="sendStu"></span>人
					</p>
					
					<!-- 指定通知 -->
					<p class="c phoneHint">
						<span class="c-title">输入手机号：</span><br> <span
							class="c-content l-content"> <textarea class="msg-content"
								id="phone" onkeyup="javascript:valida();"></textarea>
						</span>
					</p>
					<p class="c phoneHint">
						<span class="c-title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<span class="c-content"><span
							style="color: red; float: right;">手机号之间用英文的逗号【，】分隔开</span></span>
					</p>
					<p class="c emailHint" style="display:none;">
						<span class="c-title">输入邮箱：</span><br> <span
							class="c-content l-content"> <textarea class="msg-content"
								id="email" onkeyup="javascript:valida();"></textarea>
						</span>
					</p>
					<p class="c emailHint" style="display:none;">
						<span class="c-title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<span class="c-content"><span
							style="color: red; float: right;">邮箱之间用英文的逗号【，】分隔开</span></span>
					</p>
					<!-- 分组 -->
					<p class="c stuGroup" style="display:none;">
						<span class="c-title">一级分组：</span> 
						<select id="studentG1_edit" name="studentGroup1_edit" onchange="javaScript:selectGroup2(this,'_edit');"><option value="" selected="selected">全部</option></select>
						<span class="c-title">二级分组：</span>
						<select id="studentG2_edit" name="studentGroup2_edit" onchange="javaScript:selGroupStu(this);"></select>
					</p>
					<p class="c stuGroup" style="display:none;">
						<span class="c-title">学员数量：</span> <span class="c-content" id="groupStuCount"></span>
						<span class="c-title">发送学员：</span> <span class="c-content" id="_sendStu"></span>人
					</p>
					
					<p class="c emailTitle" style="display:none;">
						<span class="c-title">邮件标题：</span> <input id="email_title" type="text"/>
					</p>
					<!-- 发送内容 -->
					<p class="c ">
						<div class="con-fsnr">
							<span class="c-title">发送内容：</span><br> <span
							class="c-content l-content" id="messageContent"> <textarea
								id="msgcount" class="msg-content" onkeydown="valida();"
								onkeyup="valida();" onkeypress="valida();" maxlength="140"></textarea>
							</span>
						</div>
						
						<!-- 站内信 --> 
						<div id="ckecktor"> 
							<textarea id="newsContents" class="msg-content"></textarea>
						</div>
						<!-- 邮件 -->
						<div id="email_ckecktor" style="display:none;"> 
							<textarea id="email_newsContents" class="msg-content"></textarea>
						</div>
					</p>
					<!-- 发送条数 -->
					<p class="c zhan">
						<span class="c-title">消耗短信：</span> <span class="c-content"><span
							id="useMsg"></span> <em style="font-size: inherit;color: #999;padding-right:20px;padding-left:20px;">剩余短信：<span id="Surplus">${!empty count?count:0 } 条</span></em><span
							style="color: red; float: right;">已输入<span id="write"></span>个字符，单条短信70个字符
						</span></span>&nbsp;<span style="color:red;">* 最多140个字</span>
					</p>
					<p class="c use_email" style="display:none;">
						<span class="c-title">消耗邮件：</span> <span class="c-content"><span
							id="useEmailMsg"></span> <em style="font-size: inherit;color: #999;padding-right:20px;padding-left:20px;">剩余邮件：<span id="hasEmail">${emailCount }</span></em>
					</p>
					<p class="text-center">
						<a href="javascript:;" class="btn btn-sm btn-primary btn-send">发送通知</a>
					</p>
				</div>
			</div>
		</div>
	</div>

<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
	<script type="text/javascript"
		src="<%=rootPath%>/plugins/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		var classMoreStatus = '${classMoreStatus}';//多班号是否开启
	
		var editor = CKEDITOR.replace('newsContents');
		editor.config.width="570px";
		editor.config.toolbar = [
				[ 'mode', 'document', 'doctools' ],
				[ 'Source', '-', 'NewPage' ],
				[ 'basicstyles', 'cleanup' ],
				[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
						'Superscript' ],
				[ 'list', 'indent', 'blocks', 'align', 'bidi' ],
				[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent',
						'JustifyLeft', 'JustifyCenter', 'JustifyRight',
						'JustifyBlock' ], [ 'Link', 'Unlink' ],
				[ 'Image', 'Table' ],
				[ 'Styles', 'Format', 'Font', 'FontSize' ],
				[ 'TextColor', 'BGColor' ], [ 'Maximize' ], [ '-' ]];
		editor.config.baseFloatZIndex = 10100;
		editor.config.customConfig = 'config.js';
		
		
		
		var email_editor = CKEDITOR.replace('email_newsContents');
		email_editor.config.width="570px";
		email_editor.config.toolbar = [
				[ 'mode', 'document', 'doctools' ],
				[ 'Source', '-', 'NewPage' ],
				[ 'basicstyles', 'cleanup' ],
				[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
						'Superscript' ],
				[ 'list', 'indent', 'blocks', 'align', 'bidi' ],
				[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent',
						'JustifyLeft', 'JustifyCenter', 'JustifyRight',
						'JustifyBlock' ], [ 'Link', 'Unlink' ],
				[ 'Image', 'Table' ],
				[ 'Styles', 'Format', 'Font', 'FontSize' ],
				[ 'TextColor', 'BGColor' ], [ 'Maximize' ], [ '-' ]];
		email_editor.config.baseFloatZIndex = 10100;
		email_editor.config.customConfig = 'config.js';
	</script>

</body>
</html>