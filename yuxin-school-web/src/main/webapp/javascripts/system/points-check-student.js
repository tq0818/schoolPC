(function($){
    $(function(){
        //点击查看详情
        $(".points-check").on("click","td a",function(){
           $(".add-layer-bg,.check-student").removeClass("none")
        });
    //    点击查看详情弹出的关闭按钮
        $(".check-student").on("click",".upload-title .close",function(){
            $(".add-layer-bg,.check-student").addClass("none")
        })
    //    点击指定时间
        $(".t-content").on("click","a[mark='nos']",function(){
            $(this).removeClass("btn-default").addClass("btn-success");
            $(this).parents(".t-content").find(".daterangs").show();
        });
        $(".date-picker").datetimepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            minView: 2,
            autoclose: true
        });

    })
})(jQuery)
