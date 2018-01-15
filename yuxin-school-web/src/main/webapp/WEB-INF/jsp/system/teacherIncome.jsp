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
	@charset 'utf-8';
	/* CSS reset */
	html{color:#000;background:#FFF;font-family: 'Microsoft YaHei UI','Microsoft YaHei',SimSun,'Segoe UI',Tahoma,Helvetica,Sans-Serif;}
	body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,button,textarea,p,blockquote,th,td,strong{padding:0;margin:0;font-family: 'Microsoft YaHei UI','Microsoft YaHei',SimSun,'Segoe UI',Tahoma,Helvetica,Sans-Serif;}
	table{border-collapse:collapse;border-spacing:0;}
	fieldset,img{border:0;}
	a{text-decoration:none; color:#00c; outline:none;}/*此处待添加默认链接颜色*/
	var,em,strong{font-style:normal;}
	address,caption,cite,code,dfn,em,strong,th,var, optgroup{font-style:inherit;font-weight:inherit;}
	del,ins{text-decoration:none;}
	li{list-style:none;}
	caption,th{text-align:left;}
	h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:normal;}
	q:before,q:after{content:'';}
	abbr,acronym{border:0;font-variant:normal;}
	sup{vertical-align:baseline;}
	sub{vertical-align:baseline;}
	legend{color:#000;}
	input,button,textarea,select,optgroup,option{font-family:inherit; font-size:inherit;font-style:inherit;font-weight:inherit;}
	input,button,textarea,select{*font-size:100%;}
	.clearfix:after {content:"\200B"; display:block; height:0; clear:both; }
	.clearfix { *zoom:1; }
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
	.detailIncome{text-align: center;padding: 0 38px;position: fixed;width: 380px;height: 406px;z-index: 1000;left: 50%;top: 50%;background: #fff;
	margin-top: -203px;margin-left: -209px;}
	.detailIncome h5{height: 40px;line-height: 40px;text-align: center;font-size: 14px;color: #000;}
	.detailIncome table{background: #f6f6f6;width: 100%;border: 1px solid #c9c9c9;}
	.detailIncome tr{height: 26px;border: 1px solid #c9c9c9;}
	.detailIncome table td:not(:last-child){text-align: center;border-right: 1px solid #c9c9c9;}
	.detailIncome table th:not(:last-child){border-right: 1px solid #c9c9c9;}
	.detailIncome table th{text-align: center;background: #eeeeee;}
	.detailIncome >a{margin: 10px 10px 0;}
	.detailIncome,.opacityIncome{display: none;}

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


<%--详情弹窗--%>
<div class="detailIncome">
	<h5 style="display: inline-block;">收入明细</h5>
	<i class="icon iconfont closeIncome" style="float: right;margin-top: 10px;">&#xe610;</i>
	<table>
		<col width="20%">
		<col width="40%">
		<col width="40%">
		<tr>
			<th>序号</th>
			<th>课程名称</th>
			<th>收入（元）</th>
		</tr>
		<tr>
			<td>1</td>
			<td>如何学好英语</td>
			<td>12,122.00</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<a href="##" class="btn btn-default">上一页</a>
	<a href="##" class="btn btn-primary">下一页</a>
</div>
<div class="opacityIncome" style="position: fixed;left: 0;top: 0;background: rgba(0,0,0,0.5);width: 100%;height: 100%;"></div>


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

//关闭弹窗
	$('.closeIncome').click(function(){
	    $('.detailIncome').fadeOut();
	    $('.opacityIncome').fadeOut();
	});
//点击详情，打开弹窗
	$('.detailIncomeList').click(function(){
        $('.detailIncome').fadeIn();
        $('.opacityIncome').fadeIn();
	});

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