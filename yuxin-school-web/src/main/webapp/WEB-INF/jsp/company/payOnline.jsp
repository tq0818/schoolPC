<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员购买</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css"/>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company/payFace.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"/>
<input type="hidden" value="${servicePrice.servicePrice }" id="servicePrice"/>
<input type="hidden" value="${company.memberStartDate.time }" id="startDate"/>
<input type="hidden" value="${company.memberEndDate.time }" id="endDate"/>
<input type="hidden" value="${types }" id="types"/>
<input type="hidden" value="${oldPrice.servicePrice }" id="oldPrice"/>

<div class="u-wrap admin">
    <div class="mainbackground nopadding">
    	<c:if test="${types != 1 }">
        <div class="service-contents">
            <div class="s-head">
                <h2 class="h6">服务级别 <small>
                    <c:if test="${ company.buyFlag == 0}">
                    	免费
                    </c:if>
                    <c:if test="${ company.buyFlag == 1}">有效期至 
                    	<fmt:formatDate value="${company.memberEndDate}"/>
                    </c:if>
                 </small></h2>
            </div>
            <div class="member">
                <ul class="clear">
                <c:forEach var="c" items="${cstList }">
                    <%-- <c:if test="${c.serviceLevel == company.memberLevel }">
                    <li class="n">
                    	<em class="tips s1">当前</em>
                    </c:if> --%>
                    <c:if test="${c.serviceLevel == types }">
                    <li class="f">
                        <em class="tips s2">开通</em>
                    </c:if>
                    <c:if test="${c.serviceLevel != types  }">
                    	<li>
                    </c:if>
                        <h3 class="h6">
	                    <c:if test="${!empty basics }">
                        	<c:if test="${(c.serviceLevel == 10 or c.serviceLevel == 11)}">
                        		个人标准版
                        	</c:if>
                        	<c:if test="${(c.serviceLevel == 12 or c.serviceLevel == 13)}">
                        		个人基础版
                        	</c:if>
	                    </c:if>
                        	<c:if test="${c.serviceLevel >= 20 && c.serviceLevel < 30 }">
                        		创业标准版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 30 && c.serviceLevel < 40 }">
                        		企业基础版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 40 && c.serviceLevel < 50 }">
                        		企业标准版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 50 && c.serviceLevel < 60 }">
                        		企业专业版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 60 && c.serviceLevel < 70 }">
                        		企业高级版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 70 }">
                        		企业尊享版
                        	</c:if>
                        </h3>
                        <p class="c">
                            <span class="c-title">直播服务</span> 
                            <span class="c-content">赠送${c.giveLive }并发</span>
                        </p>
                        <p class="c">
                            <span class="c-title">视频空间</span> 
                            <span class="c-content">赠送${c.giveVideoStorage }GB</span>
                        </p>
                        <p class="c">
                            <span class="c-title">视频流量</span> 
                            <span class="c-content">赠送${c.giveVideoFlow }GB</span>
                        </p>
                        <p class="c">
                            <span class="c-title">在线人数</span> 
                            <span class="c-content">最大同时${c.faceStudentNum }人</span>
                        </p>
                        <p class="c">
                            <span class="c-title">短信服务</span> 
                            <span class="c-content">赠送${c.giveMessage }条</span>
                        </p>
                        <p class="c">
                            <span class="c-title">邮件服务</span> 
                            <span class="c-content">赠送${c.giveEmail }封</span>
                        </p>
                    </li>
                </c:forEach>
                </ul>
                <c:if test="${types != 1 }"></c:if>
                <table class="table pay-infos">
                    <col width="30%">
                    <col width="70%">
                    <tr>
                        <td>
                            <span class="c-title">购买时长：</span>
                            <span class="c-content">12个月</span>
                        </td>
                        <td>
                            <span class="c-title">服务费：</span>
                            <span class="c-content">
                            	<fmt:formatNumber value="${servicePrice.servicePrice }" pattern="###,##0.00"/>元
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="c-title">最大在线人数：</span>
                            <span class="c-content">${servicePrice.faceStudentNum}</span>
                        </td>
                        <td>
                            <span class="c-title">在线人数：</span>
                            <span class="c-content">${!empty css.onlineStudent ? css.onlineStudent : 0 }</span>
                        </td>
                    </tr>
                    <!-- <tr>
                        <td>
                            <span class="c-title">原服务剩余</span>
                            <span class="c-content" id="oldDay"></span>
                        </td>
                        <td>
                            <span class="c-title">折算</span>
                            <span class="c-content">
                                <input type="radio" name="zhe" value="1" checked>延长服务
                                <input type="radio" name="zhe" value="2" >抵扣金
                            </span>
                            <span class="c-content" id="newDay"></span>
                        </td>
                    </tr> -->
                    <tr>
                        <td>
                            <span class="c-title">有效期至：</span>
                            <span class="c-content" id="newDate"></span>
                        </td>
                        <td>
                            <span class="c-title">应交费用：</span>
                            <span class="c-content">
                            	<fmt:formatNumber value="${money }" pattern="###,##0.00"/>元
                            </span><!-- 
                            <span class="c-title">实际缴费</span>
                            <span class="c-content" id="pay"></span> -->
                        </td>
                    </tr>
                </table>
                <p class="text-center">
                    <a href="javascript:;" class="btn btn-success btn-pay">去支付</a>
                </p>
            </div>
        </div>
    	</c:if>
    	<c:if test="${types == 1 }">
        <div class="service-contents">
            <div class="s-head">
                <h2 class="h6">服务级别 <small>有效期至 
                	<fmt:formatDate value="${company.memberEndDate }" pattern="yyyy-MM-dd"/>
                </small></h2>
            </div>
            <div class="member">
                <ul class="clear">
                <c:forEach var="c" items="${cstList }">
                    <c:if test="${c.serviceLevel == company.memberLevel }">
                    <li class="n">
                    	<em class="tips s1">续费</em>
                    </c:if>
                    <c:if test="${c.serviceLevel != company.memberLevel }">
                    	<li>
                    </c:if>
                        <h3 class="h6">
                        	<c:if test="${!empty basics }">
                        	<c:if test="${(c.serviceLevel == 10 or c.serviceLevel == 11)}">
                        		个人标准版
                        	</c:if>
                        	<c:if test="${(c.serviceLevel == 12 or c.serviceLevel == 13)}">
                        		个人基础版
                        	</c:if>
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 20 && c.serviceLevel < 30 }">
                        		创业标准版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 30 && c.serviceLevel < 40 }">
                        		企业基础版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 40 && c.serviceLevel < 50 }">
                        		企业标准版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 50 && c.serviceLevel < 60 }">
                        		企业专业版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 60 && c.serviceLevel < 70 }">
                        		企业高级版
                        	</c:if>
                        	<c:if test="${c.serviceLevel >= 70 }">
                        		企业尊享版
                        	</c:if>
                        </h3>
                        <p class="c">
                            <span class="c-title">直播服务</span> 
                            <span class="c-content">赠送${c.giveLive }并发</span>
                        </p>
                        <p class="c">
                            <span class="c-title">视频空间</span> 
                            <span class="c-content">赠送${c.giveVideoStorage }GB</span>
                        </p>
                        <p class="c">
                            <span class="c-title">视频流量</span> 
                            <span class="c-content">赠送${c.giveVideoFlow }GB</span>
                        </p>
                        <p class="c">
                            <span class="c-title">在线人数</span> 
                            <span class="c-content">最大同时${c.faceStudentNum }人</span>
                        </p>
                        <p class="c">
                            <span class="c-title">短信服务</span> 
                            <span class="c-content">赠送${c.giveMessage }条</span>
                        </p>
                        <p class="c">
                            <span class="c-title">邮件服务</span> 
                            <span class="c-content">赠送${c.giveEmail }封</span>
                        </p>
                    </li>
                </c:forEach>
                </ul>
                    <table class="table pay-infos">
                        <tr>
                            <td>
                                <span class="c-title">续费时长：</span>
                                <span class="c-content">12个月</span>
                            </td>
                            <td>
                                <span class="c-title">服务费：</span>
                                <span class="c-content">
                            		<fmt:formatNumber value="${oldPrice.servicePrice }" pattern="###,##0.00"/>元
                                </span>
                            </td>
                        </tr>
	                    <tr>
	                        <td>
	                            <span class="c-title">最大在线人数：</span>
	                            <span class="c-content">${oldPrice.faceStudentNum}</span>
	                        </td>
	                        <td>
	                            <span class="c-title">在线人数：</span>
	                            <span class="c-content">${!empty css.onlineStudent ? css.onlineStudent : 0 }</span>
	                        </td>
	                    </tr>
                        <tr>
                            <td>
                                <span class="c-title">有效期至：</span>
                                <span class="c-content" id="newDate"></span>
                            </td>
                            <td>
                                <span class="c-title">应交费用：</span>
                                <span class="c-content">
                            		<fmt:formatNumber value="${oldPrice.servicePrice }" pattern="###,##0.00"/>元
                                </span>
                            </td>
                        </tr>
                    </table>
                <p class="p-b text-center">
                    <a href="javascript:;" class="btn btn-success btn-pay">去支付</a>
                </p>
            </div>
        </div>
    	</c:if>
    </div>
</div>
<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
</body>
</html>