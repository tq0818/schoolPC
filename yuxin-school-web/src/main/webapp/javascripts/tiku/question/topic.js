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
				selTopic(pageNo);
			}
		});
	});
	
	$(".l-title").find("img").attr("style","width:40px;height:40px;");

	$(".l-title").find("img").hover(function(e){
		//新建一个p标签来存放大图片，this.rel就是获取到a标签的大图片的路径，然后追加到body中
		$("body").append("<p id='bigimage' style='position:absolute;Z-index:1000;'><img id='bigIcon' src='"+ ($(this).attr("src")) +"'/></p>");
		//将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，然后以fadeIn的效果显示
		$("#bigimage").css({top:(e.pageY - 300 ),left:(e.pageX + 40 )}).fadeIn("fast");
		},function(){
		//移除新增的p标签
			$("#bigimage").remove();
	});
	$(".l-title").find("img").mousemove(function(e){ //绑定一个鼠标移动的事件
		//将鼠标的坐标和声明的x，y做运算并赋值给大图片绝对定位的坐标，这样大图片就能跟随图片而移动了
		$("#bigimage").css({top:(e.pageY - 300 ),left:(e.pageX + 40 )});
	});
	
	$(".btn-topic").click(function(){
		if($(this).attr("disabled") == "disabled"){
			return false;
		}
		var parent = $(this);
		if($(this).attr("data-btn") == "delete"){
			$.confirm("确定删除吗？",function(b){
				if(b){
					$.ajax({
						url : rootPath + "/question/delete",
						type:"post",
						data:{"id": parent.attr("data-qid")},
						dataType:"json",
						beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				        },
						success:function(data){
							$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
		        				$(this).remove();
								});
							selTopic(1);
						},
				        complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				        }
					});
				}
			});
		}else{
			var cateId = $("#categoryId").val();
			var objform = document.createElement("form");
			document.body.appendChild(objform);
			objform.action = rootPath + "/question/editQuestion";
			objform.method = "post";
			
			var btn = document.createElement("input");
			btn.type = "hidden";
			objform.appendChild(btn);
			btn.value = $(this).attr("data-btn");
			btn.name = "btn";
			
			var types = document.createElement("input");
			types.type = "hidden";
			objform.appendChild(types);
			types.value = $(".btn-primary.types").attr("data-type");
			types.name = "types";
			
			var id = document.createElement("input");
			id.type = "hidden";
			objform.appendChild(id);
			id.value = $(this).attr("data-qid");
			id.name = "id";

			var categoryId = document.createElement("input");
			categoryId.type = "hidden";
			objform.appendChild(categoryId);
			categoryId.value = cateId;
			categoryId.name = "categoryId";

			var subId = document.createElement("input");
			subId.type = "hidden";
			objform.appendChild(subId);
			subId.value = $(".btn-success.subject").attr("data-id");
			subId.name = "subId";
			
			var pageNo = document.createElement("input");
			pageNo.type = "hidden";
			objform.appendChild(pageNo);
			pageNo.value = $("#pageNo").val();
			pageNo.name = "pageNo";
			

			var status = document.createElement("input");
			status.type = "hidden";
			objform.appendChild(status);
			status.value = $(".btn-success.questionStatus").attr("data-status");
			status.name = "status";
			
			objform.submit();
		}
	});