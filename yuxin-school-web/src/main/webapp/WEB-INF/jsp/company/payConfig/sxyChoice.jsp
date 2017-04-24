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
      <style type="text/css">
	    div.w:hover{
	    background-color: #f6f6f6;
	    color: #333;
		}
		div.w:hover .iconfont{
		    color: #f8c900;
		}
		div.w:hover .yes-btn{
		    border: 1px solid #666;
		}
		div.ww{
		    border: 1px solid #0099ff;
		    color: #0099ff;
		}
		div.ww .iconfont{
		    color: #f8c900;
		}
		div.ww .yes-btn{
		    border: 1px solid #0099ff;
		}
    </style>
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
	                   <span class="t">网银支付</span>
	               </div>
	           </div>
	           <p class="prompt-font">说明：网银支付功能是由与我公司合作的第三方网上支付平台-首信易提供服务，如需开通请与我们的售后技术支持联系，我司将协助您完成相关设置</p>
	           <div class="pay-box">
	               <div class="pay-btn wy w">
	                   <p class="iconfont">&#xe672;</p>
	                   <p class="pay-way">网银支付</p>
	                   <div class="shut-box">
	                       <div class="shut-btn" jinType="PAY_TYPE_SXY">禁用</div>
	                       <div class="yes-btn select" delType="PAY_TYPE_ZFBWY" addType="PAY_TYPE_SXY">选择</div>
	                   </div>
	                   <img src="<%=rootPath%>/images/choosed-1_04.png" alt=""/>
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
						location.href=rootPath+"/payConfig/showSXYChoice";
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
		$(".sxy").addClass("active");
		
		var selectedType = $("#payType").val();
		console.log(selectedType);
		var qiOrpge = "";
		var zfbZz = "";
		var wy="";
		if(selectedType){
			var list = selectedType.split(',');
			for (var i = 0; i < list.length; i++) {
				if(list[i] == 'PAY_TYPE_ZFB'){
					qiOrpge = 'qi';
				}
				if(list[i] == 'PAY_TYPE_GRDB'){
					qiOrpge = 'ge';
				}
				if(list[i] == 'PAY_TYPE_ZFB_ZZ'){
					zfbZz = 'zz';
				}
				if(list[i] == 'PAY_TYPE_SXY'){
					wy = 'wy';
				}
			}
		}
		console.log(qiOrpge+":"+zfbZz);
		if(qiOrpge == 'qi'){
			$(".qi").removeClass("a").addClass("aa");
			$(".qi").find(".select").html("设置");
			$(".ge").find("img").hide();
	        $(".qi").find(".shut-btn").css("display","inline-block");
	        $(".ge").find(".shut-btn").css("display","none");
	        $(".qi").find("img").show();
		}
		if(qiOrpge == 'ge'){
			$(".ge").removeClass("b").addClass("bb");
			$(".ge").find(".select").html("设置");
			$(".qi").find("img").hide();
	        $(".ge").find(".shut-btn").css("display","inline-block");
	        $(".qi").find(".shut-btn").css("display","none");
	        $(".ge").find("img").show();
		}
		if(zfbZz == 'zz'){
			$(".zfbZz").removeClass("c").addClass("ccc");
			$(".start").hide();
			$(".ban-box").show();
		}
		if(wy=="wy"){
			$(".wy").removeClass("w").addClass("ww");
			$(".wy").find(".select").html("设置");
	        $(".wy").find(".shut-btn").css("display","inline-block");
	        $(".wy").find("img").show();
		}
		var delType = "";
		var addType = "";
		$(".select").click(function(){
			if($(this).parents(".pay-btn").hasClass("aa")){
				location.href=rootPath+"/payConfig/showZFB"
	        }
			if($(this).parents(".pay-btn").hasClass("a")){
	           /*  $(this).parent(".pay-btn").removeClass("a").addClass("aa");
	            delType = $(this).attr("delType");
	            addType = $(this).attr("addType");
	            console.log(delType+":"+addType)
	            updatePayType(delType,addType);
	            $(this).html("设置");
	            if($(this).parent(".pay-btn").siblings().hasClass("bb")){
	                $(this).parent(".pay-btn").siblings().removeClass("bb").addClass("b");
	                $(this).parent(".pay-btn").siblings().find(".select").html("选择");
	            } */
	            location.href=rootPath+"/payConfig/showZFB"
	        }
			
			if($(this).parents(".pay-btn").hasClass("bb")){
				location.href=rootPath+"/payConfig/showGRDB"
	        }
			if($(this).parents(".pay-btn").hasClass("b")){
	            /* $(this).parent(".pay-btn").removeClass("b").addClass("bb");
	            delType = $(this).attr("delType");
	            addType = $(this).attr("addType");
	            console.log(delType+":"+addType)
	            updatePayType(delType,addType);
	            $(this).html("设置");
	            if($(this).parent(".pay-btn").siblings().hasClass("aa")){
	                $(this).parent(".pay-btn").siblings().removeClass("aa").addClass("a");
	                $(this).parent(".pay-btn").siblings().find(".select").html("选择");
	            } */
				location.href=rootPath+"/payConfig/showGRDB"
	        }
			if($(this).parents(".pay-btn").hasClass("w")){
				location.href=rootPath+"/payConfig/showSXY"
			}
			if($(this).parents(".pay-btn").hasClass("ww")){
				location.href=rootPath+"/payConfig/showSXY"
			}
		});
		$(".ban-btn").on("click",function(){
			var jinType = $(this).attr("jinType");
			
	        if($(this).parents(".pay-btn").hasClass("ccc")){
	            updatePayType(jinType,"");
	        }
			if(jinType == 'PAY_TYPE_ZFB_ZZ'){
				updatePayType(jinType,"");
			}
	        return false;
	    });
		$(".shut-btn").click(function(){
			var jinType = $(this).attr("jinType");
			updatePayType(jinType,"");
		});
		$(".start").on("click",function(){
	        location.href=rootPath+"/payConfig/showZFBZZ";
	    });
	})
</script>
</body>
</html>