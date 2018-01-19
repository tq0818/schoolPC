(function ($) {
    var comment = {
        init: function () {
            var $this = this;
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
            //点击老师搜索评论
            $(".teahcerName").on("click", ".tName", function () {
                $(".btn-success").removeClass("btn-success");
                $(this).addClass("btn-success");
                var id = $(this).attr("id");
                $(".clickTeacherId").attr("id", id);
                $this.search(null, id);
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
            //前端js
            //删除
            $(".Y_background").on("click", ".delete1", function () {
                $(this).addClass("active");
                $(".mark1").show()
            });
            $(".Y_background").on("click", ".delete2", function () {
                var a=$(this);
                $(this).addClass("active");
                //$(".mark2").show();
                var id = $(this).attr("id");
                $(".deleteId").attr("id", id);
                $.confirm("您是否确定删除该条评论",function(result){
                    if(result){
                        $.ajax({
                            url: rootPath + "/classModule/deleteComment",
                            data: {"id": $(".deleteId").attr("id")},
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (e) {
                                if (e == "success") {
                                    $.msg("删除成功!", 3000);
                                    //$(".mark").hide();
                                    $(".delete").removeClass("active");
                                    a.parents(".Y_clear").remove();
                                } else {
                                    $.msg("出现异常!", 3000);
                                  //  $(".mark").hide();
                                    $(".delete").removeClass("active");
                                    $this.search(null, $(".clickTeacherId").attr("id"));
                                }

                            }
                        });
                    }else{
                        $(".delete").removeClass("active");
                    }
                });
            });
            $(".mark2").on("click", ".yes", function () {
                $.ajax({
                    url: rootPath + "/classModule/deleteComment",
                    data: {"id": $(".deleteId").attr("id")},
                    type: "post",
                    dataType: "json",
                    async: false,
                    success: function (e) {
                        if (e == "success") {
                            $.msg("删除成功!", 3000);
                            $(".mark").hide();
                            $(".delete").removeClass("active");
                            $this.search(null, $(".clickTeacherId").attr("id"));
                        } else {
                            $.msg("出现异常!", 3000);
                            $(".mark").hide();
                            $(".delete").removeClass("active");
                            $this.search(null, $(".clickTeacherId").attr("id"));
                        }

                    }
                });
            });
            $(".alertbox .no").on("click", "", function () {
                $(".mark").hide();
                $(".delete").removeClass("active")
            })

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
            $(document).on('click','.teacherName',function(){
                var teacherId=$(this).attr("teacherId");
                $this.search(1,teacherId);
                var name=$(document).find("#"+teacherId).text();
                $(document).find("#"+teacherId).remove();
                $(".searchAllTeacher").removeClass("btn-success");
                $(document).find(".searchAllTeacher").parents("li").after(
                    '<li><a class="tName btn btn-mini btn-default btn-success" id="' + teacherId + '">' + name + '</a></li>'
                );

            });
            // 初始化数据
            
            //--4.2
            var _tid = $.getUrlParam('ti'),
            	_id  = $.getUrlParam('i');
            if(_tid){
            	$this.search(1,null,_id);
            	$this.searchTeacher(_tid);
            }else{
            	$this.search(1);
            	$this.searchTeacher(null);
            }
        },
        search: function (page, teacherId,id) {
            var $this = this;
            var data = {};
            data.teacherId = teacherId;
            data.id = id;
            data.page = page ? page : 1;
            $(".comment_all").html('');
            $.ajax({
                url: rootPath + "/classModule/manageCommentJson",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (jsonData) {
                    if (jsonData.data.length == 0) {
                        $(".comment_all").html('<div class="empty" style="height: 330px;">亲，这个老师还没有被评论</div>');
                    }
                    var scorehtml='<span>评分:</span>' +
                        '<span class="Y_mr10" style="color: #fb9f1b;">' +
                        '<i class="iconfont">&#xe65f;</i>'+
                        '<i class="iconfont">&#xe65f;</i>'+
                        '<i class="iconfont">&#xe65f;</i>'+
                        '<i class="iconfont">&#xe65f;</i>'+
                        '<i class="iconfont">&#xe65f;</i>' +
                        '</span>';
                    $.each(jsonData.data, function (index, comment) {
                        var score=comment.score?comment.score:0;
                        if(score==1){
                            scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65f;</i>'+
                                '<i class="iconfont">&#xe65f;</i>'+
                                '<i class="iconfont">&#xe65f;</i>'+
                                '<i class="iconfont">&#xe65f;</i></span>';
                        }else if(score==2){
                            scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65f;</i>'+
                                '<i class="iconfont">&#xe65f;</i>'+
                                '<i class="iconfont">&#xe65f;</i></span>';
                        }else if(score==3){
                            scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65f;</i>'+
                                '<i class="iconfont">&#xe65f;</i></span>';
                        }else if(score==4){
                            scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65f;</i></span>';
                        }else if(score==5){
                            scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i>'+
                                '<i class="iconfont">&#xe65e;</i></span>';
                        }
                        $(".comment_all").append(
                            '<li class="Y_clear">' +
                            '<div class="headpic">' +
                            '<img src="' + (comment.userImage ? comment.userImage : rootPath + "/images/teachers.png") + '" alt="" width="50" height="50"/>' +
                            '</div>' +
                            '<div class="Y_backcomment_content">' +
                            '<div class="word Y_clear">' +
                            '<span>' + comment.userName + '：</span>' +
                            '<span class="wordcontent" style="word-break:break-all">' + comment.comment + '</span>' +
                            '</div>' +
                            '<p class="Y_time Y_mt10">' +
                            '<span>' + comment.createTimeText + '</span>' +
                            '<span>' + comment.createTime2Text + '</span>' +
//                            '<span><em>源自：</em>' + (comment.fromId > -1 ? comment.from + '</span>' +
//                            '<span><em>章节：</em>' + (comment.videoChapter ? comment.videoChapter : '') + (comment.videoLecture ? comment.videoLecture : '') + '</span>'
//                                : '老师评论</span>') +
                            scorehtml +
                            '<span>老师:'+'<a href="javascript:void(0);" class="teacherName" teacherId="'+comment.teacherId+'">'+comment.teacherName+'</a></span>'+
                            '</p>' +
                            '</div>' +
                            '<button class="delete delete2"  id="' + comment.id + '">删除</button>' +
                            '</li>'
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
                                    var teacherId = $(".clickTeacherId").attr("id");
                                    $this.search(pageNo, teacherId);
                                }
                            });
                    } else {
                        $(".pagination").html('');
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
        },
        searchTeacher: function (_tid) {
        	var _index ;
          /*  $(".teahcerName").html("");*/
            $.ajax({
                url: rootPath + "/classModule/teacherJson",
                type: "post",
                dataType: "json",
                async: false,
                success: function (jsonData) {
                    $(".teahcerName").append('<li><a class="searchAllTeacher btn btn-mini btn-default" id="" >全部</a></li>');
                    $.each(jsonData, function (index, teacher) {
                    	if(_tid==teacher.id) _index = index;
                        $(".teahcerName").append(
                            '<li><a class="tName btn btn-mini btn-default '+(_tid==teacher.id?'btn-success':'')+'" id="' + teacher.id + '">' + teacher.name + '</a></li>'
                        );
                    });
                    if(!_tid){
                    	$(document).find(".searchAllTeacher").addClass("btn-success");
                    }else{
                    	if(_index && _index>8) $(".right span").trigger('click');
                    }
                }
            });
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
                        $(".comment_all").html('');
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
            $(".comment_all").html('');
            $(".pagination").html('');
        }
    };
    $(document).ready(function () {
        comment.init();
    })
})(jQuery)