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
    <form method="post" id="searchForm">
        <div>
            <span>学科</span>
            <select name="eduSubject" id="eduSubject">
                <option value="">请选择学科</option>
                <c:forEach items="${subjectItem}" var="item" >
                    <option value="${item.itemCode}" data-id="${item.id}" >${item.itemName}</option>
                </c:forEach>
            </select>
            <span>课程</span>
            <select name="eduStep" id="eduStep">
                <option value="">请选择学段</option>
                <c:forEach items="${stepItem}" var="item" >
                    <option value="${item.itemCode}" data-id="${item.id}" >${item.itemName}</option>
                </c:forEach>
            </select>
            <select name="classTypeName" id="classTypeName">
                <option value="">请选择课程名称</option>
                <c:forEach items="${subjectItem}" var="item" >
                    <option value="${item.itemCode}" data-id="${item.id}" >${item.itemName}</option>
                </c:forEach>
            </select>
            <span>课程名</span>
            <input type="text" id="teaName" name="teaName" placeholder="请输入课程名"/>
        </div>
        <div style="margin-top: 10px;">
            <span class="date" style="margin-left: 0;">
				<i class="text">日期</i>
				<span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em>到</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>
			</span>
            <p class="screen-info btn-center">
                <button class="btns-default">查询</button>
                <button class="btns-default">导出数据</button>
            </p>
        </div>
    </form>
    <div class="course-info">
        <span class="name">有理数的混合运算——荣彬.mp4（2017-09-21至2017-09-27）</span>
        <span class="play">
				<i>播放总时长<br/>04:09:00</i>
				<i>播完率<br/>80%</i>
				<i>播放量<br/>505</i>
			</span>
        <div class="device">
				<span class="ico ico-pc">
					<i>pc</i>
					<i>105</i>
					<i>27.92%</i>
				</span>
            <span class="ico ico-yd">
					<i>移动</i>
					<i>105</i>
					<i>27.92%</i>
				</span>
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
</body>
<script type="text/javascript">
    var chart2 = {
        "id":document.getElementById("viewsCount"),
        "titleText":' ',
        "tooltip":{
            "axisPointer":'line'
        }
    };
    chart2.series = [
        {
            name:'观看量',
            type:'line',
            //symbol: 'none',
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
                90,88,81,72,66,60
            ]
        }
    ];
    chart2.xAxis = [
        {
            type: 'category',
            name: '视频时长',
            splitNumber:5,
            data: ['00:00:00','00:00:10','00:00:20','00:00:30','00:00:40','00:00:50',
                '00:20:00','00:20:10','00:20:20','00:20:30','00:20:40','00:20:50',
                '00:30:00','00:30:10','00:30:20','00:30:30','00:30:40','00:30:50',
                '00:40:00','00:40:10','00:40:20','00:40:30','00:40:40','00:40:50']
        }
    ];
    chart2.yAxis = [{
        name:'观看量',
        type: 'value',
        nameTextStyle: {
            align:'right'
        }
    }];


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
    ],
            chart3.yAxis = [{
                type : 'value',
                name: '观看量',
                nameTextStyle: {
                    align: "right"
                }
            }];
    $(document).statistical().setCharts(chart2);
    $(document).statistical().setCharts(chart3);

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
</script>