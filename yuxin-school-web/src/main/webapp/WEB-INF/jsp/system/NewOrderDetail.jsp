<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<input type="hidden" value="${payPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${payPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${companyId}" id="companyId"/>
<ul>
<c:forEach var="order" items="${payPage.data }">
	<li>
        <table class="table">
            <col width="30%">
            <col width="30%">
            <col width="25%">
            <tr>
                <td> 
                    <span class="a-title">订单编号：</span>
                    <span class="a-content">${order.orderNum }</span>
                </td>
                <td>
                	<c:if test="${!empty order.discountNo }">
                		<span class="a-title">手机号：</span>
	                    <span class="a-content">
	                    	${order.discountNo }
	                    </span>
                	</c:if>
                    <c:if test="${empty order.discountNo }">
                    	<span class="a-title">用户名：</span>
	                    <span class="a-content">
	                    	${order.couponCode }
	                    </span>
                    </c:if>
                </td>
                <td>
                    <span class="a-title">订单状态：</span>
                    <span class="a-content">
                    	<c:if test="${order.payStatus == 'PAY_NON' }"><span class="a-content" style="color:red;">未支付</span></c:if>
                    	<c:if test="${order.payStatus == 'PAY_SUCCESS' }"><span class="a-content">支付成功</span></c:if>
                    	<c:if test="${order.payStatus == 'PAY_FAIL' }"><span class="a-content" style="color:red;">支付失败</span></c:if>
                    	<c:if test="${order.payStatus == 'SUB_ORDER_DELTED' }"><span class="a-content" style="color:red;">已作废</span></c:if>
                    	<c:if test="${order.payStatus == 'PAY_REMIT' }"><span class="a-content" style="color:red;">汇款中</span></c:if>
                    	<c:if test="${order.payStatus == 'PAY_REMIT_CONFIRM' }"><span class="a-content">已确认汇款</span></c:if>
                    </span>
                </td>
                <td rowspan="3">
                   	<c:if test="${order.payStatus == 'PAY_REMIT' }">
                   		<a href="<%=rootPath%>/payOrder/detailOrder/${order.id }" class="btn btn-warning btn-pay-pay" data-id="${order.id }">确认收款</a>
                   	</c:if>
                </td>
            </tr>
            <tr>
                <td>
                <c:if test="${order.commdityType == 'COMMODITY_CLASS' || order.commdityType == 'COMMODITY_PACKAGE' }">
               		 <span class="a-title" style="padding-left:28px;">课程：</span>
                </c:if>
                <c:if test="${order.commdityType == 'INTEGRAL' }">
                	<span class="a-title" style="padding-left:28px;">积分：</span>
                </c:if>
                <c:if test="${order.commdityType == 'MEMBER' }">
                	<span class="a-title" style="padding-left:28px;">会员：</span>
                </c:if>
                    <span class="a-content">
                    	${order.commodityName }
                    </span>
                </td>
                <td>
                    <span class="a-title">订单金额：</span>
                    <span class="a-content">
                    	<fmt:formatNumber value="${order.payPrice }" pattern="#,##0.00#"/>元
                    </span>
                </td>
                <td>
	                <c:if test="${order.payStatus == 'PAY_SUCCESS' }">
	                    <span class="a-title">支付时间：</span>
	                    <span class="a-content">
	                    	<fmt:formatDate value="${order.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                    </span>
	                </c:if>
	                <c:if test="${order.payStatus == 'PAY_REMIT_CONFIRM' }">
	                    <span class="a-title">汇款时间：</span>
	                    <span class="a-content">
	                    	<fmt:formatDate value="${order.collectionTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                    </span>
	                </c:if>
	                <c:if test="${order.payStatus == 'PAY_REMIT' }">
	                    <span class="a-title">转账时间：</span>
	                    <span class="a-content">
	                    	<fmt:formatDate value="${order.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                    </span>
	                </c:if>
                </td>
            </tr>
            <tr>
                <td>
                	<span class="a-title">订单时间：</span>
                    <span class="a-content">
                    	<fmt:formatDate value="${order.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </span>
                </td>
                <td>
                    <span class="a-title">支付方式：</span>
                    <span class="a-content">
                    	<c:if test="${order.payType == 'PAY_TYPE_REMIT' }">银行汇款</c:if>
                    	<c:if test="${order.payType == 'PAY_TYPE_ZFB' }">支付宝</c:if>
                    	<c:if test="${order.payType == 'PAY_TYPE_GRDB' }">个人担保交易</c:if>
                    	<c:if test="${order.payType == 'PAY_TYPE_ZFB_ZZ' }">支付宝转账</c:if>
                    	<c:if test="${order.payType == 'PAY_TYPE_WX_PERSON' }">微信个人收款</c:if>
                    	<c:if test="${order.payType == 'PAY_TYPE_WX_GZH' }">微信公众号</c:if>
                    	<c:if test="${order.payType =='PAY_TYPE_SXY'}">首信易</div></c:if>
                    	<c:if test="${order.payType =='PAY_TYPE_STUDYCARD'}">学习卡兑换</div></c:if>
                    </span>
                </td>
                <td>
                	<span class="a-title">备注：</span>
                    <span class="a-content">
                    	${order.remark }
                    </span>
                </td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </li>
</c:forEach>
</ul>

<script type="text/javascript">
	$(function(){
		$(".pagination").html("");
		$(".pagination").pagination($("#rowCount").val(), {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ($("#pageNo").val() - 1),
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : $("#pageSize").val(),
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				selOrder(pageNo);
			}
		});
	});
</script>