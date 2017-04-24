<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>积分设置</title>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/integral/points.css"/>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <%@include file="/WEB-INF/jsp/menu/menu_integral.jsp" %>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">积分设置</span>
                </div>
            </div>
            <div class="point-set">
                <div class="set-system">
                    <div class="clear point-set-itm">
                        <div class="fl l-label">积分名称：</div>
                        <div class="info-set fl">
                            <input type="text" class="point-name" data-id="${cic.id}" <c:if test="${!empty cic.name}">value="${cic.name}"</c:if><c:if test="${empty cic.name}">value="积分"</c:if> maxlength="10"/>
                            <em>这里输入积分名称：如虚拟币，学习币等。最多输入10个字</em>
                        </div>
                    </div>
                    <div class="clear point-set-itm">
                        <div class="fl l-label">积分图标：</div>
                        <div class="info-set fl">
                            <div class="l-pic-box">
                            	<c:if test="${!empty cic.ico}">
                            		<img id="showImg" src="${commodityPicUrl}${cic.ico}" data-url="${cic.ico}"/>
                            	</c:if>
                            	<c:if test="${empty cic.ico}">
                            		<img id="showImg" src="<%=rootPath%>/images/jifen.png" alt=""/>
                            	</c:if>
                                <div class="trans">更换</div>
                            </div>

                            <input type="file" accept="image/*" id="imgData" name="imgData" onchange="javascript:upFile();" style="opacity:0;width:68px;display: inline-block;position: absolute;left: 0;top:4px;margin-left: 15px;cursor: pointer;filter:alpha(opacity = 0);" />
                            <button class="l-upload">上传</button>
                            <em>*说明:上传文件格式为ico类型,文件大小不能大于32×32。</em>
                        </div>
                    </div>
                    <div class="clear point-set-itm">
                        <div class="fl l-label">积分说明：</div>
                        <div class="info-set fl">
                            <textarea class="fl ckeditor" name="content" id="description" rows="6" data-error-container="#editor2_error" placeholder="这里输入积分说明">${cic.description}</textarea>
                        </div>

                    </div>
                     <div class="clear point-set-itm" style="margin-top:-10px;margin-bottom:-15px">
  						<p style="text-align:right;font-size:12px;color:#999;">*说明:积分说明最多100字</p>
                    </div>
                    <div class="clear point-set-itm">
                        <div class="fl l-label">积分兑换：</div>
                        <div class="info-set fl change-rate">
                            <i>设置积分与人民币兑换比率</i>
                            <input type="text" class="change-rate1" value="1" readonly="readonly"/>
                            <i>人民币兑换</i>
                            <input type="text" class="change-rate2" <c:if test="${!empty cic.exchangeScale}">value="${cic.exchangeScale}"</c:if> maxlength="6"/>
                            <i>积分</i>
                        </div>
                    </div>
                    
                    <div class="btn-wrap">
                        <button class="keep-btn btn btn-sm btn-primary save-but">保存</button>
                        <button class="cancel-btn btn btn-sm btn-default">取消</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript">
var cicDescription = "${cic.description}";
$(function(){
	$selectSubMenu('org_service');
	$chooseMenu('integralSet');
});
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/integral/integralSet.js"></script>
</body>
</html>