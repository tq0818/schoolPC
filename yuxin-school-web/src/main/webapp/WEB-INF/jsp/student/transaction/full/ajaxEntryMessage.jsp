<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>   
<c:forEach items="${payMasterList }" var="payMaster">
<div class="m-b-p clear" >
        <div class="m-b-p-left" style="height:115px">
            <p class="h c">
                <span class="p-title">学科</span>
                <span class="p-content">${payMaster.itemOneName }</span>
            </p>
            <p class="h c">
                <span class="p-title">学费</span>
                <span class="p-content">${payMaster.totalAmount }</span>
            </p>
            <p class="h c">
                <span class="p-title">学科小类</span>
                <span class="p-content">${payMaster.itemSecondName }</span>
            </p>
            <p class="h c">
                <span class="p-title">已缴</span>
                <span class="p-content">${payMaster.hasPay }</span>
            </p>
            <p class="h c">
                <span class="p-title">课程</span>
                <span class="p-content">${payMaster.classTypeName }</span>
            </p>
            <c:if test="${payMaster.totalAmount-payMaster.hasPay + payMaster.usedFee>=0}">
	             <p class="h c important">
	                <span class="p-title">欠缴</span>
	                <span class="p-content">${payMaster.unPay }</span>
	            </p>
            </c:if>
           
            <p class="h c">
                <span class="p-title">报名时间</span>
                <span class="p-content"><fmt:formatDate value="${payMaster.applyTime }" timeStyle="yyyy-MM-dd hh:mm:ss"/></span>
            </p>
         
            <p class="h c">
                <span class="p-title">来源</span>
                <span class="p-content">${wx:dictCode2Name(payMaster.applyChannelCode)}</span>
            </p>
              <p class="h c">
                <span class="p-title">状态</span>
                <span class="p-content">${wx:dictCode2Name(payMaster.payStatusCode)}</span>
            </p>
        </div>
       
        <div class="m-b-p-right
         <c:if test="${payMaster.payStatusCode!='ORDER_PAID'&&payMaster.payStatusCode!='ORDER_PART_PAY'}">
         hidden
         </c:if>
        ">
            <div class="btns">
                <a href="javascript:void(0);" onclick="returnFee(${payMaster.id},'${payMaster.applyChannelCode}')" class="m-btn">退费</a>
                <a href="javascript:void(0);" onclick="changeClass(${payMaster.id},'${payMaster.applyChannelCode}')" class="m-btn">转班</a>
                <a href="javascript:void(0);" onclick="changeStudent(${payMaster.id},'${payMaster.applyChannelCode}')" class="m-btn m-mb">转人</a>
                <a href="javascript:void(0);" onclick="detail(${payMaster.id})" class="m-btn m-mb">详情</a>
                <a href="javascript:void(0);" onclick="changeClassNo(${payMaster.id},'${payMaster.applyChannelCode}')" class="m-btn m-mb change">更换班号</a>
            </div>
        </div>
        
    </div>
    <form action="<%=request.getContextPath() %>/student/full/selectEntryDetail" id="toDetailForm" method="post">
    <input type="hidden" id="payMasterId" name="payMasterId"/>
     <input type="hidden" value="${lable }" name="lable"/>
    </form>
</c:forEach>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/full/transaction/ajaxEntryMessage.js"></script>