<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/stylesheets/add-title.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/common/utils.js"></script>
<!-- 二级导航 -->
<div class="u-wrap company">
    <div class="block clear">
      <c:choose>
         	 <c:when test="${classPackage.publishStatus=='CLASS_ON_SALE' }">
         		 <div class="labels classStatus">
				            <s id="statusText">售卖中</s>
				       <div class="sanjiao"></div>
				   </div>
         	 </c:when>
         	 <c:when test="${classPackage.publishStatus=='CLASS_STOP_SALE' }">
         	 	 <div class="labels classStatus else1">
			           <s id="statusText">已下架</s> 
			            <div class="sanjiao"></div>
			        </div>
         	 </c:when>
         	 <c:otherwise>
         	 		<div class="labels classStatus else">
				            <s id="statusText">未发布</s>
				       <div class="sanjiao"></div>
				   </div>
         	 </c:otherwise>
         </c:choose>
        <div class="class-title">${classPackage.name }</div>
        <div class="sum">共<i id="totalCourseNums">0</i>课程</div>
        <div class="price">
        	<c:choose>
        		<c:when test="${!empty classPackage}">
        			<em>￥<s id="titldRealPrice"></s></em>
            		<del>￥<s id="titldOrgingPrice"></s></del>
        		</c:when>
        	</c:choose>
        </div>
         <c:choose>
          	 <c:when test="${classPackage.publishStatus=='CLASS_ON_SALE' }">
          		<button class="public_course" ids="${classPackage.id }" mark="nosale">下架</button>
          	 </c:when>
          	 <c:otherwise>
          	 	<button class="public_course" ids="${classPackage.id }" mark="sale">发布</button>
          	 </c:otherwise>
          </c:choose>
    </div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var op=$.formatMoney('${classPackage.realPrice }');
		var po=$.formatMoney('${classPackage.originalPrice }');
		$("#titldRealPrice").text(op);
		$("#titldOrgingPrice").text(po);
		$(".public_course").on('click',function(){
			var $this=$(this);
			var id=$(this).attr("ids");
			var mark=$(this).attr("mark");
			
			if(id && id!=""){
				if(mark=="sale"){
					$.ajax({
						url : rootPath+"/classPackage/getCourseNum",
						type : "post",
						data : {"id":'${classPackage.id }'},
						dataType : "json",
						success : function(result) {
							if(result && result>0){
								$.ajax({
									url : rootPath + "/classPackage/changClassPackageCollect",
									type : "post",
									data : {"id":id,"publishStatus":'CLASS_ON_SALE'},
									success : function(result) {
										$this.text("下架");
										$(".classStatus").removeClass("else1").removeClass("else");
										$("#statusText").text("售卖中");
										$this.attr("mark","nosale");
										var txt=$(".courseStatus").text();
										if(txt){
											$(".courseStatus").text("已上架");
										}
										$.msg("课程包发布成功");
									}
								});
							}else{
								if(result && result==-1){
									$.msg("课程包中还有课程没有添加在售班号");
								}else{
									$.msg("课程包下添加课程后才可以发布");
								}
							}
						}
					});
					
				}else{
					$.confirm("您确定要下架此课程包?下架后学员将无法再报名此课程包。",function(a){
						if(a==true){
							$.ajax({
								url : rootPath + "/classPackage/changClassPackageCollect",
								type : "post",
								data : {"id":id,"publishStatus":'CLASS_STOP_SALE'},
								success : function(result) {
									$this.text("发布");
									$(".classStatus").addClass("else1").removeClass("else");
									$("#statusText").text("已下架");
									$this.attr("mark","sale");
									var txt=$(".courseStatus").text();
									if(txt){
										$(".courseStatus").text("未上架");
									}
									$.msg("课程包下架成功");
								}
							});
						}else{
							return;
						}
					});
				}
			}else{
				$.msg("您还没有完善基本信息");
			}
		
		});
		 //总课程数
		$.ajax({
			url : rootPath+"/classPackage/getCourseNum",
			type : "post",
			data : {"id":'${classPackage.id }'},
			dataType : "json",
			success : function(result) {
				$("#totalCourseNums").text(result?result:0);
			}
		});
	});
</script>