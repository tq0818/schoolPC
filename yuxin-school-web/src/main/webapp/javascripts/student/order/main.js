$(function(){
		$selectSubMenu('student_online_order');
		   $(".c-content").find("a").on("click",this,function(){
			   
			   $(this).parent().find(".btn-success").removeClass("btn-success");
			   $(this).addClass("btn-success");
			   var fType = $(this).attr("fType");
			   var fValue = $(this).attr("Value");
			   //如果是学科,则重新获取学科小类和班型
			   if("itemOne"==fType){
				   $("#two").html("");
				   $("#classType").html("");
				   var str='<a href="javascript:;" onclick="getClassType();" fType="itemSecond" class="btn btn-mini btn-default btn-success">全部</a>';
				   $("#two").append(str);
				   var str2='<a href="javascript:;" fType="classType" onclick="change();" class="btn btn-mini btn-default btn-success">全部</a>';
				   $("#classType").append(str2)
				   $.ajax({
					   url:rootPath+"/payOrder/getSecondItem",
					   type:"post",
					   data:{
						   "itemOneId":fValue
					   },
					   success:function(json){
						   for(var i=0;i<json.length;i++){
							   
							   var str1='<a href="javascript:;" onclick="getClassType('+json[i].id+');" fType="itemSecond" value="'+json[i].id+'" class="btn btn-mini btn-default">'+json[i].itemName+'</a>';
							   $("#two").append(str1);
						   }
					   }
				   })
				   $.ajax({
					   url:rootPath+"/payOrder/getClassType2",
					   type:"post",
					   data:{
						   "itemOneId":fValue
					   },
					   success:function(json){
						   for(var i=0;i<json.length;i++){
							   var str1='<a href="javascript:;" onclick="change('+json[i].id+');" fType="classType" value="'+json[i].id+'" class="btn btn-mini btn-default">'+json[i].name+'</a>';
							   $("#classType").append(str1);
						   }
					   }
				   })
			   }else if("itemSecond"==fType){
				   $("#classType").html("");
				   var itemOneId = $("#one").find(".btn-success").attr("value");
				   var itemSecondId = $("#two").find(".btn-success").attr("value");
				   $.ajax({
					   url:rootPath+"/payOrder/getClassType2",
					   type:"post",
					   data:{
						   "itemOneId":itemOneId,
						   "itemSecondId":itemSecondId
					   },
					   success:function(json){
						   var str1='<a href="javascript:;" fType="classType" value=""  class="btn btn-mini btn-default btn-success">全部</a>';
						   $("#classType").append(str1);
						   for(var i=0;i<json.length;i++){
							   
							   str1='<a href="javascript:;" fType="classType"  value="'+json[i].id+'" class="btn btn-mini btn-default">'+json[i].name+'</a>';
							   $("#classType").append(str1);
						   }
					   }
				   })
			   }
			   search(1);
		   })
		   search(1);
	   })
	   function search(pageNo){
		   $("#page").html("");
		   var itemOne=null;
		   var itemSecond=null;
		   var classType=null;
		   $(".table").find(".btn-success").each(function(i){
			  // alert(i);
			   var fType = $(this).attr("fType");
			   var value = $(this).attr("value");
			   if("itemOne"==fType){
				   itemOne=value;
			   }else if("itemSecond"==fType){
				   itemSecond=value;
			   }else if("classType"==fType){
				   classType=value;
			   }
		   })
		   $.ajax({
			   url:rootPath+"/studentPayMaster/getList",
			   type:"post",
			   data:{
				  "itemOneId":itemOne,   
				  "itemSecondId":itemSecond, 
				  "classTypeId":classType,
				  "page":pageNo
			   },
			   success:function(json){
				   console.log(json);
				   $("#message").html(json);
			   }
		   })
	   }
	   function getClassType(id){
		   if(!id){
			   $("#two").find(".btn-success").removeClass("btn-success"); 
			   $("#two").find("a").eq(0).addClass("btn-success");
			  // alert(456);
		   }
		   $("#two").find("[value="+id+"]").parent().find(".btn-success").removeClass("btn-success");
		   $("#two").find("[value="+id+"]").addClass("btn-success");
		   var itemOneId = $("#one").find(".btn-success").attr("value");
		   var itemSecondId = $("#two").find(".btn-success").attr("value");
		   $("#classType").html("");
		   var str2='<a href="javascript:;" fType="classType" onclick="change();" class="btn btn-mini btn-default btn-success">全部</a>';
		   $("#classType").append(str2)
		   $.ajax({
			   url:rootPath+"/payOrder/getClassType2",
			   type:"post",
			   data:{
				   "itemOneId":itemOneId,
				   "itemSecondId":itemSecondId
			   },
			   success:function(json){
				   //alert(json);
				   for(var i=0;i<json.length;i++){
					   
					   var str1='<a href="javascript:;" fType="classType" onclick="change('+json[i].id+')" value="'+json[i].id+'" class="btn btn-mini btn-default">'+json[i].name+'</a>';
					   $("#classType").append(str1);
				   }
			   }
		   })
		   search(1);
	   }
	   function change(id){
		   if(!id){
			   $("#classType").find(".btn-success").removeClass("btn-success"); 
			   $("#classType").find("a").eq(0).addClass("btn-success");
			  // alert(456);
		   }else{
			   $("#classType").find("[value="+id+"]").parent().find(".btn-success").removeClass("btn-success");
			   $("#classType").find("[value="+id+"]").addClass("btn-success"); 
		   }
		   
		   search(1);
	   }