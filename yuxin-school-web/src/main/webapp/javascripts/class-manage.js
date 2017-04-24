/**
 * Created by zhuo on 2016/3/18.
 */
(function($){
    $(function() {
    	 
//        $( "#sortable" ).sortable({
//            placeholder: "ui-state-highlight"
//        });
//        $( "#sortable" ).disableSelection();
//
//        $( "div.droptrue" ).sortable({
//            connectWith: "div"
//        });
//        $( "div.dropfalse" ).sortable({
//            connectWith: "div",
//            dropOnEmpty: false
//        });
//        $( ".sortable-sec" ).disableSelection();

        $(document).on('click.open-btn','.ui-state-parent .open-btn',function(){  
        	
        	if($(this).hasClass("hide")){
   			    $(this).html("&#xe681;").parents(".ui-state-parent").find(".open-box").slideDown(200);
   			    $(this).removeClass("hide");
	       	}else{
	       		$(this).html("&#xe67f;").parents(".ui-state-parent").find(".open-box").slideUp(200);
      		    $(this).addClass("hide");
	       	}
        })
        //取消
        $(".cancel-btn").on("click",function(){
        	$(".add-grade-pop").fadeOut(200);
        	$(".choice-class-pop").fadeOut(200);
        	$(".delete-sure").fadeOut(200);
        });
        
      //开关
        $(".tit-font em.normal").off().on("click",function(){
            if($(this).hasClass("open")&&(!($(this).hasClass("warning-btn")))){
                $(this).removeClass("open").addClass("close").html("&#xe604;");
                $(this).parents(".tit-font").find(".i").removeClass("open").addClass("close").text("不允许");
                $(".setstagePrice").css("display","none");
                return false;
            }else if($(this).hasClass("close")){
                $(this).removeClass("close").addClass("open").html("&#xe603;");
                $(this).parents(".tit-font").find(".i").removeClass("close").addClass("open").text("允许");
                $(".setstagePrice").css("display","block");
                return false;
            };
          
        });
        //关闭课程弹框
        $(".pop-close-btn").on("click",function(){
        
            $(".pop-fixed").fadeOut(200);
            $("#stage_id").val("");
			$("#stage_title").val("");
			$("#stage_price").val("");
			$("#stage_description").val("");
        });
    });
})(jQuery);