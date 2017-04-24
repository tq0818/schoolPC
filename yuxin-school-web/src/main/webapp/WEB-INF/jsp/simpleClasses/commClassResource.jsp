<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-课程资料</title> 
    <link rel="stylesheet" type="text/css" href="../../../stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="../../../stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="../../../stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="../../../stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="../../../stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="../../../stylesheets/classedit.css"/>
        <link rel="stylesheet" type="text/css" href="../../../stylesheets/operate.css?v=1.0"/>
	<link rel="stylesheet" type="text/css" href="../../../stylesheets/select2.min.css" />
     <style type="text/css">
		.btn-upload{float: right;margin-top:-15px}
		.h5{width:100px}
		.up-input{position:relative;width:166px;height:32px;z-index:1}
		.upfile{position:relative;display:inline-block}
		.upfile .btn-up{position:absolute;top:0;left:0;}
		.tt tr td{
			border: 1px solid #ddd;
		}
		.tt th{
		     border: 1px solid #ddd;
			 padding: 8px;
		}
	</style>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/plupload.full.min.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/i18n/zh_CN.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/moxie.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.2.1/jquery.js"></script>
	<script type="text/javascript" src="http://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.js"></script>
	 <script type="text/javascript" src="../../../javascripts/select2.full.js"></script>
	<script type="text/javascript" src="../../../javascripts/operate.js"></script>
	<script type="text/javascript" src="../../../javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="../../../javascripts/company/jquery.cityselect.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
	<script type="text/javascript" src="../../../javascripts/class/res/classres.js"></script>
    <script type="text/javascript">
     var rootPath='<%=rootPath %>';
    </script>
</head>
<body>
<div class="q-box">
<input type="hidden" value="${classtypeId }" id="cId"/>
<input type="hidden" value="${ct.name }" id="classname"/>
<input type="hidden" value="${oneId }" id="oneId"/>
<input type="hidden" value="${twoId }" id="twoId"/>
<input type="hidden" value="${ct.name }" id="classtypeName">

<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/classType/commonTitle.jsp" %>
<div class="u-wrap company overflow points-use-class">
	 <%@include file="/WEB-INF/jsp/simpleClasses/commonClass.jsp" %>
   <div class="right-side">
     	<div class="u-wrap operate" style="width: 97%;margin:0 auto;">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">课程资料</h2>
            <a href="javascript:;" class="btn btn-mini btn-primary btn-upload">上传资料</a>
            <span class="line"></span>
        </div>
        <div class="select-option clear" style="display: none;">
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
    </div>
</div>
		

    </div>
</div>
<!-- 结束 -->
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
<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
</div>
</body>
</html>