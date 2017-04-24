<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>页尾配置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
        <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
     <script type="text/javascript" src="<%=rootPath %>/javascripts/company/footer.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/company/configFooter.js"></script>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_systemconfig.jsp"></jsp:include>
    <div class="right-side">
<c:forEach items="${foots }" var="foot">
	<c:if test="${foot.name=='关于我们' }">
		<input id="about" value="${foot.content }" type="hidden"/>
	</c:if>
	<c:if test="${foot.name=='联系我们' }">
		<input id="connect" value="${foot.content }" type="hidden"/>
	</c:if>
	<c:if test="${foot.name=='隐私条款' }">
		<input id="privacy" value="${foot.content }" type="hidden"/>
	</c:if>
	<c:if test="${foot.name=='法律声明'||foot.name=='爱亲公益' }">
		<input id="legal" value="${foot.content }" type="hidden"/>
	</c:if>
</c:forEach>
       	<div class="u-wrap set-system">
    <div class="heading">
        <h2 class="h5" style="font-size: 14px;">效果预览</h2>
    </div>
    <div class="full-wrap">
        <div class="footer">
            <div class="copyright" id="copyrightList">
               
            </div>
            <div class="links">
                <p>
                    <a href="javascript:;">关于我们</a>
                    <a href="javascript:;">联系我们</a>
                    <a href="javascript:;">隐私条款</a>
                    <a href="javascript:;">法律声明</a>
                </p>
            </div>
        </div>
    </div>
</div>

<div class="u-wrap set-system">
    <div class="mainbackground">
        <div class="title">
            <h2 class="h6" style="font-size: 14px;">配置选项</h2>
        </div>
        <div class="set-config">
            <p class="c">
                <span>公司名称：</span>
                <input type="text" id="nameId" class="readonly" style="min-width: 200px;cursor: default;" name="name" value="${company.companyName }" readonly tabindex="-1">
                <input type="button" value="修改" class="btn btn-sm btn-default">
            </p>
            <p class="c">
                <span>版权：</span>
                <input type="text" id="bqInfo" class="readonly" name="copyright" style="min-width: 200px;cursor: default;" value="${company.copyright}" readonly tabindex="-1">
                <input type="button" value="修改" class="btn btn-sm btn-default">
            </p>
            <p class="c">
                <span>备案号：</span>
                <input type="text" id="baInfo" class="readonly" name="icp" style="min-width: 200px;cursor: default;" value="${company.registNo}" readonly tabindex="-1">
                <input type="button" value="修改" class="btn btn-sm btn-default">
            </p>
        </div>
        <div class="set-about">
            <div class="about-tabs">
                <p id="about-tabs">
                    <a class="active aa" href="javascript:;">关于我们</a>
                    <a href="javascript:;">联系我们</a>
                    <a href="javascript:;">隐私条款</a>
                    <a href="javascript:;">法律声明</a>
                </p>
            </div>
            <div class="about-edit"  id="aboutUs">
                <!-- <p>编辑器内容</p> -->
                <textarea class="ckeditor form-control" id="newsContents" name="content" rows="6" data-error-container="#editor2_error"></textarea>
            </div>
                <div class="subbtns" id="aboutUsButton">
                <br/>
		            <a href="javascript:;" class="btn btn-big btn-default" onclick="cancel(1);">取消保存</a>
		            <a href="javascript:;" class="btn btn-big btn-success" onclick="save(1);">提交保存</a>
        		</div>
            <div class="about-edit" style="display:none"  id="conectUs">
                <!-- <p>编辑器内容</p> -->
                <textarea class="ckeditor form-control" id="newsContents0"  name="newsContents" rows="6" data-error-container="#editor2_error"></textarea>
            </div>
                <div class="subbtns" id="conectUsButton" style="display:none">
                <br/>
		            <a href="javascript:;" class="btn btn-big btn-default" onclick="cancel(2);">取消保存</a>
		            <a href="javascript:;" class="btn btn-big btn-success" onclick="save(2);">提交保存</a>
        		</div>
            <div class="about-edit" style="display:none" id="privacyTerms">
                <!-- <p>编辑器内容</p> -->
                <textarea class="ckeditor form-control" id="newsContents1" name="newsContents" rows="6" data-error-container="#editor2_error"></textarea>
            </div>
                <div class="subbtns" id="privacyTermsButton" style="display:none">
                <br/>
		            <a href="javascript:;" class="btn btn-big btn-default" onclick="cancel(3);">取消保存</a>
		            <a href="javascript:;" class="btn btn-big btn-success" onclick="save(3);">提交保存</a>
        		</div>
            <div class="about-edit" style="display:none" id="legalStatement">
                <!-- <p>编辑器内容</p> -->
                <textarea class="ckeditor form-control" id="newsContents2" name="newsContents" rows="6" data-error-container="#editor2_error"></textarea>
            </div>
                <div class="subbtns" id="legalStatementButton" style="display:none">
                <br/>
		            <a href="javascript:;" class="btn btn-big btn-default" onclick="cancel(4);">取消保存</a>
		            <a href="javascript:;" class="btn btn-big btn-success" onclick="save(4);">提交保存</a>
        		</div>
            </div>
		</div>
        
    </div>
    </div>
</div>
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenus('system_foot');
    	});
    </script>
</body>
</html>