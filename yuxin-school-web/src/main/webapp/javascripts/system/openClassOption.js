var captions=["公开课模板1","公开课模板2","公开课模板3","公开课模板4"];

$(function(){
	$(".sc-type").html('');
	$(".title").on("click.btn.schools",".sc-type a",function(){
		$(".sc-type a").removeClass("btn-success").addClass("btn-default");
		$(this).addClass("btn-success");
		selTemplate();
	});
	if($("#isAdmin").val() && $("#isAdmin").val()=="true"){
		$.ajax({
			url: rootPath + "/sysConfigSchool/getSchoolJson",
			type:"post",
			success: function(jsonData){
				$.each(jsonData,function(i){
					$(".sc-type").append('<a href="javascript:;" id="'+jsonData[i].id+'" class="btn btn-mini btn-default">'+jsonData[i].schoolName+'</a>&nbsp;&nbsp;');
				});
				if($("#schoolId").val()){
					$(".sc-type").find("a[id='"+$("#schoolId").val()+"']").trigger("click.btn.schools");
				}else{
					$(".sc-type").find("a").eq(0).trigger("click.btn.schools");
				}
			}
		});
	}else if($("#isSubAdmin").val() && $("#isSubAdmin").val()=="true"){
		$(".sc-type").append('<a href="javascript:;" id="'+$("#schoolId").val()+'" class="btn btn-mini btn-default">'+$("#schoolName").val()+'</a>&nbsp;&nbsp;');
		$(".sc-type").find("a").eq(0).trigger("click.btn.schools");
	}
});

function selTemplate(){
	$(".sysmodules").find(".themes-list ul").html("");
	$.ajax({
		url : rootPath + "/sysBody/selTemplate",
		type:"post",
		data:{"schoolId":$(".sc-type a.btn-success").attr("id")},
		dataType:"json",
		success:function(data){
			if(data.msg == "success"){
				/*****这是系统模板******/
				for(var i = 0 ; i < 4 ; i ++){
					var html='<li class="module sysmodule" id="'+ (i + 1) +'">'+
				    '<div class="picture">';
					if(data.id == (i + 1)){
						html+='<div class="curr">当前使用</div>';
							html+='</div>'+
							'<div class="themes-title">'+
		                    '<h2 class="h6">模板名称：' + captions[i] + '</h2><a href="' + rootPath + '/sysBody/Example/' + (i + 1) + '" target="_blank">查看示例</a>'+
		                    '</div>'+
		                    '<div class="themes-content">'+
		                    '</div>'+
		                    '<div class="themes-btns"></div>'+
		                    '</li>';
					}else{
						 html+='</div>'+
						 	'<div class="themes-title">'+
		                    '<h2 class="h6">模板名称：' + captions[i] + '</h2><a href="' + rootPath + '/sysBody/Example/' + (i + 1) + '" target="_blank">查看示例</a>'+
		                    '</div>'+
		                    '<div class="themes-content">'+
		                    '</div>'+
		                    '<div class="themes-btns">'+
		                    '<a class="left use" href="javascript:;" data-num="' + (i + 1) + '">应用</a>'+
		                    '</div>'+
		                    '</li>';
					}
					$(html).appendTo($(".sysmodules").find(".themes-list ul"));
//					$this.getPictures(data.id,'sys',function(pics){
//						$(".sysmodules").find(".themes-list ul").find("#"+data.id).find(".picture").append(pics);
//					});
					$(".sysmodules").find(".themes-list ul").find("#"+ (i + 1)).find(".themes-content").html(captions[i]);
					$(".sysmodules").find(".themes-list ul").find("#"+ (i + 1)).find(".picture").append('<img src="' + rootPath + '/images/open_class_mini_' + (i + 1) + '.png"/>');
				}
				$(".use").click(function(){
					var templateId = $(this).attr("data-num");
					$.ajax({
						url : rootPath + "/sysBody/usesTemplate",
						type:"post",
						data:{"templateId":templateId,"schoolId":$(".sc-type a.btn-success").attr("id")},
						dataType:"json",
						success:function(data){
							if(data.msg == "success"){
								selTemplate();
							}else{
								window.alert("世界上最悲剧的事莫过于你点了，却没有生效...");
							}
						}
					});
				});
			}
		}
	});
}