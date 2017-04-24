<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>开通服务</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/minitip.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage-screen.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/school-manage.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company-service.js"></script>

<script type="text/javascript" src="<%=rootPath%>/javascripts/company/openSchool.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/addClassTypePic.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/addClassTypeOnsale.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
<script type="text/javascript">
	$(function(){
		$selectSubMenu('org_service');
	});
</script>
<style type="text/css">
.iconfont {
	position: relative;
	top: 2px;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	line-height: 1;
	margin-right:5px
}
.i{
	margin-right:10px
}
.iconfont.open {
	color: #1b6fbd
}

.iconfont.closed {
	color: #666
}
.p1 {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -280px;
}

.p2 {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -280px;
}

.p3 {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -280px;
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
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow manage-screen">
	<div class="left-side">
		<div class="left-side-title">
	    	<em>分校管理</em>
	    	<span class="iconfont return-pic hcancle" style="position: absolute;top: 8px;" onclick="javascript:location.href='<%=rootPath%>/company/companyService'">&#xe650;</span>
	    </div>
		<ul>
			<li class="subentry active">分校设置</li>
		</ul>
	</div>
	<div class="right-side">
	<div class="title-box">
                <div class="tit-font">
                    <span class="t">分校设置</span>
                </div>
            </div>
    <div class="mainbackground u-content clear">
        <div class="full-wrap buy-box">
            <div class="buy-title">
                <div class="place-btns">
                    <a href="javascript:;" class="btn btn-primary btn-more">申请更多分校</a>
                    <span class="c-title">已开分校&nbsp;&nbsp;&nbsp;&nbsp;${css.schoolNum }/${cms.schoolTotal }</span>
                </div>
                <div class="add-btns">
                    <a href="javascript:;" class="btn btn-primary btn-add"><em class="iconfont">&#xe606;</em>添加分校</a>
                </div>
            </div>
            <div class="school">
                <ul>
                <c:forEach var="s" items="${scsList }">
                	<li data-id="${s.id }">
                        <div class="sc-title set-focus">
                            <h2 class="h5"><span class="header">
                            <c:if test="${fn:length(s.schoolName) > 7 }">
                            	${fn:substring(s.schoolName,0,7) }...
                            </c:if>
                            <c:if test="${fn:length(s.schoolName) <= 7 }">
                            	${s.schoolName }
                            </c:if>
                            </span><em>已招生： 人</em>
                            	<em>
                            		<a href="javascript:;" class="btn btn-default btn-update" style="float:right;" data-id="${s.id }">修改</a>
                            	</em>
                            	<em>
                            		<span class="btn-status" style="float:right;" data-id="${s.id }" data-status="${s.delFlag }">
                            			<c:if test="${s.delFlag == 0 }"><i class="iconfont normal open">&#xe603;</i><span id="addStatus" class="i open">已启用</span></c:if>
                            			<c:if test="${s.delFlag == 1 }"><i class="iconfont normal closed">&#xe604;</i><span id="addStatus" class="i closed">已停用</span></c:if>
                            		</span>
                            	</em>
                            </h2>
                        </div>
                        <div class="sc-list">
                            <p class="c">
                                <span class="c-title">建校时间</span>
                                <span class="c-content">
                                	<fmt:formatDate value="${s.createTime }" pattern="yyyy-MM-dd"/>
                                </span>
                            </p>
                            <p class="c">
                                <span class="c-title">学科数</span>
                                <span class="c-content">0</span> 个
                            </p>
                            <p class="c">
                                <span class="c-title">老师数</span>
                                <span class="c-content">0</span> 人
                            </p>
                            <p class="c">
                                <span class="c-title">教务人员</span>
                                <span class="c-content">0</span> 人
                            </p>
                            <p class="c">
                                <span class="c-title">校区</span>
                                <span class="c-content">0</span> 个
                            </p>
                            <p class="c">
                                <span class="c-title">线下教室</span>
                                <span class="c-content">0</span> 间
                            </p>
                        </div>
                    </li>
                </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    </div>
    <div class="screen-right" style="display: none;">
    	
    </div>
</div>
<!-- 弹层 -->
	<div class="layer clear">
		<div class="title">
		</div>
		<p class="alert-msg">
			申请已经提交，请等候通知，我们会在24小时内处理完,请耐心等待！如有问题请联系本公司.....
		</p>
		<p class="text-center">
			<a href='javascript:;' class='btn btn-primary'>确定</a>
		</p>
	</div>
	<div class="layer-bg"></div>
<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->

<div class="upload-layer none" id="chooseDiv" style="width:1080px;">
    <div class="upload-title">
        <h2 class="h5">上传课程封面</h2>
        <i class="iconfont close">&#xe610;</i>
    </div>
    <div class="types">
        <a href="javascript:queryConditionPics('');" marks="privatePic" class="btn btn-default btn-success">私有云</a>
        <a href="javascript:queryConditionPics('');" marks="publicPic" class="btn btn-default">公有云</a>
    </div>
    <div class="types-list">
        <a href="javascript:queryAll(1,'');" class="btn btn-mini btn-link active">全部</a>
    </div>
    <div id="tlist">
	   
    </div>
    <div class="pic-upload none">
        <p class="tips">
         <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic()" value="重新选择文件"/>
          	<!--<a href="javascript:;" class="btn btn-mini btn-primary">重新选择文件</a>--> <span>建议上传的图片尺寸为：516*282px </span> 
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
            <div class="upload-sm p2" >
            	<div class="preview-container">
                <img src="">
                </div>
            </div>
            <div class="upload-mini p3">
           		<div class="preview-container">
                <img src="">
                </div>
            </div>
        </div>
        <p class="text-center">
            <a href="javascript:classTypePic();" class="btn btn-primary">确定</a>
            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
        </p>
    </div>
</div>
<div class="add-layer-bg none" id="stopDiv"></div>
</body>
</html>