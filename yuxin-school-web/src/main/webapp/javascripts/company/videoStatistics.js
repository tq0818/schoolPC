$(function () {

    $(document).ready(function (e) {
        $("#FDetail").click(function () {
            loadFlow(null, null);
        });

        //搜索点击
        $(".selectInfo").click(function () {
            var start = $("#start").val();
            var end = $("#end").val();
            if (new Date(end) < new Date(start)) {
                $('<div class="c-fa">' + "结束日期不能小于开始日期" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                    $(this).remove();
                });
                return;
            }
            loadFlow(start, end);
        });
        /* 选择日期 */
        laydate({
            elem: '#start',
            format: 'YYYY-MM-DD hh:mm:ss',
            /* min: laydate.now(), //设定最小日期为当前日期 */
            max: laydate.now(), // 最大日期
            istime: true,
            istoday: false,
            choose: function (datas) {
                end.min = datas; // 开始日选好后，重置结束日的最小日期
                end.start = datas // 将结束日的初始值设定为开始日

            }
        });
        laydate({
            elem: '#end',
            format: 'YYYY-MM-DD hh:mm:ss',
            max: '2099-06-16 23:59:59',
            istime: true,
            istoday: false,
            choose: function (datas) {
                start.max = datas; // 结束日选好后，重置开始日的最大日期
            }
        });
        /* 选择日期 end */
        // 切换
        $('.tabs').on('click', 'a.btn', function () {
            var active = $(this).hasClass('active'),
                index = $(this).index(),
                obj = $('.tabs-content').find('.p-1');

            if (!active) {
                $(this).addClass('btn-success').siblings('a.btn').removeClass('btn-success').addClass('btn-default');
                obj.eq(index).fadeIn(200).siblings('.p-1').fadeOut(200);
            }
        });

        $("#pageTwo").hide();
        //end document ready
    });


});

function loadFlow(startTime, endTime) {
    var data = {
        "startTime": startTime,
        "endTime": endTime,
        "searchType": $("#searchType").val()
    };

    $.ajax({
        url: rootPath + "/companyMemberService/toVsAjaxInfo",
        type: "post",
        data: data,
        beforeSend: function (XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
        success: function (data) {
            var time = new Array();
            var datas = new Array();
            $.each(data.flow, function (index, item) {
                time[index] = item.useDate;
                datas[index] = item.videoTotalFlow;
            });
            var myChartFlowDetail = echarts.init(document.getElementById('lineDomFlowDetail'));
            optionFlowDetail = {
                title: {
                    text: "使用流量详情(单位：GB；显示：默认显示最近15天)",
                    x: "center"
                },
                tooltip: {
                    trigger: "axis",
                    formatter: "{a} <br/>{b} : {c}GB"
                },

                xAxis: [{
                    type: "category",
                    name: "x",
                    splitLine: {
                        show: false
                    },
                    boundaryGap: false,
                    data: time
                }],
                yAxis: [{
                    type: "value",
                    name: "y"
                }],
                toolbox: {
                    show: true,
                    feature: {
                        restore: {
                            show: true
                        }
                    }
                },
                series: [{
                    name: "已使用",
                    type: "line",
                    data: datas

                }]
            };

            myChartFlowDetail.setOption(optionFlowDetail);
        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    });
}
var haveSpace = 0;
var giveSpace = $("#giveSpace").val();
if (!giveSpace) {
    giveSpace = 0;
}
if ($("#haveSpace").val()) {
    haveSpace = parseInt($("#haveSpace").val()) + parseInt(giveSpace);
} else {
    haveSpace = parseInt($("#giveSpace").val());
}

var videoUsedSpace = $("#videoUsedSpace").val();
var resourceUsedSpace = $("#resourceUsedSpace").val();

var kb = 1024;
var mb = 1024 * kb;
var gb = 1024 * mb;
var usedSpaceGb = (resourceUsedSpace / gb);
var overSpace = (haveSpace - usedSpaceGb - videoUsedSpace).toFixed(3);
if(overSpace < 0){
	overSpace = 0;
}
var myChartSpace = echarts.init(document.getElementById('pieDomSpace'));
optionSpace = {
    title: {
        text: '当前空间使用情况(单位：GB)',
        x: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}GB ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: ['视频使用空间', '资源使用空间', '剩余空间']
    },
    toolbox: {
        show: true,
        feature: {
            restore: {
                show: true
            }
        }
    },
    series: [{
        name: '使用情况',
        type: 'pie',
        radius: '55%',
        center: ['50%', '60%'],
        data: [{
            value: videoUsedSpace,
            name: '视频使用空间'
        }, {
            value: usedSpaceGb.toFixed(3),
            name: '资源使用空间'
        }, {
            value: overSpace,
            name: '剩余空间'
        }]
    }]
};

myChartSpace.setOption(optionSpace);

var haveFlow = 0;
var giveFlow = $("#giveFlow").val();
if (!giveFlow) {
    giveFlow = 0;
}
if ($("#haveFlow").val()) {
    haveFlow = parseInt($("#haveFlow").val()) + parseInt(giveFlow);
} else {
    haveFlow = parseInt($("#giveFlow").val());
}
var videoUsedFlow = $("#videoUsedFlow").val();
var resourceUsedFlow = $("#ResourceUsedFlow").val();
var kb = 1024;
var mb = 1024 * kb;
var gb = 1024 * mb;

var userFlowGb = (resourceUsedFlow / gb);
var overFlow = (haveFlow - videoUsedFlow - userFlowGb).toFixed(3);
if(overFlow < 0){
	overFlow = 0;
}
var myChartFlow = echarts.init(document.getElementById('pieDomFlow'));
optionFlow = {
    title: {
        text: '当前流量使用情况(单位：GB)',
        x: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}GB ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: ['视频使用流量', '资源使用流量', '剩余流量']
    },
    toolbox: {
        show: true,
        feature: {
            restore: {
                show: true
            }
        }
    },
    series: [{
        name: '使用情况',
        type: 'pie',
        radius: '55%',
        center: ['50%', '60%'],
        data: [{
            value: videoUsedFlow,
            name: '视频使用流量'
        }, {
            value: userFlowGb.toFixed(3),
            name: '资源已使用流量'
        }, {
            value: overFlow,
            name: '剩余流量'
        }]
    }]
};

myChartFlow.setOption(optionFlow);