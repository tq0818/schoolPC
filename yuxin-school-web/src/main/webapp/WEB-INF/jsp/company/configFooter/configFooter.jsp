<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>页尾配置</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascripts/company/footer.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugins/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/company/configFooter.js"></script>
    
</head>
<body>

<input id="companyId" value="${company.id }" type="hidden"/>
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
<jsp:include page="/WEB-INF/jsp/menu/menu_netschool.jsp"></jsp:include>
<div class="u-wrap set-system">
    <div class="heading">
        <h2 class="h5">效果预览</h2>
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
            <h2 class="h6">配置选项</h2>
        </div>
        <div class="set-config">
            <p class="c">
                <span>公司名称：</span>
                <input type="text" id="nameId" class="readonly" name="name" value="${company.companyName }" readonly tabindex="-1">
                <input type="button" value="修改" class="btn btn-sm btn-default">
            </p>
            <p class="c">
                <span>版权：</span>
                <input type="text" id="bqInfo" class="readonly" name="copyright" value="${company.copyright}" readonly tabindex="-1">
                <input type="button" value="修改" class="btn btn-sm btn-default">
            </p>
            <p class="c">
                <span>备案号：</span>
                <input type="text" id="baInfo" class="readonly" name="icp" value="${company.registNo}" readonly tabindex="-1">
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

</body>
</html>