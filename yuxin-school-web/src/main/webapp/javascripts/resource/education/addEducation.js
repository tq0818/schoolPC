var backResult=null;
var teacherNameResult = null;
var mobileNum = null;
var arrMsg = ['登陆账号不能为空', '登录账号已存在', '登录账号只能以字母开头并由数字0-9，字母（a-z，A-Z）和下划线_组成', '老师名称不能为空', '该老师姓名已存在', '老师姓名由汉字、字母 、数字、下划线组成', '手机号不能为空','该手机号已存在','手机号格式不正确','请输入密码','请输入确认密码','两次密码不一致'];
$(function(){
	
	//判断传过来的教师类型
	var teType = $("#teaType").val();
	if(teType == "PERSON_MANAGER"){
		$(".manager").addClass("btn-success").siblings(".tType").removeClass("btn-success");
	}
	if(teType == "PERSON_WAITER"){
		$(".waiter").addClass("btn-success").siblings(".tType").removeClass("btn-success");
	}
	if(teType == "PERSON_ASSISTANT"){
		$(".assistant").addClass("btn-success").siblings(".tType").removeClass("btn-success");
	}
	var teacherId = $("#teacherId").val();
	if(teacherId){
		mobileNum = 'true';
		teacherNameResult = 'true';
	}
	//加载日期控件
	$("#datetimepicker").datetimepicker({
		lang:'ch',
		timepicker:false,
		format:'Y-m-d'
	});
	//点击搜索，显示相应数据
	$("#btnSearch").click(function(){
		var mobile = $.trim($("#mobileText").val());
		if(mobile){
			location.href = rootPath+"/sysConfigTeacher/findTeacherByMobile?mobile="+mobile;
		}
		
			
	});
	
	$(".tType").click(function(){
		addClass(this);
		var teacherType = $(this).attr("typeStatus");
		$("#teaType").val(teacherType);
	});
	$("#userName").blur(function(){
		var userName = $("#userName").val();
		$.ajax({
			type : "post",
			data : {
				"userName" : userName
			},
//			async : false,
			url : rootPath + "/authPrivilege/checkUserName",
			success : function(data) {
				backResult = data;
				checkUserName();
			}
		});
	});
	//ajax请求添加老师的名称是否存在
		$("#tName").blur(function(){
			var teacherName = $("#tName").val();
			var teaType = $("#teaType").val();
			console.log("类型："+teaType);
			$.ajax({
				type : "post",
				data : {"teacherName" : teacherName , "teacherType" : teaType },
				url : rootPath + "/sysConfigTeacher/checkTeacherName",
				success : function(data) {
					teacherNameResult = data;
					checkTeacherName();
				}
			});
		});
		
		//ajax请求添加老师的手机号是否存在
		$("#mobile").blur(function(){
			var mobile = $("#mobile").val();
			var teaType = $("#teaType").val();
			console.log("类型："+teaType);
			$.ajax({
				type : "post",
				data : {"mobile" : mobile , "teacherType" : teaType},
				url : rootPath + "/sysConfigTeacher/checkMobileNum",
				success : function(data) {
					mobileNum = data;
					checkMobileNum();
				}
			});
		});
	//点击保存，进行数据保存
	$(".btn-primary").click(function(){
		var teaType =  $("#teaType").val();
		$.cookie('teaType',teaType,{ path: "/"}); // 存储 cookie 
		var teacherId = $("#teacherId").val();
		var pwd = $("#pwd").val();
		var pwdNext = $("#pwdNext").val();
		
		if(!teacherId || teacherId == 0){
			var userName = checkUserName();
			if(!userName){
				return false;
			}
			if (!pwd) {
				alertMsg(arrMsg[9]);
				return;
			}
			if (!pwdNext) {
				alertMsg(arrMsg[10]);
				return;
			}
			if (pwdNext != pwd) {
				alertMsg(arrMsg[11]);
				return;
			}
		}
		
		if(teacherId){
			var time = $("#datetimepicker").val();
			if(time){
				$("#datetimepicker").attr("name","birthday");
			}else{
				$("#datetimepicker").attr("name","");
			}
			var mobile = checkMobileNum();
			var tName = checkTeacherName();
			if(!tName){
				return false;
			}
			if(!mobile){
				return false;
			}
			
					$.ajax({
						url : rootPath+"/sysConfigTeacher/update",
						type : "post",
						data : $("#editInfo").serialize(),
						beforeSend:function(XMLHttpRequest){
							$(".btn-primary").hide();
				            $(".loading").show();
				            $(".loading-bg").show();
				        },
						success : function(data){
							if(data == "success"){
								$('<div class="c-fa">'+ "保存成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								location.href = rootPath+"/sysConfigTeacher/toEducationInfo";
							}
						},
				        complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				        }
					});
			
		}else{
			var time = $("#datetimepicker").val();
			if(time){
				$("#datetimepicker").attr("name","birthday");
			}else{
				$("#datetimepicker").attr("name","");
			}
			var mobile = checkMobileNum();
			var tName = checkTeacherName();
			if(!tName){
				return false;
			}
			if(!mobile){
				return false;
			}
					$.ajax({
						url : rootPath+"/sysConfigTeacher/addEducation",
						type : "post",
						data : $("#editInfo").serialize(),
						beforeSend:function(XMLHttpRequest){
							$(".btn-primary").hide();
				            $(".loading").show();
				            $(".loading-bg").show();
				        },
						success : function(data){
							if(data == "success"){
								$('<div class="c-fa">'+ "保存成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
								location.href = rootPath+"/sysConfigTeacher/toEducationInfo";
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
});

function addClass(obj) {
	$(".tType").each(function() {
		$(this).attr("class", "btn btn-mini btn-default tType");
	});
	$(obj).addClass("btn-success");
}

function checkTeacherName(){
	var teacherName = $("#tName").val();
	var agoTName = $("#agoTName").val();
	var agoTeaType = $("#agoTeaType").val();
	var teaType = $("#teaType").val();
	if(!teacherName){
		alertMsg(arrMsg[3]);
		return false;
	}
	if(teacherName!=agoTName || teaType!=agoTeaType){
		if(!teacherName){
			alertMsg(arrMsg[3]);
			return false;
		}else{
			if(teacherNameResult != 'true'){
				if(teacherNameResult == arrMsg[4]){
					alertMsg(arrMsg[4]);
				}else if(teacherNameResult == arrMsg[3]){
					alertMsg(arrMsg[3]);
				}else if(teacherNameResult == arrMsg[5]){
					alertMsg(arrMsg[5]);
				}
				return false;
			}else{
				return true;
			}
		}
	}else{
		return true;
	}
	
}

function checkMobileNum(){
	var mobile = $("#mobile").val();
	var agoTMobile = $("#agoTMobile").val();
	var agoTeaType = $("#agoTeaType").val();
	var teaType = $("#teaType").val();
	if(!mobile){
		alertMsg(arrMsg[6]);
		return false;
	}
	if(agoTMobile!=mobile || teaType!=agoTeaType){
		if(!mobile){
			alertMsg(arrMsg[6]);
			return false;
		}else{
			if(mobileNum != 'true'){
				if(mobileNum == arrMsg[7]){
					alertMsg(arrMsg[7]);
				}else if(mobileNum == arrMsg[6]){
					alertMsg(arrMsg[6]);
				}else if(mobileNum == arrMsg[8]){
					alertMsg(arrMsg[8]);
				}
				return false;
			}else{
				return true;
			}
		}
	}else{
		return true;
	}
	
}

function checkUserName(){
	var userName = $("#userName").val();
	if(!userName){
		alertMsg(arrMsg[0]);
		return false;
	}else{
		if(backResult != 'true'){
			if(backResult == arrMsg[1]){
				alertMsg(arrMsg[1]);
			}else if(backResult == arrMsg[0]){
				alertMsg(arrMsg[0]);
			}else if(backResult == arrMsg[2]){
				alertMsg(arrMsg[2]);
			}
			return false;
		}else{
			return true;
		}
	}
}

function alertMsg(obj){
	$('<div class="c-fa">'+obj+'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function() {
		$(this).remove();
	});
}