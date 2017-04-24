(function($){
	var pageRecord = {
			queryLeanRecord:function(page){
				var dataInfo = {commodityId:$("#classTypeId").find("option:selected").val(),commodityType:"COMMODITY_CLASS",stuId:$("#stuId").val(),classTypeId:$("#classTypeId").val(),page:page?page:1};
				$.ajax({
					url:rootPath+"/student_detail/queryClassRecordByClassTypeId",
					type:"post",
					data:dataInfo,
					beforeSend:function(XMLHttpRequest){
						$(".loading").show();
				        $(".loading-bg").show();
			         },
					success:function(jsonData){
						assign.assLeanSamll(jsonData.data,"leanRecordTable");
						if(jsonData.rowCount>jsonData.pageSize){
								$("#studentLeanPage").pagination(jsonData.rowCount, {
							    	 next_text : "下一页",
							    	 prev_text : "上一页",
							    	 current_page : jsonData.pageNo-1,
							    	 link_to : "javascript:void(0)",
							    	 num_display_entries : 8,
							    	 items_per_page : jsonData.pageSize,
							    	 num_edge_entries : 1,
							    	 callback:function(page,jq){
								    	 var pageNo = page + 1;
								    	 pageRecord.queryLeanRecord(pageNo);
							    	 }
							   });
						}else{
							$("#studentLeanPage").html('');
						}
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
				        $(".loading-bg").hide();
			        }
				});
		},
		queryEvent : function(){
			$("#classTypeId").bind('change',function(){
				pageRecord.queryLeanRecord();
			});
			$(".out_list").on('click',function(){
				if ($("#leanRecordTable").find("tr").eq(1).find("td").length <= 1) {
					$.msg("没有数据可以导出");
				} else {
					$("#searchForm").attr("action",rootPath + "/student/exportExcle").submit();
					$("#exprotForm").attr("action",rootPath+"/student_detail/exportStuLeanRecord").submit();
				}
			});
		}
	},assign = {};
	/**
	 * 学习记录赋值
	 */
	assign.assLeanSamll = function(arr,nodeId){
		var html = '<tr class="table-title">'+
						'<td>课程名称</td>'+
			            '<td>课次名称</td>'+
			            '<td>教学形式</td>'+
			            '<td>最后上课时间</td>'+
			            '<td>完成情况</td>'+
			            '<td>最长上课时间</td>'+
			        '</tr>';
		
		if(arr && arr.length>0){
			$.each(arr,function(i,obj){
				var studyTime,status;
				if(obj.type=='lecture'){
					if(!obj.studyTime || obj.studyTime =='0'){//最后学习时间，未学习的课次数据库返回0。学习过的则返回最后学习的时间
						obj.studyTime = "未学习";
					}
					status = obj.studyStatus == 3?"已完成":"未完成";//视频的课次数据库返回0,1,2,3其中只有3为完成状态
				}else{
					if(!obj.studyTime || obj.studyTime =='0'){
						obj.studyTime = "直播或面授未开始";
					}
					status = obj.studyStatus;
				}
				//学习时长的转换，直播以小时为单位，视频以小时为单位
				if(obj.type=='lession'){//直播
					studyTime = assign.getLessionTime(obj.lastTimeLone);
				}else{//视频
					studyTime = assign.getLeanTime(obj.lastTimeLone);
				}
				html += '<tr>'+
							'<td>'+(obj.classTypeName?obj.classTypeName:"")+'</td>'+            
							'<td>'+(obj.lectureName?obj.lectureName:"")+'</td>'+
		                    '<td>'+(obj.teachMethod?obj.teachMethod:"")+'</td>'+
		                    '<td>'+(obj.studyTime?obj.studyTime:"")+'</td>'+
		                    '<td>'+status+'</td>'+
		                    '<td>'+studyTime+'</td>'+
		                '</tr>';
			});
		}else{
			html += '<tr>'+
			    		'<td colspan="6">暂时没有数据</td>'+
			        '</tr>';
		}
		$("#"+nodeId).html(html);
	};
	
	assign.getLeanTime = function(time){//以秒为单位的时间字符串
		var hour,min,ss,str;
		if(time=='0')
			return '0秒';
		if(!time)
			return "";
		time = parseInt(time);
		if(parseInt(time/(60*60))>0){
			hour = parseInt(time/(60*60));
			min = parseInt((time%(60*60))/60) ;
			ss = (time%(60*60))%60;
		}
		if(parseInt(time/(60*60))==0 && parseInt(time/60)>0){
			min = parseInt(time/60);
			ss = time%60;
		}
		if(parseInt(time/(60*60))==0 && parseInt(time/60)==0){
			ss = time;
		}
		str = hour?hour+"小时"+min+"分钟"+ss+"秒":min?min+"分钟"+ss+"秒":ss+"秒";
		return str;
	};
	assign.getLessionTime = function(time){
		var studyTime = "";
		if(!time || time=='0'){
			return "0小时";
		}
		if(time.indexOf(".")!=-1){
			time = time.split(".");
			studyTime = time[0] + "小时" + parseFloat(time[1])*60 + "分钟";
		}else{
			studyTime = time + "小时";
		}
		return studyTime;
	}
	
	
	
	$(function(){
		$selectSubMenu('student_manage');
		pageRecord.queryLeanRecord();
		pageRecord.queryEvent();
	});
})(jQuery)
