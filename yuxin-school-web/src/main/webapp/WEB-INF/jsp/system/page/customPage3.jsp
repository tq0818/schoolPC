<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<% 
	String rootPath=request.getContextPath();
	/*String rootPath="//statics.yunduoketang.com/front";*/ 
	String rp=request.getContextPath();
%>
<head>

	<title id="showTemplate"></title>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript">var rootPath=''</script>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath%>/stylesheets/custom/register/fatstyle.css" />
    <link rel="stylesheet"  tydde="text/css" href="<%=rootPath%>/stylesheets/custom/register/agreementOld.css" />
	<style type="text/css">
        .background-gray div.class-menu {
            margin: 5px auto 5px;
            padding: 10px 0;
            background-color: #fff;
            min-height: 500px;
        }
    </style>
</head>
<body>
<div style="min-height: 600px;">
	<div id="showTemplateContent" class="wrap class-menu">
		${crc.registerAgreement }
	</div>
</div>
</body>
</html>