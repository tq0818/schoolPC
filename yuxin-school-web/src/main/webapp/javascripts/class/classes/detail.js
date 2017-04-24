$(function(){
		$(".pagination").html("");
		$(".pagination").pagination($("#rowCount").val(), {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ($("#pageNo").val() - 1),
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : $("#pageSize").val(),
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				selDetail(pageNo);
			}
		});
	});
	$(function(){
		//允许招生
		$(".btn-stu").each(function(){
			$(this).unbind("click").click(function(){
				var id = $(this).attr("data-id");
				$(".layer-tips-content").find("p:eq(0)").html("").html("你确定要允许此课程的招生？");
				$(".layer-tips-content").find("p:eq(1)").html("").html("允许招生后，此课程将可以开始招生和学员报名。");
				$(".layer-tips-content").find("p:eq(2)").html("").html("允许招生后，在<a class='operate-link' href='javascript:;'>招生中</a>页面可以对此课程继续排课。");
				$(".layer-tips-btns").html("").html("<a href='javascript:;' class='btn btn-mini btn-success okBtn' data-id='" + id + "' data-status='MODULE_NO_ON_SALE'>确定</a><a href='javascript:;' class='btn btn-mini btn-default qxBtn'>取消</a>");
			});
		});
		//结束招生
		$(".btn-stop-sale").each(function(){
			$(this).unbind("click").click(function(){
				var id = $(this).attr("data-id");
				$(".layer-tips-content").find("p:eq(0)").html("").html("你确定要停止此课程的招生？");
				$(".layer-tips-content").find("p:eq(1)").html("").html("停止招生后，此课程将无法报名任何学员。");
				$(".layer-tips-content").find("p:eq(2)").html("").html("停止招生后，在<a class='operate-link' href='javascript:;'>招生结束</a>页面可以看到此课程信息。");
				$(".layer-tips-btns").html("").html("<a href='javascript:;' class='btn btn-mini btn-success okBtn' data-id='" + id + "' data-status='MODULE_NO_OFFLINE'>确定</a><a href='javascript:;' class='btn btn-mini btn-default qxBtn'>取消</a>");
			});
		});
		//结课
		$(".btn-stop-offline").each(function(){
			$(this).unbind("click").click(function(){
				var id = $(this).attr("data-id");
				$(".layer-tips-content").find("p:eq(0)").html("").html("你确定此课程已经上完？");
				$(".layer-tips-content").find("p:eq(1)").html("").html("");
				$(".layer-tips-content").find("p:eq(2)").html("").html("结课生后，在<a class='operate-link' href='javascript:;'>已结课</a>页面可以看到此课程信息。");
				$(".layer-tips-btns").html("").html("<a href='javascript:;' class='btn btn-mini btn-success okBtn' data-id='" + id + "' data-status='MODULE_NO_FINISH'>确定</a><a href='javascript:;' class='btn btn-mini btn-default qxBtn'>取消</a>");
			});
		});
		
		//点击排课
		/*$(".btn-cls").each(function(){
			$(this).unbind("click").click(function(){
				var id = $(this).attr("data-id");
				var objform = document.createElement("form");
				document.body.appendChild(objform);
				objform.action =rootPath +"/classModuleNo/add" ;
				objform.method = "post";
				
				var ids = document.createElement("input");
				ids.type = "hidden";
				objform.appendChild(ids);
				module.value = id;
				module.name = "id";
				
				objform.submit();
			});
		});*/
		
		/*点击允许招生-弹出询问框*/
		$(".allowAdmissions").click(function(){
			$(".allowAdmissionsTc").showTc();
		});
		
		//查看学员
		$(".btn-sel-stu").each(function(){
			$(this).unbind("click").click(function(){
				var id = $(this).attr("data-id");
				selStudent(1,id);
				$(".checkStudentTc").showTc();
			});
		});
	});
	function selStudent(pageNo,id){
		$.ajax({
			url:rootPath + "/student/selByNoId",
			type:"post",
			data:{"resourceId":id,"page":pageNo},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
			success:function(data){
				$(".check_student_bd").html("").html(data);
			},
			complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	        }
		});
	}