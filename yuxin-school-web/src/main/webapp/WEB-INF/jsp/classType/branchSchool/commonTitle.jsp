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
       		<!--课程公开价格  -->
        	价格：${ct.publicPrice }元
        </div>
        <c:choose>
          	 <c:when test="${ct.cddsStatus=='1'  }">
          		<button class="public_course" ids="${ct.id }" mark="nosale">下架</button>
          	 </c:when>
          	 <c:otherwise>
          	 	<button class="public_course" ids="${ct.id }" mark="sale">上架</button>
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
				$.ajax({
					url : rootPath + "/branchSchool/setSaleOrNoSale",
					type : "post",
					data : {"id":id,"cddsStatus":'1'},
					beforeSend:function(XMLHttpRequest){
			              $(".loading").show();
			              $(".loading-bg").show();
			        },
					success : function(result) {
						$this.attr("mark","nosale");
						$this.text("下架");
						$.msg("课程上架成功");
					},
			        complete:function(XMLHttpRequest,textStatus){
			             $(".loading").hide();
			             $(".loading-bg").hide();
			        }
				});
			}else{
				$.confirm("您确定要下架此课程?下架后学员将无法再报名此课程。",function(a){
					if(a==true){
						$.ajax({
							url : rootPath + "/branchSchool/setSaleOrNoSale",
							type : "post",
							data : {"id":id,"cddsStatus":'0'},
							beforeSend:function(XMLHttpRequest){
					              $(".loading").show();
					              $(".loading-bg").show();
					        },
							success : function(result) {
								$this.attr("mark","sale");
								$this.text("上架");
								$.msg("课程下架成功");
							},
					        complete:function(XMLHttpRequest,textStatus){
					             $(".loading").hide();
					             $(".loading-bg").hide();
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