function payed(){
    		//alert(123);
    		var posReal=$.MoneyToNum($("#posReal").val());
    		var cashReal=$.MoneyToNum($("#cashReal").val());
    		var remitReal=$.MoneyToNum($("#remitReal").val());
    		var checkReal=$.MoneyToNum($("#checkReal").val());
    		var canPay = $.MoneyToNum($("#canPay").html());
    		var id=$("#id").val();
    		var pid=$("#pid").val();
    		//alert(canPay);
    		//alert(posReal*1+cashReal*1+remitReal*1+checkReal*1);
    		if((posReal+cashReal+remitReal+checkReal)!=canPay){
    			alert("请核对交费金额是否等于应缴费用金额!");
    		}else{
    			$.ajax({
    				url:rootPath+"/student/toPay",
    				type:"post",
    				data:{
    					"id":id,
    					"posReal":posReal,
    					"cashReal":cashReal,
    					"remitReal":remitReal,
    					"checkReal":checkReal,
    					"payMasterId" :pid
    				},
    				success:function(data){
    					if("success"==data){
    						alert("交费成功!",function(){
    							history.go(-1);
    						});
    						//$("#topayForm")[0].submit();
    						
    					}else{
    						alert("请确定交费金额!")
    						
    					}
    				}
    				
    				
    			})
    			
    		}
    	}
    	$(function(){
    		$selectSubMenu('student_urge_fee');
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
    			if(!$.isMoney($(this).val())){
    				$(this).val($.formatMoney($(this).val()));
    			}
    		})
    	})
    	function toSearch(){
    		$(".tips").fadeOut();
    		var mobile=$("#phone").val();
    		var id=$("#pid").val();
    		var stuId=$("#stuId").val();
    		var schoolId=$("#schoolId").val();
    		$.ajax({
    			url:rootPath+"/student/search",
    			type:"post",
    			data:{
    				"mobile":mobile,
    			},
    			success:function(data){
    				//alert(data);
    				if(""!=data&&data!=null){
    					$("#mob").val(mobile);
    					$.ajax({
    						url:rootPath+"/student/search",
    						type:"post",
    						data:{
    							"mobile":mobile,
    						},
    						success:function(data){
    							$.ajax({
    	    		    			url:rootPath+"/student/baseMessage",
    	    		    			type:"post",
    	    		    			data:{
    	    		    				"mobile":mobile,
    	    		    			},
    	    		    			success:function(data){
    	    		    				$("#message").html(data);
    	    		    			}
    	    		    		})	
    	    		    		$.ajax({
    	    		    					url:rootPath+"/student/classTypeMessage",
    	    		    					type:"post",
    	    		    					data:{
    	    		    						"mobile":mobile,
    	    		    					},
    	    		    					success:function(data){
    	    		    						$("#classTypeMessage").html(data);
    	    		    					}
    	    		    		})
    	    		    		$.ajax({
    	    		    					url:rootPath+"/student/feeMessage",
    	    		    					type:"post",
    	    		    					data:{
    	    		    						"mobile":mobile,
    	    		    					},
    	    		    					success:function(data){
    	    		    						$("#feeMessage").html(data);
    	    		    					}
    	    		    		})
    						}
    						
    						
    					})
    				}else{
    					$(".tips").fadeIn();
    					//alert("没有相关信息!");
    				}
    			}
    		})
    		
    	}
    	function pay(id){
    		//alert(id);
    		$("#idd").val(id);
    		$("#payForm")[0].submit();
    	}