(function($){
	$(document).ready(function(){
		
	  
	    //测试信息不同了类型显示
	    $(".ceyan-choice1").on("click",function(){
	        $(".dabiao").hide();
	        $(".exam-number").hide();
	        $("#cont-change").html("分数");
	    })
	    $(".ceyan-choice2").on("click",function(){
	         $(".dabiao").show();
	        $(".exam-number").show();
	        $("#cont-change").html("正确率");
	    });
	    //绑定试卷章节切换
	   
	    //课程资料课后作业切换
	    
	    
	    // 上传按钮
	    $('.btn-upload')
	            .on('click',function(){
	                $('.class-resource').fadeIn(200);
	            });
	    $(".btn-cancel").on("click",function(){
	        $(this).parents(".layer-tips").fadeOut(200);
	    })
	    //添加视频从库中选择
	    $(".chooseVideo").on("click",function(){
	       
	        if($(".q-type-choice").hasClass("hasClick")){
	          var $titCont= $("#c-content-one").find(".hasClick").find("label").html();
	            var $w1100="选择"+$titCont+"文件";
	            $(".w1100").fadeIn(200).find("h3").html($w1100);
	            $(".add-layer-bg").fadeIn(200);
	             $("#vedio-paper").siblings().removeClass("hasClick");
	        }else{
	           
	            $(".w1100").hide();
	             $(".w800").fadeIn(200);
	        $(".add-layer-bg").fadeIn(200);
	        }
	    });
	    $(".tabs span").on("click",function(){
	         $(this).parents(".w800").removeClass("add-div-layer");
	        $(this).addClass("active").siblings().removeClass("active");
	 
	         $(".tabs-cont").find(".layer-content").eq($(this).index()).show().siblings().hide(); 
	        
	         if($(this).index()==1&&$(this).parents(".w800").hasClass("hasClick")){
	             $(".tabs-cont").find(".layer-content").eq($(this).index()).show().siblings().hide();
	              $(".add-div").show();
	              $(this).parents(".w800").addClass("add-div-layer");
	         }else{
	           $(".tabs-cont").find(".layer-content").eq($(this).index()).show().siblings().hide();
	              $(this).parents(".w800").removeClass("add-div-layer");
	         }
	       
	    });
	    
	    //新增视频
	    $(".add-video-link").on("click",function(){
	        $(this).parents(".w800").addClass("hasClick");
	        $(this).parents(".w800").addClass("add-div-layer");
	        $(".add-div").show();
	        $(".default-div").hide();
	    })
	    //取消新增
	    $(".q-selct").on("click",function(){
	         $(".add-div").hide();
	        $(".default-div").show();
	         $(this).parents(".w800").removeClass("hasClick");
	        $(this).parents(".w800").removeClass("add-div-layer");
	    })
	    //选择试卷
	    $(".chooseChapter").on("click",function(){
	        if($(".bd-exam").hasClass("hasClick")){
	            $(".w900").fadeIn(200);
	            $(".add-layer-bg").fadeIn(200);
	        }else{
	            $(".w1000").fadeIn(200);
	            $(".add-layer-bg").fadeIn(200);
	        }
	    });
	});
})(jQuery)
    
   
