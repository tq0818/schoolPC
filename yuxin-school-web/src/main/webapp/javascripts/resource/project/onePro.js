var classHeight;

$(function(){
	$selectSubMenu('resource_item');
	classHeight = parseInt($(".stop-subs").height());
	//加载 学科小类
	$(".btn-edit-pro").hide();
	$(".btn-del-pro").hide();
	$("#jia").hide();
	$(".useicon").hide();
	imgclick();
	//div高度
	//var cheng = $(".block").length % 3 == 0 ? ($(".block").length / 3) : (($(".block").length / 3) + 1);
	//$(".main-content").attr("style","height:" + (parseInt(cheng) * 360) + "px");
	
	$(".block").each(function(){
		var dat = $(this).find(".b-content");
		var parent = $(this);
		if(parent.attr("data-type") == "start"){
			parent.mouseover(function(){
				parent.find(".btn-edit-pro").show();
				parent.find(".btn-del-pro").show();
			});
			parent.mouseout(function(){
				parent.find(".btn-edit-pro").hide();
				parent.find(".btn-del-pro").hide();
			});
		}
		if(parent.find(".oneId").val() != null && parent.find(".oneId").val() != ""){
		    debugger;
			$.ajax({
				url: rootPath + "/sysConfigItem/twoProListAjax",
				type:"post",
				data:{"oneItemId":parent.find(".oneId").val(),"oneItemCode":parent.find(".oneCode").val()},
				dataType:"html",
				success:function(data){
					dat.html(data);
	    			//判断能不能用
	    			if(parent.find(".oneStatus").val() == 1){
	    				parent.find("em").attr("class","disabled");
	    				parent.find(".r-subs-title a").html("");
	    			}
					//dat.find("li:gt(4)").attr("style","display:none");
					
	    			//伸展
	    		/*	if(parent.find(".b-content").find("li").length > 5){
	    		        parent.on("mouseenter",".hidden",function(){
	    		        	dat.find("li:gt(4)").removeAttr("style");
	    		        	$(this).animate({"height":"500px"},200);
	    		        })
	    		        .on("mouseleave",".hidden",function(){
	    		        	dat.find("li:gt(4)").attr("style","display:none");
	    		        	$(this).animate({"height":"100%"},200);
	    		        });
	    			}*/
					//$(".add-subs").hide();
					parent.find("i[name='twoToCreate']").off('click').on("click",function(){
						if(parent.attr("data-type") == "stop"){
							return false;
						}
							parent.find(".add-subs").show();
							parent.find(".add-subs").attr("data-parentId",parent.find(".oneId").val());
							parent.find(".add-subs").attr("data-parentCode",parent.find(".parentCode").val());
						});
					parent.find("input[name='twoCancel']").off('click').on("click",function(){
							parent.find(".twoName").val("");
							parent.find(".add-subs").hide();
						});

					$(".loading").hide();
		            $(".loading-bg").hide();
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		}
		
		//加载学科点击事件
		parent.find("i[name='oneStatus']").off('click').on("click",function(){
				$.ajax({
					url:rootPath + "/sysConfigItem/changeStatus",
					type:"post",
					data:{"id":parent.find(".oneId").val(),"status":parent.find(".oneStatus").val()},
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
								data:{"id":parent.find(".oneId").val()},
								dataType:"json",
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
						}else if(data.fail != null && data.fail != ""){
					        $(".loading").hide();
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
 								data:{"id":parent.find(".oneId").val()},
 								dataType:"json",
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
							});
						}else{
							$.msg("修改时遇到问题，请重新尝试！",1000);
							location.reload();
						}
					}
				});
			});
		
		//删除
		$(".btn-del-pro").off('click').on("click",function(){
			var oneItemId = $(this).attr("data-id");
			$.ajax({
				url:rootPath + "/sysConfigItem/changeStatus",
				type:"post",
				data:{"id":oneItemId,"status":parent.find(".oneStatus").val()},
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
								data:{"id":oneItemId},
								dataType:"json",
								success:function(data){
									if(data.msg == "success"){
		        						location.href = rootPath + "/sysConfigItem/project";
							            $(".loading").show();
							            $(".loading-bg").show();
									}else{
										$.msg(data.msg,1000);
			    			            $(".loading").hide();
			    			            $(".loading-bg").hide();
									}
								}
							});
					}else if(data.fail != null && data.fail != ""){
						$(".loading").hide();
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
						//bg = $('.add-subs-layer-bg');
					    //bg.fadeIn(200);
						//$("#stopPanel").show();
						//$(".stop-subs").height(classHeight + parseInt($("#classHint").height()));
						/*$(".btn-stop").click(function(){
    			              	$(".loading").show();
			    			$("#stopPanel").hide();
							//删除
							$.ajax({
 								url : rootPath+"/sysConfigItem/delpro",
 								type:"post",
 								data:{"id":oneItemId},
 								dataType:"json",
 								success:function(data){
 									if(data.msg == "success"){
		        						location.href = rootPath + "/sysConfigItem/project";
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
						$.confirm("该学科下有在售的课程，如果停用、删除学科，这些课程将不可见，确认停用该学科吗？",function(b){
							if(b){
								$.ajax({
	 								url : rootPath+"/sysConfigItem/delpro",
	 								type:"post",
	 								data:{"id":oneItemId},
	 								dataType:"json",
	 								success:function(data){
	 									if(data.msg == "success"){
			        						location.href = rootPath + "/sysConfigItem/project";
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
					}else{
						$.msg("修改时遇到问题，请重新尝试！",1000);
						$(".loading").hide();
			            $(".loading-bg").hide();
					}
				}
			});
		});
	});
    		//编辑
    		$(".btn-edit-pro").off('click').on("click",function(){
				$.ajax({
					url:rootPath + "/sysConfigItem/findById",
					type:"post",
					data:{"id":$(this).attr("data-id")},
					dataType:"json",
					success:function(data){
		    			$("#addPro").show();
		    			$("#addOrUpdateItemid").val(data.id);
		    			$("#oneItemName").val(data.name);
                        $("#itemCode").val(data.itemCode);
		    			$("#itemPic").val(data.pic);
		    			$("#remark").val(data.remark);
		    			$("#remarkHint").html((data.remark).length);
		    			$("#status").val("update");
					}
				});
    		});
    		$("#cancel").off('click').on("click",function(){
    			$("#stopPanel").hide();
				$(".loading").hide();
	            $(".loading-bg").hide();
    		});
    		
    		$("#oneCancel").off('click').on("click",function(){
    			$("#nameHith").remove();
    			$("#addPro").hide();
    			$("#bigimage").remove();
    		});
    		$("#createPro").off('click').on("click",function(){
    			$("#addPro").show();
    			$("#addOrUpdateItemid").val("");
    			$("#oneItemName").val("");
    			$("#remark").val("");
    			$("#status").val("add");
    		});
    		var checkNameOk = false;
    		//检查学科名称唯一性
    		$("#oneItemName").change(function(){
    			$.ajax({
    				url:rootPath + "/sysConfigItem/checkName",
    				type:"post",
    				data:{"id":$("#addOrUpdateItemid").val(),"itemName":$("#oneItemName").val(),"status":$("#status").val(),"itemType":1},
    				dataType:"text",
    				success:function(data){
    					if(data == "false"){
    						checkNameOk = false;
    						$.msg("当前学科名称已存在！",1000);
    					}else{
    						checkNameOk = true;
    					}
    				}
    			});
    		});

    		//添加学科
       		$("#addOne").off('click').on("click",function(){
       			var itemId = $("#addOrUpdateItemid").val();
       			var pic = $(".item-icons.active").find("img").attr("src");
       			if(typeof(pic) != "undefined"){
           			pic = pic.substring(pic.indexOf("icons"));
       			}else{
       				pic = null;
       			}
       			var oldpic = $(".item-icons.active").find("img").attr("data-url");
       			if(typeof(oldpic) == "undefined"){
       				oldpic = null;
       			}
       			
       			if(itemId == "" && (pic == null || pic == "")){
       				$.msg("请选择图标！");
					return false;
       			}
       			
       			// if(($("#remark").val()).length >128){
    				// $("#remark").focus();
    				// $("#remark").select();
       			// 	return false;
       			// }
   				if($.trim($("#oneItemName").val()) == '' || $.trim($("#oneItemName").val()) == null){
   					$.msg("分类名称不能为空！",1000);
					return false;
   				}
                if($.trim($("#itemCode").val()) == '' || $.trim($("#itemCode").val()) == null){
                    $.msg("分类编码不能为空！",1000);
                    return false;
                }
       			if(itemId == ""){
           			if(checkNameOk){
            			$.ajax({
            				url:rootPath + "/sysConfigItem/checkName",
            				type:"post",
            				data:{"id":itemId,"itemName":$("#oneItemName").val(),"status":$("#status").val(),"itemCode":$("#itemCode").val()},
            				dataType:"text",
            				success:function(data){
            					if(data == "true"){
            						$("#nameHith").remove();
        	           				$.ajax({
        		        				url:rootPath + "/sysConfigItem/addPro",
        		        				type:"post",
        		        				data:{"itemName":$("#oneItemName").val(),"itemType":1,"itemPic":oldpic,"itemBackPic":pic,"remark":$("#remark").val(),"itemCode":$("#itemCode").val()},
        		        				dataType:"text",
        		        				beforeSend:function(XMLHttpRequest){
        		        					$("#addPro").hide();
        		      		              	$(".loading").show();
        		      		              	$(".loading-bg").show();
        		        				},
        		        				success:function(data){
        		        					debugger;
        		        					if(data == "true"){
        		        						location.href = rootPath + "/sysConfigItem/project";
        	    		      		            $(".loading").show();
        	    		      		            $(".loading-bg").show();
        		        					}else{
        		        						$.msg("添加学科时出错！",1000);
        		        					}
        		        				}
        		       				});
            					}
            				}
            			});
           			}else{
           				$.msg("当前学科名称已存在！",1000);
           			}
       			}else{
       				$.ajax({
        				url:rootPath + "/sysConfigItem/checkName",
        				type:"post",
        				data:{"id":$("#addOrUpdateItemid").val(),"itemName":$("#oneItemName").val(),"status":$("#status").val()},
        				dataType:"text",
        				success:function(data){
        					if(data == "true"){
        						$("#nameHith").remove();
    	           				$.ajax({
    	            				url:rootPath + "/sysConfigItem/update",
    	            				type:"post",
    	            				data:{"id":itemId,"itemName":$("#oneItemName").val(),"itemType":1,"itemPic":oldpic,"itemBackPic":pic,"remark":$("#remark").val(),"itemCode":$("#itemCode").val()},
    	            				dataType:"text",
    	            				beforeSend:function(XMLHttpRequest){
    	          		              $(".loading").show();
    	          		              $(".loading-bg").show();
    	            				},
    	            				success:function(data){
    	            					if(data == "true"){
    		        						location.href = rootPath + "/sysConfigItem/project";
    	            					}else{
    	            						$.msg("修改学科时出错！",1000);
    	            					}
    		        				}
    	           				});
        					}
        				}
        			});
       			}
       		});
       		//换一批图标
       		$(".btn-nexticon").off('click').on("click",function(){
       			$(".itemiconone").html("");
       			$("#bigimage").remove();
       			$("#jia").show();
       			selIcon((parseInt($("#iconpage").val()) + 1),parseInt($("#pagecount").val()));
       		});
       		
       		/*
       		//选项改变事件
       		$("#itemPic").change(function(){
       			$("#icon").attr("src",$("#itemPic").val());
       		});
       		$("#icon").hover(function(e){
       			//新建一个p标签来存放大图片，this.rel就是获取到a标签的大图片的路径，然后追加到body中
       			$("body").append("<p id='bigimage' style='position:absolute;Z-index:1000;'><img id='bigIcon' src='"+ ($("#icon").attr("src")) +"'/></p>");
       			//将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，然后以fadeIn的效果显示
       			$("#bigimage").css({top:(e.pageY ),left:(e.pageX + 40 )}).fadeIn("fast");
       			},function(){
       			//移除新增的p标签
       				$("#bigimage").remove();
       		});
			$("#icon").mousemove(function(e){ //绑定一个鼠标移动的事件
				//将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，这样大图片就能跟随图片而移动了
				$("#bigimage").css({top:(e.pageY ),left:(e.pageX + 40 )});
			});*/
		});

function check(){
	$("#remarkHint").html(($("#remark").val()).length);
}

/*function init(){
	//加载 学科小类
	$(".block").each(function(){
		var dat = $(this).find(".b-content");
		var parent = $(this);
		$.ajax({
			url: rootPath + "/sysConfigItem/twoProListAjax",
			type:"post",
			data:{"oneItemId":$(this).find(".oneId").val()},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				dat.html(data);
    			//判断能不能用
    			if(parent.find(".oneStatus").val() == 1){
    				parent.find("em").attr("class","disabled");
    				parent.find(".r-subs-title a").html("");
    			}
    			if(parent.find(".b-content").find("li").length > 5){
    		        parent.on("mouseenter",".hidden",function(){
    		        	$(this).animate({"height":"500px"},200);
    		        })
    		        .on("mouseleave",".hidden",function(){
    		        	$(this).animate({"height":"100%"},200);
    		        });
    			}
				$(".add-subs").hide();
				parent.find("div[name='twoToCreate']").unbind('click').click(function(){
					if(parent.attr("data-type") == "stop"){
						return false;
					}
						parent.find(".add-subs").show();
						parent.find(".add-subs").attr("data-parentId",parent.find(".oneId").val());
					});
				parent.find("input[name='twoCancel']").unbind('click').click(function(){
						parent.find(".twoName").val("");
						parent.find(".add-subs").hide();
					});
			},
			complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	});
	
}*/

function selIcon(page,pagecount){
	//获得页码
	if((page) > pagecount){
		page = 1;
	}
	//获得已有标签id
	$.ajax({
		url : rootPath + "/sysConfigItem/selIconList",
		type:"post",
		data:{"page":page,"pageSize":8},
		dataType:"json",
		success:function(data){
			$("#jia").hide();
			$.each(data.msg.iconList,function(index,item){
				if(index <= 3){
					$(".itemiconone:eq(0)").append("<span class='item-icons' style='width:60px;height:30px;'><img src='http://"+ data.url + "/" + item.iconBackUrl + "' width='20px' data-url='" + item.iconUrl + "' title='" + item.iconName + "' style='padding:5px;'/></span>");
				}else{
					$(".itemiconone:eq(1)").append("<span class='item-icons' style='width:60px;height:30px;'><img src='http://"+ data.url + "/" + item.iconBackUrl + "' width='20px' data-url='" + item.iconUrl + "' title='" + item.iconName + "' style='padding:5px;'/></span>");
				}
			});
			imgclick();
			$("#iconpage").val(data.msg.nowpage);
			$("#pagecount").val(data.msg.pagecount);
		}
	});
}

	function imgclick(){
		$(".item-icons").click(function(){
			$("#bigimage").remove();
			$(this).attr("class","item-icons active");
			$(this).prevAll().attr("class","item-icons");
			$(this).nextAll().attr("class","item-icons");
			getAbsPoint($(this)[0]);
		});
	}
	function getAbsPoint(obj){   
	   var   x,y; 
	   x=obj.offsetLeft;
	   y=obj.offsetTop;
	   /*margin-top:-40px;margin-left:-365px*/
	   $(".itemiconone:eq(0)").after("<p id='bigimage' style='position:absolute;Z-index:1000;margin-top:15px;margin-left:20px'><i class='iconfont' style='color:green;font-size:20px'>&#xe611;</i></p>");
	   $("#bigimage").css({top:(y ),left:(x )}).fadeIn("fast");
	}