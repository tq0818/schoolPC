(function($) {
	var Forms = {
		init : function() {
		 var $this=this;
		 $selectMenu('system_config_ico');
			//打开弹窗
			$(".btn-addkeyword").on('click',function() {
				$("#uploadDiv").removeClass("none");
				$("#stopDiv").removeClass("none");
			});
			//关闭弹窗
			$(".upload-layer").on('click','i.close,a.btn-cancel',function(){
				$(".upload-layer").addClass("none");
				$("#stopDiv").addClass("none");
				$("#imgObject").attr("ids"," ").attr("src"," ");
			});
			//上传图片
			$("#c-up").on('click',function(){
				$(this).prev().trigger("click");
				return false;
			});
			//保存ico
			$(".saveIco").on('click',function(){
				var path=$("#imgObject").attr("ids");
				var realPath=$("#imgObject").attr("src");
				if(path&&path!=""){
					$this.addCompanyIcoInfo(path,realPath);
				}else{
					$.msg("请选择文件");
				}
				return false;
			});
			$this.loadCompanyIco();
		},
		loadCompanyIco : function() {
			$("#icosInfo").html("");
			 $.ajax({ 
	       		  type: "post", 
	       		  url: rootPath+"/sysConfigIco/queryIcoList", 
	       		  dataType:'json',
	       		  success: function(jsonData){
	       			  if(jsonData.length<=0){
	       				  return;
	       			  }
	       			  var html="";
	       			  $.each(jsonData,function(i,list){
	       				  var shtml='<i class="iconfont normal close">&#xe604;</i>&nbsp;&nbsp;<span class="i close">已禁用</span>';
	       				  if(list.status==1){
	       					shtml='<i class="iconfont normal open">&#xe603;</i>&nbsp;&nbsp;<span class="i open">已启用</span>';
	       				  }
	       				  html+='<li ids='+list.id+'>'+
	       				  		'<span style="float:right;"><em class="iconfont i_del">&#xe610;</em></span>'+
								'<span><img ids="" width="32" height="32" src="'+list.path+'" alt="" /></span>'+
								'<span><span class="t">是否启用：</span><span>'+shtml+'</span></span>'+
							  '</li>';
	       			  });
	       			 $("#icosInfo").append(html);
	       			 $("div").on('click','em.iconfont',function(){
	       				 var _this=$(this);
	       				var id=$(this).parents("li").attr("ids");
	       				$.ajax({
        		  			type: "post",
        		  			url:  rootPath+"/sysConfigIco/del/"+id,
        		  			dataType : "json",
        		  			success: function(jsonData){
        		  				_this.parents("li").remove();
        		  			}
        		  	     });
	       			 })
		       		 $("span").on('click','i.iconfont',function(){
		    	         var 
		    	             // 开关图标  03为左侧块 04为右侧块
		    	             s = ['&#xe603;','&#xe604;'],
		    	             // 开关文字
		    	             st = ['已启用','已禁用'],
		    	             // 获得开关实体
		    	             o = $(this),
		    	             flag,
		    	             // 获得当前状态
		    	             staus = o.hasClass('open');
		    	       
		    	         o
		    	             [staus?'removeClass':'addClass']('open')
		    	             [staus?'addClass':'removeClass']('close')
		    	             .html(s[staus?1:0])
		    	             .next('span.i').text(st[staus?1:0])
		    	             [staus?'removeClass':'addClass']('open')
		    	             [staus?'addClass':'removeClass']('close');
		    	         
		    	         var id=$(this).parents("li").attr("ids");
		    	         if(staus){
		    	        	var sta=0;
		    	        	 $.ajax({
	        		  			type: "post",
	        		  			url:  rootPath+"/sysConfigIco/update",
	        		  			dataType : "json",
	        		  			data:{"id":id,"status":sta},
	        		  			success: function(jsonData){
	        		  			}
	        		  	     });
		    	         }else{
		    	        	var sta=1;
		    	        	 $.ajax({
	        		  			type: "post",
	        		  			url:  rootPath+"/sysConfigIco/queryUseCount",
	        		  			dataType : "json",
	        		  			success: function(jsonData){
	        		  				if(jsonData>0){
	        		  					$.msg("最多只能开启一个icon文件");
	        		  					Forms.loadCompanyIco();
	        		  					return false;
	        		  				}else{
	        		  					 $.ajax({
        		        		  			type: "post",
        		        		  			url:  rootPath+"/sysConfigIco/update",
        		        		  			dataType : "json",
        		        		  			data:{"id":id,"status":sta},
        		        		  			success: function(jsonData){
        		        		  				
        		        		  			}
        		        		  	     });
	        		  				}
	        		  			}
	        		  	     });
		    	         }
		    	         return false;
		    	      });
	       		  }
			 });
		},
		addCompanyIcoInfo : function(path,realPath){
			 $.ajax({ 
	       		  type: "post", 
	       		  url: rootPath+"/sysConfigIco/add", 
	       		  data:{"path":path},
	       		  dataType:'json',
	       		  success: function(jsonData){
	       			Forms.loadCompanyIco();
	       			$("#imgObject").attr("ids"," ").attr("src"," ");
	       		  }
			 });
		},
		addCompanyIco : function(){
			$.ajaxFileUpload({
				url : rootPath+"/sysConfigIco/uploadIco;"+ window["sessionName"] + "=" + window["sessionId"],
				secureuri : false,// 安全协议
				async : false,
				fileElementId : 'imgData',
				dataType:'json',
				type : "POST",
				success : function(data) {
					$("#imgObject").attr("src",data.url);
					$("#imgObject").attr("ids",data.picPath);
				},
				error : function(resp,msg,err){
					console.log(resp);
				}
			});
		}
	}
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)