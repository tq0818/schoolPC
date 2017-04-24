<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"/>
    <div class="operate_main nospace">
       	 <div class="heading">
                <h2 class="h5">直播信息</h2>
                <span class="line"></span>
            </div>
           <div class="class_number_cell">
            	<div class="w h">
               	<span class="class_number_name">直播主题：</span>
                   <span class="class_number_sp">${cml.lessonName }</span>
               </div>
               <div class="w h">
               	<span class="class_number_name">老师：</span>
                   <span class="class_number_sp">${teacher.name }</span>
               </div>
               <div class="w h">
               	<span class="class_number_name">开始时间：</span>
                   <span class="class_number_sp">
                   <c:if test="${(endDate.time - nowDate) > 0 && status == 1}">
                   		<input class="Wdate" readonly="readonly" type="text" value="<fmt:formatDate value="${startDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="tracktime"/>
                   </c:if>
                   <c:if test="${(endDate.time - nowDate) < 0 || status == 0}">
                   		<fmt:formatDate value="${startDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                   </c:if>
                   </span>
               </div>
               <div class="w h">
               	<span class="class_number_name">结束时间：</span>
                   <span class="class_number_sp">
                   <c:if test="${(endDate.time - nowDate) > 0  && status == 1}">
                   		<input class="Wdate" readonly="readonly" type="text" value="<fmt:formatDate value="${endDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="updateTime"/>
                   </c:if>
                   <c:if test="${(endDate.time - nowDate) < 0 || status == 0}">
                   		<fmt:formatDate value="${endDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                   </c:if>
                   </span>
               </div>
               <div class="w h">
               	<span class="class_number_name">助教：</span>
               		<c:if test="${(endDate.time - nowDate) > 0 && assistantList != null }">
               			<span class="class_number_sp">
               				<select id="assistant" style="padding: 0;">
               				<option value="-1">请选择</option>
               				<c:forEach var="t" items="${assistantList }">
               					<c:if test="${t.id == assistant.id }">
               						<option value="${t.id }" selected="selected">${t.name }</option>
               					</c:if>
               					<c:if test="${t.id != assistant.id }">
               						<option value="${t.id }">${t.name }</option>
               					</c:if>
               				</c:forEach>
               				</select>
               			</span>
               		</c:if>
           			<c:if test="${(endDate.time - nowDate) < 0 && assistantList != null}">
           				<span class="class_number_sp">${assistant.name }</span>
           			</c:if>
               		<c:if test="${assistantList == null }">
                   		<span class="class_number_sp" style="padding: 0;">${assistant.name }</span>
               		</c:if>
               </div><%-- 
               <div class="w h">
               	<span class="class_number_name">课程类型：</span>
               	<c:if test="${module.moduleType == 'LEADING_COURSE' }">
                   <span class="class_number_sp">前导课</span>
               	</c:if>
               	<c:if test="${module.moduleType == 'THEORY_COURSE' }">
                   <span class="class_number_sp">理论课</span>
               	</c:if>
               	<c:if test="${module.moduleType == 'PRACTICAL_COURSE' }">
                   <span class="class_number_sp">实操课</span>
               	</c:if>
               	<c:if test="${module.moduleType == 'SERIES_COURSE' }">
                   <span class="class_number_sp">串讲课</span>
               	</c:if>
               	<c:if test="${module.moduleType == 'SEA_CLASS_COURSE' }">
                   <span class="class_number_sp">题海课</span>
               	</c:if>
               	<c:if test="${module.moduleType == 'INTERVIEW_COURSE' }">
                   <span class="class_number_sp">面试课</span>
               	</c:if>
               	<c:if test="${module.moduleType == 'EXERCISE_COURSE' }">
                   <span class="class_number_sp">习题课</span>
               	</c:if>
               	<c:if test="${module.moduleType == 'COMPUTER_COURSE' }">
                   <span class="class_number_sp">上机课</span>
               	</c:if>
               	<c:if test="${module.moduleType == 'SELF_STUDY_COURSE' }">
                   <span class="class_number_sp">自习课</span>
               	</c:if>
               	<c:if test="${module.moduleType == 'YY_ANSWER_COURSE' }">
                   <span class="class_number_sp">直播答疑</span>
               	</c:if>
               </div> --%>
               <div class="w h">
               	<span class="class_number_name">当前状态：</span>
               	<c:if test="${(startDate.time - nowDate) <= (1000 * 60 * 30) && (startDate.time - nowDate) > 0}">
                   <span class="class_number_sp">即将直播</span>
               	</c:if>
               	<c:if test="${(startDate.time - nowDate) < 0 && (endDate.time - nowDate) > 0}">
               		<span class="class_number_sp">正在直播</span>
               	</c:if>
               	<c:if test="${(startDate.time - nowDate) > (1000 * 60 * 30)}">
               		<span class="class_number_sp">等待上课</span>
               	</c:if>
               	<c:if test="${(endDate.time - nowDate) < 0}">
               		<span class="class_number_sp">直播结束</span>
               	</c:if>
               </div>
           </div>
           <div class="subbtns return">
               <a class="btn btn-big btn-default btn-return" href="javascript:;">返回列表</a>
           </div>
           <div class="subbtns update" style="display:none">
	           <c:if test="${status == 0 }">
	          		 <a class="btn btn-big btn-success btn-save-update" href="javascript:;" style="display:none">保存</a>
	           </c:if>
	           <c:if test="${status == 1 && (endDate.time - nowDate) > 0}">
	           		<a class="btn btn-big btn-success btn-save-update" href="javascript:;" data-id="${cml.id }">保存</a>
	           </c:if>
               		<a class="btn btn-big btn-default btn-return" href="javascript:;">取消</a>
           </div>
       </div>
       <script src="<%=rootPath%>/javascripts/datetime/WdatePicker.js"></script>
       <script src="<%=rootPath%>/javascripts/operate/live/detail.js">
       	
       </script>