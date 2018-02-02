<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>首页配置</title>
    <script type="text/javascript">
    	var rp='<%=rootPath %>';
    	var theme = "${set.content }";
    </script>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/pageafter.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/pagehead.css"/>
    <c:choose>
    	<c:when test="${'header-blue' == set.content }">
    		<link rel="stylesheet" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/header/header-blue.css" id="header-theme"/>
    	</c:when>
    	<c:when test="${'header-cyan' == set.content }">
    		<link rel="stylesheet" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/header/header-cyan.css" id="header-theme"/>
    	</c:when>
    	<c:when test="${'header-gray' == set.content }">
    		<link rel="stylesheet" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/header/header-gray.css" id="header-theme"/>
    	</c:when>
    	<c:when test="${'header-green' == set.content }">
    		<link rel="stylesheet" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/header/header-green.css" id="header-theme"/>
    	</c:when>
    	<c:when test="${'header-orange' == set.content }">
    		<link rel="stylesheet" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/header/header-orange.css" id="header-theme"/>
    	</c:when>
    	<c:when test="${'header-red' == set.content }">
    		<link rel="stylesheet" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/header/header-red.css" id="header-theme"/>
    	</c:when>
    	<c:when test="${'header-white' == set.content }">
    		<link rel="stylesheet" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/header/header-white.css" id="header-theme"/>
    	</c:when>
    	<c:otherwise>
    		<link rel="stylesheet" href="<%=rootPath %>/stylesheets/themesdiy/default-themes/header/header-default.css" id="header-theme"/>
    	</c:otherwise>
    </c:choose>
    <style type="text/css">
    	.ui-state-highlight{
    		height: 30px;
    		border: dashed 1px #ced7fd;
    		background: #adbdfc; 
    	}
    </style>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/sys-navbar.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/popupwinChang.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system/systemHead.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/system/templeteCommon.js"></script> 
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
                    <span class="t">导航效果预览</span>
                </div>
            </div>
            <div class="set-system">
                <div class="full-wrap">
                    <div class="header space">
<!--                         <div class="wrap navbar smbar" style="width: 100%;"> -->
<%-- 							<a href="javascript:;" class="navbar-brand img" id="companyIcoUrl" style="background-image: url(${company.companyLogo});">${company.companyNameShort }</a> --%>
<%-- 							<a href="javascript:;" style="margin-left: 0px;" class="select-place">${school.schoolName }<i></i></a> --%>
<!-- 							<ul class="nav" id="titleList"> -->
        
<!-- 							</ul> -->
<!-- 							<ul class="nav nav-right reg"> -->
<!-- 								<li><a href="javascript:;" style="font-size:12px;margin:0px 2px;">登录</a></li> -->
<!-- 								<li><a href="javascript:;" style="font-size:12px;margin:0px 2px;">注册</a></li> -->
<!-- 							</ul> -->
<!--                         </div> -->
						<header class="header header-relative">
							<div class="page-container">
								<div class="logo"><a href="javascript:;" class="navbar-brand img" id="companyIcoUrl" style="background-image: url(${company.companyLogo});" target="_self">${company.companyNameShort }</a></div>
								<div class="select-place-wrap">
									<a href="javascript:;" class="select-place" value="6">${school.schoolName } <i class="iconfont" style="font-size:10px;">&#xe656;</i></a>
								</div>
								<ul class="nav-item " id="titleList">
									<li class="active"><a href="" target="_self">我的网校</a></li>
									<li><a href="" target="_self">题库</a></li>
									<li><a href="" target="_self">问答</a></li>
									<li class="second" id="farent1"><a href="" target="_self"><i class="iconfont">&#xe6d9;</i></a></li>
								</ul>
								<div class="login-area" style="position: absolute;right: 0;top:0;">
									<ul class="header-unlogin clear">
										<li class="header-app">
											<a href="javascript:void(0);">
												<i class="iconfont">&#xe6d8;</i>
											</a>
										</li>
										<li class="my_message">
											<a href="" target="_blank">
												<i class="iconfont">&#xe6d7;</i>
												<em>2</em>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</header>
                    </div>
                </div>
            </div>
          </div>
		  
		  <div>
            <div class="title-box marg-t-10">
                <div class="tit-font ">
                    <span class="t">主题配色</span>
                </div>
            </div>
            <div class="set-system">
                <div class="theme-box">
                    <span class="them-text">选择主题：</span>
                    <span class="theme-choice header-default footer-default" id="header-default">
                       <i class=""></i>
                    </span>
                    <span class="theme-choice header-gray footer-gray" id="header-gray">
                       <i class=""></i>
                   </span>
                    <span class="theme-choice header-white footer-whiter" id="header-white">
                       <i class=""></i>
                   </span>
                    <span class="theme-choice header-red footer-red" id="header-red">
                       <i class=""></i>
                   </span>
                    <span class="theme-choice header-cyan footer-ceya" id="header-ceya">
                       <i class=""></i>
                   </span>
                    <span class="theme-choice header-orange footer-orange" id="header-orange">
                       <i class=""></i>
                   </span>
                    <span class="theme-choice header-green footer-green" id="header-green">
                       <i class=""></i>
                   </span>
                    <span class="theme-choice header-blue footer-blue" id="header-blue">
                       <i class=""></i>
                   </span>
                </div>
            </div>
        </div>
		          
          <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">企业标识</span>
                </div>
            </div>
            <div class="set-system">
                <div class="full-wrap upfile-brand">
                     <div id="chooseLists" class="view-tabs">
			            <c:choose>
			            	<c:when test="${company.companyLogoType=='picture' }">
			            		<input type="radio" class="iconfont" checked="checked" name="com" value="logo"/>LOGO &nbsp;&nbsp; <input class="iconfont" type="radio" name="com" value="comname"/>公司名称
			            	</c:when>
			            	<c:otherwise>
			            		<input type="radio" class="iconfont" name="com" value="logo"/>LOGO &nbsp;&nbsp; <input class="iconfont" type="radio" checked="checked" name="com" value="comname"/>公司名称
			            	</c:otherwise>
			            </c:choose>
		            </div>
                    <div id="oneDiv">
					<div class="tt clear">
		                <div class="brand-view">
		                    <p><img id="imgUrl" src="${company.companyLogo}" style="width:170px;height:36px;" alt="公司logo"></p>
		                </div>
		                <div class="upfile-text">
		                    <p class="tips"><i class="iconfont orange">&#xe605;</i>建议上传 <em class="orange">透明底色的PNG</em> 图片。图像尺寸为<code>170px * 36px</code></p>
		                    <p class="btns" style="margin-top: -5px;">
		                        <a href="javascript:;" class="btn btn-default c-up" id="c-up" style="font-size: 12px;">点击上传</a>
		                        <input type="file" accept="image/*" id="imgData" name="imgData" onchange="changCompanyIco()" class="up">
		                    </p>
		                </div>
		            </div>
		           </div>
		            <div id="twoDiv" style="display: none;">
		            	<div style="float: left;">
		            		<div style="margin-top: 10px;"> 
		            			<span id="spanName">${company.companyNameShort }</span>
		            			<input id="upName" type="text" style="display: none;" maxlength="8" value="${company.companyNameShort }"/>
		            		</div>
		            		<div style="margin-top: 10px;">
		            			<input type="button" onclick="changen()" id="btnupdate" value="修改" class="btn btn-sm btn-default">
		            			<input type="button" style="display: none;" id="btnsave" onclick="changCompanyName()" value="保存" class="btn btn-primary">
		            		</div>
		            	</div>
		            	<div class="upfile-text">
		                    <p class="tips"><i class="iconfont orange">&#xe605;</i>说明：填写公司名称，或者是你想显示在网校导航栏LOGO位置的文字，长度要求：2-8字 </p>
		                </div>
		            </div>
                </div>
            </div>
        </div>
        
         <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">菜单导航栏设置</span>
                </div>
            </div>
            <div class="set-system" id="navbarconfigs">
            
            </div>
        </div>
        
    </div>
</div>
<!-- 选择链接框 -->
<div class="link-choice-box chooseUrl" data-pupwin="modal">
    <div>
        <div class="link-tab-btn">
            <p class="active original" mark="course">课程链接</p>
            <p class="custom" mark="link">自定义链接</p>
        </div>
        <div class="links-box">
            <div class="top-box">
                <div class="top-box-padd1">
                    <div class="link-line clear itemOne">
                        <p>学科</p>
                        <p class="cons_content">
                            
                        </p>
                    </div>
                    <div class="link-line clear itemTwo">
                        <p>学科小类</p>
                        <p class="cons_content">
                           
                        </p>
                    </div>
                    <div class="link-line clear classes">
                        <p>课程</p>
                        <p class="cons_content">
                           
                        </p>
                    </div>
                </div>
                
                <!-- 选择自定义链接 -->
                 <div class="top-box-padd2">
                    <table id="linkList_custom">
                        <tr>
                            <td class="col-1 col-tit">标题</td>
                            <td class="col-2">自定义域名</td>
                            <td class="col-3">发布时间</td>
                            <td class="col-4">发布人</td>
                        </tr>
                       
                    </table>
                </div>
                
            </div>
            <div class="bottom-btn-box">
                <div class="pop-save" data-pupwin-btn="success">保存</div>
                <div class="pop-cancel" data-pupwin-btn="cancle">取消</div>
            </div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenus('system_head');
    	});
 </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/pagehead.js"></script>
</body>
</html>