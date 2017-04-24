<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>APP基本设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/app/app.css"/>
    <script type="text/javascript" src="<%=rootPath%>/plugins/qrcode/jquery.qrcode-0.12.0.min.js"></script> 
    <script type="text/javascript" src="<%=rootPath%>/plugins/canvasjs.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/clipboard/ZeroClipboard.min.js"></script>
</head>
<body>
<input type="hidden" value="${version }" id="company_version"/>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_appconfig.jsp"></jsp:include>
     <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">基本设置</span>
                </div>
            </div>
            <div class="cont-box">
                <p class="explain">说明：用户首次登录网校时会显示启动图：(尺寸为640*960)</p>
                <div  class="bas-set-itm clear">
                    <span class="l-label fl">启动图：</span>
                    <span class="upload-wrap fl">
                    	<c:choose>
                    		<c:when test="${!empty pic.url }">
                    			 <p class="upload-img" id="show_qd_pic"><img src="${pic.url }" alt=""/></p>
		                        <input type="file" style="display: none;" name="imgData" id="imgData" accept="image/*"  onchange="savePic()"/>
		                        <button class="btn btn-primary" id="imageObject" ids=""  src="">更换</button>
		                        <button class="btn btn-default del_btn_qd" ids="${pic.id }">删除</button>
                    		</c:when>
                    		<c:otherwise>
                    			<p class="upload-img" id="show_qd_pic"></p>
		                        <input type="file" style="display: none;" name="imgData" id="imgData" accept="image/*"  onchange="savePic()"/>
		                        <button class="btn btn-primary" id="imageObject">上传</button>
		                        <button class="btn btn-default del_btn_qd" ids="" style="display: none;">删除</button>
                    		</c:otherwise>
                    	</c:choose>
                       
                    </span>
                </div>
                <div class="bas-set-itm match-color none">
                    <span class="l-label">APP配色方案：</span>
                    <span class="chose-color">
                        <select class="js-example-templating">
                            <option value="blue">蓝色</option>
                            <option value="red">红色</option>
                            <option value="grey">灰色</option>
                            <option value="green">绿色</option>
                        </select>
                    </span>
                    <em style="margin-left: 10px;cursor: pointer">预览</em>
                </div>
                <div  class="bas-set-itm app-set-private">
                    <span class="l-label">是否添加APP快捷入口：</span>
                    <span class="nav-static l-close setConfigLable">
                        <i class="iconfont">&#xe604;</i>
                        <em>禁用</em>
                    </span>
                    <p class="explain">说明：开启后，将在网站首页顶部导航中出现机构上传的二维码，为学员提供下载地址</p>
                    
                    <div class="app-open" style="display: none;">
                        <p class="l-label">APP客户端下载地址二维码：</p>
                         <input type="file" style="display: none;" name="imgDatas" id="imgDatas" accept="image/*"  onchange="saveewmPic();"/>
                        <div class="ew-wrap" id="imageObjects">
                           <img alt="" src="">
                           <span class="alert-mask">点击更换二维码</span> 
                        </div>
                       <div class="download">
                            <span class="l-label">IOS下载地址：</span>
                            <span>
                                <input type="url" placeholder="http://" id="ios_address" class="address" disabled="disabled"/>
                                <button class="btn btn-primary edit_page" mark="ios">编辑</button>
                                <button class="btn btn-default edit_cancle" id="ios_cancle" mark="" style="display: none;">取消</button>
                            </span>
                        </div>
                        <div class="download">
                            <span class="l-label">Android下载地址：</span>
                            <span>
                                <input type="url" placeholder="http://" id="android_address" class="address" disabled="disabled"/>
                                <button class="btn btn-primary edit_page" mark="android">编辑</button>
                                <button class="btn btn-default edit_cancle" id="andorid_cancle" mark="" style="display: none;">取消</button>
                            </span>
                        </div>
                        
                        <p class="l-label">APP宣传页：</p>
                        <div class="chose-address">
                            <input type="radio" name="appPageType" checked="checked" value="0" style="margin-left: 0"/>使用默认地址
                            <input type="radio" name="appPageType" value="1" style="margin-left: 60px"/>使用自定义链接
                        </div>
                        <div class="url-address editUrling" style="display: none;">
                            <span class="l-label">链接地址：</span>
                            <span>
                                <input type="url" placeholder="请输入一个以http://开头的完整路径" id="custom_address" class="address" disabled="disabled"/>
                                <button class="btn btn-primary edit_page" mark="custom">编辑</button>
                                <button class="btn btn-default edit_cancle" id="custom_cancke" mark="" style="display: none;">取消</button>
                            </span>
                        </div>
                       </div> 
                    
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 选择链接框 -->
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenus('app_info');
    	});
 </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/app/appBase.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/app/app-set.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
     <script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>
</body>
</html>