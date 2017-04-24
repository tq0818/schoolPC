	/**
 * 在jQuery加载完成后执行
 */
(function($){
	var Model = {};
	Model.ajaxLoad = function(url,dataInfo,func){
  		 $.ajax({ 
  	 		  type: "post", 
  	 		  url : rootPath+url, 
  	 		  data: dataInfo,
  			  beforeSend:function(XMLHttpRequest){
  	            $(".loading").show();
  	            $(".loading-bg").show();
  	          },
  	 		  success: func,
  	 		  complete:function(XMLHttpRequest,textStatus){
  				  $(".loading").hide();
  		          $(".loading-bg").hide();
  		      }
  	 	  });
  	};
  	Model.ajax = function(){
		var arg = Array.prototype.slice.call(arguments),
			success = arg.length>2?arg[2]:function(){},
			error = arg.length>3?arg[3]:function(){};
		$.ajax({ 
	 		  type: "post", 
	 		  url : rootPath+arg[0], 
	 		  data: arg[1],
	 		  success: success,
	 		  error : error
	 	  });
	}
  	Model.init = function(){
  		this.event();
  		
  	};
  	Model.event = function(){
  		var _this = this;
  		$('body').addClass('q-box');
  		$selectSubMenu('home_work');
  		_this.getClassType();
  		_this.queryTikuList("","choose_tiku",0);	
  		
  	    //查询课次
  	    $('.selClass,.selAll').on('change',function(){
  	    	_this.searchLesson();
  	    });
  	    
  	    //留作业
  	    $(document).on('click','.noHomeWork',function () {
  	    	var hw = $(this).parents('li').data();
  	        _this.insertOrUpdate(hw);
  	    });
  	    //更改作业
  	    $(document).on('click','.updatehw',function(){
  	    	var hwId = $(this).parents('li').data('id');
  	    	_this.ajax('/homework/selOne',{id:hwId},function(result){
  	    		if(!result.flag){
  	    			$.msg(result.msg || '操作失败');
  	    			return;
  	    		}
  	    		_this.insertOrUpdate(result.hw);
  	    	});
  	    });
  	    //查阅作业
  	    $(document).on('click','.seldetail',function(){
  	    	var hw = $(this).parents('li').data();
  	    	
  	    	window.open(rootPath + '/homeworkStudentComplete/gotoHomeworkStudentCompletePage/' + hw.morl + '/'+ hw.moduleId + '/'+ hw.id);
  	    });
  	    //停用/启用作业
  	    $(document).on('click','.stophw',function(result){
  	    	var hwId = $(this).parents('li').data('id'),
  	    		_hw,$this = $(this);
  	    	_this.ajax('/homework/selOne',{id:hwId},function(result){
  	    		if(!result.flag){
  	    			$.msg(result.msg || '操作失败');
  	    			return;
  	    		}
  	    		_hw = result.hw;
  	    		_hw.status = _hw.status?0:1;
  	    		
  	    		_this.ajax('/homework/insertOrUpdate',_hw,function(result){
  	  	    		if(!result.flag){
  	  	    			$.msg(result.msg || '操作失败');
  	  	    			return;
  	  	    		}
  	  	    		
  	  	    		//更新数据状态
  	  	    		$this.html(_hw.status?'停用作业':'启用作业');
  	  	    		$this.parents('li').data('status',_hw.status);
  	  	    		$.msg('操作成功');
  	  	    	});
  	    	});
  	    	
  	    });
  	    
  	    //联动查询题库下的科目
  	    $('#choose_tiku').on('change',function(){
  	    	_this.queryTikuList($(this).val(),"choose_item",1);
  	    });
  	    //搜索题库
  	    $('#search_paper').on('click',function(){
  	    	_this.tikuSearch.tikuCategoryId = $('#choose_tiku').val();
  	    	_this.tikuSearch.tkuSubjectId = $('#choose_item').val();
  	    	_this.tikuSearch.paperName = $('#choose_paper').val();
  	    	_this.findTikuPaper();
  	    });
  	    //点击试题
  	    $('.table-list').on('click','tr',function(){
  	    	var tikuPaper = $(this).data('paper');
  	    	$('#test').data('paperId',tikuPaper.id);
  	    	if(!$('.shijuanmc .EncName').length){
  	    		$('.shijuanmc').append('<div class="fl rightCon"><span class="EncName">'+tikuPaper.paperName+'</span><span class="EncClose">删除</span></div>')
  	    	}else{
  	    		$('.shijuanmc .EncName').html(tikuPaper.paperName);
  	    	}
  	    	$('.classEnc,.add-classes-bg').hide();
  	    });
  	    //删除
  	    $(document).on('click','.EncClose',function(){
  	    	var $this = $(this);
  	    	if(!$('.shijuan').is(':hidden')){
  	    		$('#test').data('paperId',null);
  	    		$(this).parent().remove();
  	    	}else{
  	    		_this.ajax('/classTypeResource/delreslist',{id:$('#Enclosure').data('resourceId')},function(result){
  	    			if(result.msg == 'success'){
  	    				$('#Enclosure').data('resourceId',null);
  	    				$this.parent().remove();
  	    			}else{
  	    				$.msg(result.msg || '删除失败');
  	    			}
  	    		});
  	    	}
  	    });
  	    // 试卷和附件切换
  	    $(document).on('click','.rightCon input[type="radio"]',function () {
  	        if($(this).attr('id')=='test'){
  	            $('.fujian').hide();
  	            $('.shijuan').show();
  	        }
  	        if($(this).attr('id')=='Enclosure'){
  	        	$('.fujian').show();
  	            $('.shijuan').hide();
  	        }
  	    });
  	    // 选择试卷
  	    $(document).on('click','.TestName',function () {
  	        $('.classEnc,.add-classes-bg').show();
  	    });
  	    $(document).on('click','.q-close',function () {
  	        $('.classEnc,.add-classes-bg').hide();
  	    });
  	};
  	
  	
  	
  	/**
	 * 获取所有课程
	 */
  	Model.getClassType = function(){
  		var _this = this;
  		this.ajax('/homework/findAllClass',{},function(result){
  			if(result && result.length){
  				var html = '<option value="">请选择</option>';
  				$.each(result,function(i,obj){
  					html += '<option value="'+ obj.id +'">'+ obj.name +'</option>';
  				});
  				$('.selClass').html(html);
  			}
  			$(".selClass").select2();
  		});
  	};
  	/**
  	 * 查询课程内的课次
  	 */
  	Model.searchLesson = function(page){
  		var courseId 	   = $('#selClass').val(),
	  		homeworkStatus = $('.selAll').val(),
	  		search 		   = {},
	  		html		   = '',
	  		_this		   = this;
  		search.courseId    = courseId;
  		search.homeworkStatus = homeworkStatus;
  		search.page = page?page:1;
  		
  		this.ajaxLoad('/homework/findClassTypeLesson',search,function(result){
  			$.each(result.data,function(i,obj){
					html += '<li data-id='+ obj.id +' data-lesson-id='+ obj.lessonId +' data-lecture-id='+ obj.lectureId +' data-course-id='+ obj.courseId +'  data-module-id='+ obj.moduleId +' data-morl='+ obj.morl +' data-status='+ obj.status +' class="'+ (obj.lessonId ? ('lesson_' + obj.lessonId) : ('lecture_'+obj.lectureId)) +'">'+
	  	                    '<table class="table class-table">'+
			                    '<tr  align="left">'+
			                        '<th colspan="2">'+ obj.courseName +'</th>'+
			                        '<th colspan="2">'+ (obj.lessonName?obj.lessonName:'') +'</th>'+
			                        '<th>'+ (obj.classModuleNoName?obj.classModuleNoName:'') +'</th>'+
			                        '<th>课程属性:<span>'+ obj.classType +'</span></th>'+
			                    '</tr>'+
			                    '<tr>'+
			                        '<td width="14%">'+
			                            '<p>'+
			                                '<span>学员数量：</span>'+
			                                '<em>'+ obj.stuNum +'</em>'+
			                            '</p>'+
			                        '</td>'+
			                        '<td width="14%">'+
			                            '<p '+ _this.homeworkStatusColor(obj.notCommitedHomeWork,obj.id) +'>'+
			                                '<span>未交作业学员：</span>'+
			                                '<em>'+ obj.notCommitedHomeWork +'</em>'+
			                            '</p>'+
			                        '</td>'+
			                        '<td width="14%">'+
			                            '<p '+ _this.homeworkStatusColor1(obj.commitedHomeWork,obj.id) +'>'+
			                                '<span>已交作业学员：</span>'+
			                                '<em>'+ obj.commitedHomeWork +'</em>'+
			                            '</p>'+
			                        '</td>'+
			                        '<td width="14%">'+
			                            '<p '+ _this.homeworkStatusColor1(obj.readedHomeWork,obj.id) +'>'+
			                                '<span>已批阅个数：</span>'+
			                                '<em>'+ obj.readedHomeWork +'</em>'+
			                            '</p>'+
			                        '</td>'+
			                       '<td width="20%">'+
			                            '<p '+ _this.homeworkStatusColor(obj.notReadedHomeWork,obj.id) +'>'+
			                                '<span>未批阅个数：</span>'+
			                                '<em>'+ obj.notReadedHomeWork +'</em>'+
			                            '</p>'+
			                        '</td>'+
			                        '<td width="24%">'+
			                            '<p class="stuBtn">'+
			                                _this.homeworkStatusBtns(obj)+
			                            '</p>'+
			                        '</td>'+
			                    '</tr>'+
			                '</table>'+
			            '</li>';
			});
  			$('.classList-Con').html(html);
  			
			if(result.rowCount>result.pageSize){
				$(".classPages").pagination(result.rowCount, {
			    	 next_text : "下一页",
			    	 prev_text : "上一页",
			    	 current_page : result.pageNo-1,
			    	 link_to : "javascript:void(0)",
			    	 num_display_entries : 8,
			    	 items_per_page : result.pageSize,
			    	 num_edge_entries : 1,
			    	 callback:function(page,jq){
				    	 var pageNo = page + 1; 
				    	 _this.searchLesson(pageNo);
			    	 }
			   });
			}else{
				$(".classPages").html('');
			}
			
			if(!html){
				$('.nolesson').show();
			}else{
				$('.nolesson').hide();
			}
  		});
  	}
  	Model.homeworkStatusBtns = function(homework){
  		var html = '',
  			status = homework.homeworkStatus;
  		if(!status){
  			html = '<button class="noHomeWork">留作业</button>';
  		}else if(status == 3){
  			html = '<button class="bgRed seldetail">查看批阅</button>'+
		            '<button class="updatehw">更改作业</button>'+
		            '<button class="stophw">'+ (homework.status ? '停用' : '启用') +'作业</button>';
  		}else{
  			html = '<button class="seldetail">查看批阅</button>'+
		            '<button class="updatehw">更改作业</button>'+
		            '<button class="stophw">'+ (homework.status ? '停用' : '启用') +'作业</button>';
  		}
  		return html;
  	}
  	Model.homeworkStatusColor = function(count,id){
  		var html = '';
  		if(!count){
  			html = 'class="colorGray '+ (!id?'none':'') +'"';
  		}else{
  			html = 'class="colorRed '+ (!id?'none':'') +'"';
  		}
  		return html;
  	}
  	Model.homeworkStatusColor1 = function(count,id){
  		var html = '';
  		if(!count){
  			html = 'class="colorGray '+ (!id?'none':'') +'"';
  		}else{
  			html = 'class="'+ (!id?'none':'') +'"';
  		}
  		return html;
  	}
  	
  	Model.queryTikuList = function(tikuOrSubjectId,nodeId,type){
		$.ajax({
			url:rootPath+"/student_detail/studentTiKuList",
			type:"post",
			data:{id:tikuOrSubjectId,type:type},
			success:function(jsonData){
				Model.assTiKuOrSubject(nodeId,jsonData,type);
			}
		});
	}
  	Model.assTiKuOrSubject = function(nodeId,arr,type){
		var html ;
		if(!type){
			html = '<option value="">题库名称</option>';
		}else{
			html = '<option value="">科目名称</option>';
		}
		$.each(arr,function(i,obj){
			html += '<option value="'+obj.id+'">'+obj.name+'</option>';
		});
		$("#"+nodeId).html(html);
	};
  	
  	Model.tikuSearch = {paperType : 'PAPER_TYPE_HOMEWORK'};
  	/**
  	 * 查找题库
  	 */
  	Model.findTikuPaper = function(page){
  		var _this = this,
  			html  = '';
  		_this.tikuSearch.page = page?page:0;
  		_this.tikuSearch.pageSize = 10;
  		this.ajaxLoad('/homework/findTikuPaper',_this.tikuSearch,function(jsonData){
  			$('.table-tbody tbody').html('');
  			$.each(jsonData.data,function(i,obj){
  				html += '<tr data-paper='+JSON.stringify(obj)+'>'+
		                    '<td>'+ obj.paperName +'</td>'+
		                    '<td>'+ obj.categoryName +'</td>'+
		                    '<td>'+ obj.subjectName +'</td>'+
		                    '<td>'+ obj.dictName +'</td>'+
		                '</tr>';
  			});
  			if(!html) html = '<tr><td colspan="4">暂无数据</td></tr>';
  			
  			$('.table-tbody tbody').html(html);
  			
  			if(jsonData.rowCount>jsonData.pageSize){
				$(".tikupaper").pagination(jsonData.rowCount, {
			    	 next_text : "下一页",
			    	 prev_text : "上一页",
			    	 current_page : jsonData.pageNo-1,
			    	 link_to : "javascript:void(0)",
			    	 num_display_entries : 8,
			    	 items_per_page : jsonData.pageSize,
			    	 num_edge_entries : 1,
			    	 callback:function(page,jq){
				    	 var pageNo = page + 1; 
				    	 _this.findTikuPaper(pageNo);
			    	 }
			   });
			}else{
				$(".tikuPaper").html('');
			}
  		});
  	};
  	/**
  	 * 留作业/修改作业
  	 */
  	Model.insertOrUpdate = function(_homework){
  		_homework = _homework || {};
  		var txt='<div class="alertDialog">'+
		          '<h3 class="diaLogTil clear">'+
			        '<div class="fl">'+
			            '作业形式 :'+
			        '</div>'+
			        '<div class="fl rightCon homeworkType">'+
			            '<span>'+
			                '<input type="radio" name="test" id="test" '+ ((_homework.type == 1 )? ('checked="checked" data-paper-id="'+(_homework.paperId?_homework.paperId:'')+'"') : '')+' '+( _homework.type == 2?('disabled=disabled'):'')  +'>'+
			                '<label for="test">试卷</label>'+
			            '</span>'+
			            '<span>'+
			                '<input type="radio" name="test" id="Enclosure" '+ (_homework.type == 2  || !_homework.type ? ('checked="checked" data-resource-id="'+(_homework.resourceId?_homework.resourceId:'')+'"') : '')+'  '+ (_homework.type == 1?('disabled=disabled'):'')  +' >'+
			                '<label for="Enclosure">附件</label>'+
			            '</span>'+
			        '</div>'+
			    '</h3>'+
			    '<div class="diaLogTil clear mTop20">'+
			        '<div class="fl">'+
			            '作业描述 :'+
			        '</div>'+
			        '<div class="fl rightCon">'+
			            '<textarea name="" class="userTextCon">'+ (_homework.desciption ? _homework.desciption : '') +'</textarea>'+
			        '</div>'+
			    '</div>'+
			    
			    '<div class="diaLogTil clear mTop20 fujian '+ (_homework.type == 1 ? 'hide' : '') +'" id="up-body">'+
			        '<div class="fl workEnc">'+
			            '作业附件 :'+
			        '</div>'+
			        '<div class="fl rightCon uploadWork">'+
			            '<p class="show">'+
			                '<span>'+
			                    '<i class="iconfont iYun">&#xe6e1;</i>'+
			                    '<a href="javascript:;" class="fileBut">'+
			                        '上传附件'+
			                        '<input type="file" id="doctype" style="border:none;" name="doc"  onchange="javascript:docChange();">'+
			                    '</a>'+
			                '</span>'+
			                '<span class="colorRed" id="dochint"></span>'+
//			                '<span class="colorGre hide">上传完成</span>'+
			            '</p>'+
			        '</div>'+
			    '</div>'+
				'<div class="diaLogTil clear mTop20 fujian fujianmc '+ (_homework.type == 1 ? 'hide' : '') +'">'+
			        '<div class="fl workEnc">'+
			            '附件名称 :'+
			        '</div>'+
			        (_homework.type == 2&&_homework.fileName ? '<div class="fl rightCon"><span class="EncName">'+ _homework.fileName +'</span><span class="EncClose">删除</span></div>' : '')+
			    '</div>'+
			    
				'<div class="diaLogTil clear mTop20 shijuan '+ ((_homework.type == 2   || !_homework.type) ? 'hide' : '') +'">'+
			        '<div class="fl workEnc">'+
			            '选择作业 :'+
			        '</div>'+
			        '<div class="fl rightCon uploadWork">'+
			            '<button class="TestName">选择试卷</button>'+
			        '</div>'+
			    '</div>'+
			    '<div class="diaLogTil clear mTop20 shijuan shijuanmc '+ ((_homework.type == 2  || !_homework.type)? 'hide' : '') +'">'+
			        '<div class="fl workEnc">'+
			            '试卷名称 :'+
			        '</div>'+
			        (_homework.type == 1&&_homework.paperName ? '<div class="fl rightCon"><span class="EncName">'+ _homework.paperName +'</span><span class="EncClose">删除</span></div>' : '')+
			    '</div>'+
			'</div>',
			_this = this;
		$.confirm({title:'课后作业',text:txt,fadeOutBefor:'no',callback:function(b){
			if(b){
				var homeworkTypeInput = $('input[name="test"]:checked'),
					homework = _homework,
					paperId,
					resourceId,
					desciption;
				
				if(homeworkTypeInput[0].id == 'test'){
					paperId = homeworkTypeInput.data('paperId');
					if(!paperId){
						$.msg('请选择试卷');
						return;
					}
					homework.paperId = paperId;
					homework.type = 1;
				}else{
					resourceId = homeworkTypeInput.data('resourceId')?homeworkTypeInput.data('resourceId'):null;
//					if(!resourceId){
//						$.msg('请上传附件');
//						return;
//					}
					homework.resourceId = resourceId;
					homework.type = 2;
				}
				homework.desciption = $('.userTextCon').val();
				if(!homework.desciption){
					$.msg('作业描述不能为空');
					return;
				}
				if(homework.desciption.length>2000){
					$.msg('作业描述不能超过两千字');
					return;
				}
				for(var i in homework)
					if(!homework[i]) delete homework[i];
				
				if($('.Confirm_Ok').hasClass('lock')) return;
				$('.Confirm_Ok').addClass('lock');
				
				Model.ajax('/homework/insertOrUpdate',homework,function(result){
					if(result && result.flag){
						if(homework.id){
							$.msg('修改成功');
						}else{
							$.msg('保存成功');
							result.hw.homeworkStatus = 1;//更改按钮
							if(result.hw.lessonId){
								$('.lesson_' + homework.lessonId).find('.stuBtn').html(Model.homeworkStatusBtns(result.hw));
								$('.lesson_' + homework.lessonId + ' tr').find('p').removeClass('none');
								$('.lesson_' + homework.lessonId).data('id',result.hw.id);
							}else{
								$('.lecture_' + homework.lectureId).find('.stuBtn').html(Model.homeworkStatusBtns(result.hw));
								$('.lecture_' + homework.lectureId + ' tr').find('p').removeClass('none');
								$('.lecture_' + homework.lectureId).data('id',result.hw.id);
							}
							
						}
						$('.Confirm_Cancle').trigger('click');
					}else{
						$.msg('保存失败');
						$('.Confirm_Ok').removeClass('lock');
					}
				});
			}
		}});
		//犀牛上传初始化需要等元素加载成功后才行
		setTimeout(_this.docChange,500);
  	}

  	Model.docChange = function () {};
  	Model.guid = function() {
  	    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
  	        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
  	        return v.toString(16);
  	    });
  	}
	
	/**
	 * 页面加载完成后执行
	 */
	$(function(){
		Model.init();
		
	});

	function docChange(){
		$.ajaxFileUpload({
			url:rootPath + "/classTypeResource/docupload2;"+ window["sessionName"] + "=" + window["sessionId"],
			type:"post",
			secureuri:false,
			fileElementId:"doctype",
			dataType: "json",
			success:function(data){
				if (data.msg == 'success') {
					$("#dochint").removeClass('colorRed').addClass('colorGre').html("上传完成");
					if(!$('.fujian .EncName').length){
						$('.fujianmc').append('<div class="fl rightCon"><span class="EncName">'+ name +'</span><span class="EncClose">删除</span></div>')
					}else{
						$('.fujian .EncName').html(name);
					}
					//如果是连续上传，则删除上一个的资源
					if($('#Enclosure').data('resourceId')){
						Model.ajax('/classTypeResource/delreslist',{id:$('#Enclosure').data('resourceId')},function(result){
							if(result.msg != 'success'){
								console.log(result.msg || '删除失败');
							}
						});
					}
					$('#Enclosure').data('resourceId',data.fileId);
				} else {
					$("#dochint").html("<span style='color:red;'>" + data.msg + "</span>");
				}
			}
		});
	}
})(jQuery);

