$(document).ready(function(){
    search();
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
                }
            });
        });

        $("#class").change(function () {
            var id = $(this).find(":selected").attr("value");
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
                    $("#searchForm").attr("action",
                        rootPath + "/query/exportUserWatchExcle")
                        .submit();
                }

            });
    }
        function search(page,sort){
            var $this = this;
            var data = {};
            if(sort){
                data =$.extend(data,sort);
                data.orderBy  = data.fileName+" "+data.sortType;
            }

            data.eduArea = $("#eduArea").val();
            data.eduSchool=$("#eduSchool").val();
            data.eduStep = $("#eduStep").val();
            data.eduYear = $("#eduYear").val();
            data.eduClass = $("#eduClass").val();
            data.itemThirdCode = $("#subject").val();
            data.commodityId = $("#class").val();//
            data.lessonId = $("#lesson").val();//
            data.startTime = $("#startTime").val();//
            data.endTime=$("#endTime").val();
            data.schoolType=$("#schoolType").val();
            data.page = page ? page : 1;
            data.userNameOrMobile=$("#userNameOrMobile").val();
            $.ajax({
                url: rootPath + "/query/statistics/totalPayMasterCount",
                data: data,
                type: 'post',
                success: function (jsonData) {
                    $("#total").html(jsonData);
                }
            });
            $.ajax({
                url: rootPath + "/query/statistics/queryStudentsWatchInfoList",
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

                        $(".user-list")
                            .find("table")
                            .append(
                                '<tr class="listData">'
                                // + '<td>'
                                // + '<input type="checkbox" class="signUpMany" uName="'+(stu.username?stu.username:"")+'" value="' + (stu.mobile?stu.mobile:"") + '">'
                                // + '</td>'
                                // + '<td>'
                                // + (stu.mobile ? stu.mobile
                                //     : "")
                                // + '</td>'
                                // + '<td>'
                                // + (stu.username ? stu.username
                                //     : "")
                                // + '</td>'
                                + '<td>'
                                + stu.className
                                + '</td>'
                                + '<td>'
                                + stu.lessonName
                                + '</td>'
                                + '<td>'
                                + stu.userName
                                + '</td>'
                                + '<td>'
                                + stu.studentName
                                + '</td>'
                                + str
                                + '<td>'
                                + stu.times
                                + '</td>'
                                + '<td >'
                                + stu.studyTime
                                + '</td>'

                                // + '<td class="slink">'
                                // + '<a class="showSignUp" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">报名</a>|'
                                // + '<a class="studentDetail" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">详情</a>|'
                                // + '<a class="more" href="javascript:void(0);">更多</a>'
                                // + '<ul class="none box">'
                                // + ' <li><a class="updateStudentMsg" stuId="' + stu.id + '" href="javascript:void(0);">修改信息</a></li>'
                                // +(($("#isDelete").val()==1)?((stu.paymaterCount > 0)?' <li><a class="delStudent" stuId="'+stu.id+'" href="javascript:void(0);">取消报名</a></li>':""):"")
                                // + (stu.userId ? (stu.status == 1 ? '<li><a class="updateStatus" userId="' + stu.userId + '" status="' + stu.status + '" href="javascript:void(0);">禁用用户</a></li>'
                                //     : '<li><a class="updateStatus" userId="' + stu.userId + '" status="' + stu.status + '" href="javascript:void(0);">启用用户</a></li>' ) : '')
                                // + (stu.status == 1 ? '<li><a class="changePwd" userId="' + stu.userId + '" href="javascript:void(0);">修改密码</a></li>' : '')
                                // +(stu.status == 1 && stu.paymaterCount > 0 ? '<li><a class="exportStudyRecord" stuId="'+stu.id+'" href="'+rootPath+'/student_detail/openStdentAllCl?stuId='+stu.id+'" target="_blank">学习记录</a></li>' : '')
                                // +(stu.status == 1 && stu.paymaterCount > 0 ? '<li><a class="exportExcleRecord" stuId="'+stu.id+'" href="'+rootPath+'/student_detail/openStdentAllExt?stuId='+stu.id+'" target="_blank">做题记录</a></li>' : '')
                                // + ((stu.paymaterCount > 0 && stu.commodityType!='COMMODITY_PACKAGE') ? '<li><a class="toTransaction" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">异动</a></li>' : '')
                                // + (stu.paymaterCount > 0 ? (stu.ispay == "1" ? '<li><a class="toMessage" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">补费</a></li>' : '' ) : '')
                                // + (stu.paymaterCount > 0 ?
                                //     (stu.agentFlag == "1" ?
                                //         (stu.isAgent == "1" ?
                                //         '<li><a class="showStuMaterial" mobile="' +(stu.mobile?stu.mobile:"")+ '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">报考材料</a></li>'
                                //             : '')
                                //         : '')
                                //     : '')
                                // + '</ul></td>'
                                + '</tr>');
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
                                    $this.search(pageNo);
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