<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="zh-cn">
<head>
<title>看视频-添加视频</title>
<%@include file="/decorators/import.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/fatstyle.css">
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/manage.css">
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/operate.css">
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/operate/showTc.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/operate.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/swfobject.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/operate/videos/addVideo.js"></script>


</head>
<body>
	<!-- header start -->
	<!-- header end -->
	<!-- 二级导航 -->
	<div class="full-wrap navbar smbar">
		<div class="header">
			<a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe613;</i>运营</a>
			<ul class="nav nav-left navspace">
				<li><a href="./index.html">排课</a></li>
				<li><a href="./operate-live.html">上直播</a></li>
				<li><a href="<%=rootPath%>/video/toVideo" class="active">传视频</a></li>
			</ul>
		</div>
	</div>
	<div class="u-wrap operate">
		<div class="mainbackground">
			<div class="heading">
				<h2 class="h5">传视频</h2>
				<span class="line"></span>
			</div>
			<div class="operate_veido_top">
				<p class="operate_veido_leavings">
					已上传视频${uploadCount == 0 ? '0' :
					uploadCount}个，占用空间${storageSize}G，剩余<span>${videoOverPlus}</span>G
				</p>
			</div>
			<div class="operate_main operate_vedio_main">

				<div class="operate_vedio_add_box" id="swfDiv">
					<div class="operate_vedio_title">
						<a href="javascript:;"
							class="btn btn-bigger btn-success addVideoBtn">添加视频</a> <span>已添加5个文件，总大小4G</span>
					</div>
					<table class="table table-hover table-center operate_vedio_table">
						<tr>
							<th>文件名</th>
							<th>大小</th>
							<th width="25%">状态</th>
							<th>操作</th>
						</tr>
						<tr>
							<td><div class="operate_w">我的英语学习日记系列-01</div></td>
							<td>1.23G</td>
							<td>等待上传</td>
							<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
						</tr>
						<tr>
							<td><div class="operate_w">我的英语学习日记系列-01</div></td>
							<td>1.23G</td>
							<td>等待上传</td>
							<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
						</tr>
						<tr>
							<td><div class="operate_w">我的英语学习日记系列-01</div></td>
							<td>1.23G</td>
							<td>等待上传</td>
							<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
						</tr>
						<tr>
							<td><div class="operate_w">我的英语学习日记系列-01</div></td>
							<td>1.23G</td>
							<td>等待上传</td>
							<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
						</tr>
						<tr>
							<td><div class="operate_w">我的英语学习日记系列-01</div></td>
							<td>1.23G</td>
							<td>等待上传</td>
							<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
						</tr>
						<tr>
							<td><div class="operate_w">我的英语学习日记系列-01</div></td>
							<td>1.23G</td>
							<td>等待上传</td>
							<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
						</tr>
						<tr>
							<td><div class="operate_w">我的英语学习日记系列-01</div></td>
							<td>1.23G</td>
							<td>等待上传</td>
							<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
						</tr>
					</table>

				</div>
			</div>
		</div>
	</div>
	<!-- footer start -->
	<!-- footer end -->

	<!-- 弹层信息--已招学员-->
	<div class="addVideoTc">
		<div class="check_student_hd">
			请选择要上传的视频<i class="close iconfont">&#xe610;</i>
		</div>
		<div class="check_student_bd">
			<table class="table table-hover table-center operate_vedio_table">
				<tr>
					<th><input type="checkbox" class="allCheckbox"></th>
					<th>文件名</th>
					<th>大小</th>
					<th width="25%">状态</th>
					<th>操作</th>
				</tr>
				<tr>
					<td><input type="checkbox" class="operateCheckbox"></td>
					<td><div class="operate_w">我的英语学习日记系列-01</div></td>
					<td>1.23G</td>
					<td>等待上传</td>
					<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
				</tr>
				<tr>
					<td><input type="checkbox" class="operateCheckbox"></td>
					<td><div class="operate_w">我的英语学习日记系列-01</div></td>
					<td>1.23G</td>
					<td>等待上传</td>
					<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
				</tr>
				<tr>
					<td><input type="checkbox" class="operateCheckbox"></td>
					<td><div class="operate_w">我的英语学习日记系列-01</div></td>
					<td>1.23G</td>
					<td>等待上传</td>
					<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
				</tr>
				<tr>
					<td><input type="checkbox" class="operateCheckbox"></td>
					<td><div class="operate_w">我的英语学习日记系列-01</div></td>
					<td>1.23G</td>
					<td>等待上传</td>
					<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
				</tr>
				<tr>
					<td><input type="checkbox" class="operateCheckbox"></td>
					<td><div class="operate_w">我的英语学习日记系列-01</div></td>
					<td>1.23G</td>
					<td>等待上传</td>
					<td><a class="btn btn-sm btn-danger" href="javascript:;">删除</a></td>
				</tr>
			</table>
			<div class="operate_video_ft">
				<div class="w">
					<span class="class_number_name"><span class="sp_red">*</span>学科：</span>
					<select id="oneItem">
						<c:forEach items="${oneItemList}" var="oi">
							<option value="${oi.id}">${oi.itemName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="w">
					<span class="class_number_name"><span class="sp_red">*</span>二级：</span>
					<select id="secItem">
					</select>
				</div>
				<div class="w">
					<span class="class_number_name">标签：</span> <input type="text" />
				</div>
				<div class="w last_w">
					<input type="button" class="btn btn-big btn-default" disabled
						value="开始上传" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>