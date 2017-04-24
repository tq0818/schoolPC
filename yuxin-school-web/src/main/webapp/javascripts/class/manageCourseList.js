$(document).ready(function(){
	//加载数据
	loadData();
	 //点击左侧菜单
	 $("#course_manage").on('click','li',function(){
		 var url=$(this).attr("mark");
		 window.location.href=rootPath+url;
	 });
	 //返回
	 $(".hcancle").on('click',function(){
		 window.location.href=rootPath+"/company/companyService";
	 });
	 //选择课程查看权限
	 $(".coursewacthlist").on('click','div.timetable-box',function(){
		    if($(this).hasClass("active")){
		    	return;
		    }
			var data={};
			var functionset=new Array();
			if(!$(this).hasClass("active")){
				$(this).addClass("active").siblings("div").removeClass("active");
			}
			var code="COURSE_LIST_SHOW";
			$(".coursewacthlist").find(".timetable-box").each(function(i){
				var id=$(this).attr("ids");
				var type=$(this).attr("type");
				var functionName=$(this).attr("tent");
				var status="0";
				if($(this).hasClass("active")){
					status="1";
				}
				functionset.push({functionCode:code,functionName:functionName,content:type,status:status});
			});
			data.functionset=JSON.stringify(functionset);
			$.ajax({
				type : "post",
				url : rootPath+"/classManage/addcourseFunction",
				data : data,
				success: function(jsonData){
					$.msg("修改成功");
					//loadData();
				}
			});
		 
	 });
});


function loadData(){
	$.ajax({
		type: "post",
		url:  rootPath+"/classManage/queryShowCourse",
		beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $(".loading-bg").show();
        },
		success: function(jsonData){
			if(jsonData.length>0){
				$.each(jsonData,function(i,item){
					if(item.content == "USER_BUY_THIS" && item.status == 1){
						$(".coursewacthlist").find(".buy_courses").addClass("active").attr("ids",item.id);
					}else if(item.content == "USER_BUY_OTHER" && item.status == 1){
						$(".coursewacthlist").find(".buy_anycourses").addClass("active").attr("ids",item.id);
					}else if(item.content == "USER_LOGIN" && item.status == 1){
						$(".coursewacthlist").find(".login_users").addClass("active").attr("ids",item.id);
					}else if(item.content == "USER_ALL" && item.status == 1){
						$(".coursewacthlist").find(".all_users").addClass("active").attr("ids",item.id);
					}
				});
			}else{
				$(".coursewacthlist").find(".buy_courses").addClass("active");
			}
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
     }
	});
}
