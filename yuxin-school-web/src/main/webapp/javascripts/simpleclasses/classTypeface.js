/**
 * author zhang.zx
 * 页面js封装
 */
(function($){
	var Form={
			init : function(){
				$selectMenu("course_class_type");
				$(".footer").addClass("footer-fixed");
				this.changeTitle();
				
				//返回
				$(".facecancle").on('click',function(){
					var flag=$("#cType").val();
					if(flag=="video"){
						$("#myForm").attr("action",rootPath+"/simpleClasses/addClassTypeVideo").submit();
					}else{
						window.location.href=rootPath+"/simpleClasses/addliveOrface/"+$("#classtypeId").val()+"?type=update&ftype=live"
					}
				});
			},
			changeTitle : function(){
				var lable=$.cookie("ltype");
				var flag="";
				var b=lable.split(",");
				var headHtml1="";
				if(b.length==2){
					if(b[0]=="face"){
						headHtml1='<li class="step s1 active"><i>01</i><em>基本信息</em></li>'+
		                    '<li class="step s2 active"><i>02</i><em>课程详情</em></li>'+
		                    '<li class="step s3 active"><i>03</i><em>安排视频</em></li>'+
		                    '<li class="step s4 hover"><i>04</i><em>安排直播</em></li>';
					}else if(b[0]=="live"){
						flag="video";
						headHtml1='<li class="step s1 active"><i>01</i><em>基本信息</em></li>'+
	                    '<li class="step s2 active"><i>02</i><em>课程详情</em></li>'+
	                    '<li class="step s3 active"><i>03</i><em>安排视频</em></li>'+
	                    '<li class="step s4 hover"><i>04</i><em>安排面授</em></li>';
					}else if(b[0]=="video"){
						flag="live";
						headHtml1='<li class="step s1 active"><i>01</i><em>基本信息</em></li>'+
	                    '<li class="step s2 active"><i>02</i><em>课程详情</em></li>'+
	                    '<li class="step s3 active"><i>03</i><em>安排直播</em></li>'+
	                    '<li class="step s4 hover"><i>04</i><em>安排面授</em></li>';
					}
				}else{
					flag="live";
					headHtml1='<li class="step5 s1 active"><i>01</i><em>基本信息</em></li>'+
	                '<li class="step5 s2 active"><i>02</i><em>课程详情</em></li>'+
	                '<li class="step5 s3 active"><i>03</i><em>安排视频</em></li>'+
	                '<li class="step5 s4 active"><i>04</i><em>安排直播</em></li>'+
	                '<li class="step5 s5 hover"><i>05</i><em>安排面授</em></li>';
				}
				$("#cType").val(flag);
				$(".ulclear").html("").append(headHtml1);
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)