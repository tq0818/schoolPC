/**
 * author zhang.zx
 * 编辑课程
 * 页面js封装
 */
(function($){
	var Course={
			init : function(){
				//点击菜单列表
				$("#menuList_choose").on('click','li.subentry',function(){
					var mark=$(this).attr("mark");
					window.location.href=rootPath+mark;
				});
				//点击返回
				$(".courseCancle").on('click',function(){
					window.location.href=rootPath+"/classType/showClassTypePage";
				});
				
				//缩放
				 $(".left-side").on("click",".pop",function(){
			            $(this).parent(".left-side").css({
			                width : "3%",
			                transition : "width 0.3s"
			            });
			            $(this).parent(".left-side").find("li.subentry span").css({
			                left : "12px"
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
			}
			
		}
	$(document).ready(function(){
		Course.init();
	})
	window.Course=Course;
})(jQuery)