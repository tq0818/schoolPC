(function($){
	/**
	 * @param title
	 * @param message
	 * @param callback  function(result) result为true 表示是点击了确认 为false表示是点击了取消
	 */
	var title,message,callback;
	var $confirm=function(){
		 
		if(arguments.length==1){
			message=arguments[0];
		}else if(arguments.length==2){
			var arg1=arguments[0];
			var arg2=arguments[1];
			if(typeof arg1 =="string"){
				title=arg1;
				message=arg2;
			}else if(typeof arg1 =="object"){
				message=arg1;
				callback=arg2;
			}
		}else if(arguments.length==3){
			title=arguments[0];
			message=arguments[1];
			callback=arguments[2];
			console.log(title,message);
		}
		$('body').find('.register').remove();
		$('body').find('.mark-bg').remove();
		var html='<div class="register none" style="height:auto;"><span class="reg-close confirm_close"></span>';
		if(title){
			html+='<div class="reg-title" style="border:none;padding:20px 30px;"><h2 class="h5">'+title+'</h2></div>';
		}
       html+='<div class="reg-main"><div class="reg-form" style="font-size:15px;min-height:60px;">'+message+'</div>'+
       '<div class="reg-bottom" style="text-align:center;border:none;"><a href="javascript:;" class="btn btn-success confirm_ok">确定</a>&nbsp;&nbsp;<a href="javascript:;" class="btn btn-default confirm_cancle">取消</a></div></div></div>';
	   var mark='<div class="mark-bg none"></div>';
	   $(mark).appendTo('body').fadeIn(200,function(){
	   		$(html).appendTo('body').fadeIn(200);
	   });
	   
	   $(document).on('click.btn.cancle','.confirm_cancle',function(){
		   $('body').find('.register').fadeOut(200,function(){
				$('body').find('.mark-bg').fadeOut(200);
			})
	   		if(callback){
	   			callback(false);
	   		}
	   });
	     $(document).on('click.btn.close','.confirm_close',function(){
		   $('body').find('.register').fadeOut(200,function(){
				$('body').find('.mark-bg').fadeOut(200);
			})
	   		if(callback){
	   			callback(false);
	   		}
	   });
	   
	}
	$(document).on('click.btn.ok','.confirm_ok',function(){
		   $('body').find('.register').fadeOut(200,function(){
				$('body').find('.mark-bg').fadeOut(200);
			})
	   		if(callback){
	   			callback(true);
	   		}
	   });
	window.$confirm=$confirm;
	
})(jQuery)