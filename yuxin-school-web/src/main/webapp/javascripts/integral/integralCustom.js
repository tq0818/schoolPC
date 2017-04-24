/**
 * 在jQuery加载完成后执行
 */
(function($){
	var Model = {};
	   	Model.ajax = function(url,dataInfo,func){
	   		 $.ajax({ 
	   	 		  type: "post", 
	   	 		  url : rootPath+url, 
	   	 		  data: dataInfo,
	   	 		  success: func,
	   	 		  error:function(){
	   	 			  $.msg('操作失败');
	   	 		  }
	   	 	  });
	   	};
	   	Model.checkNum = function(dom){
	   		var num = dom.val(),floatNum;
	   		if(!num){
				$.msg("抵现不能为空");
			return false;
			}
			floatNum = parseInt(num);
			if(isNaN(floatNum)){
				$.msg("请填写有效的数字");
				return false;
			}
			floatNum = Math.abs(floatNum);
			dom.val(floatNum);
			if(floatNum<=0){
				$.msg("积分抵现必须大于 0%");
				return false;
			}
			if(floatNum>100){
				$.msg("积分抵现不能超过 100%");
				return false;
			}
			return true;
	   	}
	   	Model.parseNum = function(dom){
	   		var num = dom.val(),floatNum;
	   		if(!num){
	   			return false;
			}
			floatNum = parseInt(num);
			if(isNaN(floatNum)){
				return false;
			}
			floatNum = Math.abs(floatNum);
			dom.val(floatNum);
			if(floatNum<=0){
				return false;
			}
			if(floatNum>100){
				return false;
			}
			return true;
	   	}
	   	Model.init = function(){
	   		this.event();
	   	}
	   	Model.event = function(){
	   		var $this = this;
	   		//功能开启或者关闭
	        $(".l-btn-status").on('click',function(){
	        	var open=$(this).hasClass("open"),
	        		isClassCost = $(this).hasClass('classCost'),
	        		cic,obj,num,$input,
	        		static_icom=[{icon:"&#xe603",word:"已开启"},{icon:"&#xe604;",word:"已禁用"}],
	        		_this = this;
	        	cic={id:$('.set-system').data('id')};
	        	if(open){
	        		isClassCost?cic.classCost=0 : cic.classPackageCost=0;
	        	}else{//开启时默认如果兑换比例为正确数字则一起更新
	        		if(isClassCost){
	        			cic.classCost=1;
	        			$input = $(this).parent().next().find('input');
	        			if($this.parseNum($input)){
	        				cic.classCostMaxScale = $input.val();
	        			}
	        		}else{
	        			cic.classPackageCost=1;
	        			$input = $(this).parent().next().find('input');
	        			if($this.parseNum($input)){
	        				cic.classPackageCostMaxScale = $input.val();
	        			}
	        		}
	        	}
	        	
	        	Model.ajax("/companyIntegralConfig/saveOrUpdate",cic,function(result){
        			if(open){
        				$(_this).removeClass("open").addClass("close");
    	                $(_this).find("i").html(static_icom[1].icon);
    	                $(_this).find("em").html(static_icom[1].word);
    	                $(_this).parent().next().hide();
    	        	}else{
    	        		$(_this).removeClass("close").addClass("open");
    	                $(_this).find("i").html(static_icom[0].icon);
    	                $(_this).find("em").html(static_icom[0].word);
    	                $(_this).parent().next().show();
    	        	}
        			if(!$('.set-system').data('id'))
        				$('.set-system').data('id',result.id);
        		});
	        });
	        
	        $(".g-money input").on('blur',function(){
	        	var type = $(this).data('type'),data = $('.set-system').data();
	        	if(!$this.checkNum($(this)))
	        		return;
	        	$(this).parent().find('em').html($(this).val());
	        	if(type == 'classCostMaxScale'){
	        		data.classCostMaxScale = $(this).val();
	        	}else{
	        		data.classPackageCostMaxScale = $(this).val();
	        	}
	        	Model.ajax("/companyIntegralConfig/saveOrUpdate",data,function(result){
	        		if(!$('.set-system').data('id'))
	    				$('.set-system').data('id',result.id);
	        	});
	        })
	        
	        
	   	}
	   	   	
	/**
	 * 页面加载完成后执行
	 */
	$(function(){
		Model.init();
	});
})(jQuery);