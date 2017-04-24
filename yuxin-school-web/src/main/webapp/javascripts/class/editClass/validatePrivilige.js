	var url=rootPath+"/teachSchedulePrivilege/haveTeachSchedulePrivilege";
	var data={};
	$.ajax({
		url:url,
		data:data,
		type:"post",
		success:function(jsonData){
			//如果是排课老师权限
			if(!jsonData){
				//封装一条数据到classIndexAjax页面中
				//控制课程列表不能添加
				$(".checkStudent").data("flag","yes");
				/////////////////////////////录播///////////////////////////
				//控制录播下架隐藏
				$(".public_course").hide();
				//课程名称
				//$("#classTypeName").attr("disabled",true);
				//定价
				$("#prices").attr("disabled",true);
				//优惠价
				$("#realPrice").attr("disabled",true);
				//购买基数
				$(".baseNum").attr("disabled",true);
				//总课时
				$(".courseNum").attr("disabled",true);
				//保存和取消
				$(".operator").hide();
				/////////////////////////////录播///////////////////////////
				//其他课程中的权限控制
				$(".operatorOther").hide();
				
	    		//课程列表中权限的控制
	    		$(".deleteGoods").hide();
	    		$(".upSale").hide();
	    		$(".downGoods").hide();
	    		
	    		//
			} 
		}
	});
