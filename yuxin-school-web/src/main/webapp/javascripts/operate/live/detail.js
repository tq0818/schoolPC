$(function(){
	$(".btn-return").click(function(){
		location.href = rootPath + "/classModule/show";
	});
	$(".btn-save-update").click(function(){
		var assistantId = $("#assistant").val();
		var updateTime = $("#updateTime").val();
		var startTime = $("#tracktime").val();
		var id = $(this).attr("data-id");

		var start = startTime.replace(/-/g,"/");
		var update = updateTime.replace(/-/g,"/");
		var sl = new Date(start).getTime();
		var ul = new Date(update).getTime();
		
		if(sl > ul){
			$.msg("开始时间不能大于结束时间",1000);
			return false;
		}
		$.ajax({
			url:rootPath + "/classModule/updateLesson",
			type:"post",
			data:{"id":id,"assistantId":assistantId,"updateTime":updateTime,"startTime":startTime},
			dataType:"text",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
	        success: function(data) {
	        	if(data == "success"){
	        		location.reload(rootPath + "/classModule/show");
	        	}else{
		           $(".loading").hide();
		           $(".loading-bg").hide();
	        	}
			}
		});
	});

	$(".return").hide();
	$(".update").show();

	$("#tracktime").click(function(){
		WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:%m:00'/*,maxDate:'%y-%M-%d 23:59:59'*/});
	});

	$("#updateTime").click(function(){
		WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d {%H + 1}:%m:00'/*,maxDate:'%y-%M-%d 23:59:59'*/});
	});
});