(function ($) {
    $(function () {
       // 点击管理弹出页面
        $(document).on("click",".btn-admin",function(){
            
            $(".layer-tips-bg").show();
            if($(this).attr("buy") == 0){
                $(".order.layer2").fadeIn(200);
            }else{
                $(".order.layer1").fadeIn(200);
            }
            $(".tomanage").attr("comid",$(this).attr("comid"));
            $(".comXf").attr("comid",$(this).attr("comid"));
            $(".comUp").attr("comid",$(this).attr("comid"));
            $(".memOpen").attr("comid",$(this).attr("comid"));
        });
        //弹出框关闭按钮点击
        $(".upload-layer").on("click",".close,button",function(){
            $(this).parents(".upload-layer").hide();
            $(".layer-tips-bg").hide();
        });
        
        $(document).on("click",".tomanage",function(){
        	window.location.href = rootPath + "/companyAuthority/toManage/" + $(this).attr("comid");
        	return false;
        });
        
        // 机构管理页面各个按钮的弹出框
        $(".btn-wrap").on("click","button",function(){
            var name =$(this).attr("class");
            $(".layer-tips-bg").show();
            $(".layer-wrap").find("."+name).fadeIn(200);
        });

        // 日期插件的调用
        $(".date-picker").datetimepicker({
            language:  'zh-CN',
            format : "yyyy-mm-dd",
            minView : 2,
            autoclose : true
        });
        //日期显示到月
        $(".date-picker1").datetimepicker({
            format: 'yyyy-mm',
            weekStart: 1,
            autoclose: true,
            startView: 3,
            minView: 3,
            forceParse: false,
            language: 'zh-CN'
        });
        //点击修改并发数量页的tab
        $(".itm-line .tab").on("click","a",function(){
            $(".itm-line .tab a").removeClass("active");
            $(this).addClass("active");
            var n=$(".itm-line .tab a").index(this);
            $(".b-list").hide();
            $(".b-list").eq(n).show();
        });
    //    点击修改
        $(".b-list .button-wrap").on("click",".revise",function(){
            $(this).parents("li").find("input").addClass("border").removeAttr("disabled");
            var buttons='<a href="javascript:;" class="ctrl">保存</a>'
                +'<a href="javascript:;" class="cancels">取消</a>';
            $(this).parents(".button-wrap").html(buttons);
        });
        //点击取消或者保存
        $(".b-list .button-wrap").on("click",".ctrl",function(){
            $(this).parents("li").find("input").removeClass("border").attr("disabled","disabled");
            var live = $(this).parents("li").find("input").val();
            var id = $(this).parents("li").attr("id");
            if(live != $.cookie("live_"+id)){
            	$(this).parents("li").addClass("ud");
            }else{
            	$(this).parents("li").removeClass("ud");
            }
            var buttons='<a href="javascript:;" class="revise">修改</a>'
            $(this).parents(".button-wrap").html(buttons);
        });
        //点击取消或者保存
        $(".b-list .button-wrap").on("click",".cancels",function(){
            $(this).parents("li").find("input").removeClass("border").attr("disabled","disabled");
            var buttons='<a href="javascript:;" class="revise">修改</a>'
            $(this).parents(".button-wrap").html(buttons);
        });
    });
})(jQuery);