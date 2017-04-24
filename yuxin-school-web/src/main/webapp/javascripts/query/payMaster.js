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
				        text: '订单趋势图',
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
			$selectSubMenus('signup_news');
			//代理机构
			var proxyOrg = $('#isProxyOrg').val();
			if(proxyOrg == 'true'){
				$('.system_managelist').find('li').each(function(){
					var _this=$(this);
					if(_this.attr('code')!='signup_news'){
						_this.hide()
					}
				})
			}
			var $this=this;
			require.config({
				paths : {
					echarts : rootPath + '/javascripts/plus/echarts'
				}
			});
			$this.initChart();
			// 初始化分校
			if(($("#isAdmin").val() && $("#isAdmin").val()=="true" )||(proxyOrg && proxyOrg=='true')){
				$.ajax({
					url: rootPath+"/sysConfigSchool/getSchoolJson",
					type: "post",
					dataType: "json",
					success: function(jsonData){
						$(".classes-type").find(".c:first").find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success" value="">全部</a>');
						$.each(jsonData,function(i,data){
							$(".classes-type").find(".c:first").find(".t-content").append('<a href="javascript:;" value="'+data.id+'" class="btn btn-mini btn-default">'+data.schoolName+'</a>');
						});
					}
				})
			}else if($("#isSubAdmin").val() && $("#isSubAdmin").val()=="true"){
				$(".classes-type").find(".c:first").find(".t-content").html('').append('<a href="javascript:;" value="'+$("#schoolId").val()+'" class="btn btn-mini btn-success">'+$("#schoolName").val()+'</a>');
				$(".classes-type").find(".c:first").trigger("click a.btn");
			}
			//初始化学科
			$.ajax({
				url: rootPath+"/sysConfigItem/getJsons",
				type: "post",
				dataType: "json",
				data: {"itemType":1},
				success: function(jsonData){
					$(".classes-type").find(".c:eq(1)").find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success" value="">全部</a>');
					$.each(jsonData,function(i,data){
						$(".classes-type").find(".c:eq(1)").find(".t-content").append('<a href="javascript:;" value="'+data.id+'" class="btn btn-mini btn-default">'+data.itemName+'</a>');
					});
				}
			})
			$(".classes-type").find(".c:eq(2)").find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success">全部</a>');
			$("#classtypeList").html('').append('<option value>全部</option>');
			
			$(".classes-type").find(".c").eq(4).find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success">全部</a>');
			$(".classes-type").find(".c").eq(4).find(".t-content").append('<a href="javascript:;" value="CHANNEL_ONLINE" class="btn btn-mini btn-default">线上订单</a>');
			$(".classes-type").find(".c").eq(4).find(".t-content").append('<a href="javascript:;" value="CHANNEL_OFFLINE" class="btn btn-mini btn-default">线下订单</a>');
			
			//分校列表
			$("#schoolList").on("click","a.btn",function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
				$this.queryClassType();
			});
			
			//学科
			$("#oneList").on("click","a.btn",function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
				$this.queryItemSecond();
			    $this.queryClassType();
			})
			$this.queryItemSecond();
			//学科小类
			$("#secondList").on('click','a.btn',function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
				  $this.queryClassType();
			})
			//班型
			$("#classtypeList").on('click','a.btn',function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
			})
			//订单
			$("#orderList").on('click','a.btn',function(){
				$(this).siblings().removeClass("btn-success").addClass("btn-default");
				$(this).addClass("btn-success").removeClass("btn-default");
			})
			
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
			
			//绑定事件
			$this.bindEvent();
			
			//收索
			$("#searchContent").on("click",function(){
				$this.search();
			})
			
			//代理机构
			var html ='';
			$.ajax({
				url: rootPath + '/companyStudyCard/queryProxyOrgs',
				type: "post", 
			    success: function(jsonData){
			    	switch(jsonData.result){
			    	case 'success':
			    		$('#proxyOrgs').html('<option value="">请选择</option>');
			    		$.each(jsonData.proxyOrgs,function(i,proxyOrg){
			    			html+='<option value="'+proxyOrg.id+'">'+proxyOrg.name+'</option>';
			    		})
			    		$('#proxyOrgs').append(html);
			    		break;
			    	case 'error':
			    	default:
			    		break;
			    	}
			    }
			})
		},
		search: function(page){
			var mark="";
			var $this=this;
			var data={};
			$("#schoolList").find("a.btn").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					data.schoolId=$(this).attr("value");
				}
			});
			$("#oneList").find("a.btn").each(function(){
				var st1=$(this).hasClass("btn-success");
				if(st1){
					data.itemOneId=$(this).attr("value");
				}
			});
			$("#secondList").find("a.btn").each(function(){
				var st2=$(this).hasClass("btn-success");
				if(st2){
					data.itemSecondId=$(this).attr("value");
				}
			});
			data.commodityId=$("#classtypeList option:selected").val();
			data.applyChannelCode=$(".classes-type").find(".c").eq(4).find(".btn-success").attr("value");
			data.page=page?page:1;
			data.proxyOrgId = $('#proxyOrgs').val();
			
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
				url:rootPath+"/studentPayMaster/queryTotal",
				data:data,
				success: function(result){
					var a=$.formatMoney(result);
					$(".tabs").find("a").eq(1).find("b").html(a);
				}
			});
			$(".tables").find("table").find("tr:gt(0)").remove();
			//代理机构
			var manager = $('#isAdmin').val();	//机构管理员
			var proxyOrg = $('#isProxyOrg').val(); //代理机构
			var userorg_roleopenflag = $('#userorg_roleopenflag').val(); //代理functionset
			if(userorg_roleopenflag==1&& manager == 'true'  && proxyOrg == 'true'){
				$('#org-th').html('所属分校-代理机构');
			}
			$.ajax({
				url : rootPath+"/studentPayMaster/queryStuPayMasterList",
				data: data,
				success: function(jsonData){
					if(jsonData.data.length==0){
						$(".tables").find("table").append('<tr><td colspan="8">没有查找到数据</td></tr>');
					}
					$(".tabs").find("a").eq(0).find("b").html(jsonData.rowCount);
					$.each(jsonData.data,function(i,paymaster){
						var totMon=$.formatMoney(paymaster.totalAmount);
						$(".tables").find("table").append('<tr>'+(manager||proxyOrg?
								'<td>'+(paymaster.schoolName?paymaster.schoolName+(paymaster.proxyOrgName?'-'+paymaster.proxyOrgName:''):"")+'</td>':
								'<td>'+(paymaster.schoolName?paymaster.schoolName:"")+'</td>')+
								'<td>'+(paymaster.stuName?paymaster.stuName:"")+'</td>'+
								'<td>'+(paymaster.username?paymaster.username:"")+'</td>'+
								'<td>'+(paymaster.createTime?paymaster.createTime:"")+'</td>'+
								'<td>'+(paymaster.classTypeName?paymaster.classTypeName:"")+'</td>'+
								'<td>'+(paymaster.trainingFee?paymaster.trainingFee:"0.00")+'</td>'+
								'<td>'+(totMon?totMon:"")+'</td>'+
								'<td>'+(paymaster.applyChannelCode=="CHANNEL_ONLINE"?"线上报名":"线下报名")+'</td>'+
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
			$.ajax({
				url : rootPath+"/studentPayMaster/queryChart",
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
					console.log(startDate+"--"+endDate);
					$this.settings.xAxis.data=getDays(startDate,endDate);
					$.each($this.settings.xAxis.data,function(i){
						var d=0;
						$.each(jsonData,function(j){
							if(jsonData[j].createTime.substring(5)==$this.settings.xAxis.data[i]){
								d=jsonData[j].num;
							}
						})
						sdata.push(d);
					});
					var seri={
				            name:'订单数量',
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
			
		},
		queryItemSecond: function(){
			 var itemOneId;
			$("#oneList").find("a.btn").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					itemOneId=$(this).attr("value");
				}
			});
			$.ajax({
				url: rootPath+"/sysConfigItem/getJsonsSecondItem",
				type: "post",
				dataType: "json",
				data: {"itemType":2,"parentId":itemOneId},
				success: function(jsonData){
					$(".classes-type").find(".c:eq(2)").find(".t-content").html('').append('<a href="javascript:;" class="btn btn-mini btn-success">全部</a>');
					$.each(jsonData,function(i,data){
						$(".classes-type").find(".c:eq(2)").find(".t-content").append('<a href="javascript:;" value="'+data.id+'" class="btn btn-mini btn-default">'+data.itemName+'</a>');
					});
				}
			})
		},
		queryClassType: function(){
			var sId=0,itemOneId=0,itemSecondId=0;
			$("#schoolList").find("a.btn").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					sId=$(this).attr("value");
				}
			});
			$("#oneList").find("a.btn").each(function(){
				var st1=$(this).hasClass("btn-success");
				if(st1){
					itemOneId=$(this).attr("value");
				}
			});
			$("#secondList").find("a.btn").each(function(){
				var st2=$(this).hasClass("btn-success");
				if(st2){
					itemSecondId=$(this).attr("value");
				}
			});
			$.ajax({
				url: rootPath+"/classType/queryList",
				type: "post",
				dataType: "json",
				data: {"createSchoolId":sId,"itemOneId":itemOneId,"itemSecondId":itemSecondId},
				success: function(jsonData){
					console.log(jsonData.length);
					$("#classtypeList").html('').append('<option value>全部</option>');
					$.each(jsonData,function(i,data){
						$("#classtypeList").append('<option value="'+data.id+'">'+data.name+'</option>');
					});
				}
			})
		}
	}
	
	$(document).ready(function(){
		var stu=new student();
		stu.init();
	})
})(jQuery)