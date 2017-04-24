<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<input type="hidden" value="${topic.id }" id="topicCaseId"/>
	<c:if test="${!empty paperId and paperId ne 0}">
		<input type="hidden" value="${topic.tikuCategoryId }" id="categoryId"/>
		<input type="hidden" value="${topic.tikuSubjectId }" id="subId">
		<input type="hidden" value="TOPIC_TYPE_CASE" id="topicType">
		<input type="hidden" value="edit" id="btn">
	</c:if>

	 <span class="qution-title case-content" style="float: left;padding-left: 100px;">材料内容</span>
     <div class="qution-content">
         <div class="qution-control" style="padding-left:200px;">
             <span class="qution-input">
                 <textarea name="" id="quesContent" style="width:85%;height: 400px;">${topic.topicName }</textarea>
             </span>
         </div>
         <div class="qution-control">
             <p class="text-center">
                 <a href="javascript:;" class="btn btn-primary btn-button" data-commit="save">保存</a>
                 <c:choose>
                 	<c:when test="${!empty paperId and paperId ne 0}">
                 		<a href="javascript:;" class="btn btn-default btn-childCancel">取消</a>
                 	</c:when>
                 	<c:otherwise>
		                 <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
                 	</c:otherwise>
                 </c:choose>
             </p>
         </div>
     </div>
     <script type="text/javascript" src="/plugins/ckeditor/ckeditor.js"></script>
	 <script type="text/javascript">
		var editor = CKEDITOR.replace('quesContent');
		editor.config.width="800px";
		editor.config.toolbar = [
						[ 'mode', 'document', 'doctools' ],
						[ 'Source', ],
						[ 'basicstyles', 'cleanup' ],
						[ 'Bold', 'Italic', 'Underline', 'Strike' ],
						[ 'NumberedList', 'BulletedList',
								'JustifyLeft', 'JustifyCenter', 'JustifyRight',
								'JustifyBlock' ], [ 'Link', 'Unlink' ],
						[ 'Image', 'Table' ],
						[ 'Styles', 'Format', 'Font', 'FontSize' ], [ '-' ],
						['Maximize']
						];
		editor.config.baseFloatZIndex = 10100;
		editor.config.customConfig = 'config.js';
	 </script>
        
	<script type="text/javascript" src="/javascripts/tiku/question/edit.js"></script>