<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程包详情</title> 
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
    
    <style type="text/css">
	.p1 {
		display: block;
		position: absolute;
		z-index: 2000;
		top: 10px;
		right: -280px;
	}
	
	.p2 {
		display: block;
		position: absolute;
		z-index: 2000;
		top: 10px;
		right: -280px;
	}
	
	.p3 {
		display: block;
		position: absolute;
		z-index: 2000;
		top: 10px;
		right: -280px;
	}
	
	.p1 .preview-container {
		width: 446px;
		height: 241px;
		overflow: hidden;
	}
	
	.p2 .preview-container {
		width: 255px;
		height: 138px;
		overflow: hidden;
	}
	
	.p3 .preview-container {
		width: 181px;
		height: 96px;
		overflow: hidden;
	}
	input+span{
		color:red;
	}
	</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/coursePackage/commonTitle.jsp" %>
<div class="u-wrap company overflow">
	<%@include file="/WEB-INF/jsp/coursePackage/commonClass.jsp" %>
     <div class="right-side">
      <div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">课程包封面</h2>
            <span class="line"></span>
        </div>
        <input type="hidden" id="itemOneid" value="${itemOneId }"/>
        <input type="hidden" id="packageId" value="${classPackage.id }"/>
        <div class="c-main">
            <div class="c-content">
            
                <p class="c q-c">
                    <span class="c-title top">课程包封面</span>
                    <span class="c-content">
                    <c:choose>
                    	<c:when test="${!empty classPackage.cover }">
                    		 <span class="view">
	                            <img id="commdotityPic" src="${classPackage.cover }" realPath="${realPath }" alt="课程图片">
	                        </span>
	                        <span class="btns"><a href="javascript:;" class="btn btn-default btn-upload">更换封面</a></span>
                    	</c:when>
                    	<c:otherwise>
                    		 <span class="view">
	                            <img id="commdotityPic" src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" realPath="" alt="课程图片">
	                        </span>
	                         <span class="btns"><a href="javascript:;" class="btn btn-default btn-upload">选择封面</a></span>
                    	</c:otherwise>
                    </c:choose>
                    </span>
                </p>
                <p class="c q-c">
                    <span class="c-title">课程包简介</span>
                    <span class="c-content">
                        <textarea style="width: 100%;height: 100px;" name="description" id="description" maxlength="120" placeholder="输入课程描述">${classPackage.description}</textarea>
                    	<span style="font-size: 10px;">注:课程包简介最多输入120个字</span>
                    </span>
                </p>
                
                <p class="c" style="margin-bottom:55px;">
                    <span class="c-title q-title">课程包详情</span>
                    <div class="about-edit">
		                <textarea class="ckeditor form-control" id="newsContents" name="content" rows="6" data-error-container="#editor2_error"></textarea>
		            </div>
                    <!-- <span class="c-content" style="display:inline-block;margin-top:10px;margin-left:12px;">
                    	<textarea class="ckeditor form-control" id="" name="content" rows="6" data-error-container="#editor2_error" style="max-width:920px; min-width:800px;"></textarea>
                    </span> -->
                </p>
                <p class="c text-center" style="margin-top: 10px;">
                    <a href="javascript:;" mark="save" class="btn btn-primary save_detail">保存</a>
                    <a href="javascript:;" mark="continue" class="btn btn-primary save_detail">保存并发布</a>
                    <a href="<%=rootPath %>/classPackage/list" class="btn btn-default">取消</a>
                </p>
            </div>
        </div>
    </div>
</div>
    </div>
</div>

<div class="upload-layer none" id="chooseDiv" style="width:1080px;">
    <div class="upload-title">
        <h2 class="h5">上传课程封面</h2>
        <i class="iconfont close">&#xe610;</i>
    </div>
    <div class="types">
        <a href="javascript:queryConditionPics('');" marks="privatePic" class="btn btn-default btn-success">私有云</a>
        <a href="javascript:queryConditionPics('');" marks="publicPic" class="btn btn-default">公有云</a>
    </div>
    <div class="types-list">
        <a href="javascript:queryAll(1,'');" class="btn btn-mini btn-link active">全部</a>
    </div>
    <div id="tlist">
	   
    </div>
    <div class="pic-upload none">
        <p class="tips">
         <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic()" value="重新选择文件"/>
          	<!--<a href="javascript:;" class="btn btn-mini btn-primary">重新选择文件</a>--> <span>建议上传的图片尺寸为：516*282px </span> 
        </p>
        <div class="upload-content" style="padding:10px;">
        <div class="attributes none">
        	 <input type="hidden" id="x" name="x" value="0"/>
            <input type="hidden" id="y" name="y" value="0"/>
            <input type="hidden" id="w" name="w" value="0"/>
            <input type="hidden" id="h" name="h" value="0"/>
            <input type="hidden" id="x2" name="x2" value="0"/>
            <input type="hidden" id="y2" name="y2" value="0"/>
        </div>
        	<div class="pic" style="width:516px;height:282px;background-color:#f2f2f2;">
        		 <img id="target" src="" />
            </div>
            <div class="upload-big p1" style="width:456px;">
            	<div class="preview-container" style="margin:0 auto;">
                <img src="">
                </div>
            </div>
            <div class="upload-sm p2">
            	<div class="preview-container">
                <img src="">
                </div>
            </div>
            <div class="upload-mini p3">
           		<div class="preview-container">
                <img src="">
                </div>
            </div>
        </div>
        <p class="text-center">
            <a href="javascript:classTypePic();" class="btn btn-primary">确定</a>
            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
        </p>
    </div>
</div>
<div class="add-layer-bg none" id="stopDiv"></div>
	
	<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<textarea style="display:none" id="introduce">${classPackage.introduce }</textarea>
    <script type="text/javascript">
    $(document).ready(function(){
    	editor = CKEDITOR.replace('newsContents',{ extraPlugins: 'video',uiColor: "#fafafa" });
		//editor.config.width="750";
		//$.editor.config.height="150";
		//$.editor.config.resize_minHeight = "220";
		//$.editor.config.resize_maxHeight = "220";
		//$.editor.config.allowedContent = true;
		//$.editor.config.format_p = { element: 'p', attributes: { 'class': 'normalPara' } };
		editor.config.width="880";
			editor.config.toolbar = [
				[ 'mode', 'document', 'doctools' ], [ 'Source', '-', 'NewPage' ] ,
				[ 'basicstyles', 'cleanup' ],
				[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript' ] ,
				[ 'list', 'indent', 'blocks', 'align', 'bidi' ],  
				[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
				[ 'Link', 'Unlink' ] ,
				[ 'Image', 'Video', 'Table' ],
				[ 'Styles', 'Format', 'Font', 'FontSize' ],
			    [ 'TextColor', 'BGColor' ],
				[ 'Maximize'] ,
				[ '-' ] ,
				[ 'About' ]
				];
		editor.config.baseFloatZIndex = 10100;
		var desc = $("#introduce").val();
		var desc1 = decodeURI(desc);
		var detailDesc = desc1.replace("\r\n",
				"<br>&nbsp;&nbsp;");
		editor.setData(detailDesc);
		editor.config.customConfig = 'config.js';
		
	});
		
		
	</script>
<!--  ajax加载中div结束 -->
 	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
 	<script type="text/javascript" src="<%=rootPath %>/javascripts/class/cousePackage/packageDetail.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
     <script type="text/javascript" src="<%=rootPath %>/javascripts/class/addClassTypePic.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/class/addClassTypeOnsale.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    
</body>
</html>