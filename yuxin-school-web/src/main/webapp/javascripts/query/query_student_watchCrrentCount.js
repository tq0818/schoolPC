$(document).ready(function(){
   // search();
    $(".dayList").delegate(".daysTab ","click",function(){
        $(this).addClass("active").siblings().removeClass("active");
        var dayStr = $(this).val(),
         prevDate = DateUtil.fun_NewDate(parseInt(dayStr)),
         today = DateUtil.dateTostring('yyyy-MM-dd',new Date());
         $("#startTime").val(prevDate);
         $("#endTime").val(today);
         search();
    });
    $(".table").delegate(".max-imum","click",function(e){
        var key = ['11:00:00','11:10:00','11:20:00','11:30:00','11:40:00','11:50:00','12:00:00',
        '12:10:00','12:20:00','12:30:00','12:40:00','12:50:00','13:00:00','13:10:00','13:20:00',
        '13:30:00','13:40:00','13:50:00','14:00:00','14:10:00','14:20:00','14:30:00','14:40:00',
        '14:50:00'],
        values = [820, 932, 901, 934, 1290, 1330,1320,1330,1350,1220,1200,999,990,1000,993,980,
        960,980,965,950,960,955,940,902,900];

        var info = $(e.target).parents("tr").data();

        $.ajax({
            type:"post",
            url:"/query/statistics/queryStudentsWatchInfoTime",
            data:{'roomId':info.room_id},
            success:function(data){
                if(data.list.length>0){
                    key =new Array();
                    values =new Array();
                    for(var n = data.list.length-1 ; n>=0 ; n--){
                        key.push(data.list[n].watch_date);
                        values.push(data.list[n].totalusernum);
                    }
                    $(".max-imumbox1").show();
                    $(".max-imumbox").popup("show").css("top", "10%");
                     model.maxImum(key,values);
                }else{
                    $.msg("暂无数据");
                    return;
                }
            }
        })
    });
    $(".table").delegate(".learning-style","click",function(e){
        $(".learning-stylebox1").show();
        $(".learning-stylebox").popup("show").css("top", "10%");
        var info = $(e.target).parents("tr").data();
        var pc_percent = (parseInt(info.pc) / parseInt(info.times)).toFixed(2) *100,
            nopc_percent = (parseInt(info.no_pc) / parseInt(info.times)).toFixed(2) *100;


        model.learningStyle([parseInt(info.pc),parseInt(info.no_pc)],[[100-pc_percent,pc_percent],[100-nopc_percent,nopc_percent]]);
        
    });
    $(".canclekuang").on('click',function(){
        $(".max-imumbox1,.learning-stylebox1").hide();
    })
});


function  init() {
        $("#secondItemCode").change(function () {
            var secondItemCode = $("#secondItemCode").val();
            if (secondItemCode == null || secondItemCode == "") {
                    $("#itemThirdCode").html('<option value="">请选择学科</option>');
                $("#itemThirdCode").change();
            } else {
                $.ajax({
                    url:  "/query/statistics/findItemByPid",
                    type: "post",
                    data:{'itemCode':secondItemCode},
                    success: function (data) {
                            $("#itemThirdCode").html('<option value="">请选择学科</option>');
                        var options = '';
                        $.each(data, function (i, j) {
                                options += '<option value="' + j.itemCode + '">' + j.itemName + '</option>';

                        });
                        $("#itemThirdCode").append(options);
                        $("#itemThirdCode").change();
                    }
                });
            }
        });





        $("#itemThirdCode").change(function () {
            var itemThirdCode = $("#itemThirdCode").val();
            if(itemThirdCode==""){
                $("#class").html('<option value="">请选择课程模块</option>');
                $("#class").change();
                return;
            }
            $.ajax({
                url: rootPath + "/commodity/findCommodityByItems",
                type: "post",
                data: {'itemSecondCode':$("#secondItemCode").val(),'itemThirdCode': itemThirdCode},
                success: function (data) {
                    $("#class").html('<option value="">请选择课程模块</option>');
                    var options = '';
                    $.each(data, function (i, j) {
                        options += '<option value="' + j.id + '">' + j.name + '</option>';
                    });
                    $("#class").append(options);
                    $("#class").change();

                }
            });
        });

        $("#class").change(function () {
            var id = $(this).find(":selected").attr("value");
            if(id==""){
                $("#lesson").html('<option value="">全部</option>');
                return;
            }
            $.ajax({
                url: rootPath + "/classModuleLesson/findLessonByCommodityId",
                type: "post",
                data: {'id': id},
                success: function (data) {
                    $("#lesson").html('<option value="">全部</option>');
                    var options = '';
                    $.each(data, function (i, j) {
                        options += '<option value="' + j.id + '">' + j.lessonName + '</option>';
                    });
                    $("#lesson").append(options);
                }
            });
        });
        //加载入学年份
        $(".exportExcleStudent").on(
            'click',
            function () {
                if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                    $.msg("没有数据可以导出");
                } else {
                    $("#searchForm").attr("action",
                        rootPath + "/query/exportStudentsWatchInfoCountCurrent")
                        .submit();
                }

            });
    }
function search(page,sort){
            var $this = this;
            var data = {};
            if(sort && JSON.stringify(sort) != "{}"){
                data =$.extend(data,sort);
                data.orderBy  = data.fieldName+" "+data.sortType;
            }else{
                data.orderBy = "name";
            }
            data.startDate = $("#startDate").val();
            data.endDate=$("#endDate").val();
            if( $("#secondItemCode").val()=="" ||  $("#secondItemCode").val()==null){
                $.msg("请选择学段")
                return;
            }
            if( $("#itemThirdCode").val()=="" ||  $("#itemThirdCode").val()==null){
                $.msg("请选择学科")
                return;
            }
            if( $("#class").val()=="" ||  $("#class").val()==null){
                $.msg("请选择课程模块")
                return;
            }
            data.secondItemCode = $("#secondItemCode").val();
            data.itemThirdCode = $("#itemThirdCode").val();
            data.comId = $("#class").val();

            data.lesson =$("#lesson").val();
            data.startDate = $("#startTime").val()+" 00:00:00";//
            data.endDate=$("#endTime").val()+" 23:59:59";
            data.page = page ? page : 1;

            if ($("#endTime").val() != "") {
                if (new Date($("#endDate").val()) < new Date($("#startDate").val())) {
                    $.msg("时间范围不正确");
                    return;
                }

            }



            $.ajax({
                url: rootPath + "/query/statistics/queryStudentsWatchInfoCountCurrent",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (result) {
                    var  jsonData  =  result.pageFinder;
                    $(".user-list")
                        .find(".listData").remove();
                    if (jsonData.data.length == 0) {
                            $(".user-list")
                                .find("table")
                                .append(
                                    '<tr class="listData"><td colspan="5">没有查找到数据</td></tr>');

                    }
                    $.each(jsonData.data,function (i, stu) {
                        var str = "移动端:"+stu.no_pc+";非移动端:"+stu.pc;
                        var $tr = $('<tr class="listData">'
                            + '<td>'
                            + stu.name
                            + '</td>'
                            + '<td>'
                            + stu.lesson_name
                            + '</td>'
                            + '<td>'
                            + stu.times
                            + '</td>'
                            + '<td>'
                            + stu.max_concurrent
                            +'<i class="icon iconfont max-imum">&#xe62a;</i>'
                            + '</td><td>'
                            +str
                            +'<i class="icon iconfont learning-style">&#xe62a;</i>'
                            +'</td>'
                            + '</tr>');

                        $tr.data(stu);

                        $(".user-list")
                            .find("table")
                            .append($tr);
                    });
                    $("#rowCount").remove();
                    $("#pageNo").remove();
                    $(".user-list").after('<input type="hidden" id="pageNo" value="'+jsonData.pageNo+'"/>');

                    if (jsonData.rowCount >$("#selectCounts").val()) {
                        $(".pagination").pagination(jsonData.rowCount,
                            {
                                next_text: "下一页",
                                prev_text: "上一页",
                                current_page: jsonData.pageNo - 1,
                                link_to: "javascript:void(0)",
                                num_display_entries: 8,
                                items_per_page: jsonData.pageSize,
                                num_edge_entries: 1,
                                callback: function (page, jq) {
                                    var pageNo = page + 1;
                                    var sortTab = {},
                                        starget = $(".table .sortTarget");
                                    //如果点击页码，获取是否之前点过排序。
                                    if(starget.length>0){
                                        sortTab = {
                                            "fieldName":$(".table .sortTarget").attr("fieldName"),
                                            "sortType":$(".table .sortTarget").attr("sort")
                                        };
                                    }
                                    $this.search(pageNo,sortTab);
                                }
                            });
                        $(".pagination").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');

                        $("#selectCount").val($("#selectCounts").val());
//                            $("#selectCount").css("margin-bottom","").css("margin-bottom","-78px");
                    } else {
                        $(".pagination").html('');
//                            $("#selectCount").css("margin-bottom","").css("margin-bottom","-30px");
                    }
            },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
        });
    }
var model = {
    maxImum:function(key,values){
        var myChartStu = echarts.init(document.getElementById("maxImum"));
            option = {
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    show:false,
                    data:['最大并发']
                },
                toolbox: {
                    show : true,
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : key
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    
                    {
                        name:'最大并发',
                        type:'line',
                        stack: '总量',
                        itemStyle: {
                            normal: {
                                color:'#4eced0',
                                areaStyle:
                                 {
                                     color:'rgba(78, 206, 208, 0.4)',
                                     type: 'default'
                                 }
                                }
                            },
                        data:values
                    }
                ]
            };

            myChartStu.setOption(option);
    },
    learningStyle:function (num,values) {
        var myChartStu2 = echarts.init(document.getElementById("learningStyle"));


        option2 = {
            legend: {
                show:false,
                data:['学习方式详细']
            },
            toolbox: {
                show : true,
            },
            series : [
                seriesData(num[1],values[1],'移动端',"#5ab1ef",['28%', '40%']),seriesData(num[0],values[0],'非移动端',"#b6a2de",['68%', '40%'])
               
            ]
        };
        myChartStu2.setOption(option2);
        function seriesData(num,values,name,color,address){
            var sData = {
                    type : 'pie',
                    center : address,
                    radius : [45, 55],
                    itemStyle : {
                        normal : {
                            label : {
                                formatter : function (params){
                                    return num +'\n'+(100 - params.value) + '%'
                                },
                              
                                textStyle: {
                                    baseline : 'top'
                                }
                            }
                        },
                    },
                    data : [
                        {
                            name:'other', 
                            value:values[0],
                            itemStyle :
                             {
                                normal : {
                                    color: '#ccc',
                                    label : {
                                        show : true,
                                        position : 'center'
                                    },
                                    labelLine : {
                                        show : false
                                    }
                                },
                                emphasis: {
                                    color: 'rgba(0,0,0,0)'
                                }
                            }
                        },
                        {
                            name:name,
                            value:values[1],
                            itemStyle : {
                                normal : {
                                    color: color,
                                    label : {
                                        show : true,
                                        position : 'center',
                                        formatter : '{b}',
                                        textStyle: {
                                            baseline : 'bottom'
                                        }
                                    },
                                    labelLine : {
                                        show : false
                                    }
                                }
                            }
                        }
                    ]
                }
                return sData;
        }
    }
}