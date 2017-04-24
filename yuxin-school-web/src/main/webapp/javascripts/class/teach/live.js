$(function(){
	var teachMethod = $("#teachMethod").val();
	//alert(teachMethod);
	$("#"+teachMethod).addClass("btn-success");
	 $(".bt").bind("click",function(){
		if($(this).hasClass("btn-success")){
			$("#moduleType").val($(this).attr("fValue"));
		}else{
			$(this).parent().find(".btn-success").removeClass("btn-success");
			$(this).addClass("btn-success")
			var a=$(this).attr("fValue");
			$("#moduleType").val($(this).attr("fValue"));
		}
		search(1);
	});
	 //alert(teachMethod);
	 if(teachMethod=='TEACH_METHOD_VIDEO'){
		 //alert(123);
		 $(".arrange").html("编辑");
		 $(".arrange").attr("href",'javascript:void(0);');
		 $(".arrange").each(function(){
			 var id = $(this).attr("value");
		 $(this).attr("onclick","toEdit("+id+")");
		 })
		 
	 }
	 
	
	
})
function delet(id){
	$.ajax({
		url:rootPath+"/classModule/isUse",
		type:"post",
		data:{
			"id":id
		},
		success:function(data){
			if("yes"==data){
				$.alert("有在售的课程使用该课程单元，暂时不能删除!");
			}else{
				$.confirm(("删除后将不能再次启用，确认要删除该课程单元吗？"),function(isConfirm){
					if(isConfirm){
					$.ajax({
						url:rootPath+"/classModule/changeStatus",
						type:"post",
						data:{
							"moduleId":id
						},
						success:function(){
							$.alert("删除成功!");
							history.go(0);
						}
						
					})}else{
						return;
					}
				})
				
			}
		}
	})
	
}
function detail(id){
	$("#id").val(id);
	$("#detailForm")[0].submit();
}
function toEdit(id){
	$("#id1").val(id);
	//alert($("#addForm").serialize());

	//return;
	
	 $("#addForm")[0].submit();
}
