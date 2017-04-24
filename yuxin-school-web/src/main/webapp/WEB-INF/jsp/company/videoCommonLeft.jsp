<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="left-side">
	<!-- <div class="left-side-title subentry" data-url="/company/companyService">返回</div> -->
	<div class="left-side-title">
		<em>存储</em>
		<span class="iconfont return-pic">&#xe650;</span>
	</div>
	
	<ul>
		<li class="subentry dbTail" data-url="/companyMemberService/toVideoStatistics"><span>存储详情</span></li>
		<li class="subentry pmd" data-url="/company/marqueeOption"><span>跑马灯设置</span></li>
	</ul>
</div>
<script type="text/javascript">
$(function(){
	$(".subentry").click(function(){
		$(this).addClass("active").siblings(".subentry").removeClass("active");
		var url = $(this).attr("data-url");
		var type = $(this).attr("data-type");
		if(type){
			console.log("type存在");
			var objform = document.createElement("form");
			document.body.appendChild(objform);
			objform.action = rootPath + url;
			objform.method = "post";
			
			var types = document.createElement("input");
			types.type = "hidden";
			objform.appendChild(types);
			types.value = type;
			types.name = "types";
			objform.submit();
		}else{
			console.log("type不存在");
			location.href=rootPath+url;
		}
		
	});
	$(".return-pic").click(function(){
		var url = $(this).attr("data-url");
		location.href=rootPath+"/company/companyService";
	});
})
</script>