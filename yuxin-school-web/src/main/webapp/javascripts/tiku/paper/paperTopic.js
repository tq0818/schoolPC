$(function(){
    // 弹层
    $('.add-classes')
        .on('click','i.close,.btn-cancel',function(){
            $('.add-classes').fadeOut(200,function(){
                $('.add-classes-bg').fadeOut(200);
                $('.add-classes').html("");
            });
        });
		$(".questionedit").hide();
		$(".topic-iconfont i").hide();
		
		$(".topic-iconfont").mouseover(function(){
			$(this).find("i").show();
		});
		$(".topic-iconfont").mouseout(function(){
			$(this).find("i").hide();
		});
		
		$(".l-title").each(function(){
			var t = $.trim($(this).text());
			//清除全部p标签
			t = t.replace(/<p>/g,'');
			t = t.replace(/<\/p>/g,'');
			$(this).html(t);
		});
		
		//添加试题
		$(".btn-topic").click(function(){
			$(".questionedit").slideUp(500);
			$(".questionedit").html("");
			$(".paperCaseTopic").each(function(){
				$(this).addClass("none");
				$(this).find(".addQuestionEdit").html("");
				$(this).find(".existQuestion").html("");
			});
			$.ajax({
				url:rootPath + "/question/edit",
				type:"post",
				data:{"types":$(".btn-primary.types").attr("data-type"),"categoryId":$("#cateId").val(),"subId":$("#subId").val(),"btn":"create","paperId":$("#paperId").val()},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(data){
					$("#questionEdit").html(data);
					$("#questionEdit").slideDown(500);
					$(".paperhead").hide();
					$(".btn-papeCancel").click(function(){
						$("#questionEdit").slideUp(500);
						$("#questionEdit").html("");
					});
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		});
		
		//编辑试题
		$("table").each(function(){
			var parent = $(this);
			parent.find("i").click(function(){
				operatorTopic(this, parent);
			});
		});
		
		//获取分数
		$(".btn-score").click(function(){
			var paperId = $("#paperId").val();
			$.ajax({
				url : rootPath + "/tikuPaper/saveScore",
				type:"post",
				data:{"paperId":paperId,"topicType":$(".btn-primary.types").attr("data-type"),"score":$("#score").val()},
				dataType:"json",
				success:function(data){
					if(data.msgs == "success"){
						$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
	        				$(this).remove();
							});
						$("#score").attr("disabled","disabled");
						$(".btn-score").css("display","none");
						$(".btn-score-edit").css("display","");
						$(".btn-topic").css("display","");
						$(".btn-exist").css("display","");
					}
				}
			});
		});
		
		$(".btn-score-edit").click(function(){
			$("#score").removeAttr("disabled","disabled");
			$(".btn-score").css("display","");
			$(".btn-score-edit").css("display","none");
		});
		
		//试卷中编辑材料题
		$(".editPaperCase").on('click',function(){
			
			$(".questionedit").slideUp(500);
			$(".questionedit").html("");
			//编辑当前材料,所以此处将修改状态改为编辑
			$("#btn").val("edit");
			//根据当前按钮查找对应table的table
			var parent = $(this).parents("table");
			$.ajax({
				url:rootPath + "/question/editCase1",
				type:"post",
				data:{"topicId":$(this).attr("data-id"),"paperId":$(this).attr("data-paperid")},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(data){
					//$(".qution-content").find(".qution-control").eq(0).html(data);
					parent.next("div").html(data);
					parent.next("div").slideDown(500);
					$(".paperhead").hide();
					$(".btn-childCancel").click(function(){
						parent.next("div").slideUp(500);
						$(".questionedit").html("");
					});
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
			return false;
		});
		
		//为试卷材料新增子题
		$(".addChildQuestion").on("click",function(){
			var $this = $(this);
			$.ajax({
				url:rootPath + "/question/addCaseTopic",
				type:"post",
				data:{"topicId":$(this).attr("data-qid"), "categoryId":$("#cateId").val(), "subId":$("#subId").val(), "paperId":$("#paperId").val()},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(data){
					$this.parent().parent().next().find(".addQuestionEdit").html(data);
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		});
		
		//试卷中编辑材料题的子题
		$(".editPaperCaseChild").on('click',function(){
			$(".questionedit").slideUp(500);
			$(".questionedit").html("");
			//根据当前按钮查找对应table的table
			var parent = $(this).parents("table");
			var childQuestionObj = $(parent).next().next();
			var noneClass = $(childQuestionObj).hasClass("none");
			//如果当前材料题的子题已经展示出来，则隐藏掉就可以
			if(!noneClass){
				$(parent).next().next().slideUp(300);
				$(childQuestionObj).addClass("none");
				$(childQuestionObj).find(".addQuestionEdit").html("");
				return false;
			}
			//展开材料子题的时候需要关闭其他材料子题的展示
			$(".paperCaseTopic").each(function(){
				$(this).addClass("none");
				$(this).find(".addQuestionEdit").html("");
				$(this).find(".existQuestion").html("");
			});
			$.ajax({
				url : rootPath+"/question/slePaperChildQuestion",
				type:"post",
				data:{"parentId":$(this).attr("data-id"), "paperId":$("#paperId").val()},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success:function(data){
//					$("#existQuestion").html(data);
					$(childQuestionObj).removeClass("none");
					$(parent).next().next().slideDown(300).find(".existQuestion").html(data);
				},
		        complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		});
		
		$(".btn-exist").click(function(){
			$(".questionedit").slideUp(500);
			$(".questionedit").html("");
			var topicType = $("#ttyp").val();
			$('.add-classes-bg').fadeIn(200,function(){
                $('.add-classes').fadeIn(200);
            });
			$.ajax({
				url : rootPath + "/question/addExistTopic",
				type:"post",
				data:{"categoryId":$("#cateId").val(),"subId":$("#subId").val(), "topicType":topicType},
				dataType:"html",
				success:function(data){
					$(".add-classes").html(data);
				}
			});
		});

		$(".btn-closeDetail").click(function(){
			$(".loadDetail").html("");
			$(".topicDetail").hide();
			$(".add-classes").show();
		});
	});
	
	function delPaperCaseTopic(obj){
		operatorTopic(obj, $(obj).parents("table"));
	}


	//编辑/删除试卷中的题
	function operatorTopic(obj, parent){
		if($(obj).attr("data-btn") == "del"){
			//删除
			$.confirm("确定删除吗？",function(b){
				if(b){
					$.ajax({
						url : rootPath+"/tikuPaper/delPaperTopic",
						type:"post",
						data:{"paperId":$("#paperId").val(),"topicId":$(obj).attr("data-id")},
						dataType:"json",
						beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				        },
						success:function(data){
							$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(500).fadeOut(200,function(){
		        				$(obj).remove();
								});
							selTopic();
						},
				        complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				        }
					});
				}
			});
		}else{
			if($.trim(parent.next("div").html()) != ""){
				$(".questionedit").slideUp(500);
				$(".questionedit").html("");
			}else{
				$(".questionedit").slideUp(500);
				$(".questionedit").html("");
				//编辑 或 审核
				$.ajax({
					url:rootPath + "/question/edit",
					type:"post",
					data:{"id":$(obj).attr("data-id"),"types":$(".btn-primary.types").attr("data-type"),"categoryId":$("#cateId").val(),"subId":$("#subId").val(),"btn":$(obj).attr("data-btn"),"paperId":$("#paperId").val()},
					dataType:"html",
					beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			         },
					success:function(data){
						parent.next("div").html(data);
						parent.next("div").slideDown(500);
						$(".paperhead").hide();
						$(".btn-papeCancel").click(function(){
							parent.next("div").slideUp(500,function(){
								$(".questionedit").html("");
							});
						});
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			}
		}
	}

	function onlyNum() 
	{ 
		var event = arguments.callee.caller.arguments[0] || window.event; 
		if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39)) 
		if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))) 

			if(window.event){
				event.returnValue = false;
			}else{
				event.preventDefault();
			}
	}