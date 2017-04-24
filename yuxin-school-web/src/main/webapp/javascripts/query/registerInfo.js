(function($){
	
	function getDays(strDateStart,strDateEnd){
		var result=new Array();
		var strSeparator = "-"; //日期分隔符
		var mDays=[1,3,5,7,8,10,12];
		var oDate1;
		var oDate2;
		var iDays;
		oDate1= strDateStart.split(strSeparator);
		oDate2= strDateEnd.split(strSeparator);
		var strDateS = new Date(oDate1[0] + "-" + oDate1[1] + "-" + oDate1[2]);
		var strDateE = new Date(oDate2[0] + "-" + oDate2[1] + "-" + oDate2[2]);
		iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数 
		var currtDay=oDate1[2]-1;
		var currtMonth=oDate1[1];
		var currtYear=oDate1[0];
		for(var i=0;i<=iDays;i++){
			var day;
			if(oDate1[0]!=oDate2[0]){
				//不是同一年
				if(oDate1[1]!=oDate2[1]){
					//不是同一个月
					if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))!=-1 && currtDay<31){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))==-1 && currtDay<30){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<=12){
						currtDay=1;
						currtMonth= parseInt(currtMonth)<10?"0"+(parseInt(currtMonth)+1):parseInt(currtMonth)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else{
						currtDay=1;
						currtMonth=1;
						currtYear=parseInt(currtYear)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}
				}else{
					//是同一个月
					currtDay=parseInt(currtDay)+1;
					day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
				}
			}else{
				//是同一年
				if(oDate1[1]!=oDate2[1]){
					//不是同一个月
					if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))!=-1 && currtDay<31){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))==-1 && currtDay<30){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<12){
						currtDay=1;
						currtMonth= parseInt(currtMonth)<9?"0"+(parseInt(currtMonth)+1):parseInt(currtMonth)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else{
						currtDay=1;
						currtMonth=1;
						currtYear=parseInt(currtYear)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}
				}else{
					//是同一个月
					currtDay=parseInt(currtDay)+1;
					day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
				}
			}
			
			result.push(day);
		}
		return result;
	}
	var student=function(){
		this.settings={
				 title : {
				        text: '登录次数统计',
				        subtext: ''
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['人数']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            saveAsImage : {show: true}
				        }
				    },
			    calculable : true,
               xAxis : 
	                {
			            type : 'category',
			            boundaryGap : false,
			            data : [],
			            boundaryGap:[10,1]
			        },
               yAxis : 
	                {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value} 人'
			            }
			        }
		};
	}
	
	student.prototype={
		init: function(){
			$selectMenu('org_static_registerInfo');
			var $this=this;
			require.config({
				paths : {
					echarts : rootPath + '/javascripts/plus/echarts'
				}
			});
			$this.initChart();	
			//日期列表
			$("#dateList").on("click","a.btn",function(){
				var mark=$(this).attr("mark");
				var status=$(this).hasClass("btn-success");
				if(!status){
					$(this).addClass("btn-success").siblings("a").removeClass("btn-success");
					if(mark=="nos"){
						$(".daterangs").removeClass("none");
					}else{
						$(".daterangs").addClass("none");
					}
				}else{
					$(this).removeClass("btn-success");
					var nonestatus=$(".daterangs").hasClass("none");
					if(!nonestatus){
						$(".daterangs").addClass("none");
					}
				}
			})
			//绑定事件
			$this.bindEvent();
			//收索
			$("#searchContent").on("click",function(){
				$this.search();
			})
		},
		search: function(page){
			var mark="";
			var $this=this;
			var data={};
			data.page=page?page:1;
			//日期列表
			$("#dateList").find("a").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					mark="other";
					if($(this).attr("mark")=="nos"){
						mark="nos";
						data.startTime=$(".from").val();
						data.endTime=$(".to").val();
					}else{
						data.marks=$(this).attr("mark");
					}
				}
			});
			if(mark=="nos"){
				if(!$(".from").val() || !$(".to").val()){
					$.msg("请指定日期范围");
					return false;
				}
			}
			$.each(data,function(key,value){
				if(!value){
					delete data[key];
				}
			})
			console.log(data);
			
			$(".tables").find("table").find("tr:gt(0)").remove();
			$.ajax({
				url : rootPath+"/query/queryRegisterInfo",
				data: data,
				success: function(jsonData){
					if(jsonData.data.length==0){
						$(".tables").find("table").append('<tr><td colspan="3">没有查找到数据</td></tr>');
					}
					$.each(jsonData.data,function(i,riv){
						contentsShow="";
						$(".tables").find("table").append('<tr>'+
								'<td>'+(riv.utmUrl?riv.utmUrl:"")+'</td>'+
								'<td>'+(riv.mobile?riv.mobile:"")+'</td>'+
								'<td>'+(riv.registTime?riv.registTime:"")+'</td>'+
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
		bindEvent : function(){
			var $this=this;
			// 明细/趋势图
			$(".query").on("click.btn.panel",".title a",function(){
				if($(this).hasClass("btn-detail")){
					$this.showDetail();
				}
				if($(this).hasClass("btn-chart")){
					$this.showChart();
				}
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
			})
			$(".date-picker").datetimepicker({
				format: "yyyy-mm-dd",
 				minView:2,
 				autoclose: true,
 				language: "zh-CN"
			});
			$(".date-picker").on("change",function(){
				var from,to;
				from=$(".from").val();
				to=$(".to").val();
				if(from && to){
					$this.settings.xAxis.data.push();
				}
			})
		},
		showDetail : function(){
			$(".panel").find(".tables").fadeIn(300);
			$(".panel").find(".chars").hide();
		},
		showChart : function(){
			$(".panel").find(".chars").fadeIn(300);
			$(".panel").find(".tables").hide();
		},
		initChart : function(option){
			var $this=this;
			require([ 'echarts','echarts/chart/bar' ], function(ec) {
				var myChart = ec.init($(".chars")[0]);
				option=$.extend({},$this.settings,option);
				myChart.setOption(option);
			});
		},
		initTables: function(){
			
		}
	}
	$(document).ready(function(){
		var stu=new student();
		stu.init();
		 $(".exportexcle").on(
	                'click',
            function () {
	            marks="other";
            	$("#dateList").find("a").each(function(){
        			var st=$(this).hasClass("btn-success");
        			if(st){
        				marks="other";
        				marks=$(this).attr("mark");
        			}
        		});   	
                if ($(".tables").find("tr").eq(1).find("td").length <= 1) {
                    $.msg("没有数据可以导出");
                } else {
                    $("#searchForm").attr("action",
                        rootPath + "/query/exportRegisterInfo/"+marks)
                        .submit();
                }
	   });
		$(".hideAllPopup").on('click',function(){
			$(".showAllPopup").hide();
		}) 
	})
	
})(jQuery)
