/**
 * author zhang.zx
 * 用户权限
 * 页面js封装
 */
(function($){
	var Form={
			init : function(){
				
			},
			uploadPic : function(){
				$.ajaxFileUpload({
					url : rootPath+"/sysConfigNews/UploadPic;"+ window["sessionName"] + "=" + window["sessionId"],
					secureuri : false,// 安全协议
					async : false,
					fileElementId : 'imgData',
					dataType:'json',
					type : "POST",
					success : function(data) {
						console.log(data.picPath);
						console.log(data.url);
						$("#imgObject").attr("src",data.url);
						$("#imgObject").attr("ids",data.picPath);
					},
					error : function(resp,msg,err){
						console.log(resp);
					}
				});
			}
			
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)