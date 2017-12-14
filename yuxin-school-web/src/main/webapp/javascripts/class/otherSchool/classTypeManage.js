$(function(){
	classTypeDetail(1);
	$(".searchContents").click(function(){
		classTypeDetail(1);
	});

//  批量上架
  $('.batchesShelves').click(function(){
      $.confirm("是否批量上架？",function(s){
          if(s==true){
        	  battchUpOrDown(1);
          }
      });
  });
//  批量下架
  $('.batches').click(function(){
      $.confirm("是否批量下架？",function(s){
          if(s==true){
        	  battchUpOrDown(0);
          }
      });
  });
	
	$("#firstItemCode").change(function(){
    	selTwoItem();
	});
	$("#secondItemCode").change(function(){
    	selThreeItem();
	});
});


function saleOnOrStop(obj){
	   var id=$(obj).attr("data_id");
	   var data_type=$(obj).attr("data_type");
	   var cIds=new Array();
	   var type;
	   var publishStatus="";
	   var str="";
	   if(0==data_type){
		   type=1;
		   publishStatus="CLASS_ON_SALE";
		   str="上架";
	   }else{
		   type=0; 
	   	   publishStatus="CLASS_STOP_SALE";
		   str="下架";
	   }
	   cIds[0]=id;
		$.ajax({
	        url:rootPath + "/otherSchool/battchUpOrDown",
			 type:"post",
			 data:{"type":type,"publishStatus":publishStatus,"cIds":cIds},
			 dataType:"json",
			 beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		     },
			 success:function(data){
				 if("success"==data.result){
					 $.msg(str+"操作成功");
					 $(obj).attr("data_type",type);
				 }else{
					 $.msg(str+"操作失败");
				 }
				 classTypeDetail($("#pageNo").val());
			 },
	         complete:function(XMLHttpRequest,textStatus){
	             $(".loading").hide();
	             $(".loading-bg").hide();
	         }
		 });
}

function  battchUpOrDown(type){
	var cIds=new Array();
	var i=0;
	$(".signUpMany:checked").each(function(){
		cIds[i++]=$(this).val();
	});
	if(null==cIds||cIds.length<1){
		$.msg("至少选择一条记录");
		return false;
	}
	  var publishStatus="";
	  var str="";
	  if(1==type){		  
		   publishStatus="CLASS_ON_SALE";
		   str="上架";
	   }else{
	   	   publishStatus="CLASS_STOP_SALE";
		   str="下架";
	   }
	$.ajax({
        url:rootPath + "/otherSchool/battchUpOrDown",
		 type:"post",
		 data:{"type":type,"cIds":cIds,"publishStatus":publishStatus},
		 dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
		 success:function(data){
			 if("success"==data.result){
				 $.msg(str+"操作成功");
			 }else{
				 $.msg(str+"操作失败");
			 }
			 classTypeDetail($("#pageNo").val());
		 },
         complete:function(XMLHttpRequest,textStatus){

             $(".loading").hide();
             $(".loading-bg").hide();
         }
	 });
}

//查询学段
function selTwoItem(){
	 $("#secondItemCode").empty();
	 $("#secondItemCode").append("<option value='' data-code=''>学段</option>");
	 var oneItem =$("#firstItemCode").val();
	 if(!oneItem){
		 selThreeItem();
		 return false;
	 }else{
		 $.ajax({
		        url:rootPath + "/otherSchool/selTwoItem",
				 type:"post",
				 data:{"parentId":oneItem},
				 dataType:"json",
					beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			         },
				   success:function(data){
					 $.each(data.towItem,function(index,item){
						 $("#secondItemCode").append("<option value='" + item.id + "' data-code='"+item.itemCode+"'>" + item.itemName + "</option>");
					 });
		            selThreeItem($("#secondItemCode").val());
				 },
                 complete:function(XMLHttpRequest,textStatus){

                     $(".loading").hide();
                     $(".loading-bg").hide();
                 }
			 }); 
	 }
	 
}

//查询学科
function selThreeItem(twoItem){
	$("#thirdItemCode").empty();
	 $("#thirdItemCode").append("<option value=''  data-code=''>请选择学科</option>");
	 var twoItem =$("#secondItemCode").val();
	 if(!twoItem){
		 return false;
	 }else{
		 $.ajax({
		        url:rootPath + "/otherSchool/selThreeItem",
				 type:"post",
				 data:{"parentId":twoItem},
				 dataType:"json",
					beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			         },
				 success:function(data){
					 $.each(data.threeItem,function(index,item){
						 $("#thirdItemCode").append("<option value='" + item.id + "' data-code='"+item.itemCode+"'>" + item.itemName + "</option>");
					 });
				 },
                 complete:function(XMLHttpRequest,textStatus){
                     $(".loading").hide();
                     $(".loading-bg").hide();
                 }
			 });
	 }
}

function classTypeDetail(pageNo){
	var firstItemCode=$("#firstItemCode").find("option:selected").attr("data-code");
	var secondItemCode=$("#secondItemCode").find("option:selected").attr("data-code");
	var thirdItemCode=$("#thirdItemCode").find("option:selected").attr("data-code");
	$.ajax({
		url : rootPath + "/otherSchool/classTypeDetail",
		type:"post",
		data:{"areaId":$("#areaId").val(),"name":$("#name").val(),"firstItemCode":firstItemCode,"secondItemCode":secondItemCode,
			"thirdItemCode":thirdItemCode,"status":$("#status").val(),"startTime":$("#startTime").val(),"endTime":$("#endTime").val(),
			"page":pageNo,"pageSize":$.trim($("#pageSize").val())},
		dataType:"html",
		beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		success:function(data){
			$(".tbodyList").html(data);
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
		});
}