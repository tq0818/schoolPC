(function($){
	$.fn.showTc=function(opt){
		var option={
			backgroundColor:"#000",
			option:0.5,
			fixed:true,
			zIndex:100,
			okEvent:null
		}
		return this.each(function(){
			option=$.extend(option,opt);
			var $this=$(this);
			var w,h,bgWidth,bgHeight,windowW,windowH,thisW,thisH,left,top;
			var position=option.fixed==true?"fixed":"absolute";
			function js(){
				w=$(window).width();
				h=$(document).height();
				bgWidth=option.fixed==true?"100%":w+"px";
				bgHeight=option.fixed==true?"100%":h+"px";
				windowW=$(window).width();
				windowH=$(window).height();
				thisW=$this.width();
				thisH=$this.height();
				left=option.fixed==true?(windowW-thisW)/2:(windowW-thisW)/2;
				top=option.fixed==true?(windowH-thisH)/2:(windowH-thisH)/2+document.documentElement.scrollTop+document.body.scrollTop;
				$this.css({"position":position,"z-index":option.zIndex+1,"left":left,"top":top});
				$(".tcBg").css({width:bgWidth,height:bgHeight});
			}
			js();
			var bg='<div class="tcBg" style="display:none;width:'+bgWidth+';height:'+bgHeight+';position:'+position+';background:'+option.backgroundColor+';opacity:'+option.option+';filter:alpha(opacity='+option.option*100+');z-index:'+option.zIndex+';left:0;top:0;"></div>';
			var closeBtn=$this.find(".close");
			var qxBtn=$this.find(".qxBtn");
			var okBtn=$this.find(".okBtn");
			closeBtn.click(function(){
				$this.fadeOut(400);
				$(".tcBg").fadeOut(400,function(){$(this).remove();});	
			});
			qxBtn.click(function(){
				$this.fadeOut(400);
				$(".tcBg").fadeOut(400,function(){$(this).remove();});			
			});
			okBtn.click(function(){
				$this.fadeOut(400);
				$.ajax({
					url : rootPath + "/classModuleNo/updateStatus",
					data:{"id":$(this).attr("data-id"),"publishStatus":$(this).attr("data-status")},
					type:"post",
					dataType:"text",
					success:function(data){
						if(data == "success"){
							selDetail(1);
						}else{
							$('<div class="c-fa">'+ "修改时出现错误！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
						}
					}
				});
				$(".tcBg").fadeOut(400,function(){$(this).remove();});
			});
			$("body").append(bg);
			$(".tcBg").fadeIn(400);
			$this.fadeIn(400);
			$(window).resize(function(){
				js();
			});
		});
	}
})(jQuery);