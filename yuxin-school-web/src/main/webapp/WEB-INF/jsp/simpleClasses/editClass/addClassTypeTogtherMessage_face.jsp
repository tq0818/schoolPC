<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-安排面授</title> 
   <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
	<style type="text/css">
    	.up-input{position:relative;width:160px;height:32px;opacity:0;filter:alpha(opacity=0);z-index:1}
		.upfile{position:relative;display:inline-block}
		.upfile .btn-up{position:absolute;top:0;left:0;}
    </style>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/plupload.full.min.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/moxie.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.2.1/jquery.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<input type="hidden" id="isFusheng" value="${isFuSheng}">
<input type="hidden" id="teacherId" value="${teacherId}">
<input type="hidden" id="isJigou" value="${isJigou}">
<input type="hidden" id="isFenxiao" value="${isFenxiao}">
<form id="myForm" method="post">
    <input type="hidden" name="id" value="${ct.id }"/>
	<input type="hidden" name="lable" value="togther"/>
	<input type="hidden" name="type" id="type" value="${type}"/>
	<input type="hidden" id="servriceType" value="gh"/>
</form>
<%@include file="/WEB-INF/jsp/classType/commonTitle.jsp" %>
<div class="u-wrap company overflow">
	 <%@include file="/WEB-INF/jsp/simpleClasses/commonClass.jsp" %>
    <div class="right-side">
       <div class="u-wrap classes">
    <div class="mainbackground nopadding">
       
        <div class="c-main">
        	<div>
        		<span id="course_typeLists">
        			<a href="javascript:;" style="display: none;" mark="TEACH_METHOD_FACE" class="btn btn-default btn-primary">面授</a>
        		</span>
        		<a href="javascript:;" class="btn btn-primary btn-addlink addCourse" style="float: right;"><em class="iconfont">&#xe606;</em>新增课程单元</a>
        	</div>
            <div class="c-content">
            	 <div class="teacher-btns" style="float:right;">
            	   <input type="hidden" id="className" value="${ct.name }"/>
            	 	<input type="hidden" id="classtypeId" value="${ct.id }"/>
            	 	<input type="hidden" id="itemOneId" value="${ct.itemOneId }"/>
            	 	<input type="hidden" id="itemSecondId" value="${ct.itemSecondId }"/>
            	 	<input type="hidden" id="moduleId" value=""/>
            	 	<input type="hidden" id="moduleNoId" value=""/>
	            </div>
				<div>
					 <ul class="sortable base-sort item-panel courseliList" >

                 	</ul>
				</div>
                <p class="c text-center" style="margin-top:20px;">
                    <a href="javascript:void(0);" class="btn btn-primary next" style="margin-right:20px">保存</a>
                    <a href="<%=rootPath %>/simpleClasses/showClassTypePage" class="btn btn-default">取消</a>
                </p>
            </div>
        </div>
    </div>
</div>
    </div>
</div>




<!-- 新增课次添加模块弹窗 -->
	<div class="popupwin onepopuwin add-fix" data-pupwin="modal" style="top:20%;left:18%;width:55%;height:auto;">
		<div class="popupwin-title">
			<h2 class="h5">新增课次</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal">
			<div class="form-body">
				<div class="form-group">
				<input type="hidden" id="chaptermark" value="1"/>
				<input type="hidden" id="omoduelId" value=""/>
				<input type="hidden" id="eidtype" value="add"/>
				<input type="hidden" id="lessonId" value=""/>
				<input type="hidden" id="moduleNoIdkc" value=""/>
				<div class="form-group">
					<label class="col-md-2 control-label">课次名称</label>
					<div class="col-md-5">
						<input class="form-control" maxlength="30" id="lessonName" value="" />
					</div>
				</div>
					<label class="col-md-2 control-label">上课时间</label>
					<label class="col-md-1 control-label">日期</label>
						<div class="col-md-2">
							<input class="form-control datetime-picker date-picker" id="startDay" value="" readonly="readonly"/>
						</div>
						<div class="col-md-1">
							<input class="form-control input-group date date-picker1" readonly="readonly" data-date-format="hh:ii" dataType="startTime" id="startD" value="" data-link-format="hh:ii"/>
						</div>
						<label class="control-label" style="float:left;">—</label>
						<div class="col-md-1">
							<input class="form-control input-group date date-picker1" readonly="readonly" data-date-format="hh:ii" dataType="endTime" id="endD" value=""  data-link-format="hh:ii"/> 
						</div>
				</div>
				<div class="form-group none" id="faceclassRoom">
					<label class="col-md-2 control-label">教室</label>
					<div class="col-md-4" id="classcontent">
						<select id="classList" style="width:150px;">
						</select>
						<div class="addRoomArea none">
							<select id="campusList" class="form-control"></select><br/>
							<input type="text" placeholder="请输入教室名称" id="classRoomName" class="form-control"><br/>
				    		<input type="text" placeholder="请输入教室地址" id="classRoomAddress" class="form-control" style="width:400px"><br/>
				    		<a href="javascript:;" class="btn btn-mini btn-primary insertRoom">保存</a>
				    	</div>
				    	<span class="nullnum1 none" style="color:red;">没有数据，<a href="javascript:void(0);" class="addC">去设置</a></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">教师</label>
					<div class="col-md-8" id="tcontent">
						<select id="teacherList" style="width:150px;">
		
						</select>
						<div class="insertArea none">
		            		<input type="text" id="userName" placeholder="登陆账号" class="form-control"/>
		            		<input type="password" id="pwd" placeholder="密码" class="form-control"/>
		            		<input type="text" id="teacherName" placeholder="老师姓名" class="form-control"/>
		            		<input type="text" id="teacherMobile" placeholder="手机号" class="form-control"/>
		            		<a href="javascript:;" class="btn btn-mini btn-primary insertTeacher">保存</a>
		            	</div>
		            	<span class="nullnum2 none" style="color:red;">没有数据，<a href="javascript:void(0);" class="addTL">去设置</a></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-2 control-label">教务</label>
					<div class="col-md-8" id="tcontent1">
						<select id="tassitList" style="width:150px;"></select>
						<div class="insertTArea1 none">
							<div>
		            		<input type="text" id="userNameA" placeholder="登陆账号" class="form-control" style="width:150px;float:left;"/>
		            		<input type="password" id="pwdA" placeholder="密码" class="form-control" style="width:150px;"/>
		            		</div>
		            		<div style="float:left;">
		            		<input type="text" id="teacherTName" placeholder="教务人员姓名" class="form-control" style="width:150px;float:left;"/>
		            		<input type="text" id="teacherTMobile" placeholder="手机号" class="form-control" style="width:150px;"/>
		            		<a href="javascript:;" class="btn btn-mini btn-primary insertTTeacher">保存</a>
		            		</div>
		            	</div>
		            	<span class="nullnum3 none" style="color:red;">没有数据，<a href="javascript:void(0);" class="addT">去设置</a></span>
					</div>
				</div>
				<div class="form-group" id="mobilesupports">
					<label class="col-md-2 control-label">是否支持移动端</label>
					<div class="col-md-8">
						<input type="radio" value="1" name="supportMobile"/>是&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="0" checked="checked" name="supportMobile"/>否&nbsp;&nbsp;&nbsp;<span style="color:red;">排课后不可修改, 需要使用直播助手和桌面共享的方式进行上课。</span>
					</div>
				</div>
				<div class="form-group" id="liveClassTypeFlags">
					<label class="col-md-2 control-label">直播类型</label>
					<div class="col-md-8">
						<input type="radio" value="LIVE_BIG_CLASS_ROOM" checked="checked" name="liveClassType"/>大讲堂&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="LIVE_SMALL_CLASS_ROOM" name="liveClassType"/>小班课&nbsp;&nbsp;&nbsp;<span style="color:red;"></span>
					</div>
					<div class="col-md-8">
						<span style="color:#999;"><i class="iconfont" style="color:red;">&#xe605;</i><b>大讲堂:</b>适用于以宣讲为主的授课方式。如果您的课堂不太会出现频繁的老师与学员之间的互动。建议选用这种方式。大讲堂对学员人数无限制，您甚至可以开一场万人级别的课堂。<br/>
                        <b>小班课:</b>适用于包括讲授为主，并辅以文字、语音互动沟通的授课方式。如果您的课堂需要适当的互动过程。建议选用这种方式。小班课并没有限制进入人数，建议不要超过100人。</span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4 col-md-offset-4">
						<a class="btn btn-default btn-primary addclassLesson"  href="javascript:;">保&nbsp;&nbsp;存</a>
					&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn btn-default" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- 上传课程资料弹层信息 -->
<div class="layer-tips class-resource" id="class-resource" style="height: auto;width:500px;">
	<div class="layer-tips-content">
		<br>
	    <p>
	    	资料类型：
    		<select id="classresource" style="width:280px">
    			<option value="COURSE_RESOURCE_TEACHING">教材</option>
   				<option value="COURSE_RESOURCE_SUPPLY">教辅</option>
   				<option value="COURSE_RESOURCE_OTHER">其他</option>
    		</select>
	    </p>
		<br>
	    <p>
	    	课程资料：
			<span class="upfile"> 
				<input type="file" id="doctype" style="border:none;" name="doc" class="up-input" accept=".doc,.pdf,.xls,.ppt,.docx,.pptx,.xlsx,.zip,.rar" onchange="javascript:docChange();">
				<a href="javascript:;" style="display: block;" class="btn btn-default btn-up" id="docres">上传资料</a>
			</span>
			<span id="dochint"></span>
		</p>
		<br>
	    <p>
	    	<span style="color:red">支持上传类型:doc,pdf,xls,ppt,docx,pptx,xlsx,zip,rar;文件不能大于20M</span>
		</p>
	</div>
	<div class="layer-tips-btns">
	    <p>
	    	<a href="javascript:;" class="btn btn-mini btn-primary btn-ok">保存</a>
	    	<a href="javascript:;" class="btn btn-mini btn-default btn-cancel">取消</a>
	    </p>
	</div>
	 <p>
	 	&nbsp;&nbsp;资料列表：
	    <span id="resourceLists">
	    
	    </span>
	</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>

<script type="text/javascript">
$(document).ready(function(){
	$chooseMenu("faceCode");
});
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
function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}
</script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/editClass/classTypelive.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
<%-- 	    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script> --%>
	 <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap.min.js"></script>
	 <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	 <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script> 
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/DateUtils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/selectTeacher.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>

</body>
</html>