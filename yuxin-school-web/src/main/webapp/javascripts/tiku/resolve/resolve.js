
	$(function(){
		$(".que_left:first").addClass("current");
		/* $(".que_left:first").addClass("active"); */
		var page = 0;
		$(".que_left").each(function(){
			page += 1;
			$(this).attr("page",page);
			$(this).html(page);
		});
		//点击切换current
		$(".que_left").click(function(){
			$(".que_left").removeClass("current");
			/* $(".que_left").removeClass("active"); */
			$(this).addClass("current");
			/* $(this).addClass("active"); */
			selTopic();
		});
		$(".que_back").click(function(){
            $(".loading").show();
            $(".loading-bg").show();
			location.href = rootPath+"/tikuIndex/toTikuIndex/"+$("#cateId").val()+"/"+$("#subId").val()+"/0";
		});
		
		$(".que_quenumber_title[data-type='TOPIC_TYPE_CASE']").find("span").html($(".que_left[data-type='TOPIC_TYPE_CASE']").length);
		/*
		//点击切换active
		 $(".que_quenumber_title").click(function(){
			var types = $(this).attr("data-type");
			$(".que_left").removeClass("current");
			$(".que_left[data-type='" + types + "']").addClass("current");
			selTopic();
		});*/ 
		selTopic();
	});
	
	function selTopic(){
		$(".que_quenumber_title").removeClass("active");
		var type = $(".current").attr("data-type");
		$(".que_quenumber_title[data-type='" + type + "']").addClass("active");
		//获得当前current的试题id
		var topicId = $(".current").attr("data-id");
		var pager = $(".current").attr("page");
		if(typeof(topicId) == "undefined"){
			return false;
		}
		//获取详细信息
		$.ajax({
			url:rootPath + "/resolve/selTopic",
			type:"post",
			data:{"topicId":topicId,"userExerciseId":$("#exerId").val(),"pager":pager,"userId":$("#userId").val()},
			dataType:"html",
		 	beforeSend:function(XMLHttpRequest){
	            $(".loading").show();
	            $(".loading-bg").show();
	        },
			success:function(data){
				$(".detail").html(data);
			},
	 		complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
		        $(".loading-bg").hide();
		    }
		});
	}
	//下一题，如果是最后一题 则返回第一题
	function continueTopic(){
		//当前page
		var curPage = $(".current").attr("page");
		$(".que_left").removeClass("current");
		/* $(".que_left").removeClass("active"); */
		if(curPage == $(".que_left").length){
			$(".que_left:first").addClass("current");
			/* $(".que_left:first").addClass("active"); */
		}else{
			$(".que_left:eq(" + curPage + ")").addClass("current");
			/* $(".que_left:eq(" + curPage + ")").addClass("active"); */
		}
		selTopic();
	}