var res = null;
$(function(){
	//编辑材料
	$(".editCase").on('click',function(){
		//编辑当前材料,所以此处将修改状态改为编辑
		$("#btn").val("edit");
		//由于是编辑的材料，所以此处改为材料
		$("#topicType").val("TOPIC_TYPE_CASE");
		if(res != null){
			res.abort();
		}
		res = $.ajax({
			url:rootPath + "/question/editCase1",
			type:"post",
			data:{"topicId":$(this).attr("data-qid")},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				$(".qution-content").find(".qution-control").eq(0).html(data);
				$(".btn-childCancel").click(function(){
					window.location.reload();
				});
				$.getScript("/javascripts/tiku/question/editCase.js");
			},
			complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	});
	
	//为材料新增子题
	$(".addChildQuestion").on("click",function(){
		$.ajax({
			url:rootPath + "/question/addCaseTopic",
			type:"post",
			data:{"topicId":$(this).attr("data-qid"), "categoryId":$("#categoryId").val(), "subId":$("#subId").val()},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				$(".caseChildTopicEdit").slideUp(200).html("");
				$("#addQuestionEdit").html(data);
				$("#addQuestionEdit").css("display","block");
				return false;
			},
			complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
		return false;
	});
	
	var parentId = $("#parentId").val();
	if(parentId && parentId > 0){
		sleChildQuestion();
	}
	
	//点击提交审核材料题
	$(".btn-audite ").click(function(){
		var method = $(this).attr("data-commit");//提交方式
		
		var topic = {"id":$("#questionId").val(),"topicType":$("#questionTypes").val() };
		var paramTopic = JSON.stringify(topic);
		
		ajaxSubmitForQuestion(paramTopic, null, null,method);
	});
	
	$(".btn-return").click(function(){
        $(".loading").show();
        $(".loading-bg").show();
		var objform = document.createElement("form");
		document.body.appendChild(objform);
		objform.action = rootPath + "/question/show";
		objform.method = "post";
		
		var cateId = document.createElement("input");
		cateId.type = "hidden";
		objform.appendChild(cateId);
		cateId.value = $("#categoryId").val();
		cateId.name = "categoryId";
		
		var subId = document.createElement("input");
		subId.type = "hidden";
		objform.appendChild(subId);
		subId.value = $("#subId").val();
		subId.name = "subId";
		
		var topicType = document.createElement("input");
		topicType.type = "hidden";
		objform.appendChild(topicType);
		topicType.value = "TOPIC_TYPE_CASE";
		topicType.name = "types";
		
		var status = document.createElement("input");
		status.type = "hidden";
		objform.appendChild(status);
		status.value = $("#status").val();
		status.name = "status";
		
		var pageNO = document.createElement("input");
		pageNO.type = "hidden";
		objform.appendChild(pageNO);
		pageNO.value = $("#pageNo").val();
		pageNO.name = "pageNo";
		
		objform.submit();
	});
});

//查询该材料下所有的子题
function sleChildQuestion(){
	$.ajax({
		url : rootPath+"/question/sleChildQuestion",
		type:"post",
		data:{"parentId":$("#parentId").val()},
		dataType:"html",
		beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $(".loading-bg").show();
        },
		success:function(data){
			$("#existQuestion").html(data);
		},
        complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
	});
}


//创建、修改题目 提交方法
function ajaxSubmitForQuestion(paramTopic, paramPoint,  paramOptions, method){
	var uri = rootPath + "/question/radioEdit";
	
	$.ajax({
		url: uri,
		type:"post",
		data:{"topicString":paramTopic,"pointString":"","options":"","method":method,"btn":"audite" },
		dataType:"json",
		beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		success:function(data){
			if(data.msg == "success"){
				//如果是材料题的话，直接把材料题的题干放到当前页面
				var obj = eval('(' + paramTopic + ')');
				var topicId = obj.id;
				if(topicId == null){
					topicId = data.topicId;
				}
				var objform = document.createElement("form");
				document.body.appendChild(objform);
				objform.action = rootPath + "/question/show";
				objform.method = "post";
				
				var cateId = document.createElement("input");
				cateId.type = "hidden";
				objform.appendChild(cateId);
				cateId.value = $("#cateId").val();
				cateId.name = "categoryId";
				
				var subId = document.createElement("input");
				subId.type = "hidden";
				objform.appendChild(subId);
				subId.value = $("#subjectId").val();
				subId.name = "subId";
				
				var topicType = document.createElement("input");
				topicType.type = "hidden";
				objform.appendChild(topicType);
				topicType.value = $("#questionTypes").val();
				topicType.name = "types";
				
				var status = document.createElement("input");
				status.type = "hidden";
				objform.appendChild(status);
				status.value = $("#status").val();
				status.name = "status";
				
				var pageNO = document.createElement("input");
				pageNO.type = "hidden";
				objform.appendChild(pageNO);
				pageNO.value = $("#pageNo").val();
				pageNO.name = "pageNo";
				
				objform.submit();
			}
		}
	});
}
