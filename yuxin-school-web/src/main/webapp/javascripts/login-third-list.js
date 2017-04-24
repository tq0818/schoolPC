;(function($){
	
    var Page={
		init:function(){
			$selectSubMenu('org_service');
    		$selectSubMenus('system_stu_log');
			Page.btnInit();
		},
		btnInit:function(){
			$(".manage-btn-success").on("click",function(){
				Page.save();
			})
			$(".ask").on("click",function(){
				if($(this).hasClass("qq")){
					window.open("http://wiki.connect.qq.com/%E7%BD%91%E7%AB%99%E6%8E%A5%E5%85%A5%E6%B5%81%E7%A8%8B");  
				}else if($(this).hasClass("wx")){
					window.open("https://open.weixin.qq.com/cgi-bin/frame?t=home/web_tmpl&lang=zh_CN");
				}else if($(this).hasClass("wb")){
					window.open("http://open.weibo.com/authentication/");
				}
				if($(this).hasClass('openWeichat'))
					window.open("https://mp.weixin.qq.com/");
			})
		},
		save:function(){
			var	saveId=$("#saveId").val();
			var data={};
			if(saveId=="qq"){
				data.qqAppid=$("#qqId").val();
				data.qqKey=$("#qqKey").val();
//				data.qqValidateCode=$("#qqValidateCode").val();
				if(!data.qqAppid.length>0){
					$.msg("APP ID 不能为空！");
					return false;
				}
				if(!data.qqKey.length>0){
					$.msg("APP Key 不能为空！");
					return false;
				}
				/*if(!data.qqValidateCode.length>0){
					$.msg("登录接口验证代码 不能为空！");
					return false;
				}*/
			}else if(saveId=="wx"){
				data.wechatAppid=$("#wxId").val();
				data.wechatKey=$("#wxKey").val();
				if(!data.wechatAppid.length>0){
					$.msg("微信.开放平台  APP ID 不能为空！");
					return false;
				}
				if(!data.wechatKey.length>0){
					$.msg("微信.开放平台  AppSecret 不能为空！");
					return false;
				}
				if(!this.checkOpenWeichat(data)){
					return;
				}
			}else if(saveId=="wb"){
				data.weiboKey=$("#wbId").val();
				data.weiboSercet=$("#wbKey").val();
				data.weiboValidateCode=$("#weiboValidateCode").val();
				if(!data.weiboKey.length>0){
					$.msg("APP ID 不能为空！");
					return false;
				}
				if(!data.weiboSercet.length>0){
					$.msg("APP Key 不能为空！");
					return false;
				}
				if(!data.weiboValidateCode.length>0){
					$.msg("登录接口验证代码 不能为空！");
					return false;
				}
			}
			$.ajax({
    			url : rootPath + "/companyLoginConfig/saveThirdLoginList/"+saveId,
    			type : "post",
    			data : data,
    			success : function(data){
    				if(data.msg="success"){
    					$.msg("操作成功！")
    					location.href=rootPath+"/companyFunctionSet/stuLogin";
    				}else{
    					$.msg("操作失败，请重新操作！")
    				}
    			}
    		});
		},
		checkOpenWeichat : function(data){
			var wxOpenId  = $.trim($('#wxOpenId').val()),
				wxOpenKey = $.trim($('#wxOpenKey').val());
			if(!wxOpenId && !wxOpenKey)
				return true;
			if(!wxOpenId && wxOpenKey){
				$.msg('微信.公众平台   APP ID 不能为空');
				return false;
			}
			if(wxOpenId && !wxOpenKey){
				$.msg('微信.公众平台   AppSecret 不能为空');
				return false;
			}
			data.wechatOpenAppid = wxOpenId;
			data.wechatOpenSecret = wxOpenKey;
			return true;
		}
		
    }
    $(document).ready(function(){
		Page.init();
	});
})(jQuery)
