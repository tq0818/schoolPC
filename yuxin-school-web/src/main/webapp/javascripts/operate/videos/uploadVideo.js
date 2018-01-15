var outUrl = "";
var videoType = "";
var storageType = "";
var cate = "";
$(function () {
    $(document).ready(function () {
        init();
        height();
    }).on("click",".delres",function(){
    	var $this = $(this);
    	var id = $this.data("id");
    	/*var parent = $this.parent().parent();*/
    	$.ajax({
    		url: "/classTypeResource/delreslist",
    		type:"post",
    		data:{"id":id},
    		dataType:"json",
    		success:function(data){
    			if(data.msg == "success"){
    				$.msg("资源已删除");
    				$this.parent().parent().remove();
    			}else{
    				$.msg(data.msg);
    			}
    		}
    	});
    })/*.on('click',".uploadblvs",function(){
    	var id = $(this).data("id");
    	$("#blvsloadvideo").data("id",id);
    	addItems();
    })*/;

    function init() {
        var btypes = $("#membertype").val();
        $(".w-video").hide();
        $(".last_v").hide();
        $(".last_w").show();
        $(".last_s").hide();
        $(".scorms").hide();
        $("#fileupload").hide();
        $("#fileupload1").hide();
        $("#fileuploadscorm").hide();
        if (btypes == "qnvd") {
            $("#fileuploadqnvd").show();
            $("#uploadBLVS").remove();
            $("#fileupload").remove();
            $("#fileupload1").remove();
            $("#startbutton").remove();
            $("#blvsloadvideo").remove();
        } else if(btypes == "blvs"){
            $("#fileuploadqnvd").remove();
            $("#uploadBLVS").show();
            $("#fileupload").remove();
            $("#fileupload1").remove();
            $("#startbutton").remove();
            $("#qnuploadvideo").remove();
            //$("#addVideoBtn").remove();
            if(upload == null){
            	blveUploadify();
            }
            //blveUploadify();
        }else {
            $("#fileupload").show();
            $("#fileupload1").show();
            $("#fileuploadqnvd").remove();
            $("#uploadBLVS").remove();
            $("#qnuploadvideo").remove();
            $("#blvsloadvideo").remove();
        }
        $(".btn-method").click(function () {
            if ($(this).attr("data-type") == 0) {
            	$("div[name='ff']").show();
            	
            	$(".tips-ff").show();
                $(".w-video").hide();
                
                $(".last_v").hide();
                $(".last_w").show();
                $(".last_s").hide();
                
                $(".video_file").show();
                $(".video_url").hide();
                $(".video-msg").show();
                $(".scorms").hide();
                $("#fileuploadscorm").hide();
                if (btypes == "qnvd") {
                    $("#fileuploadqnvd").show();
                }else if(btypes == "qnvd"){
                    $("#uploadBLVS").show();
                } else{
                    $("#fileupload").show();
                    $("#fileupload1").show();
                }
            } else if ($(this).attr("data-type") == 1) {
            	$("div[name='ff']").hide();
            	
            	$(".tips-ff").hide();
                $(".w-video").show();
                
                $(".last_v").show();
                $(".last_w").hide();
                $(".last_s").hide();
                
                $(".video_file").hide();
                $(".video_url").show();
                $(".video-msg").show();
                $(".scorms").hide();
                $("#fileuploadqnvd").hide();
                $("#uploadBLVS").hide();
            } else {
            	$("div[name='ff']").show();
            	$(".tips-ff").show();
                $(".w-video").hide();
                
                $(".last_v").hide();
                $(".last_w").hide();
                $(".last_s").show();
                
                $(".video_file").show();
                $(".video_url").hide();
                $(".video-msg").hide();
                $(".scorms").show();
                $("#fileupload").hide();
                $("#fileupload1").hide();
                $("#fileuploadscorm").show();
                $("#fileuploadqnvd").hide();
                $("#uploadBLVS").hide();
            }
            $(".btn-method").addClass("btn-default").removeClass("btn-success");
            $(this).addClass("btn-success").removeClass("btn-default");
            height();
        });
        $("#btn-cancel").click(function () {
            $("#itemOneId").val(-1);
            $("#itemSecondId").val(-1);
            $("#tag").val("");
            $("#hh").val(0);
            $("#mm").val(0);
            $("#ss").val(0);
            $("#videourl").val("");
            $("#videoname").val("");
            outUrl = "";
            videoType = "";
        });
        
        $("#navBar li[for]").on("click", function (e) {
            var $this = $(this);
            $this.addClass("L-ac").siblings().removeClass("L-ac");
            $("#content>div[name=" + $this.attr("for") + "]").css("display", "block").siblings().css("display", "none");
            var acceptSource = $this.attr("name");
            var resourceUpload = $("#resourceUpload");
            var addResourceTcTips = $(".addResourceTc .tips-msg.tips-ff" );
            switch(acceptSource){
            case("flash"):{
            	resourceUpload.text("添加flash资源");
                cate="swf";
            	addResourceTcTips.text("允许上传文件格式为：swf");
                $("#resourceUploadInput").attr("accept",".swf");
            	$("#resourceSubmit").off("click").on("click",function(){
            		selajaxstroage("#pickfiles");
            	});
            	break;
            }
            case("audio"):{
            	resourceUpload.text("添加音频资源");
                cate="mp3";
            	addResourceTcTips.text("允许上传文件格式为：mp3");
            	$("#resourceSubmit").off("click").on("click",function(){
                    $("#resourceUploadInput").attr("accept",".mp3");
            		selajaxstroage("#audiofiles");
            	});
            	break;
            }
            case("ppt"):{
            	resourceUpload.text("添加ppt资源");
            	cate = "ppt";
            	addResourceTcTips.text("允许上传文件格式为：ppt,pptx");
            	$("#resourceSubmit").off("click").on("click",function(){
                    $("#resourceUploadInput").attr("accept",".ppt,pptx");
            		selajaxstroage("#pptfiles");
            	});
            	break;
            }
            case("docs"):{
            	resourceUpload.text("添加文档资源");
            	cate = "docs";
            	addResourceTcTips.text("允许上传文件格式为：doc,pdf,xls,docx,xlsx");
            	$("#resourceSubmit").off("click").on("click",function(){
                    $("#resourceUploadInput").attr("accept",".doc,pdf,xls,docx,xlsx");
            		selajaxstroage("#docfiles");
            	});
            	break;
            }
            }
        });

        $(".btn-add-url").click(function () {
            var url = $("#videourl").val();
            if (typeof (url) == "undefined" || url == null || url == "") {
                $('<div class="c-fa" style="z-index;102;">请填入外部视频连接地址</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                    $(this).remove();
                });
                return false;
            }
            //截取网址域
            if (url.indexOf("http://") >= 0) {
                url = url.substring(7);
            } else if (url.indexOf("https://") >= 0) {
                url = url.substring(8);
            } else{
                $('<div class="c-fa" style="z-index;102;">请添加协议头,http://或https://</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                    $(this).remove();
                });
                return false;
            }
            var httpTitle = url.substring(0, url.indexOf("/"));
            videoType = httpTitle;
            if (httpTitle == "v.youku.com") {
                storageType = "VIDEO_STORAGE_TYPE_YK";
                //截取优酷的
                if (url.indexOf("id_") >= 0) {
                    if (url.indexOf("==") >= 0) {
                        outUrl = (url.substring(url.indexOf("id_") + 3, url.indexOf("=")));
                    } else {
                        outUrl = (url.substring(url.indexOf("id_") + 3, url.lastIndexOf(".html")));
                    }
                } else {
                    $('<div class="c-fa" style="z-index;102;">您输入的地址不合法</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                        $(this).remove();
                    });
                    return false;
                }
            } else if (httpTitle == "www.tudou.com") {
                storageType = "VIDEO_STORAGE_TYPE_TD";
                //截取土豆的
                if (url.indexOf("albumplay/") >= 0) {
                    outUrl = "a/" + (url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html")));
                } else if (url.indexOf("listplay/") >= 0) {
                	if(url.indexOf(".html") >= 0){
                        outUrl = "l/" + (url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html")));
                	}else{
                		outUrl = url.substring(url.indexOf("listplay/") + ("listplay/").length);
                		outUrl = "l/" + (outUrl.substring(url.indexOf("/") + 1, outUrl.lastIndexOf("/")));
                	}
                } else if (url.indexOf("view/") >= 0) {
                    outUrl = (url.substring(url.indexOf("view/") + ("view/").length));
                    if(outUrl.indexOf("/") >= 0){
                    	outUrl = "v/" + (outUrl.substring(0,outUrl.indexOf("/")));
                    }else{
                    	outUrl = "v/" + outUrl;
                    }
                } else {
                    $('<div class="c-fa" style="z-index;102;">您输入的地址不合法</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                        $(this).remove();
                    });
                    return false;
                }
            } else if (httpTitle == "console.video.capitalcloud.net") {
                storageType = "VIDEO_STORAGE_TYPE_SS";
                //截取石山的
                if (url.indexOf("entryId=") >= 0) {
                    outUrl = (url.substring(url.indexOf("entryId=") + 8, url.indexOf("&pubId=")));
                    $('<div class="c-fa" style="z-index;102;">已添加外部链接</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                        $(this).remove();
                    });
                    return false;
                } else {
                    $('<div class="c-fa" style="z-index;102;">您输入的地址不合法</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                        $(this).remove();
                    });
                    return false;
                }
            } else if(httpTitle == "qzonestyle.gtimg.cn"){
				storageType = "VIDEO_STORAGE_TYPE_QQ";
				var o1 = url.substring(url.indexOf("&file_id=")+9,url.indexOf("&app_id="));
				var o2 = url.substring(url.indexOf("&app_id=")+8,url.indexOf("&version="));
				outUrl = o1 + "," + o2;
			} else {
                $('<div class="c-fa" style="z-index;102;">您输入的地址不合法</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                    $(this).remove();
                });
                return false;
            }
            $.ajax({
                url: rootPath + "/video/selVideoName",
                type: "post",
                data: {
                    "url": $("#videourl").val(),
                    "domain": httpTitle
                },
                dataType: "json",
                beforeSend: function (XMLHttpRequest) {
                        $(".addVideoTc").css("z-index", 2);
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                success: function (data) {
                    if (data.msg != "error") {
                        $("#videoname").val(data.msg);
                        $('<div class="c-fa" style="z-index;102;">已添加外部链接</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                            $(this).remove();
                        });
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".addVideoTc").css("z-index", 101);
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
        });

        $("#btn-save").click(function () {
            var oneItemId = $("#itemOneId").val();
            var twoItemId = $("#itemSecondId").val();
            var videoName = $("#videoname").val();
            var url = $("#videourl").val();
            var hh = $("#hh").val() == "" ? 0 : $("#hh").val();
            var mm = $("#mm").val() == "" ? 0 : $("#mm").val();
            var ss = $("#ss").val() == "" ? 0 : $("#ss").val();
            /* alert(oneItemId + "\n" + twoItemId + "\n" + videoName + "\n" + url + "\n" + outUrl + "\n" + videoType); */
            // || twoItemId == "" || twoItemId < 0 || twoItemId == null
            if (oneItemId == null  || oneItemId == "" || oneItemId < 0 ) {
                $('<div class="c-fa" style="z-index;102;">请选择科目</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                    $(this).remove();
                });
                return false;
            }
            // || outUrl == "" || videoType == ""
            if (url == "" ) {
                $('<div class="c-fa" style="z-index;102;">请添加外部链接地址</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                    $(this).remove();
                });
                return false;
            }
            if (videoName == "") {
                $('<div class="c-fa" style="z-index:102;">请添加视频名称</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                    $(this).remove();
                });
                return false;
            }
            if (!$("#protocol").prop("checked")) {
                $('<div class="c-fa" style="z-index:102;">您尚未同意上传协议</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                    $(this).remove();
                });
                return false;
            }
            if (hh == 0 && mm == 0 && ss == 0) {
                $('<div class="c-fa" style="z-index:102;">请填写视频时长</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                    $(this).remove();
                });
                return false;
            }
            //保存到video
            var videoTime = (hh.length == 2 ? hh : "0" + hh) + ":" + (mm.length == 2 ? mm : "0" + mm) + ":" + (ss.length == 2 ? ss : "0" + ss);
            $.ajax({
                url: rootPath + "/video/saveVideoUrl",
                type: "post",
                data: {
                    "videoName": videoName,
                    "videoTime": videoTime,
                    "videoStatus": "VIDEO_PROCESS_NOMAL",
                    "itemOneId": oneItemId,
                    "itemSecondId": twoItemId,
                    "videoTag": $("#tag").val(),
                    "webVideoId": outUrl,
                    "webVideoDomain": videoType,
                    "storageType": storageType
                },
                dataType: "json",
                beforeSend: function (XMLHttpRequest) {
                        $(".addVideoTc").css("z-index", 2);
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (data) {
                        if (data.msg == "success") {
                            var domain = "";
                            /*if(videoType == "open.163.com"){
	    					domain = "网易公开课";
	    				}else */
                            if (videoType == "v.youku.com") {
                                domain = "优酷视频";
                            } else if (videoType == "www.tudou.com") {
                                domain = "土豆视频";
                            } else if (videoType == "console.video.capitalcloud.net") {
                                domain = "石山视频";
                            }
                            $(".v_url").prepend("<tr data-id='" + data.id + "'><td><div class='operate_w'>" + videoName + "</div></td><td>" + domain + "</td><td>正常</td><td><a class='btn btn-sm btn-del' href='javascript:;' data-id='" + data.id + "'>删除</a></td></tr>");
                            $(".btn-del").click(function () {
                                var vid = $(this).attr("data-id");
                                $.ajax({
                                    url: rootPath + "/video/delVideoUrl",
                                    type: "post",
                                    data: {
                                        "id": vid
                                    },
                                    dataType: "json",
                                    beforeSend: function (XMLHttpRequest) {
                                            $(".loading").show();
                                            $(".loading-bg").show();
                                        },
                                        success: function (data) {
                                            if (data.msg == "success") {
                                                $('<div class="c-fa" style="z-index:102;">已删除</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                    $(this).remove();
                                                });
                                                $("tr[data-id='" + vid + "']").remove();
                                            } else {
                                                $('<div class="c-fa" style="z-index:102;">删除失败</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                                                    $(this).remove();
                                                });
                                            }
                                        },
                                        complete: function (XMLHttpRequest, textStatus) {
                                            $(".loading").hide();
                                            $(".loading-bg").hide();
                                        }
                                });
                            });

                            $(".tcBg").fadeOut(200, function () {
                                $(this).remove();
                            });
                            $(".addVideoTc").hide();
                            $("#itemOneId").val(-1);
                            $("#itemSecondId").val(-1);
                            $("#tag").val("");
                            $("#hh").val(0);
                            $("#mm").val(0);
                            $("#ss").val(0);
                            $("#videourl").val("");
                            $("#videoname").val("");
                            outUrl = "";
                            videoType = "";
                        } else {
                            $(".addVideoTc").css("z-index", 101);
                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".addVideoTc").css("z-index", 101);
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
            });
        });
    }


    $('#scormbutton').click(function () {
        if (!$("#itemOneId").val()) {
            $(".item_msg").html("请选择学科");
            return false;
        }
        if (!$("#itemSecondId").val()) {
            $(".item_msg").html("请选择学科小类");
            return false;
        }
        if (!$("#protocol").prop("checked")) {
            $(".protocol_msg").html("您尚未同意上传协议");
            return false;
        }
        $(".protocol_msg").html("");
        $(".item_msg").html("");

        $('#filescorm').click();
        $('.addVideoTc').find('i.close').click();
    });
});

function cancle(ele) {
    //获取当前取消的CC视频的ID
    var ccvid = $(ele).parents(".template-upload").data('data').video.ccvid;
    //根据视频ID从数据中库中删除
    var uri = rootPath + '/video/del/' + ccvid;
    $.ajax({
        url: uri,
        async: false,
        type: "post",
        data: {
            "ccvid": ccvid,
        },
        cache: false,
        error: function (data) {
                alert("取消出现错误!");
            },
            success: function (result) {

            }
    });
}

function height() {
    var docheight = -parseInt($(".addVideoTc").height());
    var divheight = parseInt(parseInt($(".check_student_hd").height()) + parseInt($(".check_student_bd").height()));
    $(".addVideoTc").height(divheight);
    $(".addVideoTc").css("top", "0px");
    $(".addVideoTc").css("margin-top", (parseInt((docheight - divheight) / 2)) + "px");
}

function uploadscorm() {
    var f = document.getElementById("filescorm").files;
    var name = f[0].name;
    var size = bytesToSize(f[0].size);
    var tag = $("#tag").val();
    var itemOneId = $("#itemOneId").val();
    var itemSecondId = $("#itemSecondId").val();
    var html = "";
    html += "<tr><td width='480'>" + name + "</td>";
    html += "<td width='100'>" + size + "</td>";
    html += "<td width='300'><span style='color:red;'>正在上传    " +
        "<img src='" + rootPath + "/images/jia.jpg' height='16px' width='16px'></span></td>";
    html += "<td width='190'></td></tr>";
    $("#fileuploadscorm table tbody").append(html);
    var obj = $("#fileuploadscorm table tbody tr:last");
    $.ajaxFileUpload({
        url: rootPath + "/video/scormupload;" + window["sessionName"] + "=" + window["sessionId"],
        type: "post",
        secureuri: false,
        fileElementId: "filescorm",
        dataType: "json",
        success: function (data) {
            if (data.msg == 'success') {
                addvideoinfo(name, itemOneId, itemSecondId, tag, data.url, "VIDEO_STORAGE_TYPE_SCORM", obj, data.size);
            } else {
                obj.find("td:eq(2)").html("<span style='color:red;'>" + data.msg + "</span>");
            }
        }
    });
}

function bytesToSize(bytes) {
    if (bytes === 0) {
        return '0 B';
    }
    var k = 1024, // or 1024
        sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
        i = Math.floor(Math.log(bytes) / Math.log(k));
    return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
}

function addvideoinfo(videoName, oneItemId, twoItemId, tag, url, storageType, obj, size,$res) {
    $.ajax({
        url: rootPath + "/video/saveVideoUrl",
        type: "post",
        data: {
            "videoName": videoName,
            "videoStatus": "VIDEO_PROCESS_NOMAL",
            "itemOneId": oneItemId,
            "itemSecondId": twoItemId,
            "videoTag": tag,
            "webVideoId": url,
            "storageType": storageType,
            "vodeoSize": size
        },
        dataType: "json",
        success: function (data) {
            if (data.msg == "success") {
                obj.attr("id", "tr_" + data.id);
                obj.find("td:eq(2)").html("<span >上传完成</span>");
                obj.find("td:eq(3)").html('<a href="javascript:;" data-id="' + data.id + '" ' +
                    'class="btn btn-mini btn-primary delres">删除</a>');
            } else {
                obj.find("td:eq(2)").html("<span style='color:red;'>" + data.msg + "</span>");
            }
        }
    });
}

function delscorm(id) {
    $.ajax({
        url: rootPath + "/video/delVideoUrl",
        type: "post",
        data: {
            "id": id
        },
        dataType: "json",
        beforeSend: function (XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
            success: function (data) {
                if (data.msg == "success") {
                    $('<div class="c-fa" style="z-index:102;">已删除</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
                        $(this).remove();
                    });
                    $("#tr_" + id).remove();
                } else {
                    $('<div class="c-fa" style="z-index:102;">删除失败</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200, function () {
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

function resourcechange(e) {
    var f = $("#resourceUploadInput")[0].files;
    for(var i=0;i<f.length;i++){
        if(cate=="swf"){
            var name = f[i].name;
            var fileText =name.substring(name.lastIndexOf(".")+1,name.length);
            var fileName =fileText.toLowerCase();
            if(fileName!="swf"){
                alert("请上传swf格式的文件")
                return;
            }else{updocs(f[i]);}
        }else if(cate=="mp3"){
            var name = f[i].name;
            var fileText =name.substring(name.lastIndexOf(".")+1,name.length);
            var fileName =fileText.toLowerCase();
            if(fileName!="mp3"){
                alert("请上传mp3格式的文件")
                return;
            }else{updocs(f[i]);}
        }else if(cate=="ppt"){
            var name = f[i].name;
            var fileText =name.substring(name.lastIndexOf(".")+1,name.length);
            var fileName =fileText.toLowerCase();
            if(fileName!="ppt"){
                alert("请上传ppt格式的文件")
                return;
            }else{updocs(f[i]);}
        }else if(cate=="docs"){
            var name = f[i].name;
            var fileText =name.substring(name.lastIndexOf(".")+1,name.length);
            var fileName =fileText.toLowerCase();
            if(fileName=="doc" || fileName=="pdf" || fileName=="xls"||fileName=="docx"||fileName=="xlsx"){
                //
                updocs(f[i]);
            }else{
                alert("请上传doc,pdf,xls,docx,xlsx格式的文件")
                return;
            }
        }
    }
}

function updocs(f){
	var name = f.name;
    var size = bytesToSize(f.size);
    var tag = $("div.addResourceTc #tag").val();
    var itemOneId = $("div.addResourceTc #itemOneId").val();
    var itemSecondId = $("div.addResourceTc #itemSecondId").val();
    var html = "";
    html += "<tr><td width='480'>" + name + "</td>";
    html += "<td width='100'>" + size + "</td>";
    html += "<td width='300'><span style='color:red;'>正在上传    " +
        "<img src='" + rootPath + "/images/jia.jpg' height='16px' width='16px'></span></td>";
    html += "<td width='190'></td></tr>";
    html = $(html);
    $("table[name=resourceList] tbody").append(html);
    var uuid = guid();
    $.ajaxFileUpload({
        url: rootPath + "/classTypeResource/uploadToSwf;" + window["sessionName"] + "=" + window["sessionId"],
        data: {
            'oneItemId': itemOneId,
            'twoItemId': itemSecondId,
            'tag': tag,
            'uuid': uuid
        },
        type: "post",
        secureuri: false,
        async: false,
        fileElementId: "resourceUploadInput",
        dataType: "json",
        success: function (data) {
            if (data.msg == 'success') {
            	html.attr("id","restr_"+data.id);
                html.find("td:eq(2)").html("<span >上传完成</span>");
                html.find("td:eq(3)").html('<a href="javascript:;" data-id="' + data.id + '" ' +
                    'class="btn btn-mini btn-primary delres">删除</a>');
            } else {
            	html.find("td:eq(2)").html("<span style='color:red;'>" + data.msg + "</span>");
            }
        }
    });
}

var upload;
function blveUploadify(){
	var obj = {
	        uploadButtton: "BLVSFiles",   //打开上传控件按钮id
	        userid : $("#userid").val(),
	        ts : $("#ts").val(),
	        hash : $("#hash").val(),
	        cataid:'1',
	        extra:{state:''},
	        sign: $("#sign").val()
	};
	upload = new PolyvUpload(obj);
	setInterval(function (){
		$.ajax({
			url : rootPath + "/video/validateBLVS",
			type:"post",
			dataType:"json",
			success:function(data){
				upload.update({
					userid:data.userid,
					ts:data.ts,
					hash:data.hash,
					sign:data.sign
				});
			}
		});
	},(3*60*1000));
}

function selajaxstroage(ele){
	if(!$("div.addResourceTc #itemOneId").val()){
		$("div.addResourceTc .item_msg").html("请选择学科");
		return false;
	}
	// if(!$("div.addResourceTc #itemSecondId").val()){
	// 	$("div.addResourceTc .item_msg").html("请选择学科小类");
	// 	return false;
	// }
	if(!$("div.addResourceTc #protocol").prop("checked")){
		$("div.addResourceTc .protocol_msg").html("您尚未同意上传协议");
		return false;
	}
	$("div.addResourceTc .protocol_msg").html("");
    $('div.addResourceTc i.close').click();
    $("#resourceUploadInput").click();
}

function resourceValidate(up,file){
	$.ajax({
		url:rootPath + "/classTypeResource/selSumNum",
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg != "success"){
				var $this = $("#"+file.id);
				$this.find("td:eq(2)").html("<span style='color:red;'>" + data.msg + "</span>");
				up.removeFile(file);
			}
		}
	});
}

function getMB(bytes) {
    if (bytes === 0) {
        return 0;
    }
    var mb = (1024 * 1024), // or 1024
        i = Math.floor(Math.log(bytes) / Math.log(mb));
    return (bytes / Math.pow(mb, i)).toPrecision(3);
}

function getKey(name){
	var suffix = name.substring(parseInt(name.lastIndexOf(".") + 1));
    var key = "";
    key = $("#companyId").val() + "/video" +
    	"/" + guid() + "." + suffix;
    return key;
}

function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}