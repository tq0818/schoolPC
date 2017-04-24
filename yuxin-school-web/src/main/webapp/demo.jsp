<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo</title>
</head>
<body>
	<div class="main-background">
		<div class="mid-div">
			<h5>请根据机构计划售卖的课程类型 ( 直播课程、面授课程、录播课程 )，开启对应的服务。</h5>	
			<div>
				<i class="iconfont">&#xe639;</i>
				<span class="tip">开启直播</span>
				<em class="choose iconfont">&#xe611;</em>
			</div>
			<div>
				<i class="iconfont">&#xe638;</i>
				<span class="tip">开启录播</span>
				<em class="choose iconfont">&#xe611;</em>
			</div>
			<div class="active">
				<i class="iconfont" style="font-size:140px;">&#xe637;</i>
				<span class="tip">开启面授</span>
				<em class="choose iconfont">&#xe611;</em>
			</div>
			<a class="btn">保存进入网校后台管理</a>
		</div>
	</div>
	<script type="text/javascript">
	
		$(function(){
			$(".mid-div").on("click","div",function(){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
				}else{
					$(this).addClass("active");
				}
			})
			
		})
	</script>
</body>
</html>