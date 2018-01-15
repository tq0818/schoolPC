/**
 * author zhang.zx
 * 页面js封装
 */
(function($){
	var Form={
		init : function(){
			var $this=this;
			//初始化数据
			$this.loadData();
		},
		loadData : function(){
			$(".courseliList").html('');
			$.ajax({
				url: rootPath+"/simpleClasses/queryDetail",
				type:"post",
				data : {"classtypeId":$("#classtypeId").val(),"teachMethod":"TEACH_METHOD_LIVE"},
				dataType:"json",
				success: function(jsonData){
					$.each(jsonData,function(i,module){
						if(module.chapterFlag&&module.chapterFlag==1){
							if(module.classmoudleNo&&module.classmoudleNo!=null){
								$("#moduleNoIdkc").val(module.classmoudleNo.id);
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
		                     		/*'<div class="action">'+
		                     		'<a href="javascript:void(0);" pid='+module.id+' oid='+lesson.id+' teac='+lesson.teachers+' mark="edit" class="editson"><i class="iconfont">&#xe625;</i></a>'+
		                     		'<a href="javascript:void(0);" pid='+module.id+' oid='+lesson.id+' teac='+lesson.teachers+' mark="del" class="editson"><i class="iconfont">&#xe626;</i></a>'+
		                     		'<a href="javascript:void(0);" style="text-decoration: none;" pid='+module.id+' oid='+lesson.id+' teac='+lesson.teachers+' mark="chose" m="close" class="editson"><i class="iconfont">&#xe623;</i></a>'+
		                     		'<ul class="box none"><a href="javascript:void(0);" ids="'+lesson.id+' teac='+lesson.teachers+'class="courseresource"><li>课程资料</li></a>'+
		                     		'</ul>'+
		                     		'</div>'+*/ceshi(module.id,lesson.id,lesson.teachers)+
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
	                 		'<div class="tt none">'+
	                 		'<span class="h3"><input type="text" value="'+(module.name?module.name:'')+'" class="addcou"/></span>'+
	                 		'<a href="javascript:void(0);"  class="btn btn-default savecon">保存</a>'+
	                 		'</div>'+
	                 		/*'<div class="action">'+
	                 		'<a href="javascript:void(0);" ids='+module.id+' class="editfather" mark="edit"><i class="iconfont">&#xe625;</i></a>'+
	                 		'<a href="javascript:void(0);" ids='+module.id+' class="editfather" mark="del"><i class="iconfont">&#xe626;</i></a>'+
	                 		'<a href="javascript:void(0);" style="text-decoration: none;" class="editfather chosec" m="close" mark="chose"><i class="iconfont">&#xe623;</i></a>'+
	                 		'<ul class="box none"><a href="javascript:void(0);" class="courseresource"><li>课程资料</li></a>'+
	                 		'<a href="javascript:void(0);" ids="'+module.id+'" class="addlec"><li>添加课次</li></a></ul>'+
	                 		'</div>'+*/
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
		                     		'</div>'+ceshi(module.id,lesson.id,lesson.teachers)+
		                     		/*'<div class="action">'+
		                     		'<a href="javascript:void(0);" pid='+module.id+' oid='+lesson.id+' teac='+lesson.teachers+' mark="edit" class="editson"><i class="iconfont">&#xe625;</i></a>'+
		                     		'<a href="javascript:void(0);" pid='+module.id+' oid='+lesson.id+' teac='+lesson.teachers+' mark="del" class="editson"><i class="iconfont">&#xe626;</i></a>'+
		                     		'<a href="javascript:void(0);" style="text-decoration: none;" pid='+module.id+' oid='+lesson.id+' teac='+lesson.teachers+' mark="chose" m="close" class="editson chosec"><i class="iconfont">&#xe623;</i></a>'+
		                     		'<ul class="box none"><a href="javascript:void(0);" ids="'+lesson.id+' teac='+lesson.teachers+'" class=""><li>课程资料</li></a>'+
		                     		'</ul>'+
		                     		'</div>'+*/
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
                                            /*'<div class="action">'+
                                            '<a href="javascript:void(0);" id='+lesson.courseExercise.id+' class="paperDel" mark="del" style="margin-right: 50px"><i class="iconfont">&#xe626;</i></a>'+
                                            '</div>'+*/
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

                                //打开试卷选择框
                                $(".coursePaper").bind("click",function(){
                                    var thisDate = this;
                                    $('.add-layer-bg').fadeIn(200,function(){
                                        $('.w900').fadeIn(200);
                                        $("#resourceId").val($(thisDate).attr("ids"));
                                    })
                                })

                                $(".paperDel").bind("click", function(){
                                    var thisDate = this;
                                    var eid = $(thisDate).attr("id");
                                    if(eid){
                                        $.ajax({
                                            url:rootPath+"/courseExercise/delExercise",
                                            data:{"eid":eid},
                                            type:"post",
                                            dataType:'json',
                                            success:function(data){
                                                if(data == 'success'){
                                                    $(thisDate).parent().parent().parent().remove();
                                                }
                                            }
                                        })
                                    }
                                })
							}
						}
					});

				}
			});
		}
}

	function ceshi(moduleId,lessonId,teachers){
		/*var html = '<div class="action">'+
 		'<a href="javascript:void(0);" pid='+moduleId+' oid='+lessonId+' teac='+teachers+' mark="edit" class="editson"><i class="iconfont">&#xe625;</i></a>'+	
 		'<a href="javascript:void(0);" pid='+moduleId+' oid='+lessonId+' teac='+teachers+' mark="del" class="editson"><i class="iconfont">&#xe626;</i></a>'+
 		'<a href="javascript:void(0);" style="text-decoration: none;" pid='+moduleId+' oid='+lessonId+' teac='+teachers+' mark="chose" m="close" class="editson chosec"><i class="iconfont">&#xe623;</i></a>'+
 		'<ul class="box none"><a href="javascript:void(0);" ids="'+lessonId+' teac='+teachers+'" class="courseresource"><li>课程资料</li></a>'+
 		'</ul>'+
 		'</div>';*/
		
		var html="";
		return html;
	}
	$(document).ready(function(){
		Form.init();
	})

	window.Form=Form;
})(jQuery)