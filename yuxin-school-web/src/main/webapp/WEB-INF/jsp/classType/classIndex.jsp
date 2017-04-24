<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    
</head>
<body style="position:relative;">
<input type="hidden" id="one" value="${itemOneId }"/>
<input type="hidden" id="two" value="${itemSecondId }"/>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
     
        <div class="m-search clear">
            <span class="fr" style="margin-top:8px;">
               <i class="tips" id="tip" style="display: none;">没有查到相关信息!</i>
                <input type="text" id="classTypeName" placeholder="请输入课程名称">
                <input onclick="Form.queryCommodityByName()" type="button" class="btn btn-default btn-sm" placeholder="输入商品名称" value="搜索">
            </span>
        </div>
    </div>
</div>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="classes-type">
            <p class="c">
                <span class="t-title">学科</span>
                <span class="t-content" id="itemOneList">
                   <c:forEach items="${firstItems }" var="itemOne" varStatus="status">
	                   <c:if test="${status.count==1 }">
	                   		 <a href="javascript:Form.queryItemSecond(${itemOne.id });" ids="${itemOne.id }" class="btn btn-mini btn-default btn-success">${itemOne.itemName }</a>
	                   </c:if>
	                   <c:if test="${status.count!=1 }">
	                   		 <a href="javascript:Form.queryItemSecond(${itemOne.id });" ids="${itemOne.id }" class="btn btn-mini btn-default">${itemOne.itemName }</a>
	                   </c:if>
                   </c:forEach>
                </span>
            </p>
            <p class="c">
                <span class="t-title">学科小类</span>
                <span class="t-content" id="itemSecondList">
                	
                </span>
            </p>
            <p class="c labeSets none">
                <span class="t-title">标签</span>
                <span class="t-content" id="labelLists">
                	
                </span>
            </p>
             <p class="c labeSecondeSets none">
                <span class="t-title">二级标签</span>
                <span class="t-content" id="labelSecondLists">
                	
                </span>
            </p>
            <p class="c">
                <span class="t-title">状态</span>
                <span class="t-content" id="statusList">
                	<a href="javascript:Form.queryAllCommdityByItem(1,null,null)" class="btn btn-mini btn-default btn-success">全部</a>
                    <a href="javascript:Form.queryAllCommdityByItem(1,null,null,'CLASS_UNPUBLISHED');" ids="CLASS_UNPUBLISHED" class="btn btn-mini btn-default">未上架</a>
                    <a href="javascript:Form.queryAllCommdityByItem(1,null,null,'CLASS_ON_SALE');" ids="CLASS_ON_SALE" class="btn btn-mini btn-default">招生中</a>
                    <a href="javascript:Form.queryAllCommdityByItem(1,null,null,'CLASS_STOP_SALE');" ids="CLASS_STOP_SALE" class="btn btn-mini btn-default">已下架</a>
                </span>
            </p>
        </div>
    </div>
</div>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div id="commodityDetailList">
        
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->

<!-- 弹层信息 -->
<div class="upload-layer none">
<div class="upload-title">
        <h2 class="h5">新增课程</h2>
        <i class="close iconfont">&#xe610;</i>
    </div>
   <div class="main" id="lsOne">
   	
<!--    		<li class="b1">直播</li> -->
<!--    		<li class="b2">录播</li> -->
<!--    		<li class="b3">面授</li> -->
<!--    		<li class="b4">混合</li> -->
<!--    		<li class="b5">其他</li> -->

<!--    		<table class="table table-center" style="border:0px;height: 200px;">
		      <tr>
			    <td><a href="javascript:Form.addClassType('live');" marks="live">直播</a></td>
			    <td><a href="javascript:Form.addClassType('face');" marks="face">面授</a></td>
			    <td><a href="javascript:Form.addClassType('video');" marks="video">录播</a></td>
			    <td><a href="javascript:Form.addClassType('togther');" marks="togther">混合</a></td>
			    <td><a href="javascript:Form.addClassType('other');" marks="other">其他</a></td>
		      </tr>
	     </table> -->
   </div>
</div>
<div class="add-layer-bg none"></div>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			url : rootPath + "/companyServiceStatic/queryCompanyNoServices",
			type : "post",
			dataType : 'json',
			success : function(jsonData) {
				var count=jsonData.length;
				var html="";
				if(count==1){
					$.each(jsonData,function(i,item){
						if(item.groupCode=='SERVICE_LIVE'){
							html+='<ul class="tabsn c4"><li class="b2">录播</li>'+
					   			'<li class="b3">面授</li>'+
						   		'<li class="b4">混合</li>'+
						   		'<li class="b5">其他</li></ul>';
						}else if(item.groupCode=='SERVICE_VIDEO'){
								html+='<ul class="tabsn c4"><li class="b1">直播</li>'+
					   			'<li class="b3">面授</li>'+
						   		'<li class="b4">混合</li>'+
						   		'<li class="b5">其他</li></ul>';
						}else if(item.groupCode=='SERVICE_FACE'){
								html+='<ul class="tabsn c4"><li class="b1">直播</li>'+
					   			'<li class="b2">录播</li>'+
						   		'<li class="b4">混合</li>'+
						   		'<li class="b5">其他</li></ul>';
						}
					});
				}else if(count==2){
					var num1,num2;
					$.each(jsonData,function(i,item){
						if(i==0){
							num1=item.groupCode;
						}else{
							num2=item.groupCode;
						}
					})
					if((num1=="SERVICE_LIVE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_LIVE")){
						html+='<ul class="tabsn c2"><li class="b3">面授</li>'+
				   		'<li class="b5">其他</li></ul>';
					}
					if((num1=="SERVICE_LIVE"&&num2=="SERVICE_FACE")||(num1=="SERVICE_FACE"&&num2=="SERVICE_LIVE")){
						html+='<ul class="tabsn c2"><li class="b2">录播</li>'+
				   		'<li class="b5">其他</li></ul>';
					}
					if((num1=="SERVICE_FACE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_FACE")){
						html+='<ul class="tabsn c2"><li class="b1">直播</li>'+
				   		'<li class="b5">其他</li></ul>';
					}
				}else if(count==3){
					html+='<ul class="tabsn c8">'+
			   		'<li class="b5">其他</li></ul>';
				}else{
					html+='<ul class="tabsn"><li class="b1">直播</li>'+
					'<li class="b2">录播</li>'+
		   			'<li class="b3">面授</li>'+
			   		'<li class="b4">混合</li>'+
			   		'<li class="b5">其他</li></ul>';
				}
				$("#lsOne").append(html);
			}
		});
		$("body").css("position","relative")
	});
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/classIndex.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>