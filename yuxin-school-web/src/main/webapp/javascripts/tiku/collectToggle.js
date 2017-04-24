$(function(){
	addCollect();
	cancelCollect();
});
	function addCollect(){
		$(".que_ok").click(function(){
			var id = $(this).attr("data-id");
			$.ajax({
				url:rootPath + "/resolve/collect",
				type:"post",
				data:{"topicId":id},
				dataType:"json",
			 	beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success:function(data){
					if(data.msg == "success"){
						$('<div class="c-fa" style="margin-left: 80px;">'+ "收藏成功！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
							$(this).remove();});
						$(".que_ok").after("<a href='javascript:;' class='que_collection_cancel que_cancel' data-id='" + data.colId + "'>取消收藏</a>");
						$(".que_ok").remove();
						cancelCollect();
					}else{
						$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
							$(this).remove();});
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		});
	}
	
	function cancelCollect(){
		$(".que_cancel").click(function(){
			var id = $(this).attr("data-id");
			$.ajax({
				url:rootPath + "/resolve/cancelCollect",
				type:"post",
				data:{"collectId":id},
				dataType:"json",
			 	beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success:function(data){
					if(data.msg == "success"){
						$('<div class="c-fa" style="margin-left: 80px;">'+ "取消收藏！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
							$(this).remove();});
						$(".que_cancel").after("<a href='javascript:;' class='que_collection que_ok' data-id='" + data.topicId + "'>收藏</a>");
						$(".que_cancel").remove();
						addCollect();
					}else{
						$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
							$(this).remove();});
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		});
	}