(function($) {
	var FormQue = {
		init : function() {
			var isCorP = $("#isCorP").val();
			var isC = $("#isC").val();
			if(isCorP == 0){
				FormQue.loadZdy();
			}else{
				FormQue.loadKc();
			}
			if(isC == 1){
				FormQue.loadKc();
			}
			$(".goBack").click(function(){
				location.href=rootPath+"/Question/questionSetIndex";
			});
			$selectSubMenu('org_service');
		},
		loadZdy : function(){
			$.ajax({
				url : rootPath+"/Question/loadZdy",
				type : "post",
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data){
					$("#zdyInfo").html("").html(data);
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		loadKc : function(){
			$.ajax({
				url : rootPath+"/Question/loadKc",
				type : "post",
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(data){
					$("#kcInfo").html("").html(data);
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		},
		addClassfy : function(itemId,name,type){
			var param = "";
			if(itemId){
				param+="&itemId="+itemId;
			}
			if(name){
				param+="&classifyName="+name;
			}
			if(type){
				param+="&classType="+type;
			}
			$.ajax({
				url : rootPath+"/QuestionClassify/add",
				type : "post",
				data : param,
				success : function(data){
					if(data == 'success'){
						if(itemId == 0){
							console.log("自定义");
							$(".new-add").slideUp(50);
							$("#definitionAddZdy").val("");
							FormQue.loadZdy();
						}else{
							console.log("课程");
							FormQue.loadKc();
						}
						
					}
				}
			});
		},
		updateClassfyZdy : function(id,name,able){
			var param = "";
			if(id){
				param+="&id="+id;
			}
			if(name){
				param+="&classifyName="+name;
			}
			if(able || able == 0){
				param+="&delFlag="+able;
			}
			$.ajax({
				url : rootPath+"/QuestionClassify/update",
				type : "post",
				data : param,
				success : function(data){
					if(data=="success"){
						$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
						});
					}
				}
			});
		},
		delClassfy : function(id){
			$.ajax({
				url : rootPath+"/QuestionClassify/del",
				type : "post",
				data : {"id":id},
				success : function(data){
					if(data=="success"){
						$(".delet").css("display","none")
						$('<div class="c-fa">'+ "删除成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
							FormQue.loadZdy();
						});
					}
				}
			});
		},
		changeStatus : function (status,fuc){
			$.ajax({
				url : rootPath+"/Question/changeStatus",
				type : "post",
				data : {"status":status,"code":"QUESTION_LABEL_STATE"},
				success : function(data){
					if(data=="success"){
						$('<div class="c-fa">'+ "修改成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
							if(fuc){
								fuc();
							}
						});
					}
				}
			});
		}
	}
	$(document).ready(function() {
		FormQue.init();
	})
	window.FormQue = FormQue;
})(jQuery)