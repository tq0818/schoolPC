(function ($) {
    var addcontent = '<div class="title-wrap save">'
        + ' <ul class="row clear">'
        + '<li class="with16"><span class="title-info">二级标题</span></li>'
        + '<li class="with55 link-address"></li>'
        + '<li class="with12"><i class="iconfont normal open">&#xe603;</i><span class="open">已启用</span></li>'
        + '<li class="with17">'
        + '<i class="iconfont edit-icon mar-lr5">&#xe628;</i>'
        + '<i class="iconfont mar-lr5 delete-icon">&#xe67e;</i>'
        + '</li>'
        + '</ul>'
        + '<div class="mask"></div>'
        + '<div class="field">'
        + '<div class="content">'
        + '<p><span class="name">显示名称：</span><input type="text" class="title-content"></p>'
        + '<p><span class="name">链接地址：</span><em>http://</em>'
        + '<span>'
        + '<input type="text" class="address" placeholder="示例:http://yunduoketang.com/course"><a href="javascript:;" class="btn-ico">...</a>'
        + '</span>'
        + '</p>'
        + '<p><span class="name">链接类型：</span><input type="radio" name="openMethod">本页打开<input type="radio" name="openMethod" checked="checked">弹出新窗口</p>'
        + '<p><input type="button"  value="保存" class="btn btn-primary"><input type="button"  value="取消" class="btn btn-primary"></p>'
        + '</div>'
        + '</div>'
        + '</div>'
    var addnew = '<div class="section">'
        + '<div class="block l-menu">'
        + '<div class="title-wrap save">'
        + '<ul class="row clear">'
        + '<li class="with20"><i class="iconfont nav-name-icon"> &#xe630;</i><span class="title-info">自定义标题</span></li>'
        + '<li class="with50 link-address"></li>'
        + '<li class="with10"><i class="iconfont normal open">&#xe603;</i><span class="open">已启用</span></li>'
        + '<li class="with20">'
        +' <div class="icon-wrap1">'
        + '<i class="iconfont edit-icon mar-lr5">&#xe628;</i>'
        + '<i class="iconfont mar-lr5 delete-icon">&#xe67e;</i>'
        + '<i class="iconfont mar-lr5 add-title">&#xe67d;</i>'
        + '</div>'
        + '</li>'
        + '</ul>'
        + ' <div class="mask"></div>'
        + '<div class="field">'
        + '<div class="content">'
        + '<p><span class="name">显示名称：</span><input type="text" class="title-content"></p>'
        + '<p><span class="name">链接地址：</span><em>http://</em>'
        + '<span>'
        + '<input type="text" class="address" placeholder="示例:http://yunduoketang.com/course"><a href="javascript:;" class="btn-ico">...</a>'
        + '</span>'
        + '</p>'
        + '<p><span class="name">链接类型：</span><input type="radio" name="openMethod">本页打开<input type="radio" name="openMethod" checked="checked">弹出新窗口</p>'
        + '<p><input type="button"  value="保存" class="btn btn-primary"><input type="button"  value="取消" class="btn btn-primary"></p>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>'
        + '<div class="block s-menu"></div>'
        + '</div>'
    $(function () {
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
//        $(".right-side").on("click", ".new-add-btn", function () {
//            var click = $(this).attr("check");
//            if (click == "true") {
//                return false
//            } else {
//                $(this).attr("check", "true");
//                $(this).before(addnew);
//                $(this).prev(".section").find(".field").slideDown()
//            }
//            $(".title-wrap").each(
//                function (index) {
//                    $(this).find(":radio").attr("name", "openMethod" + index);
//                }
//            )
//        });
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
        })
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
//        $(".right-side").on("click", ".add-title", function () {
//            var click = $(this).attr("check");
//            if (click == "true") {
//                return false
//            } else {
//                $(this).next(".slide").attr("chose", "true").html("&#xe681;");
//                $(this).attr("check","true");
//                $(this).parents(".l-menu").next(".s-menu").show().append(addcontent);
//                $(this).parents(".l-menu").next(".s-menu").find(".save .field").slideDown()
//            }
//            $(".title-wrap").each(
//                function (index) {
//                    $(this).find(":radio").attr("name", "openMethod" + index);
//                }
//            )
//        })
        //    输入框内容与标题同步
        $(".right-side").on("keyup keydown focus", ".title-content", function () {
            var text = $(this).val();
            $(this).parents(".title-wrap").find(".title-info").html(text)
        })
//        $(".right-side").on("blur", ".title-content", function () {
//            var text = $(this).val();
//            if (text == "") {
//                $(this).parents(".title-wrap").find(".title-info").html("自定义标题")
//            }
//        })
        $(".right-side").on("keyup keydown focus", ".address", function () {
            var text = $(this).val();
            $(this).parents(".title-wrap").find(".link-address").html(text)
        })
        $(".right-side").on("blur", ".address", function () {
            var text = $(this).val();
            if (text == "") {
                $(this).parents(".title-wrap").find(".link-address").html("")
            }
        })

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
        })
        
        
        //页头主题换肤切换
        var themeArr=["header-default","header-gray","header-white","header-red","header-cyan","header-orange","header-green","header-blue"];
        //根据主题值 选中 
    	if($(".theme-box .theme-choice").hasClass(theme)){
    		if(theme=="header-cyan"){
    			$(".theme-box .header-cyan").addClass("active");
    		}else{
    			$(".theme-box ."+theme).addClass("active");
    		}
    	}else{
    		$("#footer-default").addClass("active");
    	}
        $(".theme-choice").on("click",function(){
        	$(this).addClass("active").siblings().removeClass("active");
            $("#header-theme").attr("href",rp+"/stylesheets/themesdiy/default-themes/header/"+themeArr[$(this).index()-1]+".css");
            var themes = themeArr[$(this).index()-1];
            $.ajax({
            	type:"post",
            	data:{"content":themes},
            	url:rp+"/sysPageHeadFoot/updateThemes",
            	success:function(result){
            		
            	}
            })
        })
    })

})(jQuery)
