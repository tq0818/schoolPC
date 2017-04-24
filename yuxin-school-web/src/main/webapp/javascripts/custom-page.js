/**
 * Created by zhuo on 2016/3/25.
 */
(function () {
    //获取标记的被移动li的 index值
    $.num=function(){
       return $(".textarea-left.block ul li.active").index();
    }
//    $(function () {
//        $(".page-pop-title em").on("click", function () {
//            $(".add-page-pop").fadeOut(200);
//        });
//        //每一个tl-cont最后一个li里append一个content；
//        var content = '<span class="mark-wrap"><em>编辑中</em><i class="iconfont">&#xe630;</i> </span>';
//        var edit = '<span class="mark-wrap"><i class="iconfont">&#xe630;</i> </span>';
//        $(".tl-cont").each(function () {
//            $(this).find("ul li:last").append(content);
//        });
//        var pVal = $(".textarea-left.block li.active").find("p").html();
//        $(".editarea input").val(pVal);
//        //选择另一个li
//        //$(".right-side").on("click",".tl-cont li",function(){
//        //    var pVal = $(this).find("p").html();
//        //    $(this).addClass("active").append(edit);
//        //    $(this).siblings().removeClass("active").find("span").empty(content);
//        //    $(this).siblings().removeClass("active").find("span").empty(edit);
//        //    $(".editarea input").val(pVal);
//        //    //$(".editarea input").val("");
//        //});
//        //编辑
//        $(".editarea input").on("change keyup keydown keypress",function(){
//            var eaVal = $(this).val();
//            $(".textarea-left.block li.active").find("p").html(eaVal);
//        });
//        //拖拽
//        $(".sortable").each(function () {
//            $(this).sortable({
//                cancel: "p,em",
//                cursor: "crosshair"
//            });
//        })
//        //模板组选择
//        $("select.mould option").each(function () {
//            var n = $(this).index();
//            $(this).attr("value", n)
//        });
//        $("select.mould").on("change", function () {
//            var n = $(this).val();
//            $(".mould-content .textarea-left").removeClass("block");
//            $(".mould-content .textarea-left").eq(n).addClass("block");
//            var cVal = $(".textarea-left.block li.active").find("p").html();
//            $(".editarea input").val(cVal);
//            //$(".editarea input").val("");
//        });
//        $(".add-temp-box").on("mouseenter",function(){
//            $(".temp-pop").fadeIn(100);
//            $(this).removeClass("pop");
//        }).on("mouseleave",function(){
//            $(".temp-pop").fadeOut(100);
//            $(this).addClass("pop");
//        });
//        $(".add-temp-box").on("mouseenter",".temp-pop li",function(){
//            $(this).find("span").fadeIn(50);
//        }).on("mouseleave",".temp-pop li",function(){
//            $(this).find("span").fadeOut(50);
//        })
//    })
})(jQuery);