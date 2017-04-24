<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
<title>考试统计</title>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classes.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classedit.css" />
</head>
<body>
	<%@include file="/WEB-INF/jsp/menu/menu_tiku.jsp"%>
	<div class="u-wrap company clear">
		<div class="mainbackground nopadding paddno">
			<div class="heading-box">
				<div class="heading">
					<h2 class="h5">考试统计</h2>
					<span class="line"></span>
					<div class="new-set"
						onclick="location.href='<%=rootPath%>/tikuExam/toTikuExamIndex'">返回</div>
				</div>
			</div>
			<div class="exam-box examnew-mg20">
				<div class="exam-line">
					<div class="td1">
						<span>通过率：${rate.examPassRate == null?'0.0':rate.examPassRate}%</span>
					</div>
					<div class="td2 single">
						<div>
							报名考试总人数：<em>${rate.allPeople == null?'0': rate.allPeople}人</em>
						</div>
					</div>
					<div class="td3 single">
						<div>
							通过人数：<em>${rate.passPeople == null?'0':rate.passPeople }人</em>
						</div>
					</div>
					<div class="td41 single">
						<div>
							未通过人数：<em>${rate.allPeople-rate.passPeople}人</em>
						</div>
					</div>
					<div class="td51"></div>
				</div>
				<div id="info"></div>
				<div class="pages">
					<ul class="pagination">

					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="pop-fixed examnew-pop">
		<div class="select-board">
			<div class="board-title">
				<span>学员考试情况查询</span> <em class="iconfont pop-close-btn">&#xe610;</em>
			</div>
			<div class="select-content">
				<div class="selects" style="height: 85px;line-height: 40px;">
					<select name="xk" id="paperName" style="width: 200px;">
						<option value="0">全部</option>
						<c:forEach items="${paperList }" var="p">
							<option value="${p.tikuPaperId }">${p.paperName }</option>
						</c:forEach>
					</select>
					 <select name="km" id="passStatus">
						<option value="0" selected="selected">全部</option>
						<option value="1">通过</option>
						<option value="-1">不通过</option>
					</select> 
					<input type="text" placeholder="输入学员手机号或用户名" id="mobile" class="input-name" /> 
					<span id="searchInfo">搜索</span>
					<span id="exportexcle">导出数据</span>
					<input type="text" class="laydate-icon" readonly="readonly" id="start" />至
					<input type="text" class="laydate-icon" readonly="readonly" id="end" />
				</div>
				<div>
					<div id="detailList"></div>
				</div>
				<div class="pages">
					<ul class="paginations">

					</ul>
				</div>
			</div>
			<div class="page-btn-box"></div>
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
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath%>/javascripts/classedit.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/laydate/laydate.js"></script>
	<script src="<%=rootPath%>/javascripts/tiku/exam/statistics.js"></script>
	<input type="hidden" value="${examId }" id="examId" />
	<script type="text/javascript">
	$(function(){
		$selectMenu('tiku_header');		
		$("body").find(".exam").addClass("active");
		/*选择日期*/
		laydate({
			elem : '#start',
			format : 'YYYY-MM-DD ',
			/* min: laydate.now(), //设定最小日期为当前日期 */
			max : laydate.now(), //最大日期
			istime : true,
			istoday : false,
			choose : function(datas) {
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas //将结束日的初始值设定为开始日
			}
		});
		laydate({
			elem : '#end',
			format : 'YYYY-MM-DD',
			/* min : laydate.now(), */
			max : '2099-06-16 23:59:59',
			istime : true,
			istoday : false,
			choose : function(datas) {
				start.max = datas; //结束日选好后，重置开始日的最大日期
			}
		});
		/*选择日期  end*/
	})
	</script>
</body>
</html>