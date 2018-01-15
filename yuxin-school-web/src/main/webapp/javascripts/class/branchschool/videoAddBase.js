
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
				url : rootPath+"/branchSchool/customChapter",
				type: "post",
				data:{"companyId":$("#companyId").val()},
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
	                    /* '<i class="iconfont">&#xe626;</i>'+
	                   '<i class="iconfont3 add-btns" >&#xe629;'+
	                    '<b class="add-item">'+
	                    '<span class="xiaojie">新增小节</span>'+
	                    ($this.service.tikuService?'<span class="ceyan">新增测验</span>':'')+
	                    '</b>'+
	                    '</i>'+*/
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
								/*'<i class="iconfont">&#xe626;</i>'+*/
								'</li>';
								$(".t-c-l").find("#chapter_"+chapter.id+" .son").append(sub_html);
								$(".t-c-l").find("#lecture_"+lecture.id).data("info",lecture);
								k++;
							}else if(lecture.type == 2){
								var sub_html='<li id="test_'+lecture.id+'" class="class-son" draggable="true">'
								+'<a href="javascript:;"><span class="iconfont add-ceyan">&#xe6d5;</span>测验'+
								'&nbsp;&nbsp;<b>'+(lecture.name?lecture.name:"")+'</b></a>'+
								'<i class="iconfont4">&#xe630;</i>'+
								/*'<i class="iconfont">&#xe626;</i>'+*/
								'</li>';
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
		//章信息
		showChapter : function(chapter){
			chapter=$("#"+chapter.attr("id"));//别删，有用
			if(chapter.attr("id")){
				var chapter_id=chapter.attr("id").substring(chapter.attr("id").indexOf("_")+1);
				var chapterDetail='<div class="t-c-r-t chapter" value="'+chapter_id+'">'+
	                '<p class="c">'+
	                '<span class="c-title">章的名称</span>'+
	                '<span class="c-content"><input class="chapterName readonly" type="text" maxlength="20" value="" placeholder="章的名称"></span>'+
	            '</p>'+
	            '</div>';
				$(".t-c-r").html(chapterDetail);
			}else{
				var chapterDetail='<div class="t-c-r-t chapter" value="">'+
	                '<p class="c">'+
	                '<span class="c-title">章的名称</span>'+
	                '<span class="c-content"><input class="chapterName readonly" maxlength="20" type="text" value="" placeholder="章的名称"></span>'+
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
		        '<input class="lectureName  readonly" maxlength="20" type="text" placeholder="输入名称">'+
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
		        '<input type="text" class="video_name q-text  readonly"/> <input type="hidden" class="video_id"><input type="hidden" class="file_id"><input type="hidden" class="publishStatus"/><input type="hidden" class="publishDate"/>'+
		        '</span>'+
		        '</p>'+'<div class="tab-cont">'+
		        '<div class="tab-list">'+'<span  class="tab-left active" >课程资料</span>'+
                '<span class="tab-right"  id="ss2">课后练习</span>'+
                '</div>'+    
                 '<div class="tab-item">'+  
                '<div class="q-item one ">'+    
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
                       '<p class="c clear q-c">'+
                       '<span class="c-title" id="shiquanorzhangjiexinxi">试卷信息：</span>'+
                           '<span class="c-content n-list" >'+ 
                            ' </span>'+
                            '   </p>'+
                            '</div>'+
                            '</div>'+
                            '</div>'+   
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
				url: rootPath+"/branchSchool/find",
				data: {
					resourceId:lecture_id,
					"companyId":$("#companyId").val(),
					resourceType: "TEACH_METHOD_VIDEO",
				},
				type: "post",
				dataType: "json",
				success: function(exercise){
					if(exercise.tikuResourceType=="PAPER"){
						$('.t-c-r').find('#shiquanorzhangjiexinxi').text('试卷信息：');
						$(".t-c-r").find(":radio[id='bind-paper']").data("exercise",exercise).trigger('click');
						$(".t-c-r").find(".n-list").html('<b><i class="list-item">'+exercise.paperName+'</i></b>');
					}else{
						$('.t-c-r').find('#shiquanorzhangjiexinxi').text('章节信息：');
						$(".t-c-r").find(":radio[id='bind-chapter']").data("exercise",exercise).trigger('click');
						$(".t-c-r").find(".n-list").html('<tr><td width="30%">'+(exercise.chapterName?exercise.chapterName:"")+'</td><td width="30%">'+(exercise.sectionName?exercise.sectionName:"")+'</td><td width="40%"></td></tr>');
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
             '<input class="lectureName q-text readonly" maxlength="20" type="text" placeholder="输入名称"></span>'+
              '</p>'+ 	
              '<p class="c">'+ 
              '<span class="c-title" id="dabiaofenshu1">达标分数：</span>'+
              '<span class="c-content">'+
              '<input type="text" class="video_name q-text1 readonly" >'+
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
               '<input type="text" class="video_name q-text readonly" id="kaoshinumber">'+
                '</span>'+   
                '</p>'+
                 '<p class="c clear q-c" style="margin-top:-6px;">'+
                 '<span class="c-title" id="ziliaotitle">试卷列表：</span>'+
                 '<span class="c-content" id="courseList'+test_id+'"></span></p>'+
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
									$(".t-c-r").find("#courseList"+test_id).append('<b><i class="list-item">'+data.chapterName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+data.sectionName+'</i></b>');	
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
									$(".t-c-r").find("#courseList"+test_id).append('<b><i class="list-item">'+data.paperName+'</i></b>');	
								})
							}
							$(".t-c-r").find("#courseList"+test_id).data('exercises',jsonData);
						}
						
					})*/
				}
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
			var classid=$("#classTypeId").val();
			var companyId=$("#companyId").val();
			 $.ajax({
				 url : rootPath + "/branchSchool/rourseList",
				 type: "post",
				 data:{"companyId":companyId,"classTypeId":classid,"lectureId":id,"lectureType":"TEACH_METHOD_VIDEO"},
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
									html+='<b><i class="list-item">'+item.name+'</i></b>';
								}else if(name.substring(name.lastIndexOf(".")+1)=="xls"||name.substring(name.lastIndexOf(".")+1)=="xlsx"){
									html+='<b><i class="list-item">'+item.name+'</i></b>';
								}else if(name.substring(name.lastIndexOf(".")+1)=="pdf"){
									html+='<b><i class="list-item">'+item.name+'</i></b>';
								}else if(name.substring(name.lastIndexOf(".")+1)=="ppt"||name.substring(name.lastIndexOf(".")+1)=="pptx"){
									html+='<b><i class="list-item">'+item.name+'</i></b>';
								}else{
									html+='<b><i class="list-item">'+item.name+'</i></b>';
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
/*			$(".t-c-l").on("click.li.left",".class-son",function(){
				var data={};
				if($(this).find('span').attr('class') == 'add-xiaojie'){
					data.lectureOrder=$(this).index();
					$this.showLecture($(this));
				}else{
					$this.showTest($(this));
				}
				
			});*/
			
			$('.q-type-choice').on('click',function(){
				setTimeout(function () {
					$this.searchResource();
				  }, 500);
			});
			
			$(".t-c-l").find(".dis").eq(0).trigger("click.li.left");
			//返回
			$(".btn.back").on("click",function(){
				//location.href=rootPath+$('#backurl').val();
				history.back();
			})
			
			$('.t-c-r').find(".tab-cont .tab-list span .active").trigger('click');
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
						url: rootPath+"/branchSchool/find",
						data: {
							resourceId:lecture_id,
							"companyId":$("#companyId").val(),
							resourceType: "TEACH_METHOD_VIDEO",
						},
						type: "post",
						dataType: "json",
						success: function(exercise){
							 if(exercise && exercise.tikuResourceType=="CHAPTER"){
								$(".t-c-r").find(".n-list").html('<tr><td width="30%">'+(exercise.chapterName?exercise.chapterName:"")+'</td><td width="30%">'+(exercise.sectionName?exercise.sectionName:"")+'</td><td width="40%"></td></tr>');
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
					url: rootPath+"/branchSchool/find",
					data: {
						resourceId:lecture_id,
						"companyId":$("#companyId").val(),
						resourceType: "TEACH_METHOD_VIDEO",
					},
					type: "post",
					dataType: "json",
					success: function(exercise){
						if(exercise && exercise.tikuResourceType=="PAPER"){
							$(".t-c-r").find(".n-list").html('<b><i class="list-item">'+exercise.paperName+'</i></b>');
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
									$(".t-c-r").find("#courseList"+testId).append('<b><i class="list-item">'+data.paperName+'</i></b>');	
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
								$(".t-c-r").find("#courseList"+testId).append('<b><i class="list-item">'+data.chapterName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+data.sectionName+'</i></b>');	
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

		}
		
	}
	$(document).ready(function(){
		var page=new Page();
		page.init();
	})
})(jQuery)