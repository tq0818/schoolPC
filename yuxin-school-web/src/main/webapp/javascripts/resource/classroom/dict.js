
    	$(function(){
    		$("#campus").val($(".btn.btn-mini.btn-success").attr("data-id"));
    		
            // 取消添加教室
            $('.btn-close').on('click',function(){
               var _this = $(this),
                   parent = $('.add-teacher'),
                   bg = $('.add-teacher-room-bg');

               parent.fadeOut(200,function(){
                   bg.fadeOut(200);
               });
           });
            var check = false;
            //检查教室名字唯一性
            $("#roomName").change(function(){
                if($("#roomId").val() == ""){
                	$.ajax({
                		url:rootPath + "/sysConfigClassroom/checkUnique",
                		type:"post",
                		data:{"roomName":$("#roomName").val(),"campusId":$("#campus").val()},
                		dataType:"text",
                		success:function(data){
                			if(data == "success"){
                				$("#hint").remove();
                				$("#roomName").after("<span id='hint' style='color:red;'>已存在</span>");
                				check = false;
                			}else{
                				$("#hint").remove();
                				check = true;
                			}
                		}
                	});
                }else{
                	check = true;
                }
            });
            
            //确定添加
            $(".btn-primary.add-room").click(function(){
            	$('.add-teacher-room-bg').css("z-index","10001");
            	if($("#roomName").val() == ""){
    				$('<div class="c-fa">请填写教室名</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
	                	$('.add-teacher-room-bg').css("z-index","98");
            		return false;
            	}
            	if($("#roomId").val() != ""){
            		check = true;
            	}
            	if(check){
                	var url = null;
                	if($("#roomId").val() == ""){
                		url = rootPath + "/sysConfigClassroom/addClassroom";
                	}else{
                		url = rootPath + "/sysConfigClassroom/editClassroom";
                	}
                	$.ajax({
                		url:url,
                		type:"post",
                		data:{"id":$("#roomId").val(),"roomName":$("#roomName").val(),"roomAttrCode":$("#attr").val(),"roomTypeCode":$("#type").val(),"roomLevelCode":$("#level").val(),"rentScope":$("#time").val(),"address":$("#address").val(),"busLine":$("#busLine").val(),"remark":$("#remark").val(),"campusId":$("#campus").val(),"seatNumCode":$("#kind").val(),"status":$("[name='status']:checked").val()},
                		dataType:"text",
                		success:function(data){
                			if(data == "success"){
                				$('<div class="c-fa">教室信息保存成功！</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
                					$(this).remove();
                		               var _this = $(this),
                	                   parent = $('.add-teacher'),
                	                   bg = $('.add-teacher-room-bg');

                	               parent.fadeOut(200,function(){
                	                   bg.fadeOut(200);
                	               });
        							$.ajax({
        								url:rootPath + "/sysConfigClassroom/selClassroom",
        								type:"post",
        								data:{"campusId":$(".btn-mini.btn-success").attr("data-id")},
        								dataType:"html",
        								beforeSend:function(XMLHttpRequest){
        						              $(".loading").show();
        						              $(".loading-bg").show();
        						         },
        								success:function(data){
        									$(".sc-lists").html("");
        									$(".sc-lists").html(data);
        									$('.add-teacher-room-bg').hide();
        	           	                	$('.add-teacher-room-bg').css("z-index","98");
        								},
        								complete:function(XMLHttpRequest,textStatus){
        									$(".loading").hide();
        						            $(".loading-bg").hide();
        						        }
        							});
                				}
                				);
                			}else{
                				$('<div class="c-fa">'+ "保存教室信息时出现异常！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
           	                	$('.add-teacher-room-bg').css("z-index","98");
                			}
                		}
                	});
            	}else{
    				$('<div class="c-fa">'+ "教室名称已存在！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
	                $('.add-teacher-room-bg').css("z-index","98");
            	}
            });
    	});