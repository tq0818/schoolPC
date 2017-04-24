$(function(){
	//编辑试题
	$(".clickEditTopicChildQuestion").each(function(){
		var $this = $(this);
		$this.find("a").click(function(){
			//将全局的试题Id修改为当前的试题Id
			$("#topicId").val($(this).attr("data-id"));
			$("#topicType").val($(this).attr("data-type"));
			var parents = $(this).parent().parent();
			
			if($(this).attr("data-btn") == "del"){
				var paperId = $("#paperId").val();
				var uri = "";
				var data = {};
				console.log("paperId,"+paperId);
				if(paperId != null && paperId != ""){
					uri = rootPath+"/tikuPaper/delPaperTopic";
					data.topicId=$(this).attr("data-id");
					data.paperId=$('#paperId').val();
				}else{
					uri = rootPath+"/question/delete";
					data.id = $(this).attr("data-id");
				}
			//删除
				$.ajax({
					url : uri,
					type:"post",
					data:data,
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success:function(data){
						$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(500).fadeOut(300,function(){
	        				$(this).remove();
						});
						//删除之后从页面去除
						$(parents).next().remove();
						$(parents).remove();
					},
			        complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			}else{
				if($.trim($this.next("div").html()) != ""){
					$(".caseChildTopicEdit").slideUp(500);
					$(".caseChildTopicEdit").html("");
				}else{
					$(".caseChildTopicEdit").slideUp(500);
					$(".caseChildTopicEdit").html("");
					$("#addQuestionEdit").slideUp(200).html("");
					//编辑 或 审核
					$.ajax({
						url:rootPath + "/question/edit",
						type:"post",
						data:{"id":$(this).attr("data-id"),"types":$(this).attr("data-type"),"categoryId":$("#cateId").val(),"subId":$("#subId").val(),"btn":$(this).attr("data-btn"),"paperId":$("#paperId").val()},
						dataType:"html",
						beforeSend:function(XMLHttpRequest){
				              $(".loading").show();
				              $(".loading-bg").show();
				         },
						success:function(data){
							$this.next("div").html(data);
							$this.next("div").slideDown(500);
							$(".paperhead").hide();
							$(".i-del").html("&#xe626");
							$(".btn-papeCancel").click(function(){
								$(".caseChildTopicEdit").slideUp(200);
								$(".caseChildTopicEdit").html("");
							});
							
							$(".btn-childCancel").click(function(){
								$(".caseChildTopicEdit").slideUp(200);
								$(".caseChildTopicEdit").html("");
							});
						},
						complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				        }
					});
				}
			}
		});
	});
});