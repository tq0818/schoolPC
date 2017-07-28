
(function($) {
	var paperId;//试卷id
	var tikuId;//题库类型id
	var subjectId;//科目id
	var rules={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	        	paperName: {
	                required: true,
	            },
	            examTime: {    //验证答题时间
	            	examTime: true
	            },
	            totalScore: {    //验证总分数
	            	totalScore: true
	            },
	        },
	        messages: {
				examTime:{
					number:"请输出数字"
				},
				totalScore:{
					number:"请输出数字"
				}

	        },
	        submitHandler: function (form) {
	            form.submit();
	        },
	        onkeyup: false
	    };
	var Forms = {
		init : function() {
			$.extend($.validator.messages, {
			    required: "试卷名称不能为空",
			});
			$("#paperInfoFrom").validate(rules);
			jQuery.validator.addMethod("examTime", function(value, element) {
			    return (value != null && value != "");
			}, "答题时间不能为空");
			jQuery.validator.addMethod("totalScore", function(value, element) {
			    return (value != null && value != "");
			}, "总分数不能为空");
			
			//编辑时
			paperId = $("#paperId").val();
			if(paperId){
				var tocType = $("#HTopicType").val();
				var typeArray = tocType.split(",");
				$.each(typeArray,function(i,item){
					$(".topicType").each(function(i){
						var type = $(this).val();
						if(type == item){
							$(this).attr("checked","checked");
						}
					});
				})
			}
			//------
			// //绑定所属区域
			// $("#region").cityselect({
			// 	url:rootPath + "/javascripts/company/city.min.js",
			//     prov:"北京", //省份
			// });
			
			tikuId = $("#tikuId").val();
			subjectId = $("#subjectId").val();
			//保存并退出或者继续
			$(".save").click(function(){
				var thisHtml = $(this).html();
				paperId = $("#paperId").val();
				var paperName = $("#paperName").val();
				var paperType="";
				$(".paperType").each(function(){
					if($(this).is(':checked')){
						paperType = $(this).val();
					}
				});
				var examTime = $("#examTime").val();
				var totalScore = $("#totalScore").val();
				var region = $("#prov").val();
				var topicType="";
				var isChecked = "false";
				var teacherId = $("#teacherList").val();
				$(".topicType:checked").each(function(i,item){
					isChecked = "true";
						if(i==0){
							topicType += $(this).val();
						}else{
							topicType += ","+$(this).val();
						}
				});
				if($("#paperInfoFrom").valid()){
					if(isChecked == "true"){
						if(paperId){
							//存在paperId：编辑
							Forms.updatePaper(paperId, paperName, paperType, examTime, totalScore, region, topicType,thisHtml,teacherId);
						}else{
							//不存在paperId：新增
							Forms.insertPaper(paperName, paperType, examTime, totalScore, region, topicType,subjectId,tikuId,thisHtml,teacherId);
						}
					}else{
						$("#error").show(function(){
							setTimeout(function(){
								$("#error").hide();
							},3000);
						});
					}
					
				}
				
			});
			
			
		},
		insertPaper : function(paperName,paperType,examTime,totalScore,region,topicType,subjectId,tikuId,thisHtml,teacherId){
			$.ajax({
				url : rootPath+"/tikuPaper/insertAndReturnId",
				type : "post",
				data : {"paperName":paperName,"paperType":paperType,"examTime":examTime,"totalScore":totalScore,"region":region,"containTopicType":topicType,"tkuSubjectId":subjectId,"tikuCategoryId":tikuId,"teacherId":teacherId},
				beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success : function(data){
					if(data>0){
						//插入成功
						if(thisHtml == "保存并退出"){
							location.href=rootPath+"/tikuPaper/toTikuPaper/"+tikuId;
						}
						if(thisHtml == "保存并继续"){
							var objform = document.createElement("form");
							document.body.appendChild(objform);
							objform.action = rootPath + "/tikuPaper/addTopic";
							objform.method = "post";
							
							var cateId = document.createElement("input");
							cateId.type = "hidden";
							objform.appendChild(cateId);
							cateId.value = data;
							cateId.name = "paperId";
							
							var types = document.createElement("input");
							types.type = "hidden";
							objform.appendChild(types);
							types.value = $("#btn").val();
							types.name = "btn";
							
							objform.submit();
						}
					}
				},
		        complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		},
		updatePaper : function(id,paperName,paperType,examTime,totalScore,region,topicType,thisHtml,teacherId){
			$.ajax({
				url : rootPath+"/tikuPaper/updateAndReturnId",
				type : "post",
				data : {"id":id,"paperName":paperName,"paperType":paperType,"examTime":examTime,"totalScore":totalScore,"region":region,"containTopicType":topicType,"teacherId":teacherId},
				beforeSend:function(XMLHttpRequest){
		            $(".loading").show();
		            $(".loading-bg").show();
		        },
				success : function(data){
					if(data>0){
						//更新成功
						if(thisHtml == "保存并退出"){
							location.href=rootPath+"/tikuPaper/toTikuPaper/"+tikuId;
						}
						if(thisHtml == "保存并继续"){
							var objform = document.createElement("form");
							document.body.appendChild(objform);
							objform.action = rootPath + "/tikuPaper/addTopic";
							objform.method = "post";
							
							var cateId = document.createElement("input");
							cateId.type = "hidden";
							objform.appendChild(cateId);
							cateId.value = data;
							cateId.name = "paperId";
							
							var types = document.createElement("input");
							types.type = "hidden";
							objform.appendChild(types);
							types.value = $("#btn").val();
							types.name = "btn";
							
							objform.submit();
						}
					}
				},
		        complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
		            $(".loading-bg").hide();
		        }
			});
		}
	};
	$(document).ready(function() {
		Forms.init();
		$(".number").on("keydown",onlyNum);
	});
	window.Forms = Forms;
})(jQuery);

function onlyNum(event) {
	var event = arguments.callee.caller.arguments[0] || window.event;
	if (!(event.keyCode == 46) && !(event.keyCode == 8)&& !(event.keyCode == 37) && !(event.keyCode == 39)){
		if (!((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || (event.keyCode == 110) || (event.keyCode == 190))){
			return false;
		}else{
			if(event.keyCode == 69){
				return false;
			}
			var reg = /^[0-9]{0,3}(.[0-9]{0,2})?$/;
			return reg.test($("#totalScore").val());
		}
	}
}

function onlyNumA(evnt){
	console.log(evnt);
	 var keyCode=window.event?evnt.keyCode:evnt.which;
	 if(keyCode==46) return false;
	 if(keyCode>=48&&keyCode<=57||keyCode==46||keyCode==8||keyCode==37||keyCode==39){
		 var reg = /^[0-9]{0,3}(.[0-9]{0,2})?$/;
		 return reg.test($("#totalScore").val());
	 }
	 return false;
}