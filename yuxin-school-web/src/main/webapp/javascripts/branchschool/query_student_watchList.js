$(document).ready(function(){
    search();
    $(".table").delegate(".amassCount","click",function(e){
       // $(e.target)
        var obj = $(e.target).data();
        var role = $("#role").val();
        $.ajax({
           type:"post",
           url: rootPath + "/query/getStudentWatchInfo",
           data:{'lessonId':obj.lessonId,'userId':obj.userId},
           success:function (result) {
               $(".popupwin-main")
                   .find(".listData").remove();
               $.each(result,function(i,info){
                   var str = '<tr class="listData"><td>'+ obj.lessonName+ '</td>'+'<td>'+ obj.studentName+ '</td>';
                   if(role!="school"){
                       str +='<td>'+ obj.eduSchool+ '</td>';
                   }
                   str +='<td>'
                       + obj.eduStep
                       + '</td>'
                       +'<td>'
                       + obj.studyClass
                       + '</td>'
                       +'<td>'
                       + info.joinTime
                       + '</td>'
                       +'<td>'
                       + info.leaveTime
                       + '</td>'
                       +'<td>'
                       + info.watchTime
                       + '</td>'
                       + '</tr>';
                   $(".popupwin-main")
                       .find("table")
                       .append(str);
               })
               $(".cumulativeClass1").show();
               $(".cumulativeClass").popup("show").css("top", "20%");
           }
        });

    })
    $(".canclekuang").on('click',function(){
        $(".cumulativeClass1").hide();
    })
});
function  init() {
        $("#eduArea").change(function () {
            var area = $(this).find(":selected").attr("data-id")!=null?$(this).find(":selected").attr("data-id"):$("#eduArea").attr("data-id");
            var schoolVal = $.trim($("#eduSchool").attr("data-id"));
            if (area == null || area == "") {
                    $("#eduSchool").html('<option value="">请选择所在学校</option>');
            } else {
                $.ajax({
                    url: rootPath + "/student/getSchoolList/" + area,
                    type: "post",
                    success: function (data) {
                            $("#eduSchool").html('<option value="">请选择所在学校</option>');
                        var options = '';
                        $.each(data, function (i, j) {
                            if (schoolVal == j.itemValue) {
                                options += '<option value="' + j.itemCode + '" selected="selected">' + j.itemValue + '</option>';
                            } else {
                                options += '<option value="' + j.itemCode + '">' + j.itemValue + '</option>';
                            }

                        });
                        if($("#schoolType").val()){
                            $("#schoolType").change();
                        }else{
                            $("#eduSchool").append(options);
                        }
                    }
                });
            }
        });

        if($("#schoolType")){
            $("#schoolType").change(function () {
                var area = $("#eduArea").attr("data-id");
                var schoolVal = $.trim($("#eduSchool").attr("data-id"));
                if (area == null || area == "") {
                } else {
                    $.ajax({
                        url: rootPath + "/student/getSchoolList",
                        data:{"schoolType":$("#schoolType").val(),'area':area},
                        type: "post",
                        success: function (data) {
                            $("#eduSchool").html('<option value="">请选择所在学校</option>');
                            var options = '';
                            $.each(data, function (i, j) {
                                if (schoolVal == j.itemValue) {
                                    options += '<option value="' + j.itemCode + '" selected="selected">' + j.itemValue + '</option>';
                                } else {
                                    options += '<option value="' + j.itemCode + '">' + j.itemValue + '</option>';
                                }

                            });
                            $("#eduSchool").append(options);
                        }
                    });
                }
            });
        }


        var date = new Date();
        var year = date.getFullYear();
        var end = year - 15;
        var yearOption = "";
        while (year >= end) {
            yearOption += '<option value="' + year + '">' + year + '</option>';
            year--;
        }
        $("#eduYear").append(yearOption);


        $("#subject").change(function () {
            var subject = $(this).find(":selected").attr("value");
            if(subject==""){
                $("#class").html('<option value="">请选择课程模块</option>');
                $("#class").change();
                return;
            }
            $.ajax({
                url: rootPath + "/commodity/findCommodityByItems",
                type: "post",
                data: {'itemThirdCode': subject},
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
                $("#lesson").html('<option value="">请选择课次</option>');
                return;
            }
            $.ajax({
                url: rootPath + "/classModuleLesson/findLessonByCommodityId",
                type: "post",
                data: {'id': id},
                success: function (data) {
                    $("#lesson").html('<option value="">请选择课次</option>');
                    var options = '';
                    $.each(data, function (i, j) {
                        options += '<option value="' + j.id + '">' + j.lessonName + '</option>';
                    });
                    $("#lesson").append(options);
                }
            });
        });
        //加载入学年份
        $("#eduArea").change();
        $("#subject").change();
        $("#class").change();
        $(".exportExcleStudent").on(
            'click',
            function () {
                if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                    $.msg("没有数据可以导出");
                } else {
                    $("#searchForm").attr("action",rootPath + "/baseSchool/exportUserWatchExcle").submit();
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
                data.orderBy = "class_name";
            }
            data.eduArea = $("#eduArea").val();
            data.eduSchool=$("#eduSchool").val();
            data.eduStep = $("#eduStep").val();
            data.eduYear = $("#eduYear").val();
            data.eduClass = $("#eduClass").val();
            data.itemThirdCode = $("#subject").val();
            data.commodityId = $("#class").val();//
            data.lessonId = $("#lesson").val();//
            data.startTime = $("#startTime").val()+" 00:00:00";//
            data.endTime=$("#endTime").val()+" 23:59:59";
            data.schoolType=$("#schoolType").val();
            data.page = page ? page : 1;
            data.userNameOrMobile="'%"+$("#userOrMobile").val()+"%'";
            if ($("#endTime").val() != "") {
                if ($("#endTime").val() < $("#startTime").val()) {
                    $.msg("时间范围不正确");
                    return;
                }

            }
            
            $.ajax({
                url: rootPath + "/baseSchool/queryStudentsWatchInfoList",
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
                                    '<tr class="listData"><td colspan="14">没有查找到数据</td></tr>');

                    }
                    $("#watch").html(jsonData.rowCount);
                    $("#total").html(result.total);
                    $.each(jsonData.data,function (i, stu) {
                        var str = null;
                        if($("#role").val()!='school'){
                            str  =" <td>"+ stu.eduSchool+ "</td>"+" <td>"+ stu.eduStep+ "</td>"+" <td>"+ stu.eduYear+ "</td>";
                        }else
                        {
                            str  = " <td>"+stu.studyClass+ "</td>";
                        }
                        var $tr = $('<tr class="listData">'
                            + '<td>'
                            + stu.className
                            + '</td>'
                            + '<td>'
                            + stu.lessonName
                            + '</td>'
                            + '<td>'
                            + (stu.userName!=null?stu.userName:'')
                            + '</td>'
                            + '<td>'
                            + stu.studentName
                            + '</td>'
                            + str
                            + '<td>'
                            + stu.times
                            +'<i class="icon iconfont amassCount">&#xe62a;</i>'
                            + '</td>'
                            + '<td >'
                            + stu.studyTime
                            + '</td>'
                            + '</tr>');
                        $tr.find(".amassCount").data(stu);

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