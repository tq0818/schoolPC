$(document).ready(function () {
    loadData();
    //营销设置
    $(".qq-box").on("mouseenter", function () {
        if (!($(this).hasClass("qq-box-active"))) {
            $(this).css("background-color", "#eeeeee").find(".choose-btn").css("display", "inline-block");
        }
    });
    $(".qq-box").on("mouseleave", function () {
        if (!($(this).hasClass("qq-box-active"))) {
            $(this).css("background-color", "#fff").find(".choose-btn").css("display", "none");
        }
    });
    $(".choose-btn").on("click", function () {
        $(this).parents(".qq-box").addClass("qq-box-active").find(".choosed img").css("display", "block");
        $(this).parents(".qq-box").css("background-color", "#fff").find(".choose-btn").css("display", "none");
        $(this).parents(".qbox").siblings().find(".qq-box").removeClass("qq-box-active").find(".choosed img").css("display", "none");
        $(this).parents(".qbox").find(".qq-input").css("visibility", "visible");
        $(this).parents(".qbox").siblings().find(".qq-input").css("visibility", "hidden");
    });
    //开关
    $(".tit-font em.normal").off().on("click", function () {
        var status = $(this).hasClass("open");
        if ($(this).hasClass("open")) {
            $(this).removeClass("open").addClass("close").html("&#xe604;");
            $(this).parent(".tit-font").find(".i").removeClass("open").addClass("close").text("已禁用");
        } else {
            $(this).removeClass("close").addClass("open").html("&#xe603;");
            $(this).parent(".tit-font").find(".i").removeClass("close").addClass("open").text("已启用");
        };
        if (status) {
            $(this).parents("div.right-side").find("input[type=text]").attr("disabled", "disabled");
            $(this).parents("div.right-side").find("div.savebtn").find("input[type=button]").attr("disabled", "disabled");
            var data = {};
            var type = $(this).parents(".tit-font").attr("mark");
            data.id = $("#markId").val();
            if (type == "markqq") {
                data.qqFlag = 0;
            } else if (type == "weixinmark") {
                data.weixinFlag = 0;
            } else if (type == "xinlang") {
                data.weiboFlag = 0;
            } else if (type == "services") {
                data.serviceFlag = 0;
            } else if (type == "bdsq") {
                data.bdsqFlag = 0;
            }
            $.ajax({
                type: "post",
                url: rootPath + "/companyMarketSet/updateMarket",
                dataType: "json",
                data: data,
                success: function (jsonData) {
                    if (jsonData.id) {
                        $("#markId").val(jsonData.id);
                    }
                }
            });
        } else {
            $(this).parents("div.right-side").find("input[type=text]").removeAttr("disabled");
            $(this).parents("div.right-side").find("div.savebtn").find("input[type=button]").removeAttr("disabled");
            var data = {};
            var type = $(this).parents(".tit-font").attr("mark");
            data.id = $("#markId").val();
            if (type == "markqq") {
                data.qqFlag = 1;
            } else if (type == "weixinmark") {
                data.weixinFlag = 1;
            } else if (type == "xinlang") {
                data.weiboFlag = 1;
            } else if (type == "services") {
                data.serviceFlag = 1;
            } else if (type == "bdsq") {
                data.bdsqFlag = 1;
            }
            $.ajax({
                type: "post",
                url: rootPath + "/companyMarketSet/updateMarket",
                dataType: "json",
                data: data,
                success: function (jsonData) {
                    if (jsonData.id) {
                        $("#markId").val(jsonData.id);
                    }
                }
            });
        }
    });
    //保存数据
    $(".savebtn").find("input.btn-success").on('click', function () {
        var $this = $(this);
        var type = $(this).attr("marks");
        var id = $("#markId").val();
        if (type == "qqmark") {
            var qqType, qqNum, qqKey;
            $(".right-side").find(".qqtypes").each(function (i) {
                if ($(this).hasClass("qq-box-active")) {
                    qqType = $(this).attr("mark");
                }
            });
            if ("MARKET_QQ_PERSON" == qqType) {
                qqNum = $("#person_qqval").val();
            } else {
                qqNum = $("#private_qqval").val();
                qqKey = $("#private_qqkey").val();
            }
            if (qqNum.length <= 0 || isNaN(qqNum)) {
                $.msg("QQ号不能为空并且为数字");
                return;
            }
            $.ajax({
                type: "post",
                url: rootPath + "/companyMarketSet/updateMarket",
                dataType: "json",
                data: {
                    "id": id,
                    "qqType": qqType,
                    "qqNum": qqNum,
                    "qqKey": qqKey
                },
                success: function (jsonData) {
                    $.msg("操作成功");
                    loadData();
                }
            });
        } else if (type == "wxmark") {
            var weixinNo, weixinPic;
            weixinNo = $("#weixinNo").val();
            weixinPic = $("#imageObject").attr("src");
            if (weixinNo.length <= 0) {
                $.msg("公众号不能为空");
                return;
            }
            $.ajax({
                type: "post",
                url: rootPath + "/companyMarketSet/updateMarket",
                dataType: "json",
                data: {
                    "id": id,
                    "weixinNo": weixinNo,
                    "weixinPic": weixinPic
                },
                success: function (jsonData) {
                    $.msg("操作成功");
                    loadData();
                }
            });
        } else if (type == "xlmark") {
            var weiboNo, weiboUrl, weiboPic;
            weiboNo = $("#weiboNo").val();
            weiboUrl = $("#xlNo").val();
            weiboPic = $("#imageObject1").attr("src");
            $.ajax({
                type: "post",
                url: rootPath + "/companyMarketSet/updateMarket",
                dataType: "json",
                data: {
                    "id": id,
                    "weiboNo": weiboNo,
                    "weiboUrl": weiboUrl,
                    "weiboPic": weiboPic
                },
                success: function (jsonData) {
                    $.msg("操作成功");
                    loadData();
                }
            });
        } else if (type == "kfmark") {
            var servicePhone, serviceTime;
            servicePhone = $("#comPhone").val();
            if (servicePhone.length <= 0 || isNaN(servicePhone)) {
                $.msg("请正确输入电话号码");
                return;
            }
            serviceTime = $("#serviceTime").val();
            if (serviceTime == "") {
                $.msg("请输入服务时间");
                return;
            }
            $.ajax({
                type: "post",
                url: rootPath + "/companyMarketSet/updateMarket",
                dataType: "json",
                data: {
                    "id": id,
                    "servicePhone": servicePhone,
                    "serviceTime": serviceTime
                },
                success: function (jsonData) {
                    $.msg("操作成功");
                    loadData();
                }
            });
        }else if (type == "bdsq") {
            var bdsqJsUrl, bdsqType;
            bdsqJsUrl = $("#bdsqJsUrl").val();
            bdsqType = $("#bdsqType").val();
            if (bdsqJsUrl.length <= 0 ) {
                $.msg("数据不正确");
                return;
            }
            $.ajax({
                type: "post",
                url: rootPath + "/companyMarketSet/updateMarket",
                dataType: "json",
                data: {
                    "id": id,
                    "bdsqJsUrl": bdsqJsUrl,
                    "bdsqType": bdsqType
                },
                success: function (jsonData) {
                    $.msg("操作成功");
                    loadData();
                }
            });
        }
    });
    //点击右侧菜单
    $("#markin_settings").on('click', 'li', function () {
        var url = $(this).attr("mark");
        window.location.href = rootPath + url;
    });
    //选择微信图片
    $(".upload-wx-pics").on('click', 'div.weixin-pic', function () {
            $("#imgData").trigger('click');
            return;
        })
        //选择新浪图片
    $(".upload-wb-pics").on('click', 'div.weibo-pic', function () {
            $("#imgDatas").trigger('click');
            return;
        })
        //返回
    $(".hcancle").on('click', function () {
        window.location.href = rootPath + "/company/companyService";
    })
});
//初始化数据
function loadData() {
        $.ajax({
            type: "post",
            async: false,
            url: rootPath + "/companyMarketSet/companyMarketData",
            success: function (jsonData) {
                $("#markId").val(jsonData.id);
                //营销
                if (jsonData.qqType) {
                    if (jsonData.qqType == "MARKET_QQ_PERSON") {
                        $(".personqq").addClass("qq-box-active").find(".choosed img").css("display", "block");
                        $(".personqq").css("background-color", "#fff").find(".choose-btn").css("display", "none");
                        $(".marketqq").removeClass("qq-box-active").find(".choosed img").css("display", "none");
                        $(".person_qqval").css("visibility", "visible");
                    } else {
                        $(".marketqq").addClass("qq-box-active").find(".choosed img").css("display", "block");
                        $(".marketqq").css("background-color", "#fff").find(".choose-btn").css("display", "none");
                        $(".personqq").removeClass("qq-box-active").find(".choosed img").css("display", "none");
                        $(".market-input").css("visibility", "visible");
                    }
                }
            }
        });
        $("em.iconfont").each(function (i) {
            if ($(this).hasClass("close")) {
                $(this).parents("div.right-side").find("input[type=text]").attr("disabled", "disabled");
                $(this).parents("div.right-side").find("div.savebtn").find("input[type=button]").attr("disabled", "disabled");
            }
        });
    }
    //上传二维码

function savePic() {
        $.ajaxFileUpload({
            url: rootPath + "/companyMarketSet/Uploadewm",
            secureuri: false, // 安全协议
            async: false,
            fileElementId: 'imgData',
            dataType: 'json',
            type: "POST",
            success: function (data) {
                    $("#imageObject").attr("src", data.url);
                    $("#imageObject").attr("ids", data.picPath);
                    $(".weixin-pic").text("点击更换微信二维码");
                },
                error: function (arg1, arg2, arg3) {
                    //console.log(arg1);
                }
        });
    }
    //上传新浪

function savexlPic() {
    $.ajaxFileUpload({
        url: rootPath + "/companyMarketSet/Uploadxl",
        secureuri: false, // 安全协议
        async: false,
        fileElementId: 'imgDatas',
        dataType: 'json',
        type: "POST",
        success: function (data) {
                $("#imageObject1").attr("src", data.url);
                $("#imageObject1").attr("ids", data.picPath);
                $(".weibo-pic").text("点击更换微博二维码");
            },
            error: function (arg1, arg2, arg3) {
                //console.log(arg1);
            }
    });
}