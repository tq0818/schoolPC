<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<div class="u-wrap student">
	<div class="mainbackground" style="height: 110px;">
		<div class="main-content nospace" id="topMessage">
			<div class="m-b-m">
				<div class="m-b-p clear">
					<div class="m-b-p-left">
						<p class="h c">
							<span class="p-title">姓名</span> <span class="p-content">${student.name }</span>
						</p>
						<p class="h c">
							<span class="p-title">学费</span> <span class="p-content">${payMaster.totalAmount }</span>
						</p>
						<p class="h c">
							<span class="p-title">报名时间</span> <span class="p-content"><fmt:formatDate
									value="${payMaster.applyTime }" timeStyle="yyyy-MM-dd HH:mm:ss" /></span>
						</p>
						<p class="h c">
							<span class="p-title">手机号码</span> <span class="p-content">${student.mobile}</span>
						</p>
						<p class="h c">
							<span class="p-title">用户名</span> <span class="p-content">${student.username}</span>
						</p>
						<p class="h c">
							<span class="p-title">已缴</span> <span class="p-content">${payMaster.hasPay }</span>
						</p>
						<p class="h c">
							<span class="p-title">来源</span> <span class="p-content">${wx:dictCode2Name(payMaster.applyChannelCode)}</span>
						</p>
						<p class="h c">
							<span class="p-title">课程</span> <span class="p-content">${payMaster.classTypeName }</span>
						</p>
						<p class="h c important">
							<span class="p-title">欠缴</span> <span class="p-content">${payMaster.unPay }</span>
						</p>
					</div>
					<div class="m-b-p-right">
						<div class="btns">
							<a href="javascript:;" id="1" onclick="returnFee('${payMaster.applyChannelCode}');"
								class="m-btn">退费</a> <a href="javascript:;" id="2"
								onclick="changeClass('${payMaster.applyChannelCode}');" class="m-btn">转班</a> <a
								href="javascript:;" id="3" onclick="changeStudent('${payMaster.applyChannelCode}');"
								class="m-btn m-mb">转人</a> <a href="javascript:;" id="4"
								onclick="detail();" class="m-btn m-mb">详情</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<form action="<%=request.getContextPath()%>/student/selectEntryDetail"
	id="toForm" method="post">
	<input type="hidden" id="pmId" name="payMasterId"
		value="${payMaster.id }" />
	<input type="hidden" value="${lable }" name="lable"/>
</form>
<script>
	function selectType(index){
		$(".m-b-p-right").find("div").find("a").eq(index).addClass("m-active")
	}
	function detail(){
		//$("#payMasterId").val(id);
		$("#toForm")[0].submit();
	}
	function changeStudent(code){
		if(code == 'CHANNEL_STUDYCARD'){
			$.msg("学习卡兑换的课程不能做异动操作！");
			return false;
		} 
		//$("#payMasterId").val(id);
		var src="<%=request.getContextPath()%>/student/changeStudent";
		$("#toForm").attr("action",src)[0].submit();
	}
	function returnFee(code){
		if(code == 'CHANNEL_STUDYCARD'){
			$.msg("学习卡兑换的课程不能做异动操作！");
			return false;
		} 
		//$("#payMasterId").val(id);
		var src="<%=request.getContextPath()%>/student/transaction/returnFee";
		$("#toForm").attr("action",src)[0].submit();
	}
	function changeClass(code){
		if(code == 'CHANNEL_STUDYCARD'){
			$.msg("学习卡兑换的课程不能做异动操作！");
			return false;
		} 
		//$("#payMasterId").val(id);
		var src="<%=request.getContextPath()%>/student/transaction/changeClass";
		$("#toForm").attr("action",src)[0].submit();
	}
	function changeClassNo(){
		//$("#payMasterId").val(id);
		var src="<%=request.getContextPath()%>/student/transaction/changeClassNo";
		$("#toForm").attr("action", src)[0].submit();
	}
</script>