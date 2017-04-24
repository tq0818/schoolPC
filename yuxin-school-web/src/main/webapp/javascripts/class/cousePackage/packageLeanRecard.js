(function($,rootPath){
	var stuLeanCpDetail = {
			ajaxLoad : function(url,dataInfo,func){
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
			},
			ajax : function(url,dataInfo,func){
				 $.ajax({ 
			 		  type: "post", 
			 		  url : rootPath+url, 
			 		  data: dataInfo,
			 		  success: func,
			 	  });
			},
			cpIdAndStuId:{classPackageId:$("#classPackageId").val(),stuId:$("#stuId").val(),commodityId:$("#classPackageId").val(),commodityType:"COMMODITY_PACKAGE"}
	},assign = {};
	
	//学习记录
	stuLeanCpDetail.queryStudentCpLeanRecord = function(page){
		var $this = this,data;
		data = $this.cpIdAndStuId;
		data.page = page?page:1;
		$this.ajaxLoad("/stuLeanRecord/queryStudentCpLeanRecord",data,function(jsonData){
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
				    	 $this.queryStudentCpLeanRecord(pageNo);
			    	 }
			   });
			}else{
				$("#studentLeanPage").html('');
			}
		});
	}
	//课次的学习详情
	stuLeanCpDetail.queryStudentLecOrLesStudyDetail = function(page,dataInfo){
		var $this = this;
		dataInfo.page = page;
		$this.ajaxLoad("/stuLeanRecord/queryStudentLecOrLesStudyDetail",dataInfo,function(jsonData){
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
				    	 stuLeanCpDetail.queryStudentLecOrLesStudyDetail(pageNo,dataInfo);
			    	 }
			   });
			}else{
				$("#lecOrlessPage").html('');
			}
		});
	}
	//做题记录
	stuLeanCpDetail.studentTiKuRecord = function(page,searchCondition){
		var $this = this,data;
		data = $this.cpIdAndStuId;
		data.page = page?page:1;
		for(var i in searchCondition){
			data[i] = searchCondition[i];
		}
		$this.ajaxLoad("/stuLeanRecord/studentTiKuRecord",data,function(jsonData){
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
					    	 $this.studentTiKuList(pageNo,searchCondition);
				    	 }
				   });
			}else{
				$("#studentTiKuPage").html('');
			}
		});
	}
	//查询公司题库联动查询
	stuLeanCpDetail.studentTiKuList = function(tikuOrSubjectId,nodeId,type){
		var data = {id:tikuOrSubjectId,type:type},$this = this;
		$this.ajaxLoad("/stuLeanRecord/studentTiKuList",data,function(jsonData){
			assign.assTiKuOrSubject(nodeId,jsonData,type);
		});
	}
	
	/**
	 * 学习记录赋值
	 */
	assign.assLeanSamll = function(arr,nodeId){
		var html = '<tr class="table-title">'+
			            '<td>课次名称</td>'+
			            '<td>教学形式</td>'+
			            '<td>最后上课时间</td>'+
			            '<td>完成情况</td>'+
			            '<td>累计上课</td>'+
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
		                    '<td>'+(obj.lectureName?obj.lectureName:"")+'</td>'+
		                    '<td>'+(obj.teachMethod?obj.teachMethod:"")+'</td>'+
		                    '<td>'+(obj.studyTime?obj.studyTime:"")+'</td>'+
		                    '<td>'+status+'</td>'+
		                    '<td class="total-pop" data-id="'+obj.id+'" data-type="'+obj.type+'" data-cpid="'+ obj.classTypeId +'">'+(obj.count?obj.count:"0")+'次</td>'+
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
		                    '<td style="width: 10%">'+obj.finish+'/'+obj.totalTopic+'题</td>'+
		                    '<td style="width: 10%">'+obj.exerciseScore+'</td>'+
		                    '<td style="width: 10%">'+(obj.finish?(obj.rigCount/obj.finish*100).toFixed(2):obj.finish.toFixed(2))+'%</td>'+
		                    '<td style="width: 18%" class="lest-td"><span><em class="iconfont ri">&#xe660;</em><i>'+obj.rigCount+'</i></span><span><em class="iconfont wr">&#xe68c;</em><i>'+obj.errorCount+'</i></span></td>'+
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
			html = '<option value="0">题库名称</option>';
		}else{
			html = '<option value="0">科目名称</option>';
		}
		$.each(arr,function(i,obj){
			html += '<option value="'+obj.id+'">'+obj.name+'</option>';
		});
		$("#"+nodeId).html(html);
	};
	assign.LecOrLesStudyDetail = function(nodeId,arr,type){
		var html = '<tr class="top-tr">'+
			            '<td>课次名称</td>'+
			            '<td>课程属性</td>'+
			            '<td>学习记录</td>'+
			            '<td>学习时间</td>'+
			       '</tr>',timeFormate="";
		if(arr && arr.length>0){
			$.each(arr,function(i,obj){
				if("lecture"==type){
					timeFormate = assign.getLeanTime(obj.lastTimeLone);
					obj.studyTime = (obj.studyTime?obj.studyTime:"") + ' 观看' + obj.teachMethod;
				}else{
					if(obj.lastTimeLone=='0'||!obj.lastTimeLone){
						obj.studyTime = "直播或面授未开始";
					}else{
						obj.studyTime = obj.studyTime + ' 观看' + obj.teachMethod;
					}
					timeFormate = assign.getLessionTime(obj.lastTimeLone);
				}
				html += '<tr>'+
		                    '<td>'+(obj.lectureName?obj.lectureName:"")+'</td>'+
		                    '<td>'+(obj.teachMethod?obj.teachMethod:"")+'</td>'+
		                    '<td>'+obj.studyTime+'</td>'+
		                    '<td>'+timeFormate+'</td>'+
		                '</tr>';
			});
		}else{
			html += '<tr>'+
			    		'<td colspan="4">暂时没有数据</td>'+
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
			return studyTime;
		}
		if(time.indexOf(".")!=-1){
			time = time.split(".");
			studyTime = time[0] + "小时" + parseFloat(time[1])*60 + "分钟";
		}else{
			studyTime = time + "小时";
		}
		return studyTime;
	}
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
		$chooseMenu("studentsCode");
		stuLeanCpDetail.queryStudentCpLeanRecord();					//查询学习记录
		stuLeanCpDetail.studentTiKuRecord(0,assign.getCondition());//查看所有的做题记录
		stuLeanCpDetail.studentTiKuList("","stu_tiKu",0);		//查询公司下所有的题库
		$("#stu_tiKu").change(function(){			//事件绑定
			stuLeanCpDetail.studentTiKuList($(this).val(),"stu_subject",1);
		});
		$("#search").bind('click',function(){
			stuLeanCpDetail.studentTiKuRecord(0,assign.getCondition());
		});
		
		//学习记录
        $(".total-pop").on("click",function(){
            $(".pop-fixed").fadeIn(200);
        })
        $(".date-picker").datetimepicker({
			format: "yyyy-mm-dd",
			minView:2,
			autoclose: true,
			language: "zh-CN",
//			startDate:(new Date())
//			initialDate:(new Date())
	});
		$(".date-picker").datetimepicker("update",new Date());
		$("#leanRecordTable").on("click",".total-pop",function(){
			var dataInfo = $(this).data(),value=$(this).html().trim();
			value = value.split('次');
			if(value[0]!='0'&&value[0]){
//				dataInfo.classTypeId = $("#classTypeId").val();
				dataInfo.stuId = $("#stuId").val();
				dataInfo.classTypeId = dataInfo.cpid;
				stuLeanCpDetail.queryStudentLecOrLesStudyDetail(0,dataInfo);
				$(".pop-fixed").fadeIn(200);
			}
		});
		
	});
})(jQuery,rootPath)
