/**
 * 在jQuery加载完成后执行
 */


//(function($){
//	var project = {};
//	project.ajaxLoad = function(url,dataInfo,func){
//		 $.ajax({ 
//	 		  type: "post", 
//	 		  url : rootPath+url, 
//	 		  data: dataInfo,
//			  beforeSend:function(XMLHttpRequest){
//	            $(".loading").show();
//	            $(".loading-bg").show();
//	          },
//	 		  success: func,
//	 		  error : function(){
//	 			  $.msg("操作失败");
//	 		  },
//	 		  complete:function(XMLHttpRequest,textStatus){
//				  $(".loading").hide();
//		          $(".loading-bg").hide();
//		      }
//	 	  });
//	};
//	project.ajax = function(url,dataInfo,func){
//		 $.ajax({ 
//	 		  type: "post", 
//	 		  url : rootPath+url, 
//	 		  data: dataInfo,
//	 		  success: func,
//	 	  });
//	};
//	project.init = function(){
//		this.sortOneItem();
//	}
//	
//	project.sortOneItem = function(){
//		var $this = this;
//		 $("#sortTable" ).sortable({
//	            update:function(event,ui){
//					var sortMap = [];
//					$("#sortTable").find(".ui-state-default").each(function(i){
//						var oneItem={};
//						oneItem.id=$(this).data("id");
//						oneItem.sort=$(this).index()+1;
//						sortMap.push(oneItem);
//					})
//					$this.ajaxLoad("/sysConfigItem/orderOneItem","list="+JSON.stringify(sortMap),function(data){
//						$.msg("修改成功");
//					});
//				},
//				delay: 100,
//	      }).disableSelection();
//	}
//	
//	$(function(){
//		project.init();
//	});
//})(jQuery);