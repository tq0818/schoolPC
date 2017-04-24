(function($){
	
	var Form={
		init: function(){
			var $this=this;
			//添加窗口
			$(".box-select").on('click',function(){
				$(".addTypeProm").popup("show");
				Form.clearData();
			});
			
			$this.bindEvent();
			//收索
			$("#searchContent").on("click",function(){
				$this.search();
			})
			//保存
			$("#savetiketsType").on('click',function(){
				var data={};
				data.title=$("#promTypeName").val();
				data.totalNum=$("#totalNum").val();
				data.money=$("#money").val();
				data.videoSpace=$("#videoSpace").val();
				var xiane;
				$("#addRadio").find("input[type=radio]").each(function(){
					var status=$(this).is(":checked");
					if(status){
						xiane=$(this).attr("value");
					}
				});
				if(xiane==1){
					data.baseMoney=$("#baseMoney").val();
				}
				data.videoFlowRate=$("#videoFlowRate").val();
				data.smsNum=$("#messageNum").val();
				data.emailNum=$("#emailNum").val();
				data.studentNum=$("#stuNum").val();
				data.startTime=$(".startTime").val();
				data.endTime=$(".endTime").val();
				
				if($("#promTypeName").val()==""){
					$.msg("名称不能为空");
					return;
				}
				if($("#totalNum").val()==""||isNaN($("#totalNum").val())){
					$.msg("请正确输入发行数量");
					return;
				}
				if($(".startTime").val()==""||$(".endTime").val()==""){
					$.msg("请选择有效期限");
					return;
				}
				if(isNaN($("#messageNum").val())){
					$.msg("请正确输入短信数量");
					return;
				}
				if(isNaN($("#emailNum").val())){
					$.msg("请正确输入邮件数量");
					return;
				}
				if(isNaN($("#stuNum").val())){
					$.msg("请正确输入学员数量");
					return;
				}
				console.log(data);
				$.ajax({
					type : 'post',
					url : rootPath+"/sysOperationTicketType/add",
					data: data,
					success: function(jsonData){
						$(".addTypeProm").popup("hide");
						$.msg("操作成功");
					}
				});
			});
		},
		search: function(page){
			var $this=this;
			var data={};
			data.startDate=$(".from").val();
			data.endDate=$(".to").val();
			data.page=page?page:1;
			
//			if(!$(".from").val() || !$(".to").val()){
//				$.msg("请指定日期范围");
//				return;
//			}
			console.log(data);
			$(".tables").find("table").find("tr:gt(0)").remove();
			$.ajax({
				url : rootPath+"/sysOperationTicketType/showTypeList",
				data: data,
				success: function(jsonData){
					if(jsonData.data.length==0){
						$(".tables").find("table").append('<tr><td colspan="7">没有查找到数据</td></tr>');
					}
					$.each(jsonData.data,function(i,tic){
						console.log(tic.status);
						$(".tables").find("table").append('<tr>'+
								'<td>'+(tic.title?tic.title:"")+'</td>'+
								'<td>'+((tic.isUsedNum?tic.isUsedNum:0)+"/"+((tic.totalNum?tic.totalNum:0)=='-1'?'不限':tic.totalNum))+'</td>'+
								'<td>'+(tic.startTime+"至"+tic.endTime)+'</td>'+
								'<td>'+(tic.userName?tic.userName:"")+'</td>'+
								'<td>'+(tic.createTime)+'</td>'+
								'<td style="'+(tic.status==1?"color:red;":"color:")+';">'+(tic.status==0?'正常':"禁用")+'</td>'+
								'<td><a href="javascript:;" ids='+tic.id+' class="btn btn-mini btn-primary detailtickets">详情</a>&nbsp;&nbsp;<a href="javascript:;" ids='+tic.id+' class="btn btn-mini btn-primary statu">'+(tic.status==0?"禁用":"启用")+'</a></td>'+
								'</tr>');
					});
					if(jsonData.rowCount>12){
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
					//启用禁用按钮
					$(".statu").on('click',function(){
						var $this=$(this);
						var t=$this.html();
						var id=$this.attr("ids");
						if(t.trim()=='禁用'){
							$.ajax({
								url : rootPath+"/sysOperationTicketType/changeStatus",
								data: {"id":id,"status":1},
								success: function(jsonData){
									console.log(jsonData.status);
									$this.html('正常');
									$this.parent().prev().html('禁用').attr("style","color:red;");
								}
							});
						}else{
							$.ajax({
								url : rootPath+"/sysOperationTicketType/changeStatus",
								data: {"id":id,"status":0},
								success: function(jsonData){
									$this.html('禁用');
									$this.parent().prev().html('正常').attr("style","color:;");
								}
							});
						}
						
					})
					//详情
					$(".detailtickets").on('click',function(){
						$(".detailProm").popup("show");
						var $this=$(this);
						var id=$this.attr("ids");
						$.ajax({
							type : 'get',
							url : rootPath+"/sysOperationTicketType/"+id,
							dataType : 'json',
							success: function(jsonData){
								$("#promTypeName1").text((jsonData.title?jsonData.title:''));
								$("#money1").text((jsonData.money?jsonData.money:0));
								if(jsonData.totalNum==-1){
									$("#totalNum1").text("不限");
								}else{
									$("#totalNum1").text((jsonData.totalNum?jsonData.totalNum:0));
								}
								if(jsonData.baseMoney){
									var html='<input type="radio" name="xiane"/>无限额'+
										'<input type="radio" name="xiane" checked="checked"/>单笔订单满'+jsonData.baseMoney+'元';
									$("#detailRadio").html('').append(html);
								}else{
									var html='<input type="radio" name="xiane" checked="checked"/>无限额'+
									'<input type="radio" name="xiane"/>单笔订单满0元';
								    $("#detailRadio").html('').append(html);
								}
								$("#videoFlowRate1").html((jsonData.videoFlowRate?jsonData.videoFlowRate:0)+"<span>G</span>");
								$("#videoSpace1").html((jsonData.videoSpace?jsonData.videoSpace:0)+"<span>G</span>");
								$("#messageNum1").text(jsonData.smsNum?jsonData.smsNum:0);
								$("#eamilNum1").text(jsonData.emailNum?jsonData.emailNum:0);
								$("#sTime").text((jsonData.startTime?jsonData.startTime:''));
								$("#eTime").text((jsonData.endTime?jsonData.endTime:''));
								$("#stuNum1").text(jsonData.studentNum?jsonData.studentNum:0);
							}
						});
					})
				}
			});
		},
		bindEvent : function(){
			var $this=this;
			$(".date-picker").datetimepicker({
				format: "yyyy-mm-dd",
 				minView:2,
 				autoclose: true,
 				language: "zh-CN"
			});
		},
		clearData : function(){
			$("#promTypeName").val('');
			$("#totalNum").val('');
			$("#money").val('');
			$("#videoSpace").val('');
			$("#videoFlowRate").val('');
			$("#messageNum").val('');
			$("#emailNum").val('');
			$("#stuNum").val('');
			$(".startTime").val('');
			$(".endTime").val('');
		}
	}
	
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)