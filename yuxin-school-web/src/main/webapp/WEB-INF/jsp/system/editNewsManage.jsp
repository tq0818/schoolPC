<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>${type=='save'?'新增公告':'编辑公告' }</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-fileupload/bootstrap-fileupload.css" />
    
    <script type="text/javascript">
	$(function(){
		$selectSubMenu('netschool_news');
		var editor=CKEDITOR.replace('newsContents');
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
				[ 'About' ]
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
		var schoolId=$("#scId").val();
		var newsType="";
		newsType=$("#typeList option:selected").attr("marks");
		var recommendFlag=$("input[type=radio]:checked").val();
		var summary=$("#summarys").val();
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
			alert("公告标题不能为空！");
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
					"summary" : summary,
					"newsStatus" : newsStatus,
					"newsContent" : newsContent,
					"newsPic" : pic,
					"schoolId" : schoolId,
					"recommendFlag":recommendFlag
					},
					success : function(data) {
						$.msg("修改公告成功!");
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
					"summary" : summary,
					"newsStatus" : newsStatus,
					"newsContent" : newsContent,
					"newsPic" : $("#imgObject").attr("ids"),
					"schoolId" : schoolId,
					"recommendFlag":recommendFlag
					},
				success : function(data) {
					$.msg("添加公告成功!");
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
                <span class="c-title">概述</span>
                <span class="c-content">
                   <textarea id="summarys" name="summary" rows="3" maxlength="200">${sysNews.summary }</textarea>
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
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
        <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
        <script type="text/javascript" src="<%=rootPath %>/javascripts/system/editNewsManage.js"></script>
</body>
</html>