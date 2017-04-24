<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<link type="text/css" href="<%=request.getContextPath()%>/stylesheets/student.css"/>
<div class="main-content">
            <div class="m-title">
                <h2 class="h6">应缴费用</h2>
            </div>
            <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">培训费用</span>
                        <span class="c-content">${studentPayMaster.trainingFee }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">代报考费用</span>
                        <span class="c-content">${studentPayMaster.examAgentFee }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">订单总价</span>
                        <span class="c-content" id="tt">${studentPayMaster.trainingFee+studentPayMaster.examAgentFee }</span>
                    </p>
                </li>
            </ul>
        </div>
      
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">已缴费用</h2>
            </div>
            <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">POS</span>
                        <span class="c-content" id="posReal">${postReal }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">现金</span>
                        <span class="c-content" id="cashReal">${cashReal }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">支票</span>
                        <span class="c-content" id="checkReal">${checkReal }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">转账</span>
                        <span class="c-content" id="remitReal">${remitReal }</span>
                    </p>
                </li>
            </ul>
        </div>
        <c:if test="${fn:length(studentFeeStageList)>0}">
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">欠费</h2>
            </div>
            <div class="m-p-list">
                <ul class="clear">
               	  <c:forEach items="${studentFeeStageList }" var="studentFeeStage" varStatus="v">
			        	<c:if test="${studentFeeStage.stageStatus==0}">
			        	<li>
                        <span class="m-p-date">第${count+v.index}期</span>
                        <span class="m-p-time">缴款日期：</span>
                        <span class="m-p-time-n"><fmt:formatDate value="${studentFeeStage.stageDate }" timeStyle="yyyy-MM-dd"/></span>
                       <%--  <span class="c-title">金额：</span> <span class="c-content">￥ ${studentFeeStage.stageFee }</span> --%>
                        <span class="m-p-pay">金额：</span>
                        <span class="m-p-pay-m">￥ ${studentFeeStage.stageFee }</span>
                    </li>
			        	</c:if>
			       </c:forEach>
                </ul>
            </div>
        </div>
        </c:if>
        <c:if test="${countreFee>0}">
	         <div class="main-content">
	            <div class="m-title">
	                <h2 class="h6">退费</h2>
	            </div>
	             <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">POS</span>
                        <span class="c-content" id="posReal">${repostReal }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">现金</span>
                        <span class="c-content" id="cashReal">${recashReal }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">支票</span>
                        <span class="c-content" id="checkReal">${recheckReal }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">转账</span>
                        <span class="c-content" id="remitReal">${reremitReal }</span>
                    </p>
                </li>
            </ul>
	        </div>
        </c:if>
        <c:if test="${studentPayMaster.usedFee>0}">
        	<div class="main-content">
	            <div class="m-title">
	                <h2 class="h6">原订单已使用</h2>
	            </div>
	             <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-content" id="posReal">${studentPayMaster.usedFee}</span>
                    </p>
                </li>
            </ul>
	        </div>
        </c:if>
        
<script>
$(function(){
	/* var aaa=$.formatCurrency(1000000);
	alert(aaa); */
})
function setValue(){
	$("#pos").html($("#posReal").html())
	$("#cash").html($("#cashReal").html())
	$("#check").html($("#checkReal").html())
	$("#remit").html($("#remitReal").html())
	$("#canReturn").html("可退:"+($("#posReal").html()*1+$("#cashReal").html()*1+$("#checkReal").html()*1+$("#remitReal").html()*1-$("#hasUsed").val()*1));
}
</script>