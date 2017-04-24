;(function($){
	var readTopicId = 0;
	$(document).ready(function(){
		init();
	});
	function init(){
		console.log(location.href);
		$selectSubMenu('home_work');
		getHwPaperTopic($(".jiandaTopic>li:first").attr("tid"));
		$(".paperStantus ul.jiandaTopic>li[tid]").on("click",function(e){
			var $this = $(this);
			getHwPaperTopic($this.attr("tid"));
		});
		$(".submitRead").on("click",submitRead);
		$("#complete").on("click",function(e){
			submitRead(e,completeRead);
		});
		
		$(".returnButton").on("click",function(e){
			var $this = $(this);
			var complete = $("#complete");
			if(complete.length==0 || complete.attr("complete")==1){
				history.back();
			}else{
				$.confirm("您未完成批改，是否确定离开？",function(r){
					if(r){
						history.back()
						//window.location.href=rootPath+"/homeworkStudentComplete/gotoAttachmentStudentPaperHomeworkPage?studentCompleteId="+$this.attr("hscId")+"&hwId="+$this.attr("hwId");
					}
				});
			}
		});
	}
	
	function submitRead(e,callback){
		var score = $("#score").val();
		var content = $("#content").val();
		var data = {
				homeworkId:$("#data").attr("homeworkId"),
				homeworkSId:$("#data").attr("sid"),
				homeworkTid:$("#data").attr("tid"),
				topicId:readTopicId,
				score:score,
				content:content,
				exerciseId:$("#data").attr("exerciseid")
		}
		$.ajax({
            url: rootPath + "/homeworkTeacherRead/savePaperTopicScore",
            data: data,
            type: 'post',
            beforeSend: function (XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
            complete:function(){
            	$(".loading").hide();
                $(".loading-bg").hide();
            },
            success: function (data) {
            	if(data.ec==0){
            		var next = $(".jiandaTopic>li[tid="+readTopicId+"]").addClass("active").next();
            		if(next.length>0){
            			getHwPaperTopic(next.attr("tid"));
            		}else{
            			//没有可以批改的题了
            		}
            		
            		//针对最后一道题时提交保存
            		if(callback){
            			callback(data);
            		}
            	}else{
            		$.msg(data.msg);
            	}
            }
		});
	}
	function completeRead(e){
		if(!isCompleteRead()){
			$.confirm("您尚未完成批改，是否结束批改？",function(r){
				if(r){//结束修改
					inputConfirm();
				}
			});
		}else{
			inputConfirm();
		}
		
		function inputConfirm(){
			$.confirm("保存后不可以在修改，确认保存？",function(r){
				if(r){//确认保存
					$.confirm({
			        	title:'批改试卷',
			        	text:'<div class="ConfirmDig clear"> <h4>作业评语 :</h4><p> <textarea name="" id="teacherAdvice"></textarea> <span>说明：作业评分为学生考试最终分数</span></p></div>',
			        	fadeOutBefor:"no",
			        	callback : function(r){
			    			if(r){//输入评语
			    				var data = $("#teacherAdvice").val();
			    				if(isEmpty(data)){
			    					$.msg("请输入评语");
			    					return false;
			    				}
			    				main();	
			    			}
			    			$(".Confirm_Cancle").trigger("click");
			            }	
					});
				}
			});
		}
		
		function main(){
			var data = {
					id:$("#data").attr("tid"),
					content:$("#teacherAdvice").val()
			};
			$.ajax({
	            url: rootPath + "/homeworkTeacherRead/completeRead",
	            data: data,
	            type: 'post',
	            beforeSend: function (XMLHttpRequest) {
	                $(".loading").show();
	                $(".loading-bg").show();
	            },
	            complete:function(){
	            	$(".loading").hide();
	                $(".loading-bg").hide();
	            },
	            success: function (data) {
	            	if(data.ec==0){
	            		$("#complete").attr("complete",1);
	            		$(".returnButton").trigger("click");
	            	}else{
	            		$.msg(data.msg);
	            	}
	            }
			});
		}
	}
	function getHwPaperTopic(topicId){
		if(isEmpty(topicId)){
			//没有id时直接返回, 不调后台
			return ;
		}
		var data={
				topicId:topicId,
				hwId:$("#data").attr("homeworkid"),
				exerciseId:$("#data").attr("exerciseId")
		}
		$.ajax({
            url: rootPath + "/homeworkTeacherRead/findHwPaperTopic",
            data: data,
            type: 'post',
            beforeSend: function (XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
            complete:function(){
            	$(".loading").hide();
                $(".loading-bg").hide();
            },
            success: function (data) {
            	//当前正在批改的题目
            	readTopicId = topicId;
            	$(".topicIndex>h5").text("第"+$(".jiandaTopic>li[tid="+readTopicId+"]").text()+"题");
            	var topic = getObject(data,"topic");
            	var userAnswer = getObject(data,"userAnswer");
            	var hwPaperDetail = getObject(data,"hwPaperDetail");
            	
            	
            	var content = $(".topicContent").css("display","");
            	content.find(".answerDescript>dt:first").text(getObject(topic,"topicName"))
            	content.find(".studentAnswer").html(getObject(userAnswer,"userAnswer"));
            	content.find(".rightAnswer").html(getObject(topic,"answer"));
            	content.find(".anlysis").html(getObject(topic,"analyseWord"));
            	//材料题
            	if(!isEmpty(getObject(topic,"parentId")) && getObject(topic,"parentId") != 0){
            		$(".topicTitle").html(getObject(getObject(data,"parent"),"topicName"));
            		$(".cailiaoContent").css("display","");
            	}else{
            		$(".cailiaoContent").css("display","none");
            	}
            	
            	var hwPaperDetail = getObject(data,"hwPaperDetail");
            	if(hwPaperDetail){
            		$("#score").val(getObject(hwPaperDetail,"score"));
            		$("#content").val(getObject(hwPaperDetail,"content"));
            	}
            	
            	var next = $(".jiandaTopic>li[tid="+readTopicId+"]").next();
            	if(next.length==0){
            		$(".submitRead").text("完成批改").off("click").on("click",function(e){
            			submitRead(e,completeRead);
            		});
            	}else{
            		$(".submitRead").text("保存").off("click").on("click",submitRead);
            	}
            }
		});
	}
	
	function isCompleteRead(){
		var items = $(".jiandaTopic>li").not('.active');
		if(items.length>0){
			return false;
		}
		return true;
	}
})(jQuery)