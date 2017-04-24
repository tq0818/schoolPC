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
 	  //点击修改课程设置
 	  $(".savebtn_list").on('click',function(){
 		  var $this=$(this);
 		  if($(this).text()=="保存"){
 			 var data={};
	    	 var id=$("#classPackageSetId").attr("ids");
	    	 var mark=$(this).attr("mark");
	    	 data.functionCode=$("#classPackageSetId").attr("mark");
	    	 data.status="1";
	    	 var value =$.trim($(this).prev().val());
	    	 if(value.length<=0||value.length>5){
	    		 $.msg("名称不能为空且不能超过5个字符");
	    		 return;
	    	 }
	    	 if("course"==mark){
	    		 data.column1=value;
	    	 }
	    	 if("stage"==mark){
	    		 data.column2=value;
	    	 }
			 data.id=id;
			 $.ajax({
		  			type: "post",
		  			url:  rootPath+"/sysNews/editCompanyFunctions",
		  			dataType : "json",
		  			data:data,
		  			success: function(jsonData){
		  				if(jsonData=="success"){
		  					$this.prev().attr("disabled","disabled");
		  					$.msg("保存成功");
		  					$this.text("编辑").next().css("display","none");
		  					loadData();
		  				}
		  			}
		  		});
 		  }else{
 			 $this.prev().removeAttr("disabled");
 			 $(this).text("保存");
 			 $(this).next().css("display","inline-block");
 		  }
 	  });
 	  //点击取消
 	 $(".cancle-chapterandlec").on('click',function(){
 		 var txt=$.trim($(this).attr("mark"));
 		 $(this).prev().text("编辑").prev().val(txt).attr("disabled","disabled");
 		 $(this).css("display","none");
 	 });
 	//点击返回
 	$(".hcancle").on('click',function(){
 		window.location.href=rootPath+"/company/companyService";
 	});
 	//点击列表
 	$("#menu_list").on('click','li.subentry',function(){
 		var url=$(this).attr("mark");
 		window.location.href=rootPath+url;
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
			if(jsonData){
				$.each(jsonData,function(i,item){
					if(item.functionCode == "CLASS_PACKAGE_SET"){
						$("#classPackageSetId").attr("ids",item.id);
						if(item.column1&&item.column1!=""){
							$("#showClassPackageName").attr("mark",item.column1);
							$("#printClassPackageName").val(item.column1);
						}
						if(item.column2&&item.column2!=""){
							$("#showClassStageName").attr("mark",item.column2);
							$("#printClassStageName").val(item.column2);
						}
					}
				});
			}else{
				$("#printClassPackageName").val("课程包");
				$("#printClassStageName").val("阶段");
			}
			
		},
		 complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	     }
	});
}
