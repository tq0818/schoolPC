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
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    <link rel="stylesheet" href="<%=rootPath  %>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" type="text/css"/>
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
    <script type="text/javascript">
        $(function() {


            var flag = '${isFuShengPaikeTeac}';

            if(flag != null && (flag== true || flag == 'true')){
                $('#pcenter').hide();
                $('.box-select').hide();
            }
            $(".loading").show();
            $(".loading-bg").show();
            $selectMenu("course_class_type");
            $chooseMenu("detailCode");
            //	$(".footer").addClass("footer-fixed");
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
//            editor.config.startupMode ='source';
//            editor.config.toolbar =[];

            $(".pic").on("change","#target", function() {
                var theImage = new Image();
                console.log($(this).attr("src"));
                theImage.src = $(this).attr("src");
                if (theImage.complete) {
                    sourceHeight = theImage.height;
                    sourceWidth = theImage.width;
                    $.init(sourceWidth, sourceHeight);
                } else {
                    theImage.onload = function () {
                        sourceHeight = theImage.height;
                        sourceWidth = theImage.width;
                        $.init(sourceWidth, sourceHeight);
                    };
                };

            });
            $("#teacherList").select2();
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
        //编辑详情
        function save(mark) {
            CKupdate();
            var value = $("#newsContents").val();
            var desc = $("#description").val();
            var pDesc = $("#paperDescription").val();
            var teacherId = $("#teacherList").val();
            var picUrl = $("#commdotityPic").attr("realPath");
            var classTypePic = $("#classTypePic").attr("realPath");
            if ((picUrl.length < 0 || picUrl == "") && (classTypePic.length < 0 || classTypePic == "")) {
                alert("课程封面不能为空!");
                return;
            }
            if (teacherId.length < 0 || teacherId == "") {
                alert("名师不能为空!");
                return;
            }
            if (desc.length < 0 || desc == "") {
                alert("课程描述不能为空!");
                return;
            }
            if(desc.length>100){
                alert("课程描述最多不能超过100个字符！");
                return;
            }
            if(pDesc.length>100){
                alert("试卷描述最多不能超过100个字符！");
                return;
            }
            value = encodeURI(value);
            if (value.length < 0 || value == "") {
                alert("课程详情不能为空!");
                return;
            }
            $.ajax({
                url : rootPath
                + "/simpleClasses/addClassSuccess",
                data : {
                    "id" : '${classTypeId}',
                    "cId" : '${cId}',
                    "cover" : picUrl,
                    "description" : desc,
                    "paperDescription" : pDesc,
                    "teacherId" : teacherId,
                    "detailDesc" : value
                },
                type : "POST",
                success : function(data) {
                    $.msg("保存信息成功");
                    return;
                }
            });
        }

        //上传截取后的图片
        function classTypePic() {
            $.ajax({
                url : rootPath + "/simpleClasses/saveCutPic",
                data : {
                    path : $("#target").attr("src"),
                    x : $("#x").val(),
                    y : $("#y").val(),
                    w : $("#w").val(),
                    h : $("#h").val(),
                    itemOneid : $("#itemOneid").val()
                },
                type : "post",
                dataType : "json",
                success : function(data) {
                    chooseOnePic(data.picOriginalUrl,
                            data.realPath);
                }
            })
            $("#chooseDiv").css("display", "none");
            $("#stopDiv").css("display", "none");
            return;
        }
    </script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<form method="post" id="myForm">
    <input type="hidden" name="id" value="${classTypeId }"/>
    <input type="hidden" name="itemOneId" id="itemOneid" value="${itemOneid }"/>
    <input type="hidden" name="type" id="type3" value="${type2 }"/>
    <input type="hidden" name="mark" id="marktype"/>
    <input type="hidden" name="lable" value="${lable }"/>
    <input type="hidden" name="itemSecondId" value="${itemSecondId }"/>
</form>
<%@include file="/WEB-INF/jsp/classType/commonTitle.jsp" %>
<div class="u-wrap company overflow">
    <%@include file="/WEB-INF/jsp/simpleClasses/commonClass.jsp" %>
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
                     <c:if test="${ct.cover=='' }">
                      	 <span class="view">
                            <img id="commdotityPic" src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" realPath="" alt="课程图片">
                        </span>
                         <span class="btns"><a href="javascript:;" class="btn btn-default btn-upload">选择封面</a></span>
                     </c:if>
                      <c:if test="${ct.cover!='' }">
                         <span class="view">
                            <img id="commdotityPic" src="${ct.cover }" realPath="" alt="课程图片">
                             <input id="classTypePic" type="hidden" realPath="${ct.cover }">
                        </span>
                          <span class="btns"><a href="javascript:;" class="btn btn-default btn-upload">更换封面</a></span>
                      </c:if>
                    </span>
                        </p>

                        <p class="c">
                            <span class="c-title">名师 <i class="require-txt">*</i></span>
                            <span class="c-content">
                        <select name="teacherId" id="teacherList" style="width:100px;">
                             
                           <c:forEach items="${teachers }" var="t">
                               <c:if test="${teacher.id==t.id }">
                                   <option value="${t.id }" selected="selected">${t.name }</option>
                               </c:if>
                               <c:if test="${teacher.id!=t.id }">
                                   <option value="${t.id }">${t.name }</option>
                               </c:if>
                           </c:forEach>
                        </select>
                        <span><span id="checkIsCommonTeac"><a href="javascript:void(0);" class="box-select" style="color:blue;">点击我添加教师</a></span></span>
                    </span>
                        </p>
                        <p class="c">
                            <span class="c-title">描述 <i class="require-txt">*</i></span>
                            <span class="c-content">
                                <textarea name="description" id="description" placeholder="输入课程描述">${ct.description}</textarea>
                            </span>
                        </p>
                        <p class="c">
                            <span class="c-title">试卷描述</span>
                            <span class="c-content">
                                <textarea name="paperDescription" id="paperDescription" placeholder="输入试卷描述">${ct.paperDescription}</textarea>
                            </span>
                        </p>
                        <p class="c clear">
                            <span class="c-title">课程详情 <i class="require-txt">*</i></span>
                        <div class="about-edit">
                            <textarea class="ckeditor form-control" id="newsContents" name="content" rows="6" data-error-container="#editor2_error"></textarea>
                        </div>
                        </p>
                        <p class="c text-center" style="margin-top: 10px;" id="pcenter">
                            <a href="javascript:save('save');" class="btn btn-primary">保存</a>
                            <a href="<%=rootPath %>/simpleClasses/showClassTypePage" class="btn btn-default">取消</a>
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
            <input type="file" class="btn btn-mini btn-primary" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" name="imgData" id="imgData" onchange="savePic()" value="重新选择文件"/>
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

<!-- 选择视频 -->
<div class="add-layer w800 none" style="z-index:10120;width:800px;height:470px;margin-top:-280px;">
    <i class="iconfont close">&#xe610;</i>
    <div class="tabs66 tabs videotabs">
        <span class="tab1 active" ids="neibu">内部视频</span>
        <span class="tab2" ids="outside">外部视频</span>
    </div>
    <div class="layer-content tab1">
        <div class="term">
            <span class="term-title">学科</span>
            <span class="term-title">
                <select name="" id="choose_itemOne" class="itemOne">
                </select>
            </span>
            <span class="term-title">学科小类</span>
            <span class="term-title">
                <select name="" id="choose_itemSecond" class="itemSecond">
                </select>
            </span>
            <span>
            	<input type="text" id="tj" value="" placeholder="查询条件">
              <input type="button" class="btn btn-sm btn-default" value="搜索">
            </span>
        </div>
        <div class="term-list">
            <table class="table table-center table-head">
                <tbody>
                <tr class="head">
                    <th style="width:30%;">文件名</th>
                    <th style="width:10%;">大小</th>
                    <th style="width:30%;">标签</th>
                    <th style="width:10%;">上传人</th>
                    <th style="width:20%;">上传时间</th>
                </tr>
                </tbody>
            </table>
            <div class="table-list">
                <table id="data_table" class="table table-center table-tbody">
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="pages videopagination">
        </div>
    </div>
    <div class="layer-content tab2 none">
        <div class="default-div">
            <div class="term">
                <span class="term-title">学科</span>
                <span class="term-title">
	                <select name="" id="choose_itemOne" class="itemOne">
	                </select>
	            </span>
                <span class="term-title">学科小类</span>
                <span class="term-title">
	                <select name="" id="choose_itemSecond" class="itemSecond">
	                </select>
	            </span>
                <span>
	            	<input type="text" id="tj" value="" placeholder="查询条件">
	              <input type="button" class="btn btn-sm btn-default" value="搜索">
	            </span>
                <a class="add-video-link" href="javascript:;" style="text-decoration: underline;color: blue;margin-left:50px;">新增视频</a>
            </div>
            <div class="term-list">
                <table class="table table-center table-head">
                    <tbody>
                    <tr class="head">
                        <th style="width:30%;">文件名</th>
                        <th style="width:10%;">大小</th>
                        <th style="width:30%;">标签</th>
                        <th style="width:10%;">上传人</th>
                        <th style="width:20%;">上传时间</th>
                    </tr>
                    </tbody>
                </table>
                <div class="table-list">
                    <table id="data_table" class="table table-center table-tbody">
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <div class="pages videopagination2">
                </div>
            </div>
        </div>
        <div class="add-div none">
            <div class="row">
                <div class="col">
                    <div class="tt">学科</div>
                    <div class="ct">
                        <select class="itemOne">

                        </select>
                    </div>
                </div>
                <div class="col">
                    <div class="tt">学科小类</div>
                    <div class="ct">
                        <select class="itemSecond">

                        </select>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col">
                    <div class="tt">标签</div>
                    <div class="ct"><input type="hidden" class="tag"/></div>
                </div>
                <div class="col">
                    <div class="tt">视频时长</div>
                    <div class="ct">
                        <input type="text" class="datetime-picker hour"/>
                        时
                        <input type="text" class="datetime-picker minute"/>
                        分
                        <input type="text" class="datetime-picker second"/>
                        秒
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="tt">网址</div>
                    <div class="ct">
                        <input type="text" id="videourl" class="input-left-radius url" placeholder="支持优酷、土豆，将视频页面网址复制到这里点击增加即可"/><a href="javascript:;" class="btn btn-default2 input-right-radius btn-add-url">增加</a>
                    </div>
                    <span class="tips">tip：视频会增加到您的视频库，不会丢失哦~    所以为了便于您选择，建议您给视频增加标签</span>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="tt">视频名称</div>
                    <div class="ct">
                        <input type="text" class="input-left-all video-name"/>
                    </div>
                </div>
            </div>
            <div class="btns">
                <a href="javascript:;" class="btn btn-mini btn-primary btn-s">保存</a>
                <a href="javascript:;" class="btn btn-mini btn-default btn-c">取消</a>
            </div>
        </div>
    </div>
</div>

<!-- 添加教师弹窗 -->
<jsp:include page="/WEB-INF/jsp/classType/commonAddTeacher.jsp"></jsp:include>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/addClassTypePic.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/addClassTypeOnsale.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/plugins/video/plugin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/plugins/video/plugin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/addTeacher.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/addClassTypeVideo.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/checkIsCommonTeac.js"></script>
</body>
</html>