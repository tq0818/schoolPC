(function($){
    $(function(){
        //带颜色图标
        $(".img_hover1").on("mouseenter","img.qq",function(){
            $(this).attr("src",rp+"/images/footer_icon/QQ_hover.png");
        }).on("mouseleave","img.qq",function(){
            $(this).attr("src",rp+"/images/footer_icon/QQ.png")
        }).on("mouseenter","img.wx",function(){
            $(this).attr("src",rp+"/images/footer_icon/WX_hover.png");
        }).on("mouseleave","img.wx",function(){
            $(this).attr("src",rp+"/images/footer_icon/WX.png")
        }).on("mouseenter","img.wb",function(){
            $(this).attr("src",rp+"/images/footer_icon/WB_hover.png");
        }).on("mouseleave","img.wb",function(){
            $(this).attr("src",rp+"/images/footer_icon/WB.png")
        });
        //不带颜色图标
        $(".img-hover").on("mouseenter","img.qq",function(){
            $(this).attr("src",rp+"/images/footer_icon/QQ_white.png");
        }).on("mouseleave","img.qq",function(){
            $(this).attr("src",rp+"/images/footer_icon/QQ_.png")
        }).on("mouseenter","img.wx",function(){
            $(this).attr("src",rp+"/images/footer_icon/WX_white.png");
        }).on("mouseleave","img.wx",function(){
            $(this).attr("src",rp+"/images/footer_icon/WX_.png")
        }).on("mouseenter","img.wb",function(){
            $(this).attr("src",rp+"/images/footer_icon/WB_white.png");
        }).on("mouseleave","img.wb",function(){
            $(this).attr("src",rp+"/images/footer_icon/WB_.png")
        });
        //鼠标放在微信图标二维码显示
        $(".weixin-logo").on("mouseenter",function(){
            $(".pop-ewm").show();
        }).on("mouseleave",function(){
            $(".pop-ewm").hide();
        })
        
        //页尾主题换肤切换
        var themeArr=null;
    	var headerThemeColor=$("#header-theme-color").val();
    	if("header-red" == headerThemeColor){
    		themeArr=["footer-white","footer-gray","footer-red"];
    	}else if("header-white" == headerThemeColor){
    		themeArr=["footer-cyan","footer-gray","footer-default"];
    	}else if("header-cyan" == headerThemeColor){
    		themeArr=["footer-white","footer-gray","footer-cyan"];
    	}else if("header-orange" == headerThemeColor){
    		themeArr=["footer-white","footer-gray","footer-orange"];
    	}else if("header-green" == headerThemeColor){
    		themeArr=["footer-white","footer-gray","footer-green"];
    	}else if("header-gray" == headerThemeColor){
    		themeArr=["footer-white","footer-gray","footer-default"];
    	}else if("header-blue" == headerThemeColor){
    		themeArr=["footer-white","footer-gray","footer-blue"];
    	}else{
    		themeArr=["footer-default","footer-gray","footer-white",];
    	}
        //根据主题值 选中 
    	if($(".theme-box .theme-choice").hasClass(theme)){
    		$(".theme-box ."+theme).addClass("active");
    	}else{
    		$("#footer-default").addClass("active");
    	}
        $(".theme-choice").on("click",function(){
        	$(this).addClass("active").siblings().removeClass("active");
         	var themes = themeArr[$(this).index()-1];
            $("#footer-theme").attr("href",rp+"/stylesheets/themesdiy/default-themes/footer/"+themeArr[$(this).index()-1]+".css");
            $.ajax({
            	type:"post",
            	data:{"themes":themes},
            	url:rp+"/companyHeadFootConfig/updateThemes",
            	success:function(result){
            		
            	}
            })
        })
    })
})(jQuery)
