$(function(){
	classManageDetail(1);
	$(".searchContents").click(function(){
		classManageDetail(1);
	});
	
	$('.addCourse').click(function(){
        $('.popupAddCourse').show();
        $('.popupOpacity').show();
    });
    $('.closePopupAddCourse').click(function(){
        $('.popupAddCourse').hide();
        $('.popupOpacity').hide();
    });
    
    $('.searchClass').click(function(){
    	classListOfOtherSchool(1);
    });
});

function addClassManagement(obj){
	$.confirm("是否添加该课程？",function(s){
        if(s==true){
       	 var classId=$(obj).attr("data_id");
       	 $.ajax({
       			url : rootPath + "/classTypeManage/addClassType",
       			type:"post",
       			data:{"companyId":$("#companyId").val(),"schoolId":$("#schoolId").val(),"classTypeId":classId},
       			dataType:"json",
       			beforeSend:function(XMLHttpRequest){
       	              $(".loading").show();
       	              $(".loading-bg").show();
					 $('.popupAddCourse').hide();
				     $('.popupOpacity').hide();
       	         },
       			success:function(data){
       				if("success"==data.result){
       					 $.msg("添加课程成功");
       					 $('.popupAddCourse').hide();
       				     $('.popupOpacity').hide();
       				     classManageDetail(1);
       				}else if("error"!=data.result){
       					 $.msg(data.result);
       				}else{
       					$.msg("添加课程失败");
       				}
       			},
       			complete:function(XMLHttpRequest,textStatus){
       				$(".loading").hide();
       	            $(".loading-bg").hide();
       	        }
       		});
        }
    });
}

function classManageDetail(pageNo){
	$.ajax({
		url : rootPath + "/classTypeManage/classManageDetail",
		type:"post",
		data:{"companyId":$("#companyId").val(),"name":$("#name").val(),"livestatus":$("#livestatus").val(),
			"page":pageNo,"pageSize":$.trim($("#pageSize").val())},
		dataType:"html",
		beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		success:function(data){
			$(".tbodyList").html(data);
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
		});
}

function classListOfOtherSchool(pageNo){
	$.ajax({
		url : rootPath + "/classTypeManage/classListOfOtherSchool",
		type:"post",
		data:{"companyId":$("#companyId").val(),"className":$("#searchClassName").val(),
			"schoolName":$("#searchSchoolName").val(),"page":pageNo,"pageSize":'5'},
		dataType:"html",
		beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		success:function(data){
			$(".searchList").html(data);
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
		});
}