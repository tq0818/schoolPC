function detail(id){
		$("#payMasterId").val(id);
		$("#toDetailForm")[0].submit();
	}
	function changeStudent(id,code){
		if(code == 'CHANNEL_STUDYCARD'){
			$.msg("学习卡兑换的课程不能做异动操作！");
			return false;
		} 
		$("#payMasterId").val(id);
		var src=rootPath+"/student/changeStudent";
		$("#toDetailForm").attr("action",src)[0].submit();
	}
	function returnFee(id,code){
		if(code == 'CHANNEL_STUDYCARD'){
			$.msg("学习卡兑换的课程不能做异动操作！");
			return false;
		} 
		$("#payMasterId").val(id);
		var src=rootPath+"/student/transaction/returnFee";
		$("#toDetailForm").attr("action",src)[0].submit();
	}
	function changeClass(id,code){
		if(code == 'CHANNEL_STUDYCARD'){
			$.msg("学习卡兑换的课程不能做异动操作！");
			return false;
		} 
		$("#payMasterId").val(id);
		var src=rootPath+"/student/transaction/changeClass";
		$("#toDetailForm").attr("action",src)[0].submit();
	}
	function changeClassNo(id){
		$("#payMasterId").val(id);
		var src=rootPath+"/student/transaction/changeClassNo";
		$("#toDetailForm").attr("action",src)[0].submit();
	}