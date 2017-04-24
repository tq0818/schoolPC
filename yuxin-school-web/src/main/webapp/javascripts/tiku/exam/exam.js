(function($) {
	var Forms = {
		init : function() {
			Forms.loadAjaxInfo(1);
		},
		loadAjaxInfo : function(pageNo){
			$.ajax({
				url : rootPath + "/tikuExam/loadAjaxInfo",
				type : "post",
				data : {
					"page":pageNo
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					$("#info").html("").html(data);
					$(".cancleFB").click(function(){
						var status = $(this).attr("status");
						var ids = $(this).attr("ids");
						Forms.update(status,ids);
					});
					$(".FB").click(function(){
						var status = $(this).attr("status");
						var ids = $(this).attr("ids");
						$.ajax({
							url : rootPath + "/tikuExam/isFb",
							type : "post",
							data : {
								"examId":ids
							},
							success : function(data) {
								if(data){
									Forms.update(status,ids);
								}else{
									$('<div class="c-fa">'+ "当前添加的试卷数不能少于允许考试次数且试卷数不能为0" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
									return;
								}
							}
						});
					});
					
					$(".TJ").click(function(){
						var ids = $(this).attr("ids");
						location.href=rootPath+"/tikuExamPaperRelation/toStatisticsIndex/"+ids;
					});
					
					$(".delete").click(function(){
						var examId = $(this).attr("ids");
						$.ajax({
							url : rootPath + "/tikuExam/isHavePeople",
							type : "post",
							data : {
								"tikuExamId":examId
							},
							success : function(data) {
								if(data){
									alert("该考试已有学员参加，无法删除");
								}else{
									$.ajax({
										url : rootPath + "/tikuExam/isHavePaper",
										type : "post",
										data : {
											"tikuExamId":examId
										},
										success : function(data) {
											if(data){
												$confirm("提示","该考试下存在试卷信息，是否删除？",function(result){
													if(result){
														Forms.update(3,examId);
													}
												})
											}else{
												$confirm("提示","确认删除该考试吗？",function(result){
													if(result){
														Forms.update(3,examId);
													}
												})
											}
										}
									});
								}
							}
						});
					});
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		update : function(status,ids){
			$.ajax({
				url : rootPath + "/tikuExam/update",
				type : "post",
				data : {
					"status":status,
					"id":ids
				},
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data) {
					if(data == 'success'){
						if(status == 1){
							$('<div class="c-fa">'+ "取消发布成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
								Forms.loadAjaxInfo(1);
							});
						}
						if(status == 2){
							$('<div class="c-fa">'+ "发布成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
								Forms.loadAjaxInfo(1);
							});
						}
						if(status == 3){
							$('<div class="c-fa">'+ "删除成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
								Forms.loadAjaxInfo(1);
							});
						}
					}
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		}
	}
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)