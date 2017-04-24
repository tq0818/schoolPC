$(function(){
			if($("#lableTypes").val()){
				$selectMenu("course_class_type");
			}else{
				$selectSubMenu('student_change');
			}
    		selectType(2);
    		 $("#reason_type").getSysDict("RESPONSIBLE_DIVISION",null,"bycode");
	         $("#reason_type").bind("change",function(){
	        	 $("#reason_detail").html('').getSubDict($(this).find("option:selected").val(),"reason");
	        	 $("#reason_depart").html('').getSubDict($(this).find("option:selected").val(),"depart");
        	 });
	         var payMasterId = $("#payMasterId").val();
	         $.ajax({
	        	 url:rootPath+"/student/feeMessage2",
	        	 type:"post",
	        	 data:{
	        		"payMasterId":payMasterId 
	        	 },
	        	 success:function(data){
	        		 $("#message").html(data);
	        	 }
	         })
    	})
    	
    	function query(){
    		var mobileOrUsername = $("#mb").val(),
    			crcMobileFlag   = parseInt($("#crc-mobileFlag").val()),
    			crcUsernameFlag = parseInt($("#crc-usernameFlag").val());
    		
    		if(!crcMobileFlag && !crcUsernameFlag){
				$("#tips").html('机构已关闭注册，请开启后再进行操作').show();
				return;
			}
    		if(crcMobileFlag && !crcUsernameFlag && !$.checkMobile(mobileOrUsername)){
    			$("#tips").html('请填写合法的手机号码').show();
    			return;
    		}
    		if(!crcMobileFlag && crcUsernameFlag && !$.checkUsername(mobileOrUsername)){
    			$("#tips").html('请填写合法的用户名，用户名只能是纯字母或者字母开头的数字和下划线组合').show();
    			return;
    		}
    		if(!$.checkMobile(mobileOrUsername) && !$.checkUsername(mobileOrUsername)){
    			$("#tips").html('请填写合法的手机号或用户名').show();
    			return;
    		}
    		$.ajax({
    			url:rootPath+"/student/findStudent",
				type : "post",
				data : {
					"mobile" : mobileOrUsername
				},
				success : function(data) {
					$("#oldStudent").html(data);
				}
    		})
		}
		function checkHasCommodity(form){
			$.ajax({
				url: rootPath+"/studentPayMaster/checkHasCommodityByChangeStudent",
				data:form,
				type: "post",
				dataType: "text",
				async: false,
				success : function(jsonData){
					if(jsonData=="success"){
						$("#checkIsOk").val("true");
					}
				},
				error : function(){
				}
			})
		}
    	function save(data){
    		$("#checkIsOk").val("false");
    		if(!$("#newStu").valid()){
    			//alert(123);
				return;
			}
    		
    		var payMasterId=$("#payMasterId").val();
    		if("no"==data){
    			var form=$("#newStu").serialize();
    			if(document.getElementById("studentG1")){
    				form+="&groupOneId="+$("#studentG1").val();
    			}
    			if(document.getElementById("studentG2")){
    				form+="&groupTwoId="+$("#studentG2").val();
    			}
    			console.log(form);
    			checkHasCommodity(form);
    			if($("#checkIsOk").val()=="true"){
	    			$.ajax({
	    				url:rootPath+"/studentPayMaster/changeStudent",
	    				type:"post",
	    				data:form,
	    				success:function(jsonData){
	    					if(jsonData=="success"){
	    						$('<div class="c-fa">'+ "保存成功!" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();})
	    	    				history.back();
	    	    			}else{
	    	    				alert(jsonData);
	    	    			}
	    				}
	    			})
    			}else{
    				$.msg("此学员在这个班级已报过名！")
    			}
    		}else{
    			$("#studentId").val(data);
    			var form=$("#newStu").serialize();
    			if(document.getElementById("studentG1")){
    				form+="&groupOneId="+$("#studentG1").val();
    			}
    			if(document.getElementById("studentG2")){
    				form+="&groupTwoId="+$("#studentG2").val();
    			}
    			checkHasCommodity(form);
    			if($("#checkIsOk").val()=="true"){
	    			$.ajax({
	    				url:rootPath+"/studentPayMaster/changeStudent",
	    				type:"post",
	    				data:form,
	    				success:function(jsonData){
	    					if(jsonData=="success"){
	    						$('<div class="c-fa">'+ "保存成功!" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();})
	    						if($("#lableTypes").val()){
	    							setTimeout(function(){
	        							window.location.reload();
	    							},1000);
	    						}else{
	    							setTimeout(function(){
	        							window.location.href=rootPath+"/student/studentList";
	    							},1000);
	    						}
	    	    			}else{
	    	    				alert(jsonData);
	    	    			}
	    				}
	    			})
    			}else{
    				$.msg("此学员在这个班级已报过名！")
    			}
    		}
    	}
    	