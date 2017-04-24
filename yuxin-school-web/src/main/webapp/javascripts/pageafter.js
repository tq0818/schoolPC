/**
 * Created by zhuo on 2016/1/12.
 */
(function ($) {
    var addcontent = '<div class="title-wrap save">'
        + ' <ul class="row clear">'
        + '<li class="with16"><span class="title-info">新增二级链接</span></li>'
        + '<li class="with55 link-address"></li>'
        + '<li class="with12"></li>'
        + '<li class="with17">'
        + '</li>'
        + '</ul>'
        + '<div class="mask"></div>'
        + '<div class="field">'
        + '<div class="content">'
        + '<p><span class="name">链接名称：</span><input type="text" class="title-content"></p>'
        + '<p><span class="name">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方式：</span><input type="radio" name="linkMethod" checked="checked" value="1">自定义链接<input type="radio" name="linkMethod" value="2">默认链接-编辑文本<input type="radio" name="linkMethod" value="3">无连接</p>'
        + '<p class="radio-tab1 radio-tab"><span class="name">链接地址：</span><em>http://</em>'
        + '<span>'
        + '<input type="text" class="address" placeholder="示例:http://yunduoketang.com/course"><a href="javascript:;" class="btn-ico">...</a>'
        + '</span>'
        + '</p>'
        + '<p class="radio-tab2 radio-tab"><span class="name"></span>'+
          '<textarea name="" id="" cols="30" rows="10"></textarea>'+
          '</p>'
        + '<p class="link-type"><span class="name">链接类型：</span><input type="radio" name="openMethod">本页打开<input type="radio" name="openMethod" checked="checked">弹出新窗口</p>'
        + '<p><input type="button"  value="保存" class="btn btn-primary"><input type="button"  value="取消" class="btn btn-primary"></p>'
        + '</div>'
        + '</div>'
        + '</div>';
    var addnew = '<div class="section">'
        + '<div class="block l-menu">'
        + '<div class="title-wrap save">'
        + '<ul class="row clear">'
        + '<li class="with20"><i class="iconfont nav-name-icon"> &#xe630;</i><span class="title-info">新增底部链接</span></li>'
        + '<li class="with50 link-address"></li>'
        + '<li class="with10"></li>'
        + '<li class="with20">'
        + '</li>'
        + '</ul>'
        + ' <div class="mask"></div>'
        + '<div class="field">'
        + '<div class="content">'
        + '<p><span class="name">链接名称：</span><input type="text" class="title-content"></p>'
        + '<p><span class="name">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方式：</span><input type="radio" name="linkMethod" checked="checked" value="1">自定义链接<input type="radio" name="linkMethod" value="2">默认链接-编辑文本<input type="radio" name="linkMethod" value="3">无连接</p>'
        + '<p class="radio-tab1 radio-tab"><span class="name">链接地址：</span><em>http://</em>'
        + '<span>'
        + '<input type="text" class="address" placeholder="示例:http://yunduoketang.com/course"><a href="javascript:;" class="btn-ico">...</a>'
        + '</span>'
        + '</p>'
        + '<p class="radio-tab2 radio-tab"><span class="name"></span>'+
          '<textarea name="" id="" cols="30" rows="10"></textarea>'+
          '</p>'
        + '<p class="link-type"><span class="name">链接类型：</span><input type="radio" name="openMethod">本页打开<input type="radio" name="openMethod" checked="checked">弹出新窗口</p>'
        + '<p><input type="button"  value="保存" class="btn btn-primary"><input type="button"  value="取消" class="btn btn-primary"></p>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '<div class="block s-menu"></div>'
        + '</div>';

    function radioTab(obj){
        var  value =  $(obj).val();
        switch($(obj).val()){
            case "1":
            case "2":
                $(obj).parents(".content").find(".radio-tab"+value).siblings(".radio-tab").hide();
                $(obj).parents(".content").find(".radio-tab"+value).show();
                $(obj).parents(".content").find(".link-type").show();
                break;
            case "3":
                $(obj).parents(".content").find(".radio-tab").hide();
                $(obj).parents(".content").find(".link-type").hide();
                break;
            default:
                break;
        }
    }

    $(function () {
        $(".chooses-btn").on("click",function(){
            $(".chooses-btn").removeClass("active").html("选择");
            $(this).addClass("active").html("已选择");
        });
        // 添加属性
        $(".right-side span.title-info").each(function(){
            var attrcontent=$(this).html();
            $(this).attr("content",attrcontent);
            $(this).parents(".title-wrap").find(".title-content").val(attrcontent)

        });
        $(".right-side li.link-address").each(function(){
            var attrcontent=$(this).html();
            $(this).attr("content",attrcontent);
            $(this).parents(".title-wrap").find(".address").val(attrcontent)

        });
        //点击新增
        $(".right-side").on("click", ".new-add-btn", function () {
            var click = $(this).attr("check");
            if (click == "true") {
                return false
            } else {
                $(this).attr("check", "true");
                $(this).before(addnew);
                $(this).prev(".section").find(".field").slideDown()
            }
            $(".title-wrap").each(
                function (index) {
                    $(this).find(":radio").attr("name", "openMethod" + index);
                }
            )
        });
        // 点击新增或添加中的取消
        $(".right-side").on("click.a", ".save .btn-primary:last", function () {
            $(".new-add-btn").attr("check", "false");
            $(this).parents(".title-wrap").slideUp(
                function () {
                    $(this).remove()
                }
            )
        });
        // 点击新增或添加中的保存
        $(".right-side").on("click.b", ".save .btn-primary:first", function () {
            var title=$(this).parents(".title-wrap").find(".title-content").val();
            var address=$(this).parents(".title-wrap").find(".address").val();
            $(".new-add-btn").attr("check", "false");
            $(this).parents(".title-wrap").find(".title-info").attr("content",title);
            $(this).parents(".title-wrap").find(".link-address").attr("content",address);
            $(this).parents(".title-wrap").removeClass("save");
            $(this).parents(".title-wrap").find(".mask").hide();
            $(this).parents(".title-wrap").find(".field").slideUp()
        });
        //    点击编辑按钮
        $(".right-side").on("click", ".edit-icon", function () {
            $(this).parents(".title-wrap").find(".field").slideDown()
        });
        //点击编辑中 保存取消按钮
        $(".right-side").on("click.c",".title-wrap .btn-primary", function () {
            var value=$(this).val();
            if(value=="取消"){
                $(".mar-lr5").attr("check", "false");
                var title=$(this).parents(".title-wrap").find(".title-info").attr("content");
                var address=$(this).parents(".title-wrap").find(".link-address").attr("content");
                $(this).parents(".title-wrap").find(".title-info").html(title);
                $(this).parents(".title-wrap").find(".link-address").html(address);
                $(this).parents(".title-wrap").find(".title-content").val(title);
                $(this).parents(".title-wrap").find(".address").val(address);
                $(this).parents(".title-wrap").find(".field").slideUp()
            }else{
                $(".mar-lr5").attr("check", "false");
                var title=$(this).parents(".title-wrap").find(".title-info").html();
                var address=$(this).parents(".title-wrap").find(".link-address").html();
                $(this).parents(".title-wrap").find(".title-info").attr("content",title);
                $(this).parents(".title-wrap").find(".link-address").attr("content",address);
                $(this).parents(".title-wrap").removeClass("save");
                $(this).parents(".title-wrap").find(".field").slideUp()
            }
        });
        //点击添加
        $(".right-side").on("click", ".add-title", function () {
            var click = $(this).attr("check");
            if (click == "true") {
                return false
            } else {
                $(this).next(".slide").attr("chose", "true").html("&#xe681;");
                $(this).attr("check","true");
                $(this).parents(".l-menu").next(".s-menu").show().append(addcontent);
                $(this).parents(".l-menu").next(".s-menu").find(".save .field").slideDown()
            }
            $(".title-wrap").each(
                function (index) {
                    $(this).find(":radio").attr("name", "openMethod" + index);
                }
            )
        });
        //    输入框内容与标题同步
        $(".right-side").on("keyup keydown focus", ".title-content", function () {
            var text = $(this).val();
            $(this).parents(".title-wrap").find(".title-info").html(text)
        })
        $(".right-side").on("blur", ".title-content", function () {
            var text = $(this).val();
            if (text == "") {
                $(this).parents(".title-wrap").find(".title-info").html("自定义标题")
            }
        })
        $(".right-side").on("keyup keydown focus", ".address", function () {
            var text = $(this).val();
            $(this).parents(".title-wrap").find(".link-address").html(text)
        })
        $(".right-side").on("blur", ".address", function () {
            var text = $(this).val();
            if (text == "") {
                $(this).parents(".title-wrap").find(".link-address").html("")
            }
        });

        //  点击上拉下拉菜单
        $(".right-side .slide").each(function () {
            $(this).on("click", function () {
                var $click = $(this).attr("chose");
                if ($click == "true") {
                    $(".mar-lr5").attr("check", "false");
                    $(this).parents(".section").find(".field").slideUp();
                    $(this).parents(".l-menu").next(".s-menu").slideUp();
                    $(this).html("&#xe67f;");
                    $(this).attr("chose", "false");
                    $(this).parents(".section").find(".save").remove();
                } else {
                    $(this).parents(".l-menu").next(".s-menu").slideDown();
                    $(this).html("&#xe681;");
                    $(this).attr("chose", "true");
                }
            })
        });
        //连接方式选择
        $(".set-system").on("click","input[type='radio']",function(){
            radioTab(this);
        });


        //页尾
        $(".modify-btn").on("click",function(){
            if(!($(this).hasClass("saves-btn"))){
                var _labelHtml = $(this).parents(".line-right").find("label").html();
                $(this).css({
                    "border": "1px solid #237fd5",
                    "background-color": "#237fd5",
                    "color": "#fff"
                }).html("保存").addClass("saves-btn");
                $(this).next().css("display","inline-block");
                $(this).parents(".line-right").find("label").css("display","none");
                $(this).parents(".line-right").find("input").css("display","inline-block").val(_labelHtml);
            }else if($(this).hasClass("saves-btn")){
                var _val = $(this).parents(".line-right").find("input").val();
                $(this).css({
                    "border": "1px solid #ccc",
                    "background-color": "#fff",
                    "color": "#999"
                }).html("修改");
                $(this).next().css("display","none");
                $(this).parents(".line-right").find("input").css("display","none");
                $(this).parents(".line-right").find("label").css("display","block").html(_val);
                $(this).removeClass("saves-btn");
            }
        });
        $(".cancel-btn").on("click",function(){
            $(this).css("display","none");
            $(this).prev().css({
                "border": "1px solid #ccc",
                "background-color": "#fff",
                "color": "#999"
            }).html("修改").removeClass("saves-btn");
            $(this).parents(".line-right").find("input").css("display","none");
            $(this).parents(".line-right").find("label").css("display","block");
        });


        $(".edit-btn,.upload").on("click",function(){
            $(this).html("保存");
            $(this).next().css("display","inline-block");
        });
        $(".r-btn").on("click",function(){
            $(this).css("display","none");
            if($(this).prev().hasClass("edit-btn")){
                $(this).prev().html("编辑")
            }else{
                $(this).prev().html("上传")
            }
        });
    })
})(jQuery);