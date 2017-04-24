<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/ckeditor/ckeditor.js"></script>
 <div class="menu-nav-title">
      <ul class="row clear" style="background-color: #F6F6F6;">
          <li class="with20">内容名称</li>
          <li class="with50">链接</li>
          <li class="with10">方式</li>
          <li class="with20">操作</li>
      </ul>
  </div>
<c:forEach items="${foots }" var="foot">
	<div class="section n true" ids="${foot.id }" sort='${foot.sequence }'>
     <div class="block l-menu">
         <div class="title-wrap">
             <ul class="row clear">
                 <li class="with20">
                     <i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                     <span  class="title-info">${foot.name }</span>
                 </li>
                 <li class="with50 link-address">
                 	<c:choose>
                		<c:when test="${foot.urlType=='LINK_CUSTOM' }">
                			${foot.url }
                		</c:when>
                		<c:when test="${foot.urlType=='LINK_NONE' }">
                			
                		</c:when>
                		<c:otherwise>
                			sysPageHeadFoot/showDetail/${foot.id }
                		</c:otherwise>
                	</c:choose>
                 </li>
                 <li class="with10">
                	<c:choose>
                		<c:when test="${foot.urlType=='LINK_CUSTOM' }">
                			自定义链接
                		</c:when>
                		<c:when test="${foot.urlType=='LINK_NONE' }">
                			无链接
                		</c:when>
                		<c:otherwise>
                			默认链接
                		</c:otherwise>
                	</c:choose>
                 </li>
                 <li class="with20">
                     <i class="iconfont edit-icon mar-lr5">&#xe628;</i>
                     <i class="iconfont mar-lr5 delete-icon deleteparents_icon" ids="${foot.id }">&#xe67e;</i>
                 </li>
             </ul>
             <div class="field">
                 <div class="content father_content">
                     <p>
                         <span class="name">显示名称：</span>
                         <input type="text" value="${foot.name }" maxlength="6" class="title-content titleName">
                     </p>
                     <p class="links_url"><span class="name">&nbsp;&nbsp;&nbsp;&nbsp;方式：</span>
                     <c:choose>
                		<c:when test="${foot.urlType=='LINK_CUSTOM' }">
                			<input type="radio" name="linkMethod${foot.id }" checked="checked" value="LINK_CUSTOM" sort="1"/>自定义链接
                			<input type="radio" name="linkMethod${foot.id }" value="LINK_DEFAULT" sort="2"/>默认链接-编辑文本
                     		<input type="radio" name="linkMethod${foot.id }" value="LINK_NONE" sort="3"/>无连接
                		</c:when>
                		<c:when test="${foot.urlType=='LINK_NONE' }">
                			<input type="radio" name="linkMethod${foot.id }" value="LINK_CUSTOM" sort="1">自定义链接
                			<input type="radio" name="linkMethod${foot.id }" value="LINK_DEFAULT" sort="2">默认链接-编辑文本
                     		<input type="radio" name="linkMethod${foot.id }" checked="checked" value="LINK_NONE" sort="3">无连接
                		</c:when>
                		<c:otherwise>
                			<input type="radio" name="linkMethod${foot.id }" value="LINK_CUSTOM" sort="1">自定义链接
                			<input type="radio" name="linkMethod${foot.id }" checked="checked" value="LINK_DEFAULT" sort="2">默认链接-编辑文本
                     		<input type="radio" name="linkMethod${foot.id }" value="LINK_NONE" sort="3">无连接
                		</c:otherwise>
                	</c:choose>
                	</p>
                	<c:choose>
                		<c:when test="${foot.urlType=='LINK_CUSTOM' }">
                			 <p class="radio-tab1 radio-tab">
		                         <span class="name">链接地址：</span>
		                         <!-- <em>http://</em> -->
		                     <span>
		                         <input type="text" value="${foot.url }" class="address weburl" placeholder="">
		                         <a href="javascript:;" class="btn-ico">...</a>
		                     </span>&nbsp;&nbsp;示例:http://yunduoketang.com/course
		                     </p>
		                     <p class="radio-tab2 radio-tab"><span class="name"></span>
		                     	<textarea class="ckeditor form-control editorContents" id="footContents${foot.id }" ids="${foot.id }" mark="${foot.content }" rows="6"></textarea>
		                     </p>
		                     <p class="link-type type_url">
		                         <span class="name">链接类型：</span>
		                          <c:choose>
									<c:when test="${foot.openType=='self' }">
										<input type="radio" name="openType${foot.id }" checked="checked" value="self" />本页打开
					                    <input type="radio" name="openType${foot.id }" value="blank" />弹出新窗口
			                        </c:when>
									<c:otherwise>
										<input type="radio" name="openType${foot.id }" value="self" />本页打开
				                        <input type="radio" name="openType${foot.id }" checked="checked" value="blank" />弹出新窗口
			                         </c:otherwise>
								 </c:choose>
		                     </p>
                		</c:when>
                		<c:when test="${foot.urlType=='LINK_NONE' }">
                			<p class="radio-tab1 radio-tab" style="display: none;">
			                         <span class="name">链接地址：</span>
			                         <!--  <em>http://</em>-->
			                     <span>
			                         <input type="text" value="${foot.url }" class="address weburl" placeholder="">
			                         <a href="javascript:;" class="btn-ico">...</a>
			                     </span>&nbsp;&nbsp;示例:http://yunduoketang.com/course
		                     </p>
                			 <p class="radio-tab2 radio-tab" style="display: none;"><span class="name"></span>
		                     	<textarea class="ckeditor form-control editorContents" id="footContents${foot.id }" ids="${foot.id }" mark="${foot.content }" rows="6"></textarea>
		                     </p>
		                     <p class="link-type type_url" style="display: none;">
		                         <span class="name">链接类型：</span>
		                          <c:choose>
									<c:when test="${foot.openType=='self' }">
										<input type="radio" name="openType${foot.id }" checked="checked" value="self" />本页打开
					                    <input type="radio" name="openType${foot.id }" value="blank" />弹出新窗口
			                        </c:when>
									<c:otherwise>
										<input type="radio" name="openType${foot.id }" value="self" />本页打开
				                        <input type="radio" name="openType${foot.id }" checked="checked" value="blank" />弹出新窗口
			                         </c:otherwise>
								 </c:choose>
		                     </p>
                		</c:when>
                		<c:otherwise>
	                		<p class="radio-tab1 radio-tab" style="display: none;">
			                         <span class="name">链接地址：</span>
			                         <!--  <em>http://</em>-->
			                     <span>
			                         <input type="text" value="${foot.url }" class="address weburl" placeholder="">
			                         <a href="javascript:;" class="btn-ico">...</a>
			                     </span>&nbsp;&nbsp;示例:http://yunduoketang.com/course
		                     </p>
                			 <p class="radio-tab2 radio-tab" style="display: block;"><span class="name"></span>
		                     	<textarea class="ckeditor form-control editorContents" id="footContents${foot.id }" ids="${foot.id }" mark="${foot.content }" rows="6"></textarea>
		                     </p>
		                     <p class="link-type type_url">
		                         <span class="name">链接类型：</span>
		                          <c:choose>
									<c:when test="${foot.openType=='self' }">
										<input type="radio" name="openType${foot.id }" checked="checked" value="self" />本页打开
					                    <input type="radio" name="openType${foot.id }" value="blank" />弹出新窗口
			                        </c:when>
									<c:otherwise>
										<input type="radio" name="openType${foot.id }" value="self" />本页打开
				                        <input type="radio" name="openType${foot.id }" checked="checked" value="blank" />弹出新窗口
			                         </c:otherwise>
								 </c:choose>
		                     </p>
                		</c:otherwise>
                	</c:choose>
                     <p>
                         <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${foot.id }">
                         <input type="button"  value="取消" class="btn btn-primary">
                     </p>
                 </div>
             </div>
         </div>
     </div>
     <div class="block s-menu"></div>
 </div>
</c:forEach>
<div class="new-add-btn" style="margin-bottom: 10px;">
    <i class="iconfont new-add-icon">&#xe61c;</i>
    <span>新增导航链接</span>
</div>
<script type="text/javascript">
	$(function(){
         //排序
         $('#navbarconfigs').sortable({
				placeholder: "ui-state-highlight",
				items: "div.section:not(.ui-state-disabled)",
				update:function(event,ui){
					var sortMap=new Array();
					$("#navbarconfigs").find("div.section").each(function(i){
						var syspage={};
						syspage.id=$(this).attr("ids");
						syspage.sequence=$(this).index()+1;
						sortMap.push(syspage);
					})
					$.ajax({
					url: rootPath+"/sysPageHeadFoot/orderSortTitle",
					data: "list="+JSON.stringify(sortMap),
					type: "post",
					dataType: "json",
					success:function(jsonData){
						loadData();
						query_footShowContent();
					}
				})
				},
				delay: 200
			}).disableSelection();
         //文本编辑器赋值
         $("#navbarconfigs").find("div.section textarea").each(function(i){
     		var id=$(this).attr("ids");
     		var content=$(this).attr("mark");
     		var editor=CKEDITOR.replace('footContents'+id);
     		editor.config.width="90%";
         	editor.config.toolbar=[
         			['Source','Bold','Italic','Underline','Strike'],
         			['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
         			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
         			['Link','Unlink','Anchor'],
         			['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
         			['Code'],    
         		    ['Styles','Format','Font','FontSize'],
         		    ['TextColor','BGColor'],
         		    ['Maximize', 'ShowBlocks','-','About']
         		];
         	editor.config.baseFloatZIndex=10100;
         	var detailDesc="";
         	if(content){
         		detailDesc=decodeURI(content);
         	}
         	editor.setData(detailDesc);
       		editor.config.toolbarGroups = [
       			{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
       			{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
       			{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ] },
       			{ name: 'forms' },
       			'/',
       			{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
       			{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
       			{ name: 'links' },
       			{ name: 'insert' },
       			'/',
       			{ name: 'styles' },
       			{ name: 'colors' },
       			{ name: 'tools' },
       			{ name: 'others' }
       	    ];

       		editor.config.removeButtons = 'Underline,Subscript,Superscript';

       		// Set the most common block elements.
       		editor.config.format_tags = 'p;h1;h2;h3;pre';
       		
       		//set picture is null;
       		editor.config.image_previewText=' ';

       		// Simplify the dialog windows.
       		editor.config.removeDialogTabs = 'image:advanced;link:advanced';
       		
       		//图片上传
       	    editor.config.filebrowserImageUploadUrl = rootPath + '/classType/editorUploadImg';
     	});
	})
</script>
