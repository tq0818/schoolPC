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
<input type="hidden" id="twoSecItemCode" value="${classType.itemSecondCode }"/>
<input type="hidden" id="itemThirdCode" value="${classType.itemThirdCode }"/>
<input type="hidden" id="itemFourthCode" value="${classType.itemFourthCode }"/>
<%@include file="/WEB-INF/jsp/classType/commonTitle.jsp" %>
<div class="u-wrap company overflow points-use-class">
	 <%@include file="/WEB-INF/jsp/simpleClasses/commonClass.jsp" %>
    <div class="right-side">
       <div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="heading" data-ct="${classType}" data-protocol="${classType.protocolId}">
            <h2 class="h5" >基本信息</h2>
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
                                <c:if test="${type.itemCode==classType.itemOneCode }">
                                    <option selected="selected" value="${type.itemCode }" data-id="${type.id}">${type.itemName }</option>
                                </c:if>
                                <c:if test="${type.itemCode!=classType.itemOneCode }">
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
                        <select name="itemThirdCode" id="itemThirdCodeList" onchange="Form.queryTagsList(null,1)">
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
                    <span class="c-title">课程属性</span>
                    <span class="c-content">
                        <select name="isMicroClass" id="isMicroClass" >
                            <option value="0" <c:if test='${classType.isMicroClass != 1}'>selected="selected"</c:if>>回放课程</option>
                            <option value="1" <c:if test='${classType.isMicroClass == 1}'>selected="selected"</c:if>>微课</option>
                        </select>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">封面标签</span>
                    <span class="c-content"><input type="text" id="iconLable" name="iconLable" value="${classType.iconLable }" maxlength="18"></span>
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
                    		<input type="text" name="originalPrice" class="prices" id="prices" value="${classType.originalPrice }"><sb>*</sb>
                    	</c:otherwise>
                    </c:choose>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">优惠价</span>
                    <span class="c-content"><input name="realPrice" class="prices" id="realPrice"  type="text" value="${classType.realPrice }"><sb>*</sb></span>
                </p>
                <%@include file="/WEB-INF/jsp/classType/common_member_integral.jsp" %>
                 <p class="c">
                    <span class="c-title">购买基数</span>
                    <span class="c-content"><input name="baseNum" type="text" value="${classType.baseNum }" class="baseNum"><sb>*</sb>
                     <i class="iconfont ask" style="cursor: pointer;color:gray;margin-left: 10px;" title="基数的值作为课程购买人数的一部分，显示在网校前台">&#xe60f;</i>
                    </span>
                </p>
                 <p class="c setting">
                    <span class="c-title">课程有效天数</span>
                    <span class="c-content"><input name="validityDay" class="settingcount" type="text" value="${classType.validityDay }"/></span>
                </p>
                 <p class="c setting">
                     <span class="c-title">观看次数</span>
                    <span class="c-content"><input name="videoWatchCount" class="settingcount" type="text" value="${classType.videoWatchCount }"/></span>
                </p>
                 <p class="c">
                    <span class="c-title">总课时</span>
                    <span class="c-content"><input name="courseNum" type="text" value="${courseNum==null?'0':courseNum }" class="courseNum">*</span>
                </p>
                 <p class="c">
                    <span class="c-title">授课方式</span>
                    <span class="c-content">
                    	<a href="javascript:;" ids="video" id="lableType" class="btn btn-mini btn-success }">录播</a>
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
                <%--<p class="c">--%>
                    <%--<span class="c-title">是否为微课</span>--%>
                    <%--<span class="c-content" style="color:black;">--%>
                     	<%--<c:if test="${classType.isMicroClass==1 }">--%>
                            <%--<input type="radio" checked="checked" value="1" name="isMicroClass">是--%>
                            <%--<input type="radio" value="0" name="isMicroClass">否--%>
                        <%--</c:if>--%>
                       <%--<c:if test="${classType.isMicroClass==0 }">--%>
                           <%--<input type="radio" value="1" name="isMicroClass">是--%>
                           <%--<input type="radio" checked="checked" value="0" name="isMicroClass">否--%>
                       <%--</c:if>--%>
                        <%--<c:if test="${empty classType.isMicroClass }">--%>
                            <%--<input type="radio" value="1" name="isMicroClass">是--%>
                            <%--<input type="radio" checked="checked" value="0" name="isMicroClass">否--%>
                        <%--</c:if>--%>
                    <%--</span>--%>
                <%--</p>--%>
              <p class="c text-center operator">
                <a href="javascript:Form.addFormOne('save')" class="btn btn-primary ">保存</a>
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
        <input type="hidden" name="moduleId" id="mdusId"/>
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