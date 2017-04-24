(function($) {
	var Forms = {
		init : function() {
			var examId = $("#examId").val();
			Forms.loadAjaxInfo(1,examId);
			$("#searchInfo").click(function(){
				var paperId = $("#paperName").val();
				var status = $("#passStatus").val();
				var mobile = $("#mobile").val();
				var examId = $("#examId").val();
				var start = $("#start").val();
				var end = $("#end").val();
				Forms.loadDetailAjaxInfo(1, paperId, status, mobile,examId,start,end);
			});
			// 导出用户
            $("#exportexcle").on( 'click',  function () {
            	var paperId = $("#paperName").val();
				var status = $("#passStatus").val();
				var mobile = $("#mobile").val();
				var examId = $("#examId").val();
				var start = $("#start").val();
				var end = $("#end").val();
                if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                	$('<div class="c-fa">'+ "暂无数据导出" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
                	return;
                } else {
                Forms.exportInfo(1, paperId, status, mobile,examId,start,end);
                }
           });
		},
		loadAjaxInfo : function(pageNo,tikuExamId){
			$.ajax({
				url : rootPath + "/tikuExamPaperRelation/loadAjaxInfo",
				type : "post",
				data : {
					"tikuExamId" : tikuExamId,
					"page":pageNo
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					$("#info").html("").html(data);
					 $(".detail").on("click",function(){
						 	var examId = $("#examId").val();
						 	var paperId = $(this).attr("paperId");
						 	$("#paperName option").each(function(i,item){
						 		var val  = $(item).val();
						 		if(paperId == val){
						 			$(item).attr("selected","selected");
						 		}
						 	});
						 	$("#passStatus option:eq(0)").attr("selected","selected");
						 	Forms.loadDetailAjaxInfo(1,paperId,null,null,examId);
				            $(".pop-fixed").fadeIn(200);
				       })
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		loadDetailAjaxInfo : function(pageNo,paperId,status,mobile,examId,start,end){
			var param = "";
			param+="&page="+pageNo;
			if(examId){
				param+="&tikuExamId="+examId;
			}
			if(start){
				param+="&startTime="+start;
			}
			if(end){
				param+="&endTime="+end;
			}
			if(mobile){
				param+="&userMobile="+mobile;
				$("#paperName option:eq(0)").attr("selected","selected");
				$("#passStatus option:eq(0)").attr("selected","selected");
			}else{
				if(paperId){
					if(paperId!=0){
						param+="&tikuPaperId="+paperId;
					}
				}
				if(status){
					if(status!=0){
						param+="&status="+status;
					}
				}
			}
			$.ajax({
				url : rootPath + "/tikuExamUserRelation/loadDetailAjaxInfo",
				type : "post",
				data :param,
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					$("#detailList").html("").html(data);
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		exportInfo : function(pageNo,paperId,status,mobile,examId,start,end){
			$("#page").val(pageNo);
			if(examId){
				$("#tikuExamId").val(examId);
			}
			if(start){
				$("#startTime").val(start);
			}
			if(end){
				$("#endTime").val(end);
			}
			if(mobile){
				$("#userMobile").val(mobile);
			}else{
				if(paperId){
					if(paperId!=0){
						$("#tikuPaperId").val(paperId);
					}
				}
				if(status){
					if(status!=0){
						$("#status").val(status);
					}
				}
			}
			$("#exportForm").attr("action",rootPath + "/tikuExamUserRelation/exportExcle").submit();
		}
	}
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)