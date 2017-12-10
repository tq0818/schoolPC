<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-开始招生</title> 
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonTitle.jsp"></jsp:include>
<div class="u-wrap company overflow">
	<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonClass.jsp"></jsp:include>
    <div class="right-side">
       <div class="u-wrap classes">
    <div class="mainbackground nopadding">
<!--         <div class="heading"> -->
<%--             <span class="h5">${ct.name }</span> --%>
<!--             <span class="float-right"> -->
<%-- 	            <span class="courseStatus">${ct.isSale==0?'未上架':'已上架' }</span> --%>
<%-- 	            <span>${ct.itemOneName }-${ct.itemSecondName }</span> --%>
<%-- 	            <span>定价:${ct.realPrice }</span> --%>
<!--             </span> -->
<!--             <span class="line"></span> -->
<!--         </div> -->
        <div class="c-main">
            <div class="c-content">
            	 <div class="teacher-btns" style="float:right;">
            	   <input type="hidden" id="className" value="${ct.name }"/>
            	 	<input type="hidden" id="classtypeId" value="${ct.id }"/>
            	 	<input type="hidden" id="companyId" value="${ct.companyId }">
                    <input type="hidden" id="resourceId" />
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
                   <!--  <a href="javascript:save();" class="btn btn-primary">保存</a> -->
                    <a href="<%=rootPath %>/branchSchool/queryClassType" class="btn btn-default">返回</a>
                </p>
            </div>
        </div>
    </div>
</div>
    </div>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript">
$(document).ready(function(){
	$chooseMenu("listCode");
});
function save(){
	$.msg("保存信息成功");
}
</script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/class/branchschool/classTypesimplelive.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
	 <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/DateUtils.js"></script>
</body>
</html>