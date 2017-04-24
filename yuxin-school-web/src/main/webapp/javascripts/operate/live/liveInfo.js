
$(function(){
	time = [];
	
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
			var status = $(".btn-day.active").attr("data-time");
			var statuss = $(".btn-ing.active").attr("data-time");
			var name = $("#findNoLessonName").val();
			toLink(pageNo, status,statuss,name);
		}
	});

	$(".time").each(function(){
		var parent = $(this);
		var obj = $(this).find(".countdown");
		var date = $(this).find(".datetime").val();
		var index = $(this).find(".index").val();
		time[index]=setInterval(function(){
			showTime(index,parent,obj,date);
		},200);
	});
	
	$('.btn-sel').off('click').on('click',function(){
		var id = $(this).attr("data-id");
		var moduleId = $(this).attr("data-module");
		$.ajax({
			url:rootPath + "/classModule/selClass",
			type:"post",
			data:{"id":id,"classType":1},
			dataType:"html",
	        success: function(data) {
	        	$(".mainbackground").html("");
				$(".operate_live_heading").html("");
				$(".operate_live_heading").html(data);
				$(".operate_live_heading").show();
				$(".btn-sel").attr("class","btn btn-success operate_btn_30 btn-sel");
				$.ajax({
					url : rootPath + "/classModule/classDetail",
					type:"post",
					data:{"id":id,"moduleId":moduleId,"status":0},
					dataType:"html",
					beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			         },
					success:function(date){
						$(".mainbackground").html(date);
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			}
		});
	
	});
	
	$('.btn-update').off('click').on('click',function(){
		var id = $(this).attr("data-id");
		var moduleId = $(this).attr("data-module");
		$.ajax({
			url:rootPath + "/classModule/selClass",
			type:"post",
			data:{"id":id,"classType":1},
			dataType:"html",
	        success: function(data) {
	        	$(".mainbackground").html("");
				$(".operate_live_heading").html("");
				$(".operate_live_heading").html(data);
				$(".operate_live_heading").show();
				$(".btn-update").attr("class","btn btn-success operate_btn_30 btn-sel");
				$.ajax({
					url : rootPath + "/classModule/classDetail",
					type:"post",
					data:{"id":id,"moduleId":moduleId,"status":1},
					dataType:"html",
					beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			         },
					success:function(date){
						$(".mainbackground").html(date);
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			}
		});
	
	});
	
	$('.btn-up').off('click').on('click',function(){
		var curr = $(this);
		if($(this).attr("data-type") == ""){
			return false;
		}else{
			var w = window.open();
			//获得p参数
			$.ajax({
				url:rootPath +"/classModule/checkOut",
				type:"post",
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(data){
					$(".loading").hide();
		            $(".loading-bg").hide();
		            if(data.result == "success"){
						$.ajax({
							url:rootPath + "/classModule/getParam",
							type:"post",
							data:{"lessonId":curr.attr("data-id"),"types":curr.attr("data-type"),"classType":curr.attr("data-classType")},
							async: true,
							success:function(data){
								if(data.url == "timeno"){
									w.close();
					            	$('<div class="c-fa">'+ "正在进行直播课程的录制课程转码，请半个小时后再进入！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
										$(this).remove();});
								}else if(data.url == "timeno30"){
									w.close();
					            	$('<div class="c-fa">'+ "该课程正在建立手机端支持，30分钟后才能进入！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
										$(this).remove();});
								}else if(data.url == "" || data.url == null || data.url == "undefined"){
									w.close();
					            	$('<div class="c-fa">'+ "未上课或未录制，无法回看！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
										$(this).remove();});
								}else if(data.url == "error"){
									w.close();
					            	$('<div class="c-fa">'+ "回放地址还未生成！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
										$(this).remove();});
								}else{
									var pathurls = encrypt(data.url);
									w.location = rootPath + 
										"/classModule/liveroom?url=" 
										+ pathurls + "&types=" + data.types 
										+ "&isr=" + data.isr + "&auth=" 
										+ data.auth + "&liveClassType=" 
										+ data.liveClassType + "&isres=" 
										+ data.isres + "&custom=" + data.curr
										+ "&lessonId=" + curr.attr("data-id");
									/*+ data.curr*/
								}
							},
							complete:function(XMLHttpRequest,textStatus){
								$(".loading").hide();
					            $(".loading-bg").hide();
					        }
						});
		            }else if(data.result == "outTime"){
						w.close();
		            	$('<div class="c-fa">'+ "直播服务已经过期！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
							$(this).remove();});
						$(".loading").hide();
			            $(".loading-bg").hide();
		            }else if(data.result == "service"){
						w.close();
		            	$('<div class="c-fa">'+ "公司服务已到期，暂时无法上课！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
							$(this).remove();});
						$(".loading").hide();
			            $(".loading-bg").hide();
		            }
				}
			});
		}
	});
	
	$(".btn-addcha").off("click").on("click",function(){
		$(".add-classes").show();
		$(".add-classes-bg").show();
		$("#recordids").val($(this).data("id"));
		$("#lessonids").val($(this).data("lesid"));
		selCourse(1);
	});
	
	$(".btn-seach").off("click").on("click",function(){
		$(".place-list").html("");
		$(".paginationss").html("");
		$(".btn-ok").attr("disabled","disabled");
		$(".btn-del").attr("disabled","disabled");
		var t = $(".courseactive").data("types");
		if(t == 1){
			selCourse(1);
		}else if(t == 2){
			selIntercut(1);
		}
	});
	
	//下载聊天内容
	$(".btn-download").off("click").on("click",function(){
		var liveroom = $(this).data("room");
		var types = $(this).data("types");
		window.location.href = rootPath + "/classModule/downloadChat/" + liveroom + "/" + types;
	});
	
	//打开查看或者修改
	function getUrlParam (paraName) {
		var url = document.location.toString();
		var arrObj = url.split("?");
		if (arrObj.length > 1) {
			var arrPara = arrObj[1].split("&");
			var arr;
			for (var i = 0; i < arrPara.length; i++) {
				arr = arrPara[i].split("=");
				if (arr != null && arr[0] == paraName) {
					return arr[1];
				}
			}
			return "";
		}else {
			return "";
		}
	}
	var type = getUrlParam('t');
	if(type == 's'){
		$(".btn-sel").trigger('click');
	}
	if(type == 'm'){
		$(".btn-update").trigger('click');
	}
});