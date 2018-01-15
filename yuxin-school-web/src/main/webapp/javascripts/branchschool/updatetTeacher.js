var TimeFn = null;
var backResult=null;
var teacherNameResult = null;
var mobileNum = null;
var arrMsg = ['登陆账号不能为空', '登陆账号已存在', '登陆账号只能以字母开头并由数字0-9，字母（a-z，A-Z）和下划线_组成', '老师名称不能为空', '该老师姓名已存在', '老师姓名由汉字、字母 、数字、下划线组成', '手机号不能为空','该手机号已存在','手机号格式不正确','请输入密码','请输入确认密码','两次密码不一致','老师简介不能超过500个字','学校简称不能为空','老师摘要不能超过500个字'];
$(function() {
	//给二级学科id赋值
	var itemOneId = $(".itemOneClass").find("a.btn-success").attr("itemoneid");
	$("#itemOneId").val(itemOneId);
	//二级学科点击
//	$(".itemTwo").click(function(){
//		$(this).attr("class", "btn btn-mini itemTwo btn-success").siblings().attr("class",
//			"btn btn-mini itemTwo btn-default");
//		var itemTwoId = $(".show .itemTwoClass").find("a.btn-success").attr("itemTwoId");
//		$("#itemSecondId").val(itemTwoId);
//	});

	$("#schoolCode").change(function(){
		var schoolCode=$(this).val();
		if(schoolCode){
			$("#schoolName").val($.trim($(this).find("option:selected").text()));
		}else{
			$("#schoolName").val("");
		}
	})
	
	// 加载日期控件
	$("#datetimepicker").datetimepicker({
		lang : 'ch',
		timepicker : false,
		format : 'Y-m-d'
	});
	// 选择头像
	$("#img1").attr("src", rootPath + "/images/headpic.png");
	$("#img2").attr("src", rootPath + "/images/headpic.png");
	$("#img3").attr("src", rootPath + "/images/headpic.png");
	$(".addPic").click(function() {
		$('.add-subs-layer-bg').fadeIn(200, function() {
			$('.add-head').fadeIn(200);
		});
	});
	$(".hideBG").click(function() {
		$('.add-head').fadeOut(200, function() {
			$('.add-subs-layer-bg').fadeOut(200);
		});
	});

	// 给公共页头添加被选择的样式
	$(".smbar").children(".header").children(".navspace").find("li:eq(3)")
		.find("a").addClass("active");
	// 进页面的时候加载模块信息
	$(".long a:first").click();

	// 教师教学模块右侧，双击进入左侧
	$(".right").on('dblclick', 'li', function() {
		// 取消上次延时未执行的方法
		clearTimeout(TimeFn);
		rightToLeft(this);
	});

	$(".right").on('click', 'li', function() {
		var obj = this;
		// 取消上次延时未执行的方法
		clearTimeout(TimeFn);
		// 执行延时
		TimeFn = setTimeout(function() {
			// do function在此处写单击事件要执行的代码
			$(obj).addClass("active").siblings().attr("class", "");
		}, 200);
	});

	// 点击左右箭头移动对应的li
	$(".curr i:first").click(function() {
		$(".left li").each(function() {
			var $this = $(this);
			if ($this.hasClass("active")) {
				leftToRight($this);
			}
		});
	});
	$(".curr i:last").click(function() {
		$(".right li").each(function() {
			var $this = $(this);
			if ($this.hasClass("active")) {
				rightToLeft(this);
			}
		});
	});
	$("#userName").blur(function(){
		ajaxCheckUserName();
		checkUserName();
	});

	//ajax请求添加老师的名称是否存在
	$("#teacherName").blur(function(){
		// ajaxCheckTeacherName();
		checkTeacherName();
	});

	//ajax请求添加老师的手机号是否存在
	$("#mobile").blur(function(){
		var hmobile = $("#HMobile").val();
		var mobile = $("#mobile").val();
		if(mobile!=hmobile){
			ajaxCheckMobileNum();
			checkMobileNum();
		}

	});

	$("#pwd").blur(function(){
		var teaId = $("#teacherId").val();
		var pwd = $("#pwd").val();
		if(teaId == 0){
			if (!pwd) {
				alertMsg(arrMsg[9]);
				return;
			}
		}
		if(pwd.length<6){
			$.msg("密码长度不能低于6位");
			$(this).focus();
			return false;
		}
	});
	$("#pwdNext").blur(function(){
		var pwd = $("#pwd").val();
		var pwdNext = $("#pwdNext").val();
		var teaId = $("#teacherId").val();
		if(teaId == 0){
			if (!pwdNext) {
				alertMsg(arrMsg[10]);
				return;
			}
		}
		if(pwd || pwdNext){
			if (pwdNext != pwd) {
				alertMsg(arrMsg[11]);
				return;
			}
		}else{
			$("#pwd").removeAttr("name");
		}
	});


	// 提交添加老师、修改老师表单
	$(".btn-primary").click(
		function() {
//				// 获取右侧所有的模块ID
			var moduleIds = "";
//				$(".right li").each(function() {
//					moduleIds += $(this).attr("date-id") + ",";
//				});

			var schoolShortName = $('#schoolShortName').val();
			if($.trim(schoolShortName) == ""){
				alertMsg(arrMsg[13]);
				return false;
			}
			var pwd = $("#pwd").val();
			var pwdNext = $("#pwdNext").val();

			var teacherId = $("#teacherId").val();
			if(!teacherId || teacherId == 0 || $(".hiddenUserName").val() == ""){
				ajaxCheckUserName();
				var uName = checkUserName();
				if(!uName){
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

			}else{
				if(pwd || pwdNext){
					if (pwdNext != pwd) {
						alertMsg(arrMsg[11]);
						return;
					}
				}else{
					$("#pwd").removeAttr("name");
				}
			}
			// ajaxCheckTeacherName();
			teacherNameResult = 'true';
			var tName = checkTeacherName();
			if(!tName){
				return false;
			}

			//老师简介不允许超过500
			var resume = $("#resume").val();
			if(resume != null && resume.length > 500){
				alertMsg(arrMsg[12]);
				return false;
			}
			//老师摘要不允许超过500
			var remark = $("#remark").val();
			if(remark != null && remark.length > 500){
				alertMsg(arrMsg[14]);
				return false;
			}
			var hmobile = $("#HMobile").val();
			var mobile = $("#mobile").val();
			if(mobile!=hmobile){
				ajaxCheckMobileNum();
				var mobileNum = checkMobileNum();
				if(!mobileNum){
					return false;
				}
			}
			var schoolCode = $.trim($("#schoolCode").val());
			if(schoolCode==""){
				alertMsg("请填写学校名称");
				return false;
			}
			// 添加老师必须指定学科，所以如果没有学科的情况下是不可以新建老师的
			var subject = $(".itemOneClass").find("a");
			if (subject.length == 0) {
				$('<div class="c-fa">' + "请先添加学科" + '</div>').appendTo(
					'body').fadeIn(100).delay(1000).fadeOut(200,
					function() {
						$(this).remove();
					});
				return false;
			}


			var url = "";
			var msg = "";
			var birthday = $("#datetimepicker").val();
			if (!birthday) {
				$("#datetimepicker").attr("name", "");
			}
			$('#moduleIds').val(moduleIds);
			if (teacherId == "" || teacherId == 0) {
				url = rootPath + "/teacherManger/add";
				msg = "增加成功";
			} else {
				url = rootPath + "/teacherManger/update";
				msg = "修改成功";
			}
			$.ajax({
				type : "post",
				dataType:"JSON",
				data : $("#teacherManageForm").serialize(),
				url : url,
				beforeSend : function(XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success : function(result) {
					$('<div class="c-fa">' + msg + '</div>').appendTo(
						'body').fadeIn(100).delay(1000).fadeOut(200,
						function() {
							$(this).remove();
						});
				},
				complete : function(XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
					window.location.href = rootPath + "/teacherManger/getFirstItems/"+$("#companyId").val();
				},
			});
		});

	// 切换类别
	$('.tabs').on(
		'click',
		'a.btn',
		function() {
			var _this = $(this),
			// 获得索引
				index = _this.index(),
			// active
				active = _this.hasClass('.btn-success'),
			// 获得对象
				obj = $('.sel-tabs .left ul');

			if (!active) {
				_this.addClass('btn-success').siblings('a').removeClass(
					'btn-success').addClass('btn-default');
			}
			obj.eq(index).fadeIn(200).siblings('ul').fadeOut(200);

		});

	$(".itemOne").click(function(){
		chooseOneItem(this);
	});

});

//根据学科选择对应的学科小类
function chooseOneItem(obj) {
	$(obj).attr("class", "btn btn-mini btn-success").siblings().attr("class", "btn btn-mini btn-default");
	var itemOneId = $(obj).attr("itemoneid");
	$("#itemOneId").val(itemOneId);
//	var flag = false;
//	var isSecondItem = false;
//	var secondObj = null;
//	$(".secondItem").each(function() {
//		// 查找当前与imteOneId，对应的学科小类
//		var firstId = $(this).attr("item-one-id");
//
//		if (itemOneId == firstId) {
//			flag = true;
//			$(this).removeClass("hide").addClass("show");
//			$(this).find(".itemTwoClass a").each(function(index){
//				if(index == 0){
//					secondObj = $(this);
//				}
//				var classValue = $(this).attr("class");
//				if(classValue.indexOf("btn-success") > 0){
//					console.log($(this).attr("class"));
//					isSecondItem = true;
//					secondObj = null;
//				}
//			});
//		} else {
//			if ($(this).hasClass("show")) {
//				$(this).removeClass("show").addClass("hide");
//			}
//		}
//
//
//		//给二级学科id赋值
//		var itemTwoId = $(".show .itemTwoClass").find("a.btn-success").attr("itemTwoId");
//		$("#itemSecondId").val(itemTwoId);
//	});
//	//如果已经存在学科小类，则不再执行
//	if(!isSecondItem && secondObj != null){
//		$(secondObj).click();
//	}
//	if (!flag) {
//		$(".left").find("ul").each(function() {
//			$(this).html("<p class='nomodule'>该学科下还没有学科小类,请您先添加学科小类</p>");
//		});
//	}

}

////// 从左边移动到右边--如果左边不是当前
////function leftToRight(obj) {
////	var curLi = $(obj).attr("class", "").prop("outerHTML");
////
////	if (!judgeIsExist(obj, "left")) {
////		$(".right ul").append(curLi);
////		$(obj).remove();
////	} else {
////		$('<div class="c-fa">' + "该模块已经存在，不能重复添加！" + '</div>').appendTo('body')
////				.fadeIn(100).delay(1000).fadeOut(200, function() {
////					$(this).remove();
////				});
////		return false;
////	}
////}
////// 从右边移动到左边
////function rightToLeft(obj) {
////	var curLi = $(obj).attr("class", "").prop("outerHTML");
////	if (!judgeIsExist(obj, "right")) {
////		var teacherType = $(obj).find("span:eq(1)").html();
////		if (teacherType == "面授") {
////			$(".left ul:eq(1)").append(curLi);
////		} else if (teacherType == "直播") {
////			$(".left ul:first").append(curLi);
////		}
////		$(obj).remove();
////	} else {
////		$(obj).remove();
////	}
////}
//
//function judgeIsExist(obj, direction) {
//	var moduleId = $(obj).attr("date-id");
//	var flag = false;
//	if (direction == "left") {
//		// 如果是左边，需要判断右边是否已经存在该模块
//		var rightModules = $(".right ul li");
//		if (rightModules) {
//			$(rightModules).each(function() {
//				var thisModuleId = $(this).attr("date-id");
//				if (thisModuleId == moduleId) {
//					flag = true;
//				}
//			});
//		}
//	} else {
//		// 如果是右边，判断左侧所以得模块
//		var leftModules = $(".left ul li");
//		if (leftModules) {
//			$(leftModules).each(function() {
//				var thisModuleId = $(this).attr("date-id");
//				if (thisModuleId == moduleId) {
//					flag = true;
//				}
//			});
//		}
//	}
//	return flag;
//}

//function addModule() {
//	var postForm = document.createElement("form");// 表单对象
//	postForm.method = "post";
//	postForm.action = rootPath + '/classModule/toAddModule2';
//	postForm.target = "_blank";
//	var itemOneId = $(".itemOneClass").find("a.btn-success").attr("itemOneId");
//	var itemTwoId = $(".show .itemTwoClass").find("a.btn-success").attr(
//		"itemTwoId");
//	var teachMethod = $(".tabs").find("a.btn-success").attr("teachMethod");
//
//	var itemOneInput = document.createElement("input"); // 学科 input
//	itemOneInput.setAttribute("name", "itemOneId");
//	itemOneInput.setAttribute("value", itemOneId);
//	postForm.appendChild(itemOneInput);
//	var itemTwoInput = document.createElement("input");// 学科小类 input
//	itemTwoInput.setAttribute("name", "itemTwoId");
//	itemTwoInput.setAttribute("value", itemTwoId);
//	postForm.appendChild(itemTwoInput);
//	var teachMethodInput = document.createElement("input");// 模块学科 input
//	teachMethodInput.setAttribute("name", "teachMethod");
//	teachMethodInput.setAttribute("value", teachMethod);
//	postForm.appendChild(teachMethodInput);
//
//	document.body.appendChild(postForm);
//	postForm.submit();
//	document.body.removeChild(postForm);
//}

//// 根据学科小类的ID获取对应的模块
//function secondItemForModule(obj, num) {
//	$(obj).attr("class", "btn btn-mini btn-success").siblings().attr("class",
//			"btn btn-mini btn-default");
//	$
//			.ajax({
//				type : "post",
//				url : rootPath + "/classModule/moduleAjaxModule?itemSecondId="
//						+ num,
//				beforeSend : function(XMLHttpRequest) {
//					$(".loading").show();
//					$(".loading-bg").show();
//				},
//				success : function(result) {
//					$(".left").html(result);
//					// 绑定单机事件
//					$(".left").on(
//							'click',
//							'li',
//							function() {
//								var obj = this;
//								// 取消上次延时未执行的方法
//								clearTimeout(TimeFn);
//								// 执行延时
//								TimeFn = setTimeout(function() {
//									// do function在此处写单击事件要执行的代码
//
//									$(obj).addClass("active").siblings().attr(
//											"class", "");
//								}, 200);
//							});
//					// $(".left li").dblclick(function(){ //绑定双击事件
//					$(".left").on('dblclick', 'li', function() {
//						// 取消上次延时未执行的方法
//						clearTimeout(TimeFn);
//						// 执行单击的代码逻辑
//						leftToRight(this);
//					});
//
//					// 添加课程单元
//					$(".left").on(
//							'click',
//							'.nomodule a',
//							function() {
//								// addModule();
//								$("#chooseDiv").css("display", "block");
//								$("#stopDiv").css("display", "block");
//								var itemOneId = $(".itemOneClass").find(
//										"a.btn-success").attr("itemOneId");
//								var itemTwoId = $(".show .itemTwoClass").find(
//										"a.btn-success").attr("itemTwoId");
//								var teachMethod = $(".tabs").find(
//										"a.btn-success").attr("teachMethod");
//								var itemOneName = $(".itemOneClass").find(
//										"a.btn-success").html();
//								var itemTwoName = $(".show .itemTwoClass")
//										.find("a.btn-success").html();
//								var teachMethodName = $(".tabs").find(
//										"a.btn-success").html();
//								$("#tits").html('').html(teachMethodName);
//								$("#xuekeOne").attr("ids", itemOneId).html(
//										itemOneName);
//								$("#xuekeTwo").attr("ids", itemTwoId).html(
//										itemTwoName);
//								$("#tMs").attr("ids", teachMethod).html(
//										teachMethodName);
//							});
//
//					// 验证模块名称
//					$("#mouName").on(
//							'keyup',
//							function() {
//								$.ajax({
//									url : rootPath + "/classModule/checkName",
//									type : "post",
//									data : {
//										"name" : $(this).val()
//									},
//									dataType : "json",
//									success : function(b) {
//										if (!b) {
//											$(".note1").html('').css("display",
//													"block")
//													.html("课程单元名称已被使用!");
//										} else {
//											$(".note1").html('').css("display",
//													"none").html("请输入课程单元名称");
//										}
//									}
//								});
//							})
//
//					// 点击打开或关闭弹窗
//					$(".giveUp").on('click', function() {
//						$("#chooseDiv").css("display", "none");
//						$("#stopDiv").css("display", "none");
//					});
//
//					// 添加模块
//					$(".savemodule")
//							.on(
//									'click',
//									function() {
//										// 模块名称
//										$("#mNames").val($("#mouName").val());
//										// 一级项目
//										$("#yjItemId").val(
//												$("#xuekeOne").attr("ids"));
//										// 二级项目
//										$("#ejItemId").val(
//												$("#xuekeTwo").attr("ids"));
//										// 教学方式
//										$("#methodTeach").val(
//												$("#tMs").attr("ids"));
//										// 总课时
//										$("#totalHours").val(
//												$("#totalClassHour").val());
//										// 描述
//										$("#moduleMark").val(
//												$("#moduleDesc").val());
//										if ($("#mNames").val() == "") {
//											$(".note1").css("display", "block");
//											return;
//										} else {
//											$
//													.ajax({
//														url : rootPath
//																+ "/classModule/checkName",
//														type : "post",
//														data : {
//															"name" : $(
//																	"#mouName")
//																	.val()
//														},
//														dataType : "json",
//														success : function(b) {
//															if (!b) {
//																$(".note1")
//																		.html(
//																				'')
//																		.css(
//																				"display",
//																				"block")
//																		.html(
//																				"课程单元名称已被使用!");
//																return;
//															} else {
//																$(".note1")
//																		.html(
//																				'')
//																		.css(
//																				"display",
//																				"none")
//																		.html(
//																				"请输入课程单元名称");
//															}
//														}
//													});
//											$(".note1").css("display", "none");
//										}
//										if ($("#totalHours").val() == "") {
//											$(".note2").html('').html("请输入总课时")
//													.css("display", "block");
//											return;
//										} else {
//											$(".note2").css("display", "none");
//										}
//										if (isNaN($("#totalHours").val())
//												|| $("#totalHours").val() < 0) {
//											$(".note2").html('').html("请输入数字")
//													.css("display", "block");
//											return;
//										} else {
//											$(".note2").css("display", "none");
//										}
//										$
//												.ajax({
//													url : rootPath
//															+ "/classModule/saveModules",
//													type : "post",
//													data : $("#editFroms")
//															.serialize(),
//													dataType : "json",
//													success : function(result) {
//														$("#chooseDiv").css(
//																"display",
//																"none");
//														$("#stopDiv").css(
//																"display",
//																"none");
//														if ($("#tMs").attr(
//																"ids") == "TEACH_METHOD_LIVE") {
//															secondItemForModule(
//																	this,
//																	$(
//																			"#xuekeTwo")
//																			.attr(
//																					"ids"));
//														} else {
//															html = "<li date-id='"
//																	+ result
//																	+ "'>"
//																	+ "<span class='p1'>"
//																	+ $(
//																			"#mNames")
//																			.val()
//																	+ "</span>"
//																	+ "<span class='p2'>面授</span>"
//																	+ "<span class='p3'>"
//																	+ $(
//																			"#totalHours")
//																			.val()
//																	+ "课时</span>"
//																	+ "</li>";
//															$(".left")
//																	.find(
//																			"ul:eq(1)")
//																	.append(
//																			html)
//																	.find(
//																			"p.nomodule")
//																	.remove();
//															$('.tabs')
//																	.find(
//																			"a.btn:eq(1)")
//																	.trigger(
//																			"click");
//														}
//													},
//													complete : function() {
//														// console.log($("#tMs").attr("ids"));
//													}
//												});
//									})
//				},
//				complete : function(XMLHttpRequest, textStatus) {
//					$(".loading").hide();
//					$(".loading-bg").hide();
//				},
//			});
//}

function bindClickForLeftModule() {

}

// 选择图片
function savePic() {
	/*
	 * $.ajax({ url : rootPath+"/student/savePic", type : "POST",
	 * dataType:'json', success : function(data) { data = eval("(" + data +
	 * ")"); $("#target").parent().html('<img id="target"
	 * src="'+rootPath+data.url+'" style="width:300px;height:300px;"/>');
	 * $("#target").trigger("change"); $("#img1").attr("src",rootPath+data.url);
	 * $("#img2").attr("src",rootPath+data.url);
	 * $("#img3").attr("src",rootPath+data.url); } });
	 */

	$.ajaxFileUpload({
		url : rootPath + "/sysConfigTeacher/savePic;" + window["sessionName"]
		+ "=" + window["sessionId"],
		secureuri : false,// 安全协议
		async : false,
		fileElementId : 'imgData',
		dataType : 'json',
		type : "POST",
		success : function(data) {

			$("#target").parent().html(
				'<img id="target" src="' + data.url + '"/>');
			$("#target").trigger("change");
			$("#img1").attr("src", data.url);
			$("#img2").attr("src", data.url);
			$("#img3").attr("src", data.url);
		},
		error : function(arg1, arg2, arg3) {
			// console.log(arg1);
		}
	});
}

// 上传截取后的图片
function saveHeadPic() {
	var path = $("#target").attr("src");
	if (!path) {
		$('<div class="c-fa">' + "请选择头像" + '</div>').appendTo('body').fadeIn(
			100).delay(1000).fadeOut(200, function() {
			$(this).remove();
		});
		return;
	}
	$.ajax({
		url : rootPath + "/sysConfigTeacher/saveCutPic",
		data : {
			path : $("#target").attr("src"),
			x : $("#x").val(),
			y : $("#y").val(),
			w : $("#w").val(),
			h : $("#h").val()
		},
		type : "post",
		success : function(data) {
			var serverUrl = $("#imgUrl").val();
			$("#choiceHead").attr("src", serverUrl+data);
			$("#headpicUrl").val(data);
			$('.add-head').fadeOut(200, function() {
				$('.add-subs-layer-bg').fadeOut(200);
			});
		}
	});
}

function ajaxCheckTeacherName(){
	var teacherName = $("#teacherName").val();
	var teacherId = $("#teacherId").val();
	console.log("checkTeacherName方法，teacherName： "+teacherName+", teacherId:"+teacherId);
	$.ajax({
		type : "post",
		async : false,
		data : {"teacherName" : teacherName , "teacherType" : 'PERSON_TEACHER', "teacherId" : teacherId },
		url : rootPath + "/sysConfigTeacher/checkTeacherName",
		success : function(data) {
			teacherNameResult = data;
		}
	});
}

function ajaxCheckUserName(){
	var userName = $("#userName").val();
	$.ajax({
		type : "post",
		async : false,
		data : { "userName" : userName },
		url : rootPath + "/authPrivilege/checkUserName",
		success : function(data) {
			if(data!="true"){
				alertMsg(data)
			}
			backResult = data;
		}
	});
}

function ajaxCheckMobileNum(){
	var mobile = $("#mobile").val();
	var teacherId = $("#teacherId").val();
	$.ajax({
		type : "post",
		async : false,
		data : {"mobile" : mobile},
		url : rootPath + "/sysConfigTeacher/checkUserMobileNum",
		success : function(data) {
			mobileNum = data;
		}
	});
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

function checkTeacherName(){

	console.log("checkTeacherName方法，teacherName： "+teacherName+", teacherNameResult:"+teacherNameResult);
	var teacherName = $("#teacherName").val();
	if(!teacherName){
		alertMsg(arrMsg[3]);
		return false;
	}
	else if(teacherName.length>20){
		alertMsg('老师姓名长度不得超过20个字符');
		return false;
	}
	else{
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
}

function checkMobileNum(){
	var mobile = $("#mobile").val();
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
}

function alertMsg(obj){
	$('<div class="c-fa">'+obj+'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(1000,function() {
		$(this).remove();
	});
}

