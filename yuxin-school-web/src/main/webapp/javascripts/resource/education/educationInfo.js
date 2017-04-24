$(function(){
	var cookieStatus = $.cookie('teaType'); // 读取 cookie 
	if(cookieStatus){
		
		if(cookieStatus == 'PERSON_MANAGER'){
			$(".manager").addClass("btn-success").siblings(".tType").removeClass("btn-success");
			$("#teaType").val("PERSON_MANAGER");
		}
		if(cookieStatus == 'PERSON_WAITER'){
			$(".waiter").addClass("btn-success").siblings(".tType").removeClass("btn-success");
			$("#teaType").val("PERSON_WAITER");
		}
		if(cookieStatus == "PERSON_ASSISTANT"){
			$(".assistant").addClass("btn-success").siblings(".tType").removeClass("btn-success");
			$("#teaType").val("PERSON_ASSISTANT");
		}
		loadInfo(cookieStatus,null,null,1);
	}else{
		loadInfo("all",null,null,1);
	}
	
	$(".tType").click(function(){
		addClass(this);
		var teacherType = $(this).attr("typeStatus");
		$("#teaType").val(teacherType);
		loadInfo(teacherType,null,null,1);
	});
	
	//添加教务
	$(".add").click(function(){
		var teaType = $("#teaType").val();
		if(!teaType || teaType == 'all'){
			teaType = "PERSON_MANAGER";
		}
		location.href=rootPath+"/sysConfigTeacher/addEducation/-1/"+teaType+"";
	});
	
	//搜索
	$("#btnSearch").click(function(){
		var condition = $.trim($("#condition").val());
		var teaType = $("#teaType").val();
		if(condition.length == '11'){
			loadInfo(teaType,condition,null,1);
		}else{
			loadInfo(teaType,null,condition,1);
		}
	});
});
function loadInfo(teacherType,mobile,name,pageNo){
	var param;
	if(teacherType!=null && teacherType !=""){
		param += "&teacherType="+teacherType;
	}
	if(mobile!=null && mobile !=""){
		param += "&mobile="+mobile;
	}
	if(name!=null && name !=""){
		param += "&name="+name;
	}
	param += "&page="+pageNo;
	$.ajax({
		url : rootPath+"/sysConfigTeacher/educationAjaxInfo",
		type : "post",
		data : param,
		beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $(".loading-bg").show();
        },
		success : function(data){
			$("#info").html(data);
			
			//窗体加载时隐藏详细信息
			$(".detail").hide();
			//点击更多显示详细信息
			$(".moreInfo").click(function(){
				var $this = $(this);
				$this.parent(".r-more").parent(".r-list-title").parent(".teacher").animate({height: '100%', opacity: 'hide'}, 'normal',function(){ $this.parent(".r-more").parent(".r-list-title").parent(".teacher").hide();});
				$this.parent(".r-more").parent(".r-list-title").parent(".teacher").next(".detail").animate({height: '100%', opacity: 'show'}, 'normal',function(){ $this.parent(".r-more").parent(".r-list-title").parent(".teacher").next(".detail").show();});
			});
			//点击收起，隐藏相应信息
			$(".pack").click(function(){
				var $this = $(this);
				$this.parent(".r-more").parent(".r-list-title").parent(".detail").animate({height: '100%', opacity: 'hide'}, 'normal',function(){$this.parent(".r-more").parent(".r-list-title").parent(".detail").hide();});
				$this.parent(".r-more").parent(".r-list-title").parent(".detail").prev(".teacher").animate({height: '100%', opacity: 'show'}, 'normal',function(){$this.parent(".r-more").parent(".r-list-title").parent(".detail").prev(".teacher").show();});
			});
			//点击删除
			$(".delTeacher").click(function(){
				var teacherId = $(this).attr("teacherId");
				$.ajax({
					url : rootPath+"/sysConfigTeacher/teahcerLesson",
					type : "post",
					data : {"teachers":teacherId},
					dataType : "json",
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(jsonData){
						if(jsonData.length>0){
							var info = "";
							$.each(jsonData,function(i,item){
								info+="<tr>" +
										"<td style='width: 268px;text-align: center;'>"+item.lessonName+"</td>" +
										"</tr>";
							});
							$.confirm("该教师还有以下课次未结束，是否继续删除？<table>"+info+"</table>",function(result){
								if(result){
									$.ajax({
										url : rootPath+"/sysConfigTeacher/update",
										type : "post",
										data : {"id":teacherId,"delFlag":1},
										success : function(data){
											if(data == "success"){
												$('<div class="c-fa">'+ "删除成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
													loadInfo($("#teaType").val(),null,null,1);
												});
											}
										}
									});
								}
							});
						}else{
							$.confirm("该教师没有日后的课次安排，是否继续删除？",function(result){
								if(result){
									$.ajax({
										url : rootPath+"/sysConfigTeacher/update",
										type : "post",
										data : {"id":teacherId,"delFlag":1},
										success : function(data){
											if(data == "success"){
												$('<div class="c-fa">'+ "删除成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
													loadInfo($("#teaType").val(),null,null,1);
												});
											}
										}
									});
								}
							});
						}
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			});
			//点击返回隐藏
			 $('.btn-cancel').on('click',function(){
		         $(this).parents('.add-subs-layer').fadeOut(200,function(){
		             $('.add-subs-layer-bg').fadeOut(200);
		         });
		     });
		},
        complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
	});
}
function addClass(obj) {
	$(".tType").each(function() {
		$(this).attr("class", "btn btn-mini btn-default tType");
	});
	$(obj).addClass("btn-success");
}