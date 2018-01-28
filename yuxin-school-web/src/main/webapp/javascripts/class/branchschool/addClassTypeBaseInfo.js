$(function(){
	queryCourseSetting();
	
});

function saveCddsRecommendFlag(){
	var cddsRecommendFlag=$("input[name='cddsRecommendFlag']:checked").val();
	$.ajax({
        url:rootPath + "/branchSchool/setCddsRecommendFlag",
		 type:"post",
		 data:{"flag":cddsRecommendFlag,"classTypeId":$("#classtypeIds").val()},
		 dataType:"json",
			beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	         },
		 success:function(data){
			 if("success"==data.result){
				 $.msg("保存成功");
			 }else{
				 $.msg("保存失败");
			 }
		 },
         complete:function(XMLHttpRequest,textStatus){

             $(".loading").hide();
             $(".loading-bg").hide();
         }
	 });
}

function queryCourseSetting(){
	//判断公司课程设置开关
	$.ajax({
		url : rootPath + "/branchSchool/couseSetting",
		data:{"companyId":$("#companyId").val()},
		type : "post",
		success : function(result){
			if(result==""||result.status==0){
				$(".setting").addClass("none");
			}else{
				$(".setting").removeClass("none");
			}
		}
	});
}