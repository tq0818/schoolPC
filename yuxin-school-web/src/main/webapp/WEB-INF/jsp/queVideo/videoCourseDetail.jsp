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
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_statistics.jsp"></jsp:include>
<div class="u-wrap query overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>
    <div class="right-side set-system gg-biaoge">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">学校教育信息完善情况</h2>
                <span class="line"></span>
            </div>
        </div>
        <div class="content-right">
            <div class="detail-con">
            <p class="screen-info">
                <a href="<%=rootPath%>/query/statistics/videoCourseIndex" class="btn" >概况</a>
                <a href="<%=rootPath%>/query/statistics/videoCourseDetail" class="btn active">详情</a>
            </p>
            <span>学科</span>
            <select name="eduSubject" id="eduSubject" onchange="selClassOrModule()">
                <option value="">请选择学科</option>
                <c:forEach items="${subjectItem}" var="item" >
                    <option value="${item.itemCode}" data-id="${item.id}" >${item.itemName}</option>
                </c:forEach>
            </select>
            <span>课程</span>
            <select name="eduStep" id="eduStep" onchange="selClassOrModule()">
                <option value="">请选择学段</option>
                <c:forEach items="${stepItem}" var="item" >
                    <option value="${item.itemCode}" data-id="${item.id}" >${item.itemName}</option>
                </c:forEach>
            </select>
            <select name="classType" id="classType">
                <option value="">请选择课程名称</option>
            </select>
            <span>课程名</span>
            <input type="text" id="className" name="className" placeholder="请输入课程名"/>
        </div>
        <div style="margin-top: 10px;">
            <span class="date" style="margin-left: 0;">
                <i class="text">日期</i>
                <span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em>到</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>
            </span>
            <p class="screen-info btn-center">
                <button class="btns-default" id="searchData">查询</button>
                <button class="btns-default" id="exportData">导出数据</button>
            </p>
            <div class="course-info">
                <span class="name"></span>
                <span class="play"></span>
                <div class="device">
                    <span class="ico ico-pc"><i></i></span>
                    <span class="ico ico-yd"><i></i></span>
                </div>
            </div>
            <div class="statistics-con" >

                <div class="btn-grouplist">
                    <a href="" class="btn active pull-left">观看热点</a>
                    <!-- ==================终端 选择pc和移动 新加=================-->
                    <div class="btn_group2 pull-right">
                        <span class="pull-left">终端：</span>
                        <div class="pull-left" content="viewsCount">
                            <a name="terminal" v="0" t="all" class="active" href="javascript:void(0);">全部</a>
                            <a name="terminal" v="1" t="pc" href="javascript:void(0);">PC</a>
                            <a name="terminal" v="2" t="mobile" href="javascript:void(0);">移动</a>
                        </div>
                    </div>
                </div>
                <div class="demand-count viewsCount" axisPointer="line" id="viewsCount" style="width:100%;height: 380px;"></div>

                <div class="btn-grouplist"><a href="" class="btn active  pull-left">观看比例</a>
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
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
    $selectSubMenu('statistics_org_detail');
    // 初始化日期框
    $(".date-picker").datetimepicker({
        format: "yyyy-mm-dd",
        minView: 2,
        autoclose: true,
        language: "zh-CN"
    });

    function searchVideoCourseDetail(startTime, endTime){
        $.ajax({
            url: rootPath + "/query/statistics/queryVideoCourseDetail",
            data:{classId:$("#classType").val(),startTime:startTime, endTime:endTime,className:$("#className").val()},
            success:function(result){
                var videoDetail = result.videoDetail ? result.videoDetail:null;
                $(".course-info").find(".name").html("");
                $(".course-info").find(".play").empty();
                $(".ico-pc").empty().append("<i></i>");
                $(".ico-yd").empty().append("<i></i>");
                if(videoDetail!=null){
                    $(".course-info").find(".name").html(videoDetail.name+"（"+startTime+"至"+endTime+"）");
                    var totleStudyLength = videoDetail.totleStudyLength ? videoDetail.totleStudyLength:"";
                    var studyRate = videoDetail.studyRate ? videoDetail.studyRate:"";
                    var totleStudy = videoDetail.totleStudy ? videoDetail.totleStudy:"";
                    $(".course-info").find(".play").append('<i>播放总时长<br/>'+totleStudyLength+'</i>');
                    $(".course-info").find(".play").append('<i>播完率<br/>'+studyRate+'</i>');
                    $(".course-info").find(".play").append('<i>播放量<br/>'+totleStudy+'</i>');

                    var pcNum = result.pcNum ? result.pcNum : 0;
                    var pcRate = result.pcRate ? result.pcRate : 0;
                    var otherNum = result.otherNum ? result.otherNum : 0;
                    var otherRate = result.otherRate ? result.otherRate : 0;
                    $(".ico-pc").empty().append("<i>pc</i><i>"+pcNum+"</i><i>"+pcRate+"%</i>");
                    $(".ico-yd").empty().append("<i>移动</i><i>"+otherNum+"</i><i>"+otherRate+"%</i>");
                }
            }
        });
    }

    function searchVideoCourseDetail2(startTime, endTime){
        var dataKey = new Array(),dataValue = new Array();
        if(($("#classType").val()!=null && $("#classType").val()!="") || ($("#className").val()!=null && $("#className").val()!="")) {
            $.ajax({
                url: rootPath + "/query/statistics/queryVideoCourseHourly",
                data:{classId:$("#classType").val(),startTime:startTime, endTime:endTime,className:$("#className").val()},
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
                    var chart2 = {
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

                    $(document).statistical().setCharts(chart2);
                }
            });
        }
    }


    function searchVideoCourseDetail3(startTime, endTime){
        var dataKey = new Array(),dataValue = new Array();
        $.ajax({
            url: rootPath + "/query/statistics/queryVideoCourseDetail",
            data:{classId:$("#classType").val(),startTime:startTime, endTime:endTime,className:$("#className").val()},
            success:function(result){
                result = result.schoolViewList ? result.schoolViewList:null;
                if(result!=null && result.length>0){
                    for(var i=0; i<result.length; i++){
                        if(result[i].schoolCode!=null && result[i].schoolCode!=""){
                            dataKey.push(result[i].schoolName);
                            dataValue.push(result[i].viewNum);
                        }
                    }
                }
                var chart3 = {
                    "id":document.getElementById("viewsScale"),
                    "titleText":' ',
                    "seriesData":[550,503,489,480,429,350,
                        360,319,288,160],
                    "seriesName":"观看比例"

                };
                chart3.legend = {
                    show:false,
                    data:['观看比例']
                };
                chart3.xAxis = [
                    {
                        name: '已观看比例',
                        splitNumber:5,
                        type : 'category',
                        data : ['0-10%', '20%-30%', '30%-40%', '40%-50%', '50%-60%','60%-70%','70%-80%','80%-90%','90%-100%'],
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


    searchVideoCourseDetail2($(".from").val(), $(".to").val());//观看热点
    searchVideoCourseDetail3($(".from").val(), $(".to").val());//观看比例

    $(document).statistical().changeType({
        callback:function(e){
            var terminal = $(e).attr("t"),//设备类型
                    $viewId = document.getElementById($(e).parent().attr("content")),//容器
                    //更新数据
                    option =$($viewId).data();
            option.id = $viewId;
            option.tooltip.axisPointer = $($viewId).attr("axisPointer");
            option.series[0].data = [5300,810,800,720,668,590,550,503,489,480,429,350,360,319,288,260,210,90,90,88,81,72,66,60];
            $(document).statistical().setCharts(option);


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
        searchVideoCourseDetail2($(".from").val(), $(".to").val());//观看热点
        searchVideoCourseDetail3($(".from").val(), $(".to").val());//观看比列
    });

    function selClassOrModule(){
        var twoItem = $("#eduStep").val();
        var threeItem = $("#eduSubject").val();
        $.ajax({
            url:rootPath + "/classModule/selClassType",
            type:"post",
            data:{"itemSecondCode":twoItem,"itemThirdCode":threeItem},
            // data:{"itemOneId":oneItem,"itemSecondId":twoItem,"itemThridCode":threeItem},
            dataType:"json",
            beforeSend:function(XMLHttpRequest){
                $(".loading").show();
                $(".loading-bg").show();
            },
            success:function(data){
                $("#classType").empty();
                $("#classType").append('<option value="">请选择课程名称</option>');
                $.each(data.types,function(index,item){
                    $("#classType").append("<option value='" + item.id + "'>" + item.name + "</option>");
                });
            },
            complete:function(XMLHttpRequest,textStatus){
                $(".loading").hide();
                $(".loading-bg").hide();
            }
        });
    }
</script>
</body>
</html>