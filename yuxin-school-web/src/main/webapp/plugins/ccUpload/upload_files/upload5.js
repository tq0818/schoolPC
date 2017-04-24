// 支持多文件同时上传的变量定义：
var uploadDatas = [];

var fileIndex = 0;

$(function () {
    $('#fileupload').fileupload(
        {
            // 设置超时处理时间 超时时间3分钟 超时会重试
            timeout: 180000,
            maxChunkSize: 1024 * 1024, // 256kb
            dataType: 'json',
            limitConcurrentUploads: 5,
            // 文件重试
            maxRetries: 200,
            retryTimeout: 500,

            drop: function (e, data) { // drop事件，jquery的fileupload插件默认支持拖动上传，当该事件被触发时，会调用我们自定义回调函数
                console.log("drop:"+e);
            	$.each(data.files, function (index, file) {
                });
            },
            change: function (e, data) {// change事件，当文件输入框发生改变时，触发文件上传,
                // 触发时间早于add
            	console.log(e);
            	var fileSize = 0;
                $.each(data.files, function (index, file) {
                	fileSize++;
                });
                if(fileSize > 10){
                	alert("一次最多可以上传10个文件");
                	return false;
                }
            },
            done: function (e, data) { // done事件，文件上传完成后调用
            	console.log(e);
                $.each(data.files, function (index, file) {
                	//把上传完的视频ID获取出来，修改为处理中的状态
                	var ccvid = data.video.ccvid;
            		//根据视频ID从数据中库中删除
            		var uri = rootPath + '/video/udpateStatus/'+ccvid;
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
                });
            },
            fail: function (e, data) {
                console.log("fail:"+e);
                var fu = $(this).data('blueimp-fileupload') || $(this).data('fileupload');
                var retries = data.context.data('retries') || 0, retry = function () {
                    var uri = data.video.uri+ 'servlet/html5upload';
                    $.ajax({
                            url: uri,
                            async: false,
                            type: "get",
                            data: {
                                "ccvid": data.video.ccvid,
                                "uid": data.video.uid,
                                "first": data.video.first,
                                "filename": data.video.name,
                                "filesize": data.video.size,
                                "servicetype": data.video.servicetype,
                                "md5": data.md5
                            },
                            cache: false,
                            error: function () {
                                fu._trigger('fail', e, data);
                            },
                            success: function (result) {
                                if (result < 0) {
                                    errorMsgHandler("获取断点为负数,result=" + result);
                                    console.log("获取断点为负数,result=" + result);
                                    return;
                                }
                                data.uploadedBytes = parseInt(result); // 转为int，避免被隐性转换为string导致的分块失效，第二次上传会将剩下的内容一次性上传的问题
                                data.video.first = 2; // 设置续传
                                $("#fileupload").attr("action",
                                    data.video.uri
                                    + "servlet/jsresumereceive?ccvid="
                                    + data.video.ccvid
                                    + "&range="
                                    + data.uploadedBytes);
                                data.submit();
                            }
                        });
                };
                if ((!data.isCanceled)
                    && data.errorThrown !== 'abort'
                    && data.uploadedBytes < data.files[0].size
                    && retries < fu.options.maxRetries) {
                    retries += 1;
                    data.context.data('retries', retries);
                    window.setTimeout(retry, retries * fu.options.retryTimeout);
                    return;
                }
                data.context.removeData('retries');
                $.blueimp.fileupload.prototype.options.fail.call( this, e, data);
            }
        }).on('fileuploadadd',function (e, data) {
            // 处理问题信息
        	console.log("fileuploadadd:"+e);
            var fileType = data.files[0].name.split('.').pop().toLowerCase();
            var allowdtypes = 'wmv,wm,asf,asx,rm,rmvb,ra,ram,mpg,mpeg,mpe,vob,dat,mov,3gp,mp4,mp4v,m4v,mkv,avi,flv,f4v,mts';
            if (allowdtypes.indexOf(fileType) < 0) {
                var content = "请使用正确的视频文件";
                errorMsgHandler(content);
                return false;
            } else {
                return true;
            }
        });

    $('.nav-collapse.collapse.navbar-responsive-collapse').show();
});

// 用于文件添加到列表时直接autoupload上传
function resumeUpload(data) {
    uploadDatas[uploadDatas.length] = data;
    $
        .each(
        data.files,
        function (index, file) {
            data.index = fileIndex;
            // 处理数据
            var currentData = uploadDatas[fileIndex];
            // 计算文件md5值, 进行续传
            currentData.md5 =  "00000000000000000000000000000000";
            var totalSize = 0;

            var myfiles = data.files;
            // 获取文件索引位置
            var index = data.index;
            for(var i = 0; i < myfiles.length; i++){
            	totalSize += file.size;
            }
            
            $.ajax({
    	        url: rootPath+"/video/judgeVideoStorage",
    	        async: false,
    	        type: "post",
    	        data: {
    	            "totalSize": totalSize,
    	        },
    	        cache: false,
    	        error: function (data) {
    	            alert("取消出现错误!");
    	        },
    	        success: function (result) {
    	        	if(result == 'date_over'){
    	        		alert('您的视频服务已经到期');
                 		return false;
    	        	}
    	        	if(result == 'over_storage'){
                 		alert('您剩余的空间不够存放当前的视频');
                 		return false;
                 	}
    	            
    	        	 for (var i = 0; i < myfiles.length; i++) {
    	                 var file = myfiles[i];
    	                 // 向父窗口提交数据
    	                 var message = {
    	                     "index": index,
    	                     "name": file.name,
    	                     "size": file.size
    	                 };
    	                 
    	                 var video = {
    	                     index: index
    	                 };
    	                 $.ajax({
    	                         url: rootPath+"/video/upload",
    	                         async: false,
    	                         type: "post",
    	                         data: {
    	                         	"tag":$("#tag").val(),
    	                         	"itemOneId":$("#itemOneId").val(),
    	                 			"itemSecondId":$("#itemSecondId").val(),
    	                             "fileName": file.name,
    	                             "fileSize": file.size
    	                         },
    	                         cache: false,
    	                         dataType: "json",
    	                         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	                         error: function () {
    	                             video = {
    	                                 "errMsg": "获取视频文件vid出错"
    	                             };
    	                             Msg.fail('获取视频文件vid出错');
    	                         },
    	                         success: function (data) {
    	                        	if(result == 'date_over'){
    	             	        		alert('您的视频服务已经到期');
    	                          		return false;
    	             	        	}else if(data == 'over_storage'){
    	                         		alert('您剩余的空间不够存放当前的视频');
    	                         		return false;
    	                         	}else{
    	                         		data = eval("(" + data + ")");
    	                         		video.success = true;
    	                         		video.uid = data.uploadinfo.userid;
    	                         		video.ccvid = data.uploadinfo.videoid;
    	                         		video.servicetype = data.uploadinfo.servicetype;
    	                         		video.name = file.name;
    	                         		video.uri = data.uploadinfo.metaurl.substring(0,data.uploadinfo.metaurl.length - 18);
    	                         		video.first = 1;
    	                         		video.size = file.size;
    	                         	}
    	                         }
    	                     });
    	                 postMessageHandler(video);
    	             }

    	             fileIndex++;
    	        	
    	        }
    	    });
            
        });
}

// 用于处理当MD5校验完成后获取vid失败重试机制恢复
function retryGetVidUpload(data) {
    // 校验md5
    if (data.md5 === undefined || data.md5 == "") {
        errorMsgHandler("文件MD5校验状态不正确");
        return;
    }
    var myfiles = data.files;
    // 获取文件索引位置
    var index = data.index;
    for (var i = 0; i < myfiles.length; i++) {
        var file = myfiles[i];
        // 向父窗口提交数据
        var message = {
            "index": index,
            "name": file.name,
            "size": file.size
        };
    }
}

function doResumeUpload(uploadData) {
    var uri = uploadData.video.uri + 'servlet/html5upload';
    $.ajax({
        url: uri,
        async: false,
        type: "get",
        data: {
            "ccvid": uploadData.video.ccvid,
            "uid": uploadData.video.uid,
            "first": uploadData.video.first,
            "filename": uploadData.video.name,
            "filesize": uploadData.video.size,
            "servicetype": uploadData.video.servicetype,
            "md5": uploadData.md5
        },
        cache: false,
        error: function () {
            errorMsgHandler("获取文件断点出错");
        },
        success: function (result) {
            if (result < 0) {
                errorMsgHandler("获取断点为负数,result=" + result);
                return;
            }
            // 转为int，避免被隐性转换为string导致的分块失效，第二次上传会将剩下的内容一次性上传的问题
            uploadData.uploadedBytes = parseInt(result);
            uploadData.video.first = 2; // 设置续传
            $("#fileupload").attr(
                "action",
                uploadData.video.uri + "servlet/jsresumereceive?ccvid="
                + uploadData.video.ccvid + "&range="
                + uploadData.uploadedBytes);
            uploadData.submit();
        }
    });
}

function _pauseUpload(e) {
    e.preventDefault();
    var template = $(e.currentTarget).closest('.template-upload'), data = template
        .data('data');
    data.context.find("#pause").attr('style', 'display:none');
    data.context.find("#resume").attr('style', '').attr('style',
        'margin-top:3px;');
    // 设置暂停
    data.context.find("#isPause").val("true");
}

function _resumeUpload(e) {
    e.preventDefault();
    var template = $(e.currentTarget).closest('.template-upload'), data = template
        .data('data');
    data.context.find("#resume").attr('style', "display:none");
    data.context.find("#pause").attr('style', '').attr('style',
        'margin-top:3px;');
    ;
    // 取消暂停
    data.context.find("#isPause").val("false");
    // 续传
    doResumeUpload(data);
}

var postMessageHandler = function (video) {
    if (!video.success) {
        Msg.fail('获取视频ID失败');
        return;
    }

    // 判断video是否合法
    if (video.errMsg != null || video.ccvid === undefined
        || video.uid === undefined) {
        errorMsgHandler(video.errMsg + ",点击重试,再次上传.index=" + video.index);
        // 可以重新申请vid
        var currentData = uploadDatas[video.index];
        currentData.context.find("#start").attr('disabled', false);
        currentData.context.find("#start").attr('style', "");
        return;
    } else {
        var currentData = uploadDatas[video.index];
        currentData.video = video;
        // 处理重试按钮
        currentData.context.find("#start").attr('disabled', true);
        currentData.context.find("#start").attr('style', "display:none");
        // 如果当前的数据没有取消
        if (!currentData.isCanceled) {
            doResumeUpload(currentData);
        }
    }

};

function errorMsgHandler(content) {
    Msg.fail(content);
}

$(function () {
    $('#changeUploadMethod').click(function () {
        window.location.href = '/video/upload.bo';
    });
});

window.onbeforeunload = function (e) {
    if ($('.progress.progress-striped.active.mb0.tc_rel').length) {
        return '视频上传中，离开页面将无法继续上传？';
    } else {
        if (!window.event) {
            return null;
        }
    }
};
