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
<title>用户统计</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<style type="text/css">
		.pages li.disabled{padding:0px;}
	</style>
<script type="text/javascript">
	function exportExcleArea() {
            if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                $.msg("没有数据可以导出");
            } else {
                $("#searchForm").attr("action",rootPath + "/student/exportUserInfoExcle").submit();
            }
	}
	function timePX() {
		if($("#time").val()==1){
			//1 asc 2 desc
			$("#time").val("2")
			$('#paixu').val(1);
			searchData($("#pageNo").val());
		}else{
			$("#time").val("1")
			$('#paixu').val(2);
			searchData($("#pageNo").val());
		}
	}
	function penNumPX () {
		if($("#penNum").val()==3){
			//3 asc 4 desc
			$("#penNum").val("4")
			$('#paixu').val(3);
			searchData($("#pageNo").val());
		}else{
			$("#penNum").val("3")
			$('#paixu').val(4);
			searchData($("#pageNo").val());
		}
	}
	function lessNumPX () {
		if($("#lessNum").val()==7){
			//7 asc 8 desc
			$("#lessNum").val("8")
			$('#paixu').val(7);
			searchData($("#pageNo").val());
		}else{
			$("#lessNum").val("7");
			$('#paixu').val(8);
			searchData($("#pageNo").val());
		}
	}
	function clasNumPX(){
		if($("#clasNum").val()==5){
			//5 asc 6 desc
			$("#clasNum").val("6");
			$('#paixu').val(5);
			searchData($("#pageNo").val());
		}else{
			$("#clasNum").val("5");
			$('#paixu').val(6);
			searchData($("#pageNo").val());
		}
	}
	function searchData(page){
        var data = {};
        data.eduIdentity = $("#eduIdentity").val();
        data.name = $("#stuName").val();
        data.startTime = $("#startTime").val();
        data.endTime = $('#endTime').val();
        data.page = page ? page : 1;
        data.paixu=$('#paixu').val();
        data.pageSize=$("#selectCounts").val() || 10;
        $.each(data, function (key, value) {
            if (!value) {
                delete data[key];
            }
        })
        $(".user-list").find("table").find("tr:gt(0)").remove();
        //代理机构权限
        $.ajax({
                url: rootPath + "/student/queryUserListData",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest){
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (jsonData) {
                    if (jsonData.data.length == 0) {
                    		  $(".user-list")
                                .find("table")
                                .append(
                                '<tr><td colspan="14">没有查找到数据</td></tr>');
                    }
                    $.each(jsonData.data,function (i, stu) {
                            $(".user-list")
                                .find("table")
                                .append('<tr>'+
            							'<td width="8%">'+stu.name+'</td>'+
            							'<td width="5%">'+stu.eduIdentityName+'</td>'+
            							'<td width="8%">'+stu.mobile+'</td>'+
            							'<td width="8%">'+stu.createTime+'</td>'+
            							'<td width="6%">'+stu.finishCount+'</td>'+
            							'<td width="6%">'+stu.totalCount+'</td>'+
            							'<td width="5%">'+stu.payPrice+'</td></tr>');
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
                                num_display_entries: 8,
                                items_per_page: jsonData.pageSize,
                                num_edge_entries: 1,
                                callback: function (page, jq) {
                                    var pageNo = page + 1;
                                    searchData(pageNo);
                                }
                            });
                        $(".pagination").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');
                        $(".pagination").find("li:first").before('每页：<select id="selectCount"  onchange="javascript:searchCount()">'+
            					' <option value="10">10</option>'+
            					' <option value="20">20</option>'+
            					' <option value="30">30</option>'+
            					' <option value="50">50</option>'+
            					' <option value="100">100</option>'+
            					' </select> 条');
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
	}
	function searchCount(){
    	$("#selectCounts").val($("#selectCount").val());
    	searchData();
    }
	$(document).ready(function () {
		searchData();
    })
</script>
</head>
<body>
<input type="hidden" id="schoolId" value='${schoolId}'/>
		<input type="hidden" id="schoolName" value='${schoolName}'/>
		<input type="hidden" id="isAdmin" value='${isAdmin}'/>
		<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_area.jsp"></jsp:include>
	<div class="u-wrap query overflow">
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query_area.jsp"></jsp:include>
		<div class="right-side set-system">
			<div class="mainbackground nopadding">
				<div class="heading">
					<h2 class="h5">${area.itemValue}用户列表</h2>
					<span class="line"></span>
				</div>
				<form method="post" id="searchForm">
					<div>
						<input type="text" id="stuName" name="name" placeholder="姓名"/>
						<%-- <input type="hidden" id="eduArea" name="eduArea" value="${area.itemCode}"/> --%>
						<input type="hidden" id="paixu" name="paixu" value=""/>
						<select name="eduIdentity" id="eduIdentity">
							<option value="">请选择身份</option>
							<option value="0">学生</option>
							<option value="1">普通用户</option>
						</select>
					</div>
					<div style="margin: 10px 0;">
						<span>创建时间</span>
						<span><input type="text" name="startTime" id="startTime" class="date-picker from"/><em> 到 </em><input type="text" name="endTime" id="endTime" class="date-picker to"/></span>
						<c:if test="${address==1}">
						<span style="padding:0 15px;" id="caddress">
							<select id="prov" name="province"></select>
							<select id="city" name="city"></select>
							<select id="dist" name="county"></select>
						</span>
						</c:if>
						<span><a href="javascript:;" class="btn btn-primary searchContents" onclick="searchData(1);">搜索</a></span>
						<span class="fr"><a href="javascript:;" class="btn btn-primary exportExcleArea" onclick="exportExcleArea();">导出数据</a></span>
					</div>
				</form>
				<div class="user-list">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
							<th width="8%">姓名</th>
							<th width="5%">身份</th>
							<th width="8%">手机号</th>
							<th width="8%" class="btn-sort time" onclick="timePX();"> <input type="hidden" id="time" value="1"/>创建时间</th>
							<th width="6%" class="btn-sort penNum" onclick="penNumPX();">已报名课程数<input type="hidden" id="penNum" value="4"/></th>
							<th width="6%" class="btn-sort lessNum" onclick="lessNumPX();">学习课程数<input type="hidden" id="lessNum" value="8"/></th>
							<th width="5%" class="btn-sort clasNum" onclick="clasNumPX();">已消费金额<input type="hidden" id="clasNum" value="6"/></th>
						</tr>
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
	</div>
<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
 <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>--%>
<%--<script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script> &ndash;%&gt;--%>
<script type="text/javascript">
	$selectSubMenu('userList');
    //    左侧active切换
    $selectThirdMenu('userList');
</script>
</body>
</html>