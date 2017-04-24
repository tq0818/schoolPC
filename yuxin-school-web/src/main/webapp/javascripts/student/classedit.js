/**
 * Created by zhuo on 2016/1/26.
 */
(function($){
    $(function(){
        $(".left-side").on("click",".pop",function(){
            $(this).parent(".left-side").css({
                width : "3%",
                transition : "width 0.3s"
            });
            $(this).parent(".left-side").find("li.subentry span").css({
                left : "32%"
            });
            //$(this).parent(".left-side").find("li.subentry .ap").addClass("mg3");
            $(this).parent(".left-side").find("li.subentry em").hide();

            $(this).parents(".u-wrap").find(".right-side").css({
                width : "96%",
                transition : "width 0.3s"
            });
            $(this).removeClass("pop").addClass("push");
        });
        $(".left-side").on("click",".push",function(){
            $(this).parents(".u-wrap").find(".right-side").css({
                width : "81%",
                transition : "width 0.3s"
            });
            $(this).parent(".left-side").css({
                width : "18%",
                transition : "width 0.3s"
            });
            $(this).parent(".left-side").find("li.subentry span").css({
                left : "68px",
                transition : "left 0.3s"
            });
            $(this).parent(".left-side").find("li.subentry em").show();
            $(this).removeClass("push").addClass("pop");
        });
        $(".pop-fixed").on("click",".use-btn",function(){
            //$(".use-btn").css({
            //    "background-color":"#fff",
            //    "color":"#666"
            //}).html("使用").removeClass("used");
        	if($(this).hasClass("used")){
        		return false;
        	}
			
        	var html = "";
        		html += "<div class='clear'><span></span>";
        		html += "<div class='exampaper' data-id='" + $(this).attr("data-id") + "' style='cursor: pointer;'>";
        		html += "<s>" + $(this).attr("data-name") + "</s>";
        		html += "<s>【来源：题库->" + $(this).attr("data-cname") + "->" + $(this).attr("data-sname") + "】</s>";
        		html += "<i class='iconfont del-paper showes addeds' data-id=''>&#xe610;</i>";
        		html += "</div></div>";
        	$(".addpaper").after(html);
        	$(".exampaper").removeClass("active");
        	$(".exampaper:first").addClass("active");
        	gitCookie($(".exampaper.active").attr("data-id"));
			if($(".showes").length > 0){
				$(".publish").removeAttr("disabled");
			}else{
				$(".publish").attr("disabled","disabled");
			}
			
            $(this).html("已使用").addClass("used");
            return false;
                //.parents("tr").find("td").css("background-color","#0af");
        });
        //$(".pop-fixed").on("click",".used",function(){
        //    $(this).css({
        //        "background-color":"#fff",
        //        "color":"#666"
        //    }).html("使用").removeClass("used");
        //});
        $(".pop-fixed").on("mouseenter",".use-btn",function(){
            if(!($(this).hasClass("used"))){
                $(this).css("background-color","#e4e4e4");
            }
            return false;
        });
        $(".pop-fixed").on("mouseleave",".use-btn",function(){
            if(!($(this).hasClass("used"))){
                $(this).css("background-color","#fff")
            }
            return false;
        });

        $(".pop-layer-btn").on("click",function(){
            $(".pop-fixed").fadeIn(200,function(){
                //按条件查询试卷
                selPaper(1);
            });
        });
        $(".pop-close-btn").on("click",function(){
            $(".pop-fixed").fadeOut(200);
        });

        //新版考试
        $(".detail").on("click","a",function(){
            $(".pop-fixed").fadeIn(200);
        });

        //学习记录
        $(".total-pop").on("click",function(){
            $(".pop-fixed").fadeIn(200);
        })
        $(".tab2").on("click",function(){
            $(".m-table-box").hide();
            $(".b-table-box").show();
            $(this).siblings().removeClass("active");
            $(this).addClass("active");
        });
        $(".tab1").on("click",function(){
            $(".b-table-box").hide();
            $(".m-table-box").show();
            $(this).siblings().removeClass("active");
            $(this).addClass("active");
        });
    })
})(jQuery);