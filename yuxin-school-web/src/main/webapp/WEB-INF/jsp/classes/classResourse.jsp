<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>课程资料</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/select2.min.css" />
	<script type="text/javascript" src="<%=rootPath%>/javascripts/select2.full.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/operate.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/class/res/classres.js"></script>
	<style type="text/css">
		.btn-upload{float: right;margin-top:-15px}
		.h5{width:100px}
		.up-input{position:relative;width:166px;height:32px;opacity:0;filter:alpha(opacity=0);z-index:1}
		.upfile{position:relative;display:inline-block}
		.upfile .btn-up{position:absolute;top:0;left:0;}
	</style>
</head>
<body>
<input type="hidden" value="${classtypeId }" id="cId"/>
<input type="hidden" value="${oneId }" id="oneId"/>
<input type="hidden" value="${twoId }" id="twoId"/>
	<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
	<div class="u-wrap operate">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">课程资料</h2>
            <a href="javascript:;" class="btn btn-mini btn-primary btn-upload">上传资料</a>
            <span class="line"></span>
        </div>
        <div class="select-option clear">
            <ul>
                <li>
                    <span class="s-title">学科</span>
                    <span class="s-list" id="one">
                        <c:forEach var="o" items="${oneItem }">
                        		<a class="btn btn-sm btn-default oneItem" ids="${o.id }" href="javascript:;" data-id="${o.id }">${o.itemName }</a>
                        </c:forEach>
                    </span>
                </li>
                <li id="two">
                    <span class="s-title">学科小类</span>
                    <span class="s-list">
                        
                    </span>
                </li>
                <li id="term">
                    <span class="s-title">课程</span>
                    <span class="s-list">
                    	<select id="class" class="js-example-basic-single" style="width: 180px;"></select>
                    </span>
                </li>
                <li>
                    <span class="s-title">资料类型</span>
                    <span class="s-list">
                    	<a class="btn btn-sm btn-success resource" href="javascript:;" data-type="">全部</a>
                    	<c:forEach var="r" items="${rtList }">
                    		<a class="btn btn-sm btn-default resource" href="javascript:;" data-type="${r.resourceType }">${r.resourceName }</a>
                    	</c:forEach>
                    </span>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="u-wrap operate">
    <div class="mainbackground">
        <div class="operate_list">
            <div class="op-list">
            </div>
            <div class="pages">
                <ul class="pagination">
					
				</ul>
            </div>
        </div>
    </div>
</div>
<!-- 弹层信息 -->
<div class="layer-tips class-resource" id="class-resource">
	<a href="javascript:;" class="btn btn-sm btn-success btn-putong btn-res-table">普通资料</a>
	<a href="javascript:;" class="btn btn-sm btn-default btn-online btn-res-table">在线预览</a>
	<div class="layer-tips-content putong-content">
		<br>
		<p>
			课程名称：
				<select id="classname" class="js-example-basic-single" style="width:280px"></select>
		</p>
		<br>
	    <p>
	    	资料类型：
	    		<select id="classresource" style="width:280px">
	    			<c:forEach var="d" items="${dictList }">
	    				<option value="${d.itemCode }">${d.itemValue }</option>
	    			</c:forEach>
	    		</select>
	    </p>
		<br>
	    <p>
	    	课程资料：
			<span class="upfile"> 
				<input type="file" id="doctype" name="doc" class="up-input"><a
				href="javascript:;" class="btn btn-default btn-up" id="docres" style="display: block;">上传课程资料</a>
			</span>
			<span id="dochint"></span>
		</p>
		<br>
	    <p>
	    	<span style="color:red">支持上传类型:doc,pdf,xls,ppt,docx,pptx,xlsx,zip,rar</span><br>
	    	<span style="color:red">* 上传资料不大于20M</span>
		</p>
	</div>
	<div class="layer-tips-btns putong-btn">
	    <p>
	    	<a href="javascript:;" class="btn btn-mini btn-primary btn-ok">保存</a>
	    	<a href="javascript:;" class="btn btn-mini btn-default btn-cancel">取消</a>
	    </p>
	</div>
	
	<div class="layer-tips-content online-content">
		<br>
		<p>
			课程名称：
				<select id="classnames" class="js-example-basic-single" style="width:280px"></select>
		</p>
		<br>
		<input type="hidden" id="classresources" value="COURSE_RESOURCE_ONLINE"/>
	    <%-- <p>
	    	资料类型：
	    		<select id="classresources" style="width:280px">
	    			<c:forEach var="d" items="${dictList }">
	    				<option value="${d.itemCode }">${d.itemValue }</option>
	    			</c:forEach>
	    		</select>
	    </p>
		<br> --%>
	    <p>
	    	课程资料：
			<span class="upfile"> 
				<input type="file" id="pdf" name="pdf" class="up-input" accept=".doc,.pdf,.swf" onchange="javascript:pdfChange();"><a
				href="javascript:;" class="btn btn-default btn-up" id="pdfs" style="display: block;">上传课程资料</a><span id="pdfhint"></span>
			</span>
		</p>
		<br>
	    <p>
	    	<span style="color:red">支持上传类型:doc,pdf,swf</span><br>
	    	<!-- <span style="color:red">* 上传资料不大于2M</span> -->
		</p>
	</div>
	<div class="layer-tips-btns online-btn">
	    <p>
	    	<a href="javascript:;" class="btn btn-mini btn-primary btn-ok-online">保存</a>
	    	<a href="javascript:;" class="btn btn-mini btn-default btn-cancel">取消</a>
	    </p>
	</div>
</div>

<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
</body>
</html>