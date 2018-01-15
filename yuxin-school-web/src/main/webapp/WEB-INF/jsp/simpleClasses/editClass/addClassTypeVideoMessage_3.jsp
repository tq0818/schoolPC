<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-增加视频</title>
   <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" href="<%=rootPath  %>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" type="text/css"/>
    <link href="<%=rootPath  %>/plugins/select2/select2_metro.css" rel="stylesheet" type="text/css"  />
     <style type="text/css">
    	.up-input{position:relative;width:160px;height:32px;z-index:1}
		.upfile{position:relative;display:inline-block}
		.upfile .btn-up{position:absolute;top:0;left:0;}
    </style>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/plupload.full.min.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/moxie.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.2.1/jquery.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
    
    <script type="text/javascript">
    	$(document).ready(function(){
    		$chooseMenu("videoCode");
    	});

    	function guid() {
    	    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    	        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
    	        return v.toString(16);
    	    });
    	}
		function docChange(){
			$("#dochint").html("<span style='color:red;' >正在上传</span>");
			$.ajaxFileUpload({
				url:rootPath + "/classTypeResource/docupload;"+ window["sessionName"] + "=" + window["sessionId"],
				type:"post",
				secureuri:false,
				fileElementId:"doctype",
				dataType: "json",
				success:function(data){
					if(data.msg == "formatNotRight"){
						$("#dochint").html("<span style='color:red;'>上传文件格式不正确</span>");
					}else if(data.msg == "sizeOutOf"){
						$("#dochint").html("<span style='color:red;'>文件不能大于20MB</span>");
					}else if(data.msg == "success"){
						$("#doctype").attr("url",data.fileId);
						$("#dochint").html("<span style='color:green;'>上传成功</span>");
					}else if(data.msg == "nameTooLang"){
						$("#dochint").html("<span style='color:red;'>文件名太长</span>");
					}else{
						$("#dochint").html("<span style='color:green;'>" + data.msg + "</span>");
					}
				}
			});
		}
    	function save(){
    		$.msg("保存信息成功");
    	}
    	
    </script>
</head>
<body>
<div class="q-box">
<form method="post" id="myForms">
	<input type="hidden" name="id" value="${ct.id }"/>
	<input type="hidden" name="lable" value="${lable }"/>
	<input type="hidden" name="type1" value="${type }"/>
</form>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/classType/commonTitle.jsp" %>
<div class="u-wrap company overflow">
	 <%@include file="/WEB-INF/jsp/simpleClasses/commonClass.jsp" %>
    <div class="right-side">
       <div class="u-wrap classes">
    <div class="mainbackground nopadding">
   
        <div class="teacher-content">
            <div class="teacher-btns">
            	<input type="hidden" id="moduleId" value="${moduleId}" />
            	<input type="hidden" id="itemOneId" value="${ct.itemOneId}" />
            	<input type="hidden" id="itemSecondId" value="${ct.itemSecondId}" />
            	<input type="hidden" id="classTypeId" value="${ct.id}" />
            	<input type="hidden" id="classTypeName" value="${ct.name }"/>
                <a href="javascript:;" style="margin-top: 10px;" class="btn btn-mini btn-default btn-add-class"><i class="iconfont">&#xe61c;</i>新增章节</a>
                <!-- <a href="javascript:;" class="btn btn-mini btn-default">隐藏默认章节序号</a> -->
            </div>
            <div class="t-c clear" >
                <div class="t-c-l" style="height:600px;">
                    <ul class="sortable base-sort" >
                        
                    </ul>
                </div>
                <div class="t-c-r right" >
                </div>
            </div>
        </div>   
    </div>
   <div class="q-btns" style="text-align:center;">
                    <a href="javascript:;" class="q-cancel q-cancel2">取消</a>
                    <a href="javascript:save();" class="q-save next">保存</a>
     </div>
</div>
    </div>
</div>


<div class="add-layer add-class-layer none">
    <i class="iconfont close">&#xe610;</i>
    <div class="layer-content">
        <p class="c">
            <span class="c-title">节的名称</span>
            <span class="c-content">
                <input type="text" placeholder="输入名称">
            </span>
        </p>
        <p class="c">
            <span class="c-title">顺序</span>
            <span class="c-content"><input type="text"></span>
        </p>
        <p class="c">
            <span class="c-title">公开视频</span>
            <span class="c-content">
                <input type="checkbox" name="freeflag" class="feeFlag"> 免费视频
                <input type="checkbox" name="freeflag" class="feeFlag"> 试听
            </span>
        </p>
        <p class="c">
            <span class="c-title">视频文件</span>
            <span class="c-content">
                <input type="text">
                <input type="button" class="btn btn-sm btn-primary" value="从库中选择">
            </span>
        </p>
    </div>
</div>
<div class="add-layer w800 none" style="z-index:1001;margin-top:-260px">
    <h3 class="add-layer-tit tabs">
				<span class="tab1 active" ids="neibu">内部视频</span> 
				<span class="tab2" ids="outside">外部视频</span>
			</h3>
			<i class="iconfont close q-close"></i>
    <div class="tabs-cont">
				<div class="layer-content tab1">
					<div class="term">
						<span class="term-title q-term-title">学科</span> <span
							class="term-title"> <select name="" id="choose_itemOne"
							class="itemOne">
						</select>
						</span> <span class="term-title q-term-title">学科小类</span> <span
							class="term-title"> <select name="" id="choose_itemSecond"
							class="itemSecond">
						</select>
						</span> <span class="term-title q-term-title"></span> <span
							class="term-title"> <input type="text" id="choose_paper1"
							class="q-select">
						</span> <span class="btn btn-sm btn-default q-btn-primary q-btn-primary1"
						>搜索 </span>
					</div>
					<div class="term-list q-term-list">
							<table class="table table-center table-head" style="margin: 0;">
								
								<tbody>
									<tr class="head">
										<th width="40%">文件名</th>
										<th width="13%">大小</th>
										<th width="20%">标签</th>
										<th width="15%">上传人</th>
										<th width="12%">上传时间</th>
									</tr>
								</tbody>
							</table>
							<div class="table-list">
								<table id="data_table" class="table table-center table-tbody">
									<tbody>
									</tbody>
								</table>
							</div>
						<div class="pages pagination"></div>
					</div>
				</div>
				<div class="layer-content tab2 none">
				<div class="default-div">
					<div class="term">
						<span class="term-title q-term-title">学科</span> <span
							class="term-title"> <select name="" id="choose_itemOne"
							class="itemOne">
						</select>
						</span> <span class="term-title q-term-title">学科小类</span> <span
							class="term-title"> <select name="" id="choose_itemSecond"
							class="itemSecond">
						</select>
						</span> <span class="term-title q-term-title"></span> <span
							class="term-title"> <input type="text" id="choose_paper1"
							class="q-select">
						</span> <span class="btn btn-sm btn-default q-btn-primary q-btn-primary1"
							> 搜索 </span> 
							<a class="add-video-link" href="javascript:;">新增视频</a>
					</div>
					<div class="term-list q-term-list">
						<table class="table table-center table-head"  style="margin: 0;">
							<tbody>
							<colgroup>
                                <col width="20%">
                                    <col width="20%">
                                        <col width="20%">
                                            <col width="20%">
                                                <col width="20%">
                            </colgroup>
								<tr class="head">
									<th >文件名</th>
									<th >大小</th>
									<th >标签</th>
									<th >上传人</th>
									<th >上传时间</th>
								</tr>
							</tbody>
						</table>
						<div class="table-list">
							<table id="data_table" class="table table-center table-tbody">
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="pages pagination"></div>
					</div>
				</div>
				<div class="add-div none">
					<div class="row">
						<div class="col">
							<div class="tt q-term-title">学科</div>
							<div class="ct">
								<select class="itemOne q-select">
								
								</select>
							</div>
						</div>
						<div class="col">
							<div class="tt q-term-title">学科小类</div>
							<div class="ct">
								<select class="itemSecond q-select">

								</select>
							</div>
						</div>

					</div>
        	<div class="row">
        	    <div class="col">
        			<div class="tt q-term-title">标签</div>
        			<div class="ct"><input type="hidden" class="tag"/></div>
        		</div>
        		<div class="col">
        			<div class="tt q-term-title">视频时长</div>
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
        			<div class="tt q-term-title">网址</div>
        			<div class="ct" style="position:relative;">
        				<input type="text" id="videourl" class="input-left-radius url q-select" placeholder="支持优酷、土豆、腾讯，将视频页面网址复制到这里点击增加即可" style="width:498px;"/><a href="javascript:;" class="btn btn-default2 input-right-radius btn-add-url q-btn-primary">增加</a>
        			</div>
        			<span class="tips">tip：视频会增加到您的视频库，不会丢失哦~    所以为了便于您选择，建议您给视频增加标签</span>
        		</div>
        	</div>
        	<div class="row">	
        		<div class="col">
        			<div class="tt q-term-title">视频名称</div>
        			<div class="ct">
        				<input type="text" class="input-left-radius input-left-all video-name q-select"style="width:498px;"/>
        			</div>
        		</div>
        	</div>
        	<div class="btns">
        		 <a href="javascript:;" class="btn btn-mini btn-primary btn-s q-btn-primary">保存</a>
                    <a href="javascript:;" class="btn btn-mini btn-default btn-c q-btn-primary q-selct">取消</a>
        	</div>
        </div>
    </div>
	
    </div>
    </div>
<div class="add-layer-bg none" style="z-index:1000;"></div>
<div class="add-layer w900 none" style="z-index:1001;">
    <h3 class="add-layer-tit ">选择试卷</h3>
        	<i class="iconfont close q-close"></i>
			<div class="layer-content q-layer-content">
				<div class="term">
					<span class="term-title q-term-title">题库</span> <span class="term-title">
						<select name="" id="choose_tiku" style="" class="q-select">
					</select>
					</span> <span class="term-title q-term-title">科目</span> <span class="term-title">
						<select name="" id="choose_item" class="q-select">
					</select>
					</span> <span class="term-title q-term-title">试卷名称</span> <span class="term-title">
						<input type="text" id="choose_paper3" class="q-select"/>
					</span> 
					<span class="btn btn-sm btn-default q-btn-primary q-btn-primary2" id="search_paper"> 搜索
					</span>
				</div>

				<div class="term-list q-term-list">
					<table class="table table-center table-head"  style="margin: 0;">
						<tbody>
						<colgroup>
                            <col width="40%">
                                <col width="20%">
                                    <col width="20%">
                                        <col width="20%">
                        </colgroup>
							<tr >
								<th >试卷名称</th>
								<th >所属题库</th>
								<th >所属科目</th>
								<th >试卷类型</th>
							</tr>
						</tbody>
					</table>
					<div class="table-list">
						<table id="data_table_2" class="table table-center table-tbody">
							<tbody>
							</tbody>
						</table>

					</div>
				</div>
				<div class="pages pagination"></div>
			</div>
</div>
<div class="add-layer w1000 none" style="z-index:1001;">
    <h3 class="add-layer-title">选择章节</h3>
			<i class="iconfont close">&#xe610;</i>
			<div class="layer-content clear" >
				<div class="term-herv term-herv-left">
					<span class="term-title">题库</span> <span class="term-content">
						<select name="" id="choose_tiku_2"
						style="width: 150px; height: 28px;"></select>
					</span>
				</div>
				<div class="term-herv">
					<span class="term-title">科目</span> <span class="term-content">
						<select name="" id="choose_subject_2"
						style="width: 150px; height: 28px;"></select>
					</span>
				</div>
				<div class="term-herv term-herv-left">
					<span class="term-title">章</span> <span class="term-content">
						<select name="" id="choose_chapter_2"
						style="width: 150px; height: 28px;"></select>
					</span>
				</div>
				<div class="term-herv">
					<span class="term-title">节</span> <span class="term-content">
						<select name="" id="choose_lecture_2"
						style="width: 150px; height: 28px;"></select>
					</span>
				</div>
				<div class="term-herv term-herv-left">
					<span class="term-title">做题数量</span> <span class="term-content">
						<input type="text" id="choose_num_2"
						style="width: 150px; height: 28px; padding: 0" />
					</span>
				</div>
				<div class="term-sure" style="text-align: center;">
					<a href="javascript:;" class="btn btn-default q-btn-primary">确定</a>
				</div>
			</div>
</div>
<div class="add-layer w1400 none" style="z-index:1001;">
    <h3 class="add-layer-title">选择章节</h3>
			<i class="iconfont close">&#xe610;</i>
			<div class="layer-content clear" >
				<div class="term-herv term-herv-left">
					<span class="term-title">题库</span> <span class="term-content">
						<select name="" id="choose_tiku_3"
						style="width: 150px; height: 28px;"></select>
					</span>
				</div>
				<div class="term-herv">
					<span class="term-title">科目</span> <span class="term-content">
						<select name="" id="choose_subject_3"
						style="width: 150px; height: 28px;"></select>
					</span>
				</div>
				<div class="term-herv term-herv-left">
					<span class="term-title">章</span> <span class="term-content">
						<select name="" id="choose_chapter_3"
						style="width: 150px; height: 28px;"></select>
					</span>
				</div>
				<div class="term-herv">
					<span class="term-title">节</span> <span class="term-content">
						<select name="" id="choose_lecture_3"
						style="width: 150px; height: 28px;"></select>
					</span>
				</div>
				<div class="term-sure" style="text-align: center;">
					<a href="javascript:;" class="btn btn-default q-btn-primary">确定</a>
				</div>
			</div>
</div>
<div class="add-layer w1100 none" style="z-index: 1001;">
        <h3 class="add-layer-tit ">选择Flash文件</h3>
        <i class="iconfont close q-close"></i>
        <div class="layer-content q-layer-content">
            <div class="term">
                <span class="term-title q-term-title">学科</span>
                <span class="term-title">
                <select name="" id="choose_tiku1" style="" class="q-select">
                </select>
            </span>
                <span class="term-title q-term-title">学科小类</span>
                <span class="term-title">
                <select name="" id="choose_item1" class="q-select">
                </select>
            </span>
                <span class="term-title q-term-title"></span>
                <span class="term-title">
                <input type="text" id="choose_paper2" class="q-select">
            </span>
                <span class="btn btn-sm btn-default q-btn-primary q-btn-primary2" id="search_paper">
                搜索
            </span>
            </div>

            <div class="term-list q-term-list">
                    <table  class="table table-center table-head" style="top:0px;margin: 0;" >
                        <colgroup>
                            <col width="20%">
                                <col width="20%">
                                    <col width="20%">
                                        <col width="20%">
                                        <col width="20%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>文件名</th>
                                <th>大小</th>
                                <th>标签</th>
                                <th>上传人</th>
                                <th>上传时间</th>
                            </tr>
                        </tbody>
                    </table>
                <div class="table-list">
						<table id="data_table_3" class="table table-center table-tbody">
							<tbody>
							</tbody>
						</table>
                </div>

            <div class="pages pagination">
            </div>
            </div>
        </div>
    </div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!-- 弹层信息 -->
<div class="layer-tips-content add-layer w1200 class-resource none" id="class-resource">
         <h3 class="add-layer-title">上传资料</h3>
        <i class="iconfont close"></i>
        <div class="layer-content clear">
            <div class="term-herv term-herv-left">
                <span class="term-title">资料类型：</span>
                <span class="term-content">
               <select id="classresource" style="width:280px">
    			<option value="COURSE_RESOURCE_TEACHING">教材</option>
   				<option value="COURSE_RESOURCE_SUPPLY">教辅</option>
   				<option value="COURSE_RESOURCE_OTHER">其他</option>
    		</select>
            </span>
            </div>
            <div class="term-herv">
                <span class="term-title" style="width:70px;">课程资料：</span>
               <span class="upfile"> 
					<input type="file" id="doctype" style="border:none;" name="doc" class="up-input" accept=".doc,.pdf,.xls,.ppt,.docx,.pptx,.xlsx,.zip,.rar" onchange="javascript:docChange();">
					<a href="javascript:;" class="btn btn-default btn-up" id="docres" style="margin-left:-2px;border-radius:2px;">上传资料</a>
				</span>
				<span id="dochint"></span>
            </div>
            <p>
	    	<span style="color:red;font-size:12px;">支持上传类型:doc,pdf,xls,ppt,docx,pptx,xlsx,zip,rar;文件不能大于20M</span>
		</p>
        </div>

	    
		 <div class="term-sure" style="text-align:center;margin-top:20px;">
                <a href="javascript:;" class="q-save q-btn-primary btn-default btn-ok">保存</a>
               <a href="javascript:;" class="q-cancel q-btn-primary btn-default btn-cancel">取消</a>
                  
            </div>
	</div>
</div>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/select2/select2.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/select2/select2_locale_zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/classes.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/simpleclasses/teach/videoAddBase.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
</body>
</body>
</html>
