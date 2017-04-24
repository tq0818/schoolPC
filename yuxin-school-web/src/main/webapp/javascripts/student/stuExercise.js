(function($){
	var pageRecord = {
		queryTiKuExprise:function(page,searchCondition){
			var dataInfo = {stuId:$("#stuId").val(),classTypeId:$("#classTypeId").val()};
			//默认条件
			dataInfo.page = page?page:1;
			//搜索条件
			for(var i in searchCondition){
				dataInfo[i] = searchCondition[i];
			}
			$.ajax({
				url:rootPath+"/student_detail/studentTiKuRecord",
				type:"post",
				data:dataInfo,
				beforeSend:function(XMLHttpRequest){
					$(".loading").show();
			        $(".loading-bg").show();
		         },
				success:function(jsonData){
					assign.assTiKu("studentTiKuRecordTable",jsonData.data);
					if(jsonData.rowCount>jsonData.pageSize){
							$("#studentTiKuPage").pagination(jsonData.rowCount, {
						    	 next_text : "下一页",
						    	 prev_text : "上一页",
						    	 current_page : jsonData.pageNo-1,
						    	 link_to : "javascript:void(0)",
						    	 num_display_entries : 8,
						    	 items_per_page : jsonData.pageSize,
						    	 num_edge_entries : 1,
						    	 callback:function(page,jq){
							    	 var pageNo = page + 1;
							    	 pageRecord.queryTiKuExprise(pageNo,searchCondition);
						    	 }
						   });
					}else{
						$("#studentTiKuPage").html('');
					}
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
			        $(".loading-bg").hide();
		        }
			});
		},
		queryTikuList:function(tikuOrSubjectId,nodeId,type){
			$.ajax({
				url:rootPath+"/student_detail/studentTiKuList",
				type:"post",
				data:{id:tikuOrSubjectId,type:type},
				success:function(jsonData){
					assign.assTiKuOrSubject(nodeId,jsonData,type);
				}
			});
		},
		queryStudentLecOrLesStudyDetail:function(page,dataInfo){//直播和视频课程的学习详情
			dataInfo.page = page;
			$.ajax({
				url:rootPath+"/student_detail/queryStudentLecOrLesStudyDetail",
				type:"post",
				data:dataInfo,
				success:function(jsonData){
					assign.LecOrLesStudyDetail("lecOrlessTable",jsonData.data,dataInfo.type);
					if(jsonData.rowCount>jsonData.pageSize){
						$("#lecOrlessPage").pagination(jsonData.rowCount, {
					    	 next_text : "下一页",
					    	 prev_text : "上一页",
					    	 current_page : jsonData.pageNo-1,
					    	 link_to : "javascript:void(0)",
					    	 num_display_entries : 8,
					    	 items_per_page : jsonData.pageSize,
					    	 num_edge_entries : 1,
					    	 callback:function(page,jq){
						    	 var pageNo = page + 1; 
						    	 pageRecord.queryStudentLecOrLesStudyDetail(pageNo,dataInfo);
					    	 }
					   });
					}else{
						$("#lecOrlessPage").html('');
					}
				}
			});
		}
	},assign = {};
	
	/**
	 * 做题记录赋值
	 */
	assign.assTiKu = function(nodeId,arr){
		var html = '<tr class="table-title">'+
			            '<td style="width: 20%">做题时间</td>'+
			            '<td style="width: 14%">题库</td>'+
			            '<td style="width: 9.7%">科目</td>'+
			            '<td style="width: 16.2%">类型</td>'+
			            '<td style="width: 10%">做题数</td>'+
			            '<td style="width: 10%">得分</td>'+
			            '<td style="width: 10%">正确率</td>'+
			            '<td style="width: 18%">错题数</td>'+
			        '</tr>';
		if(arr && arr.length>0){
			var userId = $('#stu_userId').val();
			$.each(arr,function(i,obj){
				obj.rigCount = obj.rigCount?obj.rigCount:0;
				obj.errorCount = obj.errorCount?obj.errorCount:0;
				obj.totalTopic = obj.totalTopic?obj.totalTopic:0;//试题的总数
				obj.finish = parseInt(obj.rigCount) + parseInt(obj.errorCount);		//已经做了多少题
				obj.exerciseScore = obj.exerciseScore?obj.exerciseScore:0;//得分
				html += '<tr>'+
		                    '<td style="width: 20%">'+obj.startTime+'</td>'+
		                    '<td style="width: 14%">'+obj.tiKuName+'</td>'+
		                    '<td style="width: 9.7%">'+obj.subjectName+'</td>'+
		                    '<td style="width: 16.2%">'+obj.exerciseType+'</td>'+
		                    '<td style="width: 10%">'+obj.userDo+'/'+obj.totalTopic+'题</td>'+
		                    '<td style="width: 10%">'+obj.exerciseScore+'</td>'+
		                    '<td style="width: 10%">'+(obj.finish?(obj.rigCount/obj.finish*100).toFixed(2):obj.finish.toFixed(2))+'%</td>'+
		                    '<td style="width: 18%" class="lest-td"><span><em class="iconfont ri">&#xe660;</em><i>'+obj.rigCount+'</i></span><span><em class="iconfont wr">&#xe68c;</em><i>'+obj.errorCount+'</i></span><span class="see-subject"><a href="'+rootPath+'/resolve/showResolve/'+obj.exerciseId+'/'+userId+'" target="_blank">查看</a></span></td>'+
		                '</tr>';
			});
		}else{
			html += '<tr>'+
			    		'<td colspan="8">暂时没有数据</td>'+
			        '</tr>';
		}
		$("#"+nodeId).html(html);
	};
	/**
	 * 联动下拉赋值
	 */
	assign.assTiKuOrSubject = function(nodeId,arr,type){
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
	
	/**
	 * 获取拼接查询条件
	 */
	assign.getCondition = function(){
		var dataInfo = {};
		dataInfo.stuId = $("#stuId").val();
		dataInfo.classTypeId = $("#classTypeId").val();
		//搜索条件
		dataInfo.tiKuName =$("#stu_tiKu").find("option:selected").val() && $("#stu_tiKu").find("option:selected").val()!='0'?$("#stu_tiKu").find("option:selected").val():"";
		dataInfo.subjectName =$("#stu_subject").find("option:selected").val() && $("#stu_subject").find("option:selected").val() != '0'?$("#stu_subject").find("option:selected").val():"";
		dataInfo.exerciseType = $("#stu_exerciseType").find("option:selected").val() && $("#stu_exerciseType").find("option:selected").val() != '0'?$("#stu_exerciseType").find("option:selected").val():"";
		dataInfo.startTime = $("#stu_startTime").val() && $("#stu_startTime").val() != "做题时间"?$("#stu_startTime").val():"";
		return dataInfo;
	};
	
	
	$(function(){
		/**
		 * 日期插件
		 */
		$(".date-picker").datetimepicker({
				format: "yyyy-mm-dd",
				minView:2,
				autoclose: true,
				language: "zh-CN",
				update:new Date()
		});
		$selectSubMenu('student_manage');
		pageRecord.queryTiKuExprise(0,assign.getCondition());//查看所有的做题记录
		$(".date-picker").datetimepicker("update",new Date());
		pageRecord.queryTikuList("","stu_tiKu",0);		//查询公司下所有的题库
		$("#stu_tiKu").change(function(){			//事件绑定
			pageRecord.queryTikuList($(this).val(),"stu_subject",1);
		});
		$("#search").bind('click',function(){
			pageRecord.queryTiKuExprise(0,assign.getCondition());
		});
		
		function changeDate(){
			$("#stu_startTime").val("做题时间");
			$("#stu_startTime").on('click',function(){
				$(".date-picker").datetimepicker("update",new Date());
			});
		}
		
		$("#leanRecordTable").on("click",".total-pop",function(){
			var dataInfo = $(this).data(),value=$(this).html().trim();
			value = value.split('次');
			if(value[0]!='0'&&value[0]){
				dataInfo.classTypeId = $("#classTypeId").val();
				dataInfo.stuId = $("#stuId").val();
				pageRecord.queryStudentLecOrLesStudyDetail(0,dataInfo);
				$(".pop-fixed").fadeIn(200);
			}
		});
		$(".out_list").on('click',function(){
			if ($("#studentTiKuRecordTable").find("tr").eq(1).find("td").length <= 1) {
				$.msg("没有数据可以导出");
			} else {
				$("#exprotForm").attr("action",rootPath+"/student_detail/exportExerciseRecord").submit();
			}
		});
		
		changeDate();
	});
})(jQuery)
