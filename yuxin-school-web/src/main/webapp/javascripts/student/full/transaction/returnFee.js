$(function() {
		if($("#lableTypes").val()){
			$selectMenu("course_class_type");
		}else{
			$selectSubMenu('student_change');
		}
		selectType(0);
		 var payMasterId = $("#payMasterId").val();
         $.ajax({
        	 url:rootPath+"/student/feeMessage2",
        	 type:"post",
        	 data:{
        		"payMasterId":payMasterId 
        	 },
        	 success:function(data){
        		 $("#message").html(data);
        		 setValue();
        		 var tt=$("#posReal").html()*1+$("#cashReal").html()*1+$("#checkReal").html()*1+$("#remitReal").html()*1;
        		 $("#refundFee").val(tt);
        		 if($("#pos").html()!="" && $("#pos").html()*1>0){
//        			 $("#posReturn").val($("#pos").html());
        			 $("#posReturn").show();
        			 $("#a").show();
        		 }
        		 if($("#cash").html()!="" && $("#cash").html()*1>0){
        			 $("#cashReturn").val($("#cash").html());
        			 $("#cashReturn").show();
        			 $("#b").show();
        		 }
        		 if($("#check").html()!="" && $("#check").html()*1>0){
        			 $("#checkReturn").val($("#check").html());
        			 $("#checkReturn").show();
        			 $("#c").show();
        		 }
        		 if($("#remit").html()!="" && $("#remit").html()*1>0){
        			 $("#remitReturn").val($("#remit").html());
        			 $("#remitReturn").show();
        			 $("#d").show();
        		 }
        		 
        		 
        	 }
         })
         $(".money").bind("blur",function(){
        	 var m1 = $(this).val();
        	 var m2=$(this).parent().prev().prev().html();
        	 if(m1*1>m2*1){
        		 $(this).val(m2);
        		 $.alert("不能大于该项已交费用!");
        	 }
         })
         	$(".prices").bind("keyup",function(event){
    			//先把非数字的都替换掉，除了数字和. 
    			$(this).val($(this).val().replace(/[^\d.]/g,""));
    	        //必须保证第一个为数字而不是. 
    	        $(this).val($(this).val().replace(/^\./g,"0."));
    	        //保证只有出现一个.而没有多个. 
    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
    	        //保证.只出现一次，而不能出现两次以上
    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
    			
    		})
    		$(".prices").bind("blur",function(event){
    			//先把非数字的都替换掉，除了数字和. 
    			$(this).val($(this).val().replace(/[^\d.]/g,""));
    			 var value=$(this).val();
     	        var is=false;
     	        for (var i = 0; i < value.length; i++) {
     	            var  item =  value.charAt(i);
     	           	if("."==item){
     	           		is=true;
     	           	}
     	        }
     	        if(value!=null||value!=""){
     	        	if(!is){
     	        		$(this).val($(this).val()+".00");	
     	        	}
     	        } 
    	        //必须保证第一个为数字而不是. 
    	        $(this).val($(this).val().replace(/^\./g,"0."));
    	        //保证只有出现一个.而没有多个. 
    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
    	        //保证.只出现一次，而不能出现两次以上
    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
    			
    		})
	})
	function changeCanReturn(){
		var hasUsed = $("#hasUsed").val();
		var total=$("#posReal").html()*1+$("#cashReal").html()*1+$("#checkReal").html()*1+$("#remitReal").html()*1;
		$("#canReturn").html("可退:"+(total*1-hasUsed*1));
		$("#refundFee").val(total*1-hasUsed*1);
	}
	function save(){
		var money=0;
		$(".money").each(function(){
			money=money+$(this).val()*1;
		})
		var hasUsed = $("#hasUsed").val();
		var total=$("#posReal").html()*1+$("#cashReal").html()*1+$("#checkReal").html()*1+$("#remitReal").html()*1;
		if(money!=(total*1-hasUsed*1)){
			$("#canReturn").html("可退:"+(total*1-hasUsed*1));
			$.alert("请检查退费金额!");
		}else{
			$.ajax({
				url:rootPath+"/studentFeeRefund/returnFee",
				type:"post",
				data:$("#feeMessage").serialize(),
				success:function(data){
					if("success"==data){
						$.alert("退费成功",function(){
							//window.location.href=rootPath+"/student/toStuSearch";
							history.go(-1);
						});
	    				
					}else{
						$.alert("退费失败!",function(){
							history.go(0);
						})
						
					}
				}
			})
		}
	}