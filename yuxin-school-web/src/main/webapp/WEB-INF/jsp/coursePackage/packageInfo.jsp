<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程包信息</title> 
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
    <style type="text/css">
     	.classes .mainbackground .c-main p.c span{ 
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
        <div class="heading" data-cp="${classPackage}" data-protocol="${classPackage.protocolId}">
            <h2 class="h5">基本信息</h2>
            <span class="line"></span>
        </div>
        <c:if test="${!empty classPackage }">
        <input type="hidden" id="lableType" value="edit"/>
        </c:if>
        <c:if test="${empty classPackage }">
        <input type="hidden" id="lableType" value="add"/>
        </c:if>
        <form id="addFormOne" method="post">
        <input type="hidden" id="packageId" name="id" value="${classPackage.id }"/>
        <input type="hidden" id="categoryCode" name="categoryCode" value="${classPackage.categoryCode }"/>
        <div class="c-main">
            <div class="c-content">
                <p class="c">
                    <span class="c-title">分类</span>
                    <span class="c-content" style="width: 70%;">
                		<select id="firstTypeList" onchange="Form.queryItemSecond()"></select>
                		<select id="secondeTypeList" onchange="Form.querythirdList()"></select>
                		<select id="thirdTypeList">
                		</select>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">课程包名称</span>
                    <span class="c-content"><input type="text" id="packageName" name="name" value="${classPackage.name }">*</span>
                </p>
                <p class="c">
                    <span class="c-title">优惠价</span>
                    <span class="c-content"><input name="realPrice"  class="prices" id="realPrice" type="text" value="${classPackage.realPrice }">*</span>
                </p>
                 <p class="c">
                    <span class="c-title">购买基数</span>
                    <span class="c-content"><input name="baseNum" type="text" value="${classPackage.baseNum }">*
                    <i class="iconfont ask" style="cursor: pointer;color:gray;" title="基数的值作为课程包购买人数的一部分，显示在网校前台">&#xe60f;</i>
                    </span>
                </p>
               <p class="c isrecomment">
                    <span class="c-title">是否为推荐课程包</span>
                     <span class="c-content" style="color:black;">
                     	<c:if test="${classPackage.recommendFlag==1 }">
                     		 <input type="radio" checked="checked" value="1" name="recommendFlag">是
                     		 <input type="radio" value="0" name="recommendFlag">否
                     	</c:if>
                       <c:if test="${classPackage.recommendFlag==0 }">
                       		<input type="radio" value="1" name="recommendFlag">是
                     		 <input type="radio" checked="checked" value="0" name="recommendFlag">否
                     	</c:if>
                        <c:if test="${empty classPackage.recommendFlag }">
                        	<input type="radio" value="1" name="recommendFlag">是
                        	<input type="radio" checked="checked" value="0" name="recommendFlag">否
                        </c:if>
                    </span>
               </p>
              <p class="c text-center">
             	<a href="javascript:void(0);" mark="no_continue" class="btn btn-primary save_baseInfo">保存</a>
             	<a href="javascript:void(0);" mark="continue" class="btn btn-primary save_baseInfo">保存并继续</a>
           		<a href="<%=rootPath %>/classPackage/list" class="btn btn-default">取消</a>
             </p>
            </div>
        </div>
       </form>
    </div>
</div>
    </div>
</div>

<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
   <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/cousePackage/packageInfo.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>
</body>
</html>