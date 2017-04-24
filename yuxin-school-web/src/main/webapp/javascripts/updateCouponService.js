;(function($){
	
    var Page={
		init : function(){
			var useWay=$("#useWay").val();
			var CfsStatus=$("#CfsStatus").val();
			$selectSubMenu('org_service');
	        Page.couponsConfigInit(useWay);
	        Page.selectCounponServiceInit();
	        Page.selectCounponConfigInit();
	        Page.checkCoiponService($("#CfsStatus").val());
	        Page.buttonInit();
		},
		couponsConfigInit : function(type){
			if(type=="1"){//可叠加
				Page.chooseIsDoubleUse();
			}else if(type=="0"){//不可叠加
				Page.chooseIsNotDoubleUse();
			}
		},
		chooseIsNotDoubleUse : function(){
			//isDoubleUse
			$("#isDoubleUse").removeClass("active");
			$("#isDoubleUse").css("background-color","rgb(255, 255, 255)");
			$("#isDoubleUse-tit").css("color","rgb(153, 153, 153)");
			$("#isDoubleUse-img").css("display","");
			//isNotDoubleUse
			$("#isNotDoubleUse").addClass("active");
			$("#isNotDoubleUse").css("background-color","");
			$("#isNotDoubleUse-tit").css("color","");
			$("#isNotDoubleUse-img").css("display","block");
			//useWay==0
			$("#useWay").val(0);
		},
		chooseIsDoubleUse : function(){
			//isNotDoubleUse
			$("#isNotDoubleUse").removeClass("active");
			$("#isNotDoubleUse").css("background-color","rgb(255, 255, 255)");
			$("#isNotDoubleUse-tit").css("color","rgb(153, 153, 153)");
			$("#isNotDoubleUse-img").css("display","");
			//isDoubleUse
			$("#isDoubleUse").addClass("active");
			$("#isDoubleUse").css("background-color","");
			$("#isDoubleUse-tit").css("color","");
			$("#isDoubleUse-img").css("display","block");
			//useWay==1
			$("#useWay").val(1);
		},
		updateCouponConfigOrService : function(type,Type,obj,objType){
			$.ajax({
				url : rootPath + (Type=="config"?"/company/updateCouponConfig":"/company/updateCouponService"),
				type:"post",
				data:{"type":Type=="config"?type:(type==1?0:1)},
				dataType:"text",
				success:function(data){
					if(data){
						if(Type=="config"){
						}else{
							$("#CfsStatus").val(type==1?0:1);
							if(objType=="open"){
							$(obj).removeClass("open").addClass("close").html("&#xe604;");
							$(obj).parents(".screen-right-title").find(".i").removeClass("open").addClass("close").text("已禁用");
							}else{
							$(obj).removeClass("close").addClass("open").html("&#xe603;");
							$(obj).parents(".screen-right-title").find(".i").removeClass("close").addClass("open").text("已启用");}
						}
						$.msg("修改成功！");
					}else{
						$.msg("修改失败！");
						return false;
					}
				},
				error:function(){
					$.msg("修改失败！");
					window.location.href=rootPath+"/company/setCouponService";
					return false;
				}
			});
			Type=="config"?"":Page.checkCoiponService($("#CfsStatus").val()==1?0:1);
		},
		selectCounponServiceInit : function(){
			//开关
	        $(".screen-right-title em.normal").off().on("click",function(){
	        	var status=$("#CfsStatus").val();
	            if($(this).hasClass("open")){
	            	Page.updateCouponConfigOrService(status,"service",this,"open");
	                return false;
	            }else if($(this).hasClass("close")){
	            	Page.updateCouponConfigOrService(status,"service",this,"close");
	                return false;
	            };
	        });
			
		},
		selectCounponConfigInit : function(){
		   //选择叠加方式
		   $("#isNotDoubleUse").on("click",function(){
			   Page.chooseIsNotDoubleUse();
			   $(".save-useway").click();
	       });
	       $("#isDoubleUse").on("click",function(){
	    	 //  $(".rule-bottom").css("color","").css("color","rgb(153, 153, 153)");
	    	   Page.chooseIsDoubleUse();
	    	   $(".save-useway").click();
	       });
		},
		checkCoiponService : function(status){
			if(status=="1"){
				$(".coupon-code").show();
			}else{
				$(".coupon-code").hide();
			}
		},
		buttonInit : function(){
			$(".save-useway").on("click",function(){
				var type=$("#useWay").val();
				Page.updateCouponConfigOrService(type,"config");
			})
			$(".esc-useway").on("click",function(){
				window.location.href=rootPath+"/company/setCouponService";
			})
			$(".hcancle").on('click',function(){
				 window.location.href=rootPath+"/company/companyService";
			});
			$(".couponService").on('click',function(){
				 window.location.href=rootPath+"/company/setCouponService";
			});
		}
    }
    $(document).ready(function(){
		Page.init();
	});
	    
})(jQuery)