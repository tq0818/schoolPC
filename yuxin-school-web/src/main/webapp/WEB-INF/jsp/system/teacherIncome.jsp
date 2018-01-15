<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>老师收入</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<%--<script type="text/javascript" src="<%=rootPath%>/javascripts/system/order.js"></script>--%>
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
		<div class="mainbackground nopadding allOrderContent">
			<c:if test="${isArea eq 0}">
				<div style="margin: 12px 0;">
					<label for="schoolList">请选择分校：</label>
					<select name="school" id="companyList" style="margin-left: 28px;">
						<option value="">全部</option>
						<c:forEach items="${schoolList }" var="school">
							<option value="${school.companyId }">${school.itemValue }</option>
						</c:forEach>
					</select>
				</div>
			</c:if>
			<div class="allOrderHeader">
				<label for="date">查询日期：</label>
				<input type="text" style="margin-right: 10px" id="inpstart" readonly>至
				<input type="text" id="inpend" readonly>
				<button class="btn btn-primary" onclick="queryTeacherMoney();">查询</button>
			</div>
			<div class="user-list allOrderTable" id="teacherFetch">

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
//		日历插件
var start = {
    format: 'YYYY-MM-DD',
//    isinitVal:true,
    onClose:false,
    maxDate: $.nowDate({DD:0}), //最大日期
    okfun: function(obj){
        end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
//        endDates();
    }
};
var end = {
    format: 'YYYY-MM-DD',
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
			$selectSubMenus('teacherIncome');
			queryTeacherMoney(1);
		});

		function queryTeacherMoney(){
			if($("#inpstart").val()){
				if(!$("#inpend").val()){
					alert("请选择查询时间")
					return;
				}
			}
			queryTeacherMoney(1);
		}

		function queryTeacherMoney(pageNo){

			var companyList = $.trim($("#companyList").val());
			var inpstart = $("#inpstart").val();
			var inpend = $("#inpend").val();


			$.ajax({
				url : "/payOrder/queryTeacherMoney",
				type:"post",
				data:{"page":pageNo,"pageSize":10,"schoolId":companyList, "inpstart":inpstart, "inpend":inpend},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
					$(".loading").show();
					$(".loading-bg").show();
				},
				success:function(data){
					$("#teacherFetch").html("").html(data);
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