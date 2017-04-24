var obj = new Object();
$(function(){
	setObj();
	$("input[name='testListenAuth'],input[name='userSeeAuth']," + 
		"input[name='setPointName']").change(function(){
		$(".is-ok").show();
		return false;
	});
	$(".cancel").click(function(){
		$.each($("input[name='testListenAuth']"),function(index,item){
			if(item.value == obj.testListenAuth){
				$("input[name='testListenAuth']:eq("+index+")")
					.prop("checked","checked");
			}
		});
		$.each($("input[name='userSeeAuth']"),function(index,item){
			if(item.value == obj.testListenAuth){
				$("input[name='userSeeAuth']:eq("+index+")")
					.prop("checked","checked");
			}
		});
		$.each($("input[name='setPointName']"),function(index,item){
			if(item.value == obj.testListenAuth){
				$("input[name='setPointName']:eq("+index+")")
					.prop("checked","checked");
			}
		});
		$("#overFlowTime").val(obj.overFlowTime);
		$("#overFlowInfo").val(obj.overFlowInfo);
		$("#namedTime").val(obj.namedTime);
		$("#namedNum").val(obj.namedTime);
		$(".is-ok").hide();
		return false;
	});
	$(".ok").click(function(){
		var id = $("#id").val();
		var testListenAuth = $("input[name='testListenAuth']:checked").val();
		var overFlowTime = $("#overFlowTime").val();
		var overFlowInfo = $("#overFlowInfo").val();
		var userSeeAuth = $("input[name='userSeeAuth']:checked").val();
		var setPointName = $("input[name='setPointName']:checked").val();
		var namedTime = $("#namedTime").val();
		var namedNum = $("#namedNum").val();
		
		if(overFlowTime == 0 ){
			$.msg("试听时间不能为0");
			return false;
		}
		if(setPointName == 'LOOK_VIDEO_BY_TIME' && namedTime == 0){
			$.msg("点名时间间隔不能为0");
			return false;
		}
		if(setPointName == 'LOOK_VIDEO_BY_NUM' && namedTime == 0){
			$.msg("点名次数不能为0");
			return false;
		}
		
		if(overFlowInfo == ""){
			overFlowInfo = "该视频属于收费视频，如想继续收看请您购买课程";
		}
		
		$.ajax({
			url:rootPath + "/courseVideoLookAuth/updateOption",
			type:"post",
			data:{"id":id,
				"testListenAuth":testListenAuth,
				"overFlowTime":overFlowTime,
				"overFlowInfo":overFlowInfo,
				"userSeeAuth":userSeeAuth,
				"setPointName":setPointName,
				"namedTime":namedTime,
				"namedNum":namedNum
				},
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					$.msg("设置完成",1000);
					setObj();
				}else{
					$.msg(data.msg,1000);
				}
			}
		});
	});
});

function setObj(){
	obj.testListenAuth = $("input[name='testListenAuth']:checked").val();
	obj.overFlowTime = $("#overFlowTime").val();
	obj.overFlowInfo = $("#overFlowInfo").val();
	obj.userSeeAuth = $("input[name='userSeeAuth']:checked").val();
	obj.setPointName = $("input[name='setPointName']:checked").val();
	obj.namedTime = $("#namedTime").val();
	obj.namedNum = $("#namedNum").val();
}
function onlyNum() {
	var event = arguments.callee.caller.arguments[0] || window.event; 
	if (!(event.keyCode == 46) && !(event.keyCode == 8)
			&& !(event.keyCode == 37) && !(event.keyCode == 39)){
		if (!((event.keyCode >= 48 && event.keyCode <= 57) 
				|| (event.keyCode >= 96 && event.keyCode <= 105))){
			if(window.event){
				event.returnValue = false;
			}else{
				event.preventDefault();
			}
		}else{
			$(".is-ok").show();
		}
	}
}
function kedown(){
	$(".is-ok").show();
}