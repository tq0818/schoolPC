var res;
	$(function(){
		$("body").find(".exam").addClass("active");
		//初始化
		if($(".twoItem.active").length == 1){
			$("#twoItem").show();
		}
		if($(".course.active").length == 1){
			$("#twoItem").show();
			$("#course").show();
		}

		if($("input[name='examMode']:checked").val() == 1){
			$(".jz").hide();
			$(".zy").show();
		}else{
			$(".jz").show();
			$(".zy").hide();
		}
		
		selOneItem();
		
		//选择范围时
		$(document).on("click",".oneItem",function(){
			$(this).addClass("active")
			.siblings().removeClass("active");
			$("#twoItem").html("<option value=''>----</option>").hide();
			$("#course").html("<option value=''>----</option>").hide();
		})
		.on("click",".twoItem",function(){
			$(this).addClass("active")
			.siblings().removeClass("active");
			$("#course").html("<option value=''>----</option>").hide();
			selTwoItem($("#oneItem").val());
			$("#twoItem").show();
		})
		.on("click",".course",function(){
			$(this).addClass("active")
			.siblings().removeClass("active");
			if($("#twoItem").val() == ""){
				selTwoItem($("#oneItem").val());
			}else{
				selCoures($("#oneItem").val(),$("#twoItem").val());
			}
			$("#twoItem").show();
			$("#course").show();
		});
		
		//下拉框改变时
		$("#oneItem").change(function(){
			selTwoItem($("#oneItem").val());
		});
		$("#twoItem").change(function(){
			selCoures($("#oneItem").val(),$("#twoItem").val());
		});
		
		//考试方式
		$("input[name='examMode']").click(function(){
			if($("input[name='examMode']:checked").val() == 1){
				$(".jz").hide();
				$(".zy").show();
			}else{
				$(".jz").show();
				$(".zy").hide();
			}
		});
		
		//集中考试时间设置
		$(document).on("click","#startTime",function(){
			WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:%m:%s'/*,maxDate:'%y-%M-%d 23:59:59'*/});
		})
		.on("click","#endTime",function(){
			WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d {%H + 1}:%m:%s'/*,maxDate:'%y-%M-%d 23:59:59'*/});
		});
		
		//保存和取消
		$(document).on("click",".saveNext",function(){

			/*CKupdate();*/
			var examName = $("#examName").val();
			var introduction = $("#introduction").val();
			var examRange = $(".exam-scope .active").attr("data-examRange");
			var oneItem = $("#oneItem").val();
			var twoItem = $("#twoItem").val();
			var course = $("#course").val();
			var cover = $("#coverUrl").attr("data-cover");
			var examType = "CERTIFICATE_EXAM_TYPE";
			var examMode = $("input[name='examMode']:checked").val();
			var beginTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var examCount = $("#examCount").val();
			var passScore = $("#passScore").val();
			var status = $(this).attr("data-status");
			var id = $("#examId").val();
			var scanAnalysis = $("input[name='scanAnalysis']:checked").val();
			var allowUserExam = $("input[name='allowUserExam']:checked").val();
			var successWord = $("#successWord").val();
			var failWord = $("#failWord").val();
			
			if(typeof(examName) == "undefined" || examName == "" || examName == null){
		        $.msg("考试名称不能为空");
		        return false;
			}
			if(typeof(introduction) == "undefined" || introduction == null || introduction == ""){
		        $.msg("考试说明不能为空");
		        return false;
			}
			if(typeof(cover) == "undefined" || cover == "" || cover == null){
		        $.msg("请上传封面");
		        return false;
			}
			var reg = /\D+/
			if(examMode == 1){
				if(typeof(examCount) == "undefined" || examCount == "" || examCount == null){
			        $.msg("考试次数不能为空");
			        return false;
				}
				if(reg.test(examCount)){
					$.msg("请正确输入次数");
			        return false;
				}
			}else{
				if(typeof(beginTime) == "undefined" || beginTime == "" || beginTime == null){
			        $.msg("开始时间不能为空");
			        return false;
				}
				if(typeof(endTime) == "undefined" || endTime == "" || endTime == null){
			        $.msg("结束时间不能为空");
			        return false;
				}
			}
			if(typeof(passScore) == "undefined" || passScore == "" || passScore == null){
		        $.msg("通过分数不能为空");
		        return false;
			}

			if(reg.test(passScore)){
		        $.msg("请正确输入分数");
		        return false;
			}
			
			if(passScore == 0){
		        $.msg("通过分数不能为0");
		        return false;
			}

			if(typeof(successWord) == "undefined" || successWord == null || successWord == ""){
				$.msg("请设置通过提示");
		        return false;
			}
			if(typeof(failWord) == "undefined" || failWord == null || failWord == ""){
				$.msg("请设置未通过提示");
		        return false;
			}
			var et = $("#etcount").val();
			if(et != 0 && et < examCount){
				$.confirm("当前考试次数大于试卷数，确定继续吗？",function(b){
					if(b){
						$.ajax({
							url: rootPath + "/tikuExam/saveAndNext",
							type:"post",
							data:{"id":id,
								"examName":examName,
								"introduction":introduction,
								"examRange":examRange,
								"itemOneId":oneItem,
								"itemSecondId":twoItem,
								"classTypeId":course,
								"cover":cover,
								"examType":examType,
								"examMode":examMode,
								"startTime":beginTime,
								"endsTime":endTime,
								"examCount":examCount,
								"passScore":passScore,
								"status":status,
								"scanAnalysis":scanAnalysis,
								"allowUserExam":allowUserExam,
								"successWord":successWord,
								"failWord":failWord
								},
							dataType:"json",
							beforeSend:function(XMLHttpRequest){
					            $(".loading").show();
					            $(".loading-bg").show();
					        },
					        success:function(data){
					        	if(data.msg == "success"){
						        	window.location.href = rootPath + "/tikuExam/examContent/" + data.types + "/" + data.id;
					        	}else{
					        		$.msg(data.msg);
					        	}
					        },
					        complete:function(XMLHttpRequest,textStatus){
								$(".loading").hide();
					            $(".loading-bg").hide();
					        }
						});
					}
				});
			}else{
				$.ajax({
					url: rootPath + "/tikuExam/saveAndNext",
					type:"post",
					data:{"id":id,
						"examName":examName,
						"introduction":introduction,
						"examRange":examRange,
						"itemOneId":oneItem,
						"itemSecondId":twoItem,
						"classTypeId":course,
						"cover":cover,
						"examType":examType,
						"examMode":examMode,
						"startTime":beginTime,
						"endsTime":endTime,
						"examCount":examCount,
						"passScore":passScore,
						"status":status,
						"scanAnalysis":scanAnalysis,
						"allowUserExam":allowUserExam,
						"successWord":successWord,
						"failWord":failWord
						},
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
			        success:function(data){
			        	if(data.msg == "success"){
				        	window.location.href = rootPath + "/tikuExam/examContent/" + data.types + "/" + data.id;
			        	}else{
			        		$.msg(data.msg);
			        	}
			        },
			        complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			}
		})
		.on("click",".cancel",function(){
			window.location.href = rootPath + "/tikuExam/toTikuExamIndex";
		});
	});
	
	//查询一级项目
	function selOneItem(){
		if(res != null){
			res.abort();
		}
		res = $.ajax({
			url : rootPath + "/tikuExam/selItem",
			type: "post",
			dateType:"json",
			success:function(data){
				if(data.msg == "success"){
					$("#oneItem").empty();
					
					$.each(data.item,function(index,item){
						$("#oneItem").append("<option value='" + item.id + "'>" + item.itemName + "</option>");
					});
					
					if(typeof($("#itemOneId").val()) != "undefined" && $("#itemOneId").val() != null && $("#itemOneId").val() != ""){
						$("#oneItem").val($("#itemOneId").val());
						$("#itemOneId").val("");
					}
					if($(".oneItem.active").length == 0){
						selTwoItem($("#oneItem").val());
					}
				}else{
					$.msg(data.msg);
				}
			}
		});
	}
	//查询二级项目
	function selTwoItem(pid){
		if(res != null){
			res.abort();
		}
		res = $.ajax({
			url : rootPath + "/tikuExam/selItem",
			type: "post",
			data:{"oneItem":pid},
			dateType:"json",
			success:function(data){
				if(data.msg == "success"){
					$("#twoItem").empty();
					$.each(data.item,function(index,item){
						$("#twoItem").append("<option value='" + item.id + "'>" + item.itemName + "</option>");
					});
					
					if(typeof($("#itemSecondId").val()) != "undefined" && $("#itemSecondId").val() != null && $("#itemSecondId").val() != ""){
						$("#twoItem").val($("#itemSecondId").val());
						$("#itemSecondId").val("");
					}
					if($(".course.active").length == 1){
						selCoures($("#oneItem").val(),$("#twoItem").val());
					}
				}else{
					$.msg(data.msg);
				}
			}
		});
	}
	//查询课程
	function selCoures(oneItem,twoItem){
		if(res != null){
			res.abort();
		}
		res = $.ajax({
			url : rootPath + "/tikuExam/selCoures",
			type: "post",
			data:{"oneItem":oneItem,"twoItem":twoItem},
			dateType:"json",
			success:function(data){
				if(data.msg == "success"){
					$("#course").empty();
					$.each(data.types,function(index,item){
						$("#course").append("<option value='" + item.id + "'>" + item.name + "</option>");
					});
					if(typeof($("#classTypeId").val()) != "undefined" && $("#classTypeId").val() != null && $("#classTypeId").val() != ""){
						$("#course").val($("#classTypeId").val());
						$("#classTypeId").val("");
					}
				}else{
					$.msg(data.msg);
				}
			}
		});
	}
	// 上传封面
	function coverImgChange(){
		$("#coverHint").html("<span style='color:red;' >正在上传</span>");
	    $.ajaxFileUpload({
	    	url:rootPath + "/companyAuthority/imgUpload;"+ window["sessionName"] + "=" + window["sessionId"],
	    	type:"post",
	    	secureuri:false,
	    	fileElementId:"coverImg",
	    	dataType: "json",
	    	success:function(data){
	    		if(data.url == "formatNotRight"){
	    			$("#coverHint").html("<span style='color:red;'>上传照片格式不正确</span>");
	    		}else if(data.url == "sizeOutOf"){
	    			$("#coverHint").html("<span style='color:red;'>照片不能大于2MB</span>");
	    		}else{
	    			if(data.url == "error"){
	    				$("#coverHint").html("<span style='color:red;'>上传失败</span>");
	    			}else{
	    				$("#coverHint").html("");
	    				$("#coverUrl").attr("data-cover",data.url).html("更改封面");
	                    $(".pic-box").html("<img src='" + $("#imgUrl").val() + "/" + data.url + "'/>");
	    			}
	    		}
	    	}
	    });
	}
	function onlyNum(){ 
		var event = arguments.callee.caller.arguments[0] || window.event; 
		if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39)) 
		if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))) 

			if(window.event){
				event.returnValue = false;
			}else{
				event.preventDefault();
			}
	}
	
	//处理CKEDITOR的值
	/*function CKupdate() {
		for (instance in CKEDITOR.instances) {
			CKEDITOR.instances[instance].updateElement();
		}
	}*/