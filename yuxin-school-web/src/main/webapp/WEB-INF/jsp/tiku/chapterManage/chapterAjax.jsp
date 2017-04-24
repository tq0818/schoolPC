<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${chapterList}" var="chapter">
	<li style="position: relative;" chapterId="${chapter.id}" class="chOrder chaCss${chapter.id}">
		<span class="info" chapterId="${chapter.id}" style="display: inline-block;width: 170px;height: 32px;">
		<a href="javascript:void(0)" chapterId="${chapter.id}" style="line-height: 2.5">${chapter.chapterName}</a>
		</span>
		
		<span class=" chapterM none" >
		<a href="javascript:void(0)" class="btn btn-sm iconfont" style="padding: 7px 7px;float: right;" chapterDelId="${chapter.id}" onclick="javascript:Forms.delChapter(this)">&#xe626;</a>
		<a href="javascript:void(0)" class="btn btn-sm iconfont" ht="编辑" style="padding: 7px 7px;float: right;">&#xe625;</a>
		</span>
		
		<span style="" class="none edit"><br/>
		<input type="text" value="${chapter.chapterName}" maxlength="22" class="newChapterName" style="width: 153px;"/>
		<a href="javascript:void(0)" class="btn btn-sm btn-success" chapterAddId="${chapter.id}">保存</a>
		<a href="javascript:void(0)" class="btn btn-sm btn-default">取消</a>
		</span>
	</li>
</c:forEach>