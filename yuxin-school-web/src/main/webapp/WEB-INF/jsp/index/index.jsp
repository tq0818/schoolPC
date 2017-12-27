<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/decorators/import.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>在线网校</title>
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/homepage.css">
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="../../../header.jsp"/>
	<div></div>
<div class="q-box">
    <!-- 分校管理员、机构管理员角色 -->
    <shiro:hasAnyRoles name="机构管理员,分校管理员">
    <div class="gg-item mg20 companyStatistics">
        <div class="item-left" style="margin-right:0">
            <ul class="item-cont clear bg">
                <c:if test="${liveService }">
                <li class="item-list fl toService liveService" data-url="/companyMemberService/toLiveStatistics">
                    <p class="L-hover">
	                    <span class="item-back zhibo"></span>
	                    <span class="list-title">直播概况</span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">当月可用并发</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">当月已用并发</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">下月可用并发</span>
                        <span class="text-right red fr"></span>
                    </p>
                </li>
                </c:if>
                <c:if test="${videoService }">
                <li class="item-list fl toService videoService" data-url="/companyMemberService/toVideoStatistics">
                    <p class="L-hover">
	                    <span class="item-back cunchu"></span>
	                    <span class="list-title">点播概况</span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">流量详情</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">空间详情</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">空间有效期</span>
                        <span class="text-right red fr"></span>
                    </p>
                </li>
                </c:if>
                <c:if test="${serviceMsg }">
                <li class="item-list fl toService messageService" data-url="/companyMemberService/toMessageStatistics">
                    <p class="L-hover">
	                    <span class="item-back duanxin"></span>
	                    <span class="list-title">短信</span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">短信总量</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">已使用量</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">剩余量</span>
                        <span class="text-right red fr"></span>
                    </p>
                </li>
                 </c:if>
                <!-- <li class="item-list fl toService emailService" data-url="/companyMemberService/toEmailStatistics">
                    <p class="L-hover">
	                    <span class="item-back email"></span>
	                    <span class="list-title">邮件</span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">邮件总量</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                         <span class="text-left fl">已使用量</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl">剩余量</span>
                        <span class="text-right red fr"></span>
                    </p>
                </li> -->
                <c:if test="${!liveService}">
                <li class="item-list fl">
                    <p>
	                    <span class="item-back zhiboNo"></span>
	                    <span class="list-title">直播概况</span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl"></span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left textNo">您未开通此服务</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl"></span>
                        <span class="text-right red fr"></span>
                    </p>
                </li>
                </c:if>
                <c:if test="${!videoService }">
                <li class="item-list fl" data-url="/companyMemberService/toVideoStatistics">
                    <p>
	                    <span class="item-back cunchuNo"></span>
	                    <span class="list-title">点播概况</span>
	                </p>
                    <p class="list-text clear">
                        <span class="text-left fl"></span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left textNo">您未开通此服务</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl"></span>
                        <span class="text-right red fr"></span>
                    </p>
                </li>
                </c:if>
                 <c:if test="${!serviceMsg}">
                <li class="item-list fl">
                    <p>
	                    <span class="item-back zhiboNo"></span>
	                    <span class="list-title">短信</span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl"></span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left textNo">您未开通此服务</span>
                        <span class="text-right red fr"></span>
                    </p>
                    <p class="list-text clear">
                        <span class="text-left fl"></span>
                        <span class="text-right red fr"></span>
                    </p>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
    </shiro:hasAnyRoles>
    
    <!-- 直播教师角色 -->
    <shiro:hasAnyRoles name="直播老师,排课老师,助教">
    <c:if test="${liveService }">
    <div class="zhibo mg20 bg liveCourse">
        
    </div>
    </c:if>
    </shiro:hasAnyRoles>
    
    <!-- 课程顾问,客服,运营 -->
    <shiro:hasAnyRoles name="机构管理员,分校管理员,运营,课程顾问,客服">
    <div class="gg-biaoge mg20 clear stuAndOrder">

        <div class="biaoge-coment biaoge-left fl wit50">
            <div class="biaoge-cont">
                <div class="block-title">
                    <h3>学员趋势图</h3>
                    <span class="more"><a href="/query/page/student" style="color: #00b7ee; text-decoration:none;">更多</a></span>
                </div>
                <div class="e-charst" id="stu-qushi"></div>
            </div>

        </div>
        <div class="biaoge-coment biaoge-left fr wit50">
            <div class="biaoge-cont">
                <div class="block-title">
                    <h3>订单趋势图</h3>
                    <span class="more"><a href="/query/page/payMaster" style="color: #00b7ee; text-decoration:none;">更多</a></span>
                </div>
                <div class="e-charst" id="dingdan-qushi"></div>
            </div>


        </div>
    </div>
    </shiro:hasAnyRoles>
    <shiro:hasAnyRoles name="机构管理员,分校管理员,运营">
    <div class="gg-biaoge mg20 gg-biaoge-text clear costAndTransfer">
        <div class="biaoge-coment fl wit50 cost">
            <div class="biaoge-cont">
                <div class="block-title">
                    <h3>费用详情</h3>
                    <span class="more"><a href="/query/page/fee" style="color: #00b7ee; text-decoration:none;">更多</a></span>
                </div>
                <div class="biaoge-bottom">
                    <table class="table L-table">
                        <colgroup>
                            <col width="25%">
                                <col width="25%">
                                    <col width="25%">
                                        <col width="25%">

                        </colgroup>
                        <tbody>
                            <tr>
                                <th>姓名</th>
                                <th>课程</th>
                                <th>类型</th>
                                <th>金额</th>
                            </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="biaoge-coment fr wit50 transfer">
            <div class="biaoge-cont">
                <div class="block-title">
                    <h3>转账确认</h3>
                    <span class="more"><a href="<%=rootPath %>/payOrder/toOrder?payStatus=2" style="color: #00b7ee; text-decoration:none;">更多</a></span>
                </div>
                <div class="biaoge-bottom">
                    <table class="table L-table">
                        <colgroup>
                            <col width="25%">
                                <col width="25%">
                                    <col width="25%">
                                        <col width="25%">

                        </colgroup>
                        <tbody>
                            <tr>
                                <th>订单编号</th>
                                <th>课程/会员</th>
                                <th>订单金额</th>
                                <th>订单状态</th>
                            </tr>
                            
                        </tbody>
                    </table>
                    <p class="none-text">暂无未确认的转帐</p>
                </div>
            </div>
        </div>
    </div>
    </shiro:hasAnyRoles>
    
    <shiro:hasAnyRoles name="机构管理员,分校管理员,直播老师,运营">
    <c:if test="${courseComment }">
    <div class="gg-biaoge mg20 gg-biaoge-text clear pinglun comment">
        <div class="biaoge-coment">
            <div class="biaoge-cont">
                <div class="block-title">
                    <h3>课程评论</h3>
                    <span class="more"><a href="/classModule/comment" style="color: #00b7ee; text-decoration:none;">更多</a></span>
                </div>
                <div class="biaoge-bottom">
                    <table class="table">
                        <colgroup>
                            <col width="40%">
                                <col width="20%">
                                    <col width="20%">
                                        <col width="10%">
                                            <col width="10%">

                        </colgroup>
                        <tbody>
                            <tr>
                                <th>课程评论</th>
                                <th>评论时间</th>
                                <th>课程</th>
                                <th>老师</th>
                                <th>评分</th>
                            </tr>
							
                        </tbody>
                    </table>
                    <p class="none-text" >无最新课程评论</p>
                </div>
            </div>
        </div>
    </div>
    </c:if>
    <c:if test="${serviceQa }">
    <div class="gg-biaoge mg20 gg-biaoge-text clear pinglun question">
        <div class="biaoge-coment">
            <div class="biaoge-cont">
                <div class="block-title">
                    <h3>课程最新未作答问题</h3>
                    <span class="more"><a href="/Question/questionIndex" style="color: #00b7ee; text-decoration:none;">更多</a></span>
                </div>
                <div class="biaoge-bottom">
                    <table class="table">
                        <colgroup>
                            <col width="40%">
                                <col width="30%">
                                    <col width="30%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>问题标题</th>
                                <th>提问时间</th>
                                <th>学员名称</th>
                            </tr>
                            
                        </tbody>
                    </table>
                    <p class="none-text">无最新未作答问题</p>
                </div>
            </div>
        </div>
    </div>
    </c:if>
    <c:if test="${serviceTeacher }">
    <div class="gg-biaoge mg20 gg-biaoge-text clear pinglun revovery">
        <div class="biaoge-coment">
            <div class="biaoge-cont">
                <div class="block-title">
                    <h3>教师动态回复</h3>
                    <span class="more"><a href="/classModule/dynamics" style="color: #00b7ee; text-decoration:none;">更多</a></span>
                </div>
                <div class="biaoge-bottom">
                    <table class="table L-table">
                        <colgroup>
                            <col width="40%">
                                <col width="20%">
                                    <col width="20%">
                                    <col width="20%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>用户评论</th>
                                <th>动态内容</th>
                                <th>评论时间</th>
                                <th>学员名称</th>
                            </tr>
                        </tbody>
                    </table>
                    <p class="none-text">无教师动态回复</p>
                </div>
            </div>
        </div>
    </div>
    </c:if>
    </shiro:hasAnyRoles>
    
</div>
<!-- footer start -->
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts.common.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/crypto.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/home-page.js"></script>
</body>
</html>