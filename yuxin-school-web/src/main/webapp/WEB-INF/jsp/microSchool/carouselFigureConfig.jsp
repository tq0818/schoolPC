<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
	<title>轮播图设置</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/minitip.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/enjoyhint/jquery.enjoyhint.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/sidebar/sidebar.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/system.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/splitscreen.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/utils.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap company overflow">
		<jsp:include page="/WEB-INF/jsp/menu/menu_microschool.jsp"></jsp:include>
		<div class="right-side wxRight-side">
        <div class="u-wrap set-system">
            <div class="mainbackground space">
                <input type="hidden" id="schoolId">
                <div class="title single">
                    <h2 class="h6 fl">轮播图设置</h2>
                    <c:choose>
                    	<c:when test="${status==1}">
                    	 <div class="addbtn" >
	                        <a href="javascript:;" class="btn btn-primary" id="addfocus">
	                            <!--<em class="iconfont"></em>-->
	                            添加一张轮播
	                        </a>
	                    </div>
                    	</c:when>
                    	<c:otherwise>
                    	 <div class="addbtn" style="display: none;">
	                        <a href="javascript:;" class="btn btn-primary" id="addfocus">
	                            <!--<em class="iconfont"></em>-->
	                            添加一张轮播
	                        </a>
	                    </div>
                    	</c:otherwise>
                    </c:choose>
                   
                </div>
                <form class="ChoiceYN">
                    <span>
                        是否与网校同用一套轮播图：
                    </span>
                    <span>
                    <c:choose>
                    	<c:when test="${status==1 }">
                    		<label for="">
                            	<input type="radio" name="yesOrno" class="yesOrno" value="0" >是
	                        </label>
	                        <label for="">
	                            <input type="radio" name="yesOrno" class="yesOrno" value="1" checked="checked" >否
	                        </label>
                    	</c:when>
                    	<c:otherwise>
                    		<label for="">
                            	<input type="radio" name="yesOrno" class="yesOrno" value="0" checked="checked">是
	                        </label>
	                        <label for="">
	                            <input type="radio" name="yesOrno" class="yesOrno" value="1" >否
	                        </label>
                    	</c:otherwise>
                    </c:choose>
                        
                    </span>
                </form>
                <c:choose>
                    	<c:when test="${status==1 }">
                    		<div class="set-focus">
                    	</c:when>
                    	<c:otherwise>
                    		<div class="set-focus" style="display: none;">
                    	</c:otherwise>
                </c:choose>
                
                    <ul id="cycleDataLists" class="wxCycleDataLists">
                        <!-- 新增幻灯 -->
                        <li class="add-focus" style="display: none;">
                            <div class="focus-title" style="font-size: 16px; color:#333; line-height: 16px;">新的轮播图</div>
                            <div class="fileupload fileupload-new focus-content clear " data-provides="fileupload">
                                <div class="left" id="leftCont">
                                    <p class="tips-msg">上传图片尺寸为750*375 <a class="c-uf"href="javascript:;">点击上传</a><input type="file" id="imgDatas" name="imgData" accept="image/*" onchange="changeCyclePic('so',this);" class="up shangchuan"></p>
                                    <img id="imgObject" ids="" src="" alt="" style="visibility: hidden;">
                                </div>
                                <div class="right" >
                                    <div class="title">配置信息</div>
                                    <div class="set-infos">
                                        <p>
                                            <span class="t">上传图片：</span>
                                    <span class="btns">
                                     <input type="file" class="u-f" id="imgData" name="imgData" onchange="changeCyclePic('st',this);" accept="image/*">
                                        <a href="javascript:;" class="btn btn-default btn-mini c-uf">上传图片</a>
                                    </span>
                                            <span style="color:red;">上传图片尺寸为750*375</span>
                                        </p>
                                        <p><span class="t">链接地址：</span>
                                            <label for="cycleUrl" class="inputPosi">
                                                <span>http://</span>
                                                <input type="text" id="cycleUrl" class="url" value="" placeholder="请输入完整的网页地址">
                                                <small class="express">已适配到微信端的功能链接：课程、课程包</small>
                                            </label>
                                        </p>
                                        <p><span class="t">是否启用：</span><i class="iconfont normal open"></i><span id="addStatus" class="i open">已启用</span></p>
                                        <p>
                                            <span class="actionBut">
                                                <a href="javascript:clearData();" class="btn btn-default btn-cancel-add">取消添加</a>
                                                <a href="javascript:addCyclePic();" class="btn btn-gray btn-submit">保存设置</a>
                                            </span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <!-- 新增结束 -->
                       
                    </ul>
                </div>
            </div>
        </div>
    </div>
	</div>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<%-- 	<script type="text/javascript" src="<%=rootPath%>/javascripts/microSchool/carouselFigureConfig.js"></script> --%>
   <script type="text/javascript" src="<%=rootPath %>/javascripts/microSchool/miro-sys-focus.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/microSchool/miro-config-cycle.js"></script>
	<script type="text/javascript">
		$selectSubMenus('microSchool_carouselFigureConfig');
	</script>
</body>
</html>