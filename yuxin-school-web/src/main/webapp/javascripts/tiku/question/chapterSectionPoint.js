var categoryId;
var subId;
var chapterId;
var sectionId;
var pointId;
	$(function(){
		categoryId = $("#categoryId").val();
		subId = $("#subId").val();
		chapterId = $("#chapterId").val();
		sectionId = $("#sectionId").val();
		pointId = $("#pointId").val();
		selChapter(categoryId,subId);
		
		$("#chapter").change(function(){
			selSection($("#chapter").val());
		});
		$("#section").change(function(){
			selPoint($("#section").val());
		});
		
	});
	//查询章
	function selChapter(categoryId,subId){
		$.ajax({
			url:rootPath + "/question/selChapter",
			type:"post",
			data:{"categoryId":categoryId,"subId":subId},
			dataType:"json",
			success:function(data){
				$("#chapter").html("");
				$.each(data.msg,function(index,item){
					$("#chapter").append("<option value='" + item.id + "'>" + item.chapterName + "</option>");
				});
				if(chapterId != "" && chapterId != null){
					$("#chapter").val(chapterId);
					chapterId = "";
				}
				selSection($("#chapter").val());
			}
		});
	}
	//查询节
	function selSection(chapterId){
		$.ajax({
			url:rootPath + "/question/selSection",
			type:"post",
			data:{"chapterId":chapterId},
			dataType:"json",
			success:function(data){
				$("#section").html("");
				$.each(data.msg,function(index,item){
					$("#section").append("<option value='" + item.id + "'>" + item.sectionName + "</option>");
				});
				if(sectionId != "" && sectionId != null){
					$("#section").val(sectionId);
					sectionId = "";
				}
				selPoint($("#section").val());
			}
		});
	}
	
	//查考点
	function selPoint(sectionId){
		$.ajax({
			url:rootPath + "/question/selPoint",
			type:"post",
			data:{"sectionId":sectionId},
			dataType:"json",
			success:function(data){
				$("#exampoint").html("");
				$.each(data.msg,function(index,item){
					$("#exampoint").append("<option value='" + item.id + "'>" + item.pointName + "</option>");
				});
				if(pointId != null && pointId != ""){
					$("#exampoint").val(pointId);
					pointId = "";
				}
			}
		});
	}