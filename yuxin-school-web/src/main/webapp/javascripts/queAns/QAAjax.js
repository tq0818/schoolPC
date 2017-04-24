
//查询二级回复
function selTwoAns(id,parent){
	if(req != null){
		req.abort();
	}
	req = $.ajax({
		url : rootPath + "/questionanswermanager/selTwoAns",
		type:"post",
		data:{"id":id,"types":$("#types").val()},
		dataType:"html",
		success:function(data){
			parent.find(".two-content").html(data)
				.slideDown(200);
		}
	});
}