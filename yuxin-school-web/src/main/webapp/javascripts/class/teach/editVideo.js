$(function(){
	//alert("${moduleType}");
	$(".bt1").bind("click",function(){
		$(this).parent().find(".btn-success").removeClass("btn-success");
		$(this).addClass("btn-success");
		$("#method1").val($(this).attr("fValue"));
		$("#method").val($(this).html());
		if($(this).attr("fValue")=='TEACH_METHOD_VIDEO'){
			$("#saveButton").html("下一步");
		}else{
			$("#saveButton").html("保存");
		}
		
	})
	$(".bt2").bind("click",function(){
		$(this).parent().find(".btn-success").removeClass("btn-success");
		$(this).addClass("btn-success");
		$("#method1").val($(this).attr("fValue"));
		$("#addForm").attr("action",rootPath+"/classModule/toAddModule2");
		$("#addForm")[0].submit();
	})
	var teachMethod = $("#teachMethod").val();
	if(teachMethod=='TEACH_METHOD_VIDEO'){
		$("#saveButton").html("下一步");
	}
})

function save(){
	if(!$("#addForm1").valid()){
		
		return;
	}
	var form = $("#addForm1").serialize();
	$.ajax({
		url:rootPath+"/classModule/saveModule",
		type:"post",
		data:form,
		success:function(data){
			$("#moduleId").val(data);
			//alert($("#moduleId").val());
			$.msg("保存成功!");
			$("#addForm1")[0].submit();
		}
	})
}