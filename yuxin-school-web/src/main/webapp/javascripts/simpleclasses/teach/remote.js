$(function(){
	var teachMethod = $("#teachMethod").val();
	$("#"+teachMethod).addClass("btn-success");
	 $(".bt").bind("click",function(){
		if($(this).hasClass("btn-success")){
			//$(this).removeClass("btn-success")
			$("#moduleType").val($(this).attr("fValue"));
		}else{
			$(this).parent().find(".btn-success").removeClass("btn-success");
			$(this).addClass("btn-success")
			var a=$(this).attr("fValue");
			//alert(a);
			$("#moduleType").val($(this).attr("fValue"));
		}
		search(1);
	})
	
	
	
})
function detail(id){
	$("#id").val(id);
	$("#detailForm")[0].submit();
}
function edit(id){
	$("#id").val(id);
	var str=rootPath+"/classModule/editRemote"
	$("#detailForm").attr("action",str);
	$("#detailForm")[0].submit();
}
