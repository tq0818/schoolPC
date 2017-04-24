$(function(){
	$('.btn-more')
    .on('click',function(){
        $('.layer-bg,.layer').fadeIn(200);
    });
	// 关闭弹层
	$('.layer')
	    .on('click',function(){
	        $('.layer-bg,.layer').fadeOut(200);
	    })
	    // 如果单击的是A
	    .on('click','a',function(){
	        var url = $(this).attr('href') || '/';
	
	        $('.layer-bg,.layer').fadeOut(200);
	        location.reload();
	        return false;
	    });
	
	$(".school").find("li").each(function(){
		var project = $(this).find(".c:eq(1)").find("span:eq(1)");
		var teacher = $(this).find(".c:eq(2)").find("span:eq(1)");
		var senate = $(this).find(".c:eq(3)").find("span:eq(1)");
		var campus = $(this).find(".c:eq(4)").find("span:eq(1)");
		var classroom = $(this).find(".c:eq(5)").find("span:eq(1)");
		var student = $(this).find("h2").find("em:eq(0)");
		$.ajax({
			url:rootPath + "/sysConfigSchool/ajaxSel",
			type:"post",
			data:{"schoolId":$(this).attr("data-id")},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				senate.html("");
				project.html("");
				campus.html("");
				teacher.html("");
				classroom.html("");
				student.html("");
				
				student.text("已招生：" + data.student + " 人");
				senate.text(data.senate);
				project.text(data.project);
				campus.text(data.campus);
				teacher.text(data.teacher);
				classroom.text(data.classroom);
			},
			complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	});
	
	$(".btn-add").click(function(){
		$.ajax({
			url:rootPath + "/sysConfigSchool/sel",
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					addOrEdit(null);
				}/*else if(data.msg == "authority"){
					$('<div class="c-fa">'+ "请先完成机构认证！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();location.replace(rootPath + "/users/guide");});
				}*/else{
					$('<div class="c-fa">'+ "没有更多分校了，先去申请开通更多分校吧！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
				}
			}
		});
	});
	
	$(".btn-update").click(function(){
		addOrEdit($(this).attr("data-id"));
	});
	
		//更改分校状态
		$(".btn-status").click(function(){
			$.ajax({
				url:rootPath + "/sysConfigSchool/changeStatus",
				type:"post",
				data:{"id":$(this).attr("data-id"),"delFlag":$(this).attr("data-status")},
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(data){
					if(data.msg == "success"){
						location.reload();
					}else if(data.msg == "error"){
						$(".loading").hide();
			            $(".loading-bg").hide();
					}else{
						$(".layer").find(".alert-msg").html("");
						$(".layer").find(".alert-msg").append("<span style='color:red;'>还有以下课程在售卖</span><br>");
						$(".layer").find(".text-center").html("");
						$.each(data.msg,function(index,item){
							if(index > 4){
								$(".layer").find(".alert-msg").append("<span style='font-size:12px;'>......</span><br>");
								return false;
							}
							$(".layer").find(".alert-msg").append("<span style='font-size:12px;'>" + item + "</span><br>");
						});
						$(".layer").find(".text-center").html("<a href='javascript:;' class='btn btn-primary'>确定</a>");
						$(".layer,.layer-bg").show();
						$(".loading").hide();
			            $(".loading-bg").hide();
					}
				}
			});
		});
		
	// 关闭弹层
    $('.layer')
        .on('click','i.close',function(){
            $('.layer-bg,.layer').fadeOut(200);
        })
        // 如果单击的是A
        .on('click','a',function(){
            var url = $(this).attr('href') || '/';

            $('.layer-bg,.layer').fadeOut(200);
            location.href = url;
            return false;
        }); 
});

function addOrEdit(id){
	$.ajax({
		url:rootPath + "/sysConfigSchool/getAddressById",
		type:"post",
		data:{"id":id},
		dataType:"html",
		success:function(data){
			$(".right-side").remove();
			$(".screen-right").html(data).show();
		}
	});
}