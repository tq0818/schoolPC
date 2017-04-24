$(function(){
		selOrder(1);
		$(".btn-status").each(function(){
			$(this).click(function(){
				$(this).attr("class","btn btn-sm btn-status btn-success");
				$(this).prevAll().attr("class","btn btn-sm btn-status btn-default");
				$(this).nextAll().attr("class","btn btn-sm btn-status btn-default");
				selOrder(1);
			});
		});
	});
	function selOrder(pageNo){
		var status = $(".btn-status.btn-success").attr("data-status");
		var pageSize = $("#pageSize").val();
		$.ajax({
			url : rootPath + "/companyPayOrder/selOrder",
			type:"post",
			data:{"page":pageNo,"pageSize":pageSize,"payStatus":status},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				$(".o-list").html(data);
			},
			complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	}