
    function  init() {
        $("#eduArea").change(function () {
            var area = $(this).find(":selected").attr("data-id");
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
                        $("#eduSchool").append(options);
                    }
                });
            }
        });
        //加载入学年份
        $("#eduArea").change();
        var date = new Date();
        var year = date.getFullYear();
        var end = year-15;
        var yearOption ="";
        while(year>=end){
            yearOption += '<option value="' + year + '">' + year + '</option>';
            year--;
        }
        $("#eduYear").append(yearOption);


        $("#subject").change(function () {
            var subject = $(this).find(":selected").attr("value");
            $.ajax({
                url: rootPath + "/commodity/findCommodityByItems" ,
                type: "post",
                data:{'itemThirdCode':subject},
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
        $("#subject").change();
        $("#class").change(function () {
            var id = $(this).find(":selected").attr("value");
            $.ajax({
                url: rootPath + "/classModuleLesson/findLessonByCommodityId" ,
                type: "post",
                data:{'id':id},
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

        function search(page){
            var $this = this;
            var data = {};
            data.eduArea = $("#eduArea").val();
            data.eduSchool=$("#eduSchool").val();
            data.eduStep = $("#eduStep").val();
            data.eduYear = $("#eduYear").val();
            data.eduClass = $("#eduClass").val();
            data.itemThirdCode = $("#itemThirdCode").val();
            data.commodityId = $("#commodityId").val();// 注册状态
            data.lessonId = $("#lessonId").val();// 注册方式
            data.startTime = $("#startTime").val();// 报名状态
            data.endTime=$("#endTime").val();
            data.userNameOrMobile=$("#userNameOrMobile").val();

        }
    }