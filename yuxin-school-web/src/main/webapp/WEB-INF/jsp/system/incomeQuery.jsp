<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>分校收入</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<style type="text/css">
.head-div {
	position: relative;
	margin-top: 15px;
	padding: 3px 8px;
}

.font-size {
	font-size: 14px;
	margin-left: 10px;
	margin-right: 11px;
}

#schoolId{
	width: 150px;
}
</style>

	<%--tob--%>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/jeUI/jedate.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
	<script  src="<%=rootPath%>/plugins/jeUI/jquery.jedate.min.js" ></script>
	<script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	<div class="u-wrap admin overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_operaconfig.jsp"></jsp:include>
	<div class="right-side">
		<form action="<%=rootPath%>/payOrder/exportExcelschoolMoney" id="searchForm" method="post"></form>
			<div class="mainbackground nopadding allOrderContent">
				<c:if test="${isArea eq 0}">
					<div>
						<label for="areaId">请选择分校区域：</label>
						<select name="areaId" id="areaId">
							<option value="">全部</option>
							<c:forEach items="${firstItems }" var="area">
								<option value="${area.id }">${area.itemValue }</option>
							</c:forEach>
						</select>
					</div>
					<div style="margin: 12px 0;">
						<label for="schoolId">请选择分校：</label>
						<select name="schoolId" id="schoolId" style="margin-left: 28px;">
							<option value="">全部</option>
							<option value="18113">成都数字学校</option>
						</select>
					</div>
				</c:if>
				<div class="allOrderHeader">
					<label for="">请选择查询日期：</label>
					<input type="text" style="margin-right: 10px" name="inpstart" id="inpstart" readonly>至
					<input type="text" id="inpend" name="inpend" readonly>
					<button class="btn btn-primary" onclick="toQuery();">查询</button>
					<button class="btn btn-primary" onclick="exportData();">导出数据</button>
					<div style="display: inline-block;float: right;margin-right: 10px;margin-top: 20px;">
						总收入<span style="color: red;" id="totalMoneyId">0</span>元
					</div>
				</div>
				<div class="user-list allOrderTable" id="moneyList">

				</div>
			</div>

		<div class="pages">
			<ul class="pagination">
			</ul>
		</div>
	</div>
	</div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->

	<script>
//	分页
    $(".pagination").pagination('',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page: '',
            link_to: "javascript:void(0)",
            num_display_entries: 8,
            items_per_page: 1,
            num_edge_entries: '',
            callback: function (page, jq) {
                var pageNo = page + 1;

            }
        }
    );

</script>
	<script>
//		日历插件
var start = {
    format: 'YYYY-MM-DD hh:mm:ss',
//    isinitVal:true,
    onClose:false,
    maxDate: $.nowDate({DD:0}), //最大日期
    okfun: function(obj){
        end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
//        endDates();
    }
};
var end = {
    format: 'YYYY-MM-DD hh:mm:ss',
    onClose:false,
    maxDate: '2099-06-16 23:59:59', //最大日期
    okfun: function(obj){
        start.maxDate = obj.val; //将结束日的初始值设定为开始日的最大日期
    }
};

//或者是
$.jeDate('#inpstart',start);
$.jeDate('#inpend',end);



	</script>
	<script type="text/javascript">
		$(function() {
			$selectSubMenu('financial');
			$selectSubMenus('incomeQuery');

			$("#areaId").change(function(){
				selSchool();
			});
			querySchoolMoney(1);
		});
		function toQuery(){
			if($("#inpstart").val()){
				if(!$("#inpend").val()){
					alert("请选择查询时间")
					return;
				}
			}
			querySchoolMoney(1);
		}

		function selSchool(){
			$("#schoolId").empty();
			$("#schoolId").append("<option value=\"\">全部</option>");
			var areaId =$("#areaId").val();
			if(!areaId){
				$("#schoolId").append("<option value=\"18113\">成都数字学校</option>");
				return;
			}
			$.ajax({
				url:"/jsp/selSchool",
				type:"post",
				data:{"areaId":areaId},
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
					$(".loading").show();
					$(".loading-bg").show();
				},
				success:function(data){
					$.each(data.school,function(index,item){
						$("#schoolId").append("<option value='" + item.companyId + "'>" + item.itemValue + "</option>");
					});
				},
				complete:function(XMLHttpRequest,textStatus){

					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		}


		function exportData(){
			if($("#inpstart").val()){
				if(!$("#inpend").val()){
					alert("请选择查询时间")
					return;
				}
			}
			$("#searchForm").empty();
			var inputs = '<input type="hidden" name="page" value="1"/>' +

					'<input type="hidden" name="inpstart" value="'+$("#inpstart").val()+'"/>' +
					'<input type="hidden" name="inpend" value="'+$("#inpend").val()+'"/>';
			if("${isArea}"=="0"){
				inputs+='<input type="hidden" name="areaId" value="'+$("#areaId").val()+'"/>' +
				'<input type="hidden" name="schoolId" value="'+$("#schoolId").val()+'"/>';
			}

			$("#searchForm").append(inputs);
			$("#searchForm").submit();

		}


		function querySchoolMoney(pageNo,sort){

			var areaId = $.trim($("#areaId").val());
			var schoolId = $("#schoolId").val();
			var inpstart = $("#inpstart").val();
			var inpend = $("#inpend").val();
			if("totalSort"==sort){

			}
			if("fetchSort"==sort){

			}

			$.ajax({
				url : "/payOrder/querySchoolMoney",
				type:"post",
				data:{"page":pageNo,"pageSize":10,"areaId":areaId,"schoolId":schoolId,"inpstart":inpstart,"inpend":inpend},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
					$(".loading").show();
					$(".loading-bg").show();
				},
				success:function(data){
					$("#moneyList").html("").html(data);
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		}
	</script>
</body>
</html>