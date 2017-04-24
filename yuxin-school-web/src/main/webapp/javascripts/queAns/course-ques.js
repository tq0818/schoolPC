/**
 * Created by zhuo on 2015/12/28.
 */
(function($){
    $(".timetable-box").on("click",function(){
    	var status = $(this).attr("status");
        if($(this).hasClass("qs1")){
            $(this).addClass("active").siblings().removeClass("active");
            $(".qr1").addClass("timetable-box").removeClass("timetables-box").find(".disable-box").css("display","none");
            $(".qr2").addClass("timetables-box").removeClass("timetable-box").find(".disable-box").css("display","block");
            if($(".qr2").hasClass("active")){
            	changeStatus(1,"STUDENT_ANSWER_QUESTION_AUTHO");
                $(".qr0").addClass("active").siblings().removeClass("active");
            }
            changeStatus(status,"STUDENT_SACN_QUESTION_AUTHO");
        }else if($(this).hasClass("qs2")){
            $(this).addClass("active").siblings().removeClass("active");
            $(".qr1,.qr2").addClass("timetable-box").removeClass("timetables-box").find(".disable-box").css("display","none");
            changeStatus(status,"STUDENT_SACN_QUESTION_AUTHO");
        }else{
            if(!($(this).hasClass("qr"))){
                $(this).addClass("active").siblings().removeClass("active");
                $(".qr1,.qr2").addClass("timetables-box").removeClass("timetable-box").find(".disable-box").css("display","block");
                $(".qr0").addClass("active").siblings().removeClass("active");
                changeStatus(status,"STUDENT_SACN_QUESTION_AUTHO");
                changeStatus(1,"STUDENT_ANSWER_QUESTION_AUTHO");
            }
        }
    });
    $(".qr").on("click",function(){
    	var status = $(this).attr("status");
        if(!($(this).hasClass("timetables-box"))){
        	changeStatus(status,"STUDENT_ANSWER_QUESTION_AUTHO");
            $(this).addClass("active").siblings().removeClass("active");
        }
    });
    $(".tit-font").on("click",".iconfont",function(){
    	var code = $(this).attr("code");
    	if($(this).hasClass("open")){
    		$(this).html("&#xe604;")
    		$(this).removeClass("open").addClass("close");
    		$(this).next(".i").removeClass("open").addClass("close");
    		$(this).next(".i").html("已禁用");
    		changeStatus(0,code);
    		if(code == 'COURSE_QUESTION_FUNCTION'){
    			$(".auth").html("&#xe604;")
        		$(".auth").removeClass("open").addClass("close");
    			$(".auth").next(".i").removeClass("open").addClass("close");
    			$(".auth").next(".i").html("已禁用");
    			changeStatus(0,"COURSE_QUESTION_AUTH");
    		}
    	}else{
    		$(this).html("&#xe603;")
    		$(this).removeClass("close").addClass("open");
    		$(this).next(".i").removeClass("close").addClass("open");
    		$(this).next(".i").html("已启用");
    		changeStatus(1,code);
    	}
    });
})(jQuery);
function changeStatus(status,code){
	$.ajax({
		url : rootPath+"/Question/changeStatus",
		type : "post",
		data : {"status":status,"code":code},
		success : function(data){
			if(data=="success"){
				$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
				});
				if(code == 'COURSE_QUESTION_FUNCTION' && status == 1){
					$("#ansShow").show();
				}
				if(code == 'COURSE_QUESTION_FUNCTION' && status == 0){
					$("#ansShow").hide();
				}
			}
		}
	});
}