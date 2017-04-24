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
	                   <span class="t">支付宝付款</span>
	               </div>
	           </div>
	           <p class="prompt-font">说明：企业即时到账：开通后无需转账确认。如需开启手机端支付宝即时到账需要自行申请支付宝手机端支付服务。<br/>
            个人担保交易：以个人名义申请担保交易。学员购买后需要学员在 支付宝中确认收货。</p>
	           <div class="pay-box">
	               <div class="pay-btn qi a">
	                   <p class="iconfont">&#xe672;</p>
	                   <p class="pay-way">企业即时到账</p>
	                   <div class="shut-box">
	                       <div class="shut-btn" jinType="PAY_TYPE_ZFB">禁用</div>
	                       <div class="yes-btn select" delType="PAY_TYPE_GRDB" addType="PAY_TYPE_ZFB">选择</div>
	                   </div>
	                   <img src="<%=rootPath%>/images/choosed-1_04.png" alt=""/>
	               </div>
	                <div class="pay-btn wy w">
	                   <p class="iconfont">&#xe672;</p>
	                   <p class="pay-way">支付宝网银支付</p>
	                   <div class="shut-box">
	                       <div class="shut-btn" jinType="PAY_TYPE_ZFBWY">禁用</div>
	                       <div class="yes-btn select" delType="PAY_TYPE_SXY" addType="PAY_TYPE_ZFBWY">选择</div>
	                   </div>
	                   <img src="<%=rootPath%>/images/choosed-1_04.png" alt=""/>
	               </div>
	               <div class="pay-btn ge b">
	                   <p class="iconfont">&#xe66f;</p>
	                   <p class="pay-way">个人担保交易</p>
	                   <div class="shut-box">
	                   		<div class="shut-btn" jinType="PAY_TYPE_GRDB">禁用</div>
	                   		<div class="yes-btn select" delType="PAY_TYPE_ZFB" addType="PAY_TYPE_GRDB">选择</div>
	                   </div>
	                   <img src="<%=rootPath%>/images/choosed-1_04.png" alt=""/>
	               </div>
	           </div>
	           <div class="clear"></div>
	       </div>
	       <div>
	           <div class="title-box">
	               <div class="tit-font">
	                   <span class="t">支付宝扫码转账</span>
	               </div>
	           </div>
	           <p class="prompt-font">上传个人支付宝收款二维码。学员转账后需要管理员在<span><a href="<%=rootPath%>/payOrder/toOrder" style="color: #ff0000;">运营-财务-转账确认</a></span>中进行确认</p>
	           <div class="pay-box">
	               <div class="pay-btn zfbZz c">
	                   <p class="iconfont">&#xe673;</p>
	                   <p class="pay-way">个人支付宝转账</p>
	                   <div class="yes-btn start">启用</div>
	                   <div class="ban-box">
	                       <div class="ban-btn" jinType="PAY_TYPE_ZFB_ZZ">禁用</div>
	                       <div class="set-btn" onclick="location.href='<%=rootPath%>/payConfig/showZFBZZ'">设置</div>
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
						location.href=rootPath+"/payConfig/showPayChoice";
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
		$(".zfb").addClass("active");
		
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
				if(list[i] == 'PAY_TYPE_ZFBWY'){
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
		if(wy == 'wy'){
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
				location.href=rootPath+"/payConfig/showZFBWY"
			}
			if($(this).parents(".pay-btn").hasClass("ww")){
				location.href=rootPath+"/payConfig/showZFBWY"
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