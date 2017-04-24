$(function(){
	//点击返回列表
	$(".saveRoom").click(function(){
		//如果是从创建班型过来的话，返回到班型列表页面
		var classTypeIdFlag = $.cookie("classTypeIdFlag");
		var methed = $.cookie("methed");
		var courselable=$.cookie("courselable");
		if(classTypeIdFlag && !isNaN(parseInt(classTypeIdFlag)) && parseInt(classTypeIdFlag) > 0){
			if(courselable && courselable != ""){
				window.location.href = rootPath +"/editClass/editCourseList/"+classTypeIdFlag+"/"+courselable;
				return;
			}
			if(methed == "0"){
				window.location.href = rootPath +"/classType/"+classTypeIdFlag+"/showClassCourseDetail" ;
			}else{
				var objform = document.createElement("form");
				document.body.appendChild(objform);
				objform.action =rootPath +"/classType/showClassTypeStu" ;	
				objform.method = "post";
					
				var lable = document.createElement("input");
				lable.type = "hidden";
				lable.value = methed;
				lable.name = "lable";
				objform.appendChild(lable);
			
				var elementId = document.createElement("input");
				elementId.type = "hidden";
				elementId.value = classTypeIdFlag;
				elementId.name = "id";
				objform.appendChild(elementId);
				
				objform.submit();
			}
		}else{
			window.location.href = rootPath+"/classType/showClassTypePage";
		}
	});
})