/**
 * Created by zhuo on 2015/12/14.
 */
(function($){
	var cfyId = "";//别名表id
	var $kcJin = "";//课程标签(启用禁用)
	var $kcJinTwo = "";//课程标签(启用禁用)
    //营销设置
    $(".qq-box").on("mouseenter",function(){
        if(!($(this).hasClass("qq-box-active"))){
            $(this).css("background-color","#eeeeee").find(".choose-btn").css("display","inline-block");
        };
    });
    $(".qq-box").on("mouseleave",function(){
        if(!($(this).hasClass("qq-box-active"))){
            $(this).css("background-color","#fff").find(".choose-btn").css("display","none");
        };
    });
    $(".choose-btn").on("click",function(){
        $(this).parents(".qq-box").addClass("qq-box-active").find(".choosed img").css("display","block");
        $(this).parents(".qq-box").css("background-color","#fff").find(".choose-btn").css("display","none");
        $(this).parents(".qbox").siblings().find(".qq-box").removeClass("qq-box-active").find(".choosed img").css("display","none");
        $(this).parents(".qbox").find(".qq-input").css("visibility","visible");
        $(this).parents(".qbox").siblings().find(".qq-input").css("visibility","hidden");
    });

    $(".help-font").on("mouseenter",function(){
        $(this).next().fadeIn(50);
    });
    $(".help-font").on("mouseleave",function(){
        $(this).next().fadeOut(50);
    });
    
    //禁用提示
    $(".right-side").on("click","em.zdyic",function(){
    	var self = this;
    	cfyId = $(this).attr("cfyId");
        if($(this).hasClass("open")){
        	$.confirm('禁用后将不显示该标签下的问答', function(b){
        		$(self).addClass("tagclass");
        		if(b){
        			FormQue.updateClassfyZdy(cfyId,null,0);
        	    	if($kcJin){
        	    		$kcJin.attr("del","0");
        	    	}
        	    	if($kcJinTwo){
        	         	$kcJinTwo.attr("del","0");
        	     	}
        	        $(".tagclass").removeClass("open").addClass("close").html("&#xe604;");
        	        $(".tagclass").parent(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
        	        $(".tagclass").removeClass("tagclass");
        		}else{
        			$(".warning-btn.tagclass").removeClass("tagclass");
        		}
        	})
        }else if($(this).hasClass("close")){
            $(this).removeClass("close").addClass("open").html("&#xe603;");
            $(this).parent(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
            FormQue.updateClassfyZdy(cfyId,null,1);
            return false;
        };
    });
    $(".right-side").on("click","em.kcic",function(){
    	var self = this;
    	cfyId = $(this).attr("cfyId");
    	$kcJin = $(this).parents(".course").find(".kName");
    	$kcJinTwo = $(this).parents(".course").find(".kcName");
        if($(this).hasClass("open")){
        	$.confirm('禁用后将不显示该标签下的问答', function(b){
        		$(self).addClass("tagclass");
        		if(b){
        			FormQue.updateClassfyZdy(cfyId,null,0);
        	    	if($kcJin){
        	    		$kcJin.attr("del","0");
        	    	}
        	    	if($kcJinTwo){
        	         	$kcJinTwo.attr("del","0");
        	     	}
        	        $(".tagclass").removeClass("open").addClass("close").html("&#xe604;");
        	        $(".tagclass").parent(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
        	        $(".tagclass").removeClass("tagclass");
        		}else{
        			$(".warning-btn.tagclass").removeClass("tagclass");
        		}
        	})
        }else if($(this).hasClass("close")){
            $(this).removeClass("close").addClass("open").html("&#xe603;");
            $(this).parent(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
            if(cfyId){
            	FormQue.updateClassfyZdy(cfyId,null,1);
            	if($kcJin){
            		$kcJin.attr("del","1");
            	}
                if($kcJinTwo){
                	$kcJinTwo.attr("del","1");
            	}
            }else{
            	var tkname = $(this).parents(".course").find(".sub-name-show").html();
            	var tkid = $(this).parents(".course").find(".sub-name-show").attr("tkid");
            	FormQue.addClassfy(tkid,tkname,2);
            }
            
            return false;
        };
    });

    //开关
    $(".tit-font em.kcStatus").off().on("click",function(){
        if($(this).hasClass("open")){
        	FormQue.changeStatus(0);
        	$("#kcInfo").html("");
            $(this).removeClass("open").addClass("close").html("&#xe604;");
            $(this).parent(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
            return false;
        }else if($(this).hasClass("close")){
        	FormQue.changeStatus(1,FormQue.loadKc());
            $(this).removeClass("close").addClass("open").html("&#xe603;");
            $(this).parent(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
            return false;
        };
    });
    
    $(".classify").on("mouseenter",function(){
        if(!($(this).hasClass("active"))){
            $(this).css("background-color","#ccc").find(".classify-tit,p").css("color","#fff");
            $(this).find("span").css("color","#00aaff");
        }
        return false;
    });
    $(".classify").on("mouseleave",function(){
        if(!($(this).hasClass("active"))){
            $(this).css("background-color","#fff").find(".classify-tit,p").css("color","#999");
            $(this).find("span").css("color","#999");
        }
        return false;
    });


    //课程分类
    //删除提示
    $(".padd-box").off().on("click",".del",function(){
    	cfyId = $(this).attr("cfyId");
        /*if(!($(this).hasClass("cance1"))){
            $(".delet").css("display","block")
        }*/
    	$.confirm("删除后该分类下的问答将只能在全部问答中找到，确认删除吗？",function(b){
    		if(b){
    			FormQue.delClassfy(cfyId);
    		}
    	})
    });
    $(".delet .n").off().on("click", function () {
        $(".delet").css("display","none")
    });
    $(".delet .y").off().on("click", function () {
    	FormQue.delClassfy(cfyId);
    });

    //修改方法
    function revise(obj){
        var val = $(obj).html();
        $(obj).hide();
        $(obj).next().show().val(val);
        $(obj).parents(".par").find(".revise").html("保存修改").addClass("blue-btn");
        $(obj).parents(".par").find(".del").html("取消").addClass("cance1");
    }
    //点击修改
    function changeName(obj){
        var _value = $(obj).parent(".par").find("label").html();
        $(obj).html("保存修改").addClass("blue-btn nameZdy");
        $(obj).parents(".par").find("label").hide();
        $(obj).parents(".par").find("input").show().val(_value);
        $(obj).next(".del").html("取消").addClass("cance1");
    }
    function changeNameKc(obj){
        var _value = $(obj).parent(".par").find("label").html();
        $(obj).html("保存修改").addClass("blue-btn nameKc");
        $(obj).parents(".par").find("label").hide();
        $(obj).parents(".par").find("input").show().val(_value);
        $(obj).next(".del").html("取消").addClass("cance1");
    }
    
    //保存
    function siveZdy(obj){
    	var upName = $(obj).parent(".padd-bar").find(".iName").val();
        var _val = $(obj).parents(".par").find("input").val();
        cfyId = $(obj).attr("cfyId");
        if(upName.length>10){
        	$('<div class="c-fa">'+ "自定义名称最多10个字" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			return;
        }
        console.log(cfyId+":"+upName);
        FormQue.updateClassfyZdy(cfyId,upName,null);
        $(obj).parent(".padd-bar").find(".lName").html(upName);
        $(obj).html("修改名称").removeClass("blue-btn");
        $(obj).parents(".par").find("input").hide();
        $(obj).parents(".par").find("label").show().html(_val);
        $(obj).next(".del").html("删除").removeClass("cance1");
        $(obj).removeClass("nameZdy");

    }
    
    function siveKc(obj){
    	var upName = $(obj).parent(".course").find(".iName").val();
        var _val = $(obj).parents(".par").find("input").val();
        cfyId = $(obj).attr("cfyId");
        if(upName.length>10){
        	$('<div class="c-fa">'+ "自定义名称最多10个字" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			return;
        }
        console.log(cfyId+":"+upName);
        FormQue.updateClassfyZdy(cfyId,upName,null);
        $(obj).parent(".course").find(".kName").html(upName);
        $(obj).html("修改名称").removeClass("blue-btn");
        $(obj).parents(".par").find("input").hide();
        $(obj).parents(".par").find("label").show().html(_val);
        $(obj).removeClass("nameKc");

    }
    //取消
    function cancel(obj){
        var _val = $(obj).parents(".par").find("input").val();
        $(obj).prev(".revise").html("修改名称").removeClass("blue-btn");
        $(obj).parents(".par").find("input").hide();
        $(obj).parents(".par").find("label").show();
        $(obj).html("删除").removeClass("cance1");
    }
    $(".padd-box").on("click",".lName",function(){
    	$(this).parent("form").next(".zdyName").addClass("nameZdy");
        revise(this);
        return false;
    });
    $(".padd-box").on("click",".kName",function(){
    	var del = $(this).attr("del");
    	if(del == 1){
    		$(this).parent("form").next(".kcName").addClass("nameKc");
    		revise(this);
    	}
        return false;
    });
    $(".padd-box").on("click",".zdyName",function(){
        if(!($(this).hasClass("blue-btn"))){
            changeName(this);
            return false;
        }
    });
    $(".padd-box").on("click",".nameZdy",function(){
        siveZdy(this);
        return false;
    });
   
    $(".padd-box").on("click",".kcName",function(){
    	var del = $(this).attr("del");
        if(!($(this).hasClass("blue-btn")) && del == 1){
            changeNameKc(this);
            return false;
        }
    });
    $(".padd-box").on("click",".nameKc",function(){
        siveKc(this);
        return false;
    });
    $(".padd-box").on("click",".cance1",function(){
        cancel(this);
        return false;
    });
    //添加分类
    $(".add-btn").on("click",function(){
        $(".new-add").slideDown(50);
    });
    $(".dels").on("click",function(){
        $(".new-add").slideUp(50);
    });
    $(".saveZdy").on("click",function(){
    	var name = $("#definitionAddZdy").val();
    	if(name.length>10){
    		$('<div class="c-fa">'+ "自定义名称最多10个字" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			return;
    	}
    	FormQue.addClassfy(0,name,1);
    })
})(jQuery);
