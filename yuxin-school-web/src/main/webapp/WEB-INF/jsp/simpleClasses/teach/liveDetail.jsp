<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>课程单元详情</title>
    <%@include file="/decorators/import.jsp"%>
   <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/class/live_add.js"></script>
    
</head>
<body>

<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>

<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">课程单元详情</h2>
            <span class="line"></span>
        </div>
        <div class="main-content" style="height:360px">
            <div class="m-title">
                <h2 class="h6">模块信息</h2>
            </div>
            <ul class="list-infos clear">
                <li>
                    <p class='c'>
                        <span class="c-title">学科</span>
                        <span class="c-content">
                            	${moduleVo.itemOneName }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">学科小类</span>
                        <span class="c-content">
                           		${moduleVo.itemSecondName }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">模块名称</span>
                        <span class="c-content">
                            	${moduleVo.name }
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">授课方式</span>
                        <span class="c-content">
                            	${wx:dictCode2Name(moduleVo.teachMethod )}
                        </span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">总 课 时</span>
                        <span class="c-content">${moduleVo.totalClassHour }课时</span>
                    </p>
                </li>
                <li>
                    <p class='c'>
                        <span class="c-title">课程单元类型</span>
                        <span class="c-content">${wx:dictCode2Name(moduleVo.moduleType) }</span>
                    </p>
                </li>
                <li>
                    <p class='h'>
                        <span class="c-title">描述</span>
                        <span class="c-content">
                        <textarea  style="padding:0px;height:115px;width: 630px;border-style:solid;resize : none;background-color:transparent;border:0px" class="readonly mainbackground" disabled="disabled" readonly="readonly">${moduleVo.moduleDesc }</textarea>
                        </span>
                    </p>
                </li>
            </ul>
        </div>
        
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">包含此课程单元的课程名称</h2>
            </div>
            <div class="list-infos clear">
            <c:if test="${fn:length(list)>0 }">
            	<c:forEach items="${list }" var="classType">
                <a class="btn btn-link" href="javascript:;" style="padding:20px" id="${classType.id }">${classType.name }</a>
               </c:forEach>
             </c:if>
            <c:if test="${fn:length(list)==0 }">
                <li>
                    <p>
                        <span class="c-title" style="color:red">没有此课程单元的课程!</span>
                    </p>
                </li>
             </c:if>
            </div>
        </div>
        
        <div class="main-content">
            <div class="m-title">
                <h2 class="h6">班号信息</h2>
            </div>
             <ul class="list-infos clear"></ul>
            <div class="c-list table-list">
				<c:if test="${fn:length(classModuleNos)>0 }">
            	<c:forEach items="${classModuleNos }" var="classModuleNo">
                <table class="table table-noline">
	                <col width="33%">
					<col width="33%">
					<col width="33%">
                    <tr>
                        <td>
                            <span class="c-title" style="color:gray">学科</span>
                            <span class="c-content" style="padding:20px">${classModuleNo.itemName }</span>
                        </td>
                        <td>
                            <span class="c-title" style="color:gray">班号</span>
                            <span class="c-content" style="padding:40px">${classModuleNo.name }</span>
                        </td>
                        <td>
                            <span class="c-title" style="color:gray">校区</span>
                            <span class="c-content" style="padding:30px">${classModuleNo.schoolName }</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span class="c-title" style="color:gray">学科小类</span>
                            <span class="c-content" style="padding:20px">${classModuleNo.itemSecondName  }</span>
                        </td>
                        <td>
                            <span class="c-title" style="color:gray">授课方式</span>
                            <span class="c-content" style="padding:20px">${wx:dictCode2Name(classModuleNo.classTeachType) }</span>
                        </td>
                        <td>
                            <span class="c-title" style="color:gray">已招生</span>
                            <span class="c-content" style="padding:20px">${classModuleNo.enrollYetStudents }/${classModuleNo.planEnrollStudents }</span>
                        </td>
                    </tr>
                </table>
                
                </c:forEach>
                </c:if>
                <c:if test="${fn:length(classModuleNos)==0 }">
                        <span class="c-title" style="color:red;padding:20px;font-size:14px;">没有相关班号信息!</span>
                
                </c:if>
            </div>
            
        </div>
        
        
        <div class="m-bo text-center">
                <a href="javascript:void(0);" onclick="history.go(-1)" class="btn btn-big btn-primary">返回</a>
         </div>
    </div>
</div>
<input id="teachMethod" value="${teachMethod }" type="hidden"/>

</body>
</html>