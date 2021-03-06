<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>统计分析代码</title>
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
                    <p class="t">
                        <em>网站统计分析代码部署</em>
                        <em class="iconfont normal close">&#xe604;</em>
                        <span id="" class="i close">已禁用</span>
                    </p>
                </div>
            </div>
            <div class="code-select">
                <span>统计分析代码：CNZZ</span>
               <%-- <select id="tg_Type">
                    <option value="google">谷歌分析</option>
                    <option value="baidu">百度统计</option>
                    <option value="tencent">腾讯分析</option>
                    <option value="cnzz" selected="selected">cnzz</option>
                </select>--%>
                <span class="input-tit">id =</span>
                <input type="text" id="tg_Code" />
                <div class="codesive-btn">保存</div>
                <div class="codecancel-btn">取消</div>
            </div>
            <p class="labell">注：请将如图所示标注部分输入编辑框中</p>
            <div class="code-show-box">
                <div class="code-left">代码预览：</div>
               <%-- <div class="code-show sh1 chosed">

                </div>
                <div class="code-show sh2">

                </div>
                <div class="code-show sh3">

                </div>--%>
                <div class="code-show sh">

                </div>
            </div>
        </div>
    </div>
</div>
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('org_service');
    		$selectSubMenus('system_code');
    	});
        var _sh4 = "&lt;script type=\"text/javascript\"&gt;<br/>"+
            "	var cnzz_protocol = ((\"https:\" == document.location.protocol) ? \" https://\" : \" http://\");<br/>"+
            "	document.write(unescape(\"%3Cspan id=\'cnzz_stat_icon_<span class=\"input-show\">1257041520</span>\'%3E%3C/span%3E%3Cscript src=\'\" + cnzz_protocol + \"s11.cnzz.com/z_stat.php%3Fid%3D<span class=\"input-show\">1257041520</span>\' type=\'text/javascript\'%3E%3C/script%3E\"));<br/>"+
            "&lt;/script&gt;";
        $(".sh").show().addClass("chosed").append(_sh4);
    </script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/system/systemCompanyIco.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
   	<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
</body>
</html>