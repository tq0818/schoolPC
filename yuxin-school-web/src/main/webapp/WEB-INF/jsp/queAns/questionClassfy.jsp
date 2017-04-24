<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>问答设置</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
     <div class="left-side">
        <!-- <div class="left-side-title goBack">返回</div> -->
        <div class="left-side-title">
        	<em>问答社区</em>
        	<span class="iconfont return-pic goBack">&#xe650;</span>
        </div>
        <ul>
            <li class="subentry active">问答分类设置</li>
        </ul>
    </div>
    <div class="right-side">
        <div class="title-box">
            <div class="tit-font">
                <span class="t">${isCorP == 0?'分类内容设置':'课程分类' }</span>
            </div>
        </div>
        <c:if test="${isCorP == 0 }">
        	<p class="prompt-font">${isCorP == 0?'说明：机构可以在这里对分类名称进行自定义':'说明：可以对在问答社区中给学科小类定义别名' }</p>
        </c:if>
         <c:if test="${isCorP == 1 }">
        	<p class="prompt-font">${isCorP == 0?'说明：机构可以在这里对分类名称进行自定义':'说明：可以对在问答社区中给学科小类定义别名' }</p>
        </c:if>
        <div class="padd-box">
        	<c:if test="${isCorP == 0 }">
	        	<div>
	                <div class="sec-tit">自定义分类</div>
	                <p class="prompt">机构需要自定义分类名称</p>
	                <div id="zdyInfo">
	                
	                </div>
	                <div class="padd-bar new-add">
	                    <div class="show-name">显示名称：</div>
	                    <form action="">
	                        <label for="definition3">社区问答</label>
	                        <input type="text" id="definitionAddZdy"/>
	                    </form>
	                    <div class="sives-btn saveZdy">保存</div>
	                    <div class="dels">取消</div>
	                </div>
	                
	            </div>
	            <div class="add-btn">
	                <em class="iconfont">&#xe606;</em>
	                添加分类
	            </div>
	            <p class="label-tit">
	                <em>标签</em>
	                <em class="tit-font">
	                    <em class="iconfont normal kcStatus ${isC == 1?'open':'close' }">${isC == 1?'&#xe603;':'&#xe604;' }</em>
	                    <span class="i ${isC == 1?'open':'close' }">${isC == 1?'已启用':'已禁用' }</span>
	                </em>
	            </p>
        	</c:if>
        	<c:if test="${isCorP == 0 }">
	            <p class="prompt-font-more">
	            	说明：开启后学员可以在提问时，给问题贴标签。检索标签时根据学科小类进行检索。
	            </p>
            </c:if>
            <div class="courses-box" id="kcInfo">
            	
            </div>
        </div>
    </div>
</div>
<div class="fixed-layer delet">
    <div class="pop-layer">
        <p><em class="iconfont">&#xe653;</em>删除后该分类下的问答将只能在全部问答中找到</p>
        <div class="pop-btn-box">
            <span class="y">是</span>
            <span class="n">否</span>
        </div>
    </div>
</div>
<!--  <div class="fixed-layer disabl">
    <div class="pop-layer">
        <div class="pop-tit">请确认</div>
        <p><em class="iconfont">&#xe653;</em>禁用后将不显示该标签下的问答</p>
        <div class="pop-btn-box">
            <span class="y">是</span>
            <span class="n">否</span>
        </div>
    </div>
</div>-->
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<input type="hidden" value="${isCorP}" id="isCorP"/>
<input type="hidden" value="${isC}" id="isC"/>
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script src="<%=rootPath%>/javascripts/queAns/questionClassfyset.js"></script>
<script src="<%=rootPath%>/javascripts/queAns/questionClassfy.js"></script>

</body>
</html>