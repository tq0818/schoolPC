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
	                   <span class="t">银行汇款</span>
	               </div>
	           </div>
	           <p class="prompt-font">说明：输入银行账号。学员转账后需要管理员在<span><a href="<%=rootPath%>/payOrder/toOrder" style="color: #ff0000;">运营-财务-转账确认</a></span>中进行确认</p>
	           <div class="pay-box">
	               <div class="pay-btn bank d">
	                   <p class="iconfont">&#xe674;</p>
	                   <p class="pay-way">银行汇款</p>
	                   <div class="yes-btn start" url="/payConfig/showBank">启用</div>
	                   <div class="ban-box">
	                       <div class="ban-btn" jinType="PAY_TYPE_REMIT">禁用</div>
	                       <div class="set-btn" url="/payConfig/showBank">设置</div>
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
						location.href=rootPath+"/payConfig/showBankChoice";
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
		$(".yhhk").addClass("active");
		
		var selectedType = $("#payType").val();
		console.log(selectedType);
		var yhhk = "";
		if(selectedType){
			var list = selectedType.split(',');
			for (var i = 0; i < list.length; i++) {
				if(list[i] == 'PAY_TYPE_REMIT'){
					yhhk = 'yhhk';
				}
			}
		}
		console.log(yhhk+":"+yhhk);
		if(yhhk == 'yhhk'){
			$(".bank").removeClass("d").addClass("dd");
			$(".bank").find(".start").hide();
			$(".bank").find(".ban-box").show();
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