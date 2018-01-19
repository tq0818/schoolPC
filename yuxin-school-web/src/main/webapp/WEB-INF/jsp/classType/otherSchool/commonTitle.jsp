<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/stylesheets/add-title.css"/>

<!-- 二级导航 -->
<div class="u-wrap company">
    <div class="block clear">
      <c:choose>
         	 <c:when test="${ct.publishStatus=='CLASS_ON_SALE' }">
         		 <div class="labels classStatus">
				            <s id="statusText">售卖中</s>
				       <div class="sanjiao"></div>
				   </div>
         	 </c:when>
         	  <c:when test="${ct.publishStatus=='CLASS_STOP_SALE' }">
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
        <div class="class-title">课程名称 ： ${ct.name }</div>
        <div class="sum">
        	<c:choose>
        		<c:when test="${ct.faceFlag==1 && ct.liveFlag==0 && ct.videoFlag==0 && ct.remoteFlag==0 }">
			      	<span>授课类型：面授</span>
		        </c:when>
		        <c:when test="${ct.faceFlag==0 && ct.liveFlag==1 && ct.videoFlag==0 && ct.remoteFlag==0 }">
			      	<span>授课类型：直播</span>
		        </c:when>
		        <c:when test="${ct.faceFlag==0 && ct.liveFlag==0 && ct.videoFlag==1 && ct.remoteFlag==0 }">
			      	<span>授课类型：录播</span>
		        </c:when>
		        <c:when test="${ct.faceFlag==0 && ct.liveFlag==0 && ct.videoFlag==0 }">
			      	<span>授课类型：其他</span>
		        </c:when>
		        <c:otherwise>
		        	<span>授课类型：混合</span>
		        </c:otherwise>
        	</c:choose>
        </div>
        <div class="price">
        	价格：${ct.realPrice }元
        </div>
        <c:choose>
          	 <c:when test="${ct.publishStatus=='CLASS_ON_SALE' }">
          		<button class="public_course" ids="${ct.id }" mark="nosale">下架</button>
          	 </c:when>
          	 <c:otherwise>
          	 	<button class="public_course" ids="${ct.id }" mark="sale">发布</button>
          	 </c:otherwise>
		</c:choose>
    </div>
</div>

<!-- <div class="u-wrap company"> -->
<!--     <div class="mainbackground u-content"> -->
<!--         <div class="wrap clear" style="margin:0;width: 100%;"> -->
<!--             <div class="rows" style="width:1250px;padding:5px 0;width: 100%;"> -->
<%--                 <span>课程名称 ： ${ct.name }</span> --%>
<%--                 <c:if test="${ct.faceFlag==1 }"> --%>
<!-- 		      		 <span>授课类型：面授 -->
<%-- 				         <c:if test="${ct.liveFlag==1 }">-直播</c:if> --%>
<%-- 				         <c:if test="${ct.videoFlag==1 }">-录播</c:if> --%>
<!-- 			         </span> -->
<%-- 	         	</c:if> --%>
<%-- 	          	<c:if test="${ct.faceFlag!=1 }"> --%>
<!-- 	          		<span>授课类型： -->
<%-- 	          		<c:if test="${ct.liveFlag==1 }"> --%>
<!-- 	          		直播 -->
<%-- 		         	<c:if test="${ct.videoFlag==1 }">-录播</c:if> --%>
<%-- 	          	    </c:if> --%>
<%-- 		            <c:if test="${ct.liveFlag!=1&&ct.videoFlag==1&&ct.remoteFlag!=1 }"> --%>
<!-- 		          	录播 -->
<%-- 	         	   </c:if> --%>
<%-- 	         	   <c:if test="${ct.videoFlag!=1 && ct.liveFlag!=1}">其他</c:if> --%>
<!-- 	         	   </span> -->
<%-- 	            </c:if> --%>
<%--                 <span>价格：${ct.realPrice }元</span> --%>
<%--                 <c:choose> --%>
<%--                 	 <c:when test="${ct.isSale==0 }"> --%>
<%--                 		<em class="public_course" ids="${ct.id }" mark="sale">发布</em> --%>
<%--                 	 </c:when> --%>
<%--                 	 <c:otherwise> --%>
<%--                 	 	<em class="public_course" ids="${ct.id }" mark="nosale">下架</em> --%>
<%--                 	 </c:otherwise> --%>
<%--                 </c:choose> --%>
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- </div> -->
<script type="text/javascript">
	$(document).ready(function(){
		$(".public_course").on('click',function(){
			var $this=$(this);
			var id=$(this).attr("ids");
			var mark=$(this).attr("mark");
			if(mark=="sale"){
				var cIds=new Array();
				cIds[0]=id;
				$.ajax({
					url : rootPath + "/otherSchool/battchUpOrDown",
					type : "post",
					data : {"type":1,"cIds":cIds,"publishStatus":'CLASS_ON_SALE'},
					success : function(datas) {
						if("success"==datas.result){
							$this.text("下架");
							$(".classStatus").removeClass("else1").removeClass("else");
							$("#statusText").text("售卖中");
							$this.attr("mark","nosale");
							var txt=$(".courseStatus").text();
							if(txt){
								$(".courseStatus").text("已上架");
							}
							$.msg("课程发布成功");
						}else if("error"!=datas.result){
							$.msg(datas.result);
						}else {
							$.msg("课程发布失败");
						}
					}
				});
			}else{
				$.confirm("您确定要下架此课程?下架后学员将无法再报名此课程。",function(a){
					if(a==true){
						$.ajax({
							url : rootPath + "/classType/StopSale",
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
								$.msg("课程下架成功");
							}
						});
					}else{
						return;
					}
				});
			}
		});
	});
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/class/editClass/validatePrivilige.js"></script>