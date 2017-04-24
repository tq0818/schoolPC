$(function(){
	//加载信息
	loadInfo();
	$(".tType").click(function(){
		addClass(this);
		var camStatus = $(this).attr("camStatus");
		$("#camStatus").val(camStatus);
		loadInfo(camStatus);
	});
	//添加校区
	$(".saveCampus").click(function(){
		$.ajax({
			url : rootPath+"/sysConfigCampus/checkUnique",
			type : "post",
			data : $("#addCampus").serialize(),
			success : function(data){
				if(data == "success"){
					$('<div class="c-fa">'+ "已存在相同编号和名称的校区，请勿重复添加" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					$("[name=campusName]").val("");
					$("[name=campusNo]").val("");
					return;
				}
				if(data == "fail"){
					$.ajax({
						url : rootPath+"/sysConfigCampus/addCampus",
						type : "post",
						data : $("#addCampus").serialize(),
						success : function(data){
							if(data == "success"){
								$('<div class="c-fa">'+ "保存成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								$('.add-school').fadeOut(200,function(){
									$('.add-subs-layer-bg').fadeOut(200);
						        });
								loadInfo($("#camStatus").val());
								$("[name=campusName]").val("");
								$("[name=campusNo]").val("");
							}
						}
					});
				}
			}
		});
		
	});
	//更新校区
	$(".updateCampus").click(function(){
		$.ajax({
			url : rootPath+"/sysConfigCampus/checkUnique",
			type : "post",
			data : $("#updateCampus").serialize(),
			success : function(data){
				if(data == "success"){
					$('<div class="c-fa">'+ "已存在相同编号和名称的校区，请勿重复添加" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					return;
				}
				if(data == "fail"){
					$.ajax({
						url : rootPath+"/sysConfigCampus/editCampus",
						type : "post",
						data : $("#updateCampus").serialize(),
						success : function(data){
							if(data == "success"){
								$('<div class="c-fa">'+ "保存成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								$('.edit-school').fadeOut(200,function(){
									$('.add-subs-layer-bg').fadeOut(200);
						        });
								loadInfo($("#camStatus").val());
							}
						}
					});
				}
			}
		});
		
	});
});

function addClass(obj) {
	$(".tType").each(function() {
		$(this).attr("class", "btn btn-mini btn-default tType");
	});
	$(obj).addClass("btn-success");
}

function loadInfo(camStatus){
	$.ajax({
		url : rootPath+"/sysConfigCampus/campusAjaxInfo",
		type : "post",
		data : {"status":camStatus},
		beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $(".loading-bg").show();
        },
		success : function(data){
			$("#ajaxInfo").html(data);
			//点击停用或启用
			$(".btn-stop-sc").click(function(){
				var $this = $(this);
				var campusId = $this.prev(".campusId").val();
				var campusName = $this.parent(".sc-part-right").prev(".sc-part-left").children(".xq").find(".sc-p-content").html();
				$("#campusName").html(campusName);
				if($this.html() == "停用"){
					$.ajax({
						url : rootPath+"/sysConfigCampus/changeCampusStatus",
						type : "post",
						data : {"id":campusId,"status":0},
						beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				        },
						success : function(data){
							if(data == "success"){
								$('<div class="c-fa">'+ "校区已停用" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								loadInfo($("#camStatus").val());
							}
							if(data == "has_campus"){
								$.ajax({
									url : rootPath+"/sysConfigCampus/findNoStopClassModelNo",
									type : "post",
									data : {"id":campusId},
									dataType : "json",
									beforeSend:function(XMLHttpRequest){
							            $(".loading").show();
							            $(".loading-bg").show();
							        },
									success : function(data){
										$("#stopInfo").html("");
										$.each(data,function(index,item){
		     								if(index > 4){
		     									$("#stopInfo").append("<tr><td align='center'>.........</td></tr>");
		     									return false;
		     								}
		     								$("#stopInfo").append('<tr><td><span class="h"><span class="h-title" style="width: 350px;">班号：'+item.name+'</span></span></td></tr>');
		     							});
									},
							        complete:function(XMLHttpRequest,textStatus){
										$(".loading").hide();
							            $(".loading-bg").hide();
							        }
								});
								$('.add-subs-layer-bg').fadeIn(200,function(){
				                    $('.stop-subs').fadeIn(200);
				                });
							}
						},
				        complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				        }
					});
					
				}
				if($this.html() == "启用"){
					$.ajax({
						url : rootPath+"/sysConfigCampus/changeCampusStatus",
						type : "post",
						data : {"id":campusId,"status":1},
						beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				        },
						success : function(data){
							if(data == "success"){
								$('<div class="c-fa">'+ "校区已启用" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								loadInfo($("#camStatus").val());
							}
							if(data == "has_campus"){
								$('.add-subs-layer-bg').fadeIn(200,function(){
				                    $('.stop-subs').fadeIn(200);
				                });
							}
						},
				        complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				        }
					});
				}
			});
			//点击编辑
			$(".editCampus").click(function(){
				var $this = $(this);
				var campusId = $this.prev(".campusId").val();
				var campusName = $this.next(".campusName").val();
				var campusNo = $this.next(".campusName").next(".campusNo").val();
				$('.add-subs-layer-bg').fadeIn(200,function(){
                    $('.edit-school').fadeIn(200);
                });
				$(".UCampusName").val(campusName);
				$(".UCampusNo").val(campusNo);
				$(".UCampusId").val(campusId);
			});
		},
        complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
	});
}