<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
     <title>公开课轮播图设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/openClass/openClassBanner.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_openclass.jsp"></jsp:include>
    <div class="right-side">
       <div class="u-wrap set-system">
    <div class="mainbackground space">
		<input type="hidden" id="schoolId"/>
			<div class="title single">
				<h2 class="h6 fl" style="font-size: 14px;">选择分校</h2>
				<div class="sc-type" id="schoolList" style="top: inherit;">
					<c:forEach items="${schoolList }" var="school1" varStatus="status">
		        		<c:if test="${school1.id==schoolId }">
		        			<a href="javascript:queryCycleData(${school1.id });" class="btn btn-sm btn-default btn-success" mark="${school1.id }">${school1.schoolName }</a>
		        		</c:if>
		        		<c:if test="${school1.id!=schoolId }">
		        			<a href="javascript:queryCycleData(${school1.id });" class="btn btn-sm btn-default" mark="${school1.id }">${school1.schoolName }</a>
		        		</c:if>
		            </c:forEach>
		            <c:if test="${empty schoolList }">
		            	<a href="javascript:queryCycleData(${school.id });" class="btn btn-sm btn-default btn-success" mark="${school.id }">${school.schoolName }</a>
		            </c:if>
				</div>
				 <div class="addbtn" style="cursor: pointer;margin-top: -20px;"><a href="javascript:;" class="btn btn-primary" id="addfocus"><em class="iconfont">&#xe606;</em>添加一张轮播</a></div>
			</div>
       
        <div class="set-focus">
            <ul id="cycleDataLists">
                <!-- 新增幻灯 -->
                <li class="add-focus" style="display: none;">
                    <div class="focus-title">新的幻灯片</div>
                    <div class="fileupload fileupload-new focus-content clear " data-provides="fileupload">
                        <div class="left" id="leftCont">
                        	<p class='tips-msg'>上传图片尺寸为1400*350，<a href="javascript:;">点击上传</a><input type="file" id="imgDatas" name="imgData" onchange="changeCyclePic('so',this);"  class="up shangchuan"></p>
							<img id="imgObject" ids="" src="" alt="" />
                        </div>
                        <div class="right" style="height:182px;">
                            <div class="title">配置信息</div>
                            <div class="set-infos">
                                 <p>
                                    <span class="t">上传图片：</span>
                                    <span class="btns">
                                     <input type="file" class="u-f" id="imgData" name="imgData" onchange="changeCyclePic('st',this);" accept="image/*">
                                        <a href="javascript:;" class="btn btn-default btn-mini c-uf">上传图片</a>
                                    </span>
                                    <span style="color:red;">上传图片尺寸为1400*350</span>
                                </p>
                                <p><span class="t">链接地址：</span><span>http://</span><input type="text" style="width: 185px;" id="cycleUrl" class="url"  value="" placeholder="请输入完整的网页地址"></p>
                                 <p><span class="t"></span>地址示例:http://yunduoketang.com/course</p>
                                <p><span class="t">是否启用：</span><i class="iconfont normal open">&#xe603;</i><span id="addStatus" class="i open">已启用</span></p>
                                <p>
                                    <span class='t'></span>
                                    <a href="javascript:addCyclePic();" class="btn btn-gray btn-submit">保存设置</a>
                                    <a href="javascript:clearData();" class="btn btn-default btn-cancel-add">取消添加</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </li>
                <!-- 新增结束 -->
            </ul>
        </div>
    </div>
</div>
    </div>
</div>
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenus('system_opencycle');
    	});
    </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/sys-focus.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>