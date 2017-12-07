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
    		var iss=document.getElementById("itemOneCodeList").value;
			if(iss==''){
//				$("#itemOneCodeList").css("display","none");
			}
    	});
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<input type="hidden" id="twoSecItemId" value="${itemSecondId }"/>
<input type="hidden" id="twoSecItemCode" value="${twoSecItemCode }"/>
<input type="hidden" id="itemThirdCode" value="${itemThirdCode }"/>
<input type="hidden" id="itemFourthCode" value="${itemFourthCode }"/>
<div class="overflow points-use-class">
<c:choose>
	<c:when test="${type=='update' }">
		<div class="u-wrap classes">
		    <div class="nopadding">
		        <div class="steps">
		            <div class="line"></div>
		            <ul class="clear">
		                <li class="step2 s1 hover">
		                    <i>01</i>
		                    <em>基本信息</em>
		                </li>
		                <li class="step2 s2">
		                    <i>02</i>
		                    <em>课程详细</em>
		                </li>
		                <li class="step2 s3">
		                    <i>03</i>
		                    <em>安排课表</em>
		                </li>
		            </ul>
		        </div>
		    </div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="u-wrap classes">
		    <div class="nopadding">
		        <div class="steps">
		            <div class="line"></div>
		            <ul class="clear">
		                <li class="step2 s1 hover">
		                    <i>01</i>
		                    <em>基本信息</em>
		                </li>
		                <li class="step2 s2">
		                    <i>02</i>
		                    <em>课程详细</em>
		                </li>
		                <li class="step2 s3">
		                    <i>03</i>
		                    <em>安排课表</em>
		                </li>
		            </ul>
		        </div>
		    </div>
		</div>
	</c:otherwise>
</c:choose>
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
                    <span class="c-title">分类</span>
                    <span class="c-content">
                        <select name="itemOneCode" id="itemOneCodeList" onchange="Form.queryItemSecond()">
                        	<option  value="" data-id="">请选择</option>
                            <c:forEach items="${typeItems }" var="type" varStatus="status">
                                <c:if test="${type.itemCode==itemOneCode }">
                                    <option selected="selected" value="${type.itemCode }" data-id="${type.id}">${type.itemName }</option>
                                </c:if>
                                <c:if test="${type.itemCode!=itemOneCode }">
                                    <option value="${type.itemCode }" data-id="${type.id}">${type.itemName }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                         <c:if test="${empty typeItems }">
                             <input type="text" class="readonly" id="itemOneName" marks="${classType.itemOneCode }" value="${classType.itemOneName }" readonly>
                         </c:if>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">学段</span>
                    <span class="c-content">
                        <select name="itemSecondCode" id="itemSecondCodeList" onchange="Form.queryItemThird()">
                            <option  value="" data-id="">请选择</option>
                        </select>
                        <c:if test="${empty typeItems }">
                            <input type="text" class="readonly" id="itemSecondName" marks="${classType.itemSecondCode }" value="${classType.itemSecondName}" readonly/>
                        </c:if>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">学科</span>
                    <span class="c-content">
                        <select name="itemThirdCode" id="itemThirdCodeList" onchange="Form.queryTagsList()">
                            <option  value="" data-id="">请选择</option>
                        </select>
                        <c:if test="${empty typeItems }">
                            <input type="text" class="readonly" id="itemThirdName" marks="${classType.itemThirdCode }" value="${classType.itemThirdName}" readonly/>
                        </c:if>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">知识点</span>
                    <span class="c-content">
                        <select name="itemFourthCode" id="itemFourthCodeList" >
                            <option  value="" data-id="">请选择</option>
                        </select>
                        <c:if test="${empty typeItems }">
                            <input type="text" class="readonly" id="itemFourthName" marks="${classType.itemFourthCode }" value="${classType.itemFourthName}" readonly/>
                        </c:if>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">封面标签</span>
                    <span class="c-content"><input type="text" id="iconLable" name="iconLable" value="${classType.iconLable}" maxlength="18"></span>
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
                    <span class="c-content"><input name="realPrice" id="realPrice" type="text" class="prices" value="${classType.realPrice }"><sb>*</sb></span>
                </p>
                <%@include file="/WEB-INF/jsp/classType/common_member_integral.jsp" %>
                 <p class="c">
                    <span class="c-title">购买基数</span>
                    <span class="c-content"><input name="baseNum" type="text" value="${classType.baseNum }"><sb>*</sb>
                     <i class="iconfont ask" style="cursor: pointer;color:gray;margin-left: 10px;" title="基数的值作为课程购买人数的一部分，显示在网校前台">&#xe60f;</i>
                    </span>
                </p>
                <c:if test="${lable=='live' }">
                 <p class="c setting">
                    <span class="c-title">课程有效天数</span>
                    <span class="c-content"><input name="validityDay" class="settingcount" type="text" value="${classType.validityDay }"></span>
                </p>
                 <p class="c setting">
                     <span class="c-title">观看次数</span>
                    <span class="c-content"><input name="liveWatchCount" class="settingcount" type="text" value="${classType.liveWatchCount }"></span>
                </p>
                </c:if>
                 <p class="c">
                    <span class="c-title">授课方式</span>
                    <span class="c-content">
                    	<c:if test="${lable=='face' }">
                    		<a href="javascript:;" ids="face" id="lableType" class="btn btn-mini btn-success }">面授</a>
                    	</c:if>
                    	<c:if test="${lable=='live' }">
                    		<a href="javascript:;" ids="live" id="lableType" class="btn btn-mini btn-success }">直播</a>
                    	</c:if>
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
               <c:if test="${empty isArea or '0' eq isArea }">
               		<p class="c ">
	                    <span class="c-title">是否设为公开课程</span>
	                    <span class="c-content" style="color:black;">
	                     	<input type="radio" value="1" name="publishFlag">是
                        	<input type="radio" checked="checked" value="0" name="publishFlag">否
	                    </span>
	               </p>
	               <p class="c none">
	               		<span class="c-title">设置公开课程价格</span>
	                    <span class="c-content" style="color:black;">
	                     	<input type="text" name="publishPrice" id="publishPrices" class="prices">
	                    </span>
	               </p>
               </c:if>
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
        <input type="hidden" name="schoolsId" id="schoolsId"/>
        <input type="hidden" name="oneId" id="oneId"/>
        <input type="hidden" name="twoId" id="twoId"/>
        <input type="hidden" name="mark" id="mark"/>
       <input type="hidden" name="itemTag" id="course_lable_tag" value="${classType.itemTag }"/>
       </form>
    </div>
</div>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/addClassTypeBaseInfo.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script> 
</body>
</html>