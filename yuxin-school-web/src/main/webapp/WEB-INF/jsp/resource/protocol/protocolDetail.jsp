<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>协议</title>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/resource.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/newresource.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/userAgree.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/stylesheets/classes.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/TagManagement.css">
	<style type="text/css">
		img{cursor: pointer;}
		.main-content .block .b-title .tt .h3 b {
        	font-size: 18px;
		    padding: 15px 52px 5px 0px;
		    display: block;
		}
		.tagBtn{
			margin-left: 47px;
   			margin-top: 15px;
   			background: #23B7E5;
		}
		.clicked{
			background: #00CCCC;
			color:white;
		}
		.tagManage{
		    position: fixed;
		    left: 20%;
		    top: 10%;
		    z-index: 999;
		    background-color: #fff;
	    }
	</style>
	
<style>
     .main-content { 
	     list-style-type: none;
	      margin: 30px auto !important; 
	      padding: 0; 
	      width: 1080px; 
      }
     .ui-state-highlight {
         border-style:dashed;
         background: transparent;
     }
     .sortable-sec{
         margin-left: 25px;
     }
</style>
</head>
<body>
	<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
	<div class="u-wrap resource">
		<div class="mainbackground nopadding">
			<div class="heading">
	            <h2 class="h5">课程协议</h2>
	            <div class="search searchAdmin">
                <a href="javascript:;" class="btn btn-mini btn-primary" id="protocolback">返回</a>
            	</div>
	            <span class="line"></span>
	        </div>
	        <div class="AgreeExr">
            <span>协议名称 : <address>${protocol.name }</address></span>
            <span>创建时间 : <time>${protocol.createTimeStr}</time></span>
        	</div>
	        <section>
            <div class="userAgre">
                <div class="artTitle">
                    <h3  class="proname" data-classtype="${classType.name}">${protocol.title}</h3>
                    <!-- <a href="javascript:;" id="protocolDownload"><i class="iconfont iUpload">&#xe6e0;</i> 下载</a> -->
                </div>
                <div class="artCon">
                </div>
            </div>
        </section>
		</div>
	</div>
	<div class="loading lp-units-loading" id="ssg" style="display:none">
	<p><i></i>加载中,请稍后...</p>
	</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript">
$(function(){
	$('#resource_protocol').addClass('active');
	var content = '${protocol.content}';
	$('#protocolback').on('click',function(){
		window.location.href=rootPath+"/courseProtocolConfig/toProtocolList";
	})
	
	var classTypeName = $('.proname').data('classtype');
	if(classTypeName){
		var str = classTypeName+$('.proname').html();
		$('.proname').html(str);
	}
	var ct = '${classType}';
	var cp = '${classPackage}';
	var user = '${user}';
	var itemOne = '${itemOne}';
	var itemSecond = '${itemSecond}';
	var rp = '${userRealPrice}';
	if(user){
		var now  = dateToStr('yyyy-MM-dd',new Date());
		var commodity = ct || cp;
		commodity = $.formateStrToObj(commodity);
		cp = $.formateStrToObj(cp);
		user = $.formateStrToObj(user);
		itemOne = $.formateStrToObj(itemOne);
		itemTwo = $.formateStrToObj(itemSecond);
		content = decodeURI(content)
			        .replace(/\[username\]/g,user.username?user.username:'')
					.replace(/\[coursename\]/g,commodity.name?commodity.name:'')
					.replace(/\[StudentIDcardnumber\]/g,user.identityId?user.identityId:'')
					.replace(/\[Studentcellphonenumber\]/g,user.mobile?user.mobile:'')
					.replace(/\[coursecost\]/g,commodity.originalPrice?commodity.originalPrice:0)
					.replace(/\[PreferentiaIpriceofcourse\]/g,rp?rp:commodity.realPrice?commodity.realPrice:0)
					.replace(/\[current date\]/g,now)
					.replace(/\[Course subject\]/g,!itemOne?'':itemOne.itemName?itemOne.itemName:'');
	}else{
		content=  decodeURI(content).replace("\r\n","<br>&nbsp;&nbsp;");
	}
	$('.artCon').html(content);
	
	
	
});
</script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
</body>
</html>