<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>设置支付信息</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
	 <%@include file="/WEB-INF/jsp/company/payConfig/commonLeft.jsp"%>
	 <div class="right-side">
	     <div>
	           <div class="title-box">
	               <div class="tit-font">
	                   <span class="t">微信付款</span>
	               </div>
	           </div>
	           <p class="prompt-font">说明：微信扫码转账：上传个人微信收款二维码。学员转账后需要管理员在<span><a href="<%=rootPath%>/payOrder/toOrder" style="color: #ff0000;">运营-财务-转账确认</a></span>。进行确认<br/>
            微信公众号：需要企业申请微信公众号并申请微信支付。</p>
	           <div class="pay-box">
	               <div class="pay-btn sm a">
	                   <p class="iconfont">&#xe672;</p>
	                   <p class="pay-way">微信扫码转账</p>
	                   <div class="yes-btn start" url="/payConfig/showWX">启用</div>
	                   <div class="ban-box">
	                       <div class="ban-btn" jinType="PAY_TYPE_WX_PERSON">禁用</div>
	                       <div class="set-btn" url="/payConfig/showWX">设置</div>
	                   </div>
	               </div>
	               <div class="pay-btn gz b">
	                   <p class="iconfont">&#xe66f;</p>
	                   <p class="pay-way">微信公众号</p>
	                   <div class="yes-btn start" url="/payConfig/showWXGZ">启用</div>
	                   <div class="ban-box">
	                       <div class="ban-btn" jinType="PAY_TYPE_WX_GZH">禁用</div>
	                       <div class="set-btn" url="/payConfig/showWXGZ">设置</div>
	                   </div>
	               </div>
	           </div>
	           <div class="clear"></div>
	       </div>
	 </div>
</div>
<input type="hidden" id="companyId" value="${company.id}" />
<input type="hidden" id="payType" value="${cpc.payType}" />
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script type="text/javascript">
	function updatePayType (type,addType){
		$.ajax({
			url : rootPath+"/payConfig/updatePayType",
			type : "post",
			data : {"payType":type,"addType":addType},
			beforeSend : function(XMLHttpRequest) {
				$(".loading").show();
				$(".loading-bg").show();
			},
			success : function(data){
				if(data=="success"){
					$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
						location.href=rootPath+"/payConfig/showWxChoice";
					});
				}
			},
			complete : function(XMLHttpRequest, textStatus) {
				$(".loading").hide();
				$(".loading-bg").hide();
			}
		});
	}
	$(function() {
		$selectSubMenu('org_service');
		$(".wx").addClass("active");
		
		var selectedType = $("#payType").val();
		var wxSM = "";
		var wxGZ = "";
		if(selectedType){
			var list = selectedType.split(',');
			for (var i = 0; i < list.length; i++) {
				if(list[i] == 'PAY_TYPE_WX_PERSON'){
					wxSM = 'sm';
				}
				if(list[i] == 'PAY_TYPE_WX_GZH'){
					wxGZ = 'gz';
				}
			}
		}
		if(wxSM == 'sm'){
			$(".sm").removeClass("e").addClass("ee");
			$(".sm").find(".start").hide();
			$(".sm").find(".ban-box").show();
			
		}
		if(wxGZ == 'gz'){
			$(".gz").removeClass("e").addClass("ee");
			$(".gz").find(".start").hide();
			$(".gz").find(".ban-box").show();
		}
		
		$(".start,.set-btn").click(function(){
			var url = $(this).attr("url");
			location.href=rootPath+url;
		});
		$(".ban-btn").click(function(){
			var jinType = $(this).attr("jinType");
			updatePayType(jinType,"");
		});
	})
</script>
</body>
</html>