(function($){
	var Model = {};
	Model.ajaxLoad = function(url,dataInfo,func){
  		 $.ajax({ 
  	 		  type: "post", 
  	 		  url : rootPath+url, 
  	 		  data: dataInfo,
  			  beforeSend:function(XMLHttpRequest){
  	            $(".loading").show();
  	            $(".loading-bg").show();
  	          },
  	 		  success: func,
  	 		  complete:function(XMLHttpRequest,textStatus){
  				  $(".loading").hide();
  		          $(".loading-bg").hide();
  		      }
  	 	  });
  	};
  	Model.ajax = function(){
		var arg = Array.prototype.slice.call(arguments),
			success = arg.length>2?arg[2]:function(){},
			error = arg.length>3?arg[3]:function(){};
		$.ajax({ 
	 		  type: "post", 
	 		  url : rootPath+arg[0], 
	 		  data: arg[1],
	 		  success: success,
	 		  error : error
	 	  });
	}
  	Model.init = function(){
        //混合课，判断是否隐藏面授课菜单
        this.isServiceLiveOpen();
  		
  	};
    Model.isServiceLiveOpen = function(){
        this.queryCompanyService('SERVICE_FACE',function(flag){
            if(flag) return;

            var menus = $('.menuList_choose li'),
                menu;
            for (var i = 0; i < menus.length; i++) {
                menu = menus[i];
                if($(menu).attr('code') == 'faceCode'){
                    $(menu).remove();
                    break;
                } 
            }
        })
    }

    /**
     * 查询机构服务配置
     * @param  {[type]}   groupCode [description]
     * @param  {Function} callback  [description]
     * @return {[type]}             [description]
     */
  	Model.queryCompanyService = function(groupCode,callback){
        this.ajax('/yuxin/queryCompanyService',{groupCode:groupCode},function(jsonData){
            if(callback) callback(jsonData);
        });
    };

	
	/**
	 * 页面加载完成后执行
	 */
	$(function(){
		Model.init();
		
	});
})(jQuery);