var classHeight;
$(function(){
			//初始化第一步
	$selectSubMenu('resource_classroom');
	classHeight = parseInt($(".stop-subs").height());
			$(".sc-select a:first").nextAll().attr("class","btn btn-mini btn-default");
			$.ajax({
				url:rootPath + "/sysConfigClassroom/selClassroom",
				type:"post",
				data:{"campusId":$(".sc-select a:first").attr("data-id")},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(data){
					$(".sc-lists").html("");
					$(".sc-lists").html(data);
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
			
			//点击获得校区 教室信息
			$(".sc-select a").each(function(){
				$(this).click(function(){
					$(this).attr("class","btn btn-mini btn-success");
					$(this).prevAll("a").attr("class","btn btn-mini btn-default");
					$(this).nextAll("a").attr("class","btn btn-mini btn-default");
					$.ajax({
						url:rootPath + "/sysConfigClassroom/selClassroom",
						type:"post",
						data:{"campusId":$(this).attr("data-id")},
						dataType:"html",
						beforeSend:function(XMLHttpRequest){
				              $(".loading").show();
				              $(".loading-bg").show();
				         },
						success:function(data){
							$(".sc-lists").html("");
							$(".sc-lists").html(data);
						},
						complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				        }
					});
				});
			});
			//添加教室
			$("#create").click(function(){
					$.ajax({
						url:rootPath + "/sysConfigClassroom/getDict",
						type:"post",
						dataType:"html",
						success:function(data){
							$(".add-teacher").html(data);
						}
					});
			});
			
		});