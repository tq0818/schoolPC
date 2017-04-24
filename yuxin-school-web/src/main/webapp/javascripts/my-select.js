(function($){
    $(function(){
        //阻止事件冒泡

        function stopEvent(){ //阻止冒泡事件
            //取消事件冒泡
            var e=arguments.callee.caller.arguments[0]||event; //若省略此句，下面的e改为event，IE运行可以，但是其他浏览器就不兼容
            if (e && e.stopPropagation) {
                // this code is for Mozilla and Opera
                e.stopPropagation();
            } else if (window.event) {
                // this code is for IE
                window.event.cancelBubble = true;
            }
        }
        //    模拟select
        $(".user-choses-selected,.select-sanjiao").on("click", function () {
            $(".user-choses-selected,.user-chose-position div").addClass("active");
            var click=$(this).attr("click");
            if(click=="true"){
                $(".user-chose-position").hide();
                $(".select-sanjiao b").removeClass("active");
                $(".user-choses-selected,.select-sanjiao").attr("click","false");
                stopEvent()
            }else{
                $(".user-chose-position").show();
                $(".select-sanjiao b").addClass("active");
                $(".user-choses-selected,.select-sanjiao").attr("click","true");
                stopEvent()
            }
        }).on("mouseleave",function(){
            $(".user-choses-selected,.user-chose-position div").removeClass("active");
        }).on("mouseenter",function(){
            $(".user-choses-selected,.user-chose-position div").addClass("active");
        });

        
        $(".user-chose-position > div").on("click", function () {
        	if($(this).attr("value")=='ueser0'){
        		$("#memberList").show();
        	}else{
        		$("#memberList").hide();
        	}
            $(".user-choses-selected").html($(this).html()).attr("value",$(this).attr("value"));
            $(".user-chose-position").hide();
            $(".select-sanjiao b").removeClass("active");
            $(".user-choses-selected,.select-sanjiao").attr("click","false");
        }).on("mouseenter",function(){
            $(".user-choses-selected,.user-chose-position div").addClass("active");
        });
        $(document).on("click",function(){
            $(".user-chose-position").hide();
            $(".user-choses-selected,.user-chose-position div").removeClass("active");
            $(".select-sanjiao b").removeClass("active");
            $(".user-choses-selected,.select-sanjiao").attr("click","false");
        })
    })
})(jQuery)
