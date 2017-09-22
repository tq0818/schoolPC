     $(function(){
 		$selectSubMenu('student_message');
 		selItem();
 		$("#one").change(function(){
 	    	var messageType = $(".btn-type.btn-primary").attr("data-type");
 	    	var oneItem = $("#one").val();
 	    	selTwoItem(messageType,oneItem);
 		});
 		$("#two").change(function(){
 	    	var messageType = $(".btn-type.btn-primary").attr("data-type");
 	    	var twoItem = $("#two").val();
            selThreeItem(messageType,twoItem);
 		});
         $("#three").change(function(){
             var messageType = $(".btn-type.btn-primary").attr("data-type");
             var oneItem = $("#one option:selected").attr("data-code");
             var twoItem = $("#two option:selected").attr("data-code");
             if(messageType == "STUDENT_MESSAGE_WEIXIN"){
                 url = rootPath + "/classModule/selClassType";
                 $("#classTitle").html("课程：");
             }
             selClassOrModule(url,oneItem,twoItem,$("#three option:selected").attr("data-code"));
         });


 		$(".btn-send").click(function(){
 			var classId = $.trim($("#class").val());
			oneItemCode = $("#one option:selected").attr("data-code");
			twoItemCode = $("#two option:selected").attr("data-code");
			threeItemCode = $("#three option:selected").attr("data-code");
			stepItemCode = $("#step option:selected").attr("data-code");
			yearItemCode = $("#year option:selected").attr("data-code");
 			if(classId == ""){
				$('<div class="c-fa">'+ "您还没选择课程" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					$(this).remove();}
				);
				return false;
 			}
 			
 			$.ajax({
 				url:rootPath + "/classModule/sendWeixinMsg",
 				type:"post",
 				data:{"classId":classId,"stepItemCode":stepItemCode,"yearItemCode":yearItemCode},
 				dataType:"json",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
 				success:function(data){
 					if(data.result == "success"){
 						$(".loading").hide();
				        $('<div class="c-fa">'+ "消息已发送！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
				        	$(this).remove();
				        });
 					}else{
			            $(".loading").hide();
			            $(".loading-bg").hide();
			            if(data.result == "stuno"){
					        $('<div class="c-fa">'+ "当前没有学员！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }else if(data.result == "msgNotCount"){
					        $('<div class="c-fa">'+ "短信量不足，请购买后再发送" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }else{
					        $('<div class="c-fa">'+ "消息发送失败！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }
 					}
 				}
 			});
 		});
     });
     function selItem(){
    	 var messageType = $(".btn-type.btn-primary").attr("data-type");
    	 var oneItem = $("#one").val();
    	 selTwoItem(messageType,oneItem);
     }
     
     function selTwoItem(messageType,oneItem){
    	 $.ajax({
             url:rootPath + "/classModule/selItemRelationByPid",
    		 // url:rootPath + "/classModule/selTwoItem",
    		 type:"post",
    		 data:{"pid":oneItem},
    		 dataType:"json",
   			beforeSend:function(XMLHttpRequest){
   	              $(".loading").show();
   	              $(".loading-bg").show();
   	         },
    		 success:function(data){
    			 $("#two").empty();
    			 $.each(data.two,function(index,item){
    				 $("#two").append("<option value='" + item.id + "' data-code='"+item.itemCode+"'>" + item.itemName + "</option>");
    			 });
                 selThreeItem(messageType,$("#two").val());
    		 }
    	 });
     }

		function selThreeItem(messageType,twoItem){
			$.ajax({
				url:rootPath + "/classModule/selItemRelationByPid",
				// url:rootPath + "/classModule/selTwoItem",
				type:"post",
				data:{"pid":twoItem},
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
					$(".loading").show();
					$(".loading-bg").show();
				},
				success:function(data){
					$("#three").empty();
					$.each(data.two,function(index,item){
						$("#three").append("<option value='" + item.id + "' data-code='"+item.itemCode+"'>" + item.itemName + "</option>");
					});
					var url = "";
					if(messageType == "STUDENT_MESSAGE_WEIXIN"){
						url = rootPath + "/classModule/selClassType";
						$("#classTitle").html("课程：");
					}
                    var oneItem = $("#one option:selected").attr("data-code");
                    var twoItem = $("#two option:selected").attr("data-code");
					selClassOrModule(url,oneItem,twoItem,$("#three option:selected").attr("data-code"));
				}
			});
}
     
     function selClassOrModule(url,oneItem,twoItem,threeItem){

		 $.ajax({
			 url:url,
			 type:"post",
             data:{"itemOneCode":oneItem,"itemSecondCode":twoItem,"itemThirdCode":threeItem},
			 // data:{"itemOneId":oneItem,"itemSecondId":twoItem,"itemThridCode":threeItem},
			 dataType:"json",
	   			beforeSend:function(XMLHttpRequest){
	   	              $(".loading").show();
	   	              $(".loading-bg").show();
	   	         },
			 success:function(data){
				 $("#class").empty();
				 $.each(data.types,function(index,item){
					 $("#class").append("<option value='" + item.id + "'>" + item.name + "</option>");
				 });
				 $(".js-example-basic-single").select2();
			 },
			 complete:function(XMLHttpRequest,textStatus){

				 $(".loading").hide();
				 $(".loading-bg").hide();
			 }
		 });
     }
