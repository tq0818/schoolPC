<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>协议</title>
<%-- <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/agreement-2.css"/> --%>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/utils.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/newresource.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/code.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/coupon.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/stylesheets/classes.css" />
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/agreement-4.css"/>
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
                    <a href="javascript:;" class="btn btn-mini btn-primary addProtocol"><em class="iconfont">&#xe606;</em>添加协议</a>
                </div>
	            <span class="line"></span>
	        </div>
	        <div class="sc-select" id="categoryList">
                <span class="sc-select-title">协议类型</span>&nbsp;&nbsp;&nbsp;
                <a href="javascript:void(0);" class="btn btn-mini btn-default btn-success" mark="all" id="all">全部</a>&nbsp;
        	<a href="javascript:void(0);" class="btn btn-mini btn-default" mark="course" id="course">课程协议</a>&nbsp;
        	<a href="javascript:void(0);" class="btn btn-mini btn-default" mark="coursePackage" id="coursePackage">课程包协议</a>
            </div>
	        <div id="loadData"></div>
		</div>
	</div>
	<div class="loading lp-units-loading" id="ssg" style="display:none">
	<p><i></i>加载中,请稍后...</p>
	</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>

<input type="hidden" class="cid">
<input type="hidden" class="hid">
<input type="hidden" class="ctype">
<input type="hidden" class="htype">
<div class="layer-tips coupon-code-detail" id="courseDetail">
		<div class="layer-tips-title">
			<span id="currentCourse"><a href="javascript:void(0)" class="btn btn-sm btn-default btn-success">绑定课程</a></span><span id="historyCourse"><a href="javascript:void(0);" class="btn btn-sm btn-default">历史绑定课程</a></span><i class="close iconfont confirm_close">&#xe610;</i>
		</div>
		
		<div class="coupon-use-detail">
					<div class="ctr-alert">
						<table class="table table-center courseList">
							<tr>
								<th width="25%">课程名称</th>
								<th width="26%">添加时间</th>
								<th width="25%">创建人</th>
								<th width="25%">协议绑定人数</th>
							</tr>
						</table>
						<div class="pages pagination1"></div>
					</div>
				</div>
	</div>
	
	<div class="layer-tips coupon-code-detail" id="coursePackageDetail">
		<div class="layer-tips-title">
			<span id="currentPackage"><a href="javascript:void(0);" class="btn btn-sm btn-default btn-success">绑定课程包</a></span><span id="historyPackage"><a href="javascript:void(0);" class="btn btn-sm btn-default">历史绑定课程包</a></span><i class="close iconfont confirm_close">&#xe610;</i>
		</div>
		<div class="coupon-use-detail">
					<div class="ctr-alert">
						<table class="table table-center coursePackageList">
							<tr>
								<th width="25%">课程包名称</th>
								<th width="26%">添加时间</th>
								<th width="25%">创建人</th>
								<th width="25%">协议绑定人数</th>
							</tr>
						</table>
						<div class="pages pagination2"></div>
					</div>
				</div>
	</div>
<script type="text/javascript">
$(function(){
	$('#resource_protocol').addClass('active');
	$('.btn-default').on('click',function(){
			$(this).addClass('btn-success').parent().siblings().find('a').removeClass('btn-success');
	})
	
	var courseStatus = "";
	var packageStatus = "";
	$.ajax({
		 url:rootPath+"/companyFunctionSet/queryCompanyProtocolExist",
		 type:"post",
		 async: false,
		 data:{"functionCode":"CLASS_POTOCOL_SET"},
		 dataType:"json",
		 success:function(jsonData){
			 if(jsonData && jsonData == "success"){
				 courseStatus = "success";
			 }
		 }
	 })
	 
	 $.ajax({
		 url:rootPath+"/companyFunctionSet/queryCompanyProtocolExist",
		 type:"post",
		 async: false,
		 data:{"functionCode":"CLASSPACKAGE_POTOCOL_SET"},
		 dataType:"json",
		 success:function(jsonData){
			 if(jsonData && jsonData == "success"){
				 packageStatus = "success";
			 }
		 }
	 })
	 
	 if(packageStatus == "success" && courseStatus == ""){
		 $('#course').hide();
		 $('#coursePackage').addClass('btn-success');
		 $('#all').hide();
		 searchProtocol2("1","");
	 }else if(packageStatus == "" && courseStatus == "success"){
		 $('#coursePackage').hide();
		 $('#course').addClass('btn-success');
		 $('#all').hide();
		 searchProtocol2("0","");
	 }
	
	 function searchProtocol2(type,page){
		var condition = {};
		condition.type = type;
		condition.page = page?page:1;
		$.ajax({
			url:rootPath+"/courseProtocolConfig/queryProtocolList",
			type:"post",
			data:condition,
			success:function(jsonData){
				$('#loadData').html(jsonData);
				$("td.status").each(function(i){
					if($(this).text()=="禁用"){
						$(this).css("color","red");
					}
				});
			},
		})
		
	}
});
</script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/resource/protocol/protocolList.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/resource/protocol/addOrEditProtocol.js"></script>
</body>
</html>