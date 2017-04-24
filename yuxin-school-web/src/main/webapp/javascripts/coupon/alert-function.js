
    var alertcont = function (argm1, argm2) {
        var cont = '<div class="layer-tips confirm" style="display: block;">' +
            '<div class="layer-tips-title">' + argm1 + '<i class="close iconfont confirm_close"></i></div>' +
            '<div class="layer-tips-content">' + argm2 + '</div>' +
            '<div class="layer-tips-btns">' +
            '<a href="javascript:;" class="btn btn-mini btn-success confirm_ok">确定</a>' +
            '<a href="javascript:;" class="btn btn-mini btn-default confirm_cancle">取消</a>' +
            '</div>' +
            '</div>'
        $("body").append(cont);
        $("body").on("click", ".btn-mini,.confirm_close", function () {
            $(".layer-tips.confirm").hide().remove();
            $(".layer-tips-bg").hide();
        })
    };

