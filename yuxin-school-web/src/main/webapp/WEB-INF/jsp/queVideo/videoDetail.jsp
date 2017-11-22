<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>点播统计-详情</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query/statistics.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/classedit.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/classType/commonTitle.jsp" %>
<div class="u-wrap query overflow">
    <%@include file="/WEB-INF/jsp/simpleClasses/commonClass.jsp" %>
    <div class="right-side set-system gg-biaoge">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">点播详情统计</h2>
                <span class="line"></span>
            </div>
        </div>
        <div class="mainbackground nopadding content-right" style="padding-top:0;">
            <input type="hidden" name="classType" id="classType" value="${ct.id}"/>
            <input type="hidden" name="className" id="className" value="${ct.name}"/>
      <%--      <span class="date" >
                <i class="text">日期</i>
                <span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em>到</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>
            </span>--%>
            <p class="screen-info btn-center">
                <span class="date fl" style="margin-left: 0;">
					<i class="text">日期</i>
					<span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em>到</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>
				</span>
                <button class="btns-default fr" id="exportData">导出数据</button>
                <button class="btns-default fr" id="searchData">查询</button>

            </p>
            <div class="course-info">
                <span class="name">${ct.name}（${startTime}至${endTime}）</span>
                <span class="play">
                    <i>播放总时长<br/>0</i>
                    <i>播完率<br/>0%</i>
                    <i>播放量<br/>0</i>
                </span>
                <div class="device">
                    <span class="ico ico-pc"><i>PC</i><i>0</i><i>0%</i></span>
                    <span class="ico ico-yd"><i>移动</i><i>0</i><i>0%</i></span>
                </div>
            </div>
            <div class="statistics-con" >

                <%--<div class="btn-grouplist">--%>
                    <%--<a href="" class="btn active pull-left">观看热点</a>--%>
                    <%--<!-- ==================终端 选择pc和移动 新加=================-->--%>
                    <%--<div class="btn_group2 pull-right">--%>
                        <%--<span class="pull-left">终端：</span>--%>
                        <%--<div class="pull-left" content="viewsCount">--%>
                            <%--<a name="terminal" v="0" t="all" class="active" href="javascript:void(0);">全部</a>--%>
                            <%--<a name="terminal" v="1" t="pc" href="javascript:void(0);">PC</a>--%>
                            <%--<a name="terminal" v="2" t="mobile" href="javascript:void(0);">移动</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="demand-count viewsCount" axisPointer="line" id="viewsCount" style="width:100%;height: 380px;"></div>--%>

                <div class="btn-grouplist"><span class="btn active  pull-left">观看比例</span>
                    <!-- ==================终端 选择pc和移动 新加=================-->
                    <div class="btn_group2 pull-right">
                        <span class="pull-left">终端：</span>
                        <div class="pull-left" content="viewsScale">
                            <a name="terminal" v="0" t="all" class="active" href="javascript:void(0);">全部</a>
                            <a name="terminal" v="1" t="pc" href="javascript:void(0);">PC</a>
                            <a name="terminal" v="2" t="mobile" href="javascript:void(0);">移动</a>
                        </div>
                    </div>
                </div>
                <div class="demand-count viewsCount" axisPointer="shadow" id="viewsScale" style="width:100%;height: 380px;"></div>
            </div>
    <!-- </div> -->
        </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts-all.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/byecharts.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript">
//    $selectSubMenu('statistics_org_detail');
    $selectMenu("course_class_type");
    $chooseMenu("videoStatistics");


    function searchVideoCourseDetail(startTime, endTime){
        $.ajax({
            url: rootPath + "/query/statistics/queryVideoCourseDetail",
            data:{classId:$("#classType").val(),startTime:startTime, endTime:endTime},
            success:function(result){
                var videoDetail = result.videoDetail ? result.videoDetail:null;
                $(".course-info").find(".name").html($("#className").val());//+"（"+startTime+"至"+endTime+"）");
                var totleStudyLength = (videoDetail&&videoDetail.totleStudyLength) ? videoDetail.totleStudyLength:"0";
                var studyRate = (videoDetail&&videoDetail.studyRate) ? videoDetail.studyRate+"%":"0%";
                var totleStudy = (videoDetail&&videoDetail.totleStudy) ? videoDetail.totleStudy:"0";
                $(".course-info").find(".play").empty().append('<i>播放总时长<br/>'+totleStudyLength+'</i>');
                $(".course-info").find(".play").append('<i>播完率<br/>'+studyRate+'</i>');
                $(".course-info").find(".play").append('<i>播放量<br/>'+totleStudy+'</i>');

                var pcNum = result.pcNum ? result.pcNum : 0;
                var pcRate = result.pcRate ? result.pcRate : 0;
                var otherNum = result.otherNum ? result.otherNum : 0;
                var otherRate = result.otherRate ? result.otherRate : 0;
                $(".ico-pc").empty().append("<i>pc</i><i>"+pcNum+"</i><i>"+pcRate+"%</i>");
                $(".ico-yd").empty().append("<i>移动</i><i>"+otherNum+"</i><i>"+otherRate+"%</i>");
            }
        });
    }

    function searchVideoCourseDetail2(startTime, endTime){
        var dataKey = new Array(),dataValue = new Array();
        if(($("#classType").val()!=null && $("#classType").val()!="")) {
            $.ajax({
                url: rootPath + "/query/statistics/queryVideoCourseHourly",
                data:{classId:$("#classType").val(),startTime:startTime, endTime:endTime},
                success: function (result) {
                    result = result.schoolViewList ? result.schoolViewList : null;
                    if (result != null && result.length > 0) {
                        for (var i = 0; i < result.length; i++) {
                            if (result[i].schoolCode != null && result[i].schoolCode != "") {
                                dataKey.push(result[i].schoolName);
                                dataValue.push(result[i].viewNum);
                            }
                        }
                    }
                    var myChartStu = echarts.init(document.getElementById("viewsCount"));
                    option = {
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            show:false,
                            data:['邮件营销']
                        },
                        toolbox: {
                            show : true
                        },
                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                boundaryGap : false,
                                data: ['00:00:00','00:00:10','00:00:20','00:00:30','00:00:40','00:00:50',
                                    '00:20:00','00:20:10','00:20:20','00:20:30','00:20:40','00:20:50',
                                    '00:30:00','00:30:10','00:30:20','00:30:30','00:30:40','00:30:50',
                                    '00:40:00','00:40:10','00:40:20','00:40:30','00:40:40','00:40:50','00:50:00']
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'邮件营销',
                                type:'line',
                                stack: '总量',
                                itemStyle: {
                                    normal: {
                                        color: "transparent",
                                        lineStyle: {
                                            color:'#4bc5e8'
                                        }

                                    },
                                    emphasis: {
                                        color: "#4bc5e8",
                                        lineStyle: {
                                            color:'red'
                                        }
                                    }
                                },
                                data:[2100,810,800,720,668,590,
                                    550,503,489,480,429,350,
                                    360,319,288,260,210,90,
                                    90,88,81,72,66,60,90
                                ]
                            }
                        ]
                    };
                    myChartStu.setOption(option);
                    /*var chart2 = {
                        "id": document.getElementById("viewsCount"),
                        "titleText": ' ',
                        "tooltip": {
                            "axisPointer": 'line'
                        }
                    };
                    chart2.series = [
                        {
                            name: '观看量',
                            type: 'line',
                            //symbol: 'none',
                            itemStyle: {
                                normal: {
                                    color: "transparent",
                                    lineStyle: {
                                        color: '#4bc5e8'
                                    }

                                },
                                emphasis: {
                                    color: "#4bc5e8",
                                    lineStyle: {
                                        color: 'red'
                                    }
                                }
                            },
                            data: [2100, 810, 800, 720, 668, 590,
                                550, 503, 489, 480, 429, 350, 220, 120
                            ]
                        }
                    ];
                    chart2.xAxis = [
                        {
                            type: 'category',
                            name: '视频时长',
                            data: ['00:00:00', '00:00:10', '00:00:20', '00:00:30', '00:00:40', '00:00:50',
                                '00:10:00', '00:10:10', '00:10:20', '00:10:30', '00:10:40', '00:10:50',
                                '00:20:00', '00:20:10']
                        }
                    ];
                    chart2.yAxis = [{
                        name: '观看量',
                        type: 'value',
                        nameTextStyle: {
                            align: 'right'
                        }
                    }];

                    $(document).statistical().setCharts(chart2);*/
                }
            });
        }
    }


    function searchVideoCourseDetail3(startTime, endTime){
        var dataKey = new Array(),dataValue = new Array();
        $.ajax({
            url: rootPath + "/query/statistics/queryVideoCourseDaily",
            data:{classId:$("#classType").val(),startTime:startTime, endTime:endTime},
            success:function(result){
                result = result.attentions ? result.attentions:null;
                for(var i=0; result && i<result.length; i++){
                    dataKey.push(result[i].section);
                    dataValue.push(result[i].pc+result[i].mobile);
                }
                var chart3 = {
                    "id":document.getElementById("viewsScale"),
                    "titleText":' ',
                    "seriesData":dataValue,
                    "seriesName":"观看比例"

                };
                chart3.legend = {
                    show:false,
                    data:['观看比例']
                };
                chart3.xAxis = [
                    {
                        name: '已观看比例',
                        type : 'category',
                        data : ['0-10%', '10%-20%', '20%-30%', '30%-40%', '40%-50%', '50%-60%','60%-70%','70%-80%','80%-90%','90%-100%'],
                        axisTick: {
                            alignWithLabel: false
                        }

                    }
                ];
                chart3.yAxis = [{
                    type : 'value',
                    name: '观看量',
                    nameTextStyle: {
                        align: "right"
                    }
                }];

                $(document).statistical().setCharts(chart3);
            }
        });
    }

    searchVideoCourseDetail($(".from").val(), $(".to").val());//查询单个视频信息
    searchVideoCourseDetail3($(".from").val(), $(".to").val());//观看比例

    $(document).statistical().changeType({
        callback:function(e){
            var terminal = $(e).attr("t"),//设备类型
            $viewId = document.getElementById($(e).parent().attr("content"));//容器
            if($(e).parent().attr("content") == "viewsCount"){//line

            }else{//bar
                if ($(".to").val() != "") {
                    if ($(".to").val() < $(".from").val()) {
                        $.msg("时间范围不正确");
                        return;
                    }
                }
                var dataKey = new Array(),dataValue = new Array();
                $.ajax({
                    url: rootPath + "/query/statistics/queryVideoCourseDaily",
                    data:{classId:$("#classType").val(),startTime:$(".from").val(), endTime:$(".to").val()},
                    success:function(result){
                        result = result.attentions ? result.attentions:null;
                        for(var i=0; result && i<result.length; i++){
                            dataKey.push(result[i].section);
                            if(terminal == 'pc'){
                                dataValue.push(result[i].pc);
                            }else if(terminal == 'mobile'){
                                dataValue.push(result[i].mobile);
                            }else{
                                dataValue.push(result[i].pc+result[i].mobile);
                            }

                        }
                        //更新数据
                        var option =$($viewId).data();
                        option.id = $viewId;
                        option.tooltip.axisPointer = $($viewId).attr("axisPointer");
                        option.series[0].data = dataValue;
                        $(document).statistical().setCharts(option);
                    }
                });
            }

        }
    });

    $("#searchData").click(function(){
        if ($(".to").val() != "") {
            if ($(".to").val() < $(".from").val()) {
                $.msg("时间范围不正确");
                return;
            }
        }

        searchVideoCourseDetail($(".from").val(), $(".to").val());//查询单个视频信息
//        searchVideoCourseDetail2($(".from").val(), $(".to").val());//观看热点
        searchVideoCourseDetail3($(".from").val(), $(".to").val());//观看比列
    });


    $("#exportData").click(function(){
        if ($(".from").val()!="" && $(".to").val()!="") {
            if ($(".to").val() < $(".from").val()) {
                $.msg("时间范围不正确");
                return;
            }else if(new Date($(".to").val()) - new Date($(".from").val()) > 30*24*60*60*1000){
                $.msg("时间范围不能超过30天");
                return;
            }
        }else{
            $.msg("时间选项必填");
            return;
        }

        window.location.href = rootPath + "/query/exportVideoCourseDetailExcle?startTime="+$(".from").val()+"&endTime="+$(".to").val()+
                "&classTypeId="+$("#classType").val();
    });
</script>
</body>
</html>