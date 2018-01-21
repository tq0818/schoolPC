$(function(){
		$(".r-subs-title").each(function(){
			if($(this).find(".twoStatus").val() == 1){
				$(this).find("em").removeAttr("class");
				$(this).find("em").attr("class","disabled");
			}
		});
		$(".btn-pro,.btn-edit-pro").on('click',function(){
			var _this = $(this),
	        bg = $('.add-subs-layer-bg');

		    bg.fadeIn(200);
		});
	    $('.btn-cancel').on('click',function(){
	        $(this).parents('.add-subs-layer').fadeOut(200,function(){
	            $('.add-subs-layer-bg').fadeOut(200);
	        });
	    });

	    $(".twoName").off("click").on("click",function(){
	    	$(this).focus();
	    });
		//学科小类点击更改状态
		$(".r-subs-title i").each(function(){
			$(this).unbind('click').click(function(){
				var twoId = $(this).parent().find(".twoId").val();
				var parentId = $(this).parent().find(".pid").val();
				var status = $(this).parent().find(".twoStatus").val();
				$.ajax({
					url:rootPath + "/sysConfigItem/changeStatus",
					type:"post",
					data:{"id":twoId,"status":status,"parentId":parentId},
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
			              $(".loading-bg").show(); 
			         },
					success:function(data){
 						if(data.flag == true){
 							//修改
 							$.ajax({
 								url : rootPath+"/sysConfigItem/starStopUpdate",
 								type:"post",
 								data:{"id":twoId,"parentId":parentId},
 								dataType:"json",
 								beforeSend:function(XMLHttpRequest){
 						              $(".loading").show();
 						              $(".loading-bg").show();
 						              $('.add-subs-layer-bg').hide();
 						         },
 								success:function(data){
 									if(data.msg == "success"){
 										location.reload();
 									}else{
 										$.msg(data.msg,1000);
			    			            $(".loading").hide();
			    			            $(".loading-bg").hide();
 									}
 								}
 							});
 						}else if(data.fail != null && data.fail != ""){
 				            $(".loading-bg").hide();
 							$(".stopTitle").remove();
 							$("#classHint").html("");
 							$.each(data.fail,function(index,item){
 								if(index > 4){
 									$("#classHint").append("<tr><td colspan='2' align='center'>.........</td></tr>");
 									return false;
 								}
 								if(item.classNo == null || item.classNo == ""){
 									$("#classHint").append("<tr><td><span class='h'><span class='h-title'>课程："+ (item.className) +"</span></span></td><td></td></tr>");
 								}else{
 									$("#classHint").append("<tr><td><span class='h'><span class='h-title'>课程："+ (item.className) +"</span></span></td><td><span class='h'><span class='h-list'>课程："+ (item.classNo) +"</span></span></td></tr>");
 								}
 							});
 							bg = $('.add-subs-layer-bg');
 						    bg.fadeIn(200);
 							$("#stopPanel").show();
 							$(".stop-subs").height(classHeight + parseInt($("#classHint").height()));
 							$(".btn-stop").click(function(){
   	     			            $(".loading").show();
	    			    		$("#stopPanel").hide();
 							//修改
	 							$.ajax({
	 								url : rootPath+"/sysConfigItem/starStopUpdate",
	 								type:"post",
	 								data:{"id":twoId,"parentId":parentId},
	 								dataType:"json",
	 								beforeSend:function(XMLHttpRequest){
	 						              $(".loading").show();
	 						              $(".loading-bg").show();
	 						              $('.add-subs-layer-bg').hide();
	 						         },
	 								success:function(data){
	 									if(data.msg == "success"){
	 										location.reload();
	 									}else{
	 										$.msg(data.msg,1000);	 										$(".loading").hide();
	 										$(".loading-bg").hide();
	 									}
	 								}
	 							});
 							});
 						}else{
 							$.msg(data.flag,1000);
 							$(".loading").hide();
 				            $(".loading-bg").hide();
 						}
					}
				});
			});

		});
		
		//添加学科小类
		$(".add-subs").find("input[name='twoAdd']").each(function(){
			$(this).unbind('click').click(function(){
				var className = $(this).prev().prev().val();
                var itemCode = $(this).prev().val();
				var parentId = $(this).attr("data-pid");
                var parentCode = $(this).attr("data-pcode");
                if(itemCode==""||className==""){
                	$.msg("名称或者编码不能为空",1000);
                	return;
                }
				//验证学科名称唯一性
				$.ajax({
					url : rootPath + "/sysConfigItem/checkName",
					type:"post",
					data:{"itemName": className,"status":"add","parentId":parentId,"itemType":2,"parentCode":parentCode,"itemCode":itemCode},
					dataType:"text",
					beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			         },
					success:function(data){
						if(data == "false"){
     						$(".loading").hide();
     			            $(".loading-bg").hide();
     			            $.msg("当前子类名称或编码已存在！",1000);
						}else{
							//添加
							$.ajax({
								url:rootPath + "/sysConfigItem/addPro",
								type:"post",
								data:{"itemName":className,"itemType":2,"parentId":parentId,"parentCode":parentCode,"itemCode":itemCode},
								dataType:"text",
								beforeSend:function(XMLHttpRequest){
						              $(".loading").show();
						              $(".loading-bg").show();
						         },
								success:function(data){
		        					if(data == "true"){
 										location.reload();
		        					}else{
		        						$.msg("添加子类时出错！",1000);
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
			var itemName = $.trim($(this).find("em .itemname").text());
            var itemCode = $.trim($(this).find("em .itemcode").text());
    		//删除
			$(this).find(".btn-del-two").unbind('click').click(function(){
				var twoId = $(this).parent().find(".twoId").val();
    			$.ajax({
					url:rootPath + "/sysConfigItem/changeStatus",
					type:"post",
					data:{"id":twoId,"status":$(this).parent().find(".twoStatus").val(),"delflag":1},
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
			              $(".loading-bg").show();
			         },
					success:function(data){
						if(data.flag == true){
							//删除
							$.ajax({
 								url : rootPath+"/sysConfigItem/delpro",
 								type:"post",
 								data:{"id":twoId},
 								dataType:"json",
 								beforeSend:function(XMLHttpRequest){
 						              $(".loading").show();
 						              $(".loading-bg").show();
 						              $('.add-subs-layer-bg').hide();
 						         },
 								success:function(data){
 									if(data.msg == "success"){
 										location.reload();
 									}else{
 										$.msg(data.msg,1000);
 			    			            $(".loading").hide();
 			    			            $(".loading-bg").hide();
 									}
 								}
 							});
						}else if(data.fail != null && data.fail != ""){
				            $(".loading-bg").hide();
							$(".stopTitle").remove();
							$("#classHint").html("");
							$.each(data.fail,function(index,item){
								if(index > 4){
									$("#classHint").append("<tr><td colspan='2' align='center'>.........</td></tr>");
									return false;
								}
 								if(item.classNo == null || item.classNo == ""){
 									$("#classHint").append("<tr><td><span class='h'><span class='h-title'>课程："+ (item.className) +"</span></span></td><td></td></tr>");
 								}else{
 									$("#classHint").append("<tr><td><span class='h'><span class='h-title'>课程："+ (item.className) +"</span></span></td><td><span class='h'><span class='h-list'>课程："+ (item.classNo) +"</span></span></td></tr>");
 								}
							});
							$.confirm("该学科下有在售的课程，如果停用、删除学科，这些课程将不可见，确认停用该学科吗？",function(b){
								if(b){
									$.ajax({
		 								url : rootPath+"/sysConfigItem/delpro",
		 								type:"post",
		 								data:{"id":twoId},
		 								dataType:"json",
		 								beforeSend:function(XMLHttpRequest){
		 						              $(".loading-bg").show();
		 						              $('.add-subs-layer-bg').hide();
		 						         },
		 								success:function(data){
		 									if(data.msg == "success"){
		 										location.reload();
		 							            $(".loading").show();
		 							            $(".loading-bg").show();
		 									}else{
		 										$.msg(data.msg,1000);
					    			            $(".loading").hide();
					    			            $(".loading-bg").hide();
		 									}
		 								}
		 							});
								}else{
									$('.loading-bg').hide();
								}
							})
							//bg = $('.add-subs-layer-bg');
						    //bg.fadeIn(200);
							//("#stopPanel").show();
							//$(".stop-subs").height(classHeight + parseInt($("#classHint").height()));
							/*$(".btn-stop").click(function(){
	    			    		$("#stopPanel").hide();
							//删除
								$.ajax({
	 								url : rootPath+"/sysConfigItem/delpro",
	 								type:"post",
	 								data:{"id":twoId},
	 								dataType:"json",
	 								beforeSend:function(XMLHttpRequest){
	 						              $(".loading-bg").show();
	 						              $('.add-subs-layer-bg').hide();
	 						         },
	 								success:function(data){
	 									if(data.msg == "success"){
	 										location.reload();
	 							            $(".loading").show();
	 							            $(".loading-bg").show();
	 									}else{
	 										$.msg(data.msg,1000);
				    			            $(".loading").hide();
				    			            $(".loading-bg").hide();
	 									}
	 								}
	 							});
							});*/
						}else{
							$.msg("修改时遇到问题，请重新尝试！",1000);
							$(".loading").hide();
				            $(".loading-bg").hide();
						}
					}
    			});
			});
			
			$(document).on("click",".twoItemName",function(){
				$(this).focus();
			});
			$(this).find(".btn-edit-two").unbind('click').click(function(){
				parent.find("em").html("<input type='text' class='twoItemName sub-input' value='" + itemName + "' maxlength='10'/> <input type='text' class='twoItemCode sub-input' value='" + itemCode + "' maxlength='10'/>");
				var p = $(this);
				// p.hide();
				// parent.find(".btn-two").hide();
				// parent.find(".btn-del-two").hide();
				parent.find("span.b").hide();
				var spanB = $(this).closest("span.b");
/*				$(this).after("<input type='button' class='btn btn-mini btn-default btn-two-cancel' value='取消' />");*/
				spanB.after("<span class='showbtn'><input type='button' class='btn btn-mini btn-default btn-ok-two' value='保存'/><input type='button' class='btn btn-mini btn-default btn-two-cancel' value='取消' /></span>");
				//取消
				parent.find(".btn-two-cancel").unbind('click').click(function(){
					var name_code = '<span class="sub-input itemname">'+itemName+'</span>'+
                    '<span class="sub-input itemcode">'+itemCode+'</span>';
					parent.find("em").html(name_code);
                    parent.find("em .itemcode").html(itemCode);
					// parent.find(".btn-two").removeClass("hide-ico");
					// parent.find(".btn-del-two").removeClass("hide-ico");
					// p.removeClass("hide-ico");
					parent.find("span.b").show();
					parent.find(".showbtn").remove();
					/*parent.find(".btn-two-cancel").remove();*/
				});
				//修改
				parent.find(".btn-ok-two").unbind('click').click(function(){
					var parentId = parent.find(".pid").val();
					var twoItemId = parent.find(".twoId").val();
					var itemName = $.trim(parent.find(".twoItemName").val());
                    var itemCode = $.trim(parent.find(".twoItemCode").val());
                    var parentCode =parent.find(".pcode").val();
					//验证学科名称唯一性
					$.ajax({
						url : rootPath + "/sysConfigItem/checkName",
						type:"post",
						data:{"id":twoItemId,"itemName": itemName,"parentId":parentId,"status":"update","itemType":2,"parentCode":parentCode,"itemCode":itemCode},
						dataType:"text",
						beforeSend:function(XMLHttpRequest){
				              $(".loading").show();
				              $(".loading-bg").show();
				              
				         },
						success:function(data){
							if(data == "false"){
	     						$(".loading").hide();
	     			            $(".loading-bg").hide();
	     			            $.msg("当前子类名称或编码已存在！",1000);
							}else{
								//添加
								$.ajax({
									url:rootPath + "/sysConfigItem/update",
									type:"post",
									data:{"id":twoItemId,"itemName":itemName,"itemType":2,"parentId":parentId,"parentCode":parentCode,"itemCode":itemCode},
									dataType:"text",
									success:function(data){
			        					if(data == "true"){
	 										location.reload();
									        $(".loading").show();
										    $(".loading-bg").show();
			        					}else{
			        						$.msg("修改子类时出错！",1000);
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
		});
	});