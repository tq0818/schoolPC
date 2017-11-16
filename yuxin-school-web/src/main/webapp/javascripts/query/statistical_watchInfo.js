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
					var perfect = document.getElementById('watch_info_index');
					var dataKey = new Array(),dataValue = new Array();
					$.ajax({
						url:rootPath+'/query/statistics/watchInfoIndex',
						dataType:'json',
						data:{'startDate':$("#startDate").val()+" 00:00:00",'endDate':$("#endDate").val()+" 23:59:59",'eduStep':$("#eduStep").val()},
						type:'post',
						success:function(result){
                            if(result!=null && result.length>0){
                                for(var i=0; i<result.length; i++){
                                    if(result[i].item_value!=null && result[i].item_value!=""){
                                        dataKey.push(result[i].item_value);
                                        dataValue.push(result[i].times);
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
                                _this.setCharts(perfect,"",dataKey,dataValue,"#70ad47");
                            }else{
                                $(perfect).html('<div class="nodata">暂无数据！！！</div>');
                            }
						}
					});
				},
				/**
				**学校信息完善人数
		    	**/
				queryPerfect2 : function(eduArea, eduStep){
                    var _this = this;
                    var perfect = document.getElementById('watch_info_all');
                    var dataKey = new Array(),dataValue = new Array();
                    $.ajax({
                        url:rootPath+'/query/statistics/watchInfoAll',
                        dataType:'json',
                        data:{'startDate':$("#startDate").val()+" 00:00:00",'endDate':$("#endDate").val()+" 23:59:59",'eduStep':$("#eduStep").val()},
                        type:'post',
                        success:function(result){
                            if(result!=null && result.length>0){
                                for(var i=0; i<result.length; i++){
                                    if(result[i].item_value!=null && result[i].item_value!=""){
                                        dataKey.push(result[i].item_value);
                                        dataValue.push(result[i].times);
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
                                _this.setCharts(perfect,"",dataKey,dataValue,"#70ad47");
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
						        	fontSize:14
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