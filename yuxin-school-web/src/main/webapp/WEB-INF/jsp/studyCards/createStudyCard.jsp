<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
	<title>学习卡</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/splitscreen.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/studyCard/stuCard.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/utils.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	 <div class="u-wrap company overflow">
        <jsp:include page="/WEB-INF/jsp/menu/menu_studycard.jsp"></jsp:include>
        <div class="right-side proxyManage">
            <div>
                <div class="title-box">
                    <div class="tit-font clear">
                        <span class="t">创建学习卡</span>
                        <a href="javascript:;" class="btn proxyBtn url-button" data-url="<%=rootPath %>/companyStudyCard/gotoStudyCardsManage">返回</a>
                    </div>
                </div>
                <div class="proxyBox">
                    <form action="">
                        <ul class="addproxy">
                            <li class="clear">
                                <h4 class="fl"><i>*</i>学习卡名称 :</h4>
                                <div class="fl">
                                    <input type="text" placeholder="这里是学习卡名称" class="" id="studyCard_name" maxlength="10">
                                </div>
                            </li>
                            <li class="clear">
                                <h4 class="fl"><i>*</i>学习卡内容 :</h4>
                                <div class="fl coursePage">
                                    <p class="choiName">
                                        <input id="rad1" type="radio" name="rad" checked="checked"  class="radone" data-type="class"/> <label for="rad1">课程</label>
                                        <c:choose>
                                        	<c:when test="${classPackageflag == 1 }">
                                        	 	 <input  id="rad2" type="radio" name="rad" class="radtwo" data-type="classPackage"/><label for="rad2">课程包</label>
                                       			 <b>学习卡内不可同时包含课程与课程包</b>
                                        	</c:when>
                                        	<c:otherwise>
                                        	</c:otherwise>
                                        </c:choose>
                                       
                                    </p>
                                    <p class="choice">
                                        <a href="javascripts:void();" onclick="return false;" class="studyCard-choose">选择内容</a>
                                   	 	<span>已选择<em class="classNum">0</em>个<b class="choose-type-commodity">课程</b></span>
                                    </p>
                                </div>
                            </li>
                            <li class="clear">
                                <h4 class="fl"><i>*</i>学习卡数量 :</h4>
                                <div class="fl percent">
                                    <input type="text" id="studyCard_totalNum" value="" maxlength="11"><span>个</span>
                                    <p>一次最多只能生成1000个学习卡</p>
                                </div>
                            </li>
                            <li class="clear">
                                <h4 class="fl"><i>*</i>使用期限 :</h4>
                                <div class="fl percent time-percent">
                                    <input type="text" class="date-picker" readonly="readonly" id="start">
                                    <span>至 </span>
                                    <input type="text" readonly="readonly" class="date-picker" id="end">
                                    <input type="checkbox" class="studyCard-alltime" id="forever"/><label for="forever">永久有效</label>
                                    <p>开始时间不早于当天，结束时间不能早于开始时间</p>
                                </div>
                            </li>
                            <li class="clear">
                                <h4 class="fl">代理机构 :</h4>
                                <div class="fl selbox">
                                    <p class="select">
                                    	<span class="hasProxy">
	                                        <input class="allsel proxySelect" id="allsel" type="checkbox" name="allsel" data-type="allsel"><label for="allsel">全选</label>
	                                        <input class="nosel proxySelect" id="nosel" type="checkbox" name="nosel" data-type="nosel"><label for="nosel">全不选</label>
                                        </span>
                                        <a class="addagent addProxyOrg" onclick="return false;" href="javascripts:;">添加代理机构</a>
                                    </p>
                                    <dl class="allAgent clear proxyOrgs">
                                    </dl>
                                </div>
                            </li>
                            <li class="clear">
                                <h4 class="fl"><i>*</i>学习码前缀 :</h4>
                                <div class="fl percent">
                                    <input type="text" id="studyCard_prefix" value="" maxlength="4">
                                    <p>只能是英文字母A-Z，限4个字母，不区分大小写，不可与其他学习码前缀重复</p>
                                </div>
                            </li>
                            <li class="clear">
                                <h4 class="fl">学习卡价格 :</h4>
                                <div class="fl percent">
                                    <input type="text" id="studyCard_price" value="" /><span>元</span>
                                </div>
                            </li>
                            <li class="clear">
                                <h4 class="fl">学习卡说明 :</h4>
                                <div class="fl">
                                    <textarea name="" id="studyCard_description" class="detailAddress" maxlength="400"></textarea>
                                </div>
                            </li>
                        </ul>
                        <div class="optionBut">
                            <input type="button" value="取消" class="cancel studyCard-cancel url-button" data-url="<%=rootPath %>/companyStudyCard/gotoStudyCardsManage">
                            <input type="button" value="保存" class="save studyCard-save">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="loading-bg layerTipsBg lp-units-loading-bg" style="display:none"></div>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-pagination/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/studyCards/createStudyCard.js"></script>
</body>
</html>