<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程详情</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
    
    <script type="text/javascript">
    	$(function(){
    		$selectMenu("course_class_type");
    	});
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">课程详情</h2>
            <span class="line"></span>
        </div>
        <div class="c-main">
            <div class="s-title"><h2 class="h6">基本信息</h2></div>
            <div class="c-content">
                <p class="c">
                    <span class="c-title">学科</span>
                    <span class="c-content"><input type="text" class="readonly" value="${ct.itemOneName }" readonly></span>
                </p>
                <p class="c">
                    <span class="c-title">学科小类</span>
                    <span class="c-content"><input type="text" class="readonly" value="${ct.itemSecondName }" readonly></span>
                </p>
                <p class="c">
                    <span class="c-title">课程名称</span>
                    <span class="c-content" title="${ct.name }">
                     <c:choose>
			          	<c:when test="${fn:length(ct.name)>10}">
			          		<input type="text" class="readonly" id="courseName" value="${fn:substring(ct.name, 0, 10)}..." readonly>
			          	</c:when>
			          	<c:otherwise>
			          		<input type="text" class="readonly" id="courseName" value="${ct.name }" readonly>
			          	</c:otherwise>
			          </c:choose>
                    </span>
                </p>
                <p class="c">
                    <span class="c-title">定价</span>
                    <span class="c-content"><input type="text" class="readonly" value="${ct.originalPrice }" readonly></span>
                </p>
                <p class="c">
                    <span class="c-title">优惠价</span>
                    <span class="c-content"><input type="text" class="readonly" value="${ct.realPrice }" readonly></span>
                </p>
                <p class="c">
                    <span class="c-title">类型</span>
                    <span class="c-content"><input type="text" class="readonly" value="${wx:dictCode2Name(ct.typeCode) }" readonly></span>
                </p>
            </div>
            <c:if test="${!empty listModule }">
            <div class="s-title" id="kcdyInfo"><h2 class="h6">课程单元信息</h2></div>
            <div class="c-content">
                <ul>
                 <c:forEach items="${listModule }" var="modules">
                 	
                    <li>
                        <table class="table table-noline">
                            <tr>
                                <td>
                                    <span class="c-title" style="color:#999;">学科</span>
                                    <span class="c-content">${modules.itemOneName }</span>
                                </td>
                                <td>
                                    <span class="c-title"  style="color:#999;">班号</span>
                                    <span class="c-content">${modules.moduleNoName }</span>
                                </td>
                                <td>
                                    <span class="c-title"  style="color:#999;">校区</span>
                                    <span class="c-content">${modules.campusName }</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="c-title"  style="color:#999;">学科小类</span>
                                    <span class="c-content">${modules.itemSecondName }</span>
                                </td>
                                <td>
                                    <span class="c-title"  style="color:#999;">授课方式</span>
                                    <span class="c-content">${wx:dictCode2Name(modules.teachMethod) }</span>
                                </td>
                                <td>
                                    <span class="c-title"  style="color:#999;">已招生</span>
                                    <span class="c-content">${modules.enrollYetStudents==null?0:modules.enrollYetStudents }</span>
                                </td>
                            </tr>
                        </table>
                    </li>
                   
                  </c:forEach>
                </ul>
            </div>
            </c:if>
            <c:if test="${!empty chapterList }">
            <div class="s-title"><h2 class="h6">视频信息</h2></div>
            <div class="c-content">
                <div class="v-list">
                    <ul>
                    	<c:forEach items="${chapterList }" var="chapter" varStatus="status">
                    		<li class="v-title" chapterId="${cpr.comId }" chapter_id="${status.count }">
	                            <a href="javascript:;">第${status.count}${chapterName}：${chapter.chapterName }</a>
	                        </li>
	                        <c:forEach items="${chapter.lectures }" var="lecture" varStatus="status">
	                        	<c:choose>
	                        		<c:when test="${lecture.lectureOrder!=null }">
	                        			<li class="titleQRcode" courseId="${lecture.id }" course_id="${status.count }">
				                            <a href="javascript:;">第${status.count}${lectureName}：${lecture.lectureName }</a>
				                            <i class="iconfont iQRcode">&#xe6df;</i>
				                        </li>
	                        		</c:when>
	                        	</c:choose>
	                        </c:forEach>
                    	</c:forEach>
                    </ul>
                </div>
             </div>
			</c:if>
                <p class="c">
                    <span class="c-title">&nbsp;</span>
                    <span class="c-content">
<%--                     	<c:if test="${ct.isSale==0 }"> --%>
<!--                     		 <a href="javascript:onsale();" class="btn btn-primary">上架</a> -->
<%--                     	</c:if> --%>
                        <a href="javascript:history.go(-1)" class="btn btn-default">返回</a>
                    </span>
                </p>
            </div>
    </div>
</div>
</div>
<form id="onSaleForms" method="post">
	<input type="hidden" name="id" value="${ct.id }"/>
	<input type="hidden" name="publishStatus" value="CLASS_ON_SALE"/>
</form>
<input type="hidden" id="domain" value="${company.domain }"/>
<script type="text/javascript" src="<%=rootPath %>/plugins/qrcode/qrcode.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/myQRcode.js"></script>
<script type="text/javascript">
	function onsale(){
		$("#onSaleForms").attr("action",rootPath+"/simpleClasses/onSale").submit();
	}
</script>
<script type="text/javascript">
        $(function(){
        	 $('.titleQRcode').each(function(index,elem){
                 $(elem).attr('id',index);
                 $.myQRcode({id:index,ele:'i',"elem":elem});
             });
        });

    </script>
</body>
</html>