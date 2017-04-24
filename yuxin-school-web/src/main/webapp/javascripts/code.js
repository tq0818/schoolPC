(function ($) {
 //导出按钮
    $(".q-derive").click(function () {
        $(".q_load").show();
        $(".layer-tips-bg").show();
    });
    //启用,禁止展开隐藏
    $(".qnormal").click(function () {
        $(this).toggleClass("open").toggleClass("close");
        $(this).siblings(".qi").toggleClass("open").toggleClass("close");
        if ($(this).siblings(".qi").hasClass("open")) {
            $(this).siblings(".qi").html("已启用");
            $(".toggle").show();
        } else {
            $(this).siblings(".qi").html("已禁用");
            $(".toggle").hide();
        }
    });
    $(".q-open").click(function(){
            $("#q-cont").toggle();
    });
    $(".q-open2").click(function(){
            $(".q-hide").toggle();
    });
    $(".use-time em").click(function () {
        $(this).addClass("all").siblings("em").removeClass("all");
    });
    $(".screen-right-cont em.normal").off().on("click",function(){
            if($(this).hasClass("open")){
                $(this).removeClass("open").addClass("close").html("&#xe604;");
                $(this).parents(".screen-right-title").find(".i").removeClass("open").addClass("close").text("已禁用");
            }else if($(this).hasClass("close")){
                $(this).removeClass("close").addClass("open").html("&#xe603;");
                $(this).parents(".screen-right-title").find(".i").removeClass("close").addClass("open").text("已启用");
            };
        });
    
    $('em.qnormal').off().on('click',function(){
    	 if($(this).hasClass("open")){
             $(this).removeClass("open").addClass("close").html("&#xe604;");
             $('#erjiyaoqing').removeClass("open").addClass("close").text('已禁用');
             $('#sanjibuttoncon').hide();
    	 }else if($(this).hasClass("close")){
             $(this).removeClass("close").addClass("open").html("&#xe603;");
             $('#erjiyaoqing').removeClass("close").addClass("open").text('已启用');
             $('#sanjibuttoncon').show();
    	 };
    })
    //选中取消选中
    $(".btn-cro  .q_only").find("input[type='text']").attr("disabled", "disabled");
    $(".btn-cro .q_only .qcheck").click(function () {
        $(this).find("input[type='radio']").attr("checked", "true");
        //$(this).css("color", "#333333").siblings(".text-right").css("color", "#333333").find("input[type='text']").removeAttr("disabled");
        $(this).parents(".text1").siblings(".text1").find("input[type='radio']").removeAttr("checked");
        $(this).parents(".text1").siblings(".text1").find(".qcheck").css("color", "#949494").siblings(".text-right").css("color", "#949494");
        $(this).parents(".text1").siblings(".text1").find("input[type='text']").attr("disabled", "disabled");
    });
    
})(jQuery)
