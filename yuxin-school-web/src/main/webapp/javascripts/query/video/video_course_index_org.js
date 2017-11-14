(function ($) {

    var student = {
        init: function () {
            var $this = this;
            // $selectSubMenu('statistics_all_detail');
            // 初始化数据
            $this.search();
            // 收索
            $(".searchContents").on('click', function () {
                $this.search();
            });
        },
        search: function (page,sortdata) {
            var $this = this;
            var data = {};
            if(sortdata){
                data =$.extend(data,sortdata);
            }
            data.startTime = $(".from").val();
            data.endTime = $(".to").val();
            data.eduArea=$("#eduArea").val();
            data.eduSchool=$("#eduSchool").val();
            data.page = page ? page : 1;
            data.pageSize=$("#selectCounts").val() || 10;

            if ($(".to").val() != "") {
                if ($(".to").val() < $(".from").val()) {
                    $.msg("时间范围不正确");
                    return;
                }

            }
            $.each(data, function (key, value) {
                if (!value) {
                    delete data[key];
                }
            });
            $.ajax({
                url: rootPath + "/query/queryVideoTotleForSchool",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (jsonData) {
                    $("#userNum").html(jsonData.userNum ? jsonData.userNum:0);
                    $("#totleStudyLength").html(jsonData.totleVideo.totleStudyLength ? jsonData.totleVideo.totleStudyLength:0);
                    $("#personNum").html(jsonData.totleVideo.personNum ? jsonData.totleVideo.personNum:0);
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
            $(".user-list").find("table").find("tr:gt(0)").remove();
            //代理机构权限
            var proxyOrgRole = $("#proxyOrgRole").val();
            var userorg_roleopenflag = $("#userorg_roleopenflag").val();
            $.ajax({
                url: rootPath + "/query/queryVideoListForSchool",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (jsonData) {

                    if (jsonData.data.length == 0) {
                        if(userorg_roleopenflag == 1 && proxyOrgRole == 1){
                            $(".user-list")
                                .find("table")
                                .append(
                                    '<tr><td colspan="7">没有查找到数据</td></tr>');
                        }else{
                            $(".user-list")
                                .find("table")
                                .append(
                                    '<tr><td colspan="6">没有查找到数据</td></tr>');
                        }
                    }
                    $.each(jsonData.data,function (i, videoCourse) {
                        $(".user-list")
                            .find("table")
                            .append(
                                '<tr data-buy="'+(videoCourse.paymaterCount>0)+'">'
                                + '<td>'
                                + (videoCourse.stepName ? videoCourse.stepName
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.subjectName ? videoCourse.subjectName
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.totleStudy ? videoCourse.totleStudy
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.totleStudyLength ? videoCourse.totleStudyLength
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.studyRate ? videoCourse.studyRate + "%"
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.viewNum ? videoCourse.viewNum
                                    : "")
                                + '</td>');
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
                        // $(".pagination").find("li:first").before('每页：<select id="selectCount"  onchange="javascript:student.searchCount()">'+
                        //     ' <option value="10">10</option>'+
                        //     ' <option value="20">20</option>'+
                        //     ' <option value="30">30</option>'+
                        //     ' <option value="50">50</option>'+
                        //     ' <option value="100">100</option>'+
                        //     ' </select> 条   ');
                        $("#selectCount").val($("#selectCounts").val());
//                            $("#selectCount").css("margin-bottom","").css("margin-bottom","-78px");
                    } else {
                        $(".pagination").html('');
//                            $("#selectCount").css("margin-bottom","").css("margin-bottom","-30px");
                    }
                    $(".haostatus").each(function (i) {
                        if ($.trim($(this).text()) == "未开通") {
                            $(this).css("color", "red");
                        }
                    });
                    $(".ustatus").each(function (i) {
                        if ($.trim($(this).text()) == "禁用") {
                            $(this).css("color", "red");
                        }
                    });
                    $(".baoming").each(function (i) {
                        if ($.trim($(this).text()) == "未报名") {
                            $(this).css("color", "red");
                        }
                    });
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
            $("#maxCount").remove();
        },
    }

    $(document).ready(function () {
        student.init();
        $(".ico").css("color", "red");
    })
    window.student = student;
})(jQuery)
