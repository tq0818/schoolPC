<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html >
<html>


<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>代理机构管理</title>
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
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<script src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
<script>
  	var rootPath = '<%=rootPath%>';
</script>
</head>


<body >
	<jsp:include page="/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/menu/menu_resource.jsp"></jsp:include>
	
	<!-- 二级导航 -->
	<div class="u-wrap company overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_proxy.jsp"></jsp:include>
	<div class="right-side proxyManage">
        <div>
            <div class="title-box">
                <div class="tit-font clear">
                    <span class="t">代理机构管理</span>
                    <a href="javascript:;" class="btn proxyBtn addproxy_">添加代理机构</a>
                </div>

            </div>
            <div class="proxyBox">
            <div class="invite-block invite_ewm_code" style="display:none;">
				<div id="qrcode" mark=""></div>
				<a  class="hiddenimg" href="">dowload</a>
            </div>
            <input type="hidden" value="${openFlag }" id="openFlag">
            <input type="hidden" value="${customSetting }" id="customSetting_">
                <form class="proxySearch">
                    <select name="status" id="proxystatus">
                        <option value="">代理状态</option>
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                    <select name="commissionRate" id="commissionRate">
                        <option value="">提成比例</option>
                        <option value="1">从高到低</option>
                        <option value="0">从低到高</option>
                    </select>
                    <span>创建日期：</span>
                    <input type="text" name="startTime"  class="date-picker from"  >
                    <b>至</b>
                    <input type="text" name="endTime"  class="date-picker to" >
                    <input type="text" name="name" id="name" class="proxyAgen" placeholder="代理机构名称">
                    <input type="submit"  value="搜索">
                </form>
                <table class="table">
                    <colgroup>
                        <col width="20%">
                        <col width="12%">
                        <col width="12%">
                        <col width="12%">
                        <col width="12%">
                        <col width="12%">
                        <col width="20%">
                    </colgroup>
                    <thead>
                        <th>代理机构名称</th>
                        <th>联系人</th>
                        <th>手机号</th>
                        <th>创建日期</th>
                        <th>提成比例</th>
                        <th>状态</th>
                        <th>操作</th>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
                <div class="pages L-pages">
                    <ul class="pagination">

                    </ul>
                </div>
            </div>
        </div>
    </div>
        </div>
<script type="text/javascript" src="<%=rootPath %>/plugins/qrcode/jquery.qrcode-0.12.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/canvasjs.min.js"></script>  
<script src="<%=rootPath%>/javascripts/company/proxylist.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/FileSaver.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>

</html>
