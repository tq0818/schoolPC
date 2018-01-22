$(function(){
	$(".smbar").children(".header").children(".navspace").find("li:eq(3)").find("a").addClass("active");
	
	var cookieStatus = $.cookie('itemIndex'); // 读取 cookie 
	if(cookieStatus){
		var itemOneId = $(".sc-select a").eq(cookieStatus).attr("itemId");
		queryPageByKeys(itemOneId,1);
	}else{
		var itemOneId = $(".sc-select a").eq(0).attr("itemId");
		queryPageByKeys(itemOneId,1);
	}
	
	$(".sc-select a").click(function(){
		var $this = $(this);
		var itemOneId  = $this.attr("itemId");
		$("#itemId").val(itemOneId);
		var teaName = $("#teacherName").val();
		queryPageByKeys(itemOneId,1,teaName);
	});
	
	$("#nameSearch").click(function(){
		var itemOneId = $("#itemId").val();
		var teaName = $("#teacherName").val();
		queryPageByKeys(itemOneId,1,teaName);
	});
	$(document).delegate(".sortSave","click",function(){
		

		
		  var id = $(this).attr("data-id");
		  var sortId = $("#"+id+"_sortId").val();
		  var type =  $(this).attr("data-type");
		  sortId =sortId.trim();
		 
		  var reg =/(^$)|(^[1-9][0-9]{0,1}$)/
		  if(!reg.test(sortId)){
			  $.msg("请输入正整数，并且数字范围在1-99");
			  return false;
		  }
		  
		  if(sortId.length==0){
			  sortId =0;
		  }
		  
		  $.ajax({
			  type:"post",
			  url:rootPath+"/sysConfigTeacher/updateSortId",
			  data:{id:id,companyId:$('#companyId').val(),sortId:sortId,type:type},
			  success:function(result){
				  $.msg(result);
				  var itemOneId = $("#itemId").val();
				  var teaName = $("#teacherName").val();
				  queryPageByKeys(itemOneId,1,teaName);
			  }
			  
		  });
	  });
});

//给当前的条件添加样式
function addClassForBtn(obj){
	$(obj).removeClass("btn-default").addClass("btn-success").siblings().removeClass("btn-success").addClass("btn-default");
	$(obj).parent().children().eq(0).removeClass("btn-default");
	$(".sc-select a").each(function(index){
		if($(this).hasClass("btn-success")){
			$.cookie('itemIndex',index,{ path: "/"}); // 存储 cookie 
		}
	});
	
}

//异步加载模块列表
function queryPageByKeys(itemId,pageNo,teaName){
	$(".sc-select a").each(function(i,item){
		var itemIdChild = $(item).attr("itemId");
		if(itemIdChild == itemId){
			addClassForBtn(item);
		}
	});
	var companyId= $("#companyId").val();
	var param = "";
	if(itemId){
		param+="&itemOneId="+itemId;
	}
	if(pageNo){
		param+="&page="+pageNo;
	}
	if(teaName){
		param+="&name="+teaName;
	}
	if(companyId){
		param+="&companyId="+companyId;
	}
	  $.ajax({ 
		  type: "post", 
		  url: rootPath+"/teacherManger/getTeacherList",
		  data:param,
		  beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		  success: function(result){
			  $("#teacherContent").html("");
			  $("#teacherContent").html(result);
			//点击删除
				$(".delTeacher").click(function(){
					var teacherId = $(this).attr("teacherId");
					$.ajax({
						url : rootPath+"/sysConfigTeacher/checkTeacherAuth",
						type : "post",
						data : {"teacherId":teacherId},
						success : function(data){
							if(data){
								$('<div class="c-fa">'+ "该教师拥有其他权限，不允许删除" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								return;
							}else{
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
																queryPageByKeys($("#itemId").val(),1);
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
																	queryPageByKeys($("#itemId").val(),1);
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
							}
						}
					});
					
				});
		  },
		  complete:function(XMLHttpRequest,textStatus){
			 $(".loading").hide();
             $(".loading-bg").hide();
	      },
	  });
}

function showOtherInfo(obj, teacherId){
	$(obj).parent().parent().siblings().each(function(){
		var className = $(this).attr("class");
		if(className.indexOf("show") > 0){
			$(this).attr("class","r-list-content clear hide");
		}else{
			$(this).attr("class","r-list-content clear show");
		}
	});
	var objStatus = $(obj).html();
	if(objStatus == "更多"){
		$(obj).html("收起");
		//展开老师课程，如果没有则去数据库加载，有则直接展示
//		var spanHtml = $(obj).parents('.teacher').find('div.r-list-content:last').find("span").html();
//		if(spanHtml != "授课信息"){
//			queryTeacherOfLessons(obj, teacherId);
//		}
	}else{
		$(obj).html("更多");
	}
}

//异步加载老师对应的课时信息
function queryTeacherOfLessons(obj, teacherId){
	  $.ajax({
		  type: "get", 
		  url: rootPath+"/sysConfigTeacher/toDetail/"+teacherId, 
		  beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		  success: function(result){
			  $(obj).parents('.teacher').append(result);
//			  $(obj).parents('.teacher').find('div.r-list-content:last').append();
		  },
		  complete:function(XMLHttpRequest,textStatus){
			 $(".loading").hide();
             $(".loading-bg").hide();
	      },
	  });
	  
	  
	  
}

