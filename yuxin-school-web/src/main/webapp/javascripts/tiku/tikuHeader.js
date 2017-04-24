
(function($) {
	var Fuc = {
		init : function() {
			var id = $("#tikuId").val();
			$(".th").click(function(){
				Fuc.clickClass(this);
				var html = $(this).html();
				if(html == '试题'){
					var objform = document.createElement("form");
					document.body.appendChild(objform);
					objform.action = rootPath + "/question/show";
					objform.method = "post";
					
					var cateId = document.createElement("input");
					cateId.type = "hidden";
					objform.appendChild(cateId);
					cateId.value = id;
					cateId.name = "categoryId";
					
					objform.submit();
				}
				if(html == '试卷'){
					location.href = rootPath+"/tikuPaper/toTikuPaper/"+id;
				}
				if(html == '章节考点'){
					location.href = rootPath+"/tikuChapter/toChapter/"+id;
				}
				if(html == '科目管理'){
					location.href = rootPath+"/tikuSet/toTikuSubManage/"+id;
				}
			});
			//返回
			$("#cancle").click(function(){
				location.href = rootPath+"/tikuCategory/toTiku";
			});
			
			Fuc.getTikiName(id);
			
		},
		clickClass : function(obj){
			$(".th").each(function() {
				$(this).attr("class", "th");
			});
			$(obj).addClass("active");
		},
		getTikiName : function(id){
			$.ajax({
				url : rootPath+"/tikuCategory/getTikiName",
				type : "post",
				data : {"id":id},
				success : function(data){
					$("#tiku").html("");
					$("#tiku").html(data);
				}
			});
		}
	}
	$(document).ready(function() {
		Fuc.init();
	})
	window.Fuc = Fuc;
})(jQuery)