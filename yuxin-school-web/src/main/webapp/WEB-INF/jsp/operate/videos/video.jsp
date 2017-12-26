<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
<title>资源库</title>
<%@include file="/decorators/import.jsp"%>
<style type="text/css">
.register {
	position: fixed;
	left: 50%;
	top: 50%;
	width: 400px;
	height: 400px;
	margin-left: -200px;
	margin-top: -200px;
	padding-bottom: 15px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 5px;
	box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
	z-index: 999
}
.none{
    display: none;
}
.register .reg-close {
  position: absolute;
  top: 12px;
  right: 12px;
  width: 12px;
  height: 12px;
  background-image: url('../images/index-icons.png');
  background-repeat: no-repeat;
  background-position: 0 0;
  cursor: pointer;
}
.register .reg-title {
  padding: 15px 30px;
  border-bottom: 1px solid #e5e5e5;
}
.register .reg-form {
  padding: 0 60px;
}
.register .reg-bottom {
  padding: 2px 52px;
  border-top: 1px solid #e5e5e5;
}
.mark-bg {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: #fff;
  background-color: rgba(0,0,0,0.3);
  opacity: .6 \9;
  filter: alpha(opacity = 60);
}
</style>
<link href="<%=rootPath%>/stylesheets/resource.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/operate.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/operate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script> 
    <script src='https://player.polyv.net/script/polyvplayer.min.js'></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/operate/videos/video.js"></script>
<link href="http://vjs.zencdn.net/4.10/video-js.css" rel="stylesheet">
<script src="http://vjs.zencdn.net/4.10/video.js"></script>
<script src="<%=rootPath%>/javascripts/plus/swfobject.js?v=1.0"></script>
<script type="text/javascript" src="http://player.youku.com/jsapi"></script>
<!-- 乐视视频 -->
<script type="text/javascript" charset="utf-8" src="http://yuntv.letv.com/player/vod/bcloud.js"></script>
<!-- 腾讯视频 -->
<!-- <script type="text/javascript" src="http://imgcache.qq.com/tencentvideo_v1/tvp/js/tvp.player_v2.js"></script> -->
<script src="https://qzonestyle.gtimg.cn/open/qcloud/video/h5/h5connect.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/flexPaper/flexpaper.css" />
<script type="text/javascript" src="<%=rootPath%>/javascripts/flexPaper/js/flexpaper.js"></script>
</head>
<body>
	<!-- 二级导航开始 -->
	<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
	<!-- 二级导航结束 -->
	<div class="u-wrap operate">
		<div class="mainbackground">
			<div class="heading L-heading">
				<h2 class="h5">资源库</h2>
			    <span class="line"></span>
                <span class="space">资源空间
                    <i>${comService.videoStorage}/${comMember.videoStorage + comMember.giveVideoStorage}</i>
					<em style="top:0px;">GB</em>
                </span>
                
                <span class="space" style="margin-left: 260px">资源流量
                    <i>${comService.videoFlow}/${comMember.videoFlow + comMember.giveVideoFlow}</i>
					<em style="top:0px;">GB</em>
                </span>
				<span class="upload-tns">
					<a href="javascript:;" class="btn btn-success search" id="uploadVideo">上传资源</a>
				</span>
				<input type="hidden" value="${flag}" id="flag"/>
				<input type="hidden" value="${upVideo }" id="upVideo"/>
			</div>
			<div class="operate_main operate_vedio_main">
				<div class="class_number_cell">
                    <div class="w">
                        <span class="class_number_name">类型: </span>
                            <span  class="s-list">
                                <!-- <a searchType="all" class="btn btn-sm btn-default " href="javascript:;">全部</a> -->
                                <a searchType="video" class="btn btn-sm btn-default active" href="javascript:;">视频</a>
                                <a searchType="flash" class="btn btn-sm btn-default " href="javascript:;" >flash</a>
                                <a searchType="audio" class="btn btn-sm btn-default " href="javascript:;" >音频</a>
                                <a searchType="ppt" class="btn btn-sm btn-default" href="javascript:;" >PPT</a>
                                <a searchType="docs" class="btn btn-sm btn-default" href="javascript:;" >文档</a>
                            </span>
                    </div>
					<div class="w">
						<span class="class_number_name">学科：</span>
                        <span class="s-list">
							<a class="btn btn-sm btn-default oneItem" href="javascript:;" itemId="">全部</a>
                            <c:forEach items="${oneItemList}" var="one">
								<a class="btn btn-sm btn-default oneItem" href="javascript:;" itemId="${one.id}">${one.itemName}</a>
							</c:forEach>
						</span>
					</div>

			<!-- 	<div class="w secTwo"  style="display: none;">
						<span class="class_number_name">学科小类：</span>
                        <span class="s-list secItemInfo">
                            <a class="btn btn-sm btn-default oneItem" href="javascript:;" itemId="">全部</a>
                        </span> -->
					</div> 
					<div class="w">
						<span class="class_number_name">状态：</span> 
						<span class="s-list">
							<a class="btn btn-sm btn-default vStatus active" href="javascript:;" videoStatus="">全部</a> 
							<a class="btn btn-sm btn-default vStatus" href="javascript:;" videoStatus="VIDEO_PROCESS_NOMAL">正常</a> 
							<a class="btn btn-sm btn-default vStatus" href="javascript:;" videoStatus="VIDEO_PROCESS_DELETE">屏蔽</a> 
							<a class="btn btn-sm btn-default vStatus" href="javascript:;" videoStatus="VIDEO_PROCESS_INHAND">处理中</a>
							<a class="btn btn-sm btn-default vStatus" href="javascript:;" videoStatus="VIDEO_PROCESS_UPLOAD">上传中</a>
						</span>
					</div>
					<div class="w">
						<span class="class_number_name">上传时间：</span>
                        <input type="text" class="laydate-icon" readonly="readonly" id="start" style="width: 170px;" />
                        <span>至 </span>
                        <input type="text" readonly="readonly" class="laydate-icon" id="end" style="width: 170px;"/>
					</div>
					<div class="w">
						<span class="class_number_name">标签：</span> 
						<select class="ccTag" style="width: 138px;">
							<option value="">全部</option>
						</select>
					</div>
					<div class="w">
						<span class="class_number_name">资源名称：</span>
                        <input type="text" class="input-ctrl txt ccName" placeholder="" />
						<input type="button" value="搜索" class="btn btn-sm selectInfo btn-info L-btn-info" />
					</div>
				</div>
				<table class="table table-hover table-center table-list L-table">
					<thead>
						<tr>
							<th width="18%">资源名称</th>
							<th width="10%">学科</th>
							<th width="15%">
	                            <div class="operate_vedio_time">
	    							<p>
	                                    <span class="sp_top sort"></span>
	                                    <span class="sp_bottom sort active"></span>
	                                </p>上传时间
	    						</div>
	                        </th>
							<th width="9%">标签</th>
							<th width="7%">格式</th>
	                        <th width="8%">状态</th>
	                        <th width="6%">来源</th>
							<th width="18%">操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>

			</div>
			<div class="pages">
				<ul class="pagination">

				</ul>
			</div>
		</div>
	</div>
	<input type="hidden" id="itemOneId" />
	<input type="hidden" id="itemSecId" />
	<input type="hidden" id="vStatus" />
	<input type="hidden" id="sortBy" value="desc" />
	<input type="hidden" id="CStatus" value="${CStatus}" />
	<input type="hidden" id="role" value="${role}" />
	<div class="mark-bg none"></div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<input type="hidden" value="${userId }" id="uId">
	<input type="hidden" value="${companyId }" id="cId">
	<!--  ajax加载中div结束 -->
	<!-- 弹层信息 -->
    <div class="" style="width: 520px;display:none " id="L-allowAdmissionsTc">
        <input id = "rtype" val="" style="display:none"/>
        <div class="layer-tips-content" id="layer-tips-content">
            <p>
                <span>文件名称</span><input id="voName" type="text" style="width: 280px;"/>
            </p>
            <p>
                <span>标签</span><input id="voTag" type="text" maxlength="10" style="width: 280px;"/>
            </p>
            <div class="L-pdiv">
                <div class="L-poL">
                    <span>学科</span>
                    <select  id="itemOneId" name="itemOneId">
                        <option value="">人力</option>
                    </select>
                </div>
             <!--    <div class="L-poR">
                    <span>学科小类</span>
                    <select id="itemSecondId" name="itemSecondId">
                        <option value=""></option>
                    </select>
                </div> -->
            </div>
        </div>
    </div>
    <div class="layer-tips L-layer-tips allowAdmissionsTcs prview" style=" width: 798px; height:560px; display:none">
        <div class="layer-tips-title">
        	查看详情 <i class="close iconfont rpvc">&#xe610;</i>
        </div>
        <div class="L-videoL" style="padding:0px;">
            <div class="L-videoC" style="padding:0px;width:100%;height:480px;" id="video">
            </div>
        </div>
    </div>
	<div class="add-subs-layer-bg"></div>
	<script>
		$(function() {
			$selectSubMenu('resource_video');
		});
	</script>
</body>
</html>