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
	
	$("#areaId").change(function(){
    	selSchool();
	});
	$("#schoolCode").change(function(){
    	selOneItem();
	});
	$("#firstItemCode").change(function(){
    	selTwoItem();
	});
	$("#secondItemCode").change(function(){
    	selThreeItem();
	});
});

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
	$.ajax({
        url:rootPath + "/branchSchool/battchUpOrDown",
		 type:"post",
		 data:{"type":type,"cIds":cIds},
		 dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
		 success:function(data){
			 if("success"==data.result){
				 $.msg("操作成功");
			 }else{
				 $.msg("操作失败");
			 }
			 classTypeDetail($("#pageNo").val());
		 },
         complete:function(XMLHttpRequest,textStatus){

             $(".loading").hide();
             $(".loading-bg").hide();
         }
	 });
}

//查询学校
function selSchool(){
	 $("#schoolCode").empty();
	 $("#schoolCode").append("<option value=\"\">请选择学校</option>");
	 var areaId =$("#areaId").val();
	 if(!areaId){
		 selOneItem();
		 return false;
	 }
	 $.ajax({
        url:rootPath + "/branchSchool/selSchool",
		 type:"post",
		 data:{"areaId":areaId},
		 dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
		 success:function(data){
			 $.each(data.school,function(index,item){
				 $("#schoolCode").append("<option value='" + item.itemCode + "'>" + item.itemValue + "</option>");
			 });
			 selOneItem();
		 },
         complete:function(XMLHttpRequest,textStatus){

             $(".loading").hide();
             $(".loading-bg").hide();
         }
	 });
}

//查询分类
function selOneItem(){
	 $("#firstItemCode").empty();
	 $("#firstItemCode").append("<option value=\"\"  data-code=''>请选择分类</option>");
	 var schoolCode =$("#schoolCode").val();
	 if(!schoolCode){
		 selTwoItem();
		 return false;
	 }else{
		 $.ajax({
		      url:rootPath + "/branchSchool/selOneItem",
				 type:"post",
				 data:{"schoolCode":schoolCode},
				 dataType:"json",
				 beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			     },
				 success:function(data){
					 $.each(data.oneItem,function(index,item){
						 $("#firstItemCode").append("<option value='" + item.id + "' data-code='"+item.itemCode+"'>" + item.itemName + "</option>");
					 });
					 selTwoItem();
				 },
                 complete:function(XMLHttpRequest,textStatus){

                     $(".loading").hide();
                     $(".loading-bg").hide();
                 }
			 });
	 }
	 
}

//查询学段
function selTwoItem(){
	 $("#secondItemCode").empty();
	 $("#secondItemCode").append("<option value='' data-code=''>请选择学段</option>");
	 var schoolCode =$("#schoolCode").val();
	 var oneItem =$("#firstItemCode").val();
	 if(!schoolCode||!oneItem){
		 selThreeItem();
		 return false;
	 }else{
		 $.ajax({
		        url:rootPath + "/branchSchool/selTwoItem",
				 type:"post",
				 data:{"schoolCode":schoolCode,"parentId":oneItem},
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
	 var schoolCode =$("#schoolCode").val();
	 var twoItem =$("#secondItemCode").val();
	 if(!schoolCode||!twoItem){
		 return false;
	 }else{
		 $.ajax({
		        url:rootPath + "/branchSchool/selThreeItem",
				 type:"post",
				 data:{"schoolCode":schoolCode,"parentId":twoItem},
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
		url : rootPath + "/branchSchool/classTypeDetail",
		type:"post",
		data:{"areaId":$("#areaId").val(),"name":$("#name").val(),"schoolCode":$("#schoolCode").val(),
			"firstItemCode":firstItemCode,"secondItemCode":secondItemCode,
			"thirdItemCode":thirdItemCode,"cddsStatus":$("#cddsStatus").val(),
			"startTime":$("#startTime").val(),"endTime":$("#endTime").val(),
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