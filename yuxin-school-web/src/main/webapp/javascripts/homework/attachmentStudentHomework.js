;(function(win,doc,$){
	/*
	*	批阅附件作业
	*
	*	错误：
	*	01 homeworkId
	*   02 homeworkSId
	*/
	var ReadAttachmentJob = function(){}
	$.extend( ReadAttachmentJob.prototype, {
		//初始化
		_init:function(){
			var self = this;
			$selectSubMenu('home_work');
			self._eventInit();
			self._htmlEvent();
			self.answerShow();
		},
		//事件初始化
		_eventInit:function(){
			var self = this;
			//下载附件
			$(doc).on('click','.readattachmentjob_download',function(){
				  var form = $("<form>");   //定义一个form表单
	              form.attr('style','display:none');   //在form表单中添加查询参数
	              form.attr('target','');
	              form.attr('method','post');
	              form.attr('action',rootPath + "/homeworkTeacherRead/homeworkDdownload");
	           
	              var input1 = $('<input>'); 
	              input1.attr('type','hidden'); 
	              input1.attr('name','ids'); 
	              input1.attr('value',$(this).data("ids")); 
	           
	              $('body').append(form);  //将表单放置在web中
	              form.append(input1);   //将查询参数控件提交到表单上
	              form.submit();   
			})
			//保存
			.on('click','.readattachmentjob_save',function(){
				self.save();
			})
			//返回
			.on('click','.readattachmentjob_goback',function(){
				var resourceId = $('#hiddenId').data('resourceid');
				var moduleId = $('#hiddenId').data('moduleid');
				var homeworkId = $('#hiddenId').data('homeworkid');
				win.location.href=rootPath + '/homeworkStudentComplete/gotoHomeworkStudentCompletePage/'+resourceId+'/'+moduleId+'/'+homeworkId+ '?param='+ $.getUrlParam('param');
			})
			.on('click','.readattachmentjob_delfile',function(){
				var fileId = $("#hiddenId").data('fileid');
				//删除
				$.ajax({
		            url: rootPath + "/homeworkStudentComplete/delreslist",
		            data: {"id":fileId},
		            type: 'post',
		            async: false,
		            success: function (jsonData) {
		            	if(jsonData.msg == "success"){
		            		$.msg('删除成功');
		            		$('.readattachmentjob_delfile').remove();
		            		$('#homework_result').html('');
		            		$('#fileName').html('');
		            		$("#hiddenId").data('fileid','');
		            	}else{
		            		$.msg(jsonData.msg);
		            	}
		            }
				});
			});
		},
		_htmlEvent:function(){
			var self = this;
			var type = $('#hiddenId').data('status');
			if(type && type == 1){
				$('.MarkingWork').show();
				//self._uploadInit();
			}
			var htrResourceId = $('#hiddenId').data('htrresourceid');
			var htrResourceName = $('#hiddenId').data('htrresourcename');
			if(htrResourceId){
			   $('#hiddenId').data('fileid',htrResourceId);
          	   $("#fileName").html(htrResourceName);
          	   $(".homework_resource_name").html('<span class="EncClose readattachmentjob_delfile">删除</span>');
			}
		},
		//学生回答
		answerShow:function(){
			var self = this, data = {};
			
			data.homeworkId = $('#hiddenId').data('homeworkid');
			if(!data.homeworkId){
				$.msg("页面数据异常,请刷新页面（附件作业错误：01）");
			}
			data.stuId = $('#hiddenId').data('stuid');
			
			$.ajax({
	            url: rootPath + "/homeworkStudentComplete/getAttachmentStudentHomeworkList",
	            data: data,
	            type: 'post',
	            beforeSend: function (XMLHttpRequest) {
	                $(".loading").show();
	                $(".loading-bg").show();
	            },
	            success: function (jsonData) {
	            	var stuName = $('#hiddenId').data('stuname'); 
	                $.each(jsonData,function (i, hw) {
	                	var stuHtml='',tHtml='';
	                	stuHtml='	<div class="JobDescript clear studentWork">'+
			        			'		<h3>'+stuName+'同学的作业：</h3>'+
			        			'		<div class="descriptWork">'+(hw.stuContent?hw.stuContent:"")+'</div>'+
			        			'		<p class="consultWork">'+
			        			'			<span>'+
			        			'				<em>作业完成时间:</em>'+
			        			'				<em>'+hw.completeTime+'</em>'+
			        			'			</span>'+
			        			(hw.stuResourceId?'			<button class="readattachmentjob_download" data-ids="'+hw.stuResourceId+'">下载附件</button>':'')+
			        			'		</p>'+
			        			'	</div>';
	                	if(hw.reader){
	                		tHtml ='  <div class="JobDescript clear studentWork"> '+
	                			'  		 <h3>老师已批阅作业：</h3>'+
	                			' 		 <div class="descriptWork">'+(hw.tContent?hw.tContent:"")+'</div>'+
	                			' 		 <p class="consultWork">'+
	                			'  			 <span>'+
	                			' 				 <em>作业批改完成时间:</em>'+
	                			'  			 	 <em>'+hw.readTime+'</em>'+
	                			'  		 	 </span>'+
	                			(hw.tResourceId?'  		 	<button class="readattachmentjob_download"  data-ids="'+hw.tResourceId+'">下载附件</button>':'')+
	                			' 		 </p>'+
	                			'  	  </div>';
	                	}
	                	$('.homework_contents').append(stuHtml+tHtml);
	                });
	            },
	            complete: function (XMLHttpRequest, textStatus) {
	                $(".loading").hide();
	                $(".loading-bg").hide();
	            }
	        });
		},
		save:function(){
			var data = {},htrId = $("#hiddenId").data('htrid');
			var fileId = $("#hiddenId").data('fileid');
			if(fileId){
				data.resourceId = fileId;
			}
			data.content = $('#t_content').val();
			data.score   = $('#t_score').val();
			data.status = $('.stat').find('a').eq(0).hasClass('but-py')?3:2;
			//data.status = $('#cheched').is(':checked')?2:3;			
			data.homeworkId = $('#hiddenId').data('homeworkid');
			data.homeworkSId = $('#hiddenId').data('answerkey');
			data.htrId = htrId;
			
			if(!data.homeworkSId){
				$.msg("页面数据异常，请刷新页面（附件作业错误：02）");
				return false;
			}
			if(!data.content){
				$.msg("作业评语不能为空！");
				return false;
			}
//			if(!data.score){
//				$.msg("分数不能为空！");
//				return false;
//			}
			
			$.ajax({
	            url: rootPath + "/homeworkTeacherRead/saveScore",
	            data:data,
	            type: 'post',
	            async:false,
	            success: function (jsonData) {
	            	if(jsonData.result == "success"){
	            		$.msg("批阅成功");
	            		//跳转
	            		var homeworkId = $('#hiddenId').data('homeworkid');;
	            		var stuId = $('#hiddenId').data('stuid');;
	            		var stuName = $('#hiddenId').data('stuname');;
	            		var hscId = $('#hiddenId').data('answerkey');;
	            		var resourceId = $('#hiddenId').data('resourceid');
	    				var moduleId = $('#hiddenId').data('moduleid');
	            		win.location.href = rootPath + '/homeworkStudentComplete/gotoAttachmentStudentHomeworkPage/'+homeworkId+'/'+stuId+'/'+stuName+'/'+hscId + '/' + resourceId  + '/' + moduleId + '?param='+ $.getUrlParam('param');
	            	}else{
	            		return false;
	            	}
	            }  
			});
		},
		_uploadInit:function(){

		},
		guid:function() {
		    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
		        return v.toString(16);
		    });
		}
	});
	function docChange(){
		$("#homework_result").html('<span class="colorRed">上传中</span>');
		$.ajaxFileUpload({
			url:rootPath + "/classTypeResource/docupload;"+ window["sessionName"] + "=" + window["sessionId"],
			type:"post",
			secureuri:false,
			fileElementId:"doctype",
			dataType: "json",
			success:function(data){
				if (data.msg == 'success') {
					var fileId = data.fileId;
					var htrId = $("#hiddenId").data('htrid');
					$.ajax({
						url : "/homeworkTeacherRead/saveResource",
						type:"post",
						data:{
							'htrId':htrId,
							'resourceId':fileId},
						dataType:"json",
						success:function(data){
						}
					});

					$('#hiddenId').data('fileid',fileId);
					$("#homework_result").html('<span class="colorGre">上传完成</span>');
					$("#fileName").html(name);
					$(".homework_resource_name").html('');
					$(".homework_resource_name").append('<span class="EncClose readattachmentjob_delfile">删除</span>');
				} else {
					$("#homework_result").html('<span class="colorRed">'+data.msg+'</span>');
				}



			}
		});
	}
	$(function(){
		var readAttachmentJob = new ReadAttachmentJob();
		readAttachmentJob._init();
		win.readAttachmentJob = readAttachmentJob;
		$('.st').on('click',function(){
			if($(this).hasClass('but-hf')){
				$(this).removeClass('but-hf').addClass('but-py').siblings().eq(0).removeClass('but-py').addClass('but-hf');
				if($(this).text()=='回复')
					$('.scores').hide();
				else
					$('.scores').show();
			}
		})
	})
})(window,document,jQuery)
	