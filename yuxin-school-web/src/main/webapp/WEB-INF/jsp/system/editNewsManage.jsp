<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>${type=='save'?'新增新闻':'编辑新闻' }</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-fileupload/bootstrap-fileupload.css" />
    
    <script type="text/javascript">
	$(function(){
		$selectSubMenu('netschool_news');
		var editor=CKEDITOR.replace('newsContents',{ extraPlugins: 'video,audio',uiColor: "#fafafa",allowedContent: true });
		editor.config.toolbar=[
				[ 'mode', 'document', 'doctools' ], [ 'Source', '-', 'NewPage' ] ,
				[ 'basicstyles', 'cleanup' ],
				[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript' ] ,
				[ 'list', 'indent', 'blocks', 'align', 'bidi' ],  
				[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
				[ 'Link', 'Unlink' ] ,
				[ 'Image', 'Table' ],
				[ 'Styles', 'Format', 'Font', 'FontSize' ],
			    [ 'TextColor', 'BGColor' ],
				[ 'Maximize'] ,
				[ '-' ] ,
				[ 'About' ],
                [ 'Video', 'Audio' ]
		];
		editor.config.baseFloatZIndex = 10100;
		var desc = "${sysNews.newsContent }";
		var desc1 = decodeURI(desc);
		var detailDesc = desc1.replace("\r\n",
				"<br>&nbsp;&nbsp;");
		editor.setData(detailDesc);
		editor.config.customConfig ='config.js';
		
		//类型
		$("#typeList").on('click','a.btn',function(){
			var _this=$(this),status= _this.hasClass('btn-primary');
			if(!status){
				 _this.addClass('btn-primary').siblings('a').removeClass('btn-primary');
			}
		});
		  $('#c-up').on('click',function(){
              $(this).next('input.up').click();
          });
	});
	</script>
	<script type="text/javascript">
	 //处理CKEDITOR的值
	function CKupdate() {
	    for (instance in CKEDITOR.instances){
	      CKEDITOR.instances[instance].updateElement();
	    }
	}
	/**
	 * 保存或修改公告
	 * @param operate
	 */
	function saveNews(operate) {
		CKupdate();
		$(this).attr('disabled','disabled');
		var saveUrl=null;
		var newsStatus=0;
		var id=$("#nId").val();
		var newsTitle=$("#titles").val();
		if(newsTitle ==null || newsTitle.trim().length==0){
		    $.msg("新闻标题不能为空！");
		    return;
        }
        var secondTitle=$("#secondTitle").val();
        if(secondTitle ==null || secondTitle.trim().length==0){
            $.msg("新闻副标题不能为空！");
            return;
        }
        var author=$("#author").val();
        if(author ==null || author.trim().length==0){
            $.msg("作者不能为空！");
            return;
        }

        var keyWord=$("#keyWord1").val()+","+$("#keyWord2").val()+","+$("#keyWord3").val()+","+$("#keyWord4").val()+","+$("#keyWord5").val();
        var str =""+$("#keyWord1").val()+$("#keyWord2").val()+$("#keyWord3").val()+$("#keyWord4").val()+$("#keyWord5").val();
        if(str ==null || str.trim().length==0){
            $.msg("关键词不能为空！");
            return;
        }
		var schoolId=$("#scId").val();
		var newsType="";
		newsType=$("#typeList option:selected").attr("marks");
		var recommendFlag=$("input[type=radio]:checked").val();
		var readBaseIndex=$("#readBaseIndex").val();
		var value =$("#newsContents").val();
		var newsContent=encodeURI(value);
		
		function  getBLen(str){
			if (str == null) return 0;  
		    if (typeof str != "string"){  
		        str += "";  
		    }  
		    return str.replace(/[^\x00-\xff]/g,"01").length;  
		}
		
		//表单验证
		if(!newsTitle){
			alert("新闻标题不能为空！");
			$("#titles").val('');
			$("#titles").focus();
			$(this).attr('disabled',false)
			return;
		}
		if("saveAndOnsale" == operate){
			newsStatus=1;
		}
		//var pic2 = $('#imgObject').attr('ids');
		var src = $('#imgObject').attr('src');
		if(recommendFlag=="1" && !src){
			$.msg('推荐新闻请上传封面图片！');
			return;
		}
		var typeMark=$("#typeMark").val();
		if(typeMark=="2"){
			var pic= $("#imgObject").attr("ids");
			if(pic=="null"){
				pic="";
			}
			var id=$("#nId").val();
			saveUrl = rootPath + "/sysConfigNews/updateNews";
			$.ajax({
				url : saveUrl,
				type: "post",
				data : {"id" : id,
					"newsTitle" : newsTitle, 
					"newsType" : newsType,
					"readBaseIndex" : readBaseIndex,
					"newsStatus" : newsStatus,
					"newsContent" : newsContent,
					"newsPic" : pic,
					"schoolId" : schoolId,
					"recommendFlag":recommendFlag,
                    "secondTitle":secondTitle,
                    "author":author,
                    "keyWord":keyWord
					},
					success : function(data) {
						$.msg("修改新闻成功!");
						location.href=rootPath+"/sysConfigNews/showNews";
						$(this).attr('disabled',false);
					}
			});
		}else{
			saveUrl = rootPath+"/sysConfigNews/addNews";
			$.ajax({
				url : saveUrl,
				type: "post",
				data : {
					"newsTitle" : newsTitle,
					"newsType" : newsType,
                    "readBaseIndex" : readBaseIndex,
					"newsStatus" : newsStatus,
					"newsContent" : newsContent,
					"newsPic" : $("#imgObject").attr("ids"),
					"schoolId" : schoolId,
					"recommendFlag":recommendFlag,
                    "secondTitle":secondTitle,
                    "author":author,
                    "keyWord":keyWord
					},
				success : function(data) {
					$.msg("添加新闻成功!");
					location.href=rootPath+"/sysConfigNews/showNews";
					$(this).attr('disabled',false);
				}
			});
		}
	}
	</script>
</head>

<body>
<form method="post" id="saveForm">
	<input type="hidden" name="type" value="${type }" id="typeMark"/>
	<input type="hidden" id="nId" value="${sysNews.id }"/>
	<input type="hidden" id="scId" value="${schoolId }"/>
</form>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">${type=='save'?'新增新闻公告':'编辑新闻公告' }</h2>
            <span class="line"></span>
        </div>
        <div class="add-news">
          <div class="add-infos">
            <p class="c">
                <span class="c-title">标题</span>
                <span class="c-content">
                     <input type="text" class="long-title" id="titles" name="newsTitle" maxlength="40" value="${sysNews.newsTitle }">
                </span>
            </p>
              <p class="c">
                  <span class="c-title">副标题</span>
                  <span class="c-content">
                     <input type="text" class="long-title" id="secondTitle" name="secondTitle" maxlength="40" value="${sysNews.secondTitle }">
                </span>
              </p>
              <p class="c">
                  <span class="c-title">作者</span>
                  <span class="c-content">
                     <input type="text" class="long-title" id="author" name="author" maxlength="40" value="${sysNews.author }">
                </span>
              </p>
              <p class="c">
                  <span class="c-title">关键词</span>
                  <span class="c-content">
                      <input type="text" style="width: 83px;min-width: auto;" class="long-title" id="keyWord1" name="keyWord1" maxlength="40" value="${sysNews.keyWord1 }">
                      <input type="text" style="width: 83px;min-width: auto;" class="long-title" id="keyWord2" name="keyWord2" maxlength="40" value="${sysNews.keyWord2 }">
                      <input type="text" style="width: 83px;min-width: auto;" class="long-title" id="keyWord3" name="keyWord3" maxlength="40" value="${sysNews.keyWord3 }">
                      <input type="text" style="width: 83px;min-width: auto;" class="long-title" id="keyWord4" name="keyWord4" maxlength="40" value="${sysNews.keyWord4 }">
                      <input type="text" style="width: 83px;min-width: auto;" class="long-title" id="keyWord5" name="keyWord5" maxlength="40" value="${sysNews.keyWord5 }">
                </span>
              </p>
            <p class="c">
                <span class="c-title">分类</span>
                	<c:choose>
                		<c:when test="${type=='2'}">
		                	<select id="typeList">
		                		<c:forEach items="${newsType}" var="type">
		                			<c:choose>
		                				<c:when test="${sysNews.newsType eq type.id}">
		                					<option marks="${type.id}" selected>${type.name }</option>
		                				</c:when>
		                				<c:otherwise>
		                					<option marks="${type.id}" >${type.name }</option>
		                				</c:otherwise>
		                			</c:choose>
		                		</c:forEach>
		                	</select>
	                	</c:when>
	                	<c:otherwise>
	                		<select id="typeList">
		                		<c:forEach items="${newsType}" var="type">
		                				<option marks="${type.id}" >${type.name }</option>
		                		</c:forEach>
		                	</select>
	                	</c:otherwise>
	                </c:choose>	
              <%--   
                <span class="c-content" id="typeList">
                	<c:choose>
                		<c:when test="${type=='update' }">
                			<c:if test="${sysNews.newsType=='SYS_NEWS_TYPE_1' }">
		                		 <a href="javascript:;"  marks="SYS_NEWS_TYPE_1" class="btn btn-mini btn-default btn-primary">资讯</a>
		                         <a href="javascript:;" marks="SYS_NEWS_TYPE_2" class="btn btn-mini btn-default">公告</a>
			                </c:if>
		                	<c:if test="${sysNews.newsType!='SYS_NEWS_TYPE_1' }">
		                		 <a href="javascript:;"  marks="SYS_NEWS_TYPE_1" class="btn btn-mini btn-default btn-default">资讯</a>
		                         <a href="javascript:;" marks="SYS_NEWS_TYPE_2" class="btn btn-mini btn-primary">公告</a>
		                	</c:if>
                		</c:when>
                		<c:otherwise>
                			 <a href="javascript:;"  marks="SYS_NEWS_TYPE_1" class="btn btn-mini btn-default btn-primary">资讯</a>
                    	 	 <a href="javascript:;" marks="SYS_NEWS_TYPE_2" class="btn btn-mini btn-default">公告</a>
                		</c:otherwise>
                	</c:choose>
                </span> --%>
                
            </p>
            <p class="c">
            	<span class="c-title">是否推荐</span>
            	<span class="c-content">
            	 <c:choose>
                  		<c:when test="${type=='2' }">
                  			<c:if test="${sysNews.recommendFlag==1 }">
                  				<input type="radio" value="1" name="recommendFlag" checked="checked"/>是
                  				<input type="radio" value="0" name="recommendFlag"/>否
                  			</c:if>
                  			<c:if test="${sysNews.recommendFlag!=1 }">
                  				<input type="radio" value="1" name="recommendFlag"/>是
                  				<input type="radio" value="0" name="recommendFlag" checked="checked"/>否
                  			</c:if>
                  		</c:when>
                  		<c:otherwise>
                  			<input type="radio" value="1" name="recommendFlag"/>是
                  			<input type="radio" value="0" name="recommendFlag" checked="checked"/>否
                  		</c:otherwise>
                  </c:choose>
            	</span>
            </p>
            <p class="c">
                <span class="c-title">封面图片</span>
                    <span class="c-content">
                        <span class="view">
							  <c:choose>
									<c:when test="${empty sysNews.newsPic}">
										<img id="imgObject" ids="" width="280" height="160" src="" alt="" />
									</c:when>
									<c:otherwise>
										<img id="imgObject" ids="" width="280" height="160" src="${url }${sysNews.newsPic }" alt="" />
									</c:otherwise>
								</c:choose>
				         </span>
                    <span class="btns">
                      <input type="file" accept="image/*" id="imgData" name="imgData" onchange="Form.uploadPic()" class="c-up">
                      <a href="javascript:;" class="btn btn-default" id="c-up">点击上传</a>
                      <span style="padding:0 10px;color:#999;font-size:14px;">建议大小：620*390 ，在比例不变的情况下，最大宽度不超过900px</span>
                    </span>
                    </span>
            </p>
            <p class="c">
                <span class="c-title">阅读基数</span>
                <span class="c-content">
                   <input type="number" class="long-title" id="readBaseIndex" name="readBaseIndex" value="${sysNews.readBaseIndex }"/>
                </span>
            </p>
           </div>
            <div class="c">
                <div class="c-f-title">编辑内容</div>
                 <div class="about-edit">
	                <textarea class="ckeditor form-control" id="newsContents" name="newsContent" rows="6" data-error-container="#editor2_error"></textarea>
	             </div>
            <p class="c text-center" style="margin-top: 10px;">
                <a href="javascript:saveNews('save');" class="btn btn-sm btn-primary" id="baocun">保存</a>                
                <a href="javascript:saveNews('saveAndOnsale');" class="btn btn-sm btn-primary" id="baocunfabu">保存并发布</a>
                <a href="javascript:history.go(-1);" class="btn btn-sm btn-default">取消</a>
            </p>
        </div>
    </div>
 </div>
</div>
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
                        <input type="text" id="videourl" class="input-left-radius url" placeholder="支持优酷、土豆，腾讯视频，将视频页面网址复制到这里点击增加即可"/><a href="javascript:;" class="btn btn-default2 input-right-radius btn-add-url">增加</a>
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
<div class="add-layer showAudio none" style="z-index:10120;width:800px;height:500px;margin-top:-280px;padding-top:30px;">
    <i class="iconfont close">&#xe610;</i>
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
            <span><a href="/video/toVideo?searchType=audio" target="_blank" style="color:blue;text-decoration:underline;">现在去上传音频</a></span>
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

</div>

    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/plugins/video/plugin.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/plugins/audio/plugin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/addClassTypeVideo.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/classTypeAudio.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system/editNewsManage.js"></script>
</body>
</html>