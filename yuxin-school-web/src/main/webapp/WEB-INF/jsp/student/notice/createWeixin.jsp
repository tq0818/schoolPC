<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>通知内容</title>
    <%@include file="/decorators/import.jsp" %>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/select2.min.css" />
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/select2.full.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/notice/createWeixin.js"></script>
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
    	.main-content .lj-tops{display: inline-block;color:#999;}
    </style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp" />
	<div class="u-wrap student new-student">
		<div class="mainbackground">
			<div class="heading">
				<h2 class="h5">新建学员通知</h2>
				<span class="line"></span>
			</div>
			<div class="main-content">
				<div class="notice-main">
					<p class="c">
						<span class="c-title">通知方式：</span> <span class="c-content font-style">
							<a href="javascript:;" class="btn btn-mini btn-method btn-default"
							data-type="STUDENT_MESSAGE_WEIXIN">微信通知</a>
						</span>
					</p>
					<p class="c">
						<span class="c-title">通知类型：</span> <span class="c-content font-style">
							<a href="javascript:;" class="btn btn-mini btn-type btn-default"
							data-type="STUDENT_MESSAGE_WEIXIN">微信指定通知</a>
						</span>
					</p>
					
					<!-- 课程或者班号 -->
					<p class="c sendStuMsg">
						<span class="c-title">分类：</span> <span class="c-content"> <select
							id="one" style="width: 100px;">
								<c:forEach var="o" items="${oneItem }">
									<option value="${o.id }" data-code="${o.itemCode}">${o.itemName }</option>
								</c:forEach>
						</select>
						</span> <span class="c-title">学段：</span> <span class="c-content">
							<select id="two" style="width: 100px;">
						</select>
						</span>

					</p>
					<p class="c sendStuMsg">
						<span class="c-title">学科：</span> <span class="c-content">
							<select id="three" style="width: 100px;">
						</select>
						</span>
						<span class="c-title" id="classTitle">课程：</span> <span
							class="c-content"> <select id="class"
							class="js-example-basic-single">
						</select>
						</span> 
					</p>

					<!-- 指定通知 -->
					<p class="c phoneHint">
						<span class="c-title">学生所在学段：</span><br> <span
							class="c-content l-content"> <textarea class="msg-content"
								id="phone" onkeyup="javascript:valida();"></textarea>
						</span>
						<span class="c-title">入学年份：</span> <span class="c-content">
							<select style="width: 100px;">
						</select>
						</span>
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
	<script type="text/javascript">
		$(".lj-tops").bind("click",function(){
			
			var _checkbox= $(this).siblings().find("input");
			if(_checkbox.is(":checked")){
				_checkbox.attr('checked',false);
			}
		});
	</script>

</body>
</html>