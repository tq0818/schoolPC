(function($){
    $.fn.statistical = function(callbackfun){
        var model ={
            init: function(){
                var _this = this;
                //this.queryPerfect();
            },
            /**
             **必填id,
             **
             **/
            setCharts:function(obj){
                var myChartStu = echarts.init(obj.id);

                option = {
                    backgroundColor:'#fff',
                    title: {
                        text: obj.titleText|| " " ,
                        y:10,
                        x:'center',
                        textStyle: {
                            color:'#777',
                            fontWeight: 'normal',
                            fontSize:14
                        }
                    },
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend:obj.legend || {
                        show: false,
                        data: [""]
                    },
                    grid: obj.grid || {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis:  obj.xAxis || {
                        type: 'value',

                    },
                    yAxis: obj.yAxis || {
                        type: 'category',
                        axisLabel:{
                            interval:0,//横轴信息全部显示  
                        },
                        data: obj.yAxisData || ['100',"200","300"]
                    },
                    series: obj.seriesList || [
                        {
                            name: obj.seriesName || '直接访问',
                            type: 'bar',
                            stack: '总量',
                            itemStyle: {
                                normal: {
                                    label : {show: true,formatter: obj.seriesFormatter || '{c}'},
                                    color: obj.itemColor || "#4472c4"
                                }
                            },
                            barWidth : obj.barWidth || 20,
                            data: obj.seriesData ||[100,200,300]
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