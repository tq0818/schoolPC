<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<div class="r-list-content clear show">
	<div class="r-list-content-title">
		<span class="r-list-content-title-txt">授课信息</span>
	</div>
	<div class="r-c-list">
		<c:forEach items="${sysConfigTeacherLessonVos }" var="item">
			<table class="table">
				<col width="33%">
				<col width="33%">
				<col width="33%">
				<tr>
					<td rowspan="2"><span class="c-class-title">${item.moduleName }</span>
					</td>
					<td><span class="t-title">学科</span> <span class="t-content">${item.itemOneName }</span>
					</td>
					<td><span class="t-title">模块课时</span> <span class="t-content"><em>${item.totalHours }</em></span>
					</td>
				</tr>
				<tr>
					<td><span class="t-title">学科小类</span> <span class="t-content">${item.itemSecondName }</span>
					</td>
					<td><span class="t-title">授课方式</span> <span class="t-content"><em>${wx:dictCode2Name(item.teachMethod)}</em></span>
					</td>
					<td></td>
				</tr>
			</table>
		</c:forEach>
	</div>
</div>