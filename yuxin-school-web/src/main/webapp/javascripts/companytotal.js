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
					if(parseInt(currtMonth)<=12 && mDays.indexOf(parseInt(currtMonth))!=-1 && currtDay<31){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<=12 && mDays.indexOf(parseInt(currtMonth))==-1 && currtDay<30){
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
				        text: '学员趋势图',
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
			            data : []
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
			var $this=this;
			require.config({
				paths : {
					echarts : rootPath + '/javascripts/plus/echarts'
				}
			});
			$this.initChart(function(){
				$this.search();
			});
			//日期列表
			$("#dateList").on("click","a.btn",function(){
				var status=$(this).hasClass("btn-success");
				if(!status){
					$(this).addClass("btn-success").siblings("a").removeClass("btn-success");
				}
				$this.search();
			})
			//绑定事件
			$this.bindEvent();
		},
		search: function(page){
			var $this=this;
			var timeMark;
			//日期列表
			$("#dateList").find("a").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					timeMark=$(this).attr("mark");
				}
			});
			$.ajax({
				url : rootPath+"/query/count",
				data : {"timeMark":timeMark},
				success : function(jsonData){
					$("#tabs").find("a").eq(0).find("b").html(jsonData.mobile_num);
					$("#tabs").find("a").eq(1).find("b").html(jsonData.offline_num);
					$("#tabs").find("a").eq(2).find("b").html(jsonData.online_num);
					$("#tabs").find("a").eq(3).find("b").html(jsonData.total_num);
				}
			})
			$.ajax({
				url : rootPath+"/query/studentChart",
				data : {"timeMark":timeMark},
				success: function(jsonData){
					var options={};
					var series=new Array(),sdata=new Array();
					var startDate,endDate;
					//根据选中日期赋值
					$("#dateList").find("a").each(function(){
						var st=$(this).hasClass("btn-success");
						if(st){
							var m=$(this).attr("mark");
							if(m=="today"){
								startDate=dateAdd("d",0);
								endDate=new Date();
								startDate=dateToStr("yyyy-MM-dd",startDate);
								endDate=dateToStr("yyyy-MM-dd",endDate);
							}else if(m=="yesday"){
								startDate=dateAdd("d",1);
								endDate=new Date();
								startDate=dateToStr("yyyy-MM-dd",startDate);
								endDate=dateToStr("yyyy-MM-dd",endDate);
							}else if(m=="sevnday"){
								startDate=dateAdd("d",7);
								endDate=new Date();
								startDate=dateToStr("yyyy-MM-dd",startDate);
								endDate=dateToStr("yyyy-MM-dd",endDate);
							}else if(m=="thirty"){
								startDate=dateAdd("d",datePart("d",new Date())-1);
								endDate=new Date();
								startDate=dateToStr("yyyy-MM-dd",startDate);
								endDate=dateToStr("yyyy-MM-dd",endDate);
							}else if(m=="thirmonth"){
								startDate=dateAdd("d",90);
								endDate=new Date();
								startDate=dateToStr("yyyy-MM-dd",startDate);
								endDate=dateToStr("yyyy-MM-dd",endDate);
							}
						}
					});
					$this.settings.xAxis.data=getDays(startDate,endDate);
					$.each($this.settings.xAxis.data,function(i){
						var d=0;
						$.each(jsonData,function(j){
							if(jsonData[j].registTime.substring(5,10)==$this.settings.xAxis.data[i]){
								d=jsonData[j].num;
							}
						})
						sdata.push(d);
					});
					var seri={
				            name:'学员数量',
				            type:'line',
				            data:sdata,
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
				            }
				        };
					series.push(seri);
					options.series=series;
					$this.fillChart(options);
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
		initChart : function(func){
			var $this=this;
			require([ 'echarts', 'echarts/chart/line' ], function(ec) {
				$this.chart=ec;
				if(func){
					func();
				}
			});
		},
		fillChart : function(option){
			var $this=this;
			var myChart = $this.chart.init($(".charsStu")[0]);
			option=$.extend({},$this.settings,option);
			myChart.setOption(option);
		},
		initTables: function(){
			
		}
	}
	
	$(document).ready(function(){
		var stu=new student();
		stu.init();
	})
})(jQuery)