<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>在线直播</title>
    <%@include file="/decorators/import.jsp" %>
<script type="text/javascript">
        var rootPath='<%=rootPath%>';
</script>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/newhome.css" />
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/crypto.js"></script>
<script type="text/javascript">
function getHeight(){
	var url = decrypt($("#url").val()).split(",");
	$(document).on('click','.protocol',function(){
		window.location.href = url[0];
	}).on('click','.download',function(){
		window.location.href = url[1];
	}).on('click','.pronqq',function(){
		window.location.href = ("tencent://message/?uin=1480301768&Site=qq&Menu=yes"); 
	});
}
</script>
</head>
<body onload="javascript:getHeight();">
  
	<input type="hidden" value="${url }" id="url"/>

 <div class="width1200 box">
          <!-- header start -->
    <div class="header" style="padding:16px">
            <a href="#" title="" class="navbar-brand">
            <img src="../images/logo-2.png" height="50">
            </a>
    </div>
    <!-- header end -->
    <div class="main main-b clear">
        <ul class="top fl ">
            <li class="up ">
            <img src="../images/video.png">
            </li>

            <li class="mid">
            <a href="javascript:;" style="cursor: default;">已安装云直播客户端?</a>
            </li>

            <li class="dow">
             <a href="javascript:;" class="protocol">点击启动</a>
            </li>
           
        </ul>
        <ul class="bot fr ">
            <li class="up">
                <img src="../images/live.png">
            </li>
            <li class="mid">
             <a href="javascript:;" style="cursor: default;">第一次上课?</a>
            
            </li>
            <li class="dow">
              <a href="javascript:;" class="download">下载云直播</a>
            </li>
        </ul>

        <!-- <ul class="bot">
           
          
        </ul> -->
    </div>
    <!-- footer start -->

    <!-- footer end -->
</div>
    <!-- <script type="text/javascript" src="/javascripts/jquery.placeholder.min.js"></script> -->
    <!-- <script  type="text/javascript" src="/javascripts/login.js"></script> -->
    <!-- <script type="text/javascript" src="/plugins/jquery-validation/jquery.validate.js"></script> -->
</body>
</html>