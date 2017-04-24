(function ($) {
    Date.prototype.Format = function (fmt) { //author: meizz   
        var o = {
            "M+": this.getMonth() + 1, //月份   
            "d+": this.getDate(), //日   
            "h+": this.getHours(), //小时   
            "m+": this.getMinutes(), //分   
            "s+": this.getSeconds(), //秒   
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
            "S": this.getMilliseconds() //毫秒   
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }



    var oneId = "";
    var twoId = "";
    var publishStatus = "";
    var page = 1;
    var time = "";
    var Forms = {
        init: function () {
                $("#choiceTime").datetimepicker({
                    lang: 'ch',
                    timepicker: false,
                    format: 'Y-m-d'

                });
                $("#fzTime").datetimepicker({
                    lang: 'ch',
                    format: 'Y-m-d H:i',
                    step: 5,
                });
                $("#fzTimeEnd").datetimepicker({
                    lang: 'ch',
                    format: 'Y-m-d H:i',
                    step: 5,
                });
                $("#choiceTime").keydown(function (e) {
                    if (e.keyCode == 13) {
                        time = $("#choiceTime").val();
                        Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);
                    }
                });

                //判断是否存在学科
                var no = 0;
                $(".create").click(function () {
                    $(".oneItem").each(function () {
                        no += 1;
                    });
                    console.log(no)
                    if (no > 1) {
                        location.href = rootPath + "/liveOpenCourse/toAddOpenClass/0/0";
                    } else {
                        no = 0;
                        $.confirm("您还没有学科, 您确定要进入学科页面设置学科吗?", function (a) {
                            if (a == true) {
                                location.href = rootPath + "/sysConfigItem/project";
                            }
                        });
                    }
                });

                $(".seTime").click(function () {
                    time = $("#choiceTime").val();
                    Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);
                });

                $(".reSet").click(function () {
                    oneId = "";
                    twoId = "";
                    publishStatus = "";
                    time = "";
                    $("#choiceTime").val("");
                    $(".qb").addClass("btn-success").siblings().removeClass("btn-success");
                    Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);
                });

                $(".oneItem").click(
                    function () {
                        page = 1;
                        $("#info").html('');
                        $(this).addClass("btn-success").siblings().removeClass("btn-success");
                        oneId = $(this).attr("oneId");
                        $("#oneId").val(oneId);
                        Forms.getTwpItems(oneId);
                        Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);
                    });
                Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);

                // 状态
                $(".pubStatus").click(
                    function () {
                        page = 1;
                        $("#info").html('');
                        $(this).addClass("btn-success").siblings().removeClass(
                            "btn-success");
                        publishStatus = $(this).attr("status");

                        Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);
                    });
                // 弹层取消
                $(".btn-cancel").click(function () {
                    $(".class-resource").hide();
                    $(".loading-bg").hide();
                });
                // 不用修改
                $(".btn-no").click(
                    function () {
                        var couId = $(this).attr("couId");
                        var fzTime = $(this).attr("fzTime");
                        var time = $("#fzTime").val();
                        var endTime = $("#fzTimeEnd").val();
                        if (time) {
                            $("#errorInfo").html(
                                "注：可将当前公开课信息复制到对应日期，请您选择您想选择的日期");
                        } else {
                            $("#errorInfo").html("请选择日期");
                            return false;
                        }
                        if (endTime) {
                            $("#errorInfo").html(
                                "注：可将当前公开课信息复制到对应日期，请您选择您想选择的日期");
                        } else {
                            $("#errorInfo").html("请选择日期");
                            return false;
                        }
                        var start = new Date();
                        var startStr = start.Format("yyyy-MM-dd");
                        start = new Date(startStr.replace("-", "/").replace( "-", "/"));
                        var end = new Date(time.replace("-", "/").replace( "-", "/"));
                        var endDate = new Date(endTime.replace("-", "/").replace( "-", "/"));
                        var barrage = $("#barrage").val();
                        var modetype = $("#modetype").val();
                        if (end < start) {
                            $("#errorInfo").html("选择日期应大于实际日期！");
                            return false;
                        } else if (end >= endDate) {
                            $("#errorInfo").html("开始时间应小于结束时间！");
                            return false;
                        } else {
                            $("#errorInfo").html("注：可将当前公开课信息复制到对应日期，请您选择您想选择的日期");
                        }
                        Forms.copyInfo(couId, time, endTime,barrage,modetype);
                    });

                $(".btn-yes").click(
                    function () {
                        var couId = $(this).attr("couId");
                        var fzTime = $(this).attr("fzTime");
                        var time = $("#fzTime").val();
                        var endTime = $("#fzTimeEnd").val();
                        if (time) {
                            $("#errorInfo").html("注：可将当前公开课信息复制到对应日期，请您选择您想选择的日期");
                        } else {
                            $("#errorInfo").html("请选择日期");
                            return false;
                        }
                        if (endTime) {
                            $("#errorInfo").html("注：可将当前公开课信息复制到对应日期，请您选择您想选择的日期");
                        } else {
                            $("#errorInfo").html("请选择日期");
                            return false;
                        }
                        var start = new Date();
                        var startStr = start.Format("yyyy-MM-dd hh:mm");
                        start = new Date(startStr.replace("-", "/").replace( "-", "/"));
                        var end = new Date(time.replace("-", "/").replace( "-", "/"));
                        var endDate = new Date(endTime.replace("-", "/").replace( "-", "/"));
                        if (end < start) {
                            $("#errorInfo").html("选择日期应大于实际日期！");
                            return false;
                        } else if (end >= endDate) {
                            $("#errorInfo").html("开始时间应小于结束时间！");
                            return false;
                        } else {
                            $("#errorInfo").html("注：可将当前公开课信息复制到对应日期，请您选择您想选择的日期");
                        }
                        location.href = rootPath + "/liveOpenCourse/toAddOpenClass/" + couId + "/1?startOpenData=" + time + "&endOpenData=" + endTime;
                    });
                // 修改上下架
                $("#info").on("click", ".on", function () {
                    var html = $(this).html();
                    var id = $(this).attr("couId");
                    var time = $(this).attr("time");
                    if (html == '下架') {
                        Forms.update(id, "-1");
                        $(this).html("上架");
                    }
                    if (html == '上架') {
                        var start = new Date(new Date().Format("yyyy-MM-dd"));
                        var end = new Date(time.replace("-", "/").replace( "-", "/"));
                        if (end <= start) {
                            $('<div class="c-fa">' + "日期已过时，请编辑后上架" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                $(this).remove();
                            });
                            return;
                        }
                        Forms.update(id, "1");
                        $(this).html("下架");
                    }
                    if (html == '发布') {
                        var start = new Date(new Date().Format("yyyy-MM-dd"));
                        var end = new Date(time.replace("-", "/").replace("-", "/"));
                        if (end <= start) {
                            $('<div class="c-fa">' + "日期已过时，请编辑后发布" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                $(this).remove();
                            });
                            return;
                        }
                        Forms.update(id, "1");
                        $(this).html("下架");
                    }
                });
                // 删除
                $("#info").on("click", ".del", function () {
                    var id = $(this).attr("couId");
                    $.confirm("确认删除此公开课程吗？", function (result) {
                    	console.log(result);
                        if (result) {
                            Forms.del(id);
                        }
                    });
                });
            },
            getTwpItems: function (pId) {
                $("#twoItem").html("");
                $.ajax({
                        url: rootPath + "/liveOpenCourse/getTwoItemsByOneId",
                        type: "post",
                        data: {
                            "pId": pId
                        },
                        dataType: "json",
                        beforeSend: function (XMLHttpRequest) {
                                $(".loading").show();
                                $(".loading-bg").show();
                            },
                        success: function (jsonData) {
                            $("#twoItem").append('<a class="btn btn-sm btn-default btn-success twoItem qb" twoId="" href="javascript:;">全部</a>');
                            $.each(jsonData, function (i, item) {
                                $("#twoItem").append('<a class="btn btn-sm btn-default twoItem" twoId="' + item.id + '" href="javascript:;">' + item.itemName + '</a>');
                            });

                            $(".reSet").click(function () {
                                oneId = "";
                                twoId = "";
                                publishStatus = "";
                                time = "";
                                $("#choiceTime").val("");
                                $(".qb").addClass("btn-success").siblings().removeClass("btn-success");
                                Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);
                            });

                            $(".twoItem").on("click",function () {
                                page = 1;
                                $("#info").html('');
                                $(this).addClass("btn-success").siblings().removeClass("btn-success");
                                twoId = $(this).attr("twoId");
                                $("#twoId").val(twoId);
                                Forms.ajaxInfo(0, oneId, twoId,publishStatus, time);
                            });
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            $(".loading").hide();
                            $(".loading-bg").hide();
                        }
                    });
            },
            ajaxInfo: function (pageNo, oneId, twoId, pubStatus, time) {
                var param = "";
                param += "&page=" + pageNo;
                if (oneId) {
                    param += "&itemOneId=" + oneId;
                }
                if (twoId) {
                    param += "&itemSecondId=" + twoId;
                }
                if (pubStatus) {
                    param += "&publishStatus=" + pubStatus;
                }
                if (time) {
                    param += "&startOpenData=" + time;
                }

                $.ajax({
                    url: rootPath + "/liveOpenCourse/ajaxInfo",
                    type: "post",
                    data: param,
                    // dataType : "json",
                    beforeSend: function (XMLHttpRequest) {
                            $(".loading").show();
                            $(".loading-bg").show();
                        },
                    success: function (data) {
                        $("#info").html("").html(data);

                        $(".fz").click(function () {
                            $("#errorInfo").html("").html("注：可将当前公开课信息复制到对应日期，请您选择您想选择的日期");
                            $("#fzTime").val("");
                            $("#fzTimeEnd").val("");
                            var couId = $(this).attr("couId");
                            var fzTime = $(this).attr("fzTime");
                            var feTime = $(this).attr("feTime");
                            var barrage = $(this).attr("data-barrage");
                            var modetype = $(this).attr("data-modetype");
                            var ls = $(this).attr("data-ls");
                            $(".btn-no").attr("couId", couId);
                            $(".btn-yes").attr("couId", couId);
                            $("#fzTime").val(fzTime);
                            $("#fzTimeEnd").val(feTime);
                            if(ls == 'ht'){
                                $("#barrage").val(barrage);
                                $("#modetype").val(modetype);
                              /*  $(".barrages").html(barrage == 1 ? '是' : '否');
                                $(".modetypes").html(modetype == 1 ? '语音互动课堂' : '大班课');
                                $(".barrages").show();
                                $(".modetypes").show();*/
                            }/*else{
                                $(".barrages").hide();
                                $(".modetypes").hide();
                            }*/
                            $(".class-resource").show();
                            $(".loading-bg").show();
                        });
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
            },
            del: function (id) {
                page = 1;
                $.ajax({
                    url: rootPath + "/liveOpenCourse/update",
                    type: "post",
                    data: {
                        "id": id,
                        "delFlag": "1"
                    },
                    beforeSend: function (XMLHttpRequest) {
                            $(".loading").show();
                            $(".loading-bg").show();
                        },
                    success: function (data) {
                        $("#info").html('');
                        if (data == 'success') {
                            Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);
                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
            },
            update: function (id, status) {
                $.ajax({
                    url: rootPath + "/liveOpenCourse/update",
                    type: "post",
                    data: {
                        "id": id,
                        "publishStatus": status
                    },
                    beforeSend: function (XMLHttpRequest) {
                            $(".loading").show();
                            $(".loading-bg").show();
                        },
                    success: function (data) {
                        $("#info").html('');
                        if (data == 'success') {
                            $('<div class="c-fa">' + "操作成功！" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                $(this).remove();
                                Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);
                            });

                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
            },
            copyInfo: function (id, date, endDate,barrage,modetype) {
                page = 1;
                $.ajax({
                    url: rootPath + "/liveOpenCourse/copyInfo",
                    type: "post",
                    data: {
                        "id": id,
                        "startOpenData": date,
                        "endOpenData": endDate,
                        "barrage":barrage,
                        "modetype":modetype
                    },
                    success: function (data) {
                        if (data == 'success') {
                            $(".class-resource").hide();
                            $(".loading-bg").hide();
                            $("#info").html('');
                            Forms.ajaxInfo(0, oneId, twoId, publishStatus, time);
                        } else if(data == 'fail') {
                            $('<div class="c-fa">' + "日期相同，不能复制" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                    $(this).remove();
                                });
                            return;
                        }else{
                            $('<div class="c-fa">' + data + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                $(this).remove();
                            });
                        return;
                        }
                    }
                });
            }
    }
    $(document).ready(function () {
        Forms.init();
    })
    window.Forms = Forms;
})(jQuery)