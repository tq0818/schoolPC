<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>轮播图设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
        <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/pageafter.css"/>
    <style type="text/css">
    	a.btn-ico {
    	    text-decoration:none;
		    display: inline-block;
		    background: #00CC99;
		    color: #fff;
		    width: 30px;
		    height: 30px;
		    text-align: center;
		    position: absolute;
		    right: 40px;
		}
    </style>
     <script type="text/javascript" src="<%=rootPath%>/javascripts/popupwinChang.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/app/appBanner.js"></script>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_appconfig.jsp"></jsp:include>
    <div class="right-side">
     <div class="title-box">
          <div class="tit-font">
              <span class="t">轮播图设置</span>
              <select id="schoolList" style="margin-top: -5px;margin-left: 600px;" onchange="queryCycleData(this);">
              	<c:forEach items="${schoolList }" var="school1" varStatus="status">
		        		<c:if test="${school1.id==schoolId }">
		        			<option value="${school1.id }" selected="selected">${school1.schoolName }</option>
		        		</c:if>
		        		<c:if test="${school1.id!=schoolId }">
		        			<option value="${school1.id }">${school1.schoolName }</option>
		        		</c:if>
		            </c:forEach>
		            <c:if test="${empty schoolList }">
		            	<option value="${school1.id }" selected="selected">${school1.schoolName }</option>
		            </c:if>
              </select>
              <div class="addbtn" style="cursor: pointer;margin-top: -5px;float:right;"><a href="javascript:;" class="btn btn-primary" id="addfocus"><em class="iconfont">&#xe606;</em>添加一张轮播</a></div>
          </div>
      </div>
       	<div class="u-wrap set-system">
    <div class="mainbackground space">
		<input type="hidden" id="schoolId"/>
			<div>说明：手机网校首页展示轮播图。建议图片大小为640*330，格式支持jpg、png</div>
 
        <div class="set-focus">
            <ul id="cycleDataLists">
                <!-- 新增幻灯 -->
                <li class="add-focus" style="display: none;">
                    <div class="focus-title">新的幻灯片</div>
                    <div class="fileupload fileupload-new focus-content clear " data-provides="fileupload">
                        <div class="left" id="leftCont">
                        	<p class='tips-msg'>上传图片尺寸为640*330，<a href="javascript:;">点击上传</a><input type="file" id="imgDatas" name="imgData" accept="image/*" onchange="changeCyclePic('so',this);"  class="up shangchuan"></p>
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
                                    <span style="color:red;">上传图片尺寸为640*330</span>
                                </p>
                                <p>
                                <span class="t">链接地址：</span>
                                <span>
                                	<span style="width: 150px;" class="linNames"></span>
	                                <input type="text" style="display: none;" id="cycleUrl" class="url"  value="" placeholder="请输入完整的网页地址">
	                                <a href="javascript:;" class="bt btn-ico">...</a>
                                </span>
                                </p>
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
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>

<!-- 选择链接框 -->
<div class="link-choice-box chooseUrl" data-pupwin="modal">
    <div>
        <div class="link-tab-btn">
            <p class="active original" style="display: none;" mark="course">课程链接</p>
        </div>
        <div class="links-box">
            <div class="top-box">
                <div class="top-box-padd1">
                    <div class="link-line clear itemOne">
                        <p>学科</p>
                        <p class="cons_content">
                            
                        </p>
                    </div>
                    <div class="link-line clear itemTwo">
                        <p>学科小类</p>
                        <p class="cons_content">
                           
                        </p>
                    </div>
                    <div class="link-line clear classes">
                        <p>课程</p>
                        <p class="cons_content">
                           
                        </p>
                    </div>
                </div>
            </div>
            <div class="bottom-btn-box">
                <div class="pop-save" data-pupwin-btn="success">保存</div>
                <div class="pop-cancel" data-pupwin-btn="cancle">取消</div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="htmlType"  value="app_cycle"/>
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenus('app_cycle');
    	});
    </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/sys-focus.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
     <script type="text/javascript" src="<%=rootPath %>/javascripts/system/templeteCommon.js"></script> 
</body>
</html>