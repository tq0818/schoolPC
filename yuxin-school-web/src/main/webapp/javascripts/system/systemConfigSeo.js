(function($) {
	var keysOrder;// 顺序
	var Forms = {
		init : function() {
		 $selectMenu('system_config_seo');	
		 var $this=this;
		 // 拖动排序
         $('#keywordsInfo').sortable({
				placeholder: "ui-state-highlight",
				items: "li:not(.ui-state-disabled)",
				update:function(event,ui){
					 var sortMap=new Array();
					 $('#keywordsInfo').find("li").each(function(i){
						var keywords={};
						keywords.id=$(this).attr("ids");
						keywords.sort=$(this).index()+1;
						sortMap.push(keywords);
					 });
					 $.ajax({
							url: rootPath+"/sysConfigSeo/orderSortSeo",
							data: "list="+JSON.stringify(sortMap),
							type: "post",
							dataType: "json",
							success:function(jsonData){
							}
						})
				},
				delay: 200
			}).disableSelection();

         $('#titleInfo').sortable({
				placeholder: "ui-state-highlight",
				items: "li:not(.ui-state-disabled)",
				update:function(event,ui){
					 var sortMap=new Array();
					 $('#titleInfo').find("li").each(function(i){
						var keywords={};
						keywords.id=$(this).attr("ids");
						keywords.sort=$(this).index()+1;
						sortMap.push(keywords);
					 });
					 $.ajax({
							url: rootPath+"/sysConfigSeo/orderSortSeo",
							data: "list="+JSON.stringify(sortMap),
							type: "post",
							dataType: "json",
							success:function(jsonData){
							}
						})
				},
				delay: 200
			}).disableSelection();
			//打开弹窗
			$(".btn-addkeyword").on('click',function() {
				$("#uploadDiv").removeClass("none");
				$("#stopDiv").removeClass("none");
				$("#key_wordscontent").val("");
			});
			$(".btn-addtitle").on('click',function(){
				$("#uploadtitleDiv").removeClass("none");
				$("#stopDiv").removeClass("none");
				$("#title_content").val("");
			})
			$(".btn-addescription").on('click',function(){
				$("#descriptionDiv").removeClass("none");
				$("#stopDiv").removeClass("none");
			})
			//关闭弹窗
			$(".upload-layer").on('click','i.close,a.btn-cancel',function(){
				$(".upload-layer").addClass("none");
				$("#stopDiv").addClass("none");
			});
			//添加关键词
			$(".savekeywords").on('click',function(){
				var key_word=$.trim($("#key_wordscontent").val());
				var key_index=$('#keywordsInfo').find("li:last").index()+1;
				if(key_word==""){
					$.msg("请输入关键词");
					return;
				}
				if(key_word.length>20){
					$.msg("关键词长度不能超过20个字符");
					return;
				}
				 $.ajax({ 
	        		  type: "post", 
	        		  url: rootPath+"/sysConfigSeo/add", 
	        		  data: {"content":key_word,"sort":key_index,"type":"SEO_KEYWORDS"},
	        		  dataType:'json',
	        		  success: function(jsonData){
	        			var html="";
	      				html+='<li class="keywords" ids='+jsonData+' sort='+key_index+'>'+
	      	            	'<span class="text_content">'+
	      		            	'<span>'+key_word+'</span>'+
	      		            	'<em class="iconfont i_edit">&#xe625;</em>'+
	      		            	'<em class="iconfont i_del">&#xe610;</em>'+
	      		            	'</span>'+
	      		            	'<span class="edit_content none">'+
	      		            	'<input type="text" value="'+key_word+'"/>'+
	      		            	'<a href="javascript:;" class="btn btn-sm btn-success i_save" ids='+jsonData+' sort='+key_index+'>保存</a>'+
	      		               '<a href="javascript:;" class="btn btn-sm btn-default i_cancel">取消</a>'+
	      		           '</span>'+
	      		        '</li>';
	      				$('#keywordsInfo').append(html);
	      				$("#uploadDiv").addClass("none");
	      				$("#stopDiv").addClass("none");
	      				$("#key_wordscontent").val("");
	        		  }
				 })	 
			});
			//添加title
			$(".savetitle").on('click',function(){
				var key_word=$.trim($("#title_content").val());
				var key_index= $('#titleInfo').find("li:last").index()+1;
				if(key_word==""){
					$.msg("请输入标题内容");
					return;
				}
				if(key_word.length>20){
					$.msg("title长度不能超过20个字符");
					return;
				}
				 $.ajax({ 
	        		  type: "post", 
	        		  url: rootPath+"/sysConfigSeo/add", 
	        		  data: {"content":key_word,"sort":key_index,"type":"SEO_TITLE"},
	        		  dataType:'json',
	        		  success: function(jsonData){
	        			var html="";
	      				html+='<li class="keywords" ids='+jsonData+' sort='+key_index+'>'+
	      	            	'<span class="text_content">'+
	      		            	'<span>'+key_word+'</span>'+
	      		            	'<em class="iconfont i_edit">&#xe625;</em>'+
	      		            	'<em class="iconfont i_del">&#xe610;</em>'+
	      		            	'</span>'+
	      		            	'<span class="edit_content none">'+
	      		            	'<input type="text" value="'+key_word+'"/>'+
	      		            	'<a href="javascript:;" class="btn btn-sm btn-success i_save" ids='+jsonData+' sort='+key_index+'>保存</a>'+
	      		               '<a href="javascript:;" class="btn btn-sm btn-default i_cancel">取消</a>'+
	      		           '</span>'+
	      		        '</li>';
	      				 $('#titleInfo').append(html);
	      				$(".upload-layer").addClass("none");
	      				$("#stopDiv").addClass("none");
	      				$("#title_content").val("");
	        		  }
				 })	  
			});
			
			//添加描述
			$(".savedescription").on('click',function(){
				var status=$(this).attr("disabled");
				if(status=="disabled"){
					return;
				}
				var key_word=$("#description_content").val();
				if(key_word.length>500){
					$.msg("描述内容不能超过500个字符");
					return;
				}
				 $.ajax({ 
	        		  type: "post", 
	        		  url: rootPath+"/sysConfigSeo/add", 
	        		  data: {"content":key_word,"sort":0,"type":"SEO_DESCRIPTION"},
	        		  dataType:'json',
	        		  success: function(jsonData){
	      				$.msg("操作成功!");
	      				$(".savedescription").attr("disabled","disabled");
	        		  }
				 })	  
			});
			//取消描述
			$(".quxiaodescription").on('click',function(){
				var content=$("#dcontents").val();
				$("#description_content").val(content);
				$(".savedescription").attr("disabled","disabled");
       			
			});
			$("#description_content").on('keyup',function(){
				$(".savedescription").removeAttr("disabled");
			});
			//编辑关键词
			$("#keywordsInfo").on('click','.i_edit',function(){
				var key_words=$.trim($(this).prev().text());
				$(this).parent().addClass("none").next().removeClass("none").find("input[type=text]").val(key_words);
			})
			.on('mouseover','.i_edit',function(){
				$(this).css({"background-color":"#C1C7C0"});
			})
			.on('mouseleave','.i_edit',function(){
				$(this).css({"background-color":"#D8DCD7"});
			})
			.on('click','.i_save',function(){
				var key_words=$.trim($(this).prev().val());
				var key_index=$(this).attr("sort");
				var id=$(this).attr("ids");
				$this.updatekeywords(id,key_words);
				$(this).parent().addClass("none").prev().removeClass("none").find("span:eq(0)").text(key_words);
			})
			.on('click','.i_cancel',function(){
				$(this).parent().addClass("none").prev().removeClass("none");
			});
			//删除关键词
			$("#keywordsInfo").on('click','.i_del',function(){
				var id=$(this).parents("li").attr("ids");
				$this.delkeywords(id);
				$(this).parents("li").remove();
			});
			//------------------------------title
			$('#titleInfo').on('click','.i_edit',function(){
				var key_words=$.trim($(this).prev().text());
				$(this).parent().addClass("none").next().removeClass("none").find("input[type=text]").val(key_words);
			})
			.on('mouseover','.i_edit',function(){
				$(this).css({"background-color":"#C1C7C0"});
			})
			.on('mouseleave','.i_edit',function(){
				$(this).css({"background-color":"#D8DCD7"});
			})
			.on('click','.i_save',function(){
				var key_words=$.trim($(this).prev().val());
				var key_index=$(this).attr("sort");
				var id=$(this).attr("ids");
				$this.updatekeywords(id,key_words);
				$(this).parent().addClass("none").prev().removeClass("none").find("span:eq(0)").text(key_words);
			})
			.on('click','.i_cancel',function(){
				$(this).parent().addClass("none").prev().removeClass("none");
			});
			$('#titleInfo').on('click','.i_del',function(){
				var id=$(this).parents("li").attr("ids");
				$this.delkeywords(id);
				$(this).parents("li").remove();
			});
			
			$this.loadkeywords();
			$this.loadtitle();
			$this.loaddescription();
		},
		loadkeywords : function() {
			$('#keywordsInfo').html("");
			 $.ajax({ 
        		  type: "post", 
        		  url: rootPath+"/sysConfigSeo/queryData", 
        		  data: {"type":"SEO_KEYWORDS"},
        		  dataType:'json',
        		  success: function(jsonData){
    			    if(jsonData.length<=0){
    				  return;
    			    }
        			var html="";
        			$.each(jsonData,function(i,list){
        				html+='<li class="keywords" ids='+list.id+' sort='+list.sort+'>'+
	    	            	'<span class="text_content">'+
	    		            	'<span>'+list.content+'</span>'+
	    		            	'<em class="iconfont i_edit">&#xe625;</em>'+
	    		            	'<em class="iconfont i_del">&#xe610;</em>'+
	    		            	'</span>'+
	    		            	'<span class="edit_content none">'+
	    		            	'<input type="text" value="'+list.content+'"/>'+
	    		            	'<a href="javascript:;" class="btn btn-sm btn-success i_save" ids='+list.id+' sort='+list.sort+'>保存</a>'+
	    		               '<a href="javascript:;" class="btn btn-sm btn-default i_cancel">取消</a>'+
	    		           '</span>'+
	    		        '</li>';
        			});
        			$('#keywordsInfo').append(html);
        		  }
        	  });
		},
		delkeywords : function(id) {
			 $.ajax({ 
	       		  type: "post", 
	       		  url: rootPath+"/sysConfigSeo/del/"+id, 
	       		  dataType:'json',
	       		  success: function(jsonData){
	       			  
	       		  }
				});
		},
		updatekeywords : function(id,key_word) {
		   $.ajax({ 
       		  type: "post", 
       		  url: rootPath+"/sysConfigSeo/update", 
       		  data: {"id":id,"content":key_word},
       		  dataType:'json',
       		  success: function(jsonData){
       			  
       		  }
			});
		},
		loadtitle : function(){
		  $('#titleInfo').html("");
		  $.ajax({ 
       		  type: "post", 
       		  url: rootPath+"/sysConfigSeo/queryData", 
       		  data: {"type":"SEO_TITLE"},
       		  dataType:'json',
       		  success: function(jsonData){
       			if(jsonData.length<=0){
   				  return;
   			    }
       			var html="";
    			$.each(jsonData,function(i,list){
    				html+='<li class="keywords" ids='+list.id+' sort='+list.sort+'>'+
    	            	'<span class="text_content">'+
    		            	'<span>'+list.content+'</span>'+
    		            	'<em class="iconfont i_edit">&#xe625;</em>'+
    		            	'<em class="iconfont i_del">&#xe610;</em>'+
    		            	'</span>'+
    		            	'<span class="edit_content none">'+
    		            	'<input type="text" value="'+list.content+'"/>'+
    		            	'<a href="javascript:;" class="btn btn-sm btn-success i_save" ids='+list.id+' sort='+list.sort+'>保存</a>'+
    		               '<a href="javascript:;" class="btn btn-sm btn-default i_cancel">取消</a>'+
    		           '</span>'+
    		        '</li>';
    			});
    			$('#titleInfo').append(html);
       		  }
       	  });
		},
		loaddescription : function(){
			$.ajax({ 
	       		  type: "post", 
	       		  url: rootPath+"/sysConfigSeo/queryData", 
	       		  data: {"type":"SEO_DESCRIPTION"},
	       		  dataType:'json',
	       		  success: function(jsonData){
	       			if(jsonData.length<=0){
	       				return;
	       			}
	       			$.each(jsonData,function(i,list){
	       				$("#description_content").val(list.content);
		       			$("#dcontents").val(list.content);
	       			});
	       		  }
	       	  });
		}
	}
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)