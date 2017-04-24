var checT=null;
var chmoT=null;
var checA=null;
$(function(){
	//加载校区
	$.ajax({
		type : "post",
		url : rootPath + "/sysConfigCampus/schoolCampusList",
		dataType:'json',
		success : function(data) {
			var html="";
			$.each(data,function(i,item){
				html+='<option value="'+item.id+'">'+item.campusName+'</option>';
			});
			$("#campusList").html("").append(html);
		}
	});
	
	
	//点击添加显示输入域
	$("#tcontent").on('click','a.addTL',function(){
		 $(".insertArea").removeClass("none");
	});

	$("#tcontent1").on('click','a.addT',function(){
		 $(".insertTArea1").removeClass("none");
		 $(this).parent().remove();
	});
	
	$("#classcontent").on('click','a.addC',function(){
		var classroom=$("#campusList").val();
		if(!classroom || classroom==""){
			$.msg("您还没有添加校区，请先在(资源——校区)下添加校区");
			return;
		}
		$(".addRoomArea").removeClass("none");
		$(this).parent().remove();
	});
	
	 $("#userName").blur(function(){
		 var userName = $("#userName").val();
			$.ajax({
				type : "post",
				data : {
					"userName" : userName
				},
				url : rootPath + "/authPrivilege/checkUserName",
				success : function(data) {
					checT = data;
				}
			});
	 });
	 $("#userName").mouseleave(function(){
		 $("#userName").blur();
	 });
	 $("#teacherMobile").blur(function(){
		 var teacherMobile = $("#teacherMobile").val();
			$.ajax({
				type : "post",
				data : {
					"mobile" : teacherMobile,
					"teacherType":"PERSON_TEACHER",
					"teacherId":""
				},
				url : rootPath + "/sysConfigTeacher/checkMobileNum",
				success : function(data) {
					chmoT = data;
				}
			});
	 });
	 $("#teacherMobile").mouseleave(function(){
		 $("#teacherMobile").blur();
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
		 }else{
			 if(chmoT!="true"){
				 $('<div class="c-fa">'+ "手机号已存在或格式不正确" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
				 return;
			 }
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
							$(".insertArea").addClass("none");
							$(".nullnum2").addClass("none");
							$("#teacherList").removeClass("none");
							Form.loadData();
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
							$("#teacherTName").val("");
							$("#teacherTMobile").val("");
							$("#userNameA").val("");
							$("#pwdA").val("");
							$(".insertTArea1").addClass("none");
							$(".nullnum3").addClass("none");
							$("#tassitList").removeClass("none");
							Form.loadData();
						});
						
					}
				},
			});
	 });
	 
	   //点击保存添加教室
		$(".insertRoom").click(function(){
			var roomName = $.trim($("#classRoomName").val());
			var roomAddress = $.trim($("#classRoomAddress").val());
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
			var campusId=$("#campusList").val();
			$.ajax({
				url: rootPath+"/sysConfigClassroom/addClassroom",
				type:"post",
				data:{"roomName":roomName,"address":roomAddress,"delType":0,"campusId":campusId},
				beforeSend:function(){
					$(".insertRoom").off("click");
				},
				success:function(data){
					if(data =="success"){
						$('<div class="c-fa">'+ "添加成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
							$(this).remove();
							$(".addRoomArea").hide();
							$("#classRoomName").val("");
							$("#classRoomAddress").val("");
							$(".addRoomArea").addClass("none");
							$(".nullnum1").addClass("none");
							$("#classList").removeClass("none");
							Form.loadData();
						});
					}
				},
			});
		});
});