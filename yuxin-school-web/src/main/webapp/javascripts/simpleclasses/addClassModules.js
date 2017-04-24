/**
 * author zhang.zx
 * 班型打包
 * 页面js封装
 */
(function($){
	var Form={
			init : function(){
				$("#ulOne").on('click','li a.btn',function(){
					$("#chooseDiv").css("display", "block");
					//$(".pic-upload").css("display", "none");
					$("#stopDiv").css("display", "block");
				});
				
	    		$("#giveUp").on('click',function(){
	    			$("#chooseDiv").css("display", "none");
					$("#stopDiv").css("display", "none");
	    		});
	    		//查询课程单元中的二级项目
				this.queryItemSecondtwo($("#itemOneIds").val());
			},
			queryItemSecondtwo : function (id){
				$.ajax({
					url : rootPath + "/exam/queryItemSecond",
					type : "post",
					data : {pid:id},
					dataType : "json",
					success : function(result) {
						var html="";
						$.each(result,function(i,item){
							if(i==0){
								html+="<option value='"+item.id+"' selected='selected'>"+item.itemName+"</option>"
							}else{
								html+="<option value='"+item.id+"'>"+item.itemName+"</option>";
							}
						});
						$("#twoItems").append(html);
					}
				});
			},
			addModules:function(){
				$("#mNames").val($("#mouName").val());
				var itemSecondId=$("#twoItems option:selected").val();
				$("#ejItemId").val(itemSecondId);
				$("#tabsList").find("a.btn").each(function(i){
					if($(this).hasClass("btn-success")){
						$("#methodTeach").val($(this).attr("ids"));
					}
				});
				$("#totalHours").val($("#totalClassHour").val());
				$("#moduleMark").val($("#moduleDesc").val());
				$.ajax({
					url : rootPath + "/classModule/saveModules",
					type : "post",
					data : $("#editFroms").serialize(),
					dataType : "json",
					success : function(result) {
						$("#chooseDiv").css("display", "none");
						$("#stopDiv").css("display", "none");
						window.Form.queryModuleByClassType();
					}
				});
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)