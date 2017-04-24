$(function(){
	
	var moduleId = $("#moduleId").val();
	if(moduleId){
		$(".item").attr("disabled","disabled");
		$(".module").attr("style","display:none");
		var moduleType = $("#moduleType").val();
		$("#"+moduleType).attr("selected","selected");
		$("#method1").val("TEACH_METHOD_VIDEO");
		$("#method").val("录播");
		$("#saveButton").html("下一步");
		$(".head").attr("style","display:block");
		$(".fl").find("a[id='"+$("#teachMethod").val()+"']").addClass("btn-success");
		/*$(".moduleName").rules("remove","remote");
		$(".moduleName").rules("add",{
			name : {
				required : true,
				remote : {
					url : rootPath + "/classModule/checkName2",
					type : "post",
					dataType : "json",
					data : {
						name : function() {
							return $("#name").val();
						},
						id : function() {
							return $("#moduleId").val();
						}
					}
				}
			},
        })*/
	}
	$(".bt1").bind("click",function(){
		$(".head").hide();
		$(this).parent().find(".btn-success").removeClass("btn-success");
		$(this).addClass("btn-success");
		if($(this).attr("fValue")=='TEACH_METHOD_VIDEO'){
			$(".head").show();
		}
		$("#method1").val($(this).attr("fValue"));
		$("#method").val($(this).html());
		if($(this).attr("fValue")=='TEACH_METHOD_VIDEO'){
			$("#saveButton").html("下一步");
		}else{
			$("#saveButton").html("保存");
		}
		
	})
	$(".bt2").bind("click",function(){
		$(this).parent().find(".btn-success").removeClass("btn-success");
		$(this).addClass("btn-success");
		$("#method1").val($(this).attr("fValue"));
		$("#addForm").attr("action",rootPath+"/classModule/toAddModule2");
		$("#addForm")[0].submit();
	})
	var teachMethod = $("#teachMethod").val();
	if(teachMethod=='TEACH_METHOD_VIDEO'){
		$("#saveButton").html("下一步");
		$(".head").attr("style","display:block");
	}
	//alert(teachMethod);
	//alert(teachMethod);
	if(!moduleId){
	$("#"+teachMethod).addClass("btn-success");
	$("#method1").val($("#"+teachMethod).attr("fValue"));
	$("#method").val($("#"+teachMethod).html());
	}
	$("#one").bind("change",function(){
		$("#two").html("");
		var itemOneId = $(this).val();
		//alert(itemOneId);
		$.ajax({
			url:rootPath+"/sysConfigItem/getSecondItemByOne",
			type:"post",
			data:{
				"itemOneId":itemOneId
			},
			success:function(jsonData){
				
				for (var i = 0; i < jsonData.length; i++) {
					var str=' <option value="'+jsonData[i].id+'"> '+jsonData[i].itemName+'</option>'; 
					//alert(str);
					$("#two").append(str);
				}
			}
		})
	})
})

function save(){
	if(!$("#addForm").valid()){
		return;
	}
	var form = $("#addForm").serialize();
	$.ajax({
		url:rootPath+"/classModule/saveModule",
		type:"post",
		data:form,
		success:function(data){
			$("#moduleId").val(data);
			//在录播模块不显示弹框
//			var method = $("#method1").val();
//			if(method!='TEACH_METHOD_VIDEO'){
				$.msg("保存成功!",function(){$("#addForm")[0].submit();});
//			}
		},
		error:function(){
			$.msg("保存失败!");
			history.go(0);
		}
	})
}