(function(win,doc,$){
	function CompetitionScore(){}
	$.extend(CompetitionScore.prototype,{
		//初始化
		_init:function(){
			var self = this;
			self._eventInit();
		},
		//事件初始化
		_eventInit:function(){
			var self = this;
			//1.弹窗
			//导入成绩
			$(doc).on('click','.importScore',function(){
				$(".showImport").show();
				$(".loading-bg").show();
			});
			//添加成绩
			$(doc).on('click','.addScore',function(){
				$(".showAdd").show();
				$(".loading-bg").show();
			});
			//模板下载
			$(doc).on('click','.downloadTemplete',function(){
				var form = $("<form>");  
	            form.attr('style','display:none');   
	            form.attr('target','');
	            form.attr('method','post');
	            form.attr('action',rootPath + "/sysStudentScore/downloadExcleTemplete");
	            $('body').append(form);
	            form.submit();   
			});
			//关闭窗口（支持所有弹窗）
			$(doc).on('click','.hideShow',function(){
				$($(this).data("type")).hide();
				if(".showImport" == $(this).data("type")){
					self.importMsg("init");
				}
				if(".showAdd" == $(this).data("type")){
					self._addFormInit();
				}
				if(".showEdit" == $(this).data("type")){
					self._editFormInit();
				}
				$(".loading-bg").hide();
			});
			//2.导入弹窗 选择文件
			$(doc).on('click','.selectFile',function(){
				$(".realselectFile").trigger('click');
			});
			$(doc).on('change','.realselectFile',function(){
				$(".scoreFile").val($(this).val()).attr("title",$(this).val());
				self.importMsg("init");
			});
			//导入弹窗 开始导入
			$(doc).on('click','.importStart',function(){
				var name=$("#scoreFile").val();
				if(name==""){
					$.msg("请选择文件");
					return false;
				}
				self.importScore();
				$(".scoreFile").val("").attr("title","");
				$(".loading.import").show();
	            $(".loading-bg").show();
			});
			//导入弹窗 导入完成	
			$(doc).on('click','.importFinished',function(){
				win.location.reload();
			});
			//3.添加窗口 添加
			$(doc).on('click','.addOKScore',function(){
				self.addScore();
			});
			//4.TABLE 修改
			$(doc).on('click','.editOne',function(){
				var id = $(this).data("id")
				if(!id){
					$.msg("页面数据异常,请刷新后再试！");
					return false;
				}
				self.editQueryScore(id);
			})
			//TABLE 删除
			$(doc).on('click','.delOne',function(){
				var id = $(this).data("id")
				if(!id){
					$.msg("页面数据异常,请刷新后再试！");
					return false;
				}
				$.confirm("确认要删除么?",function(e){
					if(e){
						self.delScore(id);
					}
				})
			})
			//5.修改窗口 修改
			$(doc).on('click','.editOKScore',function(){
				var id = $(this).data("id")
				if(!id){
					$.msg("页面数据异常,请刷新后再试！");
					return false;
				}
				self.editScore(id);
			});
		
			
		},
		//添加表单初始化
		_addFormInit:function(){
			$.each($(".div_add").find("input"),function(){
				$(this).val("");
			});
		},
		//修改表单初始化
		_editFormInit:function(){
			$.each($(".div_edit").find("input"),function(){
				$(this).val("");
			});
		},
		//导入成绩
		importScore:function(){
			var self = this;
			$(".importStatus").html('<front style="color:green;">' + "导入中" + '</front>');
			$.ajaxFileUpload({
				url : rootPath+"/sysStudentScore/importCompetitionScore",
				secureuri : false,
				async : false,
				fileElementId : 'scoreFile',
				type : "POST",
				dataType : "json",
				success:function(jsonData){
					if(jsonData.result == "success"){
						self.importMsg(true,"导入成功");
						self.search();
					}else if(jsonData.result == "error"){
						self.importMsg(false,"导入失败",jsonData.reason);
					}
				},
				complete:function(XMLHttpRequest,textStatus){
					//结束后删除临时文件夹 引用学员导入接口
					$.ajax({
						url : rootPath+"/student/deleteFile",
						type: 'post',
						success:function(data){
						}
					});
					$(".loading.import").hide();
		            $(".loading-bg").hide();
		        },
				error:function(){
				}
			});
		},
		//导入状态信息
		importMsg:function(status,result,reason){
			if("init" == status){
				$(".importStatus").html('<front style="">' + "待导入" + '</front>');
				$(".importStart").show();
				$(".importFinished").hide();
				$(".errorReason").hide();
				return false;
			}
			if(status){
				$(".importStatus").html('<front style="color:#00b7ee;">' + result + '</front>');
				$(".importStart").hide();
				$(".importFinished").show();
				
			}else{
				$(".importStatus").html('<front style="color:#fe5151;">' + result + '</front>');
				$(".importStart").show();
				$(".importFinished").hide();
				
			}
			if(reason){
				$(".importReason").html(reason.replace(/\&lt;/g,"<").replace(/\&gt;/g,">"));
				$(".errorReason").show();
			}else{
				$(".errorReason").hide();
			}
		},
		//查询
		search:function(page){
			var self = this;
			var data = {};
			data.page = page ? page : 1;
			data.pageSize = $("#selectCounts").val()||10;
			$.ajax({
                url: rootPath + "/sysStudentScore/queryCompetitionScore",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                    $(".loading.insert").show();
                    $(".loading-bg").show();
                },
                success:function(jsonData){
                	$(".tables").find("table").find("tr:gt(0)").remove();
                	if (jsonData.data.length == 0) {
                        $(".tables").find("table").append( '<tr><td colspan="8">没有查找到数据</td></tr>');
                    }
                    $.each(jsonData.data,function (i, sss) {
                    	html = '<tr>'+
						'<td style="line-height:22px;">'+(sss.name?sss.name:"")+'</td>'+
						'<td style="line-height:22px;">'+(sss.code?sss.code:"")+'</td>'+
						'<td style="line-height:22px;">'+(sss.idCode?sss.idCode:"")+'</td>'+
						'<td style="line-height:22px;">'+(sss.grade?sss.grade:"")+'</td>'+
						'<td style="line-height:22px;">'+(sss.score?sss.score:"")+'</td>'+
						'<td style="line-height:22px;">'+(sss.awards?sss.awards:"")+'</td>'+
						'<td style="line-height:22px;"><a style="text-decoration:none;" href="'+(sss.link?sss.link:"")+' " target="_blank">'+(sss.link?sss.link:"")+'</a></td>'+
						'<td style="line-height:22px;"><a style="text-decoration:none;" data-id="'+sss.id+'" class="btn btn-mini btn-default edit achie btn-achieu delOne">删除</a> <input type="hidden" value="VIDEO_PROCESS_NOMAL"><a style="text-decoration:none;" data-id="'+sss.id+'" class="btn btn-mini btn-default deleteBtn btn-achieb achie editOne">修改</a></td>'+
						'</tr>';
                    	$(".tables").find("table").append(html);
                    });
                	if (jsonData.rowCount >$("#selectCounts").val()) {
                         $(".pagination").pagination(jsonData.rowCount, {
	                        	 next_text: "下一页",
	                             prev_text: "上一页",
	                             current_page: jsonData.pageNo - 1,
	                             link_to: "javascript:void(0)",
	                             num_display_entries: 8,
	                             items_per_page: jsonData.pageSize,
	                             num_edge_entries: 1,
	                             callback: function (page) {
                                     var pageNo = page + 1;
                                     self.search(pageNo);
                                 }
                          });
                         $(".pagination").find("li:first").before('每页：<select id="selectCount"  onchange="javascript:competitionScore.searchCount()">'+
             					' <option value="10">10</option>'+
             					' <option value="20">20</option>'+
             					' <option value="30">30</option>'+
             					' <option value="50">50</option>'+
             					' <option value="100">100</option>'+
             					' </select> 条   ');
                         $("#selectCount").val($("#selectCounts").val());
                	 }else{
                		 $(".pagination").html('');
                	 }
                },
            	complete:function(XMLHttpRequest,textStatus){
					$(".loading.insert").hide();
		            $(".loading-bg").hide();
		        },
                error:function(){
                }
			});
		},
		//添加成绩
		addScore:function(){
			var self = this;
			var data = {};
			data.name = $("#add_name").val();
			data.code = $("#add_code").val();
			data.idCode = $("#add_idCode").val();
			data.grade = $("#add_grade").val();
			data.score = $("#add_score").val();
			data.awards = $("#add_awards").val();
			data.link = $("#add_link").val();
			if(!data.name){
				$.msg("姓名不能为空");
				return false;
			}
			if(!data.code){
				$.msg("考号不能为空");
				return false;
			}
			if(!data.idCode){
				$.msg("证件不能为空");
				return false;
			}
			if(!data.grade){
				$.msg("年级组不能为空");
				return false;
			}
			if(!data.score){
				$.msg("分数不能为空");
				return false;
			}
			$.ajax({
                url: rootPath + "/sysStudentScore/addCompetitionScore",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                },
                success:function(jsonData){
                	if(jsonData.result == "success"){
                		$.msg("添加成功");
                		self.search();
                		$(".showAdd .hideShow").click();
                	}else if(jsonData.result == "error"){
                		$.msg(jsonData.reason);
                	}
                },
                complete:function(XMLHttpRequest,textStatus){
		        },
                error:function(){
                }
			});
		},
		//修改查询
		editQueryScore:function(id){
			var data = {};
			data.id = id;
			$.ajax({
                url: rootPath + "/sysStudentScore/editToQueryCompetitionScore",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                },
                success:function(jsonData){
                	if(jsonData.result == "success"){
                		$("#edit_name").val(jsonData.sysStudentScore.name);
                		$("#edit_code").val(jsonData.sysStudentScore.code);
                		$("#edit_idCode").val(jsonData.sysStudentScore.idCode);
                		$("#edit_grade").val(jsonData.sysStudentScore.grade);
                		$("#edit_score").val(jsonData.sysStudentScore.score);
                		$("#edit_awards").val(jsonData.sysStudentScore.awards);
                		$("#edit_link").val(jsonData.sysStudentScore.link);
                		$(".editOKScore").data("id",jsonData.sysStudentScore.id);
                		$(".showEdit").show();
        				$(".loading-bg").show();
                	}else if(jsonData.result == "error"){
                		$.msg(jsonData.reason);
                	}
                },
                complete:function(XMLHttpRequest,textStatus){
		        },
                error:function(){
                }
			});
		},
		//修改成绩
		editScore:function(id){
			var self = this;
			var data = {};
			data.id = id;
			data.name = $("#edit_name").val();
			data.code = $("#edit_code").val();
			data.idCode = $("#edit_idCode").val();
			data.grade = $("#edit_grade").val();
			data.score = $("#edit_score").val();
			data.awards = $("#edit_awards").val();
			data.link = $("#edit_link").val();
			if(!data.name){
				$.msg("姓名不能为空");
				return false;
			}
			if(!data.code){
				$.msg("考号不能为空");
				return false;
			}
			if(!data.idCode){
				$.msg("证件不能为空");
				return false;
			}
			if(!data.grade){
				$.msg("年级组不能为空");
				return false;
			}
			if(!data.score){
				$.msg("分数不能为空");
				return false;
			}
			$.ajax({
                url: rootPath + "/sysStudentScore/editCompetitionScore",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                },
                success:function(jsonData){
                	if(jsonData.result == "success"){
                		$.msg("修改成功");
                		self.search();
                		$(".showEdit .hideShow").click();
                	}else if(jsonData.result == "error"){
                		$.msg(jsonData.reason);
                	}
                },
                complete:function(XMLHttpRequest,textStatus){
		        },
                error:function(){
                }
			});
		},
		//删除成绩
		delScore:function(id){
			var self = this;
			var data = {};
			data.id = id;
			$.ajax({
                url: rootPath + "/sysStudentScore/delCompetitionScore",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                },
                success:function(jsonData){
                	if(jsonData.result == "success"){
                		$.msg("删除成功");
                		self.search();
                	}else if(jsonData.result == "error"){
                		$.msg(jsonData.reason);
                	}
                },
                complete:function(XMLHttpRequest,textStatus){
		        },
                error:function(){
                }
			});
		},
		searchCount:function(){
			var self = this;
			$("#selectCounts").val($("#selectCount").val());
			self.search();
		}
	});
	$(function(){
		var competitionScore = new CompetitionScore();
		win.competitionScore = competitionScore;
		competitionScore._init();
		competitionScore.search();
		$("body").find(".competitionScore").addClass("active");
	})
})(window,document,jQuery)
	