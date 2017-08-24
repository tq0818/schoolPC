var topicName; //题目
var analyseWord; //文字解析
var difficulty; //难度
var answer; // 答案
var tikuSubjectId; // 科目id
var tikuCategoryId; // 题库id
var chapterId; // 章id
var sectionId; // 节id
var pointId; // 考点id
var topicId; // 试题 id
var optionArray = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");//初始化选项数组
var optionCheck = false; // 选项 选中
var optionSpace; // 选项描述
$(function(){
	
    $('.add-topicname-ckeditor').attr("style","z-index:99");
    $('.add-subs-layer-bg').attr("style","z-index:98");
    $(document).on('click','.i-edit',function(e){
       var _this = $(this),
           parent = $('.add-topicname-ckeditor'),
           bg = $('.add-subs-layer-bg');
       bg.fadeIn(200,function(){
    	   parent.attr("data-select",_this.attr("data-select"));
           parent.fadeIn(200);
       });
       rec.setData(_this.closest("span.qution-input").find("textarea,input[type='text']").val());
    })
    .on('click',"input[name='answer']",function(){
		var right = "";
		for(var i = 0 ; i < $(".quesOption").length ; i ++){
			if($(".quesOption:eq(" + i + ")").find("input[name='answer']").prop("checked")){
				right += $(".quesOption:eq(" + i + ")").find("input[name='answer']").val();
			}
		}
		$(".right-answer").html(right);
	})
    .on('click','.btn-topicNameOk,.btn-topicNameCancel',function(){
        var _this = $(this),
            bg = $('.add-subs-layer-bg');

            bg.fadeOut(200);
    });
    
	$(".i-del").html("&#xe626");
    
    $(".btn-topicNameOk").click(function(){
    	CKupdate();
    	var s = $('.add-topicname-ckeditor').attr("data-select");
    	if(s == ""){
        	$("#quesContent").val($("#createQues").val());
        	$('.add-topicname-ckeditor').hide();
        	rec.setData("");
    	}else{
    		$("input[data-select='" + s + "']").val($("#createQues").val());
        	$('.add-topicname-ckeditor').hide();
        	rec.setData("");
    	}
    });
    
    $(".btn-topicNameCancel").click(function(){
    	$('.add-topicname-ckeditor').hide();
    	rec.setData("");
    });
    
	delOption();
	//点击添加选项
	$(".add-option").click(function(){
		var inputtype = $("input[name='answer']:eq(0)").attr("type");
		if(($("input[name='answer']").length) >= optionArray.length){
			return false;
		}
		$(".add-option-parent").before("<div class='qution-control option-div'><span class='qution-title'>选项" + optionArray[$(".quesOption").length] + "</span><span class='qution-input quesOption'><input type='text' style='margin-left: 3px;' data-select='" + optionArray[$(".quesOption").length] + "'><label class='q-i-i' style='margin-left: 3px;'><input type='" + inputtype + "' name='answer' value='" + optionArray[$(".quesOption").length] + "'/> 设为正确答案<i class='iconfont i-del' style='margin-left: 3px;'>&#xe626;</i><i class='iconfont i-edit' data-select='" + optionArray[$(".quesOption").length] + "' style='font-size:12px;margin-left:3px;'>&#xe628;</i></label></span></div>");
		delOption();
	});
	
	function delOption(){
		//删除选项
		$(".option-div").each(function(){
			var parent = $(this);
			parent.find(".i-del").click(function(){
				if($(".option-div").length <= 2){
					return false;
				}
				var id = parseInt(parent.find("input[name='answer']").attr("data-id"));
				if(!isNaN(id)){
					//同步删除数据库 选项
					$.ajax({
						url : rootPath + "/question/delOption",
						type:"post",
						data:{"id":id},
						dataType:"json",
						success:function(data){
							if(data.msg == "success"){
								parent.remove();
								for(var i = 0 ; i < $(".option-div").length ; i ++){
									$(".option-div:eq(" + [i] + ")").find(".qution-title").html("选项" + optionArray[i]);
									$(".option-div:eq(" + [i] + ")").find("input[name='answer']").val(optionArray[i]);
								}
								$(".right-answer").html($("input[name=answer]:checked").val()==null?"":$("input[name=answer]:checked").val());
								//修改选项
								var param = getOptionJson();
								$.ajax({
									url : rootPath + "/question/updateOption",
									type:"post",
									data:{"options":param},
									dataType:"json",
									success:function(data){
										
									}
								});
							}else{
								$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(3000).fadeOut(200,function(){
									$(this).remove();
								});
							}
						}
					});
				}else{
					parent.remove();
					for(var i = 0 ; i < $(".option-div").length ; i ++){
						$(".option-div:eq(" + [i] + ")").find(".qution-title").html("选项" + optionArray[i]);
						$(".option-div:eq(" + [i] + ")").find("input[name='answer']").val(optionArray[i]);
					}
					$(".right-answer").html($("input[name=answer]:checked").val()==null?"":$("input[name=answer]:checked").val());
				}
			});
			
		});
	}
	//获取题目的选项，将选项解析成为json字符串
	function getOptionJson(){
		var options = [];
		optionCheck = false;
		optionSpace = false;
		for(var i = 0 ; i < $(".quesOption").length ; i ++){
			optionId = $(".quesOption:eq("+i+")").find("input[name='answer']").attr("data-id");
			optionNo =  $(".quesOption:eq("+i+")").find("input[name='answer']").val();
			optionName = $(".quesOption:eq("+i+")").find("input:first").val();
			correctFlag = ($(".quesOption:eq("+i+")").find("input[name='answer']").prop("checked") == true ? 1 : 0);
			var option = {"id":optionId,"optionNo":optionNo,"optionName":optionName,"correctFlag":correctFlag};
			options.push(option);
			if($(".quesOption:eq("+i+")").find("input[name='answer']").prop("checked")){
				optionCheck = true;
			}
			if($(".quesOption:eq("+i+")").find("input:first").val() == ""){
				optionSpace = true;
			}
		}
		return  JSON.stringify(options);
	}
	
	//将获取的值赋值到题的对象中，将题解析成为json字符串
	function getQuestion(){
		//获得值
		var types = $("#topicType").val();//题型
		if("TOPIC_TYPE_CASE" == types){
			topicId = $("#topicCaseId").val();
			if(topicId == null || topicId == 0 || topicId == "undefined"){
				topicId = $("#questionId").val();
			}
		}else{
			topicId = $("#topicId").val();
			if(!topicId){
				topicId = $("#topicCaseId").val();
			}
		}
		
		parentId = $("#parentId").val();  //材料子题的父Id
		if("TOPIC_TYPE_CASE" == types && !parentId){
			parentId = $("#topicCaseId").val();
		}
		  //题目名称
		topicName = $("#quesContent").val();
		analyseWord = $("#newsContents").val();  	//题目解析
		difficulty = $("input[name='ndsd']:checked").val();	//题目难易程度
		tikuCategoryId = $("#categoryId").val();		//题目所属类目
		tikuSubjectId = $("#subId").val();		//题目所属科目
		answer = $("#answerContent").val();
		var topic = {"id":topicId,"topicName":topicName,"topicType":types,"answer":answer,"difficulty":difficulty,"analyseWord":analyseWord,"tikuSubjectId":tikuSubjectId,"tikuCategoryId":tikuCategoryId,"parentId":parentId };
		return JSON.stringify(topic);
	}
	
	//获取章节知识点，解析为json格式
	function getPoint(){
		chapterId = $("#chapter").val();		//题目所属章
		sectionId = $("#section").val();			//题目所属节
		pointId = $("#exampoint").val();		//题目所属知识点
		
		var point = {"chapterId":chapterId,"sectionId":sectionId,"pointId":pointId};
		return JSON.stringify(point);
	}
	
	//点击提交
	$(".btn-button").click(function(){
		var types = $("#topicType").val();//题型
		var method = $(this).attr("data-commit");//提交方式
		var commitType = $(this).attr("data-commit");
		if(!(types == "TOPIC_TYPE_CASE"  && (commitType == "okAndContinue" || commitType == "noAndContinue"))){
			CKupdate();
		}
		var isEmpty = true;
		
		if(types == "TOPIC_TYPE_RADIO"){
			//单选
			var paramOptions = getOptionJson();
			var paramTopic = getQuestion();
			var paramPoint = getPoint();

			isEmpty = notEmptyTopicName();
			if(!isEmpty){
				return false;
			}
			isEmpty = notEmptyOptionSpace();
			if(!isEmpty){
				return false;
			}
			isEmpty = notEmptyOptionCheck();
			if(!isEmpty){
				return false;
			}
			
			ajaxSubmitForQuestion(paramTopic, paramPoint,  paramOptions, method);
		}else if(types == "TOPIC_TYPE_MULTIPLE"){
			//多选
			var paramOptions = getOptionJson();
			var paramTopic = getQuestion();
			var paramPoint = getPoint();
			
			if($(".quesOption").find("input[name='answer']:checked").length < 2){
				$('<div class="c-fa">答案最少是2个 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
					$(this).remove();
				});
				return false;
			}
			
			isEmpty = notEmptyTopicName();
			if(!isEmpty){
				return false;
			}
			isEmpty = notEmptyOptionSpace();
			if(!isEmpty){
				return false;
			}
			isEmpty = notEmptyOptionCheck();
			if(!isEmpty){
				return false;
			}
			
			ajaxSubmitForQuestion(paramTopic, paramPoint,  paramOptions, method);
		}else if(types == "TOPIC_TYPE_TRUE_FALSE"){
			//判断
			var options = [];
			optionCheck = false;
			for(var i = 0 ; i < $("input[name='answer']").length ; i ++){
				optionId = $("input[name='answer']:eq(" + i + ")").attr("data-id");
				optionNo =  $("input[name='answer']:eq(" + i + ")").val();
				correctFlag = ($("input[name='answer']:eq(" + i + ")").prop("checked") == true ? 1 : 0);
				var option = {"id":optionId,"optionNo":optionNo,"correctFlag":correctFlag};
				if($("input[name='answer']:eq(" + i + ")").prop("checked")){
					optionCheck = true;
				}
				options.push(option);
			}
			var paramOptions = JSON.stringify(options);
			var paramTopic = getQuestion();
			var paramPoint = getPoint();
			
			isEmpty = notEmptyTopicName();
			if(!isEmpty){
				return false;
			}
			isEmpty = notEmptyOptionCheck();
			if(!isEmpty){
				return false;
			}
			
			ajaxSubmitForQuestion(paramTopic, paramPoint,  paramOptions, method);
		}else if(types == "TOPIC_TYPE_ANSWER"){
			//简答
			var paramTopic = getQuestion();
			var paramPoint = getPoint();

			isEmpty = notEmptyTopicName();
			if(!isEmpty){
				return false;
			}
			
			if(answer == ""){
				$('<div class="c-fa">答案不能为空 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
				});
				return false;
			}
			
			ajaxSubmitForQuestion(paramTopic, paramPoint,  paramOptions, method);
		}else if(types == "TOPIC_TYPE_FILLING"){
			//填空
			var paramTopic = getQuestion();
			var paramPoint = getPoint();
			isEmpty = notEmptyTopicName();
			if(!isEmpty){
				return false;
			}
			//题干中必须包含有答案
			var hasAnswer = notEmptyFillingAnswer();
			if(!hasAnswer){
				return false;
			}
			ajaxSubmitForQuestion(paramTopic, paramPoint, null,method);
			
		}else if(types == "TOPIC_TYPE_CASE"){
			//材料题
			var paramTopic = getQuestion();
			
			//材料不能为空
			var hasAnswer = notEmptyTopicCase();
			if(!hasAnswer){
				return false;
			}
			ajaxSubmitForQuestion(paramTopic, null, null,method);
			
		}else if(types == "TOPIC_TYPE_UNDEFINED"){
			//不定项
			var paramOptions = getOptionJson();
			var paramTopic = getQuestion();
			var paramPoint = getPoint();
			
			if($(".quesOption").find("input[name='answer']:checked").length < 1){
				$('<div class="c-fa">答案最少是1个 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
					$(this).remove();
				});
				return false;
			}
			
			isEmpty = notEmptyTopicName();
			if(!isEmpty){
				return false;
			}
			isEmpty = notEmptyOptionSpace();
			if(!isEmpty){
				return false;
			}
			isEmpty = notEmptyOptionCheck();
			if(!isEmpty){
				return false;
			}
			ajaxSubmitForQuestion(paramTopic, paramPoint,  paramOptions, method);
		}
	});
	
	//材料不能为空
	function notEmptyTopicCase(){
		if(topicName == ""){
			$('<div class="c-fa">材料不能为空 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
					$(this).remove();
			});
			return false;
		}else{
			return true;
		}
	}
	
	//题干不能为空
	function notEmptyTopicName(){
		if(topicName == ""){
			$('<div class="c-fa">题目不能为空 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
					$(this).remove();
			});
			return false;
		}else{
			return true;
		}
	}
	//选项描述不能为空
	function notEmptyOptionSpace(){
		if(optionSpace){
			$('<div class="c-fa">请填写选项描述 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return false;
		}else{
			return true;
		}
	}
	
	//选择答案不能为空
	function notEmptyOptionCheck(){
		if(optionCheck == false){
			$('<div class="c-fa">请选择正确答案 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return false;
		}else{
			return true;
		}
	}
	
	//填空的题干中必须有要包含答案
	function notEmptyFillingAnswer(){
//		topicName
		if(topicName.indexOf("[[") < 0){
			$('<div class="c-fa">题干中必须包含答案 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return false;
		}else if(topicName.indexOf("[[]]") >= 0){
			$('<div class="c-fa">题干中不能包含空答案 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return false;
		}else{
			return true;
		}
		
	}
	
	//创建、修改题目 提交方法
	function ajaxSubmitForQuestion(paramTopic, paramPoint,  paramOptions, method){
		var types = $("#topicType").val();//题型
		var uri = "";
		var paperId = $("#paperId").val();
		var topicScore = $("#score").val();
		if(types == 'TOPIC_TYPE_FILLING'){
			uri = rootPath + "/question/editFilling";
		}else{
			uri = rootPath + "/question/radioEdit";
		}
		
		$.ajax({
			url: uri,
			type:"post",
			data:{"topicString":paramTopic,"pointString":paramPoint,"options":paramOptions,"method":method,"btn":$("#btn").val(),"paperId":paperId,"topicScore":topicScore},
			dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				if(data.msg == "success"){
					$(".loading").hide();
					chul();
					//如果是材料题的话，直接把材料题的题干放到当前页面
					var obj = eval('(' + paramTopic + ')');
					var tId = obj.id;
					if(tId == null || tId == 0){
						tId = data.topicId;
						
						//添加完材料之后给questionId赋值， 添加子题的时候就有父级ID了
						$("#questionId").val(tId);
						$("#parentId").val(tId);
					}
					if("TOPIC_TYPE_CASE" == data.topicType){
						//ajax请求当前材料的内容
						$.ajax({
							url:rootPath + "/question/editCase",
							type:"post",
							data:{"topicId":tId,"cateId":$("#categoryId").val(),"paperId":paperId},
							dataType:"html",
							success:function(dataHtml){
								$.getScript("/javascripts/tiku/question/editCase.js");
								$.getScript("/javascripts/tiku/question/edit.js");
								$(".qution-content").html(dataHtml);
								$(".case-content").html("");
								$(".loading").hide();
					            $(".loading-bg").hide();
							}
						});
						
					}else{
						//返回到首页
						$(".loading").hide();
						$('<div></div>').appendTo('body').fadeIn(100).delay(200).fadeOut(200,function(){
							$(this).remove();
					        $(".loading").show();
							editSuccess();
						});
					}
				}else if(data.msg == "continue"){
					$(".loading").hide();
					chul();
					//如果是材料题，审核通过后修改一下页面上ID
					if(data.types != null && "TOPIC_TYPE_CASE" == data.types){
						$("#questionId").val(data.id);
					}
					editNewQuestion(data);
					$(".loading").hide();
		            $(".loading-bg").hide();
				}else if(data.msg == "paperSuccess"){
					$(".loading").hide();
					chul();
					if(types == 'TOPIC_TYPE_FILLING'){
						CKEDITOR.remove(CKEDITOR.instances['quesContent']); 
					}
					//刷新当前页
					selTopic();
					$(".loading").hide();
		            $(".loading-bg").hide();
				}else if(data.msg == "caseSuccess"){  //添加材料子题,添加完之后刷新页面
					$(".loading").hide();
					chul();
					if(types == 'TOPIC_TYPE_FILLING'){
						CKEDITOR.remove(CKEDITOR.instances['quesContent']); 
					}
					$("#addQuestionEdit").html("");
					//把当前材料的题型恢复
					$("#topicType").val("TOPIC_TYPE_CASE");
					$("#btn").val("edit");
					//查询当前材料题中对应的子题
					sleChildQuestion();
					$(".loading").hide();
		            $(".loading-bg").hide();
				}else if(data.msg == "casePaperSuccess"){  //添加试卷中的材料子题
					$(".loading").hide();
					chul();
					if(types == 'TOPIC_TYPE_FILLING'){
						CKEDITOR.remove(CKEDITOR.instances['quesContent']); 
					}
					var hashHtml = null;  //用于记录当前新增或者编辑的对象
					$(".addQuestionEdit").each(function(){
						if($(this).html() != null && $(this).html() != ""){
							hashHtml = $(this).prev();
						}
						$(this).html("");   //清空所有正在编辑或者新增的对象
						$(".loading").hide();
			            $(".loading-bg").hide();
					});
					if(hashHtml != null){   //刷新当前正在编辑、新增的材料题的子题
						var obj = eval('(' + paramTopic + ')');
						$.ajax({
							url : rootPath+"/question/slePaperChildQuestion",
							type:"post",
							data:{"parentId":obj.parentId, "paperId":$("#paperId").val()},
							dataType:"html",
							success:function(data){
								$(hashHtml).slideDown(300).html(data);
							},
					        complete:function(XMLHttpRequest,textStatus){
								$(".loading").hide();
					            $(".loading-bg").hide();
					        }
						});
					}
					//把当前正在编辑的试题缩放回去
					$(".caseChildTopicEdit").slideUp(200);
					$(".caseChildTopicEdit").html("");
				}else{
					$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
							$(this).remove();
					});
					$(".loading").hide();
		            $(".loading-bg").hide();
				}
			}
		});
	}
	
	//提交成功后直接返回
	function editSuccess(){
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
		topicType.value = $("#topicType").val();
		topicType.name = "types";
		
		var status = document.createElement("input");
		status.type = "hidden";
		objform.appendChild(status);
		status.value = $("#status").val();
		status.name = "status";
		
		objform.submit();
	}
	
	//提交成功后继续添加题目
	function editNewQuestion(data){
		$.ajax({
			url:rootPath + "/question/edit",
			type:"post",
			data:{"types":data.types,"id":data.id,"categoryId":data.categoryId,"subId":data.subId,"btn":data.btn},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				$("#questionEdit").html(data);
				$(".loading").hide();
	            $(".loading-bg").hide();
			}
		});
	}
	
	$(".btn-cancel").click(function(){
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
		topicType.value = $("#topicType").val();
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
function chul(){
	if($("#btn").val() == "create"){
		$('<div class="c-fa">添加完成 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
			$(this).remove();
		});
	}else if($("#btn").val() == "edit"){
		$('<div class="c-fa">编辑完成 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
			$(this).remove();
		});
	}else if($("#btn").val() == "audite"){
		$('<div class="c-fa">审核完成 </div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
			$(this).remove();
		});
	}
}
//处理CKEDITOR的值
function CKupdate() {
	for (instance in CKEDITOR.instances) {
		CKEDITOR.instances[instance].updateElement();
	}
}