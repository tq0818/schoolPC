var msgCount;
     $(function(){
		$(".sendStuMsg").show();
		$(".phoneHint").hide();
		$("#ckecktor").hide();
 		$selectSubMenu('student_message');
 		selectGroup1('_edit');
 		selItem();
 		msgCount = $("#msgCount").val();
 		$("#one").change(function(){
 	    	var messageType = $(".btn-type.btn-primary").attr("data-type");
 	    	var oneItem = $("#one").val();
 	    	selTwoItem(messageType,oneItem);
 		});
 		$("#two").change(function(){
 	    	var messageType = $(".btn-type.btn-primary").attr("data-type");
 	    	var oneItem = $("#one").val();
 			 if(messageType == "STUDENT_MESSAGE_CLASSTYPE"){
 				 url = rootPath + "/classModule/selClassType";
 				 $("#classTitle").html("课程：");
 			 }else if(messageType == "STUDENT_MESSAGE_MODULENO"){
 				 url = rootPath + "/classModule/selModuleNo";
 				 $("#classTitle").html("班号：");
 			 }
 	    	selClassOrModule(url,oneItem,$("#two").val());
 		});
 		if($(".btn-type.btn-primary").attr("data-type")=='STUDENT_MESSAGE_CLASSTYPE' && $(".btn-method.btn-primary").attr("data-type")=='STUDENT_MESSAGE_MOBILE'){
			$(".con-tzbt,.con-fsnr").hide();
			$(".notice-main").css({'margin':'100px auto 100px'});
		}
 		
 		
 		$(".btn-method").on('click',function(){
 			$(".con-tzbt,.con-fsnr").show();
 			$(".notice-main").css({'margin':'0 auto'});
 			var type = $(this).data("type");
			$(this).addClass('btn-primary').removeClass('btn-default').siblings().removeClass('btn-primary').addClass('btn-default');//选中第一行
			$(".btn-type:first").addClass('btn-primary').removeClass('btn-default').siblings().removeClass('btn-primary').addClass('btn-default');//第二行默认选中第一个
			selItem();
			if(type == 'STUDENT_MESSAGE_MOBILE'){
				$(".btn-type").last().prev().show();
				$(".phoneHint,.emailHint,.emailTitle,#ckecktor,#email_ckecktor,.use_email,.stuGroup").hide();
				$(".sendStuMsg,.zhan,#messageContent").show();
				if($(".btn-type.btn-primary").attr("data-type")=='STUDENT_MESSAGE_CLASSTYPE'){
					$(".con-tzbt,.con-fsnr").hide();
					$(".notice-main").css({'margin':'100px auto 100px'});
				}
			}
			if(type == "STUDENT_MESSAGE_WEB"){
				$(".btn-type").last().prev().hide();
				$("#messageContent,.zhan,.phoneHint,.emailHint,.emailTitle,#email_ckecktor,#use_email,.stuGroup,.use_email").hide();
				$("#ckecktor,.sendStuMsg").show();
 				
			}
			if(type == 'STUDENT_MESSAGE_EMAIL'){
				$(".btn-type").last().prev().show();
				$(".phoneHint,.emailHint,#ckecktor,#messageContent,.zhan").hide();
				$(".sendStuMsg,.emailTitle,#email_ckecktor,.use_email").show();
			}
 		});
 		$('.btn-type').on('click',function(){
 			$(".con-tzbt,.con-fsnr").show();
 			$(".notice-main").css({'margin':'0 auto'});
 			var notice_fs;
 			var notice_type = $(this).data('type');
 			$.each($('.btn-method'),function(){
 				if($(this).hasClass('btn-primary')) notice_fs = $(this).data('type');
 			});
 			$(this).addClass('btn-primary').removeClass('btn-default').siblings().removeClass('btn-primary').addClass('btn-default');
 			if(notice_fs == 'STUDENT_MESSAGE_MOBILE' && notice_type == 'STUDENT_MESSAGE_SPECIAL'){
 				$(".phoneHint").show();
 				$(".sendStuMsg,.emailHint,.stuGroup").hide();
 			}
 			if(notice_fs == 'STUDENT_MESSAGE_EMAIL' && notice_type == 'STUDENT_MESSAGE_SPECIAL'){
 				$(".emailHint").show();
 				$(".sendStuMsg,.phoneHint,.stuGroup").hide();
 			}
 			if(notice_type == 'STUDENT_MESSAGE_CLASSTYPE' || notice_type == 'STUDENT_MESSAGE_MODULENO'){
 				selItem();
 				$('.sendStuMsg').show();
 				$('.phoneHint,.emailHint,.stuGroup').hide();
 				if($(".btn-method.btn-primary").attr("data-type")=='STUDENT_MESSAGE_MOBILE'){
					$(".con-tzbt,.con-fsnr").hide();
					$(".notice-main").css({'margin':'100px auto 100px'});
				}
 			}
 			if(notice_type == 'STUDENT_MESSAGE_GROUP'){
 				selectGroup1('_edit');
 				$('.stuGroup').show();
 				$('.phoneHint,.emailHint,.sendStuMsg').hide();
 			}
 			valida();
 		});
 		
 		$("#class").change(function(){
 			selPerson();
 		});
 		$("#useMsg").html("0条");
 		$("#write").html(0);
 		$("#SurootPathlus").html(msgCount + "条");
 		
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
 			if(title == ""){
				$("#title").focus();
				$("#title").select();
				return false;
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
 			if(!msgcount){
// 				if(!reg.test($.trim(arr[i]))){
					$('<div class="c-fa">'+ "发送内容不能为空！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 						$(this).remove();}
 					);
 					return false;
//				}
 			}
 			console.log('send');
 			$.ajax({
 				url:rootPath + "/classModule/sendMsg",
 				type:"post",
 				data:{"title":title,"content":msgcount,"messageType":types,"messageMethod":method,"itemOneId":oneItemId,"itemSecondId":twoItemId,"classTypeId":classId,'groupOneId':groupOneId,'groupTwoId':groupTwoId,'email':email,'emailTitle':emailTitle,"phone":phone,"moduleNoId":classId},
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
    		 url:rootPath + "/classModule/selTwoItem",
    		 type:"post",
    		 data:{"oneItem":oneItem},
    		 dataType:"json",
   			beforeSend:function(XMLHttpRequest){
   	              $(".loading").show();
   	              $(".loading-bg").show();
   	         },
    		 success:function(data){
    			 $("#two").empty();
    			 $.each(data.two,function(index,item){
    				 $("#two").append("<option value='" + item.id + "'>" + item.itemName + "</option>");
    			 });
    			 var url = "";
    			 if(messageType == "STUDENT_MESSAGE_CLASSTYPE"){
    				 url = rootPath + "/classModule/selClassType";
    				 $("#classTitle").html("课程：");
    			 }else if(messageType == "STUDENT_MESSAGE_MODULENO"){
    				 url = rootPath + "/classModule/selModuleNo";
    				 $("#classTitle").html("班号：");
    			 }
    			 selClassOrModule(url,oneItem,$("#two").val());
    		 }
    	 });
     }
     
     function selClassOrModule(url,oneItem,twoItem){
		 $.ajax({
			 url:url,
			 type:"post",
			 data:{"itemOneId":oneItem,"itemSecondId":twoItem},
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
				 selPerson();
			 }
		 });
     }
     
     function selPerson(){
    	 //查询人数
    	 var messageType = $(".btn-type.btn-primary").attr("data-type");
    	 var classTypeId = $("#class").val();
    	 var itemOneId = $("#one").val();
    	 var itemSecondId = $("#two").val();
    	 $.ajax({
    		 url:rootPath + "/classModule/selPerson",
    		 type:"post",
    		 data:{"messageType":messageType,"id":classTypeId,"itemOneId":itemOneId,"itemSecondId":itemSecondId},
    		 dataType:"json",
    			beforeSend:function(XMLHttpRequest){
     	              $(".loading").show();
     	              $(".loading-bg").show();
     	         },
    		 success:function(data){
    			 $(".btn-view").html(data.count + "人");
    			 $("#sendStu,#useEmailMsg").html(data.count);
    			 $("#useMsg").html(data.count+"条");
    		 },
	   			complete:function(XMLHttpRequest,textStatus){
	   				$(".loading").hide();
	   	            $(".loading-bg").hide();
	   	        }
    	 });
     }
     function valida(){
    	 var method = $.trim($(".btn-method.btn-primary").attr("data-type"));
    	 switch(method){
    	 	case "STUDENT_MESSAGE_EMAIL":
    	 		if($(".btn-type.btn-primary").attr("data-type") == "STUDENT_MESSAGE_SPECIAL"){
    	 			 var count = 1;
    				 var phone = $.trim($("#email").val());
    				 if(phone.indexOf(",") < 0){
    					 var useMsg = count;
    		      		 $("#useEmailMsg").html(useMsg);
    				 }else{
    					 var phones = phone.split(",");
    					 var person = phones.length;
    		      		 var useMsg = count * parseInt(person);
    		      		 $("#useEmailMsg").html(useMsg);
    				 }
    			}
    	 		break;
    	 	default:
    	 		 if($(".btn-type.btn-primary").attr("data-type") == "STUDENT_MESSAGE_MODULENO"){
    	        	 var count = ($("#msgcount").val()).length;
    	      		 $("#write").html(count);
    	      		 var person = $.trim($("#sendStu").text());
    	      		 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : parseInt(count / 70) + 1 ) * parseInt(person);
    	        	 
    	      		 $("#useMsg").html(useMsg + "条");
    	      		 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
    	      		 $("#write").html(count);
    	 		 }else if($(".btn-type.btn-primary").attr("data-type") == "STUDENT_MESSAGE_CLASSTYPE"){
	      			 var count = ($("#msgcount").val()).length;
	      			 $("#write").html(count);
	      			 var person = $.trim($("#sendStu").text());
	      			 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : parseInt(count / 70) + 1 ) * parseInt(person);
	      			 
	      			 $("#useMsg").html(useMsg + "条");
	      			 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
	      			 $("#write").html(count);
    			}else if($(".btn-type.btn-primary").attr("data-type") == "STUDENT_MESSAGE_GROUP"){
    				 var count = ($("#msgcount").val()).length;
    	      		 $("#write").html(count);
    	      		 var person = parseInt($("#_sendStu").html());
    	      		 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : parseInt(count / 70) + 1 ) * parseInt(person);
    	        	 
    	      		 $("#useMsg").html(useMsg + "条");
    	      		 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
    	      		 $("#write").html(count);
    			}else{
    				 var count = ($("#msgcount").val()).length;
    				 var phone = $.trim($("#phone").val());
    				 if(phone.indexOf(",") < 0){
    					 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : parseInt(count / 70) + 1 );
    		      		 $("#useMsg").html(useMsg + "条");
    		      		 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
    				 }else{
    					 var phones = phone.split(",");
    					 var person = phones.length;
    		      		 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : (count / 70) + 1 ) * parseInt(person);
    		      		 $("#useMsg").html(useMsg + "条");
    		      		 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
    				 }
    	      		 $("#write").html(count);
    			}
    	 		break;
    	 }
     }
   //处理CKEDITOR的值
		function CKupdate() {
			for (instance in CKEDITOR.instances) {
				CKEDITOR.instances[instance].updateElement();
			}
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
			
			$.ajax({
		    	url: rootPath+"/studentGroup/selGroupStu",
		    	type: "post",
		    	dataType: "json",
		    	data : reData,
		    	async:false,
		    	success: function(jsonData){
		    		$('#groupStuCount').html(jsonData.count+'人');
		    		$('#_sendStu,#useEmailMsg').html(jsonData.count);
		    	}
		     })
		}
		