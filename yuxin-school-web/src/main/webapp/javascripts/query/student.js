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
			$selectSubMenu('query_statistics');
			var $this=this;
			require.config({
				paths : {
					echarts : rootPath + '/javascripts/plus/echarts'
				}
			});
			$this.initChart();
			// 初始化分校
			if($("#isAdmin").val() && $("#isAdmin").val()=="true"){
				$.ajax({
					url: rootPath+"/sysConfigSchool/getSchoolJson",
					type: "post",
					dataType: "json",
					success: function(jsonData){
						$(".classes-type").find(".c:first").find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success">全部</a>');
						$.each(jsonData,function(i,data){
							$(".classes-type").find(".c:first").find(".t-content").append('<a href="javascript:;" value="'+data.id+'" class="btn btn-mini btn-default">'+data.schoolName+'</a>');
						});
					}
				})
			}else if($("#isSubAdmin").val() && $("#isSubAdmin").val()=="true"){
				$(".classes-type").find(".c:first").find(".t-content").html('').append('<a href="javascript:;" id="'+$("#schoolId").val()+'" class="btn btn-mini btn-success">'+$("#schoolName").val()+'</a>');
				//$(".sc-type").find("a").eq(0).trigger("click.btn.schools");
			}
			$(".classes-type").find(".c").eq(1).find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success">全部</a>');
			$(".classes-type").find(".c").eq(1).find(".t-content").append('<a href="javascript:;" value="1" class="btn btn-mini btn-default">官网注册</a>');
			$(".classes-type").find(".c").eq(1).find(".t-content").append('<a href="javascript:;" value="2" class="btn btn-mini btn-default">线下录入</a>');
			$(".classes-type").find(".c").eq(1).find(".t-content").append('<a href="javascript:;" value="3" class="btn btn-mini btn-default">手机端</a>');

			$(".classes-type").find(".c").eq(2).find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success">全部</a>');
			$(".classes-type").find(".c").eq(2).find(".t-content").append('<a href="javascript:;" value="1" class="btn btn-mini btn-default">收费用户</a>');
			$(".classes-type").find(".c").eq(2).find(".t-content").append('<a href="javascript:;" value="0" class="btn btn-mini btn-default">免费用户</a>');
			//绑定事件
			$this.bindEvent();
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
				}
			})
		},
		search: function(page){
			var mark="";
			var $this=this;
			var data={};
			data.schoolId=$(".classes-type").find(".c").eq(0).find(".btn-success").attr("value");
			data.registType=$(".classes-type").find(".c").eq(1).find(".btn-success").attr("value");
			data.vipFlag=$(".classes-type").find(".c").eq(2).find(".btn-success").attr("value");
//			data.startDate=$(".from").val();
//			data.endDate=$(".to").val();
			data.page=page?page:1;
			//日期列表
			$("#dateList").find("a").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					mark="other";
					if($(this).attr("mark")=="nos"){
						mark="nos";
						data.startDate=$(".from").val();
						data.endDate=$(".to").val();
					}else{
						data.timeMark=$(this).attr("mark");
					}
				}
			});
			if(mark==""){
				$.msg("请指定日期范围");
				return;
			}else if(mark=="nos"){
				if(!$(".from").val() || !$(".to").val()){
					$.msg("请指定日期范围");
					return false;
				}
				if ($(".to").val() != "") {
	                if ($(".to").val() < $(".from").val()) {
	                    $.msg("时间范围不正确");
	                    return;
	                }

	            }
				if(getDays($(".from").val(),$(".to").val()).length>90){
					$.msg("日期范围不能超过三个月");
					return;
				}
			}
			$.each(data,function(key,value){
				if(!value){
					delete data[key];
				}
			})
			console.log(data);
			$.ajax({
				url : rootPath+"/query/count",
				data : data,
				success : function(jsonData){
					$(".tabs").find("a").eq(0).find("b").html(jsonData.mobile_num);
					$(".tabs").find("a").eq(1).find("b").html(jsonData.offline_num);
					$(".tabs").find("a").eq(2).find("b").html(jsonData.online_num);
					$(".tabs").find("a").eq(3).find("b").html(jsonData.total_num);
				}
			})
			$(".tables").find("table").find("tr:gt(0)").remove();
			$.ajax({
				url : rootPath+"/query/studentTable",
				data: data,
				success: function(jsonData){
					if(jsonData.data.length==0){
						$(".tables").find("table").append('<tr><td colspan="8">没有查找到数据</td></tr>');
					}
					var hhh = '<tr><th width="12%">所属分校</th><th width="12%">姓名</th><th width="12%">用户名</th><th width="12%">手机</th><th width="12%">邮箱</th><th width="15%">注册时间</th><th width="10%">用户类型</th><th width="15%">来源</th></tr>';
					$.each(jsonData.data,function(i,user){
						var registHtml="";
						if(user.registType){
							if(user.registType=="1"){
								registHtml="官网注册";
							}else if(user.registType=="2"){
								registHtml="线下录入";
							}else if(user.registType=="3"){
								registHtml="手机端";
							}
						}
						hhh+='<tr>'+
							'<td>'+(user.schoolName?user.schoolName:"")+'</td>'+
							'<td>'+(user.name?user.name:"")+'</td>'+
							'<td>'+(user.username?user.username:"")+'</td>'+
							'<td>'+(user.mobile?user.mobile:"")+'</td>'+
							'<td>'+(user.email?user.email:"")+'</td>'+
							'<td>'+(user.registTime?user.registTime:"")+'</td>'+
							'<td>'+(user.vipFlag=="1"?"收费用户":"免费用户")+'</td>'+
							'<td>'+registHtml+'</td>'+
							'</tr>';

//						if(user.registType=="1"){
//							online_input+=1;
//						}else{
//							offline_input+=1;
//						}
					});
					$(".tables").find("table").html(hhh);
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
			$.ajax({
				url : rootPath+"/query/studentChart",
				data: data,
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
							}else{
								startDate=$(".from").val();
								endDate=$(".to").val();
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
					$this.initChart(options);
				}
			});
		},
		bindEvent : function(){
			var $this=this;
			//条件
			$(".classes-type").on("click.btn.search","a",function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
			})
			//search
			.on("click.btn.search",":button",function(){
				$this.search();
			})
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
			require([ 'echarts', 'echarts/chart/line' ], function(ec) {
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
	})
})(jQuery)