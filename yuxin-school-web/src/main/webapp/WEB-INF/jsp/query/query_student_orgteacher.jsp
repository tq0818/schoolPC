<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>查询统计</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/classList.css">
	<style type="text/css">
		.pages li.disabled{padding:0px;}
		.xingMark{background: url("../../../images/xing.png") no-repeat left 3px ;
			padding-left: 7px;}
		.tableSecond th{
			overflow: hidden;
			text-overflow:ellipsis;
			white-space: nowrap;
		}
		.classNo{
			display: none;width: 49%; text-align: center;border: 1px solid #666;
			margin: 20px 0 10px;border: 1px solid #ddd;
		}
	</style>
</head>
<body>
<input type="hidden" id="schoolId" value='${schoolId}'/>
		<input type="hidden" id="schoolName" value='${schoolName}'/>
		<input type="hidden" id="isAdmin" value='${isAdmin}'/>
		<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>
		<input type="hidden" id="current_is_area" value='${CURRENT_IS_AREA}'/>	
		<input type="hidden" id="sourceFromStatic" value='1'/>
		<input type="hidden" id="eduYearM" value='${eduYear}'/>
		<input type="hidden" id="eduClassM" value='${eduClass}'/>
		<input type="hidden" id="eduStepM" value='${eduStep}'/>
<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_org.jsp"></jsp:include>
	<div class="u-wrap query overflow">
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query_org.jsp"></jsp:include>
		<div class="right-side set-system">
			<div class="mainbackground nopadding">
				<div class="heading">
						<c:if test="${role==1}">
							<h2 class="h5 active">学员列表</h2>
							<i class="markTitle"></i>
							<h2 class="h5 studentListTitle " >班级学生列表</h2>
						</c:if>
						<c:if test="${role==2}">
							<h2 class="h5 active">用户列表</h2>
							<i class="markTitle"></i>
							<h2 class="h5 studentListTitle " >班级学生列表</h2>
						</c:if>
						<c:if test="${role!=1 and role != 2}">
							<h2 class="h5 active">${school.itemValue}学员列表</h2>
							<i class="markTitle"></i>
							<h2 class="h5 studentListTitle " >班级学生列表</h2>
						</c:if>
					<%--<i class="markTitle"></i>
					<h2 class="h5 studentListTitle " >班级学生列表</h2>--%>
					<span class="line"></span>
				</div>
				<form method="post" id="searchForm" class="studentListContent">
					<div>
						<%--<input type="text" id="stuMobile" name="mobile" placeholder="手机号" maxlength="11"/>--%>
						<%--<input type="text" id="stuusername" name="username" placeholder="用户名"/>--%>
						<input type="hidden" id="eduSchool" name="eduSchool" value='${school.itemCode}'/>
						<c:if test="${role == '2'}">
							<input type="hidden" id="hiddenEduStep" name="hiddenEduStep" value='${eduStep}'/>
							<input type="hidden" id="hiddenEduYear" name="hiddenEduYear" value='${eduYear}'/>
							<input type="hidden" id="hiddenEduClass" name="hiddenEduClass" value='${eduClass}'/>
						</c:if>
						<input type="hidden" id="role" name="role" value='${role}'/>
						<input type="text" id="stuName" name="name" placeholder="姓名"/>
						<input type="hidden" id="sfzh" name="identityId" placeholder="证件号码"/>
						<input type="hidden" id="isStu" name="isStu" value="1"/>
                        <span style="margin-left:30px">当前学段</span>
                       		<c:if test="${role != '2' && role != '3'}">
                       		 	<select id="eduStep" name="eduStep" style="width:150px;">
		                           <option value="">请选择学段</option>
		                           <c:forEach items="${stepList}" var="step">
		                               <option value="${step.itemCode}">${step.itemValue}</option>
		                           </c:forEach>
		                        </select>
                          </c:if> 
                       		<c:if test="${role == '3' }">
                       			<select id="eduStep" name="eduStep" style="width:150px;" onchange="changeLevel(this);">
		                           	<option value="">请选择学段</option>
			                            <c:forEach items="${eduStep}" var="step">
			                               <option value="${step.eduStep}">${step.eduStepName}</option>
			                           </c:forEach>
	                         	</select>  
                          </c:if> 
                          <c:if test="${role == '2' }">
	                          <select id="eduStep" name="eduStep" style="width:150px;">
	                          	<option value="">请选择学段</option>
	                          	<option value="${eduStep}">${eduStepName}</option>
	                          </select>
                          </c:if>
	                        <c:if test="${role!='2' && role!='3' }">
	                        	<select id="eduYear" name="eduYear" style="width:150px;">
	                           		<option value="">请选择入学年份</option>
	                           	</select>
	                        </c:if>
	                         <c:if test="${role=='3' }">
		                         <select id="eduYear" name="eduYear" style="width:150px;" onchange="changeGrade(this);">
			                         	<option value="">请选择入学年份</option>
			                           <c:forEach items="${eduYear}" var="year">
			                               <option value="${year.eduYear}" class="${year.eduStep}" style="display:none;">${year.eduYear}年</option>
			                           </c:forEach> 
		                          </select>
	                        </c:if> 
	                        <c:if test="${role=='2'}">
	                        	<select id="eduYear" name="eduYear" style="width:150px;">
	                        		<option value="">请选择入学年份</option>
	                           		<%-- <option value="${eduYear}">${eduYear}年</option> --%>
	                           	</select>
	                      </c:if>
                        <select id="eduClass" name="eduClass" style="width:150px;">
                        <c:if test="${role!='2' && role!='3' }">
                        	<option value="">请选择班级</option>
                        </c:if>
                        <c:if test="${role=='2'}">
                        	<option value="">请选择班级</option>
                        	<%-- <option value="${eduClass}">${eduClass}班</option> --%>
                        </c:if>
                        <c:if test="${role=='3' }">
                         	<option value="">请选择班级</option>
                           	<c:forEach items="${eduClass}" var="classes">
                               <option value="${classes.eduClass}" class="${classes.eduYear}" style="display:none;">${classes.eduClass}班</option>
                           	</c:forEach> 
	                     </c:if>  
                        </select>
					</div>
					<div style="margin-top: 10px;">
						<span>创建时间</span>
						<span><input type="text" name="startTime" class="date-picker from"/><em>到</em><input type="text" name="endTime" class="date-picker to"/></span>
						<c:if test="${address==1}">
						<span style="padding:0 15px;" id="caddress">
							<select id="prov" name="province"></select>
							<select id="city" name="city"></select>
							<select id="dist" name="county"></select>
						</span>
						</c:if>

						<span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
						<span class="fr">
							<%-- <c:if test="${isArea ne 1}">
							<a href="javascript:;" class="btn btn-primary importexcle" >导入用户</a></c:if> --%>
							<a href="javascript:;" class="btn btn-primary exportExcleSchool">导出数据</a></span>
					</div>
				</form>
				<div class="user-list studentListContent">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
							<th width="6%">用户名</th>
							<th width="8%">姓名</th>
							<th width="5%">身份</th>
							<%--<th width="8%">区域</th>--%>
							<%--<th width="12%">学校</th>--%>
							<th width="10%">所在班级</th>
							<c:if test="${userorg_roleopenflag==1 }">
								<shiro:hasAnyRoles name="机构管理员,代理机构">
									<th width="10%">所属代理机构</th>
								</shiro:hasAnyRoles>
							</c:if>
							<th width="8%">创建时间</th>
						<%--	<th width="6%">前台登录账号</th>--%>
							<th width="6%">前台账号状态</th>
							<%--<th width="5%">报名状态</th>--%>
							<%--<th width="10%">操作</th>--%>
						</tr>
						<c:choose>
							<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">
								<tr><td colspan="15">暂无数据</td></tr>
							</c:when>
							<c:otherwise>
								<tr><td colspan="14">暂无数据</td></tr>
							</c:otherwise>
						</c:choose>


					</table>
					<div class="pages pagination"></div>
				</div>
				<div class="classListContent">
					<form>
						<ul>
							<c:if test="${role == 2}">
								<li>
									<label for="" class="xingMark">学科</label>
									<select name="" id="subject">
										<c:forEach items="${subjectItem}" var="subject">
											<option value="${subject.itemCode}">${subject.itemName}</option>
										</c:forEach>
									</select>
								</li>
								<li>
									<label for="" class="xingMark">课程形式</label>
									<select name="" id="liveFlag">
										<option value="1">直播</option>
										<option value="0">点播</option>
									</select>
								</li>
							</c:if>
							<c:if test="${role != 2}">
								<li>
									<label for="" class="xingMark">学段</label>
										<select id="eduStep2" name="eduStep" >
											<c:forEach items="${stepList}" var="step">
												<option value="${step.itemCode}">${step.itemValue}</option>
											</c:forEach>
										</select>
								</li>
								<li>
									<label for="">入学年份</label>
										<select id="eduYear2" name="eduYear" >

										</select>
								</li>
								<li>
									<label for="">班级</label>
									<select name="" id="eduClass2">
											<option value="">请选择班级</option>
									</select>
								</li>
							<li>
								<label for="" class="xingMark">学科</label>
								<select name="" id="subject">
									<c:forEach items="${subjectItem}" var="subject">
										<option value="${subject.itemCode}">${subject.itemName}</option>
									</c:forEach>
								</select>
							</li>
							<li>
								<label for="" class="xingMark">课程形式</label>
								<select name="" id="liveFlag">
									<option value="1">直播</option>
									<option value="0">点播</option>
								</select>
							</li>
							</c:if>
							<li>
								<a href="##" class="btn btn-mb btn-primary" style="margin-right: 10px;" onclick="masterFindClassStu(0)">查询</a>
								<a href="##" class="btn btn-mb btn-primary">导出</a>
							</li>
						</ul>
					</form>
					<%--<div class="user-list">--%>
					<div style="width: 100%;height: 50px;line-height: 110px;text-align: right;">
						<span class="xingMark" style="font-size: 12px;margin-right: 30px;">
							实际观课效果：√表示观课时间超过了70%，✘表示观课时长未超过70%
						</span>
					</div>
					<div>
						<div class="studentContent" style="width:100%;">
							<table class="table table-center tableFirst">
								<tr data-buy="true">
									<th width="8%">姓名</th>
									<th width="7%">班级</th>
									<th width="7%">观课总节数</th>
									<th width="11%">观课总时长（分钟）</th>
								</tr>
								<tbody id="stuListTbody"></tbody>
							</table>
							<table  class="table table-center tableSecond">

								<tr id="className" data-buy="true">

								</tr>
								<tbody id="classListTbody"></tbody>
							</table>
							<div class="leftIcon changeIcon" style="display:none;">
								<i id="leftIconBtn" class="icon iconfont ">&#xe650;</i>
							</div>
							<div class="rightIcon changeIcon">
								<i id="rightIconBtn" class="icon iconfont ">&#xe651;</i>
							</div>
							<div class="classNo">
								<img src="<%=rootPath%>/images/classNew.jpg" alt="" style="margin-top: 150px;">
							</div>
						</div>
						<div class="studentNo" style="display: none;width: 100%; text-align: center;">
							<img src="<%=rootPath%>/images/studentNew.jpg" alt="" style="margin-top: 150px;">
						</div>
						<div id="paginationStuList" class="pages pagination"></div>
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
	</div>
<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/master_query_student.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/query_student_orgteachcommon.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/selectStudentGroup.js"></script>
<script type="text/javascript">
	//$selectSubMenu('statistics_org_detail');
    //    左侧active切换
   // $selectSubMenus('studentList');
	
	function changeGrade (obj){
    	var gradeCode=$(obj).val();
    	$("#eduClass").find("option").each(function(){
    		var optionClass=$(this).attr("class");
    		$(this).attr('selected',false);
    		if(optionClass==''||optionClass==undefined)
    			return;
    		if(gradeCode==optionClass){
    			$(this).attr('style','display:block');
    		}else{
    			$(this).attr('style','display:none');
    		}
    	});
    }
	
	function changeLevel(obj){
    	var levelCode=$(obj).val();
    	$("#eduClass").find("option").each(function(){
    		var optionClass=$(this).attr("class");
    		$(this).attr('selected',false);
    		if(optionClass==''||optionClass==undefined)
    			return;
    	});
    	$("#eduYear").find("option").each(function(){
    		var optionClass=$(this).attr("class");
    		$(this).attr('selected',false);
    		if(optionClass==''||optionClass==undefined)
    			return;
    		if(levelCode==optionClass){
    			$(this).attr('style','display:block');
    		}else{
    			$(this).attr('style','display:none');
    		}
    	});
    }
	
	$(document).ready(function(){
        var currdate = new Date();
        var year = currdate.getFullYear();
        var yearBody = "";
        for(i = 0;i < 12;i++){
            var li ="<option value='"+(year - i)+"'>"+(year - i)+"年</option>";
            yearBody += li;
        }
        $("#eduYear2").append(yearBody);
        var classesBody = "";
        for(i = 1;i <=30; i++){
            var li ="<option value='"+i+"'>"+i+"班</option>";
            classesBody += li;
        }
        $("#eduClass2").append(classesBody);




		 var role='${role}';
		 if(role=='2'){
			 var yearBody ="<option value='"+$("#hiddenEduYear").val()+"'>"+$("#hiddenEduYear").val()+"年</option>";
			 var classesBody ="<option value='"+$("#hiddenEduClass").val()+"'>"+$("#hiddenEduClass").val()+"班</option>";
			 $("#eduYear").append(yearBody);
			 $("#eduClass").append(classesBody);
		 }else if(role=='3'){
			 return;
		 }else{
			 var currdate = new Date();
			 var year = currdate.getFullYear();
			 var yearBody = "";
	         for(i = 0;i < 12;i++){
	           var li ="<option value='"+(year - i)+"'>"+(year - i)+"年</option>";
	           yearBody += li;
	         }
         $("#eduYear").append(yearBody);
         var classesBody = "";
         for(i = 1;i <=30; i++){
           var li ="<option value='"+i+"'>"+i+"班</option>";
           classesBody += li;
         }
         $("#eduClass").append(classesBody);
		 }
	});



    //    tab切换
    $('.heading h2').click(function(){
        $(this).addClass('active');
        $(this).siblings('h2').removeClass('active');
        if(!$(this).index()){
            //点击的是学员列表
            $('.studentListContent').show();
            $('.classListContent').hide();
        }else {

            //点击的是班级学生列表
            $('.studentListContent').hide();
            $('.classListContent').show();
            //根据列表的高度设置切换按钮的高度
            var tableHeight = ($('.tableFirst').height()-35)+'px';
            $('.changeIcon').css('height',tableHeight).css('line-height',tableHeight).css('margin-top','75px');
            $('.classNo').css('height',($('.tableFirst').height()-2)+'px');
        }
    });

/*
    $('.leftIcon').click(function(){
        $('.tableSecond').html("");
        $('.tableSecond').append(contentHtml);
    });

    //点击下一页
    $('.rightIcon').click(function(){
        $('.tableSecond').html("");
        $('.tableSecond').append(contentHtml);
    });*/
</script>
</body>
</html>