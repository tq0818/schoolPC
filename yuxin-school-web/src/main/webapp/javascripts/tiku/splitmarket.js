/**
 * Created by zhuo on 2015/12/14.
 */
(function($){
    //营销设置
    $(".qq-box").on("mouseenter",function(){
        if(!($(this).hasClass("qq-box-active"))){
            $(this).css("background-color","#eeeeee").find(".choose-btn").css("display","inline-block");
        };
    });
    $(".qq-box").on("mouseleave",function(){
        if(!($(this).hasClass("qq-box-active"))){
            $(this).css("background-color","#fff").find(".choose-btn").css("display","none");
        };
    });
    $(".choose-btn").on("click",function(){
        $(this).parents(".qq-box").addClass("qq-box-active").find(".choosed img").css("display","block");
        $(this).parents(".qq-box").css("background-color","#fff").find(".choose-btn").css("display","none");
        $(this).parents(".qbox").siblings().find(".qq-box").removeClass("qq-box-active").find(".choosed img").css("display","none");
        $(this).parents(".qbox").find(".qq-input").css("visibility","visible");
        $(this).parents(".qbox").siblings().find(".qq-input").css("visibility","hidden");
    });

    //禁用提示
    $(".right-side").on("click","em.warning-btn",function(){
        if($(this).hasClass("open")){
            $(".disabl").show();
            $(this).addClass("tagclass");
        }
    });
    $(".disabl .n").on("click",function(){
        $(".disabl").hide();
        $(".warning-btn.tagclass").removeClass("tagclass");
    });
    $(".disabl .y").on("click",function(){
        $(".disabl").hide();
        $(".tagclass").removeClass("open").addClass("close").html("&#xe604;");
        $(".tagclass").parent(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
        $(".tagclass").removeClass("tagclass");
    });

    //开关
    $(".tit-font em.normal").off().on("click",function(){
        if($(this).hasClass("open")&&(!($(this).hasClass("warning-btn")))){
            $(this).removeClass("open").addClass("close").html("&#xe604;");
            $(this).parents(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
            return false;
        }else if($(this).hasClass("close")){
            $(this).removeClass("close").addClass("open").html("&#xe603;");
            $(this).parents(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
            return false;
        };
    });

    //问答
    $(".cl-choose").on("click",function(){
        $(this).html("去设置");
        $(this).parents(".classify").addClass("active");
        $(this).parents(".classify").find("img").css("display","block");
        $(this).parents(".classify").find("div,p").css({
            "color":"#0099ff"
        });
        $(this).parents(".classify").css({
            "background-color":"#fff"
        });
        $(this).parents(".classify").siblings().removeClass("active").find("img").css("display","none");
        $(this).parents(".classify").siblings().find(".cl-choose").html("选择");
        $(this).parents(".classify").siblings().find("div,p,span").css("color","#999");
    });
    $(".classify").on("mouseenter",function(){
        if(!($(this).hasClass("active"))){
            $(this).css("background-color","#ccc").find(".classify-tit,p").css("color","#fff");
            $(this).find("span").css("color","#00aaff");
        }
        return false;
    });
    $(".classify").on("mouseleave",function(){
        if(!($(this).hasClass("active"))){
            $(this).css("background-color","#fff").find(".classify-tit,p").css("color","#999");
            $(this).find("span").css("color","#999");
        }
        return false;
    });


    //课程分类
    //删除提示
    $(".padd-box").off().on("click",".del",function(){
        if(!($(this).hasClass("cance1"))){
            $(".delet").css("display","block")
        }
    });
    $(".delet .n").off().on("click", function () {
        $(".delet").css("display","none")
    });

    //修改方法
    function revise(obj){
        var val = $(obj).html();
        $(obj).hide();
        $(obj).next().show().val(val);
        $(obj).parents(".par").find(".revise").html("保存修改").addClass("blue-btn");
        $(obj).parents(".par").find(".del").html("取消").addClass("cance1");
    }
    //点击修改
    function changeName(obj){
        var _value = $(obj).parent(".par").find("label").html();
        $(obj).html("保存修改").addClass("blue-btn");
        $(obj).parents(".par").find("label").hide();
        $(obj).parents(".par").find("input").show().val(_value);
        $(obj).next(".del").html("取消").addClass("cance1");
    }
    //保存
    function sive(obj){
        var _val = $(obj).parents(".par").find("input").val();
        $(obj).html("修改名称").removeClass("blue-btn");
        $(obj).parents(".par").find("input").hide();
        $(obj).parents(".par").find("label").show().html(_val);
        $(obj).next(".del").html("删除").removeClass("cance1");
    }
    //取消
    function cancel(obj){
        var _val = $(obj).parents(".par").find("input").val();
        $(obj).prev(".revise").html("修改名称").removeClass("blue-btn");
        $(obj).parents(".par").find("input").hide();
        $(obj).parents(".par").find("label").show();
        $(obj).html("删除").removeClass("cance1");
    }
    $(".padd-box").on("click","label",function(){
        revise(this);
        return false;
    });
    $(".padd-box").on("click",".revise",function(){
        if(!($(this).hasClass("blue-btn"))){
            changeName(this);
            return false;
        }
    });
    $(".padd-box").on("click",".blue-btn",function(){
        sive(this);
        return false;
    });
    $(".padd-box").on("click",".cance1",function(){
        cancel(this);
        return false;
    });
    //添加分类
    $(".add-btn").on("click",function(){
        $(".new-add").slideDown(50);
    });
    $(".dels").on("click",function(){
        $(".new-add").slideUp(50);
    });

    //支付页面
    $(".select").on("click",function(){
        $(this).parents(".pay-btn").find("img").show();
        $(this).parents(".pay-btn").siblings().find("img").hide();
        $(this).siblings(".shut-btn").css("display","inline-block");
        $(this).parents(".pay-btn").siblings().find(".shut-btn").css("display","none");

        if($(this).parents(".pay-btn").hasClass("a")){
            $(this).parents(".pay-btn").removeClass("a").addClass("aa");
            $(this).html("设置");
            if($(this).parents(".pay-btn").siblings().hasClass("bb")){
                $(this).parents(".pay-btn").siblings().removeClass("bb").addClass("b");
                $(this).parents(".pay-btn").siblings().find(".select").html("选择");
            }
        }else if($(this).parents(".pay-btn").hasClass("b")){
            $(this).parents(".pay-btn").removeClass("b").addClass("bb");
            $(this).html("设置");
            if($(this).parents(".pay-btn").siblings().hasClass("aa")){
                $(this).parents(".pay-btn").siblings().removeClass("aa").addClass("a");
                $(this).parents(".pay-btn").siblings().find(".select").html("选择");
            }
        }
    });
    $(".pay-btn").on("click",".shut-btn",function(){
        $(this).parents(".pay-btn").find(".select").html("选择");
        $(this).css("display","none");
        $(this).parents(".pay-btn").find("img").hide();
        if($(this).parents(".pay-btn").hasClass("aa")){
            $(this).parents(".pay-btn").removeClass("aa").addClass("a");
        }else if($(this).parents(".pay-btn").hasClass("bb")){
            $(this).parents(".pay-btn").removeClass("bb").addClass("b");
        }
    });
    $(".start").on("click",function(){
        if($(this).parent(".pay-btn").hasClass("c")){
            $(this).hide();
            $(this).next().show();
            $(this).parent(".pay-btn").removeClass("c").addClass("ccc");
        }
        return false;
    });
    $(".start").on("click",function(){
        if($(this).parent(".pay-btn").hasClass("d")){
            $(this).hide();
            $(this).next().show();
            $(this).parent(".pay-btn").removeClass("d").addClass("dd");
        }
        return false;
    });
    $(".start").on("click",function(){
        if($(this).parent(".pay-btn").hasClass("e")){
            $(this).hide();
            $(this).next().show();
            $(this).parent(".pay-btn").removeClass("e").addClass("ee");
        }
        return false;
    });
    $(".ban-btn").on("click",function(){
        if($(this).parents(".pay-btn").hasClass("ccc")){
            $(this).parent(".ban-box").hide();
            $(this).parents(".pay-btn").find(".start").show();
            $(this).parents(".pay-btn").removeClass("ccc").addClass("c");
        }
        return false;
    });
    $(".ban-btn").on("click",function(){
        if($(this).parents(".pay-btn").hasClass("dd")){
            $(this).parent(".ban-box").hide();
            $(this).parents(".pay-btn").find(".start").show();
            $(this).parents(".pay-btn").removeClass("dd").addClass("d");
        }
        return false;
    });
    $(".ban-btn").on("click",function(){
        if($(this).parents(".pay-btn").hasClass("ee")){
            $(this).parent(".ban-box").hide();
            $(this).parents(".pay-btn").find(".start").show();
            $(this).parents(".pay-btn").removeClass("ee").addClass("e");
        }
        return false;
    });

    $(".help-font").on("mouseenter",function(){
        $(this).next().fadeIn(50);
    });
    $(".help-font").on("mouseleave",function(){
        $(this).next().fadeOut(50);
    });

    //SEO设置
    $(".upload-pic-box").on("mouseenter",function(){
        $(this).find(".trans").fadeIn(50)
    });
    $(".upload-pic-box").on("mouseleave",function(){
        $(this).find(".trans").fadeOut(50)
    });

    //网站统计分析代码部署
    $(".code-select input").on("change keyup keydown keypress",function(){
        var _val = $(this).val();
        $(".chosed span.input-show").html(_val);
        $(".codesive-btn").addClass("blue-btn");
        $(".codecancel-btn").css("display","inline-block");
    });
    var _sh1 = "&lt;script&gt;<br/>"+
        "	(function(i, s, o, g, r, a, m) {<br/>"+
        "		i[\'GoogleAnalyticsObject\'] = r;<br/>"+
        "		i[r] = i[r] ||<br/>"+
        "		function() { (i[r].q = i[r].q || []).push(arguments)<br/>"+
        "		},"+
        "				i[r].l = 1 * new Date();<br/>"+
        "		a = s.createElement(o),<br/>"+
        "				m = s.getElementsByTagName(o)[0];<br/>"+
        "		a.async = 1;<br/>"+
        "		a.src = g;<br/>"+
        "		m.parentNode.insertBefore(a, m)<br/>"+
        "	})(window, document, \'script\', \'//www.google-analytics.com/analytics.js\', \'ga\');<br/>"+
        "	ga(\'create\', \'UA-<span class=\"input-show\">71745163-1</span>\', \'auto\');<br/>"+
        "	ga(\'send\', \'pageview\');<br/>"+
        "&lt;/script&gt;";
    var _sh2 = "&lt;script&gt;<br/>"+
        "var _hmt = _hmt || [];<br/>"+
        "(function() <br/>"+
        "var hm = document.createElement(\"script\");<br/>"+
        "hm.src = \""+
        "//hm.baidu.com/hm.js?<br/>"+
        "<span class=\"input-show\">f6000fc073c6b683e3d957c89bf098cd</span>\";<br/>"+
        "var s = document.getElementsByTagName(\"script\")[0];<br/>"+
        "s.parentNode.insertBefore(hm, s);<br/>"+
        "})();<br/>"+
        "&lt;/script&gt;";
    var _sh3 = "&lt;script type=\"text/javascript\" src=\"http://tajs.qq.com/stats?sId=<span class=\"input-show\">53614549</span>\" charset=\"UTF-8\"&gt;&lt;/script&gt;";
    var _sh4 = "&lt;script type=\"text/javascript\"&gt;<br/>"+
        "	var cnzz_protocol = ((\"https:\" == document.location.protocol) ? \" https://\" : \" http://\");<br/>"+
        "	document.write(unescape(\"%3Cspan id=\'cnzz_stat_icon_<span class=\"input-show\">1257041520</span>\'%3E%3C/span%3E%3Cscript src=\'\" + cnzz_protocol + \"s11.cnzz.com/z_stat.php%3Fid%3D<span class=\"input-show\">1257041520</span>\' type=\'text/javascript\'%3E%3C/script%3E\"));<br/>"+
        "&lt;/script&gt;";

    $(document).ready(function(){
        $(".sh1").append(_sh1);
    });
    $(".code-select select").change(function(){
        $(this).siblings("input").val("");
        $(".code-show").html("");
        $(".codesive-btn").removeClass("blue-btn");
        $(".codecancel-btn").css("display","none");
        var _value = $(this).find("option:checked").attr("value");


        if(_value == 1){
            $(".sh1").show().addClass("chosed").append(_sh1);
            $(".sh2,.sh3,.sh4").hide().removeClass("chosed");
            $(".input-tit").html("UA -");
        }else if(_value == 2){
            $(".sh2").show().addClass("chosed").append(_sh2);
            $(".sh1,.sh3,.sh4").hide().removeClass("chosed");
            $(".input-tit").html("hm.src =");
        }else if(_value == 3){
            $(".sh3").show().addClass("chosed").append(_sh3);
            $(".sh1,.sh2,.sh4").hide().removeClass("chosed");
            $(".input-tit").html("sId =");
        }else if(_value == 4){
            $(".sh4").show().addClass("chosed").append(_sh4);
            $(".sh1,.sh2,.sh3").hide().removeClass("chosed");
            $(".input-tit").html("id =");
        }

    });
    $(".codecancel-btn").on("click",function(){
        $(".code-select input").val("");
        $(".codesive-btn").removeClass("blue-btn");
        $(this).css("display","none");
        if($(".code-select select").find("option:checked").attr("value") == 1){
            $(".sh1").html("").append(_sh1);
        }else if($(".code-select select").find("option:checked").attr("value") == 2){
            $(".sh2").html("").append(_sh2);
        }else if($(".code-select select").find("option:checked").attr("value") == 3){
            $(".sh3").html("").append(_sh3);
        }else if($(".code-select select").find("option:checked").attr("value") == 4){
            $(".sh4").html("").append(_sh4);
        }
        return false;
    });

    var _sive1 = "";
    var _sive2 = "";
    var _sive3 = "";
    var _sive4 = "";

    $(".code-select").on("click",'div.codesive-btn',function(){
        $(".code-select input").val("");
        if($(this).hasClass("blue-btn")){
            $(this).removeClass("blue-btn");
        }
        $(".codecancel-btn").css("display","none");
        if($(".code-select select").find("option:checked").attr("value") == 1){
            _sive1 = $(".sh1").text();
            console.log(_sive1)
        }else if($(".code-select select").find("option:checked").attr("value") == 2){
            _sive2 = $(".sh2").text();
        }else if($(".code-select select").find("option:checked").attr("value") == 3){
            _sive3 = $(".sh3").text();
        }else if($(".code-select select").find("option:checked").attr("value") == 4){
            _sive4 = $(".sh4").text();
        }
        return false;
    });

    //开通服务
    $("i.btn-colse-ser").on("click",function(){
        $(this).parent(".s-name").prev(".icons").addClass("disable");
    });
})(jQuery);
