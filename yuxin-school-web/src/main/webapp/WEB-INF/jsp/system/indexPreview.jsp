<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<%=rootPath %>/images/favicon.ico" type="image/x-icon" /> 
	<link rel="shortcut icon" href="<%=rootPath %>/images/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/swiper/swiper.min.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/perview.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/themes/fashion/manage_newHomeIndex.css">
   
    <script type="text/javascript">
		var rp = rootPath='<%=rootPath%>';
	</script>
</head>
<body class="background-cyan">
<header class="full-wrap navbar navbar-fixed">
    <div class="wrap">
    	<div id="headContents">
	    	<a class="navbar-brand" href="<%=rootPath %>/index" title="网站首页" style="height:32px;"></a>
	        <a href="javascript:void(0);" class="select-place"></a>
	        <ul class="nav nav-left top-menu">
	           	
	        </ul>   
     	</div>
        <ul id="btns" class="nav nav-right reg">
        	<c:choose>
		        <c:when test="${sessionScope.currtUser.name ==null && sessionScope.currtUser.mobile==null && sessionScope.currtUser.email==null}">
		        	<li><a href="javascript:void(0);" onclick="Form.showLoginPopup()">登录</a></li>
		            <li><a href="javascript:void(0);" onclick="Form.showRegistPopup()">注册</a></li>
		        </c:when>
	        </c:choose>
        </ul>
        <ul class="nav nav-right reg" style="width: 190px;">
            <li class="user">
            <c:choose>
            	<c:when test="${sessionScope.currtUser.name!=null && sessionScope.currtUser.name!=''}">
            		<a href="javascript:void(0)">${sessionScope.currtUser.name}</a><i></i>
            		<span class="message none" style="cursor: pointer;"></span>
            	</c:when>
            	<c:when test="${sessionScope.currtUser.mobile!=null && sessionScope.currtUser.mobile!='' }">
            		<a href="javascript:void(0)">${sessionScope.currtUser.mobile}</a><i></i>
            		<span class="message none" style="cursor: pointer;"><em>0</em></span>
            	</c:when>
            	<c:when test="${sessionScope.currtUser.email!=null && sessionScope.currtUser.email!='' }">
            		<a href="javascript:void(0)">${sessionScope.currtUser.email}</a><i></i>
            		<span class="message none" style="cursor: pointer;"><em>0</em></span>
            	</c:when>
            </c:choose>
            </li>
            <ul class="dropdown">
                <li><a href="<%=rootPath%>/usersFront/userCenter">个人中心</a></li>
                <li><a href="<%=rootPath%>/student/showInfo">个人设置</a></li>
                <li><a href="<%=rootPath%>/student/toInfoCenter">消息中心</a></li>
                <li><a href="javascript:;" onclick="Form.logout()">退出</a></li>
            </ul>
        </ul>
    </div>
   
    <div class="places school_place" id="divOne" style="z-index: 998px;"> 
  
    </div>

</header>
<article>
    <section class="headerSwiper">
        <div class="swiper-container swiperwell-container">
            <ul class="swiper-wrapper" id="banner">
                
            </ul>
            <div class="swiper-pagination selfPagination"></div>
            <div class="prevBut">
                <i class="iconfont">&#xe650;</i>
            </div>
            <div class="nextBut">
                <i class="iconfont">&#xe651;</i>
            </div>
        </div>
    </section>
</article>

<footer class="full-wrap fat-footer">
    <div class="wrap">
        <div class="rows">
            <div class="col-6">
                <div class="copyright" id="copyrightList">
                </div>
            </div>
            <div class="col-6">
                <ul class="foot-menu" id="footContents">
                  
                </ul>
            </div>
        </div>
    </div>
</footer>
<input type="hidden" id="userGhId" value="${userGhId }"/>
<input type="hidden" id="userZsId" value="${userZsId }"/>
<input type="hidden" id="userName" value="${userName }"/>
<input type="hidden" id="indextitle" value="${seo.title }"/>


<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/html5.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/swiper/swiper.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/system/perview_index.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/themes/fashion/index_preview.js"></script>
</body>
</html>