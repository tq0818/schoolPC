$(function(){
	$selectSubMenu('netschool_item'); 
	$("#remarkHint").html("0");

    $(".btn-edit-pro").on("click",function(){
    	var _this = $(this),
        bg = $('.loading-bg');

	    bg.fadeIn(200,function(){
	    });
    });
	    $('#oneCancel').on('click',function(){
	        var _this = $(this),
	        bg = $('.loading-bg');
	        bg.fadeOut(200);
		});
	//编辑
	$(".btn-edit-pro").each(function(){
		$(this).click(function(){
			$.ajax({
				url:rootPath + "/sysConfigItem/findById",
				type:"post",
				data:{"id":$(this).attr("data-id")},
				dataType:"json",
				success:function(data){
	    			$("#addPro").show();
	    			$("#addOrUpdateItemid").val(data.id);
	    			$("#oneItemName").val(data.name);
	    			$("#itemPic").val(data.pic);
	    			$("#remark").val(data.remark);
	    			$("#remarkHint").html((data.remark).length);
	    			$("#status").val("update");
				}
			});
		});
	});
	//加载 学科小类
    		$(".clear li").each(function(){
    			var dat = $(this).find(".r-subs");
    			var parent = $(this);
    			
    			$.ajax({
    				url: rootPath + "/sysConfigItem/moreTwoProListAjax",
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
    	    			if(parent.find(".oneStatus").val() == 0){
    	    				parent.find("em").attr("class","disabled");
    	    				parent.find(".r-subs-title a").html("");
    	    			}
    	    			
    					$(".add-subs").hide();
    					parent.find("a[name='twoToCreate']").unbind('click').click(function(){
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
    			
        		//加载学科点击事件
    			$(this).find("a[name='oneStatus']").click(function(){
     				$.ajax({
     					url:rootPath + "/sysConfigItem/changeStatusCompany",
     					type:"post",
     					data:{"id":parent.find(".oneId").val(),"status":parent.find(".oneStatus").val()},
     					dataType:"json",
     					beforeSend:function(XMLHttpRequest){
     			              $(".loading").show();
     			              $(".loading-bg").show();
     			         },
     					success:function(data){
     						if(data == "error"){
     							$('<div class="c-fa">'+ "当前学科还有分校在使用！" +'</div>').appendTo('body').fadeIn(100).delay(3000).fadeOut(200,function(){
                					$(this).remove();
     							});
     						}else{
     							location.reload();
     						}
     					},
     					complete:function(XMLHttpRequest,textStatus){
     						$(".loading").hide();
     			            $(".loading-bg").hide();
     			        }
     				});
     			});
        		
 			});
    		
    		$("#cancel").click(function(){
    			$("#stopPanel").hide();
    		});
    		
    		$("#oneCancel").click(function(){
    			$("#nameHith").remove();
    			$("#addPro").hide();
    		});
    		
    		$("#createPro").click(function(){
    			$("#addPro").show();
    			$("#addOrUpdateItemid").val("");
    			$("#oneItemName").val("");
    			$("#itemPic").val("-1");
    			$("#remark").val("");
    			$("#status").val("add");
    		});
    		//检查学科名称唯一性
    		$("#oneItemName").change(function(){
    			$.ajax({
    				url:rootPath + "/sysConfigItem/checkName",
    				type:"post",
    				data:{"id":$("#addOrUpdateItemid").val(),"itemName":$("#oneItemName").val(),"status":$("#status").val(),"itemType":1},
    				dataType:"text",
    				success:function(data){
    					if(data == "false"){
    						$("#nameHith").remove();
    						$("#addPro").find(".c:eq(0)").find(".l-content").after("<span id='nameHith' class='l-content' style='color:red;padding-left:85px;'>当前学科名称已存在！</span>");
    					}else{
    						$("#nameHith").remove();
    					}
    				}
    			});
    		});
    		
    		//添加学科
       		$("#addOne").click(function(){
       			if(($("#remark").val()).length > 128){
    				$("#remark").focus();
    				$("#remark").select();
    				return false;
       			}
       			var pic = $("#itemPic").val();
       			pic = pic.substring(pic.indexOf("icons"));
       			
       			var itemId = $("#addOrUpdateItemid").val();
    			$.ajax({
    				url:rootPath + "/sysConfigItem/checkName",
    				type:"post",
    				data:{"id":$("#addOrUpdateItemid").val(),"itemName":$("#oneItemName").val(),"status":$("#status").val()},
    				dataType:"text",
    				success:function(data){
    					if(data == "true"){
    						$("#nameHith").remove();
    	           			if(itemId == ""){
    	           				$.ajax({
			        				url:rootPath + "/sysConfigItem/addPro",
			        				type:"post",
			        				data:{"itemName":$("#oneItemName").val(),"itemType":1,"itemPic":pic,"remark":$("#remark").val()},
			        				dataType:"text",
			        				beforeSend:function(XMLHttpRequest){
			      		              $(".loading").show();
			      		              $(".loading-bg").show();
			        				},
			        				success:function(data){
			        					if(data == "true"){
			        						location.reload();
			        					}else{
			        						$('<div class="c-fa">'+ "添加学科时出错！" +'</div>').appendTo('body').fadeIn(100).delay(3000).fadeOut(200,function(){
			                					$(this).remove();
			     							});
			        					}
			        				}
			       				});
    	           			}else{
    	           				$.ajax({
    	            				url:rootPath + "/sysConfigItem/update",
    	            				type:"post",
    	            				data:{"id":itemId,"itemName":$("#oneItemName").val(),"itemPic":pic,"remark":$("#remark").val()},
    	            				dataType:"text",
    	            				beforeSend:function(XMLHttpRequest){
    	          		              $(".loading").show();
    	          		              $(".loading-bg").show();
    	            				},
    	            				success:function(data){
    	            					if(data == "true"){
    	            						location.reload();
    	            					}else{
    	            						$('<div class="c-fa">'+ "修改学科时出错！" +'</div>').appendTo('body').fadeIn(100).delay(3000).fadeOut(200,function(){
    	                    					$(this).remove();
    	         							});
    	            					}
    		        				}
    	           				});
    	           			}
    					}
    				}
    			});
       		});
       		
       		//选项改变事件
       		$("#itemPic").change(function(){
       			$("#icon").attr("src",$("#itemPic").val());
       			$("#icon").attr("style","background-color:blue");
       		});
       		$("#icon").hover(function(e){
       			//新建一个p标签来存放大图片，this.rel就是获取到a标签的大图片的路径，然后追加到body中
       			$("body").append("<p id='bigimage' style='position:absolute;Z-index:1000;'><img id='bigIcon' src='"+ ($("#icon").attr("src")) +"' style='background-color:blue;'/></p>");
       			//将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，然后以fadeIn的效果显示
       			$("#bigimage").css({top:(e.pageY ),left:(e.pageX + 40 )}).fadeIn("fast");
       			},function(){
       			//移除新增的p标签
       				$("#bigimage").remove();
       		});
			$("#icon").mousemove(function(e){ //绑定一个鼠标移动的事件
				//将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，这样大图片就能跟随图片而移动了
				$("#bigimage").css({top:(e.pageY ),left:(e.pageX + 40 )});
			});
 		});

function check(){
	$("#remarkHint").html(($("#remark").val()).length);
}