<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
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
                        <span class="c-content">${postReal }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">现金</span>
                        <span class="c-content">${cashReal }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">支票</span>
                        <span class="c-content">${checkReal }</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">转账</span>
                        <span class="c-content">${remitReal }</span>
                    </p>
                </li>
            </ul>
        </div>
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
                        <span class="m-p-pay">金额：</span>
                        <span class="m-p-pay-m">￥ ${studentFeeStage.stageFee }</span>
                        <span class="m-p-b"><a href="javascript:void(0);" class="btn btn-sm btn-default" onclick="pay(${studentFeeStage.id});">缴费</a></span>
                    </li>
			        	</c:if>
			       </c:forEach>
                </ul>
            </div>
        </div>
<script>
	 $(function(){
		$("#totalAmount").val($("#tt").html());
		
	})

</script>