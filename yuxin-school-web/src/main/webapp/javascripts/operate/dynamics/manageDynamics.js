(function ($) {
    var dynamics = {
        init: function () {
            var $this = this;
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
                                    //$(".mark").hide();
                                    $(".delete").removeClass("active");
                                    $(".statusId_" + $(".deleteId").attr("id")).remove();
                                } else {
                                    $.msg("出现异常!", 3000);
                                    //$(".mark").hide();
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
                                    $(".mark").hide();
                                    $(".delete").removeClass("active");
                                    var count=parseInt(a.parents(".Y_comment").prev().find(".contentCount").attr("count"))-1;
                                    a.parents(".Y_comment").prev().find(".contentCount").attr("count",count);
                                    a.parents(".Y_comment").prev().find(".contentCount").attr("isclick","评论（"+count+"）");
                                    $(".dynamicsList").find(".replayId_" + $(".deleteReplayId").attr("id")).remove();
                                } else {
                                    $.msg("出现异常!", 3000);
                                    $(".mark").hide();
                                    $(".delete").removeClass("active");
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
            //                $(".statusId_" + $(".deleteId").attr("id")).remove();
            //            } else {
            //                $.msg("出现异常!", 3000);
            //                $(".mark").hide();
            //                $(".delete").removeClass("active");
            //            }
            //
            //        }
            //    });
            //});
            ////删除动态的评论
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
            //搜索栏回车时搜索老师
            $(".searchTeacher").on("keydown", function (event) {
                if (event.keyCode == 13) {
                    $this.searchTeacherName();
                }

            });
            //搜索按钮点击时搜索
            $(".search-icon").on("click", function () {
                $this.searchTeacherName();
            });
            //点击老师搜索动态
            $(".teahcerName").on("click", ".tName", function () {
                $(".searchAllTeacher").removeClass("btn-success");
                $(".tName").removeClass("btn-success");
                $(this).addClass("btn-success");
                $this.search(1);
            })
                //点击显示全部老师
                .on("click", ".showAllTeacher", function () {
                    $this.searchTeacher();
                    $this.search(1);
                    $(".searchTeacher").val("");
                })
                //点击全部按钮
                .on('click', '.searchAllTeacher', function () {
                    $(".btn-success").removeClass("btn-success");
                    $(this).addClass("btn-success");
                    $this.search(1);
                    $this.searchTeacher();
                    $(".searchTeacher").val("");
                });
            //改变搜索类型 1.最新 2.热门
            $(".searchType").on('click', function () {
                $(".searchType").removeClass("click");
                $(this).addClass("click");
                $this.search(1);
            });
            //改变收起与展开
            $(".right").on('click', 'span', function () {
                if ($(this).attr("hide-div") == "showRight") {
                    $(".showRight").hide();
                    $(".hideRight").show();
                    $(".middle").css("height", "90px");
                    $(".middle").css("overflow-y", "scroll");
                } else {
                    $(".hideRight").hide();
                    $(".showRight").show();
                    $(".middle").css("height", "29px");
                    $(".middle").css("overflow-y", "hidden");
                    $(".middle").scrollTop(0);
                }
            });
            //点击老师名字查询老师评论
            $(document).on('click','.teacherLink',function(){
                var teacherId=$(this).attr("teacherId");

                var name=$(document).find("#"+teacherId).text();
                $(document).find("#"+teacherId).remove();
                $(".searchAllTeacher").removeClass("btn-success");
                $(".tName").removeClass("btn-success");
                $(document).find(".searchAllTeacher").parents("li").after(
                    '<li><a class="tName btn btn-mini btn-default btn-success" id="' + teacherId + '">' + name + '</a></li>'
                );
                $this.search(1);
            });
            // 初始化数据
            $this.searchTeacher();
            $this.search(1);

        },
        search: function (page) {
            var $this = this;
            var data = {};
            data.searchType = $(".click").attr("searchType");
            var idList = new Array;
            $(".btn-success").each(function () {
                idList.push($(this).attr("id"));
            });
            data.teacherIdList = idList.join(",");
            data.page = page ? page : 1;
            $(".dynamicsList").html('');
            $.ajax({
                url: rootPath + "/classModule/manageDynamicsJson",
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
                                '<img src="' + url + '" alt="" />' +
                                '</a>';
                        });
                        $(".dynamicsList").append(
                            '<div class="Y_trend Y_clear statusId_' + dynamics.id + '">' +
                            '<div class="headicon">' +
                            '<a class="teacherLink" teacherId="'+dynamics.teacherId+'" href="javascript:void(0);" >' +
                            '<img src="' + (dynamics.headpicUrl ? dynamics.headpicUrl : rootPath + "/images/teachers.png") + '" alt="" width="50" height="50"/>' +
                            '<br>' +
                            '<em>' + dynamics.name + '</em>' +
                            '</a>' +
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
                            if($(this).prop("loaded")){
                                return;
                            }
                            if($(this)[0].complete){
                                $(this).prop("loaded","true");
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
                url: rootPath + "/classModule/manageDynamicsReplayJson",
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
        },
        searchTeacher: function () {
            $(".teahcerName").html("");
            $.ajax({
                url: rootPath + "/classModule/teacherJson",
                type: "post",
                dataType: "json",
                async: false,
                success: function (jsonData) {
                    $(".teahcerName").append('<li><a class="searchAllTeacher btn btn-mini btn-default" id="" >全部</a></li>');
                    $(document).find(".searchAllTeacher").addClass("btn-success");
                    $.each(jsonData, function (index, teacher) {
                        $(".teahcerName").append(
                            '<li><a class="tName btn btn-mini btn-default" id="' + teacher.id + '">' + teacher.name + '</a></li>'
                        );
                    });

                }
            });
            if($('.teahcerName').height()>30){
        		$('.showRight').show();
        	}else{
        		$('.showRight').hide();
        	}
        },
        searchTeacherName: function () {
            var searchTeacher = $(".searchTeacher").val();
            $(".teahcerName").html('');
            $.ajax({
                url: rootPath + "/classModule/searchTeacherJson",
                type: "post",
                data: {"search": searchTeacher},
                dataType: "json",
                async: false,
                success: function (jsonData) {
                    if (jsonData.length == 0) {
                        $(".teahcerName").append(
                            '<li class="showAllTeacher"><a class="btn btn-mini btn-default">亲，没有这个老师，点我显示全部老师</a></li>'
                        );
                        $(".dynamicsList").html('');
                        $(".pagination").html('');
                    } else {
                        $(".teahcerName").append('<li><a class="searchAllTeacher btn btn-mini btn-default" id="" >全部</a></li>');
                        $.each(jsonData, function (index, teacher) {
                            $(".teahcerName").append(
                                '<li><a class="tName btn btn-mini btn-default" id="' + teacher.id + '">' + teacher.name + '</a></li>'
                            );
                        });
                        $(".tName").eq(0).trigger('click');
                    }
                }
            });
            if($('.teahcerName').height()>30){
        		$('.showRight').show();
        	}else{
        		$('.showRight').hide();
        	}
        }

    }
    $(document).ready(function () {
        dynamics.init();

    })
})(jQuery)

