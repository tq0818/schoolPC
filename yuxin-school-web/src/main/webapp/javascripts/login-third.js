;(function($){
	
	var first=0;
    var Page={
		init:function(){
			Page.btnInit();
			Page.selectInit();
		},
		selectInit:function(){
			var multiOnline=$("#multiOnline").val();
			var thirdLoginFlag=$("#thirdLoginFlag").val();
			var qqLogin=$("#qqLogin").val();
			var wbLogin=$("#wbLogin").val();
			var wxLogin=$("#wxLogin").val();
			var mobileKind=$("#mobileKind").val();
			if(qqLogin==1){$(".QQ").click();}
			if(wbLogin==1){$(".WB").click();}
			if(wxLogin==1){$(".WX").click();}
			if(mobileKind==1){
				$(".login-mobile .pay-btn").removeClass("ccc");
				$(".login-mobile .must").addClass("ccc");
			}else{
				$(".login-mobile .pay-btn").removeClass("ccc");
				$(".login-mobile .notmust").addClass("ccc");
			}
			if(thirdLoginFlag==1){
				$(".thirdLoginFlagDiv").show();
			}
			first++;
		},
    	btnInit:function(){
    		var _this = this;
    		$selectSubMenu('org_service');
     		$selectSubMenus('system_stu_log');
    		//点击启用
    	    $(".start").on("click",function(){
    	        if($(this).parent(".pay-btn").hasClass("c")){
    	            $(this).hide();
    	            $(this).next().show();
    	            $(this).nextAll(".choose-img").show()
    	            $(this).parent(".pay-btn").removeClass("c").addClass("ccc");
    	            
    	            //跳转页面
    	            if(first>0){
    	            	location.href=rootPath+"/companyFunctionSet/setThirdLogin/"+$(this).attr("startName");
    	            }
    	        }
    	        return false;
    	    });
    	    //点击禁用
    	    $(".ban-btn").on("click",function(){
    	        if($(this).parents(".pay-btn").hasClass("ccc")){
    	            $(this).parent(".ban-box").hide();
    	            $(this).parents(".pay-btn").find(".start").show();
    	            $(this).parents(".pay-btn").removeClass("ccc").addClass("c");
    	            $(this).parents(".pay-btn").find(".choose-img").hide()
    	            
    	            //禁用
    	            Page.delThirdLoginStatus($(this).attr("delName"));
    	        }
    	        return false;
    	    });
    	    //    点击绑定手机号
    	    $(".login-mobile").on("click",".pay-btn",function(){
    	        $(".login-mobile .pay-btn").removeClass("ccc");
    	        $(this).addClass("ccc");
    	        
    	        $("#mobileKind").val($(this).attr("kind"));
    	        Page.saveThirdLoginStatus();
    	    });
    	    //开关
    	    $(".screen-right-cont em.normal").off().on("click",function(){
    	    	var kind = $(this).next().attr("kind");
    	        if($(this).hasClass("open")){
    	            $(this).removeClass("open").addClass("close").html("&#xe604;");
    	            $(this).parents(".l-title").find(".i").removeClass("open").addClass("close").text("已禁用");
    	            if(kind=="thirdLoginFlag"){//第三方登录开关
    	            	$(".thirdLoginFlagDiv").hide();
    	            	$("#thirdLoginFlag").val(0);
    	            }
    	            if(kind == 'multiOnline'){//多端登录开关
    	            	$("#multiOnline").val(0);
    	            }
    	            if(kind == 'useLoginPage'){
    	            	$("#useLoginPage").val(0);
    	            }
    	            Page.saveThirdLoginStatus();
    	            return false;
    	        }else if($(this).hasClass("close")){
    	            $(this).removeClass("close").addClass("open").html("&#xe603;");
    	            $(this).parents(".l-title").find(".i").removeClass("close").addClass("open").text("已启用");
    	            if(kind == "thirdLoginFlag"){//第三方登录开关
    	            	$(".thirdLoginFlagDiv").show();
    	            	$("#thirdLoginFlag").val(1);
    	            }
    	            if(kind == 'multiOnline'){//多端登录开关
    	            	$("#multiOnline").val(1);
    	            }
    	            if(kind == 'useLoginPage'){
    	            	$("#useLoginPage").val(1);
    	            }
    	            Page.saveThirdLoginStatus();
    	            return false;
    	        };
    	    });
    	    
    	    _this.checkGlobalLogin();
    	    $('#global').on('click',_this.setGlobalLogin);
    	},
    	saveThirdLoginStatus:function(){
    		var data={};
    		data.multiOnline=$("#multiOnline").val();
    		data.thirdLoginFlag=$("#thirdLoginFlag").val();
    		data.useLoginPage = $('#useLoginPage').val();
    		data.bingMobile=$("#mobileKind").val();
    		$.ajax({
    			url : rootPath + "/companyLoginConfig/saveThirdLoginStatus",
    			type : "post",
    			data : data,
    			success : function(data){
    				if(data.msg="success"){
    					$.msg("操作成功！")
    				}else{
    					$.msg("操作失败，请刷新页面重新操作！")
    				}
    			}
    		});
    	},
    	delThirdLoginStatus:function(delName){
    		$.ajax({
    			url : rootPath + "/companyLoginConfig/delThirdLoginStatus",
    			type : "post",
    			data : {"delName":delName},
    			success : function(data){
    				if(data.msg="success"){
    					$.msg("操作成功！")
    				}else{
    					$.msg("操作失败，请刷新页面重新操作！")
    				}
    			}
    		});
    	},
    	checkGlobalLogin : function(){
    		$.ajax({
    			url : rootPath + "/companyFunctionSet/globalLogin",
    			type : "post",
    			success : function(data){
    				if(data.flag){
    					$('#global').prop('checked','checked');
    				}
    			}
    		});
    	},
    	setGlobalLogin : function(){
    		$.ajax({
    			url : rootPath + "/companyFunctionSet/globalLogin",
    			type : "post",
    			data : {"status":$('#global').is(':checked')?1:0},
    			success : function(data){
    				if(data.flag){
    					$.msg("操作成功！")
    				}else{
    					$.msg("操作失败，请刷新页面重新操作！")
    				}
    			}
    		});
    	}
    }
    $(document).ready(function(){
		Page.init();
	});
})(jQuery)
