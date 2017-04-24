// 注册相关内容
(function($){
	$(function(){
		//$(".footer").addClass("footer-fixed");
		 $('.input-ctrol').on('click focus',function(){
		        $(this).addClass('active');
		    }).on('blur',function(){
		        if( $(this).val() == '' ){
		            $(this).removeClass('active');
		        }
		    });

		    // 关闭按钮
		    $('.reg-close').on('click',function(){
		        $(this).parents('.register').fadeOut(200,function(){
		            $('.mark-bg').fadeOut(200);
		        });
		    });

		    // 复选框
		    $('.pro').on('click',function(){
		        var o = $(this).find('i');

		        o[o.hasClass('active')?'removeClass':'addClass']('active');
		    });
	})
})(jQuery)