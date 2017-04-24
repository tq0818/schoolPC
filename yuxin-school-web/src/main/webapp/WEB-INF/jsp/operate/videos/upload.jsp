<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
    <title>上传视频</title>
    <%@include file="/decorators/import.jsp"%>
    <link href="<%=rootPath%>/stylesheets/resource.css" rel="stylesheet" type="text/css"/>
    
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/ccUpload/upload_files/bootstrap.css">
    <%-- <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/ccUpload/upload_files/main.css"> --%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/ccUpload/upload_files/style.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.fileupload.css">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    
</head>
<body>
<!-- 二级导航开始 -->
	<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>	
	<!-- 二级导航结束 -->
<div class="u-wrap operate">
	<div class="mainbackground">
	
		<!-- 中间部分上传CC视频开始 -->
        
	        <div class="wrap">
			    <!-- 未读消息数 -->
			    <div class="container-fluid">
			        <div class="row-fluid">
			            <div class="span10 ml17">
			                <div class="upbox">
			                    <form id="fileupload" method="POST" enctype="multipart/form-data"
			                          action="http://1.15.vacombiner.bokecc.com/servlet/jsresumereceive?ccvid=FB91D83C7460AA7E9C33DC5901307461&range=0">
			                        <div class="row fileupload-buttonbar">
			                            <div class="col-lg-7 mb10">
								                <span class="btn btn-primary fileinput-button">
								                    <i class="glyphicon glyphicon-plus"></i>
								                    <span>上传视频</span>
								                    <input type="file" name="files" multiple="">
								                </span>
			                                <span class="fileupload-process"></span>
			                            </div>
			                        </div>
			                        <div class="updiv mt1">
			                            <table class="uptable table table-striped table-hover">
			                                <thead>
			                                <tr>
			                                    <th style="width:300px;">文件名</th>
			                                    <th style="width:80px;">大小</th>
			                                    <th>状态</th>
			                                    <th style="width:180px;">操作</th>
			                                    <th style="display:none"></th>
			                                </tr>
			                                </thead>
			                                <tbody class="files"></tbody>
			                            </table>
			                        </div>
			                    </form>
			                </div>
			            </div>
			        </div>
			    </div>
			    <div class="footer_push"></div>
			</div>
        <input type="hidden" id="errMsg" value="">
        <!-- 中间部分上传CC视频开结束 -->
    </div>
</div>

<script type="text/javascript" src="<%=rootPath%>/plugins/ccUpload/upload_files/h.js"></script>
<script id="template-upload" type="text/x-tmpl">
		{% for (var i=0, file; file=o.files[i]; i++) { %}
		    <tr class="template-upload fade">
		        <td style="width:300px;">
		            <p class="name" style="margin-top: 6px;">{%=file.name%}</p>
		            <strong class="error text-danger"></strong>
		        </td>
		        <td style="width:80px;">
		            <p class="size" style="margin-top: 6px;">处理中...</p>
		        </td>
				<td class="progressbar">
					<div style="margin-top:4px; width:150px; display:inline-block;" class="progress progress-striped active mb0 tc_rel" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
						<div class="progress-bar progress-bar-success" style="width:0%;"></div>
						<div style="margin-top: 3px;" class="tc tc_pos upload_percent progress-u">0%</div>
					</div>
					<span class="rate" style="vertical-align:super;"></span>
				</td>
		        <td style="width:180px;" class="operate">
		            {% if (!i && !o.options.autoUpload) { %}
		                <button class="btn btn-primary btn-xs start" style="margin-top:3px;" disabled>
		                    <i class="glyphicon glyphicon-upload"></i>
		                    <span>开始上传</span>
		                </button>
		            {% } %}
		            {% if (!i) { %}
		                <button class="btn btn-warning btn-xs cancel" style="margin-top:3px;">
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
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/bootstrap.min.js"></script>
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
<script>
    $(function () {
        if ($(".nav-list li").length == 1) {
            $(".nav-list li a").addClass("br6");
        }
   		$selectSubMenu('resource_video');
    })
</script>

</body>
</html>