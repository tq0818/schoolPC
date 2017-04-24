$(function(){
		$(".r-subs-title").each(function(){
			if($(this).find(".twoStatus").val() == 0){
				$(this).find("em").removeAttr("class");
				$(this).find("em").attr("class","disabled");
			}
		});
		$(".btn-pro").off("click").on('click',function(){
			var _this = $(this),
	        bg = $('.add-subs-layer-bg');

		    bg.fadeIn(200);
		});
	    $('.btn-cancel').off("click").on('click',function(){
	        $(this).parents('.add-subs-layer').fadeOut(200,function(){
	            $('.add-subs-layer-bg').fadeOut(200);
	        });
	    });

		//学科小类点击更改状态
		$(".r-subs-title a").each(function(){
			$(this).off("click").on("click",function(){
				$.ajax({
					url:rootPath + "/sysConfigItem/changeStatusCompany",
					type:"post",
					data:{"id":$(this).parent().find(".twoId").val(),"status":$(this).parent().find(".twoStatus").val(),"parentId":$(this).parent().find(".pid").val()},
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			         },
					success:function(data){
						if(data == "error"){
 							$(".loading").hide();
 				            $(".loading-bg").hide();
 							$('<div class="c-fa">'+ "当前学科还有分校在使用！" +'</div>').appendTo('body').fadeIn(100).delay(3000).fadeOut(200,function(){
            					$(this).remove();
 							});
 						}else{
 							location.reload();
 						}
					}
				});
				
			}); 
		});
		//添加学科小类
		$(".add-subs").find("input[name='twoAdd']").each(function(){
			$(this).off("click").on("click",function(){
				var className = $(this).prev().val();
				var parentId = $(this).attr("data-pid");
				//验证学科名称唯一性
				$.ajax({
					url : rootPath + "/sysConfigItem/checkName",
					type:"post",
					data:{"itemName": className,"status":"add","parentId":parentId,"itemType":2},
					dataType:"text",
					beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			         },
					success:function(data){
						if(data == "false"){
     						$(".loading").hide();
     			            $(".loading-bg").hide();
 							$('<div class="c-fa">'+ "当前学科小类名称已存在！" +'</div>').appendTo('body').fadeIn(100).delay(3000).fadeOut(200,function(){
            					$(this).remove();
 							});
						}else{
							//添加
							$.ajax({
								url:rootPath + "/sysConfigItem/addPro",
								type:"post",
								data:{"itemName":className,"itemType":2,"parentId":parentId},
								dataType:"text",
								success:function(data){
		        					if(data == "true"){
		        						location.reload();
		        					}else{
		        						alert("添加学科时出错！");
		        						$(".loading").hide();
		        			            $(".loading-bg").hide();
		        					}
		        				}
							});
						}
					}
				});
			});
		});
		
		$(".r-subs-title").each(function(){
			var parent = $(this);
			var itemName = $.trim($(this).find("em").text());
			$(this).find(".btn-edit-two").off("click").on("click",function(){
				parent.find("em").html("<input type='text' class='twoItemName' value='" + itemName + "'/>");
				$(this).html("保存");
				parent.find(".btn-two").hide();
				$(this).after("<a href='javascript:;' class='btn btn-mini btn-link btn-two-cancel'>&nbsp;&nbsp;取消</a>");
				//取消
				parent.find(".btn-two-cancel").off("click").on("click",function(){
					parent.find("em").html(itemName);
					parent.find(".btn-edit-two").html("编辑");
					parent.find(".btn-two").show();
					parent.find(".btn-two-cancel").remove();
				});
				//修改
				$(this).off("click").on("click",function(){
					var parentId = parent.find(".pid").val();
					var twoItemId = parent.find(".twoId").val();
					var itemName = $.trim(parent.find(".twoItemName").val());
					//验证学科名称唯一性
					$.ajax({
						url : rootPath + "/sysConfigItem/checkName",
						type:"post",
						data:{"id":twoItemId,"itemName": itemName,"parentId":parentId,"status":"update","itemType":2},
						dataType:"text",
						beforeSend:function(XMLHttpRequest){
				              $(".loading").show();
				              $(".loading-bg").show();
				              
				         },
						success:function(data){
							if(data == "false"){
	     						$(".loading").hide();
	     			            $(".loading-bg").hide();
	 							$('<div class="c-fa">'+ "当前学科小类名称已存在！" +'</div>').appendTo('body').fadeIn(100).delay(3000).fadeOut(200,function(){
	            					$(this).remove();
	 							});
							}else{
								//添加
								$.ajax({
									url:rootPath + "/sysConfigItem/update",
									type:"post",
									data:{"id":twoItemId,"itemName":itemName,"itemType":2,"parentId":parentId},
									dataType:"text",
									success:function(data){
			        					if(data == "true"){
			        						location.reload();
			        					}else{
			        						alert("修改学科时出错！");
			        					}
			        				}
								});
							}
						}
					});
				});
			});
		});
	});