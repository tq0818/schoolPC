<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>展示图</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
	<link rel="stylesheet" href="<%=rootPath %>/stylesheets/manager.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
	<link rel="stylesheet" href="<%=rootPath%>/stylesheets/css/muXinXin.css"/>
	<link rel="stylesheet" href="<%=rootPath%>/stylesheets/css/aspirations.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/starfun.css" />
	<style>
		.dtr{margin: 0 2% 0 2%;padding: 1px;font-size: 14px;line-height: 28px;margin-top: 10px;}
		.ctext{cursor: pointer;}
	</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
	 <%@include file="/WEB-INF/jsp/studentStar/commonLeft.jsp"%>
	 <div class="right-side">
	     <div>
			   <div class="title-box">
				   <div class="tit-font">
					   <span class="t">展示图</span>
				   </div>
			   </div>
			 <div class="dtr state clear L-state">
				 <div class="starImg">
					 <div class="titleImg">
						 <c:choose>
							 <c:when test="${pic!=null}">
								 <img src="${imgHost}${pic}" style="width: 468px;height: 180px;">
							 </c:when>
							 <c:otherwise>
								 <img src="<%=rootPath%>/images/kong_img.png" alt="">
							 </c:otherwise>
						 </c:choose>
					 </div>
					 <div class="uploadImg asButton">
						 <button>上传展示图</button>
						 <input type="hidden" name="userPic"/>
						 <input type="file"  name="imgData" id="imgData" class="intFile"  onchange="uploadImg()"/>
						 <span>请上传png、gif、jpg格式的图片文件。图片建议大写1920*340</span>
					 </div>
				 </div>
			 </div>
			 <%-- <div id="tb" style="margin-top: 40px;">
				 <p align="center" style="margin-top: 50px">
					 <a href="<%=rootPath%>/company/companyService"
						class="btn btn-sm btn-default">返回</a>
					 <a href="javascript:;" class="btn btn-sm btn-primary" id="msg-save">保存</a>
				 </p>
			 </div> --%>
	           <div class="clear"></div>
	       </div>
	 </div>
</div>
<div class="layer-tips layer-tips-b L-allowAdmissionsTc" id="alert-msg" style="border: 1px solid #ccc;">
	<div class="layer-tips-title">
		提示 <i class="close iconfont">&#xe610;</i>
	</div>
	<p class="title-prompt"></p>
	<div class="layer-tips-btns " >
		<a href="javascript:;" class="btn btn-mini btn-default btn-info" >确定</a>
	</div>
</div>
<!--背景阴影-->
<div class="layer-tips-bg"></div>
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript">
	function alertMsg(msg){
		$('#alert-msg').find(".title-prompt").html(msg);
		$('.layer-tips-bg').css('display', 'block');
		$('#alert-msg').css('display', 'block');
	}
	$(function() {
		$selectSubMenu('org_service');
		$("li.zst").addClass("active");
		$("#msg-save").click(function(){
			var data={};
			data.picUrl=$(":hidden[name='userPic']").val();
			if(data.picUrl==""){
				alertMsg("请上传展示图");
				return;
			}
			$.ajax({
				url: rootPath + "/studentStar/updateImg",
				async: true,
				data:data,
				dataType: 'json',
				type: "POST",success: function (result) {
					if(result.status="success"){
						alertMsg("保存成功");
					}else{
						alertMsg("保存失败:"+result.msg);
						return;
					}
				}
			})
		});


		$('.close,.layer-tips-btns .btn').click(function(){
			$(this).parents('.L-allowAdmissionsTc').css('display','none');
			$('.layer-tips-bg').css('display','none');
		});
	});

	function uploadImg(){
		$.ajaxFileUpload({
			url: rootPath + "/studentStar/updateImg",
			secureuri: false, // 安全协议
			async: false,
			fileElementId: 'imgData',
			dataType: 'json',
			type: "POST",
			success: function (data) {
				if(data.status == 'error'){
					$.msg(data.msg);
					return;
				}
				$(".titleImg").html('<img style="width: 468px;height: 180px;" src="'+data.url+'"/>');
				$(":hidden[name='userPic']").val(data.picPath);
				$.msg('上传成功');
			},
			error: function (arg1, arg2, arg3) {
				//console.log(arg1);
			}
		});
	}
</script>
</body>
</html>