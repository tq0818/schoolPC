(function ($) {
    var dynamics = {
        init: function () {
            var $this = this;
            $this.search();
            //删除
            $(".Y_background").on("click", ".delete1", function () {
                $(this).addClass("active");
                //$(".mark1").show();
                var id = $(this).attr("id");
                $(".deleteId").attr("id", id);
                $.confirm('您是否确定删除该条动态及评论',function(result){
                    if(result){
                        $.ajax({
                            url: rootPath + "/classModule/deleteDynamics",
                            data: {"id": $(".deleteId").attr("id")},
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (e) {
                                if (e == "success") {
                                    $.msg("删除成功!", 3000);
                                    // $(".mark").hide();
                                    $(".delete").removeClass("active");
                                    $(".status_" + $(".deleteId").attr("id")).remove();
                                } else {
                                    $.msg("出现异常!", 3000);
                                    // $(".mark").hide();
                                    $(".delete").removeClass("active");
                                }

                            }
                        });
                    }else{
                        $(".delete").removeClass("active");
                    }
                });
            });
            $(".Y_background").on("click", ".delete2", function () {
                $(this).addClass("active");
                var a=$(this);
                //$(".mark2").show();
                var id = $(this).attr("id");
                $(".deleteReplayId").attr("id", id);
                $.confirm("您是否确定删除该条评论",function(result){
                    if(result){
                        $.ajax({
                            url: rootPath + "/classModule/deleteDynamicsReplay",
                            data: {"id": $(".deleteReplayId").attr("id")},
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (e) {
                                if (e == "success") {
                                    $.msg("删除成功!", 3000);
                                    //$(".mark").hide();
                                    $(".delete").removeClass("active");
                                    var count=parseInt(a.parents(".Y_comment").prev().find(".contentCount").attr("count"))-1;
                                    a.parents(".Y_comment").prev().find(".contentCount").attr("count",count);
                                    a.parents(".Y_comment").prev().find(".contentCount").attr("isclick","评论（"+count+"）");
                                    $(".dynamicsList").find(".replayId_" + $(".deleteReplayId").attr("id")).remove();
                                } else {
                                    $.msg("出现异常!", 3000);
                                    //$(".mark").hide();
                                    $(".delete").removeClass("active");
                                    $this.search();
                                }

                            }
                        });
                    }else{
                        $(".delete").removeClass("active");
                    }
                });

            });

            $(".alertbox .no").on("click", "", function () {
                $(".mark").hide();
                $(".delete").removeClass("active")
            });
            //删除动态
            //$(".mark1").on("click", ".yes", function () {
            //    $.ajax({
            //        url: rootPath + "/classModule/deleteDynamics",
            //        data: {"id": $(".deleteId").attr("id")},
            //        type: "post",
            //        dataType: "json",
            //        async: false,
            //        success: function (e) {
            //            if (e == "success") {
            //                $.msg("删除成功!", 3000);
            //                $(".mark").hide();
            //                $(".delete").removeClass("active");
            //                $(".status_" + $(".deleteId").attr("id")).remove();
            //            } else {
            //                $.msg("出现异常!", 3000);
            //                $(".mark").hide();
            //                $(".delete").removeClass("active");
            //            }
            //
            //        }
            //    });
            //});
            //删除动态的评论
            //$(".mark2").on("click", ".yes", function () {
            //    $.ajax({
            //        url: rootPath + "/classModule/deleteDynamicsReplay",
            //        data: {"id": $(".deleteReplayId").attr("id")},
            //        type: "post",
            //        dataType: "json",
            //        async: false,
            //        success: function (e) {
            //            if (e == "success") {
            //                $.msg("删除成功!", 3000);
            //                $(".mark").hide();
            //                $(".delete").removeClass("active");
            //                $(".dynamicsList").find(".replayId_" + $(".deleteReplayId").attr("id")).remove();
            //            } else {
            //                $.msg("出现异常!", 3000);
            //                $(".mark").hide();
            //                $(".delete").removeClass("active");
            //                $this.search();
            //            }
            //
            //        }
            //    });
            //});

//			    加载图片
//            var picture = function () {
//                var width, height, loaded = true;
//                $(".trend_pic img").each(function () {
//                    if ($(this).attr("loaded")) {
//                        return;
//                    }
//                    if ($(this)[0].complete) {
//                        $(this).attr("loaded", "true");
//                        loadPic($(this))
//                    }
//                    loaded = loaded && $(this)[0].complete;
//                })
//                if (loaded) {
//                    clearInterval(reloadPic);
//                }
//            }
//
//            var loadPic = function ($this) {
//                var width = $this.width();
//                var height = $this.height();
//                var h = width / height;
//                var xwidth = (50 * h - 50) / 2;
//                var xheight = (50 * h - 50) / 2;
//                if (h && h >= 1) {
//                    $(".trend_pic img").css({
//                        height: "50px",
//                        left: -xwidth
//                    })
//                } else {
//                    $(".trend_pic img").css({
//                        width: 50,
//                        top: -xheight
//                    })
//                }
//            }
//            reloadPic = setInterval(picture, 100);
            //绑定置顶取消置顶事件
            $(".dynamicsList").on('click', '.setTop', function () {
                var setTop = $(this).attr("setTop");
                var id = $(this).attr("id");
                $.ajax({
                    url: rootPath + "/classModule/dynamicsSetTop",
                    data: {
                        'topFlag': setTop,
                        'id': id
                    },
                    type: 'post',
                    success: function (e) {
                        if (e = "success") {
                            $this.search();
                        }
                    }
                });
            });
            //点击查看评论
            $(".dynamicsList").on('click', '.contentCount', function () {
                //判断评论是否为0
                if ($(this).attr("count") > 0) {
                    var id = $(this).attr("id");
                    //修改em信息
                    if ($(this).attr("isClick") == 0) {
                        $(this).attr("isClick", $(this).children("em").text());
                        $(this).children("em").text("收起评论");
                        $(".comment_" + id).slideDown();
                    } else {
                        $(this).children("em").text($(this).attr("isClick"));
                        $(this).attr("isClick", 0);
                        $(".comment_" + id).slideUp();
                    }
                    //判断是否首次点击
                    if ($(this).attr("clickOnce") == 0) {
                        $(this).attr("clickOnce", 1);
                        $this.searchComment($(".comment_" + id), id, 1);
                    }
                }
            });
            //展示更多
            $(".dynamicsList").on('click', '.replayMore', function () {
                var id = $(this).attr("id");
                var pageNo = parseInt($(this).attr("pageNo")) + 1;
                $this.searchComment($(".comment_" + id), id, pageNo);
                $(this).remove();
            });
            //回复评论
            $(".dynamicsList").on('click', '.answer', function () {
                var id = $(this).attr("id");
                var statusId = $(this).attr("statusId");
                //判断回复按钮是否有active
                if ($(this).hasClass("active")) {
                    $(this).removeClass("active");
                    $(".answer_" + id).remove();
                } else {
                    $(this).addClass("active");
                    $(".replayId_" + id).after(
                        '<div class="comment_content Y_clear answer_' + id + '">' +
                        '<div class="replaycontent" style="width: 780px;"><span>回复评论</span>' +
                        '&nbsp<span class="text-span checkText">您还可以输入140字</span></div>' +
                        '<textarea class="textarea replayArea" id="textArea_'+id+'" maxlength="140" style="resize:none" >' +
                        '</textarea>' +
                        '<div class="replayButton"><button class=" btn btn-primary answer-btn" statusId="' + statusId + '" parentId="' + id + '" disabled>确定</button></div>' +
                        '</div>'
                    );
                }
            });
            //监控回复文本框
            $(".dynamicsList").on('keyup', '.textarea', function () {
                var maxCount = 140;
                var len = $.trim(this.value).length;
                if (len > 0 && len <=maxCount) {
                    $(".answer-btn").prop("disabled", false);
                    $(".text-span").html('您还可以输入' + (maxCount - len) + '字');
                } else if (len > maxCount) {
                    $(".answer-btn").prop("disabled", true);
                    $(".text-span").html('您已经超出' + (len - maxCount) + '字');
                } else if (len == 0) {
                    $(".answer-btn").prop("disabled", true);
                    $(".text-span").html('您还可以输入' + (maxCount - len) + '字');
                }
            });
            //监控添加动态文本框
            $(".add-textarea").on('keyup', function () {
                var maxCount = 140;
                var len = $.trim(this.value).length;
                if (len > 0 && len <= maxCount) {
                    $(".add-btn").prop("disabled", false);
                    $(".add-span").html('您还可以输入<span class="num_140">' + (maxCount - len) + '</span>字');
                } else if (len > maxCount) {
                    $(".add-btn").prop("disabled", true);
                    $(".add-span").html('您已经超出<span class="num_140" style="color: #fa7d3c;">' + (len - maxCount) + '</span>字');
                } else if (len == 0) {
                    $(".add-btn").prop("disabled", true);
                    $(".add-span").html('您还可以输入<span class="num_140">' + (maxCount - len) + '</span>字');
                }
            });
            //添加回复的按钮click
            $(".dynamicsList").on('click', '.answer-btn', function () {
                var parentId = $(this).attr("parentId");
                var statusId = $(this).attr("statusId");
                var content = $("#textArea_"+parentId).val();
                var a=$(this);
                $.ajax({
                    url: rootPath + "/classModule/insertDynamicsReplay",
                    data: {
                        "statusId": statusId,
                        "parentId": parentId,
                        "content": content
                    },
                    type: "post",
                    dataType: "json",
                    async: false,
                    success: function (e) {
                        var replay = e.data[0];
                        if (replay) {
                            var id = replay.statusId;
                            $(".dynamicsList").find(".replayId_" + parentId).after(
                                '<div class="comment_content Y_clear replayId_' + replay.id + '">' +
                                '<div class="headicon">' +
                                '<a href=""><img src="' + (replay.userPic ? replay.userPic : rootPath + "/images/teachers.png") + '" alt="" width="50" height="50"/></a>' +
                                '</div>' +
                                '<div class="word">' +
                                '<div class="word_content">' +
                                (replay.userType == 1 ? '<i class="iconfont teachericon">&#xe657;</i>' : '') +
                                (replay.userName ? replay.userName : '匿名用户') +
                                (replay.parentId ? '@' + replay.parentName : '') +
                                '<span>' + replay.publishTimeString + '</span>' +
                                '<span>' + replay.publishTimeString2 + '</span>' +
                                '<span class="btns">' +
                                '<button class="delete delete2 " id="' + replay.id + '">' +
                                '删除' +
                                '</button>' +
                                '</span>' +
                                '</div>' +
                                '<p style="word-break: break-all">' +
                                replay.content +
                                '</p>' +
                                '</div>' +
                                '</div>'
                            );
                            var count=parseInt(a.parents(".Y_comment").prev().find(".contentCount").attr("count"))+1;
                            a.parents(".Y_comment").prev().find(".contentCount").attr("count",count);
                            a.parents(".Y_comment").prev().find(".contentCount").attr("isclick","评论（"+count+"）");
                            $(".dynamicsList").find(".answer_" + parentId).remove();
                            $(".answer").removeClass("active");
                        } else {
                            $.msg("出现异常!", 3000);
                            $this.search();
                        }

                    }
                });
            });
            //弹出添加动态层
            $(".Y_btn").on('click', function () {
                $(".addDynamics").popup("show");
                $(".num_140").text("140");
                $(".addForm")[0].reset();
                $(".img_div").remove();
                $(".clickUploadDiv").show();

            });
            //添加动态
            $(".add-btn").on('click', function () {
                var content = $(".add-textarea").val();
                var list = new Array;
                $(".addDynamics").find('.imgObject').each(function () {
                    list.push($(this).attr("ids"));
                });
                $.ajax({
                    url: rootPath + "/classModule/insertDynamics",
                    data: {
                        "content": content,
                        "list": list.join(",")
                    },
                    type: 'post',
                    success: function (jsonData) {
                        console.log(jsonData);
                        if (jsonData == "success") {
                            $.msg("发布成功", function () {
                                $this.search();
                            });
                            $(".addDynamics").popup("hide");
                        }
                    },
                    error:function(arg1,arg2,arg3){
                        console.log(arg1);
                        console.log(arg1);
                        console.log(arg1);
                    }
                });

            });
            //点击上传图片事件
            $(".clickUploadDiv").on('click',function(){
                $("#imgDatas").trigger("click");
            });
            //图片蒙层
            $(document).on('mouseover','.img_div',function(){
                $(this).find(".img_bg").show();
                $(this).find(".delete_div").show();
            }).on('mouseout','.img_div',function(){
                $(this).find(".img_bg").hide();
                $(this).find(".delete_div").hide();
            });
            //图片上的删除图片按钮点击事件
            $(document).on('click','.delete_div',function(){
                $(".clickUploadDiv").show();
                $(this).parent(".img_div").remove();
            })


        },
        search: function (page) {
            var $this = this;
            var data = {};
            data.page = page ? page : 1;
            $(".dynamicsList").html('');
            $.ajax({
                url: rootPath + "/classModule/dynamicsJson",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();

                },
                success: function (jsonData) {
                    if (jsonData.data.length == 0) {
                        $(".dynamicsList").html('<div class="empty" style="height: 330px;">暂时没有发布的动态</div>');
                    }
                    $.each(jsonData.data, function (index, dynamics) {
                        var img = '';
                        $.each(dynamics.picList, function (index, url) {
                            img = img + '<a href="' + url + '" class="Y_pic" data-lightbox="roadtrip'+dynamics.id+'">' +
                                '<img src="' + url + '" alt=""/>' +
                                '</a>';
                        });
                        $(".dynamicsList").append(
                            '<div class="Y_trend Y_clear status_' + dynamics.id + '">' +
                            '<div class="headicon">' +
                            '<img src="' + (dynamics.headpicUrl ? dynamics.headpicUrl : rootPath + "/images/teachers.png") + '" alt="" width="50" height="50"/>' +
                            '</div>' +
                            '<div class="Y_content">' +

                            '<p style="word-break: break-all">' +
                            (dynamics.topFlag == 1 ? '<em class="top">顶</em>' : '') +
                            dynamics.content +
                            '</p>' +
                            '<div class="trend_pic Y_clear">' +
                                //动态图片
                            img +
                            '</div>' +
                            '<p class="Y_time">' +
                            '<span class="Y_mr20">' + dynamics.publishTimeString + '</span>' +
                            '<span>' + dynamics.publishTime2String + '</span>' +
                            '</p>' +
                            '<p class="Y_icon">' +
                            '<span class="Praise Y_mr20">' +
                            '<i class="iconfont fnt_color">&#xe64e;</i>' +
                            '<em style="cursor: auto">赞 (' + dynamics.goodCount + ')</em>' +
                            '</span>' +
                            '<span class="comment contentCount" count="' + dynamics.commentCount + '" clickOnce="0" isClick="0" id="' + dynamics.id + '">' +
                            '<i class="iconfont fnt_color">&#xe64f;</i>' +
                            '<em>评论 (' + dynamics.commentCount + ')</em>' +
                            '</span>' +
                            '</p>' +
                            '<div class="Y_comment comment_' + dynamics.id + '" style="display :none;">' +
                            '</div>' +
                            '</div>' +
                            '<div class="btns">' +
                            '<button class="setTop"  id="' + dynamics.id + '" setTop="' + dynamics.topFlag + '">' + (dynamics.topFlag == 1 ? "取消置顶" : "置顶") + '</button>' +
                            '<button class="delete delete1" id="' + dynamics.id + '">删除</button>' +
                            '</div>' +
                            '<div class="Y_cb"></div>' +
                            '<hr/>' +
                            '</div>'
                        );
                    });
                    if (jsonData.rowCount > 10) {
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
                    } else {
                        $(".pagination").html('');
                    }
//                  加载图片
                    var picture=function(){
                        var width,height,loaded=true;
                        $(".trend_pic img").each(function(){
                            if($(this).attr("loaded")){
                                return;
                            }
                            if($(this)[0].complete){
                                $(this).attr("loaded","true");
                                loadPic($(this))
                            }
                            loaded=loaded && $(this)[0].complete;
                        })
                        if(loaded){
                            clearInterval(reloadPic);
                        }
                    }

                    var loadPic=function ($this){
                        var width=$this.width();
                        var height=$this.height();
                        var h=width/height;
                        var xwidth=(80*h-80)/2;
                        var xheight=(80*h-80)/2;
                        if(h && h>=1){
                        	$this.css({
                                height:"80px",
                                left:-xwidth
                            })
                        }else{
                        	$this.css({
                                width:80,
                                top:-xheight
                            })
                        }
                    }
                    reloadPic=setInterval(picture,100);
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
        },
        searchComment: function ($this, id, page) {
            $.ajax({
                url: rootPath + "/classModule/dynamicsReplayJson",
                data: {'id': id, 'page': page},
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (jsonData) {
                    $.each(jsonData.data, function (index, replay) {
                        $this.append(
                            '<div class="comment_content Y_clear replayId_' + replay.id + '">' +
                            '<div class="headicon">' +
                            '<img src="' + (replay.userPic ? replay.userPic : rootPath + "/images/teachers.png") + '" alt="" width="50" height="50"/>' +
                            '</div>' +
                            '<div class="word">' +
                            '<div class="word_content">' +
                            '<span>' +
                            (replay.userType == 1 ? '<i class="iconfont teachericon">&#xe657;</i>' : '') +
                            (replay.userName ? replay.userName : '匿名用户') +
                            (replay.parentId ? '@' + replay.parentName : '') +
                            '</span>' +
                            '<span>' + replay.publishTimeString + '</span>' +
                            '<span>' + replay.publishTimeString2 + '</span>' +
                            '<span class="btns">' +
                            (replay.isSelf == 0 ? '<button class="answer" id="' + replay.id + '" statusId="' + id + '">回复</button>' : '' ) +
                            '<button class="delete delete2 " id="' + replay.id + '">' +
                            '删除' +
                            '</button>' +
                            '</span>' +
                            '</div>' +
                            '<p style="word-break: break-all">' +
                            replay.content +
                            '</p>' +
                            '</div>' +
                            '</div>'
                        );
                    });
                    if (jsonData.pageNo < jsonData.pageCount) {
                        $this.append(
                            '<div class="slide slideup replayMore" id="' + id + '" pageNo="' + jsonData.pageNo + '">' +
                            '<a href="javascript:void(0);">展示更多</a>' +
                            '</div>'
                        );
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
        dynamics.init();

    })
})(jQuery)

//上传图片
function changeCyclePic(ele) {
        $.ajaxFileUpload({
            url: rootPath + "/classModule/UploadCycles" ,
            secureuri: false,// 安全协议
            async: false,
            fileElementId: 'imgDatas',
            dataType: 'json',
            type: "POST",
            success: function (data) {
                var num=0;
                $(".imgObject").each(function(){
                    num=num+1;
                });
                if(num>=5){
                    $(".clickUploadDiv").hide();
                }
                $(".clickUploadDiv").before(
                    '<div class="img_div"><div class="img_bg"></div>'+
                    '<a href="javascript:void(0);" class="delete_div"></a>'+
                    '<img class="imgObject" ids="' +
                    data.picPath + '" src="' + data.url + '" alt="" style=""/></div>');
            },
            error: function (resp, msg, err) {
                console.log(resp);
            },
//            loadingEle: $(".clickUploadDiv"),
            fileName: 'imgData'
        });
}