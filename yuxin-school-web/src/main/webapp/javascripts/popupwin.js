!(function($){
	
	var Popupwin=function(element,options){
		this.init(element, options);
	}
	Popupwin.prototype={
		constructor: Popupwin,
		init:function(element,options){
			var that = this;
			this.options = options;
			this.$ele=$(element).delegate('[data-pupwin="modal"]', 'click.pupwin.modal', $.proxy(this.hide, this));
			//设置窗口大小和位置
			if(this.options.width){
				this.$ele.animate({width:this.options.width});
			}
			if(this.options.height){
				this.$ele.animate({height:this.options.height});
			}
			var ww,hh,w,h,t,l;
			ww=$(window).width();
			hh=$(window).height();
			w=this.$ele.width();
			h=this.$ele.height();
			l=(ww-w)/2;
			t=(hh-h)/2-100;
			t=t<150?150:t;
			console.log(t);
			this.$ele.css({top:'20%',left:l});
			this.$ele.after('<div class="popupwin-bg colsekuang"></div>');
			//绑定事件
			$('[data-pupwin="modal"]')
			.on('click.pupwin.close','.close',$.proxy(that.hide,this))
			.on('click.pupwin.ok','[data-pupwin-btn="success"]',function(){
				var success=typeof that.options.success === 'function'?that.options.success.call(that,$(this)) : that.options.success;
				that.hide();
			})
			.on('click.pupwin.cancle','[data-pupwin-btn="cancle"]',function(){
				var cancle=typeof that.options.cancle === 'function'?that.options.cancle.call(that,$(this)) : that.options.cancle;
				that.hide();
			})
			this.$ele.next().on('click.pupwin.modal',function(){
				   return that.options.modal?that.options.modal:that.hide.apply(that);
			})
			$(".popup-open").css("height",hh);
			
		},
		show: function(){
			var that=this;
			this.$ele.next('.popupwin-bg').fadeIn(300,function(){
				that.$ele.fadeIn(200);
				that.isShown=true;
			});
			$("body").addClass("popup-open");
			$("html").addClass("popup-open");
		},
		hide: function(){
			var that=this;
			this.$ele.fadeOut(300,function(){
				that.$ele.next('.popupwin-bg').fadeOut(200);
				that.isShown=false;
			});
			$("body").removeClass("popup-open");
			$("html").removeClass("popup-open");
		},
		toggle: function(){
			return this[!this.isShown ? 'show' : 'hide']();
		}
	}
	
	
	$.fn.popup=function(option, args){
		return this.each(function () {
			var $this = $(this),data;
			if(option.init){
				data=null;
			}else{
				data = $this.data('modal')
			}
			var options = $.extend({}, $.fn.popup.defaults, $this.data(), typeof option == 'object' && option);

			if (!data) $this.data('modal', (data = new Popupwin(this, options)));
			if (typeof option == 'string') data[option].apply(data, [].concat(args));
			else if (options.show) data.show();
		})
	}
	$.fn.popup.defaults={
			init: true,
			show: true,
			hide: false,
			modal: true,
			isshown: false,
			success: function(){},
			cancle: function(){}
	}
	$.fn.popup.Constructor = Popupwin;
})(jQuery)