<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>数据库差异比较</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css" />
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"> 
    
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    
    <script type="text/javascript">var rootPath = "<%=rootPath%>"</script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/database/compare.js"></script>
    
    <style>
        .url-form {
            width: 300px;
            padding-top: 10px;
        }
        
        #content table {
            width: 800px;
        }
        .row{
	    padding-left: 30%;
        }
        .url-form input {
            margin-bottom: 10px;
        }
       .row .url-form .form-control{
	    margin-bottom: 10px;
        }
        #submit {
            margin-left: 35%;
        }
        
        #showSQL {
            margin-left: 15%;
        }
        
        .q-icon {
            position: absolute;
            top: -10px;
            right: 0;
            width: 10px;
            height: 10px;
            color: #ccc;
        }
        #content table{
            margin-left: auto;
            margin-right: auto;
        }
        .loading{
            width: 32px;
            height: 32px;
            position: fixed;
            left:50%;
            top:50%;
            margin-left: -16px;
            margin-top: -16px;
            z-index: 10;
        }
        .loading img{
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <div class="full-wrap navbar smbar">
        <div class="header">    
            <a href="javascript:;" class="navbar-brand"><i class="iconfont"></i>管理控制台</a>
            <ul class="nav nav-left navspace">
                <li code="course_class_type"><a href="<%=rootPath %>/console/index">redis管理</a></li>
                <li code="course_class_type"><a href="<%=rootPath%>/console/marketing" >营销管理</a></li>
                <li code="course_module_no"><a href="<%=rootPath %>/task/manage" >手动更新定时任务</a></li>
                <li code="course_module_no"><a href="<%=rootPath %>/database/index" class="active" >数据库比对</a></li>
            </ul>
        </div>
    </div>
    <input class="form-control" value="jdbc:mysql://localhost:3306/compare?user=root&password=" style="display:none">
    <input class="form-control" value="jdbc:mysql://123.57.58.230:3306/yunduo_test?user=yuxin_user&password=yuuxin2016&useUnicode=true&characterEncoding=utf-8" style="display:none">
    <div class="row">
        <div class="url-form col-lg-6">
            <div id="url1" class="input-group">
                <label>url1-比较库</label>
                <input id="url1-ip" class="form-control" value="171.91.159.138" />
                <input id="url1-port" class="form-control" value="3306" />
                <input id="url1-database" class="form-control" value="lxmoocc" />
                <input id="url1-username" class="form-control" value="root" />
                <input id="url1-password" class="form-control" value="lxmoocc2020" />
            </div>
        </div>
        <div class="url-form col-lg-6">
            <div id="url2" class="input-group">
                <label>url2-标准库,以此链接为基准比较url1发生的变更</label>
                <input id="url2-ip" class="form-control" value="123.57.58.230" />
                <input id="url2-port" class="form-control" value="3306" />
                <input id="url2-database" class="form-control" value="yunduo_test" />
                <input id="url2-username" class="form-control" value="yuxin_user" />
                <input id="url2-password" class="form-control" value="yuuxin2016" />
            </div>
        </div>
    </div>
    <button id="submit" type="button" class="btn btn-default">比较</button>
    <button id="showSQL" type="button" class="btn btn-default">显示sql</button>
    <div id="content"></div>
    <div id="delets" style="display:none">
        <span>删除的表</span>
        <ul id="delets" class="list-group"></ul>
    </div>
    
    <div id="sqlContent" style="display:none;position:fixed;left:150px;top:150px;z-index:9">
    	<i class="iconfont q-icon" style="">&#xe6be;</i>
        <span>sql:</span>
        <textarea rows=20 cols=180></textarea>
    </div>
    <div class="loading" style="display:none">
        <img src="../images/jia.jpg" alt="">
    </div>
</body>
</html>

