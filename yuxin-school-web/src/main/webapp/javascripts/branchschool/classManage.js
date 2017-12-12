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
//    点击添加隐藏弹窗
    $('.addClassManagement').click(function(){
        $('.popupAddCourse').hide();
        $('.popupOpacity').hide();
    });
    
    $('.searchClass').click(function(){
    	classListOfOtherSchool(1);
    });
});

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