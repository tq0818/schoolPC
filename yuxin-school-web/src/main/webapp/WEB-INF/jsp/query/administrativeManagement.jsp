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
<title>学校行政管理</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
	<style type="text/css">
	.pages li.disabled{padding:0px;}
   	.tips{
   		color:red;
   	}
	.u-wrap .mainbackground{height: 800px;}
	table .editDisableInput {text-align: center;background: #f6f6f6 !important;border: none !important;-webkit-appearance:none;}
	table .editDisable{text-align: center;background: #f6f6f6 !important;border: none !important;-webkit-appearance:none;}
	.opacityPopup,.opacityPopupAccount{display: none;position: fixed;left: 50%;top: 50%;width: 570px;height: 260px;background: #fff;z-index: 10;
		text-align: left;margin-top: -130px;margin-left: -275px;border: 1px solid #b2b2b2;border-radius: 5px;}
	.opacityPopupBg{display: none;width: 100%;height: 100%;background: rgba(0,0,0,.5);position: fixed;left: 0;top: 0;}
	.opacityPopup li{height: 35px;line-height: 35px;}
	.opacityPopup label{color: #868686;font-size: 14px;display: inline-block;width: 226px;text-align: right;margin-right: 14px;}
	.opacityPopup h2,.opacityPopupAccount h2{font-size: 16px;color: #333333;font-weight: normal;height: 38px;line-height: 38px;text-align: center;}
	.btnOpacity{text-align: center;}
	.btnOpacity a{display: inline-block;margin: 20px 50px;}
	.opacityPopupAccount >span{display: inline-block;width: 100%;text-align: center;height: 20px;line-height: 20px;}
	.chooseBtn{margin-bottom: 10px;height: 34px;line-height: 34px;text-align: center;}
	.chooseBtn li{display: inline-block;width: 148px;font-size: 12px;color: #333333;}
	.chooseBtn li.active{border-bottom: 1px solid #797979;}
	.modefyPassword label{display: inline-block;width: 200px;text-align: right;margin-right: 16px;color: #333333; }
	.modefyPassword li{height: 44px;line-height: 44px;}
	.resetPassword{width: 100%;text-align: center;height: 88px;line-height: 88px;color: #333333;font-size: 14px;}
	.resetPassword{display: none;}
	.schoolNameLength{width: 270px;}
	</style>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</head>
<body>
<input type="hidden" id="pageSize" value='${pageFinder.pageSize}'/>
<input type="hidden" id="pageNo" value='${pageFinder.pageNo}'/>
<input type="hidden" id="rowCount" value='${pageFinder.rowCount}'/>
<input type="hidden" id="selectCounts" value="10">
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics.jsp"></jsp:include>
	<div class="u-wrap query overflow">
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>
		<div class="right-side set-system">
			<div class="mainbackground nopadding">
				<div class="heading">
					<h2 class="h5">学校行政管理</h2>
					<span class="line"></span>
				</div>
				<form method="post" id="searchForm">
					<div>
						<input type="text" placeholder="分校名称/组织机构代码" style="margin-right: 5px;" id = "searchName">
						<c:choose>
							<c:when test="${isAdministrativeManagement eq 2}">
								<select name="eduArea" id="eduArea">
								<option value="${countyId}">${countyName}</option>
								</select>
							</c:when>
							<c:otherwise>
							<select name="eduArea" id="eduArea">
								<option value="">请选择区域</option>
								<c:forEach items="${areas}" var="area" >
									<option value="${area.id}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
								</c:forEach>
							</select>
							</c:otherwise>
						</c:choose>
						<select name="eduSchool" id="eduSchool">
							<option value="">请选择学校性质</option>
							<c:forEach items="${school}" var="school" >
								<option value="${school.id}" data-id="${school.id}" ${student.eduArea==school.itemValue?"selected":""}>${school.itemValue}</option>
							</c:forEach>
						</select>

							<a href="##" class="btn btn-primary searchAllSchool" style="margin-left: 5px;margin-bottom: 10px;">搜索</a>
							<a href="##" class="btn btn-primary addNewSchool" style="margin-left: 5px;float: right;">新增学校</a>

					</div>
				</form>
				<div class="user-list">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
							<th width="10%">序号</th>
							<th width="30%">学校名称</th>
							<th width="15%">组织机构代码</th>
							<th width="20%">区域</th>
							<th width="10%">学校性质</th>
							<th width="30%">操作</th>
						</tr>
						<c:forEach items="${pageFinder.data}" var="allSchool" varStatus="stauts">
						<tr>
							<td>${stauts.count}</td>
							<td><input type="text" value="${allSchool.itemValue}" disabled class="editDisable" ></td>
							<td><input type="text" value="${allSchool.itemCode}" disabled  class='editDisableInput'></td>
							<td>
								<select name="schoolArea" disabled class="editDisable">
								<option value="">${allSchool.dictName}</option>
								<c:forEach items="${areas}" var="area" >
								<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
								</c:forEach>
								</select>
							</td>
							<td>
								<select name="schoolNature" disabled class="editDisable">
								<option value="">${allSchool.dictCode}</option>
								<c:forEach items="${school}" var="school" >
								<option value="${school.itemCode}" data-id="${school.id}" ${student.eduArea==school.itemValue?"selected":""}>${school.itemValue}</option>
								</c:forEach>
								</select>
							</td>
							<td>
								<a href="##" class="modify"  schoolId='${allSchool.id}'>修改</a>/
								<a href="##" class="accountSettings">账号设置</a>
							</td>
						</tr>
						</c:forEach>
						<%--<c:choose>--%>
							<%--<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">--%>
								<%--<tr><td colspan="15">暂无数据</td></tr>--%>
							<%--</c:when>--%>
							<%--<c:otherwise>--%>
								<%--<tr><td colspan="14">暂无数据</td></tr>--%>
							<%--</c:otherwise>--%>
						<%--</c:choose>--%>


					</table>
					<div class="pages pagination"></div>
				</div>
			</div>
		</div>


		<!-- ajax加载中div开始 -->
		<div class="loading lp-units-loading" style="display:none">
			<p><i></i>加载中,请稍后...</p>
		</div>
		<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
		<!--  ajax加载中div结束 -->

			<!-- <div class="pages">
				<ul class="pagination">

				</ul>
			</div> -->

<%--新增学校弹窗--%>
<div class="opacityPopup">
	<h2>添加分校</h2>
	<ul>
		<li>
			<label for="">组织结构代码：</label>
			<input type="text" id = "schoolCode">
		</li>
		<li>
			<label for="">学校名称：</label>
			<input type="text" id = "addSchoolName">
		</li>
		<li>
			<label for="">区域：</label>
			<select name="" id="countyCode">
				<option value="">请选择区域</option>
				<c:forEach items="${areas}" var="area" >
					<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
				</c:forEach>
			</select>
		</li>
		<li>
			<label for="">性质：</label>
			<select name="" id="schoolPro">
				<option value="">请选择学校性质</option>
				<c:forEach items="${school}" var="school" >
					<option value="${school.itemCode}" data-id="${school.id}" ${student.eduArea==school.itemValue?"selected":""}>${school.itemValue}</option>
				</c:forEach>
			</select>
		</li>
	</ul>
	<div class="btnOpacity">
		<a href="##" class="btn btn-primary" id = "addSchool">确认添加</a>
		<a href="##" class="btn btn-primary">取消</a>
	</div>
</div>
<div class="opacityPopupBg"></div>
<%--账号设置弹窗--%>
<div class="opacityPopupAccount">
	<h2>分校管理员账号设置</h2>
	<span style="color: #ff0000" id='schoolNameNew'></span>
	<ul class="chooseBtn">
		<li class="active"><a href="##">修改密码</a></li>
		<li><a href="##">重置密码</a></li>
	</ul>
	<div>
		<ul class="modefyPassword" >
			<li>
				<label for="">输入密码：</label>
				<input type="password">
			</li>
			<li>
				<label for="">确认密码：</label>
				<input type="password">
			</li>
		</ul>
		<div class="resetPassword">
			重置账号密码为初始化密码？
		</div>
	</div>
	<div class="btnOpacity">
		<a href="##" class="btn btn-primary" id = "password">确认修改</a>
		<a href="##" class="btn btn-primary">取消</a>
	</div>
</div>


<%--<script type="text/javascript" src="<%=rootPath %>/javascripts/query/query_student.js"></script>--%>
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
(function ($) {
    var company = {
        init: function () {
            var $this = this;
            // 初始化数据
            $this.search(1,null);
            //搜索
            $(".searchAllSchool").on('click', function () {
                $this.search(1);
            });
        },
        searchCount: function(){
        	$("#selectCounts").val($("#selectCount").val());
        	$this.search(1);
        },
        search: function (page) {
            var $this = this;
            var data = {};
            var searchName = $("#searchName").val();
    		var eduArea = $("#eduArea").val();
    		var eduSchool  = $("#eduSchool").val();
            data.searchName=$("#searchName").val();
            data.eduArea=$("#eduArea").val();
            data.eduSchool=$("#eduSchool").val();
            data.page = page ? page : 1;
            $(".user-list").find("table").find("tr:gt(0)").remove();
            //代理机构权限
            $.ajax({
                    url: rootPath + "/jsp/searchAdministrativeManagement",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                        if (jsonData.data.length == 0) {
	                          $(".user-list").find("table").append('<tr><td colspan="15">没有查找到数据</td></tr>');
                        }
                        $.each(jsonData.data,function (i, allSchool) {
                            var eduIdentity = null;
                                $(".user-list")
                                    .find("table")
                                    .append('<tr>'+
                                    		'<td>'+((jsonData.pageNo-1)*jsonData.pageSize+i+1)+'</td>'+
                							'<td>'+'<input type="text" value="'+allSchool.itemValue+'" disabled class="editDisable schoolNameLength" title="'+allSchool.itemValue+'" >'+'</td>'+
                							'<td>'+'<input type="text" value="'+allSchool.itemCode+'" disabled  class="editDisableInput">'+'</td>'+
                							'<td>'+'<select name="schoolArea" disabled class="editDisable">'+
                								'<option value="">'+allSchool.dictName+'</option>'+
                								'<c:forEach items="${areas}" var="area" >'+
                								'<option value="${area.itemCode}" data-id="${area.id}">${area.itemValue}</option>'+
                								'</c:forEach>'+
                								'</select>'+
                							'</td>'+
                							'<td>'+
                								'<select name="schoolNature" disabled class="editDisable">'+
                								'<option value="">'+allSchool.dictCode+'</option>'+
                								'<c:forEach items="${school}" var="school" >'+
                								'<option value="${school.itemCode}" data-id="${school.id}">${school.itemValue}</option>'+
                								'</c:forEach>'+
                								'</select>'+
                							'</td>'+
                							 '<td><a href="##" class="modify"  schoolId="'+allSchool.id+'">修改</a>/'+
                								'<a href="##" class="accountSettings">账号设置</a>'+
                							'</td>'+
                						'</tr>');
                            });
                        $("#rowCount").remove();
                        $("#pageNo").remove();
                        $(".user-list").after('<input type="hidden" id="pageNo" value="'+jsonData.pageNo+'"/>');
                        if (jsonData.rowCount >$("#selectCounts").val()) {
                            $(".pagination").pagination(jsonData.rowCount,
                                {
                                    next_text: "下一页",
                                    prev_text: "上一页",
                                    current_page: jsonData.pageNo - 1,
                                    link_to: "javascript:void(0)",
                                    num_display_entries: 5,
                                    items_per_page: jsonData.pageSize,
                                    num_edge_entries: 1,
                                    callback: function (page, jq) {
                                        var pageNo = page + 1;
                                        $this.search(pageNo);
                                    }
                                });
                            $(".pagination").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');
                            /* $(".pagination").find("li:first").before('每页：<select id="selectCount"  onchange="javascript:company.searchCount()">'+
                					' <option value="10">10</option>'+
                					' <option value="20">20</option>'+
                					' <option value="30">30</option>'+
                					' <option value="50">50</option>'+
                					' <option value="100">100</option>'+
                					' </select> 条   '); */
                            $("#selectCount").val($("#selectCounts").val());
                        } else {
                            $(".pagination").html('');
                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
            $("#maxCount").remove();
        }
    }

    $(document).ready(function () {
    	company.init();
    })
    window.company=company;
})(jQuery)
</script>

<script type="text/javascript">
$(function(){
	//搜索
	/* $('.searchAllSchool').click(function(){
		var searchName = $("#searchName").val();
		var eduArea = $("#eduArea").val();
		var eduSchool  = $("#eduSchool").val();
		$.ajax({
   	        type : 'post',
   	        url : rootPath + '/jsp/searchAdministrativeManagement',
   	        data : {
   	        	searchName:searchName,
   	        	eduArea:eduArea,
   	        	eduSchool:eduSchool
   	        },
   	        success : function(data){
				
   	        }
   	    });
	}); */
	//判断组织机构代码已存在
	$('#schoolCode').blur(function(){
		var schoolCode = $("#schoolCode").val();
		if(schoolCode.length > 0){
		$.ajax({
   	        type : 'post',
   	        url : rootPath + '/jsp/checkCode',
   	        data : {
   	        	schoolCode:schoolCode	  
   	        },
   	        success : function(data){
   	        	if(data=="false"){
   	        		$.msg("组织机构代码已存在");
   	        	}
   	        }
   	    })
		}
	});
	//判断学校名称已存在
	$('#addSchoolName').blur(function(){
		var schoolName = $("#addSchoolName").val();
		if(schoolName.length > 0){
		$.ajax({
   	        type : 'post',
   	        url : rootPath + '/jsp/checkName',
   	        data : {
   	        	schoolName:schoolName	  
   	        },
   	        success : function(data){
   	        	if(data=="false"){
   	        		$.msg("学校名称已存在");
   	        	}
   	        }
   	    })
		}
	});
	
	
	//添加学校
	$("#addSchool").click(function(){
		var schoolName = $("#addSchoolName").val();
		var schoolCode = $("#schoolCode").val();
		var countyCode = $("#countyCode").val();
		var schoolPro  = $("#schoolPro").val();
		
		if(schoolName == null || schoolName == ''){
			$.msg("学校名称不能为空");
			return;
		}
		if(schoolCode == null || schoolCode == ''){
			$.msg("组织机构代码不能为空");
			return;
		}
		if(countyCode == null || countyCode == ''){
			$.msg("区域不能为空");
			return;
		}
		if(schoolPro == null || schoolPro == ''){
			$.msg("学校性质不能为空");
			return;
		}
		if(schoolName.length < 5){
			$.msg("学校名称长度不能小于5个字符");
			return;
		}
		if(isNaN(schoolCode)) { 
			$.msg("组织机构代码只允许输入数字！"); 
			return; 
		} 
		$.ajax({
   	        type : 'post',
   	        url : rootPath + '/jsp/addSchool',
   	        data : {
   	        	schoolName:schoolName,
   	        	schoolCode:schoolCode,
   	        	countyCode:countyCode,
   	        	schoolPro:schoolPro
   	        },
   	        success : function(data){
   	        	if(data=="success"){
   	        		$.msg("添加成功");
   	        	}else{
   	        		alert("添加失败");
   	        	}
   	        }
   	    });
	});
	//修改密码
	$("#password").click(function(){
		if($('.chooseBtn').children('li').eq(0).hasClass('active')){
			var password = $('.modefyPassword').children('li').eq(0).children('input').val();
			var passwordNew = $('.modefyPassword').children('li').eq(1).children('input').val();
			if(password == null){
				$.msg("密码为空");
				return;
			}
			if(passwordNew != password){
				$.msg("两次密码不相等");
				return;
			}
			if(passwordNew == password){
				$.ajax({
		   	        type : 'post',
		   	        url : rootPath + '/jsp/updatePassword',
		   	        data : {
		   	        	password:password,
		   	        	schoolCode:schoolCode
		   	        },
		   	        success : function(data){
		   	        	if(data=="success"){
		   	        		$.msg("保存成功");
		   	        	}else{
		   	        		alert("保存失败");
		   	        	}
		   	        }
		   	    });
			}
		}else{
			$.ajax({
	   	        type : 'post',
	   	        url : rootPath + '/jsp/resetPassword',
	   	        data : {
	   	        	schoolCode:schoolCode
	   	        },
	   	        success : function(data){
	   	        }
	   	    });
		}
		
	});
	//点击修改
	$('table').on('click','.modify',function(){
	    $(this).parent('td').siblings('td').children().removeClass('editDisable').attr('disabled',false);
	    if($(this).html()=='修改'){
            $(this).html("保存");
            
		}else {
			var id = $(this).attr('schoolId');
            $(this).html("修改");
            $(this).parent('td').siblings('td').children().addClass('editDisable').attr('disabled',true);
            var testschool = $(this).parent('td').siblings('td').children();
            console.log(testschool);
            var itemValue=testschool.eq(0).val();
            var itemCode=testschool.eq(1).val();
            var dictCode=testschool.eq(2).val();
            var dictName=testschool.eq(3).val();
            $.ajax({
	   	        type : 'post',
	   	        url : rootPath + '/jsp/updateManagement',
	   	        data : {
	   	        	id:id,
	   	        	itemValue:itemValue,
	   	        	itemCode:itemCode,
	   	        	dictCode:dictCode,
	   	        	dictName:dictName
	   	        },
	   	        success : function(data){
	   	        	if(data=="success"){
	   	        		$.msg("保存成功");
	   	        		var timer = setTimeout(function(){
	   	        			window.location.href = rootPath+"/jsp/AdministrativeManagement";
	   	        		},2000);
	   	        		
	   	        	}else{
	   	        		alert("保存失败");
	   	        	}
	   	        }
	   	    });
		}
	});
	//点击确定取消，隐藏弹窗
	$('.btnOpacity').children().click(function () {
		$('.opacityPopup').fadeOut();
		$('.opacityPopupBg').fadeOut();
        $('.opacityPopupAccount').fadeOut();
    });
	//点新增页面弹出弹窗
	$('.addNewSchool').click(function(){
        $('.opacityPopup').fadeIn();
        $('.opacityPopupBg').fadeIn();
	});
	//点击账号设置弹出弹窗
	var schoolName;
	var schoolCode;
	$('body').on('click','.accountSettings',function(){
        $('.opacityPopupAccount').fadeIn();
        $('.opacityPopupBg').fadeIn();
         schoolName = $(this).parents('td').siblings('td').eq(1).children().val();
         schoolCode = $(this).parents('td').siblings('td').eq(2).children().val();
        $('#schoolNameNew').html(schoolName);
    });
	//修改密码和重置密码的切换
	$('.chooseBtn').children().click(function(){
	    $(this).addClass("active");
        $(this).siblings('li').removeClass("active");
	    if($(this).index()==0){
	        $('.modefyPassword').show();
	        $('.resetPassword').hide();
        }else {
            $('.modefyPassword').hide();
            $('.resetPassword').show();
		}
	});


//分页
    /* $(".pagination").pagination($("#rowCount").val(),
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page:($("#pageNo").val() - 1),
            link_to: "javascript:void(0)",
            num_display_entries: 5,
            items_per_page: 1,
            num_edge_entries: $("#pageSize").val(),
            callback: function (page, jq) {
                var pageNo = page + 1;
                $this.search(pageNo);
            }
        }
    ); */
    
}
);
	



	$selectSubMenu('AdministrativeManagement');
</script>
</body>
</html>