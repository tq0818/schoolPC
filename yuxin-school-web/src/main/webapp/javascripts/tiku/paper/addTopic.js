	var subId;
    var types;
    var categoryId;
    var paperId;
    	$(function(){
    		$selectMenu('tiku_header');
    		$(".tiHeader .navspace li>a:eq(1)").addClass("active");
    		$(".types:eq(0)").attr("class","btn btn-mini btn-primary types");
    		selTopic();
    		types = $("#types").val();
    		if(types != ""){
    			$(".types[data-type='" + types + "']").attr("class","btn btn-mini btn-primary types");
    			$(".types[data-type='" + types + "']").prevAll().attr("class","btn btn-mini btn-default types");
    			$(".types[data-type='" + types + "']").nextAll().attr("class","btn btn-mini btn-default types");
    		}
    		
    		$(".types").click(function(){
    			$(this).removeClass("btn-default").addClass("btn-primary").siblings().removeClass("btn-primary").addClass("btn-default");
    			selTopic();
    		});
    		//审核试卷
    		$(".btn-paper").click(function(){
    			$.ajax({
    				url:rootPath + "/tikuPaper/commitAudite",
    				type:"post",
    				data:{"paperId":$("#paperId").val(),"commit":$(this).attr("data-commit")},
    				dataType:"json",
        			beforeSend:function(XMLHttpRequest){
        	            $(".loading").show();
        	            $(".loading-bg").show();
        	        },
    				success:function(data){
    					if(data.msg == "commit"){
    						$.msg("试卷已提交审核",1000,function(){
    							window.location.href = rootPath + "/tikuPaper/toTikuPaper/" + $("#cateId").val();
    						});
    					}else if(data.msg == "ok"){
    						$.msg("试卷已通过审核",1000,function(){
    							window.location.href = rootPath + "/tikuPaper/toTikuPaper/" + $("#cateId").val();
    						});
    					}else if(data.msg == "no"){
    						$.msg("试卷未通过审核",1000,function(){
    							window.location.href = rootPath + "/tikuPaper/toTikuPaper/" + $("#cateId").val();
    						});
    					}else if(data.msg == "publish"){
    						$.msg("试卷已发布",1000,function(){
    							window.location.href = rootPath + "/tikuPaper/toTikuPaper/" + $("#cateId").val();
    						});
    					}else if(data.msg == "score"){
    						$.msg("试题分数总和大于设定的试卷分数",2000);
    					}else{
    						$.msg("试卷提交审核时遇到问题",1000);
    					}
    				},
        	        complete:function(XMLHttpRequest,textStatus){
        				$(".loading").hide();
        	            $(".loading-bg").hide();
        	        }
    			});
    		});
    	});
    	function selTopic(){
    		types = $(".btn-primary.types").attr("data-type");
    		paperId = $("#paperId").val();
    		
    		$.ajax({
    			url : rootPath + "/tikuPaper/selTopic",
    			type:"post",
    			data:{"paperId":paperId,"topicType":types},
    			dataType:"html",
    			beforeSend:function(XMLHttpRequest){
    	            $(".loading").show();
    	            $(".loading-bg").show();
    	        },
    			success:function(data){
    				$(".table-list").html(data);
    				//排序
    				for ( var i = 0; i < $(".topic-iconfont").length; i++) {
    					$(".topic-iconfont:eq(" + i + ")").find(".status").html((i+1));
					}
    			},
    	        complete:function(XMLHttpRequest,textStatus){
    				$(".loading").hide();
    	            $(".loading-bg").hide();
    	        }
    		});
    	}