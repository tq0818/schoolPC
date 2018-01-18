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
    
    $('.closeCourse').click(function(){
        $('.popupContainerCourse').hide();
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
			pageOtherData();
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
		});
}
function pageOtherData(){
	$(".classpagination").html("");
	var rowCount=$('#sRowCount').val();
	var pageNo=$("#sPageNo").val();
	var pageSize=$("#sPageSize").val();
	$(".classpagination").pagination(rowCount, {
		next_text : "下一页",
		prev_text : "上一页",
		current_page :pageNo,
		link_to : "javascript:;",
		num_display_entries : 5,
		items_per_page :pageSize,
		num_edge_entries : 1,
		callback : function(page, jq) {
			var pageNo = page + 1;
			classListOfOtherSchool(pageNo);
		}
	});
}
function showCourse(obj){
	 $('.popupContainerCourse').show();
     $('.popupOpacity').show();
     var classId=$(obj).attr("data-id");
     queryCourseDetail(classId);
}

function queryCourseDetail(classId){
	$(".courseliList").html('');
	$.ajax({
		url: rootPath+"/simpleClasses/queryDetail",
		type:"post",
		data : {"classtypeId":classId,"teachMethod":"TEACH_METHOD_LIVE"},
		dataType:"json",
		success: function(jsonData){
			$.each(jsonData,function(i,module){
				if(module.chapterFlag&&module.chapterFlag==1){
					if(module.classmoudleNo&&module.classmoudleNo!=null){
						$.each(module.classmoudleNo.classModuleLessons,function(i,lesson){
							var lec='<li class="item item-chapter" ids="'+lesson.id+'" moduleId="'+module.id+'">'+
							'<div class="ball"></div><div class="line"></div>'+
                    		'<div class="content">'+
                    		'<div class="tt">'+
                    		'<span class="h3" title='+(lesson.lessonName?lesson.lessonName:"")+'>'+(lesson.lessonName?(lesson.lessonName.length>8?lesson.lessonName.substring(0,8)+"...":lesson.lessonName):"")+'</span><span>共'+(lesson.lessonHour?lesson.lessonHour:"0")+'课时</span>'+
                    		'</div>'+
                    		'<div class="info">'+
                    		'<span class="time">'+lesson.lessonDate+'  '+(lesson.weekType?lesson.weekType:"")+'  '+(lesson.lessonTimeStart?lesson.lessonTimeStart:'')+'</span>'+
                    		'<span></span>'+
                    		'<span class="people">'+(lesson.teachers=='del'?'此老师已删除':(lesson.teachersName?lesson.teachersName:""))+'</span>'+
                    		'</div>'+
                    		'</div></li>';
							$(".courseliList").append(lec);
						});
					}
				}else{
					var chapter='<li class="item item-chapter" id="father'+module.id+'" ids="'+module.id+'" sort="'+module.sort+'" modulenoid="'+(module.classmoudleNo?module.classmoudleNo.id:"")+'">'+
            		'<div class="ball"></div><div class="line"></div>'+
            		'<div class="content">'+
            		'<div class="tt">'+
            		'<span class="h3">'+(module.name?module.name:"")+'</span><span class="sta">未排课</span><span>共'+(module.totalClassHour?module.totalClassHour:"0")+'课时</span>'+
            		'</div>'+
            		'</div>'+
            		'</li>';

                   $(".courseliList").append(chapter);
					if(module.classmoudleNo&&module.classmoudleNo!=null){
						if((module.classmoudleNo.classModuleLessons).length<=0){
							$("#father"+module.id).find("span.sta").text("未排课");
						}else{
							$("#father"+module.id).find("span.sta").text("已排课");
						}
						$.each(module.classmoudleNo.classModuleLessons,function(i,lesson){
							var nowDate=dateToStr("yyyy-MM-dd",new Date());
							if(lesson.lessonDate<nowDate){
								$("#father"+module.id).find("span.sta").text("已结课");
							}
							var lec='<li class="item item-lession chird'+module.id+'" ids="'+lesson.id+'">'+
                    		'<div class="content">'+
                    		'<div class="tt">'+
                    		'<span class="h3" title='+(lesson.lessonName?lesson.lessonName:"")+'>'+(lesson.lessonName?(lesson.lessonName.length>8?lesson.lessonName.substring(0,8)+"...":lesson.lessonName):"")+'</span><span>共'+(lesson.lessonHour?lesson.lessonHour:"0")+'课时</span>'+
                    		'</div>'+
                    		'<div class="info">'+
                    		'<span class="time">'+lesson.lessonDate+'  '+(lesson.weekType?lesson.weekType:"")+'  '+(lesson.lessonTimeStart?lesson.lessonTimeStart:'')+'</span>'+
                    		'<span></span>'+
                    		'<span class="people">'+(lesson.teachers=='del'?'此老师已删除':(lesson.teachersName?lesson.teachersName:""))+'</span>'+
                    		'</div>'
                    		'</div></li>';
							$(".courseliList").append(lec);
                           //添加试卷
                           if(lesson.tikuPaper && lesson.tikuPaper.id){
                               var paper='<li class="item item-lession-thr chird" id="'+lesson.courseExercise.id+'" paperId="'+lesson.tikuPaper.id+'" >'+
                                   '<div class="ball"></div><div class="line"></div>'+
                                   '<div class="content">'+
                                   '<div class="tt">'+
                                   '<span class="h3">课后练习：'+(lesson.tikuPaper.paperName?lesson.tikuPaper.paperName:"")+'</span>'+
                                   '</div>'+
                                   '</div>'+
                                   '</li>';
                               $(".courseliList").append(paper);
                           }
						});
						if(module.classmoudleNo.classModuleLessons.length>0){
							$("#father"+module.id).removeClass("non-children");
						}else{
							$("#father"+module.id).addClass("non-children");
						}
					}
				}
			});
		}
	});
}