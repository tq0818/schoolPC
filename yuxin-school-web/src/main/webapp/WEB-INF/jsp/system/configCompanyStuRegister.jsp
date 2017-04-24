<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
	<title>学员注册设置</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/minitip.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/system.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/splitscreen.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage-screen.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/student-register.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css" />
	<style>
	.open {
		color: #0099ff;
	}
	.close {
		color: #666;
	}
	.screen-right-cont .close {
    	color: #666;
	}
	.screen-right-cont .open {
    	color: #0099ff;
	}
	.screen-right-cont h2.l-title {
	    font-size: 14px;
	    color: #333;
	    margin-left: 20px;
	    border-bottom: none;
	    margin-top: 20px;
	    padding-bottom: 10px;
	}
	.L-right-side{
	    float: right;
	    width: 81%;
	    min-height: 725px;
	    border: 1px solid #e9edee;
	    background-color: #f6f6f6;
	    margin-bottom: 50px;
	}
	</style>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/configCompanyStuRegister.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var editor = CKEDITOR.replace('newsContents');
			editor.config.width="920";
			editor.config.height="220";
			editor.config.resize_minHeight = "220";
			editor.config.resize_maxHeight = "220";
			editor.config.allowedContent = true;
			editor.config.format_p = { element: 'p', attributes: { 'class': 'normalPara' } };
			editor.config.toolbar = [
				[ 'mode', 'document', 'doctools' ], [ 'Source', '-', 'NewPage' ] ,
				[ 'basicstyles', 'cleanup' ],
				[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript' ] ,
				[ 'list', 'indent', 'blocks', 'align', 'bidi' ],  
				[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
				[ 'Link', 'Unlink' ] ,
				[ 'Image', 'Table' ],
				[ 'Styles', 'Format', 'Font', 'FontSize' ],
			    [ 'TextColor', 'BGColor' ],
				[ 'Maximize'] ,
				[ '-' ] ,
				[ 'About' ]
				];
			editor.config.baseFloatZIndex = 10100;
			var desc = $("#registerAgreement").val();
			var desc1 = decodeURI(desc);
			var detailDesc = desc1.replace("\r\n",
					"<br>&nbsp;&nbsp;");
			editor.setData(detailDesc);
			editor.config.customConfig = 'config.js';
		});
		
	</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap company clear">
		<jsp:include page="/WEB-INF/jsp/menu/menu_studentconfig.jsp"></jsp:include>
		<div class="right-side L-right-side">
			<div>
				<div class="title-box">
					<div class="tit-font">
						<span class="t">学员注册设置</span>
					</div>
					<div class="q_cont">
						<p class="p1">说明：此功能可以设置网校的注册方式，所选项作为学员登录网校的唯一标识</p>
						<p class="p2">学员注册方式:</p>
						<ul class="q_list">
							<li class="q_li"><input type="checkbox" id="mobile" class="checkbox" kind="mobile"><span>手机号注册(默认)</span></li>
							<li class="q_li"><input type="checkbox" id="username" class="checkbox" kind="username"><span>用户名注册</span></li>
							<li class="q_li"><input type="checkbox" id="close" class="checkbox" kind="close"><span>关闭注册</span></li>
							<li class="li_last">注意：选择了"关闭注册"将无法通过网校地址进行注册</li>
						</ul>
					</div>
					<%-- <div class="screen-right-cont">
						 <h2 class="l-title">
							<i>邮箱注册验证</i>
							<c:choose>
								<c:when test="${crc.validateEmailFlag == 1}">
									<em class="iconfont normal open emailFlag">&#xe603;</em>
									<span id="" class="i open">已启用</span>
								</c:when>
								<c:otherwise>
									<em class="iconfont normal close emailFlag">&#xe604;</em>
									<span id="" class="i close">已禁用</span>
								</c:otherwise>
							</c:choose>
						</h2>
					  <p class="p-p1">说明：关闭此功能后,用户使用邮箱注册只需要填写邮箱即可注册成功</p>
					</div> --%>
					<div class="screen-right-cont">
						<h2 class="l-title">
							<i>学员注册协议</i>
							<c:choose>
								<c:when test="${crc.registerAgreementFlag == 1}">
									<em class="iconfont normal open">&#xe603;</em>
									<span id="" class="i open" kind="useLoginPage">已启用</span>
								</c:when>
								<c:otherwise>
									<em class="iconfont normal close">&#xe604;</em>
									<span id="" class="i close" kind="useLoginPage">已禁用</span>
								</c:otherwise>
							</c:choose>
						</h2>
						<div id = "registerAgreement_">
							<p class="c clear">
					            <textarea class="ckeditor form-control" id="newsContents" name="content" rows="6" data-error-container="#editor2_error"></textarea>
			                </p>
			                <p class="c text-center" style="margin-top: 10px;">
			                    <a href="javascript:void(0);" class="btn btn-primary">保存</a>
			                    <a href="<%=rootPath%>/companyFunctionSet/showRegister" class="btn btn-default" target="_blank">预览</a>
			                </p>
		                </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="mobileFlag" value='${crc.mobileFlag }' />
	<input type="hidden" id="usernameFlag" value='${crc.usernameFlag }' />
	<input type="hidden" id="closeFlag" value="${crc.closeFlag }" />
	<!-- 注册协议 -->
<%-- 	<input type="hidden" id="registerAgreement" value="${crc.registerAgreement }" /> --%>
	<textarea style="display:none" id="registerAgreement">${crc.registerAgreement }</textarea>
	<textarea style="display:none" id="registerAgreementDef">${def.registerAgreement }</textarea>
<%-- 	<input type="hidden" id="registerAgreementDef" value="${def.registerAgreement }" /> --%>
	<input type="hidden" id="registerAgreementFlag" value="${crc.registerAgreementFlag }" />
</body>
</html>