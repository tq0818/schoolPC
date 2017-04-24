<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-基本信息</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
   <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
   <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
    <style type="text/css">
    .classes .mainbackground .c-main p.c .c-content .tips,sb{
   		color:red;
   	}
    </style> 
     
    <script type="text/javascript">
    	$(function(){
    		$selectMenu("course_class_type");
    		var iss=document.getElementById("itemOneList").value;
			if(iss==''){
				$("#itemOneList").css("display","none");
			}
    	});
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<input type="hidden" id="twoSecItemId" value="${itemSecondId }"/>
<div class="overflow points-use-class">
<div class="u-wrap classes">
    <div class="nopadding">
        <div class="steps">
            <div class="line"></div>
            <ul class="clear">
                
            </ul>
        </div>
    </div>
</div>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="heading"  data-ct="${classType}" data-protocol="${classType.protocolId}">
            <h2 class="h5">基本信息</h2>
            <span class="line"></span>
        </div>
        <form id="addFormOne" method="post">
        <div class="c-main">
            <div class="c-content">
                <p class="c">
                    <span class="c-title">学科</span>
                    <span class="c-content">
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
                         <c:if test="${empty firstItems }">
                            <input type="text" class="readonly" id="itemOneName" marks="${classType.itemOneId }" value="${classType.itemOneName }" readonly>
                        </c:if>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">学科小类</span>
                    <span class="c-content">
                        <select name="itemSecondId" id="itemSecondList" onchange="Form.queryTagsList()">
                         
                        </select>
                        <c:if test="${empty firstItems }">
                        	<input type="text" class="readonly" id="itemSecondName" marks="${classType.itemSecondId }" value="${classType.itemSecondName}" readonly/>
                        </c:if>
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
                    <span class="c-content"><input name="realPrice" class="prices" type="text" id="realPrice" value="${classType.realPrice }"><sb>*</sb></span>
                </p>
                <%@include file="/WEB-INF/jsp/classType/common_member_integral.jsp" %>
                 <p class="c">
                    <span class="c-title">购买基数</span>
                    <span class="c-content"><input name="baseNum" type="text" value="${classType.baseNum }"><sb>*</sb>
                    <i class="iconfont ask" style="cursor: pointer;color:gray;margin-left: 10px;" title="基数的值作为课程购买人数的一部分，显示在网校前台">&#xe60f;</i>
                    </span>
                </p>
                <p class="c setting">
                    <span class="c-title">课程有效天数</span>
                    <span class="c-content"><input name="validityDay" class="settingcount" type="text" value="${classType.validityDay }"></span>
                </p>
                <p class="c setting">
                     <span class="c-title">直播观看次数</span>
                    <span class="c-content"><input name="liveWatchCount" class="settingcount" type="text" value="${classType.liveWatchCount }"></span>
                </p>
                <p class="c setting">
                     <span class="c-title">视频观看次数</span>
                    <span class="c-content"><input name="videoWatchCount" class="settingcount" type="text" value="${classType.videoWatchCount }"></span>
                </p>
                 <p class="c">
                    <span class="c-title" id="lableType">授课方式</span>
                    <span class="c-content" id="ltype">
	                    <c:choose>
	                    	<c:when test="${type=='update' }">
	                    		<c:choose>
	                    			<c:when test="${classType.videoFlag==1 }">
	                    				<a href="javascript:;" ids="video" class="btn btn-mini btn-default btn-success">录播</a>
	                    			</c:when>
	                    			<c:otherwise>
	                    				<a href="javascript:;" ids="video" class="btn btn-mini btn-default">录播</a>
	                    			</c:otherwise>
	                    		</c:choose>
	                    		<c:choose>
	                    			<c:when test="${classType.liveFlag==1}">
	                    				<a href="javascript:;" ids="live" class="btn btn-mini btn-default btn-success">直播</a>
	                    			</c:when>
	                    			<c:otherwise>
	                    				<a href="javascript:;" ids="live" class="btn btn-mini btn-default">直播</a>
	                    			</c:otherwise>
	                    		</c:choose>
	                    		<c:choose>
	                    			<c:when test="${classType.faceFlag==1}">
	                    				<a href="javascript:;" ids="face" class="btn btn-mini btn-default btn-success">面授</a>
	                    			</c:when>
	                    			<c:otherwise>
	                    				<a href="javascript:;" ids="face" class="btn btn-mini btn-default">面授</a>
	                    			</c:otherwise>
	                    		</c:choose>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<a href="javascript:;" ids="video" class="btn btn-mini btn-default btn-success">录播</a>
			                    <a href="javascript:;" ids="live" class="btn btn-mini btn-default btn-success">直播</a>
			                    <a href="javascript:;" ids="face" class="btn btn-mini btn-default btn-success">面授</a>
	                    	</c:otherwise>
	                    </c:choose>
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
              <p class="c text-center">
             	<a href="javascript:Form.addFormOne('saveandtui')" class="btn btn-primary">保存并退出</a>
                <a href="javascript:Form.addFormOne('save')" class="btn btn-primary">保存并继续</a>
           		<a href="<%=rootPath %>/simpleClasses/showClassTypePage" class="btn btn-default">取消</a>
             </p>
            </div>
        </div>
        <input type="hidden" name="type1" id="type1" value="${type }"/>
        <input type="hidden" name="id" id="classtypeIds" value="${classType.id }"/>
        <input type="hidden" name="isSale" value="${classType.isSale==null?0:classType.isSale }"/>
     	<input type="hidden" name="lable" id="lable"/>
        <input type="hidden" name="oneId" id="oneId"/>
        <input type="hidden" name="twoId" id="twoId"/>
        <input type="hidden" name="mark" id="mark"/>
        <input type="hidden" name="ltype" id="courseType"/>
        <input type="hidden" name="itemTag" id="course_lable_tag" value="${classType.itemTag }"/>
       </form>
    </div>
</div>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/addClassTogtherBaseInfo.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script> 
</body>
</html>