<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp"%>
<title>题库设置</title>
<style type="text/css">
.register {
	position: fixed;
	left: 50%;
	top: 50%;
	width: 400px;
	height: 400px;
	margin-left: -200px;
	margin-top: -200px;
	padding-bottom: 15px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
	z-index: 999
}

.none {
	display: none;
}

.register .reg-close {
	position: absolute;
	top: 12px;
	right: 12px;
	width: 12px;
	height: 12px;
	background-image: url('../images/index-icons.png');
	background-repeat: no-repeat;
	background-position: 0 0;
	cursor: pointer;
}

.register .reg-title {
	padding: 15px 30px;
	border-bottom: 1px solid #e5e5e5;
}

.register .reg-form {
	padding: 0 60px;
}

.register .reg-bottom {
	padding: 2px 52px;
	border-top: 1px solid #e5e5e5;
}

.mark-bg {
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background-color: #fff;
	background-color: rgba(255, 255, 255, 0.6);
	opacity: .6 \9;
	filter: alpha(opacity = 60);
}
.ml58{
	margin-left: 58px;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/classes.css">
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/common/message.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/tiku/tikuSett.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_tiku.jsp"%>
	<div class="u-wrap classes">
		<div class="mainbackground nopadding">
			<div class="heading">
				<h2 class="h5">题库设置</h2>
				<span class="line"></span>
			</div>
			<div class="c-main" style="width:95%;">
				<div class="s-title">
					<h2 class="h6">免费学员做题权限设置</h2>
				</div>
				<div class="s-content ml58">
					<c:if test="${set.freeStuNo == '1'}">
						<p class="c">
							<span class=""><input type="radio" name="freeStu"
								class="freeStu" value="free_stu_no" checked="checked" /></span> <span
								class="">免费用户不可以使用题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="freeStu"
								class="freeStu" value="free_stu_try" /></span> <span class="">免费用户可以免费体验题库，但只能体验
								<input type="text" id="freeDay" style="width: 33px;" maxlength="3"
								onkeyup="value=this.value.replace(/\D+/g,'')" />天 <a
								href="javascript:;" class="btn btn-sm btn-success saveFree">保存</a>
							</span>
							<span style="color: red;">&nbsp;&nbsp;&nbsp;注：填“0”代表免费学员做题不受限制</span>
						</p>
					</c:if>
					<c:if test="${set.freeStuTry == '1'}">
						<p class="c">
							<span class=""><input type="radio" name="freeStu"
								class="freeStu" value="free_stu_no" /></span> <span class="">免费用户不可以使用题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="freeStu"
								class="freeStu" value="free_stu_try" checked="checked" /></span> <span
								class="">免费用户可以免费体验题库，但只能体验 <input type="text" style="width: 33px;"
								id="freeDay" value="${set.freeStuTryDay}" maxlength="3"
								onkeyup="value=this.value.replace(/\D+/g,'')" />天 <a
								href="javascript:;" class="btn btn-sm btn-success saveFree">保存</a>
							</span>
							<span style="color: red;">&nbsp;&nbsp;&nbsp;注：填“0”代表免费学员做题不受限制</span>
						</p>
					</c:if>
					<c:if test="${set.freeStuTry != '1' && set.freeStuNo != '1'}">
						<p class="c">
							<span class=""><input type="radio" name="freeStu"
								class="freeStu" value="free_stu_no" /></span> <span class="">只有报名了课程的学员才可以使用题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="freeStu"
								class="freeStu" value="free_stu_try" /></span> <span class="">免费用户可以免费体验题库，但只能体验
								<input type="text" id="freeDay"
								onkeyup="value=this.value.replace(/\D+/g,'')" />天 <a
								href="javascript:;" class="btn btn-sm btn-success saveFree">保存</a>
							</span>
						</p>
					</c:if>
				</div>

				<div class="s-title">
					<h2 class="h6">收费学员做题权限设置</h2>
				</div>
				<div class="s-content ml58">
					<c:if test="${set.chargeStuAll == '1'}">
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_all" checked="checked" /></span> <span
								class="">购买了课程的学员，可以做任何科目下的题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_item" /></span> <span class="">购买了课程的学员，只可以做自己所购买的课程所属学科下的题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_item_second" /></span> <span
								class="">购买了课程的学员，只可以做自己所购买的课程所属学科小类下的题库</span>
						</p>
					</c:if>
					<c:if test="${set.chargeStuItem == '1'}">
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_all" /></span> <span class="">购买了课程的学员，可以做任何科目的下的题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_item" checked="checked" /></span> <span
								class="">购买了课程的学员，只可以做自己所购买的课程所属的学科下的题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_item_second" /></span> <span
								class="">购买了课程的学员，只可以做自己所购买的课程所属的学科小类下的题库</span>
						</p>
					</c:if>
					<c:if test="${set.chargeStuItemSecond == '1'}">
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_all" /></span> <span class="">购买了课程的学员，可以做任何科目的下的题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_item" /></span> <span class="">购买了课程的学员，只可以做自己所购买的课程所属的学科下的题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_item_second"
								checked="checked" /></span> <span class="">购买了课程的学员，只可以做自己所购买的课程所属的学科小类下的题库</span>
						</p>
					</c:if>
					<c:if
						test="${set.chargeStuItemSecond != '1' && set.chargeStuItem != '1' && set.chargeStuAll != '1'}">
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_all" /></span> <span class="">购买了课程的学员，可以做任何科目的下的题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_item" /></span> <span class="">购买了课程的学员，只可以做自己所购买的课程所属的学科下的题库</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="chargeStu"
								class="chargeStu" value="charge_stu_item_second" /></span> <span
								class="">购买了课程的学员，只可以做自己所购买的课程所属的学科小类下的题库</span>
						</p>
					</c:if>
				</div>

				<div class="s-title">
					<h2 class="h6">试题审核设置</h2>
				</div>
				<div class="s-content ml58">
					<c:if test="${set.topicAuditYes == '1'}">
						<p class="c">
							<span class=""><input type="radio" name="topicAudit"
								class="topicAudit" value="topic_audit_yes" checked="checked" /></span>
							<span class="">每道题目必须经过审核，并且审核通过后才能对外开放</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="topicAudit"
								class="topicAudit" value="topic_audit_no" /></span> <span class="">题目创建完毕，即可对外开放，不用经过审核</span>
						</p>
					</c:if>
					<c:if test="${set.topicAuditNo == '1'}">
						<p class="c">
							<span class=""><input type="radio" name="topicAudit"
								class="topicAudit" value="topic_audit_yes" /></span> <span class="">每道题目必须经过审核，并且审核通过后才能对外开放</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="topicAudit"
								class="topicAudit" value="topic_audit_no" checked="checked" /></span> <span
								class="">题目创建完毕，即可对外开放，不用经过审核</span>
						</p>
					</c:if>
					<c:if test="${set.topicAuditNo != '1' && set.topicAuditYes != '1'}">
						<p class="c">
							<span class=""><input type="radio" name="topicAudit"
								class="topicAudit" value="topic_audit_yes" /></span> <span class="">每道题目必须经过审核，并且审核通过后才能对外开放</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="topicAudit"
								class="topicAudit" value="topic_audit_no" /></span> <span class="">题目创建完毕，即可对外开放，不用经过审核</span>
						</p>
					</c:if>
				</div>

				<div class="s-title">
					<h2 class="h6">试卷审核设置</h2>
				</div>
				<div class="s-content ml58">
					<c:if test="${set.paperAuditYes == '1'}">
						<p class="c">
							<span class=""><input type="radio" name="paperAudit"
								class="paperAudit" value="paper_audit_yes" checked="checked" /></span>
							<span class="">试卷必须经过审核，并且审核通过后才能对外开放，审核试卷就是对试卷中的每道题进行审核</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="paperAudit"
								class="paperAudit" value="paper_audit_no" /></span> <span class="">试卷创建完毕，即可对外开放，不用经过审核</span>
						</p>
					</c:if>
					<c:if test="${set.paperAuditNo == '1'}">
						<p class="c">
							<span class=""><input type="radio" name="paperAudit"
								class="paperAudit" value="paper_audit_yes" /></span> <span class="">试卷必须经过审核，并且审核通过后才能对外开放，审核试卷就是对试卷中的每道题进行审核</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="paperAudit"
								class="paperAudit" value="paper_audit_no" checked="checked" /></span> <span
								class="">试卷创建完毕，即可对外开放，不用经过审核</span>
						</p>
					</c:if>
					<c:if test="${set.paperAuditNo != '1' && set.paperAuditYes != '1'}">
						<p class="c">
							<span class=""><input type="radio" name="paperAudit"
								class="paperAudit" value="paper_audit_yes" /></span> <span class="">试卷必须经过审核，并且审核通过后才能对外开放，审核试卷就是对试卷中的每道题进行审核</span>
						</p>
						<p class="c">
							<span class=""><input type="radio" name="paperAudit"
								class="paperAudit" value="paper_audit_no" /></span> <span class="">试卷创建完毕，即可对外开放，不用经过审核</span>
						</p>
					</c:if>
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
		<div>
			<input type="hidden" id="tikuId" value="${tikuId}" /> <input
				type="hidden" id="setId" value="${setId}" />
		</div>
		<script type="text/javascript">
			$(function() {
				if($(".tiHeader .navspace li").length == 1){
					$(".tiHeader .navspace li>a:eq(0)").addClass("active");
				}else{
					$(".tiHeader .navspace li>a:eq(3)").addClass("active");
				}
				$("body").find(".set").addClass("active");
			});
		</script>
</body>
</html>