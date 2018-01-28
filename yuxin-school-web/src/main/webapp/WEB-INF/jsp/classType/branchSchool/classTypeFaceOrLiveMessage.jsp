<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-基本信息</title> 
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <style type="text/css">
    .classes .mainbackground .c-main p.c .c-content .tips,sb{
   		color:red;
   	}
    </style>
    <script type="text/javascript">
    	$(function(){
    		$selectMenu("course_class_type");
    		$chooseMenu("baseCode");
    	});
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonTitle.jsp"></jsp:include>
<div class="u-wrap company overflow points-use-class">
	 <jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonClass.jsp"></jsp:include>
    <div class="right-side">
       <div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="heading" data-ct="${ct}" data-protocol="${ct.protocolId}">
            <h2 class="h5">基本信息</h2>
            <span class="line"></span>
        </div>
        <form id="addFormOne" method="post">
        <div class="c-main">
            <div class="c-content">
                <p class="c">
                	<span class="c-title">分类</span>
                	<span class="c-content">
                    	<input type="text" class="readonly" id="itemOneName" marks="${ct.itemOneCode }" value="${ct.itemOneName }" readonly/>
                	</span>
                </p>
                <p class="c">
                    <span class="c-title">学段</span>
                    <span class="c-content">
                        <input type="text" class="readonly" id="itemSecondName" marks="${ct.itemSecondCode }" value="${ct.itemSecondName}" readonly/>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">学科</span>
                    <span class="c-content">
                         <input type="text" class="readonly" id="itemThirdName" marks="${ct.itemThirdCode }" value="${ct.itemThirdName}" readonly/>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">知识点</span>
                    <span class="c-content">
                         <input type="text" class="readonly" id="itemFourthName" marks="${ct.itemFourthCode }" value="${ct.itemFourthName}" readonly/>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">封面标签</span>
                    <span class="c-content">
                    	<input type="text" id="iconLable" class="readonly" name="iconLable" value="${ct.iconLable }" readonly/>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">课程名称</span>
                    <span class="c-content"><input type="text"  class="readonly"  name="name" value="${ct.name }" readonly/></span>
                </p>
                <p class="c">
                    <span class="c-title">定价</span>
                    <span class="c-content">
                    	<input type="text" class="readonly" name="originalPrice" id="prices" class="prices" value="${ct.originalPrice }" readonly>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">公开价</span>
                    <span class="c-content">
                    	<input name="realPrice"  class="readonly" id="realPrice" type="text" class="prices" value="${ct.publicPrice }" readonly>
                    </span>
                </p>
                <jsp:include page="/WEB-INF/jsp/classType/branchSchool/common_member_integral.jsp"></jsp:include>
                 <p class="c">
                    <span class="c-title">购买基数</span>
                    <span class="c-content"><input name="baseNum" class="readonly"  type="text" value="${ct.baseNum }" readonly>
                     <%--<i class="iconfont ask" style="cursor: pointer;color:gray;margin-left: 10px;" title="基数的值作为课程购买人数的一部分，显示在网校前台">&#xe60f;</i>--%>
                    </span>
                </p>
                <c:if test="${lable=='live' }">
	                 <p class="c setting">
	                    <span class="c-title">课程有效天数</span>
	                    <span class="c-content"><input name="validityDay" class="readonly" class="settingcount" type="text" value="${ct.validityDay }"></span>
	                 </p>
	                 <p class="c setting">
	                     <span class="c-title">观看次数</span>
	                    <span class="c-content"><input name="liveWatchCount" class="readonly" class="settingcount" type="text" value="${ct.liveWatchCount }"></span>
	                </p>
                </c:if>
                 <p class="c">
                    <span class="c-title">授课方式</span>
                    <span class="c-content">
                    	<c:if test="${ct.faceFlag=='1' }">
                    		<a href="javascript:;"  class="btn btn-mini btn-success">面授</a>
                    	</c:if>
                    	<c:if test="${ct.liveFlag=='1' }">
                    		<a href="javascript:;"  class="btn btn-mini btn-success">直播</a>
                    	</c:if>
                    </span>
                </p>
               <p class="c">
                    <span class="c-title">是否为数校推荐课程</span>
                    <span class="c-content" style="color:black;">
	                    <c:choose>
	                    	<c:when test="${ct.cddsRecommendFlag==1 }">
	                     		 <input type="radio" checked="checked" value="1" name="cddsRecommendFlag">是
	                     		 <input type="radio" value="0" name="cddsRecommendFlag">否
	                    	</c:when>
	                    	<c:otherwise>
	                    		<input type="radio" value="1" name="cddsRecommendFlag">是
	                     		 <input type="radio" checked="checked" value="0" name="cddsRecommendFlag">否
	                    	</c:otherwise>
	                    </c:choose>
                    </span>
               </p>
              <p class="c text-center operator">
                <a href="javascript:void(0);" onclick="saveCddsRecommendFlag();" class="btn btn-primary">保存</a>
           		<a href="<%=rootPath %>/branchSchool/queryClassType" class="btn btn-default">取消</a>
             </p>
            </div>
        </div>
	        <input type="hidden" id="companyId" value="${ct.companyId }"/>
	        <input type="hidden" name="id" id="classtypeIds" value="${ct.id }"/>
	        <input type="hidden" name="isSale" value="${ct.isSale==null?0:ct.isSale }"/>
	        <input type="hidden" name="lable" id="lable"/>
	        <input type="hidden" name="oneId" id="oneId"/>
	        <input type="hidden" name="twoId" id="twoId"/>
	        <input type="hidden" name="mark" id="mark"/>
	        <input type="hidden" name="moduleId" id="mdusId"/>
	        <input type="hidden" name="itemTag" id="course_lable_tag" value="${ct.itemTag }"/>
        </form>
    </div>
</div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/branchschool/addClassTypeBaseInfo.js"></script>
</body>
</html>