<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${itemCourses}" var="item" varStatus="index">
	<li><span class="info"> <span
			class="itemClass w150 itemObj" itemId="${item.configItemId}">${item.itemName}</span>
			<span class="itemClass w180 ">有效期&nbsp;<input type="text"
				class="w25 itDate${index.index}" style="height: 11px;" value="${item.validityDay}" />&nbsp;天
		</span> <span class="itemClass w180 ">单视频观看次数&nbsp;<input type="text"
				class="w25 itVideo${index.index}" style="height: 11px;" value="${item.videoWatchCount}" />&nbsp;次
		</span> <span class="itemClass w180 ">单直播回看次数&nbsp;<input type="text"
				class="w25 itLive${index.index}" style="height: 11px;" value="${item.liveWatchCount}" />&nbsp;次
		</span>
	</span></li>
</c:forEach>