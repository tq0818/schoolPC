<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<%=rootPath %>/images/favicon.ico" type="image/x-icon" /> 
	<link rel="shortcut icon" href="<%=rootPath %>/images/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/perview.css"/>
	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system/homeDefault.css">
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript">
		var rootPath='<%=rootPath%>';
	</script>
	<title>首页-预览</title>
<script type="text/javascript">
	var isIndex=true; 
	$(document).ready(function(){
	var unslider;
	loadBody();
	
	querySysCyclePic("homepage",function(){
 		unslider = $('.banner').unslider({
        speed: 500,
        delay: 5000,
        complete: function() {},
        keys: true,
        fluid: false
        });
 		 $('a.focus-arrow').click(function() {
             var fn = this.className.split(' ')[1];
             unslider.data('unslider')[fn]();
         });
    });
	//点击切换分校
	$(".select-place").click(function(){
		showSchoolsList();
	});
});
</script>
</head>
<body class=""> 
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
<div class="full-wrap index-content gap">
    <div class="banner">
  		<ul id="banners">
  		
  		</ul>
  		<a href="javascript:;" class="focus-arrow prev">上一个</a>
        <a href="javascript:;" class="focus-arrow next">下一个</a>
    </div>
</div>
<div class="box homeBox">

</div>
<input type="hidden" id="schoolId" value="${schoolId}"/>
<input type="hidden" id="templateId" value="${templateId}"/>
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
<script type="text/javascript">

     //显示分校列表
 	function showSchoolsList() {
		$.ajax({
			type : "post",
			url : rootPath + "/sysPageHeadFoot/showHeadChoose",
			success : function(result) {
			 setCookie('school_list', JSON.stringify(result));
			   $(".school_place").html('');
				if(result.length<6){
					$(".school_place").append("<ul id='placeOne'></ul>");
					$.each(result,function(i,item){
						$("#placeOne").append("<i class='curr'></i><li><a href=javascript:Form.changeSchoolValue("+item.id+",'"+item.schoolName+"')>"+item.schoolName+"</a></li>");
					});
					$(".school_place").slideToggle(function() {
						$(this).addClass("display", "block");
					});
					$(".school_place").css("left", $(".select-place").offset().left-10);
				}else if(result.length==6){
					$(".school_place").addClass("places p1");
					$(".school_place").append("<ul id='placeTwoUl'></ul>");
					$.each(result,function(i,item){
						$("#placeTwoUl").append("<i class='curr'></i><li><a href=javascript:Form.changeSchoolValue("+item.id+",'"+item.schoolName+"')>"+item.schoolName+"</a></li>");
					});
					$(".school_place").slideToggle(function() {
						$(this).addClass("display", "block");
					});
					$(".places").css("left", $(".select-place").offset().left-50);
				}else{
					$(".school_place").addClass("places p2");
					$(".school_place").append("<ul id='placeThree'></ul>");
					$.each(result,function(i,item){
						$("#placeThree").append("<i class='curr'></i> <li><a href=javascript:Form.changeSchoolValue("+item.id+",'"+item.schoolName+"')>"+item.schoolName+"</a></li>");
					});
					$(".school_place").slideToggle(function() {
						$(this).addClass("display", "block");
					});
					$(".school_place").css("left", $(".select-place").offset().left-100);
				}
				//$("#placesOne").css("top", $(window).offset().top + 45);
			}
		});
   }
	
 	function  setCookie(c_name, value, expiredays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + expiredays ? "" : expiredays);
		document.cookie = c_name + "=" + encodeURI(value)
				+ (!expiredays ? "" : ";expires=" + exdate.toGMTString());
	}
 	
	function loadBody(){
		var pages,models,configs;
		$.ajax({
			url : rootPath+"/sysBody/loadBody",
			type : "post",
			data: "templateId="+$("#templateId").val(),
			dataType : "json",
			success : function(jsonData){
				pages=jsonData.pages;
				models=jsonData.models;
				configs=jsonData.configs;
				$(".box").html("");
				var result=ModelUtil.resolve(pages,models,configs);
				$.each(result,function(i){
					var div=result[i];
					var config=eval(configs)[i];
					$(".box").append(div);   
					ModelUtil.fillData(i,$(".box").find(".wrap").eq(i),config);
					delMoreHref();
					changeIconfont();
				});
			}
		});
	}
	function querySysCyclePic(type,callback){
		$(".banner").remove();
		$.ajax({ 
	 		  type: "post", 
	 		  url: rootPath+"/sysCyclePic/showPic2",
	 		  data: {"picType" : type},
	 		  success: function(result){
	 			$(".index-content").append('<div class="banner" style="overflow: hidden; height: 536px;">'+
	 			'<ul id="banners" style="width: 500%; position: relative; left: 0%; height: 536px;"></ul>'+
	 			'<a href="javascript:;" class="focus-arrow prev">上一个</a><a href="javascript:;" class="focus-arrow next">下一个</a>'+
	 			'</div>');
	 			$.each(result,function(i,item){
	 				if(item.clickUrl){
	 					$("#banners").append("<li class='page"+i+"'><a href='"+item.clickUrl+"' target='_blank'></a></li>");
	 				}else{
	 					$("#banners").append("<li class='page"+i+"'><a href='javascript:;'></a></li>");
	 				}
	 				var image=new Image();
	 				image.src=item.picUrl;
	 				image.onload=function(){
	 					var h=image.height;
	 					var w=image.width;
	 					var ww=window.screen.width;	
	 					var hh=window.screen.height;
	 					var gwidth=(ww-w)/4;
						console.log(gwidth+"-----");//background-position-x":gwidth;overflow:hidden;
						if(gwidth<0){
							$(".page"+i).css({"background-image":"url(" + item.picUrl + ")","background-position-x":"center","background-repeat":"no-repeat"});
						}else{
							$(".page"+i).css({"background-image":"url(" + item.picUrl + ")","background-repeat":"no-repeat"});
						}
	 					
	 				}
	 			});
	 			  if(callback){
	 				  callback()
	 			  }
	 		  }
	 	  });
	}
	
	function delMoreHref(){
		$(".more").find('a').attr('href','javascript:;')
	}
	
	function changeIconfont(){
		/*setTimeout(function(){
			
		},5000);*/
		var list = $('i.iconfont'),
		content;
		console.log('kaishi');
		list.each(function(i){
			content = $(this).html();
			if($(this).hasClass('iYan')){
				$(this).html('&#xe6e7;');
			}
			if($(this).parents('p').hasClass('lastp') && !$(this).hasClass('iYan')){
				$(this).html('&#xe6e5;');
			}
			if($(this).parent().hasClass('_people')){
				$(this).html('&#xe6e6;');
			}
		});
		console.log('jieshu');
	}
</script>

    <script type="text/javascript" src="<%=rootPath%>/javascripts/unslider.min.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system/ModelUtil.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system/perview_index.js"></script>
</body>
</html>