<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>如何开通微信公众号</title>
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
	 <%@include file="/WEB-INF/jsp/company/payConfig/commonLeft.jsp"%>
	 <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">如何开通微信公众号</span>
                </div>
            </div>
            <div class="padd-3">
                <div class="step-font"><span>STEP01</span> 进入微信的官方网站：</div>
                <div class="step-url">- http://weixin.qq.com/点击【公众平台】如下图所示：</div>
                <div class="step-pic">
                    <img src="../images/u87.png" alt="" width="800px"/>
                </div>
                <div class="step-font"><span>STEP02</span>  如无账号请点击【立即注册】如下图所示：</div>
                <div class="step-pic">
                    <img src="../images/u95.png" alt="" width="800px"/>
                </div>
                <div class="step-font"><span>STEP03</span>  输入您要的邮箱地址，和登陆微信公众平台的密码。点击【注册】</div>
                <div class="step-pic">
                    <img src="../images/u101.png" alt="" width="800px"/>
                </div>
                <div class="step-font"><span>STEP04</span>  激活公众平台帐号感谢注册！确认邮件已发送至您的注册邮箱 :xxxx@qq.com。请进入邮箱查看邮件，并激活公众平台帐号。</div>
                <div class="step-pic">
                    <img src="../images/u107.png" alt="" width="800px"/>
                </div>
                <div class="step-font"><span>STEP05</span>  进入您的邮箱以后会收到一封激微信账号的邮件，进入邮件后请点击以下链接激活帐号</div>
                <div class="step-pic">
                    <img src="../images/u113.png" alt="" width="602px"/>
                </div>
                <div class="step-font"><span>STEP06</span>  输入您的相关相关信息，需要是真实并有效的资料。</div>
                <div class="step-pic">
                    <img src="../images/u119.png" alt="" width="383"/>
                </div>
                <div class="step-font"><span>STEP07</span>  微信类型个人只可选择一项，选中即可</div>
                <div class="step-font"><span>STEP08</span>  公众号信息中的账号名称设置后无法更改，请慎重填写。其他资料请根据个人真实情况填写</div>
                <div class="step-pic">
                    <img src="../images/u125.png" alt="" width="388"/>
                </div>
                <div class="step-font"><span>STEP09</span>  注册成功后即可登录微信公众平台</div>
                <div class="step-pic">
                    <img src="../images/u129.jpg" alt="" width="800px"/>
                </div>
            </div>
            <div class="back-btn-box">
                <div class="back-btn" onclick="javascript:location.href='<%=rootPath%>/payConfig/showWXGZ'">返回</div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	$(".wx").addClass("active");
	$selectSubMenu('org_service');
})
</script>
</body>
</html>