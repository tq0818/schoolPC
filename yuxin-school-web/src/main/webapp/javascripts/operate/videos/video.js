var videoTagSelect = null;
var editPopWindow = null;
$(function () {
    $(document).ready(function () {
            var start_paramter = {
                elem: '#start',
                format: 'YYYY-MM-DD',
                min: '1970-01-01',
                max: '2099-06-16 23:59:59',
                start: laydate.now(), //设定初始值为当前日期 
                istime: true,
                istoday: false,
                choose: function (datas) {
                    end_paramter.starts = datas;
                    end_paramter.min = datas; //开始日选好后，重置结束日的最小日期
                }
            }
            var end_paramter = {
                elem: '#end',
                format: 'YYYY-MM-DD',
                min: '1970-01-01',
                start: laydate.now(), //设定初始值为当前日期
                istime: true,
                istoday: false,
                choose: function (datas) {
                    start_paramter.max = datas;
                }
            }
            laydate(start_paramter);
            laydate(end_paramter);
        })
        /*选择日期  end*/
        editPopWindow = $("#L-allowAdmissionsTc").remove().show();
        //加载编辑框的学科选择下拉框
        editPopWindow.find("#itemOneId").getSysItem(function () {
        	editPopWindow.find("#itemOneId").bind("change", function () {
        		editPopWindow.find("#itemSecondId").html('').getSysItem($(this).val(), function () {
        			editPopWindow.find("#itemSecondId").removeAttr("disabled");
            });
        });
    });

    $(".oneItem:eq(0)").addClass("active");
    var oneText = $(".oneItem:eq(0)").html();
    if (oneText == "全部") {
        $(".secTwo").hide();
    }

    loadSecItem();
    //加载一次标签
    loadVideoTag();

    $(document).on("click", ".pview", function () {
            var types = $(this).attr("rtype");
            var rid = $(this).attr("rid");
            if (types == "video") {
                //video表
                getVideo(rid);
            } else {
                //resource表
                getResAjax(rid);
            }
        })
        .on("click", ".rpvc", function () {
            $(".prview").fadeOut(200);
            $(".add-subs-layer-bg").fadeOut(200);
            $("#video").html("");
        });

    $("[searchType]").on("click", function (e) {
        var $this = $(this);
        $this.addClass("active").siblings().removeClass("active");
        $(".selectInfo").trigger("click");
    });

    $(".oneItem").click(function () {
        var $this = $(this);
        var oneItemId = $this.attr("itemId");
        $(this).addClass("active").siblings(".oneItem").removeClass("active");
        $("#itemOneId").val(oneItemId);
        var oneText = $(this).html();
        if (oneText == "全部") {
            $(".secTwo").hide();
            loadSecItem();
        } else {
            $(".secTwo").show();
            loadSecItem(oneItemId);
        }
    });
    $(".vStatus").click(function () {
        var $this = $(this);
        var vStatus = $this.attr("videoStatus");
        $("#vStatus").val(vStatus);
        $(this).addClass("active").siblings(".vStatus").removeClass("active");
        loadVideoInfo(null, null, null, null, vStatus, null, null);
    });
    $(".add-subs-layer-bg").click(function () {
        //关闭预览
        $(".prview").fadeOut(200);
        //关闭遮罩
        $(".add-subs-layer-bg").fadeOut(200);

        $("#video").html("");
    });
    //上传视频
    $("#uploadVideo").click(function () {
        var flag = $("#flag").val();
        var upVideo = $("#upVideo").val();
        if (flag == 'over') {
            alert("您的视频存储服务已到期,不能继续上传视频");
            return false;
        } else {
            if (upVideo == 'can') {
                window.location.href = rootPath + "/video/uploadVideo";
            } else {
                alert("您的视频存储空间已满,不能继续上传视频");
                return false;
            }
        }
    });
    //排序
    $(".sp_bottom").click(function () {
        //筛选条件
        var oneId = $("#itemOneId").val();
        var secId = $(".secItem.active").attr("itemid");
        var vStatus = $("#vStatus").val();
        var ccName = $(".ccName").val();
        var ccTag = $(".ccTag option:selected").val();
        var oldStartTime = $("#start").val();
        var oldOverTime = $("#end").val();
        $("#sortBy").val("desc");
        loadVideoInfo(oneId, secId, oldStartTime, oldOverTime, vStatus, ccTag, ccName, "desc");
    });
    $(".sp_top").click(function () {
        //筛选条件
        var oneId = $("#itemOneId").val();
        var secId = $(".secItem.active").attr("itemid");
        var vStatus = $("#vStatus").val();
        var ccName = $(".ccName").val();
        var ccTag = $(".ccTag option:selected").val();
        var oldStartTime = $("#start").val();
        var oldOverTime = $("#end").val();
        $("#sortBy").val("asc");
        loadVideoInfo(oneId, secId, oldStartTime, oldOverTime, vStatus, ccTag, ccName, "asc");
    });

    //点击搜索
    $(".selectInfo").click(function () {
        //筛选条件
        var oneId = $("#itemOneId").val();
        var secId = $(".secItem.active").attr("itemid");
        var vStatus = $("#vStatus").val();
        var ccName = $(".ccName").val();
        var ccTag = $(".ccTag option:selected").val();
        var oldStartTime = $("#start").val();
        var oldOverTime = $("#end").val();
        $(".sp_bottom").addClass("active").siblings(".sort").removeClass("active");
        loadVideoInfo(oneId, secId, oldStartTime, oldOverTime, vStatus, ccTag, ccName);
    });
});

function loadVideoTag() {
    $.ajax({
        url: rootPath + "/videoTag/list",
        type: "post",
        dataType: "json",
        success: function (data) {
            $(".ccTag").empty();
            $(".ccTag").append("<option value=''>全部</option>");
            $.each(data, function (i, item) {
                $(".ccTag").append('<option value="' + item.tagName + '">' + item.tagName + '</option>');
            });
            videoTagSelect = $(".ccTag").select2();
        }
    });
}

function loadSecItem(nearOneItemId) {
    $.ajax({
        url: rootPath + "/video/findSecItemByOneId",
        type: "post",
        data: {
            "oneItemId": nearOneItemId
        },
        success: function (data) {
            $(".secItemInfo").html(data);
            //进行一次搜索
            $(".selectInfo").trigger("click");
            //点击学科小类时
            $(".secItem").click(function () {
                var $this = $(this);
                //var secItemId = $this.attr("itemId");
                //$("#itemSecondId").val(secItemId);
                $(this).addClass("active").siblings(".secItem").removeClass("active");
                $(".sp_bottom").addClass("active").siblings(".sort").removeClass("active");

                //进行一次搜索
                $(".selectInfo").trigger("click");
            });
        }
    });
}


function loadVideoInfo(nearOneItemId, itemSecondId, beginTime, endTime, videoStatus, videoTag, videoName, sort) {
    var param = "&page=" + $(".pagination li.active").text();

    var searchType = $("[searchType].active").attr("searchType");
    if (searchType != null && searchType != "" && searchType != "all") {
        param += "&searchType=" + searchType;
    }

    if (nearOneItemId == null || nearOneItemId == "") {
        nearOneItemId = $("#itemOneId").val();
    }
    param += "&itemOneId=" + nearOneItemId;
    if (itemSecondId == null || itemSecondId == "") {
        var secId = $(".secItem.active").attr("itemid");
    }
    param += "&itemSecondId=" + itemSecondId;
    if (beginTime != null && beginTime != "") {
        param += "&beginTime=" + beginTime;
    }
    if (endTime != null && endTime != "") {
        param += "&endTime=" + endTime;
    }
    if (videoStatus != null && videoStatus != "") {
        param += "&videoStatus=" + videoStatus;
    }
    if (videoTag != null && videoTag != "") {
        param += "&videoTag=" + videoTag;
    }
    if (videoName != null && videoName != "") {
        param += "&videoName=" + videoName;
    }

    if (sort == null || sort == "") {
        sort = 'desc';
    }
    param += "&sortBy=" + sort;

    $.ajax({
        url: rootPath + "/video/loadVideoAjaxInfo?_=" + new Date().getTime(),
        type: "post",
        data: param,
        beforeSend: function (XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
            success: function (data) {
                $(".table-center>tbody").empty().append(data);
                if ($(".table-center>tbody").children("tr").length <= 0) {
                    $(".table-center>tbody").append("<tr><th colspan=8>没有数据</th></tr>");
                }
                var oneId = $("#itemOneId").val();
                var secId = $(".secItem.active").attr("itemid");
                var vStatus = $("#vStatus").val();
                //禁用按钮
                $(".disable").click(function () {
                    var $this = $(this);
                    var voStatus = "";
                    var ht = $this.html();
                    var cz = "";
                    if (ht == "禁用") {
                        voStatus = "VIDEO_PROCESS_DELETE";
                        cz = "禁用";
                    }
                    if (ht == "启用") {
                        voStatus = "VIDEO_PROCESS_NOMAL";
                        cz = "启用";
                    }
                    var voId = $this.attr("videoId");
                    var videoS = $this.next().val();
                    if (videoS == "VIDEO_PROCESS_NOMAL" || videoS == "VIDEO_PROCESS_DELETE") {
                        if (voId) {
                            if (ht == "禁用") {
                                $.confirm("视频屏蔽后将无法被使用，确认禁用吗？", function (result) {
                                    if (result) {
                                        $.ajax({
                                            url: rootPath + "/video/isGroupSchool",
                                            type: "post",
                                            data: {
                                                "videoId": voId
                                            },
                                            success: function (data) {
                                                if (data) {
                                                    $.ajax({
                                                        url: rootPath + "/video/update",
                                                        type: "post",
                                                        data: {
                                                            "id": voId,
                                                            "videoStatus": voStatus
                                                        },
                                                        success: function (data) {
                                                            if (data == "success") {
                                                                $('<div class="c-fa">' + cz + "成功" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                                    $(this).remove();
                                                                    loadVideoInfo(oneId, secId, null, null, vStatus, null, null);
                                                                });
                                                            }
                                                        }
                                                    });
                                                } else {
                                                    $('<div class="c-fa">' + "该视频不属于该校区" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                        $(this).remove();
                                                        loadVideoInfo(oneId, secId, null, null, vStatus, null, null);
                                                    });
                                                    return;
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                            if (ht == "启用") {
                                $.ajax({
                                    url: rootPath + "/video/isGroupSchool",
                                    type: "post",
                                    data: {
                                        "videoId": voId
                                    },
                                    success: function (data) {
                                        if (data) {
                                            $.ajax({
                                                url: rootPath + "/video/update",
                                                type: "post",
                                                data: {
                                                    "id": voId,
                                                    "videoStatus": voStatus
                                                },
                                                success: function (data) {
                                                    if (data == "success") {
                                                        $('<div class="c-fa">' + cz + "成功" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                            $(this).remove();
                                                            loadVideoInfo(oneId, secId, null, null, vStatus, null, null);
                                                        });
                                                    }
                                                }
                                            });
                                        } else {
                                            $('<div class="c-fa">' + "该视频不属于该校区" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                $(this).remove();
                                                loadVideoInfo(oneId, secId, null, null, vStatus, null, null);
                                            });
                                            return;
                                        }
                                    }
                                });
                            }
                        }
                    } else {
                        $.msg("当前状态不允许操作");
                    }
                });
                //删除按钮
                $(".delete").click(function () {
                    var $this = $(this);
                    var voId = $this.attr("videoId");
                    var ccId = $this.attr("ccid");
                    if(typeof(ccId) == "undefined" || ccId == null){
                    	ccId = "";
                    }
                    var stype = $this.attr("stype");
                    var rtype = $this.parent().attr("rtype");
                    if (rtype == "video" && voId) {
                        $.confirm("视频删除后将无法还原，确认删除吗？", function (result) {
                            if (result) {
                                $.ajax({
                                    url: rootPath + "/video/isGroupSchool",
                                    type: "post",
                                    data: {
                                        "videoId": voId,
                                        "rtype": rtype
                                    },
                                    success: function (data) {
                                        if (data) {
                                            $.ajax({
                                                url: rootPath + "/video/delVideo/" + voId,
                                                data: {
                                                    "videoid": ccId,
                                                    "stype": stype
                                                },
                                                type: "post",
                                                success: function (data) {
                                                    if (data == "success") {
                                                        $('<div class="c-fa">' + "删除成功" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                            $(this).remove();
                                                            $(".selectInfo").trigger("click");
                                                        });
                                                    }
                                                }
                                            });
                                        } else {
                                            $('<div class="c-fa">' + "该视频不属于该校区" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                $(this).remove();
                                                $(".selectInfo").trigger("click");
                                            });
                                            return;
                                        }
                                    }
                                });
                            }
                        });
                    } else if (voId) {
                        $.confirm("资源删除后将无法还原，确认删除吗？", function (result) {
                            if (result) {
                                $.ajax({
                                    url: rootPath + "/classTypeResource/delreslist",
                                    type: "post",
                                    data: {
                                        "id": voId
                                    },
                                    success: function (data) {
                                        if (data.msg == "success") {
                                            loadVideoInfo(oneId, secId, null, null, vStatus, null, null);
                                        } else {
                                            $('<div class="c-fa">' + "删除异常" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                $(this).remove();
                                                loadVideoInfo(oneId, secId, null, null, vStatus, null, null);
                                            });
                                            return;
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
                //编辑时显示信息
                $(".edit").click(function () {
                    var $this = $(this);
                    var voId = $this.attr("videoId");
                    var voName = $this.attr("videoName");
                    var type = $this.parent().attr("rtype");
                    var voTag = $this.parent().parent().find("td [videoTag]");
                    var item = $this.parent().parent().find("td [name='itemId']");
                    //填充数据
                    editPopWindow.find('input#rtype').val(type);
                    editPopWindow.find('#voName').val(voName);
                    editPopWindow.find('#voTag').val(voTag.attr("videoTag"));
                    editPopWindow.find('#itemOneId').val(item.attr("itemOneId"));
                    editPopWindow.find('#itemOneId').trigger("change");
                    editPopWindow.find('#itemSecondId').val(item.attr("itemSecondId"));
                    editPopWindow.find('#voName').attr("voId", voId);
                    $.confirm({
                        title: '编辑',
                        text: editPopWindow,
                        callback: function (result) {
                                if (result) {
                                    var voId = editPopWindow.find('#voName').attr("voId");
                                    var oneId = editPopWindow.find(" #itemOneId").val();
                                    var secId = editPopWindow.find(" #itemSecondId").val();
                                    var vStatus = $("#vStatus").val();
                                    var rType = editPopWindow.find("input#rtype").val();
                                    var data = {
                                        id: voId,
                                        videoName: $("#voName").val(),
                                        videoTag: $("#voTag").val(),
                                        itemOneId: oneId,
                                        itemSecondId: secId
                                    }
                                    if (rType == "video" && voId) {
                                        $.ajax({
                                            url: rootPath + "/video/update",
                                            type: "post",
                                            data: data,
                                            success: function (data) {
                                                if (data == "success") {
                                                    $('<div class="c-fa">' + "修改成功" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                        $(this).remove();
                                                        $(".selectInfo").trigger("click");
                                                    });
                                                }
                                            }
                                        });
                                    } else if (voId) {
                                        $.ajax({
                                            url: rootPath + "/classTypeResource/updateReslist",
                                            type: "post",
                                            data: data,
                                            success: function (data) {
                                                if (data.msg == "success") {
                                                    $('<div class="c-fa">' + "修改成功" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                        $(".selectInfo").trigger("click");
                                                    });
                                                } else {
                                                    $('<div class="c-fa">' + "修改失败" + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                        $(this).remove();
                                                        $(".selectInfo").trigger("click");
                                                    });
                                                    return;
                                                }
                                            }
                                        });
                                    }
                                }
                            },
                            save: '保存'
                    });
                });
            },
            complete: function (XMLHttpRequest, textStatus) {
                $(".loading").hide();
                $(".loading-bg").hide();
            }
    });
}

function getVideo(id) {
    $.ajax({
        url: rootPath + "/video/getpview",
        type: "post",
        data: {
            "id": id
        },
        dataType: "json",
        success: function (data) {
                if (data.msg == "success") {
                	switch (data.types) {
					case "VIDEO_STORAGE_TYPE_LETV":
                        var player = new CloudVodPlayer();

                        player.init({
                            definition: 0,
                            share: 0,
                            extend: 0,
                            uu: data.letvId,
                            vu: data.playId,
                            isShowRight: 2
                        }, "video");
						break;
					case "VIDEO_STORAGE_TYPE_YK":
                        new YKU.Player('video', {
                            styleid: '0',
                            client_id: 'YOUR YOUKUOPENAPI CLIENT_ID',
                            vid: data.playId,
                            autoplay: true
                        });
						break;
					case "VIDEO_STORAGE_TYPE_TD":
                        $("#video").append("<iframe src='http://www.tudou.com/programs/view/html5embed.action?code=" + data.playId.substring(data.playId.indexOf("/") + 1) + "&autoPlay=true&playType=AUTO'" + "width='100%'" + "height='100%'" + "frameborder='0'" + "scrolling='no'" + "></iframe>");
						break;
					case "VIDEO_STORAGE_TYPE_SS":
                        script.type = "text/javascript";
                        script.charset = "utf-8";
                        script.src = "http://pub.video.capitalcloud.net/publishing/smvp.js?publisherId=479630023176179886&playerId=479630027471147195" +
                            "&videoId=" + data.playId +
                            "&width=" + $("#video").width() +
                            "&height=" + $("#video").height();
                        document.getElementById("video").appendChild(script);
						break;
					case "VIDEO_STORAGE_TYPE_QQ":
                        if (!data.playId) {
                            $('<div class="c-fa">当前视频无法预览</div>')
                                .appendTo('body').fadeIn(100).delay(1000).fadeOut(200,
                                    function () {
                                        $(this).remove();
                                    });
                            return false;
                        }
                        var arr = data.playId.split(",");
                        $("#video").html('<iframe src="http://play.video.qcloud.com/iplayer.html?' + '$appid=' + arr[1] + '&$fileid=' + arr[0] + '&$autoplay=1&$sw=' + 772 + '&$sh=' + $("#video").height() + '" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>');
						break;
					case "VIDEO_STORAGE_TYPE_SCORM":
                        var url = "http://" + data.doma + "/" + data.playId + "story.html";
                        $("#video").html("<iframe src='" + url + "'" + "width='100%'" + "height='100%'" + "frameborder='0'" + "scrolling='no'></iframe>");
						break;
					case "VIDEO_STORAGE_TYPE_QNVD":
						break;
					case "VIDEO_STORAGE_TYPE_BLVS":
						polyvObject('#video').videoPlayer({
							 'width':'100%',
							 'height':'100%',
							 'vid' : data.playId,
							 'code':data.code
						});
						break;
					default:
                        var script = document.createElement("script");
	                    script.type = "text/javascript";
	                    script.src = "http://p.bokecc.com/player?vid=" + data.ccid + "&siteid=" + data.cuId + "&autoStart=true&width=100%&height=100%&playertype=1";
	                    document.getElementById("video").appendChild(script);
						break;
					}
                    /*$(".vname").html(data.name+'<i class="close iconfont rpvc">&#xe610;</i>');*/
                    $(".prview").fadeIn(200);
                    $(".add-subs-layer-bg").fadeIn(200);
                } else {
                    $('<div class="c-fa">' + data.msg + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,
                            function () {
                                $(this).remove();
                            });
                }
            },
            error: function (request, status, error) {
                console.log(request.readyState);
                console.log(status);
            }
    });
}

function getResAjax(id) {
    $.ajax({
        url: "/classTypeResource/pview",
        type: "post",
        data: {
            "id": id
        },
        dataType: "json",
        success: function (data) {
                if (data.msg == "success") {
                    getResource(data.name, data.path, data.cate, data.resid);
                } else {
                    $('<div class="c-fa">' + data.msg + '</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                        $(this).remove();
                    });
                }
            },
            error: function (request, status, error) {

            }
    });
}

function get_cc_verification_code(ccid) {
    var uId = $("#uId").val();
    var cId = $("#cId").val();
    if (uId == null || uId == "") {
        uId = 0;
    }
    if (cId == null || cId == "") {
        cId = 0;
    }

    console.log("当前CC视频的用户,cId:" + cId + ", uId:" + uId);
    return cId + "_" + uId + "_b";
}

function getResource(name, path, cate, resid) {
    if (cate == "flash") {
        var html = "<div id='flashvideo' style='width:772px;height:480px'></div>";
        $("#video").html(html);
        var el = document.getElementById("flashvideo");
        swfobject.embedSWF(path, el, 772,
            $("#video").height(), 10);
    } else if (cate == "video" || cate == "audio") {
        var html = "";
        html += "<video id='example_video_1' class='video-js vjs-default-skin' ";
        html += "controls width='100%' height='100%' autoplay>";
        html += "<source src='" + path + "' type='video/mp4'/>";
        html += "</video>";
        $("#video").html(html);
        /*var myPlayer = videojs('example_video_1');
		myPlayer.play();*/
    } else {
        var html = '';
        html += '<iframe src="/javascripts/generic/web/viewer.html?file=';
        html += '/classTypeResource/getPDFStream/' + resid + '" ';
        html += 'width="100%" height="100%"></iframe>';
        $("#video").html(html);
    }
    /*$(".vname").html(name+'<i class="close iconfont rpvc">&#xe610;</i>');*/
    $(".prview").fadeIn(200);
    $(".add-subs-layer-bg").fadeIn(200);
}