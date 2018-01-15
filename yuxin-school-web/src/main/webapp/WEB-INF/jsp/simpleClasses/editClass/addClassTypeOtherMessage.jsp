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
      <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
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
<input type="hidden" id="twoSecItemId" value="${itemSecondId }"/>
<%@include file="/WEB-INF/jsp/classType/commonTitle.jsp" %>
<div class="u-wrap company overflow points-use-class">
	<%@include file="/WEB-INF/jsp/simpleClasses/commonClass.jsp" %>
    <div class="right-side">
       <div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="heading" data-ct="${classType}" data-protocol="${classType.protocolId}">
            <h2 class="h5">基本信息</h2>
            <span class="line"></span>
        </div>
        <form id="addFormOne" method="post">
        <div class="c-main">
            <div class="c-content">
                <p class="c">
                    <span class="c-title">学科</span>
                    <span class="c-content">
                        <c:choose>
                    	<c:when test="${!empty firstItems }">
                    		<select name="itemOneId" id="itemOneList" onchange="Form.queryItemSecond()">
	                        	<c:forEach items="${firstItems }" var="itemOne" varStatus="status">
	                        		<c:if test="${itemOne.id==itemOneId }">
	                        			<option selected="selected" value="${itemOne.id }">${itemOne.itemName }</option>
	                        		</c:if>
	                               <c:if test="${itemOne.id!=itemOneId }">
	                        			<option value="${itemOne.id }">${itemOne.itemName }</option>
	                        		</c:if>
	                            </c:forEach>
	                        </select>
                    	</c:when>
                    	<c:otherwise>
                    		<input type="hidden" class="readonly" id="itemOneName" marks="${classType.itemOneId }" value="${classType.itemOneName }" readonly>
                    		${classType.itemOneName }
                    	</c:otherwise>
                    </c:choose>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">学科小类</span>
                    <span class="c-content">
                        <c:choose>
                    		<c:when test="${!empty firstItems }">
                    			<select name="itemSecondId" id="itemSecondList">
                         
                        		</select>
                    		</c:when>
                    		<c:otherwise>
                    			<input type="hidden" class="readonly" id="itemSecondName" marks="${classType.itemSecondId }" value="${classType.itemSecondName}" readonly/>
                    			${classType.itemSecondName }
                    		</c:otherwise>
                    	</c:choose>
                    </span>
                </p>
               <p class="c" id="labeSets">
                    <span class="c-title">所属标签</span>
                    <span class="c-content">
                        <select class="itemTagLists" id="itemTagLists_one" style="width:150px;"></select>
                    </span>
                </p>
                 <p class="c" id="labeSecondSets">
                    <span class="c-title">二级标签</span>
                    <span class="c-content">
                        <select class="itemTagLists" id="itemTagLists_two" style="width:150px;"></select>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">课程名称</span>
                    <span class="c-content"><input type="text" id="classTypeName" name="name" value="${classType.name }"><sb>*</sb></span>
                    <span class="firstspan" style="display: none;margin-left:360px;margin-top:-28px;">该课程名称已存在</span>
                </p>
                <p class="c">
                    <span class="c-title">定价</span>
                    <span class="c-content">
                    <c:choose>
                    	<c:when test="${empty classType.originalPrice }">
                    		<input id="prices" class="prices" name="originalPrice" type="text"><sb>*</sb>
                    	</c:when>
                    	<c:otherwise>
                    		<input type="text" name="originalPrice" id="prices" class="prices" value="${classType.originalPrice }"><sb>*</sb>
                    	</c:otherwise>
                    </c:choose>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">优惠价</span>
                    <span class="c-content"><input name="realPrice" type="text" class="prices" id="realPrice" value="${classType.realPrice }"><sb>*</sb></span>
                </p>
                <%@include file="/WEB-INF/jsp/classType/common_member_integral.jsp" %>
                 <p class="c">
                    <span class="c-title">购买基数</span>
                    <span class="c-content"><input name="baseNum" type="text" class="baseNum" value="${classType.baseNum }"><sb>*</sb>
                     <i class="iconfont ask" style="cursor: pointer;color:gray;margin-left: 10px;" title="基数的值作为课程购买人数的一部分，显示在网校前台">&#xe60f;</i>
                    </span>
                </p>
                 <p class="c">
                    <span class="c-title">授课方式</span>
                    <span class="c-content">
                    	<a href="javascript:;" ids="remote" id="lableType" class="btn btn-mini btn-success }">其他</a>
                    </span>
                </p>
               <p class="c">
                    <span class="c-title">是否为推荐课程</span>
                     <span class="c-content" style="color:black;">
                     	<c:if test="${classType.recommendFlag==1 }">
                     		 <input type="radio" checked="checked" value="1" name="recommendFlag">是
                     		 <input type="radio" value="0" name="recommendFlag">否
                     	</c:if>
                       <c:if test="${classType.recommendFlag==0 }">
                       		<input type="radio" value="1" name="recommendFlag">是
                     		 <input type="radio" checked="checked" value="0" name="recommendFlag">否
                     	</c:if>
                        <c:if test="${empty classType.recommendFlag }">
                        	<input type="radio" value="1" name="recommendFlag">是
                        	<input type="radio" checked="checked" value="0" name="recommendFlag">否
                        </c:if>
                    </span>
               </p>
                <c:if test="${not empty isArea and '0' ne isArea }">
               		<p class="c ">
	                    <span class="c-title">是否设为公开课程</span>
	                    <span class="c-content" style="color:black;">
	                     	<input type="radio" value="1" name="isPublic" <c:if test="${classType.isPublic==1 }"> checked="checked"</c:if>>是
                        	<input type="radio" value="0" name="isPublic"  <c:if test="${classType.isPublic==0 or empty classType.isPublic }"> checked="checked"</c:if>>否
	                    </span>
	               </p>
	                 <c:choose>
               			<c:when test="${classType.isPublic==1 }">
               				<p class="c publicPrice"> 
               			</c:when>
               			<c:otherwise>
               				<p class="c none publicPrice"> 
               			</c:otherwise>
               		</c:choose>
	               		<span class="c-title">设置公开课程价格</span>
	                    <span class="c-content" style="color:black;">
	                     	<input type="text" name="publicPrice" id="publicPrice" class="prices" value="${classType.publicPrice }">
	                    </span>
	               </p>
               </c:if>
              <p class="c text-center operator">
                <a href="javascript:Form.addFormOne('save')" class="btn btn-primary">保存</a>
           		<a href="<%=rootPath %>/simpleClasses/showClassTypePage" class="btn btn-default">取消</a>
             </p>
            </div>
        </div>
        <input type="hidden" name="type1" id="type1" value="${type }"/>
        <input type="hidden" name="id" id="classtypeIds" value="${classType.id }"/>
        <input type="hidden" name="isSale" value="${classType.isSale==null?0:classType.isSale }"/>
        <input type="hidden" name="lable" id="lable"/>
        <input type="hidden" name="schoolsId" id="schoolsId"/>
        <input type="hidden" name="oneId" id="oneId"/>
        <input type="hidden" name="twoId" id="twoId"/>
        <input type="hidden" name="mark" id="mark"/>
       <input type="hidden" name="itemTag" id="course_lable_tag" value="${classType.itemTag }"/>
       </form>
    </div>
</div>
    </div>
</div>

<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/editClass/addClassTypeBaseInfo.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script> 
</body>
</html>