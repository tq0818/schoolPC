     $(function(){
 		$selectSubMenu('student_message');
 		selectGroup1('_edit');
 		selItem();
 		$("#one").change(function(){
 	    	var messageType = $(".btn-type.btn-primary").attr("data-type");
 	    	var oneItem = $("#one").val();
 	    	selTwoItem(messageType,oneItem);
 		});
 		$("#two").change(function(){
 	    	var messageType = $(".btn-type.btn-primary").attr("data-type");
 	    	var twoItem = $("#two").val();
            selThreeItem(messageType,twoItem);
 	    	// selClassOrModule(url,oneItem,$("#two").val());
 		});
         $("#three").change(function(){
             var messageType = $(".btn-type.btn-primary").attr("data-type");
             var oneItem = $("#one option:selected").attr("data-code");
             var twoItem = $("#two option:selected").attr("data-code");
             if(messageType == "STUDENT_MESSAGE_WEIXIN"){
                 url = rootPath + "/classModule/selClassType";
                 $("#classTitle").html("课程：");
             }
             selClassOrModule(url,oneItem,twoItem,$("#three option:selected").attr("data-code"));
         });


 		$(".btn-send").click(function(){
 			var title = $.trim($("#title").val());
 			var method = $.trim($(".btn-method.btn-primary").attr("data-type"));
 			var types = $.trim($(".btn-type.btn-primary").attr("data-type"));
 			var msgcount = "";
 			var emailTitle = '';
 			var oneItemId = null;
 			var twoItemId = null;
 			var classId = null;
 			var groupOneId = null;
 			var groupTwoId = null;
 			var phone = null;
 			var email = null;
 			if($(".btn-type.btn-primary").attr("data-type")!='STUDENT_MESSAGE_CLASSTYPE' && $(".btn-method.btn-primary").attr("data-type")!='STUDENT_MESSAGE_MOBILE'){
 				if(title == ""){
 					$("#title").focus();
 					$("#title").select();
 					
 					return false;
 				}
 			}
 			
 			if(method == 'STUDENT_MESSAGE_MOBILE' && types == "STUDENT_MESSAGE_SPECIAL"){
 				phone = $.trim($("#phone").val());
 				if(phone == ""){
 					$("#phone").focus();
 					$("#phone").select();
 					return false;
 				}
 				phone = phone.replace(/，/g,",");
 				var reg = /^0?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/;
 				if(phone.indexOf(",") < 0){
 	 				if(!reg.test($.trim(phone))){
 	 					$("#phone").focus();
 	 					$('<div class="c-fa">'+ "手机号" + phone + "格式不正确！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 	 						$(this).remove();}
 	 					);
 	 					return false;
 	 				}
 				}else{
 					var array = phone.split(",");
 					for(var i = 0 ; i < array.length ; i++){
 	 	 				if(!reg.test($.trim(array[i]))){
 	 	 					$("#phone").focus();
 	 	 					$('<div class="c-fa">'+ "手机号" + array[i] + "格式不正确！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 	 	 						$(this).remove();}
 	 	 					);
 	 	 					return false;
 	 	 				}
 					}
 				}
 			}
 			if(method == 'STUDENT_MESSAGE_EMAIL'){
 				emailTitle = $.trim($('#email_title').val());
 				if(!emailTitle){
 					$('#emailTitle').focus();
 					$('<div class="c-fa">'+ "请填写邮件标题！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
	 						$(this).remove();}
	 					);
 					return;
 				}
 			}
 			if(method == 'STUDENT_MESSAGE_EMAIL' && types == "STUDENT_MESSAGE_SPECIAL"){
 				email = $.trim($('#email').val());
 				if(!email){
 					$("#email").focus();
 					$("#email").select();
 					return false;
 				}
 				email = email.replace(/，/g,",");
 				var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
 				if(email.indexOf(',') < 0){
 					if(!reg.test(email)){
 						$('#email').focus();
 						$('<div class="c-fa">'+ "邮箱" + email + "格式不正确！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 	 						$(this).remove();}
 	 					);
 	 					return false;
 					}
 				}else{
 					var arr = email.split(',');
 					for(var i=0;i<arr.length;i++){
 						if(!reg.test($.trim(arr[i]))){
 							$('<div class="c-fa">'+ "邮箱" + arr[i] + "格式不正确！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 	 	 						$(this).remove();}
 	 	 					);
 	 	 					return false;
 						}
 					}
 				}
 			}
 			if(types == 'STUDENT_MESSAGE_CLASSTYPE' || types == 'STUDENT_MESSAGE_MODULENO'){
 				oneItemId = $.trim($("#one").val());
 				twoItemId = $.trim($("#two").val());
                oneItemCode = $("#one option:selected").attr("data-code");
                twoItemCode = $("#two option:selected").attr("data-code");
                threeItemCode = $("#three option:selected").attr("data-code");
 				classId = $.trim($("#class").val());
 			}
 			if(types == 'STUDENT_MESSAGE_GROUP'){
 				groupOneId = $.trim($('#studentG1_edit').val());
 				groupTwoId = $.trim($('#studentG2_edit').val());
 			}
 			
 			if(method == "STUDENT_MESSAGE_WEB"){
 				CKupdate();
 				msgcount = $("#newsContents").val();
 				msgcount = msgcount.replace(/<p>/g, "<span>");
 				msgcount = msgcount.replace(/<p /g, "<span ");
 				msgcount = msgcount.replace(/<\/p>/g, "</span><br>");
 			}else if(method == 'STUDENT_MESSAGE_EMAIL'){
 				CKupdate();
 				msgcount = $("#email_newsContents").val();
// 				msgcount = msgcount.replace(/<p>/g, "<span>");
// 				msgcount = msgcount.replace(/<p /g, "<span ");
// 				msgcount = msgcount.replace(/<\/p>/g, "</span><br>");
 			}else{
 				msgcount = $("#msgcount").val();
 			}
 			if($(".btn-type.btn-primary").attr("data-type")!='STUDENT_MESSAGE_CLASSTYPE' && $(".btn-method.btn-primary").attr("data-type")!='STUDENT_MESSAGE_MOBILE'){
	 			if(!msgcount){
	// 				if(!reg.test($.trim(arr[i]))){
						$('<div class="c-fa">'+ "发送内容不能为空！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
	 						$(this).remove();}
	 					);
	 					return false;
	//				}
	 			}
 			}
 			console.log('send');
 			var isHurry = $('.hurryNotice:checked').val();
 			var lessonId = $('#classLesson').val();
 			if($.trim(lessonId) ==""){
 				 $('<div class="c-fa">'+ "您还没选择课次" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
			        	$(this).remove();
			      });
 				 return;
 			}
 			$.ajax({
 				url:rootPath + "/classModule/sendMsg",
 				type:"post",
 				data:{"title":title,"content":msgcount,"messageType":types,"messageMethod":method,"itemOneCode":oneItemCode,"itemSecondCode":twoItemCode,"itemThirdCode":threeItemCode,"classTypeId":classId,'groupOneId':groupOneId,'groupTwoId':groupTwoId,'email':email,'emailTitle':emailTitle,"phone":phone,"moduleNoId":classId,"isHurry":isHurry,"lessonId":lessonId},
 				dataType:"json",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
 				success:function(data){
 					if(data.result == "success"){
 						$(".loading").hide();
				        $('<div class="c-fa">'+ "消息已发送！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
				        	$(this).remove();
		 					location.href = rootPath + "/student/notice";
		 					$(".loading").show();
				        });
 					}else{
			            $(".loading").hide();
			            $(".loading-bg").hide();
			            if(data.result == "stuno"){
					        $('<div class="c-fa">'+ "当前没有学员！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }else if(data.result == "msgNotCount"){
					        $('<div class="c-fa">'+ "短信量不足，请购买后再发送" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }else{
					        $('<div class="c-fa">'+ "消息发送失败！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }
 					}
 				}
 			});
 		});
     });
     function selItem(){
    	 var messageType = $(".btn-type.btn-primary").attr("data-type");
    	 var oneItem = $("#one").val();
    	 selTwoItem(messageType,oneItem);
     }
     
     function selTwoItem(messageType,oneItem){
    	 $.ajax({
             url:rootPath + "/classModule/selItemRelationByPid",
    		 // url:rootPath + "/classModule/selTwoItem",
    		 type:"post",
    		 data:{"pid":oneItem},
    		 dataType:"json",
   			beforeSend:function(XMLHttpRequest){
   	              $(".loading").show();
   	              $(".loading-bg").show();
   	         },
    		 success:function(data){
    			 $("#two").empty();
    			 $.each(data.two,function(index,item){
    				 $("#two").append("<option value='" + item.id + "' data-code='"+item.itemCode+"'>" + item.itemName + "</option>");
    			 });
                 selThreeItem(messageType,$("#two").val());
    		 }
    	 });
     }

		function selThreeItem(messageType,twoItem){
			$.ajax({
				url:rootPath + "/classModule/selItemRelationByPid",
				// url:rootPath + "/classModule/selTwoItem",
				type:"post",
				data:{"pid":twoItem},
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
					$(".loading").show();
					$(".loading-bg").show();
				},
				success:function(data){
					$("#three").empty();
					$.each(data.two,function(index,item){
						$("#three").append("<option value='" + item.id + "' data-code='"+item.itemCode+"'>" + item.itemName + "</option>");
					});
					var url = "";
					if(messageType == "STUDENT_MESSAGE_CLASSTYPE"){
						url = rootPath + "/classModule/selClassType";
						$("#classTitle").html("课程：");
					}else if(messageType == "STUDENT_MESSAGE_MODULENO"){
						url = rootPath + "/classModule/selModuleNo";
						$("#classTitle").html("班号：");
					}
                    var oneItem = $("#one option:selected").attr("data-code");
                    var twoItem = $("#two option:selected").attr("data-code");
					selClassOrModule(url,oneItem,twoItem,$("#three option:selected").attr("data-code"));
				}
			});
}
     
     function selClassOrModule(url,oneItem,twoItem,threeItem){

		 $.ajax({
			 url:url,
			 type:"post",
             data:{"itemOneCode":oneItem,"itemSecondCode":twoItem,"itemThirdCode":threeItem},
			 // data:{"itemOneId":oneItem,"itemSecondId":twoItem,"itemThridCode":threeItem},
			 dataType:"json",
	   			beforeSend:function(XMLHttpRequest){
	   	              $(".loading").show();
	   	              $(".loading-bg").show();
	   	         },
			 success:function(data){
				 $("#class").empty();
				 $.each(data.types,function(index,item){
					 $("#class").append("<option value='" + item.id + "'>" + item.name + "</option>");
				 });
				 $(".js-example-basic-single").select2();
			 }
		 });
     }
     
		//初始化
		function selectGroup1(type){
			$("#studentG1"+type).html('');
			 $.ajax({
		    	url: rootPath+"/studentGroup/selectStudentGroup1",
		    	type: "post",
		    	dataType: "json",
		    	async:false,
		    	success: function(jsonData){
		    		var id ;
//		    		$("#studentG1"+type).append('<option value="" selected="selected">全部</option>');
		    		$.each(jsonData,function(i, g){
		    			if(i==0) id = g.id;
		    			$("#studentG1"+type).append('<option value="' + g.id + '">'
						+ g.groupName + '</option>');
		    		})
		    		$("#studentG2"+type).append('<option value="" selected="selected">全部</option>');
		    		selectGroup2(id,'_edit');
		    	}
		     })
		}
		//一级切换事件
		function selectGroup2(a,type){
			$("#studentG2"+type).html('');
			if($("#studentG1"+type).val()==""){
				$("#studentG2"+type).append('<option value="" selected="selected">全部</option>');
				return false;
			}
			$.ajax({
		    	url: rootPath+"/studentGroup/selectStudentGroup2/"+$("#studentG1"+type).val(),
		    	type: "post",
		    	dataType: "json",
		    	async:false,
		    	success: function(jsonData){
		    		$("#studentG2"+type).append('<option value="" selected="selected">全部</option>');
		    		$.each(jsonData,function(i, g){
		    			$("#studentG2"+type).append('<option value="' + g.id + '">'
						+ g.groupName + '</option>');
		    		})
		    	}
		     })
		     selGroupStu({groupOneId:$("#studentG1"+type).val()});
		}
		//二级切换事件
		function selGroupStu(dataInfo){
			var reData = {};
			if(typeof dataInfo === 'object' && !dataInfo.hasOwnProperty('groupOneId')){
				reData.groupTwoId = $(dataInfo).find('option:selected').val();
			}else{
				reData = dataInfo;
			}
			if(!reData.groupTwoId){
				reData.groupOneId = $('#studentG1_edit').find('option:selected').val();
			}
			
		}
		