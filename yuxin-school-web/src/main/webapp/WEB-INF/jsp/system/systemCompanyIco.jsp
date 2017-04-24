<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>SEO设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
     <jsp:include page="/WEB-INF/jsp/menu/menu_systemconfig.jsp"></jsp:include>
     <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">SEO设置</span>
                </div>
            </div>
            <div class="pay-box paddres seo_lists">
                <div class="pay-btn c mr-40 index_seo">
                    <p class="pay-way mt-45">首页SEO</p>
                    <div class="yes-btn start mt-30" type="SEO_SET_INDEX">启用</div>
                    <div class="ban-box mt-35">
                        <div class="ban-btn" type="SEO_SET_INDEX">禁用</div>
                        <div class="set-btn" type="SEO_SET_INDEX">设置</div>
                    </div>
                </div>
                <div class="pay-btn c mr-40 course_seo">
                    <p class="pay-way mt-45">课程列表SEO</p>
                    <div class="yes-btn start mt-30" type="SEO_SET_COURSE">启用</div>
                    <div class="ban-box mt-35">
                        <div class="ban-btn" type="SEO_SET_COURSE">禁用</div>
                        <div class="set-btn" type="SEO_SET_COURSE">设置</div>
                    </div>
                </div>
                <div class="pay-btn c mr-40 openclass_seo">
                    <p class="pay-way mt-45">公开课列表SEO</p>
                    <div class="yes-btn start mt-30" type="SEO_SET_OPENCLASS">启用</div>
                    <div class="ban-box mt-35">
                        <div class="ban-btn" type="SEO_SET_OPENCLASS">禁用</div>
                        <div class="set-btn" type="SEO_SET_OPENCLASS">设置</div>
                    </div>
                </div>
                <div class="pay-btn c mr-40 news_seo">
                    <p class="pay-way mt-45">新闻列表SEO</p>
                    <div class="yes-btn start mt-30" type="SEO_SET_NEWS">启用</div>
                    <div class="ban-box mt-35">
                        <div class="ban-btn" type="SEO_SET_NEWS">禁用</div>
                        <div class="set-btn" type="SEO_SET_NEWS">设置</div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('org_service');
    		$selectSubMenus('system_seo');
    	});
    </script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/system/systemCompanyIco.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
   	<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
</body>
</html>