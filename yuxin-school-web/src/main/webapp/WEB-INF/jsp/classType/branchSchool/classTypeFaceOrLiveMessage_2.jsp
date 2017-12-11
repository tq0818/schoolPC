<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>课程-课程详情</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
    <script type="text/javascript">
        $(function() {
    		$selectMenu("course_class_type");
    		$chooseMenu("detailCode");
            var editor = CKEDITOR.replace('newsContents',{ extraPlugins: 'video',uiColor: "#fafafa",allowedContent: true } );
            editor.config.width="880";
            editor.config.toolbar = [
                [ 'mode', 'document', 'doctools' ], [ 'Source', '-', 'NewPage' ] ,
                [ 'basicstyles', 'cleanup' ],
                [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript' ] ,
                [ 'list', 'indent', 'blocks', 'align', 'bidi' ],
                [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
                [ 'Link', 'Unlink' ] ,
                [ 'Image','Video','Table' ],
                [ 'Styles', 'Format', 'Font', 'FontSize' ],
                [ 'TextColor', 'BGColor' ],
                [ 'Maximize'] ,
                [ '-' ] ,
                [ 'About' ]
            ];
            editor.config.baseFloatZIndex = 10100;
            var desc = "${ct.detailDesc}";
            var desc1 = decodeURI(desc);
            var detailDesc = desc1.replace("\r\n",
                    "<br>&nbsp;&nbsp;").replace(/<i /g,'<p ').replace(/<\/i>/g,'</p>');
            editor.setData(detailDesc);
            editor.config.customConfig = 'config.js';
            CKEDITOR.on('instanceReady', function (ev) {
	               editor = ev.editor;
	               editor.setReadOnly(true); 
	           });
//            editor.config.startupMode ='source';
//            editor.config.toolbar =[];

        });
        window.onload=function(){
            $(".loading").hide();
            $(".loading-bg").hide();
        }
        //处理CKEDITOR的值
        function CKupdate() {
            for (instance in CKEDITOR.instances) {
                CKEDITOR.instances[instance].updateElement();
            }
        }
    </script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonTitle.jsp"></jsp:include>
<div class="u-wrap company overflow">
	<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonClass.jsp"></jsp:include>
    <div class="right-side">
        <div class="u-wrap classes">
            <div class="mainbackground nopadding">
                <!--          <div class="heading"> -->
                <%--             <span class="h5">${ct.name }</span> --%>
                <!--             <span class="float-right"> -->
                <%-- 	             <span class="courseStatus">${ct.isSale==0?'未上架':'已上架' }</span> --%>
                <%-- 	            <span>${ct.itemOneName }-${ct.itemSecondName }</span> --%>
                <%-- 	            <span>定价:${ct.realPrice }</span> --%>
                <!--             </span> -->
                <!--             <span class="line"></span> -->
                <!--         </div> -->
                <div class="c-main">
                    <div class="c-content">
                        <p class="c">
                            <span class="c-title top">课程封面 <i class="require-txt">*</i></span>
                            <span class="c-content">
	                         <span class="view">
	                            <c:choose>
	                            	<c:when test="${not empty ct.cover }"><img id="commdotityPic" src="${ct.cover }" realPath="" alt="课程图片"></c:when>
	                            	<c:otherwise>
			                            <img id="commdotityPic" src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" realPath="" alt="课程图片">
			                        </c:otherwise>
	                            </c:choose>
	                        </span>
                    </span>
                        </p>

                        <p class="c">
                            <span class="c-title">名师 <i class="require-txt">*</i></span>
                            <span class="c-content">
                            	${ct.teacherName }
                    		</span>
                        </p>
                        <p class="c">
                            <span class="c-title">描述 <i class="require-txt">*</i></span>
                            <span class="c-content">
                                <textarea name="description" id="description" placeholder="输入课程描述" class="readonly" readonly="readonly">${ct.description}</textarea>
                            </span>
                        </p>
                        <p class="c">
                            <span class="c-title">试卷描述</span>
                            <span class="c-content">
                                <textarea name="paperDescription" id="paperDescription" placeholder="输入试卷描述"  class="readonly" readonly="readonly">${ct.paperDescription}</textarea>
                            </span>
                        </p>
                        <p class="c clear">
                            <span class="c-title">课程详情 <i class="require-txt">*</i></span>
                        <div class="about-edit">
                            <textarea class="ckeditor form-control" id="newsContents" name="content" rows="6" data-error-container="#editor2_error"></textarea>
                        </div>
                        </p>
                        <p class="c text-center" style="margin-top: 10px;" id="pcenter">
                           <!--  <a href="javascript:save('save');" class="btn btn-primary">保存</a> -->
                            <a href="<%=rootPath %>/branchSchool/queryClassType" class="btn btn-default">返回</a>
               
						    <input type="hidden" name="id" value="${ct.id }"/>
						    <input type="hidden" id="companyId" value="${ct.companyId }"/>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 选择视频 -->


<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/plugins/video/plugin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/plugins/video/plugin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
</body>
</html>