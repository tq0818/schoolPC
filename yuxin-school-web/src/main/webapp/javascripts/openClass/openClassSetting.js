;(function($){
	var Page = {
		init : function(){
			$selectSubMenu('org_service');
    		$selectSubMenus('system_opensetting');
    		
			$(document).on('click','.screen-right-cont em',function(){
				var kind = $(this).next().data('kind');
				var status = $(this).next().data('status');
				if(kind == 'needLogin'){
					if($(this).hasClass("open")){
						$(this).removeClass("open").addClass("close").html("&#xe604;");
	    	            $(this).parents(".l-title").find(".i").removeClass("open").addClass("close").text("已禁用");
					}else if($(this).hasClass("close")){
						$(this).removeClass("close").addClass("open").html("&#xe603;");
	    	            $(this).parents(".l-title").find(".i").removeClass("close").addClass("open").text("已启用");
					}
					Page.updateLiveOpenClassSettingNeedLogin(status);
				}
			})
		},
		updateLiveOpenClassSettingNeedLogin : function(status){
			$.ajax({
				url : rootPath + '/liveOpenCourse/updateLiveOpenClassSettingNeedLogin',
				data : {
					'status' : status
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if('success' == data.status){
					
					}else if('error' == data.status){
						$.msg(data.result);
					}
					window.location.reload();
				}
			})
		}
	}
	$(document).ready(function() {
		Page.init();
	})
})(jQuery)