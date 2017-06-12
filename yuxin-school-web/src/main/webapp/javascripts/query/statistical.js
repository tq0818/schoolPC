(function($){
	$.fn.statistical = function(callbackfun){
			var model ={
				init: function(){
					var _this = this;
					//this.queryPerfect();
				},
				/**
				**教育信息完善人数
		    	**/
				queryPerfect : function(){
					var _this = this;
					var perfect = document.getElementById('perfect-qushi');
					var dataCode = ["510102","510104","510105","510106","510107","510108",
						"510112","510113","510114","510115","510116","510117","510121","510129","510131","510132",
						"510156","510181","510182","510183","510184","510185"];
					var dataName = ["高新区","锦江区","青羊区","金牛区","武侯区","成华区","龙泉驿区","青白江区","新都区","温江区","双流区",
						"郫都区","金堂县","大邑县","蒲江县","新津县","天府新区","都江堰市","彭州市","邛崃市","崇州市","简阳市"];
					var dataKey = new Array(),dataValue = new Array();
					$.ajax({
						url:rootPath+'/query/statistics/indexOfComplete',
						dataType:'json',
						type:'post',
						success:function(result){
							if(result!=null && result.length>0){
								for(var i=dataCode.length-1; i>=0; i--){
									var num = 0;
									for(var j=0; j<result.length; j++){
										if(result[j].edu_area!=null && result[j].edu_area!="" && dataCode[i] == result[j].edu_area){
											dataKey.push(dataName[i]);
											dataValue.push(result[j].stuNum);

											result.splice(j, 1);
											j--;
											num++;
										}
									}
									if(num == 0){
										dataKey.push(dataName[i]);
										dataValue.push(0);
									}
								}
								_this.setCharts(perfect,"教育信息完善人数",dataKey,dataValue,"#4472c4");
							}
						}
					});
				},
				/**
				**学校信息完善人数
		    	**/
				queryPerfect2 : function(eduArea, eduStep){
					var _this = this;
					var perfect = document.getElementById('perfect-qushi');
					var dataKey = new Array(),dataValue = new Array();
					$.ajax({
						url:rootPath+'/query/statistics/orgStudentStatistics',
						data:{'eduArea':eduArea, 'eduStep':eduStep},
						dataType:'json',
						type:'post',
						success:function(result){
							if(result!=null && result.length>0){
								for(var i=0; i<result.length; i++){
									if(result[i].item_value!=null && result[i].item_value!=""){
										dataKey.push(result[i].item_value);
										dataValue.push(result[i].stuNum);
									}
								}
								if(dataKey.length>25 && dataKey.length<=35){
									$('.e-charst').css('height', '500px');
								}else if(dataKey.length>35 && dataKey.length<=45){
									$('.e-charst').css('height', '700px');
								}else if(dataKey.length>45 && dataKey.length<=55){
									$('.e-charst').css('height', '900px');
								}else if(dataKey.length>55){
									$('.e-charst').css('height', '4000px');
								}else {
									$('.e-charst').css('height', '390px');
								}
								_this.setCharts(perfect,"学校教育信息完善情况",dataKey,dataValue,"#70ad47");
							}else{
								$(perfect).html('<div class="nodata">暂无数据！！！</div>');
							}
						}
					});
				},
				/**
				**id:绑定的元素id
				** titleName ：标题
				** key  value ： 就是data
				** itemColor：柱状颜色
				**/
				setCharts:function(id,titleName,key,value,itemColor){
					var myChartStu = echarts.init(id);

						option = {
							title: {
						        text: titleName,
						        x:'center',
						        textStyle: {
						        	color:'#777',
						        	fontWeight: 'normal',
						        	fontSize:16
						        },
						        top:15
						    },
						    tooltip : {
						        trigger: 'axis',
						        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						        }
						    },
						    // legend: {
						    // 	show: false,
						    //     data: ['教育信息完善人数']
						    // },
						    grid: {
						        left: '3%',
						        right: '4%',
						        bottom: '3%',
						        containLabel: true
						    },
						    xAxis:  {
						        type: 'value'
						    },
						    yAxis: {
						        type: 'category',
						        axisLabel:{  
			                         interval:0,//横轴信息全部显示  
			                    },
						        data: key
						    },
						    series: [
						        {
						            name: '直接访问',
						            type: 'bar',
						            stack: '总量',
						            label: {
						                normal: {
						                    show: true,
						                    position: 'right'
						                }
						            },
						            itemStyle: {
										normal: {
											color: itemColor
										}
									},
									barWidth : 5,
						            data: value
						        }
						    ]
						};
			    		myChartStu.setOption(option);
				}
			}
			//model.init();
		return model;
	}
})(jQuery)