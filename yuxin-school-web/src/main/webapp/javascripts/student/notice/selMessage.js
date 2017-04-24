
     	$(function(){
     		$selectSubMenu('student_message');
     		$(".btn-view").click(function(){
     			var msgId = $("#msgId").val();
     			var status = $(".btn-view").attr("data-type");
     			selStudent(0,5,status,msgId);
     		});
	 		$(".btn-failure").click(function(){
	 			var msgId = $("#msgId").val();
	 			var status = $(".btn-failure").attr("data-type");
	 			selStudent(0,5,status,msgId);
	 		});
	 		$(".btn-returns").click(function(){
	 			location.href = rootPath + "/student/notice";
	 		});
     	});
    	function selStudent(pageNo,pageSize,status,msgId){
    		$.ajax({
    			url:rootPath + "/classModuleLesson/selStudent",
    			type:"post",
    			data:{"page":pageNo,"pageSize":pageSize,"status":status,"msgId":msgId},
    			dataType:"html",
    			success:function(data){
    				$(".place-list").html(data);
    			}
    		});
    	}