$(document).ready(function(){
	//加载数据
	loadData();
	 $('span')
     .on('click','i.iconfont',function(){
         var 
             // 开关图标  03为左侧块 04为右侧块
             s = ['&#xe603;','&#xe604;'],
             // 开关文字
             st = [' 已启用',' 已禁用'],
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
		 }else if(code=="COMPANY_COURSE_SET"){
			 $(".savechapterBtn").attr("disabled","disabled");
			 $(".savelectureBtn").attr("disabled","disabled");
		 }else if(code=="COMPANY_FUNCTION_COURSE"){
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
        		  					location.href=rootPath+"/classManage/manage_classno";
        		  				}
        		  			}
        		  	});
        		}else{
        			location.href=rootPath+"/classManage/manage_classno";
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
		}else if(code=="COMPANY_COURSE_SET"){
			$(".savechapterBtn").removeAttr("disabled");
			$(".savelectureBtn").removeAttr("disabled");
		}else if(code=="COMPANY_FUNCTION_COURSE"){
	       	 $.confirm("确定开启此操作吗?",function(b){
	     		if(b){
	     			 $.ajax({
	     		  			type: "post",
	     		  			url:  rootPath+"/sysNews/editCompanyFunctions",
	     		  			dataType : "json",
	     		  			data:data,
	     		  			success: function(jsonData){
	     		  				if(jsonData=="success"){
	     		  					location.href=rootPath+"/classManage/manage_classno";
	     		  					loadData();
	     		  				}
	     		  			}
	     		  	});
	     		}else{
	     			location.href=rootPath+"/classManage/manage_classno";
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
    	 var code =$('#onefunction').attr('mark');
         data.functionCode = code,
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
    	 var code =$('#twofunction').attr('mark');
         data.functionCode = code,
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
	 	  //点击修改章节
	 	  $(".savebtn_list").on('click',function(){
	 		  var sta=$(this).attr("disabled");
	 		  if(sta=="disabled"){
	 			  return;
	 		  }
	 		  if($(this).text()=="保存"){
	 			 var data={};
		    	 var id=$("#chapterLecfunction").attr("ids");
		    	 var mark=$(this).attr("mark");
		    	 var value =$.trim($(this).prev().val());
		    	 if(value.length<=0||value.length>5){
		    		 $.msg("此项不能为空且不能超过5个字符");
		    		 return;
		    	 }
		    	 if("chapter"==mark){
		    		 data.column1=value;
		    	 }
		    	 if("lecture"==mark){
		    		 data.column2=value;
		    	 }
				 data.id=id;
				 data.functionCode="COMPANY_COURSE_SET";
				 $.ajax({
			  			type: "post",
			  			url:  rootPath+"/sysNews/editCompanyFunctions",
			  			dataType : "json",
			  			data:data,
			  			success: function(jsonData){
			  				if(jsonData=="success"){
			  					$.msg("保存成功");
			  				}
			  			}
			  		});
	 			 $(this).text("修改").prev().css("display","none").prev().css("display","inline-block").text(value);
	 			 $(this).next().css("display","none");
	 		  }else{
	 			 $(this).text("保存").prev().css("display","inline-block").prev().css("display","none");
	 			 $(this).next().css("display","inline-block");
	 		  }
	 	  });
	 	  //点击取消
	 	 $(".cancle-chapterandlec").on('click',function(){
	 		 var txt=$.trim($(this).prev().prev().prev().text());
	 		 $(this).prev().text("修改").prev().val(txt).css("display","none").prev().css("display","inline-block");
	 		 $(this).css("display","none");
	 	 });
});


function loadData(){
	$.ajax({
		type: "post",
		url:  rootPath+"/sysNews/queryFunctions",
		beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $(".loading-bg").show();
        },
		success: function(jsonData){
			$.each(jsonData,function(i,item){
				if(item.functionCode == "COURSE_COMMENT"){
					$("#videofunction").attr("ids",item.id);
					if(item.status == "1"){
						$("#videofunction").html('<i class="iconfont open font_big">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}else if(item.functionCode == "COMPANY_FUNCTION_APPLY"){
					$("#onefunction").attr("ids",item.id);
					$("#messageContent").val(item.content);
					if(item.status == "1"){
						$("#addmessage").css("display","block");
						$("#onefunction").html('<i class="iconfont open font_big">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}else if(item.functionCode == "COMPANY_FUNCTION_BUY"){
					$("#twofunction").attr("ids",item.id);
					$("#newsContents").val(item.content);
					if(item.status == "1"){
						$("#addMessageModules").css("display","block");
						$("#twofunction").html('<i class="iconfont open font_big">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}else if(item.functionCode == "COMPANY_FUNCTION_COURSE"){
					$("#threefunction").attr("ids",item.id);
					if(item.status == "1"){
						$("#threefunction").html('<i class="iconfont open font_big">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}else if(item.functionCode == "COMPANY_COURSE_SET"){
					$("#chapterLecfunction").attr("ids",item.id);
					if(item.status == "1"){
						$(".savechapterBtn").removeAttr("disabled");
						$(".savelectureBtn").removeAttr("disabled");
						$("#chapterLecfunction").html('<i class="iconfont open font_big">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
					if(item.column1&&item.column1!=""){
						$("#showchapterName").text(item.column1);
						$("#printchapterName").val(item.column1);
					}
					if(item.column2&&item.column2!=""){
						$("#showlecName").text(item.column2);
						$("#printlecName").val(item.column2);
					}
				}else if(item.functionCode == "COURSE_THIRD_CATEGORY"){
					$("#courseLablefunction").attr("ids",item.id);
					if(item.status == "1"){
						$("#courseLablefunction").html('<i class="iconfont open font_big">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}else if(item.functionCode == "COURSE_SECOND_CATEGORY"){
					$("#secondLablefunction").attr("ids",item.id);
					if(item.status == "1"){
						$("#secondLablefunction").html('<i class="iconfont open font_big">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}else if(item.functionCode == "COURSE_BUY_SET"){
					$("#stuBuyfunction").attr("ids",item.id);
					if(item.status == "1"){
						$("#stuBuyfunction").html('<i class="iconfont open font_big">&#xe603;</i><span class="i open">&nbsp;&nbsp;已启用</span>');
					}
				}
			});
			
		},
		 complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	     }
	});
}
