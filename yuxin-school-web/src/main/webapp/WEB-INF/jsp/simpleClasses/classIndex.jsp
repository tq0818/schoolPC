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
    
    <style type="text/css">
    	.tabsn li.b6{ background: #13b5b1;}
    	.tabsn li.b6:hover{box-shadow: 0 0 10px #666;}
        #opacityShow {width: 634px !important;height: 242px !important;left: 50% !important;top: 50% !important;;margin-left: -317px;
        margin-top: -121px;box-shadow: 0 0 25px #666 !important;}
        .tabsn{margin: 0 !important;padding: 25px 22px 0;}
        .tabsn li.b5{margin-right: 0 !important;}
        .tabsn{text-align: center;}




    </style>
</head>
<body style="position:relative;">
<input type="hidden" id="one" value="${itemOneId }"/>
<input type="hidden" id="two" value="${itemSecondId }"/>
<input type="hidden" id="three" value="${itemOneId }"/>
<input type="hidden" id="four" value="${itemSecondId }"/>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
     
        <div class="m-search clear">
            <span class="fr" style="margin-top:8px;">
               <i class="tips" id="tip" style="display: none;">没有查到相关信息!</i>
                <input type="text" id="classTypeName" placeholder="请输入课程名称">
                <input onclick="Form.queryCommodityByName()" type="button" class="btn btn-default btn-sm" placeholder="输入商品名称" value="搜索">
                <input type="hidden" id="isArea" value="${isArea }"/>
            </span>
        </div>
    </div>
</div>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="classes-type">
           <p class="c">
                <span class="t-title">分类</span>
                <span class="t-content" id="itemOneCodeList">
                    <a href="javascript:Form.queryAllCommdityByItemNew(1,'all');" data-code="all"  class="btn btn-mini btn-default btn-success">全部</a>
                   <c:forEach items="${firstItem }" var="type" varStatus="status">
                       <a href="javascript:Form.queryAllCommdityByItemNew(1,'${type.itemCode }');" ids="${type.id}" data-code="${type.itemCode }" class="btn btn-mini btn-default">${type.itemName }</a>
                   </c:forEach>

                </span>
            </p>
                <p class="c">
                    <span class="t-title">学段</span>
                    <span class="t-content" id="itemSecondCodeList">
                     <a href="javascript:Form.queryAllCommdityByItemNew(1);" id="xueduan" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>
                     <c:forEach items="${secondItem }" var="second" varStatus="status">
                         <a style="display: none" href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="${second.itemCode }"  parentId="${second.parentId}" ids="${second.id}" class="btn btn-mini btn-default parentId">${second.itemName }</a>
                     </c:forEach>
                </span>
            </p>

            <p class="c">
                <span class="t-title">学科</span>
                <span class="t-content" id="itemThirdCodeList">
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="all" id="xueke" class="btn btn-mini btn-default btn-success">全部</a>
                   <c:forEach items="${thirdItem }" var="third" varStatus="status">
                           <a style="display: none" href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="${third.itemCode }" parentId="${third.parentId}"  ids="${third.id}"  class="btn btn-mini btn-default thirdId">${third.itemName }</a>
                   </c:forEach>

                </span>
            </p>
            <p class="c">
                <span class="t-title">知识点</span>
                <span class="t-content" id="itemFourthCodeList">
                   <%-- <a   id="zhishidian" class="btn btn-mini btn-default btn-success" href="##">全部</a>--%>
                     <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="all" id="zhishidian" class="btn btn-mini btn-default btn-success">全部</a>
                   <c:forEach items="${fourthItem }" var="fourth" varStatus="status">
                           <a style="display: none" href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="${fourth.itemCode }" parentId="${fourth.parentId}" ids="${fourth.id}"  class="btn btn-mini btn-default fourthId">${fourth.itemName }</a>
                   </c:forEach>
                </span>
            </p>
            <%--<p class="c">--%>
                <%--<span class="t-title">学科</span>--%>
                <%--<span class="t-content" id="itemOneList">--%>
                   <%--<c:forEach items="${firstItems }" var="itemOne" varStatus="status">--%>
	                   <%--<c:if test="${status.count==1 }">--%>
	                   		 <%--<a href="javascript:Form.queryItemSecond(${itemOne.id });" ids="${itemOne.id }" class="btn btn-mini btn-default btn-success">${itemOne.itemName }</a>--%>
	                   <%--</c:if>--%>
	                   <%--<c:if test="${status.count!=1 }">--%>
	                   		 <%--<a href="javascript:Form.queryItemSecond(${itemOne.id });" ids="${itemOne.id }" class="btn btn-mini btn-default">${itemOne.itemName }</a>--%>
	                   <%--</c:if>--%>
                   <%--</c:forEach>--%>
                <%--</span>--%>
            <%--</p>--%>
            <%--<p class="c">--%>
                <%--<span class="t-title">学科小类</span>--%>
                <%--<span class="t-content" id="itemSecondList">--%>
                	<%----%>
                <%--</span>--%>
            <%--</p>--%>
            <%--<p class="c labeSets none">--%>
                <%--<span class="t-title">标签</span>--%>
                <%--<span class="t-content" id="labelLists">--%>
                	<%----%>
                <%--</span>--%>
            <%--</p>--%>
             <%--<p class="c labeSecondeSets none">--%>
                <%--<span class="t-title">二级标签</span>--%>
                <%--<span class="t-content" id="labelSecondLists">--%>
                	<%----%>
                <%--</span>--%>
            <%--</p>--%>
            <p class="c">
                <span class="t-title">状态</span>
                <span class="t-content" id="statusList">
                	<a href="javascript:Form.queryAllCommdityByItemNew(1)" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="CLASS_UNPUBLISHED" class="btn btn-mini btn-default">未上架</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="CLASS_ON_SALE" class="btn btn-mini btn-default">招生中</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="CLASS_STOP_SALE" class="btn btn-mini btn-default">已下架</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">授课方式</span>
                <span class="t-content" id="flagList">
                	<a href="javascript:Form.queryAllCommdityByItemNew(1)" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_LIVE" class="btn btn-mini btn-default">直播</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_VIDEO" class="btn btn-mini btn-default">录播</a>
                    <c:if test="${isArea eq 0}">
                        <a href="javascript:Form.queryAllCommdityByItemNew(1);" style="display: none" ids="IS_FACE" class="btn btn-mini btn-default">面授</a>
                        <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_REMOTE" class="btn btn-mini btn-default">其他</a>
                    </c:if>
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
<div class="upload-layer none" id="opacityShow">
<div class="upload-title" >
        <h2 class="h5" style="text-align: center;font-size: 14px;color: #6e6e6e;">新增课程</h2>
        <i class="close iconfont" style="color: #dfdfdf;">&#xe610;</i>
    </div>
   <div class="main" id="lsOne">
 
   </div>
</div>
<div class="add-layer-bg none"></div>
<%--//分类，学段，学科，知识点联动--%>
<script>
    function showList(type) {
        if(type == 0){
            $("#itemSecondCodeList").children('a').removeClass('btn-success');
            $("#itemThirdCodeList").children('a').removeClass('btn-success');
            $("#itemFourthCodeList").children('a').removeClass('btn-success');
            $("#xueduan").addClass('btn-success');
            $("#xueke").addClass('btn-success');
            $("#zhishidian").addClass('btn-success');
            for(var i = 0;i<$('.thirdId').length;i++){
                $('.thirdId').eq(i).hide();
            }
            for(var i = 0;i<$('.fourthId').length;i++){
                $('.fourthId').eq(i).hide();
            }
        }
        if(type==1){
            $("#itemThirdCodeList").children('a').removeClass('btn-success');
            $("#itemFourthCodeList").children('a').removeClass('btn-success');
            $("#xueke").addClass('btn-success');
            $("#zhishidian").addClass('btn-success');
            for(var i = 0;i<$('.fourthId').length;i++){
                $('.fourthId').eq(i).hide();
            }
        }
    }
    //点击分类
    $('#itemOneCodeList').children('a').click(function () {
        showList(0);
        if($(this).index()!=0){
            var id= $(this).attr('ids');
            for(var i = 0;i<$('.parentId').length;i++){
                if(id==$('.parentId').eq(i).attr('parentId')){
                    $('.parentId').eq(i).show();
                }else {
                    $('.parentId').eq(i).hide();
                }
            }
        }else{
            for(var i = 0;i<$('.parentId').length;i++){
                $('.parentId').eq(i).hide();
            }
        }
    });
    //点击学段
    $('#itemSecondCodeList').children('a').click(function () {
        showList(1);
        if($(this).index()!=0){
            var id= $(this).attr('ids');
            for(var i = 0;i<$('.thirdId').length;i++){
                if(id==$('.thirdId').eq(i).attr('parentId')){
                    $('.thirdId').eq(i).show();
                }else {
                    $('.thirdId').eq(i).hide();
                }
            }
        }else{
            for(var i = 0;i<$('.thirdId').length;i++){
                $('.thirdId').eq(i).hide();
            }
        }
    });
    $('#itemThirdCodeList').children('a').click(function () {
        $("#itemFourthCodeList").children('a').removeClass('btn-success');
        $("#zhishidian").addClass('btn-success');
        if($(this).index()!=0){
            var id= $(this).attr('ids');
            for(var i = 0;i<$('.fourthId').length;i++){
                if(id==$('.fourthId').eq(i).attr('parentId')){
                    $('.fourthId').eq(i).show();
                }else {
                    $('.fourthId').eq(i).hide();
                }
            }
        }else{
            for(var i = 0;i<$('.fourthId').length;i++){
                $('.fourthId').eq(i).hide();
            }
        }
    });
    //知识点
    $('#itemFourthCodeList').children('a').click(function () {
        if($(this).index()!=0){
            $('#zhishidian').removeClass('btn-success');
        }else {
            $(this).siblings('a').removeClass('btn-success');
        }
    });
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			url : rootPath + "/companyServiceStatic/queryCompanyNoServices",//查询未开通的服务
			type : "post",
			dataType : 'json',
			success : function(jsonData) {
				var isArea=$("#isArea").val();
				var b6="";
				if("0"==isArea){
					b6="<li class=\"b6 classTypeManage\">分校课程</li>";
				}else{
					b6="<li class=\"b6 buyClassType\">已购课程</li>";
				}
				var count=jsonData.length;
				var html="";
				if("0"==isArea){
                    if(count==1){
                        $.each(jsonData,function(i,item){
                            if(item.groupCode=='SERVICE_LIVE'){
                                html+='<ul class="tabsn"><li class="b2">录播</li>'+
                                    /* '<li class="b3">面授</li>'+
                                    '<li class="b4">混合</li>'+ */
                                    b6+
                                    '<li class="b5">其他</li></ul>';
                            }else if(item.groupCode=='SERVICE_VIDEO'){
                                html+='<ul class="tabsn"><li class="b1">直播</li>'+
                                    /* '<li class="b3">面授</li>'+
                                    '<li class="b4">混合</li>'+ */
                                    b6+
                                    '<li class="b5">其他</li></ul>';
                            }else if(item.groupCode=='SERVICE_FACE'){
                                html+='<ul class="tabsn"><li class="b1">直播</li>'+
                                    '<li class="b2">录播</li>'+
                                    /* '<li class="b4">混合</li>'+ */
                                    b6+
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
                            html+=/*'<ul class="tabsn c2"><li class="b3">面授</li>' */b6+'<li class="b5">其他</li></ul>';
                        }
                        if((num1=="SERVICE_LIVE"&&num2=="SERVICE_FACE")||(num1=="SERVICE_FACE"&&num2=="SERVICE_LIVE")){
                            html+='<ul class="tabsn"><li class="b2">录播</li>'+
                                b6+
                                '<li class="b5">其他</li></ul>';
                        }
                        if((num1=="SERVICE_FACE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_FACE")){
                            html+='<ul class="tabsn"><li class="b1">直播</li>'+
                                '<li class="b2">录播</li>'+
                                /* '<li class="b3">面授</li>'+
                                '<li class="b4">混合</li>'+ */
                                b6+'<li class="b5">其他</li></ul>';
                        }
                    }else if(count==3){
                        html+='<ul class="tabsn">'+
                            '<li class="b2">录播</li>'+
                            /* '<li class="b3">面授</li>'+
                            '<li class="b4">混合</li>'+ */
                            b6+'<li class="b5">其他</li></ul>';
                    }else{
                        html+='<ul class="tabsn"><li class="b1">直播</li>'+
                            	'<li class="b2">录播</li>'+
                                   /* '<li class="b3">面授</li>'+
                                   '<li class="b4">混合</li>'+ */
                            b6+
                            '<li class="b5">其他</li></ul>';
                    }
                }else{
                	if(count==0){
                		html+='<ul class="tabsn">' +
                        '<li class="b1">直播</li>'+
                        '<li class="b2">录播</li>'+
                        b6+'</ul>';
                	}else if(count==1){
                        $.each(jsonData,function(i,item){
                            if(item.groupCode=='SERVICE_LIVE'){
                                html+='<ul class="tabsn">' +
                                    '<li class="b2">录播</li>'+
                                    b6+'</ul>';
                            }else if(item.groupCode=='SERVICE_VIDEO'){
                                html+='<ul class="tabsn">' +
                                    '<li class="b1">直播</li>'+
                                    b6+'</ul>';
                            }else{
                                html+='<ul class="tabsn">' +
                                    '<li class="b1">直播</li>'+
                                    '<li class="b2">录播</li>'+
                                    b6+'</ul>';
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
                        /* if((num1=="SERVICE_LIVE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_LIVE")){
                            html+='<ul class="tabsn">' +
                                '<li class="b1">直播</li>'+
                                '<li class="b2">录播</li>'+
                                b6+'</ul>';
                        }
                        if((num1=="SERVICE_LIVE"&&num2=="SERVICE_FACE")||(num1=="SERVICE_FACE"&&num2=="SERVICE_LIVE")){
                            html+='<ul class="tabsn">' +
                                '<li class="b1">直播</li>'+
                                '<li class="b2">录播</li>'+
                                b6+'</ul>';
                        }
                        if((num1=="SERVICE_FACE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_FACE")){
                            html+='<ul class="tabsn">' +
                                '<li class="b1">直播</li>'+
                                '<li class="b2">录播</li>'+
                                b6+'</ul>';
                        } */
                        if((num1=="SERVICE_LIVE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_LIVE")){
                            html+='<ul class="tabsn">'+
                                b6+'</ul>';
                        }
                        if((num1=="SERVICE_LIVE"&&num2=="SERVICE_FACE")||(num1=="SERVICE_FACE"&&num2=="SERVICE_LIVE")){
                            html+='<ul class="tabsn">'
                                '<li class="b2">录播</li>'+
                                b6+'</ul>';
                        }
                        if((num1=="SERVICE_FACE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_FACE")){
                            html+='<ul class="tabsn">' +
                                '<li class="b2">录播</li>'+
                                b6+'</ul>';
                        }
                    }else if(count==3){
                        html+='<ul class="tabsn">'+
                            b6+'</ul>';
                    }else{
                        html+='<ul class="tabsn">'+b6+'</ul>';
                    }
                }

				/* $("#lsOne").append(html); */
				$(html).appendTo("#lsOne");
			}
		});
		$("body").css("position","relative")
	});
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/classIndex.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>


<script>



</script>
</body>
</html>
