<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
	<title>视频库-上传视频</title>
    <style>
        .iconfontH{color: red;}
        .tipsH{color: red;}
        .iii{
            position: absolute;
            top: 23px;
            right: -8px;
            width: 0;
            height: 0;
            border: 5px solid transparent;
            border-top-color: #ddd;
        }
        .cc{
            height: 62px;
            padding: 0 1%;
            line-height: 1.5;
        }
        .alert {
            padding: 15px;
            border: 1px solid transparent;
            margin:0 auto;
        }
    
        .alert-warning {
            color: #c09853;
            background-color: #fcf8e3;
            border-color: #fbeed5
        }
        .alert-dismissable .cloze {
            position: relative;
            right: 50px;
            color: inherit;
            font-size:1.6rem;
            cursor:pointer;
        }
        .cloze {
            float: right;
            font-size: 21px;
            font-weight: bold;
            line-height: 1;
            color: #000;
            text-shadow: 0 1px 0 #fff;
            opacity: .2;
            filter: alpha(opacity=20);
            display: inline-block;
            margin-top: 0px;
            margin-right: 0px;
            width: 9px;
            height: 9px;
            background-repeat: no-repeat !important;
        }
        
        form{
        	display: none;
        }
    
        .noalert {
            float: right;
            font-size: 12px;
            font-weight: bold;
            line-height: 1;
            color: #000;
            text-shadow: 0 1px 0 #fff;
            opacity: .2;
            filter: alpha(opacity=20);
            display: inline-block;
            margin: 1px -15px 0 15px;
            width: 80px;
            height: 9px;
            background-repeat: no-repeat !important;
            cursor:pointer;
        }
    
        .cloze:hover,.cloze:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
            opacity: .5;
            filter: alpha(opacity=50)
        }
        button.cloze {
            padding: 0;
            cursor: pointer;
            background: transparent;
            border: 0;
            -webkit-appearance: none
        }
    </style>
	<%@include file="/decorators/import.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/operate.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/UploadProgressUtils.css">
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/plupload.full.min.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/moxie.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.2.1/jquery.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.js"></script>
	<script src="http://v.polyv.net/file/plug-in2/js/polyv-upload.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/ydUpload/yunduo.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/operate/showTc.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/operate.js?_=1.0"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/onlynum.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/operate/videos/uploadVideo.js?_=1.0"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/letv/letvUpload.js?v=1.0"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
</head>
<body>
<!-- 二级导航开始 -->
<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<!-- 二级导航结束 -->

<!-- 上传视频内容开始 -->
<div class="u-wrap operate">
	<div class="mainbackground">
		<div class="heading L-heading">
			<h2 class="h5" style="width:200px;">上传资源</h2>
			<a href="<%=rootPath %>/video/toVideo" class="btn btn-sm btn-default" style="float:right;margin-top: -25px;">返回</a>
			<span class="line"></span>
		</div>
        <div id="navBar" class="operate_main operate_vedio_main L-video-con clear">
            <ul class="L-ul">
                <li name="video" for="video" class="L-ac">视频</li>
                <li name="flash" for="resource">flash</li>
                <li name="audio"  for="resource">音频</li>
                <li name="ppt" for="resource">PPT</li>
                <li name="docs" for="resource">文档</li>
            </ul>
            <div id="content" class="L-operate_vedio">
               <div name="video" >
                    <div>
        				<a href="javascript:;" class="btn btn-success btn-mini btn-method" data-type="0">本地视频</a>
            			<a href="javascript:;" class="btn btn-default btn-mini btn-method" data-type="1">网络视频</a>
            			<c:if test="${!empty cfs && cfs.status == 1 }">
            				<a href="javascript:;" class="btn btn-default btn-mini btn-method" data-type="2">scorm课件</a>
            			</c:if>
                    </div>
                    <div name="ff">
            			<div class="operate_vedio_title">
            				<a id="addVideoBtn" href="javascript:;" class="btn btn-bigger btn-success addVideoBtn">上传文件 </a>
            			</div>
                		<div class="operate_vedio_add_box video_file L-operate_vedio_add_box">
            				<form id="fileupload" method="POST" enctype="multipart/form-data" action="http://1.15.vacombiner.bokecc.com/servlet/jsresumereceive?ccvid=FB91D83C7460AA7E9C33DC5901307461&range=0">
            					<input type="file" name="files" accept=".avi,.mp4*,.asf,.sdx,.wmv,.rmvb,.3gp,.mkv,.flv,.f4v,.rm,.ra,.ram,.mpg,.pgeg,.mpe,.vob,.dat,.mov,.3gp,.mts" id="filebutton" class="filebutton" multiple="">
            					<table class="table table-hover table-center operate_vedio_table L-table L-table-hover">
            						<thead>
                						<tr>
                							<th width="480">文件名</th>
                							<th width="100">大小</th>
                							<th width="300">状态</th>
                							<th width="190">操作</th>
                						</tr>
            						</thead>
            						<tbody class="files">
                                    </tbody>
            					</table>
            				</form>
            				<form id="fileuploadscorm" method="POST" enctype="multipart/form-data" action="">
            					<input type="file" name="files" accept=".zip" id="filescorm" class="filebutton" multiple="" onchange="javascript:uploadscorm();">
            					<table class="table table-hover table-center operate_vedio_table">
            						<thead>
            						<tr>
            							<th width="480">文件名</th>
            							<th width="100">大小</th>
            							<th width="300">状态</th>
            							<th width="190">操作</th>
            						</tr>
            						</thead>
            						<tbody class="files">
            						</tbody>
            					</table>
            				</form>
            				<form id="fileuploadqnvd" method="POST" enctype="multipart/form-data">
            					<input type="file" name="files" id="quVideoFiles" class="filebutton" multiple="">
            					<table class="table table-hover table-center operate_vedio_table L-table L-table-hover">
            						<thead>
                						<tr>
                							<th width="480">文件名</th>
                							<th width="100">大小</th>
                							<th width="300">状态</th>
                							<th width="190">操作</th>
                						</tr>
            						</thead>
            						<tbody class="files">
                                    </tbody>
            					</table>
            				</form>
            				<form id="uploadBLVS" method="POST" enctype="multipart/form-data">
            					<input type="button" name="BLVSFiles" id="BLVSFiles" class="filebutton" multiple="">
            					<table class="table table-hover table-center operate_vedio_table L-table L-table-hover">
            						<thead>
                						<tr>
                							<th width="480">文件名</th>
                							<th width="100">大小</th>
                							<th width="300">状态</th>
                							<th width="190">操作</th>
                						</tr>
            						</thead>
            						<tbody class="files">
                                    </tbody>
            					</table>
            				</form>
            				<c:choose>
            					<c:when test="${member.videoServiceProvider=='letv'}">
            						<span class="video-msg">**说明:本地视频上传后会做转码压缩工作，存储为[超清][高清][流畅]三种格式。</span>
            					</c:when>
            					<c:otherwise>
            						<span class="video-msg">**说明:本地视频上传后会做转码压缩工作，存储为[高清][标清][普清][移动端高清][移动端标清]五种格式，上传后占同空间大小约为原视频大小的2-4倍。</span>
            					</c:otherwise>
            				</c:choose>
            				<span class="scorms">**说明:上传scorm课件，格式为 .zip压缩格式; <span style="color:red">上传过程可能需要几分钟，请耐心等待</span></span>
                			<c:if test="${member.videoServiceProvider=='letv'}">
                				<script>
                					$("#fileupload").attr("action","").attr("id","fileupload1");
                					$("#filebutton").attr("accept",".wmv,.avi,.dat,.asf,.rm,.rmvb,.ram,.mpg,.mpeg,.mp4,.mov,.m4v,.mkv,.flv,.vob,.qt,.divx,.cpk,.fli,.flc,.mod,.dvix,.dv,.ts");
                					$(function(){
                						$("#fileupload").attr("action","");
                						$("#filebutton").attr("id","filebutton");
                						$("body").on("click","#cancel_letv",function(){
											var id = $(this).parents("tr").attr("id");
											$("tr[id="+id+"]").remove();
                							//$(this).parents("tr").remove();
                							html5Upload.exportObject.selectFile.xhrAbort();
                
                							html5Upload.exportObject.selectFile.nextUpload();
                							$("td.operate[finishFlag!=true]:first").html('<button class="btn btn-warning btn-xs cancel"  id="cancel_letv" style="margin-top:3px;"> <i class="glyphicon glyphicon-ban-circle"></i><span>取消</span></button>');
                						});
                						$("body").on("click","#pause_letv",function(){
                
                						});
                						$("#filebutton").uploadletv({
                							uploadBefore:function(data){
                								data.target.options = data.target.options || {};
                								var func = tmpl("template-upload",data.target);
                								var r = $(func);
                								r.find(".start").hide();
                								r.find("button[id=pause]").hide();
                								r.find("button[id=cancel]").hide();
                								r.find("#cancel").attr("id","cancel_letv").attr("onclick","");
                								r.find("#pause").attr("id","pause_letv");
                								$(".files").append(r);
                								$("button[id=cancel_letv]:first").show();
                							},
                							uploadInitUrl: "/video/uploadLetv",//初始化上传地址
                							uploadProgress: function (data,key) { //进度回调
                								$("#"+key).find(".size").html(data.size);
                //										if(!$("#"+key).find("#cancel").is("button")){
                //											$("#"+key+" .operate").html('<button class="btn btn-warning btn-xs cancel" onclick="cancel_letv(this)"  id="cancel_letv" style="margin-top:3px;"> <i class="glyphicon glyphicon-ban-circle"></i><span>取消</span></button>');
                //										}
                								$("#"+key+" .progressbar").find(".progress-bar-success").css("width",data.progress);
                								$("#"+key+" .progressbar").find(".progress-u").text(data.progress);
                								$("#"+key+" .progressbar").find(".rate").text(data.speed);
                								//$("#"+key+" .size")	.text(data.size);
                								//$("#videoStatus").html("上传中");
                							},
                							uploadFinish: function (data,key) { //上传完成回调
                								$("#"+key+" .progressbar").html('<div style="margin-top:6px;" class="tc">上传完毕</div>');
                								$("#"+key+" .operate").html("").attr("finishFlag","true");
                								var filename = $.trim($("#"+key+" .name:first").text());
                								filename = filename.substring(0,filename.lastIndexOf("."));
                								$.ajax({
                									url : rootPath + "/video/saveLetv",
                									type : "post",
                									data : {videoUnique:data.video_unique,"itemSecondId":$("#itemSecondId").val(),"itemOneId":$("#itemOneId").val(),"tag":$("#tag").val(),"fileSize":data.filesize,"fileName":filename,"videoid":data.video_id},
                									success : function(data) {
                
                									}
                								});
                
                								if($("#"+key).next().is("tr")){
                									$("#"+key).next().find(".operate").html('<button class="btn btn-warning btn-xs cancel" id="cancel_letv" style="margin-top:3px;"> <i class="glyphicon glyphicon-ban-circle"></i><span>取消</span></button>');
                								}
                								//$("#"+key+" .operate").html("");
                							},
                							uploadError: function (data) { //上传错误回调
                								alert("上传错误！错误码：" + data.code + ";错误消息：" + data.msg);
                								console.log("3"+data);
                							},
                							uploadAbort:function(data){ //中断
                								console.log(data);
                							}
                						});
                
                					})
                				</script>
                			</c:if>
                		</div>
                    </div>
                    
                    
                    <div class="operate_vedio_add_box video_url L-operate_vedio_add_box" style="display:none">
                        <div class="operate_vedio_title">
                            <a id="addVideoBtn" href="javascript:;" class="btn btn-bigger btn-success addVideoBtn" style="position: relative;top: -40px;">插入链接</a>
                            <!-- <span>已添加5个文件，总大小4GB</span> -->
                        </div>
                        <table class="table table-hover table-center operate_vedio_table L-table L-table-hover">
                            <thead>
                            <tr>
                                <th width="480">文件名</th>
                                <th width="100">来源</th>
                                <th width="300">状态</th>
                                <th width="190">操作</th>
                            </tr>
                            </thead>
                            <tbody class="v_url">
                                <!-- <tr>
                                    <td><div class="operate_w">我的英语学习日记系列-01</div></td>
                                    <td>1.23G</td>
                                    <td>等待上传</td>
                                    <td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
                                </tr> -->
                            </tbody>
                        </table>
                        <span class="video-msg"></span>
                    </div>
                </div>
                <!-- 资源上传 -->
                <div name="resource" class="L-operate_vedio  operate_vedio_box-l" style="display:none">
                    <div class="operate_vedio_add_box L-operate_vedio_add_box">
                        <div class="operate_vedio_title" style="margin-bottom:40px;">
                            <a id="resourceUpload" href="javascript:;" class="btn btn-bigger btn-success addResourceBtn" style="width: 85px; border: none; padding: 9px 10px; font-size: 12px; line-height: 12px; text-align: center;  margin-top:0px; ">上传资源 </a>
                            <!-- <span>已添加5个文件，总大小4GB</span> -->
                        </div>
                        <form method="POST" enctype="multipart/form-data" action="" id="resourceform">
							<input id="resourceUploadInput" type="file" name="files" class="filebutton" onchange="javascript:resourcechange();" />
                            <input id="pickfiles" type="file" name="files" class="filebutton" />
                            <input id="audiofiles" type="file" name="files" class="filebutton" />
                            <input id="pptfiles" type="file" name="files" class="filebutton" />
                            <input id="docfiles" type="file" name="files" class="filebutton" />
                        </form>
                        <table name="resourceList" class="table table-hover table-center operate_vedio_table L-table L-table-hover">
                            <thead>
                                <tr>
                                    <th width="480">文件名</th>
                                    <th width="100">大小</th>
                                    <th width="300">状态</th>
                                    <th width="190">操作</th>
                                </tr>
                            </thead>
                            <tbody class="files" id="qnuploadt">
                            </tbody>
                        </table>
                        <span class="video-msg">文件必须小于1GB，否则上传不成功。</span>
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>
<!-- 上传视频内容结束 -->

<!-- 设置视频上传信息-->
<div class="addVideoTc">
	<div class="check_student_hd">设置视频上传信息<i class="close iconfont">&#xe610;</i></div>
	<div class="check_student_bd">
		<div class="operate_video_ft">
			<div class="w">
				<span class="class_number_name"><span class="sp_red">*</span>学科：</span>
				<select  id="itemOneId" name="itemOneId" class="subj-sel">
					<!-- <option>人力资源师</option>
                        <option>建造师</option>
                        <option>心理咨询师</option> -->
				</select>
				<span class="class_number_name"><span class="sp_red">*</span>学科小类：</span>
				<select id="itemSecondId" name="itemSecondId" class="subj-sel">
					<!--<option>人力资源师</option>
                        <option>建造师</option>
                        <option>心理咨询师</option> -->
				</select>
				<span class="tips-msg item_msg"></span>
			</div>
			<div class="w">
				<span class="class_number_name">标签：</span>
				<input type="text" id="tag" value="" maxlength="10"/>
			</div>
			<div class="w w-video">
				<span class="class_number_name"><span class="sp_red">*</span>视频时长：</span>
				<input type="text" value="0" id="hh" maxlength="2" onkeydown="javascript:onlyNum();" size="2"/>
				<span>&nbsp;&nbsp;时&nbsp;&nbsp;</span>
				<input type="text" value="0" id="mm" maxlength="2" onkeydown="javascript:onlyNum();" size="2"/>
				<span>&nbsp;&nbsp;分&nbsp;&nbsp;</span>
				<input type="text" value="0" id="ss" maxlength="2" onkeydown="javascript:onlyNum();" size="2"/>
				<span>&nbsp;&nbsp;秒&nbsp;&nbsp;</span>
			</div>
			<div class="w w-video">
				<span class="class_number_name"><span class="sp_red">*</span>视频网址：</span>
				<input type="text" id="videourl" size="61" placeholder="支持优酷、土豆、腾讯，将视频页面网址复制到这里点击增加即可"/>
				<a href="javascript:;" class="btn btn-primary btn-mini btn-add-url">增加</a>
			</div>
			<div class="w w-video">
				<span class="class_number_name"></span>
				<span style="color:darkgrey">tip:视频会增加到您的视频库，不会丢失哦~ 所以为了便于您选择，建议您给视频增加标签</span>
			</div>
			<div class="w w-video">
				<span class="class_number_name"><span class="sp_red">*</span>视频名称：</span>
				<input type="text" id="videoname" size="45">
			</div>
			<div class="w">
				<span class="agree"><input type="checkbox" checked id="protocol"><a href="<%=rootPath%>/fonts/protocol.html" target="_blank" >同意上传协议</a></span>
				<span class="tips-msg protocol_msg"></span>
			</div>
			<div class="w">
				<span class="agree"></span>
				<span class="tips-msg tips-ff">视频大小必须介于1-1024M之间，否则上传不成功。每次最多选中10个文件。</span>
			</div>
			<div class="w last_w">
				<input type="button" class="btn btn-big btn-primary" id="startbutton" value="选择本地文件" />
				<input type="button" class="btn btn-big btn-primary" id="qnuploadvideo" value="选择本地文件" />
				<input type="button" class="btn btn-big btn-primary" id="blvsloadvideo" value="选择本地文件" />
			</div>
			<div class="w last_s" style="text-align:center;">
				<input type="button" class="btn btn-big btn-primary" id="scromupload" value="选择本地文件" />
			</div>
			<div class="w last_v" style="text-align: center;">
				<input type="button" class="btn btn-big btn-primary" id="btn-save" value="保存" />
				<input type="button" class="btn btn-big btn-default" id="btn-cancel" value="取消" />
			</div>
		</div>
	</div>
</div>
<!-- 添加资源弹窗 -->
<div class="addResourceTc">
    <div class="check_student_hd">设置资源上传信息<i class="close iconfont">&#xe610;</i></div>
    <div class="check_student_bd">
        <div class="operate_video_ft">
            <div class="w">
                <span class="class_number_name"><span class="sp_red">*</span>学科：</span>
                <select  id="itemOneId" name="itemOneId" class="subj-sel">
                    <!-- <option>人力资源师</option>
                        <option>建造师</option>
                        <option>心理咨询师</option> -->
                </select>
                <span class="class_number_name"><span class="sp_red">*</span>学科小类：</span>
                <select id="itemSecondId" name="itemSecondId" class="subj-sel">
                    <!--<option>人力资源师</option>
                        <option>建造师</option>
                        <option>心理咨询师</option> -->
                </select>
                <span class="tips-msg item_msg"></span>
            </div>
            <div class="w">
                <span class="class_number_name">标签：</span>
                <input type="text" id="tag" value="" maxlength="10"/>
            </div>
            <div class="w w-video">
                <span class="class_number_name"></span>
                <span style="color:darkgrey">tip:视频会增加到您的视频库，不会丢失哦~ 所以为了便于您选择，建议您给文件增加标签</span>
            </div>
            <div class="w">
                <span class="agree"><input type="checkbox" checked id="protocol"><a href="<%=rootPath%>/fonts/protocol.html" target="_blank" >同意上传协议</a></span>
                <span class="tips-msg protocol_msg"></span>
            </div>
            <div class="w">
                <span class="agree"></span>
                <span class="tips-msg tips-ff">文件必须小于1GB，否则上传不成功。</span>
            </div>
            <div class="w" style="text-align:center;">
                <input type="button" class="btn btn-big btn-primary" id="resourceSubmit" value="选择本地文件" />
            </div>
        </div>
    </div>
</div>
<!-- 弹层信息结束， 选择一级学科小类 -->
<input type="hidden" id="errMsg" value="">
<c:choose>
	<c:when test="${member.videoServiceProvider=='letv'}">

	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="<%=rootPath%>/plugins/ccUpload/upload_files/h.js"></script>
	</c:otherwise>
</c:choose>

<script id="template-upload" type="text/x-tmpl">
		{% for (var i=0, file; file=o.files[i]; i++) {
			var getFileType = function (file) {
                return file.name.split(".").pop();
            };
		 var key = [getFileType(file), file.size, (file.lastModifiedDate==null)?0:file.lastModifiedDate.getTime()].join('_');
		 %}
		    <tr class="template-upload fade" id="{%=key%}">
		        <td>
		            <p class="name" style="margin-top: 6px;">{%=file.name%}</p>
		            <strong class="error text-danger"></strong>
		        </td>
		        <td>
		            <p class="size" style="margin-top: 6px;">处理中...</p>
		        </td>
				<td class="progressbar">
					<div style="margin-top:4px; width:150px; display:inline-block;" class="progress progress-striped active mb0 tc_rel" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
						<div class="progress-bar progress-bar-success" style="width:0%;"></div>
						<div style="margin-top: 3px;" class="tc tc_pos upload_percent progress-u">0%</div>
					</div>
					<span class="rate" style="vertical-align:super; "></span>
				</td>
		        <td class="operate">
		            {% if (!i && !o.options.autoUpload) { %}
		                <button class="btn btn-primary btn-xs start" style="margin-top:3px;" disabled>
		                    <i class="glyphicon glyphicon-upload"></i>
		                    <span>开始上传</span>
		                </button>
		            {% } %}
		            {% if (!i) { %}
		                <button class="btn btn-warning btn-xs cancel" id="cancel" onclick="cancle(this)" style="margin-top:3px;">
		                    <i class="glyphicon glyphicon-ban-circle"></i>
		                    <span>取消</span>
		                </button>
		            {% } %}
					{% if (!i) { %}
		                <button class="btn btn-primary btn-xs" id="pause" style="margin-top:3px;">
		                    <i class="glyphicon glyphicon-ban-circle"></i>
		                    <span>暂停</span>
		                </button>
		            {% } %}
					{% if (!i) { %}
		                <button class="btn btn-primary btn-xs" id="resume" style="display:none; margin-top:3px;">
		                    <i class="glyphicon glyphicon-upload"></i>
		                    <span>续传</span>
		                </button>
		            {% } %}
		        </td>
				<td width="0%" style="display:none">
		            <input id="isPause" value="false">
		        </td>
		    </tr>
		{% } %}
	</script>

<script>
	$(function () {
		if ($(".nav-list li").length == 1) {
			$(".nav-list li a").addClass("br6");
		}
		$selectSubMenu('resource_video');
	});
</script>
<input type="hidden" value="${qndomain }" id="qndomain"/>
<input type="hidden" value="${comId }" id="companyId"/>
<input type="hidden" value="${schoolId }" id="schoolId"/>
<input type="hidden" value="${userid }" id="userid"/>
<input type="hidden" value="${sessionScope.loginUser.id }" id="uid"/>
<input type="hidden" value="${ts }" id="ts"/>
<input type="hidden" value="${hash }" id="hash"/>
<input type="hidden" value="${sign }" id="sign"/>
<input type="hidden" value="${member.videoServiceProvider}" id="membertype"/>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/encapsulated_getJson.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/msgPrompt.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.ui.widget.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.iframe-transport.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.fileupload.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/tmpl.min.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.fileupload-process.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.fileupload-ui.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/crypt.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/spark-md5.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/upload5.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/itemList.js"></script>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
</body>

</html>