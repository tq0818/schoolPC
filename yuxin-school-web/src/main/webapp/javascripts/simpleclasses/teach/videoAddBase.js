
(function($){
	var Page=function(){
		this.options={}
	}
	var getChapterName=function(){
		var length=$(".t-c-l").find(".sortable").find(".dis").length;
		return $.changeNum(length+1);
	}
	var getLecureName=function(chapter){
		var length=chapter.find(".add-xiaojie").length;
		return $.changeNum(length+1);
	}
	
	
	var getCname=function(){
		return $("#chapterName").val()?$("#chapterName").val():"章";
	}
	var getLname=function(){
		return $("#lectureName").val()?$("#lectureName").val():"节";
	}
	
	Page.prototype={
		//机构开启的服务
		service : {
			tikuService:true //题库服务
		},
		init: function(){
			$selectMenu("course_class_type");
			var $this=this;
			/*****************这个抄到前台**********************/
			//查询自定义的章节名称
			$.ajax({
				url : rootPath+"/courseVideoChapter/customChapter",
				type: "post",
				dataType: "json",
				success: function(data){
					$("body").append('<input type="hidden" id="chapterName" value="'+(data.chapterName?data.chapterName:"")+'"/>');
					$("body").append('<input type="hidden" id="lectureName" value="'+(data.lectureName?data.lectureName:"")+'"/>');
					$this.service.tikuService = data.tikuService;
					//查询数据
					$this.loadData($("#moduleId").val());
				}
			});
			/***********************************************/
			this.initBase();
			this.init800();
			this.init900();
			this.init1000();
			this.init1100();
			this.init1200();
			this.init1300();
			
		},
		loadData: function(moduleId){
			var $this=this;
			if(!moduleId){
				$('.base-sort').sortable({
					placeholder: "ui-state-highlight",
					update:function(event,ui){
						$this.sort(ui.item);
					},
					delay: 200,
					start: $this.collapseAll
				}).disableSelection();
				$('.base-sort').find(".sortable").sortable({
					placeholder: "ui-state-highlight",
					update:function(event,ui){
						$this.sort(ui.item.siblings());
						$this.sort(ui.item);
					},
					receive:function(event,ui){
						$this.mvLecture(ui.sender,$(event.target).parents(".dis"),ui.item);
					},
					delay: 200,
					connectWith: ".son"
				}).disableSelection();
				return;
			}
			
			
			$.ajax({
				url: rootPath+"/courseVideoChapter/load/"+moduleId,
				type:"post",
				dataType:"json",
				success: function(jsonData){
					$.each(jsonData,function(i){
						var chapter=jsonData[i];
						var html='<li id="chapter_'+chapter.id+'" class="dis" sort="'+chapter.chapterOrder+'"><p class="father">'+
						'<i class="iconfont4">&#xe630;</i>'+
						'<i class="iconfont2 open">&#xe62f;</i>'+
	                    '<a href="javascript:;">第'+getChapterName()+getCname()+'&nbsp;&nbsp;<b>'+(chapter.chapterName?chapter.chapterName:"")+'</b></a>'+
	                    '<i class="iconfont">&#xe626;</i>'+
	                    '<i class="iconfont3 add-btns" >&#xe629;'+
	                    '<b class="add-item">'+
	                    '<span class="xiaojie">新增小节</span>'+
	                    ($this.service.tikuService?'<span class="ceyan">新增测验</span>':'')+
	                    '</b>'+
	                    '</i>'+
	                    '</p><ul class="son sortable"></ul></li>';
						$(".t-c-l").find(".base-sort").append(html);
						$(".t-c-l").find("#chapter_"+chapter.id).data("info",chapter);
						var k = 0;
						$.each(chapter.lectureAndTests,function(j){
							var lecture=chapter.lectureAndTests[j];
							if(lecture.type == 1){
								var sub_html='<li id="lecture_'+lecture.id+'" class="class-son" draggable="true">'
								+'<a href="javascript:;"><span class="add-xiaojie"></span>第'
								+$.changeNum(k+1)+getLname()+
								'&nbsp;&nbsp;<b>'+(lecture.name?lecture.name:"")+'</b></a>'+
								'<i class="iconfont4">&#xe630;</i>'+
								'<i class="iconfont">&#xe626;</i></li>';
								$(".t-c-l").find("#chapter_"+chapter.id+" .son").append(sub_html);
								$(".t-c-l").find("#lecture_"+lecture.id).data("info",lecture);
								k++;
							}else if(lecture.type == 2){
								var sub_html='<li id="test_'+lecture.id+'" class="class-son" draggable="true">'
								+'<a href="javascript:;"><span class="iconfont add-ceyan">&#xe6d5;</span>测验'+
								'&nbsp;&nbsp;<b>'+(lecture.name?lecture.name:"")+'</b></a>'+
								'<i class="iconfont4">&#xe630;</i>'+
								'<i class="iconfont">&#xe626;</i></li>';
								$(".t-c-l").find("#chapter_"+chapter.id+" .son").append(sub_html);
								$(".t-c-l").find("#test_"+lecture.id).data("testInfo",lecture);
							}
						});
						$('.base-sort').sortable({
							placeholder: "ui-state-highlight",
							update:function(event,ui){
								$this.sort(ui.item);
							},
							delay: 200,
							start: $this.collapseAll
						});
						$('.base-sort').find(".sortable").sortable({
							placeholder: "ui-state-highlight",
							update:function(event,ui){
								$this.sort(ui.item.find(".dis").eq(0));
								$this.sort(ui.item);
							},
							receive:function(event,ui){
								$this.mvLecture(ui.sender,$(event.target).parents(".dis"),ui.item);
							},
							delay: 200,
							connectWith: ".son"
						}).disableSelection();
					});
					
				},
				error: function(resp,err,msg){
					console.log(resp);
				}
			})
		},
		//保存章
		saveChapter: function(data){
			var $this=this;
			if(!data){
				return ;
			}
			$.ajax({
				url:rootPath+"/courseVideoChapter/saveChapter",
				data: data,
				type:"post",
				dataType:"json",
				success:function(jsonData){
					if(jsonData.flag){
						jsonData = jsonData.chapter;
						
						var chapter=$("#chapter_"+jsonData.id);
						if(chapter.find(".father").find("a b").length>0){
							chapter.find(".father").find("a b").html(jsonData.chapterName);
						}else{
							chapter.find(".father").find("a").html("<b>"+jsonData.chapterName+"</b>");
						}
						chapter.data("info",jsonData);
						console.log(chapter,chapter.data("info"));
//						$this.sort(chapter);
						$.msg("保存成功");
					}else{
						$.msg(jsonData.msg || '保存失败');
					}
				}
			})
		},
		//保存节
		saveLecture: function(data){
			var $this=this;
			if(!data){
				return;
			}
			delete data.updateTime;
			delete data.updator;
			delete data.createTime;
			delete data.publishDate;
			if(data.name){
				delete data.name;
			}
			$.ajax({
				url:rootPath+"/courseVideoLecture/saveLecture",
				data: data,
				type:"post",
				dataType:"json",
				success:function(jsonData){
					if(jsonData.flag){
						jsonData = jsonData.lecture;
						
						var lecture=$("#lecture_"+jsonData.id);
						lecture.find("a b").html(jsonData.lectureName);
						lecture.data("info",jsonData);
						var dd=$(".t-c-r").find(".n-list").data("exercise");
						if(dd){
							dd.resourceId=$(".t-c-r").find(".lecture").attr("value");
							if(dd.paperId){
								dd.topicNum = null;
								dd.tikuChapterId = null;
								dd.tikuSectionId = null;
							}else if(dd.topicNum && dd.tikuChapterId && dd.tikuSectionId){
								dd.paperId = null;
							}
							dd.exerciseType='PRACTICE_AFTER_CLASS';
							$.ajax({
								url: rootPath+"/courseExercise/save",
								data: dd,
								type:"post",
								dataType:"json",
								success:function(){
								}
							})
						}
						$.msg("保存成功");
					}else{
						$.msg(jsonData.msg || '保存失败');
					}
				}
			})
		},
		
		//保存测验
		saveTest : function(data){
			var $this=this;
			if(!data){
				return;
			}
			if($('body').hasClass('ccc'))
				return;
			$('body').addClass('ccc');
			delete data.updateTime;
			delete data.updator;
			$.ajax({
				url:rootPath+"/courseAfterTest/saveTest",
				data: data,
				type:"post",
				async:false,
				dataType:"json",
				success:function(jsonData){
					var test=$("#test_"+jsonData.id);
					test.find("a b").html(jsonData.testName);
					test.data("testInfo",jsonData);
					var dd=$(".t-c-r").find("#courseList"+jsonData.id).data("exercises");
					if(dd && dd.length >0){
						for(var x=0;x<dd.length;x++){
							if(dd[x]){
								var item = dd[x];
								$.ajax({
									url: rootPath+"/courseAfterTestContent/saveTestContent",
									data: item,
									type:"post",
									dataType:"json",
									async:false,
									success:function(data){
										$('body').removeClass('ccc');
										$.msg("保存成功");
									}
								})
							}
						}
					}
					
				}
			})
		},
		delChapter : function(chapter){
			var $this=this;
			$.ajax({
				url: rootPath+"/courseVideoChapter/delChapter/"+chapter.attr("id").substring(8),
				type: "post",
				dataType: "json",
				success: function(jsonData){
					chapter.fadeOut(300,function(){
						chapter.remove();
						$(".t-c-r").find(".chapter").remove();
						$this.sort(chapter)
					})
				}
			})
		},
		delLecture: function(lecture){
			var $this=this;
			$.ajax({
				url: rootPath+"/courseVideoLecture/delLecture/"+lecture.attr("id").substring(8),
				type: "post",
				dataType: "json",
				success: function(jsonData){
					lecture.fadeOut(300,function(){
						var ele=lecture.siblings().eq(0);
						lecture.remove();
						$this.sort(ele);
						$this.showChapter(ele.parents(".dis"));
					})
				}
			})
		},
		delTest: function(test){
			var $this=this;
			$.ajax({
				url: rootPath+"/courseAfterTest/delTest/"+test.attr("id").substring(5),
				type: "post",
				dataType: "json",
				success: function(jsonData){
					test.fadeOut(300,function(){
						var ele=test.siblings().eq(0);
						test.remove();
						$this.sort(ele);
						$this.showChapter(ele.parents(".dis"));
					})
				}
			})
		},
		//排序
		sort: function(ele){
			var list=new Array();
			if(ele.hasClass("dis")){
				$(".base-sort").find(".dis").each(function(i){
					var chapter={};
					var title=$(this).find(".father a b").html();
					$(this).find(".father a").html("第"+$.changeNum(i+1)+getCname()+"&nbsp;&nbsp;<b>"+title+"</b>");
					chapter.id=$(this).attr("id").substring(8);
					chapter.chapterOrder=i+1;
					list.push(chapter);
				})
				$.ajax({
					url: rootPath+"/courseVideoChapter/sortChapter",
					data: "list="+JSON.stringify(list),
					type: "post",
					dataType: "json",
					success:function(jsonData){
						$.each(jsonData,function(i,data){
							$("#chapter_"+data.id).data("info",data);
						})
					}
				})
			}
			
			
			if(ele.hasClass("class-son")){
				var j = 0;
				ele.parents(".dis").find(".class-son").each(function(i){
					var type = $(this).find('span').attr('class');
					if(type == 'add-xiaojie'){
						var title=$(this).find("a b").html();
						$(this).find("a").html("<span class='add-xiaojie'></span>第"+$.changeNum(j+1)+getLname()+" &nbsp;&nbsp;<b>"+title+"</b>");
						j++;
					}
					if(type =='iconfont add-ceyan'){
						var title1=$(this).find("a b").html();
						$(this).find("a").html('<span class="iconfont add-ceyan">&#xe6d5;</span>' +
								'测验'+'&nbsp;&nbsp;<b>'+title1+'</b>');
						
					}
				})
			}
			
			
			if(ele.hasClass("class-son")){
				ele.parents(".dis").find(".class-son").each(function(i){
					var lecture={};
					var type = $(this).find('span').attr('class');
					if(type == 'add-xiaojie'){
						lecture.id=$(this).attr("id").substring(8);
						lecture.lectureOrder=i+1;
						//小节
						lecture.type=1;
					}
					if(type=='iconfont add-ceyan'){
						lecture.id=$(this).attr("id").substring(5);
						lecture.lectureOrder=i+1;
						//测验
						lecture.type=2;
					}
					list.push(lecture);
				})
				$.ajax({
					url: rootPath+"/courseVideoLecture/sortLecture",
					data: "list="+JSON.stringify(list),
					type: "post",
					dataType: "json",
					success:function(jsonData){
						$.each(jsonData,function(i,data){
							if(data.type == 1){
								$("#lecture_"+data.id).data("info",data);
							}else{
								$("#test_"+data.id).data("info",data);
							}
						})
					}
				})
			}
		},
		addChapter : function(chapter,callback){
			var para={};
			para.moduleId=$("#moduleId").val();
			para.itemOneId=$("#itemOneId").val();
			para.itemSecondId=$("#itemSecondId").val();
			para.classTypeId=$("#classTypeId").val();
			para.classTypeName=$("#classTypeName").val();
			para.chapterOrder=chapter.index();
			$.ajax({
				url: rootPath+"/courseVideoChapter/addChapter",
				data: para,
				type: "post",
				dataType: "json",
				success:function(data){
					chapter.attr("id","chapter_"+data.id);
					$("#chapter_"+data.id).data("info",data);
					$("#moduleId").val(data.moduleId);
					if(callback){
						callback();
					}
				}
			})
		},
		addLecture: function(lecture,callback){
			var $this=this;
			var para={};
			para.chapterId=lecture.parents(".dis").attr("id").substring(8);
			para.lectureOrder=lecture.index();
			$.ajax({
				url: rootPath+"/courseVideoLecture/addLecture",
				data: para,
				type: "post",
				dataType: "json",
				success:function(data){
					lecture.attr("id","lecture_"+data.id);
					$("#lecture_"+data.id).data("info",data);
					$('.base-sort').find(".sortable").sortable({
						placeholder: "ui-state-highlight",
						update:function(event,ui){
							$this.sort(ui.item.siblings());
							$this.sort(ui.item);
						},
						receive:function(event,ui){
							$this.mvLecture(ui.sender,$(event.target).parents(".dis"),ui.item);
						},
						delay: 200,
						connectWith: ".son"
					})
					if(callback){
						callback();
					}
				}
			})
		},
		addTest: function(test,callback){
			var $this=this;
			var para={};
			para.chapterId=test.parents(".dis").attr("id").substring(8);
			para.sort=test.index();
			$.ajax({
				url: rootPath+"/courseAfterTest/addTest",
				data: para,
				type: "post",
				dataType: "json",
				success:function(data){
					test.attr("id","test_"+data.id);
					$("#test_"+data.id).data("testInfo",data);
					$('.base-sort').find(".sortable").sortable({
						placeholder: "ui-state-highlight",
						update:function(event,ui){
							$this.sort(ui.item.siblings());
							$this.sort(ui.item);
						},
						receive:function(event,ui){
							$this.mvLecture(ui.sender,$(event.target).parents(".dis"),ui.item);
						},
						delay: 200,
						connectWith: ".son"
					})
					if(callback){
						callback();
					}
				}
			})
		},
		showChapter : function(chapter){
			chapter=$("#"+chapter.attr("id"));//别删，有用
			if(chapter.attr("id")){
				var chapter_id=chapter.attr("id").substring(chapter.attr("id").indexOf("_")+1);
				var chapterDetail='<div class="t-c-r-t chapter" value="'+chapter_id+'">'+
	                '<p class="c">'+
	                '<span class="c-title">章的名称</span>'+
	                '<span class="c-content"><input class="chapterName" type="text" maxlength="20" value="" placeholder="章的名称"></span>'+
	            '</p>'+
	            '<p class="c">'+
		        '<span class="c-title"></span>'+
	            '<span class="c-content">'+
	            '<a href="javascript:;" class="btn q-btn-primary save" id="q-save">保存</a>'+
	            '</span>'+
	            '</p>'+
	            '</div>';
				$(".t-c-r").html(chapterDetail);
			}else{
				var chapterDetail='<div class="t-c-r-t chapter" value="">'+
	                '<p class="c">'+
	                '<span class="c-title">章的名称</span>'+
	                '<span class="c-content"><input class="chapterName" maxlength="20" type="text" value="" placeholder="章的名称"></span>'+
	            '</p>'+
	            '<p class="c">'+
		        '<span class="c-title"></span>'+
	            '<span class="c-content">'+
	            '<a href="javascript:;" class="btn btn-mini btn-primary save">保存</a>'+
	            '</span>'+
	            '</p>'+
				 '</div>';
				$(".t-c-r").html(chapterDetail);
			}

			for(var i=1;i<=$(".t-c-l").find(".dis").length;i++){
				$(".t-c-r").find("#sortChapter").append('<option value="'+i+'">'+i+'</option>');
			}
			var data=chapter.data("info");
			if(data){
				//TODO 填充数据
				if(data.chapterName){
					$(".t-c-r").find(".chapterName").val(data.chapterName);
				}
				if(data.chapterOrder){
					$(".t-c-r").find("#sortChapter").find("option[value='"+data.chapterOrder+"']").attr("selected","selected");
				}else{
					$(".t-c-r").find("#sortChapter").find("option[value='"+(chapter.index()+1)+"']").attr("selected","selected");
				}
				
			}
		},
		showLecture : function(lecture){
			var chapter_id=lecture.parents(".dis").attr("id").substring(lecture.parents(".dis").attr("id").indexOf("_")+1);
			var lecture_id=lecture.attr("id").substring(lecture.attr("id").indexOf("_")+1);
			var lectureDetail='<div class="project-part"> '+'<h3 class="add-title">'+
                '课次信息'+
                '</h3>'+'<div class="t-c-r-t lecture" value="'+lecture_id+'" fatherValue="'+chapter_id+'">'+
                '<p class="c">'+
                '<span class="c-title">类型：</span>'+
                '<span class="c-content" id="c-content-one">'+		
                '<a href="javascript:;" id="vedio-paper" >'+
                '<input type="radio" name="type-set" class="radio" id="type-paper" value="paper" checked="true">'+
                '<label style="display:inline;" for="type-paper">视频</label>	'+
                '</a>'+
                   '<a href="javascript:;" class="q-type-choice"  >'+
                       '<input type="radio" name="type-set" class="radio" id="type-paper2" value="chapter">'+
                   '<label style="display:inline;" for="type-paper2">Flash</label>'+	
                   '</a>'+
                   '<a href="javascript:;" class="q-type-choice" >'+
                   '<input type="radio" name="type-set" class="radio" id="type-paper3" value="paper" >'+
                   '<label style="display:inline;" for="type-paper3">音频</label>'+
                   '</a>'+
                   '<a href="javascript:;" class="q-type-choice"  >'+
                   '<input type="radio" name="type-set" class="radio" id="type-paper4" value="chapter" >'+
                   '<label style="display:inline;" for="type-paper4">PPT</label>'+
                   '</a>'+
                   '<a href="javascript:;" class="q-type-choice"  >'+
                   '<input type="radio" name="type-set" class="radio" id="type-paper5" value="paper" >'+
                   '<label style="display:inline;" for="type-paper5">文档</label>	'+
                   '</a>'+
                   '</span>'+
                   '</p>'+
				'<p class="c">'+
		        '<span class="c-title">节的名称：</span>'+
		        '<span class="c-content">'+
		        '<input class="lectureName" maxlength="20" type="text" placeholder="输入名称">'+
		        '<span id="free-sorce" class="none">'+'<input type="checkbox" style="position:relative;top:2px;">免费资源</span>'+
		        '</span>'+
		        '</p>'+
		        '<p class="c" id="open-veido">'+
		        '<span class="c-title">公开视频：</span>'+
		        '<span class="c-content">'+
		        '<input type="checkbox" name="open-set" class="radio" id="open-paper" value="paper" >'+
                '<label style="display:inline;" for="open-paper">免费</label>	'+	
                '<input type="checkbox" name="open-set" class="radio" id="open-paper2" value="chapter">'+
                '<label style="display:inline;" for="open-paper2">试听</label>'+	
		        '</span>'+
		        '</p>'+
		        '<p class="c vedio-name">'+
		        '<span class="c-title">视频名称：</span>'+
		        '<span class="c-content">'+
		        '<input type="text" class="video_name q-text"/> <input type="hidden" class="video_id"><input type="hidden" class="file_id"><input type="hidden" class="publishStatus"/><input type="hidden" class="publishDate"/>'+
		        '<input type="button" class="btn btn-sm btn-primary chooseVideo"  value="从库中选择">'+
		        '</span>'+
		        '</p>'+'<div class="tab-cont">'+
		        '<div class="tab-list">'+'<span  class="tab-left active" >课程资料</span>'+
                '<span class="tab-right"  id="ss2">课后练习</span>'+
                '</div>'+    
                 '<div class="tab-item">'+  
                '<div class="q-item one ">'+
                '<p class="c">'+
                 '<span class="c-title">上传资料：</span>' +
                 '<span class="c-content">'+
                  '<i class="iconfont">&#xe6a3;</i>'+           
                    '<a href="javascript:;" class="btn btn-mini btn-primary btn-upload">上传资料</a>'+                
                        '</span>'+            
                           '</p>'+     
                        '<p class="c clear q-c" style="margin-top:-6px;">'+
                        '<span id="spanlist" class="c-title">资料列表：</span>'+
                          '<span class="c-content" id="courseList1'+lecture_id+'">'+  
                           '</span>'+ 
                            '</p>'+   
                        '</div>'+
                    '<div class="q-item two none">'+
                    '<p class="c">'+
                        '<span class="c-title">课后练习：</span>'+
                            '<span class="c-content">'+
                            	'<a href="javascript:;" class="bd-exam hasClick" >'+
                               '<input type="radio" name="bind-set" class="radio" id="bind-paper" value="paper" checked="true">'+
                                '<label style="display:inline;" for="bind-paper">绑定试卷</label>'+   
                                '</a>'+
                                '<a href="javascript:;" class="bd-part" >'+	
                               '<input type="radio" name="bind-set" class="radio" id="bind-chapter" value="chapter">'+
                                '<label style="display:inline;" for="bind-chapter">绑定章节</label>'+   
                                '</a>'+
                               	'</span>'+
                            '</p>'+
                        '<div class="c" style="margin-bottom:20px;" id="q-chose">'+
                        '<span class="c-content" id="q-c-cont">'+
                          '<a href="javascript:;" class="q-btn btn btn-default choosePaper" id="selectpc">选择试卷</a>'+  
                                '</span></div>'+
                       '<p class="c clear q-c">'+
                       '<span class="c-title" id="shiquanorzhangjiexinxi">试卷信息：</span>'+
                           '<span class="c-content n-list" >'+ 
                            ' </span>'+
                            '   </p>'+
                            '</div>'+
                            '</div>'+
                            '</div>'+   
	            '<p class="c" style="text-align:center;padding:30px;">'+
	            '<a href="javascript:;" class="btn btn-primary q-btn-primary save">保存</a>'+
	            '</p>'+
		    '</div>'+
		    '</div>';
		   
			$(".t-c-r").html(lectureDetail);
			this.queryCorseResoure(lecture_id);
			for(var i=1;i<=lecture.parent().find("li").length;i++){
				$(".t-c-r").find("#sortLecture").append('<option value="'+i+'">'+i+'</option>');
			}
			var data=lecture.data("info");
			//TODO 填充数据
			if(data){
				if(data.name)
				$(".lectureName").val(data.name);
				else
					$(".lectureName").val(data.lectureName);
				if(data.lectureOrder){
					$(".t-c-r").find("#sortLecture").find("option[value='"+data.lectureOrder+"']").attr("selected","selected");
				}else{
					$(".t-c-r").find("#sortLecture").find("option[value='"+(lecture.index()+1)+"']").attr("selected","selected");
				}
				if(data.videoId){
					$('.t-c-r').find('#type-paper').trigger('click');
					$.ajax({
						url : rootPath + "/video/"+data.videoId,
						type: "get",
						dataType: "json",
						success : function(jsonData){
							$(".video_name").val(jsonData.videoName);
						}
					})
					if(data.freeFlag==1){
						$("#open-paper").attr("checked",true);
					}else if(data.freeFlag==2){
						$("#open-paper2").attr("checked",true);
					}
					$(".video_id").val(data.videoId);
				}
				
				if(data.fileId){
					if(data.freeFlag == 1){
						$('.t-c-r').find('#free-score').find('input[type="checkbox"]').attr('checked','checked');
					}
					
					$.ajax({
						url : rootPath + "/resourceList/"+data.fileId,
						type: "get",
						dataType: "json",
						success : function(jsonData){
							$(".video_name").val(jsonData.fileName);
							if(jsonData.fileCategory == 'flash')
								$('.t-c-r').find('#type-paper2').trigger('click');
							else if(jsonData.fileCategory == 'audio')
								$('.t-c-r').find('#type-paper3').trigger('click');
							else if(jsonData.fileCategory == 'ppt')
								$('.t-c-r').find('#type-paper4').trigger('click');
							else if(jsonData.fileCategory == 'docs')
								$('.t-c-r').find('#type-paper5').trigger('click');
						}
					})
					$('.file_id').val(data.fileId);
				}
			}
			
			$.ajax({
				url: rootPath+"/courseExercise/find",
				data: {
					resourceId:lecture_id,
					resourceType: "TEACH_METHOD_VIDEO",
				},
				type: "post",
				dataType: "json",
				success: function(exercise){
					if(exercise.tikuResourceType=="PAPER"){
						$('.t-c-r').find('#shiquanorzhangjiexinxi').text('试卷信息：');
						$(".t-c-r").find(":radio[id='bind-paper']").data("exercise",exercise).trigger('click');
						$(".t-c-r").find(".n-list").html('<b><i class="list-item">'+exercise.paperName+'</i> <a class="del" eid="'+exercise.id+'"><strong>删除</strong></a></b>');
					}else{
						$('.t-c-r').find('#shiquanorzhangjiexinxi').text('章节信息：');
						$(".t-c-r").find(":radio[id='bind-chapter']").data("exercise",exercise).trigger('click');
						$(".t-c-r").find(".n-list").html('<tr><td width="30%">'+(exercise.chapterName?exercise.chapterName:"")+'</td><td width="30%">'+(exercise.sectionName?exercise.sectionName:"")+'</td><td width="40%"><a href="javascript:;" eid="'+exercise.id+'">删除</a></td></tr>');
					}
					$(".t-c-r").find(".n-list").data("exercise",exercise);
				}
				
			})
			
		},
		showTest : function(test){
			var chapter_id=test.parents(".dis").attr("id").substring(test.parents(".dis").attr("id").indexOf("_")+1);
			var test_id=test.attr("id").substring(test.attr("id").indexOf("_")+1);
			var testDetail = '<div class="ceshi-part">'+
            '<h3 class="add-title">测验信息</h3>'+
            '<div class="t-c-r-t test" value="'+test_id+'" fathervalue="'+chapter_id+'">'+
            '<p class="c">'+
            '<span class="c-title">类型：</span>'+   
            '<span class="c-content">'+
            '<a href="javascript:;" class="ceyan-choice1 bd-exam" >'+
            '<input type="radio" name="type-deb" class="radio" id="type-paper-ceyan" value="paper" checked="true" >'+		
            '<label style="display:inline;" for="type-paper-ceyan">绑定试卷</label>'+  
            '</a>'+       
            '<a href="javascript:;" class="ceyan-choice2 bd-part" >'+ 		
            '<input type="radio" name="type-deb" class="radio" id="type-paper2-ceyan" value="chapter" >'+  
            '<label style="display:inline;" for="type-paper2-ceyan">绑定章节</label>'+  
            '</a></span></p>'+      
             '<p class="c">'+  	
             '<span class="c-title">测验名称：</span>'+ 
             '<span class="c-content">'+ 
             '<input class="lectureName q-text" maxlength="20" type="text" placeholder="输入名称"></span>'+
              '</p>'+ 	
              '<p class="c">'+ 
              '<span class="c-title" id="dabiaofenshu1">达标分数：</span>'+
              '<span class="c-content">'+
              '<input type="text" class="video_name q-text1" >'+
              '</span>'+      
              '</p>'+
               '<p class="c" style="color:#666666;font-size:12px;margin-top:-12px;">'+
               '<span class="c-title"></span>'+
               '<span class="c-content">'+
               '<input type="checkbox" id="dabiaoxuexi" style="position:relative;top:2px;" >'+
                '<span id="cont-change">分数</span>'+'未达标不允许继续学习'+
               '</span></p>'+  
               '<p class="c exam-number none">'+
               '<span class="c-title">考试题目数：</span>'+
               '<span class="c-content">'+
               '<input type="text" class="video_name q-text" id="kaoshinumber">'+
                '</span>'+   
                '</p>'+
                '<div class="c" style="margin-bottom:20px;" id="q-chose">'+
                '<span class="c-content" id="q-c-cont">'+
                '<a href="javascript:;" id="selectShiorZhang" class="q-btn btn btn-default choosePaper1">选择试卷</a>'+   
                 '</span></div>'+ 
                 '<p class="c clear q-c" style="margin-top:-6px;">'+
                 '<span class="c-title" id="ziliaotitle">试卷列表：</span>'+
                 '<span class="c-content" id="courseList'+test_id+'"></span></p>'+
                '<p class="c" style="text-align:center;padding:30px;">'+    
                '<a href="javascript:;" class="btn btn-primary q-btn-primary" id="saveCeyan">保存</a>'+
                '</span></p>'+
                '</div>'+
                '</div>';
			$(".t-c-r").html(testDetail);
			var data=test.data("testInfo");
			if(data){
				$(".t-c-r").find("#courseList"+test_id).html('');
				if(data.name)
				$('.lectureName').val(data.name);
				else
					$('.lectureName').val(data.testName);
				
				$('.q-text1').val(data.testScore);
				if(data.allowContinue == 0){
					$('.t-c-r').find('#dabiaoxuexi').attr('checked','checked');
				}
				if(data.testType == 0){
					$('.t-c-r').find('#type-paper2-ceyan').trigger('click');
					$('.t-c-r').find('#kaoshinumber').val(data.testTotalNum);
					/*$.ajax({
						url:rootPath+"/courseAfterTestContent/findContentListByTestId",
						data:{testId:data.id},
						type:"post",
						dataType:"json",
						success:function(jsonData){
							if(jsonData && jsonData.length>0){
								$.each(jsonData,function(i,data){
									$(".t-c-r").find("#courseList"+test_id).append('<b><i class="list-item">'+data.chapterName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+data.sectionName+'</i><a class="delrel" mark="'+data.id+'" delIndex = "'+i+'"href="javascript:;"><strong>删除</strong></a></b>');	
								})
							}
							$(".t-c-r").find("#courseList"+test_id).data('exercises',jsonData);
						}
					})*/
				}else{
					$('.t-c-r').find('#type-paper-ceyan').trigger('click');
					/*$.ajax({
						url:rootPath+"/courseAfterTestContent/findContentListByTestId2",
						data:{testId:data.id},
						type:"post",
						dataType:"json",
						success:function(jsonData){
							if(jsonData && jsonData.length>0){
								$.each(jsonData,function(i,data){
									$(".t-c-r").find("#courseList"+test_id).append('<b><i class="list-item">'+data.paperName+'</i> <a class="del" mark1="'+data.paperId+'"><strong>删除</strong></a></b>');	
								})
							}
							$(".t-c-r").find("#courseList"+test_id).data('exercises',jsonData);
						}
						
					})*/
				}
			}
		},
		searchPapers: function(page){
			$this = this;
			var $tab=$(".w900"),search={};
			$tab.find("#data_table_2").find("tr").remove();
			search.tikuCategoryId=$("#choose_tiku").find("option:selected").val();
			search.tkuSubjectId=$("#choose_item").find("option:selected").val();
			search.paperName=$("#choose_paper3").val();
			search.page=page;
			$.each(search,function(k,v){
				if(!v){
					delete search[k];
				}
			})
			$tab.find(".pagination").html('');
			$.ajax({
				url: rootPath+"/tikuPaper/search1",
				data:search,
				type:"post",
				dataType:"json",
				success:function(jsonData){
					if(jsonData.data && jsonData.data.length>0){
						$.each(jsonData.data,function(i,data){
							$tab.find("#data_table_2").find("tbody")
							.append('<tr id="'+data.id+'">'+
									'<td style="width:25%;" title="'+data.paperName+'">'+(data.paperName?data.paperName.length>16?data.paperName.substring(0,16):data.paperName:"")+'</td>'+
				                    '<td style="width:25%;">'+(data.categoryName?data.categoryName:"")+'</td>'+
				                    '<td style="width:25%;">'+(data.subjectName?data.subjectName:"")+'</td>'+
				                    '<td style="width:25%;">'+(data.dictName?data.dictName:"")+'</td>'+
				                '</tr>');
							$tab.find("#data_table_2").find("tbody").find("tr:last").data("paper",data);
						})
						$tab.find(".pagination").pagination(jsonData.rowCount, {
					    	 next_text : "下一页",
					    	 prev_text : "上一页",
					    	 current_page : jsonData.pageNo-1,
					    	 link_to : "javascript:void(0)",
					    	 num_display_entries : 8,
					    	 items_per_page : jsonData.pageSize,
					    	 num_edge_entries : 1,
					    	 callback:function(page,jq){
						    	 var pageNo = page + 1;
						    	 $this.searchPapers(pageNo);
					    	 }
					   });
					}else{
						$tab.find("#data_table_2").find("tbody").append('<tr><td>没有查到试卷&nbsp;&nbsp;</td></tr>');
					}
				}
			})
		},
		//检索视频
		searchVideos: function(page){
			var $this=this;
			var search={};
			var $tab=$("div.tab1");
			//获取当前视频类型
			$(".tabs").find("span").each(function(){
				if($(this).hasClass("active")){
					search.flag=$(this).attr("ids");
					$tab=$("div.tab"+($(this).index()+1));
				}
			});
			
			if($tab.find("#choose_itemOne").val()){
				search.itemOneId=$tab.find("#choose_itemOne").val();
			}
			if($tab.find("#choose_itemSecond").val()){
				search.itemSecondId=$tab.find("#choose_itemSecond").val();
			}
			
			
			if($tab.find("#choose_paper1").val()){
				search.videoName=$tab.find("#choose_paper1").val();
			}
			search.page=page?page:1;

			$tab.find("#data_table").find('tbody').find('tr').remove();
			$tab.find(".pagination").html('');
			$.ajax({
				url: rootPath+"/video/searchVideos",
				data: search,
				type:"post",
				dataType:"json",
				success: function(jsonData){
					if(jsonData.data && jsonData.data.length>0){
						$.each(jsonData.data,function(i,data){
							$tab.find("#data_table").find("tbody")
							.append('<tr id="'+data.id+'" videoCcId="'+data.videoCcId+'" has-data="true" videoStatus="'+data.videoStatus+'">'+
				                    '<td style="width:20%;" title="'+data.videoName+'">'+(data.videoName?data.videoName.length>16?data.videoName.substring(0,16):data.videoName:"")+'</td>'+
				                    '<td style="width:20%;">'+(data.vodeoSize?data.vodeoSize.toFixed(2):0)+'M</td>'+
				                    '<td style="width:20%;">'+(data.videoTag?data.videoTag:"")+'</td>'+
				                    '<td style="width:20%;">'+data.creatorName+'</td>'+
				                    '<td style="width:20%;">'+(data.createTime?data.createTime:"")+'</td>'+
				                '</tr>');
						})
						$tab.find(".pagination").pagination(jsonData.rowCount, {
					    	 next_text : "下一页",
					    	 prev_text : "上一页",
					    	 current_page : jsonData.pageNo-1,
					    	 link_to : "javascript:void(0)",
					    	 num_display_entries : 8,
					    	 items_per_page : jsonData.pageSize,
					    	 num_edge_entries : 1,
					    	 callback:function(page,jq){
						    	 var pageNo = page + 1;
						    	 $this.searchVideos(pageNo);
					    	 }
					   });
					}else{
						$tab.find("#data_table").find("tbody").html('<tr><td>没有查到视频资源&nbsp;&nbsp;<a href="'+rootPath+'/video/toVideo" target="_blank" style="color:blue;text-decoration:underline;">现在去上传视频</a></td></tr>');
					}
					$('.q-btn-primary1').attr('disabled',false);
				},
				error: function(resp,err,msg){
					console.log(resp);
					alert("检索异常");
				}
				
			});
			
		},
		searchResource : function(page){
			var $this=this;
			var search={};
			search.uploadType = 0;
			if($("#choose_tiku1").val()){
				search.sysItemOne=$("#choose_tiku1").val();
			}
			if($("#choose_item1").val()){
				search.sysItemSecond=$("#choose_item1").val();
			}
			if($("#choose_paper2").val()){
				search.fileName=$("#choose_paper2").val();
			}
			search.page=page?page:1;
			if($('#type-paper2').parent().hasClass('hasClick'))
				search.fileCategory = 'flash';
			else if($('#type-paper3').parent().hasClass('hasClick'))
				search.fileCategory = 'audio';
			else if($('#type-paper4').parent().hasClass('hasClick'))
				search.fileCategory = 'ppt';
			else if($('#type-paper5').parent().hasClass('hasClick'))
				search.fileCategory = 'docs';
			else
				search.fileCategory = '';
			$('.w1100').find("#data_table_3").find('tbody').find('tr').remove();
			$('.w1100').find(".pagination").html('');
			
			$.ajax({
				url: rootPath+"/resourceList/searchResourceList",
				data: search,
				type:"post",
				dataType:"json",
				success: function(jsonData){
					if(jsonData.data && jsonData.data.length>0){
						$.each(jsonData.data,function(i,data){
							$('.w1100').find("#data_table_3").find("tbody")
							.append('<tr id="'+data.id+'" has-data="true" >'+
				                    '<td style="width:20%;" title="'+data.fileName+'">'+(data.fileName?data.fileName.length>16?data.fileName.substring(0,16):data.fileName:"")+'</td>'+
				                    '<td style="width:20%;">'+(data.fileSize?(data.fileSize/(1024*1024)).toFixed(2):0)+'M</td>'+
				                    '<td style="width:20%;">'+(data.tag?data.tag:"")+'</td>'+
				                    '<td style="width:20%;">'+(data.uploadUserName?data.uploadUserName:"")+'</td>'+
				                    '<td style="width:20%;">'+(data.uploadTime?data.uploadTime:"")+'</td>'+
				                '</tr>');
						})
						$('.w1100').find(".pagination").pagination(jsonData.rowCount, {
					    	 next_text : "下一页",
					    	 prev_text : "上一页",
					    	 current_page : jsonData.pageNo-1,
					    	 link_to : "javascript:void(0)",
					    	 num_display_entries : 8,
					    	 items_per_page : jsonData.pageSize,
					    	 num_edge_entries : 1,
					    	 callback:function(page,jq){
						    	 var pageNo = page + 1;
						    	 $this.searchResource(pageNo);
					    	 }
					   });
					}else{
						$('.w1100').find("#data_table_3").find("tbody").append('<tr><td>没有查到相关资源&nbsp;&nbsp;</td></tr>');
					}
					
				},
				error: function(resp,err,msg){
					console.log(resp);
					alert("检索异常");
				}
				
			});
		},
		mvLecture: function(src,target,lecture){
			var $this=this;
			var para={};
			para.target=target.attr("id").substring(8);
			if(lecture.find('a span').attr('class')=='add-xiaojie'){
				para.lecture=lecture.attr("id").substring(8);
				$.ajax({
					url: rootPath+"/courseVideoLecture/mvLecture",
					data:para,
					type: "post",
					dataType: "json",
					success: function(json){
						if(src.parents(".dis").find("li").length>0){
							$this.sort(src.parents(".dis").find("li").eq(0));
						}
						
					}
				})
			}
			if(lecture.find('a span').attr('class')=='iconfont add-ceyan'){
				para.lecture=lecture.attr("id").substring(5);
				$.ajax({
					url: rootPath+"/courseAfterTest/mvTest",
					data:para,
					type: "post",
					dataType: "json",
					success: function(json){
						if(src.parents(".dis").find("li").length>0){
							$this.sort(src.parents(".dis").find("li").eq(0));
						}
					}
				})
			}
		},
		collapseAll: function(){
			$(".base-sort").find(".sortable").slideUp(200);
		},
		togglespan: function($ele){
			$ele.find(".sortable").slideToggle(200);
			if($ele.find(".father").find("i").hasClass("open")){
				$ele.find(".father").find(".iconfont2").removeClass("open").addClass("close").html('&#xe62e;');
			}else if($ele.find(".father").find("i").hasClass("close")){
				$ele.find(".father").find(".iconfont2").removeClass("close").addClass("open").html('&#xe62f;');
			}
		},
		queryCorseResoure : function(id){
			var oneItem=$("#itemOneId").val();
			var twoItem=$("#itemSecondId").val(); 
			var classid=$("#classTypeId").val();
			 $.ajax({
				 url : rootPath + "/classTypeResource/rourseList",
				 type: "post",
				 data:{"itemOneId":oneItem,"itemSecondId":twoItem,"classTypeId":classid,"lectureId":id,"lectureType":"TEACH_METHOD_VIDEO"},
				 dataType:"json",
				 success:function(data){
					 
					 if(data == null || data.length == 0){
							$(".t-c-r").find('#spanlist').text('');
						}else{
							if($(".t-c-r").find('#spanlist').text() == ''){
								$(".t-c-r").find('#spanlist').text('资料列表：');
							}
							var html="";
							$.each(data,function(i,item){
								var name=item.name;
								if(name.substring(name.lastIndexOf(".")+1)=="doc"||name.substring(name.lastIndexOf(".")+1)=="docx"){
									html+='<b><i class="list-item">'+item.name+'</i><a href="javascript:void(0);" class="delresource"  ids="'+item.id+'"><strong>删除</strong></a></b>';
								}else if(name.substring(name.lastIndexOf(".")+1)=="xls"||name.substring(name.lastIndexOf(".")+1)=="xlsx"){
									html+='<b><i class="list-item">'+item.name+'</i><a href="javascript:void(0);" class="delresource"  ids="'+item.id+'"><strong>删除</strong></a></b>';
								}else if(name.substring(name.lastIndexOf(".")+1)=="pdf"){
									html+='<b><i class="list-item">'+item.name+'</i><a href="javascript:void(0);" class="delresource"  ids="'+item.id+'"><strong>删除</strong></a></b>';
								}else if(name.substring(name.lastIndexOf(".")+1)=="ppt"||name.substring(name.lastIndexOf(".")+1)=="pptx"){
									html+='<b><i class="list-item">'+item.name+'</i><a href="javascript:void(0);" class="delresource"  ids="'+item.id+'"><strong>删除</strong></a></b>';
								}else{
									html+='<b><i class="list-item">'+item.name+'</i><a href="javascript:void(0);" class="delresource"  ids="'+item.id+'"><strong>删除</strong></a></b>';
								}
							});
							$("#courseList1"+id).html("").append(html);
						}
				 }
			 });
		},
		initBase: function(){
			var $this=this;
			//点击章，显示章详情
			$(".t-c-l").on("click.li.left",".father",function(){
				$this.showChapter($(this).parent());
				$this.togglespan($(this).parent());
			});
			
			//点击节，显示节详情
			$(".t-c-l").on("click.li.left",".class-son",function(){
				var data={};
				if($(this).find('span').attr('class') == 'add-xiaojie'){
					data.lectureOrder=$(this).index();
					$this.showLecture($(this));
				}else{
					$this.showTest($(this));
				}
				
			});
			//添加章操作
			$(".teacher-btns").on("click.btn.page",".btn-add-class",function(){
				var chapter='<li class="dis none"><p class="father">'+
					'<i class="iconfont4">&#xe630;</i>'+
					'<i class="iconfont2 open">&#xe62f;</i>'+
                    '<a href="javascript:;">第'+getChapterName()+getCname()+'&nbsp;&nbsp;<b></b> </a>'+
                    '<i class="iconfont">&#xe626;</i>'+
                    '<i class="iconfont3 add-btns">&#xe629;'+
                    '<b class="add-item">'+
                    '<span class="xiaojie">新增小节</span>'+
                    ($this.service.tikuService?'<span class="ceyan">新增测验</span>':'')+
                    '</b>'+
                    '</i>'+
                '</p><ul class="son sortable"></ul></li>';
				$(".t-c-l").find(".base-sort").append(chapter);
				var chapter=$(".t-c-l").find(".sortable").find(".dis:last");
				chapter.fadeIn(300);
				$this.addChapter(chapter,function(){
					$this.showChapter(chapter);
				});
			});
			
			
			//添加节操作
			$(".sortable").on("click.btn.page",".xiaojie",function(){
				var lecture='<li class="class-son none" draggable="true">'
					+'<a href="javascript:;"><span class="add-xiaojie"></span>第'
					+getLecureName($(this).parents('.dis'))+getLname()+' &nbsp;&nbsp;<b></b></a>'
					+'<i class="iconfont4">&#xe630;</i>'
					+'<i class="iconfont">&#xe626;</i></li>';
				var chapter=$(this).parent().parent().parent().next().append(lecture);
				var lecture=$(this).parent().parent().parent().next().find(".class-son:last");
				lecture.fadeIn(300);
				$this.addLecture(lecture,function(){
					$this.showLecture(lecture);	
				});
				return false;
			});
			
			//添加测验操作
			$(".sortable").on("click.btn.page",".ceyan",function(){
				var test='<li class="class-son none" draggable="true">'
					+'<a href="javascript:;"><span class="iconfont add-ceyan">&#xe6d5;</span>测验'
					+'&nbsp;&nbsp;&nbsp;<b></b></a>'
					+'<i class="iconfont4">&#xe630;</i>'
					+'<i class="iconfont">&#xe626;</i></li>';
				var chapter=$(this).parent().parent().parent().next().append(test);
				var test=$(this).parent().parent().parent().next().find(".class-son:last");
				test.fadeIn(300);
				$this.addTest(test,function(){
					$this.showTest(test);	
				});
				return false;
			});
			
			//删除章操作
			$(".sortable").on("click.btn.right",".father .iconfont",function(){
				var _this=$(this);
				if(_this.parent().parent().find(".class-son").length>0){
					alert("请先清空此章下的节");
					return false;
				}else{
					$.confirm("确定删除？",function(e){
						if(e){
							$this.delChapter(_this.parent().parent());
						}
					})
				}
				
			});
			//删除节操作
			$(".sortable").on("click.btn.right",".son .iconfont",function(){
				var _this=$(this);
				var type = _this.parent().find('span').attr('class');
				if(type == 'add-xiaojie'){
					$.confirm("确定删除？",function(e){
						if(e){
							$this.delLecture(_this.parent())
						}
					})
				}
				if(type == 'iconfont add-ceyan'){
					$.confirm("确定删除？",function(e){
						if(e){
							$this.delTest(_this.parent())
						}
					})
				}
			});
			
			//保存章节内容
			$(".t-c-r").on("click.btn.right",".save",function(){
				var data={};
				if($(this).parents(".chapter").length){
					data.id=$(this).parents(".t-c-r").find(".t-c-r-t").attr("value");
					data.moduleId = $('#moduleId').val();
					data.chapterName=$(this).parents(".t-c-r").find(".chapterName").val();
					data.chapterOrder=$(this).parents(".t-c-r").find("#sortChapter").val();
					$this.saveChapter(data);
				}
				if($(this).parents(".lecture").length){
					var id=$(this).parents(".t-c-r").find(".t-c-r-t").attr("value");
					var lecture=$(this).parents(".t-c-r").find(".t-c-r-t");
					data=$("#lecture_"+id).data("info");
					data.lectureName=lecture.find(".lectureName").val();
					if(lecture.find('#type-paper').is(':checked')){
						if(lecture.find("#open-paper").is(":checked")){
							data.freeFlag=1;
						}else if(lecture.find("#open-paper2").is(":checked")){
							data.freeFlag=2;
						}else{
							data.freeFlag=0;
						}
						data.videoId=lecture.find(".video_id").val();
						data.videoName=lecture.find(".video_name").val();
						data.fileId = null;
						data.fileType = 'video';
						if(!data.videoId){
							$.msg('请选择一个视频');
							lecture.find(".video_name").focus();
							return;
						}
					}else{
						if(lecture.find('#type-paper2').is(':checked'))
							data.fileType = 'flash';
						else if(lecture.find('#type-paper3').is(':checked'))
							data.fileType = 'audio';
						else if(lecture.find('#type-paper4').is(':checked'))
							data.fileType = 'ppt';
						else if(lecture.find('#type-paper5').is(':checked'))
							data.fileType = 'doc';
						if(lecture.find('.lectureName').next('span').find('input[type="checkbox"]').is(':checked'))
							data.freeFlag = 1;
						else 
							data.freeFlag = 0;
						data.fileId = lecture.find('.file_id').val();
						data.fileName = lecture.find(".video_name").val();
						data.videoId = null;
						if(!data.fileId){
							$.msg('请选择一个资源文件');
							lecture.find(".video_name").focus();
							return;
						}
					}
					if(!data.lectureName){
						$.msg('请输入节的名字');
						lecture.find(".lectureName").focus();
						return;
					}
					$.each(data,function(k,v){
						if(v==null){
							delete data[k];
						}
					})
					$this.saveLecture(data);
				}
			});
			//保存测验
			$('.t-c-r').on('click','#saveCeyan',function(){
				var id=$(this).parents(".t-c-r").find(".t-c-r-t").attr("value");
				var test=$(this).parents(".t-c-r").find(".t-c-r-t");
				data=$("#test_"+id).data("testInfo");
				data.testName=test.find(".lectureName").val();
				data.testScore = $('.q-text1').val();
				
				var flag = $('#type-paper-ceyan').is(':checked');
				if(flag){
					data.testType = 1;
					if(isNaN(data.testScore) || data.testScore<0 || data.testScore >100){
						$.msg('请输入有效的达标分数');
						$('.q-text1').val('').focus();
						return ;
					}
					if(data.testScore == null || data.testScore == ''){
						$.msg('请输入达标分数');
						$('.q-text1').val('').focus();
						return ;
					}
				}
				else{
					data.testType = 0;
					data.testTotalNum = $('#kaoshinumber').val();
					if(isNaN(data.testTotalNum) || data.testTotalNum<0){
						$.msg('请输入有效的考试题目数');
						$('#kaoshinumber').val('').focus();
						return ;
					}
					if(data.testTotalNum== null || data.testTotalNum == ''){
						$.msg('考试题目数不能未空');
						$('#kaoshinumber').val('').focus();
						return ;
					}
					if(isNaN(data.testScore) || data.testScore<0 || data.testScore >100){
						$.msg('请输入有效的达标正确率');
						$('.q-text1').val('').focus();
						return ;
					}
					if(data.testScore == null || data.testScore == ''){
						$.msg('请输入达标正确率');
						$('.q-text1').val('').focus();
						return ;
					}
				}
				if($('#cont-change').siblings().is(':checked'))
						data.allowContinue = 0;
				else
					data.allowContinue = 1;
				if(data.testName == null || data.testName == ''){
					$.msg('请输入测验名称');
					test.find('.lectureName').focus();
					return ;
				}
				
				var ll = $(".t-c-r").find("#courseList"+id).data("exercises");
				if(ll== null || ll.length<=0){
					$.msg('请选择试卷或章节');
					return ;
				}
				$.each(data,function(k,v){
					if(v==null){
						delete data[k];
					}
				})
				
					
					$this.saveTest(data);
			})
			//章排序
			$(".t-c-r").on("change.btn.right","#sortChapter",function(){
				
			});
			//打开视频选择框
			$(".t-c-r").on("click.btn.right",".chooseVideo",function(){
				if($("#type-paper").attr('checked')=='checked'){
					$('.add-layer-bg').fadeIn(200,function(){
						$('.w800').fadeIn(200);
						$this.searchVideos();
					})	
				}else{
					setTimeout(function () {
						$this.searchResource();
					  }, 1000);
					
					$('.add-layer-bg').fadeIn(200,function(){
						$('.w1100').fadeIn(200);
						setTimeout(function () {
							$this.searchResource();
						  }, 500);
					})
				}
			});
			
			$('.q-type-choice').on('click',function(){
				setTimeout(function () {
					$this.searchResource();
				  }, 500);
			});
			
			
			/*//选择课后练习方式
			$(".t-c-r").on("change.radio.right",".radio",function(){
				var exercise=$(this).data("exercise");
				$(this).parents(".c").next().find(".c-content").find("a").remove();
				$(".n-list").find("tr").remove();
				
				if($(".t-c-r").find(".radio:checked").val()=="paper"){
					if(exercise){
						$(".t-c-r").find(".n-list").html('<tr><td width="40%">'+(exercise.subjectName?exercise.subjectName:"")+'</td><td width="40%">'+(exercise.paperName?exercise.paperName:"")+'</td><td><a href="javascript:;">删除</a></td></tr>');
					}
					//$(this).parents(".c").next().find(".c-content").append('<a href="javascript:;" class="btn btn-default choosePaper">选择试卷</a>');
					
				}else{
					if(exercise){
						$(".t-c-r").find(".n-list").html('<tr><td width="40%">'+exercise.chapterName+'</td><td width="40%">'+exercise.sectionName+'</td><td><a href="javascript:;">删除</a></td></tr>');
					}
					//$(this).parents(".c").next().find(".c-content").append('<a href="javascript:;" class="btn btn-default chooseChapter">选择章节</a>');
					
				}
				
			})
			.on("click.checkbox.right",".freeFlag",function(){
				$(this).siblings().removeAttr("checked");
			})*/
			//打开试卷选择框
			$(".t-c-r").on("click.btn.right",".choosePaper",function(){
				 $('.add-layer-bg').fadeIn(200,function(){
	               $('.w900').fadeIn(200);
	             })
			});
			
			$(".t-c-r").on("click.btn.right",".choosePaper1",function(){
				 $('.add-layer-bg').fadeIn(200,function(){
	               $('.w900').fadeIn(200);
	             })
			});
			
			$(".t-c-r").on("click.btn.right",".chooseChapter1",function(){
				 $('.add-layer-bg').fadeIn(200,function(){
	               $('.w1400').fadeIn(200);
	             })
			});
			
			//打开题库章节选择框
			$(".t-c-r").on("click.btn.right",".chooseChapter",function(){
				 $('.add-layer-bg').fadeIn(200,function(){
	               $('.w1000').fadeIn(200);
	             })
			});
			

			$(".t-c-l").find(".dis").eq(0).trigger("click.li.left");
			//返回
			$(".btn.back").on("click",function(){
				//location.href=rootPath+$('#backurl').val();
				history.back();
			})
			//取消
			$(".btn.cancle").on("click",function(){
				location.href=rootPath+"/simpleClasses/showClassTypePage";
			});
			$(".q-cancel2").on("click",function(){
				location.href=rootPath+"/simpleClasses/showClassTypePage";
			});
			//下一步
			$("#next1").on("click",function(){
				$("#myForms").attr("action",rootPath+"/simpleClasses/onSale").submit();
			});
			//打开添加资料弹框
			$(".t-c-r").on("click",".btn.btn-upload",function(){
				$(".class-resource").show();
				$(".loading-bg").show();
				$("#doctype").attr("lecid",$(this).parents("div.lecture").attr("value"));
				
			});
			//关闭资料弹框
			$(".q-cancel").click(function(){
				$(".class-resource").hide();
				$(".loading-bg").hide();
				$("#doctype").val("");
				$("#dochint").html("");
			});
			$('.close').click(function(){
				$(".loading-bg").hide();
			})
			//保存资料
			$(".btn-ok").on('click',function(){
				$(".loading-bg").show();
				var oneItem=$("#itemOneId").val();
				var twoItem=$("#itemSecondId").val(); 
				var classid=$("#classTypeId").val();
				//类型code
				var resource=$("#classresource").val();
				//类型name
				var resName = $.trim($("#classresource").find("option:selected").text());
				var classTypeName=$("#classTypeName").val();
				var lectureId=$("#doctype").attr("lecid");
				var docurl=$("#doctype").attr("url");
    			var docname=$("#doctype").attr("cname");
    			var docsize=$("#doctype").attr("mark");
    			if(docurl == null || docurl == ""){
					$.msg("请先上传文档");
					$(".loading-bg").hide();
					return false;
				}
					$.ajax({
						url : rootPath + "/classTypeResource/save",
						type:"post",
						data:{"itemOneId":oneItem,
							"itemSecondId":twoItem,
							"classTypeId":classid,
							"resourceType":resource,
							"classTypeName":classTypeName,
							"fileId":docurl,
							"lectureId":lectureId,
							"lectureType":"TEACH_METHOD_VIDEO"},
						dataType:"json",
						success:function(data){
							if(data.msg == "success"){
								$(".class-resource").hide();
								$("#doctype").attr("url","");
					    	//	$("#doctype").val("");
					    		$("#dochint").html("");
					    		$this.queryCorseResoure(lectureId);
					    		$(".loading-bg").hide();
					    		$.msg("资料上传成功");
							}else{
								$(".loading-bg").hide();
								$.msg("保存资料失败");
							}
						}
					});
			});
			//tab
			$(".tabs").on('click','span',function(){
				$(".w800").find("div.layer-content").hide();
				$("div.tab"+($(this).index()+1)).show();
				$(this).siblings().removeClass("active");
				$(this).addClass("active");
				$this.searchVideos();
				 $(".term-list").find(".table-head").css("top","0px");
			});
			//删除课程资料
			$(".t-c-r").on("click","a.delresource",function(){
				var $t=$(this);
				var id=$t.attr("ids");
				$.ajax({
					url : rootPath + "/classTypeResource/delete/"+id,
					type:"post",
					dataType:"json",
					success:function(data){
						$t.parent().remove();
					}
				});
				var oneItem=$("#itemOneId").val();
				var twoItem=$("#itemSecondId").val(); 
				var classid=$("#classTypeId").val();
				var lectureId=$('.t-c-r').find('.lecture').attr('value');
				 $.ajax({
					 url : rootPath + "/classTypeResource/rourseList",
					 type: "post",
					 data:{"itemOneId":oneItem,"itemSecondId":twoItem,"classTypeId":classid,"lectureId":lectureId,"lectureType":"TEACH_METHOD_VIDEO"},
					 dataType:"json",
					 success:function(data){
						 
						 if(data == null || data == '' || data.length == 0){
								$('.t-c-r').find('#spanlist').text('');
							}
					 }
				 });
			})
			.on("click.link.del",".n-list a",function(){
				$(this).parents("tr").remove();
				var sk = $(".t-c-r").find(".n-list").data("exercise");
				if(sk){
					delete sk;
					$(".t-c-r").find(".n-list").data("exercise",null);
				}
				var eid = $(this).attr('eid');
				if(eid){
					$.ajax({
						url:rootPath+"/courseExercise/delExercise",
						data:{"eid":eid},
						type:"post",
						dataType:'json',
						success:function(data){
						}
					})
				}
			})
			
			$('.t-c-r').on('click','a.delrel',function(){
				var $that = $(this);
				$.confirm("您确认删除改资料吗?",function(b){
					if(b){
						$that.parent().remove();
						var index = $that.attr('delIndex');
						var testId = $('.t-c-r').find('.test').attr('value');
						var items = $(".t-c-r").find("#courseList"+testId).data("exercises");
						if(items != null && items.length >0){
							if(items[index])
								delete items[index];
						}
						$(".t-c-r").find("#courseList"+testId).data("exercises",items);
						var contentId = $that.attr('mark');
						if(contentId){
							$.ajax({
								url:rootPath+"/courseAfterTestContent/delContent",
								type:'post',
								dataType:'json',
								data:{"contentId":contentId},
								success:function(data){
								}
							})
						}
					}
				});
				
			})
			
			$('.t-c-r').find(".tab-cont .tab-list span .active").trigger('click');
			$('.t-c-r').on('click','.del',function(){
				var $that = $(this);
				$.confirm("您确认删除改资料吗?",function(b){
					if(b){
						$that.parent().remove();
						var testId = $('.t-c-r').find('.test').attr('value');
						var items = $(".t-c-r").find("#courseList"+testId).data("exercises");
						if(items&&items.length >0){
							for(var x = 0;x <items.length;x++){
								var id2 = $that.attr('mark1');
								if(id2 && items[x].paperId && id2 == items[x].paperId){
									var contentId = items[x].id;
									if(contentId){
										$.ajax({
											url:rootPath+"/courseAfterTestContent/delContent",
											type:'post',
											dataType:'json',
											data:{"contentId":contentId},
											success:function(){
											}
										})
									}
									delete items[x];
									$(".t-c-r").find("#courseList"+testId).data("exercises",items);
								}
							}
						}
						var sk = $(".t-c-r").find(".n-list").data("exercise");
						if(sk){
							delete sk;
							$(".t-c-r").find(".n-list").data("exercise",null);
							var eid = $(this).attr('eid');
							if(eid){
								$.ajax({
									url:rootPath+"/courseExercise/delExercise",
									data:{"eid":eid},
									type:"post",
									dataType:'json',
									success:function(){
									}
								})
							}
						}
						
					}
					
				});
				
			});
			$('.t-c-r').on("click",'.tab-cont .tab-list span',function(){
    	        $(this).addClass("active").siblings().removeClass("active");
    	        $(".tab-item").find(".q-item").eq($(this).index()).show().siblings().hide();
    	    });
			$('.t-c-r').on('click','.q-type-choice',function(){
				var    $radio = $(this).find("input[type=radio]"),
	             $flag  = $radio.is(":checked");
				if( !$flag ){
					$radio.prop("checked",true);   
				}
				$("#free-sorce").show();
    	        $("#open-veido").hide();
    	        $('#type-paper').attr('checked',false);
    	         $(".vedio-name .c-title").html("资源名称：")
    	         $("#choose_paper2").val('');
    	         var cid = $(this).find('input[name="type-set"]').attr('id');
    	         var cname = null;
    	         if(cid == 'type-paper2')
    	        	 cname = 'flash';
    	         else if(cid == 'type-paper3')
    	        	 cname = 'audio';
    	         else if(cid == 'type-paper4')
    	        	 cname = 'ppt';
    	         else if(cid == 'type-paper5')
    	        	 cname = 'docs';
    	       $(this).addClass("hasClick").siblings().removeClass("hasClick");
    	       var lid = $('.t-c-r-t').attr('value');
    	       var info = $(".t-c-l").find("#lecture_"+lid).data("info");
    	       if(info && info.freeFlag == 1){
    	    	   $('.t-c-r').find('#free-sorce').find('input[type="checkbox"]').attr('checked','checked');
    	       }
    	       if(info && info.fileId){
    	    	   $.ajax({
						url : rootPath + "/resourceList/"+info.fileId,
						type: "get",
						dataType: "json",
						success : function(jsonData){
							if(jsonData.fileCategory == cname)
								$('.q-text').val(jsonData.fileName);
							else {
								$('.q-text').val('');
							}
						}
					})
    	       }else{
    	    	   $('.q-text').val(''); 
    	       }
			});
			$('.t-c-r').on('click','#vedio-paper',function(){
				var    $radio = $(this).find("input[type=radio]"),
	             $flag  = $radio.is(":checked");
				if( !$flag ){
					$radio.prop("checked",true);   
				}
				$("#free-sorce").hide();
    	        $("#open-veido").show();
    	        $('#type-paper').attr('checked',true);
    	        $("#choose_paper1").val('');
    	        $(".vedio-name .c-title").html("视频名称：");
    	        var lid = $('.t-c-r-t').attr('value');
     	       	var info = $(".t-c-l").find("#lecture_"+lid).data("info");
    	        if(info && info.videoId){
    	        	$.ajax({
						url : rootPath + "/video/"+info.videoId,
						type: "get",
						dataType: "json",
						success : function(jsonData){
							if(jsonData && jsonData.videoName)
							$(".video_name").val(jsonData.videoName);
							else
								$(".video_name").val('');
						}
					})
    	        }else{
    	        	$(".video_name").val('');
    	        }
			})
			$('.t-c-r').on('click','.chooseVideo',function(){
				if($(".q-type-choice").hasClass("hasClick")){
    	          var $titCont= $("#c-content-one").find(".hasClick").find("label").html();
    	            var $w1100="选择"+$titCont+"文件";
    	            $(".w1100").find("h3").html($w1100);
    	        }
			})
			$('.t-c-r').on('click','.bd-part',function(){
				 $("#selectpc").html("选择章节");
				 $('.t-c-r').find('#shiquanorzhangjiexinxi').text('章节信息：');
				 $('.t-c-r').find('.n-list').find('b').remove();
				 var lecture_id = $('.t-c-r').find('.lecture').attr('value');
				 $(this).addClass("hasClick").siblings().removeClass("hasClick");
				 $(this).parent().parent().next().find('span a').removeClass('choosePaper').addClass('chooseChapter');
				 $.ajax({
						url: rootPath+"/courseExercise/find",
						data: {
							resourceId:lecture_id,
							resourceType: "TEACH_METHOD_VIDEO",
						},
						type: "post",
						dataType: "json",
						success: function(exercise){
							 if(exercise && exercise.tikuResourceType=="CHAPTER"){
								$(".t-c-r").find(".n-list").html('<tr><td width="30%">'+(exercise.chapterName?exercise.chapterName:"")+'</td><td width="30%">'+(exercise.sectionName?exercise.sectionName:"")+'</td><td width="40%"><a href="javascript:;" eid="'+exercise.id+'">删除</a></td></tr>');
							}
							$(".t-c-r").find(".n-list").data("exercise",exercise);
						}
						
					})
			});
			
			
			$('.t-c-r').on('click','.bd-exam',function(){
				$("#selectpc").html("选择试卷");
				$('.t-c-r').find('#shiquanorzhangjiexinxi').text('试卷信息：');
				$('.t-c-r').find('.n-list').find('tr').remove();
				var lecture_id = $('.t-c-r').find('.lecture').attr('value');
		        $(this).addClass("hasClick").siblings().removeClass("hasClick");
		        $(this).parent().parent().next().find('span a').removeClass('chooseChapter').addClass('choosePaper');
		        $.ajax({
					url: rootPath+"/courseExercise/find",
					data: {
						resourceId:lecture_id,
						resourceType: "TEACH_METHOD_VIDEO",
					},
					type: "post",
					dataType: "json",
					success: function(exercise){
						if(exercise && exercise.tikuResourceType=="PAPER"){
							$(".t-c-r").find(".n-list").html('<b><i class="list-item">'+exercise.paperName+'</i> <a class="del" eid="'+exercise.id+'"><strong>删除</strong></a></b>');
						}
						$(".t-c-r").find(".n-list").data("exercise",exercise);
					}
					
				})
			});
			
			$('.t-c-r').on('click','#open-paper',function(){
				$('.t-c-r').find('#open-paper2').attr('checked',false);
			})
			
			$('.t-c-r').on('click','#open-paper2',function(){
				$('.t-c-r').find('#open-paper').attr('checked',false);
			})
			
			$('.t-c-r').on('click','#type-paper-ceyan',function(){
					$(".dabiao").hide();
	    	        $(".exam-number").hide();
	    	        $("#cont-change").html("分数");
	    	        $('#dabiaofenshu1').text('达标分数：');
	    	        if($('.q-text1').val()==''){
	   	         		$('.q-text1').attr('placeholder','请输入正整数');
	   	         	}
	    	        $('.t-c-r').find('#selectShiorZhang').text('选择试卷');
	    	        $('.chooseChapter1').removeClass('chooseChapter1').addClass('choosePaper1');
	    	        var testId = $(this).parents('.test').attr('value');
	    	        $('.t-c-r-t').find('#courseList'+testId).find('b').remove();
	    	        var kks = $(".t-c-r").find("#courseList"+testId).data('exercises');
	   	         	if(kks && kks.length >0){
	   	         		for(var j=0;j<kks.length;j++){
	   	         			if(kks[j]&&!kks[j].paperId){
	   	         			$(".t-c-r").find("#courseList"+testId).data('exercises',[]);
	   	         			break;
	   	         			}
	   	         		}
	   	         	}
	    	        $.ajax({
						url:rootPath+"/courseAfterTestContent/findContentListByTestId2",
						data:{testId:testId},
						type:"post",
						dataType:"json",
						success:function(jsonData){
							if(jsonData && jsonData.length>0){
								$('.t-c-r').find('#ziliaotitle').text('试卷列表：');
								$.each(jsonData,function(i,data){
									$(".t-c-r").find("#courseList"+testId).append('<b><i class="list-item">'+data.paperName+'</i> <a class="del" mark1="'+data.paperId+'"><strong>删除</strong></a></b>');	
								})
								$(".t-c-r").find("#courseList"+testId).data('exercises',jsonData);
							}else{
								$('.t-c-r').find('#ziliaotitle').text('');
							}
						}
						
					})
			})
			
			
			$('.t-c-r').on('click','#type-paper2-ceyan',function(){
				$('#type-paper-ceyan').attr('checked',false);
   	         	$(".dabiao").show();
   	         	$(".exam-number").show();
   	         	$('#dabiaofenshu1').text('达标正确率：');
   	         	$("#cont-change").html("正确率");
   	         	if($('.q-text1').val()==''){
   	         		$('.q-text1').attr('placeholder','请输入整数且范围在0-100');
   	         	}
   	         	$('.t-c-r').find('#selectShiorZhang').text('选择章节');
   	         	$('.choosePaper1').removeClass('choosePaper1').addClass('chooseChapter1');
   	         	var testId = $(this).parents('.test').attr('value');
   	         	$('.t-c-r-t').find('#courseList'+testId).find('b').remove();
   	         	var tts = $(".t-c-r").find("#courseList"+testId).data('exercises');
   	         	if(tts && tts.length >0){
   	         		for(var j=0;j<tts.length;j++){
   	         			if(tts[j]&&tts[j].paperId){
   	         			$(".t-c-r").find("#courseList"+testId).data('exercises',[]);
   	         			break;
   	         			}
   	         		}
   	         	}
   	         	$.ajax({
					url:rootPath+"/courseAfterTestContent/findContentListByTestId",
					data:{testId:testId},
					type:"post",
					dataType:"json",
					success:function(jsonData){
						if(jsonData && jsonData.length>0){
							$('.t-c-r').find('#ziliaotitle').text('章节列表：');
							$.each(jsonData,function(i,data){
								$(".t-c-r").find("#courseList"+testId).append('<b><i class="list-item">'+data.chapterName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+data.sectionName+'</i><a class="delrel" mark="'+data.id+'" delIndex = "'+i+'"href="javascript:;"><strong>删除<strong></a></b>');	
							})
							$(".t-c-r").find("#courseList"+testId).data('exercises',jsonData);
						}else{
							$('.t-c-r').find('#ziliaotitle').text('');
						}
					}
				})
			})
			
			 $('#c-content-one a').click(function(){
				    var    $radio = $(this).find("input[type=radio]"),
				             $flag  = $radio.is(":checked");
				    if( !$flag ){
				        $radio.prop("checked",true);   
				    }
				});

		},
		init800: function(){
			var $this=this;
			//初始化一学科小类
			$(".tab1").find("#choose_itemOne").getSysItem();
			$(".tab1").find("#choose_itemOne").on("change",function(){
				$(".tab1").find("#choose_itemSecond").getSysItem($(this).val(),function(){
					
				});
			});
			$(".tab2").find("#choose_itemOne").getSysItem();
			$(".tab2").find("#choose_itemOne").on("change",function(){
				$(".tab2").find("#choose_itemSecond").getSysItem($(this).val(),function(){
					
				});
			});
			$(".tab2").find(".itemOne").getSysItem();
			$(".tab2").find(".itemOne").on("change",function(){
				$(".tab2").find(".itemSecond").getSysItem($(this).val(),function(){
				});
			});
			//初始化视频列表
			$(".tab1").find("#choose_itemOne").find("option[value='"+$("#itemOneId").val()+"']").attr("selected","selected");
			$(".tab1").find("#choose_itemSecond").getSysItem($("#choose_itemOne").val(),function(){
				$(".tab1").find("#choose_itemSecond").find("option[value='"+$("#itemSecondId").val()+"']").attr("selected","selected");
			});
			$(".tab2").find("#choose_itemOne").find("option[value='"+$("#itemOneId").val()+"']").attr("selected","selected");
			$(".tab2").find("#choose_itemSecond").getSysItem($(".tab2").find("#choose_itemOne").val(),function(){
				$(".tab2").find("#choose_itemSecond").find("option[value='"+$("#itemSecondId").val()+"']").attr("selected","selected");
			});
			
			
			$.ajax({
				url: rootPath+"/videoTag/list",
				type: "post",
				dataType : "json",
				success: function(jsonData){
					var datas=[];
					$.each(jsonData,function(i,data){
						if(data){
							datas.push({id:data.id,text:(data.tagName?data.tagName:"")});
						}
					});
					$(".tag").each(function(){
						$(this).select2({
							multiple: true,
							data: datas,
							width: '150px',
							height:'32px'
						})
					})
				}
			});
			
			//单机选中视频，双击选择视频
			$(".w800").on("click.row.model",".term-list tr[has-data]",function(){
				$(".w800").find(".term-list tr").removeClass("active");
				$(this).addClass("active");
			})
			//检索视频
			.on("click",".q-btn-primary1",function(){
				
				setTimeout(function () {
					$this.searchVideos();
				  }, 1000);
				$(this).attr('disabled','disabled');
			})
			.on("click.row.model",".term-list tr[has-data]",function(){
				$(".video_name").val($(this).children().eq(0).html());
				if($('#type-paper').is(':checked')){
					$(".video_id").val($(this).attr("id"));
					$(".publishStatus").val($(this).attr("publishStatus"));
					$(".publishDate").val($(this).attr("publishDate"));
				}
				$('.w800').fadeOut(200,function(){
                    $('.add-layer-bg').fadeOut(200);
                });
			})
			.on("click",".add-video-link",function(){
				$(".add-div").show();
				$(".default-div").hide();
			})
			.on("click",".btn-add-url",function(){
				var url = $("#videourl").val();
				if(typeof(url) == "undefined" || url == null || url == ""){
					$.msg("请填入外部视频连接地址");
					return false;
				}
				//截取网址域
				if(url.indexOf("http://") >= 0){
					url = url.substring(7);
				}else if(url.indexOf("https://") >= 0){
					url = url.substring(8);
				}
				var httpTitle = url.substring(0,url.indexOf("/"));
				var videoType = httpTitle;
				var storageType="";
				/*if(httpTitle == "open.163.com"){
					//截取163的
					if(url.lastIndexOf("/") >= 0){
						outUrl = (url.substring(url.lastIndexOf("/") + 1 , url.lastIndexOf(".")));
					}else{
						$('<div class="c-fa" style="z-index;102;">您输入的地址不合法</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
						return false;
					}
				}else */if(httpTitle == "v.youku.com"){
					storageType = "VIDEO_STORAGE_TYPE_YK";
					//截取优酷的
					if(url.indexOf("id_") >= 0){
						if(url.indexOf("==") >= 0){
							outUrl = (url.substring(url.indexOf("id_") + 3 , url.indexOf("=")));
							$("#videourl").data("outUrl",outUrl);
						}else{
							outUrl = (url.substring(url.indexOf("id_") + 3 , url.lastIndexOf(".html")));
							$("#videourl").data("outUrl",outUrl);
						}
					}else{
						$.msg('您输入的地址不合法');
						return false;
					}
				}else if(httpTitle == "www.tudou.com"){
					storageType = "VIDEO_STORAGE_TYPE_TD";
	                //截取土豆的
	                if (url.indexOf("albumplay/") >= 0) {
	                    outUrl = "a/" + (url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html")));
	                } else if (url.indexOf("listplay/") >= 0) {
	                	if(url.indexOf(".html") >= 0){
	                        outUrl = "l/" + (url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html")));
	                	}else{
	                		outUrl = url.substring(url.indexOf("listplay/") + ("listplay/").length);
	                		outUrl = "l/" + (outUrl.substring(url.indexOf("/") + 1, outUrl.lastIndexOf("/")));
	                	}
	                } else if (url.indexOf("view/") >= 0) {
	                    outUrl = (url.substring(url.indexOf("view/") + ("view/").length));
	                    if(outUrl.indexOf("/") >= 0){
	                    	outUrl = "v/" + (outUrl.substring(0,outUrl.indexOf("/")));
	                    }else{
	                    	outUrl = "v/" + outUrl;
	                    }
	                } else {
	                    $('<div class="c-fa" style="z-index;102;">您输入的地址不合法</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
	                        $(this).remove();
	                    });
	                    return false;
	                }
				}else if(httpTitle == "console.video.capitalcloud.net"){
					storageType = "VIDEO_STORAGE_TYPE_SS";
					//截取石山的
					if(url.indexOf("entryId=") >= 0){
						outUrl = (url.substring(url.indexOf("entryId=") + 8 , url.indexOf("&pubId=")));
						$("#videourl").data("outUrl",outUrl);
					}else{
						$('<div class="c-fa" style="z-index;102;">您输入的地址不合法</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
						return false;
					}
				}else if(httpTitle == "qzonestyle.gtimg.cn"){
					storageType = "VIDEO_STORAGE_TYPE_QQ";
					var o1 = url.substring(url.indexOf("&file_id=")+9,url.indexOf("&app_id="));
					var o2 = url.substring(url.indexOf("&app_id=")+8,url.indexOf("&version="));
					outUrl = o1 + "," + o2;
					$("#videourl").data("outUrl",outUrl);
				}else{
					$.msg('您输入的地址不合法');
					return false;
				}
				$.ajax({
					url :rootPath + "/video/selVideoName" ,
					type:"post",
					data:{"url":$("#videourl").val(),"domain":httpTitle},
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
						$(".addVideoTc").css("z-index",2);
		              $(".loading").show();
		              $(".loading-bg").show();
		         	},
					success:function(data){
						if(data.msg != "error"){
							$(".video-name").val(data.msg);
							$.msg('已添加外部链接');
						}
					}
				});
			})
			.on("click.btn.save",".tab2 .btn-s",function(){
				var oneItemId = $(".add-div").find(".itemOne").val();
				var twoItemId = $(".add-div").find(".itemSecond").val();
				var tag=$(".add-div").find(".tag").val();
				var videoName = $(".add-div").find(".video-name").val();
				var url = $(".add-div").find("#videourl").val();
				var outUrl=$("#videourl").data("outUrl");
				var hh = $(".hour").val() == "" ? 0 : $(".hour").val();
				var mm = $(".minute").val() == "" ? 0 : $(".minute").val();
				var ss = $(".second").val() == "" ? 0 : $(".second").val();
				/* alert(oneItemId + "\n" + twoItemId + "\n" + videoName + "\n" + url + "\n" + outUrl + "\n" + videoType); */
				if(oneItemId == null || twoItemId == null || oneItemId < 0 || twoItemId < 0){
					$.msg('请选择科目');
					return false;
				}
				if(!outUrl || url == "" || outUrl == ""){
					$.msg('请添加外部链接地址');
					return false;
				}
				if(videoName == ""){
					$.msg('请添加视频名称');
					return false;
				}
//	        	if(!$("#protocol").prop("checked")){
//	        		$('您尚未同意上传协议');
//	        		return false;
//	        	}
	        	if(hh == 0 && mm == 0 && ss == 0){
	        		$.msg('请填写视频时长');
	        		return false;
	        	}
	        	//截取网址域
	        	//截取网址域
				if(url.indexOf("http://") >= 0){
					url = url.substring(7);
				}else if(url.indexOf("https://") >= 0){
					url = url.substring(8);
				}
				var httpTitle = url.substring(0,url.indexOf("/"));
				var videoType = httpTitle;
				var storageType="";
				if(httpTitle == "v.youku.com"){
					storageType = "VIDEO_STORAGE_TYPE_YK";
				}else if(httpTitle == "www.tudou.com"){
					storageType = "VIDEO_STORAGE_TYPE_TD";
				}else if(httpTitle == "console.video.capitalcloud.net"){
					storageType = "VIDEO_STORAGE_TYPE_SS";
				}else if(httpTitle == "qzonestyle.gtimg.cn"){
					storageType = "VIDEO_STORAGE_TYPE_QQ";
				}else{
					$.msg('您输入的地址不合法');
					return false;
				}
	        	//保存到video
	        	var videoTime = (hh.length == 2 ? hh : "0" + hh) + ":" + (mm.length == 2 ? mm : "0" + mm) + ":" + (ss.length == 2 ? ss : "0" + ss);
	        	$.ajax({
	        		url:rootPath + "/video/saveVideoUrl",
	        		type:"post",
	        		data:{"videoName":videoName,"videoTime":videoTime,"videoStatus":"VIDEO_PROCESS_NOMAL","itemOneId":oneItemId,"itemSecondId":twoItemId,"videoTag":tag,"webVideoId":outUrl,"webVideoDomain":videoType,"storageType":storageType},
	        		dataType:"json",
					beforeSend:function(XMLHttpRequest){
			             $.loading("")
			         },
	        		success:function(data){
	        			if(data.msg == "success"){
	        				var domain = "";
	        				/*if(videoType == "open.163.com"){
	        					domain = "网易公开课";
	        				}else */if(videoType == "v.youku.com"){
	        					domain = "优酷视频";
	        				}else if(videoType == "www.tudou.com"){
	        					domain = "土豆视频";
	        				}else if(videoType == "console.video.capitalcloud.net"){
	        					domain = "石山视频";
	        				}
//	        				$(".v_url").prepend("<tr data-id='" + data.id + "'><td><div class='operate_w'>" + videoName + "</div></td><td>" + domain + "</td><td>正常</td><td><a class='btn btn-sm btn-del' href='javascript:;' data-id='" + data.id + "'>删除</a></td></tr>");
	        				// 刷新视频列表
	        				$(".default-div").show();
	        				$(".add-div").hide();
	        				$this.searchVideos();
	        				$(".add-div").find(".itemOne").val(-1);
	        				$(".add-div").find(".itemSecond").val(-1);
	        				$(".add-div").find(".tag").val("");
	        				$(".add-div").find(".hour").val(0);
	        				$(".add-div").find(".minute").val(0);
	        				$(".add-div").find(".second").val(0);
	        				$(".add-div").find(".video-url").val("");
	        				$(".add-div").find(".video-name").val("");
	        				outUrl = "";
	        				videoType = "";
	        			}else{
							$.msg("未保存");
	        			}
	        		},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			            $.loadover()
			        }
	        	});
			}).on("click.btn.cancle",".tab2 .btn-c",function(){
				$(".add-div").hide();
				$(".default-div").show();
			})
		},
		//绑定题库事件
		init900: function(){
			var $this=this;
			//初始化题库
			$.ajax({
				url:rootPath+'/tikuCategory/getList',
				type:'post',
				dataType:'json',
				success:function(jsonData){
					$("#choose_tiku").find("option").remove();
					$("#choose_tiku").append("<option value=''>选择题库</option>");
					$("#choose_item").append('<option value="">选择科目</option>');
					$.each(jsonData,function(i,data){
						$("#choose_tiku").append('<option value="'+data.id+'">'+data.tikuName+'</option>')
					})
				}
			})
			$(".w900").on("change","#choose_tiku",function(){
				//初始化题库下科目
				$.ajax({
					url:rootPath+'/tikuSubject/getList/'+$(this).val(),
					type:'post',
					dataType:'json',
					success:function(jsonData){
						$("#choose_item").find("option").remove();
						$("#choose_item").append('<option value="">选择科目</option>');
						$.each(jsonData,function(i,data){
						$("#choose_item").append('<option value="'+data.id+'">'+data.subjectName+'</option>')
					})
					}
				})
			})
			
			//搜索试卷
			.on("click.btn.search","#search_paper",function(){
				$this.searchPapers();
			})
			//单击行选中试卷
			.on("click.tr.choose","#data_table_2 tr",function(){
				var d=$(this).data("paper");
				var data={};
				data.tikuCategoryId=d.tikuCategoryId;
				data.paperId=d.id;
				data.classTypeId=$("#classTypeId").val();
				data.resourceType="TEACH_METHOD_VIDEO";
				data.resourceId=$(".t-c-r").find(".lecture").attr("value");
				data.tikuResourceType="PAPER";
				data.tikuSubjectId=d.tkuSubjectId;
				$(".t-c-r").find(".n-list").html('').append('<b><i class="list-item">'+d.paperName+'</i> <a class="del" ><strong>删除</strong></a></b>');
				$(".t-c-r").find(".n-list").data("exercise",data);
				$('.add-layer-bg').fadeOut(200,function(){
					$(".w900").fadeOut(200);
				});
			})
		},
		init1000: function(){
			var $this=this;
			//初始化题库
			$.ajax({
				url:rootPath+'/tikuCategory/getList',
				type:'post',
				dataType:'json',
				success:function(jsonData){
					$("#choose_tiku_2").find("option").remove();
					$("#choose_tiku_2").append("<option value=''>选择题库</option>")
					$.each(jsonData,function(i,data){
						$("#choose_tiku_2").append('<option value="'+data.id+'">'+data.tikuName+'</option>')
					})
				}
			})
			$(".w1000").on("change","#choose_tiku_2",function(){
				//初始化题库下科目
				$.ajax({
					url:rootPath+'/tikuSubject/getList/'+$(this).val(),
					type:'post',
					dataType:'json',
					success:function(jsonData){
						$("#choose_subject_2").find("option").remove();
						$("#choose_subject_2").append('<option value="">选择科目</option>')
						$.each(jsonData,function(i,data){
						$("#choose_subject_2").append('<option value="'+data.id+'">'+data.subjectName+'</option>')
					})
					}
				})
			})
			.on("change","#choose_subject_2",function(){
				//初始化题库下科目
				$.ajax({
					url:rootPath+'/tikuChapter/getList',
					data:{
						tikuCategoryId: $("#choose_tiku_2").val(),
						tikuSubjectId: $(this).val()
					},
					type:'post',
					dataType:'json',
					success:function(jsonData){
						$("#choose_chapter_2").find("option").remove();
						$("#choose_chapter_2").append('<option value="">选择章</option>')
						$.each(jsonData,function(i,data){
						$("#choose_chapter_2").append('<option value="'+data.id+'">'+data.chapterName+'</option>')
					})
					}
				})
				
			})
			.on("change","#choose_chapter_2",function(){
				//初始化题库下科目
				$.ajax({
					url:rootPath+'/tikuSection/getList',
					data:{
						tikuCategoryId: $("#choose_tiku_2").val(),
						chapterId: $(this).val()
					},
					type:'post',
					dataType:'json',
					success:function(jsonData){
						$("#choose_lecture_2").find("option").remove();
						$("#choose_lecture_2").append('<option value="">选择节</option>')
						$.each(jsonData,function(i,data){
						$("#choose_lecture_2").append('<option value="'+data.id+'">'+data.sectionName+'</option>')
					})
					}
				})
			})
			.on("click.btn",".btn",function(){
				var data={};
				data.tikuCategoryId=$("#choose_tiku_2").find("option:selected").val();
				data.tikuSubjectId=$("#choose_subject_2").find("option:selected").val();
				data.tikuChapterId=$("#choose_chapter_2").find("option:selected").val();
				data.tikuSectionId=$("#choose_lecture_2").find("option:selected").val();
				data.categoryName=$("#choose_tiku_2").find("option:selected").val()?$("#choose_tiku_2").find("option:selected").text():"";
				data.chapterName=$("#choose_chapter_2").find("option:selected").val()?$("#choose_chapter_2").find("option:selected").text():"";
				data.lectureName=$("#choose_lecture_2").find("option:selected").val()?$("#choose_lecture_2").find("option:selected").text():"";
				data.classTypeId=$("#classTypeId").val();
				data.resourceType="TEACH_METHOD_VIDEO";
				data.tikuResourceType="CHAPTER";
				data.resourceId=$(".t-c-r").parents(".lecture").attr("value");
				data.topicNum=$("#choose_num_2").val();
				
				//检查数据
				if(!data.tikuChapterId){
					$.msg("必须选择章或节");
					return ;
				}
				
				if(!data.topicNum){
					$.msg("做题数量不能为空");
					return;
				}
				
				$('.t-c-r').find('#shiquanorzhangjiexinxi').text('章节信息：');
				$(".t-c-r").find(".n-list").html('<tr><td width="30%" title="'+data.chapterName+'">'+(data.chapterName.length>10?data.chapterName.substring(0,10):data.chapterName)+'</td><td width="30%" title="'+data.lectureName+'">'+(data.lectureName.length>10?data.lectureName.substring(0,10):data.lectureName)+'</td><td width="40%"><a href="javascript:;">删除</a></td></tr>');
				$(".t-c-r").find(".n-list").data("exercise",data);
				$('.add-layer-bg').fadeOut(200,function(){
					$(".w1000").fadeOut(200);
				});
			})
			$("#choose_num_2").on("keyup",function(){
				if($(this).val() && isNaN($(this).val())){
					$(this).val("");
				}
			})
			
		},
		init1100 : function(){
			var $this=this;
			//初始化一学科小类
			$("#choose_tiku1").getSysItem();
			$("#choose_tiku1").on("change",function(){
				$(".w1100").find("#choose_item1").getSysItem($(this).val(),function(){
					
				});
			});
			//初始化资源列表
			$(".w1100").find("#choose_tiku1").find("option[value='"+$("#itemOneId").val()+"']").attr("selected","selected");
			$(".w1100").find("#choose_item1").find("option[value='"+$("#itemSecondId").val()+"']").attr("selected","selected");
			
			//单机选中资源文件，双击选择资源文件
			$(".w1100").on("click.row.model",".term-list tr[has-data]",function(){
				$(".w1100").find(".term-list tr").removeClass("active");
				$(this).addClass("active");
			})
			//检索资源文件
			.on("click",".q-btn-primary2",function(){
				setTimeout(function () {
					$this.searchResource();
				  }, 500);
			})
			.on("click.row.model",".term-list tr[has-data]",function(){
				$(".video_name").val($(this).children().eq(0).html());
				$(".file_id").val($(this).attr("id"));
				$('.w1100').fadeOut(200,function(){
                    $('.add-layer-bg').fadeOut(200);
                });
			})
		},
		init1300: function(){
			var $this=this;
			var i = 0;
			//初始化题库
			$.ajax({
				url:rootPath+'/tikuCategory/getList',
				type:'post',
				dataType:'json',
				success:function(jsonData){
					$("#choose_tiku_3").find("option").remove();
					$("#choose_tiku_3").append("<option value=''>选择题库</option>")
					$.each(jsonData,function(i,data){
						$("#choose_tiku_3").append('<option value="'+data.id+'">'+data.tikuName+'</option>')
					})
				}
			})
			$(".w1400").on("change","#choose_tiku_3",function(){
				//初始化题库下科目
				$.ajax({
					url:rootPath+'/tikuSubject/getList/'+$(this).val(),
					type:'post',
					dataType:'json',
					success:function(jsonData){
						$("#choose_subject_3").find("option").remove();
						$("#choose_subject_3").append('<option value="">选择科目</option>')
						$.each(jsonData,function(i,data){
						$("#choose_subject_3").append('<option value="'+data.id+'">'+data.subjectName+'</option>')
					})
					}
				})
			})
			.on("change","#choose_subject_3",function(){
				//初始化题库下科目
				$.ajax({
					url:rootPath+'/tikuChapter/getList',
					data:{
						tikuCategoryId: $("#choose_tiku_3").val(),
						tikuSubjectId: $(this).val()
					},
					type:'post',
					dataType:'json',
					success:function(jsonData){
						$("#choose_chapter_3").find("option").remove();
						$("#choose_chapter_3").append('<option value="">选择章</option>')
						$.each(jsonData,function(i,data){
						$("#choose_chapter_3").append('<option value="'+data.id+'">'+data.chapterName+'</option>')
					})
					}
				})
				
			})
			.on("change","#choose_chapter_3",function(){
				//初始化题库下科目
				$.ajax({
					url:rootPath+'/tikuSection/getList',
					data:{
						tikuCategoryId: $("#choose_tiku_3").val(),
						chapterId: $(this).val()
					},
					type:'post',
					dataType:'json',
					success:function(jsonData){
						$("#choose_lecture_3").find("option").remove();
						$("#choose_lecture_3").append('<option value="">选择节</option>')
						$.each(jsonData,function(i,data){
						$("#choose_lecture_3").append('<option value="'+data.id+'">'+data.sectionName+'</option>')
					})
					}
				})
			})
			.on("click.btn",".btn",function(){
				var data={};
				data.categoryId=$("#choose_tiku_3").find("option:selected").val();
				data.subjectId=$("#choose_subject_3").find("option:selected").val();
				data.chapterId=$("#choose_chapter_3").find("option:selected").val();
				data.lectureId=$("#choose_lecture_3").find("option:selected").val();
				data.categoryName=$("#choose_tiku_3").find("option:selected").val()?$("#choose_tiku_3").find("option:selected").text():"";
				data.chapterName=$("#choose_chapter_3").find("option:selected").val()?$("#choose_chapter_3").find("option:selected").text():"";
				data.sectionName=$("#choose_lecture_3").find("option:selected").val()?$("#choose_lecture_3").find("option:selected").text():"";
				data.testId=$('.t-c-r').find(".test").attr("value");
				data.testType = 0;
				data.delIndex = i;
				
				//检查数据
				if(!data.chapterId || !data.lectureId){
					$.msg("必须选择章或节");
					return ;
				}
				var items = $(".t-c-r").find("#courseList"+data.testId).data("exercises");
				if(items == null || items == undefined)
					items = [];
				if(items && items.length >0){
					for(var m=0;m<items.length;m++){
						if(items[m]&&items[m].testType&&items[m].testType==1){
							items = [];
							break;
						}
					}
				}
				if($('.t-c-r').find('#ziliaotitle').text()==''){
					$('.t-c-r').find('#ziliaotitle').text('章节列表：')
				}
				items.push(data);
				$(".t-c-r").find("#courseList"+data.testId).append('<b><i class="list-item">'+data.chapterName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+data.sectionName+'</i> <a href="javascript:;" class="delrel" delIndex = '+i+'><strong>删除</strong></b>');
				++i;
				$(".t-c-r").find("#courseList"+data.testId).data("exercises",items);
				$('.add-layer-bg').fadeOut(200,function(){
					$(".w1400").fadeOut(200);
				});
			})
		},
		init1200 : function(){
			var $this=this;
			var flag1 = true;
			//初始化题库
			$.ajax({
				url:rootPath+'/tikuCategory/getList',
				type:'post',
				dataType:'json',
				success:function(jsonData){
					$("#choose_tiku").find("option").remove();
					$("#choose_tiku").append("<option value=''>选择题库</option>")
					$.each(jsonData,function(i,data){
						$("#choose_tiku").append('<option value="'+data.id+'">'+data.tikuName+'</option>')
					})
				}
			})
			$(".w900").on("change","#choose_tiku",function(){
				//初始化题库下科目
				$.ajax({
					url:rootPath+'/tikuSubject/getList/'+$(this).val(),
					type:'post',
					dataType:'json',
					success:function(jsonData){
						$("#choose_item").find("option").remove();
						$("#choose_item").append('<option value="">选择科目</option>')
						$.each(jsonData,function(i,data){
						$("#choose_item").append('<option value="'+data.id+'">'+data.subjectName+'</option>')
					})
					}
				})
			})
			
			//搜索试卷
			.on("click.btn.search","#search_paper2",function(){
				$this.searchPapers();
			})
			//单击行选中试卷
			.on("click.tr.choose","#data_table_2 tr",function(){
				var d=$(this).data("paper");
				var data={};
				data.paperId=d.id;
				data.testId=$(".t-c-r").find(".test").attr("value");
				data.testType = 1;
				data.paperName=d.paperName;
				var items = $(".t-c-r").find("#courseList"+data.testId).data("exercises");
				if(items == null || items == undefined)
					items = [];
				if(items && items.length>0){
					for(var j=0;j<items.length;j++){
						if(items[j]&&items[j].testType == 0){
							items = [];
							break;
						}
					}
				}
				if(items&&items.length>0){
					flag1 = true;
					for(var x=0;x<items.length;x++){
						if(items[x]&&items[x].paperId && items[x].paperId == data.paperId){
							flag1 = false;
							break;
						}
					}
					if(flag1){
						items.push(data);
						if($('.t-c-r').find('#ziliaotitle').text()==''){
							$('.t-c-r').find('#ziliaotitle').text('试卷列表：')
						}
						$(".t-c-r").find("#courseList"+data.testId).append('<b><i class="list-item">'+d.paperName+'</i> <a class="del" mark1="'+d.id+'"><strong>删除</strong></a></b>');
					}
				}else{
					items.push(data);
					if($('.t-c-r').find('#ziliaotitle').text()==''){
						$('.t-c-r').find('#ziliaotitle').text('试卷列表：')
					}
					$(".t-c-r").find("#courseList"+data.testId).append('<b><i class="list-item">'+d.paperName+'</i> <a class="del" mark1="'+d.id+'"><strong>删除</strong></a></b>');
				}
				$(".t-c-r").find("#courseList"+data.testId).data("exercises",items);
				$('.add-layer-bg').fadeOut(200,function(){
					$(".900").fadeOut(200);
				});
			})
		}
		
		
		
	}
	
	
	
	$(document).ready(function(){
		var page=new Page();
		page.init();
	})
})(jQuery)