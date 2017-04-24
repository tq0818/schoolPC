<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>获取积分</title>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/integral/points.css"/>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <%@include file="/WEB-INF/jsp/menu/menu_integral.jsp" %>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">获取积分</span>
                </div>
            </div>
            <div class="point-get">
                <div class="set-system point-get-content">
                    <div class="point-get-itm">
                        <div>
                            <i>购买积分</i>
                            <c:choose>
                            	<c:when test="${cic.buyIntegralSwitch == 1}">
                            		<span class="l-btn-status  open buyIntegralSwitch"><i class="iconfont">&#xe603</i> <em>已开启</em></span>
                            	</c:when>
                            	<c:otherwise>
                            		<span class="l-btn-status  close buyIntegralSwitch"><i class="iconfont">&#xe604</i> <em>已禁用</em></span>
                            	</c:otherwise>
                            </c:choose>
                        </div>
                        <p>说明：学员可通过积分充值的方式购买积分。</p>
                        <div>
                            <i>用户行为获取积分</i>
                            <c:choose>
                            	<c:when test="${cic.getIntegralSwitch == 1}">
                            		<span class="l-btn-status  open getIntegralSwitch"><i class="iconfont">&#xe603</i> <em>已开启</em></span>
                            	</c:when>
                            	<c:otherwise>
                            		<span class="l-btn-status  close getIntegralSwitch"><i class="iconfont">&#xe604</i> <em>已禁用</em></span>
                            	</c:otherwise>
                            </c:choose>
                            <button class="renew btn-sm btn-primary" <c:if test="${cic.getIntegralSwitch == 0}">style="display:none;"</c:if>>恢复默认设置</button>
                        </div>
                        <p>说明：用户可设定积分获取规则，学员根据积分规则，进行某些特定行为获取积分。</p>
                    </div>
                    <div class="check-wrap" <c:if test="${cic.getIntegralSwitch != 1}">style="display:none;"</c:if>>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" bordercolor="#f2f1f1" data-id="${cic.id}">
                            <colgroup>
                                <col width="35%">
                                <col width="15%">
                                <col width="40%">
                                <col width="10%">
                            </colgroup>
                            <tr>
                                <td>
                                    <input data-type="registFlag" type="checkbox" name="give-point" <c:if test="${cic.registFlag == 1}">checked="checked"</c:if>/>
                                    <em>注册赠送积分</em>
                                </td>
                                <td>获取积分：</td>
                                <td>
                                    <em><c:if test="${!empty cic.registNum}">${cic.registNum }</c:if><c:if test="${empty cic.registNum}">10</c:if></em>分
                                </td>
                                <td><button class="btn-sm btn-primary">编辑</button></td>
                            </tr>
                            <tr>
                                <td>
                                    <input data-type="loginFlag" type="checkbox" name="give-point" <c:if test="${cic.loginFlag == 1}">checked="checked"</c:if>/>
                                    <em>登录赠送积分（一天只计算一次）</em>
                                </td>
                                <td>获取积分：</td>
                                <td>
                                    <em><c:if test="${!empty cic.loginNum}">${cic.loginNum }</c:if><c:if test="${empty cic.loginNum}">10</c:if></em>分
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>
                                    <em style="padding-left: 20px;">连续登录赠送积分</em>
                                </td>
                                <td>获取积分：</td>
                                <td>
                                    <em><c:if test="${!empty cic.continueLoginNum}">${cic.continueLoginNum }</c:if><c:if test="${empty cic.continueLoginNum}">10</c:if></em>分
                                </td>
                                <td><button class="btn-sm btn-primary">编辑</button></td>
                            </tr>
                            <tr>
                                <td>
                                    <input data-type="consumeFlag" type="checkbox" name="give-point" <c:if test="${cic.consumeFlag == 1}">checked="checked"</c:if>/>
                                    <em>单次消费满送积分</em>
                                </td>
                                <td>获取积分：</td>
                                <td>
                                    <em><c:if test="${!empty cic.consumeNum}">${cic.consumeNum }</c:if><c:if test="${empty cic.consumeNum}">10</c:if></em>分
                                    <span>（单次消费满<em><c:if test="${!empty cic.costMoreThen}">${cic.costMoreThen }</c:if><c:if test="${empty cic.costMoreThen}">10</c:if></em>元）</span>
                                </td>
                                <td><button class="btn-sm btn-primary">编辑</button></td>
                            </tr>
                            <tr style="display:none;">
                                <td>
                                    <input data-type="inviteFlag" type="checkbox" name="give-point" <c:if test="${cic.inviteFlag == 1}">checked="checked"</c:if>/>
                                    <em>邀请码赠送积分</em>
                                </td>
                                <td>获取积分：</td>
                                <td>
                                    <em><c:if test="${!empty cic.inviteNum}">${cic.inviteNum }</c:if><c:if test="${empty cic.inviteNum}">10</c:if></em>分
                                </td>
                                <td><button class="btn-sm btn-primary">编辑</button></td>
                            </tr>
                            <tr>
                                <td>
                                    <input data-type="commentsFlag" type="checkbox" name="give-point" <c:if test="${cic.commentsFlag == 1}">checked="checked"</c:if>/>
                                    <em>课程评论</em>
                                </td>
                                <td>获取积分：</td>
                                <td>
                                    <em><c:if test="${!empty cic.commentsNum}">${cic.commentsNum }</c:if><c:if test="${empty cic.commentsNum}">10</c:if></em>分
                                </td>
                                <td><button class="btn-sm btn-primary">编辑</button></td>
                            </tr>
                            <tr>
                                <td>
                                    <input data-type="questionFlag" type="checkbox" name="give-point" <c:if test="${cic.questionFlag == 1}">checked="checked"</c:if>/>
                                    <em>提问赠送积分</em>
                                </td>
                                <td>获取积分：</td>
                                <td>
                                    <em><c:if test="${!empty cic.questionNum}">${cic.questionNum }</c:if><c:if test="${empty cic.questionNum}">5</c:if></em>分
                                    <span>每日最多&nbsp;&nbsp;&nbsp;&nbsp;<em><c:if test="${!empty cic.quesMaxNum}">${cic.quesMaxNum }</c:if><c:if test="${empty cic.quesMaxNum}">5</c:if></em>&nbsp;&nbsp;&nbsp;&nbsp;分</span>
                                </td>
                                <td><button class="btn-sm btn-primary">编辑</button></td>
                            </tr>
                            <tr>
                                <td>
                                    <input data-type="answerFlag" type="checkbox" name="give-point" <c:if test="${cic.answerFlag == 1}">checked="checked"</c:if>/>
                                    <em>回答问题赠送积分</em>
                                </td>
                                <td>获取积分：</td>
                                <td>
                                    <em><c:if test="${!empty cic.answerNum}">${cic.answerNum }</c:if><c:if test="${empty cic.answerNum}">5</c:if></em>分
                                    <span>每日最多&nbsp;&nbsp;&nbsp;&nbsp;<em><c:if test="${!empty cic.anseMaxNum}">${cic.anseMaxNum }</c:if><c:if test="${empty cic.anseMaxNum}">5</c:if></em>&nbsp;&nbsp;&nbsp;&nbsp;分</span>
                                </td>
                                <td><button class="btn-sm btn-primary">编辑</button></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="mask-bg none"></div>

<script type="text/javascript">
$(function(){
	$selectSubMenu('org_service');
	$chooseMenu('getIntegral');
});
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/integral/integralGet.js"></script>
</body>
</html>