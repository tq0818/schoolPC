  	$(function(){
	  		if($("#lableTypes").val()){
				$selectMenu("course_class_type");
			}else{
				$selectSubMenu('student_change');
			}
    		selectType(3);
    		var id  =$("#id").val();
    		var stuId=$("#stuId").val();
    		var mobile=$("#mobile").val();
    		$.ajax({
    			url:rootPath+"/student/selectClassType",
    			type:"post",
    			data:{
    				"id":id,
    			},
    			success:function(html){
    				$("#classtype").html(html);
    			}
    			
    		})
    		
    		$.ajax({
    			url:rootPath+"/student/feeMessage2",
    			type:"post",
    			data:{
    				//"mobile":mobile
    				"payMasterId":id,
    			},
    			success:function(html){
    				$("#feeMessage").html(html);
    			}
    			
    		})
    		$.ajax({
    			url:rootPath+"/studentAgentMaterial/material",
    			type:"post",
    			data:{
    				"mobile":mobile,
    				"id":id
    			},
    			success:function(html){
    				$("#material").html(html);
    			}
    			
    		})
    	})