<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
	<title>导航配置</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/minitip.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/system.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/splitscreen.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage-screen.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/footer.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/utils.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/pagehead.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/footSet.css">
   	<style type="text/css">
   		.HeadNameInput{width: 110px; }
   	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap company overflow">
		<jsp:include page="/WEB-INF/jsp/menu/menu_microschool.jsp"></jsp:include>
		<div class="right-side wxRight-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">导航配置</span>
                </div>
            </div>
            <div class="footerSetBox">
                <h4>说明：可通过开启和禁用来控制此功能是否在微官网进行显示和使用。</h4>
                <div>
                    <div class="set-system ui-sortable" id="navbarconfigs">
                        <div class="section n true" data-service="SERVICE_CLASS" data-sort="1" id="SERVICE_CLASS">
                            <div class="block l-menu">
                                <div class="title-wrap">
                                    <ul class="row clear">
                                        <li class="with13">
                                            <span class="title-info in2304 headName"><span class="HeadName">课程</span><input class="HeadNameInput none" type="text" value="课程"/></span>
                                            
                                        </li>
                                        <li class="with10">
                                            <a href="javascript:void(0);" class="headStatus" data-service="SERVICE_CLASS" style="text-decoration: none;">
                                                <i class="iconfont normal open" >&#xe603;</i>
												<span class="open">已启用</span>
                                            </a>
                                        </li>
                                        <li class="with70 link-address"></li>
                                        <li class="with7">
                                            <i class="iconfont edit-icon mar-lr5" data-service="SERVICE_CLASS"></i>
                                            <i class="iconfont nav-name-icon" style="cursor: move;"> </i>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="section n true" data-service="SERVICE_CLASS_VIDEO" data-sort="2" id="SERVICE_CLASS_VIDEO">
                            <div class="block l-menu">
                                <div class="title-wrap">
                                    <ul class="row clear">
                                        <li class="with13">
                                            <span class="title-info in2304 headName"><span class="HeadName">点播课程</span><input class="HeadNameInput none" type="text" value="点播课程"/></span>

                                        </li>
                                        <li class="with10">
                                            <a href="javascript:void(0);" class="headStatus" data-service="SERVICE_CLASS_VIDEO" style="text-decoration: none;">
                                                <i class="iconfont normal open" >&#xe603;</i>
												<span class="open">已启用</span>
                                            </a>
                                        </li>
                                        <li class="with70 link-address"></li>
                                        <li class="with7">
                                            <i class="iconfont edit-icon mar-lr5" data-service="SERVICE_CLASS_VIDEO"></i>
                                            <i class="iconfont nav-name-icon" style="cursor: move;"> </i>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                       <%-- <div class="section n true" data-service="SERVICE_CLASS_PACKAGE" data-sort="3" id="SERVICE_CLASS_PACKAGE">
                            <div class="block l-menu">
                                <div class="title-wrap">
                                    <ul class="row clear">
                                        <li class="with13">
                                            <span class="title-info in2304 headName"><span class="HeadName">课程包</span><input class="HeadNameInput none" type="text" value="课程包"/></span>
                                        </li>
                                        <li class="with10">
                                            <a href="javascript:void(0);"  class="headStatus" data-service="SERVICE_CLASS_PACKAGE" style="text-decoration: none;">
                                            <c:choose>
                                            	<c:when test="${SERVICE_CLASS_PACKAGE == 1 }">
                                            	 	<i class="iconfont normal open">&#xe603;</i>
													<span class="open">已启用</span>
                                            	</c:when>
                                            	<c:otherwise>
                                            	 	<i class="iconfont normal close" style="color: #999999;">&#xe604;</i>
													<span class="close">已禁用</span>
                                            	</c:otherwise>
                                            </c:choose>
                                            </a>
                                        </li>
                                        <li class="with70 link-address"></li>

                                        <li class="with7">
                                            <i class="iconfont edit-icon mar-lr5" data-service="SERVICE_CLASS_PACKAGE"></i>
                                            <i class="iconfont nav-name-icon" style="cursor: move;"> </i>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                       
                        <div class="section n true" data-service="SERVICE_OPENCLASS" data-sort="4" id="SERVICE_OPENCLASS">
                            <div class="block l-menu">
                                <div class="title-wrap">
                                    <ul class="row clear">
                                        <li class="with13">
                                            <span class="title-info in2304 headName"><span class="HeadName">公开课</span><input class="HeadNameInput none" type="text" value="公开课"/></span>
                                        </li>
                                        <li class="with10">
                                            <a href="javascript:void(0);"  class="headStatus" data-service="SERVICE_OPENCLASS" style="text-decoration: none;">
                                            <c:choose>
                                            	<c:when test="${SERVICE_OPENCLASS == 1 }">
                                            	 	<i class="iconfont normal open">&#xe603;</i>
													<span class="open">已启用</span>
                                            	</c:when>
                                            	<c:otherwise>
                                            	 	<i class="iconfont normal close" style="color: #999999;">&#xe604;</i>
													<span class="close">已禁用</span>
                                            	</c:otherwise>
                                            </c:choose>
                                            </a>
                                        </li>
                                        <li class="with70 link-address"></li>

                                        <li class="with7">
                                            <i class="iconfont edit-icon mar-lr5" data-service="SERVICE_OPENCLASS"></i>
                                            <i class="iconfont nav-name-icon" style="cursor: move;"> </i>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>--%>
                        <c:if test="${sessionScope.isAreaSchool1 eq 0}">
                        <div class="section n true" data-service="SERVICE_TEACHER" data-sort="5" id="SERVICE_TEACHER">
                            <div class="block l-menu">
                                <div class="title-wrap">
                                    <ul class="row clear">
                                        <li class="with13">
                                            <span class="title-info in2304 headName"><span class="HeadName">名师</span><input class="HeadNameInput none" type="text" value="名师"/></span>
                                        </li>
                                        <li class="with10">
                                            <a href="javascript:void(0);"  class="headStatus" data-service="SERVICE_TEACHER" style="text-decoration: none;">
                                            <c:choose>
                                            	<c:when test="${SERVICE_TEACHER == 1 }">
                                            	 	<i class="iconfont normal open">&#xe603;</i>
													<span class="open">已启用</span>
                                            	</c:when>
                                            	<c:otherwise>
                                            	 	<i class="iconfont normal close" style="color: #999999;">&#xe604;</i>
													<span class="close">已禁用</span>
                                            	</c:otherwise>
                                            </c:choose>
                                            </a>
                                        </li>
                                        <li class="with70 link-address"></li>

                                        <li class="with7">
                                            <i class="iconfont edit-icon mar-lr5" data-service="SERVICE_TEACHER"></i>
                                            <i class="iconfont nav-name-icon" style="cursor: move;"> </i>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        </c:if>
                       <%--  <div class="section n true" data-service="SERVICE_TIKU" data-sort="6" id="SERVICE_TIKU">
                            <div class="block l-menu">
                                <div class="title-wrap">
                                    <ul class="row clear">
                                        <li class="with13">
                                            <span class="title-info in2304 headName"><span class="HeadName">题库</span><input class="HeadNameInput none" type="text" value="题库"/></span>
                                        </li>
                                        <li class="with10">
                                            <a href="javascript:void(0);"  class="headStatus" data-service="SERVICE_TIKU" style="text-decoration: none;">
											<c:choose>
                                            	<c:when test="${SERVICE_TIKU == 1 }">
                                            	 	<i class="iconfont normal open">&#xe603;</i>
													<span class="open">已启用</span>
                                            	</c:when>
                                            	<c:otherwise>
                                            	 	<i class="iconfont normal close" style="color: #999999;">&#xe604;</i>
													<span class="close">已禁用</span>
                                            	</c:otherwise>
                                            </c:choose>
                                            </a>
                                        </li>
                                        <li class="with70 link-address"></li>

                                        <li class="with7">
                                            <i class="iconfont edit-icon mar-lr5" data-service="SERVICE_TIKU"></i>
                                            <i class="iconfont nav-name-icon" style="cursor: move;"> </i>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div> --%>
                        <div class="section n true" data-service="SERVICE_QUESTION_ANSWER" data-sort="7" id="SERVICE_QUESTION_ANSWER">
                            <div class="block l-menu">
                                <div class="title-wrap">
                                    <ul class="row clear">
                                        <li class="with13">
                                            <span class="title-info in2304 headName"><span class="HeadName">问答</span><input class="HeadNameInput none" type="text" value="问答"/></span>
                                        </li>
                                        <li class="with10">
                                            <a href="javascript:void(0);"  class="headStatus" data-service="SERVICE_QUESTION_ANSWER" style="text-decoration: none;">
                                            <c:choose>
                                            	<c:when test="${SERVICE_QUESTION_ANSWER == 1 }">
                                            	 	<i class="iconfont normal open">&#xe603;</i>
													<span class="open">已启用</span>
                                            	</c:when>
                                            	<c:otherwise>
                                            	 	<i class="iconfont normal close" style="color: #999999;">&#xe604;</i>
													<span class="close">已禁用</span>
                                            	</c:otherwise>
                                            </c:choose>
                                            </a>
                                        </li>
                                        <li class="with70 link-address"></li>

                                        <li class="with7">
                                            <i class="iconfont edit-icon mar-lr5" data-service="SERVICE_QUESTION_ANSWER"></i>
                                            <i class="iconfont nav-name-icon" style="cursor: move;"> </i>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                       <%-- <div class="section n true" data-service="SERVICE_MEMBER" data-sort="8" id="SERVICE_MEMBER">
                            <div class="block l-menu">
                                <div class="title-wrap">
                                    <ul class="row clear">
                                        <li class="with13">
                                            <span class="title-info in2304 headName"><span class="HeadName">会员专区</span><input class="HeadNameInput none" type="text" value="会员专区"/></span>
                                        </li>
                                        <li class="with10">
                                            <a href="javascript:void(0);" class="headStatus" data-service="SERVICE_MEMBER" style="text-decoration: none;">
                                            <c:choose>
                                            	<c:when test="${SERVICE_MEMBER == 1 }">
                                            	 	<i class="iconfont normal open">&#xe603;</i>
													<span class="open">已启用</span>
                                            	</c:when>
                                            	<c:otherwise>
                                            	 	<i class="iconfont normal close" style="color: #999999;">&#xe604;</i>
													<span class="close">已禁用</span>
                                            	</c:otherwise>
                                            </c:choose>
                                            </a>
                                        </li>
                                        <li class="with70 link-address"></li>

                                        <li class="with7">
                                            <i class="iconfont edit-icon mar-lr5" data-service="SERVICE_MEMBER"></i>
                                            <i class="iconfont nav-name-icon" style="cursor: move;"> </i>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>--%>
                       
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
	</div>
	<div class="layerTips Confirm none" style="margin-left: -210px; margin-top: -103px;">
	<div class="layerTipsTitle">编辑导航名称<i class="Close iconfont Confirm_Close"></i></div>
	<div class="layerTipsContent"><div class="gNchoice"><label for=""><i>功能名称：</i><input type="text" class="updateName" maxlength="4"></label>  </div></div>
	<div class="layerTipsBtns"><a href="javascript:;" class="btn btn-mini btn-default Confirm_Cancle">取消</a> <a href="javascript:;" class="btn btn-mini btn-success Confirm_Ok">确定</a></div>
	</div>
	<div class="layerTipsBg Confirm" style="display: none;"></div>
	<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<script type="text/javascript">
		$selectSubMenus('microSchool_navigationConfig');
	</script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
                         
  	<script type="text/javascript" src="<%=rootPath%>/javascripts/microSchool/navigationConfig.js"></script>
</body>
</html>