$(function(){
    		//$(".footer").addClass("footer-fixed");
	 $selectMenu("course_module");
    		var item1=$("#item1").val();
    		var item2=$("#item2").val();
    		$("#one").find('a[fValue='+item1+']').addClass("btn-success");
    		$("#two").find('a[fValue='+item2+']').addClass("btn-success");
    		$(".btn-mini").bind("click",function(){
    			var fType = $(this).attr("fType");
    			if(fType=="oneItem"){
    				$("#two").html("");
    				var fValue = $(this).attr("fValue");
    				$.ajax({
    					url:rootPath+"/sysConfigItem/getSecondItemByOne",
    					type:"post",
    					data:{
    						"itemOneId":fValue
    					},
    					success:function(jsonData){
    						for (var i = 0; i < jsonData.length; i++) {
    							var str=' <a href="javascript:;" id="'+jsonData[i].id+'" onclick="change1('+jsonData[i].id+')" fType="twoItem" fValue="'+jsonData[i].id+'" class="btn btn-mini btn-default">'+jsonData[i].itemName+'</a>';
    							$("#two").append(str);
							}
    					}
    				})
    			}
    			if($(this).hasClass("btn-success")){
    				//$(this).removeClass("btn-success")
    			}else{
    				$(this).parent().find(".btn-success").removeClass("btn-success");
    				$(this).addClass("btn-success")
    				
    			}
    			//alert(123);
    			search(1);
    		})
    		search(1);
    		
    	})
    	function search(pageNo){
    		//alert(pageNo);
    		var itemOneId=null;
			var itemTwoId=null;
			var modleType=null;
    		$("#item").find(".btn-success").each(function(i){
    			var type = $(this).attr("fType");
    			var value = $(this).attr("fValue");
    			//alert(type+":"+value);
    			if("oneItem"==type){
    				itemOneId=value;
    			}else if("twoItem"==type){
    				itemTwoId=value;
    			}
    			
    		})
    		modleType = $("#moduleType").val();
    		if(modleType==""||modleType==null){
    			modleType="TEACH_METHOD_LIVE";
    		}
    	 	/* alert(itemOneId);
    		alert(itemTwoId);
    		alert(modleType);  */
    		
    		 $.ajax({
    			url:rootPath+"/classModule/toSearch",
    			type:"post",
    			data:{
    				"itemOneId":itemOneId,
    				"itemSecondId":itemTwoId,
    				"teachMethod":modleType,
    				"page":pageNo
    			},
    			success:function(data){
    				//alert(data);
    				$("#main").html(data);
    				//alert(data);
    			}
    			
    		})
    	}
    	function change1(id){
    		$("#"+id).parent().find(".btn-success").removeClass("btn-success");
    		$("#"+id).addClass("btn-success")
    		search();
    		
    	}
    	function add(){
    		var teachMethod = $("#teachMethod").val();
    		var itemOneId = $("#one").find(".btn-success").attr("fValue");
    		var itemTwoId = $("#two").find(".btn-success").attr("fValue");
    		$("#method").val(teachMethod);
    		$("#itemOne").val(itemOneId);
    		$("#itemTwo").val(itemTwoId);
    		$("#addForm")[0].submit();
    	}