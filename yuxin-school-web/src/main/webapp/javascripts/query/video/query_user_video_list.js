(function ($) {

    var student = {
        init: function () {
            $("#eduArea").change(function(){
                var area = $(this).find(":selected").attr("data-id");
                var stepId = $("#eduSchoolStep").find(":selected").attr("data-id");
                var schoolVal = $.trim($("#eduSchool").attr("data-id"));
                if(stepId==null || stepId==""){
                    $("#eduSchool").html('<option value="">请选择所在学校</option>');
                }else{
                    $.ajax({
                        url: rootPath + "/student/getSchoolListByStep",
                        data:{stepId:stepId, parentItemId:area},
                        type: "post",
                        success: function (data) {
                            $("#eduSchool").html('<option value="">请选择所在学校</option>');
                            var options = '';
                            $.each(data,function(i,j){
                                if(schoolVal==j.itemValue){
                                    options+='<option value="'+j.itemCode+'" selected="selected">'+j.itemValue+'</option>';
                                }else{
                                    options+='<option value="'+j.itemCode+'">'+j.itemValue+'</option>';
                                }

                            });
                            $("#eduSchool").append(options);
                        }
                    });
                }
            });
            $("#eduSchoolStep").change(function(){
                var area = $("#eduArea").find(":selected").attr("data-id");
                var stepId = $(this).find(":selected").attr("data-id");
                var schoolVal = $.trim($("#eduSchool").attr("data-id"));
                if(stepId==null || stepId==""){
                    $("#eduSchool").html('<option value="">请选择所在学校</option>');
                }else{
                    $.ajax({
                        url: rootPath + "/student/getSchoolListByStep",
                        data:{stepId:stepId, parentItemId:area},
                        type: "post",
                        success: function (data) {
                            $("#eduSchool").html('<option value="">请选择所在学校</option>');
                            var options = '';
                            $.each(data,function(i,j){
                                if(schoolVal==j.itemValue){
                                    options+='<option value="'+j.itemCode+'" selected="selected">'+j.itemValue+'</option>';
                                }else{
                                    options+='<option value="'+j.itemCode+'">'+j.itemValue+'</option>';
                                }

                            });
                            $("#eduSchool").append(options);
                        }
                    });
                }
            });
            var $this = this;
            // $selectSubMenu('statistics_all_detail');
            // 初始化日期框
            $(".date-picker").datetimepicker({
                format: "yyyy-mm-dd",
                minView: 2,
                autoclose: true,
                language: "zh-CN"
            });
            // 初始化数据
            $this.search();
            // 收索
            $(".searchContents").on('click', function () {
                //清除之前字段排序的值
                $(".table .btn-sort").attr("sort","");
                $(".table .sortTarget").removeClass("sortTarget");
                $this.search();
            });
            // 导出用户
            $(".exportexcle").on(
                'click',
                function () {
                    if ($(".to").val() != "") {
                        if ($(".to").val() < $(".from").val()) {
                            $.msg("时间范围不正确");
                            return;
                        }
                    }
                    if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                        $.msg("没有数据可以导出");
                    } else {
                        $("#searchForm").attr("action",
                            rootPath + "/query/exportUserVideoExcle")
                            .submit();
                    }

                });
            //全选 取消全选
            $(".checkboxAll").on('change', function () {
                if ($(this).prop("checked")) {
                    $("#tableList").find(".signUpMany").prop("checked", true);
                } else {
                    $("#tableList").find(".signUpMany").prop("checked", false);
                }
            });
            $("#caddress").cityselect({
                url:rootPath + "/javascripts/company/city.min.js",
                prov:"", //省份
                city:"",     //市
                nodata:"none", //当子集无数据时，隐藏select
                required: false
            });

        },
        searchCount: function(){
            $("#selectCounts").val($("#selectCount").val());
            student.search();
        },
        search: function (page,sortdata) {
            var $this = this;
            var data = {};
            if(sortdata && JSON.stringify(sortdata) != "{}"){
                data =$.extend(data,sortdata);
            }
            data.startTime = $(".from").val();
            data.endTime = $(".to").val();
            data.eduArea=$("#eduArea").val();
            data.eduSchool=$("#eduSchool").val();
            data.eduSchoolStep=$("#eduSchoolStep").val();
            data.page = page ? page : 1;
            data.pageSize=$("#selectCounts").val() || 10;
            data.proxyOrgName = $('#proxyOrgName').val();
            data.eduYear = $('#eduYear').val();
            data.eduClass = $('#eduClass').val();
            data.username = $("#username").val();

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
            })
            $(".user-list").find("table").find("tr:gt(0)").remove();
            $(".checkboxAll").prop("checked", false);
            //代理机构权限
            var proxyOrgRole = $("#proxyOrgRole").val();
            var userorg_roleopenflag = $("#userorg_roleopenflag").val();
            $.ajax({
                url: rootPath + "/query/queryUserVideoList",
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
                                    '<tr><td colspan="15">没有查找到数据</td></tr>');
                        }else{
                            $(".user-list")
                                .find("table")
                                .append(
                                    '<tr><td colspan="14">没有查找到数据</td></tr>');
                        }
                    }
                    $.each(jsonData.data,function (i, videoCourse) {
                        $(".user-list")
                            .find("table")
                            .append(
                                '<tr data-buy="'+(videoCourse.paymaterCount>0)+'">'
                                + '<td>'
                                + (videoCourse.courseName ? videoCourse.courseName
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.courseStepName ? videoCourse.courseStepName
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.subjectName ? videoCourse.subjectName
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.schoolName ? videoCourse.schoolName
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.username ? videoCourse.username
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.name ? videoCourse.name
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.yearName ? videoCourse.yearName + "级"
                                    : "")
                                + '</td>'
                                + '<td>'
                                + (videoCourse.className ? videoCourse.className + "班"
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
                                    var sortTab = {},
                                        starget = $(".table .sortTarget");
                                    //如果点击页码，获取是否之前点过排序。
                                    if(starget.length>0){
                                        sortTab = {
                                            "fieldName":$(".table .sortTarget").attr("fieldName"),
                                            "sortType":$(".table .sortTarget").attr("sort")
                                        }
                                    }
                                    $this.search(pageNo,sortTab);
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
        checkMaxSignUpNum: function(ele){
            var flag=true;
            $.ajax({
                type: "post",
                url:  rootPath+"/company/queryCompany",
                async: false,
                success: function(result){
                    if(result){
                        var cnt=0;
                        $("#tableList").find("tr").find("input:checkbox:checked").each(function(){
                            if($(this).parents("tr").attr("data-buy")!="true"){
                                cnt++;
                            }
                        })
                        if(ele && ele.parents("tr").attr("data-buy")!="true"){
                            cnt=1;
                        }
                        if((parseInt($("#rowCount").val())+cnt)>$("#maxCount").val()){
                            $.msg("报名人数已超过最大服务数，请升级版本或续费");
                            flag=false;
                        }
                    }
                }
            });
            return flag;
        }
    }

    $(document).ready(function () {
        student.init();
        $(".ico").css("color", "red");
    })
    window.student = student;
})(jQuery)
