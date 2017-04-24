(function ($) {
    var tikuId;
    var setId;
    var sameSubName;
    var Forms = {
        init: function () {
                var subjectId; // 初始化科目id
                tikuId = $("#tikuId").val();
                setId = $("#setId").val();
                Forms.loadSubject(tikuId);
                // 隐藏保存按钮
                $(".freeStu").each(function () {
                    if ($(this).is(':checked')) {
                        var tval = $(this).val();
                        if (tval == "free_stu_no") {
                            $(".saveFree").hide();
                            $("#freeDay").attr("disabled", "disabled");
                        }
                        if (tval == "free_stu_try") {
                            $(".saveFree").show();
                            $("#freeDay").removeAttr("disabled");
                        }
                    } else {
                        $(".saveFree").hide();
                        $("#freeDay").attr("disabled", "disabled");
                    }
                });

                // 免费学员做题权限设置
                $(".freeStu").click(
                    function () {
                        var thisVal = $(this).val();
                        if (thisVal == "free_stu_no") {
                            $("#freeDay").val("");
                            $(".saveFree").hide();
                            $("#freeDay").attr("disabled", "disabled");
                            Forms.updateSet(setId, 1, 0, null, null, null, null,
                                null, null, null, null);
                        }
                        if (thisVal == "free_stu_try") {
                            // 显示保存按钮
                            $(".saveFree").show();
                            $("#freeDay").removeAttr("disabled");
                        }
                    });

                $(".saveFree").click(
                    function () {
                        var freeDay = $("#freeDay").val();
                        if (freeDay) {
                            freeDay = parseInt(freeDay);
                            if (freeDay == 0) {
                                $("#freeDay").val(0)
                            }
                            Forms.updateSet(setId, 0, 1, freeDay, null, null, null,
                                null, null, null, null);
                        } else {
                            $('<div class="c-fa">' + "请输入体验天数" + '</div>')
                                .appendTo('body').fadeIn(100).delay(
                                    1000).fadeOut(200, function () {
                                    $(this).remove();
                                });
                            return;
                        }

                    });

                // 收费学员做题权限设置
                $(".chargeStu").click(
                    function () {
                        var thisVal = $(this).val();
                        if (thisVal == "charge_stu_all") {
                            Forms.updateSet(setId, null, null, null, 1, 0, 0,
                                null, null, null, null);
                        }
                        if (thisVal == "charge_stu_item") {
                            Forms.updateSet(setId, null, null, null, 0, 1, 0,
                                null, null, null, null);
                        }
                        if (thisVal == "charge_stu_item_second") {
                            Forms.updateSet(setId, null, null, null, 0, 0, 1,
                                null, null, null, null);
                        }
                    });

                // 试题审核设置
                $(".topicAudit").click(
                    function () {
                        var thisVal = $(this).val();
                        if (thisVal == "topic_audit_yes") {
                            Forms.updateSet(setId, null, null, null, null, null, null, 1, 0, null, null);
                        }
                        if (thisVal == "topic_audit_no") {
                            Forms.updateSet(setId, null, null, null, null, null, null, 0, 1, null, null);
                        }
                    });

                // 试卷审核设置
                $(".paperAudit").click(
                    function () {
                        var thisVal = $(this).val();
                        if (thisVal == "paper_audit_yes") {
                            Forms.updateSet(setId, null, null, null, null, null, null, null, null, 1, 0);
                        }
                        if (thisVal == "paper_audit_no") {
                            Forms.updateSet(setId, null, null, null, null, null, null, null, null, 0, 1);
                        }
                    });
                //添加科目
                $(".addCBtn").find("a").click(function () {
                    $(".addCBtn").hide();
                    $(".addCConter").show();
                });
                $(".addCConter").find("a").click(
                    function () {
                        var thisHtml = $(this).html();
                        if (thisHtml == "保存") {
                            var CName = $("#addSubName").val();
                            if (CName) {
                                $.ajax({
                                    url: rootPath + "/tikuSubject/checkSubName",
                                    type: "post",
                                    data: {
                                        "subjectName": CName,
                                        "categoryId": tikuId
                                    },
                                    success: function (data) {
                                        if (data) {
                                            Forms.addSubject(CName);
                                        } else {
                                            $('<div class="c-fa">' + "科目名称不允许重复" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                $(this).remove();
                                            });
                                            return;
                                        }
                                    }
                                });
                            } else {
                                $('<div class="c-fa">' + "请输入科目的名称" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                    $(this).remove();
                                });
                                return;
                            }
                        }
                        if (thisHtml == "取消") {
                            $("#addSubName").val("");
                            $(".addCConter").hide();
                            $(".addCBtn").show();
                        }
                    })

            },
            checkSubName: function (subjectName) {
                $.ajax({
                    url: rootPath + "/tikuSubject/checkSubName",
                    type: "post",
                    data: {
                        "subjectName": subjectName,
                        "categoryId": tikuId
                    },
                    success: function (data) {

                    }
                });
            },
            addSubject: function (subjectName) {
                $.ajax({
                    url: rootPath + "/tikuSubject/add",
                    type: "post",
                    data: {
                        "subjectName": subjectName,
                        "categoryId": tikuId,
                        "delFlag": 0
                    },
                    beforeSend: function (XMLHttpRequest) {
                            $(".loading").show();
                            $(".loading-bg").show();
                        },
                        success: function (data) {
                            if (data == "success") {
                                Forms.loadSubject(tikuId);
                            }
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            $(".loading").hide();
                            $(".loading-bg").hide();
                        }
                });
            },
            loadSubject: function (tikuId) {
                $.ajax({
                    url: rootPath + "/tikuSubject/loadSubject",
                    type: "post",
                    data: {
                        "categoryId": tikuId,
                        "delFlag": 0
                    },
                    beforeSend: function (XMLHttpRequest) {
                            $(".loading").show();
                            $(".loading-bg").show();
                        },
                        success: function (data) {
                            $("#subInfo").html("");
                            $("#subInfo").html(data);

                            $("#subInfo").find("li").find(".setM").find("a").click(
                                function () {
                                    var $this = $(this);
                                    var mana = $this.attr("ht");
                                    if (mana == "编辑") {
                                        $this.parent(".setM").hide();
                                        $this.parent(".setM").next(".edit").attr("style", "display:inherit;margin-top: -14px;height:46px;");
                                        $this.parent(".setM").prev(".info").hide();
                                    }
                                });
                            $("#subInfo").find("li").find(".edit").find("a").click(
                                function () {
                                    var $this = $(this);
                                    var mana = $this.html();
                                    if (mana == "保存") {
                                        var $containsItem = $this.parentsUntil("li").parent().siblings();
                                        //var $containsItem = $("#subInfo").children("li");
                                        var $contains = new Array();
                                        $.each($containsItem, function (i, e) {
                                            console.log(e);
                                            e = $(e);
                                            $contains.push(e.find("span:first a").text().replace(/(^\s*)|(\s*$)/g, ""));
                                        });
                                        var newSubName = $this.prev(".newSubName").val();
                                        if ($contains.includes(newSubName)) {
                                            alert("科目已存在");
                                            return false;
                                        }
                                        var subId = $this.attr("subId");
                                        Forms.updateSubject(subId, newSubName);
                                    }
                                    if (mana == "取消") {
                                        $this.parent(".edit").hide();
                                        $this.parent(".edit").prev(".setM")
                                            .prev(".info").show();
                                        $this.parent(".edit").prev(".setM")
                                            .show();
                                    }
                                });
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            $(".loading").hide();
                            $(".loading-bg").hide();
                        }

                });
                $("#addSubName").val("");
                $(".addCConter").hide();
                $(".addCBtn").show();
            },
            updateSubject: function (id, subjectName) {
                $.ajax({
                    url: rootPath + "/tikuSubject/update",
                    type: "post",
                    data: {
                        "id": id,
                        "subjectName": subjectName
                    },
                    beforeSend: function (XMLHttpRequest) {
                            $(".loading").show();
                            $(".loading-bg").show();
                        },
                        success: function (data) {
                            if (data == "success") {
                                Forms.loadSubject(tikuId);
                            }
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            $(".loading").hide();
                            $(".loading-bg").hide();
                        }
                });
            },
            delSubject: function (obj) {
            	if($("#subInfo").children("li").length <= 1){
            		alert("最后要一个科目不允许删除");
            		return false;
            	}
                var subId = $(obj).attr("subId");
                if (subId) {
                    $.ajax({
                        url: rootPath + "/tikuSubject/checkSubjectIsHasChapter",
                        type: "post",
                        data: {
                            "subId": subId
                        },
                        success: function (data) {
                            if (data) {
                                $confirm("科目", "该科目下存在试题信息是否继续删除？", function (result) {
                                    if (result) {
                                        $.ajax({
                                            url: rootPath + "/tikuSubject/delSubById",
                                            type: "post",
                                            data: {
                                                "id": subId
                                            },
                                            success: function (data) {
                                                if (data == "success") {
                                                    Forms.loadSubject(tikuId);
                                                }
                                            }
                                        });
                                    }
                                });
                            } else {
                                $.ajax({
                                    url: rootPath + "/tikuSubject/delSubById",
                                    type: "post",
                                    data: {
                                        "id": subId
                                    },
                                    success: function (data) {
                                        if (data == "success") {
                                            Forms.loadSubject(tikuId);
                                        }
                                    }
                                });
                            }
                        }
                    });

                } else {
                    alert("error:id no exists")
                }
            },
            updateSet: function (id, freeStuNo, freeStuTry, freeStuTryDay, chargeStuAll, chargeStuItem, chargeStuItemSecond, topicAuditYes, topicAuditNo, paperAuditYes, paperAuditNo) {
                $.ajax({
                    url: rootPath + "/tikuSet/update",
                    type: "post",
                    data: {
                        "id": id,
                        "freeStuNo": freeStuNo,
                        "freeStuTry": freeStuTry,
                        "freeStuTryDay": freeStuTryDay,
                        "chargeStuAll": chargeStuAll,
                        "chargeStuItem": chargeStuItem,
                        "chargeStuItemSecond": chargeStuItemSecond,
                        "topicAuditYes": topicAuditYes,
                        "topicAuditNo": topicAuditNo,
                        "paperAuditYes": paperAuditYes,
                        "paperAuditNo": paperAuditNo
                    },
                    beforeSend: function (XMLHttpRequest) {
                            $(".loading").show();
                            $(".loading-bg").show();
                        },
                        success: function (data) {
                            if (data == "success") {
                                $('<div class="c-fa">' + "设置成功" + '</div>')
                                    .appendTo('body').fadeIn(100).delay(
                                        1000).fadeOut(200, function () {
                                        $(this).remove();
                                    });
                            }
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            $(".loading").hide();
                            $(".loading-bg").hide();
                        }
                });
            }
    }
    $(document).ready(function () {
        Forms.init();
    })
    window.Forms = Forms;
})(jQuery)