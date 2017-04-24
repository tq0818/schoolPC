(function($){

	var Page={
			init : function(){
				var $this=this;
				// 初始化日期框
	            $(".date-picker").datetimepicker({
	                format: "yyyy-mm-dd",
	                minView: 2,
	                autoclose: true,
	                language: "zh-CN"
	            });
	            //加载课程包下的阶段
	            $.ajax({
					url: rootPath+"/classPackageStage/queryClassPackageStages",
					type: "post",
					dataType: "json",
					data: {"classPackageId":$("#classPackageId").val()},
					success: function(jsonData){
					 var html="<option value=''>阶段名称</option>";
					  $.each(jsonData,function(i,data){
						  html+='<option value='+data.id+'>'+data.title+'</option>';
					  });
					 $("#stageNames").html(html);
					}
	            });	
	            //点击收索
	            $(".searchContents").on('click',function(){
	            	$this.search();
	            });
	            //点击时间段查询
	            $("#timeMarking a").on('click',function(){
	            	if(!$(this).hasClass("choose")){
	            		$(this).addClass("choose").siblings("a").removeClass("choose");
	            	}
	            	$this.search1();
	            });
	            $this.search();
			},
			search: function(page,mark){
				var mark="";
				var $this=this;
				var data={};
				data.commodityId=$("#classPackageId").val();
				var stageId=$("#stageNames").val();
				if(stageId && stageId!=""){
					data.stageId=$("#stageNames option:selected").val();
				}
				if($("#stuName").val()!=""){
					data.stuName=$("#stuName").val();
				}
				if($(".from").val()!=""){
					data.startDate=$(".from").val();
				}
				if($(".to").val()!=""){
					data.endDate=$(".to").val();
				}
				data.page=page?page:1;
				
			    if ($(".to").val() != "") {
	                if ($(".to").val() < $(".from").val()) {
	                    $.msg("时间范围不正确");
	                    return;
	                }

	            }
				$.each(data,function(key,value){
					if(!value){
						delete data[key];
					}
				})
				//总额统计
				$.ajax({
					type : 'post',
					url : rootPath+"/payOrder/classPackageOrder",
					data: data,
					success: function(jsonData){
						$("#payTotal").html(jsonData.totalMoney);
						$("#payedOrder").html(jsonData.payMoney);
						$("#paying").html(jsonData.notPayMoney);
					}
				});
				$("#tableList").find("tr:gt(0)").remove();
				$.ajax({
					type : 'post',
					url : rootPath+"/classPackage/searchOrderList",
					data: data,
					success: function(jsonData){
						if(jsonData.data.length==0){
							$("#tableList").append('<tr><td colspan="9">没有查找到数据</td></tr>');
						}
						$.each(jsonData.data,function(i,paymaster){
							var toalMoney=$.formatMoney((paymaster.totalMount?paymaster.totalMount:"0.00"));
							var paymoney=$.formatMoney((paymaster.payPrice?paymaster.payPrice:"0.00"));
							$("#tableList").append('<tr ids='+paymaster.id+'>'+
									'<td>'+(paymaster.mobile?paymaster.mobile:"")+'</td>'+
									'<td>'+(paymaster.stuName?paymaster.stuName:"")+'</td>'+
									'<td>'+(paymaster.username?paymaster.username:"")+'</td>'+
									'<td>'+(paymaster.orderTime?paymaster.orderTime:"")+'</td>'+
									'<td>'+(paymaster.payTime?paymaster.payTime:paymaster.orderTime)+'</td>'+
									'<td>'+(paymaster.applyChannelCode=="CHANNEL_ONLINE"?"线上报名":"线下报名")+'</td>'+
									'<td>'+(paymaster.payType?paymaster.payType:"")+'</td>'+
									'<td>'+toalMoney+'</td>'+
									'<td>'+paymoney+'</td>'+
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
						
					}
				});
			},
			search1: function(page){
				var mark="";
				var $this=this;
				var data={};
				data.commodityId=$("#classPackageId").val();
				$("#timeMarking").find("a").each(function(){
					if($(this).hasClass("choose")){
						if($(this).attr("mark")){
							data.timeMark=$(this).attr("mark");
						}
					}
				});
				data.page=page?page:1;
				
				$.each(data,function(key,value){
					if(!value){
						delete data[key];
					}
				})
				//总额统计
				$.ajax({
					type : 'post',
					url : rootPath+"/payOrder/classPackageOrder",
					data: data,
					success: function(jsonData){
						$("#payTotal").html(jsonData.totalMoney);
						$("#payedOrder").html(jsonData.payMoney);
						$("#paying").html(jsonData.notPayMoney);
					}
				});
				$("#tableList").find("tr:gt(0)").remove();
				$.ajax({
					type : 'post',
					url : rootPath+"/classPackage/searchOrderList",
					data: data,
					success: function(jsonData){
						if(jsonData.data.length==0){
							$("#tableList").append('<tr><td colspan="9">没有查找到数据</td></tr>');
						}
						$.each(jsonData.data,function(i,paymaster){
							var toalMoney=$.formatMoney((paymaster.totalMount?paymaster.totalMount:"0.00"));
							var paymoney=$.formatMoney((paymaster.payPrice?paymaster.payPrice:"0.00"));
							$("#tableList").append('<tr>'+
									'<td>'+(paymaster.mobile?paymaster.mobile:"")+'</td>'+
									'<td>'+(paymaster.stuName?paymaster.stuName:"")+'</td>'+
									'<td>'+(paymaster.username?paymaster.username:"")+'</td>'+
									'<td>'+(paymaster.orderTime?paymaster.orderTime:"")+'</td>'+
									'<td>'+(paymaster.payTime?paymaster.payTime:paymaster.orderTime)+'</td>'+
									'<td>'+(paymaster.applyChannelCode=="CHANNEL_ONLINE"?"线上报名":"线下报名")+'</td>'+
									'<td>'+(paymaster.payType?paymaster.payType:"")+'</td>'+
									'<td>'+toalMoney+'</td>'+
									'<td>'+paymoney+'</td>'+
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
						
					}
				});
			}
		}
	$(document).ready(function(){
		Page.init();
	})
	window.Page=Page;
})(jQuery)