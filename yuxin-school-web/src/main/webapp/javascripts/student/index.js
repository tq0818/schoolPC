;
(function($) {
	var Form={
		search : function() {
			if(!/^09\d{8}|1[3,4,5,7,8]\d{9}$/.test($("#mobile").val())){
				Form.showTip("不是有效的手机号");
				$("#mobile").val("");
				return false;
			}
			$.ajax({
						url : rootPath + "/student/searchByMobile/"+ $("#mobile").val(),
						type : "post",
						dataType : "json",
						success : function(jsonData) {
							if (jsonData && jsonData.id) {
								console.log(jsonData);
								$.each(jsonData,function(key,value){
									if(value && value!="null"){
										$("#gonext").append('<input type="hidden" id="'+key+'" name="'+key+'" value="'+value+'"/>');
									}
									
								});
								$("#gonext").attr("action",rootPath + "/student/showSignUp").attr("method","post").submit();
								
								// 跳转到报名页面
//								location.href=rootPath + "/student/showSignUp";
//								$.ajax({
//									url : rootPath + "/student/showSignUp",
//									data : student,
//									type : "post",
//									dataType : "html",
//									success : function(page) {
//										$(document).html(page);
//									}
//								})
							}else if(jsonData && jsonData.message){
								Form.showTip(jsonData.message);
							} else {
								Form.showTip("你查询的学员手机号不存在，是否&nbsp;&nbsp;<a id='showDiolog' href='javascript:void(0);' style='color:blue;font-style:italic;'>新增学员并报名</a>");
								$("#showDiolog").on("click", Form.addUser);
							}
						},
						error : function(resp,msg,err) {
							console.log(resp);
							Form.showTip("你查询的学员手机号不存在，是否&nbsp;&nbsp;<a id='showDiolog' href='javascript:void(0);' style='color:blue;font-style:italic;'>新增学员并报名</a>");
							$("#showDiolog").on("click", Form.addUser);
						}
					})
		},
		addUser : function() {
//			$.ajax({
//				url : rootPath + "/student/showSignUp",
//				type : "post",
//				dataType : "html",
//				success : function(page) {
//					$(".main").html(page);
//				}
//			})
			$("#gonext").append('<input type="hidden" id="mobile" name="mobile" value="'+$("#mobile").val()+'"/>');
			$("#gonext").attr("action",rootPath + "/student/showSignUp").attr("method","post").submit();
//			location.href=rootPath + "/student/showSignUp";
		},
		/**
		 * 提示
		 */
		showTip : function(message) {
			$(".tips").find(".ps").html("").html(message);
		}
	}

	$(document).ready(function() {
		$("#search").on("click", Form.search);
		$("#mobile").on("keypress",function(e){
			if(e.keyCode==13){
				Form.search();
			}
		});	
		$(".footer").addClass("footer-fixed");
	})
	return Form;
})(jQuery)
