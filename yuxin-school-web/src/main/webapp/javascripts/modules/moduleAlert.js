$(function(){
	$.alert=function(msg,callback){
		msg="<p>"+msg+"</p>";
		var tips='<div class="layer-tips alert" style="display: none;">'+
	        '<div class="layer-tips-title">提示信息 <i class="close iconfont"></i></div>'+
	        '<div class="layer-tips-content">'+msg+'</div>'+
	        '<div class="layer-tips-btns">'+
	        '    <a href="javascript:;" class="btn btn-mini btn-success alert_ok">确定</a>'+
	        '</div>'+
	    '</div>'+
	    '<div class="layer-tips-bg alert" style="display: none;"></div>';
		$(document).find(".layer-tips").remove();
		$(document).find(".layer-tips-bg").remove();
		$(document).find("body").append(tips);
		$('.layer-tips-bg.alert').fadeIn(200,function(){
            $('.layer-tips.alert').fadeIn(200);
        });
		$(document).on("click.ok.alert",".alert_ok",function(){
			if(callback){
				callback();
			}
			$(this).parents('.layer-tips').fadeOut(200,function(){
                $('.layer-tips-bg.alert').fadeOut(200);
            });
		});
	};
	$.confirm=function(msg,callback){
		var tips='<div class="layer-tips confirm" style="display: none;">'+
	        '<div class="layer-tips-title">请确认 <i class="close iconfont"></i></div>'+
	        '<div class="layer-tips-content">'+msg+'</div>'+
	        '<div class="layer-tips-btns">'+
	        '    <a href="javascript:;" class="btn btn-mini btn-success confirm_ok">确定</a>'+
	        '   <a href="javascript:;" class="btn btn-mini btn-default confirm_cancle">取消</a>'+
	        '</div>'+
	    '</div>'+
	    '<div class="layer-tips-bg confirm" style="display: none;"></div>';
		$(document).find(".layer-tips").remove();
		$(document).find(".layer-tips-bg").remove();
		$(document).find("body").append(tips);
		$('.layer-tips-bg').fadeIn(200,function(){
            $('.layer-tips.confirm').fadeIn(200);
        });
        //确定
		$(document).off("click.ok.confirm").on("click.ok.confirm",".confirm_ok",function(){
			$(this).parents('.layer-tips').fadeOut(200,function(){
                $('.layer-tips-bg.confirm').fadeOut(200);
            });
			if(callback){
				callback(true);
			};
		});
		//取消
		$(document).off("click.cancle.confirm").on("click.cancle.confirm",".confirm_cancle",function(){
			$(this).parents('.layer-tips').fadeOut(200,function(){
                $('.layer-tips-bg.confirm').fadeOut(200);
            });
            if(callback){
				callback(false);
			};
		});
	};
	
});