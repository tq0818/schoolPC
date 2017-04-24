var time = new Array();
var res = null;
function showTime(index,parent,obj,date){
	if(typeof(date) != "undefined" && date != null && date != ""){
		date = date.replace(/-/g,"/");
	}
	var nowTime = new Date().getTime();
	var overTime = new Date(date).getTime();
	var middleTime = overTime - nowTime;
	var day    = Math.floor(middleTime / ( 24 * 60 * 60 * 1000));
	var hour   = Math.floor((middleTime - day * 24 * 60 * 60 * 1000) / (60 * 60 * 1000));
	var minute = Math.floor((middleTime - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000) / (60 * 1000)) + "";
	var second = Math.floor((middleTime - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000) + "";
	var ms     = Math.floor((middleTime - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - minute * 60 * 1000 - second * 1000) / 1) + "";

	minute.length < 2 ? minute = "0" + minute : minute;
	second.length < 2 ? second = "0" + second : second;
	ms.length < 2 ? ms = "0" + ms : ms;
	ms.length < 3 ? ms = "0 " + ms : ms;
	obj.html(minute + "分" + second + "秒");
	if(middleTime <= 0){
		window.clearInterval(time[index]);
		parent.html("");
		parent.html("<p><span class='sp_red'>直播中</span></p>");
	}
}
$(function(){
	$selectSubMenu('teacher_live');
			toLink(1,1,0,"");
			$("#status").val(1);
			$(".btn-day").click(function(){
				$(".btn-day").removeClass("active");
				$(this).addClass("active");
				toLink(1, $(this).attr("data-time"),
						$(".btn-ing.active").attr("data-time")
						,$("#findNoLessonName").val());
			});
			$(".btn-ing").click(function(){
				$(".btn-ing").removeClass("active");
				$(this).addClass("active");
				toLink(1, $(".btn-day.active").attr("data-time"),
						$(this).attr("data-time")
						,$("#findNoLessonName").val());
			});
			
			$(".course-table li").off("click").on("click",function(){
				$(".courseactive").removeClass("courseactive");
				$(this).addClass("courseactive");
				$(".place-list").html("");
				$(".paginationss").html("");
				var t = $(this).attr("data-types");
				if(t == 1){
					$("#findClassLessonName").val("");
					$(".btn-ok").show().attr("disabled","disabled");
					$(".btn-del").hide().attr("disabled","disabled");
					selCourse(1);
				}else if(t == 2){
					$("#findClassLessonName").val("");
					$(".btn-ok").hide().attr("disabled","disabled");
					$(".btn-del").show().attr("disabled","disabled");
					selIntercut(1);
				}
			});
			$(".btn-cancel").on("click",function(){
				$("#findClassLessonName").val("");
				$(".courseactive").removeClass("courseactive");
				$(".course-table li:eq(0)").addClass("courseactive");
				$(".btn-ok").show().attr("disabled","disabled");
				$(".btn-del").hide().attr("disabled","disabled");
				$(".place-list").html("");
				$(".paginationss").html("");
			});
			
			$(".btn-ok").off("click").on("click",function(){
				if($(this).attr("disabled") == "disabled"){
					return false;
				}
				var number = "";
				for(var i = 0; i < $(".addTopic").length ; i++){
					if($(".addTopic:eq(" + i + ")").prop("checked")){
						number += $(".addTopic:eq(" + i + ")").attr("data-name")+",";
					}
				}
				number = number.substring(0,(number.length) -1);
				//添加插拨件
				$.ajax({
					url : rootPath + "/companyLiveCoursewareZs/addcha",
					type:"post",
					data:{"names":number,"id":$("#recordids").val(),"lessonId":$("#lessonids").val()},
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
						$(".add-classes").hide();
			         },
					success:function(data){
			            $(".loading").hide();
						if(data.msg == "success"){
							$(".add-classes-bg").hide();
							$.msg("添加插播课件成功");
							$(".courseactive").removeClass("courseactive");
							$(".course-table li:eq(0)").addClass("courseactive");
							$(".place-list").html("");
							$(".paginationss").html("");
						}else{
							$(".add-classes").show();
							$.msg(data.msg);
						}
					}
				});
			});
			$(".btn-del").off("click").on("click",function(){
				if($(this).attr("disabled") == "disabled"){
					return false;
				}
				var number = "";
				for(var i = 0; i < $(".addTopic").length ; i++){
					if($(".addTopic:eq(" + i + ")").prop("checked")){
						number += $(".addTopic:eq(" + i + ")").attr("data-name")+",";
					}
				}
				number = number.substring(0,(number.length) -1);
				//删除插拨件
				$.ajax({
					url : rootPath + "/companyLiveCoursewareZs/delcha",
					type:"post",
					data:{"names":number,"id":$("#recordids").val(),"lessonId":$("#lessonids").val()},
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
						$(".add-classes").hide();
			         },
					success:function(data){
			            $(".loading").hide();
						if(data.msg == "success"){
							$.msg("删除插播课件成功");
							$(".place-list").html("");
							$(".paginationss").html("");
							$(".btn-del").attr("disabled","disabled");
							selIntercut(1);
							$(".add-classes").show();
						}else{
							$(".add-classes").show();
							$.msg(data.msg);
						}
					}
				});
			});
		});
		
function toLink(pageNo,status,statuss,name) {
	var dataInfo = {"status":status,"page":pageNo,"statuss":statuss,"name":name};
	//从首页点击进来查出课次
	var id = getUrlParam('id');
	if(id) dataInfo.id = id;
	$.ajax({
		url:rootPath + "/classModule/selClass?_="+(new Date().getTime()),
		type:"post",
		data:dataInfo,
		dataType:"html",
		beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
        success: function(data) {
			$(".operate_list").html("");
			$(".operate_list").html(data);
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
	});
}
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

function selCourse(page){
	var lessonName = $("#findClassLessonName").val();
	if(res != null){
		res.abort();
	}
	res = $.ajax({
		url : rootPath + "/companyLiveCoursewareZs/selCourse",
		type:"post",
		data:{"name":lessonName,"page":page,"pageSize":8},
		dataType:"html",
		success:function(data){
			$(".place-list").html(data);
		}
	});
}

function listener(){
	var event = arguments.callee.caller.arguments[0] || window.event; 
	if(event.keyCode == 13){
		toLink(1, $(".btn-day.active").attr("data-time")
				, $(".btn-ing.active").attr("data-time")
				, $("#findNoLessonName").val());
	}
	return false;
}

function search(){
	toLink(1, $(".btn-day.active").attr("data-time")
			, $(".btn-ing.active").attr("data-time")
			, $("#findNoLessonName").val());
	return false;
}

function selIntercut(page){
	var lessonName = $("#findClassLessonName").val();
	if(res != null){
		res.abort();
	}
	res = $.ajax({
		url : rootPath + "/companyLiveCoursewareZs/selIntercut",
		type:"post",
		data:{"recordName":lessonName,"page":page,"pageSize":8,"classModuleLessionId":$("#lessonids").val()},
		dataType:"html",
		success:function(data){
			$(".place-list").html(data);
		}
	});
}