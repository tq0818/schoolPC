<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开通服务</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css?v=1.0"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/minitip.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/utils.css"/>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company-service.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company/companyService.js"></script>

<style>
	.company .service-list ul li p.btns{
            margin-top: 34px;
            position: absolute;
            bottom: 15px;
            width: 88%;
        }
        .company .service-list ul li p.btns .right-btn{
            float: right;
        }
      .left  .add_text{
   		 color:red;
   		 font-size:12px;
      }
</style>
</head>
<body class="qBox">
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
 	<div class="u-wrap company q-wrap">
       	<input type="hidden" value="${caId }" id="caId"/>
        <div class="heading" style="width:150px;">
            <h2 class="h5" id="hint">已开通服务</h2>
        </div>
    </div>
    <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"/>
	<div class="u-wrap company">
		<div class="mainbackground u-content clear q-mainbackground">
			<div class="service-list open-service">
				<div class="q-ucont">
					<div class="inter-set q-text">
						<p><i class="iconfont">&#xe6c9;</i>网校功能管理</p>
					</div>
					<ul class="clear">
						<c:if test="${requestScope.SERVICE_ONLINE_SET == null }">
							<li class="clear">
								<div class="left" style="width: 200px">
									<div>
										<i class="icons i2 active" style="cursor: pointer;"
										   onclick="forwardnetSchool();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardnetSchool();">网校设置<i
													class="icon-red"></i> </a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">网校模板配置</p>
									<p class="infos">网校页面优化</p>
									<p class="infos">网校推广管理</p>
								</div>
								<shiro:hasPermission name="netschool_head">
									<p class="btns isok">
										<c:choose>
											<c:when test="${company.memberLevel == 12 or company.memberLevel == 13 }">
												<c:if test="${empty sessionScope.SYSTEM_HEAD or sessionScope.SYSTEM_HEAD == 1}">
													<a href="<%=rootPath %>/sysPageHeadFoot/showHead"
													   class="btn btn-sm btn-default">页头导航</a>
												</c:if>
												<c:if test="${empty sessionScope.SYSTEM_FOOT or sessionScope.SYSTEM_FOOT == 1}">
													<a href="<%=rootPath %>/companyHeadFootConfig/showFootTemplete"
													   class="btn btn-sm btn-default">页尾导航</a>
												</c:if>
												<c:if test="${empty sessionScope.SYSTEM_INDEX or sessionScope.SYSTEM_INDEX == 1}">
													<a href="<%=rootPath %>/sysBody/show" class="btn btn-sm btn-default">首页模板</a>
												</c:if>
												<%--<c:if test="${empty sessionScope.SYSTEM_SEO or sessionScope.SYSTEM_SEO == 1}">
													<a href="<%=rootPath %>/sysConfigIco/showIco" class="btn btn-sm btn-default">SEO设置</a>
												</c:if>--%>
											</c:when>
											<c:otherwise>
												<a href="<%=rootPath %>/sysPageHeadFoot/showHead"
												   class="btn btn-sm btn-default">页头导航</a>
												<a href="<%=rootPath %>/companyHeadFootConfig/showFootTemplete"
												   class="btn btn-sm btn-default">页尾导航</a>
												<a href="<%=rootPath %>/sysBody/show"
												   class="btn btn-sm btn-default">首页模板</a>
												<%--<a href="<%=rootPath %>/sysConfigIco/showIco" class="btn btn-sm btn-default">SEO设置</a>--%>
											</c:otherwise>
										</c:choose>
										<a href="<%=rootPath %>/sysPageHeadFoot/showHead"
										   class="btn btn-sm btn-default right-btn more">更多</a>
									</p>
								</shiro:hasPermission>
							</li>
						</c:if>
						<%-- c:if test="${requestScope.SERVICE_BRANCH_SCHOOL_SET == null }">
						<c:choose>
							<c:when test="${(company.memberLevel == 12 or company.memberLevel == 13) and !empty sessionScope.SERVICE_SCHOOL }">
								  <c:if test="${sessionScope.SERVICE_SCHOOL == 1 }">
									<li class="clear">
										<div class="left" style="width: 200px;">
											<div>
												<i class="icons i14 active" style="cursor: pointer;" onclick="forwardSchool();"></i>
												<p class="s-name">
													<a href="javascript:void(0);" onclick="forwardSchool();" style="text-decoration: none;">分校管理</a>
												</p>
											</div>
										</div>
										<div class="right server-infos">
											<p class="infos">
												<em style="font-size:14px; width: 85px;margin-top: 3px;">${css.schoolNum }</em>分校数量
											</p>
										</div>
										<shiro:hasPermission name="school_head">
											<p class="btns isok" style="height:28px">
												<a href="javascript:;" class="btn btn-sm btn-default" data-url="companyMemberService/openSchool">分校设置</a>
											</p>
										</shiro:hasPermission>
									  </li>
								  </c:if>
							</c:when>
							<c:otherwise>
								<li class="clear">
									<div class="left" style="width: 200px;">
										<div>
											<i class="icons i14 active" style="cursor: pointer;" onclick="forwardSchool();"></i>
											<p class="s-name">
												<a href="javascript:void(0);" onclick="forwardSchool();" style="text-decoration: none;">分校管理</a>
											</p>
										</div>
									</div>
									<div class="right server-infos">
										<p class="infos">
											<em style="font-size:14px; width: 85px;margin-top: 3px;">${css.schoolNum }</em>分校数量
										</p>
									</div>
									<shiro:hasPermission name="school_head">
										<p class="btns isok" style="height:28px">
											<a href="javascript:;" class="btn btn-sm btn-default" data-url="companyMemberService/openSchool">分校设置</a>
										</p>
									</shiro:hasPermission>
									</li>
							</c:otherwise>
						</c:choose>
						</c:if> --%>
						<c:if test="${requestScope.SERVICE_MICROSCHOOL == null }">
							<li class="clear">
								<div class="left" style="width: 200px">
									<div>
										<i class="icons i22 active" style="cursor: pointer;"
										   onclick="forwardMicroSchool();"></i>
										<p class="s-name">
											<a href="##" onclick="forwardMicroSchool();">WAP管理 </a>
											<c:if test="${!empty manganger }">
												<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务"
												   data-type="SERVICE_MICROSCHOOL">&#xe635;</i>
											</c:if>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<%--<p class="infos">导航配置</p>
									<p class="infos">页尾设置</p>--%>
									<p class="infos">轮播图设置</p>
								</div>
								<p class="btns isok">
									<%--<a href="<%=rootPath %>/microSchool/gotoNavigationConfig"
									   class="btn btn-sm btn-default micro-set">导航配置</a>
									<a href="<%=rootPath %>/microSchool/gotoFooterConfig"
									   class="btn btn-sm btn-default micro-set">页尾设置</a>--%>
									<a href="<%=rootPath %>/microSchool/gotoCarouselFigureConfig"
									   class="btn btn-sm btn-default micro-set">轮播图设置</a>
								</p>
							</li>
						</c:if>
					</ul>
				</div>
				<div class="q-ucont">
					<div class="inter-set q-text">
						<p><i class="iconfont">&#xe6c7;</i>教学功能管理</p>
					</div>
					<ul class="clear">
						<c:if test="${requestScope.SERVICE_LIVE == null }">
							<li class="clear">
								<div class="left" style="width: 200px">
									<div>
										<i class="icons i3 active" style="cursor: pointer;" onclick="forwardLive();"></i>
										<p class="s-name">
											<a href="##" onclick="forwardLive();"> 直播概况 </a>
											<c:if test="${!empty manganger }">
												<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务"
												   data-type="SERVICE_LIVE">&#xe635;</i>
											</c:if>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">
										<em style="font-size:14px; width: 85px;margin-top: 3px;margin-top: 3px;">
												${empty clc ? 0 : clc.concurrentMax }
										</em>当月可用并发<i class="iconfont ask" title="当月可用并发">&#xe60f;</i>
									</p>
									<p class="infos">
										<em style="font-size:14px; width: 85px;margin-top: 3px;">
												<%-- <c:if test="${!empty company.serviceVersion &&
												 company.serviceVersion == 'ONLINE_COUNT'}">
													${css.onlineStudent }
												</c:if>
												<c:if test="${empty company.serviceVersion ||
												 company.serviceVersion == 'USER_COUNT'}"> --%>
												${css.liveConcurrent }
												<%-- </c:if> --%>
										</em>当月已用并发<i class="iconfont ask" title="当月已用并发">&#xe60f;</i>
									</p>
									<p class="infos">
										<em style="font-size:14px; width: 85px;margin-top: 3px;">${empty clcn ? 0 : clcn.concurrentMax }</em>下月可用并发<i
											class="iconfont ask" title="下月可用并发">&#xe60f;</i>
									</p>
								</div>
							</li>
						</c:if>
						<c:if test="${requestScope.SERVICE_VIDEO == null }">
							<li class="clear">
								<div>
									<div class="left" style="width: 200px;">
											<%-- <span class="new"></span> --%>
										<div>

											<i class="icons i4 active" style="cursor: pointer;"
											   onclick="forwardVideo();"></i>
											<p class="s-name">
												<a href="javascript:void(0);" onclick="forwardVideo();">点播概况</a>
												<c:if test="${!empty manganger }">
													<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务"
													   data-type="SERVICE_VIDEO">&#xe635;</i>
												</c:if>
												<span class="q-tip">(录播+文件)</span>
											</p>
										</div>
									</div>
									<div class="right server-infos">
										<p class="infos">
											流量<em style="font-size:14px; width: 85px;margin-top: 4px;">
											<c:if test="${cms.videoFlow == null}">${cms.giveVideoFlow }</c:if>
											<c:if test="${cms.videoFlow != null}">${cms.videoFlow+cms.giveVideoFlow }</c:if>
										</em>GB<i
												class="iconfont ask" title="流量">&#xe60f;</i>
										</p>
										<p class="infos">
											空间<em style="font-size:14px; width: 85px;margin-top: 3px;">
											<c:if test="${cms.videoStorage == null }">${cms.giveVideoStorage }</c:if>
											<c:if test="${cms.videoStorage != null }">${cms.videoStorage+cms.giveVideoStorage }</c:if>
										</em>GB<i
												class="iconfont ask" title="总空间">&#xe60f;</i>
										</p>
										<p class="infos">
											<c:if test="${cms.videoEndDate != null }">
												<em style="font-size:14px;width: 85px; margin-top:3px;"><fmt:formatDate
														value="${cms.videoEndDate }" pattern="yyyy-MM-dd"/></em>空间到期时间
											</c:if>
											<c:if test="${cms.videoEndDate == null }">
												<em style="font-size:14px;width: 85px; margin-top:3px;"><fmt:formatDate
														value="${cms.giveVideoStorageDate }" pattern="yyyy-MM-dd"/></em>空间到期时间
											</c:if>
										</p>
									</div>
								</div>
							</li>
						</c:if>
						<%-- <c:if test="${requestScope.SERVICE_FACE == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i5 active" style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">面授服务</a>
											<c:if test="${!empty manganger }">
											<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_FACE">&#xe635;</i>
											</c:if>
										</p>
									</div>

								</div>
								<div class="right server-infos">
									<p class="infos"> </p>
									<c:if test="${requestScope.SERVICE_AGENT == null }">
									<p class="infos"> </p>
									<p class="infos" style="width:120px;margin-left:-30px">已开启代报考服务</p>
										<!-- <i class="iconfont ask" title="可招学员">&#xe60f;</i> -->
									</c:if>
									<c:if test="${requestScope.SERVICE_AGENT == 'SERVICE_AGENT' }">
									<p class="infos"> </p>
									<p class="infos" style="width:120px;margin-left:-30px">未开启代报考服务</p>
										<!-- <i class="iconfont ask" title="可招学员">&#xe60f;</i> -->
									</c:if>
								</div>
								<c:if test="${!empty manganger }">
								<p class="btns">
									<c:if test="${requestScope.SERVICE_AGENT == null }">
										<a href="javascript:;" class="btn btn-sm btn-default btn-colse-ser" data-type="SERVICE_AGENT">关闭代报考业务</a>
									</c:if>
									<c:if test="${requestScope.SERVICE_AGENT == 'SERVICE_AGENT' }">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_AGENT">开启代报考业务</a>
									</c:if>
								</p>
								</c:if>
							</li>
						</c:if> --%>
					</ul>
				</div>
				<div class="q-ucont">
					<div class="inter-set q-text">
						<p><i class="iconfont">&#xe67c;</i>课程功能管理</p>
					</div>
					<ul class="clear">
						<c:if test="${requestScope.SERVICE_COURSE_SET == null }">
							<li class="clear">
								<div class="left" style="width: 200px">
									<div>
										<!-- 		                      	 	<span class="new"></span> -->
										<i class="icons i1 active" style="cursor: pointer;" onclick="forwardCourse();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardCourse();"> 课程配置 </a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">课程设置</p>
									<p class="infos">课程权限</p>
									<p class="infos">课程管理</p>
								</div>
								<shiro:hasPermission name="course_head">
									<p class="btns isok">
										<c:choose>
											<c:when test="${company.memberLevel == 12 or company.memberLevel == 13 }">
												<%--<c:if test="${empty sessionScope.COURSE_VALIDATE or sessionScope.COURSE_VALIDATE == 1 }">
													<a href="javascript:;" class="btn btn-sm btn-default" data-url="classManage/manageCourseValidaty">课程有效期</a>
												</c:if>--%>
												<%--<c:if test="${empty sessionScope.SIGNUP_NEWS or sessionScope.SIGNUP_NEWS == 1 }">
													<a href="javascript:;" class="btn btn-sm btn-default"
													   data-url="classManage/signup_news">报名通知</a>
												</c:if>--%>
												<c:if test="${empty sessionScope.COURSE_PL or sessionScope.COURSE_PL == 1 }">
													<a href="javascript:;" class="btn btn-sm btn-default"
													   data-url="classManage/manage_classpl">课程评论</a>
												</c:if>
												<c:if test="${empty sessionScope.COURSE_WD or sessionScope.COURSE_WD == 1 }">
													<a href="javascript:;" class="btn btn-sm btn-default"
													   data-url="Question/queAnsSet">课程问答</a>
												</c:if>
											</c:when>
											<c:otherwise>
												<%--<a href="javascript:;" class="btn btn-sm btn-default" data-url="classManage/manageCourseValidaty">课程有效期</a>--%>
												<%--<a href="javascript:;" class="btn btn-sm btn-default"
												   data-url="classManage/signup_news">报名通知</a>--%>
												<a href="javascript:;" class="btn btn-sm btn-default"
												   data-url="classManage/manage_classpl">课程评论</a>
												<a href="javascript:;" class="btn btn-sm btn-default"
												   data-url="Question/queAnsSet">课程问答</a>
											</c:otherwise>
										</c:choose>
										<a href="javascript:;" class="btn btn-sm btn-default right-btn more"
										   data-url="classManage/manage_classpl">更多</a>
									</p>
								</shiro:hasPermission>
							</li>
						</c:if>
						<%--  <c:if test="${requestScope.SERVICE_CLASS_PACKAGE == null }">
							 <li class="clear">
								 <div class="left" style="width: 200px;">
									 <div>
										 <i class="icons i17 active" style="cursor: pointer;" onclick="forwardclassPackage();"></i>
										 <p class="s-name">
											 <a href="javascript:void(0);"  onclick="forwardclassPackage();">课程包</a>
											 <c:if test="${!empty manganger }">
											 <i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_CLASS_PACKAGE">&#xe635;</i>
											 </c:if>
										 </p>
									 </div>
								 </div>
								 <div class="right server-infos">
									 <p class="infos">课程阶段</p>
									 <p class="infos">打包售卖</p>
									 <p class="infos">学习计划</p>
								 </div>
								 <c:if test="${!empty manganger }">
									 <p class="btns isno">
										 <a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_CLASS_PACKAGE">开通</a>
									 </p>
								 </c:if>
								 <shiro:hasPermission name="class_package">
								 <input type="hidden" value="1" id="classPackageServicePri"/>
								 <p class="btns isok" style="height:28px">
									 <a href="<%=rootPath %>/classPackageCategory/addClassPackageCategory" class="btn btn-sm btn-default">添加分类</a>
									 <a href="<%=rootPath %>/classPackageCategory/SetClassPackageCategory" class="btn btn-sm btn-default">设置</a>
								 </p>
								 </shiro:hasPermission>
							 </li>
						 </c:if> --%>
						<%--  <c:if test="${requestScope.SERVICE_OPENCLASS == null }">
							 <li class="clear">
								 <div class="left" style="width: 200px;">
									 <div>
									  <span class="new"></span>
									 <i class="icons i6 active" style="cursor: pointer;" onclick="forwardOpenCourse();"></i>
										 <p class="s-name">
											 <a href="javascript:void(0);" onclick="forwardOpenCourse();" style="text-decoration: none;">公开课</a>
											 <c:if test="${!empty manganger }">
											 <i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_OPENCLASS">&#xe635;</i>
											 </c:if>
										 </p>
									 </div>
								 </div>
								 <div class="right server-infos">
									 <p class="infos">公开课列表</p>
									 <p class="infos">公开课轮播图</p>
									 <p class="infos">公开课模板</p>
								 </div>
								 <shiro:hasPermission name="openclass_head">
									 <p class="btns isok" style="height:28px">
										 <a href="<%=rootPath %>/liveOpenCourse/showLiveOpenClassSetting" class="btn btn-sm btn-default">公开课设置</a>
										 <a href="<%=rootPath %>/sysBody/openClassOption" class="btn btn-sm btn-default">公开课模板</a>
										 <a href="<%=rootPath %>/liveOpenCourse/showBannerPic" class="btn btn-sm btn-default">公开课轮播图</a>
									 </p>
								 </shiro:hasPermission>
							 </li>
						 </c:if> --%>
					</ul>
				</div>
				<div class="q-ucont">
					<div class="inter-set q-text">
						<p><i class="iconfont">&#xe6c5;</i>运营功能管理</p>
					</div>
					<ul class="clear">
						<c:if test="${requestScope.SERVICE_PAY_SET == null and CURRENT_IS_AREA eq '0'}">
							<c:if test="${sessionScope.isAreaSchool1 eq 0}">
								<li class="clear">
									<div class="left" style="width: 200px;">
										<div>
											<i class="icons i8 active" style="cursor:pointer;"
											   onclick="forwardPaySet();"></i>
											<p class="s-name">
												<a href="javascript:void(0);" onclick="forwardPaySet();"
												   style="cursor:pointer; text-decoration: none;">支付</a>
											</p>
										</div>
									</div>
									<div class="right server-infos">
										<p class="infos">
										</p>
										<p class="infos">
											<c:if test="${zfbStatus=='0'}">
												<em></em><span style="color: red;">未开通</span>
											</c:if>
											<c:if test="${zfbStatus=='1'}">
												<em></em><span>已开通</span>
											</c:if>

										</p>
									</div>
								</li>
							</c:if>
						</c:if>
						<c:if test="${requestScope.SERVICE_QUESTION_ANSWER == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
										<i class="icons i10 active" style="cursor: pointer;" onclick="forwardQuest();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardQuest();"
											   style="text-decoration: none;">问答社区</a>
											<c:if test="${!empty manganger }">
												<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务"
												   data-type="SERVICE_QUESTION_ANSWER">&#xe635;</i>
											</c:if>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">学员提问</p>
									<p class="infos">社区运营</p>
									<p class="infos">问答管理</p>
								</div>
								<shiro:hasPermission name="community_head">
									<p class="btns isok" style="height:28px">
										<a href="javascript:;" class="btn btn-sm btn-default"
										   data-url="Question/questionSetIndex">设置</a>
									</p>
								</shiro:hasPermission>
							</li>
						</c:if>
						<c:if test="${requestScope.SERVICE_MARKETING_SET == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
										<i class="icons i12 active" style="cursor: pointer;"
										   onclick="forwardMarking();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardMarking();">营销工具</a>
										</p>
									</div>

								</div>
								<div class="right server-infos">
									<p class="infos">公众号运营</p>
									<p class="infos">在线客服</p>
									<p class="infos">QQ运营</p>
								</div>
								<shiro:hasPermission name="marketing_head">
									<p class="btns isok" style="height:28px">
										<a href="<%=rootPath %>/companyMarketSet/showCompanyMarket"
										   class="btn btn-sm btn-default">营销QQ</a>
										<a href="<%=rootPath %>/companyMarketSet/CompanyMarket_wx"
										   class="btn btn-sm btn-default">微信</a>
										<a href="<%=rootPath %>/companyMarketSet/CompanyMarket_wb"
										   class="btn btn-sm btn-default">新浪微博</a>
										<a href="<%=rootPath %>/companyMarketSet/CompanyMarket_kf"
										   class="btn btn-sm btn-default">客服电话</a>
										<a href="<%=rootPath %>/companyMarketSet/showCompanyMarket"
										   class="btn btn-sm btn-default right-btn more">更多</a>
									</p>
								</shiro:hasPermission>
							</li>
						</c:if>

						<c:if test="${requestScope.SERVICE_MSG_SET == null }">
							<c:if test="${sessionScope.isAreaSchool1 eq 0}">
								<li class="clear">
									<div class="left" style="width: 200px;">
										<div>
											<i class="icons i15 active" style="cursor: pointer;"
											   onclick="forwardSms();"></i>
											<p class="s-name">
												<a href="javascript:void(0);" onclick="forwardSms();">短信</a>
											</p>
										</div>

									</div>
									<div class="right server-infos">
										<p class="infos">
											<em style="font-size:14px; width: 85px;margin-top: 3px;">
												<c:if test="${cm.messageTotal ==null }">${cm.giveMessage }</c:if>
												<c:if test="${cm.messageTotal !=null }">${cm.messageTotal+cm.giveMessage}</c:if>
											</em>短信总量<i
												class="iconfont ask" title="短信总量">&#xe60f;</i>
										</p>
										<p class="infos">
											<em style="font-size:14px; width: 85px;margin-top: 3px;">${cs.messageSend }</em>已使用量<i
												class="iconfont ask" title="已使用短信量">&#xe60f;</i>
										</p>
										<p class="infos">
											<em style="font-size:14px; width: 85px;margin-top: 3px;">${cm.messageTotal - cs.messageSend + cms.giveMessage }</em>条剩余
										</p>
									</div>
								</li>
							</c:if>
						</c:if>
						<%-- <c:if test="${requestScope.SERVICE_EMAIL_SET == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i16 active" style="cursor: pointer;" onclick="forwardEmail();"></i>
										<p class="s-name">
											<a href="javascript:void();" onclick="forwardEmail();">邮件</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">
										<em style="font-size:14px; margin-top: 3px;">
											<c:if test="${cms.emailTotal == null }">${cms.giveEmail }</c:if>
											<c:if test="${cms.emailTotal != null }">${cms.emailTotal+cms.giveEmail }</c:if>
										</em>封总量<i
											class="iconfont ask" title="邮件总量">&#xe60f;</i>
									</p>
									<p class="infos">
										<em style="font-size:14px;margin-top: 3px;">${css.emailSend }</em>已使用
									</p>
									<p class="infos">
										<em style="font-size:14px;margin-top: 3px;">${cms.emailTotal - css.emailSend + cms.giveEmail }</em>封剩余
									</p>
								</div>
							</li>
						</c:if> --%>
						<%-- <c:if test="${requestScope.SERVICE_INTEGRAL == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
										<i class="icons i18 active" style="cursor: pointer;" onclick="forwardIntegral();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardIntegral();">积分</a>
											<c:if test="${!empty manganger }">
											<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_INTEGRAL">&#xe635;</i>
											</c:if>
										</p>
									</div>

								</div>
								<div class="right server-infos">
									<p class="infos">积分设置</p>
									<p class="infos">获取积分</p>
									<p class="infos">积分消费</p>
								</div>
								<shiro:hasPermission name="integral_head">
								<input type="hidden" value="1" id="integralServicePri"/>
								<p class="btns isok" style="height:28px">
									<a href="<%=rootPath %>/companyIntegralConfig/openIntegralSet" class="btn btn-sm btn-default">积分设置</a>
									<a href="<%=rootPath %>/companyIntegralConfig/openGetIntegral" class="btn btn-sm btn-default">获取积分</a>
									<a href="<%=rootPath %>/companyIntegralConfig/openCustomIntegral" class="btn btn-sm btn-default">积分消费</a>
								</p>
								</shiro:hasPermission>
							</li>
						</c:if> --%>
						<%-- <c:if test="${requestScope.SERVICE_PROMOTION == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
										 <span class="new"></span>
										<i class="icons i21 active" style="cursor: pointer;" onclick="forwardpromotion();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardpromotion();">促销<i class="icon-red"></i></a>
											<c:if test="${!empty manganger }">
											<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_PROMOTION">&#xe635;</i>
											</c:if>
										</p>
									</div>

								</div>
								<div class="right server-infos">
									<p class="infos">优惠码</p>
									<p class="infos">邀请他人</p>
								</div>
								<p class="btns isok" style="height:28px">
									<shiro:hasPermission name="promotion_head">
									 <input type="hidden" value="1" id="promotionServicePri"/>
									<a href="<%=rootPath %>/company/setCouponService" class="btn btn-sm btn-default">优惠码</a>
									 </shiro:hasPermission>
									 <shiro:hasPermission name="invite_code_head">
									<a href="<%=rootPath %>/companyInvitConfig/toCompanyInviteCofig" class="btn btn-sm btn-default">邀请码</a>
									</shiro:hasPermission>
									<c:if test="${rechargecard_service==1 }">
									<a href="<%=rootPath %>/companyRechargePatch/gotoRechargeCardOpenFlagPage" class="btn btn-sm btn-default">充值卡</a>
									</c:if>
								</p>
							</li>
						</c:if> --%>
						<%-- <c:if test="${requestScope.SERVICE_TEACHER == null }">
							<c:if test="${sessionScope.isAreaSchool1 eq 0}">
								<li class="clear">
									<div class="left" style="width: 200px;">
										<div>
											<i class="icons i9 active" style="cursor: default;"></i>
											<p class="s-name">
												<a href="javascript:;" style="cursor: default;text-decoration: none;">名师专题</a>
												<c:if test="${!empty manganger }">
													<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务"
													   data-type="SERVICE_TEACHER">&#xe635;</i>
												</c:if>
											</p>
										</div>

									</div>
									<div class="right server-infos">
										<p class="infos">名师动态</p>
										<p class="infos">名师评论</p>
										<p class="infos">名师个人主页</p>
									</div>
									<p class="btns isok" style="height:28px">
									</p>
								</li>
							</c:if>
						</c:if> --%>
						<%-- <c:if test="${requestScope.SERVICE_STAGE == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i13 active" style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">分期缴费服务</a>
											<c:if test="${!empty manganger }">
											<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_STAGE">&#xe635;</i>
											</c:if>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">分期缴费</p>
									<p class="infos">补费</p>
									<p class="infos">催缴</p>
								</div>
								<p class="btns isok" style="height:28px"><!--
										<a href="javascript:;" class="btn btn-sm btn-default" data-type="SYS_CONFIG_SERVICE_STAGE">开通</a>
										<a href="javascript:;" class="btn btn-sm btn-default" data-type="studetail">详情</a> -->
									</p>
							</li>
						</c:if> --%>
						<%-- <c:if test="${requestScope.SERVICE_STUDYCARD == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<span class="new"></span>
									<i class="icons i24 active" style="cursor: default;" ></i>
										<p class="s-name">
											<a href="javascript:void(0);"  style="cursor: default;text-decoration: none;">学习卡</a>
											<c:if test="${!empty manganger }">
											<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_STUDYCARD">&#xe635;</i>
											</c:if>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">学习卡</p>
								</div>
								<p class="btns isok" style="height:28px"><!--
										<a href="javascript:;" class="btn btn-sm btn-default" data-type="SYS_CONFIG_SERVICE_STAGE">开通</a>
										<a href="javascript:;" class="btn btn-sm btn-default" data-type="studetail">详情</a> -->
								</p>
							</li>
						</c:if> --%>
						<%-- <c:if test="${requestScope.SERVICE_STUDENT_ASPIRATIONS == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<span class="new"></span>
									<i class="icons i26 active" style="cursor: pointer;" onclick="forwardStudentAspirations();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardStudentAspirations();" style="cursor: pointer;text-decoration: none;">学员心声</a>
											<c:if test="${!empty manganger }">
											<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_STUDENT_ASPIRATIONS">&#xe635;</i>
											</c:if>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">学员心声展示</p>
								</div>
								<p class="btns isok" style="height:28px">
										<a href="<%=rootPath%>/studentStar/showImg" class="btn btn-sm btn-default" data-type="SYS_CONFIG_SERVICE_STAGE">展示图</a>
								</p>
							</li>
						</c:if> --%>
					</ul>
				</div>
				<%-- <div class="q-ucont">
					  <div class="inter-set q-text">
						  <p><i class="iconfont">&#xe6c6;</i>学员功能管理</p>
					  </div>
					  <ul class="clear">
					  <c:if test="${requestScope.SERVICE_STUDENT_SET == null }">
					  <c:choose>
						  <c:when test="${(company.memberLevel == 12 or company.memberLevel == 13) and !empty sessionScope.SERVICE_STUDENT_SET }">
							  <c:if test="${sessionScope.SERVICE_STUDENT_SET == 1 }">
								<li class="clear">
									<div class="left" style="width: 200px;">
										<div>
											<i class="icons stud active" style="cursor: pointer;" onclick="forwardStudentSet();"></i>
											<p class="s-name">
												<a href="javascript:void(0);" onclick="forwardStudentSet();" style="text-decoration: none;">学员设置</a>
													<c:if test="${!empty manganger }">
		<!-- 											<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_MEMBER">&#xe635;</i> -->
													</c:if>
											</p>
										</div>
									</div>
									<div class="right server-infos">
										<p class="infos">登录设置</p>
										<p class="infos" style="width:108px;">注册设置</p>
									</div>
									 <p class="btns isok" style="height:28px">
								<c:choose>
									<c:when test="${company.memberLevel == 12 or company.memberLevel == 13 }">
									<c:if test="${empty sessionScope.SYSTEM_STU_LOG or sessionScope.SYSTEM_STU_LOG == 1 }">
										<a href="<%=rootPath %>/companyFunctionSet/stuLogin" class="btn btn-sm btn-default stu-set">学员登录设置</a>
									</c:if>
										<a href="<%=rootPath %>/companyFunctionSet/stuRegister" class="btn btn-sm btn-default stu-set">学员注册设置</a>
									<c:if test="${empty sessionScope.STUDENT_GROUP or sessionScope.STUDENT_GROUP == 1 }">
										<a href="<%=rootPath %>/studentGroup/setStudentGroup" class="btn btn-sm btn-default stu-set">学员分组</a>
									</c:if>
									</c:when>
									<c:otherwise>
										<a href="<%=rootPath %>/companyFunctionSet/stuLogin" class="btn btn-sm btn-default stu-set">学员登录设置</a>
										<a href="<%=rootPath %>/companyFunctionSet/stuRegister" class="btn btn-sm btn-default stu-set">学员注册设置</a>
										<a href="<%=rootPath %>/studentGroup/setStudentGroup" class="btn btn-sm btn-default stu-set">学员分组</a>
									</c:otherwise>
								</c:choose>
									</p>
								</li>
							  </c:if>
						  </c:when>
						  <c:otherwise>
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
										<i class="icons stud active" style="cursor: pointer;" onclick="forwardStudentSet();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardStudentSet();" style="text-decoration: none;">学员设置</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">登录设置</p>
									<p class="infos" style="width:108px;">注册设置</p>
								</div>
								 <p class="btns isok" style="height:28px">
								<c:choose>
									<c:when test="${company.memberLevel == 12 or company.memberLevel == 13 }">
										<a href="<%=rootPath %>/companyFunctionSet/stuRegister" class="btn btn-sm btn-default stu-set">学员注册设置</a>
									<c:if test="${empty sessionScope.SYSTEM_STU_LOG or sessionScope.SYSTEM_STU_LOG == 1 }">
										<a href="<%=rootPath %>/companyFunctionSet/stuLogin" class="btn btn-sm btn-default stu-set">学员登录设置</a>
									</c:if>
									<c:if test="${empty sessionScope.STUDENT_GROUP or sessionScope.STUDENT_GROUP == 1 }">
										<a href="<%=rootPath %>/studentGroup/setStudentGroup" class="btn btn-sm btn-default stu-set">学员分组</a>
									</c:if>
									</c:when>
									<c:otherwise>
										<a href="<%=rootPath %>/companyFunctionSet/stuRegister" class="btn btn-sm btn-default stu-set">学员注册设置</a>
										<a href="<%=rootPath %>/companyFunctionSet/stuLogin" class="btn btn-sm btn-default stu-set">学员登录设置</a>
										<a href="<%=rootPath %>/studentGroup/setStudentGroup" class="btn btn-sm btn-default stu-set">学员分组</a>
									</c:otherwise>
								</c:choose>
								</p>
							</li>
						  </c:otherwise>
					  </c:choose>
					  </c:if>
						<c:if test="${requestScope.SERVICE_MEMBER == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
										<i class="icons i19 active" style="cursor: pointer;" onclick="forwardMember();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardMember();">会员</a>
											<c:if test="${!empty manganger }">
											<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_MEMBER">&#xe635;</i>
											</c:if>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">设置会员</p>
									<p class="infos" style="width:108px;">设置会员等级</p>
								</div>
								<shiro:hasPermission name="member_head">
								<input type="hidden" value="1" id="memberServicePri"/>
								<p class="btns isok" style="height:28px">
									<a href="<%=rootPath %>/companyMemberConfig/addUI" class="btn btn-sm btn-default">设置会员</a>
									<a href="<%=rootPath %>/companyMemberLevelConfig/vipSet" class="btn btn-sm btn-default">设置会员等级</a>
								</p>
								</shiro:hasPermission>
							</li>
						</c:if>
					  </ul>
				</div> --%>
				<%-- <div class="q-ucont">
					  <div class="inter-set q-text">
						  <p><i class="iconfont">&#xe6c8;</i>题库功能管理</p>
					  </div>
					  <ul class="clear">
							 <c:if test="${requestScope.SERVICE_TIKU == null }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i7 active" style="cursor:pointer;" onclick="forwardTiku();"></i>
										<p class="s-name">
											<a href="javascript:void(0);" onclick="forwardTiku();" style="text-decoration: none;">题库</a>
											<c:if test="${!empty manganger }">
												<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_TIKU">&#xe635;</i>
											</c:if>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">专业题库</p>
									<p class="infos">随堂考试</p>
									<p class="infos">模拟真实考题</p>
								</div>
								<shiro:hasPermission name="tiku_head">
									<p class="btns isok" style="height:28px">
										<a href="<%=rootPath %>/tikuSet/toSet/stuFree" class="btn btn-sm btn-default">题库设置</a>
									</p>
								</shiro:hasPermission>
							</li>
						</c:if>
					 </ul>
				   </div> --%>
				<c:if test="${CURRENT_IS_AREA == '2'}">
					<div class="q-ucont">
						<div class="inter-set q-text">
							<p><i class="iconfont">&#xe6c8;</i>分校行政</p>
						</div>
						<ul class="clear">
							<c:if test="${requestScope.SERVICE_CLASS_SET == null }">
								<li class="clear">
									<div class="left" style="width: 200px;">
										<div>
											<i class="icons i7 active" style="cursor:pointer;" onclick="forwardClassSet();"></i>
											<p class="s-name">
												<a href="javascript:void(0);" onclick="forwardClassSet();"
												   style="text-decoration: none;">行政班设置</a>
												<c:if test="${!empty manganger }">
													<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务"
													   data-type="SERVICE_TIKU">&#xe635;</i>
												</c:if>
											</p>
										</div>
									</div>
									<div class="right server-infos">
											<%--<p class="infos">行政班设置</p>
											<p class="infos">随堂考试</p>
											<p class="infos">模拟真实考题</p>--%>
									</div>
										 <%--<shiro:hasPermission name="administrative_class">--%>
									<p class="btns isok" style="height:28px">
										<a href="<%=rootPath%>/administrativeClassManager/administrativeClass"
										   class="btn btn-sm btn-default">行政班设置</a>
									</p>
										 <%--</shiro:hasPermission>--%>
								</li>
							</c:if>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<%--未开通服务--%>
	<div class="u-wrap company q-wrap q-wrap-two">
		<div class="heading">
			<h2 class="h5">未开通服务</h2>
		</div>
	</div>
    <div class="u-wrap company no-open"  >
	      <div class="mainbackground u-content clear q-mainbackground last-list">
	          <div class="service-list" >
                 <div class="q-ucont">
                 	 <ul class="clear">
                 	 	<c:if test="${requestScope.SERVICE_ONLINE_SET =='SERVICE_ONLINE_SET' }">
                     	<li class="clear">
		                    <div class="left" style="width: 200px">
		                        <div>
		                            <i class="icons i2 active disable" style="cursor: pointer;"></i>
		                            <p class="s-name">
		                                <a href="javascript:void(0);"> 网校设置 <i class="icon-red"></i> </a>
		                            </p>
		                        </div>
		                    </div>
		                    <div class="right server-infos">
		                       <p class="infos">网校模板配置</p>
		                       <p class="infos">网校页面优化</p>
		                       <p class="infos">网校推广管理</p>
		                    </div>
			                 <%--<c:if test="${!empty manganger }">--%>
								<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_ONLINE_SET">开通</a>
									</p>
								<%--</c:if>--%>
		                </li>
		                </c:if>
		              <%--   <c:if test="${requestScope.SERVICE_BRANCH_SCHOOL_SET =='SERVICE_BRANCH_SCHOOL_SET'}">
				                <li class="clear">
				                    <div class="left" style="width: 200px;">
				                        <div>
				                            <i class="icons i14 active disable" style="cursor: pointer;"></i>
				                            <p class="s-name">
				                                <a href="javascript:void(0);" style="text-decoration: none;">分校管理</a>
				                            </p>
				                        </div>
				                    </div>
				                    <div class="right server-infos">
				                        <p class="infos">
				                            <em style="font-size:14px; width: 85px;margin-top: 3px;">${css.schoolNum }</em>分校数量
				                        </p>
				                    </div>
					                <p class="btns isok" style="height:28px">
					                     <a href="javascript:;" class="btn btn-sm btn-default">分校设置</a>
					                </p>
					            <c:if test="${!empty manganger }">
									<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_BRANCH_SCHOOL_SET">开通</a>
									</p>
								</c:if>
		              		</li>
		                </c:if> --%>
                	 		<c:if test="${requestScope.SERVICE_LIVE == 'SERVICE_LIVE' }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i3 active disable" style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">直播概况</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">直播授课</p>
									<p class="infos">在线答疑</p>
									<p class="infos">直播回放</p>
								</div>
								<%--<c:if test="${!empty manganger }">--%>
								<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_LIVE">开通</a>
									</p>
								<%--</c:if>--%>
							</li>
						</c:if>
						<c:if test="${requestScope.SERVICE_VIDEO == 'SERVICE_VIDEO' }">
							<li>
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i4 active disable" style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">点播概况</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">多种视频格式</p>
									<p class="infos">全网加速</p>
									<p class="infos">视频防盗链接</p>
								</div>
								<%--<c:if test="${!empty manganger }">--%>
								<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_VIDEO">开通</a>
									</p>
								<%--</c:if>--%>
							</li>
						</c:if>
					<%-- 	<c:if test="${requestScope.SERVICE_FACE == 'SERVICE_FACE' }">
							<li>
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i5 active disable" style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">面授服务</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">线下运营</p>
									<p class="infos">代报考</p>
									<p class="infos">分期缴费</p>
								</div>
								<c:if test="${!empty manganger }">
								   	<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_FACE">开通</a>
									</p>
								</c:if>
							</li>
						</c:if> --%>
						<c:if test="${requestScope.SERVICE_COURSE_SET == 'SERVICE_COURSE_SET'}">
                     	<li class="clear">
		                    <div class="left" style="width: 200px">
		                        <div>
		                            <i class="icons i1 active disable" style="cursor: pointer;"></i>
		                            <p class="s-name">
		                                <a href="javascript:void(0);"> 课程配置 </a>
		                            </p>
		                        </div>
		                    </div>
		                    <div class="right server-infos">
		                       <p class="infos">课程设置</p>
		                       <p class="infos">课程权限</p>
		                       <p class="infos">课程管理</p>
		                    </div>
			                <%--<c:if test="${!empty manganger }">--%>
								   <p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_COURSE_SET">开通</a>
									</p>
							<%--</c:if>--%>
		                </li>
		                </c:if>
						<%-- <c:if test="${requestScope.SERVICE_OPENCLASS == 'SERVICE_OPENCLASS' }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i6 active disable" style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">公开课</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">公开课列表</p>
									<p class="infos">公开课轮播图</p>
									<p class="infos">公开课模板</p>
								</div>
								<c:if test="${!empty manganger }">
								<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_OPENCLASS">开通</a>
									</p>
								</c:if>
							</li>
						</c:if> --%>
						<%-- <c:if test="${requestScope.SERVICE_TIKU == 'SERVICE_TIKU' }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i7 active disable" style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">题库</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">专业题库</p>
									<p class="infos">随堂考试</p>
									<p class="infos">模拟真实考题</p>
								</div>
								 <c:if test="${!empty manganger }">
									<p class="btns isno">
											<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_TIKU">开通</a>
									</p>
								</c:if>
							</li>
						</c:if> --%>
						<%-- <c:if test="${requestScope.SERVICE_TEACHER == 'SERVICE_TEACHER' }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i9 active disable" style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">名师专题</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">名师动态</p>
									<p class="infos">名师评论</p>
									<p class="infos">名师个人主页</p>
								</div>
								<c:if test="${!empty manganger }">
								<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_TEACHER">开通</a>
									</p>
								</c:if>
							</li>
						</c:if> --%>
						 <c:if test="${requestScope.SERVICE_QUESTION_ANSWER == 'SERVICE_QUESTION_ANSWER' }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i10 active disable"style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">问答社区</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">学员提问</p>
									<p class="infos">社区运营</p>
									<p class="infos">问答管理</p>
								</div>
								<c:if test="${!empty manganger }">
								<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_QUESTION_ANSWER">开通</a>
									</p>
								</c:if>
							</li>
						</c:if>
						<%-- <c:if test="${requestScope.SERVICE_STAGE == 'SERVICE_STAGE' }">
							<li class="clear">
								<div class="left" style="width: 200px;">
									<div>
									<i class="icons i13 active disable" style="cursor: default;"></i>
										<p class="s-name">
											<a href="javascript:;" style="cursor: default;text-decoration: none;">分期缴费服务</a>
										</p>
									</div>
								</div>
								<div class="right server-infos">
									<p class="infos">分期缴费</p>
									<p class="infos">补费</p>
									<p class="infos">催缴</p>
								</div>
								<c:if test="${!empty manganger }">
								<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_STAGE">开通</a>
									</p>
								</c:if>
							</li>
						</c:if> --%>
						<%-- <c:if test="${requestScope.SERVICE_CLASS_PACKAGE == 'SERVICE_CLASS_PACKAGE' }">
							<li class="clear">
			                    <div class="left" style="width: 200px;">
			                        <div>
			                            <i class="icons i17 active disable"></i>
			                            <p class="s-name">
			                                <a href="javascript:void(0);">课程包</a>
			                            </p>
			                        </div>
			
			                    </div>
			                    <div class="right server-infos">
			                        <p class="infos">课程阶段</p>
									<p class="infos">打包售卖</p>
									<p class="infos">学习计划</p>
			                    </div>
			                    <c:if test="${!empty manganger }">
									<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_CLASS_PACKAGE">开通</a>
									</p>
								</c:if>
			                </li>
		                </c:if> --%>
		                <%-- <c:if test="${requestScope.SERVICE_APP == 'SERVICE_APP' }">
		                <li class="clear">
			                    <div class="left" style="width: 200px;">
			                        <div>
			                            <i class="icons i20 active disable"></i>
			                            <p class="s-name">
			                                <a href="javascript:void(0);">APP</a>
			                            </p>
			                        </div>
			
			                    </div>
			                    <div class="right server-infos">
			                        <p class="infos">APP网校</p>
									<p class="infos">APP题库</p>
			                    </div>
			                    <c:if test="${!empty manganger }">
									<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_APP">开通</a>
									</p>
								</c:if>
			                </li>
			                </c:if> --%>
							<%-- <c:if test="${requestScope.SERVICE_INTEGRAL == 'SERVICE_INTEGRAL' }">
							<li class="clear">
			                    <div class="left" style="width: 200px;">
			                        <div>
			                            <i class="icons i18 active disable"></i>
			                            <p class="s-name">
			                                <a href="javascript:void(0);">积分</a>
			                            </p>
			                        </div>
			
			                    </div>
			                    <div class="right server-infos">
			                        <p class="infos">积分设置</p>
									<p class="infos">获取积分</p>
									<p class="infos">积分消费</p>
			                    </div>
			                    <c:if test="${!empty manganger }">
								<p class="btns isno">
									<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_INTEGRAL">开通</a>
								</p>
								</c:if>
			                </li>
		                </c:if> --%>
		                <%-- <c:if test="${requestScope.SERVICE_MEMBER == 'SERVICE_MEMBER' }">
							<li class="clear">
			                    <div class="left" style="width: 200px;">
			                        <div>
			                            <i class="icons i19 active disable"></i>
			                            <p class="s-name">
			                                <a href="javascript:void(0);">会员</a>
			                            </p>
			                        </div>
			
			                    </div>
			                    <div class="right server-infos">
			                        <p class="infos">设置会员</p>
									<p class="infos">设置会员等级</p>
			                    </div>
			                    <c:if test="${!empty manganger }">
								<p class="btns isno">
									<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_MEMBER">开通</a>
								</p>
								</c:if>
			                </li>
		                </c:if> --%>
		               <%--  <c:if test="${requestScope.SERVICE_PROMOTION == 'SERVICE_PROMOTION' }">
							<li class="clear">
			                    <div class="left" style="width: 200px;">
			                        <div>
 			                       		<span class="new"></span> 
			                            <i class="icons i21 active disable"></i>
			                            <p class="s-name">
			                                <a href="javascript:void(0);">促销</a>
			                            </p>
			                        </div>
			
			                    </div>
			                    <div class="right server-infos">
			                        <p class="infos">优惠码</p>
			                        <p class="infos">邀请他人</p>
			                    </div>
			                    <c:if test="${!empty manganger }">
								<p class="btns isno">
									<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_PROMOTION">开通</a>
								</p>
								</c:if>
			                </li>
		                </c:if> --%>
		                <c:if test="${requestScope.SERVICE_MICROSCHOOL == 'SERVICE_MICROSCHOOL' }">
							<li class="clear">
			                    <div class="left" style="width: 200px;">
			                        <div>
			                            <i class="icons i23 active disable"></i>
			                            <p class="s-name">
			                                <a href="javascript:void(0);">WAP管理</a>
			                            </p>
			                        </div>
			
			                    </div>
			                    <div class="right server-infos">
			                        <%--<p class="infos">导航配置</p>
		                         	<p class="infos">页尾设置</p>--%>
		                        	<p class="infos">轮播图设置</p>
			                    </div>
			                    <%--<c:if test="${!empty manganger }">--%>
								<p class="btns isno">
									<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_MICROSCHOOL">开通</a>
								</p>
								<%--</c:if>--%>
			                </li>
		                </c:if>
		               <%--  <c:if test="${requestScope.SERVICE_STUDYCARD == 'SERVICE_STUDYCARD' }">
							<li class="clear">
			                    <div class="left" style="width: 200px;">
			                        <div>
			                            <i class="icons i25 active disable"></i>
			                            <p class="s-name">
			                                <a href="javascript:void(0);">学习卡</a>
			                            </p>
			                        </div>
			
			                    </div>
			                    <div class="right server-infos">
			                        <p class="infos">学习卡</p>
			                    </div>
			                    <c:if test="${!empty manganger }">
								<p class="btns isno">
									<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_STUDYCARD">开通</a>
								</p>
								</c:if>
			                </li>
		                </c:if> --%>
		                <%-- <c:if test="${requestScope.SERVICE_STUDENT_ASPIRATIONS == 'SERVICE_STUDENT_ASPIRATIONS' }">
							<li class="clear">
			                    <div class="left" style="width: 200px;">
			                        <div>
			                            <i class="icons i27 active disable"></i>
			                            <p class="s-name">
			                                <a href="javascript:void(0);">学员心声</a>
			                            </p>
			                        </div>
			
			                    </div>
			                    <div class="right server-infos">
			                        <p class="infos">学员心声展示</p>
			                    </div>
			                    <c:if test="${!empty manganger }">
								<p class="btns isno">
									<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_STUDENT_ASPIRATIONS">开通</a>
								</p>
								</c:if>
			                </li>
		                </c:if> --%>
		                <c:if test="${requestScope.SERVICE_PAY_SET =='SERVICE_PAY_SET' and CURRENT_IS_AREA eq '0' }">
                     	<li class="clear">
							<div class="left" style="width: 200px;">
								<div>
								<i class="icons i8 active disable" style="cursor:pointer;"></i>
									<p class="s-name">
										<a href="javascript:void(0);" style="cursor:pointer; text-decoration: none;">支付</a>
									</p>
								</div>
							</div>
							<div class="right server-infos">
								<p class="infos">
								</p>
								<p class="infos">
									<c:if test="${zfbStatus=='0'}">
										<em></em><span style="color: red;">未开通</span>
									</c:if>
									<c:if test="${zfbStatus=='1'}">
										<em></em><span>已开通</span>
									</c:if>
									
								</p>
								<p class="infos">
								</p>
							</div>
							<%--<c:if test="${!empty manganger }">--%>
								<p class="btns isno">
									<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_PAY_SET">开通</a>
								</p>
								<%--</c:if>--%>
						</li>
						</c:if>
						<c:if test="${requestScope.SERVICE_MARKETING_SET == 'SERVICE_MARKETING_SET' }">
						<li class="clear">
		                    <div class="left" style="width: 200px;">
		                        <div>
		                            <i class="icons i12 active disable" style="cursor: pointer;"></i>
		                            <p class="s-name">
		                                <a href="javascript:void(0);">营销工具</a>
		                            </p>
		                        </div>
		
		                    </div>
		                    <div class="right server-infos">
		                        <p class="infos">公众号运营</p>
		                        <p class="infos">在线客服</p>
		                        <p class="infos">QQ运营</p>
		                    </div>
		                    <%--<c:if test="${!empty manganger }">--%>
								<p class="btns isno">
									<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_MARKETING_SET">开通</a>
								</p>
							<%--</c:if>--%>
		                </li>
		                </c:if>
		                <c:if test="${requestScope.SERVICE_MSG_SET == 'SERVICE_MSG_SET' }">
		                <li class="clear" style="display: none;">
							<div class="left" style="width: 200px;">
								<div>
								<i class="icons i15 active disable" style="cursor: pointer;"></i>
									<p class="s-name">
										<a href="javascript:void(0);">短信</a>
									</p>
								</div>
								
							</div>
							<div class="right server-infos">
								<p class="infos">
									<em style="font-size:14px; width: 85px;margin-top: 3px;">
										<c:if test="${cms.messageTotal ==null }">${cms.giveMessage }</c:if>
										<c:if test="${cms.messageTotal !=null }">${cms.messageTotal+cms.giveMessage}</c:if>
									</em>短信总量<i
										class="iconfont ask" title="短信总量">&#xe60f;</i>
								</p>
								<p class="infos">
									<em style="font-size:14px; width: 85px;margin-top: 3px;">${css.messageSend }</em>已使用量<i
										class="iconfont ask" title="已使用短信量">&#xe60f;</i>
								</p>
								<p class="infos">
									<em style="font-size:14px; width: 85px;margin-top: 3px;">${cms.messageTotal - css.messageSend + cms.giveMessage }</em>条剩余
								</p>
							</div>
							<%--<c:if test="${!empty manganger }">--%>
									<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_MSG_SET">开通</a>
									</p>
								<%--</c:if>--%>
						</li>
						</c:if>
						<%-- <c:if test="${requestScope.SERVICE_STUDENT_SET=='SERVICE_STUDENT_SET'}">
							<li class="clear">
			                    <div class="left" style="width: 200px;">
			                        <div>
			                            <i class="icons stud active disable" style="cursor: pointer;"></i>
			                            <p class="s-name">
			                                <a href="javascript:void(0);" style="text-decoration: none;">学员设置</a>
									    </p>
			                        </div>
			                    </div>
			                    <div class="right server-infos">
			                        <p class="infos">登录设置</p>
									<p class="infos" style="width:108px;">注册设置</p>
			                    </div>
									<p class="btns isno">
										<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_STUDENT_SET">开通</a>
									</p>
 			                </li>
                      </c:if> --%>
                      <c:if test="${isArea == '2'}">
	                      <c:if test="${requestScope.SERVICE_CLASS_SET == 'SERVICE_CLASS_SET' }">
								<li class="clear">
									<div class="left" style="width: 200px;">
										<div>
											<i class="icons i7 active disable" style="cursor:pointer;" onclick="forwardClassSet();"></i>
											<p class="s-name">
												<a href="javascript:void(0);" onclick="forwardClassSet();" style="text-decoration: none;">行政班设置</a>
												<c:if test="${!empty manganger }">
													<i class="iconfont btn-colse-ser" style="color:#dddddd;" title="点击关闭服务" data-type="SERVICE_TIKU">&#xe635;</i>
												</c:if>
											</p>
										</div>
									</div>
									<div class="right server-infos">
									</div>
								<%--<c:if test="${!empty manganger }">--%>
										<p class="btns isno">
											<a href="javascript:;" class="btn btn-sm btn-default btn-open-ser" data-type="SERVICE_CLASS_SET">开通</a>
										</p>
								<%--</c:if>--%>
								</li>
							</c:if>
						</c:if>
                 	 </ul>
                 </div>
             </div>
       	 </div>
    </div>
	<!-- 弹层 -->
	<div class="layer clear">
		<div class="title">
		</div>
		<p class="alert-msg">
		</p>
		<p class="text-center">
		</p>
	</div>
	<div class="layer-bg"></div>
	<div class="loading lp-units-loading" style="display:none">
		<p><i></i>加载中,请稍后...</p>
	</div>
	 <shiro:hasPermission name="course_head"> 
	 	<input type="hidden" value="1" id="courseHeadPri"/> 
	 </shiro:hasPermission>
	 <shiro:hasPermission name="netschool_head">
	 	<input type="hidden" value="1" id="netschoolPri"/>   
	 </shiro:hasPermission>
	 <shiro:hasPermission name="netschool_head">
	 	<input type="hidden" value="1" id="studentSet"/>   
	 </shiro:hasPermission>
	 <shiro:hasPermission name="live_head"> 
	 	<input type="hidden" value="1" id="liveServicePri"/> 
	 </shiro:hasPermission>
	 <shiro:hasPermission name="video_head">
	 	<input type="hidden" value="1" id="videoServicePri"/> 
	 </shiro:hasPermission>
	  <shiro:hasPermission name="marketing_head">
	  	<input type="hidden" value="1" id="marketingServicePri"/> 
	  </shiro:hasPermission>
	  <shiro:hasPermission name="sms_head">
	  	<input type="hidden" value="1" id="smsServicePri"/> 
	  </shiro:hasPermission>
	  <shiro:hasPermission name="email_head">
	  	<input type="hidden" value="1" id="emailServicePri"/> 
	  </shiro:hasPermission>
	   <shiro:hasPermission name="openclass_head">
	  	<input type="hidden" value="1" id="openClassServicePri"/> 
	  </shiro:hasPermission>
	  <shiro:hasPermission name="tiku_head">
		<input type="hidden" value="1" id="tikuServicePri"/> 
	</shiro:hasPermission>
	<shiro:hasPermission name="pay_head">
		<input type="hidden" value="1" id="payServicePri"/> 
	</shiro:hasPermission>
	<shiro:hasPermission name="pay_head">
		<input type="hidden" value="1" id="questServicePri"/> 
	</shiro:hasPermission>
	 <shiro:hasPermission name="school_head">
       <input type="hidden" value="1" id="schoolServicePri"/>
     </shiro:hasPermission>
     <shiro:hasPermission name="netschool_head">
	 	<input type="hidden" value="1" id="microSchoolPri"/>   
	 </shiro:hasPermission>
	 <shiro:hasPermission name="student_star">
	 	<input type="hidden" value="1" id="studentAspirationsPri"/>   
	 </shiro:hasPermission>

<input type="hidden" id = "isOpen" value="${sessionScope.isAreaSchool1}">
<script type="text/javascript">
$(function(){
	$selectSubMenu('org_service');
	$("body").addClass('qBox');
});
</script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company/serviceAuth.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company/company-width.js"></script>

<script type="text/javascript">
/* 模块未开通则删除模块显示 */
var modules = $(".open-service").find(".q-ucont");
for (var i = 0; i < modules.length; i++) {
	if($(modules[i]).find(".clear").html().trim()==""){
		$(modules[i]).remove();
	}
}
</script>
</body>
</html>
