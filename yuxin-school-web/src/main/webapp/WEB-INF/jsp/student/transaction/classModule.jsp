<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${list }" var="classModule">
	<c:if test="${classModule.teachMethod!='TEACH_METHOD_VIDEO' }">
		<p class="public clear">
			<span class="left clear"> <span class="c-title">课程单元</span> <span
				class="c-content">${classModule.name }</span> <span class="c-title">课程单元类型</span>
				<span class="c-content">
				<c:if test="${classModule.teachMethod=='TEACH_METHOD_FACE' }">
					面授
				</c:if>
				<c:if test="${classModule.teachMethod=='TEACH_METHOD_LIVE' }">
					直播
				</c:if>
				</span>
			</span> 
			
			<span class="right clear">
			<c:if test="${fn:length(classModule.classModuleNos) ==0}">
							<span class="block"> <span class="c-title">可选班号</span> <span
								class="c-content"><em>此模块没有安排课程，请督促安排</em></span>
							</span>
			</c:if> 
			<c:if test="${classModule.classModuleNos !=''&&classModule.classModuleNos !=null }">
				<c:forEach items="${classModule.classModuleNos }" var="classModuleNo"
					varStatus="v">

					<c:if test="${v.index==0 }">

						<c:if test="${classModuleNo.name!=''&& classModuleNo.name!=null }">
							<span class="block type" value="${classModule.id }:${classModuleNo.id }" campusId="${classModuleNo.campusId}"> <span class="c-title">可选班号</span>
								<span class="c-content type"><b>${classModuleNo.name }</b></span>
							</span>
						</c:if>

					</c:if>
					<c:if test="${v.index!=0 }">
						<span class="block  type" value="${classModule.id }:${classModuleNo.id }"  campusId="${classModuleNo.campusId}"> <span class="c-title">&nbsp;</span> <span
							class="c-content"><b>${classModuleNo.name }</b></span>
						</span>
					</c:if>

				</c:forEach>
			</c:if>
			
			</span>
		</p>
	</c:if>
	<c:if test="${classModule.teachMethod=='TEACH_METHOD_VIDEO' }">
		<p class="long">
			<span class="c-title">课程单元</span> <span class="c-content">${classModule.name }</span>
			<span class="c-title">课程单元类型</span> <span class="c-content">录播</span> <span
				class="c-title">总课时</span> <span class="c-content">${classModule.totalClassHour }课时</span>
		</p>
	</c:if>
</c:forEach>
<script>
	$(function(){
		
		$(".type").bind("click",function(){
			/* $(this).addClass("active");
			alert($(this)); */
			$(this).parent().find(".active").removeClass("active");
			$(this).addClass("active");
		})
	})
	

</script>