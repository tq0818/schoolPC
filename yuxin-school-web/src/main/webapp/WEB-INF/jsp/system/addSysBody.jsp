<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页页面设置</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/web-index.css" />
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system/homeDefault.css">
    
    <style type="text/css">
.p1 {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -280px;
/*	padding: 6px;
 	border: 1px rgba(0, 0, 0, .4) solid;
	background-color: white;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2); */
}

.p2 {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -280px;
/*	padding: 6px;
 	border: 1px rgba(0, 0, 0, .4) solid;
	background-color: white;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2); */
}

.p3 {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -280px;
/* 	padding: 6px;
	border: 1px rgba(0, 0, 0, .4) solid;
	background-color: white;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2); */
}

.p1 .preview-container {
	width: 446px;
	height: 241px;
	overflow: hidden;
}

.p2 .preview-container {
	width: 255px;
	height: 138px;
	overflow: hidden;
}

.p3 .preview-container {
	width: 181px;
	height: 96px;
	overflow: hidden;
}
</style>
<script type="text/javascript">
	$(function(){
		$selectSubMenu('org_service');
	});
</script>
</head>
<body class="homeBox">
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>

<!-- 二级导航结束 -->
<div class="u-wrap set-system">
<div id="attributes" class="none">
	<input type="hidden" id="schoolId" name="schoolId" value="${template.schoolId}"/>
	<input type="hidden" id="templateId" name="templateId" value="${template.id}"/>
	<input type="hidden" id="templateName" name="templateName" value="${template.templateName}"/>
</div>
    <div class="mainbackground">
        <div class="title"><h2 class="h6">模板选择</h2></div>
        <div class="set-body select-themes clear">
            <div class="box b1" data-index="b1">
                <div class="c">
                    <h5>单图课程</h5>
                </div>                
            </div>
            <div class="box b2" data-index="b2">
                <div class="c">
                    <h5>双图课程</h5>
                </div>
            </div>
            <div class="box b3" data-index="b3">
                <div class="c">
                    <h5>三图课程</h5>
                </div>
            </div>
            <div class="box b4" data-index="b4">
                <div class="c">
                    <h5>四图课程</h5>
                </div>
            </div>
            <div class="box b5" data-index="b5">
                <div class="c">
                    <h5>五图课程</h5>
                </div>
            </div>
            <div class="box b6" data-index="b6">
                <div class="c">
                    <h5>自定义内容</h5>
                </div>
            </div>
            <div class="box b7" data-index="b7">
                <div class="c">
                    <h5>八图课程</h5>
                </div>
            </div>
            <div class="box b8" data-index="b8">
                <div class="c">
                    <h5>新闻列表</h5>
                </div>
            </div>
            <div class="box b9">
                <div class="c">
                    <h5>更多模板陆续添加中...</h5>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="u-wrap set-system">
    <div class="mainbackground bodys">
   		 <div class="sys-i">
        	<div class="title"><h2 class="h6">模版名称：<b class="templatename"></b></h2></div>
        	<c:if test="${template.templateStatus==1}">
        	<div class="submit-btn">
        		<a href="javascript:;" class="btn btn-primary goback">返  回</a>
                <a href="javascript:;" class="btn btn-primary publish">发布生效</a>
            </div>
            </c:if>
        </div>
        <div class="set-body" id="set-body">
        
        </div>
    </div>
</div>

<div class="upload-layer none" id="chooseDiv" style="width:1080px;">
    <div class="upload-title">
        <h2 class="h5">上传课程封面</h2>
        <i class="iconfont close">&#xe610;</i>
    </div>
    <div class="types">
        <a href="javascript:;" marks="privatePic" class="btn btn-default btn-success">私有云</a>
        <a href="javascript:;" marks="publicPic" class="btn btn-default">公有云</a>
    </div>
    <div class="types-list">
        <a href="javascript:;" class="btn btn-mini btn-link active">全部</a>
    </div>
    <div id="tlist">
	   
    </div>
    <div class="pic-upload none">
        <p class="tips">
         <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgData" onchange="savePic()" value="重新选择文件"/>
          	<!--<a href="javascript:;" class="btn btn-mini btn-primary">重新选择文件</a>--> <span>建议上传的图片尺寸为：516*282 </span> 
        </p>
        <div class="upload-content" style="padding:10px;">
        <div class="attributes none">
        	 <input type="hidden" id="x" name="x" value="0"/>
            <input type="hidden" id="y" name="y" value="0"/>
            <input type="hidden" id="w" name="w" value="0"/>
            <input type="hidden" id="h" name="h" value="0"/>
            <input type="hidden" id="x2" name="x2" value="0"/>
            <input type="hidden" id="y2" name="y2" value="0"/>
        </div>
        	<div class="pic" style="width:516px;height:282px;background-color:#f2f2f2;">
        		 <img id="target" src="" />
            </div>
            <div class="upload-big p1" style="width:456px;">
            	<div class="preview-container" style="margin:0 auto;">
                <img src="">
                </div>
            </div>
            <div class="upload-sm p2" style="left:540px;">
            	<div class="preview-container">
                <img src="">
                </div>
            </div>
            <div class="upload-mini p3" style="left:805px;">
           		<div class="preview-container">
                <img src="">
                </div>
            </div>
        </div>
        <p class="text-center">
            <a href="javascript:;" class="btn btn-primary btn-ok">确定</a>
            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
        </p>
    </div>
</div>
<div class="add-layer-bg none" id="stopDiv"></div>
<script type="text/javascript" src="<%=rootPath%>/plugins/jquery.scrollTo-2.1.0/jquery.scrollTo.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/addClassTypeOnsale.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/system/ModelUtil.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/system/body_divs.js?v=1.0"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/system/addSysBody.js?v=1.0"></script>



</body>
</html>