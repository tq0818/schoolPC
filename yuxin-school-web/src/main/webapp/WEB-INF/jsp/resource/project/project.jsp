<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>学科</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/stylesheets/classes.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/TagManagement.css">
	<style type="text/css">
		img{cursor: pointer;}
		.main-content .block .b-title .tt .h3 b {
        	font-size: 18px;
		    padding: 15px 52px 5px 0px;
		    display: block;
		}
		.tagBtn{
			margin-left: 47px;
   			margin-top: 15px;
   			background: #23B7E5;
		}
		.clicked{
			background: #00CCCC;
			color:white;
		}
		.tagManage{
		    position: fixed;
		    left: 20%;
		    top: 10%;
		    z-index: 999;
		    background-color: #fff;
	    }
	</style>
	
<style>
     .main-content { 
	     list-style-type: none;
	      margin: 30px auto !important; 
	      padding: 0; 
	      width: 1080px; 
      }
     .ui-state-highlight {
         border-style:dashed;
         background: transparent;
     }
     .sortable-sec{
         margin-left: 25px;
     }
</style>
</head>
<body>
	<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
	<input type="hidden" value="${ImagePath }" id="imgurl"/>
	<div class="u-wrap resource">
		<div class="mainbackground nopadding">
			<div class="heading">
	            <h2 class="h5">学科</h2>
	            <span class="line"></span>
	        </div>
	        <div id="loadData"></div>
		</div>
	</div>
	
<script type="text/javascript">
$(function(){
	var project = {};
	project.ajax = function(url,dataInfo,func){
		 $.ajax({ 
	 		  type: "post", 
	 		  url : rootPath+url, 
	 		  data: dataInfo,
	 		  success: func,
	 	  });
	};
	project.queryOneItem = function(){
		this.ajax("/sysConfigItem/ajaxProject", {}, function(data){
			$("#loadData").html(data);
		});
	};
	project.queryOneItem();
});
</script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>

</body>
</html>