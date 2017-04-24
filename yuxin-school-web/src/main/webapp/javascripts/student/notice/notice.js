$(function(){
    		$selectSubMenu('student_message'); 
    		$("#two").hide();
    		$(".btn-one:first").attr("class","btn btn-mini btn-one btn-success");
    		$(".btn-notice").each(function(){
    			$(this).click(function(){
    				$(this).attr("class","btn btn-mini btn-notice btn-success");
    				$(this).prevAll().attr("class","btn btn-mini btn-notice btn-link");
    				$(this).nextAll().attr("class","btn btn-mini btn-notice btn-link");
					selDetail(1);
    			});
    		});
    		$(".btn-one").each(function(){
    			$(this).click(function(){
    				$(this).attr("class","btn btn-mini btn-one btn-success");
    				$(this).prevAll().attr("class","btn btn-mini btn-one btn-link");
    				$(this).nextAll().attr("class","btn btn-mini btn-one btn-link");
    				getTwoItem();
    			});
    		});
    		$(".btn-method").each(function(){
    			$(this).click(function(){
    				$(this).attr("class","btn btn-mini btn-method btn-success");
    				$(this).prevAll().attr("class","btn btn-mini btn-method btn-link");
    				$(this).nextAll().attr("class","btn btn-mini btn-method btn-link");
					selDetail(1);
    			});
    		});
    		getTwoItem();
    	});
		//加载 学科小类
		function getTwoItem(){
			if($(".btn-one.btn-success").attr("data-id") > 0){
				$.ajax({
					url:rootPath + "/sysConfigItem/twoProByClass",
					type:"post",
					data:{"oneItemId":$(".btn-one.btn-success").attr("data-id")},
					dataType:"json",
					success:function(data){
						if(data.twoItem != null){
							$("#two").show();
							$("#two").html("");
							$.each(data.twoItem,function(index,item){
								$("#two").append("<a class='btn btn-mini btn-two btn-link' href='javascript:;' data-id='" + item.id + "'>" + item.itemName+ "</a>");
							});
							$(".btn-two:first").attr("class","btn btn-mini btn-two btn-success");
							$(".btn-two").each(function(){
								$(this).click(function(){
									$(this).attr("class","btn btn-mini btn-two btn-success");
									$(this).prevAll().attr("class","btn btn-mini btn-two btn-link");
									$(this).nextAll().attr("class","btn btn-mini btn-two btn-link");
									selDetail(1);
								});
							});
							selDetail(1);
						}
					}
				});
			}else{
	    		$("#two").hide();
				$("#two").html("");
				selDetail(1);
			}
		}
    	//加载详细信息
    	function selDetail(pageNo){
    		messageType = $.trim($(".btn-notice.btn-success").attr("data-type"));
    		itemOneId = $.trim($(".btn-one.btn-success").attr("data-id"));
    		itemSecondId = $.trim($(".btn-two.btn-success").attr("data-id"));
	   		messageMethod = 	$.trim($(".btn-method.btn-success").attr("data-type"));
	   		pageSize = 	$.trim($("#pageSize").val());
	   		$.ajax({
	   			url : rootPath + "/classModuleLesson/noticeDetail",
	   			type:"post",
	   			data:{"page":pageNo,"itemOneId":itemOneId,"itemSecondId":itemSecondId,"messageType":messageType,"messageMethod":messageMethod,"pageSize":pageSize},
	   			dataType:"html",
	   			beforeSend:function(XMLHttpRequest){
	   	              $(".loading").show();
	   	              $(".loading-bg").show();
	   	         },
	   			success:function(data){
	   				$(".notice-list").html(data);
	   			},
	   			complete:function(XMLHttpRequest,textStatus){
	   				$(".loading").hide();
	   	            $(".loading-bg").hide();
	   	        }
   			});
    	}