<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>试题</title>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
<script type="text/javascript" src="<%=rootPath %>/javascripts/operate.js"></script>
<script>
$(function(){
	var editor = CKEDITOR.replace('newsContents');
	editor.config.width="800";
	editor.config.toolbar = [
		[ 'mode', 'document', 'doctools' ], [ 'Source', '-', 'NewPage' ] ,
		[ 'basicstyles', 'cleanup' ],
		[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript' ] ,
		[ 'list', 'indent', 'blocks', 'align', 'bidi' ],  
		[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
		[ 'Link', 'Unlink' ] ,
		[ 'Image', 'Table' ],
		[ 'Styles', 'Format', 'Font', 'FontSize' ],
	    [ 'TextColor', 'BGColor' ],
		[ 'Maximize','helloworld']
		];
	editor.config.baseFloatZIndex = 10100;
});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
	<div class="u-wrap classes">
    	<div class="mainbackground nopadding">
			<p class="c clear">
                 <span class="c-title">班型详情</span>
                   <div class="about-edit">
               <textarea class="ckeditor form-control" id="newsContents" name="content" rows="6" data-error-container="#editor2_error"></textarea>
             </div>
             </p>
         </div>
      </div>
      <script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
</body>
</html>