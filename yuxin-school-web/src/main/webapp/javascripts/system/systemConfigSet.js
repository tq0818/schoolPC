$(document).ready(function(){
	$selectSubMenu('system_config');
	//加载数据
	loadData();
	 $('span')
     .on('click','i.iconfont',function(){
         var 
             // 开关图标  03为左侧块 04为右侧块
             s = ['&#xe603;','&#xe604;'],
             // 开关文字
             st = ['启用','禁用'],
             // 获得开关实体
             o = $(this),
             flag,
             // 获得当前状态
             staus = o.hasClass('open');
       
         o
             [staus?'removeClass':'addClass']('open')
             [staus?'addClass':'removeClass']('close')
             .html(s[staus?1:0])
             .next('span.i').text(st[staus?1:0])
             [staus?'removeClass':'addClass']('open')
             [staus?'addClass':'removeClass']('close');
         
     if(staus){
    	 var data={};
    	 var code=o.parent().attr("mark"); 
    	 var id=o.parent().attr("ids");
    	 var name=o.parent().attr("con");
    	 data.id=id;
    	 data.functionCode=code;
    	 data.functionName=name;
		 data.status="0";
		 if(code=="COMPANY_FUNCTION_BUY"){
			 $("#addMessageModules").css("display","none");
			var value = $("#newsContents").val();
			data.content=value;
		 }else if(code=="COMPANY_FUNCTION_APPLY"){
			 $("#addmessage").css("display","none");
			 var value = $("#messageContent").val();
			 data.content=value;
		 }else{
        	 $.confirm("确定此关闭操作吗?",function(b){
        		if(b){
        			 $.ajax({
        		  			type: "post",
        		  			url:  rootPath+"/sysNews/editCompanyFunctions",
        		  			dataType : "json",
        		  			data:data,
        		  			success: function(jsonData){
        		  				if(jsonData=="success"){
        		  					loadData();
        		  					location.href=rootPath+"/sysNews/systemSet";
        		  				}
        		  			}
        		  	});
        		}else{
        			location.href=rootPath+"/sysNews/systemSet";
        			loadData();
        			return;
        		}
    		 }); 
        	 return;
		 }
    	 $.ajax({
  			type: "post",
  			url:  rootPath+"/sysNews/editCompanyFunctions",
  			dataType : "json",
  			data:data,
  			success: function(jsonData){
  				if(jsonData=="success"){
  					loadData();
  				}
  			}
  		});
     }else{
    	 var $this=$(this);
    	 var data={};
    	 var code=$this.parent().attr("mark"); 
    	 var id=$this.parent().attr("ids");
    	 var name=o.parent().attr("con");
    	 data.id=id;
    	 data.functionCode=code;
    	 data.functionName=name;
		 data.status="1";
		 if(code=="COMPANY_FUNCTION_BUY"){
			 $("#addMessageModules").css("display","block");
			var value = $("#newsContents").val();
			data.content=value;
		}else if(code=="COMPANY_FUNCTION_APPLY"){
			 $("#addmessage").css("display","none");
			 var value = $("#messageContent").val();
			 data.content=value;
		}else{
	       	 $.confirm("确定开启此操作吗?",function(b){
	     		if(b){
	     			 $.ajax({
	     		  			type: "post",
	     		  			url:  rootPath+"/sysNews/editCompanyFunctions",
	     		  			dataType : "json",
	     		  			data:data,
	     		  			success: function(jsonData){
	     		  				if(jsonData=="success"){
	     		  					location.href=rootPath+"/sysNews/systemSet";
	     		  					loadData();
	     		  				}
	     		  			}
	     		  	});
	     		}else{
	     			location.href=rootPath+"/sysNews/systemSet";
	     			loadData();
	     			return;
	     		}
	 		 }); 
	     	 return;
		 }
		 
    	 $.ajax({
  			type: "post",
  			url:  rootPath+"/sysNews/editCompanyFunctions",
  			dataType : "json",
  			data:data,
  			success: function(jsonData){
  				if(jsonData=="success"){
  					loadData();
  				}
  			}
  		});
     }
    });
	//保存短信模板内容
	 $(".saveMessageContent").on('click',function(){
		 var data={};
    	 var id=$("#onefunction").attr("ids");
    	 var value =$("#messageContent").val();
    	 data.content=value;

		 data.id=id;
		 $.ajax({
	  			type: "post",
	  			url:  rootPath+"/sysNews/editCompanyFunctions",
	  			dataType : "json",
	  			data:data,
	  			success: function(jsonData){
	  				if(jsonData=="success"){
	  					$.msg("保存成功");
	  					loadData();
	  				}
	  			}
	  		});
	 });
	 //保存站内信模板内容
	 $(".saveContent").on('click',function(){
		 var data={};
    	 var id=$("#twofunction").attr("ids");
    	 var value =$("#newsContents").val();
    	 data.content=value;

		 data.id=id;
		 $.ajax({
	  			type: "post",
	  			url:  rootPath+"/sysNews/editCompanyFunctions",
	  			dataType : "json",
	  			data:data,
	  			success: function(jsonData){
	  				if(jsonData=="success"){
	  					$.msg("保存成功");
	  					loadData();
	  				}
	  			}
	  		});
	 });
	 
});


function loadData(){
	$.ajax({
		type: "post",
		url:  rootPath+"/sysNews/queryFunctions",
		success: function(jsonData){
			//var count=jsonData.length;
			$.each(jsonData,function(i,item){
				if(item.functionCode == "COURSE_COMMENT"){
					$("#videofunction").attr("ids",item.id);
					if(item.status == "1"){
						$("#videofunction").html('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}else if(item.functionCode == "COMPANY_FUNCTION_APPLY"){
					$("#onefunction").attr("ids",item.id);
					$("#messageContent").val(item.content);
					if(item.status == "1"){
						$("#addmessage").css("display","block");
						$("#onefunction").html('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}else if(item.functionCode == "COMPANY_FUNCTION_BUY"){
					$("#twofunction").attr("ids",item.id);
					$("#newsContents").val(item.content);
					if(item.status == "1"){
						$("#addMessageModules").css("display","block");
						$("#twofunction").html('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}else if(item.functionCode == "COMPANY_FUNCTION_COURSE"){
					$("#threefunction").attr("ids",item.id);
					if(item.status == "1"){
						$("#threefunction").html('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}//else if(item.functionCode == "COMPANY_FUNCTION_TEACHER"){
//					$("#teacherfunction").attr("ids",item.id);
//					if(item.status == "1"){
//						$("#teacherfunction").html('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
//					}
//				}
			});
			
	
//			if(count>=3){
//				$.each(jsonData,function(i,item){
//					if(item.functionCode=="COMPANY_FUNCTION_APPLY"){
//						$("#onefunction").attr({"ids":item.id,"mark":item.functionCode});
//						$("#messageContent").val(item.content);
//						if(item.status=="1"){
//							$("#addmessage").css("display","block");
//							$("#onefunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//						}else{
//							$("#onefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//						}
//					}else if(item.functionCode=="COMPANY_FUNCTION_BUY"){
//						$("#twofunction").attr({"ids":item.id,"mark":item.functionCode});
//						$("#newsContents").val(item.content);
//						if(item.status=="1"){
//							 $("#addMessageModules").css("display","block");
//							$("#twofunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//						}else{
//							$("#twofunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//						}
//					}else{
//						$("#threefunction").attr({"ids":item.id,"mark":item.functionCode});
//						if(item.status=="1"){
//							$("#threefunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//						}else{
//							$("#threefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//						}
//					}
//				});
//			}else if(count==2){
//				var item1,item2;
//				$.each(jsonData,function(i,item){
//					if(i==0){
//						item1=item.functionCode;
//					}else{
//						item2=item.functionCode;
//					}
//				});
//				if(item1=="COMPANY_FUNCTION_APPLY"&&item2=="COMPANY_FUNCTION_BUY"||item1=="COMPANY_FUNCTION_BUY"&&item2=="COMPANY_FUNCTION_APPLY"){
//					$.each(jsonData,function(i,item){
//						if(item.functionCode=="COMPANY_FUNCTION_APPLY"){
//							$("#onefunction").attr({"ids":item.id,"mark":item.functionCode});
//							$("#messageContent").val(item.content);
//							if(item.status=="1"){
//								$("#addmessage").css("display","block");
//								$("#onefunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//							}else{
//								$("#onefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//							}
//						}else{
//							$("#twofunction").attr({"ids":item.id,"mark":item.functionCode});
//							$("#newsContents").val(item.content);
//							if(item.status=="1"){
//								 $("#addMessageModules").css("display","block");
//								$("#twofunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//							}else{
//								$("#twofunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//							}
//						}
//					});
//					$("#threefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//				}else if(item1=="COMPANY_FUNCTION_APPLY"&&item2=="COMPANY_FUNCTION_COURSE"||item1=="COMPANY_FUNCTION_COURSE"&&item2=="COMPANY_FUNCTION_APPLY"){
//					$.each(jsonData,function(i,item){
//						if(item.functionCode=="COMPANY_FUNCTION_APPLY"){
//							$("#onefunction").attr({"ids":item.id,"mark":item.functionCode});
//							$("#messageContent").val(item.content);
//							if(item.status=="1"){
//								$("#addmessage").css("display","block");
//								$("#onefunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//							}else{
//								$("#onefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//							}
//						}else{
//							$("#threefunction").attr({"ids":item.id,"mark":item.functionCode});
//							if(item.status=="1"){
//								$("#threefunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//							}else{
//								$("#threefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//							}
//						}
//					});
//					$("#twofunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//				}else{
//					$.each(jsonData,function(i,item){
//						if(item.functionCode=="COMPANY_FUNCTION_BUY"){
//							$("#twofunction").attr({"ids":item.id,"mark":item.functionCode});
//							$("#newsContents").val(item.content);
//							if(item.status=="1"){
//								 $("#addMessageModules").css("display","block");
//								$("#twofunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//							}else{
//								$("#twofunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//							}
//						}else{
//							$("#threefunction").attr({"ids":item.id,"mark":item.functionCode});
//							if(item.status=="1"){
//								$("#threefunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//							}else{
//								$("#threefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//							}
//						}
//					});
//					$("#onefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//				}
//			}else if(count==1){
//				var item1=jsonData[0].functionCode;
//				var item=jsonData[0];
//				if(item1=="COMPANY_FUNCTION_APPLY"){
//					$("#onefunction").attr({"ids":item.id,"mark":item.functionCode});
//					$("#messageContent").val(item.content);
//					if(item.status=="1"){
//						$("#addmessage").css("display","block");
//						$("#onefunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//					}else{
//						$("#onefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//					}
//					$("#twofunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//					$("#threefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//				}else if(item1=="COMPANY_FUNCTION_BUY"){
//					$("#twofunction").attr({"ids":item.id,"mark":item.functionCode});
//					$("#newsContents").val(item.content);
//					if(item.status=="1"){
//						 $("#addMessageModules").css("display","block");
//						$("#twofunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//					}else{
//						$("#twofunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//					}
//					$("#onefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//					$("#threefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//				}else{
//					$("#threefunction").attr({"ids":item.id,"mark":item.functionCode});
//					if(item.status=="1"){
//						$("#threefunction").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>')
//					}else{
//						$("#threefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">&nbsp;&nbsp;已禁用</span>');
//					}
//					$("#onefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//					$("#twofunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//				}
//			}else{
//				$("#onefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//				$("#twofunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//				$("#threefunction").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">已禁用</span>');
//			}
			
		}
	});
}
