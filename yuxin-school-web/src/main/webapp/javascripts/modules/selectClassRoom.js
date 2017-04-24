//教室Id
var classRoomId;
//教室名称
var classRoomName;
$(function(){
	
	// 关闭弹层
	$('.add-classes').on('click','i.close,.btn-cancel',function(){
		$('.add-classes').fadeOut(200,function(){
			$('.add-classes-bg').fadeOut(200);
		});
		$(".addRoomArea").hide();
	});
	
	$(".editClassRoom").click(function(){
		$('.add-classes').fadeIn(200,function(){
			$('.add-classes-bg').fadeIn(200);
		});
		var lessonId = $(this).attr("lesson_id");
		
		//查询当前分校所有的校区
		selectAllCampus(lessonId);
	});
	
	//点击校区获取对应的教室
	$(".place").on('click','.btn-mini',function(){
		$(this).removeClass("btn-default").addClass("btn-success");
		$(this).siblings().removeClass("btn-success").addClass("btn-default");
		var campusId = $(this).attr("campusid");
		$("#campusId").val(campusId);
		selectAllClassRoomByCampusId(campusId);
	});
	
	//点击确定，给课次配置教室
	$('.add-classes').on('click','p .btn-success',function(){
		//判断当前有没有教室被选中
//		var classRoomId = $(".place-list .active").attr("classroomid");
		if(!classRoomId){
			$('<div class="c-fa">'+ "请选择教室！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return false;
		}
//		var classRoomName = $(".place-list .active").html();
		var id = $("#lessonId").val();
		
		var moduleNoId = "";
		var allSetClassRoom = $("#allSetClassRoom").prop("checked");
		//如果本期课次全部安排此老师被选中，则把班号ID传过去
		if(allSetClassRoom){
			moduleNoId = $("#moduleNoId").val();
		}
		
		$.ajax({
			url: rootPath+"/classModuleLesson/editClassRoomForLesson",
			type:"get",
			dataType: "json",
			data : {
				"id" : id,
				"classroomId" : classRoomId,
				"classroom" : classRoomName,
				"moduleNoId" : moduleNoId
			},
			success:function(data){
				if(data == "batchSuccess"){
					$('<div class="c-fa">'+ "所有课次的教室安排成功！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
					});
					$('.add-classes').fadeOut(200,function(){
						$('.add-classes-bg').fadeOut(200);
					});
					window.location.reload();
				}else if(data == "success"){
					$('<div class="c-fa">'+ "该课次教室安排成功！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
					});
					$('.add-classes').fadeOut(200,function(){
						$('.add-classes-bg').fadeOut(200);
					});
					window.location.reload();
				}else{
					$('<div class="c-fa">'+ "该课次教室安排失败！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
					});
				}
			},
		});
	
		
	});
	//点击添加教室显示信息
	$(".addClassRoom").click(function(){
		$(".addRoomArea").show();
	});
	//点击保存添加教室
	$(".insertRoom").click(function(){
		var roomName = $.trim($("#classRoomName").val());
		var roomAddress = $.trim($("#classRoomAddress").val());
		var campusId = $("#campusId").val();
		if(!roomName){
			$('<div class="c-fa">'+ "请输入教室名称" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return;
		}
		if(!roomAddress){
			$('<div class="c-fa">'+ "请输入教室地址" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return;
		}
		$.ajax({
			url: rootPath+"/sysConfigClassroom/addClassroom",
			type:"post",
			data:{"roomName":roomName,"address":roomAddress,"delType":0,"campusId":campusId},
			success:function(data){
				if(data =="success"){
					$('<div class="c-fa">'+ "添加成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
						$(".addRoomArea").hide();
						$("#classRoomName").val("");
						$("#classRoomAddress").val("");
						selectAllClassRoomByCampusId(campusId);
					});
				}
			},
		});
	});
	// 选择教室
	$('.place-list').on('click',"a.btn",function(){
			var _this = $(this),
				// 选中状态
				active = _this.hasClass('active');

			active?
				_this.removeClass('active'):
				_this.addClass('active').parent('li').siblings('li').find('a.btn').removeClass('active');
		});
	
});


//查询该管理下所有的校区，以及教师
function selectAllCampus(lessonId){
	$("#lessonId").val(lessonId);
	$.ajax({
		url: rootPath+"/sysConfigCampus/getCampusesJson",
		type:"post",
		dataType: "json",
		success:function(data){
			//清空原来的
			$("#campusList").html("");
			if(data.length == 0){
				$('#campusList').html("当前分校还没有校区哦！");
			}else{
				for(var i=0;i<data.length;i++){
					if(i == 0){
						$('#campusList').append("<a href='javascript:;' campusId='"+data[i].id+"' class='btn btn-success btn-mini'>"+data[i].campusName+"</a>");
						$("#campusId").val(data[i].id);
						selectAllClassRoomByCampusId(data[i].id);
					}else{
						$('#campusList').append("<a href='javascript:;' campusId='"+data[i].id+"' class='btn btn-default btn-mini'>"+data[i].campusName+"</a>");
					}
	       	 	 };
			}
		},
	});
}

//根据校区Id获取对应的教室
function selectAllClassRoomByCampusId(campusId){
	$.ajax({
		url: rootPath+"/sysConfigClassroom/getClassRoomByCampus/"+campusId,
		type:"post",
		dataType: "json",
		success:function(data){
			//清空原来的
			$("#classRoomList").html("");
			
			if(data.length == 0){
				$('#classRoomList').html("当前校区还没有教室哦！");
			}else{
				for(var i=0;i<data.length;i++){
					$('#classRoomList').append("<li><a href='javascript:;' classRoomId='"+data[i].id+"' class='btn btn-link'>"+data[i].roomName+"</a></li>");
	       	 	};
			}
			$(".btn-link").click(function(){
				classRoomId = $(this).attr("classroomid");
				classRoomName = $(this).html();
			});
		},
	});
}