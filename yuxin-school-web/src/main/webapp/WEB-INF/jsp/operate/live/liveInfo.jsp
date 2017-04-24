<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" value="${mvlPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${mvlPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${mvlPage.pageSize }" id="pageSize"/>
<table class="table table-hover table-center operate_live_table">
<c:if test="${!empty mvlPage.data }">
<c:forEach var="m" items="${mvlPage.data }" varStatus="status">
<tr>
     	<td width="10%">
         	<div class="operate_w time">
  	<c:if test="${(m.liveDate.time - nowDate) <= (1000 * 60 * 60) && (m.liveEnd.time - nowDate) > 0}">
       		<p><span class="sp_red">直播倒计时</span></p>
           	<p><span class="sp_red countdown"></span></p>
           	<input type="hidden" value="<fmt:formatDate value="${m.liveDate }" pattern="yyyy-MM-dd HH:mm"/>" class="datetime"/>
           	<input type="hidden" value="${status.index }" class="index"/>
</c:if>
<c:if test="${(m.liveDate.time - nowDate) < 0 && (m.liveEnd.time - nowDate) > 0}">
       		<p><span class="sp_red">直播中</span></p>
</c:if>
<c:if test="${(m.liveDate.time - nowDate) > (1000 * 60 * 60)}">
<p><span class="sp_name">直播时间</span></p>
           	<p><span class="sp_name"><fmt:formatDate value="${m.liveDate }" pattern="yyyy-MM-dd HH:mm"/></span></p>
</c:if>
<c:if test="${(m.liveEnd.time - nowDate) < 0}">
<p><span class="sp_name">直播已结束</span></p>
           	<p><span class="sp_name"><fmt:formatDate value="${m.liveDate }" pattern="yyyy-MM-dd HH:mm"/></span></p>
</c:if>
        </div>
    </td>
    <td>
    	<div class="operate_w" style="text-align: left;">
    		<c:if test="${m.classType == 1 }">
    			<p><span class="sp_name">班号：</span><span class="sp_infor">${m.name }</span></p>
    		</c:if>
    		<p><span class="sp_name">课次：</span><span class="sp_infor">${m.lessonName }</span></p>
        </div>
    </td>
    <td>
    	<div class="operate_w" style="text-align: left;">
    		<c:if test="${m.classType == 1 }">
    			<p><span class="sp_name">学员数量：</span><span class="sp_infor">${m.enrollYetStudents == null ? 0 : m.enrollYetStudents }人</span></p>
    		</c:if>
    		<p><span class="sp_name">时长：</span><span class="sp_infor">${m.timeLong }</span></p>
        </div>
    </td>
    <td>
    	<div class="operate_w" style="text-align:justify;">
    		<p><span class="sp_name">授课老师：</span><span class="sp_infor">${m.teacherName }</span></p>
    		<p><span class="sp_name">授课类型：</span><span class="sp_infor"><c:if test="${m.classType == 1 }">直播课</c:if><c:if test="${m.classType == 2 }">公开课</c:if>
    		</span></p>
        </div>
    </td>
    <td width="20%" style="text-align: right;">
    	<div class="operate_btn_cell">
			<c:if test="${m.isday == 0 && (m.liveDate.time - nowDate) > 0}">
				<c:if test="${((role == 'admin' && m.userId == userid && roles == 'adminAndTeacher') || role == 'teacher') && m.classType == 1 }">
					<div class="operate_btn_fore">
		                <a href="javascript:;" class="btn btn-success operate_btn_30 btn-sel" data-id="${m.id }" data-module="${m.moduleId }">查看</a>
		        		<a href="javascript:;" class="btn btn-success operate_btn_30 btn-update" data-id="${m.id }" data-module="${m.moduleId }">修改</a>
		            </div>
	            </c:if>
	        	<%-- <div class="operate_btn_fore" style="display:none;">
	                <a href="javascript:;" class="btn btn-success operate_btn_30 btn-live">直播学员</a>
	                <a href="javascript:;" class="btn btn-success operate_btn_30" data-room="${m.id }" data-types="${m.classType }">下载聊天</a>
	            </div> --%>
	            <c:if test="${(role == 'admin' && m.userId == userid && roles == 'adminAndTeacher') || role == 'teacher'}">
		            <c:if test="${!empty m.liveCompanyType && m.liveCompanyType == 'zs' }">
			            <div class="operate_btn_fore operate_btn">
			            	<a href="javascript:;" class=" btn btn-success operate_btn btn-addcha" data-id="${m.liveroomIdGh }" data-lesid="${m.id }">课件管理</a>
			            </div>
		            </c:if>
		            <div class="operate_btn_fore operate_btn">
		            	<a href="javascript:;" class=" btn btn-success operate_btn btn-up" data-type="upclass" data-id="${m.id }" data-classType="${m.classType }">开始讲课</a>
		            </div>
	            </c:if>
	            <c:if test="${(role == 'admin' && m.userId != userid) || (role == 'admin' && m.userId == userid && roles == null)}">
		            <div class="operate_btn_fore operate_btn">
		            	<a href="javascript:;" class=" btn btn-success operate_btn btn-up" data-type="upclass" data-id="${m.id }" data-classType="${m.classType }">进入听课</a>
		            </div>
	            </c:if><%-- 
	            <c:if test="${role == 'admin' && m.userId == userid}">
		            <div class="operate_btn_fore operate_btn">
		            	<a href="javascript:;" class=" btn btn-success operate_btn btn-up" data-type="upclass" data-id="${m.id }">开始讲课</a>
		            </div>
	            </c:if> --%>
			</c:if>
			<c:if test="${(m.liveDate.time - nowDate) < 0 && (m.liveEnd.time - nowDate) > 0}">
				<c:if test="${((role == 'admin' && m.userId == userid && roles == 'adminAndTeacher') || role == 'teacher') && m.classType == 1 }">
	        	<div class="operate_btn_fore">
	                <a href="javascript:;" class="btn btn-success operate_btn_30 btn-sel" data-id="${m.id }" data-module="${m.moduleId }">查看</a>
	        		<a href="javascript:;" class="btn btn-success operate_btn_30 btn-update" data-id="${m.id }" data-module="${m.moduleId }">修改</a>
	            </div>
	            </c:if>
	        	<%--<div class="operate_btn_fore" style="display:none;">
	                <a href="javascript:;" class="btn btn-success operate_btn_30 btn-live">直播学员</a>
	                <a href="javascript:;" class="btn btn-success operate_btn_30">下载聊天</a>
	            </div> 
	            <c:if test="${role == 'teacher' || (m.userId == userid)}">
	            <div class="operate_btn_fore operate_btn">
	            	<a href="javascript:;" class=" btn btn-success operate_btn btn-up" data-type="upclass" data-id="${m.id }">开始讲课</a>
	            </div>
	            </c:if> --%>
	            <c:if test="${(role == 'admin' && m.userId != userid) || (role == 'admin' && m.userId == userid && roles == null)}">
	            <div class="operate_btn_fore operate_btn">
	            	<a href="javascript:;" class=" btn btn-success operate_btn btn-up" data-type="upclass" data-id="${m.id }" data-classType="${m.classType }">进入听课</a>
	            </div>
	            </c:if>
	            <c:if test="${(role == 'admin' && m.userId == userid && roles == 'adminAndTeacher') || role == 'teacher'}">
		            <c:if test="${!empty m.liveCompanyType && m.liveCompanyType == 'zs' }">
			            <div class="operate_btn_fore operate_btn">
			            	<a href="javascript:;" class=" btn btn-success operate_btn btn-addcha" data-id="${m.liveroomIdGh }"  data-lesid="${m.id }">课件管理</a>
			            </div>
		            </c:if>
		            <div class="operate_btn_fore operate_btn">
		            	<a href="javascript:;" class=" btn btn-success operate_btn btn-up" data-type="upclass" data-id="${m.id }" data-classType="${m.classType }">开始讲课</a>
		            </div>
	            </c:if>
			</c:if>
			<c:if test="${m.isday == 2}">
				<c:if test="${((role == 'admin' && m.userId == userid && roles == 'adminAndTeacher') || role == 'teacher') && m.classType == 1 }">
		        	<div class="operate_btn_fore">
		                <a href="javascript:;" class="btn btn-default operate_btn_30 btn-sel" data-id="${m.id }" data-module="${m.moduleId }">查看</a>
		        		<a href="javascript:;" class="btn btn-default operate_btn_30 btn-update" data-id="${m.id }" data-module="${m.moduleId }">修改</a>
		            </div>
	            </c:if>
	        	<%-- <div class="operate_btn_fore" style="display:none;">
	                <a href="javascript:;" class="btn btn-default operate_btn_30 btn-live">直播学员</a>
	                <a href="javascript:;" class="btn btn-default operate_btn_30">下载聊天</a>
	            </div> --%>
	            <div class="operate_btn_fore operate_btn">
	            	<a href="javascript:;" class=" btn btn-default operate_btn btn-up" data-type="" data-id="">等待上课</a>
	            </div>
			</c:if>
			<c:if test="${(m.isday == 1 || m.isday == 0) && (nowDate - m.liveEnd.time) > (1000 * 60 * 60)}">
				<c:if test="${((role == 'admin' && m.userId == userid && roles == 'adminAndTeacher') || role == 'teacher') && m.classType == 1 }">
		        	<div class="operate_btn_fore">
		                <a href="javascript:;" class="btn btn-default operate_btn_30 btn-sel" data-id="${m.id }" data-module="${m.moduleId }">查看</a>
		        		<a href="javascript:;" class="btn btn-default operate_btn_30 btn-update" data-id="${m.id }" data-module="${m.moduleId }">修改</a>
		            </div>
	            </c:if>
	        	<%-- <div class="operate_btn_fore" style="display:none;">
	                <a href="javascript:;" class="btn btn-default operate_btn_30 btn-live">直播学员</a>
	                <a href="javascript:;" class="btn btn-default operate_btn_30">下载聊天</a>
	            </div> --%>
	            <div class="operate_btn_fore operate_btn">
                	<a href="javascript:;" class="btn btn-success operate_btn btn-download" data-room="${m.id }" data-types="${m.classType }">下载聊天</a>
	            </div>
	            <div class="operate_btn_fore operate_btn">
	            	<a href="javascript:;" class=" btn btn-default operate_btn btn-up" data-type="huifang" data-id="${m.id }" data-classType="${m.classType }">查看回放</a>
	            </div>
			</c:if>
        </div>
    </td>
</tr> 
</c:forEach>
</c:if>
<c:if test="${empty mvlPage.data}">
	<tr>
		<td align="center">没有发现您要找的内容!</td>
	</tr>
</c:if>
</table>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/operate/live/liveInfo.js"></script>