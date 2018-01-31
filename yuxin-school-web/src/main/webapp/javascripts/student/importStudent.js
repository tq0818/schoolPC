(function($){

	var student={
		init: function(){
			var $this=this;
			$selectSubMenu('student_manage');
			$("#chossewenjian").on('click',function(){
				$("#imgData").trigger('click');
			});
			//校验数据
			$(".validateData").on('click',function(){
				var name=$("#imgData").val();
				if(name==""){
					$.msg("请选择文件");
					return;
				}
				$(".loading.check").show();
	            $(".loading-bg").show();
				$.ajaxFileUpload({
					url : rootPath+"/manageStudent/validateData",//student/validateData
					secureuri : false,// 安全协议
					async : false,
					fileElementId : 'imgData',
					type : "POST",
					//dataType : "json",
//					beforeSend:function(XMLHttpRequest){
//			            $(".loading").show();
//			            $(".loading-bg").show();
//			        },
					success : function(jsonData) {
						jsonData=JSON.parse(jsonData);
						var html='<span style="font-size:14px;">校验结果:</span><br/>';
						if(jsonData.teletmperror){
							html+='<span style="color:red;font-size:14px;">'+jsonData.teletmperror+'</span>';
							$(".insertData").addClass("none");
							$(".chooseFile").removeClass("none");
						}else{
							if(jsonData.error.length>0){
								$.each(jsonData.error,function(i,data){
									html+='<span style="font-size:14px;">'+(i+1)+"、"+data+'</span><br/>';
								});
								$(".chooseFile").removeClass("none");
								$(".newinsert").removeClass("none");
								$(".allupdate").removeClass("none");
								$(".chooseInsert").addClass("none");
							}else{
								html+='<span style="color:red;font-size:14px;">无错误信息!</span>';
								$(".chooseFile").addClass("none");
								$(".newinsert").addClass("none");
								$(".allupdate").addClass("none");
								$(".chooseInsert").removeClass("none");
							}
							//$(".insertData").removeClass("none");
							$("#markUrl").val(jsonData.exclePath);
						}
						$("#returnInfo").html(html);
						var mobilelists="";
						if(jsonData.stusMobile && jsonData.stusMobile.length>0){
							$.each(jsonData.stusMobile,function(i,item){
								mobilelists+="'"+item+"'"+",";
							});
						}
						$("#stuMobiles").val(mobilelists);
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        },
					error : function(resp,msg,err){
						console.log(resp);
					}
				});
				//$("#fileNames").val("");
			})
			//导入学员信息
			$(".insertData").on('click',function(){
				var $this=$(this);
				if($this.hasClass("btn-default")){
					return;
				}
				$this.removeClass("btn-primary").addClass("btn-default");
				var n=$("#markUrl").val();
				var mark=$(this).attr("mark");
				if(n==""){
					$.msg("请选择文件并校验数据");
					$this.addClass("btn-primary").removeClass("btn-default");
					return;
				}
				$.ajax({
					url : rootPath+"/student/insertStudents",
					data: {"filePath":$("#markUrl").val(),"mark":mark,"groupOneId":$("#studentG1").val(),"groupTwoId":$("#studentG2").val()},
					type: 'post',
					dataType :'json',
					beforeSend:function(){
						$(".loading.insert").show();
			            $(".loading-bg").show();
					},
					success: function(jsonData){
						$("#markUrl").val("");
						if(jsonData=="error"){
							$.msg("导入信息有误！");
							$this.addClass("btn-primary").removeClass("btn-default");
							$("#returnInfo").html("");
							$(".newinsert").addClass("none");
							$(".allupdate").addClass("none");
							return;
						}
						//成功后删除临时文件夹
						$.msg("导入信息完毕",function(){
							$.ajax({
								url : rootPath+"/student/deleteFile",
								type: 'post',
								success:function(data){
									$("#DataForm")[0].submit();
									$this.addClass("btn-primary").removeClass("btn-default");
								}
							});
						});
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			})
			//重新导入信息
			$(".chooseFile").on('click',function(){
				$("#imgData").trigger('click');
			})
			//返回学员列表
			$(".backreturn").on('click',function(){
				/*window.location.href=rootPath+"/query/orgstatistics/studentList";*/
                window.history.go(-1);
			})
		},
		search: function(page){
			var $this=this;
			var data={};
			data.page=page?page:1;
		
			$.each(data,function(key,value){
				if(!value){
					delete data[key];
				}
			})
			$(".user-list").find("table").find("tr:gt(0)").remove();
			$.ajax({
				url : rootPath+"/student/queryStudentsList",
				data: data,
				success: function(jsonData){
					if(jsonData.data.length==0){
						$(".user-list").find("table").append('<tr><td colspan="9">没有查找到数据</td></tr>');
					}
					$.each(jsonData.data,function(i,stu){
						$(".user-list").find("table").append('<tr>'+
								'<td>'+(stu.mobile?stu.mobile:"")+'</td>'+
								'<td>'+(stu.name?stu.name:"")+'</td>'+
								'<td>'+(stu.identityId?stu.identityId:"")+'</td>'+
								'<td>'+(stu.createTime?stu.createTime:"")+'</td>'+
								'<td>'+(stu.registTime?stu.registTime:"")+'</td>'+
								'<td>'+(stu.status==1?'已注册':'未注册')+'</td>'+
								'<td>'+(stu.registType==1?'前台注册':'后台录入')+'</td>'+
								'<td>'+(stu.paymaterCount>0?'已报名':'未报名')+'</td>'+
								'<td><a href="javascript:void(0);">报名</a>|<a href="javascript:void(0);">详情</a>|<a href="javascript:void(0);">更多</a></td>'+
								'</tr>');
					});
					if(jsonData.rowCount>10){
						$(".pagination").pagination(jsonData.rowCount, {
					    	 next_text : "下一页",
					    	 prev_text : "上一页",
					    	 current_page : jsonData.pageNo-1,
					    	 link_to : "javascript:void(0)",
					    	 num_display_entries : 8,
					    	 items_per_page : jsonData.pageSize,
					    	 num_edge_entries : 1,
					    	 callback:function(page,jq){
						    	 var pageNo = page + 1;
						    	 $this.search(pageNo);
					    	 }
					   });
					}else{
						$(".pagination").html('');
					}
				}
			});
		}
	}
	
	$(document).ready(function(){
		student.init();
		selectGroup1('');
	})
})(jQuery)
