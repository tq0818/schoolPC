<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html >
<html>
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
	<title>课后作业</title>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/utils.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/classWork.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/homeWork.css">
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_teach.jsp"></jsp:include>
	<div class="u-wrap operate">
		<div class="mainbackground">
			<div class="heading">
				<h2 class="h5">作业列表</h2>
				<a href="javascript:;" class="returnBut studentsjob_goback">返回</a>
				<span class="line"></span>
			</div>
			<div class="workList">
				<ul class="classList-Con">
					<li>
						<table class="table class-table">
							<tr align="left">
								<th>${!empty courseName?courseName:"无此课程" }</th>
								<th colspan="2" class="pullL22">${!empty lessonName ? lessonName :"无此课次"}</th>
								<th>
									<p>
										<span>课次作业状态:</span> ${homeworkStatus }
									</p>

								</th>
							</tr>
							<tr class=" workListTitle">
								<td width="20%">
									<p>
										<span>已提交作业人员:</span> <em>${alreadyCommit }</em>
									</p>
								</td>
								<td width="15%">
									<p>
										<span>已批阅个数:</span> <em>${alreadyRead }</em>
									</p>
								</td>
								<td width="15%">
									<p>
										<span>未交作业学员:</span> <em>${lastCommit }</em>
									</p>
								</td>
								<td width="50%" class="pullL95">
									<p>
										<span>未批阅个数:</span> <em>${lastRead }</em>
									</p>
								</td>
							</tr>
						</table>
					</li>
				</ul>
			</div>
			<div class="workListSearch">
				<form action="" id="homeworkForm">
				<span>作业状态</span> 
				<span> 
					<select id="status" name="status">
							<option value="-1">全部</option>
							<option value="0">学员未提交作业</option>
							<option value="1">学员已提交作业</option>
							<option value="2">待重新提交作业</option>
							<option value="3">教师已批阅作业</option>
					</select>
				</span> 
				<span> <input type="text" id="mobile" name="mobile" value="" placeholder="手机号"> </span> 
				<span> <input type="text" id="username" name="username" value="" placeholder="用户名"> </span> 
				<span> <input type="text" id="name" name="name" value="" placeholder="姓名"> </span> 
				<c:if test="${sgOpen==1 }">
					<span>
						<select id="studentG1_search" name="studentGroup1_search" onchange="javaScript:selectGroup2(this,'_search');">
			        	</select>
			        	<select id="studentG2_search" name="studentGroup2_search" style="width: 110px;" >
			        	</select>
					</span>
				</c:if>
				<span class="search_input">
					<input type="button" class="studentsjob_search" value="搜索"/>
				</span>
				</form>
			</div>
			<div class="tableCon studentsjobTable">
				<table class="classTable">
					<colgroup>
						<col width="13%">
						<col width="13%">
						<col width="13%">
						<col width="13%">
						<col width="13%">
						<col width="13%">
						<col width="7%">
						<col width="15%">
					</colgroup>
					 <thead>
                           <tr>
                               <th>手机号</th>
                               <th>用户名</th>
                               <th>姓名</th>
                               <th>状态</th>
                               <th>作业提交时间</th>
                               <th>批阅日期</th>
                               <th>分数</th>
                               <th>操作</th>
                           </tr>
                       </thead>
                		   <tr><td colspan="9">暂无数据</td></tr>
	            </table>
	            <div class="pages L-pages">
	                <ul class="pagination"></ul>
	            </div>
	        </div>
	    </div>
		</div>
		<!--选择试卷-->
		
		<!--背影遮罩-->
		<div class="add-classes-bg hide" style="_display: block; width: 100%; height:100%; background: rgba(0,0,0,.2); position: fixed;left:0; top:0;"></div>
		<!-- ajax加载中div开始 -->
		<div class="loading lp-units-loading" style="display:none">
		    <p><i></i>加载中,请稍后...</p>
		</div>
		<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
		<!--  ajax加载中div结束 -->
		<input type="hidden" id="hiddenId" data-resourceid="${resourceId}" data-moduleid="${moduleId}" data-homeworkid="${homeworkId }" data-type="${type}" data-courseid="${courseId }">
		<style>
			.search_input input{
			    width: 45px;
			    height: 28px;
			    background-color: #07bbee;
			    font-size: 12px;
			    color: #fff;
			    line-height: 28px;
			    text-align: center;
			    outline: none;
			    border: none;
			    padding: 0;
			    margin-left: 10px;
			    border-radius: 4px;
		    }
			.search_input input:hover{
				background-color: #01aadd;
			}
		</style>
		<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
		<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
		<script type="text/javascript" src="<%=rootPath %>/javascripts/homework/studentsHomework.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/javascripts/selectStudentGroup.js"></script>
	</body>
</html>