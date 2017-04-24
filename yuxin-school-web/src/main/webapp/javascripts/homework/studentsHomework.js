;(function(win,doc,$){
	/*
	*	课次-学生作业列表
	*/
	var StudentsJob = function(){}
	$.extend( StudentsJob.prototype, {
		//初始化
		_init:function(){
			var self = this;
			$selectSubMenu('home_work');
			self._eventInit();
			
			selectGroup1('_search');
			var param = $.getUrlParam('param');
			if(param){
				param = JSON.parse(param);
				$.each(param,function(k,v){
					$("[name="+k+"]").val(v);
					if(k == 'studentGroup1_search')
						selectGroup2($("[name="+k+"]"),'_search');
				})
			}
			self.queryStudentsList();
		},
		//事件初始化
		_eventInit:function(){
			var self = this;
			//查询搜索
			$(doc).on('click','.studentsjob_search',function(){
				self.queryStudentsList();
			})
			//返回
			.on('click','.studentsjob_goback',function(){
				win.location.href=rootPath + '/homework/toHomeWorkIndex';
			})
			//批阅作业
			.on('click','.studentsjob_read',function(){
				var type = $('#hiddenId').data('type');
				var homeworkId = $('#hiddenId').data('homeworkid');
				var stuId = $(this).data('stuid');
				var hscId = $(this).data('hscid');
				var stuName = $(this).data('stuname');
				var resourceId = $('#hiddenId').data('resourceid');
				var moduleId = $('#hiddenId').data('moduleid');
				
				var param ={};  
				$('#homeworkForm').find('[name]').each(function() {
			        param[$(this).attr('name')] = $(this).val();
			    });
				
				if(type && type == 1){
					//试卷作业 
					win.location.href= rootPath + '/homeworkStudentComplete/gotoAttachmentStudentPaperHomeworkPage?studentCompleteId=' + hscId +'&hwId=' + homeworkId + '&urlSuffix='+ resourceId + '/' + moduleId + '/' + homeworkId + '&param=' + JSON.stringify(param);
				}else if(type && type == 2){
					//附件作业
					win.location.href= rootPath + '/homeworkStudentComplete/gotoAttachmentStudentHomeworkPage/'+homeworkId+'/'+stuId+'/'+encodeURI(encodeURI(stuName))+'/'+hscId + '/' + resourceId +  '/' + moduleId + '?param=' + JSON.stringify(param);
				}else{
					$.msg('页面数据异常，请刷新后重试！');
				}
			})
			//查看批阅
			.on('click','.studentsjob_watch',function(){
				var type = $('#hiddenId').data('type');
				var homeworkId = $('#hiddenId').data('homeworkid');
				var stuId = $(this).data('stuid');
				var hscId = $(this).data('hscid') || 0;
				var stuName = $(this).data('stuname');
				var resourceId = $('#hiddenId').data('resourceid');
				var moduleId = $('#hiddenId').data('moduleid');
				
				var param ={};  
				$('#homeworkForm').find('[name]').each(function() {
			        param[$(this).attr('name')] = $(this).val();
			    });
				 
				if(type && type == 1){
					//试卷作业
					win.location.href= rootPath + '/homeworkStudentComplete/gotoAttachmentStudentPaperHomeworkPage?studentCompleteId=' + hscId +'&hwId=' + homeworkId + '&urlSuffix='+ resourceId + '/' + moduleId + '/' + homeworkId + '&param=' + JSON.stringify(param);
				}else if(type && type == 2){
					//附件作业
					win.location.href= rootPath + '/homeworkStudentComplete/gotoAttachmentStudentHomeworkPage/'+homeworkId+'/'+stuId+'/'+encodeURI(encodeURI(stuName))+'/'+hscId + '/' + resourceId +  '/' + moduleId + '?param=' + JSON.stringify(param) +'';
				}else{
					$.msg('页面数据异常，请刷新后重试！');
				}
			});
		},
		//查询列表
		queryStudentsList:function(page,pageSize){
			var self = this;
		    var data = {},statusName = "",button = "";
		    data.page = page || 1;
		    data.pageSize = pageSize || 10;

		    data.resourceId = $('#hiddenId').data('resourceid');
		    data.moduleId = $('#hiddenId').data('moduleid');
		    data.courseId = $('#hiddenId').data('courseid');
		    data.homeworkId = $('#hiddenId').data('homeworkid');
		    data.status = $('#status').val();
		    data.mobile = $('#mobile').val();
		    data.username = $('#username').val();
		    data.name = $('#name').val();
		    data.groupOneId = $("#studentG1_search").val();
		    data.groupTwoId = $("#studentG2_search").val();

		    $(".studentsjobTable").find("table").find("tr:gt(0)").remove();
		    $.ajax({
	            url: rootPath + "/homeworkStudentComplete/getHomeworkStudentCompleteDataList",
	            data: data,
	            type: 'post',
	            beforeSend: function (XMLHttpRequest) {
	                $(".loading").show();
	                $(".loading-bg").show();
	            },
	            success: function (jsonData) {
	               
	                if (jsonData.data.length == 0) {
	                    $(".studentsjobTable").find("table").append('<tr><td colspan="8">没有查找到数据</td></tr>');
	                }
	                $.each(jsonData.data,function (i, stu) {
	                	var button = '',statusName = '';
	                	if(stu.status){
	                		if(stu.status == 0){
	                			statusName = '<span class="colorRed">学员未提交作业</span>';
	                			button = '<span><button class="studentsjob_watch" data-hscid='+stu.hscId+' data-stuid='+stu.id+' data-stuname='+(stu.name?stu.name:(stu.mobile?stu.mobile:(stu.username?stu.username:"“”")))+'>查看</button></span>';
	                		}else if(stu.status == 1){
	                			statusName = '<span class="">学员已提交作业</span>';
	                			button = '<span><button class="studentsjob_read" data-hscid='+stu.hscId+' data-stuid='+stu.id+' data-stuname='+(stu.name?stu.name:(stu.mobile?stu.mobile:(stu.username?stu.username:"“”")))+'>批阅</button></span>';
	                		}else if(stu.status == 2){
	                			statusName = '<span class="colorRed">待重新提交作业</span>';
	                			button = '<span><button class="studentsjob_watch" data-hscid='+stu.hscId+' data-stuid='+stu.id+' data-stuname='+(stu.name?stu.name:(stu.mobile?stu.mobile:(stu.username?stu.username:"“”")))+'>查看</button></span>';
	                		}else if(stu.status == 3){
	                			statusName = '<span class="colorBlu">教师已批阅作业</span>';
	                			button = '<span><button class="studentsjob_watch" data-hscid='+stu.hscId+' data-stuid='+stu.id+' data-stuname='+(stu.name?stu.name:(stu.mobile?stu.mobile:(stu.username?stu.username:"“”")))+'>查看</button></span>';
	                		}
	                	}else{
	                		statusName = '<span class="colorRed">学员未提交作业</span>';
	                		button = '<span><button class="studentsjob_watch" data-hscid='+stu.hscId+' data-stuid='+stu.id+' data-stuname='+(stu.name?stu.name:(stu.mobile?stu.mobile:(stu.username?stu.username:"“”")))+'>查看</button></span>';
	                	}
                        $(".studentsjobTable").find("table").append('<tr>'
                        	+ '<td><span>'+(stu.mobile?stu.mobile:"")+'</span></td>'
                        	+ '<td><span>'+(stu.username?stu.username:"")+'</span></td>'
                        	+ '<td><span>'+(stu.name?stu.name:"")+'</span></td>'
                        	+ '<td>'+statusName+'</td>'
                        	+ '<td><span>'+((stu.status == 1 || stu.status == 3)?(stu.completeTime?stu.completeTime:""):"")+'</span></td>'
                        	+ '<td><span>'+((stu.status == 1 || stu.status == 3)?(stu.readTime?stu.readTime:""):"")+'</span></td>'
                        	+ '<td><span>'+((stu.status == 1 || stu.status == 3)?(stu.score?stu.score:""):"")+'</span></td>'
                        	+ '<td>'+button+'</td>'
                            + '</tr>');
                    });
	            
	                
	                if (jsonData.rowCount > data.pageSize) {
	                    $(".pagination").pagination(jsonData.rowCount,{
                            next_text: "下一页",
                            prev_text: "上一页",
                            current_page: jsonData.pageNo - 1,
                            link_to: "javascript:void(0)",
                            num_display_entries: 8,
                            items_per_page: jsonData.pageSize,
                            num_edge_entries: 1,
                            callback: function (page, jq) {
                                var page = page + 1;
                                self.queryStudentsList(page);
                            }
                        });
	                } else{
	                	$(".pagination").html("");
	                }
	            },
	            complete: function (XMLHttpRequest, textStatus) {
	                $(".loading").hide();
	                $(".loading-bg").hide();
	            }
	        });
		}
	})
	$(function(){
		var studentsjob = new StudentsJob();
		studentsjob._init();
	})
})(window,document,jQuery)
	