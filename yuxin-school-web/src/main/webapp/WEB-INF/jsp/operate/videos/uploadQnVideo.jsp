<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>视频库-上传视频</title>
<%@include file="/decorators/import.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/operate.css">
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.public.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/operate/showTc.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/operate.js"></script>
<script type="text/javascript">
	function cancle(ele){
		//获取当前取消的CC视频的ID
		var ccvid = $(ele).parents(".template-upload").data('data').video.ccvid;
		//根据视频ID从数据中库中删除
		var uri = rootPath + '/video/del/'+ccvid;
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

</script>
</head>
<body>
	<!-- 二级导航开始 -->
	<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>	
	<!-- 二级导航结束 -->

	<!-- 上传视频内容开始 -->
		<div class="u-wrap operate">
			<div class="mainbackground">
		        <div class="heading">
		            <h2 class="h5">上传视频</h2>
		            <span class="line"></span>
		            <%-- <span class="search tips">已上传视频${uploadCount == 0 ? '0' :
					uploadCount}个，占用空间${storageSize}G，剩余<span>${videoOverPlus}</span>GB</span> --%>
		        </div>
		        <div class="operate_main operate_vedio_main">
		        <div class="operate_vedio_add_box">
		        	<div class="operate_vedio_title">
		            	<a href="javascript:;" class="btn btn-bigger btn-success addVideoBtn">上传文件 </a>
		                <!-- <span>已添加5个文件，总大小4GB</span> -->
		            </div>
		            <form id="fileupload" method="POST" enctype="multipart/form-data"
		                          action="http://1.15.vacombiner.bokecc.com/servlet/jsresumereceive?ccvid=FB91D83C7460AA7E9C33DC5901307461&range=0">
		                <input type="file" name="files" accept=".avi,.mp4,.asf,.sdx,.wmv,.rmvb,.3gp,.mkv,.flv,.f4v,.rm,.ra,.ram,.mpg,.pgeg,.mpe,.vob,.dat,.mov,.3gp,.mts" id="filebutton" class="filebutton" multiple="">
			            <table class="table table-hover table-center operate_vedio_table">
			                <thead>
			                    <tr>
			                        <th width="480">文件名</th>
			                        <th width="100">大小</th>
			                        <th width="300">状态</th>
			                        <th width="190">操作</th>
			                    </tr>
			                </thead>
			                <tbody class="files" id="fsUploadProgress">

			                </tbody>
			            </table>
		            </form>
		            <span class="video-msg">**说明:本地视频上传后会做转码压缩工作，存储为[高清][标清][普清][移动端高清][移动端标清]五种格式，上传后占同空间大小约为原视频大小的2-4倍。</span>
		           </div>
		         </div>
		       </div>
	       </div>
	    <!-- 上传视频内容结束 -->
	    
	    <!-- 弹层信息 已招学员-->
		<div class="addVideoTc">
			<div class="check_student_hd">设置视频上传信息<i class="close iconfont">&#xe610;</i></div>
		    <div class="check_student_bd">
		        <div class="operate_video_ft">
		        	<div class="w">
		                <span class="class_number_name"><span class="sp_red">*</span>学科：</span>
		                <select  id="itemOneId" name="itemOneId">
		                    <!-- <option>人力资源师</option>
		                    <option>建造师</option>
		                    <option>心理咨询师</option> -->
		                </select>
		              <!--   <span class="class_number_name"><span class="sp_red">*</span>学科小类：</span>
		                <select id="itemSecondId" name="itemSecondId">
		                    <option>人力资源师</option>
		                    <option>建造师</option>
		                    <option>心理咨询师</option>
		                </select>
		                <span class="tips-msg item_msg"></span> -->
		            </div>
		            <div class="w">
		                <span class="class_number_name">标签：</span>
		                <input type="text" id="tag" value=""/>
		            </div>
		            <div class="w">
		                <span class="agree"><input type="checkbox" checked id="protocol">同意<a href="<%=rootPath%>/fonts/protocol.html" target="_blank" style="font-style: italic;">上传协议</a></span>
		                 <span class="tips-msg protocol_msg"></span>
		            </div>
		            <div class="w last_w" id="container">
		                <input type="button" class="btn btn-big btn-primary" id="pickfiles" value="选择本地文件" />
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 弹层信息结束， 选择一级学科小类 -->
		<input type="hidden" id="errMsg" value="">
	
	<script type="text/javascript" src="<%=rootPath%>/plugins/ccUpload/upload_files/h.js"></script>
	<script>
	    $(function () {
	        if ($(".nav-list li").length == 1) {
	            $(".nav-list li a").addClass("br6");
	        }
	   		$selectSubMenu('resource_video');
	    })
	</script>

	<script type="text/javascript" src="<%=rootPath %>/plugins/qnUpload/plupload/plupload.full.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/plugins/qnUpload/plupload/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/plugins/qnUpload/ui.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/plugins/qnUpload/qiniu.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/plugins/qnUpload/highlight/highlight.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/plugins/qnUpload/main.js"></script>
	
</body>

</html>