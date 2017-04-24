<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>直播公开课列表</title>
	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
	<link href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/openClass/liveShow.js"></script>
	<%-- <script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script> --%>
	<style type="text/css">
        .btn - upload {
	        float: right;
	        margin - top: -15px
	    }
	    .h5 {
	        width: 100px
	    }
	    .up - input {
	        position: relative;
	        width: 166px;
	        height: 32px;
	        opacity: 0;
	        filter: alpha(opacity = 0);
	        z - index: 1
	    }
	    .upfile {
	        position: relative;
	        display: inline - block
	    }
	    .upfile.btn - up {
	        position: absolute;
	        top: 0;
	        left: 0;
	    }
	    .register {
	        position: fixed;
	        left: 50 % ;
	        top: 50 % ;
	        width: 400px;
	        height: 400px;
	        margin - left: -200px;
	        margin - top: -200px;
	        padding - bottom: 15px;
	        background - color: #fff;
	        border: 1px solid# ddd;
	        border - radius: 5px;
	        box - shadow: 0 0 30px rgba(0, 0, 0, 0.2);
	        z - index: 999
	    }
	    .none {
	        display: none;
	    }
	    .register.reg - close {
	        position: absolute;
	        top: 12px;
	        right: 12px;
	        width: 12px;
	        height: 12px;
	        background - image: url('../images/index-icons.png');
	        background - repeat: no - repeat;
	        background - position: 0 0;
	        cursor: pointer;
	    }
	    .register.reg - title {
	        padding: 15px 30px;
	        border - bottom: 1px solid# e5e5e5;
	    }
	    .register.reg - form {
	        padding: 0 60px;
	    }
	    .register.reg - bottom {
	        padding: 2px 52px;
	        border - top: 1px solid# e5e5e5;
	    }
	    .mark - bg {
	        position: fixed;
	        top: 0;
	        right: 0;
	        bottom: 0;
	        left: 0;
	        background - color: #fff;
	        background - color: rgba(255, 255, 255, 0.6);
	        opacity: .6\ 9;
	        filter: alpha(opacity = 60);
	    }
	</style>
</head>
<body>
<input type="hidden" id="oneId"/>
<input type="hidden" id="twoId"/>
<input type="hidden" id="publishStatus"/>
<input type="hidden" id="imgUrl"/>
	<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
	<div class="u-wrap operate">
    <div class="mainbackground">
        <div class="select-option clear">
            <ul>
                <li>
                    <span class="s-title">学科</span>
                    <span class="s-list" id="one">
                    	<a class="btn btn-sm btn-default btn-success oneItem qb" oneId="" href="javascript:;">全部</a>
                        <c:forEach var="o" items="${oneItems}">
                        		<a class="btn btn-sm btn-default oneItem" oneId="${o.id }" href="javascript:;">${o.itemName }</a>
                        </c:forEach>
                    </span>
                </li>
                <li id="two">
                    <span class="s-title">学科小类</span>
                    <span class="s-list" id="twoItem">
                        <a class="btn btn-sm btn-default btn-success twoItem qb" twoId="" href="javascript:;">全部</a>
                    </span>
                </li>
                <li>
                    <span class="s-title">状态</span>
                    <span class="s-list">
                    	<a class="btn btn-sm btn-default btn-success pubStatus qb" href="javascript:;" pubStatus="">全部</a>
                    	<a class="btn btn-sm btn-default pubStatus" href="javascript:;" status="0">未发布</a>
                    	<a class="btn btn-sm btn-default pubStatus" href="javascript:;" status="1">已发布</a>
                    	<a class="btn btn-sm btn-default pubStatus" href="javascript:;" status="-1">已下架</a>
                    </span>
                </li>
                 <li>
                    <span class="s-title">时间</span>
                    <span class="s-list">
                    	<input type="text" id="choiceTime"/>
                    	<a class="btn btn-sm btn-default seTime" href="javascript:;">查询</a>
                    	<a class="btn btn-sm btn-default reSet" href="javascript:;">重置</a>
                    </span>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="u-wrap classes">
    <div class="mainbackground nopadding">
    	<div class="heading">
            <h2 class="h5">公开课列表</h2>
            <a href="javascript:void(0)" class="btn btn-mini btn-primary create" style="margin-top: -20px;float: right;">新建公开课</a>
            <span class="line"></span>
        </div>
        <div id="info">
        
        </div>
    </div>
</div>
<!-- 弹层信息 -->
<div class="layer-tips class-resource" style="width: 580px;height: 200px;line-height: 4;">
	<div class="layer-tips-content">
		<br>
		<p style="color: red;" id="errorInfo">
			注：可将当前公开课信息复制到对应日期，请您选择您想选择的日期
		</p>
		<p>
			开始时间：
			<input type="text" id="fzTime" readonly="readonly"/>
			结束时间：
			<input type="text" id="fzTimeEnd" readonly="readonly"/>
			<!-- 使用弹幕:<span class="barrages"></span>
			模式:<span class="modetypes"></span> -->
			<input type="hidden" id="barrage" readonly="readonly"/>
			<input type="hidden" id="modetype" readonly="readonly"/>
		</p>
	</div>
	<div class="layer-tips-btns">
	    <p>
	    	<a href="javascript:;" class="btn btn-mini btn-primary btn-yes">需要修改</a>
	    	<a href="javascript:;" class="btn btn-mini btn-primary btn-no">不用修改</a>
	    	<a href="javascript:;" class="btn btn-mini btn-default btn-cancel">取消</a>
	    </p>
	</div>
</div>
<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script>
$(function(){
	$selectSubMenu('open_class_set');
});
</script>
</body>
</html>