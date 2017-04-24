$(document).ready(function(){
	$(".loading").show();
    $(".loading-bg").show();
    $selectMenu("course_package");
	$chooseMenu("detailCode");
	$(".pic").on("change","#target", function() {
		var theImage = new Image();
		theImage.src = $(this).attr("src");
		 if (theImage.complete) {
		 	sourceHeight = theImage.height;
			sourceWidth = theImage.width;
			$.init(sourceWidth, sourceHeight);
	     }else {
	    	theImage.onload = function () {
	        	sourceHeight = theImage.height;
			sourceWidth = theImage.width;
			$.init(sourceWidth, sourceHeight);
	        };
	    }
		
	}); 
	//点击保存信息
	$(".save_detail").on('click',function(){
		CKupdate();
		var status;
		var mark=$(this).attr("mark");
		var id=$("#packageId").val();
		var desc = $("#description").val();
		var picUrl = $("#commdotityPic").attr("realPath");
		var introduce = $("#newsContents").val();
//		var introduce = $.editor.getData();
		if(desc.length>120){
			$.msg("课程包简介最多不能超过120个字符");
			return;
		}
		if(introduce.length>2000){
			$.msg("课程包详情最多不能超过2000个字符");
			return;
		}
		if("continue"==mark){
			$.ajax({
				url : rootPath+"/classPackage/getCourseNum",
				type : "post",
				data : {"id":id},
				dataType : "json",
				success : function(result) {
					if(result && result>0){
						status="CLASS_ON_SALE";
						$.ajax({
							url : rootPath+"/classPackage/updateClassPackageInfo",
							type : "post",
							data : {"id":id,"cover":picUrl,"description":desc,"publishStatus":status,"introduce":introduce},
							dataType : "json",
							success : function(result) {
								if("continue"==mark){
									$(".public_course").text("下架");
									$(".classStatus").removeClass("else1").removeClass("else");
									$("#statusText").text("售卖中");
									$(".public_course").attr("mark","nosale");
									$.msg("保存信息成功并已发布");
								}else{
									$.msg("保存信息成功");
								}
							}
						});
					}else{
						if(result && result==-1){
							$.msg("课程包中还有课程没有添加在售班号");
						}else{
							$.msg("课程包下添加课程后才可以发布");
						}
						return;
					}
				}
			});
		}else{
			$.ajax({
				url : rootPath+"/classPackage/updateClassPackageInfo",
				type : "post",
				data : {"id":id,"cover":picUrl,"description":desc,"publishStatus":status,"introduce":introduce},
				dataType : "json",
				success : function(result) {
					if("continue"==mark){
						$(".public_course").text("下架");
						$(".classStatus").removeClass("else1").removeClass("else");
						$("#statusText").text("售卖中");
						$(".public_course").attr("mark","nosale");
						$.msg("保存信息成功并已发布");
					}else{
						$.msg("保存信息成功");
					}
				}
			});
		}

		
	});
});
window.onload=function(){
	$(".loading").hide();
    $(".loading-bg").hide();
}
//上传截取后的图片
function classTypePic() {
	$.ajax({
				url : rootPath + "/classType/saveCutPic",
				data : {
					path : $("#target").attr("src"),
					x : $("#x").val(),
					y : $("#y").val(),
					w : $("#w").val(),
					h : $("#h").val(),
					itemOneid : $("#itemOneid").val()
				},
				type : "post",
				dataType : "json",
				success : function(data) {
					chooseOnePic(data.picOriginalUrl,
							data.realPath);
				}
			})
	$("#chooseDiv").css("display", "none");
	$("#stopDiv").css("display", "none");
	return;
}

 //处理CKEDITOR的值
function CKupdate() {
    for (instance in CKEDITOR.instances){
      CKEDITOR.instances[instance].updateElement();
    }
}