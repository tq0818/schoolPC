var checT=null;
var checA=null;
$(function(){
	
	
	//从cookie中获取是否为面授
	var classTeachType = $("#classTeachType").val();
	if(classTeachType != 'TEACH_METHOD_FACE'){
		$(".steps .clear").find("li").each(function(){
			$(this).removeClass("step").addClass("step2");
		});
		var saveteacher = $(".nextClassRoom");
		$(saveteacher).html("保存，返回排课");
		$(saveteacher).removeClass("nextClassRoom").addClass("saveRoom");
	}
	
	// 关闭弹层
	$('.add-classes').on('click','i.close,.btn-cancel',function(){
		$('.add-classes').fadeOut(200,function(){
			$('.add-classes-bg').fadeOut(200);
			$(".insertArea").hide();
			$(".insertTArea").hide();
			$(".iconfont").removeClass("active");
//			$(".iconfont").html("&#xe609;");
		});
		
	});
	
	$(".editTeacher").click(function(){
		$('.add-classes').fadeIn(200,function(){
			$('.add-classes-bg').fadeIn(200);
		});
		var lessonId = $(this).attr("lesson_id");
		
		//显示全部老师
		findAllTeachers(lessonId,this);
//		//查询当前校区所有的老师
//		selectAllTeachers(lessonId, this);
		//查询当前校区所有的教务人员
		selectAllEducational(this);
		
	});
	
	//点击确定，给课次配置老师
	$('.add-classes').on('click','.btn-success',function(){
		
		//判断当前有没有老师被选中
		var teacherId = $("#teacherList .active").attr("teacherid");
		if(!teacherId){
			$('<div class="c-fa">'+ "请选择老师！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return false;
		}
		var teachersName = $("#teacherList .active").html();
		var assistants = $("#assistantsList .active").attr("assistantsId");
		if(!assistants){
			assistants = "";
		}
		
		var assistantsName = $("#assistantsList .active").html();
		var id = $("#lessonId").val();
		
		var moduleNoId = "";
		var allSetTeacher = $("#allSetTeacher").prop("checked");
		//如果本期课次全部安排此老师被选中，则把班号ID传过去
		if(allSetTeacher){
			moduleNoId = $("#moduleNoId").val();
		}
		
		$.ajax({
			url: rootPath+"/classModuleLesson/editTeacherForLesson",
			type:"get",
			dataType: "json",
			data : {
				"id" : id,
				"teachers" : teacherId,
				"assistants" : assistants,
				"teachersName" : teachersName,
				"assistantsName" : assistantsName,
				"moduleNoId" : moduleNoId
			},
			success:function(data){
				if(data == "batchSuccess"){
					$('<div class="c-fa">'+ "所有课次的老师更新成功！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
					});
					$('.add-classes').fadeOut(200,function(){
						$('.add-classes-bg').fadeOut(200);
					});
					window.location.reload();
				}else if(data == "success"){
					$('<div class="c-fa">'+ "该课次老师更新成功！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
					});
					$('.add-classes').fadeOut(200,function(){
						$('.add-classes-bg').fadeOut(200);
					});
					window.location.reload();
				}else{
					$('<div class="c-fa">'+ "该课次老师更新失败！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
					});
				}
			},
		});
	});
	
	//点击进入安排教室
	$(".nextClassRoom").click(function(){
		var classTypeIdFlag = $.cookie("classTypeIdFlag");
		var moduleNoId = $("#moduleNoId").val();
		window.location.href = rootPath+"/classModuleNo/editClassRoom/"+moduleNoId;
	});
	
	//点击返回排课页面
	$(".prevLesson").click(function(){
		var moduleNoId = $("#moduleNoId").val();
		window.location.href = rootPath+"/classModuleNo/moduleLessons/"+moduleNoId;
	});
	
	// 选择老师
	$('.place-list').on('click',"a.btn",function(){
			var _this = $(this),
				// 选中状态
				active = _this.hasClass('active');

			active?
				_this.removeClass('active'):
				_this.addClass('active').parent('li').siblings('li').find('a.btn').removeClass('active');
		});
	
	
	 //点击显示全部老师
	 $('.add-classes').on('click','small',function(){
         var 
             _this = $(this).find('i'),
             status = ['&#xe609;','&#xe60a;'],
             active = _this.hasClass('active');
         var lessonId = $("#lessonId").val();
         if ( !active ) {
             _this.addClass('active').html(status[1]);
             findAllTeachers();
         } else {
             _this.removeClass('active').html(status[0]);
             selectAllTeachers(lessonId, null);
         }
     });
	 //点击添加显示输入域
	 $(".addTL").click(function(){
		 $(".insertArea").attr("style","display:block;");
	 });
	 $(".addT").click(function(){
		 $(".insertTArea").attr("style","display:block;");
	 });
	 
	 $("#userName").blur(function(){
		 var userName = $("#userName").val();
			$.ajax({
				type : "post",
				data : {
					"userName" : userName
				},
//				async : false,
				url : rootPath + "/authPrivilege/checkUserName",
				success : function(data) {
					checT = data;
				}
			});
	 });
	 $("#userName").mouseleave(function(){
		 $("#userName").blur();
	 });
	 //添加教师
	 $(".insertTeacher").click(function(){
		 var userName = $.trim($("#userName").val());
		 var pwd = $.trim($("#pwd").val());
		 var tName = $.trim($("#teacherName").val());
		 var tMobile = $.trim($("#teacherMobile").val());
		 var moduleId = $("#moduleId").val();
		 var lessonId = $("#lessonId").val();
		 if(!userName){
				$('<div class="c-fa">登陆账号不能为空</div>').appendTo(
				'body').fadeIn(100).delay(1000).fadeOut(200,
				function() {
					$(this).remove();
				});
				return;
			}else{
			if(checT != 'true'){
				if(checT == '用户名不能为空'){
					$('<div class="c-fa">登陆账号不能为空</div>').appendTo(
					'body').fadeIn(100).delay(1000).fadeOut(200,
					function() {
						$(this).remove();
					});
				}else if(checT == '用户名不能为空'){
					$('<div class="c-fa">登陆账号不能为空</div>').appendTo(
					'body').fadeIn(100).delay(1000).fadeOut(200,
					function() {
						$(this).remove();
					});
				}else if(checT == '用户名已存在'){
					$('<div class="c-fa">登陆账号已存在</div>').appendTo(
					'body').fadeIn(100).delay(1000).fadeOut(200,
					function() {
						$(this).remove();
					});
				}else if(checT == '用户名只能以字母开头并由数字0-9，字母（a-z，A-Z）和下划线_组成'){
					$('<div class="c-fa">登陆账号只能以字母开头并由数字0-9，字母（a-z，A-Z）和下划线_组成</div>').appendTo(
					'body').fadeIn(100).delay(1000).fadeOut(200,
					function() {
						$(this).remove();
					});
				}
				return;
			}
			}
		 if(!pwd){
			 $('<div class="c-fa">'+ "请输入密码" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			 return;
		 }
		 if(!tName){
			 $('<div class="c-fa">'+ "请输入教师姓名" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			 return;
		 }
		 if(!tMobile){
			 $('<div class="c-fa">'+ "请输入教师手机号" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			 return;
		 }
		 $.ajax({
				url: rootPath+"/sysConfigTeacher/insertTAndTL",
				type:"post",
				beforeSend:function(){
					$(".insertTeacher ").attr("disabled",true);
				},
				data:{"name":tName,"mobile":tMobile,"moduleId":moduleId,"userName":userName,"pwd":pwd,"teaOrAdu":"tea"},
				success:function(data){
					if(data == "success"){
						$('<div class="c-fa">'+ "添加成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
//							$('.add-classes').fadeOut(200,function(){
//								$('.add-classes-bg').fadeOut(200);
//							});
							$("#teacherName").val("");
							$("#teacherMobile").val("");
							$("#userName").val("");
							$("#pwd").val("");
							$(".insertArea").attr("style","display:none;");
							selectAllTeachers(lessonId, null);
							$(".insertTeacher ").attr("disabled",false);
						});
						
					}else if(data == "error"){
						$.msg("此手机号已被使用");
						$(".insertTeacher ").attr("disabled",false);
					}
				},
			});
	 });
	 
	 
	 
	 $("#userNameA").blur(function(){
		 var userName = $("#userNameA").val();
			$.ajax({
				type : "post",
				data : {
					"userName" : userName
				},
//				async : false,
				url : rootPath + "/authPrivilege/checkUserName",
				success : function(data) {
					checT = data;
				}
			});
	 });
	 $("#userNameA").mouseleave(function(){
		 $("#userNameA").blur();
	 });
	 //添加教务
	 $(".insertTTeacher").click(function(){
		 var userName = $.trim($("#userNameA").val());
		 var pwd = $.trim($("#pwdA").val());
		 var tName = $.trim($("#teacherTName").val());
		 var tMobile = $.trim($("#teacherTMobile").val());
		 var lessonId = $("#lessonId").val();
		 if(!userName){
				$('<div class="c-fa">登陆账号不能为空</div>').appendTo(
				'body').fadeIn(100).delay(1000).fadeOut(200,
				function() {
					$(this).remove();
				});
				return;
			}else{
			if(checT != 'true'){
				if(checT == '用户名不能为空'){
					$('<div class="c-fa">登陆账号不能为空</div>').appendTo(
					'body').fadeIn(100).delay(1000).fadeOut(200,
					function() {
						$(this).remove();
					});
				}else if(checT == '用户名不能为空'){
					$('<div class="c-fa">登陆账号不能为空</div>').appendTo(
					'body').fadeIn(100).delay(1000).fadeOut(200,
					function() {
						$(this).remove();
					});
				}else if(checT == '用户名已存在'){
					$('<div class="c-fa">登陆账号已存在</div>').appendTo(
					'body').fadeIn(100).delay(1000).fadeOut(200,
					function() {
						$(this).remove();
					});
				}else if(checT == '用户名只能以字母开头并由数字0-9，字母（a-z，A-Z）和下划线_组成'){
					$('<div class="c-fa">登陆账号只能以字母开头并由数字0-9，字母（a-z，A-Z）和下划线_组成</div>').appendTo(
					'body').fadeIn(100).delay(1000).fadeOut(200,
					function() {
						$(this).remove();
					});
				}
				return;
			}
			}
		 if(!tName){
			 $('<div class="c-fa">'+ "请输入教务人员姓名" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			 return;
		 }
		 if(!tMobile){
			 $('<div class="c-fa">'+ "请输入教务人员手机号" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			 return;
		 }
		 $.ajax({
				url: rootPath+"/sysConfigTeacher/insertT",
				type:"post",
				beforeSend:function(){
					$(".insertTTeacher").off("click");
				},
				data:{"name":tName,"mobile":tMobile,"userName":userName,"pwd":pwd,"teaOrAdu":"adu"},
				success:function(data){
					if(data == "success"){
						$('<div class="c-fa">'+ "添加成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
//							$('.add-classes').fadeOut(200,function(){
//								$('.add-classes-bg').fadeOut(200);
//							});
							$("#teacherTName").val("");
							$("#teacherTMobile").val("");
							$("#userNameA").val("");
							$("#pwdA").val("");
							$(".insertTArea").attr("style","display:none;");
							selectAllEducational(null);
						});
						
					}
				},
			});
	 });
});

//查询该管理员所属公司及校区下的所有老师
function findAllTeachers(lessonId, obj){
	if(lessonId){
		$("#lessonId").val(lessonId);
	}
	var teacherName = $(obj).attr("lesson_teacher");
	$.ajax({
		url: rootPath+"/sysConfigTeacher/PERSON_TEACHER",
		type:"get",
		dataType: "json",
		success:function(data){
			//清空原来的
			$("#teacherList").html("");
			
			for(var i=0;i<data.length;i++){
				if(teacherName == data[i].name){
					$('#teacherList').append("<li><a href='javascript:;' teacherId='"+data[i].id+"' class='btn btn-link active'>"+data[i].name+"</a></li>");
				}else{
					$('#teacherList').append("<li><a href='javascript:;' teacherId='"+data[i].id+"' class='btn btn-link'>"+data[i].name+"</a></li>");
				}
       	 	 };
		},
	});
}

//查询该管理员所属校区的所有的老师
function selectAllTeachers(lessonId, obj){
	var moduleId = $("#moduleId").val();
	$("#lessonId").val(lessonId);
	var teacherName = $(obj).attr("lesson_teacher");
	
	$.ajax({
		url: rootPath+"/sysConfigTeacher/"+moduleId+"/PERSON_TEACHER",
		type:"get",
		dataType: "json",
		success:function(data){
			//清空原来的
			$("#teacherList").html("");
			
			for(var i=0;i<data.length;i++){
				if(teacherName == data[i].name){
					$('#teacherList').append("<li><a href='javascript:;' teacherId='"+data[i].id+"' class='btn btn-link active'>"+data[i].name+"</a></li>");
				}else{
					$('#teacherList').append("<li><a href='javascript:;' teacherId='"+data[i].id+"' class='btn btn-link'>"+data[i].name+"</a></li>");
				}
       	 	 };
		},
	});
}

//查询该管理员所属校区的所有的教务人员
function selectAllEducational(obj){
	var moduleId = $("#moduleId").val();
	var educationalName = $(obj).attr("lesson_educational");
	$.ajax({
		url: rootPath+"/sysConfigTeacher/"+moduleId+"/PERSON_ASSISTANT",
		type:"get",
		dataType: "json",
		success:function(data){
			//清空原来的
			$("#assistantsList").html("");
			
			for(var i=0;i<data.length;i++){
				if(educationalName == data[i].name){
					$('#assistantsList').append("<li><a href='javascript:;'  assistantsId='"+data[i].id+"' class='btn btn-link active'>"+data[i].name+"</a></li>");
				}else{
					$('#assistantsList').append("<li><a href='javascript:;'  assistantsId='"+data[i].id+"' class='btn btn-link'>"+data[i].name+"</a></li>");
				}
				
       	 	 };
		},
	});
}