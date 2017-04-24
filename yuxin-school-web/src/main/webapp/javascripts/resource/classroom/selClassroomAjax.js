$(function(){// 编辑按钮
    $('.btn-edit,.btn-add-class').on('click',function(){
       var _this = $(this),
           parent = $('.add-teacher'),
           bg = $('.add-teacher-room-bg');

       bg.fadeIn(200,function(){
           parent.fadeIn(200);
       });
    });
    
    $(".btn-status").on("click",function(){
    	var _this = $(this),
        bg = $('.add-teacher-room-bg');

	    bg.fadeIn(200,function(){
	    });
    });
	
    $('.btn-cancel').on('click',function(){
        $(this).parents('.add-subs-layer').fadeOut(200,function(){
            $('.add-teacher-room-bg').fadeOut(200);
        });
    });

	//编辑教室
	$(".btn-edit").each(function(){
		$(this).click(function(){
				$.ajax({
					url:rootPath + "/sysConfigClassroom/getDict",
					type:"post",
					data:{"id":$(this).attr("data-id")},
					dataType:"html",
					success:function(data){
						$(".add-teacher").html("");
						$(".add-teacher").html(data);
					}
				});
		});
	});
	//更改教室状态
	$(".btn-status").each(function(){
		$(this).click(function(){
			var status = $(this).attr("data-status");
			var id = $(this).attr("data-id");
			if(status == 1){
				status = 0 ;
				$.ajax({
					url:rootPath + "/sysConfigClassroom/stopStatus",
					type:"post",
					data:{"id":id,"status":status},
					datyType:"json",
					success:function(data){
						if(data.msg == "success"){
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
								},
								complete:function(XMLHttpRequest,textStatus){
									$(".loading").hide();
						            $(".loading-bg").hide();
						        }
							});
						}else if(data.msg == "fail"){
							$('<div class="c-fa">修改教室状态过程中出现错误！</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
						}else{$(".stopTitle").remove();
 							$("#classHint").html("");
 							$.each(data.cmlList,function(index,item){
 								if(index > 4){
 									$("#classHint").append("<tr><td colspan='2' align='center'>.........</td></tr>");
 									return false;
 								}
 								$("#classHint").append("<tr><td><span class='h'><span class='h-title'>日期："+ (item.lessonDate) +"</span></span></td><td><span class='h'><span class='h-list'>课程："+ (item.lessonName) +"</span></span></td></tr>");
 							});
 							$("#stopPanel").show();
 							$(".stop-subs").height(classHeight + parseInt($("#classHint").height()));
 							$("#changeOk").attr("data-id",id);
 							$("#changeOk").attr("data-status",status);
 							return false;
						}
					}
				});
			}else{
				status = 1 ;
				$.ajax({
					url:rootPath + "/sysConfigClassroom/editClassroom",
					type:"post",
					data:{"id":id,"status":status},
					datyType:"text",
					success:function(data){
						if(data == "success"){
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
								},
								complete:function(XMLHttpRequest,textStatus){
									$(".loading").hide();
						            $(".loading-bg").hide();
						        }
							});
						}else{
							$('<div class="c-fa">修改教室状态过程中出现错误！</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
						}
					}
				});
			}
		});
	});
	
	$("#changeOk").click(function(){
		$.ajax({
			url:rootPath + "/sysConfigClassroom/editClassroom",
			type:"post",
			data:{"id":$(this).attr("data-id"),"status":$(this).attr("data-status")},
			datyType:"text",
			success:function(data){
				if(data == "success"){
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
							$(".sc-lists").html(data);
							$('.add-teacher-room-bg').hide();
							$('.add-subs-layer').hide();
						},
						complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				        }
					});
				}else{
					$('<div class="c-fa">修改教室状态过程中出现错误！</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				}
			}
		});
	});
});