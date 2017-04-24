var res;
		$(function(){
			$("body").find(".exam").addClass("active");
			getCateList();
			if($(".exampaper").hasClass("active")){
				gitCookie($(".exampaper.active").attr("data-id"));
			}else{
				$(".detail").hide();
			}
			
			if($(".showes").length > 0){
				$(".save").removeAttr("disabled");
			}else{
				$(".save").attr("disabled","disabled");
			}
			
			$("#cate").change(function(){
				$("#paperName").val("");
				getSubList($(this).val());
			});

			$("#sub").change(function(){
				$("#paperName").val("");
			});
			
			//点击试卷信息
			$(document).on("click",".exampaper",function(){
				$(".exampaper").removeClass("active");
				$(this).addClass("active");
				gitCookie($(this).attr("data-id"));
			})
			.on("click",".cancel",function(){
				//取消
				window.location.href = rootPath + "/tikuExam/updateExam/" + $("#examId").val();
			})
			.on("click",".save",function(){
				if($(this).attr("disabled") == "disabled"){
					return false;
				}
				//保存
				//添加与删除，试卷信息
				var status = $(this).attr("data-status");
				var delid = "";
				var addid = "";
				if($(".hideds").length > 0){
					for(var i = 0; i < $(".hideds").length; i++){
						if(($(".hideds").length -1) == i){
							delid += $(".hideds:eq(" + i + ")").attr("data-id");
						}else{
							delid += $(".hideds:eq(" + i + ")").attr("data-id") + ",";
						}
					}
				}

				if($(".addeds").length > 0){
					for(var i = 0; i < $(".addeds").length; i++){
						if(($(".addeds").length -1) == i){
							addid += $(".addeds:eq(" + i + ")").closest(".exampaper").attr("data-id");
						}else{
							addid += $(".addeds:eq(" + i + ")").closest(".exampaper").attr("data-id") + ",";
						}
					}
				}
				$.ajax({
					url:rootPath + "/tikuExam/savecontent",
					type:"post",
					data:{"id":$("#examId").val(),"delid":delid,"addid":addid,"status":status,"counts":$(".showes").length},
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success:function(data){
						if(data.msg == "success"){
							window.location.href = rootPath + "/tikuExam/toTikuExamIndex";
						}else{
							$.msg(data.msg);
						}
					},
			        complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			})
			.on("click",".del-paper",function(){
				$this = $(this);
				$.confirm("确定删除吗？",function(b){
					if(b){
						var paper = $this.closest(".exampaper").attr("data-id");
						if($this.attr("data-id") == ""){
							$this.closest(".clear").remove();
						}else{
							$this.addClass("hideds").removeClass("showes").closest(".clear").hide().find(".exampaper").removeClass("active");
						}

						if($(".showes").length > 0){
							$(".show").removeAttr("disabled");
						}else{
							$(".show").attr("disabled","disabled");
						}

						if(!$(".exampaper").hasClass("active")){
							$(".detail").hide();
						}
						
						for(var i=0; i< $(".used").length; i++){
							if(paper == $(".used:eq(" + i + ")").attr("data-id")){
								$this.removeClass("used");
								return false;
							}
						}
					}
				});
				return false;
			})
			.on("click",".selPaperClick",function(){
				selPaper(1);
			})
			.on("click",".btn-create-paper",function(){
				var url = rootPath + "/tikuPaper/toPaperInfo/" + $("#cate").val() + "/" + $("#sub").val() + "/0/add/exam";
				window.open(url);
			});

		});
	//解析cookie
	function detailCookie(str){
		var json = JSON.parse(str);
		//单选
		$(".radio_count").text(json.radio_count);
		$(".radio_score").text(json.radio_score);
		$(".radio_sum").text(json.radio_sum);
		//多选
		$(".multiple_count").text(json.multiple_count);
		$(".multiple_score").text(json.multiple_score);
		$(".multiple_sum").text(json.multiple_sum);
		//不定项
		$(".undefined_count").text(json.undefined_count);
		$(".undefined_score").text(json.undefined_score);
		$(".undefined_sum").text(json.undefined_sum);
		//判断题
		$(".truefalse_count").text(json.truefalse_count);
		$(".truefalse_score").text(json.truefalse_score);
		$(".truefalse_sum").text(json.truefalse_sum);
		//填空题
		$(".filling_count").text(json.filling_count);
		$(".filling_score").text(json.filling_score);
		$(".filling_sum").text(json.filling_sum);
		//简答题
		$(".answer_count").text(json.answer_count);
		$(".answer_score").text(json.answer_score);
		$(".answer_sum").text(json.answer_sum);
		//材料题
		$(".case_count").text(json.case_count);
		$(".case_score").text(json.case_score);
		$(".case_sum").text(json.case_sum);
		//汇总
		$(".sum_count").text(json.sum_count);
		$(".sum_score").text(json.sum_score);
		$(".sum_sum").text(json.sum_sum);
	}
	//查询当前试题详细,并且存入cookie
	function selPaperDetail(paperId){
		$.ajax({
			url:rootPath + "/tikuExam/selPaperDetail",
			type:"post",
			data:{"paperId":paperId},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
	            $(".loading").show();
	            $(".loading-bg").show();
	        },
			success:function(data){
				if(data.msg == "success"){
					data.sum_count = (data.radio_count + data.multiple_count
							+ data.undefined_count + data.truefalse_count
							+ data.filling_count + data.answer_count + data.case_count);
					data.sum_score = "----";
					data.sum_sum = (data.radio_sum + data.multiple_sum
							+ data.undefined_sum + data.truefalse_sum
							+ data.filling_sum + data.answer_sum + data.case_sum);
					var str = JSON.stringify(data);
					$.cookie("exam_paper_" + paperId,str);
					
					detailCookie(str);
				}else{
			        $('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
			        	$(this).remove();
			        });
				}
			},
	        complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	}
	function gitCookie(paperId){
		//获取cookie数据
		var str = $.cookie("exam_paper_" + paperId);
		if(typeof(str) != "undefined" && str != null && str != ""){
			detailCookie(str);
		}else{
			selPaperDetail(paperId);
		}
		$(".detail").show();
	}
	
	//按条件查询试卷
	function selPaper(page){
		var cateId = $("#cate").val();
		var subId = $("#sub").val();
		var name = $("#paperName").val();
		if(typeof(name) != "undefined" && name != null && name != ""){
			cateId = "";
			subId = "";
		}
		$.ajax({
			url:rootPath + "/tikuExam/selPaper",
			type:"post",
			data:{"examId":$("#examId").val(),"paperName":name,"tikuCategoryId":cateId,"tkuSubjectId":subId,"page":page,"pageSize":10},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	            $(".loading").show();
	            $(".loading-bg").show();
	        },
			success:function(data){
				$(".paper-dt").html(data);
			},
	        complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	}
	//获得题库列表
	function getCateList(){
		$.ajax({
			url:rootPath + "/tikuExam/getCateList",
			type:"post",
			data:{
				examId:$("#examId").val()
			},
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					$("#cate").empty();
					$.each(data.cate,function(index,item){
						$("#cate").append("<option value='" + item.id + "'>" + item.tikuName + "</option>");
					});
					getSubList($("#cate").val());
				}
			}
		});
	}

	//获得获得科目列表
	function getSubList(cateId){$.ajax({
			url:rootPath + "/tikuExam/getSubList",
			type:"post",
			data:{
				cateId:cateId
			},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
	            $(".loading").show();
	            $(".loading-bg").show();
	        },
			success:function(data){
				if(data.msg == "success"){
					$("#sub").empty();
					$.each(data.sub,function(index,item){
						$("#sub").append("<option value='" + item.id + "'>" + item.subjectName + "</option>");
					});
				}
			},
	        complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	}