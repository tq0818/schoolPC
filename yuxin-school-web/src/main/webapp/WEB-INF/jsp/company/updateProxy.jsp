<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>添加代理机构</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath %>/stylesheets/fatstyle.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath %>/stylesheets/manage.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath %>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/Acinf.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/resource.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/utils.css">
<script src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
<script>
  	var rootPath = '<%=rootPath%>';
  	var pid = '${pid}';
  	var provinceid = '${provinceid}';
  	var cityid = '${cityid}';
  	var prefixu = '${prefixu}';
</script>
</head>


<body >
	<jsp:include page="/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/menu/menu_resource.jsp"></jsp:include>
	
	<!-- 二级导航 -->
	<div class="u-wrap company overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_proxy.jsp"></jsp:include>
	<script>
	$(function(){
		$selectSubMenus('proxy');
	})
	</script>
	  <div class="right-side proxyManage">
        <div>
            <div class="title-box">
                <div class="tit-font clear">
                    <span class="t">代理机构管理</span>
                </div>

            </div>
            <div class="proxyBox">
                <form id="proxyForm" action="">
                <input type="hidden" name="id" value="${proxy.id }">
                <input type="hidden" name="address"/>
                    <ul class="addproxy">
                        <li class="clear">
                             <h4 class="fl"><i>*</i>代理机构名称：</h4>
                            <div class="fl">
                                <input type="text" placeholder="这里是代理机构名称" name="name" class="agentName" maxlength="15" value="${proxy.name }">
                            </div>
                        </li>
                        <li class="clear">
                            <h4 class="fl">公司名称：</h4>
                            <div class="fl">
                                <input type="text" placeholder="这里是公司名称" name="companyName" class="agentName" maxlength="255" value="${proxy.companyName }">
                            </div>
                        </li>
                        <li class="clear">
                            <h4 class="fl">公司地址：</h4>
                            <div class="fl">
                                <select name="province" id="province">
                                    <option value="">请选择</option>
                                </select>
                                <select name="city" id="city">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </li>
                        <li class="clear">
                            <h4 class="fl">详细地址：</h4>
                            <div class="fl">
                                <input type="text" name="detailAddress" value="${proxy.detailAddress }" maxlength="220" class="detailAddress">
                            </div>
                        </li>
                        <li class="clear">
                            <h4 class="fl"><i>*</i>联系人：</h4>
                            <div class="fl">
                                <input type="text" name="contracter" value="${proxy.contracter }" maxlength="20" class="contact">
                            </div>
                        </li>
                        <li class="clear">
                            <h4 class="fl"><i>*</i>手机号码：</h4>
                            <div class="fl">
                                <input type="text" name="contractNumber" value="${proxy.contractNumber }" maxlength="11" class="contact">
                            </div>
                        </li>
                        <li class="clear">
                            <h4 class="fl">E-mail：</h4>
                            <div class="fl">
                                <input type="text" name="contractEmail" value="${proxy.contractEmail }" maxlength="255" class="contact">
                            </div>
                        </li>
                        <li class="clear">
                            <h4 class="fl">备注：</h4>
                            <div class="fl">
                                <textarea name="remark1" id="remark" class="detailAddress" value="${proxy.remark}">${proxy.remark}</textarea>
                            </div>
                        </li>
                        <input  type="hidden" name="remark">
                        <li class="clear">
                            <h4 class="fl"><i>*</i>代理商前缀 ：</h4>
                            <div class="fl">
                                <input id="prefix1" type="text" name="prefix" value="${proxy.prefix }" class="detailAddress" maxlength="4">
                           		<p style="line-height: 1;font-size: 12px;color: #999; padding-top: 10px;">说明：只能是英文字母a-zA-Z，限4个字母，不区分大小写，不可与其他代理商前缀重复，此代理商前缀用于创建学习卡等内容时使用。</p>
                            </div>
                        </li>
                        <li class="clear" id="customsetting">
                            <h4 class="fl">提成比例个性化设置：</h4>
                            <div class="fl percent">
                                <input type="text" value="${proxy.commissionRate }" name="commissionRate" id="commissionRate" maxlength="11"><span>%</span>
                                <p>说明：如果不填写或填写0，则以设定的统一提成比例为准，提成按实际支付的金额计算。</p>
                            </div>
                        </li>
                    </ul>
                    <div class="optionBut">
                        <input type="button" value="取消" class="cancel">
                        <input type="button" value="保存" class="save">
                    </div>
                </form>
            </div>
        </div>
    </div>
        </div>
	<script src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
	<script src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script src="<%=rootPath%>/javascripts/company/addproxy.js"></script>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>

</html>
