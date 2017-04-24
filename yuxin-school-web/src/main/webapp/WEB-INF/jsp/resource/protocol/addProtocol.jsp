<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/decorators/import.jsp" %>

<!DOCTYPE html >
<html lang="zh-cn">
<head>
    <title>新增协议</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/newresource.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/agreement-4.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/agreement-3.css"/>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
     <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    
     <script type="text/javascript">
    	$('#resource_protocol').addClass('active');
    	var cicDescription = '${protocol.content}';
    	if(cicDescription == null || cicDescription == undefined){
    		cicDescription = '';
    	}
    	
    </script>
    <style>
    	.t-btns{
    		    margin-top: 30px;
    	}
    	.mainbackground .cke_1{
    		    position: relative;
			    top: 0px;
			    left: 64px;
			    
    	}
    	.mainbackground .cke_1 .cke_inner{
    		margin-left: 0px;
			margin-bottom: 0px;
    	}
    	.u-wrap .mainbackground.nopadding{
    		height:auto;
    	}
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<div class="u-wrap resource">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5"><c:if test="${!empty protocol }">修改协议</c:if><c:if test="${empty protocol }">新增协议</c:if></h2>
            <div class="search searchAdmin">
                 <a href="javascript:;" class="btn btn-mini btn-primary" id="pback"><em class="iconfont"></em>返回</a>
             </div>
            <span class="line"></span>
        </div>
        
        <div class="content">
        <input type="hidden" class="hiddenProtocolId" value="${protocol.id }">
                <div class="inp-1">
                    <span>名称</span>&nbsp;
                    <input type="text" maxlength="50" id="name" name="name" value="${protocol.name }">
                </div>
                <div class="inp-2">
                    <span>协议标题</span>&nbsp;
                    <input type="text" maxlength="50" id="title" name="title" value="${protocol.title }">
                </div>
                <div class="inp-3">
                    <span>协议类型</span>&nbsp;
                    <c:if test="${empty protocol }">
	                    	<select name="type" id="schoolId">
				       					<c:if test='${courseOpen eq  "success"}'><option value="0" selected="selected">课程协议</option></c:if>
				       					<c:if test='${packageOpen eq  "success"}'><option value="1">课程包协议</option></c:if>
				       		</select>
				       		</c:if>
				       		<c:if test="${!empty protocol && protocol.type eq 0}">
	                    	<select name="type" id="schoolId">
	                    	<c:if test='${courseOpen eq  "success"}'><option value="0" selected="selected">课程协议</option></c:if>
				       		<c:if test='${packageOpen eq  "success"}'><option value="1">课程包协议</option></c:if>
				       		</select>
				       		</c:if>
				       		<c:if test="${!empty protocol && protocol.type eq 1}">
	                    	<select name="type" id="schoolId">
				       					<c:if test='${courseOpen eq  "success"}'><option value="0" >课程协议</option></c:if>
				       					<c:if test='${packageOpen eq  "success"}'><option value="1" selected="selected">课程包协议</option></c:if>
				       		</select>
				       		</c:if>
                </div>
                <div class="inp-4">
                    <span>发送方式</span>&nbsp;
                    <c:if test="${empty protocol }">
	                    	<select name="useTime" id="schoolId">
				       					<option value="0" selected="selected">购买前</option>
				       					<option value="1">学习前</option>
				       		</select>
				       		</c:if>
				       		<c:if test="${!empty protocol && protocol.useTime eq 0}">
	                    	<select name="useTime" id="schoolId">
				       					<option value="0" selected="selected">购买前</option>
				       					<option value="1">学习前</option>
				       		</select>
				       		</c:if>
				       		<c:if test="${!empty protocol && protocol.useTime eq 1}">
	                    	<select name="useTime" id="schoolId">
				       					<option value="0" >购买前</option>
				       					<option value="1" selected="selected">学习前</option>
				       		</select>
				       		</c:if>
                </div>
                <div class="inp-5">协议详情</div>
                <div class="inp-6 clear">
                    <div class="left">变量说明：</div>
                    <div class="right">
                        <div class="right-1">
                            <div class="right-l"> [username]学员名称</div>
                            <div class="right-r">   [coursename]课程名称/课程包名称</div>
                        </div>
                        <div class="right-2">
                            <div class="right-l">  [StudentIDcardnumber]学员证件号码</div>
                            <div class="right-r">
                                 [Studentcellphonenumber]学员电话号码
                            </div>
                        </div>
                        <div class="right-3">
                            <div class="right-l">
                                  [coursecost]课程原价
                            </div>
                            <div class="right-r">
                                 [PreferentiaIpriceofcourse]课程优惠价
                            </div>
                        </div>
                        <div class="right-4">
                            <div class="right-l">
                                 [current date]签署日期        
                            </div>
                            <div class="right-r">
                                 [Course subject]课程所属学科/课程包所属分类
                            </div>
                        </div>
                    </div>
                 
                </div>
					<textarea name="editor01" id="q-content" maxlength="1000">${protocol.content }</textarea>
            </div>
            <div class="t-btns">
                <p class="text-center">
                    <a href="javascript:history.go(-1);" class="btn btn-default">取消</a>
                    <a href="javascript:;" class="btn btn-primary saveProtocol">保存</a>
                </p>
            </div>
        
    </div>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/resource/protocol/alone2.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/resource/protocol/addOrEditProtocol.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>