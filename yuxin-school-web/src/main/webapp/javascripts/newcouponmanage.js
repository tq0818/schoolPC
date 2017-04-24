(function ($) {
	
    $(function () {
        //鼠标移入更多
        $(".coupon-page").on("mouseenter.a", ".a-more,.a-more-cont", function () {
            $(this).parent(".operate-cont").find(".a-more").addClass("active");
            $(this).parent(".operate-cont").find(".iconfont,.a-more-cont").stop().show();
        }).on("mouseleave.b", ".a-more,.a-more-cont", function () {
            $(this).parent(".operate-cont").find(".a-more").removeClass("active");
            $(this).parent(".operate-cont").find(".iconfont,.a-more-cont").stop().hide();
        });

        // 日期插件的调用
        $(".date-picker").datetimepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd hh:ii",
            autoclose: true
        });

        // 点击发放,删除,查看,详情
        $(".user-list").on("click", ".give-out", function () {
            alertcont("提示", '您创建的优惠码将以优惠券的形式，发放到<i>"优惠对象"</i> 的个人中心里，您确定发放吗？')
        }).on("click.c", ".a-delete", function () {
            alertcont("提示", '您真的要删除该优惠吗？')
        }).on("click",".a-check",function(){
            $(".layer-tips-bg,.check-coupon-code").show();
        }).on("click",".a-detail",function(){
            $(".layer-tips-bg,.coupon-code-detail").show();
        }).on("click","a.a-derive",function(){
            $(".layer-tips-bg,.add-alert-content.lead-out-code").show();
        });
        $(".operate-cont").on("click","a.a-delete,a.a-check,a.give-out,a.a-detail,.a-derive",function(){
            $(".layer-tips-bg").show();
        });


        // 查看优惠码的margin-top值
        $(".check-coupon-code").css("margin-top", '-' + $(".check-coupon-code").height() / 2 + 'px');
        // 优惠码详情的margin-top值
        $(".coupon-code-detail").css("margin-top", '-' + $(".coupon-code-detail").height() / 2 + 'px');

        // 点击关闭按钮
        $(".layer-tips-title").on("click",".confirm_close",function(){
            $(".layer-tips,.layer-tips-bg").hide()
        });
        //点击导出excel文件按钮
        $(".s_box").on("click",".right a",function(){
            $(".add-alert-content,.layer-tips-bg").hide()
        });
//  点击指定用户中的关闭按钮与取消保存按钮
        $(".s_box").on("click",".s_right.iconfont,.conserve button,.right a",function(){
            $(".add-alert-content,.layer-tips-bg").hide()
        });
//优惠券详情 上下按钮切换
        $(".coupon-use-detail th:gt(0)").click(function(){
            if($(this).find(".iconfont").eq(0).hasClass("chang_one")){
                $(this).find(".iconfont").eq(1).addClass("chang_one").siblings().removeClass("chang_one");
            }else{
                $(this).find(".iconfont").eq(0).addClass("chang_one").siblings().removeClass("chang_one");
            }
            
        });
    })
})(jQuery)