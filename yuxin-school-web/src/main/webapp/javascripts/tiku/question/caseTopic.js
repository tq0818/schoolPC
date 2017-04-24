$(function(){
		/*$(".questionedit").hide();
		$(".topic-iconfont i").hide();
		$(".topic-iconfont").mouseover(function(){
			$(this).find("i").show();
		});
		$(".topic-iconfont").mouseout(function(){
			$(this).find("i").hide();
		});*/
		
		//添加试题
		$(".redioButton").click(function(){
			var types = $(this).attr("data-type");
			//
			$("#topicType").val(types);
			$("#btn").val("create");
			$.ajax({
				url:rootPath + "/question/edit",
				type:"post",
				data:{"types":types,"categoryId":$("#categoryId").val(),"subId":$("#subId").val(),"btn":"create", "parentId":$("#parentId").val()},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(data){
					$(".questionEdit").html(data);
					$(".questionEdit").slideDown(500);
					$(".paperhead").hide();
					$(".btn-childCancel").click(function(){
						$("#addQuestionEdit").slideUp(200);
						$("#addQuestionEdit").html("");
					});
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		});
		
		
	});

