<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>跑马灯设置</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/marquee/marquee.css" />
<link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>/stylesheets/minitip.css" rel="stylesheet"
	type="text/css" />

<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
<%-- <script type="text/javascript"
	src="<%=rootPath%>/javascripts/divselect.js"></script> --%>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/marquee/marquee.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/marquee/colorjs.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap operate overflow">
		<%@include file="/WEB-INF/jsp/company/videoCommonLeft.jsp"%>
		<div class="right-side">
			<div class="mainbackground">
				<c:if test="${marquee.stauts == 1 }">
					<div class="heading">
						<span style="font-size: 14px;">跑马灯</span>
						<span style="margin-left: 20px; font-size: 14px;">
							<i class="iconfont open oc">&#xe603;</i>
							<span class="i open">&nbsp;已启用</span>
						</span>
					</div>
					<span class="line"></span>
				</c:if>
				<c:if test="${marquee.stauts != 1 }">
					<div>
						<span style="font-size: 14px;">跑马灯</span><span
							style="margin-left: 20px; font-size: 14px;"><i
							class="iconfont close oc">&#xe604;</i><span class="i close">&nbsp;已禁用</span></span>
					</div>
				</c:if>
				<p>
					<span style="color: red">* 添加跑马灯效果，防止其他机构恶意录屏。</span>
				</p>
			</div>
			<c:if test="${marquee.stauts == 1 }">
				<div class="mainbackground option">
			</c:if>
			<c:if test="${marquee.stauts != 1 }">
				<div class="mainbackground option" style="display: none;">
			</c:if>
			<c:if test="${empty cms.videoServiceProvider 
				or cms.videoServiceProvider eq 'cc'
				or cms.videoServiceProvider eq 'letv'
				or cms.videoServiceProvider eq 'qnvd'}">
			<div class="heading" style="margin-top: -20px">
				<span style="font-size: 14px;">跑马灯滚动方式</span>
			</div>
			<span class="line"></span>
			<div class="select-option clear">
				<ul class="marg">
					<li data-id="6">
						<div class="jian">
							<div style="margin-left: 80px; margin-top: 50px">
								<span>盗版必究</span>
							</div>
							<div style="margin-left: 70px">
								<img alt="" src="<%=rootPath%>/images/marquee/left1.png">
							</div>
						</div>
						<div class="fonts">文字从右向左移动</div>
					</li>
					<li data-id="5">
						<div class="jian">
							<div style="margin-left: 80px; margin-top: 50px">
								<span>盗版必究</span>
							</div>
							<div style="margin-left: 70px">
								<img alt="" src="<%=rootPath%>/images/marquee/right1.png">
							</div>
						</div>
						<div class="fonts">文字从左往右移动</div>
					</li>
					<li data-id="4">
						<div class="jian">
							<div style="margin-left: 80px; margin-top: 40px">
								<span>盗版必究</span>
							</div>
							<div style="margin-left: 70px">
								<img alt="" src="<%=rootPath%>/images/marquee/ld1.png">
							</div>
						</div>
						<div class="fonts">文字从右上向左下移动</div>
					</li>
					<li data-id="1">
						<div class="jian">
							<div style="margin-left: 80px; margin-top: 40px">
								<span>盗版必究</span>
							</div>
							<div style="margin-left: 70px">
								<img alt="" src="<%=rootPath%>/images/marquee/rd1.png">
							</div>
						</div>
						<div class="fonts">文字从左上向右下移动</div>
					</li>
					<li data-id="8">
						<div class="jian">
							<div style="margin-left: 100px; margin-top: 60px">
								<span>盗版必究</span>
							</div>
							<div style="margin-left: 70px; margin-top: -50px">
								<img alt="" src="<%=rootPath%>/images/marquee/up1.png">
							</div>
						</div>
						<div class="fonts">文字从下向上移动</div>
					</li>
					<li data-id="7">
						<div class="jian">
							<div style="margin-left: 100px; margin-top: 60px">
								<span>盗版必究</span>
							</div>
							<div style="margin-left: 70px; margin-top: -50px">
								<img alt="" src="<%=rootPath%>/images/marquee/down1.png">
							</div>
						</div>
						<div class="fonts">文字从上向下移动</div>
					</li>
					<li data-id="2">
						<div class="jian">
							<div style="margin-left: 70px; margin-top: 40px">
								<img alt="" src="<%=rootPath%>/images/marquee/lu1.png">
							</div>
							<div style="margin-left: 80px">
								<span>盗版必究</span>
							</div>
						</div>
						<div class="fonts">文字从右下向左上移动</div>
					</li>
					<li data-id="3">
						<div class="jian">
							<div style="margin-left: 70px; margin-top: 40px">
								<img alt="" src="<%=rootPath%>/images/marquee/ru1.png">
							</div>
							<div style="margin-left: 80px">
								<span>盗版必究</span>
							</div>
						</div>
						<div class="fonts">文字从左下向右上移动</div>
					</li>
				</ul>
			</div>
			<div class="heading">
				<span style="font-size: 14px;">选择循环次数</span>
			</div>
			<span class="line"></span>
			<div class="select-option clear">
				<p>
					<span class="te">跑马灯循环次数</span>
					<c:if test="${marquee == null || marquee.cycleTime == -1 }">
						<span class="tc"><input type="radio" name="count"
							value="-1" checked="checked" />无限次循环</span>
						<span class="tc"><input type="radio" name="count" value="1" />自定义次数</span>
						<span><input type="text" id="marqueeCount"
							onkeydown="javascript:onlyNum();" disabled="disabled" size="2" />必须大于1次</span>
					</c:if>
					<c:if test="${marquee.cycleTime > 0 }">
						<span class="tc"><input type="radio" name="count"
							value="-1" />无限次循环</span>
						<span class="tc"><input type="radio" name="count" value="1"
							checked="checked" />自定义次数</span>
						<span><input type="text" id="marqueeCount"
							onkeydown="javascript:onlyNum();" size="2"
							value="${marquee.cycleTime }" maxlength="4"/>必须大于1次</span>
					</c:if>
				</p>
				<p>
					<span class="te">跑马灯单次时间</span>
					<c:if test="${marquee == null || marquee.singleCostTime == 5000 }">
						<span class="tc"><input type="radio" name="time"
							value="5000" checked="checked" />5秒</span>
						<span class="tc"><input type="radio" name="time"
							value="10000" />10秒</span>
						<span class="tc"><input type="radio" name="time"
							value="20000" />20秒</span>
					</c:if>
					<c:if test="${marquee.singleCostTime == 10000 }">
						<span class="tc"><input type="radio" name="time"
							value="5000" />5秒</span>
						<span class="tc"><input type="radio" name="time"
							value="10000" checked="checked" />10秒</span>
						<span class="tc"><input type="radio" name="time"
							value="20000" />20秒</span>
					</c:if>
					<c:if test="${marquee.singleCostTime == 20000 }">
						<span class="tc"><input type="radio" name="time"
							value="5000" />5秒</span>
						<span class="tc"><input type="radio" name="time"
							value="10000" />10秒</span>
						<span class="tc"><input type="radio" name="time"
							value="20000" checked="checked" />20秒</span>
					</c:if>
				</p>
			</div>
			<div class="heading">
				<span style="font-size: 14px;">内容编辑</span>
			</div>
			<span class="line"></span>
			<div class="select-option clear">
				<p>
					<span class="te">跑马灯的内容</span> <span class="tc"><input
						type="text" id="content" placeholder="请输入跑马灯内容" size="30"
						onkeyup="javascript:look();" value="${marquee.content }"
						maxlength="32" /></span> <span style="color: red">&nbsp;&nbsp;*
						最多32个字</span>
				</p>
				<p>
					<span class="te">选择字体大小</span> <span class="tc"> <select
						id="fontSize">
							<option value="26" selected="selected">大</option>
							<option value="22">中</option>
							<option value="18">小</option>
					</select>
					</span>
				</p>
				<p>
					<span class="te">选择文字颜色</span>
				<div class="tot">
					<div class="selcol" style="width: 60px; height: 60px">
						<ul class="colfont" style="width: 100%; height: 100%">
							<li class="sebg1" data-col="ff5f71"
								style="background-color: #ff5f71"></li>
							<li class="sebg1" data-col="c96266"
								style="background-color: #c96266"></li>
							<li class="sebg1" data-col="cb0066"
								style="background-color: #cb0066"></li>
							<li class="sebg1" data-col="2ce3b9"
								style="background-color: #2ce3b9"></li>
							<li class="sebg1" data-col="6acd6a"
								style="background-color: #6acd6a"></li>
							<li class="sebg1" data-col="2ab3c7"
								style="background-color: #2ab3c7"></li>
							<li class="sebg1" data-col="2c87fd"
								style="background-color: #2c87fd"></li>
							<li class="sebg1" data-col="6f38dd"
								style="background-color: #6f38dd"></li>
							<li class="sebg1" data-col="320984"
								style="background-color: #320984"></li>
						</ul>
					</div>
					<!-- <div id="slide"></div> -->
					<div class="sebg" data-col="ff5f71" style="display: none"></div>
				</div>
				<div class="looks"
					style="margin-left: 500px; margin-top: -20px;
					 height: 50px;word-break: break-all;">
				</div>
				</p>
				<p align="center" style="margin-top: 50px">
					<a href="javascript:;" class="btn btn-sm btn-primary btn-save">保存</a>
					<a href="<%=rootPath%>/company/companyService"
						class="btn btn-sm btn-default">取消</a>
				</p>
			</div>
			</c:if>
			<c:if test="${cms.videoServiceProvider eq 'blvs'}">
			<div class="select-option clear">
				<p>
					<span class="te">跑马灯的内容</span> <span class="tc"><input
						type="text" id="content" placeholder="请输入跑马灯内容" size="30"
						onkeyup="javascript:look();" value="${marquee.content }"
						maxlength="32" /></span> <span style="color: red">&nbsp;&nbsp;*
						最多32个字</span>
				</p>
				<p>
					<span class="te">选择字体大小</span> <span class="tc"> <select
						id="fontSize">
							<option value="26" selected="selected">大</option>
							<option value="22">中</option>
							<option value="18">小</option>
					</select>
					</span>
				</p>
				<p>
					<span class="te">选择文字颜色</span>
				<div class="tot">
					<div class="selcol" style="width: 60px; height: 60px">
						<ul class="colfont" style="width: 100%; height: 100%">
							<li class="sebg1" data-col="ff5f71"
								style="background-color: #ff5f71"></li>
							<li class="sebg1" data-col="c96266"
								style="background-color: #c96266"></li>
							<li class="sebg1" data-col="cb0066"
								style="background-color: #cb0066"></li>
							<li class="sebg1" data-col="2ce3b9"
								style="background-color: #2ce3b9"></li>
							<li class="sebg1" data-col="6acd6a"
								style="background-color: #6acd6a"></li>
							<li class="sebg1" data-col="2ab3c7"
								style="background-color: #2ab3c7"></li>
							<li class="sebg1" data-col="2c87fd"
								style="background-color: #2c87fd"></li>
							<li class="sebg1" data-col="6f38dd"
								style="background-color: #6f38dd"></li>
							<li class="sebg1" data-col="320984"
								style="background-color: #320984"></li>
						</ul>
					</div>
					<!-- <div id="slide"></div> -->
					<div class="sebg" data-col="ff5f71" style="display: none"></div>
				</div>
				<div class="looks"
					style="margin-left: 500px; margin-top: -20px;
					 height: 50px;word-break: break-all;">
				</div>
				</p>
				<p align="center" style="margin-top: 50px">
					<a href="javascript:;" class="btn btn-sm btn-primary btn-save">保存</a>
					<a href="<%=rootPath%>/company/companyService"
						class="btn btn-sm btn-default">取消</a>
				</p>
			</div>
			</c:if>
		</div>
	</div>
	<br>
	</div>
	<input type="hidden" value="${cms.videoServiceProvider }" id="vsp"/>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<script type="text/javascript">
		$(function() {
			$(".pmd").addClass("active");
		})
	</script>
</body>
</html>