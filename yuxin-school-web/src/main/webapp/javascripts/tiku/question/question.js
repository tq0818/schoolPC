var pageSize;
var subId;
var status;
var types;
var categoryId;
var name;
	$(function(){
		$selectMenu('tiku_header');
		$(".tiHeader .navspace li>a:eq(0)").addClass("active");
		$(".subject[data-id='" + $("#subId").val() + "']").attr("class","btn btn-sm btn-success subject");
		var page = $("#pageNO").val();
		
		//点击更换
		$(".subject").click(function(){
			$(this).attr("class","btn btn-mini btn-success subject");
			$(this).prevAll().attr("class","btn btn-mini btn-default subject");
			$(this).nextAll().attr("class","btn btn-mini btn-default subject");
			$("#topicName").val("");
			selTopic(1);
		});
		$(".questionStatus").click(function(){
			$(this).attr("class","btn btn-mini btn-success questionStatus");
			$(this).prevAll().attr("class","btn btn-mini btn-default questionStatus");
			$(this).nextAll().attr("class","btn btn-mini btn-default questionStatus");
			$("#topicName").val("");
			selTopic(1);
		});
		
		types = $("#types").val();
		if(types != ""){
			$(".types[data-type='" + types + "']").attr("class","btn btn-mini btn-primary types");
			$(".types[data-type='" + types + "']").prevAll().attr("class","btn btn-mini btn-default types");
			$(".types[data-type='" + types + "']").nextAll().attr("class","btn btn-mini btn-default types");
		}
		status = $("#status").val();
		if(status != ""){
			$(".questionStatus[data-status='" + status + "']").attr("class","btn btn-mini btn-success questionStatus");
			$(".questionStatus[data-status='" + status + "']").prevAll().attr("class","btn btn-mini btn-default questionStatus");
			$(".questionStatus[data-status='" + status + "']").nextAll().attr("class","btn btn-mini btn-default questionStatus");
		}
		$(".types").click(function(){
			$(this).attr("class","btn btn-mini btn-primary types");
			$(this).prevAll().attr("class","btn btn-mini btn-default types");
			$(this).nextAll().attr("class","btn btn-mini btn-default types");
			$("#topicName").val("");
			selTopic(1);
		});
		$(".btn-sel-no").click(function(){
			selTopic(1);
		});
		if(page != null && page > 0){
			selTopic(page);
		}else{
			selTopic(1);
		}
	});
	//分页查询
	function selTopic(pageNo){
		pageSize = $("#pageSize").val();
		subId = $(".btn-success.subject").attr("data-id");
		status = $(".btn-success.questionStatus").attr("data-status");
		types = $(".btn-primary.types").attr("data-type");
		categoryId = $("#categoryId").val();
		name = $("#topicName").val();
		
		$.ajax({
			url:rootPath + "/question/selTopic",
			type:"post",
			data:{"page":pageNo,"pageSize":pageSize,"tikuSubjectId":subId,"tikuCategoryId":categoryId,"status":status,"topicType":types,"topicName":name},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	            $(".loading").show();
	            $(".loading-bg").show();
	        },
			success:function(data){
				$(".table-list").html(data);
			},
	        complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	}