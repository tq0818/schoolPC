<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>自定义页面管理</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/custom-page.css"/> 
    <style type="text/css">
    	.ui-state-highlight{
    		height: 30px;
    		border: dashed 1px #ced7fd;
    		background: #adbdfc; 
    	}
    </style> 
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company clear">
    <jsp:include page="/WEB-INF/jsp/menu/menu_systemconfig.jsp"></jsp:include>
     <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">站内编辑</span>
                </div>
            </div>
            <input type="hidden" id="cusPageId" value="${customPage.id }"/>
            <input type="hidden" id="cusPageContent" value="${customPage.content }"/>
            <input type="hidden" id="cusPageTempId" value="${customPage.templateId }"/>
            <input type="hidden" id="cusPageGroupId" value="${customPage.groupId }"/>
            <div class="add-page-pop11">
                <div class="">
                    <div class="pop-input-box clear">
                        <div>
                            <div>标题</div>
                            <div class="editarea">
                                <input type="text" id="customTitle" value="${customPage.title }" maxlength="12"/>
                            </div>
                        </div>
                        <div>
                            <div>自定义域名</div>
                            <div class="custom-domain">
                                /custom/page/<input type="text" id="customUrl" value="${customPage.url }" maxlength="40"/>
                            </div>
                        </div>
                        <div>
                            <div>页面模板</div>
                            <div>
                                <select name="" id="customTypeList" onchange="changeTemplete();" style="width: 130px;">

                                </select>
                            </div>
                        </div>
                        <div>
                            <div>页头页尾</div>
                            <div>
                                <select name="" id="isContainStatus" style="width: 130px;">
                                	<c:choose>
                                		<c:when test="${!empty customPage }">
                                			<c:choose>
                                				<c:when test="${customPage.includeHeadFoot==1 }">
                                					<option value="1" selected="selected">包含</option>
                                    				<option value="0">不包含</option>
                                				</c:when>
                                				<c:otherwise>
                                					<option value="1">包含</option>
                                    				<option value="0" selected="selected">不包含</option>
                                				</c:otherwise>
                                			</c:choose>
                                		</c:when>
                                		<c:otherwise>
                                			<option value="1" selected="selected">包含</option>
                                    		<option value="0">不包含</option>
                                		</c:otherwise>
                                	</c:choose>
                                </select>
                            </div>
                        </div>
                        <div id="setWidthLable">
                            <div>是否通屏</div>
                            <div>
                                <select name="" id="customWidthSet">
                                	<c:choose>
                                		<c:when test="${!empty customPage }">
                                			<c:choose>
                                				<c:when test="${customPage.screenFlag==1 }">
                                					<option value="0">不通屏</option>
                                    				<option value="1" selected="selected">通屏</option>
                                				</c:when>
                                				<c:otherwise>
                                					<option value="0" selected="selected">不通屏</option>
                                    				<option value="1">通屏</option>
                                				</c:otherwise>
                                			</c:choose>
                                		</c:when>
                                		<c:otherwise>
                                			<option value="0" selected="selected">不通屏</option>
                                    		<option value="1">通屏</option>
                                		</c:otherwise>
                                	</c:choose>
                                </select>
                            </div>
                        </div>
                    </div>
                    <p class="yellow-care">注：请输入2-12个字符</p>
                    <div class="text-title" style="line-height: 40px;font-size: 12px;color: #333;"><span>正文</span><em id="showTemplete">预览</em></div>
                    <div id="customTypeContent">
                    
                    </div>
                    <div class="pop-bottom-btn">
                        <span id="saveCustom_pageContent" class="aa">保存</span>
                        <span class="cancel-btn">取消</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!--创建新模板-->
<div class="add-page-pop po1 addTemplateCustom">
    <div class="add-temp-board">
        <p class="page-pop-title"><span>创建新模板组</span><em class="iconfont">&#xe610;</em></p>
        <div class="small-pop-cont">
            <div class="two-line">
                <p>请输入模板组名称</p>
                <p><input type="text" id="template_Name" placeholder="请输入12字以内的模板组名称" maxlength="12"/></p>
            </div>
        </div>
        <div class="btn-box">
            <div class="addTemplateGroup" class="aa">确定</div>
            <div class="cancle_btn">返回</div>
        </div>
    </div>
</div>
<!--编辑模板-->
<div class="add-page-pop po2 editTemplateCustom">
    <div class="add-temp-board">
        <p class="page-pop-title"><span>编辑模板组</span><em class="iconfont">&#xe610;</em></p>
        <div class="small-pop-cont">
            <div class="one-line">
            	<input type="hidden" value="" id="g_Id"/>
                <input type="text" id="edit_template_Name" placeholder="请输入12字以内的模板组名称" maxlength="12"/>
            </div>
        </div>
        <div class="btn-box">
            <div class="editTemplateGroup">确定</div>
            <div class="cancle_btn">返回</div>
        </div>
    </div>
</div>
<!--确定删除模板-->
<div class="add-page-pop po3">
    <div class="add-temp-board">
        <p class="page-pop-title"><span></span><em class="iconfont">&#xe610;</em></p>
        <div class="small-pop-cont">
            <div class="one-line">
                确定要删除模板？
            </div>
        </div>
        <div class="btn-box">
            <div>确定删除</div>
            <div class="cancle_btn">返回</div>
        </div>
    </div>
</div>
<!--输入模板组名称-->
<div class="add-page-pop po4">
    <div class="add-temp-board">
        <p class="page-pop-title"><span></span><em class="iconfont">&#xe610;</em></p>
        <div class="small-pop-cont">
            <div class="one-line">
                请先输入模板组名称
            </div>
        </div>
        <div class="btn-box">
            <div class="cancle_btn">返回编辑</div>
        </div>
    </div>
</div>
<!--移除模板内的页面再删除模板-->
<div class="add-page-pop po5">
    <div class="add-temp-board">
        <p class="page-pop-title"><span></span><em class="iconfont">&#xe610;</em></p>
        <div class="small-pop-cont">
            <div class="one-line">
                请先移除模板内的页面再删除模板
            </div>
        </div>
        <div class="btn-box">
            <div class="cancle_btn">返回</div>
        </div>
    </div>
</div> 
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
	<script type="text/javascript" src="<%=rootPath %>/javascripts/system/customManage.js"></script>
 	<script type="text/javascript" src="<%=rootPath %>/javascripts/custom-page.js"></script>
 	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
 	<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
</body>
</html>