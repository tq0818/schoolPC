$(function(){
		$selectSubMenu('student_urge_fee');
    		//$(".footer").addClass("footer-fixed");
    		$("#classType select").select2();
    		$('.s-list select').on('change',function(){
            	//alert(123);
            	var type = $(this).find("option:selected").attr("fType"); 
                var value = $(this).find("option:selected").attr("fValue"); 
                toExcute1(type,value);
//                var 
//                    // 当前对象
//                    _this = $(this),
//                    // 是否被选中
//                    active = _this.hasClass('btn-success');
//
//                if ( !active ) {
//                    _this.addClass('btn-success').siblings('a').removeClass('btn-success');
//                }
               /*  var type = $(this).attr("fType"); 
                var value = $(this).attr("fValue"); 
                toExcute1(type,value); */
                search();
            })
    		
	    	search();
    	})
    	function toExcute1(key,id){
    		//alert(key);
    		//var key = $(this).attr("fType");
    		if("itemOne"==key){
            $("#itemTwo").find("option:gt(0)").remove();
            $("#term").find("option:gt(0)").remove();
            $("#classType").find("select").find("option").remove(); 
    			$("#itemOneId").val(id);
    			var itemOneId = $("#itemOneId").val();
    			//根据学科查出所对应的学科小类
    			$.ajax({
	    			url:rootPath+"/fee/findSecondItem",
	    			type:"post",
	    			data:{
	    				"itemOneId":itemOneId
	    			},
	    			success:function(json){
	    				//alert(json.data);
	    				var jsonData=json;
	    				for(var i=0;i<jsonData.length;i++){
	    					$("#itemTwo").find("select").append('<option name="itemTwo" ftype="itemTwo" fValue="'+jsonData[i].id+'">'+jsonData[i].itemName +'</option>');
	    				}
	    				
	    				
	    			}
	    		})
	    		//根据学科查出所对应的考期
	    		$.ajax({
	    			url:rootPath+"/fee/findTerm",
	    			type:"post",
	    			data:{
	    				"itemOneId":itemOneId
	    			},
	    			success:function(json){
	    				var jsonData=json;
	    				for(var i=0;i<jsonData.length;i++){
	    					$("#term").find("select").append('<option name="term" ftype="term" fValue="'+jsonData[i].id+'">'+jsonData[i].termName +'</option>');
	    				}
	    			}
	    		})
	    		//根据学科查出所对应的班型
	    		$.ajax({
	    			url:rootPath+"/fee/findClassType",
	    			type:"post",
	    			data:{
	    				"itemOneId":itemOneId
	    			},
	    			success:function(json){
	    				$("#classType").find("select").append('<option name="classType" ftype="classType" fValue="">全部</option>');
	    				var jsonData=json;
	    				for(var i=0;i<jsonData.length;i++){
	    					$("#classType").find("select").append('<option name="classType" ftype="classType" fValue="'+jsonData[i].id+'">'+jsonData[i].name +'</option>');
	    				}
	    				//search();
	    			}
	    		})
	    		
    		}
    		if("itemTwo"==key){
    			//search();
    			$("#classType").find("select").find("option").remove();
    			$("#itemSecondId").val(id);
    			var itemOneId = $("#itemOneId").val();
    			var itemSecondId = $("#itemSecondId").val();
    			//根据学科小类查出所对应的一二级班型
    			$.ajax({
	    			url:rootPath+"/fee/findClassType",
	    			type:"post",
	    			data:{
	    				"itemOneId":itemOneId,
	    				"itemSecondId":itemSecondId
	    			},
	    			success:function(json){
	    				$("#classType").find("select").append('<option name="classType" ftype="classType" fValue="">全部</option>');
	    				var jsonData=json;
	    				for(var i=0;i<jsonData.length;i++){
	    					$("#classType").find("select").append('<option name="classType" ftype="classType" fValue="'+jsonData[i].id+'">'+jsonData[i].name +'</option>');
	    				}
	    			}
	    		})
    		}
    		
    	}
    	function search(pageNo){
    		var itemOneId=$("#itemOne").find("select").find("option:selected").attr("fValue");
			var itemSecondId=$("#itemTwo").find("select").find("option:selected").attr("fValue");
			var classTypeId=$("#classType").find("select").find("option:selected").attr("fValue");
			var examTermId=$("#term").find("select").find("option:selected").attr("fValue");
    		$.ajax({
    			url:rootPath+"/fee/search",
    			type:"post",
    			data:{
    				"itemOneId":itemOneId,
    				"itemSecondId":itemSecondId,
    				"classTypeId":classTypeId,
    				"examTermId":examTermId,
    				"pageNo":pageNo
    			},
    			success:function(html){
    				$("#message").html(html);
    			}
    			
    		})
    	}
    	function toSearch(){
    		$("#tip").attr("style","display:none");
    		var mobile = $("#mobile").val();
    		$.ajax({
    			url:rootPath+"/fee/search",
    			type:"post",
    			data:{
    				"mobile":mobile
    			},
    			success:function(data){
    			
    				 if(data==null||data==""){
    					$("#tip").attr("style","display:block");
    				}else{
    					$.ajax({
							url:rootPath+"/fee/search",
							type:"post",
							data:{
								"mobile":mobile								
							},
							success:function(html){
								$("#message").html(html);
							}
    					})
    				}  
    					
    			},
    			
    		})
    		
    	}