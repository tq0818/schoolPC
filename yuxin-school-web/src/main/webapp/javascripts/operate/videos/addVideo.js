$(function(){
	var loadOneItemId = $("#oneItem").children("option:eq(0)").val();
	loadSecItem(loadOneItemId);
	$("#oneItem").change(function(){
		var itemId = $(this).val();
		loadSecItem(itemId);
	});
	uploadFlash();
});


function loadSecItem(itemId){
	$.ajax({
		url : rootPath+"/video/findAddVideoItemByOneId",
		type : "post",
		data : {"oneItemId":itemId},
		success : function(data){
			$("#secItem").html("");
			$("#secItem").html(data);
		}
	});
}
